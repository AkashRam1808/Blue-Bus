package com.bluebus.service;

import com.bluebus.model.User;

public interface IUserService {

	String register(User user);
	User login(String username, String password);
	int changePassword(String oldpassword, String newpassword);
	
}
