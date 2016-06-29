package com.jfsnpm.jfsnpm.core.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.core.util.MessageHelper;
import com.jfsnpm.jfsnpm.demo.txa;
import com.jfsnpm.jfsnpm.demo.txb;
import com.jfsnpm.jfsnpm.plugin.bjui.Bjui;
import com.jfsnpm.jfsnpm.plugin.bjui.BjuiRender;

public class DemoController extends Controller {
	public void language(){
	}
	public void tree(){
	}
	@Before(Tx.class)
	public void testtx() throws Exception{
		txa.savea();
		txb.saveb();
		renderText("ok");
	}
	public void sendmsg() throws UnsupportedEncodingException{
		String title = "通知";
		String to = getPara("to");
		String msg = URLDecoder.decode(getPara("msg"),"utf-8");
		if(MessageHelper.sendNotify(title, to, msg)){
			renderText("ok");
		}else{
			renderText("error");
		}
	}
	public void timeout(){
		render(BjuiRender.timeout());
	}
	public void getjson(){
		renderJson(Db.find("SELECT * FROM jfsnpm_role"));
	}
	public void success(){
		render(BjuiRender.success("成功"));
	}
	public void editsuccess(){
		String json = getPara("json");
		json = json.replaceAll("\"addFlag\":true", "\"addFlag\":false");
		renderJson(json);
	}
	public void datagrid_remote(){
	}
	public void datagrid_remote_get(){
		renderJson(Bjui.use().page("select * from jfsnpm_logs order by id desc", this));
	}
	/*public void datagrid_remote_get_export(){
		String[] headers = {"id","用户","URL"};
		String[] columns = {"id","userId","url"};
		render(AppHelper.exportExcel(Bjui.use().find("select * from jfsnpm_logs order by id desc", "/Demo/datagrid_remote_get", this),
				"DEMO导出LOGS", "all", headers,columns));
	}*/
	public void testhtml(){
		renderHtml("<h1>标题1</h1></br>测试换行</br>${rootpath}");
	}
}
