package com.se.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;import javax.swing.plaf.basic.BasicBorders.RolloverButtonBorder;

import org.apache.xmlbeans.impl.jam.mutable.MPackage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.se.dao.TeamConfigDao;
import com.se.dao.TeamHomeworkDao;
import com.se.pojo.Assistant;
import com.se.pojo.Course;
import com.se.pojo.Homework;
import com.se.pojo.Student;
import com.se.pojo.StudentRollCall;
import com.se.pojo.Team;
import com.se.service.HomeworkService;
import com.se.service.impl.AssistantServiceImpl;
import com.se.service.impl.CourseServiceImpl;
import com.se.service.impl.HomeworkServiceImpl;
import com.se.service.impl.StudentServiceImpl;
import com.se.service.impl.TeamServiceImpl;

import oracle.sql.DATE;

@Controller
@RequestMapping(value="/teacher")
public class TeacherController {


		
		@Resource
		private StudentServiceImpl studentService;
		@Resource
		private CourseServiceImpl courseService;
		@Resource
		private TeamServiceImpl teamService;
		@Resource
		private AssistantServiceImpl assistantService;
		@Resource
		private HomeworkServiceImpl homeworkService;
		
		@RequestMapping(value="/uploadStudentFile",method=RequestMethod.POST)
		@ResponseBody
		public String uploadStudentFile(@RequestParam("course_id")String course_id,@RequestParam("files")MultipartFile[] files){
			String message=null;
			if(files.length==0) message="请选择提交的文件!!";
			else{
				studentService.readStudentInfo(files,course_id);
				message="提交成功";
			}
			return message;
		}
		
		@PostMapping(value="modifyStudentRollCallStat")
		@ResponseBody
		public String modifyStudentRollCallStat(@RequestBody List<StudentRollCall> studentRollCalls){
			studentService.setStudentRollCallListByStudentList(studentRollCalls);
			return "success";
		}
		
		@RequestMapping(value="/addCourse",method=RequestMethod.POST)
		@ResponseBody
		public String addCourse(String teacher_id,String course_name,String description){
			return courseService.addCourse(course_name, teacher_id, description);		
		}
	
		@PostMapping(value="/addTeamConfig")
		@ResponseBody
		public String addTeamConfig(String course_id,String team_max,String team_min,String year,String class_id){
			teamService.addTeamConfig(course_id, year, class_id, team_max, team_min);
			return "success";
		}
		
		
		@GetMapping(value="/courseList")
		@ResponseBody
		public Map<String,Object> ShowCourseList(String teacher_id){
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("courseList",courseService.getTeacherCourse(teacher_id));
			return map;
		}
		
		@GetMapping(value="/studentList")
		public ModelAndView studentList(String course_id){
			return new ModelAndView("/teacher/studentList","rollcall_max",studentService.CourseMaximumRollCall(course_id));
		}
		
		@GetMapping(value="/studentRollCall")
		public ModelAndView studentRollCall(String course_id,int roll_order){				
			List<Student> studentList=studentService.getCourseStudent(course_id);
			List<Integer> rollCalls = studentService.getStudentRollCallListByStudentList(course_id, roll_order, studentList);
			List<Map<String, Object>> list=new ArrayList<>();
			assert(rollCalls.size()==studentList.size());
			for(int i=0;i<rollCalls.size();i++){
				Map<String, Object> map2=new HashMap<>();
				map2.put("student", studentList.get(i));
				map2.put("rollCall", rollCalls.get(i));
				list.add(map2);
			}
			Map<String, Object> map = new HashMap<>();
			map.put("data", list);
			map.put("roll_order", roll_order);
			return new ModelAndView("/teacher/studentRollCall","data",map);
		}
		
		@GetMapping(value="/studentResult")
		@ResponseBody
		public Map<String, Object> studentResult(String course_id){
			System.out.println(course_id);
			List<Student> studentList=studentService.getCourseStudent(course_id);
			List<List<Map<String, Object>>> homework_result = studentService.getStudentResultListByCourse(course_id, studentList);
			List<List<Map<String, Integer>>> roll_calls = studentService.getStudentRollCallListByCourse(course_id,studentList);
			List<Map<String, Object>> list = new ArrayList<>();
			
			for(int i=0;i<studentList.size();i++){
				Map<String, Object> tMap = new HashMap<>();
				tMap.put("student", studentList.get(i));
				tMap.put("rollCall", roll_calls.get(i));
				tMap.put("homeworkGrade", homework_result.get(i));
				list.add(tMap);
			}
			Map<String, Object> map=new HashMap<>();
			map.put("data", list);
			return map;
		}
		
