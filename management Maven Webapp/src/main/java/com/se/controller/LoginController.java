package com.se.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
			
	@RequestMapping(value="login")
	public ModelAndView login(){
		ModelAndView mv=new ModelAndView("welcome");
		return mv;
	}
}
