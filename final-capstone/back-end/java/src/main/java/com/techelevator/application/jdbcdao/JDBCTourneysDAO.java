package com.techelevator.application.jdbcdao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.application.dao.TourneysDAO;
import com.techelevator.application.model.Tourneys;
@Component
public class JDBCTourneysDAO implements TourneysDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCTourneysDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Tourneys> getAllTourneys() {
		List<Tourneys>listOfTourneys = new ArrayList<>();
		String sql = "select * from tournaments";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
			while(results.next()) {
				Tourneys tourney = mapRowToTourneys(results);
				listOfTourneys.add(tourney);
			}	
		return listOfTourneys;
	}

	@Override
	public List<Tourneys> getTourneysByName(String username) {
		List<Tourneys>listOfTourneys = new ArrayList<>();
		String sql = "select * from tournaments";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username.toLowerCase());
			while(results.next()) {
				Tourneys tourney = mapRowToTourneys(results);
				listOfTourneys.add(tourney);
			}	
		return listOfTourneys;
	}
	
	
	@Override //                                                query for username to get id                                                           (set a max)
	public void createATourney(String name, String description, String host, LocalDate startDate, LocalDate endDate, boolean tourneyIsActive, boolean regIsActive, int maxNumberOfParticipants, int numberOfParticipants) {
		String subSql = "select user_id from users where username = ?";
		
		SqlRowSet subQuery = jdbcTemplate.queryForRowSet(subSql, host);
		subQuery.next();
		
		long id = subQuery.getInt("user_id");
		
		String sql = "insert into tournaments (tourney_name, tourney_desc, tourney_host, start_date, end_date, tourney_is_active, open_for_reg, participant_max, participant_num) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(sql, name, description, id, startDate, endDate, tourneyIsActive, regIsActive, maxNumberOfParticipants, numberOfParticipants);
		
	}
		
	
	@Override
	public void updateATourney(Tourneys updatedTourney) {
		String sql = "update tournaments set tourney_name = ?, tourney_desc = ?,  tourney_host = ?, "
				+ "start_date = ?, end_date = ?, tourney_is_active = ?,"
				+ "open_for_reg = ?, participant_max = ?, participant_num = ? "
				+ "where tourney_id = ?";
		
		jdbcTemplate.update(sql, updatedTourney.getTourneyName(), updatedTourney.getTourneyDesc(), findHostByUsername(updatedTourney.getTourneyHost()),
				updatedTourney.getStartDate(), updatedTourney.getEndDate(), updatedTourney.isActive(), updatedTourney.isOpenForReg(), updatedTourney.getMaxNumOfParticipants(),
				updatedTourney.getNumOfParticpants(), updatedTourney.getTourneyId());
		
	}

	@Override
	public void deleteATourney(long id) {
		
		String deleteSql = "delete from tournaments where tourney_id = ?";
		
		jdbcTemplate.update(deleteSql, id);
	}

	@Override
	public List<Tourneys> getTourneysByDate() { //Filtering will be done on FrontEnd side
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateATourneyName() { // Yes
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateATourneyDesc() { // Yes
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long updateStartDate() { // Yes
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long updateEndDate() { // Yes
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateStatus() { // yes
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int changeNumOfParticipants() { // Yes
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Long updateStartTime() { // Yes
		// TODO Auto-generated method stub
		return null;
	}
	
	private Tourneys mapRowToTourneys(SqlRowSet results) {
		Tourneys tourneysRow = new Tourneys();
		tourneysRow.setTourneyId  (results.getLong("tourney_id"));
		tourneysRow.setTourneyName(results.getString("tourney_name"));
		tourneysRow.setTourneyDesc(results.getString("tourney_desc"));
		tourneysRow.setTourneyHost(results.getString("tourney_host"));
		tourneysRow.setStartDate (results.getDate("start_date").toLocalDate());
		tourneysRow.setEndDate(results.getDate("end_date").toLocalDate());
		tourneysRow.setActive(results.getBoolean("tourney_is_active"));
		tourneysRow.setOpenForReg(results.getBoolean("open_for_reg"));
		tourneysRow.setNumOfParticpants(results.getInt("participant_num"));
		tourneysRow.setMaxNumOfParticipants(results.getInt("participant_max"));
		return tourneysRow;
		
	}
	
	private long findHostByUsername(String username) {
		String subSql = "select user_id from users where username = ?";
		
		SqlRowSet subQuery = jdbcTemplate.queryForRowSet(subSql, username);
		subQuery.next();
		
		int id = subQuery.getInt("user_id");
		return id;
	}

	


	
}