package Controller;

import java.util.LinkedList;
import DBLayer.DBTravelAgency;
import DBLayer.IFDBTravelAgency;
import Model.TravelAgency;

public class TravelAgencyCtr 
{
	public TravelAgencyCtr(){}
	
	public LinkedList<TravelAgency> getAllTravelAgencies()
	{
		IFDBTravelAgency dbTravelAgency = new DBTravelAgency();
		LinkedList<TravelAgency> travelAgencyList = new LinkedList<TravelAgency>();
		travelAgencyList = dbTravelAgency.getAllTravelAgencies(true);
		return travelAgencyList;
	}
	
	public TravelAgency getTravelAgencyByCVR(int cvr)
	{
		IFDBTravelAgency dbTravelAgency = new DBTravelAgency();
		TravelAgency travelAgencyObj = new TravelAgency();
		travelAgencyObj = dbTravelAgency.getTravelAgencyByCVR(cvr, true);
		return travelAgencyObj;
	}
	
	public TravelAgency getTravelAgencyByName(String name)
	{
		IFDBTravelAgency dbTravelAgency = new DBTravelAgency();
		TravelAgency travelAgencyObj = new TravelAgency();
		travelAgencyObj = dbTravelAgency.getTravelAgencyByName(name, true);
		return travelAgencyObj;
	}
	
	public void insertTravelAgency()
	{
		
	}
	
	public int updateTravelAgency(int cvr, String name, int zipcode, String country,
			String address, String phoneNo, String email)
	{
		TravelAgency travelAgency = new TravelAgency();
		travelAgency.setCVR(cvr);
		travelAgency.setName(name);
		travelAgency.setZipCode(zipcode);
		travelAgency.setCountry(country);
		travelAgency.setAddress(address);
		travelAgency.setPhoneNo(phoneNo);
		travelAgency.setEmail(email);
		
		IFDBTravelAgency dbTravelAgency = new DBTravelAgency();
		return dbTravelAgency.updateTravelAgency(travelAgency);
	}
	
	public int deleteTravelAgencyByCvr(int cvr)
	{
		IFDBTravelAgency dbTravelAgency = new DBTravelAgency();
		return dbTravelAgency.deleteTravelAgencyByCvr(cvr);
	}
	
	public int deleteTravelAgencyByName(String name)
	{
		IFDBTravelAgency dbTravelAgency = new DBTravelAgency();
		return dbTravelAgency.deleteTravelAgencyByName(name);
	}
}
