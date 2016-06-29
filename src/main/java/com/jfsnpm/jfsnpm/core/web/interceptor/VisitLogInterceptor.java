package com.jfsnpm.jfsnpm.core.web.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfsnpm.jfsnpm.core.dao.User;
import com.jfsnpm.jfsnpm.core.service.UserService;
import com.jfsnpm.jfsnpm.core.util.AppHelper;

public class VisitLogInterceptor implements Interceptor {
	public void intercept(Invocation ai){
		ai.getController().setAttr("__url", ai.getActionKey());
		String url = ai.getController().getPara("__menuId");
		if(AppHelper.isEmpty(url)) url = ai.getActionKey();
		//无需拦截的系统URL
		if("/Login".equals(ai.getControllerKey())
				||"/Index".equals(ai.getControllerKey())
				||"/Flow".equals(ai.getControllerKey())
				||"/Snaker".equals(ai.getControllerKey())
				||"/Lookup".equals(ai.getControllerKey())
				||"/Main".equals(ai.getControllerKey())){
			ai.invoke();
			return;
		}
		User user = UserService.getUserInfor(ai.getController());
		if(user!=null){
			String userId = user.getUserId();
			AppHelper.setLogs(userId, url);
		}
		ai.invoke();
	}

}
