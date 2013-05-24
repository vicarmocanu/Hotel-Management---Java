package Model;

public class Participant
{
	//attributes
	private Team team;
	private Guest participant;
	
	//constructor
	public Participant(Team team, Guest participant)
	{
		this.team=team;
		this.participant=participant;
	}
	
	public Participant()
	{}
	
	//setters and getters
	
	public void setTeam(Team team)
	{
		this.team=team;
	}
	public Team getTeam()
	{
		return this.team;
	}
	
	public void setParticipant(Guest participant)
	{
		this.participant=participant;
	}
	public Guest getParticipant()
	{
		return this.participant;
	}
}
