package com.wbs.entity.duty;

import java.text.SimpleDateFormat;
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

/**
 * 值班日记
 * 
 */
@Entity
@Table(name = "DUTY_DUTY_DIARY")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DutyDiary extends StringIdEntity {

	private String title;
	private String content;
	private Date createTime;
	private String createBy;
	private String createPerson;
	private Date updateTime;
	private String updateBy;
	private String updatePerson;
	private String status;
	private Department department;
	private boolean theSameDay;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(length = 5000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * 创建人姓名
	 * 
	 * @return
	 */
	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	/**
	 * 更新人姓名
	 * 
	 * @return
	 */
	public String getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	/**
	 * 日记状态，参考({@link com.dhcc.bjweathersys.utils.data.DiaryStatus})
	 * 
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 值班岗位
	 * 
	 * @return
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPART_ID", nullable = false)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Transient
	public String getDepartmentName() {
		return department == null ? null : department.getName();
	}
	
	@Transient
	public boolean isTheSameDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 	
		String time=sdf.format(createTime);
		String now_time = sdf.format(new Date());
		return time.equals(now_time);
	}

}
