package com.xxc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.xxc.po.TextMessage;

public class MessageUtil {
	
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_IMAGE= "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	
	/**
	 * xml转为map集合
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public static Map<String,String>xmlToMap(HttpServletRequest request) throws IOException, DocumentException
	{
		Map<String,String> map = new HashMap<String,String>();
		
SAXReader reader = new SAXReader();
		
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		
		Element root = doc.getRootElement();
		
		List<Element> list = root.elements();
		
		for(Element e : list)
		{
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}
	
	public static String textMaessageToXml(TextMessage textMessage)
	{
		XStream xstream = new XStream();
		xstream.alias("xml",textMessage.getClass());
		return xstream.toXML(textMessage);
	}
	
	/**
	 *拼接文本消息
	 * @return
	 */
	public static String initText(String toUserName,String fromUserName,String content)
	{
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
	 return textMaessageToXml(text);	
	}
	
	/**
	 * 主菜单
	 * @return
	 */
	public static String menuText()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎你的关注，请按照菜单提示进行操作:\n\n");
		sb.append("1. 公众号介绍\n");
		sb.append("2. 内容测试\n\n");
		sb.append("回复?调出此菜单。");
		return sb.toString();		
	}
	
	public static String firstMenu()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("本公众号主要涉及人文，科技，历史的介绍");
		return sb.toString();		
	}
	
	public static String secondMenu()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("Apple Watch是苹果公司于2014年9月公布的一款智能手表，");
		sb.append("分别为普通款（Apple Watch）、运动款（Apple Watch Sport）和定制款（Apple Watch Edition）三种。");
		sb.append("Apple Watch采用蓝宝石屏幕与Force Touch触摸技术，有多种颜色可供选择，能使用微信、微博等已开发Apple Watch专用的软件。");
		return sb.toString();		
	}
}
