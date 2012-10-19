package com.wbs.web.struts2.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.springside.zsword.modules.orm.PageBean;
import com.springside.zsword.modules.web.struts2.SimpleActionSupport;
import com.wbs.entity.duty.DutyDiary;
import com.wbs.entity.message.InfoMessage;
import com.wbs.entity.system.User;
import com.wbs.serveice.duty.DutyDiaryService;
import com.wbs.serveice.message.InfoMessageService;
import com.wbs.tools.AppLoginUtils;

/**
 * 管理首页
 */
@SuppressWarnings("serial")
public class IndexAction extends SimpleActionSupport {
	private InfoMessageService messageService;
	private List<InfoMessage> messages;
	private User user;
	private DutyDiaryService diaryService;
	private List<DutyDiary> dutyDiaryList;

	public String execute() throws Exception {
		user = AppLoginUtils.getLoginUser();
		if (isValueBlank(user)) {
			return LOGIN;
		}
		this.messages = this.loadMessages();
		this.dutyDiaryList = this.loadDutyDiaryList();
		return super.execute();
	}

	private List<InfoMessage> loadMessages() {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		String departmentId = AppLoginUtils.getSyncDepartmentId();
		if (departmentId != null) {
			PropertyFilter filter = new PropertyFilter("EQS_department.id",
					departmentId);
			filters.add(filter);
		}
		Page<InfoMessage> page = new PageBean<InfoMessage>(20);
		page.setOrderBy("sendTime");
		page.setOrder(Page.DESC);
		page = messageService.findPage(page, filters);
		return page.getResult();
	}

	private List<DutyDiary> loadDutyDiaryList() {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		String departmentId = AppLoginUtils.getDepartmentId();
		PropertyFilter filter = new PropertyFilter("EQS_department.id",
				departmentId);
		filters.add(filter);
		Page<DutyDiary> page = new PageBean<DutyDiary>(20);
		page.setOrderBy("createTime");
		page.setOrder(Page.DESC);
		page = diaryService.findPage(page, filters);
		return page.getResult();
	}

	public User getUser() {
		return user;
	}

	public List<InfoMessage> getMessages() {
		return messages;
	}

	public List<DutyDiary> getDutyDiaryList() {
		return dutyDiaryList;
	}

	@Autowired
	public void setDiaryService(DutyDiaryService diaryService) {
		this.diaryService = diaryService;
	}

	@Autowired
	public void setMessageService(InfoMessageService messageService) {
		this.messageService = messageService;
	}

}
