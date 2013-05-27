package DBLayer;

import java.util.LinkedList;

import Model.Employee;

public interface IFDBEmployee {
	
	//get all teams
		public LinkedList<Employee> getAllEmployees(boolean retrieveAssociation);
		
		//get team by id
		public Employee getEmployeeById(int id, boolean retrieveAssociation);
		
		//insert team
		public int insertEmployee(Employee employeeObj) throws Exception;
		
		//update team
		public Employee updateEmployee(Employee employeeObj);
		
		//delete team by leader id
		public int deleteEmployeeById(int id);
		
		//delete team by team id

}
