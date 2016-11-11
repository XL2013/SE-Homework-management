package com.se.service.impl;

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
		 User user=userDao.getUser(user_id, password);
		 return user;
	}

}
