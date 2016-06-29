package com.jfsnpm.jfsnpm.core.web.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfsnpm.jfsnpm.core.dao.User;
import com.jfsnpm.jfsnpm.core.service.FlowService;
import com.jfsnpm.jfsnpm.core.service.MenuService;
import com.jfsnpm.jfsnpm.core.service.OrgService;
import com.jfsnpm.jfsnpm.core.service.RoleService;
import com.jfsnpm.jfsnpm.core.service.UserService;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.core.util.JfsnpmException;
import com.jfsnpm.jfsnpm.core.util.MD5Util;
import com.jfsnpm.jfsnpm.plugin.bjui.Bjui;
import com.jfsnpm.jfsnpm.plugin.bjui.BjuiRender;
import org.apache.commons.lang.StringUtils;

public class SystemController extends Controller {
	private static SimpleDateFormat YMDFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 菜单维护
	 */
	public void menu(){
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
	/**
	 * 组织维护
	 */
	public void org(){
		setAttr("menuList", OrgService.getOrgAll());
	}
	@Before(Tx.class)
	public void org_save() throws JfsnpmException {
		if(OrgService.updateOrg(getPara("menus"), getPara("deleteids"))){
			render(BjuiRender.success("保存成功！",false));
		}else{
			render(BjuiRender.error("保存失败！"));
		}
	}
	public void org_user(){
		String id = getPara("id");
		setAttr("id", id);
	}
	public void org_user_get(){
		String id = getPara("id");
		//renderJson(Db.find(AppHelper.getSql("org.getOrgUser"), id));
		renderJson(Bjui.use().page(AppHelper.getSql("org.getOrgUser"), this, id));
	}
	@Before(Tx.class)
	public void org_user_edit(){
		String id = getPara("id");
		String json = getPara("json");
		if(Bjui.use().update("jfsnpmr_org_user", json,"userId,orgId->orgId->"+id,true,"userId,orgId")){
			renderJson(Bjui.returnSuccessJson(json));
		}else{
			render(BjuiRender.error("保存失败！"));
		}
	}
	@Before(Tx.class)
	public void org_user_delete(){
		String json = getPara("json");
		if(Bjui.use().delete("jfsnpmr_org_user", json, "orgId,userId")){
			render(BjuiRender.success("删除成功！", false));
		}else{
			render(BjuiRender.error("删除失败！"));
		}
	}
	
	/**
	 * 用户管理
	 */
	public void user(){
	}
	public void user_get(){
		renderJson(Db.find(AppHelper.getSql("user.getUserALL")));
	}
	/**
	 * 客户管理编辑修改
	 */
	public void editUser() {
		String id = getPara("id");
		if (StringUtils.isEmpty(id)) {
			setAttr("operation", "add");
		} else {
			setAttr("operation", "edit");
			setAttr("company", (Db.find(AppHelper.getSql("user.getUserByUserId"), id)));
		}
	}
	public void user_edit(){
		String operation = getPara("operation");
		String id = getPara("id");
		String userNo = getPara("userNo");
		String userName = getPara("userName");
		String province = getPara("province");
		String city = getPara("city");
		String county = getPara("county");
		String status = getPara("status");
		String userMail = getPara("userMail");
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("[{");
		sBuffer.append("\"id\":\"").append(id).append("\",");
		sBuffer.append("\"userNo\":\"").append(userNo).append("\",");
		sBuffer.append("\"userName\":\"").append(userName).append("\",");
		sBuffer.append("\"province\":\"").append(province).append("\",");
		sBuffer.append("\"city\":\"").append(city).append("\",");
		sBuffer.append("\"county\":\"").append(county).append("\",");
		sBuffer.append("\"status\":\"").append(status).append("\",");
		sBuffer.append("\"userMail\":\"").append(userMail);
		if("add".equals(operation)){
			User user = UserService.getUserInfor(this);
			sBuffer.append("\",\"createdby\":\"").append(user.getUserId());
			sBuffer.append("\",\"timelastupdate\":\"").append(YMDFORMAT.format(new Date()));
			sBuffer.append("\",\"timecreated\":\"").append(YMDFORMAT.format(new Date()));
			sBuffer.append("\",").append("\"addFlag\":true").append("}]");
		} else {
			sBuffer.append("\",\"timelastupdate\":\"").append(YMDFORMAT.format(new Date()));
			sBuffer.append("\"").append("}]");
		}

		String json = sBuffer.toString();

		List<HashMap> list = AppHelper.getObjectsFromJson(json, HashMap.class);
		for (HashMap data : list) {
			boolean newRecordYN = (data.containsKey("addFlag")&&(Boolean) data.get("addFlag"));
			if(newRecordYN){//新增
				data.put("password", MD5Util.string2MD5("p@ssw0rd"));
				json = AppHelper.object2Json(data);
			}
		}

		if(Bjui.use().update("jfsnpm_user", json,"id->id",false,"")){
			render(BjuiRender.success("操作成功！", true));
		}else{
			render(BjuiRender.error("操作失败！"));
		}
	}

	public void user_delete(){
		String json = getPara("json");

		List<HashMap> list = AppHelper.getObjectsFromJson(json, HashMap.class);
		for (HashMap data : list) {
			if(!data.containsKey("userNo")){
				return ;
			}
			String userNo = (String) data.get("userNo");
			if("admin".endsWith(userNo)){//新增
				render(BjuiRender.error("admin为超级管理员，不允许删除！"));
			}
		}

		if(Bjui.use().delete("jfsnpm_user", json, "id")){
			render(BjuiRender.success("删除成功！", false));
		}else{
			render(BjuiRender.error("删除失败！"));
		}
	}

	public void user_updatepassword(){
		String id = getPara("id");
		User user = UserService.getUserById(id);
		if(user == null){
			render(BjuiRender.error("无法找到该用户！"));
		}
		String newPW = getPara("newPassword");
		String newRepeatPW = getPara("newRepeatPassword");

		if(newPW.equals(newRepeatPW)){
			newPW = MD5Util.string2MD5(newPW);
			if(UserService.updatePassWord(id, newPW)){
				render(BjuiRender.success("密码修改成功！", true));
			} else {
				render(BjuiRender.error("密码修改失败！"));
			}
		}else{
			render(BjuiRender.error("两次输入的密码不一致！"));
		}
	}

	public void getUserById(){
		String id = getPara("id");
		if(!StringUtils.isEmpty(id)){
			renderJson(Db.find(AppHelper.getSql("user.getUserByUserId"), id));
		}
	}
	/**
	 * 流程管理
	 */
	public void flowprocess(){
	}
	public void flowprocess_get(){
		renderJson(FlowService.getProcessAll());
	}
	public void flowprocess_role(){
		String processId = getPara("processId");
		setAttr("processId", processId);
	}
	public void flowprocess_role_get(){
		String processId = getPara("processId");
		renderJson(Db.find(AppHelper.getSql("flowprocess.getProcessRole"), processId));
	}
	public void flowprocess_role_edit(){
		String processId = getPara("processId");
		String json = getPara("json");
		if(Bjui.use().update("jfsnpmr_process_role", json,"roleId,processId->processId->"+processId,true,"roleId,processId")){
			renderJson(Bjui.returnSuccessJson(json));
		}else{
			render(BjuiRender.error("保存失败！"));
		}
	}
	public void flowprocess_role_delete(){
		String json = getPara("json");
		if(Bjui.use().delete("jfsnpmr_process_role", json, "roleId,processId")){
			render(BjuiRender.success("删除成功！", false));
		}else{
			render(BjuiRender.error("删除失败！"));
		}
	}
	/**
	 * 角色维护
	 */
	public void role(){
	}
	public void role_get(){
		renderJson(RoleService.getRoleAll());
	}
	public void role_edit(){
		String json = getPara("json");
		if(Bjui.use().update("jfsnpm_role", json,"id->id",true,"id,name")){
			renderJson(Bjui.returnSuccessJson(json));
		}else{
			render(BjuiRender.error("保存失败！"));
		}
	}
	public void role_delete(){
		String json = getPara("json");
		if(Bjui.use().delete("jfsnpm_role", json, "id")){
			render(BjuiRender.success("删除成功！", false));
		}else{
			render(BjuiRender.error("删除失败！"));
		}
	}
	public void role_user(){
		String id = getPara("id");
		String name = Db.findById("jfsnpm_role", id).get("name");
		setAttr("id", id);
		setAttr("name",name);
	}
	public void role_user_get(){
		String id = getPara("id");
		renderJson(Db.find(AppHelper.getSql("role.getRoleUser"), id));
	}
	public void role_user_edit(){
		String roleId = getPara("id");
		String json = getPara("json");
		if(Bjui.use().update("jfsnpmr_role_user", json,"userId,roleId->roleId->"+roleId,true,"roleId,userId")){
			renderJson(Bjui.returnSuccessJson(json));
		}else{
			render(BjuiRender.error("保存失败！"));
		}
	}
	public void role_user_delete(){
		String json = getPara("json");
		if(Bjui.use().delete("jfsnpmr_role_user", json, "roleId,userId")){
			render(BjuiRender.success("删除成功！", false));
		}else{
			render(BjuiRender.error("删除失败！"));
		}
	}
	/**
	 * 流程表单
	 */
	public void flowstruct(){}
	public void flowstruct_get(){
		renderJson(Db.find(AppHelper.getSql("flow.getFlowstruct")));
	}
	public void flowstruct_edit(){
		String json = getPara("json");
		if(Bjui.use().update("jfsnpm_form_h", json,"id->id",false,"")){
			renderJson(Bjui.returnSuccessJson(json));
		}else{
			render(BjuiRender.error("保存失败！"));
		}
	}
	public void flowstruct_delete(){
		String json = getPara("json");
		if(Bjui.use().delete("jfsnpm_form_h", json, "id")){
			render(BjuiRender.success("删除成功！", false));
		}else{
			render(BjuiRender.error("删除失败！"));
		}
	}
	
	public void flowstructd(){
		String id = getPara("id");
		Record form = Db.findById("jfsnpm_form_h", id);
		setAttr("processName", form.getStr("processName"));
		setAttr("formName", form.getStr("formName"));
	}
	public void flowstructd_get(){
		String processName = getPara("processName");
		String formName = getPara("formName");
		renderJson(Db.find(AppHelper.getSql("flow.getFlowstructd"), processName,formName));
	}
	public void flowstructd_edit(){
		String processName = getPara("processName");
		String formName = getPara("formName");
		String json = getPara("json");
		if(Bjui.use().update("jfsnpm_form_d", json,"id,processName->processName->"+processName
				+",formName->formName->"+formName,false,"")){
			renderJson(Bjui.returnSuccessJson(json));
		}else{
			render(BjuiRender.error("保存失败！"));
		}
	}
	public void flowstructd_delete(){
		String json = getPara("json");
		if(Bjui.use().delete("jfsnpm_form_d", json, "id")){
			render(BjuiRender.success("删除成功！", false));
		}else{
			render(BjuiRender.error("删除失败！"));
		}
	}
	public void flowstructg(){
		String id = getPara("id");
		Record form = Db.findById("jfsnpm_form_h", id);
		setAttr("processName", form.getStr("processName"));
		setAttr("formName", form.getStr("formName"));
	}
	public void flowstructg_get(){
		String processName = getPara("processName");
		String formName = getPara("formName");
		renderJson(Db.find(AppHelper.getSql("flow.getFlowstructg"), processName,formName));
	}
	public void flowstructg_edit(){
		String processName = getPara("processName");
		String formName = getPara("formName");
		String json = getPara("json");
		if(Bjui.use().update("jfsnpm_form_g", json,"id,processName->processName->"+processName
				+",formName->formName->"+formName,false,"")){
			renderJson(Bjui.returnSuccessJson(json));
		}else{
			render(BjuiRender.error("保存失败！"));
		}
	}
	public void flowstructg_delete(){
		String json = getPara("json");
		if(Bjui.use().delete("jfsnpm_form_g", json, "id")){
			render(BjuiRender.success("删除成功！", false));
		}else{
			render(BjuiRender.error("删除失败！"));
		}
	}
	public void flowstruct_copy(){
		String id = getPara("id");
		Record form = Db.findById("jfsnpm_form_h", id);
		setAttr("processName", form.getStr("processName"));
		setAttr("formName", form.getStr("formName"));
	}
	@Before(Tx.class)
	public void flowstruct_copy_do(){
		String processName = getPara("processName");
		String formName = getPara("formName");
		String processNameN = getPara("processNameN");
		String formNameN = getPara("formNameN");
		if(AppHelper.isEmpty(processName)||AppHelper.isEmpty(processNameN)||
				AppHelper.isEmpty(formName)||AppHelper.isEmpty(formNameN)){
			render(BjuiRender.error("复制参数不能为空", false));
			return;
		}
		Record formhCheck = Db.findFirst(AppHelper.getSql("flow.getFormH"), processNameN,formNameN);
		List<Record> formdCheck = Db.find(AppHelper.getSql("flow.getFlowstructd"), processNameN,formNameN);
		List<Record> formgCheck = Db.find(AppHelper.getSql("flow.getFlowstructg"), processNameN,formNameN);
		if(formhCheck!=null||formdCheck.size() > 0||formgCheck.size() > 0){
			render(BjuiRender.error("复制目标已存在", false));
			return;
		}
		Record formh = Db.findFirst(AppHelper.getSql("flow.getFormH"), processName,formName);
		List<Record> formd = Db.find(AppHelper.getSql("flow.getFlowstructd"), processName,formName);
		List<Record> formg = Db.find(AppHelper.getSql("flow.getFlowstructg"), processName,formName);
		formh.set("id", AppHelper.getUUID()).set("processName", processNameN).set("formName", formNameN);
		Db.save("jfsnpm_form_h", formh);
		for(Record item:formd){
			item.set("id", AppHelper.getUUID()).set("processName", processNameN).set("formName", formNameN);
			Db.save("jfsnpm_form_d", item);
		}
		for(Record item:formg){
			item.set("id", AppHelper.getUUID()).set("processName", processNameN).set("formName", formNameN);
			Db.save("jfsnpm_form_g", item);
		}
		render(BjuiRender.success("成功复制表单数据从【"+processName+"."+formName+"】"
				+ "到【"+processNameN+"."+formNameN+"】"));
	}
	//公共文件管理
	public void filelist(){
	}
	public void filelist_get(){
		renderJson(Db.find("select * from jfsnpm_file where fileType = ? ", "public"));
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
	
}
