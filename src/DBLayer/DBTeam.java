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
		
		try
		{
			teamObj.setId(results.getInt("id"));
			teamObj.setLeader(dbGuest.searchGuestById(results.getInt("leaderId"), false));
			teamObj.setNumberOfParticipants(results.getInt("number_participants"));
		}
		catch(Exception e)
		{
			System.out.println("Error in building the team type object.");
		}
		return teamObj;
	}
	
	private Team singleWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		Team teamObj=new Team();
		
		String query = buildQuery(wClause);
		System.out.println(query);
		
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
				System.out.println("Leader is selected.");
				teamObj.setLeader(guestObj);
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
					System.out.println("Leader is selected.");
					teamObj.setLeader(guestObj);
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
		String wClause = "  id= '" + id + "'";
		return singleWhere(wClause, retrieveAssociation);
	}

	@Override
	public Team getTeamByLeaderId(int leaderId, boolean retrieveAssociation)
	{
		String wClause = "  leaderId= '" + leaderId + "'";
		return singleWhere(wClause, retrieveAssociation);
	}

	@Override
	public int insertTeam(Team team) throws Exception
	{
		int result = -1;
		
		String query = "INSERT INTO Team(id, leaderId, number_participants) VALUES ('" +
				team.getId() + "','" + 
				team.getLeader().getId() + "','" + 
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
	    	System.out.println("Team has not been inserted correctly. Exception: " + e);
	    }
	    
	    return(result);
	}

	@Override
	public int updateTeam(Team team) 
	{
		Team teamObj=team;
		
		String query="UPDATE Team SET " + 
		"id= '" + teamObj.getId() + "', " + 
				"leaderId= '" + teamObj.getLeader().getId() + "', " +
		"number_participants= '" + teamObj.getNumberOfParticipants() + "'";
		
		int result=-1;
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
			System.out.println("Team has not been updated correctly. Exception: " + e);
		}
		
		return(result);
	}

	@Override
	public int deleteTeamByLeader(int leaderId)
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
	  		System.out.println("Team has not been deleted successfully. Exception: " + e);
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
	  		System.out.println("Team has not been deleted successfully. Exception: " + e);
	  	}
	  	
	  	return(result);
	}
	

}
