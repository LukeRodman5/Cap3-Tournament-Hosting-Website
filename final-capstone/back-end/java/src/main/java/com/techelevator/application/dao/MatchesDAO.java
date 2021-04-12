package com.techelevator.application.dao;

import java.time.LocalDate;
import java.util.List;

import com.techelevator.application.model.Matches;

public interface MatchesDAO { 		  // update match 

	List<Matches> getAllMatches();
	List<Matches> getMatchById(int matchId);
	void createAMatch(LocalDate startDate, LocalDate startTime); 
	void deleteAMatch(int matchId);   // keep
	void updateAMatch(LocalDate startDate, LocalDate startTime, int matchId);
	void addUserToMatch(String username, int matchId);
	void updateUserToDiffMatch(int newMatchId, String username, int currentMatchId);
	List<Matches> getMatchesByDate(); // No
	String updateAMatchId();          // Not this time
	Long updateStartDate();           // Changed to update an entire match instead
	Long updateStartTime();           // Changed to update an entire match instead
	
	}
