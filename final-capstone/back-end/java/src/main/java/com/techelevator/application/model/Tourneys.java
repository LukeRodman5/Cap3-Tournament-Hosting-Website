package com.techelevator.application.model;

public class Tourneys {
	private Long tourneyId;
	private String tourneyName;
	private String tourneyDesc;
	private int tourneyHost;
	private Long schedTime;
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
	public Long getSchedTime() {
		return schedTime;
	}
	public void setSchedTime(Long schedTime) {
		this.schedTime = schedTime;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getParticipateNum() {
		return numOfParticpants;
	}
	public void setParticipateNum(int numOfParticpants) {
		this.numOfParticpants = numOfParticpants;
	}
	@Override
	public String toString() {
		return "Tourneys [tourneyId=" + tourneyId + ", tourneyName=" + tourneyName + ", tourneyDesc=" + tourneyDesc
				+ ", tourneyHost=" + tourneyHost + ", schedTime=" + schedTime + ", isActive=" + isActive
				+ ", numOfParticpants=" + numOfParticpants + "]";
	}
	
	
}