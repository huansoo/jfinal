package com.jfsnpm.jfsnpm.core.web.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.NoUrlPara;
import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.service.LoginService;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.plugin.bjui.BjuiRender;

public class LoginController extends Controller {
	@Before(NoUrlPara.class)
	public void index() {
		redirect("/Login/main");
	}
	
	public void main(){
		String preurl = getCookie("preUrl");
		if(AppHelper.isEmpty(preurl)){
			preurl = "/";
		}
		setAttr("preurl", preurl);
		if(AppConfig.DevMode){
			render("main4dev.html");
		}
	}
	public void login(){
		if(LoginService.checkUserPassword(this)){
			if(!AppHelper.isEmpty(getCookie("preUrl"))){
				setCookie("preUrl", "", 0);
			}
			render(BjuiRender.success("验证成功!"));
			return;
		}
		render(BjuiRender.error("用户名、密码不正确!"));
	}
	public void logout(){
		LoginService.logout(this);
		redirect("/Login/main");
	}
	/**
	 * rtx登录跳转页面
	 */
	public void loginrtx(){
		setAttr("acctoken", AppHelper.createAccToken());
	}
	/**
	 * oschina登录跳转页面
	 */
	public void loginoschina(){
		setAttr("client_id", AppHelper.getEnv(AppConfig.properties.getProperty("oschina_client_id")));
		setAttr("redirect_uri", AppHelper.getEnv(AppConfig.properties.getProperty("oschina_redirect_uri")));
		setAttr("acctoken", AppHelper.createAccToken());
	}
	
	public void login_page(){
		String username = getPara("username");
		setAttr("username", username);
	}
}
