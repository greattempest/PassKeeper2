package com.tempest.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tempest.entity.PkUser;
import com.tempest.shiro.User;
import com.tempest.util.RandomValidateCodeUtil;
import com.tempest.util.Response;
import com.tempest.util.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
public class LoginController {
	//@Autowired
	//private AccountService accountService;

	/*@PostMapping("/valid")
    public String post(@RequestBody Map<String, String> para,HttpServletRequest request) {
		String json="";
		Account account = accountService.selectByAccount(para.get("username"));
		if(account==null)
			json="{\"code\":\"-1\"}";
		else if(!account.getPassword().equals(para.get("pass"))){
			json="{\"code\":\"2\"}";
		}else {
			json="{\"code\":\"1\",\"index\":\"/home\"}";
			request.getSession().setAttribute("user", account);
		}
    	
        return json;
    }*/
	
	@RequestMapping("/login")
    public String login() {
        return "login";
    }
	
	@RequestMapping("/logout")
    public Response logout() {
		ServletRequestAttributes requestAttributes = ServletRequestAttributes.class.
		        cast(RequestContextHolder.getRequestAttributes());
		    HttpServletRequest contextRequest = requestAttributes.getRequest(); 
            HttpSession session = contextRequest.getSession();
            session.removeAttribute("user");
        return ResponseUtil.success();
    }
	
	@RequestMapping("/getlogin")
    public Response getlogin(@RequestBody PkUser user) {

		ServletRequestAttributes requestAttributes = ServletRequestAttributes.class.
		        cast(RequestContextHolder.getRequestAttributes());
		    HttpServletRequest contextRequest = requestAttributes.getRequest(); 
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
        		user.getUsername(),
        		user.getPassword()
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            PkUser loginUser = (PkUser) subject.getPrincipal();
            if(loginUser.getLockdate()!=null && loginUser.getLockdate().getTime()>(new Date().getTime()-900*1000))
            	return ResponseUtil.error("账号已锁定，请稍后重试！");
            //这里做的不是登录成功标记，而且我们页面上需要使用登陆者的信息，所以我们保存登陆者的信息
            
            HttpSession session = contextRequest.getSession();
            session.setAttribute("user",loginUser);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseUtil.error("账号或密码错误！");
        } catch (AuthorizationException e) {
            e.printStackTrace();
            return ResponseUtil.error("没有权限");
        }
        return ResponseUtil.success(user);
    }
     //注解验角色和权限
    @RequiresRoles("admin")
    @RequiresPermissions("add")
    @RequestMapping("/index")
    public String index() {
        return "index!";
    }
    
    /**
     * 生成验证码
     */
    @GetMapping("/getVerify")
    public void getVerify(javax.servlet.http.HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

}
