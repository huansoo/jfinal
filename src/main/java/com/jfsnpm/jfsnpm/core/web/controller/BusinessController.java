package com.jfsnpm.jfsnpm.core.web.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfsnpm.jfsnpm.core.dao.Org;
import com.jfsnpm.jfsnpm.core.dao.User;
import com.jfsnpm.jfsnpm.core.service.FlowService;
import com.jfsnpm.jfsnpm.core.service.MenuService;
import com.jfsnpm.jfsnpm.core.service.OrgService;
import com.jfsnpm.jfsnpm.core.service.RoleService;
import com.jfsnpm.jfsnpm.core.service.UserService;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.core.util.JfsnpmException;
import com.jfsnpm.jfsnpm.plugin.bjui.Bjui;
import com.jfsnpm.jfsnpm.plugin.bjui.BjuiRender;

public class BusinessController extends Controller {
	private final Logger logger = LoggerFactory.getLogger(BusinessController.class); 
	private static SimpleDateFormat YMDFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 流程表单
	 */
	public void companyManager(){}
	public void companyManager_get(){
		renderJson(Db.find(AppHelper.getSql("business.getCompanyList")));
	}
	public void companyManager_edit(){
		String json = getPara("json");
		if(Bjui.use().update("fst_company", json,"id->id",false,"")){
			renderJson(Bjui.returnSuccessJson(json));
		}else{
			render(BjuiRender.error("保存失败！"));
		}
	}
	public void companyManager_delete(){
		String json = getPara("json");
		if(Bjui.use().delete("jfsnpm_form_h", json, "id")){
			render(BjuiRender.success("删除成功！", false));
		}else{
			render(BjuiRender.error("删除失败！"));
		}
	}
	
	/**
	 * 客户管理编辑修改
	 */
	public void company() {
		String id = getPara("id");
		logger.info("the request param is id:{}", id);
		if (StringUtils.isEmpty(id)) {
			setAttr("operation", "add");
		} else {
			setAttr("operation", "edit");
			setAttr("company", (Db.find(AppHelper.getSql("business.getCompanyById"), id)));
		}
	}
	public void company_edit(){
		String operation = getPara("operation"); 
		String id = getPara("id");
		String no = getPara("no");
		String name = getPara("name");
		String province = getPara("province");
		String city = getPara("city");
		String county = getPara("county");
		String address = getPara("address");
		String type = getPara("type");
		String postcode = getPara("postcode");
		String telephone = getPara("telephone");
		String mobile = getPara("mobile");
		String email = getPara("email");
		String legalperson = getPara("legalperson");
		String ratepayerno = getPara("ratepayerno");
		String accountowner = getPara("accountowner");
		String account = getPara("account");
		String principal = getPara("principal");
		String dangerous = getPara("custom.dangerous");
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("[{");
		sBuffer.append("\"id\":\"").append(id).append("\",");
		sBuffer.append("\"no\":\"").append(no).append("\",");
		sBuffer.append("\"name\":\"").append(name).append("\",");
		sBuffer.append("\"province\":\"").append(province).append("\",");
		sBuffer.append("\"city\":\"").append(city).append("\",");
		sBuffer.append("\"county\":\"").append(county).append("\",");
		sBuffer.append("\"address\":\"").append(address).append("\",");
		sBuffer.append("\"type\":\"").append(type).append("\",");
		if("产废单位".equals(type)){
			String receiveCompany = getPara("receiver");
			sBuffer.append("\"receiveCompany\":\"").append(receiveCompany).append("\",");
		}
		sBuffer.append("\"dangerous\":\"").append(dangerous).append("\",");
		sBuffer.append("\"postcode\":\"").append(postcode).append("\",");
		sBuffer.append("\"telephone\":\"").append(telephone).append("\",");
		sBuffer.append("\"mobile\":\"").append(mobile).append("\",");
		sBuffer.append("\"email\":\"").append(email).append("\",");
		sBuffer.append("\"legalperson\":\"").append(legalperson).append("\",");
		sBuffer.append("\"ratepayerno\":\"").append(ratepayerno).append("\",");
		sBuffer.append("\"accountowner\":\"").append(accountowner).append("\",");
		sBuffer.append("\"account\":\"").append(account).append("\",");
		sBuffer.append("\"principal\":\"").append(principal).append("\",");
		sBuffer.append("\"timecreated\":\"").append(YMDFORMAT.format(new Date()));
		if("add".equals(operation)){
			sBuffer.append("\",").append("\"addFlag\":true").append("}]");
		} else {
			sBuffer.append("\"").append("}]");
		}
		
		String json = sBuffer.toString();
		if(Bjui.use().update("fst_company", json,"id->id",false,"")){
			render(BjuiRender.success("操作成功！", true));
		}else{
			render(BjuiRender.error("操作失败！"));
		}
	}
	
	/**
	 * 根据企业类型获取企业列表
	 */
	public void listCompanyByType(){
		String type = getPara("type");
		logger.info("list company by type.[type={}]", type);
		renderJson(Db.find(AppHelper.getSql("business.getCompanyByType"), type));
	}
	
	public void listCompanyById(){
		String id = getPara("id");
		renderJson(Db.find(AppHelper.getSql("business.getCompanyById"), id));
	}
	
