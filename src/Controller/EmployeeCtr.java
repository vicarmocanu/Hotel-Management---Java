package Controller;

import java.util.LinkedList;

import DBLayer.DBConnection1;
import DBLayer.DBEmployee;
import DBLayer.DBPerson;
import DBLayer.IFDBEmployee;
import Model.Employee;
import Model.Person;

public class EmployeeCtr {
	
	public EmployeeCtr()
	{
		 
	}
	
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
	public void insertEmployee(int id, String name, String address, int zipcode,	String country, String phoneNo, 
			String email, String personType, String password, double salary)
	{
		Employee employeeObj = new Employee();
    	Person personObj = new Person();
    	
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
    	
    	try
		 {
			 DBConnection1.startTransaction();
			 DBPerson dbPerson = new DBPerson();
			 dbPerson.insertPerson(personObj);
			 DBEmployee dbEmployee =new DBEmployee();
			 dbEmployee.insertEmployee(employeeObj);
			 DBConnection1.commitTransaction();
		 }
		 catch(Exception e)
		 {
			 DBConnection1.rollbackTransaction();
		 }
	}
	public int updateEmployee(int id, String name, String address, int zipcode,	String country, String phoneNo, 
			String email, String personType, String password, double salary)
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
