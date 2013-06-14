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
		employeeList = dbEmployee.getAllEmployees();
		return employeeList;
	}
	
	public Employee getEmployeeById(int id)
	{
		IFDBEmployee dbEmployee = new DBEmployee();
		Employee employeeObj = new Employee();
		employeeObj = dbEmployee.getEmployeeById(id);
		return employeeObj;
	}
	
	public void insertEmployee(String employeeName, double salary)
	{
		IFDBPerson dbPerson = new DBPerson();
		Person personReferenceObj = new Person();
		personReferenceObj = dbPerson.searchPersonByName(employeeName, true);
		int referenceId = personReferenceObj.getId();
		
		Employee employeeObj = new Employee();
		employeeObj.setId(referenceId);
		employeeObj.setSalary(salary);
		
		try
		{
			DBConnection1.startTransaction();
			DBEmployee dbEmployee = new DBEmployee();
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
		employeeObj.setId(id);
		employeeObj.setSalary(salary);
		return dbEmployee.updateEmployee(employeeObj);
	}
	
	public int deleteEmployeeById(int id)
	{
		IFDBEmployee dbEmployee = new DBEmployee();
		return dbEmployee.deleteEmployeeById(id);
	}
}