	/**
	 * 行政区划
	 */
	public void getCitys(){
		String code = getPara("code"); 
		if(StringUtils.isEmpty(code)){
			renderJson(Db.find(AppHelper.getSql("xzqh.getProvinces")));
		} else {
			renderJson(Db.find(AppHelper.getSql("xzqh.getByParentCode"), code));
		}
	}
	
	public void getByCode(){
		String code = getPara("code"); 
		if(!StringUtils.isEmpty(code)){
			renderJson(Db.find(AppHelper.getSql("xzqh.getByCode"), code));
		}
	}
	
	//危废管理
	public void dangerous(){
		
	}
	
	public void dangerous_get(){
		renderJson(Db.find(AppHelper.getSql("business.getPDangerous")));
	}
	
	public void dangerous_edit(){
		User user = UserService.getUserInfor(this);
		String json = getPara("json");
		List<HashMap> list = AppHelper.getObjectsFromJson(json, HashMap.class);
		for (HashMap data : list) {
			boolean newRecordYN = (data.containsKey("addFlag")&&(Boolean) data.get("addFlag"));
			if(newRecordYN){//新增
				data.put("createby", user.getUserNo());
				data.put("createtime", YMDFORMAT.format(new Date()));
				json = AppHelper.object2Json(data);
			}
		}
		
		if(Bjui.use().update("fst_dangerous", json,"id->id",false,"")){
			renderJson(Bjui.returnSuccessJson(json));
		}else{
			render(BjuiRender.error("保存失败！"));
		}
	}
	
	public void dangerous_delete(){
		String json = getPara("json");
		if(Bjui.use().delete("fst_dangerous", json, "id")){
			render(BjuiRender.success("删除成功！", false));
		}else{
			render(BjuiRender.error("删除失败！"));
		}
	}
	
	
	public void dangerous_list(){
		String id = getPara("id");
		String pcode = Db.findById("fst_dangerous", id).get("code");
		setAttr("id", id);
		setAttr("pcode",pcode);
	}
	public void dangerous_list_get(){
		String pcode = getPara("pcode");
		renderJson(Db.find(AppHelper.getSql("business.getListByPCode"), pcode));
	}
	public void dangerous_list_edit(){
		String pcodeString = getPara("pcode");
		User user = UserService.getUserInfor(this);
		String json = getPara("json");
		List<HashMap> list = AppHelper.getObjectsFromJson(json, HashMap.class);
		for (HashMap data : list) {
			boolean newRecordYN = (data.containsKey("addFlag")&&(Boolean) data.get("addFlag"));
			if(newRecordYN){//新增
				data.put("createby", user.getUserNo());
				data.put("createtime", YMDFORMAT.format(new Date()));
				data.put("pcode", pcodeString);
				data.put("id", "");
				json = AppHelper.object2Json(data);
			}
		}
		if(Bjui.use().update("fst_dangerous", json,"id->id",false,"")){
			renderJson(Bjui.returnSuccessJson(json));
		}else{
			render(BjuiRender.error("保存失败！"));
		}
	}
	public void dangerous_list_delete(){
		String json = getPara("json");
		if(Bjui.use().delete("fst_dangerous", json, "id")){
			render(BjuiRender.success("删除成功！", false));
		}else{
			render(BjuiRender.error("删除失败！"));
		}
	}
	
	public void dangerous_select(){
		String code = getPara("code");
		List<Record> results = null;
		if(StringUtils.isEmpty(code)){
			results = Db.find(AppHelper.getSql("business.getAllDangerous"));
		} else {
			setAttr("code", code);
			code = "%" + code + "%";
			results = Db.find(AppHelper.getSql("business.getListByCode"), code);
			if(results == null || results.size()<0){
				render(BjuiRender.error("无数据！"));
			}
		}
		List<Map<String, String>> lists = new ArrayList<Map<String,String>>();
		for(Record r:results){
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", r.getStr("id"));
			map.put("createtime", r.getStr("createtime"));
			map.put("createby", r.getStr("createby"));
			map.put("name", r.getStr("name"));
			map.put("code", r.getStr("code"));
			map.put("pcode", r.getStr("pcode"));
			lists.add(map);
		}
		setAttr("dangerList", lists);
	}
	
	/**
	 * 菜单维护
	 */
	public void function(){
		setAttr("menuList", MenuService.getMenuAll());
	}
	public void menu_role(){
		String id = getPara("id");
		setAttr("id", id);
	}
	public void menu_role_get(){
		String id = getPara("id");
		renderJson(Db.find(AppHelper.getSql("menu.getMenuRole"), id));
	}
	@Before(Tx.class)
	public void menu_role_edit(){
		String id = getPara("id");
		String json = getPara("json");
		if(Bjui.use().update("jfsnpmr_menu_role", json,"roleId,menuId->menuId->"+id,true,"roleId,menuId")){
			renderJson(Bjui.returnSuccessJson(json));
		}else{
			render(BjuiRender.error("保存失败！"));
		}
	}
	@Before(Tx.class)
	public void menu_role_delete(){
		String json = getPara("json");
		if(Bjui.use().delete("jfsnpmr_menu_role", json, "roleId,menuId")){
			render(BjuiRender.success("删除成功！", false));
		}else{
			render(BjuiRender.error("删除失败！"));
		}
	}
	@Before(Tx.class)
	public void menu_save() throws JfsnpmException {
		if(MenuService.updateMenu(getPara("menus"), getPara("deleteids"))){
			render(BjuiRender.success("保存成功！",false));
		}else{
			render(BjuiRender.error("保存失败！"));
		}
	}
}
