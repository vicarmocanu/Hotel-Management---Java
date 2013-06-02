package Controller;

import java.util.LinkedList;
import DBLayer.DBActivityType;
import DBLayer.DBConnection1;
import DBLayer.DBEmployee;
import DBLayer.DBInstructor;
import DBLayer.DBPerson;
import DBLayer.IFDBActivityType;
import DBLayer.IFDBInstructor;
import DBLayer.IFDBPerson;
import Model.ActivityType;
import Model.Employee;
import Model.Instructor;
import Model.Person;

public class InstructorCtr
{
	public InstructorCtr(){}
	
	public LinkedList<Instructor> getAllInstructors()
	{
		IFDBInstructor dbInstructor = new DBInstructor();
		LinkedList<Instructor> instructorList = new LinkedList<Instructor>();
		instructorList = dbInstructor.getAllInstructors(true);
		return instructorList;
	}
	
	public Instructor getInstructorById(int employeeId)
	{
		IFDBInstructor dbInstructor = new DBInstructor();
		Instructor instructorObj = new Instructor();
		instructorObj = dbInstructor.getInstructorById(employeeId, true);
		return instructorObj;
	}
	
	public void insertInstructor(int id, String name, String address, int zipcode,	String country, String phoneNo, 
			String email, String personType, String password, int activityTypeId, double price, String status, double salary)
	{
		Person personObj = new Person();
		personObj.setName(name);
		personObj.setAddress(address);
		personObj.setZipcode(zipcode);
		personObj.setCountry(country);
		personObj.setPhoneNo(phoneNo);
		personObj.setEmail(email);
		personObj.setPersonType(personType);
		personObj.setPassword(password);
		
		try
		{
			DBConnection1.startTransaction();
			DBPerson dbPerson = new DBPerson();
			dbPerson.insertPerson(personObj);
			DBConnection1.commitTransaction();
		}
		catch(Exception e)
		{
			DBConnection1.rollbackTransaction();
		}
		
		IFDBPerson dbPerson = new DBPerson();
		Person personReferenceObj = new Person();
		personReferenceObj = dbPerson.searchPersonByName(name, true);
		int referenceId = personReferenceObj.getId();
		
		Employee employeeObj = new Employee();
		employeeObj.setId(referenceId);
		employeeObj.setSalary(salary);
		
		try
		{
			DBConnection1.startTransaction();
			DBEmployee dbEmployee =new DBEmployee();
			dbEmployee.insertEmployee(employeeObj);
			DBConnection1.commitTransaction();
		}
		catch(Exception e)
		{
			DBConnection1.rollbackTransaction();
		}
		
		Instructor instructorObj = new Instructor();
		instructorObj.setId(referenceId);
		
		IFDBActivityType dbActivityType = new DBActivityType();
		ActivityType activityTypeObj = new ActivityType();
		activityTypeObj = dbActivityType.getActivityTypeByID(activityTypeId, false);
		if(activityTypeObj != null)
		{
			instructorObj.setActivityType(activityTypeObj);
		}
		
		instructorObj.setStatus(status);
		instructorObj.setPrice(price);
	}
	
	public int updateInstructor(int id, int activityTypeId, double price, String status)
	{
		IFDBActivityType dbActivityType = new DBActivityType();
		ActivityType activityTypeObj = new ActivityType();
		activityTypeObj = dbActivityType.getActivityTypeByID(activityTypeId, false);
		
		Instructor instructorObj = new Instructor();
		instructorObj.setId(id);
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
		availableInstructorForActivityList =dbInstructor.getActivityAvailableInstructors(activityTypeId, status, true);
		return availableInstructorForActivityList;
	}

}
