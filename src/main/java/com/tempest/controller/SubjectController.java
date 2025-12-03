package com.tempest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson2.JSONObject;
import com.tempest.common.Query;
import com.tempest.common.ResponseUtil;
import com.tempest.entity.PkSubject;
import com.tempest.math.AES256Util;
import com.tempest.math.AESUtil;
import com.tempest.math.Code;
import com.tempest.service.SubjectService;
import com.tempest.shiro.User;
import com.tempest.util.Response;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/subject")
public class SubjectController {
	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping("/save")
	@ResponseBody
	public Response savesubject(@RequestBody Map<String, String> para1,HttpSession httpSession,HttpServletRequest request) {
		long start = new Date().getTime();
		//System.out.println((new Date().getTime())-start);
		//User user = (User) SecurityUtils.getSubject().getPrincipal();
		/*Account account = (Account) httpSession.getAttribute("user");
		//测试用，如果未登录则模拟登录0号用户
		if(account==null) {
			account=accountService.selectById(0);
			request.getSession().setAttribute("user", account);
		}*/
		//System.out.println((new Date().getTime())-start);
		Map<String, Object> para = (Map<String, Object>) JSONObject.parseObject(para1.get("item"));
		PkSubject oldSubjecy = new PkSubject();
		String userkey = ((String)para1.get("userkey"));
		PkSubject subject = new PkSubject();
		String id = (String)para.get("id");
		String decodedpass = (String) para1.get("decodedpass");
		String decodedqpass = (String) para1.get("decodedqpass"); 
		if(id==null || id.equals("") || id.equals("null"))
			id = null;
		else {
			subject.setId(id);
			oldSubjecy = subjectService.get(id);
		}
		//subject.setUserid(user.getId()+"");
		subject.setAddtime(new Date());
		subject.setObjid((String)para.get("objid"));
		subject.setObjecttype((String)para.get("objecttype"));
		//subject.setObjpass(para.get("objpass"));
		//subject.setObjquerypass(para.get("objquerypass"));
		subject.setPhone((String)para.get("phone"));
		subject.setEmail((String)para.get("email"));
		subject.setTipquestion((String)para.get("tipquestion"));
		subject.setTipanswer((String)para.get("tipanswer"));
		subject.setWeburl((String)para.get("weburl"));
		subject.setBusiname((String)para.get("businame"));
		subject.setLimits((String)para.get("limits"));
		subject.setRemark((String)para.get("remark"));
		subject.setRemoved("0");
		
		String objpass = (String) para.get("objpass");
		String objquerypass = (String) para.get("objquerypass");
		
		//byte[] passbyte = AES256Util.encrypt(objpass, AES256Util.AesPass);
		//byte[] querypassbyte = AES256Util.encrypt(objquerypass, AES256Util.AesPass);
		
		String passbyte="";
		String querypassbyte="";
		if(decodedpass!=null || decodedqpass!=null)
		try {
			passbyte = AESUtil.encrypt(AES256Util.AesPass, decodedpass);
			querypassbyte = AESUtil.encrypt(AES256Util.AesPass, decodedqpass);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println((new Date().getTime())-start);
		//System.out.println("encode1:"+passbyte);
		
		objpass = Code.CODE(passbyte, userkey);
		objquerypass = Code.CODE(querypassbyte, userkey);
		subject.setObjpass(objpass);
		subject.setObjquerypass(objquerypass);
		if(objpass==null || objpass.equals("")) {
			subject.setObjpass(oldSubjecy.getObjpass());
		}
		if(objquerypass==null || objquerypass.equals("")) {
			subject.setObjquerypass(oldSubjecy.getObjquerypass());
		}
		//System.out.println((new Date().getTime())-start);
		subjectService.save(subject);
		//System.out.println((new Date().getTime())-start);
		return ResponseUtil.success(subject);
    }
	
	@PostMapping("/del")
    public Response delsubject(@RequestBody  Map<String, String> para, HttpServletRequest request) {
		String json="";
		String id = para.get("id");
		try {
			PkSubject subject = subjectService.get(id);
			subject.setRemoved("1");
			subjectService.save(subject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseUtil.error("删除失败。");
		}
		return ResponseUtil.success(id);
    	
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
	
	@RequestMapping("/home")
	public Response home(@RequestBody Map<String, String> para,HttpServletRequest request) {
		//User user = (User) SecurityUtils.getSubject().getPrincipal();
		PkSubject pkSubject = new PkSubject();
		pkSubject.setRemoved("0");
		pkSubject.setBusiname(para.get("name"));
		ExampleMatcher matcher = ExampleMatcher.matching()
		        .withMatcher("businame" ,ExampleMatcher.GenericPropertyMatchers.contains());//全部模糊查询，即%{address}%
		Example<PkSubject> exam = Example.of(pkSubject,matcher);
		
		List<PkSubject> list = subjectService.findAll(exam);
		long count = subjectService.count();
		Query query = new Query();
		query.setList(list);
		query.addParam("count", count);
		
		return ResponseUtil.success(query);
	}
	
	@RequestMapping("/decode")
	@ResponseBody
	public Response decode(@RequestBody Map<String,String> para,HttpSession httpSession,HttpServletRequest request) {
		/*Account account = (Account) httpSession.getAttribute("user");
		//测试用，如果未登录则模拟登录0号用户
		if(account==null) {
			account=accountService.selectById(0);
			request.getSession().setAttribute("user", account);
		}*/
		String id = para.get("id");
		String userkey = para.get("userkey");
		//String userkey="";
		PkSubject subject = new PkSubject();
		if(id!=null)
			subject=subjectService.get(id);
		request.setAttribute("subject", subject);
		//String json="";
		HashMap<String, String> json = new HashMap<String, String>();
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
		
		json.put("pass", objpass);
		json.put("qpass", objquerypass);
		
		//json="{\"pass\":\""+objpass+ "\",\"qpass\":\""+objquerypass+ "\"}";
		
		//return json;
		return ResponseUtil.success(json);
    }
}
