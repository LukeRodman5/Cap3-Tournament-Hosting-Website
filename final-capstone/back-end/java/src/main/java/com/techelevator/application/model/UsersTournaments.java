package com.techelevator.application.model;

public class UsersTournaments {
	private int userId;
	private int tourneyId;
	private int status;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTourneyId() {
		return tourneyId;
	}
	public void setTourneyId(int tourneyId) {
		this.tourneyId = tourneyId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "UsersTournaments [userId=" + userId + ", tourneyId=" + tourneyId + ", status=" + status + "]";
	}
	
	

}
