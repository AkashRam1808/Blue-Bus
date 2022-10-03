package com.bluebus.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bluebus.model.Bluebus;


public class BusMapper implements IRowMapper{

	public List<Bluebus> rowMap(ResultSet resultSet) throws SQLException{
		List<Bluebus> buses = new ArrayList<>();
		while(resultSet.next()) {
			Bluebus bus = new Bluebus();
			bus.setBusNumber(resultSet.getInt(1));
			bus.setBusName(resultSet.getString(2));
			bus.setStartDate(resultSet.getDate(3).toLocalDate());
			bus.setCategory(resultSet.getString(4));
			bus.setType(resultSet.getString(5));
			bus.setCost(resultSet.getDouble(6));
			bus.setSource(resultSet.getString(7));
			bus.setDestination(resultSet.getString(8));
			bus.setSeatsAvailable(resultSet.getInt(9));
			bus.setStartTime(resultSet.getTimestamp(10).toLocalDateTime());
			buses.add(bus);
			
			
		}
		return buses;
		
	}

	

}
