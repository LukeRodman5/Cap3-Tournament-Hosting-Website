/********************************************************************************************************************** 
* This will should contain Application API Controllers and related objects
**********************************************************************************************************************/

package com.techelevator.application.controller;

import java.sql.Timestamp;
import java.util.List;
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
@RequestMapping
	(path = "/tournaments", method = RequestMethod.POST)
	public void addTournament(@RequestBody Tourneys tourney) {
		logRequest("adding a /tournament post");
		tourneysDAO.createATourney(tourney.getTourneyName(), tourney.getTourneyDesc(), tourney.getTourneyHost(), tourney.getStartDate(), tourney.getEndDate(),
								   tourney.isActive(), tourney.isOpenForReg(), tourney.getMaxNumOfParticipants(), tourney.getNumOfParticpants());
		}
@RequestMapping
	(value = "/tournaments", method = RequestMethod.GET)
	public List<Tourneys> tournament() { 
    	return tourneysDAO.getAllTourneys();
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

