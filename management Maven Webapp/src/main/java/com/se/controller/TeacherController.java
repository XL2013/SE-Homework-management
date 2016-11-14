package com.se.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.se.dao.TeamConfigDao;
import com.se.pojo.Course;
import com.se.pojo.Student;
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
		
		
		@GetMapping(value="/courseList")
		@ResponseBody
		public Map<String,Object> ShowstudentList(String teacher_id){
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("courseList",courseService.getTeacherCourse(teacher_id));
			return map;
		}
		
		@GetMapping(value="/studentList")
		public ModelAndView studentList(String course_id){
			List<Student> studentList=studentService.getCourseStudent(course_id);
			return new ModelAndView("/teacher/studentList","studentList",studentList);
		}
		
		
		@PostMapping(value="/courseInfo")
		public ModelAndView courseInfo(String course_id){
			return new ModelAndView("/teacher/courseInfo","course",courseService.getCourse(course_id));
		}
}
