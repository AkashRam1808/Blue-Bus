package com.bluebus.model;

public enum Category {

	SLEEPER("Sleeper"), SEMI("Semi-Sleeper"), SEAT("Seater");

	public String category;

	private Category(String category) {
		this.category = category;
	}

}
