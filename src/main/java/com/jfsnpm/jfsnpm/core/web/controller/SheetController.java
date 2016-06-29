package com.jfsnpm.jfsnpm.core.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import com.jfsnpm.jfsnpm.core.dao.User;
import com.jfsnpm.jfsnpm.core.service.UserService;
import com.jfsnpm.jfsnpm.core.util.ApiResult;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.core.util.StringUtils;
import com.jfsnpm.jfsnpm.core.vo.PaginationVO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yangchuanhuan on 16/5/14.
 */
//@ControllerBind(controllerKey="Sheet")
public class SheetController extends BaseController {

    public static final Logger log = Logger.getLogger(SheetController.class);

    public void list(){

    }
    /**
     * 获取联单列表
     */
    public void getSheetList(){
        Record params = getParams();
        int pageSize = Integer.valueOf(params.get("pageSize", 10).toString());
        int pageNum = Integer.valueOf(params.get("pageNum", 1).toString());
        StringBuffer selectClause = new StringBuffer("SELECT * from (SELECT list.*,FROM_UNIXTIME(list.update_time,'%Y-%m-%d %H:%i:%S') as update_time_fromat,(SELECT c.`name` from fst_company c WHERE c.id = list.produce_company_id) produce_company_name, (SELECT c.`name` from fst_company c WHERE c.id = list.transport_company_id) transport_company_name,(SELECT c.`name` from fst_company c WHERE c.id = list.receive_company_id) receive_company_name ");
        selectClause.append(" from fst_linked_list list where status = 1 ) t ");
        StringBuffer whereClause = new StringBuffer("where 1 =1 ");

        StringBuffer countClause = new StringBuffer("SELECT count(1) num from (SELECT list.*,(SELECT c.`name` from fst_company c WHERE c.id = list.produce_company_id) produce_company_name, (SELECT c.`name` from fst_company c WHERE c.id = list.transport_company_id) transport_company_name,(SELECT c.`name` from fst_company c WHERE c.id = list.receive_company_id) receive_company_name  from fst_linked_list list where status = 1 ) t ");

        String produce_company_name = params.getStr("produce_company_name");
        String transport_company_name = params.getStr("transport_company_name");
        String receive_company_name = params.getStr("receive_company_name");
        if (!StringUtils.isNullString(produce_company_name)) {
            whereClause.append(" and t.produce_company_name like '%")
                    .append(produce_company_name).append("%'");
        }
        if(!StringUtils.isNullString(transport_company_name)){
            whereClause.append(" and t.transport_company_name like '%")
                    .append(transport_company_name).append("%'");
        }
        if (!StringUtils.isNullString(receive_company_name)) {
            whereClause.append(" and t.receive_company_name like '%")
                    .append(receive_company_name).append("%'");
        }

        List<Record> sheetList = Db.find(selectClause.append(whereClause).append(" order by t.update_time desc ").append("limit ").append((pageNum-1)*pageSize).append(",").append(pageSize).toString());
        Record record = Db.findFirst(countClause.append(whereClause).append(" and status = 1 ").toString());
        PaginationVO vo = new PaginationVO();
        vo.setPageCurrent(pageNum);
        vo.setTotal(record.getLong("num"));
        vo.setList(sheetList);
        renderJson(vo);
    }

    /**
     * 跳转到联单编号列表页
     */
    public void sheetnumList(){
        render("sheet_num_list.html");
    }

    /**
     * 跳转到联单编号编辑页
     */
    public void delSheetNum(){
        ApiResult result = new ApiResult();
        String id = getPara("id");
        if(!StringUtils.isNullString(id)){
            Db.update("update fst_linked_list_num set status = ? where id = ?", -1, id);
        }
        result.setSuccessData(true);
        renderJson(result);
    }

