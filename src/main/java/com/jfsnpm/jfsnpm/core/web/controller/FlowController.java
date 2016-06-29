package com.jfsnpm.jfsnpm.core.web.controller;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Task;
import org.snaker.engine.model.TaskModel.TaskType;
import org.snaker.jfinal.plugin.SnakerPlugin;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.dao.User;
import com.jfsnpm.jfsnpm.core.service.FlowService;
import com.jfsnpm.jfsnpm.core.service.UserService;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.plugin.bjui.Bjui;
import com.jfsnpm.jfsnpm.plugin.bjui.BjuiRender;
import com.jfsnpm.jfsnpm.plugin.snakerflow.TaskNotice;

public class FlowController extends Controller {
	/**
	 * 启动流程
	 */
	@Before(Tx.class)
	public void processStart(){
		String processid = getPara("processid");
		User user = UserService.getUserInfor(this);
		String userid = user.getUserId();
		if(AppHelper.isEmpty(userid)){
			render(BjuiRender.error("参数：未获取到用户登录信息！请尝试重新登录！", true));
			return;
		}
		if(AppHelper.isEmpty(processid)){
			render(BjuiRender.error("参数：流程ID不能为空！请尝试刷新页面！", true));
			return;
		}
		Map<String,Object> args = new HashMap<String, Object>();
		Order order = FlowService.processStart(processid, userid , args);
		if(order==null){
			render(BjuiRender.error("失败：启动流程失败！", true));
			return;
		}
		Task task = FlowService.getTaskByOrderUser(order, userid);
		if(task==null){
			renderText("启动流程成功，当前实例没有您的待办任务！您可以在【流程进度】中查看该实例！");
			return;
		}
		//有需要处理的任务，显示任务界面
		//界面使用autoajaxload 自动加载
		setAttr("taskId", task.getId());
	}
	/**
	 * 显示任务
	 */
	public void taskView(){
		String taskId = getPara("taskId");
		User user = UserService.getUserInfor(this);
		String userid = user.getUserId();
		if(AppHelper.isEmpty(userid)){
			render(BjuiRender.error("参数：未获取到用户登录信息！请尝试重新登录！", true));
			return;
		}
		if(AppHelper.isEmpty(taskId)){
			render(BjuiRender.error("参数：任务ID不能为空！请尝试刷新页面！", true));
			return;
		}
		//检查是否有处理当前任务的权利
		if(!FlowService.checkIsAllowed(taskId, userid)){
			render(BjuiRender.error("当前任务已处理或您没有处理该任务的权限！请刷新！", true));
			return;
		}
		String orderid="";
		String formname="";
		Task taskon = SnakerPlugin.getEngine().query().getTask(taskId);
		if(taskon==null){
			HistoryTask task = SnakerPlugin.getEngine().query().getHistTask(taskId);
			orderid=task.getOrderId();
			formname=task.getActionUrl();
			if(AppHelper.isEmpty(formname)){
				formname = task.getTaskName();
			}
		}else{
			orderid=taskon.getOrderId();
			formname=taskon.getActionUrl();
			if(AppHelper.isEmpty(formname)){
				formname = taskon.getTaskName();
			}
		}
		HistoryOrder order = SnakerPlugin.getEngine().query().getHistOrder(orderid);
		Process process = SnakerPlugin.getEngine().process().getProcessById(order.getProcessId());
		String formpath=process.getInstanceUrl();
		//TODO:暂时用order id作为表单主键，后续需改为从Variable字段获取
		String formid = orderid;
		if(AppHelper.isEmpty(formpath)){
			formpath = process.getName();
		}
		Record formdata = Db.findFirst("select form_"+formpath+".* ,jfsnpm_user.userName from form_"+formpath+" left join jfsnpm_user on "
				+ "form_"+formpath+".pm_creator = jfsnpm_user.id where form_"+formpath+".id = ?", formid);
		
		
		setAttr("taskId", taskId);
		setAttr("historyTasks", FlowService.getHistoryTasks(orderid));
		
		setAttr("username", user.getUserName());
		setAttr("userid", user.getUserId());
		setAttr("orderid", orderid);
		setAttr("taskid", taskId);
		setAttr("formpath", formpath);
		setAttr("formdata", formdata);
		
		if(AppConfig.flowSqlForm){
			setAttr("formstruct",Db.find(AppHelper.getSql("flow.getFlowSqlForm"), formpath,formname));
			setAttr("formdatagrid",Db.find(AppHelper.getSql("flow.getFlowSqlFormG"), formpath,formname));
			render("form/template.ftl");
		}else{
			render("form/"+formpath+"/"+formname+".html");
		}
		
	}
	/**
	 * 保存表单
	 */
	public void form_save(){
		String userid = UserService.getUserInfor(this).getUserId();
		String id = getPara("__id");//表单ID
		String orderid = getPara("__orderid");//实例ID
		String taskid = getPara("__taskid");//任务ID
		String formpath = getPara("__formpath");//任务路径
		String ordertitlecolname = getPara("__ordertitlecolname");//更新标题列
		String argscolname = getPara("__argscolname");//写入参数列
		String flowoperation = getPara("__flowoperation");//操作
		String flowoperationdesc = getPara("__flowoperationdesc");//写入操作说明列
		String tousers = getPara("__tousers");//转发给
		String rejto = getPara("__rejto");//退回到
		
		//检查是否有处理当前任务的权利
		if(!FlowService.checkIsAllowed(taskid, userid)){
			render(BjuiRender.error("当前任务已处理或您没有处理该任务的权限！请刷新！", true));
			return;
		}
		Record formdata = Db.findById("form_"+formpath, id);
		if(formdata == null){
			render(BjuiRender.error("获取表单失败！"));
			return;
		}
		if(AppHelper.isEmpty(flowoperation)){
			render(BjuiRender.error("未知的操作方式！"));
			return;
		}
		//更新流程实例标题
		if(!AppHelper.isEmpty(ordertitlecolname)){
			String orderTitle = getPara(ordertitlecolname);
			if(!AppHelper.isEmpty(orderTitle)){
				Db.update(AppHelper.getSql("flow.updateOrderTitle"),orderTitle,orderid );
			}
		}
		//写入流程变量
		Map<String,Object> args = new HashMap<String, Object>();
		if(!AppHelper.isEmpty(orderid)){
			String[] argscolnames = argscolname.split(",");
			for(String argname:argscolnames){
				String arg = getPara(argname);
				if(!AppHelper.isEmpty(arg)){
					if(argname.indexOf("date") > 0){
						//包含date关键字，尝试转换成date类型。
						args.put(argname, AppHelper.string2Date(arg));
					}else{
						args.put(argname, arg);
					}
				}
			}
			SnakerPlugin.getEngine().order().addVariable(orderid, args);
		}
		//更新表单
		Map<String, String[]> paramap = getParaMap();
		Set<String> key = paramap.keySet();
        for(Iterator<String> it = key.iterator(); it.hasNext();) {
            String paraname = it.next();
            if(paraname.startsWith("__")) continue;
            if(AppHelper.isEmpty(getPara(paraname))) continue;
            formdata.set(paraname, getPara(paraname));
        }
        Db.update("form_"+formpath,formdata);
        //进行流程执行操作
        if(!AppHelper.isEmpty(flowoperation)){
        	List<Task> taskList = null;
        	String flowoperationdescdata = getPara(flowoperationdesc);
        	if(AppHelper.isEmpty(flowoperationdescdata)) flowoperationdescdata = "";
    		args.put("flow_operate_mark", flowoperationdescdata);
    		args.put("flow_operate", flowoperation);
        	if("同意".equals(flowoperation)||
        			"完成".equals(flowoperation)||
        			"送签".equals(flowoperation)||
        			"接收".equals(flowoperation)||
        			"批准".equals(flowoperation)||
        			"结案".equals(flowoperation)){
        		taskList = SnakerPlugin.getEngine().executeTask(taskid, userid, args);
				render(BjuiRender.success("保存成功，处理【"+flowoperation+"】成功！").divid("mytodo"));
				TaskNotice.send(taskList);
        	}else if("不同意".equals(flowoperation)||
        			"退回".equals(flowoperation)){
				if(AppHelper.isEmpty(rejto)) rejto = null;
				taskList = SnakerPlugin.getEngine().executeAndJumpTask(taskid, userid, args,rejto);
				render(BjuiRender.success("保存成功，处理【"+flowoperation+"】成功！").divid("mytodo"));
				TaskNotice.send(taskList);
        	}else if("转发".equals(flowoperation)){
				if(AppHelper.isEmpty(tousers)){
					render(BjuiRender.error("保存成功，处理【"+flowoperation+"】失败，没有获取到转发人！"));
					return;
				}
				taskList = SnakerPlugin.getEngine().task().createNewTask(taskid, TaskType.Major.ordinal(),tousers.split(","));
				SnakerPlugin.getEngine().task().complete(taskid, userid, args);
				render(BjuiRender.success("保存成功，处理【"+flowoperation+"】成功！").divid("mytodo"));
				TaskNotice.send(taskList);
        	}else if("保存".equals(flowoperation)){
        		render(BjuiRender.success("保存成功！",false));
        	}else{
				render(BjuiRender.error("保存成功，不支持【"+flowoperation+"】的处理方式！",false));
        	}
        }else{
        	render(BjuiRender.success("保存成功！",false));
        }
	}
	/**
	 * 流程实例查看
	 */
	public void orderView(){
		String orderId = getPara("orderId");
		if(AppHelper.isEmpty(orderId)){
			render(BjuiRender.error("获取流程实例ID失败！请刷新重试！", true));
			return;
		}
		String formname="readonly";
		HistoryOrder order = SnakerPlugin.getEngine().query().getHistOrder(orderId);
		Process process = SnakerPlugin.getEngine().process().getProcessById(order.getProcessId());
		String formpath=process.getInstanceUrl();
		//TODO:暂时用order id作为表单主键，后续需改为从Variable字段获取
		String formid = orderId;
		if(AppHelper.isEmpty(formpath)){
			formpath = process.getName();
		}
		Record formdata = Db.findFirst("select form_"+formpath+".* ,jfsnpm_user.userName from form_"+formpath+" left join jfsnpm_user on "
				+ "form_"+formpath+".pm_creator = jfsnpm_user.id where form_"+formpath+".id = ?", formid);
		
		setAttr("orderId", orderId);
		setAttr("historyTasks", FlowService.getHistoryTasks(orderId));
		setAttr("orderState",order.getOrderState());
		setAttr("orderid", orderId);
		setAttr("formpath", formpath);
		setAttr("formdata", formdata);
		if(AppConfig.flowSqlForm){
			setAttr("formstruct",Db.find(AppHelper.getSql("flow.getFlowSqlForm"), formpath,formname));
			setAttr("formdatagrid",Db.find(AppHelper.getSql("flow.getFlowSqlFormG"), formpath,formname));
			render("form/template.ftl");
		}else{
			render("form/"+formpath+"/"+formname+".html");
		}
		
	}
	/**
	 * 强制终止流程
	 */
	public void orderTerminate(){
		String orderId = getPara("orderId");
		if(AppHelper.isEmpty(orderId)){
			render(BjuiRender.error("获取流程实例ID失败！请刷新重试！", true));
			return;
		}
		User user = UserService.getUserInfor(this);
		String userId = user.getUserId();
		if(AppHelper.isEmpty(userId)){
			render(BjuiRender.error("参数：未获取到用户登录信息！请尝试重新登录！", true));
			return;
		}
		if(FlowService.orderTerminate(orderId, userId)){
			render(BjuiRender.success("成功：流程实例已强制结束！"));
			return;
		}else{
			render(BjuiRender.error("失败：请确认您是否为流程创建者并且流程状态为'进行中'！", false));
			return;
		}
		
		
	}
	
	
	
