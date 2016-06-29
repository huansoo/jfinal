package com.jfsnpm.jfsnpm.plugin.snakerflow;


import org.snaker.engine.core.Execution;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Process;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfsnpm.jfsnpm.core.util.AppHelper;

public class JfsnpmFormData {
	public static void init(Execution execution) {
		Order order = execution.getOrder();
		String orderIdSub = order.getId();
		String userid = execution.getOperator();
		//产生表单记录
		Process process = execution.getProcess();
		String formpath=process.getInstanceUrl();
		if(AppHelper.isEmpty(formpath)){
			formpath = process.getName();
		}
		Db.save("jfsnpm_ext_wf_order", new Record().set("orderId", orderIdSub));
		Db.save("form_"+formpath, new Record().set("id", orderIdSub)
				.set("pm_creator",userid).set("pm_create_time", AppHelper.getNow())
				);
		
	}

}
