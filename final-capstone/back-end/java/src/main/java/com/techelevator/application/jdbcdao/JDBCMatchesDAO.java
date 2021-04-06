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
	public List<Matches> getAllMatches() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Matches> getMatchById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matches createAMatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matches deleteAMatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Matches> getMatchesByDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateAMatchId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long updateStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long updateStartTime() {
		// TODO Auto-generated method stub
		return null;
	}
}
