package com.bluebus.service;

import java.time.LocalDateTime;
import java.util.List;

import com.bluebus.exception.BusNotFoundException;

import com.bluebus.model.Bluebus;

public interface IBluebusService {

	void addBus(Bluebus bus);

	void updateBus(int busNumber, double cost);

	Bluebus getByNumber(int busNumber) throws BusNotFoundException;

	void deleteBus(int busNumber);

	List<Bluebus> getAll(String source, String destination);

	List<Bluebus> getByLessFare(String source, String destination, double cost) throws BusNotFoundException;

	List<Bluebus> getbyCategory(String source, String destination, String category) throws BusNotFoundException;

	List<Bluebus> getbyType(String source, String destination, String type) throws BusNotFoundException;

	List<Bluebus> getbyStartTime(String source, String destination, LocalDateTime startTime)
			throws BusNotFoundException;

}
