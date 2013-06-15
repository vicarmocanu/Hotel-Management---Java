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
		facilityObj = dbFacility.getFacilityById(id);
		return facilityObj;
	}
	
	public LinkedList<Facility> getAllFacilities()
	{
		IFDBFacility dbFacility = new DBFacility();
		LinkedList<Facility> facilityList = new LinkedList<Facility>();
		facilityList = dbFacility.getAllFacilities();
		return facilityList;
	}
	
	public LinkedList<Facility> getFacilityForActivity(int activityId)
	{
		IFDBFacility dbFacility = new DBFacility();
		LinkedList<Facility> facilityList = new LinkedList<Facility>();
		facilityList = dbFacility.getFacilitiesForActivity(activityId);
		return facilityList;
	}
	
	public Facility getFacilityByName(String name)
	{
		IFDBFacility dbFacility = new DBFacility();
		Facility facilityObj = new Facility();
		facilityObj = dbFacility.getFacilityByName(name);
		return facilityObj;
	}
	
	public void insertFacility(String name, int activityId, String status)
	{
		Facility facilityObj = new Facility();
		facilityObj.setName(name);
		facilityObj.setStatus(status);
		
		IFDBActivityType dbActivityType = new DBActivityType();
		ActivityType activityTypeObj = new ActivityType();
		if(activityId == 0)
		{
			activityTypeObj = null;
			facilityObj.setActivity(activityTypeObj);
		}
		else
		{
			activityTypeObj = dbActivityType.getActivityTypeByID(activityId);
			facilityObj.setActivity(activityTypeObj);
		}
		
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
		Facility facilityObj = new Facility();
		facilityObj.setId(id);
		facilityObj.setName(name);
		facilityObj.setStatus(status);
		
		IFDBActivityType dbActivity = new DBActivityType();
		ActivityType activityTypeObj = new ActivityType();
		
		if(activityId == 0)
		{
			activityTypeObj = null;
			facilityObj.setActivity(activityTypeObj);
		}
		else
		{
			activityTypeObj = dbActivity.getActivityTypeByID(activityId);
			facilityObj.setActivity(activityTypeObj);
		}
		
		return dbFacility.updateFacility(facilityObj);
	}
	
	public int deleteFacility(int id)
	{
		IFDBFacility dbFacility = new DBFacility();
		return dbFacility.deleteFacility(id);
	}
	
	public LinkedList<Facility> getAvailableFacilitiesForActivity(int activityId, String status)
	{
		IFDBFacility dbFacility = new DBFacility();
		LinkedList<Facility> availableFacilitiesForActivity = new LinkedList<Facility>();
		availableFacilitiesForActivity = dbFacility.getAvailableFacilitiesForActivity(activityId, status);
		return availableFacilitiesForActivity;
	}
	
	public boolean checkFacilityInstanceCount(int id, String name)
	{
		boolean check = false;
		int instances = 0;
		
		IFDBFacility dbFacility = new DBFacility();
		instances = dbFacility.getFacilityInstances(id, name);
		
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
