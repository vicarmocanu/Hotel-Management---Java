package Model;


public class ActivityBooking 
{
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
		this.date=date;
		this.date = new String();
		this.status = status;
		this.guest=guest;
	}
	
	public ActivityBooking(String date, String status, Guest guest)
	{
		this.date=date;
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
		this.date = date;
	}
	
	
}
