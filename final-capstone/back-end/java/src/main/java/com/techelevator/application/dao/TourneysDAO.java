package com.techelevator.application.dao;

import java.util.List;

import com.techelevator.application.model.*;

public interface TourneysDAO {
	
	List<Tourneys> getAllTourneys();
	List<Tourneys> getTourneysByName();
	Tourneys createATourney();
	Tourneys deleteATourney();
	List<Tourneys> getTourneysByDate();
	String updateATourneyName();
	String updateATourneyDesc();
	Long updateDate();
	boolean updateStatus();
	int changeNumOfParticipants();
	
	
}