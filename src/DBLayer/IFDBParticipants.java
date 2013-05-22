package DBLayer;

import java.util.LinkedList;

import Model.Guest;

public interface IFDBParticipants
{
	//get the participants of a team
	public LinkedList<Guest> getTeamParticipantsByTeamId(int teamId, boolean retrieveAssociation);
	
	//insert a participant
	public int insertParticipant(Guest guest) throws Exception;
	
	//remove a participant
	public int deleteParticipant(int guestId);
}
