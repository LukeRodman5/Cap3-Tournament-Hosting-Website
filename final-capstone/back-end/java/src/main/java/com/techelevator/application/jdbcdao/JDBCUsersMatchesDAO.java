package com.techelevator.application.jdbcdao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.techelevator.application.dao.UsersMatchesDAO;

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

}
