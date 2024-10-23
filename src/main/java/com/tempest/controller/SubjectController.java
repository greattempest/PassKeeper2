package com.tempest.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tempest.common.ResponseUtil;
import com.tempest.entity.PkSubject;
import com.tempest.math.AES256Util;
import com.tempest.math.AESUtil;
import com.tempest.math.Code;
import com.tempest.service.SubjectService;
import com.tempest.shiro.User;
import com.tempest.util.Response;

@RestController
@RequestMapping("/subject")
public class SubjectController {
	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping("/save")
	public Response savesubject(@RequestParam Map<String, String> para,HttpSession httpSession,HttpServletRequest request) {
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
		PkSubject subject = new PkSubject();
		subject.setUserid(user.getId()+"");
		subject.setAddtime(new Date());
		subject.setObjid(para.get("objid"));
		subject.setObjecttype(para.get("objtype"));
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
		subjectService.save(subject);
		//System.out.println((new Date().getTime())-start);
		return ResponseUtil.success(subject);
    }
	
	@PostMapping("/del")
	@ResponseBody
    public Response delsubject(@RequestParam Map<String, String> para,HttpServletRequest request) {
		String json="";
		String id = para.get("id");
		try {
			subjectService.deleteById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseUtil.error("删除失败。");
		}
		return ResponseUtil.success();
    	
    }
	
	@RequestMapping("/detail")
	public Response detailsubject(@RequestParam Map<String, String> para,HttpSession httpSession,HttpServletRequest request) {
		/*Account account = (Account) httpSession.getAttribute("user");
		//测试用，如果未登录则模拟登录0号用户
		if(account==null) {
			account=accountService.selectById(0);
			request.getSession().setAttribute("user", account);
		}*/
		String id = para.get("id");
		PkSubject subject = new PkSubject();
		if(id!=null)
			subject=subjectService.get(id);
		
		return ResponseUtil.success(subject);
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
		PkSubject subject = new PkSubject();
		if(id!=null)
			subject=subjectService.get(id);
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
