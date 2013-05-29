package Controller;

import java.util.LinkedList;

import DBLayer.DBActivityType;
import DBLayer.DBConnection1;
import DBLayer.DBEmployee;
import DBLayer.DBInstructor;
import DBLayer.DBPerson;
import DBLayer.IFDBActivityType;
import DBLayer.IFDBInstructor;
import Model.ActivityType;
import Model.Employee;
import Model.Instructor;
import Model.Person;


public class InstructorCtr {
	
	public InstructorCtr()
	{}
	
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
		ActivityType activityTypeObj= new ActivityType();
		Employee employeeObj = new Employee();
    	Instructor instructorObj = new Instructor();
    	IFDBActivityType dbActivityType = new DBActivityType();
    	
    	activityTypeObj = dbActivityType.getActivityTypeByID(activityTypeId, false);
    	
    	personObj.setId(id);
    	personObj.setName(name);
    	personObj.setAddress(address);
    	personObj.setZipcode(zipcode);
    	personObj.setCountry(country);
    	personObj.setPhoneNo(phoneNo);
    	personObj.setEmail(email);
    	personObj.setPersonType(personType);
    	personObj.setPassword(password);
    	
    	employeeObj.setSalary(salary);
    	if(activityTypeObj != null)
    	{
    		instructorObj.setActivityType(activityTypeObj);
    	}
    	instructorObj.setPrice(price);
    	instructorObj.setStatus(status);
    	instructorObj.setSalary(salary);
    	
    	
    	
    	try
		 {
			 DBConnection1.startTransaction();
			 DBPerson dbPerson = new DBPerson();
			 dbPerson.insertPerson(personObj);
			 DBEmployee dbEmployee =new DBEmployee();
			 dbEmployee.insertEmployee(employeeObj);
			 DBInstructor dbInstructor = new DBInstructor();
			 dbInstructor.insertInstructor(instructorObj);
			 DBConnection1.commitTransaction();
		 }
		 catch(Exception e)
		 {
			 DBConnection1.rollbackTransaction();
		 }
	}
	public int updateInstructor(int id, String name, String address, int zipcode,	String country, String phoneNo, 
			String email, String personType, String password, int activityTypeId, double price, String status, double salary)
	{
		Person personObj = new Person();
		ActivityType activityTypeObj= new ActivityType();
		Employee employeeObj = new Employee();
    	Instructor instructorObj = new Instructor();
    	IFDBActivityType dbActivityType = new DBActivityType();
    	
    	activityTypeObj = dbActivityType.getActivityTypeByID(activityTypeId, false);
    	
    	personObj.setId(id);
    	personObj.setName(name);
    	personObj.setAddress(address);
    	personObj.setZipcode(zipcode);
    	personObj.setCountry(country);
    	personObj.setPhoneNo(phoneNo);
    	personObj.setEmail(email);
    	personObj.setPersonType(personType);
    	personObj.setPassword(password);
    	
    	employeeObj.setSalary(salary);
    	if(activityTypeObj != null)
    	{
    		instructorObj.setActivityType(activityTypeObj);
    	}
    	instructorObj.setPrice(price);
    	instructorObj.setStatus(status);
    	instructorObj.setSalary(salary);
    	
    	IFDBInstructor dbInstructor = new DBInstructor();
    	
    	return dbInstructor.updateInstructor(instructorObj);
	}
	public int deleteInstructorById(int employeeId)
	{
		IFDBInstructor dbInstructor = new DBInstructor();
		return dbInstructor.deleteInstructorById(employeeId);
	}

}
