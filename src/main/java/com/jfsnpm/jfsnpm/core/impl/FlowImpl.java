package com.jfsnpm.jfsnpm.core.impl;

import java.util.List;
import java.util.Map;

import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Process;
import org.snaker.engine.model.TaskModel;
import org.snaker.jfinal.plugin.SnakerPlugin;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.global.IFlow;
import com.jfsnpm.jfsnpm.core.util.AppHelper;

public class FlowImpl implements IFlow {
	@Override
	public List<Record> getTodoListByUserId(String userId) {
		List<Record> list = Db.find(AppHelper.getSql("flowprocess.getMyTodo"), userId);
		for(Record item:list){
			item.set("render_item", item.get("id")+","+item.getStr("display_Name"));
		}
		return list;
	}

	@Override
	public List<Process> getProcessAll() {
		return SnakerPlugin.getEngine().process().getProcesss(new QueryFilter());
	}

	@Override
	public List<Record> getMyProcess(String userId) {
		List<Record> list = Db.find(AppHelper.getSql("flowprocess.getMyProcess"), userId);
		for(Record item:list){
			item.set("render_item", item.get("id")+","+item.getStr("display_Name"));
		}
		return list;
	}
	@Override
	public List<Record> getMyRelease(String userId) {
		List<Record> list = Db.find(AppHelper.getSql("flowprocess.getMyRelease"), userId);
		String taskIdc = "";
		for(int i=(list.size() - 1);i>=0;i--){
			String taskId = list.get(i).getStr("taskId");
			if(AppHelper.isSame(taskIdc, taskId)){
				list.get(i).set("userName", list.get(i).get("userName")+","+list.get(i+1).getStr("userName"));
				list.remove(i+1);
			}
			taskIdc = taskId;
		}
		return list;
	}

	@Override
	public List<Record> getMyHistory(String userId) {
		return Db.find(AppHelper.getSql("flowprocess.getMyHistory"), userId);
	}

	@Override
	public Map<String, Object> getTaskModelsArgs(String userId, String processId, Map<String, Object> args) {
		List<TaskModel> taskModels = SnakerPlugin.getEngine().process().getProcessById(processId)
				.getModel().getTaskModels();
		for(TaskModel taskModel:taskModels){
			String assignee = taskModel.getAssignee();
			if(!AppHelper.isEmpty(assignee)){
				String assigneeData = AppConfig.flowArgsimpl.getFlowAssignee(userId, assignee);
				if(!AppHelper.isEmpty(assigneeData)){
					args.put(assignee, assigneeData);
				}
			}
		}
		return args;
	}
	

}
