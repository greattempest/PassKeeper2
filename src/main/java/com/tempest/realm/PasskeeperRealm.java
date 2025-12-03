package com.tempest.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.tempest.entity.PkUser;
import com.tempest.service.UserService;

import jakarta.annotation.Resource;

public class PasskeeperRealm extends AuthorizingRealm {

    //这里因为没有调用后台，直接默认只有一个用户("luoguohui"，"123456")
    //private static final String USER_NAME = "zhoujie";  
    //private static final String PASSWORD = "mfkrgvh";  
	
	@Resource
	UserService userService;

    /* 
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) { 
        Set<String> roleNames = new HashSet<String>();  
        Set<String> permissions = new HashSet<String>();  
        roleNames.add("administrator");//添加角色
        permissions.add("home");  //添加权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);  
        info.setStringPermissions(permissions);  
        return info;  
    }

    /* 
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String password = "*****";
        PkUser user = null;
        if (token.getPassword() != null)
        {
            password = new String(token.getPassword());
        }
        try{
        	user = userService.login(token.getUsername(), password);
        	if(user==null || user.getId()==null || user.getId().equals(""))
        		throw new AuthenticationException();  
            //return new SimpleAuthenticationInfo(USER_NAME, DigestUtils.md5DigestAsHex(PASSWORD.getBytes()),getName());  
            return new SimpleAuthenticationInfo(user, password,getName());  
        }catch(Exception e){
            throw new AuthenticationException();  
        }
        
    }
    

}


