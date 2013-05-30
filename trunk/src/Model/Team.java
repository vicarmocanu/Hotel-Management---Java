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
	
	public Team (int id, Guest leader)
	{
		this.id=id;
		this.leader=leader;
		
		LinkedList<Participant> participants = new LinkedList<Participant>();
		Participant participant = new Participant(this, leader);
		participants.add(participant);
		this.numberOfParticipants=participants.size();
	}
	
	public Team(){}
	
	public Team(Guest leader)
	{
		this.leader=leader;
		
		LinkedList<Participant> participants = new LinkedList<Participant>();
		Participant participant = new Participant(this, leader);
		participants.add(participant);
		this.numberOfParticipants=participants.size();
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
		Participant oldLeader = getParticipant(this.getId(), leader.getId());
		this.removeParticipant(this.getId(), oldLeader.getGuest().getId());
		this.leader=leader;
		Participant newLeader = new Participant(this, leader);
		this.addParticipant(newLeader);
	}
	
	public int getNumberOfParticipants()
	{
		this.numberOfParticipants=this.participants.size();
		return numberOfParticipants;
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
		int newNumberOfParticipants = getNumberOfParticipants();
		this.setNumberOfParticipants(newNumberOfParticipants);
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
			int newNumberOfParticipants = getNumberOfParticipants();
			this.setNumberOfParticipants(newNumberOfParticipants);
		}
	}
	
}
