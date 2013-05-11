package Model;
import java.util.ArrayList;
import java.util.Iterator;


public class Guest extends Person{
	private String type;
	private ArrayList<ActivityBooking> activityBookings;
	private ArrayList<RoomBooking> roomBookings;

	public Guest()
	{
		super();
	}
	
	public Guest(int id)
	{
		super(id);
	}
	
	public Guest(int id, String name, String address, int zipcode, String city,
			String country, String phoneNo, String email, String password, String type) {
		super(id, name, address, zipcode, city, country, phoneNo, email, password);
		this.type = type;
	}

//Getters and setters
	//type
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void setId(int id)
	{
		super.setId(id);
	}
	
	public int getId()
	{
	    return super.getId();
	}
	
	public void setName(String name)
	{
		super.setName(name);
	}
	
	public String getName()
	{
		return super.getName();
		
	}
	
	public void setCity(String city)
	{
		super.setCity(city);
	}
	
	public String getCity()
	{
		return super.getCity();
	}
	
	public void setCountry(String country)
	{
		super.setCountry(country);
	}
	
	public String getCountry()
	{
		return super.getCountry();
	}
	
	public void setAddress(String address)
	{
		super.setAddress(address);
	}
	
	public String getAddress()
	{
		return super.getAddress();
	}
	
	public void setZipCode(int zipcode)
	{
		super.setZipcode(zipcode);
	}
	
	public int getZipCode()
	{
		return super.getZipcode();
	}
	
	public void setPhoneNo(String phoneNo)
	{
		super.setPhoneNo(phoneNo);
	}
	
	public String getPhoneNo()
	{
		return super.getPhoneNo();
	}
	
	public void setEmail(String email)
	{
		super.setEmail(email);
	}
	
	public String getEmail()
	{
		return super.getEmail();
	}
	
	public void setPassword(String password)
	{
		super.setPassword(password);
	}
	
	public String getPassword()
	{
		return super.getPassword();
	}
//end getters and setters
	public void addActivityBooking(ActivityBooking activityBooking){
		activityBookings.add(activityBooking);
	}
	
	public void removeActivityBooking(ActivityBooking activityBooking) {
		activityBookings.remove(activityBooking);
	}
	
	public ActivityBooking getActivityBooking(int id) throws Exception {  
		ActivityBooking activityBooking = null;
		        boolean found = false;
		       Iterator<ActivityBooking> it = activityBookings.iterator();
		        while(it.hasNext() && !found)
		        {
		        	ActivityBooking booking = it.next();
		            if(booking.getId() == id)
		            {
		            	activityBooking = booking;
		                found = true;
		            } 
		        }
		        if(!found) throw new Exception("Booking was not found");
		        return activityBooking;
		 }
	
	public void setActivtyBooking(ArrayList<ActivityBooking> activityBooking){
		this.activityBookings= activityBooking;
	}
	
	public void addRoomBooking(RoomBooking roomBooking){
	roomBookings.add(roomBooking);
	}
	
	public void removeRoomBooking(RoomBooking roomBooking) {
		roomBookings.remove(roomBooking);
	}
	
	public RoomBooking getRoomBooking(int id) throws Exception {  
		RoomBooking roomBooking = null;
		        boolean found = false;
		       Iterator<RoomBooking> it = roomBookings.iterator();
		        while(it.hasNext() && !found)
		        {
		        	RoomBooking rBookings = it.next();
		            if(rBookings.getId() == id)
		            {
		            	roomBooking = rBookings;
		                found = true;
		            } 
		        }
		        if(!found) throw new Exception("Room Booking was not found");
		        return roomBooking;
		 }
	
	public void setRoomBooking(ArrayList<RoomBooking> roomBooking){
		this.roomBookings= roomBooking;
	}
	
	
}
