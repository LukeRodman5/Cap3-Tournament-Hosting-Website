package com.techelevator.application.dao;

import java.time.LocalDate;
import java.util.List;

import com.techelevator.application.model.*;

public interface TourneysDAO {
	
	List<Tourneys> getAllTourneys();
	List<Tourneys> getTourneysByName(String username);
	List<Tourneys> getTourneysByHostID(int hostID);
	List<Users> getAllUsersInATourney(int tourneyId);
	void createATourney(String tourneyName, String tourneyDesc, String tourneyHost, LocalDate startDate, LocalDate endDate, boolean isActive, boolean regIsActive, int numOfParticipants, int maxNumOfParticipants);
	void deleteATourney(long id);
	void updateATourney(Tourneys updatedTourney);
	void addUserToTourney(String username, int tourneyId);
	void updateUserTourney(String username, int newTourneyId, int currentTourneyId);
	void removeUserFromTourney(String username, int tourneyId);
	List<Tourneys> getTourneysByDate();
	String updateATourneyName();
	String updateATourneyDesc();
	Long updateStartDate();
	Long updateEndDate();
	boolean updateStatus();
	int changeNumOfParticipants();
	Long updateStartTime();
	Tourneys getATourneyById(long Id);
	
	
}