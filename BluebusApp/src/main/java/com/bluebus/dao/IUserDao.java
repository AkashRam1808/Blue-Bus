package com.bluebus.dao;

import com.bluebus.model.User;

public interface IUserDao {
	String addUser(User user);
	User login(String username, String password);
	int changePassword(String oldpassword, String newpassword);

}
