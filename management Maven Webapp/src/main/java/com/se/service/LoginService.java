package com.se.service;

import org.springframework.stereotype.Service;


public interface LoginService {
	boolean checkUser(String username,String password);
}
