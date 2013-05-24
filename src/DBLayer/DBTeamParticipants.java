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
			teamParticipantObj.setParticipant(dbGuest.searchGuestById(results.getInt("participantId"), false));
		}
		catch(Exception e)
		{
			System.out.println("Error in building the team participant object.");
		}
		return teamParticipantObj;
	}
	
	private LinkedList<Participant> miscWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		LinkedList<Participant> participantList=new LinkedList<Participant>();
		String query =  buildQuery(wClause);
		
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
			{//participant and team reference
				IFDBGuest dbGuest= new DBGuest();
				IFDBTeam dbTeam=new DBTeam();
				for(Participant teamParticipantObj : participantList)
				{
					Guest participantObj=dbGuest.searchGuestById(teamParticipantObj.getParticipant().getId(), false);
					System.out.println("Participant selected.");
					teamParticipantObj.setParticipant(participantObj);
					
					Team teamObj=dbTeam.getTeamById(teamParticipantObj.getTeam().getId(), false);
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
				teamParticipant.getParticipant().getId() + "')";
		
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
	    	System.out.println("Participant has not been inserted correctly. Exception: " + e);
	    }
	    return(result);
	}

	@Override
	public int deleteTeamParticipant(int teamId, int participantId)
	{
		int result=-1;
		  
	  	String query="DELETE FROM TeamParticipants WHERE teamId= '" + teamId + "'" + " AND participantId= '" + participantId + "'";
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
	  		System.out.println("Participant has not been deleted successfully. Exception: " + e);
	  	}
	  	
	  	return(result);
	}

}
