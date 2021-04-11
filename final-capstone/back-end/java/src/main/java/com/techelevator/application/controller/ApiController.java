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
	public ApiController(TourneysDAO tourneysDAO, MatchesDAO matchesDAO, UsersDAO usersDAO) {
		this.tourneysDAO = tourneysDAO;
		this.matchesDAO = matchesDAO;
		this.usersDAO = usersDAO;
		}	
/******************************************************************************
***   ***   ***   *** Tournaments API Controllers ***   ***   ***   ***   ***
*******************************************************************************/
	
/* Add a new Tournament to Database */
@RequestMapping
	(path = "/tournaments", method = RequestMethod.POST)
	public void addTournament(@RequestBody Tourneys tourney) {
		logRequest("adding a /tournament post");
		tourneysDAO.createATourney(tourney.getTourneyName(), tourney.getTourneyDesc(), tourney.getTourneyHost(), tourney.getStartDate(), tourney.getEndDate(),
								   tourney.isActive(), tourney.isOpenForReg(), tourney.getMaxNumOfParticipants(), tourney.getNumOfParticipants());
	}

/* Get all tournaments in a list */
@RequestMapping
	(value = "/tournaments", method = RequestMethod.GET)
	public List<Tourneys> tournament() { 
		logRequest("Getting all tournaments");
    	return tourneysDAO.getAllTourneys();
    }

/* Update a Tournament by tourney_id */
@RequestMapping
	(path = "/tournaments/{id}", method = RequestMethod.PUT)
	public void tournamentUpdate(@RequestBody Tourneys tourney, @PathVariable long id) {
		logRequest("Updating a tournament");
		tourneysDAO.updateATourney(tourney);
	}

/* Delete a tournament by tourney_id */
@ResponseStatus(HttpStatus.NO_CONTENT)
@RequestMapping
	(path = "/tournaments/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {
		logRequest("Deleting a tournament");
		tourneysDAO.deleteATourney(id);
	}
	
/* Get a tournament by tourney_id */
@RequestMapping
	(path = "/tournaments/{id}", method = RequestMethod.GET) 
	public Tourneys getTourney(@PathVariable long id) {
		logRequest("Getting a tournament by Tourney ID");
		return tourneysDAO.getATourneyById(id);
	}

// Add a user to a tournament
@RequestMapping
	(path = "/tournaments/{tourneyID}/{username}", method = RequestMethod.POST)
	public void addUserToTourney(@PathVariable String username,  @PathVariable int tourneyID) {
		logRequest("Adding user to a tournament");
		tourneysDAO.addUserToTourney(username, tourneyID);
	}

/* Get a tournament by user (for regular users not for hosts) */
@RequestMapping
	(path = "/tournaments/users/{username}", method = RequestMethod.GET)
	public List<Tourneys> allUserTourneys(@PathVariable String username) { 
		logRequest("Getting all tourneys by user");
		return tourneysDAO.getTourneysByName(username);
	}

/* Update tournament the user is in */
@RequestMapping
	(path = "/tournaments/{username}/{currentTourneyID}/{newTourneyID}", method = RequestMethod.PUT)
	public void updateUsernameTourneys(@PathVariable String username,@PathVariable int currentTourneyID,@PathVariable int newTourneyID) { 
		logRequest("Updating the tournament the user is in");
		tourneysDAO.updateUserTourney(username, newTourneyID, currentTourneyID);
	}

/* Delete a user from tournament */
@ResponseStatus(HttpStatus.NO_CONTENT)
@RequestMapping
	(path = "/tournaments/users/{username}/{tourneyID}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String username,@PathVariable int tourneyID) {
		logRequest("Deleting user from tournament");
		tourneysDAO.removeUserFromTourney(username, tourneyID);
	}

@RequestMapping
(path = "tournaments/{tourneyID}/users/", method = RequestMethod.GET)
public List<Users> getAllUsersInATourney(@PathVariable int tourneyID) {
	logRequest("Getting all tourneys by user");
	return tourneysDAO.getAllUsersInATourney(tourneyID);
}

/******************************************************************************
***   ***   ***   *** Matches API Controllers ***   ***   ***   ***   ***
*******************************************************************************/

/* Add a new Match to Database */
@RequestMapping
(path = "/matches", method = RequestMethod.POST)
public void addMatches( @RequestBody Matches match) {
	logRequest("Add a list of matches");
	matchesDAO.createAMatch(match.getStartTime(), match.getStartDate());
}

/* Get all matches in a list */
@RequestMapping
	(path = "/matches", method = RequestMethod.GET)
	public List<Matches> match() { 
		logRequest("Getting all matches");
    	return matchesDAO.getAllMatches();
    }


/******************************************************************************
***   ***   ***   *** Users API Controllers ***   ***   ***   ***   ***
*******************************************************************************/
@RequestMapping
(path = "/tournaments/{tourneyID}/username", method = RequestMethod.GET)
public String usernameByHostID(@PathVariable long tourneyID) {
	logRequest("Getting username by tourneyID");
	return usersDAO.getUsernameByTourneyId(tourneyID);
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

