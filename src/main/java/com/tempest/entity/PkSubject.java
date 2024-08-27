package com.tempest.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pk_subject") // 表名称
public class PkSubject extends BaseEntity {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 使用数据库自增
	private Long id;

	// 用户ID
	@Column
	private String userid;

	// 类型
	@Column
	private String objecttype;

	// 对象ID
	@Column
	private String objid;

	// 对象密码
	@Column
	private String objpass;

	// 查询密码
	@Column
	private String objquerypass;

	// 手机
	@Column
	private String phone;

	// 邮箱
	@Column
	private String email;

	// 提示问题
	@Column
	private String tipquestion;

	// 提示答案
	@Column
	private String tipanswer;

	// 网站地址
	@Column
	private String weburl;

	// 商户名称
	@Column
	private String businame;

	// 期限
	@Column
	private String limits;

	// 备注
	@Column
	private String remark;

	// 新增时间
	@Column
	private Date addtime;

	// 更新时间
	@Column
	private String updatetime;

}
