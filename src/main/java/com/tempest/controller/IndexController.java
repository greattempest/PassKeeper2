package com.tempest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
    @RequestMapping("/index2")
    public String login(Model model) {
    	model.addAttribute("name", "test1");
    	
        return "login";
    }
    
    @RequestMapping("/ui")
    public String ui(Model model) {
    	model.addAttribute("name", "test1");
    	
        return "index";
    }
}
