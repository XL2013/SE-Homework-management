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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.se.pojo.User;
import com.se.service.impl.UserServiceImpl;

@Controller
@RequestMapping(value="/manager")
public class ManagerController {
	
	@Resource
	private UserServiceImpl userService;
	
	@GetMapping(value="/userList")
	@ResponseBody
	public ModelAndView userList(int user_role){
		List<User> userList=userService.getUserList(user_role);
		Map<String,Object> data=new HashMap<String, Object>();
		data.put("userList", userList);
		data.put("role", user_role);
		if(user_role==2){
			List<User> teacherList=userService.getUserList(1);
			data.put("teachers", teacherList);
		}
		return new ModelAndView("manager/userList","data",data);
	}
	
	@PostMapping(value="/deleteUser")
	@ResponseBody
	public Map<String, Object> deleteUser(String user_id){
		userService.deleteUser(user_id);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("message","success"); 
		return map;
	}
	
	@PostMapping(value="/addUser")
	@ResponseBody
	public Map<String, Object> addUser(String user_id, String user_name, String user_pwd, int user_role,String teacher_id){
		User user=userService.addUser(user_id, user_name, user_pwd, user_role);
		if(user!=null&&user_role==2){
			userService.addAssistant(teacher_id, user_id, user_name);
		}
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("message","success"); 
		System.out.print(teacher_id);
		return map;
	}
	
	@GetMapping(value="/showUserList")
	@ResponseBody
	public Map<String,Object> showUserList(int user_role){
		List<User> userList=userService.getUserList(user_role);
		Map<String,Object> data=new HashMap<String, Object>();
		data.put("members", userList);
		return data;
	}
	
	@PostMapping(value="/modifyUser")
	@ResponseBody
	public Map<String, Object> modifyUser(String user_id, String user_name, String user_pwd,int user_role){
		Map<String,Object> map=new HashMap<String, Object>();
		userService.updateUser(user_id, user_name, user_pwd, user_role);
		map.put("message","success"); 
		return map;
	}
}
