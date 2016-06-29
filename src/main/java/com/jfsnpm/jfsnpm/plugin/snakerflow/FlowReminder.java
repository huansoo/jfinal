package com.jfsnpm.jfsnpm.plugin.snakerflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Task;
import org.snaker.engine.model.NodeModel;
import org.snaker.engine.scheduling.IReminder;
import org.snaker.jfinal.plugin.SnakerPlugin;



public class FlowReminder implements IReminder {

	public void remind(Process process, String orderId, String taskId,
			NodeModel nodeModel, Map<String, Object> data) {
		// TODO 具体提醒操作
		/*System.out.println("提醒:------------------");
		System.out.println("orderid="+orderId);
		System.out.println("taskid="+taskId);
		System.out.println("-----------------------");*/
		List<Task> taskList = new ArrayList<Task>();
		taskList.add(SnakerPlugin.getEngine().query().getTask(taskId));
		TaskNotice.send(taskList);
	}

}
