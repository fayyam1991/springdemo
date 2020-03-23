package com.india.springdemo.model;

public class GraphData {
	private String key;
	private String cases;

	public GraphData(String key, String cases) {
		super();
		this.key = key;
		this.cases = cases;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCases() {
		return cases;
	}

	public void setCases(String cases) {
		this.cases = cases;
	}

}
