/********************************************************************************************************************** 
* This will should contain Application API Controllers and related objects
**********************************************************************************************************************/

package com.techelevator.application.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;
import com.techelevator.application.dao.*;
import com.techelevator.application.model.*;

@RestController
@CrossOrigin
public class ApiController {
	private TourneysDAO tourneysDAO;
	private MatchesDAO matchesDAO;
	private UsersDAO usersDAO;
	private UsersMatchesDAO usersMatchesDAO;
	public ApiController(TourneysDAO tourneysDAO, MatchesDAO matchesDAO, UsersDAO usersDAO, UsersMatchesDAO usersMatchesDAO) {
		this.tourneysDAO = tourneysDAO;
		this.matchesDAO = matchesDAO;
		this.usersDAO = usersDAO;
		this.usersMatchesDAO = usersMatchesDAO;
		}	
/******************************************************************************
***   ***   ***   *** Tournaments API Controllers ***   ***   ***   ***   ***
*******************************************************************************/

@RequestMapping		/* Add a new Tournament to Database */
	(path = "/tournaments", method = RequestMethod.POST)
	public void addTournament(@RequestBody Tourneys tourney) {
		logRequest("adding a /tournament post");
		tourneysDAO.createATourney(tourney.getTourneyName(), tourney.getTourneyDesc(), tourney.getTourneyHost(), tourney.getStartDate(), tourney.getEndDate(),
								   tourney.isActive(), tourney.isOpenForReg(), tourney.getMaxNumOfParticipants(), tourney.getNumOfParticipants());
	}

@RequestMapping		/* Add a user to a tournament */
	(path = "/tournaments/{tourneyID}/{username}/{status}", method = RequestMethod.POST)
	public void addUserToTourney(@PathVariable String username,  @PathVariable int tourneyID, @PathVariable String status) {
		logRequest("Adding user to a tournament");
		tourneysDAO.addUserToTourney(username, tourneyID, status);
	}

@RequestMapping		/* Get all tournaments in a list */
	(value = "/tournaments", method = RequestMethod.GET)
	public List<Tourneys> tournament() { 
		logRequest("Getting all tournaments");
    	return tourneysDAO.getAllTourneys();
    }

@RequestMapping		/* Get all users in a tournament */
	(path = "tournaments/{tourneyID}/users/", method = RequestMethod.GET)
	public List<Users> getAllUsersInATourney(@PathVariable int tourneyID) {
		logRequest("Getting all tourneys by user");
		return tourneysDAO.getAllUsersInATourney(tourneyID);
	}

@RequestMapping		/* Get a tournament by tourney_Id */
	(path = "/tournaments/{Id}", method = RequestMethod.GET) 
	public Tourneys getTourney(@PathVariable long Id) {
		logRequest("Getting a tournament by Tourney Id");
		return tourneysDAO.getATourneyById(Id);
	}

@RequestMapping		/* Get a tournament by user (for regular users not for hosts) */
	(path = "/tournaments/users/{username}", method = RequestMethod.GET)
	public List<Tourneys> allUserTourneys(@PathVariable String username) { 
		logRequest("Getting all tourneys by user");
		return tourneysDAO.getTourneysByName(username);
	}

@RequestMapping		/* Update a Tournament by tourney_id */
	(path = "/tournaments/{Id}", method = RequestMethod.PUT)
	public void tournamentUpdate(@RequestBody Tourneys tourney, @PathVariable long Id) {
		logRequest("Updating a tournament");
		tourneysDAO.updateATourney(tourney);
	}

@RequestMapping		/* Update tournament the user is in */
	(path = "/tournaments/{username}/{currentTourneyId}/{newTourneyId}", method = RequestMethod.PUT)
	public void updateUsernameTourneys(@PathVariable String username,@PathVariable int currentTourneyId,@PathVariable int newTourneyId) { 
		logRequest("Updating the tournament the user is in");
		tourneysDAO.updateUserTourney(username, newTourneyId, currentTourneyId);
	}

@ResponseStatus(HttpStatus.NO_CONTENT)
@RequestMapping		/* Delete a tournament by tourney_Id */
	(path = "/tournaments/{Id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int Id) {
		logRequest("Deleting a tournament");
		tourneysDAO.deleteATourney(Id);
	}
	
@ResponseStatus(HttpStatus.NO_CONTENT)
@RequestMapping		/* Delete a user from tournament */
	(path = "/tournaments/{tourneyId}/users/{username}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String username,@PathVariable int tourneyId) {
		logRequest("Deleting user from tournament");
		tourneysDAO.removeUserFromTourney(username, tourneyId);
	}

/******************************************************************************
***   ***   ***   *** Matches API Controllers ***   ***   ***   ***   ***
*******************************************************************************/

@RequestMapping		/* Add a new Match to Database */
	(path = "/matches/tourneyID/{tourneyID}", method = RequestMethod.POST)
	public int addMatches( @RequestBody Matches match, @PathVariable long tourneyID) {
		logRequest("Add a list of matches");
		return matchesDAO.createAMatch(match, tourneyID);
	}

@RequestMapping		/* Add a match to a tournament */
	(path = "/tournaments/{tourneyId}/matches/{matchId}", method = RequestMethod.POST)
	public void addAMatchToTourney(@PathVariable long tourneyId,  @PathVariable long matchId) {
		logRequest("Adding match to a tournament");
		matchesDAO.addAMatchToTourney(tourneyId, matchId);
	}

@RequestMapping		/* Update start date */
	(path = "/matches/localDate/{startDate}", method = RequestMethod.PUT)
	public void updateStartDate(@PathVariable LocalDate startDate, @PathVariable long currentStartDate, @PathVariable long newStartDate) {
		logRequest("Updating a start date");
		matchesDAO.updateStartDate();
	}

@RequestMapping		/* Update start time */
	(path = "/matches/localDate/{startTime}", method = RequestMethod.PUT)
	public void updateStartTime(@PathVariable LocalDate startTime, @PathVariable long currentStartTime, @PathVariable long newStartTime) {
		logRequest("Updating a start time");
		matchesDAO.updateStartTime();
	}

@RequestMapping		/* Get all matches in a list */
	(path = "/matches", method = RequestMethod.GET)
	public List<Matches> match() { 
		logRequest("Getting all matches");
    	return matchesDAO.getAllMatches();
    }

@RequestMapping
	(path="/user-matches/{tourneyID}", method = RequestMethod.GET)
	public List<UsersMatches> getAllUserMatchesByTourneyID(@PathVariable long tourneyID) {
		logRequest("Getting all user_matches by Tourney ID");
		return usersMatchesDAO.getAllMatchesInUMByTourneyID(tourneyID);
}

@RequestMapping		/* Get matches by start date */
	(path = "/matches/{startDate}", method = RequestMethod.GET)
	public List<Matches> allMatchesByDate(@PathVariable LocalDate startDate, LocalDate startTime) { 
		logRequest("Getting all tourneys by user");
		return matchesDAO.getMatchesByDate();
	}

@RequestMapping
	(path = "/matches/{matchID}/{username}", method = RequestMethod.POST)
	public void addAUserToAMatch(@PathVariable int matchID, @PathVariable String username) {
	logRequest("Adding a user to a match");
	matchesDAO.addUserToMatch(username, matchID);
}

@ResponseStatus(HttpStatus.NO_CONTENT)
@RequestMapping		/* Delete a user from match */
	(path = "/matches/{matchId}/{username}", method = RequestMethod.DELETE)
	public void removeUserFromMatch(@PathVariable String username, long matchId) {
		logRequest("Deleting user from match");
		matchesDAO.removeUserFromMatch(username, matchId);
	}

@ResponseStatus(HttpStatus.NO_CONTENT)
@RequestMapping		/* Delete a match from tournament*/
	(path = "/matches/{tourneys}", method = RequestMethod.DELETE)
	public void removeMatchFromTourney(@PathVariable long tourneyId,long matchId) {
		logRequest("Deleting a match from tourney");
		matchesDAO.removeMatchFromTourney(tourneyId, matchId);
	}

@ResponseStatus(HttpStatus.NO_CONTENT)
@RequestMapping		/* Delete a match */
	(path = "/matches/{matchId}", method = RequestMethod.DELETE)
	public void deleteAMatch(@PathVariable int matchId) {
		logRequest("Deleting a match");
		matchesDAO.deleteAMatch(matchId);
	}

@RequestMapping 	/* Get all matches in a tournament */
	(path = "/tourneys/matches/{tourneyId}", method = RequestMethod.GET)
	public List<Matches> getAllMatchesInATourney(@PathVariable long tourneyId) { 
			logRequest("Getting all matches in a tournament");
			return matchesDAO.getAllMatchesInATourney(tourneyId);
	}

/******************************************************************************
***   ***   ***   *** Users API Controllers ***   ***   ***   ***   ***
*******************************************************************************/
@RequestMapping		/* Get username by tourney id */
	(path = "/tournaments/{tourneyId}/username", method = RequestMethod.GET)
	public String usernameByHostID(@PathVariable long tourneyId) {
		logRequest("Getting username by tourneyId");
		return usersDAO.getUsernameByTourneyId(tourneyId);}

@RequestMapping			/* Get all users */
	(path = "/users", method = RequestMethod.GET)
	public List<Users> getAllUsers() {
		logRequest("Getting all users");
		return usersDAO.getAllUsers();}




@RequestMapping
	(path = "/usersmatches/{winStatus}/{userID}/{matchID}", method = RequestMethod.PUT)
	public void updateUsersMatches(@PathVariable boolean winStatus, @PathVariable int userID, @PathVariable int matchID) {
		logRequest("update Users_Matches table");
		usersMatchesDAO.updateUserMatches(winStatus, userID, matchID);
}

/********************************************************************************************************************* 
* Use this method if you'd like to log calls to your controllers - these message can aid in your troubleshooting
* 
* To use it: logRequest("a message you want to appear after the timestamp in the server log")
* 
* There are examples in the AuthenicationController.java program in the com.techelevator.security.controller package
**********************************************************************************************************************/	
    static void logRequest(String message) {
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	 
    	System.out.println(timestamp + " - " + message);
    }
    
    
}

