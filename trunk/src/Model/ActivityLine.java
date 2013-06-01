package Model;

public class ActivityLine
{
	
	//attributes
	private ActivityType activity;
	private ActivityBooking activityBooking;
	private String date;
	private String startHour;
	private String endHour;
	private Facility facility;
	private Instructor instructor;
	private Team team;
	
	
	
	
	//constructor
	public ActivityLine (ActivityType activity, ActivityBooking activityBooking,
			String date, String startHour, String endHour, Facility facility,
			Instructor instructor, Team team)
	{
		this.activity=activity;
		this.activityBooking=activityBooking;
		this.date = date;
		this.startHour=startHour;
		this.endHour=endHour;
		this.facility=facility;
		this.instructor=instructor;
		this.setTeam(team);
	}
	
	public ActivityLine ()
	{}
	
	//getters and setters
	
	public ActivityType getActivity()
	{
		return this.activity;
	}
	public void setActivity(ActivityType activity)
	{
		this.activity=activity;
	}
	
	public Instructor getInstructor()
	{
		return this.instructor;
	}
	public void setInstructor(Instructor instructor)
	{
		this.instructor=instructor;
	}
	
	public String getDate()
	{
		return this.date;
	}
	public void setDate(String date)
	{
		this.date = date;
	}
	
	public String getStartHour()
	{
		return this.startHour;
	}
	public void setStartHour(String startHour)
	{
		this.startHour = startHour;
	}
	
	public String getEndHour()
	{
		return this.endHour;
	}
	public void setEndHour(String endHour)
	{
		this.endHour = endHour;
	}
	
	public Facility getFacility()
	{
		return this.facility;
	}
	public void setFacility(Facility facility)
	{
		this.facility=facility;
	}
	
	public ActivityBooking getActivityBooking()
	{
		return this.activityBooking;
	}
	public void setActivityBooking(ActivityBooking activityBooking)
	{
		this.activityBooking=activityBooking;
	}	
	

	public Team getTeam()
	{
		return team;
	}
	public void setTeam(Team team)
	{
		this.team = team;
	}

}
