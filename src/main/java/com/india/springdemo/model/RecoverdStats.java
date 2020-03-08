package com.india.springdemo.model;

public class RecoverdStats implements Comparable<RecoverdStats> {

	private String state;
	private String country;
	private int recovered;
	private int totalRecovered;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getRecovered() {
		return recovered;
	}

	public void setRecovered(int recovered) {
		this.recovered = recovered;
	}

	public int getTotalRecovered() {
		return totalRecovered;
	}

	public void setTotalRecovered(int totalRecovered) {
		this.totalRecovered = totalRecovered;
	}

	@Override
	public int compareTo(RecoverdStats o) {
		return this.getCountry().compareTo(o.getCountry());
	}

}
