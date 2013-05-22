package Model;

import java.util.Date;
import java.text.SimpleDateFormat;

public class ActivityLine
{
	//attributes
	private ActivityType activity;
	private Instructor instructor;
	
	SimpleDateFormat days=new SimpleDateFormat("mm-dd-yyyy");
	private Date date;
	
	SimpleDateFormat hours=new SimpleDateFormat("HH:mm");
	private Date startHour;
	private Date endHour;
	
	private Facility facility;
	private ActivityBooking activityBooking;
	
	//constructor
	public ActivityLine (ActivityType activity, Instructor instructor, String date, String startHour, String endHour,
			Facility facility, ActivityBooking activityBooking)
	{
		this.activity=activity;
		this.instructor=instructor;
		
		try
		{
			this.date=days.parse(date);
		}
		catch(Exception e)
		{
			System.out.println("Cannot create activity booking. Problem with date input.");
		}
		
		try
		{
			this.startHour=hours.parse(startHour);
		}
		catch(Exception e)
		{
			System.out.println("Cannot create activity booking. Problem with start date input.");
		}
		
		try
		{
			this.endHour=hours.parse(endHour);
		}
		catch(Exception e)
		{
			System.out.println("Cannot create activity booking. Problem with end date input.");
		}
		
		this.facility=facility;
		this.activityBooking=activityBooking;
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
	
	public Date getDate()
	{
		return this.date;
	}
	public String getStringDate()
	{
		String date=days.format(getDate());
		return date;
	}
	public void setDate(String date)
	{
		try
		{
			this.date=days.parse(date);
		}
		catch(Exception e)
		{
			System.out.println("Cannot create activity booking. Problem with date input.");
		}
	}
	
	public Date getStartHour()
	{
		return this.startHour;
	}
	public String getStringStartHour()
	{
		String startHour=hours.format(getStartHour());
		return startHour;
	}
	public void setStartHour(String startHour)
	{
		try
		{
			this.startHour=hours.parse(startHour);
		}
		catch(Exception e)
		{
			System.out.println("Cannot create activity booking. Problem with start date input.");
		}
	}
	
	public Date getEndHour()
	{
		return this.endHour;
	}
	public String getStringEndHour()
	{
		String endHour=hours.format(getEndHour());
		return endHour;
	}
	public void setEndHour(String endHour)
	{
		try
		{
			this.endHour=hours.parse(endHour);
		}
		catch(Exception e)
		{
			System.out.println("Cannot create activity booking. Problem with end date input.");
		}
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

}
