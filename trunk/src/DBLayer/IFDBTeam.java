package DBLayer;

import java.util.LinkedList;

import Model.Team;

public interface IFDBTeam
{
	//get all teams
	public LinkedList<Team> getAllTeams(boolean retrieveAssociation);
	
	//get team by id
	public Team getTeamById(int id, boolean retrieveAssociation);
	
	//get team by leader id
	public Team getTeamByLeaderId(int leaderId, boolean retrieveAssociation);
	
	//insert team
	public int insertTeam(Team team) throws Exception;
	
	//update team
	public int updateTeam(Team team);
	
	//delete team by leader id
	public int deleteTeamByLeader(int leaderId);
	
	//delete team by team id
	public int deleteTeamById(int id);
}