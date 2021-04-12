package com.techelevator.application.dao;

import java.time.LocalDate;
import java.util.List;
import com.techelevator.application.model.Matches;

public interface MatchesDAO {// update match 

	List<Matches> getAllMatches();//
	List<Matches> getMatchById(int matchId);//
	List<Matches> getMatchesByDate();// No
	List<Matches> getAllMatchesInATourney(long tourneyID);
	int createAMatch(LocalDate startDate, LocalDate startTime);//
	void updateAMatch(LocalDate startDate, LocalDate startTime, int matchId);//
	void deleteAMatch(int matchId);// Keep
	void addUserToMatch(String username, int matchId);//
	void updateUserToDiffMatch(int newMatchId, String username, int currentMatchId);//
	void removeUserFromMatch(String username, long matchId);
	String updateAMatchId();// Not this time
	Long updateStartDate();// Changed to update an entire match instead
	Long updateStartTime();// Changed to update an entire match instead
	void addMatchToTourney(long tourneyID, long matchId);
	void removeMatchFromTourney(long tourneysID, long matchID);
	
	}
