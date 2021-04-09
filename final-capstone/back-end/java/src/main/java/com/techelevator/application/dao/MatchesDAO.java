package com.techelevator.application.dao;

import java.time.LocalDate;
import java.util.List;

import com.techelevator.application.model.Matches;

public interface MatchesDAO { // update match 

	List<Matches> getAllMatches();
	List<Matches> getMatchById(int matchID);
	void createAMatch(LocalDate startTime, LocalDate startDate); 
	void deleteAMatch(int matchID);        // keep
	void updateAMatch(LocalDate startTime, LocalDate startDate, int matchID);
	void addUserToMatch(String username, int matchID);
	void updateUserToDiffMatch(int newMatchID, String username, int currentMatchID);
	List<Matches> getMatchesByDate(); // No
	String updateAMatchId();          // Not this time
	Long updateStartDate();           // Changed to update an entire match instead
	Long updateStartTime();           // Changed to update an entire match instead
	
	}
