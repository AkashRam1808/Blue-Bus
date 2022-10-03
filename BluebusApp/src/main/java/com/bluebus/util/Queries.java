package com.bluebus.util;

public class Queries {

	public static final String ADDQUERY = "insert into bluebus(BusNumber,BusName,StartDate,category,type,cost,Source,Destination,SeatAvailable,StartTime) values(?,?,?,?,?,?,?,?,?,?)";
	public static final String UPDATEQUERY = "update bluebus set cost = ? where BusNumber = ?";
	public static final String QUERYBYID = "select * from bluebus where BusNumber = ?";
	public static final String DELETEQUERY = "delete from bluebus where BusNumber = ?";

	public static final String QUERYBYSOURCE = "select * from bluebus where Source = ? and Destination = ?";
	public static final String QUERYBYCOST = "select * from bluebus where Source = ? and Destination = ? and cost <= ?";
	public static final String QUERYBYCATEGORY = "select * from bluebus where Source = ? and Destination = ? and category = ?";
	public static final String QUERYBYTYPE = "select * from bluebus where Source = ? and Destination = ? and type = ?";
	public static final String QUERYBYTIME = "select * from bluebus where Source = ? and Destination = ? and StartTime >= ?";
	
	public static final String LOGINQUERY = "select * from Users where UserName = ? and pass = ?";
	public static final String REGISTERQUERY = "insert into Users(UserName,Name,Mobile,Email,City,pass,LoginType) values(?,?,?,?,?,?,?)";
	public static final String CHANGEQUERY = "update Users set pass = ? where pass = ?";
}
