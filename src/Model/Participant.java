package Model;

public class Participant
{
	//attributes
	private Team team;
	private Guest guest;
	
	//constructor
	public Participant(Team team, Guest guest)
	{
		this.team=team;
		this.guest=guest;
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
		this.guest=guest;
	}
	public Guest getGuest()
	{
		return this.guest;
	}
}
