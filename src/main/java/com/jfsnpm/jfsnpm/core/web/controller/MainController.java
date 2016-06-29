package com.jfsnpm.jfsnpm.core.web.controller;

import java.util.ArrayList;
import java.util.List;

import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.RoseType;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.data.PieData;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Pie;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfsnpm.jfsnpm.core.dao.User;
import com.jfsnpm.jfsnpm.core.service.FlowService;
import com.jfsnpm.jfsnpm.core.service.UserService;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.plugin.bjui.Bjui;

public class MainController extends Controller {
	/**
	 * 我的信息
	 */
	public void myinfor(){
		setAttr("user", UserService.getUserInfor(this));
	}
	/**
	 * 修改密码
	 */
	public void mypassword(){
		User user = UserService.getUserInfor(this);
		setAttr("id", user.getUserId());
	}
	/**
	 * 主页
	 */
	public void mainpage(){}
	/**
	 * 启动流程
	 */
	public void process(){}
	public void process_get(){
		renderJson(FlowService.getMyProcess(this));
	}
	/**
	 * 待办任务
	 */
	public void mytodo(){}
	public void mytodopage(){
		setAttr("page", 15);
		render("mytodo.html");
	}
	public void mytodo_get(){
		renderJson(FlowService.getMyTodoList(this));
	}
	/**
	 * 我发起的未完成的任务
	 */
	public void myrelease(){}
	public void myrelease_get(){
		renderJson(FlowService.getMyRelease(this));
	}
	/**
	 * 我处理过的流程实例
	 */
	public void myhistory(){}
	public void myhistory_get(){
		User user = UserService.getUserInfor(this);
		String userId = user.getUserId();
		renderJson(Bjui.use().page(AppHelper.getSql("flowprocess.getMyHistory"), this, userId));
	}
	public void myhistory_export(){
		String[] headers = {"状态","流程名称","任务名称","发起人","标题","建立日期","实例ID"};
		String[] columns = {"order_State","process_name","task_name","userName","orderTitle","create_Time","orderId"};
		User user = UserService.getUserInfor(this);
		String userId = user.getUserId();
//		render(AppHelper.exportExcel(Bjui.use().find(AppHelper.getSql("flowprocess.getMyHistory"), "/Main/myhistory_get", this, userId),
//				"myhistory我的流程历史", "all", headers,columns));
	}
	/**
	 * 菜单点击量饼图
	 */
	public void menuClickPie(){
		GsonOption option = new GsonOption();
		//标题
		option.title().text("菜单点击量统计").textStyle().fontFamily("Verdana");
		//工具条
		option.toolbox().show(true)
			.feature(
					Tool.dataView,
					new MagicType(Magic.pie),
					Tool.restore,
					Tool.saveAsImage
					);
		//数据
		option.tooltip();
		option.calculable(true);
		List<Record> dataList = Db.find(AppHelper.getSql("menu.getMenuClickPie"));
		List<String> legends = new ArrayList<String>();
		for(Record data:dataList){
			legends.add(data.getStr("text"));
		}
		option.legend(legends.toArray());
		option.legend().setOrient(Orient.vertical);
		option.legend().setX("left");
		option.legend().setY("center");
		Pie pie = new Pie().name("点击量");
		List<PieData> piedatas = new ArrayList<PieData>();
		for(Record data:dataList){
			piedatas.add(new PieData(data.getStr("text"), Long.parseLong(data.get("qty").toString())));
		}
		pie.data(piedatas.toArray());
		pie.roseType(RoseType.area);
		option.series(pie);
		//System.out.println(option.toString());
		renderJson(option.toString());
	}
	/**
	 * 菜单点击量线图
	 */
	public void menuClickLine(){
		GsonOption option = new GsonOption();
		//标题
		option.title().text("菜单点击量趋势").textStyle().fontFamily("Verdana");
		//工具条
		option.toolbox().show(true)
			.feature(
					Tool.dataView,
					new MagicType(Magic.line,Magic.bar),
					Tool.restore,
					Tool.saveAsImage
					);
		//数据
		option.tooltip();
		option.calculable(true);
		option.legend("点击量");
		option.yAxis(new ValueAxis());
		CategoryAxis xaxis = new CategoryAxis();
		List<String> x = new ArrayList<String>();
		List<Long> y = new ArrayList<Long>();
		Line line = new Line();
		List<Record> dataList = Db.find(AppHelper.getSql("menu.getMenuClickLine"));
		for(Record data:dataList){
		x.add(data.get("logdate").toString());
		y.add(Long.parseLong(data.get("logcount").toString()));
		}
		xaxis.data(x.toArray());
		option.xAxis(xaxis);
		line.name("点击量").data(y.toArray());
		option.series(line);
		//System.out.println(option.toString());
		renderJson(option.toString());
	}
	
}
