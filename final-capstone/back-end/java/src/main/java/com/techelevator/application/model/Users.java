package com.techelevator.application.model;

public class Users {
	private long userId;
	private String username;
									/****************** Getters *********************/
									/************************************************/
public long getUserId() {
	return userId;
}
public String getUsername() {
	return username;
}
									/****************** Setters *********************/
									/************************************************/
public void setUserId(long userId) {
	this.userId = userId;
}
public void setUsername(String username) {
	this.username = username;
}
									/****************** ToString *********************/
									/*************************************************/
@Override
public String toString() {
	return "Users [userId=" + userId + ", username=" + username + "]";
}
 
}
