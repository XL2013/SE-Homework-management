package com.se.service;

import com.se.pojo.User;


public interface UserService {
	User checkUser(String user_id,String password);
}
