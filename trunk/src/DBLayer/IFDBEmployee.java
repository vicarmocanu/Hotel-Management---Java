package DBLayer;

import java.util.LinkedList;

import Model.Employee;

public interface IFDBEmployee
{
	public LinkedList<Employee> getAllEmployees();
	public Employee getEmployeeById(int id);
	public int insertEmployee(Employee employeeObj) throws Exception;	
	public int updateEmployee(Employee employeeObj);
	public int deleteEmployeeById(int id);
}
