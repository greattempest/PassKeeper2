package com.tempest.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	// 创建时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "addtime")
	private Date addtime;



	// 更新时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "updatetime")
	private Date updatetime;



	public Date getAddtime() {
		return addtime;
	}



	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}



	public Date getUpdatetime() {
		return updatetime;
	}



	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	
}
