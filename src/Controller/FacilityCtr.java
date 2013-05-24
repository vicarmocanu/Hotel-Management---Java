package Controller;

import java.util.LinkedList;

import DBLayer.DBActivityType;
import DBLayer.DBConnection1;
import DBLayer.DBFacility;
import DBLayer.IFDBActivityType;
import DBLayer.IFDBFacility;
import Model.ActivityType;
import Model.Facility;

public class FacilityCtr
{
	public FacilityCtr()
	{}
	
	public Facility getFacilityById(int id)
	{
		IFDBFacility dbFacility = new DBFacility();
		Facility facilityObj = new Facility();
		facilityObj = dbFacility.getFacilityById(id, true);
		return facilityObj;
	}
	
	public LinkedList<Facility> getAllFacilities()
	{
		IFDBFacility dbFacility = new DBFacility();
		LinkedList<Facility> facilityList = new LinkedList<Facility>();
		facilityList = dbFacility.getAllFacilities(true);
		return facilityList;
	}
	
	public Facility getFacilityForActivity(int activityId)
	{
		IFDBFacility dbFacility = new DBFacility();
		Facility facilityObj = new Facility();
		facilityObj = dbFacility.getFacilityForActivity(activityId, true);
		return facilityObj;
	}
	
	public Facility getFacilityByName(String name)
	{
		IFDBFacility dbFacility = new DBFacility();
		Facility facilityObj = new Facility();
		facilityObj = dbFacility.getFacilityByName(name, true);
		return facilityObj;
	}
	
	public void insertFacility(String name, int activityId, String status)
	{
		IFDBActivityType dbActivity = new DBActivityType();
		ActivityType activityObj = dbActivity.getActivityTypeByID(activityId, false);
		
		Facility facilityObj = new Facility(name, activityObj, status);
		
		try
		 {
			 DBConnection1.startTransaction();
			 DBFacility dbFacility = new DBFacility();
			 dbFacility.insertFacility(facilityObj);
			 DBConnection1.commitTransaction();
		 }
		 catch(Exception e)
		 {
			 DBConnection1.rollbackTransaction();
		 }
	}
	
	public int updateFacility(int id, String name, int activityId, String status)
	{
		IFDBFacility dbFacility = new DBFacility();
		IFDBActivityType dbActivity = new DBActivityType();
		
		ActivityType activityObj = dbActivity.getActivityTypeByID(activityId, false);
		
		Facility facilityObj = new Facility(id, name, activityObj, status);
		return dbFacility.updateFacility(facilityObj);
	}
	
	public int deleteFacility(int id)
	{
		IFDBFacility dbFacility = new DBFacility();
		return dbFacility.deleteFacility(id);
	}
}
