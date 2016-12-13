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
import com.se.service.impl.AssistantServiceImpl;
import com.se.service.impl.CourseServiceImpl;
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
	@GetMapping(value="/correctHomework")
	public ModelAndView showCorrectHomeworkView(String assistant_id){
		return new ModelAndView("/assistant/correctHomework");
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
		return new ModelAndView("/assistant/studentList");
	}
	
	@PostMapping(value="changeTeamLeader")
	@ResponseBody
	public void changeTeamLeader(String team_id,String student_id){
		teamService.setTeamLeader(team_id, student_id);
	}
}
