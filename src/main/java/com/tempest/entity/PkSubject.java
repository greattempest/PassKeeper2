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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getObjecttype() {
		return objecttype;
	}

	public void setObjecttype(String objecttype) {
		this.objecttype = objecttype;
	}

	public String getObjid() {
		return objid;
	}

	public void setObjid(String objid) {
		this.objid = objid;
	}

	public String getObjpass() {
		return objpass;
	}

	public void setObjpass(String objpass) {
		this.objpass = objpass;
	}

	public String getObjquerypass() {
		return objquerypass;
	}

	public void setObjquerypass(String objquerypass) {
		this.objquerypass = objquerypass;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipquestion() {
		return tipquestion;
	}

	public void setTipquestion(String tipquestion) {
		this.tipquestion = tipquestion;
	}

	public String getTipanswer() {
		return tipanswer;
	}

	public void setTipanswer(String tipanswer) {
		this.tipanswer = tipanswer;
	}

	public String getWeburl() {
		return weburl;
	}

	public void setWeburl(String weburl) {
		this.weburl = weburl;
	}

	public String getBusiname() {
		return businame;
	}

	public void setBusiname(String businame) {
		this.businame = businame;
	}

	public String getLimits() {
		return limits;
	}

	public void setLimits(String limits) {
		this.limits = limits;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

}
