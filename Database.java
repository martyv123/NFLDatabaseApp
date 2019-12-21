import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.ResultSet;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for connecting to database and performing necessary setup.
 */
public class Database {

	/** The name of the MySQL account to use (or empty for anonymous) */
	private String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private String password = "root";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "nflprostats";

	/** The name of the table we are testing with */
	private final String tableName = "JDBC_TEST";

	/**
	 * Sets user name and password of the DB connection
	 * @param username, password
	 */
	public void setUsernamePassword(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);

		return conn;
	}

	/**
	 * Gets the head coaches from the database.
	 * @return coaches
	 */
	public ArrayList<String> getCoaches() {
		ArrayList coaches = new ArrayList<String>();
		try {
			Connection conn = this.getConnection();
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT coach_id, coach_name FROM head_coach");
			while (result.next()) {
				coaches.add("Coach ID: " + result.getString("coach_id") + ", " + result.getString("coach_name"));
			}
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
		return coaches;
	}
	
	/**
	 * Gets the matches from the database.
	 * @return matches
	 */
	public ArrayList<String> getMatches() {
		ArrayList matches = new ArrayList<String>();
		try {
			Connection conn = this.getConnection();
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT match_number, week, home_team, home_points, away_team, away_points FROM nfl_match");
			while (result.next()) {
				matches.add("match: " + result.getString("match_number") + ", " + "week: " + result.getString("week") 
				+ ", " + result.getString("home_team") + ", points: " + result.getString("home_points") + ", " 
						+ result.getString("away_team") + ", points: " + result.getString("away_points"));
			}
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
		return matches;
	}
	
	/**
	 * Gets the team names from the database.
	 * @return teams
	 */
	public ArrayList<String> getTeams() {
		ArrayList teams = new ArrayList<String>();
		try {
			Connection conn = this.getConnection();
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT team_name FROM team");
			while (result.next()) {
				teams.add(result.getString("team_name"));
			}
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
		return teams;
	}

	/**
	 * Gets the player names from the database.
	 * @return players
	 */
	public ArrayList<String> getPlayers() {
		ArrayList players = new ArrayList<String>();
		try {
			Connection conn = this.getConnection();
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT id, first_name, last_name FROM player");
			while (result.next()) {
				players.add("ID: " + result.getString("id") + ", " + result.getString("first_name") + " " + result.getString("last_name"));
			}
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
		return players;
	}
	
	/**
	 * Gets the player offensive stats from the database.
	 * @param playerID
	 * @return players
	 */
	public ArrayList<String> getOffStats(int playerID) {
		ArrayList offStats = new ArrayList<String>();
		try {
			Connection conn = this.getConnection();
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT passing_yards, rushing_yards, receiving_yards FROM player_stats_off WHERE player_id = " + playerID);
			while (result.next()) {
				offStats.add("passing yards: " + result.getString("passing_yards") + ", receiving yards: " + result.getString("receiving_yards") 
				+ ", rushing yards: " + result.getString("rushing_yards"));
			}
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
		return offStats;
	}
	
	/**
	 * Gets the player defensive stats from the database.
	 * @param playerID
	 * @return players
	 */
	public ArrayList<String> getDefStats(int playerID) {
		ArrayList defStats = new ArrayList<String>();
		try {
			Connection conn = this.getConnection();
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT tackles, interceptions, sacks FROM player_stats_def WHERE player_id = " + playerID);
			while (result.next()) {
				defStats.add("tackles: " + result.getString("tackles") + ", interceptions: " + result.getString("interceptions") 
				+ ", sacks: " + result.getString("sacks"));
			}
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
		return defStats;
	}
	
	/**
	 * Gets the player match stats from the database.
	 * @param matchID
	 * @return matchStats
	 */
	public ArrayList<String> getMatchStats(int matchID) {
		ArrayList matchStats = new ArrayList<String>();
		try {
			Connection conn = this.getConnection();
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT player_id, team, passing_yards, rushing_yards, receiving_yards, tackles, interceptions,"
					+ "sacks FROM match_player_stats WHERE match_number = " + matchID);
			while (result.next()) {
				matchStats.add("player ID: " + result.getString("player_id") + ", team: " + result.getString("team") + ", passing yards: " 
			+ result.getString("passing_yards") + ", receiving yards: " + result.getString("receiving_yards") + ", rushing yards: " + result.getString("rushing_yards") + ", "
			+ "tackles: " + result.getString("tackles") + ", interceptions: " + result.getString("interceptions") + ", sacks: " + result.getString("sacks"));
			}
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
		return matchStats;
	}

	/**
	 * Adds a match to the matches table
	 * @param match_number, week, home_team, away_team, home_points, away_points, home_pass_yards,
	    			away_pass_yards, home_run_yards, away_run_yards
	 */
	public void addMatch(int match_number, int week, String home_team, String away_team, int home_points, int away_points,
			int home_pass_yards, int away_pass_yards, int home_run_yards, int away_run_yards) {
		String sql = "INSERT INTO nfl_match VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = null;
		try {
			Connection conn = this.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, match_number);
			stmt.setInt(2, week);
			stmt.setString(3, home_team);
			stmt.setString(4, away_team);
			stmt.setInt(5, home_points);
			stmt.setInt(6, away_points);
			stmt.setInt(7, home_pass_yards);
			stmt.setInt(8, away_pass_yards);
			stmt.setInt(9, home_run_yards);
			stmt.setInt(10, away_run_yards);

			stmt.executeUpdate(); // This will throw a SQLException if it fails
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Adds a team to the team table
	 * @param team_name, stadium, wins, losses, tot_points_scored, tot_points_allowed, tot_pass_yards, tot_run_yards, tot_pass_yards_conceded, 
							tot_run_yards_conceded, championships_won, superbowls_won
	 */
	public void addTeam(String team_name, String stadium, int wins, int losses, double tot_points_scored, double tot_points_allowed, double tot_pass_yards,
			double tot_run_yards, double tot_pass_yards_conceded, double tot_run_yards_conceded, int championships_won, int superbowls_won) {
		String sql = "INSERT INTO team VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = null;
		try {
			Connection conn = this.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, team_name);
			stmt.setString(2, stadium);
			stmt.setInt(3, wins);
			stmt.setInt(4, losses);
			stmt.setDouble(5, tot_points_scored);
			stmt.setDouble(6, tot_points_allowed);
			stmt.setDouble(7, tot_pass_yards);
			stmt.setDouble(8, tot_run_yards);
			stmt.setDouble(9, tot_pass_yards_conceded);
			stmt.setDouble(10, tot_run_yards_conceded);
			stmt.setInt(11, championships_won);
			stmt.setInt(12, superbowls_won);

			stmt.executeUpdate(); // This will throw a SQLException if it fails
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Adds a head coach to the head_coach table
	 * @param coach_id, coach_name, years_experience, current_team, win_loss_ratio, championships_won, superbowls_won
	 */
	public void addCoach(int coach_id, String coach_name, int years_experience, String current_team, double win_loss_ratio, int championships_won, int superbowls_won) {
		String sql = "INSERT INTO head_coach VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = null;
		try {
			Connection conn = this.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, coach_id);
			stmt.setString(2, coach_name);
			stmt.setInt(3, years_experience);
			stmt.setString(4, current_team);
			stmt.setDouble(5, win_loss_ratio);
			stmt.setInt(6, championships_won);
			stmt.setInt(7, superbowls_won);

			stmt.executeUpdate(); // This will throw a SQLException if it fails
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Adds a player to the player table
	 * @param id, player_number, first_name, last_name, position, current_team, games_so_far, years_experience, rating, championships_won, superbowls_won
	 */
	public void addPlayer(int id, int player_number, String first_name, String last_name, String position, String current_team, int games_so_far, 
			int years_experience, double rating, int championships_won, int superbowls_won) {
		String sql = "INSERT INTO player VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = null;
		try {
			Connection conn = this.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.setInt(2, player_number);
			stmt.setString(3, first_name);
			stmt.setString(4, last_name);
			stmt.setString(5, position);
			stmt.setString(6, current_team);
			stmt.setInt(7, games_so_far);
			stmt.setInt(8, years_experience);
			stmt.setDouble(9, rating);
			stmt.setInt(10, championships_won);
			stmt.setInt(11, superbowls_won);

			stmt.executeUpdate(); // This will throw a SQLException if it fails
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Adds a player's stats from a match into match_player_stats table
	 * @param player_id, team, match_number, passing_yards, rushing_yards, receiving_yards, tackles, sacks, interceptions
	 */
	public void addPlayerMatchStats(int player_id, String team, int match_number, int passing_yards, int rushing_yards, int receiving_yards,
			int tackles, int sacks, int interceptions) {
		String sql = "INSERT INTO match_player_stats VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = null;
		try {
			Connection conn = this.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, player_id);
			stmt.setString(2, team);
			stmt.setInt(3, match_number);
			stmt.setInt(4, passing_yards);
			stmt.setInt(5, rushing_yards);
			stmt.setInt(6, receiving_yards);
			stmt.setInt(7, tackles);
			stmt.setInt(8, sacks);
			stmt.setInt(9, interceptions);

			stmt.executeUpdate(); // This will throw a SQLException if it fails
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Deletes a team from the team table
	 * @param team_name
	 */
	public void deleteTeam(String team_name) {
		String sql = "DELETE FROM team WHERE team_name = ?";
		PreparedStatement stmt = null;
		try {
			Connection conn = this.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, team_name);
			stmt.executeUpdate(); // This will throw a SQLException if it fails
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Deletes a coach from the head_coach table
	 * @param coach_id
	 */
	public void deleteCoach(int coach_id) {
		String sql = "DELETE FROM head_coach WHERE coach_id = ?";
		PreparedStatement stmt = null;
		try {
			Connection conn = this.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, coach_id);
			stmt.executeUpdate(); // This will throw a SQLException if it fails
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Deletes a player from the player table
	 * @param id
	 */
	public void deletePlayer(int id) {
		String sql = "DELETE FROM player WHERE id = ?";
		PreparedStatement stmt = null;
		try {
			Connection conn = this.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate(); // This will throw a SQLException if it fails
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Updates a team in the team table
	 * @param team_name, stadium, wins, losses, tot_points_scored, tot_points_allowed, tot_pass_yards, tot_run_yards, tot_pass_yards_conceded, 
							tot_run_yards_conceded, championships_won, superbowls_won
	 */
	public void updateTeam(String team_name, String stadium, int wins, int losses, double tot_points_scored, double tot_points_allowed, double tot_pass_yards,
			double tot_run_yards, double tot_pass_yards_conceded, double tot_run_yards_conceded, int championships_won, int superbowls_won) {
		String sql = "UPDATE team SET stadium = ?, wins = ?, losses = ?, tot_points_scored = ?, tot_points_allowed = ?, tot_pass_yards = ?, tot_run_yards = ?,"
				+ "tot_pass_yards_conceded = ?, tot_run_yards_conceded = ?, championships_won = ?, superbowls_won = ? WHERE team_name = ?";
		PreparedStatement stmt = null;
		try {
			Connection conn = this.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, stadium);
			stmt.setInt(2, wins);
			stmt.setInt(3, losses);
			stmt.setDouble(4, tot_points_scored);
			stmt.setDouble(5, tot_points_allowed);
			stmt.setDouble(6, tot_pass_yards);
			stmt.setDouble(7, tot_run_yards);
			stmt.setDouble(8, tot_pass_yards_conceded);
			stmt.setDouble(9, tot_run_yards_conceded);
			stmt.setInt(10, championships_won);
			stmt.setInt(11, superbowls_won);
			stmt.setString(12, team_name);
			stmt.executeUpdate(); // This will throw a SQLException if it fails
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
		
		/**
		 * Update a coach in the head_coach table
		 * @param coach_id, coach_name, years_experience, current_team, win_loss_ratio, championships_won, superbowls_won
		 */
		public void updateCoach(int coach_id, String coach_name, int years_experience, String current_team, double win_loss_ratio, int championships_won, int superbowls_won) {
		String sql = "UPDATE head_coach SET coach_name = ?, years_experience = ?, current_team = ?, win_loss_ratio = ?, championships_won = ?, superbowls_won = ? "
				+ "WHERE coach_id = ?";
		PreparedStatement stmt = null;
		try {
			Connection conn = this.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, coach_name);
			stmt.setInt(2, years_experience);
			stmt.setString(3, current_team);
			stmt.setDouble(4, win_loss_ratio);
			stmt.setInt(5, championships_won);
			stmt.setInt(6, superbowls_won);
			stmt.setInt(7, coach_id);

			stmt.executeUpdate(); // This will throw a SQLException if it fails
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
		/**
		 * Update a player in the player table
		 * @param id, player_number, first_name, last_name, position, current_team, games_so_far, years_experience, rating, championships_won, superbowls_won
	 */
	public void updatePlayer(int id, int player_number, String first_name, String last_name, String position, String current_team, int games_so_far, 
			int years_experience, double rating, int championships_won, int superbowls_won) {
		String sql = "UPDATE player SET player_number = ?, first_name = ?, last_Name = ?, position = ?, current_team = ?, games_so_far = ?, years_experience = ?, "
				+ "rating = ?, championships_won = ?, superbowls_won = ? WHERE id = ?";
		PreparedStatement stmt = null;
		try {
			Connection conn = this.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, player_number);
			stmt.setString(2, first_name);
			stmt.setString(3, last_name);
			stmt.setString(4, position);
			stmt.setString(5, current_team);
			stmt.setInt(6, games_so_far);
			stmt.setInt(7, years_experience);
			stmt.setDouble(8, rating);
			stmt.setInt(9, championships_won);
			stmt.setInt(10, superbowls_won);
			stmt.setInt(11, id);

			stmt.executeUpdate(); // This will throw a SQLException if it fails
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Updates a match in the nfl_match table
	 * @param match_number, week, home_team, away_team, home_points, away_points, home_pass_yards,
	    			away_pass_yards, home_run_yards, away_run_yards
	 */
	public void updateMatch(int match_number, int week, String home_team, String away_team, int home_points, int away_points,
			int home_pass_yards, int away_pass_yards, int home_run_yards, int away_run_yards) {
		String sql = "UPDATE NFL_match SET week = ?, home_team = ?, away_team = ?, home_points = ?, away_points = ?, home_pass_yards = ?, away_pass_yards = ?, "
				+ "home_run_yards = ?, away_run_yards = ? WHERE match_number = ?";
		PreparedStatement stmt = null;
		try {
			Connection conn = this.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, week);
			stmt.setString(2, home_team);
			stmt.setString(3, away_team);
			stmt.setInt(4, home_points);
			stmt.setInt(5, away_points);
			stmt.setInt(6, home_pass_yards);
			stmt.setInt(7, away_pass_yards);
			stmt.setInt(8, home_run_yards);
			stmt.setInt(9, away_run_yards);
			stmt.setInt(10, match_number);

			stmt.executeUpdate(); // This will throw a SQLException if it fails
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Updates a player's stats from a match in the match_player_stats table
	 * @param player_id, team, match_number, passing_yards, rushing_yards, receiving_yards, tackles, sacks, interceptions
	 */
	public void updatePlayerMatchStats(int player_id, String team, int match_number, int passing_yards, int rushing_yards, int receiving_yards,
			int tackles, int sacks, int interceptions) {
		String sql = "UPDATE match_player_stats SET team = ?, passing_yards = ?, rushing_yards = ?, receiving_yards = ?, tackles = ?, sacks = ?, interceptions = ? "
				+ "WHERE player_id = ? and match_number = ?";
		PreparedStatement stmt = null;
		try {
			Connection conn = this.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, team);
			stmt.setInt(2, passing_yards);
			stmt.setInt(3, rushing_yards);
			stmt.setInt(4, receiving_yards);
			stmt.setInt(5, tackles);
			stmt.setInt(6, sacks);
			stmt.setInt(7, interceptions);
			stmt.setInt(8, player_id);
			stmt.setInt(9, match_number);

			stmt.executeUpdate(); // This will throw a SQLException if it fails
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the average passing yards for a team
	 * @param team_name
	 */
	public void displayAvgPassTeam(String team_name) {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL avg_pass_team(?)");
			statement.setString(1, team_name);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append(result.getString("AvgPassYardsPerGame"));
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the average passing yards conceded for a team
	 * @param team_name
	 */
	public void displayAvgPassTeamConceded(String team_name) {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL avg_pass_conceded_team(?)");
			statement.setString(1, team_name);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append(result.getString("AvgPassYardsConcededPerGame"));
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the average rushing yards for a team
	 * @param team_name
	 */
	public void displayAvgRunTeam(String team_name) {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL avg_run_team(?)");
			statement.setString(1, team_name);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append(result.getString("AvgRunYardsPerGame"));
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the average rushing yards conceded for a team
	 * @param team_name
	 */
	public void displayAvgRunConcededTeam(String team_name) {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL avg_run_conceded_team(?)");
			statement.setString(1, team_name);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append(result.getString("AvgRunYardsConcededPerGame"));
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the average points per game for a team
	 * @param team_name
	 */
	public void displayAvgPointsTeam(String team_name) {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL avg_points_team(?)");
			statement.setString(1, team_name);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append(result.getString("AvgPointsPerGame"));
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the average points conceded per game for a team
	 * @param team_name
	 */
	public void displayAvgPointsConcededTeam(String team_name) {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL avg_points_conceded_team(?)");
			statement.setString(1, team_name);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append(result.getString("AvgPointsConcededPerGame"));
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the top 5 teams by wins
	 */
	public void displayTopTeamsByWins() {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL top_5_wins_team()");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append(result.getString("team_name") + ", wins: " + result.getString("Wins"));
				procedure.append("\n");
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the bottom 5 teams by losses
	 * @param team_name
	 */
	public void displayBottomTeamsByLosses() {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL top_5_losses_team()");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append(result.getString("team_name") + ", losses: " + result.getString("Losses"));
				procedure.append("\n");
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the top 5 teams by win/loss ration
	 * @param team_name
	 */
	public void displayTopTeamsByWinLoss() {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL top_5_winlosspct_team()");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append(result.getString("team_name") + ", Win Percentage: " + result.getString("WinPercentage"));
				procedure.append("\n");
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the top 5 teams by most points scored
	 */
	public void displayTopTeamsByMostPoints() {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL top_5_points_team()");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append(result.getString("team_name") + ", Total Points Scored: " + result.getString("TotalPointsScored"));
				procedure.append("\n");
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the top 5 teams by fewest points allowed
	 */
	public void displayTopTeamsByFewestPointsAllowed() {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL top_5_points_allowed_team()");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append(result.getString("team_name") + ", Total Points Allowed: " + result.getString("TotalPointsAllowed"));
				procedure.append("\n");
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the top 5 teams by most passing yards
	 */
	public void displayTopTeamsByMostPassingYards() {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL top_5_pass_team()");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append(result.getString("team_name") + ", Total Passing Points: " + result.getString("TotalPassScored"));
				procedure.append("\n");
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the top 5 teams by fewest passing yards allowed
	 */
	public void displayTopTeamsByFewestPassingYardsAllowed() {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL top_5_pass_yards_conceded_team()");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append(result.getString("team_name") + ", Total Passing Points Allowed: " + result.getString("TotalPassAllowed"));
				procedure.append("\n");
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the top 5 teams by most rushing yards
	 */
	public void displayTopTeamsByMostRushingYards() {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL top_5_run_team()");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append(result.getString("team_name") + ", Total Rushing Points: " + result.getString("TotalRunScored"));
				procedure.append("\n");
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the top 5 teams by fewest rushing yards allowed
	 */
	public void displayTopTeamsByFewestRushingYardsAllowed() {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL top_5_run_yards_conceded_team()");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append(result.getString("team_name") + ", Total Passing Points Allowed: " + result.getString("TotalRunAllowed"));
				procedure.append("\n");
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the average passing yards per game for a player
	 * @param fname, lname, num, team
	 */
	public void displayAvgPassPlayer(String fname, String lname, int num, String team) {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL avg_pass_yards_player(?, ?, ?, ?)");
			statement.setString(1, fname);
			statement.setString(2, lname);
			statement.setInt(3, num);
			statement.setString(4, team);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append("Player name: " + result.getString("first_name") + " " + result.getString("last_name") + ", Average Passing Yards: "
						+ result.getString("YardsPerGame"));
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the average receiving yards per game for a player
	 * @param fname, lname, num, team
	 */
	public void displayAvgReceivePlayer(String fname, String lname, int num, String team) {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL avg_rec_yards_player(?, ?, ?, ?)");
			statement.setString(1, fname);
			statement.setString(2, lname);
			statement.setInt(3, num);
			statement.setString(4, team);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append("Player name: " + result.getString("first_name") + " " + result.getString("last_name") + ", Average Receiving Yards: "
						+ result.getString("YardsPerGame"));
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the average rushing yards per game for a player
	 * @param fname, lname, num, team
	 */
	public void displayAvgRushPlayer(String fname, String lname, int num, String team) {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL avg_run_yards_player(?, ?, ?, ?)");
			statement.setString(1, fname);
			statement.setString(2, lname);
			statement.setInt(3, num);
			statement.setString(4, team);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append("Player name: " + result.getString("first_name") + " " + result.getString("last_name") + ", Average Rushing Yards: "
						+ result.getString("YardsPerGame"));
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the average tackles per game for a player
	 * @param fname, lname, num, team
	 */
	public void displayAvgTacklesPlayer(String fname, String lname, int num, String team) {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL avg_tackles_player(?, ?, ?, ?)");
			statement.setString(1, fname);
			statement.setString(2, lname);
			statement.setInt(3, num);
			statement.setString(4, team);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append("Player name: " + result.getString("first_name") + " " + result.getString("last_name") + ", Average Tackles: "
						+ result.getString("TacklesPerGame"));
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the average interceptions per game for a player
	 * @param fname, lname, num, team
	 */
	public void displayAvgInterceptionsPlayer(String fname, String lname, int num, String team) {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL avg_interceptions_player(?, ?, ?, ?)");
			statement.setString(1, fname);
			statement.setString(2, lname);
			statement.setInt(3, num);
			statement.setString(4, team);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append("Player name: " + result.getString("first_name") + " " + result.getString("last_name") + ", Average Interceptions: "
						+ result.getString("IntsPerGame"));
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the average sacks per game for a player
	 * @param fname, lname, num, team
	 */
	public void displayAvgSacksPlayer(String fname, String lname, int num, String team) {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL avg_sacks_player(?, ?, ?, ?)");
			statement.setString(1, fname);
			statement.setString(2, lname);
			statement.setInt(3, num);
			statement.setString(4, team);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append("Player name: " + result.getString("first_name") + " " + result.getString("last_name") + ", Average Sacks: "
						+ result.getString("SacksPerGame"));
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the top 5 receivers
	 */
	public void displayTopReceivers() {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL top_5_receivers()");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append("Player name: " + result.getString("first_name") + " " + result.getString("last_name") + ", Receiving Yards Per Game: "
						+ result.getString("RecYardsPerGame"));
				procedure.append("\n");
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the top 5 quarterbacks
	 */
	public void displayTopQuarterbacks() {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL top_5_quarterbacks()");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append("Player name: " + result.getString("first_name") + " " + result.getString("last_name") + ", Passing Yards Per Game: "
						+ result.getString("PassYardsPerGame"));
				procedure.append("\n");
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Displays the top 5 runningbacks
	 */
	public void displayTopRunningbacks() {
		StringBuilder procedure = new StringBuilder();
		try {
			Connection conn = this.getConnection();
			CallableStatement statement = conn.prepareCall("CALL top_5_runningbacks()");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				procedure.append("Player name: " + result.getString("first_name") + " " + result.getString("last_name") + ", Running Yards Per Game: "
						+ result.getString("RunYardsPerGame"));
			}
			System.out.println(procedure);
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}
	}
	
	/**
	 * Connect to the DB and do some stuff
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(new InputStreamReader(System.in));
		Database app = new Database();
		Connection conn = null;
		String username = "root";
		String password = "root";

		System.out.println("Welcome to our free NFL Database.");
		System.out.println("Here you can view statistics regarding players, teams, coaches, and matches for the 2019 season.");
		System.out.println("To access this database, please follow the prompts below.");

		try {
			System.out.println("Input your MySQL username.");
			username = input.next();
		} finally {
			// do nothing
		}
		try {
			System.out.println("Input your MySQL password.");
			password = input.next();
		} finally {
			// do nothing
		}

		app.setUsernamePassword(username, password);
		try {
			conn = app.getConnection();
			System.out.println("Connection successful.");
		} catch (SQLException e) {
			System.out.println("Connection unsuccessful. Please re-run your program and "
					+ "enter your credentials again.");
			System.exit(0);
		}

		System.out.println("Below is a list of available commmands that can be run. Please note that the "
				+ "commands are case sensitive.");
		ArrayList commands = new ArrayList<String>();
		commands.add("Exit");
		commands.add("Commands");
		// Read operations
		commands.add("Display-all-teams");
		commands.add("Display-all-head-coaches");
		commands.add("Display-all-matches");
		commands.add("Display-all-players");
		commands.add("Display-player-offensive-stats");
		commands.add("Display-player-defensive-stats");
		commands.add("Display-match-stats");
		// Create operations
		commands.add("Add-match");
		commands.add("Add-team");
		commands.add("Add-head-coach");
		commands.add("Add-player");
		commands.add("Add-player-match-stats");
		// Delete operations
		commands.add("Delete-team");
		commands.add("Delete-head-coach");
		commands.add("Delete-player");
		// Update operations
		commands.add("Update-team");
		commands.add("Update-head-coach");
		commands.add("Update-player");
		commands.add("Update-match");
		commands.add("Update-player-match-stats");
		// Procedures for teams
		commands.add("Display-avg-pass-team");
		commands.add("Display-avg-pass-conceded-team");
		commands.add("Display-avg-run-team");
		commands.add("Display-avg-run-conceded-team");
		commands.add("Display-avg-points-team");
		commands.add("Display-avg-points-conceded-team");
		commands.add("Display-top-5-teams-by-win");
		commands.add("Display-bottom-5-teams-by-losses"); 
		commands.add("Display-top-5-teams-by-win-loss");
		commands.add("Display-top-5-teams-by-most-points-scored");
		commands.add("Display-top-5-teams-by-fewest-points-allowed");
		commands.add("Display-top-5-teams-by-most-passing-yards");
		commands.add("Display-top-5-teams-by-fewest-allowed-passing-yards");
		commands.add("Display-top-5-teams-by-most-rushing-yards");
		commands.add("Display-top-5-teams-by-fewest-allowed-rushing-yards"); 
		// Procedures for players
		commands.add("Display-avg-pass-player");
		commands.add("Display-avg-receiving-player");
		commands.add("Display-avg-rush-player");
		commands.add("Display-avg-tackles-player");
		commands.add("Display-avg-interceptions-player");
		commands.add("Display-avg-sacks-player");
		commands.add("Display-top-5-receivers");
		commands.add("Display-top-5-quarterbacks"); 
		commands.add("Display-top-5-runningbacks"); 
		
		for (int i = 0; i < commands.size(); i++) {
			System.out.print(i + ". ");
			System.out.println(commands.get(i));
		}
		System.out.println("-----------------------");
		System.out.println("Enter your command:");
		String command = "";
		while (true) {
			String current= input.next();
			if (commands.contains(current)) {
				command = current;
				if (command.equals("Exit")) {
					conn = null;
					break;
				}

				else if (command.equals("Commands")) {
					System.out.println("Below is a list of the commands."); 
					for (int i = 0; i < commands.size(); i++) {
						System.out.print(i + ". ");
						System.out.println(commands.get(i));
					}
					System.out.println("Please enter another command.");
				}

				else if (command.equals("Display-all-teams")) {
					System.out.println("Below is a list of the current teams."); 
					ArrayList teamNames = app.getTeams();
					for (int i = 0; i < teamNames.size(); i++) {
						System.out.println(teamNames.get(i));
					}
					System.out.println("Please enter another command.");
				}
				
				else if (command.equals("Display-all-head-coaches")) {
					System.out.println("Below is a list of current head coaches."); 
					ArrayList coachNames = app.getCoaches();
					for (int i = 0; i < coachNames.size(); i++) {
						System.out.println(coachNames.get(i));
					}
					System.out.println("Please enter another command.");
				}
				
				else if (command.equals("Display-all-matches")) {
					System.out.println("Below is a list of the matches this season."); 
					ArrayList matches = app.getMatches();
					for (int i = 0; i < matches.size(); i++) {
						System.out.println(matches.get(i));
					}
					System.out.println("Please enter another command.");
				}

				else if (command.equals("Display-all-players")) {
					System.out.println("Below is a list of the current players."); 
					ArrayList playerNames = app.getPlayers();
					for (int i = 0; i < playerNames.size(); i++) {
						System.out.println(playerNames.get(i));
					}
					System.out.println("Please enter another command.");
				}
				
				else if (command.equals("Display-player-offensive-stats")) {
					int playerID = 0;
					System.out.println("Please enter the player ID to find their offensive statistics.");
					playerID = input.nextInt();
					ArrayList offStats = app.getOffStats(playerID);
					for (int i = 0; i < offStats.size(); i++) {
						System.out.println(offStats.get(i));
					}
					System.out.println("Please enter another command.");
				}
				
				else if (command.equals("Display-player-defensive-stats")) {
					int playerID = 0;
					System.out.println("Please enter the player ID to find their defensive statistics.");
					playerID = input.nextInt();
					ArrayList defStats = app.getDefStats(playerID);
					for (int i = 0; i < defStats.size(); i++) {
						System.out.println(defStats.get(i));
					}
					System.out.println("Please enter another command.");
				}
				
				else if (command.equals("Display-match-stats")) {
					int matchID = 0;
					System.out.println("Please enter the match number to find player match statistics.");
					matchID = input.nextInt();
					ArrayList matchStats = app.getMatchStats(matchID);
					for (int i = 0; i < matchStats.size(); i++) {
						System.out.println(matchStats.get(i));
					}
					System.out.println("Please enter another command.");
				}

				else if (command.equals("Add-match")) {
					int match_number = 0;
					int week = 0;
					String home_team = "";
					String away_team = "";
					int home_points = 0;
					int away_points = 0;
					int home_pass_yards = 0;
					int away_pass_yards = 0;
					int home_run_yards = 0;
					int away_run_yards = 0;
					System.out.println("Match number?");
					match_number = input.nextInt();
					System.out.println("Week number?");
					week = input.nextInt();
					System.out.println("Home team?");
					home_team = input.next();
					System.out.println("Away team?");
					away_team = input.next();
					System.out.println("Home points?");
					home_points = input.nextInt();
					System.out.println("Away points?");
					away_points = input.nextInt();
					System.out.println("Home passing yards?");
					home_pass_yards = input.nextInt();
					System.out.println("Away passing yards?");
					away_pass_yards = input.nextInt();
					System.out.println("Home running yards?");
					home_run_yards = input.nextInt();
					System.out.println("Away running yards?");
					away_run_yards = input.nextInt();
					app.addMatch(match_number, week, home_team, away_team, home_points, away_points, home_pass_yards,
							away_pass_yards, home_run_yards, away_run_yards);
					System.out.println("Match added successfully. Please enter another command.");
				}
				
				else if (command.equals("Add-team")) {
					String team_name = "";
					String stadium = "";
					int wins = 0;
					int losses = 0;
					double tot_points_scored = 0;
					double tot_points_allowed = 0;
					double tot_pass_yards = 0;
					double tot_run_yards = 0;
					double tot_pass_yards_conceded = 0;
					double tot_run_yards_conceded = 0;
					int championships_won = 0;
					int superbowls_won = 0;
					
					System.out.println("Team name?");
					team_name = input.next();
					System.out.println("Stadium?");
					stadium = input.next();
					System.out.println("Number of wins?");
					wins = input.nextInt();
					System.out.println("Number of losses?");
					losses = input.nextInt();
					System.out.println("Total points scored?");
					tot_points_scored = input.nextDouble();
					System.out.println("Total points allowed?");
					tot_points_allowed = input.nextDouble();
					System.out.println("Total passing yards?");
					tot_pass_yards = input.nextDouble();
					System.out.println("Total running yards?");
					tot_run_yards = input.nextDouble();
					System.out.println("Total passing yards conceded?");
					tot_pass_yards_conceded = input.nextDouble();
					System.out.println("Total running yards conceded?");
					tot_run_yards_conceded = input.nextDouble();
					System.out.println("Number of championships?");
					championships_won = input.nextInt();
					System.out.println("Number of superbowls?");
					superbowls_won = input.nextInt();
					
					app.addTeam(team_name, stadium, wins, losses, tot_points_scored, tot_points_allowed, tot_pass_yards, tot_run_yards, tot_pass_yards_conceded, 
							tot_run_yards_conceded, championships_won, superbowls_won);
					System.out.println("Team added successfully. Please enter another command.");
				}
				
				else if (command.equals("Add-head-coach")) {
					int coach_id = 0;
					String coach_name = "";
					int years_experience = 0;
					String current_team = "";
					double win_loss_ratio = 0.0;
					int championships_won = 0;
					int superbowls_won = 0;
					
					System.out.println("Coach ID?");
					coach_id = input.nextInt();
					System.out.println("Coach last name?");
					coach_name = input.next();
					System.out.println("Years of experience?");
					years_experience = input.nextInt();
					System.out.println("Current team?");
					current_team = input.next();
					System.out.println("Win/loss ratio?");
					win_loss_ratio = input.nextDouble();
					System.out.println("Number of championships?");
					championships_won = input.nextInt();
					System.out.println("Number of superbowls?");
					superbowls_won = input.nextInt();
					
					app.addCoach(coach_id, coach_name, years_experience, current_team, win_loss_ratio, championships_won, superbowls_won);
					System.out.println("Head coach added successfully. Please enter another command.");
				}
				
				else if (command.equals("Add-player")) {
					int id = 0;
					int player_number = 0;
					String first_name = "";
					String last_name = "";
					String position = "";
					String current_team = "";
					int games_so_far = 0;
					int years_experience = 0;
					double rating = 0.0;
					int championships_won = 0;
					int superbowls_won = 0;
					
					System.out.println("Player ID?");
					id = input.nextInt();
					System.out.println("Player number?");
					player_number = input.nextInt();
					System.out.println("First name?");
					first_name = input.next();
					System.out.println("Last name?");
					last_name = input.next();
					System.out.println("Current team?");
					current_team = input.next();
					System.out.println("Games so far?");
					games_so_far = input.nextInt();
					System.out.println("Years of experience?");
					years_experience = input.nextInt();
					System.out.println("Rating?");
					rating = input.nextDouble();
					System.out.println("Number of championships?");
					championships_won = input.nextInt();
					System.out.println("Number of superbowls?");
					superbowls_won = input.nextInt();
					
					app.addPlayer(id, player_number, first_name, last_name, position, current_team, games_so_far, years_experience, rating, championships_won, superbowls_won);
					System.out.println("Player added successfully. Please enter another command.");
				}
				
				else if (command.equals("Add-player-match-stats")) {
					int player_id = 0;
					String team = "";
					int match_number = 0;
					int passing_yards = 0;
					int rushing_yards = 0;
					int receiving_yards = 0;
					int tackles = 0;
					int interceptions = 0;
					int sacks = 0;
					
					System.out.println("Player ID?");
					player_id = input.nextInt();
					System.out.println("Team?");
					team = input.next();
					System.out.println("Match number?");
					match_number = input.nextInt();
					System.out.println("Number of passing yards?");
					passing_yards = input.nextInt();
					System.out.println("Number of rushing yards?");
					rushing_yards = input.nextInt();
					System.out.println("Number of receiving yards?");
					receiving_yards = input.nextInt();
					System.out.println("Number of tackles?");
					tackles = input.nextInt();
					System.out.println("Number of sacks?");
					sacks = input.nextInt();
					System.out.println("Number of interceptions?");
					interceptions = input.nextInt();
					
					app.addPlayerMatchStats(player_id, team, match_number, passing_yards, rushing_yards, receiving_yards, tackles, sacks, interceptions);
					System.out.println("Player match stats added successfully. Please enter another command.");
				}
				
				else if (command.equals("Delete-team")) {
					String team_name = "";
					System.out.println("Which team would you like to delete?");
					team_name = input.next();
					
					app.deleteTeam(team_name);
					System.out.println("Team deleted successfully. Enter another command.");
				}
				
				else if (command.equals("Delete-head-coach")) {
					int coach_id = 0;
					System.out.println("Enter the coach ID of the coach you would like to delete.");
					coach_id = input.nextInt();
					
					app.deleteCoach(coach_id);
					System.out.println("Head coach deleted successfully. Enter another command.");
				}
				
				else if (command.equals("Delete-player")) {
					int id = 0;
					System.out.println("Enter the player ID of the player you would like to delete.");
					id = input.nextInt();
					
					app.deletePlayer(id);
					System.out.println("Player deleted successfully. Enter another command.");
				}
				
				else if (command.equals("Update-team")) {
					String team_name = "";
					String stadium = "";
					int wins = 0;
					int losses = 0;
					double tot_points_scored = 0;
					double tot_points_allowed = 0;
					double tot_pass_yards = 0;
					double tot_run_yards = 0;
					double tot_pass_yards_conceded = 0;
					double tot_run_yards_conceded = 0;
					int championships_won = 0;
					int superbowls_won = 0;
					
					System.out.println("Enter the team name that you would like to update.");
					team_name = input.next();
					System.out.println("Stadium?");
					stadium = input.next();
					System.out.println("Number of wins?");
					wins = input.nextInt();
					System.out.println("Number of losses?");
					losses = input.nextInt();
					System.out.println("Total points scored?");
					tot_points_scored = input.nextDouble();
					System.out.println("Total points allowed?");
					tot_points_allowed = input.nextDouble();
					System.out.println("Total passing yards?");
					tot_pass_yards = input.nextDouble();
					System.out.println("Total running yards?");
					tot_run_yards = input.nextDouble();
					System.out.println("Total passing yards conceded?");
					tot_pass_yards_conceded = input.nextDouble();
					System.out.println("Total running yards conceded?");
					tot_run_yards_conceded = input.nextDouble();
					System.out.println("Number of championships?");
					championships_won = input.nextInt();
					System.out.println("Number of superbowls?");
					superbowls_won = input.nextInt();
					
					app.updateTeam(team_name, stadium, wins, losses, tot_points_scored, tot_points_allowed, tot_pass_yards, tot_run_yards, tot_pass_yards_conceded, 
							tot_run_yards_conceded, championships_won, superbowls_won);
					System.out.println("Team updated successfully. Please enter another command.");
				}
				
				else if (command.equals("Update-head-coach")) {
					int coach_id = 0;
					String coach_name = "";
					int years_experience = 0;
					String current_team = "";
					double win_loss_ratio = 0.0;
					int championships_won = 0;
					int superbowls_won = 0;
					
					System.out.println("What is the ID of the coach that you want to update?");
					coach_id = input.nextInt();
					System.out.println("Coach last name?");
					coach_name = input.next();
					System.out.println("Years of experience?");
					years_experience = input.nextInt();
					System.out.println("Current team?");
					current_team = input.next();
					System.out.println("Win/loss ratio?");
					win_loss_ratio = input.nextDouble();
					System.out.println("Number of championships?");
					championships_won = input.nextInt();
					System.out.println("Number of superbowls?");
					superbowls_won = input.nextInt();
					
					app.updateCoach(coach_id, coach_name, years_experience, current_team, win_loss_ratio, championships_won, superbowls_won);
					System.out.println("Head coach updated successfully. Please enter another command.");
				}
				
				else if (command.equals("Update-player")) {
					int id = 0;
					int player_number = 0;
					String first_name = "";
					String last_name = "";
					String position = "";
					String current_team = "";
					int games_so_far = 0;
					int years_experience = 0;
					double rating = 0.0;
					int championships_won = 0;
					int superbowls_won = 0;
					
					System.out.println("What is the player ID that you would like to update?");
					id = input.nextInt();
					System.out.println("Player number?");
					player_number = input.nextInt();
					System.out.println("First name?");
					first_name = input.next();
					System.out.println("Last name?");
					last_name = input.next();
					System.out.println("Current team?");
					current_team = input.next();
					System.out.println("Games so far?");
					games_so_far = input.nextInt();
					System.out.println("Years of experience?");
					years_experience = input.nextInt();
					System.out.println("Rating?");
					rating = input.nextDouble();
					System.out.println("Number of championships?");
					championships_won = input.nextInt();
					System.out.println("Number of superbowls?");
					superbowls_won = input.nextInt();
					
					app.updatePlayer(id, player_number, first_name, last_name, position, current_team, games_so_far, years_experience, rating, championships_won, superbowls_won);
					System.out.println("Player updated successfully. Please enter another command.");
				}
				
				else if (command.equals("Update-match")) {
					int match_number = 0;
					int week = 0;
					String home_team = "";
					String away_team = "";
					int home_points = 0;
					int away_points = 0;
					int home_pass_yards = 0;
					int away_pass_yards = 0;
					int home_run_yards = 0;
					int away_run_yards = 0;
					System.out.println("What is the match number that you would like to update?");
					match_number = input.nextInt();
					System.out.println("Week number?");
					week = input.nextInt();
					System.out.println("Home team?");
					home_team = input.next();
					System.out.println("Away team?");
					away_team = input.next();
					System.out.println("Home points?");
					home_points = input.nextInt();
					System.out.println("Away points?");
					away_points = input.nextInt();
					System.out.println("Home passing yards?");
					home_pass_yards = input.nextInt();
					System.out.println("Away passing yards?");
					away_pass_yards = input.nextInt();
					System.out.println("Home running yards?");
					home_run_yards = input.nextInt();
					System.out.println("Away running yards?");
					away_run_yards = input.nextInt();
					app.updateMatch(match_number, week, home_team, away_team, home_points, away_points, home_pass_yards,
							away_pass_yards, home_run_yards, away_run_yards);
					System.out.println("Match updated successfully. Please enter another command.");
				}
				
				else if (command.equals("Update-player-match-stats")) {
					int player_id = 0;
					String team = "";
					int match_number = 0;
					int passing_yards = 0;
					int rushing_yards = 0;
					int receiving_yards = 0;
					int tackles = 0;
					int interceptions = 0;
					int sacks = 0;
					
					System.out.println("What is the match ID that you would like to update player stats for?");
					match_number = input.nextInt();
					System.out.println("Team?");
					team = input.next();
					System.out.println("Player ID?");
					player_id = input.nextInt();
					System.out.println("Number of passing yards?");
					passing_yards = input.nextInt();
					System.out.println("Number of rushing yards?");
					rushing_yards = input.nextInt();
					System.out.println("Number of receiving yards?");
					receiving_yards = input.nextInt();
					System.out.println("Number of tackles?");
					tackles = input.nextInt();
					System.out.println("Number of sacks?");
					sacks = input.nextInt();
					System.out.println("Number of interceptions?");
					interceptions = input.nextInt();
					
					app.updatePlayerMatchStats(player_id, team, match_number, passing_yards, rushing_yards, receiving_yards, tackles, sacks, interceptions);
					System.out.println("Player match stats updated successfully. Please enter another command.");
				}
				
				else if (command.equals("Display-avg-pass-team")) {
					String team_name = "";
					System.out.println("Enter a team name to find their average passing yards per game.");
					team_name = input.next();
					app.displayAvgPassTeam(team_name);
					System.out.println("Please enter another command.");
				}
				
				else if (command.equals("Display-avg-pass-conceded-team")) {
					String team_name = "";
					System.out.println("Enter a team name to find their average passing yards conceded per game.");
					team_name = input.next();
					app.displayAvgPassTeamConceded(team_name);
					System.out.println("Please enter another command.");
				}
				
				else if (command.equals("Display-avg-run-team")) {
					String team_name = "";
					System.out.println("Enter a team name to find their average rushing yards per game.");
					team_name = input.next();
					app.displayAvgRunTeam(team_name);
					System.out.println("Please enter another command.");
				}
				else if (command.equals("Display-avg-run-conceded-team")) {
					String team_name = "";
					System.out.println("Enter a team name to find their average rushing yards conceded per game.");
					team_name = input.next();
					app.displayAvgRunConcededTeam(team_name);
					System.out.println("Please enter another command.");
				}
				
				else if (command.equals("Display-avg-points-team")) {
					String team_name = "";
					System.out.println("Enter a team name to find their average points per game.");
					team_name = input.next();
					app.displayAvgPointsTeam(team_name);
					System.out.println("Please enter another command.");
				}
				
				else if (command.equals("Display-avg-points-conceded-team")) {
					String team_name = "";
					System.out.println("Enter a team name to find their average points conceded per game.");
					team_name = input.next();
					app.displayAvgPointsConcededTeam(team_name);
					System.out.println("Please enter another command.");
				}
				
				else if (command.equals("Display-top-5-teams-by-win")) {
					app.displayTopTeamsByWins();
					System.out.println("Please enter another command.");
				}
				
				else if (command.equals("Display-bottom-5-teams-by-losses")) {
					app.displayBottomTeamsByLosses();
					System.out.println("Please enter another command.");
				}
				
				else if (command.equals("Display-top-5-teams-by-win-loss")) {
					app.displayTopTeamsByWinLoss();
					System.out.println("Please enter another command.");
				}
				
				else if (command.equals("Display-top-5-teams-by-most-points-scored")) {
					app.displayTopTeamsByMostPoints();
					System.out.println("Enter another command.");
				}
				
				else if (command.equals("Display-top-5-teams-by-fewest-points-allowed")) {
					app.displayTopTeamsByFewestPointsAllowed();
					System.out.println("Enter another command.");
				}
				
				else if (command.equals("Display-top-5-teams-by-most-passing-yards")) {
					app.displayTopTeamsByMostPassingYards();
					System.out.println("Enter another command.");
				}
				
				else if (command.equals("Display-top-5-teams-by-fewest-allowed-passing-yards")) {
					app.displayTopTeamsByFewestPassingYardsAllowed();
					System.out.println("Enter another command.");
				}
				
				else if (command.equals("Display-top-5-teams-by-most-rushing-yards")) {
					app.displayTopTeamsByMostRushingYards();
					System.out.println("Enter another command.");
				}
				
				else if (command.equals("Display-top-5-teams-by-fewest-allowed-rushing-yards")) {
					app.displayTopTeamsByFewestRushingYardsAllowed();
					System.out.println("Enter another command.");
				}
				
				else if (command.equals("Display-avg-pass-player")) {
					String fname = "";
					String lname = "";
					int num = 0;
					String team = "";
					System.out.println("Enter the player's first name to see their average passing yards per game.");
					fname = input.next();
					System.out.println("Enter the player's last name to see their average passing yards per game.");
					lname = input.next();
					System.out.println("Enter the player's number to see their average passing yards per game.");
					num = input.nextInt();
					System.out.println("Enter the player's team to see their average passing yards per game.");
					team = input.next();
					app.displayAvgPassPlayer(fname, lname, num, team);
					System.out.println("Enter another command.");
				}
				
				else if (command.equals("Display-avg-receiving-player")) {
					String fname = "";
					String lname = "";
					int num = 0;
					String team = "";
					System.out.println("Enter the player's first name to see their average receiving yards per game.");
					fname = input.next();
					System.out.println("Enter the player's last name to see their average receiving yards per game.");
					lname = input.next();
					System.out.println("Enter the player's number to see their average receiving yards per game.");
					num = input.nextInt();
					System.out.println("Enter the player's team to see their average receiving yards per game.");
					team = input.next();
					app.displayAvgReceivePlayer(fname, lname, num, team);
					System.out.println("Enter another command.");
				}
				
				else if (command.equals("Display-avg-rush-player")) {
					String fname = "";
					String lname = "";
					int num = 0;
					String team = "";
					System.out.println("Enter the player's first name to see their average rushing yards per game.");
					fname = input.next();
					System.out.println("Enter the player's last name to see their average rushing yards per game.");
					lname = input.next();
					System.out.println("Enter the player's number to see their average rushing yards per game.");
					num = input.nextInt();
					System.out.println("Enter the player's team to see their average rushing yards per game.");
					team = input.next();
					app.displayAvgRushPlayer(fname, lname, num, team);
					System.out.println("Enter another command.");
				}
	
				else if (command.equals("Display-avg-tackles-player")) {
					String fname = "";
					String lname = "";
					int num = 0;
					String team = "";
					System.out.println("Enter the player's first name to see their average tackles per game.");
					fname = input.next();
					System.out.println("Enter the player's last name to see their average tackles per game.");
					lname = input.next();
					System.out.println("Enter the player's number to see their average tackles per game.");
					num = input.nextInt();
					System.out.println("Enter the player's team to see their average tackles per game.");
					team = input.next();
					app.displayAvgTacklesPlayer(fname, lname, num, team);
					System.out.println("Enter another command.");
				}
				
				else if (command.equals("Display-avg-interceptions-player")) {
					String fname = "";
					String lname = "";
					int num = 0;
					String team = "";
					System.out.println("Enter the player's first name to see their average interceptions per game.");
					fname = input.next();
					System.out.println("Enter the player's last name to see their average interceptions per game.");
					lname = input.next();
					System.out.println("Enter the player's number to see their average interceptions per game.");
					num = input.nextInt();
					System.out.println("Enter the player's team to see their average interceptions per game.");
					team = input.next();
					app.displayAvgInterceptionsPlayer(fname, lname, num, team);
					System.out.println("Enter another command.");
				}
				
				else if (command.equals("Display-avg-sacks-player")) {
					String fname = "";
					String lname = "";
					int num = 0;
					String team = "";
					System.out.println("Enter the player's first name to see their average sacks per game.");
					fname = input.next();
					System.out.println("Enter the player's last name to see their average sacks per game.");
					lname = input.next();
					System.out.println("Enter the player's number to see their average sacks per game.");
					num = input.nextInt();
					System.out.println("Enter the player's team to see their average sacks per game.");
					team = input.next();
					app.displayAvgSacksPlayer(fname, lname, num, team);
					System.out.println("Enter another command.");
				}
				
				else if (command.equals("Display-top-5-receivers")) {
					app.displayTopReceivers();
					System.out.println("Enter another command.");
				}
				
				else if (command.equals("Display-top-5-quarterbacks")) {
					app.displayTopQuarterbacks();
					System.out.println("Enter another command.");
				}
				
				else if (command.equals("Display-top-5-runningbacks")) {
					app.displayTopRunningbacks();
					System.out.println("Enter another command.");
				}
			}
			
			else {
				System.out.println("Invalid command. Please check your spelling or type 'Commands' to see the "
						+ "available list of commands.");
			}
		}

		//app.run(character);
		//conn = null;
		System.out.println("Program complete. Disconnected from database.");
	}
}