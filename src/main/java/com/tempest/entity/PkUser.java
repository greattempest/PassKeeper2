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
    private Long id;
    
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
}
