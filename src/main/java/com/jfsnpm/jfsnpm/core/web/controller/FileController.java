package com.jfsnpm.jfsnpm.core.web.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.gson.JsonObject;
import com.jfinal.aop.Before;
import com.jfinal.config.JFinalConfig;
import com.jfinal.core.Controller;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.upload.UploadFile;
import com.jfsnpm.jfsnpm.core.dao.User;
import com.jfsnpm.jfsnpm.core.service.UserService;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.core.util.JfsnpmException;
import com.jfsnpm.jfsnpm.plugin.bjui.BjuiRender;
import com.jfsnpm.jfsnpm.plugin.bjui.JfsnpmFileRender;
import com.jfsnpm.jfsnpm.plugin.bjui.NameComparator;
import com.jfsnpm.jfsnpm.plugin.bjui.SizeComparator;
import com.jfsnpm.jfsnpm.plugin.bjui.TypeComparator;

public class FileController extends Controller {
	public void upload(){
		User userinfor = UserService.getUserInfor(this);
		UploadFile uploadFile = getFile();
		File file = uploadFile.getFile();
		String fileType = getPara("type","public");
		String releKey = getPara("rele","xxx");
		String fileName = getPara("fileName","none");
		String fileId = AppHelper.getUUID();
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		File filePath = new File(uploadFile.getUploadPath()+File.separator+fileType+File.separator+releKey+File.separator);
		if(!filePath.exists()){
			filePath.mkdirs();
		}
		//移动位置
		File fileNew = new File(uploadFile.getUploadPath()+File.separator+fileType+File.separator
				+releKey+File.separator+fileId+"."+fileExt);
		file.renameTo(fileNew);
		
		Record record = new Record().set("id", fileId)
				.set("fileName", fileName).set("fileType", fileType)
				.set("releKey", releKey).set("modifier", userinfor.getUserId())
				.set("modifyDate", AppHelper.getNow())
				.set("filePath", fileNew.getPath());
		if(Db.save("jfsnpm_file", record)){
			render(BjuiRender.success("上传成功", false));
		}else{
			render(BjuiRender.error("上传失败"));
		}
		
	}
	public void download(){
		String id = getPara("id");
		if(AppHelper.isEmpty(id)){
			renderHtml("{\"statusCode\":\"300\",\"message\":\"参数有误\"}");
			return;
		}
		Record record = Db.findById("jfsnpm_file", id);
		String filePath = record.getStr("filePath");
		String fileName = record.getStr("fileName");
		if(AppHelper.isEmpty(filePath)){
			renderHtml("{\"statusCode\":\"300\",\"message\":\"文件地址有误\"}");
			return;
		}
		File file = new File(filePath);
		if (file == null || !file.exists()||file.isDirectory()){
			renderHtml("{\"statusCode\":\"300\",\"message\":\"文件没有找到\"}");
			return;
		}
		render(new JfsnpmFileRender(file, fileName));
	}
	//PDF文件签名
	/*public void filesign(){
		User userinfor = UserService.getUserInfor(this);
		if(AppHelper.isEmpty(userinfor.getUserId())||
				AppHelper.isEmpty(userinfor.getUserMail())){
			render(BjuiRender.error("用户信息不完整"));
			return;
		}
		String id = getPara("id");
		if(AppHelper.isEmpty(id)){
			render(BjuiRender.error("参数有误"));
			return;
		}
		Record record = Db.findById("jfsnpm_file", id);
		String filePath = record.getStr("filePath");
		String fileName = record.getStr("fileName");
		if(AppHelper.isEmpty(filePath)){
			render(BjuiRender.error("文件地址有误"));
			return;
		}
		String prefix = fileName.toLowerCase().substring(fileName.lastIndexOf(".")+1);
		if(!"pdf".equals(prefix)){
			render(BjuiRender.error("只能对PDF文件进行签名"));
			return;
		}
		File file = new File(filePath);
		if (file == null || !file.exists()||file.isDirectory()){
			render(BjuiRender.error("文件没有找到"));
			return;
		}
		//String fileNameSign = fileName.substring(0, fileName.length()-4)+"_sign.pdf";
		String idSign = AppHelper.getUUID();
		String fileNameSignL = idSign+".pdf";
		File fileSign = new File(file.getParent()+File.separator+fileNameSignL);
		try {
			//File fileimg = new File("");
			signPdf sign = new signPdf(userinfor.getUserMail(), userinfor.getUserId(), file,fileSign);
			//sign.setVisible(true, fileimg, 0, 0, 1, 1);
			sign.signature();
		} catch (Throwable e) {
			throw new JfsnpmException(e.getMessage());
		}
		record//.set("id", idSign).set("fileName", fileNameSign)
			.set("filePath", fileSign.getPath())
			.set("modifier", userinfor.getUserId())
			.set("modifyDate", AppHelper.getNow());
		if(Db.update("jfsnpm_file", record)&&file.delete()){
			render(BjuiRender.success("签名成功！", false));
		}else{
			render(BjuiRender.error("签名成功，但插入数据库文件记录失败！"));
		}
	}*/
	//简单文件查看删除
	public void list() throws UnsupportedEncodingException{
		String path=getPara("path","");
		path = URLDecoder.decode(path, "UTF-8");
		String basePath = new File(JFinal.me().getConstants().getBaseUploadPath()).getPath();
		if(AppHelper.isEmpty(path)){
			path = basePath;
		}
		if(path.indexOf(basePath)!=0){
			render(BjuiRender.error("文件路径不合法！", false));
			return;
		}
		//文件查看
		File d = new File(path);
		if(!d.exists()){
			render(BjuiRender.error("文件路径不存在！", false));
			return;
		}
		if(d.isDirectory()){
			File list[] = d.listFiles();
			setAttr("list", list);
			setAttr("parentPath",d.getParent());
			setAttr("thisPath","\\"+d.getPath().substring(basePath.length()));
		}else{
			renderFile(d);
			return;
		}
	}
	public void listdelete() throws UnsupportedEncodingException{
		String path=getPara("path","");
		path = URLDecoder.decode(path, "UTF-8");
		String basePath = new File(JFinal.me().getConstants().getBaseUploadPath()).getPath();
		if(AppHelper.isEmpty(path)){
			path = basePath;
		}
		if(path.indexOf(basePath)!=0){
			render(BjuiRender.error("路径不合法！", false));
			return;
		}
		File d = new File(path);
		if(!d.exists()||d.isDirectory()){
			render(BjuiRender.error("路径不存在！", false));
			return;
		}
		if(!d.delete()){
			render(BjuiRender.error("文件删除失败！", false));
			return;
		}
		render(BjuiRender.success("删除成功！", false));
	}
	