    public void sheetnumEdit(){
        render("sheet_num_edit.html");
    }
    /**
     * 保存分配联单编号
     */
    public void linkedListNumEdit(){
        ApiResult result = new ApiResult();
        String area_code =getPara("area_code");
        String linked_list_num  = getPara("linked_list_num");
        /*Jedis jedis = RedisPlugin.getJedis();
        String newSheetNum = jedis.get("newsestSheetNum");
        //说明是第一次分配
        if(StringUtils.isNullString(newSheetNum)){
            jedis.set
        }*/
        Record beginRecord = Db.findFirst("SELECT max(n.sheet_num) as num_begin FROM fst_linked_list_num n where n.`status` = 1 ");

        int begin_int_num = 1;
        //如果num_begin为null,则说明是第一次分配
        if(null != beginRecord.getInt("num_begin")){
            int num_begin = beginRecord.getInt("num_begin");
            begin_int_num = num_begin+1;
        }

        int end_int_num = begin_int_num+Integer.valueOf(linked_list_num)-1;
        Record sheet_num_record = new Record();
        String begin = AppHelper.parseObjectToFormatNum(begin_int_num);
        String end = AppHelper.parseObjectToFormatNum(end_int_num);
        String id = AppHelper.getUUID();
        sheet_num_record.set("id", id);
        sheet_num_record.set("area_code", area_code);
        sheet_num_record.set("sheet_num_begin", begin);
        sheet_num_record.set("current_num", begin);
        sheet_num_record.set("sheet_num_end", end);
        sheet_num_record.set("sheet_num", end_int_num);
        sheet_num_record.set("count", linked_list_num);
        sheet_num_record.set("create_time", System.currentTimeMillis()/1000);
        Db.save("fst_linked_list_num", sheet_num_record);
        result.setSuccessData(true);
        renderJson(result);
    }

    /**
     * 获取联单编号列表
     */
    public void getSheetNumList(){
        Record params = getParams();
        int pageSize = Integer.valueOf(params.get("pageSize", 10).toString());
        int pageNum = Integer.valueOf(params.get("pageNum", 1).toString());
        Page page = Db.paginate(pageNum, pageSize, "select n.id,n.area_code,(select x.xzqh_name from xzqh x where x.xzqh_code = n.area_code) as area_name,FROM_UNIXTIME(n.create_time,'%Y-%m-%d %H:%i:%s') as create_time, count, CONCAT(sheet_num_begin,'-',sheet_num_end) AS num_area ", " from fst_linked_list_num n where n.status = ? order by n.create_time desc ", 1);
        PaginationVO vo = new PaginationVO();
        vo.setPageCurrent(pageNum);
        vo.setTotal(page.getTotalRow());
        vo.setList(page.getList());
        renderJson(vo);
    }
    /**
     * 获取公司combo
     */
    public void getCompanyCombo(){
        ApiResult result = new ApiResult();
        List<Record> list = Db.find("select c.id, c.name from fst_company c");
        result.setSuccessData(list);
        renderJson(result);
    }

    /**
     *
     */
    /**
     * 获取危险垃圾Combo
     */
    public void getRubbishDangerous(){
        ApiResult result = new ApiResult();
        List<Record> list = Db.find("select r.* from fst_rubbish_info r ");
        result.setSuccessData(list);
        renderJson(result);
    }

    /**
     * 获取危险类别combo
     */
    public void getDangerousCategory(){
        ApiResult result = new ApiResult();
        List<Record> list = Db.find("SELECT DISTINCT d.`code`, d.`name` from fst_dangerous d WHERE d.pcode IS NULL ORDER BY d.`code`");
        result.setSuccessData(list);
        renderJson(result);
    }

    /**
     * 通过id获取危险物名称
     */
    public void getRubbishById(){
        ApiResult result = new ApiResult();
        String id = getPara("rubbishId");
        if(StringUtils.isNullString(id)){
            result.setExceptionData("params id can not be null ");
        }else{
            Record info = Db.findFirst("select r.* from fst_rubbish_info r where r.id = ?", id);
            result.setSuccessData(info);
        }
        renderJson(result);
    }
    /**
     * 通过公司id获取公司信息
     */
    public void getCompanyInfoById(){
        ApiResult result = new ApiResult();
        String companyId = getPara("companyId");
        Record record = Db.findFirst("select * from fst_company c where c.id = ?", companyId);
        result.setSuccessData(record);
        renderJson(result);
    }

