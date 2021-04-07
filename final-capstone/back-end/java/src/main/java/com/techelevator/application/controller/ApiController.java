/********************************************************************************************************************** 
* This will should contain Application API Controllers and related objects
**********************************************************************************************************************/

package com.techelevator.application.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.techelevator.application.dao.*;
import com.techelevator.application.model.*;

@RestController
@CrossOrigin
public class ApiController {
	private TourneysDAO tourneysDAO;
	
	public ApiController(TourneysDAO tourneysDAO) {
		this.tourneysDAO = tourneysDAO;
	}
/**********************************************************************************************************************
* Put your Application API Controllers here
**********************************************************************************************************************/
	
/* 
 * Add a new Tournament to Database	
 */
@RequestMapping
	(path = "/tournaments", method = RequestMethod.POST)
	public void addTournament(@RequestBody Tourneys tourney) {
		logRequest("adding a /tournament post");
		tourneysDAO.createATourney(tourney.getTourneyName(), tourney.getTourneyDesc(), tourney.getTourneyHost(), tourney.getStartDate(), tourney.getEndDate(),
								   tourney.isActive(), tourney.isOpenForReg(), tourney.getMaxNumOfParticipants(), tourney.getNumOfParticpants());
		}

/*
 * Get all tournaments in a list
 */
@RequestMapping
	(value = "/tournaments", method = RequestMethod.GET)
	public List<Tourneys> tournament() { 
	logRequest("Getting all tournaments");
    	return tourneysDAO.getAllTourneys();
    	}

/*
 * Update a Tournament by tourney_id
 */
@RequestMapping
	(path = "/tournaments/{id}", method = RequestMethod.PUT)
	public void tournamentUpdate(@RequestBody Tourneys tourney, @PathVariable long id) {
	logRequest("Updating a tournament");
		tourneysDAO.updateATourney(tourney);
}


/*
 * Delete a tournament by tourney_id
 */
@ResponseStatus(HttpStatus.NO_CONTENT)
@RequestMapping
	(path = "/tournaments/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {
	logRequest("Deleting a tournament");
		tourneysDAO.deleteATourney(id);
}
	

/*
 * Get a tournament by tourney_id
 */
@RequestMapping
	(path = "/tournaments/{id}", method = RequestMethod.GET) 
	public Tourneys getTourney(@PathVariable long id) {
	logRequest("Deleting a tournament");
	return tourneysDAO.getATourneyById(id);
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

