package com.jfsnpm.jfsnpm.core.web.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Task;
import org.snaker.engine.helper.StreamHelper;
import org.snaker.engine.model.ProcessModel;
import org.snaker.jfinal.plugin.SnakerPlugin;

import com.jfinal.core.Controller;
import com.jfsnpm.jfsnpm.core.dao.User;
import com.jfsnpm.jfsnpm.core.service.UserService;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.plugin.bjui.BjuiRender;
import com.jfsnpm.jfsnpm.plugin.snakerflow.SnakerHelper;

/**
 * 流程图相关操作
 * @author 天为之殇
 *
 */
public class SnakerController extends Controller {
	/**
	 * 流程图文件上传部署
	 */
	public void deploy(){
		try {
			String userid = UserService.getUserInfor(this).getUserId();
			String processid = SnakerPlugin.getEngine().process().deploy(StreamHelper.getStreamFromFile(getFile().getFile()), userid);
			if(AppHelper.isEmpty(processid)){
				render(BjuiRender.error("部署失败！"));
			}else{
				render(BjuiRender.success("部署成功！",false));
			}
		} catch (Exception e) {
			String errormsg;
			Throwable et=e;
			do {
				errormsg = et.getMessage();
				et = et.getCause();
			} while (AppHelper.isEmpty(errormsg));
			render(BjuiRender.error("部署失败！"+errormsg));
		}
	}
	/**
	 * 流程图查看
	 */
	public void processview(){
		String processid = getPara("processid");
		if(AppHelper.isEmpty(processid)){render(BjuiRender.code(801));return;}
		Process process = SnakerPlugin.getEngine().process().getProcessById(processid);
		ProcessModel processModel = process.getModel();
		if(processModel != null) {
			String json = SnakerHelper.getModelJson(processModel);
			setAttr("process", json);
		}else{
			render(BjuiRender.code(801));return;
		}
		setAttr("processid", processid);
	}
	/**
	 * 流程图设计
	 */
	public void processdesign(){
		String processid = getPara("processid");
		if(AppHelper.isEmpty(processid)){render(BjuiRender.code(801));return;}
		Process process = SnakerPlugin.getEngine().process().getProcessById(processid);
		if(process!=null){
			ProcessModel processModel = process.getModel();
			if(processModel != null) {
				String json = SnakerHelper.getModelJson(processModel);
				setAttr("process", json);
			}else{
				render(BjuiRender.code(801));return;
			}
		}else{
			setAttr("process", "");
		}
		
		setAttr("processid", processid);
	}
	/**
	 * 流程图部署xml string
	 */
	public void deploy_string(){
		String userid = UserService.getUserInfor(this).getUserId();
		String processid = getPara("processid");
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" + SnakerHelper.convertXml(getPara("model"));
		if(AppHelper.isEmpty(processid)){render(BjuiRender.code(801));return;}
		if("new".equals(processid)){
			processid = SnakerPlugin.getEngine().process().deploy(StreamHelper.getStreamFromString(xml), userid);
		}else{
			SnakerPlugin.getEngine().process().redeploy(processid, StreamHelper.getStreamFromString(xml));
		}
		if(AppHelper.isEmpty(processid)){
			render(BjuiRender.error("部署失败！"));
		}else{
			render(BjuiRender.success("部署成功！",false));
		}
	}
	/**
	 * 流程实例流程图
	 */
	public void orderview(){
		String orderid = getPara("orderid");
		if(AppHelper.isEmpty(orderid)){render(BjuiRender.code(801));return;}
		HistoryOrder order = SnakerPlugin.getEngine().query().getHistOrder(orderid);
		Process process = SnakerPlugin.getEngine().process().getProcessById(order.getProcessId());
		ProcessModel processModel = null;
		if(process!=null){
			processModel = process.getModel();
			if(processModel != null) {
				String json = SnakerHelper.getModelJson(processModel);
				setAttr("process", json);
			}else{
				render(BjuiRender.code(801));return;
			}
		}else{
			setAttr("process", "");
		}
		List<Task> tasks = SnakerPlugin.getEngine().query().getActiveTasks(new QueryFilter().setOrderId(orderid));
		List<HistoryTask> historyTasks = SnakerPlugin.getEngine().query().getHistoryTasks(new QueryFilter().setOrderId(orderid));
		String json = SnakerHelper.getStateJson(processModel, tasks, historyTasks);
		setAttr("active", json);
		/*if(tasks != null && !tasks.isEmpty()) {
			String json = SnakerHelper.getStateJson(processModel, tasks, historyTasks);
			setAttr("active", json);
		}else{
			setAttr("active", "{}");
		}*/
		setAttr("orderid", orderid);
	}
	/**
	 * 流程图点击提示信息
	 */
	public void tasktip(){
		String orderid = getPara("orderid");
		String taskname = getPara("taskname");
		List<Task> tasks = SnakerPlugin.getEngine().query().getActiveTasks(new QueryFilter().setOrderId(orderid));
		StringBuilder builder = new StringBuilder();
        String createTime = "";
        String taskdisname = "";
        for(Task task : tasks) {
            if(task.getTaskName().equalsIgnoreCase(taskname)) {
                String[] actors = SnakerPlugin.getEngine().query().getTaskActorsByTaskId(task.getId());
                String actorname="";
                for(String actor : actors) {
                	User user = UserService.getUserById(actor);
                	if(user!=null) actorname=user.getUserName();
                	if(!AppHelper.isEmpty(actorname)){
                		builder.append(actorname).append(",");
                	}else{
                		builder.append(actor).append(",");
                	}
                }
                createTime = task.getCreateTime();
                taskdisname = task.getDisplayName();
            }
        }
        if(builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        setAttr("operator", builder.toString());
        setAttr("task_name", taskdisname);
        setAttr("create_time", createTime);
        
	}
	/**
	 * 根据流程ID返回流程文件
	 * @throws IOException
	 * @throws SQLException
	 */
	public void saveasfile() throws IOException, SQLException{
		String processid = getPara("processid");
		if(AppHelper.isEmpty(processid)){render(BjuiRender.code(801));return;}
		Process process = SnakerPlugin.getEngine().process().getProcessById(processid);
		String processName = process.getName();
		Blob b = process.getContent();
		String fileName = processName + ".snaker";
		File snakerfile = new File(fileName);
		if(snakerfile.exists()) snakerfile.delete();
		snakerfile.createNewFile();
		FileOutputStream fos = new FileOutputStream(snakerfile);
		fos.write(b.getBytes(1, (int)b.length()));
		fos.close();
		renderFile(snakerfile);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
