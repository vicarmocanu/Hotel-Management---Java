package Controller;

import java.util.LinkedList;

import DBLayer.DBActivityType;
import DBLayer.DBConnection1;
import DBLayer.IFDBActivityType;
import Model.ActivityType;

public class ActivityCtr
{
	public ActivityCtr(){}
	
	public LinkedList<ActivityType> getAllActivityTypes()
	{
		IFDBActivityType dbActivity = new DBActivityType();
		LinkedList<ActivityType> allActivityTypes = new LinkedList<ActivityType>();
		allActivityTypes = dbActivity.getAllActivityTypes(false);
		return allActivityTypes;
	}
	
	public ActivityType getActivityTypeByID(int id)
	{
		IFDBActivityType dbActivity = new DBActivityType();
		ActivityType activityObj = new ActivityType();
		activityObj=dbActivity.getActivityTypeByID(id, false);
		return activityObj;
	}
	
	public ActivityType getActivityTypeByName(String name)
	{
		IFDBActivityType dbActivity = new DBActivityType();
		ActivityType activityObj = new ActivityType();
		activityObj = dbActivity.getActivityTypeByName(name, false);
		return activityObj;
	}
	
	public void insertActivityType(String name, int maxParticipants)
	{
		ActivityType activityObj = new ActivityType(name, maxParticipants);
		
		try
		 {
			 DBConnection1.startTransaction();
			 DBActivityType dbActivityType =new DBActivityType();
			 dbActivityType.insertActivityType(activityObj);
			 DBConnection1.commitTransaction();
		 }
		 catch(Exception e)
		 {
			 DBConnection1.rollbackTransaction();
		 }
	}
	
	public int updateActivityType(int id, String name, int maxParticipants)
	{
		IFDBActivityType dbActivityType = new DBActivityType();
		ActivityType activityObj = new ActivityType(id, name, maxParticipants);
		return dbActivityType.updateActivityType(activityObj);
	}
	
	public int deleteActivityTypeByID(int id)
	{
		IFDBActivityType dbActivityType = new DBActivityType();
		return dbActivityType.deleteActivityTypeByID(id);
	}
	
	public int deleteActivityTypeByName(String name)
	{
		IFDBActivityType dbActivityType = new DBActivityType();
		return dbActivityType.deleteActivityTypeByName(name);
	}
}
