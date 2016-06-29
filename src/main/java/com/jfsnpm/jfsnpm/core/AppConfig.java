package com.jfsnpm.jfsnpm.core;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.snaker.jfinal.plugin.SnakerPlugin;


import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.i18n.I18nInterceptor;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.AnsiSqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.FreeMarkerRender;
import com.jfinal.render.ViewType;
import com.jfsnpm.jfsnpm.core.global.IAuth;
import com.jfsnpm.jfsnpm.core.global.IFlow;
import com.jfsnpm.jfsnpm.core.global.IFlowArgs;
import com.jfsnpm.jfsnpm.core.global.IMessage;
import com.jfsnpm.jfsnpm.core.global.IOrg;
import com.jfsnpm.jfsnpm.core.global.ISql;
import com.jfsnpm.jfsnpm.core.global.IUser;
import com.jfsnpm.jfsnpm.core.global.IUserRemote;
import com.jfsnpm.jfsnpm.core.global.IUserThird;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.core.util.FreeMarkerHelper;
import com.jfsnpm.jfsnpm.core.web.controller.IndexController;
import com.jfsnpm.jfsnpm.core.web.interceptor.AuthInterceptor;
import com.jfsnpm.jfsnpm.core.web.interceptor.ExceptionInterceptor;
import com.jfsnpm.jfsnpm.core.web.interceptor.VisitLogInterceptor;
import com.jfsnpm.jfsnpm.plugin.autoroutes.AutoBindRoutes;
import com.jfsnpm.jfsnpm.plugin.mail.MailPlugin;
import com.jfsnpm.jfsnpm.plugin.redis.RedisPlugin;
import com.jfsnpm.jfsnpm.plugin.sqlinxml.SqlInXmlPlugin;

import freemarker.template.TemplateModelException;


public class AppConfig extends JFinalConfig {
	
	public static Properties properties;//配置文件
	public static boolean DevMode=false;//是否开发模式
	public static boolean flowSqlForm = false;//流程表单使用数据库实现
	public static C3p0Plugin c3p0Plugin;//连接池
	public static String rootpath=JFinal.me().getContextPath();
	
	public static IUser userimpl;
	public static IUserRemote userRemoteimpl;
	public static IUserThird userThirdimpl;
	public static ISql sqlimpl;
	public static IAuth authimpl;
	public static IOrg orgimpl;
	public static IFlow flowimpl;
	public static IMessage messageimpl;
	public static IFlowArgs flowArgsimpl;
	
	@Override
	public void configConstant(Constants me) {
		properties = loadPropertyFile("config.txt");//读取配置文件
		DevMode=getPropertyToBoolean("devMode", true);//获取是否开发模式
		flowSqlForm=getPropertyToBoolean("flowSqlForm", false);//流程表单使用数据库实现
		me.setDevMode(DevMode);//设置开发模式
		me.setViewType(ViewType.FREE_MARKER);//设置使用FreeMarker模板引擎
		me.setBaseViewPath("WEB-INF/jfsnpm/");//定义默认模板基础路径
		me.setBaseDownloadPath(properties.getProperty("filepath","/jfsnpmFileSystem/file/"));//默认render文件的路径
		me.setBaseUploadPath(properties.getProperty("filepath","/jfsnpmFileSystem/file/"));//默认上传文件的路径
		me.setI18nDefaultBaseName("i18n");
		me.setError404View("/WEB-INF/jfsnpm/error/404.html");
		me.setError500View("/WEB-INF/jfsnpm/error/500.html");
		
		//加载接口的实现
		userimpl = (IUser) AppHelper.newInstance(getProperty("userimpl","com.jfsnpm.jfsnpm.core.impl.UserImpl"));
		if(userimpl==null){throw new IllegalArgumentException("用户接口实现类加载失败!");}
		userRemoteimpl = (IUserRemote) AppHelper.newInstance(getProperty("userRemoteimpl","com.jfsnpm.jfsnpm.core.impl.UserRemoteImpl"));
		if(userRemoteimpl==null){throw new IllegalArgumentException("远程用户接口实现类加载失败!");}
		userThirdimpl = (IUserThird) AppHelper.newInstance(getProperty("userThirdimpl","com.jfsnpm.jfsnpm.core.impl.UserThirdImpl"));
		if(userThirdimpl==null){throw new IllegalArgumentException("第三方用户接口实现类加载失败!");}
		sqlimpl = (ISql)AppHelper.newInstance(getProperty("sqlimpl","com.jfsnpm.jfsnpm.core.impl.SqlImpl"));
		if(sqlimpl==null){throw new IllegalArgumentException("SQL语句接口实现类加载失败!");}
		authimpl = (IAuth)AppHelper.newInstance(getProperty("authimpl","com.jfsnpm.jfsnpm.core.impl.AuthImpl"));
		if(authimpl==null){throw new IllegalArgumentException("权限接口实现类加载失败!");}
		orgimpl = (IOrg)AppHelper.newInstance(getProperty("orgimpl","com.jfsnpm.jfsnpm.core.impl.OrgImpl"));
		if(orgimpl==null){throw new IllegalArgumentException("组织接口实现类加载失败!");}
		flowimpl = (IFlow)AppHelper.newInstance(getProperty("flowimpl","com.jfsnpm.jfsnpm.core.impl.FlowImpl"));
		if(flowimpl==null){throw new IllegalArgumentException("流程接口实现类加载失败!");}
		messageimpl = (IMessage)AppHelper.newInstance(getProperty("messageimpl","com.jfsnpm.jfsnpm.core.impl.MessageImpl"));
		if(messageimpl==null){throw new IllegalArgumentException("消息接口实现类加载失败!");}
		flowArgsimpl = (IFlowArgs)AppHelper.newInstance(getProperty("flowArgsimpl","com.jfsnpm.jfsnpm.core.impl.FlowArgsImpl"));
		if(flowArgsimpl==null){throw new IllegalArgumentException("流程参与者接口实现类加载失败!");}
		
	}

