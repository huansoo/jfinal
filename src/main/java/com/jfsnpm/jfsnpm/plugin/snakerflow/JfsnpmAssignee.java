package com.jfsnpm.jfsnpm.plugin.snakerflow;

import org.snaker.engine.core.Execution;
import org.snaker.engine.entity.Order;

import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.util.AppHelper;

public class JfsnpmAssignee {
	/**
	 * 自定义的全局参与者处理类
	 * @param execution
	 * @param assignee
	 * @return
	 */
	public static Object assign(Execution execution,String assignee){
		if(AppHelper.isEmpty(assignee)) return null;
		Order order = execution.getOrder();
		String userId = order.getCreator();
		if("__creator".equals(assignee)) return userId;
		return AppConfig.flowArgsimpl.getFlowAssignee(userId, assignee);
	}
}
