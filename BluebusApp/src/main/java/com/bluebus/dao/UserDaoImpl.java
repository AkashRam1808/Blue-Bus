package com.bluebus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bluebus.model.User;
import com.bluebus.util.DbConnection;
import com.bluebus.util.Queries;

public class UserDaoImpl implements IUserDao {

	Connection connection = DbConnection.openConnection();
	@Override
	public String addUser(User user) {

		String mobile = String.valueOf(user.getMobile());
		try{
			PreparedStatement statement = connection.prepareStatement(Queries.REGISTERQUERY);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getName());
			statement.setString(3, mobile);
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getCity());
			statement.setString(6,user.getPassword());
			statement.setInt(7, 0);
			statement.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user.getPassword();
	}

	@Override
	public User login(String username, String password) {
		ResultSet resultset = null;
		User user = null;
		try{
			PreparedStatement statement = connection.prepareStatement(Queries.LOGINQUERY);
			statement.setString(1, username);
			statement.setString(2, password);
			resultset = statement.executeQuery();
			while(resultset.next()) {
				user = new User();
				user.setUsername(resultset.getString(1));
				user.setName(resultset.getString(2));
				user.setMobile(resultset.getLong(3));
				user.setEmail(resultset.getString(4));
				user.setCity(resultset.getString(5));
				user.setUserId(resultset.getInt(6));
				user.setPassword(resultset.getString(7));
				
				
				
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int changePassword(String oldpassword, String newpassword) {
		// TODO Auto-generated method stub
		int change = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(Queries.CHANGEQUERY);
			statement.setString(1, newpassword);
			statement.setString(2, oldpassword);
			change = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return change;
	}
	

}
