package com.se.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.se.pojo.Course;
import com.se.pojo.Homework;
import com.se.pojo.HomeworkFile;
import com.se.pojo.Student;
import com.se.pojo.Team;
import com.se.pojo.TeamConfig;
import com.se.pojo.TeamHomework;
import com.se.service.HomeworkService;
import com.se.service.impl.CourseServiceImpl;
import com.se.service.impl.HomeworkServiceImpl;
import com.se.service.impl.RollCallServiceImpl;
import com.se.service.impl.StudentServiceImpl;
import com.se.service.impl.TeamServiceImpl;
import com.se.util.FileHelper;

import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping(value="/student")
public class StudentController {
		
	@Resource
	private TeamServiceImpl teamService;
	
	@Resource
	private CourseServiceImpl courseService;
	
	@Resource
	private StudentServiceImpl student_service;
	
	@Resource
	private HomeworkServiceImpl homeworkService;
	@Resource
	private RollCallServiceImpl rollCallService;
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
			//没有小组的课程不予显示
			if(teamService.getStudentTeam(course_id, student_id)==null) continue;
			data.put("course_name", courseService.getCourse(course_id).getCourse_name());
			data.put("team_id", teamService.getStudentTeam(course_id, student_id).getTeam_id());
			teamList.add(data);
		}
		return new ModelAndView("student/homework","teamList",teamList);
	}
	@GetMapping(value="/grade")
	public ModelAndView showGrade(String student_id){
		List<Course> courses=new ArrayList<Course>();
		for(String course_id:courseService.getStudentCourses(student_id)){
			courses.add(courseService.getCourse(course_id));
		}
		return new ModelAndView("student/grade","courses",courses);
	}
	
	
	/**
	 * 如果小组已存在，则返回小组的信息，如果不存在，先创建小组再返回小组信息
	 * @param student_id
	 * @param course_id
	 * @return
	 */	
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
	
	@GetMapping(value="getTeamHomeworks")
	@ResponseBody
	public Map<String,Object> getTeamHomeworks(String team_id){
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
	
	/**
	 * 这里返回小组作业的具体信息，以便前台显示，所以需要作业的基本信息和小组作业的信息
	 */
	@GetMapping(value="getTeamHomework")
	@ResponseBody
	public Map<String,Object> getTeamHomework(String team_id,String homework_id){
		Map<String,Object> data=new HashMap<String, Object>();
		TeamHomework team_homework=homeworkService.getTeamHomework(homework_id, team_id);
		List<HomeworkFile> files=homeworkService.getTeamHomewokFiles(homework_id, team_id);
		Homework homework=homeworkService.getHomework(homework_id);
		data.put("homework", homework);
		data.put("team_homework", team_homework);
		data.put("files", files);
		return data;
	}
	
	@PostMapping(value="setTeamHomeworkComment")
	@ResponseBody
	public void setTeamHomeworkComment(String team_id,String homework_id,String comment){
		homeworkService.setComment(comment, homework_id, team_id);		
	}
	
	/**
	 * 接受学生上传的作业文件并返回对应得小组和作业，以便前台更新作业列表
	 */
	@PostMapping(value="uploadHomeWorkFile")
	@ResponseBody
	public Map<String,Object> uploadHomeworkFiles(@RequestParam("homework_id")String homework_id,@RequestParam("team_id")String team_id,@RequestParam("files")MultipartFile[] files){
		Map<String,Object> data=new HashMap<String, Object>();
		homeworkService.saveHomeworkFile(team_id, homework_id, files);
		data.put("team_id", team_id);
		data.put("homework_id",homework_id);
		
		return data;

	}
	
	@PostMapping(value="checkHomeworkFile")
	@ResponseBody
	public Map<String,Object> checkHomeworkFile(String file_names,String homework_id,String team_id){
		Map<String ,Object> data=new HashMap<String, Object>();
		String []files=StringUtils.split(file_names, ',');
		boolean isExist=false;
		for(String file_name : files){
			file_name=file_name.trim();
			if(homeworkService.checkHomeworkFile(file_name, homework_id, team_id))//如果存在该文件
				isExist=true;
		}
		data.put("isExist", isExist);
		return data;
	}
	
	/**
	 * 当作业提交界面点击确定时，将作业的状态改为已提交
	 * @param team_id
	 * @param homework_id
	 */
	@PostMapping(value="updateTeamHomework_status")
	@ResponseBody
	public void updateTeamHomework_status(String team_id,String homework_id,String student_id){
		homeworkService.submitTeamHomework(team_id, homework_id,student_id);
	}
	
	//这个函数可以作为fileController的函数
	@GetMapping(value="showTeamHomeworkFile")
	@ResponseBody
	public Map<String, Object> showTeamHomeworkFile(String team_id,String homework_id){
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("files", homeworkService.getHomeworkFiles(team_id, homework_id));
		
		return data;
	}
	
	
	
	
	@GetMapping(value="getStudentHomeworkGrade")
	@ResponseBody
	public Map<String,Object> getStudentHomeworkGrade(String course_id,String student_id){
		Map<String,Object> data=new HashMap<String, Object>();
		Team team=teamService.getStudentTeam(course_id, student_id);
		if(team==null){
			data.put("isTeamExist",false);
			return data;
		}
		//返回需要的作业信息：作业id，作业名称，作业成绩
		List<Map<String,Object>> gradeInfos=new ArrayList<Map<String,Object>>();
		for(TeamHomework teamHomework : homeworkService.getTeamHomeworks(team.getTeam_id())){
			Map<String,Object> gradeInfo=new HashMap<String, Object>();
			String homework_id=teamHomework.getHomework_id();
			String homework_name=homeworkService.getHomework(homework_id).getHomework_name();
			gradeInfo.put("homework_id", homework_id);
			gradeInfo.put("homework_name", homework_name);
			gradeInfo.put("grade", teamHomework.getGrade());
			gradeInfos.add(gradeInfo);
		}
		int absenceTimes=rollCallService.getAbsenceTimes(student_id, course_id);
		int total=rollCallService.getStudentRollCallTotals(course_id, student_id);
		//返回学生课程总成绩
		int grade=student_service.getStudentCourseGrade(course_id, student_id);
		data.put("grade", grade);
		data.put("absenceTimes", absenceTimes);
		data.put("rollCallTimes",total);
		data.put("gradeInfos", gradeInfos);
		data.put("isTeamExist", true);
		return data;
	}

}
