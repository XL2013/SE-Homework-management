package com.se.service;

import java.util.List;

import com.se.pojo.User;


public interface UserService {
	User checkUser(String user_id,String password);
	List<User> getUserList(int user_role);
	User getUserById(String user_id);
	User addUser(String user_id, String user_name, String user_pwd, int user_role);
	void deleteUser(String user_id);
	void updateUser(String user_id, String user_name,String user_pwd,int user_role);
		
}

