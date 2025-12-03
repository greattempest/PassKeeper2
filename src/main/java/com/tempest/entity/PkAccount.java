package com.tempest.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pk_account") // 表名称
public class PkAccount extends BaseEntity{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 使用数据库自增
	private String id;

	// 用户ID
	@Column
	private String userid;

	// 账号
	@Column
	private String account;

	// 类型
	@Column
	private String type;

	// 密码
	@Column
	private String password;

	// 属性1
	@Column
	private String property1;

	// 属性2
	@Column
	private String property2;

	// 属性3
	@Column
	private String property3;

	// 属性4
	@Column
	private String property4;

	// 属性5
	@Column
	private String property5;

	// 属性6
	@Column
	private String property6;

	// 属性7
	@Column
	private String property7;

	// 属性8
	@Column
	private String property8;

	// 属性9
	@Column
	private String property9;

	// 属性10
	@Column
	private String property10;

	// 标志
	@Column
	private String flag;

	// 编码版本
	@Column
	private String codeversion;

	// 创建时间
	@Column
	private Date createdate;

	// 更新时间
	@Column
	private String updatedate;

}
