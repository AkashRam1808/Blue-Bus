package com.bluebus.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Bluebus {

	private Integer busNumber;
	private String busName;
	private LocalDate startDate;
	private String category;
	private String type;
	private double cost;
	private String source;
	private String destination;
	private int seatsAvailable;
	private LocalDateTime startTime;

	public Bluebus() {
		super();
	}

	public Bluebus(Integer busNumber, String busName, LocalDate startDate, String category, String type, double cost,
			String source, String destination, int seatsAvailable, LocalDateTime startTime) {
		super();
		this.busNumber = busNumber;
		this.busName = busName;
		this.startDate = startDate;
		this.category = category;
		this.type = type;
		this.cost = cost;
		this.source = source;
		this.destination = destination;
		this.seatsAvailable = seatsAvailable;
		this.startTime = startTime;
	}

	public Integer getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(Integer busNumber) {
		this.busNumber = busNumber;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	@Override
	public String toString() {
		return "Bus [busNumber=" + busNumber + ", busName=" + busName + ", startDate=" + startDate + ", category="
				+ category + ", type=" + type + ", cost=" + cost + ", source=" + source + ", destination=" + destination
				+ ", seatsAvailable=" + seatsAvailable + ", startTime=" + startTime + "]";
	}

}
