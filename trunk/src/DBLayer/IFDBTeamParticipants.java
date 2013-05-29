package DBLayer;

import java.util.LinkedList;

import Model.Participant;

public interface IFDBTeamParticipants
{
	//method to return all members of a team based on the id of the team
	public LinkedList<Participant> getTeamParticipants(int teamId, boolean retrieveAssociation);
	
	//method to add a new participant to the team based on team id and participant id
	public int insertTeamParticipant(Participant teamParticipant) throws Exception;
	
	//method to remove a participant from the team based on team id and participant id
	public int deleteTeamParticipant(int teamId, int guestId);
	
	//method to delete all participants from a team
	public int deleteTeamParticipants(int teamId);
}
