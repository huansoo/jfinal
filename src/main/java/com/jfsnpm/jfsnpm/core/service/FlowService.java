package com.jfsnpm.jfsnpm.core.service;



import java.util.List;
import java.util.Map;

import org.snaker.engine.entity.Task;
import org.snaker.jfinal.plugin.SnakerPlugin;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.core.AccessService;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Process;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.dao.User;
import com.jfsnpm.jfsnpm.core.util.AppHelper;

public class FlowService {
	public static List<Process> getProcessAll(){
		return AppConfig.flowimpl.getProcessAll();
	}
	public static List<Record> getMyTodoList(Controller c){
		User user = UserService.getUserInfor(c);
		String userId = user.getUserId();
		return AppConfig.flowimpl.getTodoListByUserId(userId);
	}
	public static List<Record> getMyProcess(Controller c){
		User user = UserService.getUserInfor(c);
		String userId = user.getUserId();
		return AppConfig.flowimpl.getMyProcess(userId);
	}
	public static List<Record> getMyRelease(Controller c){
		User user = UserService.getUserInfor(c);
		String userId = user.getUserId();
		return AppConfig.flowimpl.getMyRelease(userId);
	}
	public static List<Record> getMyHistory(Controller c){
		User user = UserService.getUserInfor(c);
		String userId = user.getUserId();
		return AppConfig.flowimpl.getMyHistory(userId);
	}
	/**
	 * 启动流程，返回Order
	 * @param processId 流程ID
	 * @param operator 操作人
	 * @param args 参数
	 * @return
	 */
	public static Order processStart(String processId,String operator,Map<String, Object> args){
		return SnakerPlugin.getEngine().startInstanceById(processId, operator,args);
	}
	/**
	 * 获取order中操作人可执行的活动任务
	 * @param order
	 * @param userId
	 * @return
	 */
	public static Task getTaskByOrderUser(Order order,String userId){
		List<Task> tasks = SnakerPlugin.getEngine().query().getActiveTasks(new QueryFilter().setOrderId(order.getId()));
		for(Task task:tasks){
			if(SnakerPlugin.getEngine().task().isAllowed(task, userId)){
				return task;
			}
		}
		return null;
	}
	
	/**
	 * 获取经过处理过的历史任务列表
	 * @param orderid
	 * @return
	 */
	public static List<Record> getHistoryTasks(String orderId){
		List<Record> historytasks = Db.find(AppHelper.getSql("flow.getHistoryTasks"),orderId);
		for(Record historytask:historytasks){
			String histaskid = historytask.getStr("id");
			Map<String, Object> historyargs = SnakerPlugin.getEngine().query().getHistTask(histaskid).getVariableMap();
			historytask.set("flow_operate", historyargs.get("flow_operate") );
			historytask.set("flow_operate_mark", historyargs.get("flow_operate_mark") );
		}
		return historytasks;
	}
	/**
	 * 检查是否能处理该任务
	 * @param taskId
	 * @param userId
	 * @return
	 */
	public static boolean checkIsAllowed(String taskId,String userId){
		Task task = SnakerPlugin.getEngine().query().getTask(taskId);
		if(task == null) return false;
		return SnakerPlugin.getEngine().task().isAllowed(task, userId);
	}
	/**
	 * 强制终止流程实例
	 * @param orderId 实例ID
	 * @param userId 处理人
	 * @return
	 */
	public static boolean orderTerminate(String orderId , String userId){
		HistoryOrder historyOrder = SnakerPlugin.getEngine().query().getHistOrder(orderId);
		String orderCreator = historyOrder.getCreator();
		if( AccessService.STATE_ACTIVE == historyOrder.getOrderState()&&AppHelper.isSame(userId, orderCreator)){
			//实例状态正常，建立者相符
			SnakerPlugin.getEngine().order().terminate(orderId, userId);
			return true;
		}
		return false;
	}
	/**
	 * 获取历史流程实例
	 * @param orderId
	 * @return
	 */
	public static HistoryOrder getHistoryOrder(String orderId){
		return SnakerPlugin.getEngine().query().getHistOrder(orderId);
	}
	/**
	 * 获取任务模型中参与者，写入参数MAP
	 * @param userId
	 * @param args
	 * @return
	 */
	public static Map<String,Object> getTaskModelsArgs(String userId,String processId ,Map<String,Object> args){
		return AppConfig.flowimpl.getTaskModelsArgs(userId, processId, args);
	}
}
