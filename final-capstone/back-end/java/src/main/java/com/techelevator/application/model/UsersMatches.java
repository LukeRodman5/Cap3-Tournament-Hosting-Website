package com.techelevator.application.model;

public class UsersMatches {
	private int userId;
	private int matchId;
	private boolean winStatus;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMatchId() {
		return matchId;
	}
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	public boolean isWinStatus() {
		return winStatus;
	}
	public void setWinStatus(boolean winStatus) {
		this.winStatus = winStatus;
	}
	@Override
	public String toString() {
		return "Users_Matches [userId=" + userId + ", matchId=" + matchId + ", winStatus=" + winStatus + "]";
	}
	
	
}
