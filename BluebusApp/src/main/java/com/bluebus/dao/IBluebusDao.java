package com.bluebus.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.bluebus.model.Bluebus;

public interface IBluebusDao {

	void addBus(Bluebus bus);

	void updateBus(int busNumber, double cost);

	Bluebus getByNumber(int busNumber);

	void deleteBus(int busNumber);

	List<Bluebus> findAll(String source, String destination);

	List<Bluebus> findByLessFare(String source, String destination, double cost);

	List<Bluebus> findbyCategory(String source, String destination, String category);

	List<Bluebus> findbyType(String source, String destination, String type);

	List<Bluebus> findbyStartTime(String source, String destination, LocalDateTime startTime);

}
