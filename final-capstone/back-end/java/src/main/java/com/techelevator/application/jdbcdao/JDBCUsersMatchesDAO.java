package com.techelevator.application.jdbcdao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.application.dao.UsersMatchesDAO;
import com.techelevator.application.model.Matches;
import com.techelevator.application.model.UsersMatches;

@Component
public class JDBCUsersMatchesDAO implements UsersMatchesDAO {
	private JdbcTemplate jdbcTemplate;
	public JDBCUsersMatchesDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public void updateUserMatches(boolean winStatus, int userID, int matchID) {
		String sql = "update users_matches set win_status = ? where user_id = ? and match_id = ?";
		jdbcTemplate.update(sql, winStatus, userID, matchID);
		
	}
	
	@Override
	 public void addUserToMatch(String username, int matchID, int playerNum) {
			String sql = "insert into users_matches (user_id, match_id, win_status, player_num) "
					   + "values ((select user_id from users where username = ?), ?, null, ?)";
			jdbcTemplate.update(sql, username, matchID);
			}
	
	@Override
	public List<UsersMatches> getAllMatchesInUMByTourneyID(long tourneyID) {
		List<UsersMatches>listOfUsersMatches = new ArrayList<>();
		String sql = "select user_id, users_matches.match_id, win_status "
				+ "from users_matches "
				+ "inner join matches "
				+ "on matches.match_id = users_matches.match_id "
				+ "inner join tournaments_matches "
				+ "on matches.match_id = tournaments_matches.match_id "
				+ "where tourney_id = ?";
			SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tourneyID);
			while(results.next()) {
				UsersMatches aUserMatch = new UsersMatches();
				aUserMatch.setUserId(results.getInt("user_id"));
				aUserMatch.setMatchId(results.getInt("match_id"));
				aUserMatch.setWinStatus(results.getBoolean("win_status"));
				listOfUsersMatches.add(aUserMatch);
					}
			 return listOfUsersMatches;
			}
		
	

}
