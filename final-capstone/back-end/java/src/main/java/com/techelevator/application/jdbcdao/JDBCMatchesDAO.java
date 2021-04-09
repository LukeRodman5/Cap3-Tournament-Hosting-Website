package com.techelevator.application.jdbcdao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.application.dao.MatchesDAO;
import com.techelevator.application.model.Matches;

public class JDBCMatchesDAO implements MatchesDAO {
		
	private JdbcTemplate jdbcTemplate;
	
	public JDBCMatchesDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Matches> getAllMatches() { 
		List<Matches>listOfMatches = new ArrayList<>();
		String sql = "select * from matches";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
			
			while(results.next()) {
				Matches aMatch = mapRowToMatches(results);
				listOfMatches.add(aMatch);
			}
		return listOfMatches;
	}

	@Override
	public List<Matches> getMatchById(int matchID) { // needs it by user id not match id
		List<Matches>listOfMatches = new ArrayList<>();
		String sql = "select * from matches where match_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, matchID);
			
			while(results.next()) {
				Matches aMatch = mapRowToMatches(results);
				listOfMatches.add(aMatch);
			}
		return listOfMatches;
	}

	@Override
	public void createAMatch(LocalDate startTime, LocalDate startDate) { // needs specified
		String sql = "insert into matches (start_time, start_date) values (?, ?)";
		jdbcTemplate.update(sql, startTime, startDate);
	}
		
	@Override
	public void updateAMatch(LocalDate startTime, LocalDate startDate, int matchID) {
		String sql = "update matches set start_time = ?, start_date = ? where match_id = ?";
		
		jdbcTemplate.update(sql, startTime, startDate, matchID);
	}
	
	@Override
	public void deleteAMatch(int matchID) { // needs 
		String sql = "delete from matches where match_id = ?";
		
		jdbcTemplate.update(sql, matchID);
		
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
	
	private Matches mapRowToMatches(SqlRowSet results) {
		Matches matchRow = new Matches();
		matchRow.setMatchId(results.getLong("match_id"));
		matchRow.setStartTime(results.getDate("start_time").toLocalDate());
		matchRow.setStartDate(results.getDate("start_date").toLocalDate());
		
		return matchRow;
	}

	
	
	
}
