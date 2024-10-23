package com.tempest.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tempest.entity.PkSubject;
import com.tempest.math.AES256Util;
import com.tempest.math.AESUtil;
import com.tempest.math.Code;
import com.tempest.service.AccountService;
import com.tempest.service.SubjectService;
import com.tempest.shiro.User;

@Controller
public class HomeController {
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private AccountService accountService;
	
	@RequiresAuthentication
	@RequestMapping("/home")
	public String home(Model model,HttpSession httpSession,HttpServletRequest request) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		httpSession.setAttribute("user",user);
		//Account account = (Account) httpSession.getAttribute("user");
		//测试用，如果未登录则模拟登录0号用户
		/*if(account==null) {
			account=accountService.selectById(0);
			request.getSession().setAttribute("user", account);
		}*/
		
		List<PkSubject> list = subjectService.findAll();
		long count = subjectService.count();
		request.setAttribute("count", count);
		request.setAttribute("list", list);
    	
        return "home";
    }
	
	
}
