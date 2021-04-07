package com.techelevator.application.jdbcdao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.application.dao.MatchesDAO;
import com.techelevator.application.model.Matches;

public class JDBCMatchesDAO implements MatchesDAO {
		
	private JdbcTemplate jdbcTemplate;
	
	public JDBCMatchesDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Matches> getAllMatches() { // needs
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Matches> getMatchById() { // needs it by user id not match id
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matches createAMatch() { // needs specified
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matches deleteAMatch() { // needs 
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Matches> getMatchesByDate() { // no
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateAMatchId() { // not certain if needed, but definitely need to be able to update
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long updateStartDate() { // needs. suggestion to add a match update, date, time, all together as one match item to send the full object
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long updateStartTime() { // see above
		// TODO Auto-generated method stub
		return null;
	}
}