	@Override
	public void configHandler(Handlers me) {
	}

	@Override
	public void configInterceptor(Interceptors me) {
		if(!DevMode) me.add(new ExceptionInterceptor());
		me.add(new I18nInterceptor());
		me.add(new AuthInterceptor());
		me.add(new VisitLogInterceptor());
	}

	@Override
	public void configPlugin(Plugins me) {
		RedisPlugin redis = new RedisPlugin();
		me.add(redis);
		//主系统数据库
		String databaseUse = getProperty("databaseUse","master");
		c3p0Plugin = new C3p0Plugin(getProperty(databaseUse+"_jdbcUrl"),
				getProperty(databaseUse+"_user"), getProperty(databaseUse+"_password").trim(),
				getProperty(databaseUse+"_jdbcClass"));
		me.add(c3p0Plugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		System.out.println(databaseUse+":"+getProperty(databaseUse+"_jdbcUrl")+" "+
				getProperty(databaseUse+"_user")+" "+ getProperty(databaseUse+"_password").trim()+" "+
				getProperty(databaseUse+"_jdbcClass"));
		if(DevMode) arp.setShowSql(true);//开发模式显示执行的SQL
		if(getProperty(databaseUse+"_jdbcClass").contains("sqlserver")) arp.setDialect(new AnsiSqlDialect());//数据库方言
		//其他数据库
		/*String otherDataBase[] = getProperty("otherDataBase","").split(",");
		for(String dbName:otherDataBase){
			if(AppHelper.isEmpty(dbName)) continue;
			C3p0Plugin c3p0other = new C3p0Plugin(getProperty(dbName+"_jdbcUrl"),
					getProperty(dbName+"_user"), getProperty(dbName+"_password").trim(),
					getProperty(dbName+"_jdbcClass"));
			me.add(c3p0other);
			ActiveRecordPlugin arpother = new ActiveRecordPlugin(dbName,c3p0other);
			me.add(arpother);
			System.out.println(databaseUse+":"+getProperty(databaseUse+"_jdbcUrl")+" "+
					getProperty(databaseUse+"_user")+" "+ getProperty(databaseUse+"_password").trim()+" "+
					getProperty(databaseUse+"_jdbcClass"));
			if(DevMode) arpother.setShowSql(true);//开发模式显示执行的SQL
			if(getProperty(dbName+"_jdbcClass").contains("sqlserver")) arpother.setDialect(new AnsiSqlDialect());//数据库方言
		}*/
		//SQL XML 插件,默认路径 /sql
		me.add(new SqlInXmlPlugin());
		//流程插件
//		SnakerPlugin snakerPlugin = new SnakerPlugin(c3p0Plugin, properties);
//		me.add(snakerPlugin);
//		//调度插件
//		QuartzPlugin quartzPlugin = new QuartzPlugin();
//		me.add(quartzPlugin);
		//邮件插件
		if(properties.getProperty("mail_use").equals("true")){
			MailPlugin mailplugin = new MailPlugin();
			me.add(mailplugin);
		}
		
	}

	@Override
	public void configRoute(Routes me) {
		me.add(new AutoBindRoutes());//自动扫描绑定路由
		me.add("/", IndexController.class);
	}
	
	/**
	 * 配置模板全局变量
	 */
	public void afterJFinalStart(){
		try {
			FreeMarkerRender.getConfiguration().setClassicCompatible(true);
			FreeMarkerRender.getConfiguration().setDateFormat("yyyy/mm/dd");
			FreeMarkerRender.getConfiguration().setSharedVariable("rootpath", JFinal.me().getContextPath());
			FreeMarkerRender.getConfiguration().setSharedVariable("tool",new FreeMarkerHelper());
		} catch (TemplateModelException e) {
			Logger.getLogger(getClass()).error("FreeMarker设置错误.", e);
		}
		
	  }
	/**
	 * 支持获取环境变量
	 */
	@Override
	public String getProperty(String key) {
		String result = super.getProperty(key);
		return AppHelper.getEnv(result);
	}
	/**
	 * 支持获取环境变量
	 */
	@Override
	public String getProperty(String key, String defaultValue) {
		String result = super.getProperty(key, defaultValue);
		return AppHelper.getEnv(result);
	}
	

}
