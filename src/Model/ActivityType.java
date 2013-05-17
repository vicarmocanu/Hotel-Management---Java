package Model;

public class ActivityType 
{
	//Atributes
	private int id;
	private String name;
	private String description;
	private int maxParticipants;
	private double price;
	
	//Constructor
	
	public ActivityType()
	{}
	
	public ActivityType(int id)
	{
		this.id=id;
	}
	
	public ActivityType(String name, String description, int maxParticipants, double price)
	{
		this.name=name;
		this.description=description;
		this.maxParticipants=maxParticipants;
		this.price=price;
	}
	
	public ActivityType(int id, String name, String description, int maxParticipants, double price)
	{
		this.id=id;
		this.name=name;
		this.description=description;
		this.maxParticipants=maxParticipants;
		this.price=price;
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
	
	//description
	public String getDescription()
	{
		return this.description;
	}
	public void setDescription(String description)
	{
		this.description=description;
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
	
	//price
	public double getPrice()
	{
		return this.price;
	}
	public void setPrice(double price)
	{
		this.price=price;
	}
}