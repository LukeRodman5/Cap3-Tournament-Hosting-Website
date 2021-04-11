package com.techelevator.application.dao;
import java.util.List;
import com.techelevator.application.model.Users;

public interface UsersDAO {
	
	List<Users> getAllUsers();
	List<Users> getUserByUsername(String username);
	List<Users> getUserByUserId(long userId);
	List<Users> getAllUsersInATourney(int tourneyID);
	String getUsernameByTourneyId(long tourneyID);
	String getUsernameByUserID (long userID);
	void createAUser(long userId, String username); 
	void deleteAUser(long userId, String username);
	void updateAUser(long userId, String username);
		void addUser(long userId, String username);
		
}
