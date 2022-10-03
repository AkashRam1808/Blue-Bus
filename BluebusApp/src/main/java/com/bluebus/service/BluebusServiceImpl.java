package com.bluebus.service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

import com.bluebus.dao.BluebusDaoImpl;
import com.bluebus.dao.IBluebusDao;
import com.bluebus.exception.BusNotFoundException;

import com.bluebus.model.Bluebus;

public class BluebusServiceImpl implements IBluebusService {

	IBluebusDao bluebusdao = new BluebusDaoImpl();

	@Override
	public void addBus(Bluebus bus) {
		bluebusdao.addBus(bus);
	}

	@Override
	public void updateBus(int busNumber, double cost) {

		bluebusdao.updateBus(busNumber, cost);

	}

	@Override
	public Bluebus getByNumber(int busNumber) throws BusNotFoundException {

		Bluebus bus = bluebusdao.getByNumber(busNumber);
		if (bus != null) {
			return bus;
		} else {
			throw new BusNotFoundException("Bus Number not found");
		}

	}

	@Override
	public void deleteBus(int busNumber) {

		bluebusdao.deleteBus(busNumber);

	}

	@Override
	public List<Bluebus> getAll(String source, String destination) {

		List<Bluebus> buses = bluebusdao.findAll(source, destination).stream()
				.sorted((bus1, bus2) -> bus1.getBusName().compareTo(bus2.getBusName())).collect(Collectors.toList());
		return buses;
	}

	@Override
	public List<Bluebus> getByLessFare(String source, String destination, double cost) throws BusNotFoundException {

		List<Bluebus> buses = bluebusdao.findByLessFare(source, destination, cost).stream()
				.sorted((bus1, bus2) -> bus1.getBusName().compareTo(bus2.getBusName())).collect(Collectors.toList());

		if (buses.isEmpty()) {

			throw new BusNotFoundException("No bus found!");
		} else {
			return buses;
		}
	}

	@Override
	public List<Bluebus> getbyCategory(String source, String destination, String category) throws BusNotFoundException {

		List<Bluebus> buses = bluebusdao.findbyCategory(source, destination, category).stream()
				.sorted((bus1, bus2) -> bus1.getBusName().compareTo(bus2.getBusName())).collect(Collectors.toList());
		if (buses.isEmpty()) {
			throw new BusNotFoundException("No bus found!");
		} else {
			return buses;
		}
	}

	@Override
	public List<Bluebus> getbyType(String source, String destination, String type) throws BusNotFoundException {

		List<Bluebus> buses = null;
		buses = bluebusdao.findbyType(source, destination, type).stream()
				.sorted((bus1, bus2) -> bus1.getBusName().compareTo(bus2.getBusName())).collect(Collectors.toList());
		if (buses.isEmpty()) {
			throw new BusNotFoundException("No bus found!");
		} else {
			return buses;
		}
	}

	@Override
	public List<Bluebus> getbyStartTime(String source, String destination, LocalDateTime startTime)
			throws BusNotFoundException {

		List<Bluebus> buses = null;
		buses = bluebusdao.findbyStartTime(source, destination, startTime).stream()
				.sorted((bus1, bus2) -> bus1.getBusName().compareTo(bus2.getBusName())).collect(Collectors.toList());
		if (buses.isEmpty()) {
			throw new BusNotFoundException("No bus found!");
		} else {
			return buses;
		}
	}

}
