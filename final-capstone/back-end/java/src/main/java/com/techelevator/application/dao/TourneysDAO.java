package com.techelevator.application.dao;

import java.time.LocalDate;
import java.util.List;

import com.techelevator.application.model.*;

public interface TourneysDAO {
	
	List<Tourneys> getAllTourneys();
	List<Tourneys> getTourneysByName(String username);
	Tourneys createATourney(String tourneyName, String tourneyDesc, int tourneyHost, LocalDate startDate, LocalDate endDate, boolean isActive, boolean openForReg, int numOfParticipants);
	Tourneys deleteATourney();
	List<Tourneys> getTourneysByDate();
	String updateATourneyName();
	String updateATourneyDesc();
	Long updateStartDate();
	Long updateEndDate();
	boolean updateStatus();
	int changeNumOfParticipants();
	Long updateStartTime();
	
}