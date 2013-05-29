package Controller;

import java.util.LinkedList;

import DBLayer.DBConnection1;
import DBLayer.DBGuest;
import DBLayer.DBPerson;
import DBLayer.DBTravelAgency;
import DBLayer.IFDBGuest;
import DBLayer.IFDBTravelAgency;
import Model.Guest;
import Model.Person;
import Model.TravelAgency;

public class GuestCtr {
	
	public GuestCtr()
	{
		
	}
	
	 public LinkedList<Guest> getAllGuests()
	 {
		 IFDBGuest dbGuest = new DBGuest();
			LinkedList<Guest> guestList = new LinkedList<Guest>();
			guestList = dbGuest.getAllGuests(true);
			return guestList; 
	 }
	   

	    public Guest searchGuestByName(String name)
	    {
	    	IFDBGuest dbGuest = new DBGuest();
			Guest guestObj = new Guest();
			guestObj = dbGuest.searchGuestByName(name, true);
			return guestObj;
	    }
	    
	    public Guest searchGuestById(int personId)
	    {
	    	IFDBGuest dbGuest = new DBGuest();
			Guest guestObj = new Guest();
			guestObj = dbGuest.searchGuestById(personId, true);
			return guestObj;
	    }
	    
	    //find guest who was in this room on that date
	    public Guest findGuestInRoom(String date, int roomNo)
		{
			IFDBGuest dbGuest = new DBGuest();
			Guest guestObj = new Guest();
			guestObj = dbGuest.findGuestInRoom(date, roomNo, true);
			return guestObj;
		}
	    

	    public void insertGuest(int id, String name, String address, int zipcode,
				String country, String phoneNo, String email, String personType, String password, String guestType, 
				int travelAgencyCVR)
	    {
	    	Guest guestObj = new Guest();
	    	Person personObj = new Person();
	    	personObj.setId(id);
	    	personObj.setName(name);
	    	personObj.setAddress(address);
	    	personObj.setZipcode(zipcode);
	    	personObj.setCountry(country);
	    	personObj.setPhoneNo(phoneNo);
	    	personObj.setEmail(email);
	    	personObj.setPersonType(personType);
	    	personObj.setPassword(password);
	    	
	    	guestObj.setId(id);
	    	guestObj.setGuestType(guestType);
	    	IFDBTravelAgency dbTravelAgency = new DBTravelAgency();
	    	TravelAgency travelAgencyObj = new TravelAgency();
	    	travelAgencyObj = dbTravelAgency.getTravelAgencyByCVR(travelAgencyCVR, true);
	    	if(travelAgencyObj != null){
	    	guestObj.setTravelAgency(travelAgencyObj);
	    	}
	    	
			try
			 {
				 DBConnection1.startTransaction();
				 DBPerson dbPerson = new DBPerson();
				 dbPerson.insertPerson(personObj);
				 DBGuest dbGuest =new DBGuest();
				 dbGuest.insertGuest(guestObj);
				 DBConnection1.commitTransaction();
			 }
			 catch(Exception e)
			 {
				 DBConnection1.rollbackTransaction();
			 }
	    }
	    
	   
	    public int updateGuest(int id, String name, String address, int zipcode,
				String country, String phoneNo, String email, String personType, String password, String guestType, 
				int travelAgencyCVR)
	    {
	    	IFDBGuest dbGuest = new DBGuest();
			Guest guestObj = new Guest();
	    	
	    	guestObj.setId(id);
	    	guestObj.setGuestType(guestType);
	    	IFDBTravelAgency dbTravelAgency = new DBTravelAgency();
	    	TravelAgency travelAgencyObj = new TravelAgency();
	    	travelAgencyObj = dbTravelAgency.getTravelAgencyByCVR(travelAgencyCVR, true);
	    	if(travelAgencyObj != null)
	    	{
	    	guestObj.setTravelAgency(travelAgencyObj);
	    	}
	 
			return dbGuest.updateGuest(guestObj);
	    }
	    
	    //delete Customer by Id    
	    public int deleteGuest(int id)
	    {
	    	IFDBGuest dbGuest = new DBGuest();
			return dbGuest.deleteGuest(id);
	    }

}
