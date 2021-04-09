package com.techelevator.application.dao;

import java.util.List;

import com.techelevator.application.model.Matches;

public interface MatchesDAO { // update match 

	List<Matches> getAllMatches(); // Keep
	List<Matches> getMatchById();  // Keep
	Matches createAMatch();        // keep
	Matches deleteAMatch();        // keep
	List<Matches> getMatchesByDate(); // No
	String updateAMatchId();          // Not this time
	Long updateStartDate();           // keep
	Long updateStartTime();           // keep
	}
