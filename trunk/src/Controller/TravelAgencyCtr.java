package Controller;

import java.util.LinkedList;

import DBLayer.DBConnection1;
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
	
	public void insertTravelAgency(int CVR, String name, int zipcode, String country,
			String address, String phoneNo, String email)
	{
		TravelAgency travelAgencyObj = new TravelAgency();
		travelAgencyObj.setCVR(CVR);
		travelAgencyObj.setName(name);
		travelAgencyObj.setZipCode(zipcode);
		travelAgencyObj.setCountry(country);
		travelAgencyObj.setAddress(address);
		travelAgencyObj.setPhoneNo(phoneNo);
		travelAgencyObj.setEmail(email);
		
		try
		{
			DBConnection1.startTransaction();
			DBTravelAgency dbTravelAgency = new DBTravelAgency();
			dbTravelAgency.insertTravelAgency(travelAgencyObj);
			DBConnection1.commitTransaction();
		}
		catch(Exception e)
		{
			DBConnection1.rollbackTransaction();
		}
	}
	
	public int updateTravelAgency(int cvr, String name, int zipcode, String country,
			String address, String phoneNo, String email)
	{
		TravelAgency travelAgencyObj = new TravelAgency();
		travelAgencyObj.setCVR(cvr);
		travelAgencyObj.setName(name);
		travelAgencyObj.setZipCode(zipcode);
		travelAgencyObj.setCountry(country);
		travelAgencyObj.setAddress(address);
		travelAgencyObj.setPhoneNo(phoneNo);
		travelAgencyObj.setEmail(email);
		
		IFDBTravelAgency dbTravelAgency = new DBTravelAgency();
		return dbTravelAgency.updateTravelAgency(travelAgencyObj);
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
	
	public boolean checkTravelAgencyInstanceCount(int cvr, String name, int zipcode, String country, String address)
	{
		boolean check = false;
		int instances = 0;
		
		IFDBTravelAgency dbTravelAgency = new DBTravelAgency();
		instances = dbTravelAgency.getTravelAgencyInstances(cvr, name, zipcode, country, address);
		
		if(instances == 0)
		{
			check = true;
		}
		else
		{
			check = false;
		}
		
		return check;
	}
}
