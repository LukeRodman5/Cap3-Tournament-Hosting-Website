package com.techelevator.application.dao;

import java.util.List;

import com.techelevator.application.model.Matches;
import com.techelevator.application.model.UsersMatches;

public interface UsersMatchesDAO {

	void updateUserMatches(boolean winStatus, int userID, int matchID);
	List<UsersMatches> getAllMatchesInUMByTourneyID(long tourneyID);
	
}
