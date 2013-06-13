package Controller;

import java.util.LinkedList;

import DBLayer.DBConnection1;
import DBLayer.DBLocation;
import DBLayer.IFDBLocation;
import Model.Location;

public class LocationCtr {
	
	public Location searchLocationByCity(String city, boolean retriveAssociation)
	{
		IFDBLocation dbLocation = new DBLocation();
		Location locationObj = new Location();
		locationObj = dbLocation.searchLocationByCity(city, true);
		return locationObj;
	}
	public Location searchLocationByZipCode(int zipCode,boolean retriveAssociation)
	{
		IFDBLocation dbLocation = new DBLocation();
		Location locationObj = new Location();
		locationObj = dbLocation.searchLocationByZipCode(zipCode, true);
		return locationObj;
	}
	
	public LinkedList<Location> getAllLocations()
	{
		IFDBLocation dbLocation = new DBLocation();
		LinkedList<Location> locationList = new LinkedList<Location>();
		locationList = dbLocation.getAllLocation(true);
		return locationList;
	}
	
	public int deleteLocation(int loczipCode)
	{
		IFDBLocation dbLocation = new DBLocation();
		return dbLocation.deleteLocation(loczipCode);
	}
	
	public int updateLocation(int zipCode, String country, String city)
	{
		IFDBLocation dbLocation = new DBLocation();
		Location locationObj = new Location();
		locationObj = dbLocation.searchLocationByZipCode(zipCode, true);
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
