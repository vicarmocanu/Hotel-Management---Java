package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Model.Guest;
import Model.Participant;
import Model.Team;

public class DBTeamParticipants implements IFDBTeamParticipants
{
	private Connection con;
	
	public DBTeamParticipants()
	{
		con=DBConnection1.getInstance().getDBcon();
	}
	
	private String buildQuery(String wClause)
	{
		String query="SELECT * FROM TeamParticipants";
		if (wClause.length()>0)
		{
			query=query+" WHERE "+ wClause;
		}
		return query;
	}
	
	private Participant buildTeamParticipant(ResultSet results)
	{
		Participant teamParticipantObj=new Participant();
		
		IFDBGuest dbGuest=new DBGuest();
		IFDBTeam dbTeam=new DBTeam();
		
		try
		{
			teamParticipantObj.setTeam(dbTeam.getTeamById(results.getInt("teamId"), false));
			teamParticipantObj.setGuest(dbGuest.searchGuestById(results.getInt("participantId"), false));
		}
		catch(Exception e)
		{
			System.out.println("Exception in building the team participant object: " + e);
		}
		return teamParticipantObj;
	}
	
	private Participant singleWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		Participant participantObj = new Participant();
		
		String query = buildQuery(wClause);
		System.out.println("Query: " + query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			if( results.next() )
			{
				participantObj=buildTeamParticipant(results);
				stmt.close();
			}
			if(retrieveAssociation)
			{//guest and team reference
				IFDBGuest dbGuest= new DBGuest();
				Guest guestObj=dbGuest.searchGuestById(participantObj.getGuest().getId(), false);
				System.out.println("Guest selected.");
				participantObj.setGuest(guestObj);
				
				IFDBTeam dbTeam=new DBTeam();
				Team teamObj=dbTeam.getTeamById(participantObj.getTeam().getId(), true);
				System.out.println("Team selected.");
				participantObj.setTeam(teamObj);
			}
		}
		catch(Exception e)
		{
			System.out.println("Single selection query exception: " + e);
		}
		return participantObj;
	}
	
	private LinkedList<Participant> miscWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		LinkedList<Participant> participantList=new LinkedList<Participant>();
		String query =  buildQuery(wClause);
		System.out.println("Query: "+query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			while( results.next() )
			{
				Participant teamParticipantObj=new Participant();
				teamParticipantObj=buildTeamParticipant(results);
				participantList.add(teamParticipantObj);
			}
			stmt.close();
			if(retrieveAssociation)
			{//guest and team reference
				IFDBGuest dbGuest= new DBGuest();
				IFDBTeam dbTeam=new DBTeam();
				for(Participant teamParticipantObj : participantList)
				{
					Guest guestObj=dbGuest.searchGuestById(teamParticipantObj.getGuest().getId(), false);
					System.out.println("Guest selected.");
					teamParticipantObj.setGuest(guestObj);
					
					Team teamObj=dbTeam.getTeamById(teamParticipantObj.getTeam().getId(), true);
					System.out.println("Team selected.");
					teamParticipantObj.setTeam(teamObj);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Multiple selection query exception: " + e);
			e.printStackTrace();
		}
		
		return participantList;
	}
	
	

	@Override
	public LinkedList<Participant> getTeamParticipants(int teamId, boolean retrieveAssociation)
	{
		String wClause = "  teamId= '" + teamId + "'";
		return miscWhere(wClause, retrieveAssociation);
	}

	@Override
	public int insertTeamParticipant(Participant teamParticipant) throws Exception
	{
		int result = -1;
		
		String query = "INSERT INTO TeamParticipants(teamId, participantId) VALUES ('" +
				teamParticipant.getTeam().getId() + "','" +
				teamParticipant.getGuest().getId() + "')";
		
		System.out.println("Inserti query: " + query);
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
	public int deleteTeamParticipant(int teamId, int participantId)
	{
		int result=-1;
		  
	  	String query="DELETE FROM TeamParticipants WHERE teamId= '" + teamId + "'" + 
	  	" AND participantId= '" + participantId + "'";
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
	public int deleteTeamParticipants(int teamId)
	{
		int result=-1;
		  
	  	String query="DELETE FROM TeamParticipants WHERE teamId= '" + teamId + "'";
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
	public Participant getParticipant(int teamId, int participantId, boolean retrieveAssociation) 
	{
		String wClause = "  teamId= '" + teamId + "' AND participantId= '" + participantId + "'";
		return singleWhere(wClause, retrieveAssociation);
	}

}