	/**
	 * 流程表单表格的操作
	 */
	public void form_grid_get(){
		String orderId = getPara("orderId");
		String gridDbName = getPara("gridDbName");
		String gridDbKey = getPara("gridDbKey");
		if(AppHelper.isEmpty(gridDbKey)||AppHelper.isEmpty(gridDbName)||AppHelper.isEmpty(orderId)){
			render(BjuiRender.error("参数不能为空！"));
			return;
		}
		renderJson(Db.find("SELECT * FROM "+gridDbName+" WHERE "+gridDbKey+" = ?", orderId));
	}
	public void form_grid_edit(){
		String orderId = getPara("orderId");
		String gridDbName = getPara("gridDbName");
		String gridDbKey = getPara("gridDbKey");
		String json = getPara("json");
		if(AppHelper.isEmpty(gridDbKey)||AppHelper.isEmpty(gridDbName)
				||AppHelper.isEmpty(orderId)||AppHelper.isEmpty(json)){
			render(BjuiRender.error("参数不能为空！"));
			return;
		}
		if(Bjui.use().update(gridDbName, json,"id,"+gridDbKey+"->"+gridDbKey+"->"+orderId,false,"")){
			renderJson(Bjui.returnSuccessJson(json));
		}else{
			render(BjuiRender.error("保存失败！"));
		}
	}
	public void form_grid_delete(){
		String orderId = getPara("orderId");
		String gridDbName = getPara("gridDbName");
		String gridDbKey = getPara("gridDbKey");
		String json = getPara("json");
		if(AppHelper.isEmpty(gridDbKey)||AppHelper.isEmpty(gridDbName)
				||AppHelper.isEmpty(orderId)||AppHelper.isEmpty(json)){
			render(BjuiRender.error("参数不能为空！"));
			return;
		}
		if(Bjui.use().delete(gridDbName, json, "id")){
			render(BjuiRender.success("删除成功！", false));
		}else{
			render(BjuiRender.error("删除失败！"));
		}
	}
	
	
	
	
	
	
}
