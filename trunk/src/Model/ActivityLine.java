package Model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ActivityLine
{
	final static String DATE_FORMAT = "dd-MM-yyyy";
	
	//attributes
	private ActivityType activity;
	private Instructor instructor;	
	
	private String date;
	private String startHour;
	private String endHour;
	
	private Facility facility;
	private ActivityBooking activityBooking;
	
	//constructor
	public ActivityLine (ActivityType activity, Instructor instructor, String date, String startHour, String endHour,
			Facility facility, ActivityBooking activityBooking)
	{
		this.activity=activity;
		this.instructor=instructor;
		
		if(isDateValid(date)==true)
		{
			this.date = date;
		}
		else
		{
			System.out.println("Problem with date input.");
			this.date = new String();
		}
		this.startHour=startHour;
		this.endHour=endHour;
		
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
	
	public String getDate()
	{
		return this.date;
	}
	
	public void setDate(String date)
	{
		if(isDateValid(date)==true)
		{
			this.date = date;
		}
		else
		{
			System.out.println("Problem with date input.");
			this.date = new String();
		}
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
	
	public static boolean isDateValid(String date) 
	{
		try
		{
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			df.setLenient(false);
			df.parse(date);
			return true;
		} 
		catch (ParseException e)
		{
			return false;
		}
	}

}
