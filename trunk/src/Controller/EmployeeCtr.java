package Controller;

import java.util.LinkedList;

import DBLayer.DBConnection1;
import DBLayer.DBEmployee;
import DBLayer.DBPerson;
import DBLayer.IFDBEmployee;
import DBLayer.IFDBPerson;
import Model.Employee;
import Model.Person;

public class EmployeeCtr
{
	public EmployeeCtr(){}
	
	public LinkedList<Employee> getAllEmployees()
	{
		IFDBEmployee dbEmployee = new DBEmployee();
		LinkedList<Employee> employeeList = new LinkedList<Employee>();
		employeeList = dbEmployee.getAllEmployees(true);
		return employeeList;
	}
	
	public Employee getEmployeeById(int id)
	{
		IFDBEmployee dbEmployee = new DBEmployee();
		Employee employeeObj = new Employee();
		employeeObj = dbEmployee.getEmployeeById(id, true);
		return employeeObj;
	}
	
	public void insertEmployee(String name, String address, int zipcode,	String country, String phoneNo, 
			String email, String personType, String password, double salary)
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
	}
	
	public int updateEmployee(int id, double salary)
	{
		IFDBEmployee dbEmployee = new DBEmployee();
		Employee employeeObj = new Employee();
		employeeObj.setSalary(salary);
		return dbEmployee.updateEmployee(employeeObj);
	}
	
	public int deleteEmployeeById(int id)
	{
		IFDBEmployee dbEmployee = new DBEmployee();
		return dbEmployee.deleteEmployeeById(id);
	}
}
