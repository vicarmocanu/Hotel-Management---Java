package Model;

public class Facility
{
	//attributes
	private int id;
	private String name;
	private String description;
	private ActivityType activity;
	private String status;
	
	
	//constructors
	public Facility()
	{}
	
	public Facility(int id, String name, String description, ActivityType activity, String status)
	{
		this.id=id;
		this.name=name;
		this.description=description;
		this.activity=activity;
		this.status=status;
	}
	
	public Facility(int id)
	{
		this.id=id;
	}
	
	//getters and setters
	
	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	public void setDescription(String description)
	{
		this.description=description;
	}
	
	public ActivityType getActivity()
	{
		return this.activity;
	}
	public void setActivity(ActivityType activity)
	{
		this.activity=activity;
	}
	
	public String getStatus()
	{
		return this.status;
	}
	public void setStatus(String status)
	{
		this.status=status;
	}
}
