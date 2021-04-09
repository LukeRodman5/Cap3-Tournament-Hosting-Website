package com.techelevator.application.dao;

import java.time.LocalDate;
import java.util.List;

import com.techelevator.application.model.Matches;

public interface MatchesDAO { // update match 

	List<Matches> getAllMatches();
	List<Matches> getMatchById(int matchID);
	void createAMatch(LocalDate startTime, LocalDate startDate); 
	void deleteAMatch(int matchID);        // keep
	List<Matches> getMatchesByDate(); // No
	String updateAMatchId();          // Not this time
	Long updateStartDate();           // keep
	Long updateStartTime();           // keep
	}
