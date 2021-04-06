package com.techelevator.application.dao;

import java.time.LocalDate;
import java.util.List;

import com.techelevator.application.model.*;

public interface TourneysDAO {
	
	List<Tourneys> getAllTourneys();
	List<Tourneys> getTourneysByName(String username);
	Tourneys createATourney(String username, String tourneyName, String tourneyDesc, int tourneyHost, LocalDate startDate, LocalDate endDate, int numOfParticipants, int maxNumOfParticipants);
	Tourneys deleteATourney();
	Tourneys updateATourney();
	List<Tourneys> getTourneysByDate();
	String updateATourneyName();
	String updateATourneyDesc();
	Long updateStartDate();
	Long updateEndDate();
	boolean updateStatus();
	int changeNumOfParticipants();
	Long updateStartTime();
	
}