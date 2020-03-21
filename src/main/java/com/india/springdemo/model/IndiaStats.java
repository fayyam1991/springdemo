package com.india.springdemo.model;

public class IndiaStats implements Comparable<IndiaStats>{

	private String state;
	private int totalIndianCases;
	private int totalForeignCases;
	private int totalRecovered;
	private int totalDeaths;
	private int newCasesToday;
	public IndiaStats(String state, int totalIndianCases, int totalForeignCases, int totalRecovered, int totalDeaths,
			int newCasesToday) {
		super();
		this.state = state;
		this.totalIndianCases = totalIndianCases;
		this.totalForeignCases = totalForeignCases;
		this.totalRecovered = totalRecovered;
		this.totalDeaths = totalDeaths;
		this.newCasesToday = newCasesToday;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getTotalIndianCases() {
		return totalIndianCases;
	}
	public void setTotalIndianCases(int totalIndianCases) {
		this.totalIndianCases = totalIndianCases;
	}
	public int getTotalForeignCases() {
		return totalForeignCases;
	}
	public void setTotalForeignCases(int totalForeignCases) {
		this.totalForeignCases = totalForeignCases;
	}
	public int getTotalRecovered() {
		return totalRecovered;
	}
	public void setTotalRecovered(int totalRecovered) {
		this.totalRecovered = totalRecovered;
	}
	public int getTotalDeaths() {
		return totalDeaths;
	}
	public void setTotalDeaths(int totalDeaths) {
		this.totalDeaths = totalDeaths;
	}
	public int getNewCasesToday() {
		return newCasesToday;
	}
	public void setNewCasesToday(int newCasesToday) {
		this.newCasesToday = newCasesToday;
	}
	@Override
	public int compareTo(IndiaStats o) {
		return Integer.compare( o.getTotalIndianCases(),this.getTotalIndianCases());
	}
	
	
	

	
}
