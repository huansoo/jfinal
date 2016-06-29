package com.jfsnpm.jfsnpm.core.web.controller;


import com.jfinal.core.Controller;
import com.jfsnpm.jfsnpm.plugin.bjui.Bjui;

public class LookupController extends Controller {
	public void role(){
		renderJson(Bjui.use().page("SELECT * FROM jfsnpm_role", this));
	}
	public void user(){
		renderJson(Bjui.use().page("SELECT * FROM jfsnpm_user", this));
	}
}
