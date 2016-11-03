package com.se.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.se.service.LoginService;

@Controller
public class LoginController {
			
	@Resource
	private LoginService loginService;
	@RequestMapping(value="login")
	public ModelAndView login(@RequestParam String username,@RequestParam String password){
		String message;
		System.out.println("调用login方法");
		if(loginService.checkUser("chushan", "123"))
			message="用户验证成功";
		else
			message="用户验证失败";
		ModelAndView mv=new ModelAndView("/showResult","message",message);
		return mv;
	}
}