	//系统管理的文件管理
	public void filelist(){
		String type = getPara("type");
		String id = getPara("rele","xxx");
		String auth = getPara("auth","");
		if(!(!AppHelper.isEmpty(type)||
				"public".equals(type)||
				"private".equals(type)||
				"project".equals(type)||
				"org".equals(type))){
			render(BjuiRender.error("没有指定正确的类型"));
			return;
		}
		setAttr("type", type);
		setAttr("rele", id);
		if("project".equals(type)){
			setAttr("auth",auth);
			render("filelist_project.html");
		}
	}
	public void filelist_get(){
		String type = getPara("type");
		String id = getPara("rele","xxx");
		if(!(!AppHelper.isEmpty(type)||
				"public".equals(type)||
				"private".equals(type)||
				"project".equals(type)||
				"org".equals(type))){
			render(BjuiRender.error("没有指定正确的类型"));
			return;
		}
		renderJson(Db.find("select * from jfsnpm_file where fileType = ? and releKey = ? ", type,id));
	}
	@Before(Tx.class)
	public void filelist_delete(){
		String json = getPara("json");
		List<HashMap> list = AppHelper.getObjectsFromJson(json, HashMap.class);
		for(Map<String,Object> data:list){
			String id = (String) data.get("id");
			String filePath = Db.findById("jfsnpm_file", id).getStr("filePath");
			Db.update("DELETE FROM jfsnpm_file WHERE id = ?", id);
			File file = new File(filePath);
			if (file == null || !file.exists()){
				
			}else{
				if(!file.delete()){
					throw new JfsnpmException("删除文件失败");
				}
			}
		}
		render(BjuiRender.success("删除成功", false));
	}
	/**
	 * kindeditor文件管理
	 */
	public void kupload(){
		UploadFile uploadFile = getFile();
		File file = uploadFile.getFile();
		String dir = getPara("dir","image");
		String daypath = AppHelper.getNow("yyyyMMdd");
		String fileId = AppHelper.getNow("yyyyMMddHHmmss")+ "_" + new Random().nextInt(1000);
		String fileName = file.getName();
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		File filePath = new File(uploadFile.getUploadPath()+File.separator+"kindeditor"+File.separator+
				dir+File.separator+daypath+File.separator);
		if(!filePath.exists()){
			filePath.mkdirs();
		}
		//移动位置
		File fileNew = new File(uploadFile.getUploadPath()+File.separator+"kindeditor"+File.separator+
				dir+File.separator+daypath+File.separator+fileId+"."+fileExt);
		file.renameTo(fileNew);
		//setAttr("error", 0);
		//setAttr("url", JFinal.me().getContextPath()+"/kdownload/"+fileNew.getName());
		renderJson("{\"error\":0,\"url\":\""+JFinal.me().getContextPath()+"/File/kdownload?dir="+dir+"&filename="+fileNew.getName()+"\"}");
		//renderJson();
	}
	public void kdownload(){
		String dir = getPara("dir","image");
		String fileName = getPara("filename");
		String filePath = JFinal.me().getConstants().getBaseUploadPath()+"kindeditor"+File.separator+
				dir+File.separator+fileName.substring(0, 8);
		System.out.println(filePath);
		File file = new File(filePath+File.separator+fileName);
		renderFile(file);
	}
	public void kmanage(){
		String dir = getPara("dir","image");
		String path = getPara("path","");
		String order = getPara("order","NAME");
		String moveupDirPath = "";
		String filePath = JFinal.me().getConstants().getBaseUploadPath()+"kindeditor"+File.separator+
				dir+File.separator;
		if(!AppHelper.isEmpty(path)){
			filePath = filePath + path ;
		}
		//不允许使用..移动到上一级目录
		if (filePath.indexOf("..") >= 0) {
			renderText("Access is not allowed.");
			return;
		}
		//目录不存在或不是目录
		File currentPathFile = new File(filePath);
		if(!currentPathFile.isDirectory()){
			renderText("["+path+"]Directory does not exist.");
			return;
		}
		order = order.toLowerCase();
		//图片扩展名
		String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};
		//遍历目录取的文件信息
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		if(currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if(file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if(file.isFile()){
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				fileList.add(hash);
			}
		}
		//排序
		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		String currentUrl = JFinal.me().getContextPath()+"/File/kdownload?dir="+dir+"&filename=";
		
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", path);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);
		renderJson(result);
		
	}
	
}
