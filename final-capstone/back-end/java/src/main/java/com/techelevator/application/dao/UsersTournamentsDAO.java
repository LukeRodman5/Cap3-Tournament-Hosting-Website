package com.techelevator.application.dao;

import java.util.List;

import com.techelevator.application.model.UsersTournaments;

public interface UsersTournamentsDAO {

	void changeUserTourneyStatus(String status, String username, int tourneyID);
	
}
