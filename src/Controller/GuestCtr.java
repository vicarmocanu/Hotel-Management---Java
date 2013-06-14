package Controller;

import java.util.LinkedList;

import DBLayer.DBConnection1;
import DBLayer.DBGuest;
import DBLayer.DBPerson;
import DBLayer.DBTravelAgency;
import DBLayer.IFDBGuest;
import DBLayer.IFDBPerson;
import DBLayer.IFDBTravelAgency;
import Model.Guest;
import Model.Person;
import Model.TravelAgency;

public class GuestCtr
{
	public GuestCtr(){}

	public LinkedList<Guest> getAllGuests()
	{
		IFDBGuest dbGuest = new DBGuest();
		LinkedList<Guest> guestList = new LinkedList<Guest>();
		guestList = dbGuest.getAllGuests();
		return guestList;
	}
	
	public Guest searchGuestById(int personId)
	{
		IFDBGuest dbGuest = new DBGuest();
		Guest guestObj = new Guest();
		guestObj = dbGuest.searchGuestById(personId);
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
	
	public void insertGuest(String guestName, int travelAgencyCVR, String guestType)
	{
		IFDBPerson dbPerson = new DBPerson();
		Person personReferenceObj = new Person();
		personReferenceObj = dbPerson.searchPersonByName(guestName, true);
		int referenceId = personReferenceObj.getId();
		
		Guest guestObj = new Guest();
		guestObj.setId(referenceId);
		guestObj.setGuestType(guestType);
		
		IFDBTravelAgency dbTravelAgency = new DBTravelAgency();
		TravelAgency travelAgencyObj = new TravelAgency();
		if(travelAgencyCVR == 0)
		{
			travelAgencyObj = null;
			guestObj.setTravelAgency(travelAgencyObj);
		}
		else
		{
			travelAgencyObj = dbTravelAgency.getTravelAgencyByCVR(travelAgencyCVR, true);
			guestObj.setTravelAgency(travelAgencyObj);
		}
		
		try
		{
			DBConnection1.startTransaction();
			DBGuest dbGuest =new DBGuest();
			dbGuest.insertGuest(guestObj);
			DBConnection1.commitTransaction();
		}
		catch(Exception e)
		{
			DBConnection1.rollbackTransaction();
		}
	}
	
	public int updateGuest(int id, String guestType, int travelAgencyCVR)
	{
		IFDBGuest dbGuest = new DBGuest();
		Guest guestObj = new Guest();
		
		guestObj.setId(id);
		guestObj.setGuestType(guestType);
		
		IFDBTravelAgency dbTravelAgency = new DBTravelAgency();
		TravelAgency travelAgencyObj = new TravelAgency();
		if(travelAgencyCVR == 0)
		{
			travelAgencyObj = null;
			guestObj.setTravelAgency(travelAgencyObj);
		}
		else
		{
			travelAgencyObj = dbTravelAgency.getTravelAgencyByCVR(travelAgencyCVR, true);
			guestObj.setTravelAgency(travelAgencyObj);
		}
		
		return dbGuest.updateGuest(guestObj);
	}
	
	public int deleteGuest(int id)
	{
		IFDBGuest dbGuest = new DBGuest();
		return dbGuest.deleteGuest(id);
	}
}
