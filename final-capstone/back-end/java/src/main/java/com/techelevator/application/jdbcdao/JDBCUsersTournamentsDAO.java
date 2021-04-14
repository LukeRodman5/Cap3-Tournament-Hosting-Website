package com.techelevator.application.jdbcdao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.techelevator.application.dao.UsersTournamentsDAO;
import com.techelevator.application.model.UsersTournaments;

@Component
public class JDBCUsersTournamentsDAO implements UsersTournamentsDAO {

private JdbcTemplate jdbcTemplate;
	
	public JDBCUsersTournamentsDAO(JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void changeUserTourneyStatus(String status, String username, int tourneyID) {
		String sql = "update users_tournaments set status = ? where user_id = "
				   + "(select user_id from users where username = ?) and tourney_id = ?";
		
		jdbcTemplate.update(sql, status, username, tourneyID);
		
	}
	

}
