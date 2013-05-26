package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityBooking 
{
	SimpleDateFormat days=new SimpleDateFormat("mm-dd-yyyy");
	
	private int id;
	private Date date;
	private String status;//booked, done, waitingList
	private Guest guest;
	private Team team;
	
	//Constructor
	public ActivityBooking()
	{}
	
	public ActivityBooking(int id)
	{
		this.id = id;
	}
	
	public ActivityBooking(int id, String date, String status, Guest guest, Team team)
	{
		this.id = id;
		
		try
		{
			this.date=days.parse(date);
		}
		catch(Exception e)
		{
			System.out.println("Cannot create activity booking. Problem with date input.");
		}
		
		this.status = status;
		this.guest=guest;
		this.team=team;
	}
	
	public ActivityBooking(String date, String status, Guest guest, Team team)
	{
		try
		{
			this.date=days.parse(date);
		}
		catch(Exception e)
		{
			System.out.println("Cannot create activity booking. Problem with date input.");
		}
		
		this.status = status;
		this.guest=guest;
		this.team=team;
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
	
	public Team getTeam()
	{
		return this.team;
	}
	public void setTeam(Team team)
	{
		this.team=team;
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

}
