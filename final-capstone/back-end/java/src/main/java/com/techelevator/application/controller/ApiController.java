/********************************************************************************************************************** 
* This will should contain Application API Controllers and related objects
**********************************************************************************************************************/

package com.techelevator.application.controller;

import java.sql.Timestamp;

import org.springframework.web.bind.annotation.*;

import com.techelevator.application.dao.*;
import com.techelevator.application.model.*;

@RestController
public class ApiController {
	private TourneysDAO tourneysDAO;
	
	public ApiController(TourneysDAO tourneysDAO) {
		this.tourneysDAO = tourneysDAO;
	}
/**********************************************************************************************************************
* Put your Application API Controllers here
**********************************************************************************************************************/
	@RequestMapping(path = "/tournaments", method = RequestMethod.POST)
	public void addTournament(Tourneys tourney) {
		//tourneysDAO.
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

