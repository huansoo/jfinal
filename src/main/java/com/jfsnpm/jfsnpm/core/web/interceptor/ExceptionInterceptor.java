package com.jfsnpm.jfsnpm.core.web.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.core.util.JfsnpmException;
import com.jfsnpm.jfsnpm.plugin.bjui.BjuiRender;

public class ExceptionInterceptor implements Interceptor {

	public void intercept(Invocation ai){
		try {
			ai.invoke();
		}
		catch(JfsnpmException e){
			String msg = e.getMessage();
			ai.getController().render(BjuiRender.error(msg));
		}
		catch (Exception e) {
			ai.getController().setAttr("msg", AppHelper.printStackTraceToString(e));
			ai.getController().renderError(500);
		}
		return;
	}

}