    /**
     * 上传附件
     */
    public void uploadAttachment(){
        ApiResult result = new ApiResult();
        String path = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        UploadFile uploadFile = getFile("attachment");
        File source = uploadFile.getFile();
        String sourcefileName = uploadFile.getFileName();
        String fileName = sourcefileName;
        String fileId = AppHelper.getUUID();
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        File filePath = new File(uploadFile.getUploadPath()+File.separator);
        if(!filePath.exists()){
            filePath.mkdirs();
        }
        //移动位置
        File fileNew = new File(uploadFile.getUploadPath()+File.separator+fileId+"."+fileExt);
        source.renameTo(fileNew);
        JSONObject json = new JSONObject();
        try {
            json.put("fileName", sourcefileName);
            json.put("url", fileNew.getPath());
            source.delete();
            result.setSuccessData(json);
        } catch (Exception e) {
            json.put("message", "文件写入服务器出现错误，请稍后再上传");
            result.setExceptionData(json);
        }
        renderJson(result);

    }

    /**
     * 打开附件预览窗口
     */
    public void attachmentOverview(){
        String  url = getPara("url");
        setAttr("attachment_url", url);
        render("attachment_overview.html");
    }
    public void getOverviewImg(){
        String  url = getPara("url");
        File imageFile = new File(url);
        try {
            InputStream is = new FileInputStream(imageFile);
            BufferedInputStream bis = new BufferedInputStream(is);
            OutputStream os = getResponse().getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            byte[] buf = new byte[2048];
            int length = bis.read(buf);
            while (length != -1){
                os.write(buf);
                length = bis.read(buf);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 联单新增
     */
    public void add(){
        String sheet_id = getPara("sheet_id");
        if(!StringUtils.isNullString(sheet_id)){
            Record sheet = Db.findFirst("SELECT list.*,(SELECT c.`name` from fst_company c WHERE c.id = list.produce_company_id) produce_company_name, (SELECT c.`name` from fst_company c WHERE c.id = list.transport_company_id) transport_company_name,(SELECT c.`name` from fst_company c WHERE c.id = list.receive_company_id) receive_company_name,(select r.rubbish_name from fst_rubbish_info r where r.id = list.rubbish_name) as rubbish_name  from fst_linked_list list where list.id = ?", sheet_id);
            setAttr("sheet", sheet);
        }
        render("sheet_edit.html");
    }
    /**
     * 联单详情
     */
    public void detail(){
        String sheet_id = getPara("sheet_id");
        if(!StringUtils.isNullString(sheet_id)){
            Record sheet = Db.findFirst("SELECT list.*,(SELECT c.`name` from fst_company c WHERE c.id = list.produce_company_id) produce_company_name, (SELECT c.`name` from fst_company c WHERE c.id = list.transport_company_id) transport_company_name,(SELECT c.`name` from fst_company c WHERE c.id = list.receive_company_id) receive_company_name,(select r.rubbish_name from fst_rubbish_info r where r.id = list.rubbish_name) as rubbish_name  from fst_linked_list list where list.id = ?", sheet_id);
            setAttr("sheet", sheet);
        }
        render("sheet_detail.html");
    }

    public void delSheet(){
        ApiResult result = new ApiResult();
        String id = getPara("id");
        Db.update("update fst_linked_list set status = ? where id = ?", -1, id);
        result.setSuccessData(true);
        renderJson(result);
    }
    /**
     * 保存联单
     * 联单编号不允许修改
     * @throws IOException
     */
    public void linkedListEdit() throws IOException {
        ApiResult result = new ApiResult();
//        String json_tmp = getStringFromStream(getRequest());
        Record record = getParams();

        boolean flag = false;
        record.set("update_time", System.currentTimeMillis()/1000);
        User user = UserService.getUserInfor(this);
        if(StringUtils.isNullString(record.getStr("id"))){
            String dangerous_category = record.getStr("dangerous_category");
            if(StringUtils.isNullString(dangerous_category)){
                result.setExceptionData("危险废物类别不能为空....");
                renderJson(result);
                return;
            }
            record.remove("dangerous_category");
            String id = AppHelper.getUUID();
            record.set("id", id);

           //获取行政区划码
            String area_code = user.getCounty();
            Integer sheet_num = 1;
            //判断是否是廊坊市级用户,如果是市级用户则自动获取未分配的联单号
            Record beginRecord = null;
            if("131000".equals(area_code)){
                beginRecord = Db.findFirst("SELECT max(n.sheet_num) as num_begin FROM fst_linked_list_num n WHERE n.`status` = 1");
                sheet_num = beginRecord.getInt("num_begin")+1;
                //更新num表,新增一条自动分配联单编号记录
                Record numRecord = new Record();
                numRecord.set("id", AppHelper.getUUID());
                numRecord.set("area_code", "131000");
                numRecord.set("sheet_num_begin", AppHelper.parseObjectToFormatNum(sheet_num));
                numRecord.set("sheet_num_end", AppHelper.parseObjectToFormatNum(sheet_num));
                numRecord.set("sheet_num", sheet_num);
                numRecord.set("create_time", System.currentTimeMillis()/1000);
                numRecord.set("count", 1);
                numRecord.set("current_num", (sheet_num%9999) ==0?9999:(sheet_num%9999));
                Db.save("fst_linked_list_num ",numRecord);
            }else{
                //获取该行政区划下的联单编号开始位置和分配的编号最大值,进行比较
                beginRecord = Db.findFirst("SELECT max(n.sheet_num) as num_begin,n.current_num FROM fst_linked_list_num n WHERE n.`status` = 1 and n.area_code=?", area_code);
                String sheet_num_end =  beginRecord.getStr("sheet_num_end");
                Integer current_num = beginRecord.getInt("current_num");
                if(current_num > Integer.valueOf(sheet_num_end)){
                    result.setExceptionData("联单编号已经使用完,请向上级环保局申请联单编号");
                    renderJson(result);
                    return;
                }
                //更新num表的current_num值,current_num++;
                Db.update("update fst_linked_list_num set current_num = current_num + 1");
                sheet_num = current_num;
            }

            String linked_list_num = AppHelper.parseObjectToFormatNum(sheet_num);
            linked_list_num="1130"+dangerous_category.substring(2)+linked_list_num;
            record.set("linked_list_num", linked_list_num);
            record.set("create_user_name", user.getUserName());
            record.set("create_user_id", user.getUserId());
            flag = Db.save("fst_linked_list",record);
        }else{
            Db.update("fst_linked_list", record);
        }
        result.setSuccessData(flag);
        renderJson(result);
    }
    /**
     * 从请求中获取
     * Content-Type:application/json格式请求的参数
     * @param request
     * @return
     * @throws IOException
     */
    public String getStringFromStream(HttpServletRequest request) throws IOException {
        //①表示请求的内容区数据为json数据
        InputStream is = request.getInputStream();
        byte bytes[] = new byte[request.getContentLength()];
        is.read(bytes);
        //②得到请求中的内容区数据（以CharacterEncoding解码）
        //此处得到数据后你可以通过如json-lib转换为其他对象
        String jsonStr = new String(bytes, request.getCharacterEncoding());
        bytes = null;
        return jsonStr;
    }
    public void download(){
        String path = getPara(0);
        String img = PathKit.getWebRootPath() + "/img/u/" + path.replaceAll("_", "/");
//        ZipUtil.zip(img, PathKit.getWebRootPath() + "/img/temp/" + path);
        renderFile("/img/temp/" + path + ".zip");
    }

    private String generateWord() {
        String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7",
                "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z" };
        List<String> list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(3, 9);
        return result;
    }
}
