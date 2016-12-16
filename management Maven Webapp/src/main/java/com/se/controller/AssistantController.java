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
import com.se.pojo.TeamHomework;
import com.se.service.impl.AssistantServiceImpl;
import com.se.service.impl.CourseServiceImpl;
import com.se.service.impl.HomeworkServiceImpl;
import com.se.service.impl.TeamServiceImpl;

@Controller
@RequestMapping(value="/assistant")
public class AssistantController {
	
	@Resource
	private TeamServiceImpl teamService;
	@Resource
	private AssistantServiceImpl assistantService;
	@Resource
	private CourseServiceImpl courseService;
	
	@Resource
	private HomeworkServiceImpl homeworkService;
	@GetMapping(value="/correctHomework")
	public ModelAndView showCorrectHomeworkView(String assistant_id){
		List<Map<String, Object>> teamList=new ArrayList<Map<String,Object>>();
		for(String team_id :assistantService.getAssitantTeams(assistant_id)){
			Map<String, Object> teamInfo=new HashMap<String, Object>();
			Team team=teamService.getTeamById(team_id);
			String course_name=courseService.getCourse(team.getCourse_id()).getCourse_name();
			teamInfo.put("team_id", team.getTeam_id());
			teamInfo.put("course_name", course_name);
			teamList.add(teamInfo);
		}	
		return new ModelAndView("/assistant/correctHomework","teamList",teamList);
	}
	
	@GetMapping(value="/team")
	public ModelAndView showTeamView(String assistant_id){
		List<Map<String, Object>> teamInfos=new ArrayList<Map<String,Object>>();
		for(String team_id :assistantService.getAssitantTeams(assistant_id)){
			Map<String, Object> teamInfo=new HashMap<String, Object>();
			Team team=teamService.getTeamById(team_id);
			
			String course_name=courseService.getCourse(team.getCourse_id()).getCourse_name();
			List<Student> members=teamService.getTeamStudents(team_id);
			teamInfo.put("team", team);
			teamInfo.put("course_name", course_name);
			teamInfo.put("members", members);
			teamInfos.add(teamInfo);
		}		
		return new ModelAndView("/assistant/assistTeam","teamInfos",teamInfos);
	}
	
	@GetMapping(value="/studentList")
	public ModelAndView showStudentListView(String assistant_id){
		return new ModelAndView("/assistant/studentList","courses",assistantService.getAssistantCourses(assistant_id));
	}
	
	@PostMapping(value="changeTeamLeader")
	@ResponseBody
	public void changeTeamLeader(String team_id,String student_id){
		teamService.setTeamLeader(team_id, student_id);
	}
	
	@GetMapping(value="getTeamHomeworks")
	@ResponseBody
	public Map<String, Object> getTeamHomeworks(String team_id){
		Map<String,Object> data=new HashMap<String, Object>();
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		List<TeamHomework> teamHomeworks=homeworkService.getTeamHomeworks(team_id);
		for(TeamHomework homework: teamHomeworks){
			Map<String,Object> map=new HashMap<String, Object>();
			String homework_id=homework.getHomework_id();
			String status="";
			if(homework.getStatus()==0)
				status="未提交";
			else  if(homework.getStatus()==1)
				status="已提交";
			else
				status="已批改";
			String homework_name="";
			if( homeworkService.getHomework(homework_id)!=null)
				homework_name=homeworkService.getHomework(homework_id).getHomework_name();						
			map.put("homework_id", homework_id);
			map.put("status", status);
			map.put("homework_name", homework_name);
			map.put("grade", homework.getGrade());
			list.add(map);
		}
		data.put("homeworks", list);		
		return data;
	}
	@PostMapping(value="evaluateTeamHomework")
	@ResponseBody
	public void evaluateTeamHomework(String homework_id,String team_id,String comment,int grade){
		homeworkService.evaluateTeamHomework(team_id, homework_id, comment, grade);
	}
}
