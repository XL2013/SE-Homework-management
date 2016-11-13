package com.se.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.se.dao.TeamConfigDao;
import com.se.service.impl.CourseServiceImpl;
import com.se.service.impl.StudentServiceImpl;
import com.se.service.impl.TeamServiceImpl;

@Controller
@RequestMapping(value="/teacher")
public class TeacherController {


		
		@Resource
		private StudentServiceImpl studentService;
		@Resource
		private CourseServiceImpl courseService;
		@Resource
		private TeamServiceImpl teamConfigService;
		
		@RequestMapping(value="/uploadStudentFile",method=RequestMethod.POST)
		@ResponseBody
		public String uploadStudentFile(@RequestParam("course_id")String course_id,@RequestParam("files")MultipartFile[] files){
			String message=null;
			if(files.length==0) message="请选择提交的文件!!";
			else{
				studentService.readStudentInfo( files,course_id);
				message="提交成功";
			}
			return message;
		}
		
		@RequestMapping(value="/addCourse",method=RequestMethod.POST)
		@ResponseBody
		public String addCourse(String teacher_id,String course_name,String description){
			return courseService.addCourse(course_name, teacher_id, description);		
		}
	
		@PostMapping(value="/addTeamConfig")
		@ResponseBody
		public String addTeamConfig(String course_id,String team_max,String team_min,String year,String class_id){
			teamConfigService.addTeamConfig(course_id, year, class_id, team_max, team_min);
			return "success";
		}
}
