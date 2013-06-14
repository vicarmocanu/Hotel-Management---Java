package Controller;

import java.util.LinkedList;

import DBLayer.DBActivityType;
import DBLayer.DBInstructor;
import DBLayer.DBPerson;
import DBLayer.IFDBActivityType;
import DBLayer.IFDBInstructor;
import DBLayer.IFDBPerson;
import Model.ActivityType;
import Model.Instructor;
import Model.Person;

public class InstructorCtr
{
	public InstructorCtr(){}
	
	public LinkedList<Instructor> getAllInstructors()
	{
		IFDBInstructor dbInstructor = new DBInstructor();
		LinkedList<Instructor> instructorList = new LinkedList<Instructor>();
		instructorList = dbInstructor.getAllInstructors();
		return instructorList;
	}
	
	public Instructor getInstructorById(int employeeId)
	{
		IFDBInstructor dbInstructor = new DBInstructor();
		Instructor instructorObj = new Instructor();
		instructorObj = dbInstructor.getInstructorById(employeeId);
		return instructorObj;
	}
	
	public void insertInstructor(String instructorName, int activityTypeId, double price, String status)
	{
		IFDBPerson dbPerson = new DBPerson();
		Person personReferenceObj = new Person();
		personReferenceObj = dbPerson.searchPersonByName(instructorName, true);
		int referenceId = personReferenceObj.getId();
		
		IFDBActivityType dbActivityType = new DBActivityType();
		ActivityType activityTypeObj = new ActivityType();
		activityTypeObj = dbActivityType.getActivityTypeByID(activityTypeId);
		
		Instructor instructorObj = new Instructor();
		instructorObj.setId(referenceId);
		
		if(activityTypeObj != null)
		{
			instructorObj.setActivityType(activityTypeObj);
		}
		instructorObj.setStatus(status);
		instructorObj.setPrice(price);
	}
	
	public int updateInstructor(int employeeId, int activityTypeId, double price, String status)
	{
		IFDBActivityType dbActivityType = new DBActivityType();
		ActivityType activityTypeObj = new ActivityType();
		activityTypeObj = dbActivityType.getActivityTypeByID(activityTypeId);
		
		Instructor instructorObj = new Instructor();
		instructorObj.setId(employeeId);
		instructorObj.setPrice(price);
		instructorObj.setStatus(status);
		
		if(activityTypeObj != null)
		{
			instructorObj.setActivityType(activityTypeObj);
		}
		
		IFDBInstructor dbInstructor = new DBInstructor();
		return dbInstructor.updateInstructor(instructorObj);
	}
	
	public int deleteInstructorById(int employeeId)
	{
		IFDBInstructor dbInstructor = new DBInstructor();
		return dbInstructor.deleteInstructorById(employeeId);
	}
	
	public LinkedList<Instructor> getAvailableInstructorsForActivityList(int activityTypeId, String status)
	{
		IFDBInstructor dbInstructor = new DBInstructor();
		LinkedList<Instructor> availableInstructorForActivityList = new LinkedList<Instructor>();
		availableInstructorForActivityList =dbInstructor.getActivityAvailableInstructors(activityTypeId, status);
		return availableInstructorForActivityList;
	}

}
