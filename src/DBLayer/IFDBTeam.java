package DBLayer;

import java.util.LinkedList;

import Model.Team;

public interface IFDBTeam
{
	//get all teams
	public LinkedList<Team> getAllTeams(boolean retrieveAssociation);
	
	//get team by id
	public Team getTeamById(int id, boolean retrieveAssociation);
	
	//get teams made by a leader id
	public LinkedList<Team> getTeamsByLeaderId(int leaderId, boolean retrieveAssociation);
	
	//insert team
	public int insertTeam(Team team) throws Exception;
	
	//update team
	public int updateTeam(Team team);
	
	//delete team by leader id
	public int deleteTeamsByLeader(int leaderId);
	
	//delete team by team id
	public int deleteTeamById(int id);
}