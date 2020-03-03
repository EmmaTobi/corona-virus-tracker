package com.corona.model;

public class LocationStat {

	private String state;
	private String country;
	private int infectedCount;
	private int previousInfectedCount;
	private int changeFromInfectedCount;
	
	
	
	public int getPreviousInfectedCount() {
		return previousInfectedCount;
	}
	public void setPreviousInfectedCount(int previousInfectedCount) {
		this.previousInfectedCount = previousInfectedCount;
	}
	public int getChangeFromInfectedCount() {
		return changeFromInfectedCount;
	}
	public void setChangeFromInfectedCount(int changeFromInfectedCount) {
		this.changeFromInfectedCount = changeFromInfectedCount;
	}
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
	public int getInfectedCount() {
		return infectedCount;
	}
	public void setInfectedCount(int infectedCount) {
		this.infectedCount = infectedCount;
	}
}
