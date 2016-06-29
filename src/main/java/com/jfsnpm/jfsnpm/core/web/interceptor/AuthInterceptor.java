package com.jfsnpm.jfsnpm.core.web.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfsnpm.jfsnpm.core.service.LoginService;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.plugin.bjui.BjuiRender;

public class AuthInterceptor implements Interceptor {

	public void intercept(Invocation ai) {
		//无需拦截的部分URL
		if("/Login".equals(ai.getControllerKey())||"/Demo".equals(ai.getControllerKey())
				||"/Public".equals(ai.getControllerKey())){
			ai.invoke();
			return;
		}
		//login服务进行验证token
		if(!LoginService.checkToken(ai.getController())){
			//API 记录登录前的URL
			if("/Api".equals(ai.getControllerKey())){
				String url = ai.getActionKey();
				if(!AppHelper.isEmpty(ai.getController().getPara())){
					url = url + "/" + ai.getController().getPara();
				}
				if(!AppHelper.isEmpty(ai.getController().getRequest().getQueryString())){
					url = url + "?" + ai.getController().getRequest().getQueryString();
				}
				ai.getController().setCookie("preUrl", url, 600);
			}
			if("/Api".equals(ai.getControllerKey())||"/".equals(ai.getControllerKey())||"/Index".equals(ai.getControllerKey())){
				ai.getController().redirect("/Login/main");
			}else{
				ai.getController().render(BjuiRender.timeout());
			}
			return;
		}
		ai.invoke();
		return;
	}

}
