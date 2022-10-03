package com.bluebus.model;

public class User {

	private String username;
	private String name;
	private long mobile;
	private String email;
	private String city;
	private Integer userId;
	private String password;
	
	
	
	public User() {
		super();
	}
	public User(String username, String name, long mobile, String email, String city, Integer userId,
			String password) {
		super();
		this.username = username;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.city = city;
		this.userId = userId;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", name=" + name + ", mobile=" + mobile + ", email=" + email + ", city="
				+ city + ", userId=" + userId + ", password=" + password + "]";
	}
	
	
}
