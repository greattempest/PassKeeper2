package com.tempest.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tempest.shiro.User;

@Controller
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
	
	@RequestMapping("/getlogin")
    public String getlogin(User user) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUserName(),
                user.getPassword()
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            e.printStackTrace();
            return "没有权限";
        }
        return "redirect:/home";
    }
     //注解验角色和权限
    @RequiresRoles("admin")
    @RequiresPermissions("add")
    @RequestMapping("/index")
    public String index() {
        return "index!";
    }

}
