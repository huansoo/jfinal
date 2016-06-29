package com.jfsnpm.jfsnpm.plugin.bjui;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;

import com.jfinal.render.Render;
import com.jfinal.render.RenderException;
/**
 * statusCode 		int 	必选。状态码(ok = 200, error = 300, timeout = 301)，可以在BJUI.init时配置三个参数的默认值。
 * message 			string 	可选。信息内容。
 * tabid 			string 	可选。待刷新navtab id，多个id以英文逗号分隔开，当前的navtab id不需要填写，填写后可能会导致当前navtab重复刷新。
 * dialogid 		string 	可选。待刷新dialog id，多个id以英文逗号分隔开，请不要填写当前的dialog id，要控制刷新当前dialog，请设置dialog中表单的reload参数。
 * divid 			string 	可选。待刷新div id，多个id以英文逗号分隔开，请不要填写当前的div id，要控制刷新当前div，请设置该div中表单的reload参数。
 * closeCurrent 	boolean 可选。是否关闭当前窗口(navtab或dialog)。
 * forward 			string 	可选。跳转到某个url。
 * forwardConfirm 	string 	可选。跳转url前的确认提示信息。
 * 
 * 使用静态方法
 * render(BjuiRender.success("成功"));
 * 自定义使用可连续用.号
 * render(BjuiRender.success("成功").closeCurrent("false"));
 * 
 * @author 天为之殇
 *
 */

public class BjuiRender extends Render {
	private static final String contentType = "application/json; charset=" + getEncoding();
	
	private String statusCode = "200";
	private String message = "";
	private String tabid = "";
	private String dialogid = "";
	private String divid = "";
	private String closeCurrent = "false";
	private String forward = "";
	private String forwardConfirm = "";
	/**
	 * 成功-关闭当前窗口(navtab或dialog)
	 * @return
	 */
	public static BjuiRender success(){
		return new BjuiRender().closeCurrent("true");
	}
	/**
	 * 成功-关闭当前窗口(navtab或dialog)
	 * @param message 提示信息
	 * @return
	 */
	public static BjuiRender success(String message){
		return new BjuiRender().message(message).closeCurrent("true");
	}
	/**
	 * 成功
	 * @param message 提示信息
	 * @param closeCurrent 是否关闭当前窗口(navtab或dialog)
	 * @return
	 */
	public static BjuiRender success(String message,boolean closeCurrent){
		return new BjuiRender().message(message).closeCurrent(String.valueOf(closeCurrent));
	}
	/**
	 * 错误-不关闭当前窗口(navtab或dialog)
	 * @return
	 */
	public static BjuiRender error(){
		return new BjuiRender().statusCode("300");
	}
	/**
	 * 错误-不关闭当前窗口(navtab或dialog)
	 * @param message 错误信息
	 * @return
	 */
	public static BjuiRender error(String message){
		return new BjuiRender().statusCode("300").message(message);
	}
	/**
	 * 错误
	 * @param message 错误信息
	 * @param closeCurrent 是否关闭当前窗口(navtab或dialog)
	 * @return
	 */
	public static BjuiRender error(String message,boolean closeCurrent){
		return new BjuiRender().statusCode("300").message(message).closeCurrent(String.valueOf(closeCurrent));
	}
	/**
	 * 超时-不关闭当前窗口(navtab或dialog)
	 * @return
	 */
	public static BjuiRender timeout(){
		return new BjuiRender().statusCode("301");
	}
	/**
	 * 自定义错误类型
	 * @param code
	 *  401 没有权限
	 * 	404 无页面
	 * 	801 参数错误
	 * 	802 请求类型错误
	 */
	public static BjuiRender code(int code){
		BjuiRender bjuiRender = new BjuiRender();
		switch (code) {
		case 401:
			bjuiRender.statusCode("300").message("您没有相关的权限！");
			break;
		case 404:
			bjuiRender.statusCode("300").message("要加载的页面没有找到！");
			break;
		case 801:
			bjuiRender.statusCode("300").message("提供的参数列表不正确！");
			break;
		case 802:
			bjuiRender.statusCode("300").message("请求的类型无法处理！");
			break;
		default:
			bjuiRender.statusCode("300").message("未知的错误代码！");
			break;
		}
		return bjuiRender;
	}
	
	@Override
	public void render() {
		String jsonText = "\"statusCode\":\"{0}\",\"message\":\"{1}\",\"tabid\":\"{2}\",\"dialogid\":\"{3}\","
				+ "\"divid\":\"{4}\",\"closeCurrent\":{5},\"forward\":\"{6}\",\"forwardConfirm\":\"{7}\"";
		jsonText = "{"
				+ MessageFormat.format(jsonText,statusCode,message,tabid,dialogid,divid,closeCurrent,forward,forwardConfirm)
				+ "}";
		PrintWriter writer = null;
		try {
			response.setHeader("Pragma", "no-cache");	// HTTP/1.0 caches might not implement Cache-Control and might only implement Pragma: no-cache
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType(contentType);
			writer = response.getWriter();
	        writer.write(jsonText);
	        writer.flush();
		} catch (IOException e) {
			throw new RenderException(e);
		}
		finally {
			if (writer != null)
				writer.close();
		}
		
	}
	
	public BjuiRender(){
		
	}
	public BjuiRender statusCode(String statusCode){
		this.statusCode = statusCode;
		return this;
	}
	public BjuiRender message(String message){
		this.message = message;
		return this;
	}
	public BjuiRender tabid(String tabid){
		this.tabid = tabid;
		return this;
	}
	public BjuiRender dialogid(String dialogid){
		this.dialogid = dialogid;
		return this;
	}
	public BjuiRender divid(String divid){
		this.divid = divid;
		return this;
	}
	public BjuiRender closeCurrent(String closeCurrent){
		this.closeCurrent = closeCurrent;
		return this;
	}
	public BjuiRender forward(String forward){
		this.forward = forward;
		return this;
	}
	public BjuiRender forwardConfirm(String forwardConfirm){
		this.forwardConfirm = forwardConfirm;
		return this;
	}
}
