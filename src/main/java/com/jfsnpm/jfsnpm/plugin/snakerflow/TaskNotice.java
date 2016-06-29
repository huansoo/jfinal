package com.jfsnpm.jfsnpm.plugin.snakerflow;

import java.util.List;

import org.snaker.engine.entity.Task;

import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.dao.User;
import com.jfsnpm.jfsnpm.core.service.UserService;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.core.util.MessageHelper;

public class TaskNotice {
	public static boolean send(List<Task> taskList){
		for(Task task : taskList) {
			String tos="";
			String content="<html>";
			content+="<h2>任务提醒</h2>";
			content+="任务名称："+task.getDisplayName()+"<br>";
			content+="参与者：";
			StringBuffer buffer = new StringBuffer(100);
			buffer.append("任务标识=").append(task.getId());
			buffer.append(",名称=[").append(task.getDisplayName())
				.append("|").append(AppHelper.getEnv(AppConfig.properties.getProperty("site"))+AppConfig.rootpath+"/Api/viewTask/?"
					+ "taskId="+task.getId()).append("]");
			buffer.append(",创建时间=").append(task.getCreateTime());
			buffer.append(",参与者={");
			if(task.getActorIds() != null) {
				for(String actor : task.getActorIds()) {
					if(tos.equals("")){
						tos = actor;
					}else{
						tos = tos + ";"+ actor;
					}
					User user = UserService.getUserById(actor);
					buffer.append(user.getUserName()).append(";");
					content+=user.getUserName();
				}
			}
			buffer.append("}");
			
			content+="<br>";
			content+="任务说明："+task.getVariableMap().get("flow_operate_mark")+"<br>";
			content+="<a href='"+AppHelper.getEnv(AppConfig.properties.getProperty("site"))+AppConfig.rootpath+"/Api/viewTask/?"
					+ "taskId="+task.getId()+"'>处理任务</a>";
			content+="</html>";
			//MessageHelper.sendNotify("任务通知", tos, buffer.toString());
			MessageHelper.sendMail("任务提醒", tos, content);
		}
		return true;
	}
}
