package com.techelevator.application.jdbcdao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techelevator.application.dao.UsersTournamentsDAO;
import com.techelevator.application.model.UsersTournaments;

@Component
public class JDBCUsersTournamentsDAO implements UsersTournamentsDAO {

	@Override
	public List<UsersTournaments> getAllTourneysByUserStatus() {
		
		return null;
	}
	

}
