package com.wbs.entity.common;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * String型主键数据实体映射基类
 * 
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.
 * 
 * 子类可重载getId()函数重定义id的列名映射和生成策略.
 * 
 */
@MappedSuperclass
public abstract class StringIdEntity extends AbstractIdEntity<String> {

	@Id
	@Column(columnDefinition = "CHAR(32)")
	// @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return getIdObject();
	}

	public void setId(String id) {
		super.setId(id);
	}
}
