package com.jfsnpm.jfsnpm.core.web.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.NoUrlPara;
import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.dao.User;
import com.jfsnpm.jfsnpm.core.service.ApiService;
import com.jfsnpm.jfsnpm.core.service.MenuService;
import com.jfsnpm.jfsnpm.core.service.UserService;

public class IndexController extends Controller {
		/*@Before(NoUrlPara.class)
		@Clear
		public void index() {
			redirect("/Public/main");
		}*/
		@Before(NoUrlPara.class)
		public void index() {
			String mobile = getCookie("mobile");
			if("mobile".equals(mobile)){
				redirect("/Index/mainm");
			}else{
				redirect("/Index/main");
			}
		}
		/**
		 * 系统入口
		 */
		public void main(){
			String mobile = getCookie("mobile");
			if("mobile".equals(mobile)){
				redirect("/Index/mainm");
				return;
			}
			ApiService.get(this);
			setAttr("menuList", MenuService.getMenu(this));
			User user = UserService.getUserInfor(this);
			setAttr("toptenlist", MenuService.getMenuTopTen(user.getUserId()));
			setAttr("username", user.getUserName());
			setAttr("userno", user.getUserNo());
			if(AppConfig.DevMode){
				render("main4dev.html");
			}
		}
		public void mainm(){
			//TODO:手机主页待制作
			renderJson("这里是手机主页");
		}
}
