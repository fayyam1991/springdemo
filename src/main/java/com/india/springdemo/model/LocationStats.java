package com.india.springdemo.model;

/**
 * 
 * @author Fayyam
 *
 */
public class LocationStats implements Comparable<LocationStats>{

	private String state;
	private String contry;
	private int latestReportedCases;
	private int diffFromPrevDay;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getContry() {
		return contry;
	}

	public void setContry(String country) {
		this.contry = country;
	}

	/**
	 * @return the latestReportedCases
	 */
	public int getLatestReportedCases() {
		return latestReportedCases;
	}

	/**
	 * @param latestReportedCases the latestReportedCases to set
	 */
	public void setLatestReportedCases(int latestReportedCases) {
		this.latestReportedCases = latestReportedCases;
	}

	/**
	 * @return the diffFromPrevDay
	 */
	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}

	/**
	 * @param diffFromPrevDay the diffFromPrevDay to set
	 */
	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}
	
	@Override
	public int compareTo(LocationStats o) {
		return this.getContry().compareTo(o.getContry());
	}

}
