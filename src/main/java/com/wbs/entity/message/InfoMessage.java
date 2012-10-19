package com.wbs.entity.message;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wbs.entity.common.StringIdEntity;
import com.wbs.entity.system.Department;
import com.wbs.tools.message.MessageStatus;

/**
 * 消息提醒
 * 
 */
@Entity
@Table(name = "MESS_INFO_MESSAGE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InfoMessage extends StringIdEntity {
	private String title;
	private String content;
	private Integer status = MessageStatus.UNREAD.ordinal();
	private String from;
	private Department department;
	private Date sendTime;
	private Date readTime;
	private String type;

	/**
	 * 消息标题
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 消息内容
	 * 
	 * @return
	 */
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 消息状态(MessageStatus枚举)
	 * 
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 发件人
	 * 
	 * @return
	 */
	@Column(name = "from_user")
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * 发送时间
	 * 
	 * @return
	 */
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * 阅读时间
	 * 
	 * @return
	 */
	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	/**
	 * 所属岗位
	 * 
	 * @return
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPART_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Transient
	public String getDepartmentName() {
		return this.department == null ? null : department.getName();
	}

	/**
	 * 消息类型
	 * @return
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
