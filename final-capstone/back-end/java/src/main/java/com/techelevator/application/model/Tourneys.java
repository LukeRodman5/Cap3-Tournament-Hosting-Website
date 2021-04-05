package com.techelevator.application.model;

public class Tourneys {
	private Long tourneyId;
	private String tourneyName;
	private String tourneyDesc;
	private int tourneyHost;
	private Long startDate;
	private Long endDate;
	private Long startTime;
	private  boolean isActive = true;
	private int numOfParticpants;
	public Long getTourneyId() {
		return tourneyId;
	}
	public void setTourneyId(Long tourneyId) {
		this.tourneyId = tourneyId;
	}
	public String getTourneyName() {
		return tourneyName;
	}
	public void setTourneyName(String tourneyName) {
		this.tourneyName = tourneyName;
	}
	public String getTourneyDesc() {
		return tourneyDesc;
	}
	public void setTourneyDesc(String tourneyDesc) {
		this.tourneyDesc = tourneyDesc;
	}
	public int getTourneyHost() {
		return tourneyHost;
	}
	public void setTourneyHost(int tourneyHost) {
		this.tourneyHost = tourneyHost;
	}
	public Long getStartDate() {
		return startDate;
	}
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}
	public Long getEndDate() {
		return endDate;
	}
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getNumOfParticpants() {
		return numOfParticpants;
	}
	public void setNumOfParticpants(int numOfParticpants) {
		this.numOfParticpants = numOfParticpants;
	}
	@Override
	public String toString() {
		return "Tourneys [tourneyId=" + tourneyId + ", tourneyName=" + tourneyName + ", tourneyDesc=" + tourneyDesc
				+ ", tourneyHost=" + tourneyHost + ", startDate=" + startDate + ", endDate=" + endDate + ", startTime="
				+ startTime + ", isActive=" + isActive + ", numOfParticpants=" + numOfParticpants + "]";
	}
	

	
}