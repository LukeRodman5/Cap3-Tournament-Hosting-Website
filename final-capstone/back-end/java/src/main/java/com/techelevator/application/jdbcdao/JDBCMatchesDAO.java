package com.techelevator.application.jdbcdao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.application.dao.MatchesDAO;
import com.techelevator.application.model.Matches;

@Component
public class JDBCMatchesDAO implements MatchesDAO {
	 private JdbcTemplate jdbcTemplate;
	 public JDBCMatchesDAO(JdbcTemplate jdbcTemplate) {
		 	this.jdbcTemplate = jdbcTemplate;
		 	}

	@Override
	 public List<Matches> getAllMatches() { // update to by tourneyId
			List<Matches>listOfMatches = new ArrayList<>();
			String sql = "select * from matches"; SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		 while(results.next()) {
			Matches aMatch = mapRowToMatches(results);
				listOfMatches.add(aMatch);
				}
		 return listOfMatches;
		 }

	@Override
	 public List<Matches> getMatchById(int matchId) { // needs it by user id not match id
			List<Matches>listOfMatches = new ArrayList<>();
			String sql = "select * from matches where match_Id = ?"; SqlRowSet results = jdbcTemplate.queryForRowSet(sql, matchId);
		while(results.next()) {
			Matches aMatch = mapRowToMatches(results);
				listOfMatches.add(aMatch);
				}
		 return listOfMatches;
		 }
	
	@Override
	public List<Matches> getAllMatchesInATourney(long tourneyId) {
		   List<Matches>listOfMatches = new ArrayList<>();
		   String sql = "select matches.match_id, round_level from matches inner join tournaments_matches on tournaments_matches.match_id = matches.match_id where tourney_id = ? order by round_level, matches.match_id";
		   SqlRowSet results =jdbcTemplate.queryForRowSet(sql, tourneyId);
		while(results.next()) {
			Matches aMatch = new Matches();
			aMatch.setMatchId(results.getLong("match_id"));
			aMatch.setRoundLevel(results.getInt("round_level"));
				listOfMatches.add(aMatch);
				}
		 return listOfMatches;
		 }

	@Override
	 public int createAMatch(Matches newMatch, long tourneyId) { // needs specified
			String sql = "insert into matches (start_time, start_date, round_level) values (?, ?, ?)"; 
			jdbcTemplate.update(sql, newMatch.getStartTime(), newMatch.getStartDate(), newMatch.getRoundLevel());
			
			String secondSql = "insert into tournaments_matches (tourney_id, match_id) values (?,(select currval('matches_match_id_seq')))";
			jdbcTemplate.update(secondSql, tourneyId);
			
			String newSql = "select currval('matches_match_id_seq')"; 
			SqlRowSet result = jdbcTemplate.queryForRowSet(newSql);
				if(result.next()) {
					return result.getInt(1);
				}
				return 0;
		
		}
		
	@Override
	 public void updateAMatch(LocalDate startDate, LocalDate startTime, int matchId) {
			String sql = "update matches set where match_id = ?, start_time = ?, start_date = ?";
			jdbcTemplate.update(sql, startDate, startTime, matchId);
			}
	
	@Override
	 public void deleteAMatch(int matchId) { 							
			String sql = "delete from matches where match_id = ?";
			jdbcTemplate.update(sql, matchId);
			}
	
	@Override
	 public void addUserToMatch(String username, int matchID) {
			String sql = "insert into users_matches (user_id, match_id, win_status) values ((select user_id from users where username = ?), ?, null)";
			jdbcTemplate.update(sql, username, matchID);
			}
	
	@Override
	 public void updateUserToDiffMatch(int newMatchId, String username, int currentMatchId) {
			String sql = "update users_matches set match_id = ? where user_id = (select user_id from users where username = ?) and match_id = ?";
			jdbcTemplate.update(sql, newMatchId, username, currentMatchId);
			}
	
	@Override
	public void removeUserFromMatch(String username, long matchID) {
			String sql = "delete from users_matches where user_id = (select user_id from users where username = ?) and match_id = ?";
			jdbcTemplate.update(sql, username, matchID);
			}
	
	@Override
	public void addAMatchToTourney(long tourneyId, long matchId) {
			String sql = "insert into tournaments_matches (tourney_id, match_id) values (?,?)";
			jdbcTemplate.update(sql, tourneyId, matchId);
			}
	
	@Override
	public void removeMatchFromTourney(long tourneyId, long matchId) {
			String sql = "delete from tournaments_matches where tourney_id = ? and match_id = ?";
			jdbcTemplate.update(sql, tourneyId, matchId);
			}

	@Override
	 public List<Matches> getMatchesByDate() {
			// TODO Auto-generated method stub
			return null;
			}

	@Override
	 public String updateAMatchId() { // not certain if needed, but definitely need to be able to update
			// TODO Auto-generated method stub
			return null;
			}

	@Override
	 public Long updateStartDate() { // needs suggestion to add a match update, date, time, all together as one match item to send the full object
			// TODO Auto-generated method stub
			return null;
			}

	@Override
	 public Long updateStartTime() { // see above notes
			// TODO Auto-generated method stub
			return null;
			}
	
	private Matches mapRowToMatches(SqlRowSet results) {
		Matches matchRow = new Matches();
				matchRow.setMatchId(results.getLong("match_id"));
				matchRow.setStartDate(results.getDate("start_date").toLocalDate());
				matchRow.setStartTime(results.getDate("start_time").toLocalDate());
				matchRow.setRoundLevel(results.getInt("round_level"));
		 return matchRow;
		 }

	
}
