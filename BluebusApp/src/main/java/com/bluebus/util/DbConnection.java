package com.bluebus.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	static Connection connection;

	public static Connection openConnection() {

		String url = "jdbc:mysql://localhost:3306/bluebusdb";
		String username = "root";
		String password = "root";
		try {
			connection = DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void closeConnection() {
		if (connection == null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}