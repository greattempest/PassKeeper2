package com.tempest.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pk_user") // 表名称
public class PkUser extends BaseEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 使用数据库自增
    private String id;
    
    //账号
    @Column
    private String username;
    
    //密码
    @Column
    private String password;
    
    //姓名
    @Column
    private String relname;
    
    //邮箱
    @Column
    private String email;
    
    //标志
    @Column
    private String flag;
    
    //注册时间
    @Column
    private Date registerdate;
    
    //登陆时间
    @Column
    private Date logindate;
    
    //错误次数
    @Column
    private Integer errorcount;
    
    //锁定时间
    @Column
    private Date lockdate;
    
    //mac
    @Column
    private String mac;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRelname() {
		return relname;
	}

	public void setRelname(String relname) {
		this.relname = relname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getRegisterdate() {
		return registerdate;
	}

	public void setRegisterdate(Date registerdate) {
		this.registerdate = registerdate;
	}

	public Date getLogindate() {
		return logindate;
	}

	public void setLogindate(Date logindate) {
		this.logindate = logindate;
	}

	public Integer getErrorcount() {
		return errorcount;
	}

	public void setErrorcount(Integer errorcount) {
		this.errorcount = errorcount;
	}

	public Date getLockdate() {
		return lockdate;
	}

	public void setLockdate(Date lockdate) {
		this.lockdate = lockdate;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
}
