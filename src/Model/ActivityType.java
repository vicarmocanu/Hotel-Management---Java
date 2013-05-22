package Model;

public class ActivityType 
{
	//Attributes
	private int id;
	private String name;
	private int maxParticipants;	
	
	//Constructor
	
	public ActivityType()
	{}
	
	public ActivityType(int id)
	{
		this.id=id;
	}
	
	public ActivityType(String name, int maxParticipants)
	{
		this.name=name;
		this.maxParticipants=maxParticipants;
	}
	
	public ActivityType(int id, String name, int maxParticipants)
	{
		this.id=id;
		this.name=name;
		this.maxParticipants=maxParticipants;
	}
	
	//Getters and setters
	
	//id
	public int getID()
	{
		return this.id;
	}
	public void setID(int id)
	{
		this.id=id;
	}
	
	//name
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	//maxParticipants
	public int getMaxParticipants()
	{
		return this.maxParticipants;
	}
	public void setMaxParticipants(int maxParticipants)
	{
		this.maxParticipants=maxParticipants;
	}
}