		@GetMapping(value="/studentResult1")
		public ModelAndView studentResult1(String course_id){
			List<Student> studentList=studentService.getCourseStudent(course_id);
			List<Integer>total_grades = studentService.getStudentCourseTotalGradeByStudentList(course_id,studentList);
			List<Integer>person_grades = studentService.getStudentCoursePersonGradeBystudentList(course_id, studentList);
			List<Map<String, Object>> list = new ArrayList<>();
			for(int i=0;i<studentList.size();i++){
				Map<String, Object> tMap = new HashMap<>();
				tMap.put("student", studentList.get(i));
				tMap.put("total_grade", total_grades.get(i));
				tMap.put("person_grade", person_grades.get(i));//TODO
				list.add(tMap);
			}
			
			return new ModelAndView("/teacher/studentResult","data",list);
		}
		
		@GetMapping(value="/homeWorkArrange")
		public ModelAndView homeWorkArrange(String course_id){
			return new ModelAndView("/teacher/homeWorkArrange");
		}
		
		@GetMapping(value="/allSearch")
		@ResponseBody
		public Map<String,Object> allSearch(){
			Map<String,Object> map=new HashMap<String, Object>();
			map=homeworkService.getTeamHomeworkViewData();
			return map;
		}
		
		@PostMapping(value="/conditionSearch")
		@ResponseBody
		public Map<String,Object> conditionSearch(String homework_name, String submit_time, String team_id){
			System.out.println(homework_name);
			System.out.println(submit_time);
			System.out.println(team_id);
			return homeworkService.getTeamHomeworkViewData(homework_name, submit_time, team_id);
		}
		
		@GetMapping(value="/homeWorkReview")
		public ModelAndView homeWorkReview(String course_id){
			return new ModelAndView("/teacher/homeWorkReview");
		}
		
		@PostMapping(value="/homeWorksUpdateTab")
		@ResponseBody
		public Map<String,Object> homeWorksUpdateTab(@RequestBody List<Homework> homeworks){
			homeworkService.addHomeworkInfos(homeworks);
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("message","success"); 
			return map;
		}
		
		@GetMapping(value="/modifyHomeworkRatio")
		@ResponseBody
		public Map<String,Object> modifyHomeworkRatio(String homework_id, double ratio){
			System.out.println("ratio:"+ratio);
			homeworkService.modifyHomeworkRatio(homework_id, ratio);
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("message","success"); 
			return map;
		}
		
		@GetMapping(value="/getHomeworksInfoByCourseID")
		@ResponseBody
		public Map<String,Object> getHomeworksInfoByCourseID(String course_id){	
			List<Homework> homework_list= homeworkService.getHomeworksInfoByCourseID(course_id);
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("homworkList",homework_list); 
			return map;
		}
		
		@GetMapping(value="teamSetting")
		public ModelAndView teamSetting(String course_id){
			Map<String,Object> data=new HashMap<String, Object>();
			List<Team> teams=teamService.getCourseTeam(course_id);
			
			List<Map<String,Object>> teamInfos=new ArrayList<Map<String,Object>>();
			for(Team team : teams){
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("team", team);
				String name="";
				if(assistantService.getTeamAssistant(team.getTeam_id())==null)
					name="请设置一名助教";
				else
					name=assistantService.getTeamAssistant(team.getTeam_id()).getAssistant_name();
				map.put("name",name);
				teamInfos.add(map);
				
			}
			String teacher_id=courseService.getCourse(course_id).getTeacher_id();
			List<Assistant> assistants=assistantService.getTeacherAssistants(teacher_id);
			
			data.put("teamInfos", teamInfos);
			data.put("assistants", assistants);
			return new ModelAndView("/teacher/teamSetting","data",data);
		}
	
		
		@GetMapping(value="/courseInfo")
		public ModelAndView courseInfo(String course_id){
			return new ModelAndView("/teacher/courseInfo","course",courseService.getCourse(course_id));
		}
		
		@PostMapping(value="setTeamAssistant")
		@ResponseBody
		public void setTeamAssistant(String team_id,String assistant_id){
			assistantService.setTeamAssistant(team_id, assistant_id);
		}
}
