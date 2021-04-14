package com.techelevator.application.jdbcdao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.application.dao.UsersDAO;
import com.techelevator.application.model.Users;	
	
@Component
	public class JDBCUsersDAO implements UsersDAO{
	private JdbcTemplate jdbcTemplate;
	public JDBCUsersDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		}

@Override	/* Retrieve all users from database */
public List<Users> getAllUsers() {
	   List<Users>listOfUsers = new ArrayList<>();
	   String sql = "select user_id, username from users";
	
	  SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			Users user = mapRowToUsers(results);
			listOfUsers.add(user);
			}	
		return listOfUsers;
		}

/* Retrieve all users by username */
private Users mapRowToUsers(SqlRowSet results) {
	Users usersRow = new Users();
	usersRow.setUserId(results.getLong("user_id"));
	usersRow.setUsername(results.getString("username"));
		return usersRow;
		}

// Get a list of all users currently in a tournament by tourneyID
@Override
public List<Users> getAllUsersInATourney(int tourneyId) {
	   List<Users>listOfUsers = new ArrayList<>();
	   String sql = "select username, users.user_id "
			   + "from users inner join users_tournaments "
			   + "on users.user_id = users_tournaments.user_id "
			   + "where tourney_id = ?";
	
	SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tourneyId);
		while(results.next()) {
			Users user = new Users();
			user.setUserId(results.getLong("user_id"));
			user.setUsername(results.getString("username"));
			listOfUsers.add(user);}	
		return listOfUsers;}


@Override
public String getUsernameByTourneyId(long tourneyId) {
	   String sql = "select username "
			   + "from users "
			   + "inner join tournaments "
			   + "on tournaments.tourney_host = users.user_id "
			   + "where tourney_id = ?";

       SqlRowSet hostUser = jdbcTemplate.queryForRowSet(sql, tourneyId);
    return hostUser.toString();}


@Override
public String getUsernameByUserID(long userId) {
	   String sql = "select username from users where user_id = ?";
	   SqlRowSet user = jdbcTemplate.queryForRowSet(sql, userId);
	return user.toString();
	}

@Override
public List<Users> getUserByUserId(long userId) {
		// TODO Auto-generated method stub
	return null;
	}

@Override
public void createAUser(long userId, String username) {
		// TODO Auto-generated method stub
	}

@Override
public void deleteAUser(long userId, String username) {
		// TODO Auto-generated method stub
	}

@Override
public void updateAUser(long userId, String username) {
		// TODO Auto-generated method stub
}

@Override
public void addUser(long userId, String username) {
		// TODO Auto-generated method stub
}

@Override
public List<Users> getUserByUsername(String username) {
		// TODO Auto-generated method stub
	return null;
	}
}
