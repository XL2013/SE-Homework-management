package com.se.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.se.service.impl.StudentServiceImpl;

@Controller
@RequestMapping(value="/teacher")
public class TeacherController {

		@Resource
		private HttpServletRequest request;
		
		@Resource
		private StudentServiceImpl studentService;
		
		@RequestMapping(value="/uploadStudentFile",method=RequestMethod.POST)
		@ResponseBody
		public String uploadStudentFile(@RequestParam("course_id")String course_id,@RequestParam("files")MultipartFile[] files){
			Map<String,Object> map=new HashMap<String, Object>();
			String message=null;
			String path= request.getSession().getServletContext().getRealPath("/upload/studentList/");
			if(files.length==0) message="请选择提交的文件!!";
			else{
				studentService.readStudentInfo(path, files);
				message="success";
			}
			return message;
		}
		
	
}
