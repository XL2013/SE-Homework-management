package com.se.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/student")
public class StudentController {
		
	@GetMapping(value="/team")
	public ModelAndView showTeam(String student_id){
		
		return new ModelAndView("student/team");
	}
	@GetMapping(value="/homework")
	public ModelAndView showHomework(String student_id){
		
		return new ModelAndView("student/homework");
	}
	@GetMapping(value="/grade")
	public ModelAndView showGrade(String student_id){
		
		return new ModelAndView("student/grade");
	}
}
