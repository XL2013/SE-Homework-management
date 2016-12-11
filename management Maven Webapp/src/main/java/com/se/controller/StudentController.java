package com.se.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.se.pojo.Student;
import com.se.pojo.Team;
import com.se.pojo.TeamConfig;
import com.se.service.impl.CourseServiceImpl;
import com.se.service.impl.StudentServiceImpl;
import com.se.service.impl.TeamServiceImpl;

@Controller
@RequestMapping(value="/student")
public class StudentController {
		
	@Resource
	private TeamServiceImpl teamService;
	
	@Resource
	private CourseServiceImpl courseService;
	
	@Resource
	private StudentServiceImpl student_service;
	/**
	 * 向team.jsp传递json数据：
	 * data:{
	 * 		courseSetting:{
	 * 			//学生在当前课程是否有小组
	 * 			"java":1,
	 * 			"c++" :0,
	 * 	
	 * 	},
	 * 		teamInfos:{
	 * 			{
	 * 				小组信息json数据
	 * 		},
	 * 			{
	 * 		},
	 * }
	 */
	@GetMapping(value="/team")
	public ModelAndView showTeam(String student_id){
		Map<String,Object> data=new HashMap<String, Object>();
		List<String> courses=courseService.getStudentCourses(student_id);
		List<Map<String,Object>> cs=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> teamInfos=new ArrayList<Map<String,Object>>();

		for(String course: courses){
			//如果已经在小组里面
			Team curTeam=teamService.getStudentTeam(course, student_id);
			if(curTeam!=null){
				Map<String,Object> c=new HashMap<String, Object>();
				c.put("course", courseService.getCourse(course));
				c.put("hasTeam",1);
				cs.add(c);
				
				Map<String,Object> teamInfo=new HashMap<String, Object>();
				teamInfo.put("setting",curTeam);
				teamInfo.put("members", teamService.getTeamStudents(curTeam.getTeam_id()));
				teamInfo.put("course_name",courseService.getCourse(course).getCourse_name());
				teamInfos.add(teamInfo);
			}
			else{
				Map<String,Object> c=new HashMap<String, Object>();
				c.put("course", courseService.getCourse(course));
				c.put("hasTeam",0);
				cs.add(c);
			}
		}
		data.put("courseSetting",cs);
		data.put("teamInfos", teamInfos);
		return new ModelAndView("student/team","data",data);
	}
	@GetMapping(value="/homework")
	public ModelAndView showHomework(String student_id){
		List<Map<String,Object>> teamList=new ArrayList<Map<String,Object>>();
		for(String course_id : courseService.getStudentCourses(student_id)){
			Map<String,Object> data=new HashMap<String, Object>();
			data.put("course_name", courseService.getCourse(course_id).getCourse_name());
			data.put("team_id", teamService.getStudentTeam(course_id, student_id).getTeam_id());
			teamList.add(data);
		}
		return new ModelAndView("student/homework","teamList",teamList);
	}
	@GetMapping(value="/grade")
	public ModelAndView showGrade(String student_id){
		
		return new ModelAndView("student/grade");
	}
	
	
	
	//如果小组已存在，则返回小组的信息，如果不存在，先创建小组再返回小组信息
	@GetMapping(value="/initTeamInfo")
	@ResponseBody
	public Map<String,Object> initTeamInfo(String student_id,String course_id){
		Team team=null;
		if(teamService.getStudentTeam(course_id, student_id)==null){
			team=teamService.addTeam(course_id, student_id);
			
		}
		else
			team=teamService.getStudentTeam(course_id, student_id);
		TeamConfig config=teamService.getTeamConfig(course_id);
		String course_name=courseService.getCourse(course_id).getCourse_name();
		List<Student> members=teamService.getTeamStudents(team.getTeam_id());
		
		Map<String,Object> data=new HashMap<String, Object>();
		data.put("team", team);
		data.put("course_name",course_name);
		data.put("members", members);
		data.put("teamConfig", config);
		
		return data;
		
	}
	
	
	@GetMapping(value="showTeamInfo")
	@ResponseBody
	public Map<String,Object> showTeamInfo(String team_id){
		Map<String,Object> map=new HashMap<String, Object>();
		Team team=teamService.getTeamById(team_id);
		String course_name=courseService.getCourse(team.getCourse_id()).getCourse_name();
	
		map.put("team", team);
		map.put("course_name",course_name);
		map.put("members", teamService.getTeamStudents(team_id));
		
		return map;
	}
	
	@PostMapping(value="addTeamMember")
	@ResponseBody
	public Map<String,Object> addTeamMember(String student_id,String team_id){
		Map<String,Object> data=new HashMap<String, Object>();
		
		Student student=student_service.getStudentById(student_id);
		teamService.addTeamMember(team_id, student_id);
		
		data.put("student", student);
		return data;
	}
	
	@PostMapping(value="searchResult")
	@ResponseBody
	public Map<String,Object> searchResult(String info,String course_id){
		System.out.println(info);
		Map<String,Object> data=new HashMap<String, Object>();
		data.put("members",student_service.searchStudent(info, course_id));
		return data;
	}
	
	@PostMapping(value="emailChange")
	@ResponseBody
	public void emailChange(String team_id,String email){
		teamService.setTeamEmail(team_id, email);
	}
}
