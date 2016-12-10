package com.se.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.se.dao.UserDao;
import com.se.pojo.User;
import com.se.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	@Override
	public User checkUser(String user_id, String password) {
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
		user.setUser_role(user_role);
		user.setUser_id(user_id);
		user.setUser_name(user_name);
		user.setUser_pwd(user_pwd);
		userDao.addUser(user);
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
		user.setUser_role(user_role);
		user.setUser_id(user_id);
		user.setUser_name(user_name);
		user.setUser_pwd(user_pwd);
		userDao.updateUser(user);
	}
	
}
