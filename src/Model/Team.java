package Model;
import java.util.LinkedList;

public class Team 
{
	//attributes
	private int id;
	private Guest leader;
	private LinkedList<Guest> participants;
	private int numberOfParticipants;
	
	//constructor
	
	public Team (int id, Guest leader)
	{
		this.id=id;
		this.leader=leader;
		LinkedList<Guest> participants = new LinkedList<Guest>();
		this.numberOfParticipants=participants.size();
	}
	
	public Team(){}
	
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
		this.numberOfParticipants=this.participants.size();
		return numberOfParticipants;
	}
	
	public void setParticipants(LinkedList<Guest> participants)
	{
		this.participants=participants;
	}
	public void addParticipant(Guest participant)
	{
		participants.add(participant);
	}
	public Guest getParticipantById(int id)
	{
		Guest wantedParticipant = null;
		for(Guest participant : participants)
		{
			if(participant.getId()==id)
			{
				wantedParticipant=participant;
				break;
			}
		}
		if(wantedParticipant==null)
		{
			System.out.println("There is no such participant by this id. Please insert a valid guest id.");
		}
		return wantedParticipant;
	}
	public LinkedList<Guest> getParticipants()
	{
		return participants;
	}
	public void updateParticipantById(int id, String name, String address, int zipcode, 
			String city, String country, String phoneNo, String email, String password,
			String type)
	{
		Guest wantedParticipant=getParticipantById(id);
		if(wantedParticipant!=null)
		{
			wantedParticipant.setName(name);
			wantedParticipant.setAddress(address);
			wantedParticipant.setZipCode(zipcode);
			wantedParticipant.setCity(city);
			wantedParticipant.setCountry(country);
			wantedParticipant.setPhoneNo(phoneNo);
			wantedParticipant.setEmail(email);
			wantedParticipant.setPassword(password);
			wantedParticipant.setType(type);
		}
	}
	public void removeParticipantById(int id)
	{
		Guest wantedParticipant=getParticipantById(id);
		if(wantedParticipant!=null)
		{
			participants.remove(wantedParticipant);
		}
	}
}
