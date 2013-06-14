package Controller;

import java.util.LinkedList;
import DBLayer.DBConnection1;
import DBLayer.DBGuest;
import DBLayer.DBTeam;
import DBLayer.DBTeamParticipants;
import DBLayer.IFDBGuest;
import DBLayer.IFDBTeam;
import DBLayer.IFDBTeamParticipants;
import Model.Guest;
import Model.Participant;
import Model.Team;

public class TeamCtr
{
	public TeamCtr(){}
	
	public LinkedList<Team> getAllTeams()
	{
		IFDBTeam dbTeam = new DBTeam();
		LinkedList<Team> teamList = new LinkedList<Team>();
		teamList=dbTeam.getAllTeams();
		return teamList;
	}
	
	public Team getTeamById(int teamId)
	{
		IFDBTeam dbTeam = new DBTeam();
		Team teamObj=dbTeam.getTeamById(teamId);
		return teamObj;
	}
	
	public LinkedList<Team> getTeamsByLeaderId(int leaderId)
	{
		IFDBTeam dbTeam = new DBTeam();
		LinkedList<Team> teamLeaderList = new LinkedList<Team>();
		teamLeaderList = dbTeam.getTeamsByLeaderId(leaderId);
		return teamLeaderList;
	}
	
	public void insertTeam(int leaderId)
	{
		IFDBGuest dbGuest = new DBGuest();		
		Guest teamLeader = dbGuest.searchGuestById(leaderId);
		
		Team teamObj = new Team();
		teamObj.setLeader(teamLeader);
		
		Participant participantObj = new Participant();
		participantObj.setGuest(teamLeader);
		participantObj.setTeam(teamObj);		
		teamObj.addParticipant(participantObj);
		
		int numberOfParticipants = teamObj.getParticipantsNumbers();
		teamObj.setNumberOfParticipants(numberOfParticipants);
		
		try
		{
			DBConnection1.startTransaction();
			DBTeam dbTeam = new DBTeam();
			dbTeam.insertTeam(teamObj);
			DBConnection1.commitTransaction();
		}
		catch(Exception e)
		{
			DBConnection1.rollbackTransaction();
		}
	}
	
	public int deleteTeamsByLeader(int leaderId)
	{
		IFDBTeam dbTeam=new DBTeam();
		IFDBTeamParticipants dbTeamParticipants = new DBTeamParticipants();
		
		LinkedList<Team> leaderTeamList = new LinkedList<Team>();
		leaderTeamList = dbTeam.getTeamsByLeaderId(leaderId);
		
		for(Team team : leaderTeamList)
		{
			int teamId = team.getId();
			dbTeamParticipants.deleteTeamParticipants(teamId);
		}		
		return dbTeam.deleteTeamsByLeader(leaderId);
	}
	
	public int deleteTeamById(int teamId)
	{
		IFDBTeam dbTeam=new DBTeam();
		return dbTeam.deleteTeamById(teamId);
	}
	
	public int deleteTeamByBothIDs(int teamId, int leaderId)
	{
		IFDBTeam dbTeam = new DBTeam();
		return dbTeam.deleteTeamByBothIDs(teamId, leaderId);
	}
	
	public LinkedList<Participant> getTeamParticipantsByTeamId(int teamId)
	{
		IFDBTeamParticipants dbTeamParticipants=new DBTeamParticipants();
		LinkedList<Participant> teamParticipantList=new LinkedList<Participant>();
		teamParticipantList = dbTeamParticipants.getTeamParticipants(teamId);
		return teamParticipantList;
	}
	
	public Participant getTeamParticipant(int teamId, int participantId)
	{
		IFDBTeamParticipants dbTeamParticipants = new DBTeamParticipants();
		Participant participantObj = dbTeamParticipants.getParticipant(teamId, participantId);
		return participantObj;
	}
	
	public void insertTeamParticipant(int teamId, int participantId)
	{
		IFDBTeam dbTeam=new DBTeam();
		IFDBGuest dbGuest=new DBGuest();
		
		Team teamObj = dbTeam.getTeamById(teamId);
		Guest guestObj = dbGuest.searchGuestById(participantId);
		
		Participant participantObj = new Participant(teamObj, guestObj);
		
		teamObj.addParticipant(participantObj);
		int newNumberOfParticipants= teamObj.getNumberOfParticipants() + 1;
		teamObj.setNumberOfParticipants(newNumberOfParticipants);
		dbTeam.updateTeam(teamObj);
		
		try
		 {
			 DBConnection1.startTransaction();			 
			 DBTeamParticipants dbTeamParticipants = new DBTeamParticipants();
			 dbTeamParticipants.insertTeamParticipant(participantObj);			 
			 DBConnection1.commitTransaction();
		 }
		 catch(Exception e)
		 {
			 DBConnection1.rollbackTransaction();
		 }
	}
	
	public int deleteTeamParticipant(int teamId, int participantId)
	{
		IFDBTeamParticipants dbTeamParticipants = new DBTeamParticipants();
		IFDBTeam dbTeam = new DBTeam();
		
		Team teamObj = new Team();		
		teamObj = dbTeam.getTeamById(teamId);
		
		if(teamObj != null)
		{
			teamObj.removeParticipant(teamId, participantId);
			int newNumberOfParticipants= teamObj.getNumberOfParticipants() - 1;
			teamObj.setNumberOfParticipants(newNumberOfParticipants);
			dbTeam.updateTeam(teamObj);
		}
		return dbTeamParticipants.deleteTeamParticipant(teamId, participantId);
	}
	
	public int deleteTeamParticipants(int teamId)
	{
		IFDBTeamParticipants dbTeamParticipants = new DBTeamParticipants();
		return dbTeamParticipants.deleteTeamParticipants(teamId);
	}
	
	public boolean checkTeamParticipantInstances(int teamId, int participantId)
	{
		boolean check = false;
		int instances = 0;
		
		IFDBTeamParticipants dbTeamParticipants = new DBTeamParticipants();
		instances = dbTeamParticipants.getTeamParticipantInstances(teamId, participantId);
		
		if(instances == 0)
		{
			check = true;
		}
		else
		{
			check = false;
		}
		return check;
		
	}
}
