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

	
	// Retrieve all tournaments from database
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
	
	// Retrieve all tournaments from database by username
	@Override
	public List<Tourneys> getTourneysByName(String username) {
		List<Tourneys>listOfTourneys = new ArrayList<>();
		String sql = "select * from tournaments "
				+ "inner join users_tournaments "
				+ "on tournaments.tourney_id = users_tournaments.tourney_id "
				+ "where user_id = (select user_id from users where username = ?)";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username.toLowerCase());
			while(results.next()) {
				Tourneys tourney = mapRowToTourneys(results);
				listOfTourneys.add(tourney);
			}	
		return listOfTourneys;
	}
	
	// Retrieve all tournaments from database by Host ID
	@Override
	public List<Tourneys> getTourneysByHostID(int hostID) {
		List<Tourneys>listOfTourneys = new ArrayList<>();
		String sql = "select * from tournaments where tourney_host = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, hostID);
			while(results.next()) {
				Tourneys tourney = mapRowToTourneys(results);
				listOfTourneys.add(tourney);
			}	
		return listOfTourneys;
	}
	
	// Get all users in a tournament
		@Override
		public List<Tourneys> getAllUsersInATourney(int tourneyID) {
			List<Tourneys>listOfTourneys = new ArrayList<>();
			String sql = "select username, users.user_id "
					   + "from users inner join users_tournaments "
					   + "on users.user_id = users_tournaments.user_id "
					   + "where tourney_id = ?";
			
			SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tourneyID);
				while(results.next()) {
					Tourneys tourney = mapRowToTourneys(results);
					listOfTourneys.add(tourney);
				}	
			return listOfTourneys;
			
		}
	
	// Create a new tournament
	@Override                                                                                                            
	public void createATourney(String name, String description, String host, LocalDate startDate, LocalDate endDate, 
			                   boolean tourneyIsActive, boolean regIsActive, int maxNumberOfParticipants, int numberOfParticipants) {
		
		String subSql = "select user_id from users where username = ?";
		
		SqlRowSet subQuery = jdbcTemplate.queryForRowSet(subSql, host);
		subQuery.next();
		
		long id = subQuery.getInt("user_id");
		
		String sql = "insert into tournaments (tourney_name, tourney_desc, tourney_host, start_date, end_date, tourney_is_active, "
				   + "open_for_reg, participant_max, participant_num) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(sql, name, description, id, startDate, endDate, tourneyIsActive, regIsActive, maxNumberOfParticipants, numberOfParticipants);
		
	}
		
	// Update a tournament
	@Override
	public void updateATourney(Tourneys updatedTourney) {
		String sql = "update tournaments set tourney_name = ?, tourney_desc = ?,  tourney_host = ?, "
				+ "start_date = ?, end_date = ?, tourney_is_active = ?,"
				+ "open_for_reg = ?, participant_max = ?, participant_num = ? "
				+ "where tourney_id = ?";
		
		jdbcTemplate.update(sql, updatedTourney.getTourneyName(), updatedTourney.getTourneyDesc(), findHostByUsername(updatedTourney.getTourneyHost()),
				updatedTourney.getStartDate(), updatedTourney.getEndDate(), updatedTourney.isActive(), updatedTourney.isOpenForReg(), updatedTourney.getMaxNumOfParticipants(),
				updatedTourney.getNumOfParticipants(), updatedTourney.getTourneyId());
		
	}
	
	// Delete a tournament
	@Override
	public void deleteATourney(long id) {
		
		String deleteSql = "delete from tournaments where tourney_id = ?";
		
		jdbcTemplate.update(deleteSql, id);
	}
	
	// Get a tournament by tourney ID
	@Override
	public Tourneys getATourneyById(long id) {
		String sql = "select * from tournaments where tourney_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		if(results.next()) {
			return mapRowToTourneys(results);
		} else {
			return null;
		}
	}
	
	// Add a user to a tournament
	@Override
	public void addUserToTourney(String username, int tourneyID) {
		String sql = "insert into users_tournaments (user_id, tourney_id) values ((select user_id from users where username = ?), ?)";
		
		jdbcTemplate.update(sql, username, tourneyID);
		
	}
	
	// Update a user from their current tournament to another
	@Override
	public void updateUserTourney(String username, int newTourneyID, int currentTourneyID) {
		String sql = "update users_tournament set tourney_id = ? where user_id = (select user_id from users where username = ? and tourney_id = ?";
		
		jdbcTemplate.update(sql, newTourneyID, username, currentTourneyID);
	}
	
	// Remove a user from a tournament
	@Override
	public void removeUserFromTourney(String username, int tourneyID) {
		String sql = "delete from users_tournaments "
				+ "where user_id = (select user_id from users where username = ?) "
				+ "and tourney_id = ?";
		jdbcTemplate.update(sql, username, tourneyID);
		
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
		tourneysRow.setNumOfParticipants(results.getInt("participant_num"));
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