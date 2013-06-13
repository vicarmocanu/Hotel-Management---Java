package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Model.Guest;
import Model.Team;

public class DBTeam implements IFDBTeam
{
	private Connection con;
	
	public DBTeam()
	{
		con=DBConnection1.getInstance().getDBcon();
	}
	
	private String buildQuery(String wClause)
	{
		String query="SELECT * FROM Team";
		
		if (wClause.length()>0)
		{
			query=query+" WHERE "+ wClause;
		}
		
		return query;
	}
	
	private Team buildTeam(ResultSet results)
	{
		Team teamObj=new Team();
		IFDBGuest dbGuest=new DBGuest();
		Guest guestObj = new Guest();
		
		try
		{
			teamObj.setId(results.getInt("teamId"));
			
			guestObj = dbGuest.searchGuestById(results.getInt("leaderId"));
			teamObj.setLeader(guestObj);
			
			teamObj.setNumberOfParticipants(results.getInt("numberOfParticipants"));
		}
		catch(Exception e)
		{
			System.out.println("Exception in building the team object: " + e);
		}
		
		return teamObj;
	}
	
	private Team singleWhere(String wClause)
	{
		ResultSet results;
		
		Team teamObj=new Team();
		String query = buildQuery(wClause);
		System.out.println("Query: " + query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			if( results.next() )
			{
				teamObj=buildTeam(results);
				stmt.close();
			}
		}
		catch(Exception e)
		{
			System.out.println("Single selection query exception: " + e);
		}
		
		return teamObj;
	}
	
	private LinkedList<Team> miscWhere(String wClause)
	{
		ResultSet results;
		
		LinkedList<Team> teamList=new LinkedList<Team>();
		String query =  buildQuery(wClause);
		System.out.println("Query: " + query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			while( results.next() )
			{
				Team teamObj=new Team();
				teamObj=buildTeam(results);
				teamList.add(teamObj);
			}
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println("Multiple selection query exception: " + e);
			e.printStackTrace();
		}
		
		return teamList;
	}

	@Override
	public LinkedList<Team> getAllTeams()
	{
		return miscWhere("");
	}

	@Override
	public Team getTeamById(int teamId)
	{
		String wClause = " teamId= '" + teamId + "'";
		return singleWhere(wClause);
	}

	public LinkedList<Team> getTeamsByLeaderId(int leaderId)
	{
		String wClause = " leaderId= '" + leaderId + "'";
		return miscWhere(wClause);
	}

	@Override
	public int insertTeam(Team team) throws Exception
	{
		//call to get the next team id
		int nextTeamId = GetMax.getMaxId("SELECT MAX(teamId) FROM Team");
		nextTeamId = nextTeamId + 1;
		System.out.println("Next team id = " + nextTeamId);
		
		int result = -1;
		
		Guest guestObj = team.getLeader();
		String query = "INSERT INTO Team(teamId, leaderId, numberOfParticipants) VALUES ('" + nextTeamId + "','" +
		guestObj.getId() + "','" +
				team.getNumberOfParticipants() + "') INSERT INTO TeamParticipants(teamId, participantId) VALUES ('" + nextTeamId + "','" +
		guestObj.getId() + "')";
		System.out.println("Insertion query: " + query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			result = stmt.executeUpdate(query);
			stmt.close();
		}
		catch(SQLException e)
		{
			System.out.println("Insert exception: " + e);
		}
		return(result);
	}

	@Override
	public int updateTeam(Team team) 
	{
		int result=-1;
		
		Team teamObj=team;
		Guest guestObj = teamObj.getLeader();
		String query = "UPDATE Team SET leaderId= '" + guestObj.getId() + "', " +
		"numberOfParticipants= '" + teamObj.getNumberOfParticipants() + "' " +
				"WHERE teamId= '" + teamObj.getId() + "'";
		System.out.println("Update query: " + query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			result = stmt.executeUpdate(query);
			stmt.close();
		}
		catch(SQLException e)
		{
			System.out.println("Update exception: " + e);
		}
		return(result);
	}

	@Override
	public int deleteTeamsByLeader(int leaderId)
	{
		int result=-1;
		
		String query="DELETE FROM Team WHERE leaderId= '" + leaderId + "'";
		System.out.println("Delete query: " + query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			result = stmt.executeUpdate(query);
			stmt.close();
		}
		catch(SQLException e)
		{
			System.out.println("Delete exception: " + e);
		}
		return(result);
	}

	@Override
	public int deleteTeamById(int id)
	{
		int result=-1;
		
		String query="DELETE FROM Team WHERE teamId= '" + id + "'";
		System.out.println("Delete query: " + query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			result = stmt.executeUpdate(query);
			stmt.close();
		}
		catch(SQLException e)
		{
			System.out.println("Delete exception: " + e);
		}
		return(result);
	}

	@Override
	public int deleteTeamByBothIDs(int teamId, int leaderId)
	{
		int result=-1;
		
		String query="DELETE FROM Team WHERE teamId= '" + teamId + "' AND leaderId= '" + leaderId + "'";
		System.out.println("Delete query: " + query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			result = stmt.executeUpdate(query);
			stmt.close();
		}
		catch(SQLException e)
		{
			System.out.println("Delete exception: " + e);
		}
		
		return(result);
	}
}