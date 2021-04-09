package com.techelevator.application.model;

public class Users {
 private long userId;
 private String username;
 
/**
 * @return the userId
 */
public long getUserId() {
	return userId;
}
/**
 * @param userId the userId to set
 */
public void setUserId(long userId) {
	this.userId = userId;
}
/**
 * @return the username
 */
public String getUsername() {
	return username;
}
/**
 * @param username the username to set
 */
public void setUsername(String username) {
	this.username = username;
}
@Override
public String toString() {
	return "Users [userId=" + userId + ", username=" + username + "]";
}
 
}
