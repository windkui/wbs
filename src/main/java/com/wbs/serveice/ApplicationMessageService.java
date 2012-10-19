package com.wbs.serveice;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbs.entity.message.Broadcast;
import com.wbs.entity.message.InfoMessage;
import com.wbs.entity.system.Department;
import com.wbs.serveice.message.BroadcastService;
import com.wbs.serveice.message.InfoMessageService;
import com.wbs.tools.AppLoginUtils;
import com.wbs.tools.message.MessageStatus;
import com.wbs.tools.message.MessageType;

/**
 * 应用消息中心Service
 */
@Service
public class ApplicationMessageService {
	private static final Logger logger = LoggerFactory
			.getLogger(ApplicationMessageService.class);
	private static final String SCRIPT_PAGE = "/";
	private static final String USER_ID = "userId";
	private static final String USER_NAME = "userName";
	private static final String DEPART_ID = "departId";
	private static WebContext wctx;

	public ApplicationMessageService() {
		super();
		ApplicationMessageService.wctx = WebContextFactory.get();
	}

	/**
	 * 发送系统文本消息
	 * 
	 * @param title
	 * @param text
	 */
	public static void sendSystemMessageByText(String title, String text) {
		InfoMessage message = new InfoMessage();
		message.setTitle(title);
		message.setContent(text);
		message.setSendTime(new Date());
		sendMessage(message);
	}

	/**
	 * 发送消息对象
	 * 
	 * @param message
	 */
	public static void sendSystemMessage(InfoMessage message) {
		sendMessage(message);
	}

	/**
	 * 构建消息对象
	 * 
	 * @param from
	 * @param department
	 * @param title
	 * @param content
	 * @return
	 */
	private static InfoMessage buildInfoMessage(String from,
			Department department, String title, String content) {
		InfoMessage message = new InfoMessage();
		message.setFrom(from);
		message.setDepartment(department);
		message.setTitle(title);
		message.setContent(content);
		message.setSendTime(new Date());
		message.setStatus(MessageStatus.UNREAD.ordinal());
		return message;
	}

	/**
	 * 发送部门系统即时消息
	 * 
	 * @param from
	 * @param department
	 * @param title
	 * @param content
	 */
	public static void sendDepartmentMessage(String from,
			Department department, String title, String content) {
		InfoMessage message = buildInfoMessage(from, department, title, content);
		sendMessage(message);
	}

	/**
	 * 处理发送消息
	 * 
	 * @param message
	 */
	private static void sendMessage(InfoMessage message) {
		message.setStatus(MessageStatus.READED.ordinal());
		message.setReadTime(new Date());
		if (!MessageType.success.name().equals(message.getType())) {
			messageService.save(message);
		}
		// Loop over all the users on the current page
		Collection<ScriptSession> pages = loadScriptPages();
		Department msgDepartment = message.getDepartment();
		String msgDepartId = null;
		if (msgDepartment != null) {
			msgDepartId = msgDepartment.getId();
		}
		for (Iterator<ScriptSession> it = pages.iterator(); it.hasNext();) {
			ScriptSession otherSession = it.next();
			if (msgDepartId != null) {
				String sessionDepartId = (String) otherSession
						.getAttribute(DEPART_ID);
				if (!msgDepartId.equals(sessionDepartId)) {
					continue;
				}
			}
			Util util = new Util(otherSession);
			util.addFunctionCall("showAppMessage", message);
		}
	}

	@SuppressWarnings("unchecked")
	private static Collection<ScriptSession> loadScriptPages() {
		WebContext context = WebContextFactory.get();
		if (context == null) {
			context = wctx;
		}
		String currentPage = context.getServletContext().getContextPath()
				+ SCRIPT_PAGE;
		// Loop over all the users on the current page
		Collection<ScriptSession> pages = context
				.getScriptSessionsByPage(currentPage);
		logger.info(currentPage + " ScriptSessions: " + pages.size());
		return pages;
	}

	public static void publishAppBroadcast(Broadcast broadcast) {
		publishBroadcast(broadcast);
	}

	/**
	 * 处理发布系统广播
	 * 
	 * @param broadcast
	 */
	private static void publishBroadcast(Broadcast broadcast) {
		// broadcast.setStatus(BroadcastStatus.PUBLISH);
		broadcastService.save(broadcast);
		Collection<ScriptSession> pages = loadScriptPages();
		for (Iterator<ScriptSession> it = pages.iterator(); it.hasNext();) {
			ScriptSession otherSession = it.next();
			Util util = new Util(otherSession);
			util.addFunctionCall("showAppBroadcast", broadcast);
		}
	}

	/**
	 * 添加在线用户
	 */
	public void addOnlineUser() {
		WebContext context = WebContextFactory.get();
		String userId = AppLoginUtils.getLoginUserId();
		String userName = AppLoginUtils.getLoginUserName();
		String departmentId = AppLoginUtils.getDepartmentId();
		ScriptSession scriptSession = context.getScriptSession();
		scriptSession.setAttribute(USER_ID, userId);
		scriptSession.setAttribute(USER_NAME, userName);
		scriptSession.setAttribute(DEPART_ID, departmentId);
	}

	private static InfoMessageService messageService;
	private static BroadcastService broadcastService;

	@Autowired
	public void setMessageService(InfoMessageService messageService) {
		ApplicationMessageService.messageService = messageService;
	}

	@Autowired
	public void setBroadcastService(BroadcastService broadcastService) {
		ApplicationMessageService.broadcastService = broadcastService;
	}

}
