package DBLayer;

import java.util.LinkedList;

import Model.Employee;

public interface IFDBEmployee
{
	public LinkedList<Employee> getAllEmployees(boolean retrieveAssociation);
	public Employee getEmployeeById(int id, boolean retrieveAssociation);
	public int insertEmployee(Employee employeeObj) throws Exception;	
	public int updateEmployee(Employee employeeObj);
	public int deleteEmployeeById(int id);
}
