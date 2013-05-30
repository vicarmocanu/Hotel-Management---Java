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
		teamList=dbTeam.getAllTeams(true);
		return teamList;
	}
	
	public Team getTeamById(int id)
	{
		IFDBTeam dbTeam = new DBTeam();
		Team teamObj=dbTeam.getTeamById(id, true);
		return teamObj;
	}
	
	public LinkedList<Team> getTeamByLeaderId(int leaderId)
	{
		IFDBTeam dbTeam = new DBTeam();
		LinkedList<Team> teamLeaderList = new LinkedList<Team>();
		teamLeaderList = dbTeam.getTeamsByLeaderId(leaderId, true);
		return teamLeaderList;
	}
	
	public void insertTeam(int leaderId)
	{
		IFDBGuest dbGuest = new DBGuest();		
		Guest teamLeader = dbGuest.searchGuestById(leaderId, false);
		
		Team teamObj = new Team(teamLeader);
		
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
	
	public int updateTeam(int id, int leaderId)
	{
		IFDBTeam dbTeam = new DBTeam();
		IFDBGuest dbGuest = new DBGuest();
		
		Guest teamLeader=dbGuest.searchGuestById(leaderId, false);
		Team teamObj=new Team(id, teamLeader);
		return dbTeam.updateTeam(teamObj);
	}
	
	public int deleteTeamsByLeader(int leaderId)
	{
		IFDBTeam dbTeam=new DBTeam();
		IFDBTeamParticipants dbTeamParticipants = new DBTeamParticipants();
		
		LinkedList<Team> leaderTeamList = new LinkedList<Team>();
		leaderTeamList = dbTeam.getTeamsByLeaderId(leaderId, true);
		
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
	
	public LinkedList<Participant> getTeamParticipantsByTeamId(int teamId)
	{
		IFDBTeamParticipants dbTeamParticipants=new DBTeamParticipants();
		LinkedList<Participant> teamParticipantList=new LinkedList<Participant>();
		teamParticipantList = dbTeamParticipants.getTeamParticipants(teamId, true);
		return teamParticipantList;
	}
	
	public void insertTeamParticipant(int teamId, int participantId)
	{
		IFDBTeam dbTeam=new DBTeam();
		IFDBGuest dbGuest=new DBGuest();
		Team teamObj = dbTeam.getTeamById(teamId, false);
		Guest guestObj = dbGuest.searchGuestById(participantId, false);
		Participant participantObj = new Participant(teamObj, guestObj);
		
		try
		 {
			 DBConnection1.startTransaction();
			 IFDBTeamParticipants dbTeamParticipants = new DBTeamParticipants();
			 dbTeamParticipants.insertTeamParticipant(participantObj);
			 DBConnection1.commitTransaction();
			 teamObj.addParticipant(participantObj);
			 dbTeam.updateTeam(teamObj);
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
		teamObj = dbTeam.getTeamById(teamId, true);
		
		if(teamObj != null)
		{
			teamObj.removeParticipant(teamId, participantId);
			dbTeam.updateTeam(teamObj);
		}
		return dbTeamParticipants.deleteTeamParticipant(teamId, participantId);
	}
	
	public int deleteTeamParticipants(int teamId)
	{
		IFDBTeamParticipants dbTeamParticipants = new DBTeamParticipants();
		return dbTeamParticipants.deleteTeamParticipants(teamId);
	}
}
