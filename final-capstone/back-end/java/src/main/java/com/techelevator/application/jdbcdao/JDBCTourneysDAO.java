package com.techelevator.application.jdbcdao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.application.dao.TourneysDAO;
import com.techelevator.application.model.Tourneys;

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
	public List<Tourneys> getTourneysByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tourneys createATourney() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tourneys deleteATourney() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tourneys> getTourneysByDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateATourneyName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateATourneyDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long updateStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long updateEndDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int changeNumOfParticipants() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Long updateStartTime() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Tourneys mapRowToTourneys(SqlRowSet results) {
		Tourneys tourneysRow = new Tourneys();
		tourneysRow.setTourneyId  (results.getLong("tourney_id"));
		tourneysRow.setTourneyName(results.getString("tourney_name"));
		tourneysRow.setTourneyDesc(results.getString("tourney_desc"));
		tourneysRow.setTourneyHost(results.getInt("tourney_host"));
		tourneysRow.setStartDate (results.getDate("start_date").toLocalDate());
		tourneysRow.setEndDate(results.getDate("end_date").toLocalDate());
		tourneysRow.setActive(results.getBoolean("tourney_is_active"));
		tourneysRow.setOpenForReg(results.getBoolean("participant_num"));
		
		return tourneysRow;
		
	}
	
}