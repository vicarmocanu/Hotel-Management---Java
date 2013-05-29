package Model;

public class Participant
{
	//attributes
	private Team team;
	private Guest participant;
	
	//constructor
	public Participant(Team team, Guest guest)
	{
		this.team=team;
		this.participant=guest;
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
	
	public void setGuest(Guest guest)
	{
		this.participant=guest;
	}
	public Guest getGuest()
	{
		return this.participant;
	}
}
