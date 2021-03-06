package com.india.springdemo.model;

public class DeathStats implements Comparable<DeathStats> {

	private String state;
	private String country;
	private int deaths;

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

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	

	@Override
	public int compareTo(DeathStats o) {
		return this.getCountry().compareTo(o.getCountry());
	}

}
