package Model;
import java.util.LinkedList;

public class Team 
{
	//attributes
	private int id;
	private Guest leader;
	private LinkedList<Participant> participants;
	private int numberOfParticipants;
	
	//constructor
	public Team()
	{
		participants = new LinkedList<Participant>();
	}
	
	//getters and setters + linked list CRUD
	
	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	
	public Guest getLeader()
	{
		return this.leader;
	}
	public void setLeader(Guest leader)
	{
		this.leader=leader;
	}
	
	public int getNumberOfParticipants()
	{
		return this.numberOfParticipants;
	}
	public int getParticipantsNumbers()
	{
		return this.participants.size();
	}
	public void setNumberOfParticipants(int numberOfParticipants)
	{
		this.numberOfParticipants=numberOfParticipants;
	}
	
	public void setParticipants(LinkedList<Participant> participants)
	{
		this.participants=participants;
	}
	
	public void addParticipant(Participant participant)
	{
		participants.add(participant);
	}
	
	public Participant getParticipant(int teamId, int guestId)
	{
		Participant wantedParticipant = null;
		for(Participant participant : participants)
		{
			if((participant.getGuest().getId() == guestId) && (participant.getTeam().getId() == teamId))
			{
				wantedParticipant=participant;
				break;
			}
		}
		if(wantedParticipant==null)
		{
			System.out.println("There is no such participant by these parameteres. Please insert a valid team and guest id.");
		}
		return wantedParticipant;
	}
	
	public LinkedList<Participant> getParticipants()
	{
		return participants;
	}
	
	public void removeParticipant(int teamId, int guestId)
	{
		Participant wantedParticipant = getParticipant(teamId, guestId);
		if(wantedParticipant!=null)
		{
			participants.remove(wantedParticipant);
		}
	}
}