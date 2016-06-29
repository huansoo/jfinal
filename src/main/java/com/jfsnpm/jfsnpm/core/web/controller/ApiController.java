package com.jfsnpm.jfsnpm.core.web.controller;

import com.jfinal.core.Controller;
import com.jfsnpm.jfsnpm.core.service.ApiService;
import com.jfsnpm.jfsnpm.core.util.AppHelper;

public class ApiController extends Controller {
	public void open(){
		ApiService.open(this);
	}
	public void viewTask(){
		String taskId = getPara("taskId");
		if(AppHelper.isEmpty(taskId)){
			renderText("参数错误，taskId不能为空!");
			return;
		}
		ApiService.set(this, "dialog", "/Flow/taskView/?taskId="+taskId);
	}
}
