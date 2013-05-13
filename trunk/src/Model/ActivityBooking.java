package Model;

public class ActivityBooking 
{
	private int id;
	private String date;
	private String startHour;
	private String endHour;
	private String status;//booked, done, waitingList
	private int numberOfParticipants;
	private String place;
	
	private Guest guest;
	private ActivityType activityType;
	private Instructor instructor;
	
	//Constructor
	public ActivityBooking()
	{}
	
	public ActivityBooking(int id)
	{
		this.id = id;
	}
	
	public ActivityBooking(int id, String date, String startHour, String endHour,
			String status, int numberOfParticipants, String place, 
			Guest guest, ActivityType activityType, Instructor instructor)
	{
		this.id = id;
		this.date = date;
		this.startHour = startHour;
		this.endHour = endHour;
		this.status = status;
		this.numberOfParticipants = numberOfParticipants;
		this.place = place;
	
		this.guest=guest;
		this.activityType=activityType;
		this.instructor=instructor;
	}

	//Getters and setters
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getDate()
	{
		return date;
	}
	public void setDate(String date)
	{
		this.date = date;
	}

	public String getStartHour()
	{
		return startHour;
	}
	public void setStartHour(String startHour)
	{
		this.startHour = startHour;
	}

	public String getEndHour()
	{
		return endHour;
	}
	public void setEndHour(String endHour)
	{
		this.endHour = endHour;
	}

	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}

	public int getNumberOfParticipants()
	{
		return numberOfParticipants;
	}
	public void setNumberOfParticipants(int numberOfParticipants)
	{
		this.numberOfParticipants = numberOfParticipants;
	}

	public String getPlace()
	{
		return place;
	}
	public void setPlace(String place)
	{
		this.place = place;
	}
	
	public Instructor getInstructor()
	{
		return instructor;
	}	
	public void setInstructor(Instructor instructor)
	{
		this.instructor=instructor;
	}
	
	public ActivityType getActivity()
	{
		return activityType;
	}	
	public void setActivity(ActivityType activityType)
	{
		this.activityType=activityType;
	}
	
	public Guest getGuest() 
	{
		return guest;
	}
	public void setGuest(Guest guest)
	{
		this.guest = guest;
	}
//end getters and setters
}
