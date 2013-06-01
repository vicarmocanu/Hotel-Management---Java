package Model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ActivityBooking 
{
	final static String DATE_FORMAT = "dd-MM-yyyy";
	
	private int id;
	private String date;
	private String status;//booked, done, waitingList
	private Guest guest;
	
	//Constructor
	public ActivityBooking()
	{}
	
	public ActivityBooking(int id)
	{
		this.id = id;
	}
	
	public ActivityBooking(int id, String date, String status, Guest guest)
	{
		this.id = id;
		if(isDateValid(date)==true)
		{
			this.date=date;
		}
		else
		{
			System.out.println("Problem with date input.");
			this.date = new String();
		}
		this.status = status;
		this.guest=guest;
	}
	
	public ActivityBooking(String date, String status, Guest guest)
	{
		if(isDateValid(date)==true)
		{
			this.date=date;
		}
		else
		{
			System.out.println("Problem with date input.");
			this.date = new String();
		}
		this.status = status;
		this.guest=guest;
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

	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public Guest getGuest() 
	{
		return guest;
	}
	public void setGuest(Guest guest)
	{
		this.guest = guest;
	}
	
	public String getDate()
	{
		return this.date;
	}
	public void setDate(String date)
	{
		if(isDateValid(date)==true)
		{
			this.date=date;
		}
		else
		{
			System.out.println("Problem with date input.");
			this.date = new String();
		}
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
