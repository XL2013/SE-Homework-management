package com.se.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.se.dao.AssistantDao;
import com.se.dao.StudentDao;
import com.se.dao.TeacherDao;
import com.se.dao.UserDao;
import com.se.pojo.Assistant;
import com.se.pojo.Student;
import com.se.pojo.Teacher;
import com.se.pojo.User;
import com.se.service.UserService;
import com.se.util.MD5Helper;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	@Resource
	private TeacherDao teacherDao;
	@Resource
	private StudentDao studentDao;
	@Resource
	private AssistantDao assistantDao;
	@Override
	public User checkUser(String user_id, String password) {
		 password=MD5Helper.encrypt(password);
		 User user=userDao.checkUser(user_id, password);
		 return user;
	}
	@Override
	public List<User> getUserList(int user_role) {
		// TODO Auto-generated method stub
		return userDao.getUserList(user_role);
	}
	@Override
	public User getUserById(String user_id) {
		// TODO Auto-generated method stub
		return userDao.getUser(user_id);
	}
	@Override
	public User addUser(String user_id, String user_name, String user_pwd, int user_role) {
		// TODO Auto-generated method stub
		User user = new User();
		user_pwd=MD5Helper.encrypt(user_pwd);
		user.setUser_role(user_role);
		user.setUser_id(user_id);
		user.setUser_name(user_name);
		user.setUser_pwd(user_pwd);
		if(userDao.getUser(user_id)!=null) return null;
		userDao.addUser(user);
	
		if(user_role==1){
			Teacher teacher=new Teacher();
			teacher.setTeacher_id(user_id);
			teacher.setTeacher_name(user_name);
			teacherDao.addTeacher(teacher);
		}
		return user;
	}
	@Override
	public void deleteUser(String user_id) {
		// TODO Auto-generated method stub
		userDao.deleteUser(user_id);
	}
	@Override
	public void updateUser(String user_id, String user_name, String user_pwd, int user_role) {
		// TODO Auto-generated method stub
		User user = new User();
		user_pwd=MD5Helper.encrypt(user_pwd);
		user.setUser_role(user_role);
		user.setUser_id(user_id);
		user.setUser_name(user_name);
		user.setUser_pwd(user_pwd);
		userDao.updateUser(user);
	}
	public void addAssistant(String teacher_id,String assistant_id,String name){
		Assistant assistant=new Assistant();
		assistant.setAssistant_id(assistant_id);
		assistant.setAssistant_name(name);
		assistant.setTeacher_id(teacher_id);
		assistantDao.addAssistant(assistant);
	}
	
}
