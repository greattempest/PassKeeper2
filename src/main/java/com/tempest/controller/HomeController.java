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

import com.tempest.jdbc.model.Subject;
import com.tempest.jdbc.service.AccountService;
import com.tempest.jdbc.service.SubjectService;
import com.tempest.math.AES256Util;
import com.tempest.math.AESUtil;
import com.tempest.math.Code;
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
		
		List<Subject> list = subjectService.selectByUserid(user.getId()+"");
		long count = subjectService.countByUserid(user.getId()+"");
		request.setAttribute("count", count);
		request.setAttribute("list", list);
    	
        return "home";
    }
	
	@RequiresAuthentication
	@RequestMapping("/addsubject")
	public String addsubject(Model model,HttpSession httpSession,HttpServletRequest request) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		//Account account = (Account) httpSession.getAttribute("user");
		//测试用，如果未登录则模拟登录0号用户
		/*if(account==null) {
			account=accountService.selectById(0);
			request.getSession().setAttribute("user", account);
		}*/
		
		return "addsubject";
    }
	
	@RequestMapping("/savesubject")
	public String savesubject(@RequestParam Map<String, String> para,HttpSession httpSession,HttpServletRequest request) {
		long start = new Date().getTime();
		//System.out.println((new Date().getTime())-start);
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		/*Account account = (Account) httpSession.getAttribute("user");
		//测试用，如果未登录则模拟登录0号用户
		if(account==null) {
			account=accountService.selectById(0);
			request.getSession().setAttribute("user", account);
		}*/
		//System.out.println((new Date().getTime())-start);
		String userkey = (para.get("userkey"));
		Subject subject = new Subject();
		subject.setUserid(user.getId()+"");
		subject.setAddtime(new Date());
		subject.setObjid(para.get("objid"));
		subject.setObjtype(para.get("objtype"));
		//subject.setObjpass(para.get("objpass"));
		//subject.setObjquerypass(para.get("objquerypass"));
		subject.setPhone(para.get("phone"));
		subject.setEmail(para.get("email"));
		subject.setTipquestion(para.get("tipquestion"));
		subject.setTipanswer(para.get("tipanswer"));
		subject.setWeburl(para.get("weburl"));
		subject.setBusiname(para.get("businame"));
		subject.setLimits(para.get("limits"));
		subject.setRemark(para.get("remark"));
		
		String objpass = para.get("objpass");
		String objquerypass = para.get("objquerypass");
		
		//byte[] passbyte = AES256Util.encrypt(objpass, AES256Util.AesPass);
		//byte[] querypassbyte = AES256Util.encrypt(objquerypass, AES256Util.AesPass);
		
		String passbyte="";
		String querypassbyte="";
		try {
			passbyte = AESUtil.encrypt(AES256Util.AesPass, objpass);
			querypassbyte = AESUtil.encrypt(AES256Util.AesPass, objquerypass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println((new Date().getTime())-start);
		//System.out.println("encode1:"+passbyte);
		
		objpass = Code.CODE(passbyte, userkey);
		objquerypass = Code.CODE(querypassbyte, userkey);
		subject.setObjpass(objpass);
		subject.setObjquerypass(objquerypass);
		//System.out.println((new Date().getTime())-start);
		subjectService.insert(subject);
		//System.out.println((new Date().getTime())-start);
		return "savesubject";
    }
	
	@PostMapping("/delsubject")
	@ResponseBody
    public String delsubject(@RequestParam Map<String, String> para,HttpServletRequest request) {
		String json="";
		String id = para.get("id");
		int i = subjectService.deleteById(id);
		if(i==0)
			json="{\"code\":\"-1\"}";
		else
			json="{\"code\":\"1\"}";
    	
        return json;
    }
	
	@RequestMapping("/detailsubject")
	public String detailsubject(@RequestParam Map<String, String> para,HttpSession httpSession,HttpServletRequest request) {
		/*Account account = (Account) httpSession.getAttribute("user");
		//测试用，如果未登录则模拟登录0号用户
		if(account==null) {
			account=accountService.selectById(0);
			request.getSession().setAttribute("user", account);
		}*/
		String id = para.get("id");
		Subject subject = new Subject();
		if(id!=null)
			subject=subjectService.selectById(id);
		request.setAttribute("subject", subject);
		
		return "detailsubject";
    }
	
	@RequestMapping("/decode")
	@ResponseBody
	public String decode(@RequestParam Map<String, String> para,HttpSession httpSession,HttpServletRequest request) {
		/*Account account = (Account) httpSession.getAttribute("user");
		//测试用，如果未登录则模拟登录0号用户
		if(account==null) {
			account=accountService.selectById(0);
			request.getSession().setAttribute("user", account);
		}*/
		String id = para.get("id");
		String userkey = para.get("userkey");
		Subject subject = new Subject();
		if(id!=null)
			subject=subjectService.selectById(id);
		request.setAttribute("subject", subject);
		String json="";
		String objpass = subject.getObjpass();
		String objquerypass = subject.getObjquerypass();
		objpass = Code.DECODE(objpass, userkey);
		//System.out.println("decode1:"+objpass);
		objquerypass = Code.DECODE(objquerypass, userkey);

		//objpass = AES256Util.decrypt(objpass.getBytes(), AES256Util.AesPass);
		//objquerypass = AES256Util.decrypt(objquerypass.getBytes(), AES256Util.AesPass);

		try {
			objpass = AESUtil.decrypt(AES256Util.AesPass,objpass);
			objquerypass = AESUtil.decrypt(AES256Util.AesPass, objquerypass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		json="{\"pass\":\""+objpass+ "\",\"qpass\":\""+objquerypass+ "\"}";
		
		return json;
    }
	
}
