package com.tempest.controller;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String test(Model model) {
    	model.addAttribute("name", "test1");
        return "test";
    }
    
	
	@PostMapping("/post")
    public String post(@RequestBody Map<String, String> para) {

    	
        return "index";
    }
	
	@GetMapping("/get")
    public String get(@RequestParam(name = "name") String name) {

    	
        return "index";
    }
}
