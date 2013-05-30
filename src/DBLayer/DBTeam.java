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
			teamObj.setId(results.getInt("id"));
			
			guestObj = dbGuest.searchGuestById(results.getInt("leaderId"), false);
			teamObj.setLeader(guestObj);
			
			teamObj.setNumberOfParticipants(results.getInt("numberOfParticipants"));
		}
		catch(Exception e)
		{
			System.out.println("Exception in building the team object: " + e);
		}
		return teamObj;
	}
	
	private Team singleWhere(String wClause, boolean retrieveAssociation)
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
			if(retrieveAssociation)
			{//the leader is to be built as well
				IFDBGuest dbGuest= new DBGuest();
				Guest guestObj=dbGuest.searchGuestById(teamObj.getLeader().getId(), false);
				if(guestObj != null)
				{
					System.out.println("Leader is selected.");
					teamObj.setLeader(guestObj);
				}
			}
			else
			{
				teamObj=null;
			}
		}
		catch(Exception e)
		{
			System.out.println("Single selection query exception: " + e);
		}
		return teamObj;
	}
	
	private LinkedList<Team> miscWhere(String wClause, boolean retrieveAssociation)
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
			if(retrieveAssociation)
			{//the leader is to be selected as well
				IFDBGuest dbGuest= new DBGuest();
				for(Team teamObj : teamList)
				{
					Guest guestObj=dbGuest.searchGuestById(teamObj.getLeader().getId(), false);
					if(guestObj != null)
					{
						System.out.println("Leader is selected.");
						teamObj.setLeader(guestObj);
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Multiple selection query exception: " + e);
			e.printStackTrace();
		}
		
		return teamList;
	}

	@Override
	public LinkedList<Team> getAllTeams(boolean retrieveAssociation)
	{
		return miscWhere("", retrieveAssociation);
	}

	@Override
	public Team getTeamById(int id, boolean retrieveAssociation)
	{
		String wClause = " id= '" + id + "'";
		return singleWhere(wClause, retrieveAssociation);
	}

	public  LinkedList<Team> getTeamsByLeaderId(int leaderId, boolean retrieveAssociation)
	{
		String wClause = " leaderId= '" + leaderId + "'";
		return miscWhere(wClause, retrieveAssociation);
	}

	@Override
	public int insertTeam(Team team) throws Exception
	{
		int result = -1;
		Guest guestObj = team.getLeader();
		String query = new String();
		
		query = "INSERT INTO Team(leaderId, numberOfParticipants) VALUES ('" +
		guestObj.getId() + "','" + 
		team.getNumberOfParticipants() + "')";
		
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
		String query = new String();
		
			query="UPDATE Team SET " + 
		"leaderId= '" + guestObj.getId() + "', " +
					"numberOfParticipants= '" + teamObj.getNumberOfParticipants() + "' " +
		"WHERE id= '" + teamObj.getId() + "'";
		
		
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
		  
	  	String query="DELETE FROM Team WHERE id= '" + id + "'";
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
		  
	  	String query="DELETE FROM Team WHERE id= '" + teamId + "' AND leaderId= '" + leaderId + "'";
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
