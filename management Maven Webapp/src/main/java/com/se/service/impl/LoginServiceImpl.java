package com.se.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.se.dao.UserDao;
import com.se.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService{

	@Resource
	private UserDao userDao;
	@Override
	public boolean checkUser(String username, String password) {
		// TODO Auto-generated method stub
		if(userDao.getUser(username, password)!=null)
			return true;
		else 
			return false;
	}

}
