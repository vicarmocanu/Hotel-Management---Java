package Controller;

import java.util.LinkedList;

import DBLayer.DBConnection1;
import DBLayer.DBLocation;
import DBLayer.IFDBLocation;
import Model.Location;

public class LocationCtr
{
	public Location getLocation(int zipcode, String country)
	{
		IFDBLocation dbLocation = new DBLocation();
		Location locationObj = new Location();
		locationObj = dbLocation.getLocation(zipcode, country);
		return locationObj;
	}
	
	public Location getCompleteLocation(int zipcode, String country, String city)
	{
		IFDBLocation dbLocation = new DBLocation();
		Location locationObj = new Location();
		locationObj = dbLocation.getCompleteLocation(zipcode, country, city);
		return locationObj;
	}
	
	public LinkedList<Location> getAllLocations()
	{
		IFDBLocation dbLocation = new DBLocation();
		LinkedList<Location> locationList = new LinkedList<Location>();
		locationList = dbLocation.getAllLocations();
		return locationList;
	}
	
	public int deleteLocation(int zipcode, String country)
	{
		IFDBLocation dbLocation = new DBLocation();
		return dbLocation.deleteLocation(zipcode, country);
	}
	
	public int updateLocation(int zipCode, String country, String city)
	{
		IFDBLocation dbLocation = new DBLocation();
		Location locationObj = new Location();
		locationObj = dbLocation.getLocation(zipCode, country);
		locationObj.setZipCode(zipCode);
		locationObj.setCountry(country);
		locationObj.setCity(city);
		
		return dbLocation.updateLocation(locationObj);
	}
	
	public void insertLocation(int zipCode, String country, String city)
	{
		Location locationObj = new Location();
		locationObj.setZipCode(zipCode);
		locationObj.setCountry(country);
		locationObj.setCity(city);
		
		try
		{
			DBConnection1.startTransaction();
			DBLocation dbLocation = new DBLocation();
			dbLocation.insertLocation(locationObj);
			DBConnection1.commitTransaction();
		}
		catch(Exception e)
		{
			DBConnection1.rollbackTransaction();
		}
	}
}
