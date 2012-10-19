package com.wbs.entity.message;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wbs.entity.common.audit.AuditableStringIdEntity;
import com.wbs.tools.message.BroadcastStatus;
import com.wbs.tools.message.BroadcastType;

/**
 * 系统广播实体
 * 
 */
@Entity
@Table(name = "MESS_BROADCAST")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Broadcast extends AuditableStringIdEntity {
	private String title;
	private String type;
	private String status = BroadcastStatus.NORMAL.name();
	private String content;
	private Date endTime;

	/**
	 * 标题
	 * 
	 * @return
	 */
	@Column(nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 类型
	 * 
	 * @return
	 */
	@Column(length = 20, nullable = false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Transient
	public void setType(BroadcastType type) {
		this.type = type.name();
	}

	@Column(length = 20)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Transient
	public void setStatus(BroadcastStatus status) {
		this.status = status.name();
	}

	/**
	 * 内容
	 * 
	 * @return
	 */
	@Column(length = 10000, nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 结束日期
	 * 
	 * @return
	 */
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
