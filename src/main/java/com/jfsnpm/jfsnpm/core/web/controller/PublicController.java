package com.jfsnpm.jfsnpm.core.web.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.NoUrlPara;

public class PublicController extends Controller {
	@Before(NoUrlPara.class)
	public void index(){
		redirect("/Public/main");
	}
	/**
	 * 主页
	 */
	public void main(){
		
	}
}
