package com.bluebus.service;

import com.bluebus.dao.IUserDao;
import com.bluebus.dao.UserDaoImpl;
import com.bluebus.model.User;

public class UserServiceImpl implements IUserService {

	IUserDao userDao = new UserDaoImpl();
	@Override
	public String register(User user) {
		// TODO Auto-generated method stub
		String password = userDao.addUser(user);
		return password;
	}

	

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		User user = userDao.login(username, password);
		return user;
	}

	@Override
	public int changePassword(String oldpassword, String newpassword) {
		// TODO Auto-generated method stub
		int check = userDao.changePassword(oldpassword, newpassword);
		return check;
	}

}
