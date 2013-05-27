package DBLayer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import Model.Employee;
import Model.Location;

public class DBEmployee implements IFDBEmployee
{
	private Connection con;
	
	//constructor - connection
	public DBEmployee()
	{
		con=DBConnection1.getInstance().getDBcon();
	}
	
	private String buildQuery(String wClause)
	{
		String query="SELECT * FROM Employee";
		
		if (wClause.length()>0)
		{
			query=query+" WHERE "+ wClause;
		}
		return query;
	}
	
	private Employee buildEmployee(ResultSet results)
	{
		Employee employeeObj= new Employee();
		
		try
		{
			employeeObj.setId(results.getInt("personId"));
			employeeObj.setSalary(results.getDouble("salary"));
			employeeObj.setEmployeeType(results.getString("employeeType"));
		}
		catch(Exception e)
		{
			System.out.println("Exception in building the employee object: " +e);
		}
		
		return employeeObj;
	}
	
	//singleWhere is used when we select only one object
	private Employee singleWhere (String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		Employee employeeObj=new Employee();		
		String query = buildQuery(wClause);
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			if( results.next() )
			{
				employeeObj = buildEmployee(results);
				stmt.close();
			}
			if(retrieveAssociation)
			{//location selection
				IFDBLocation dbLocation = new DBLocation();
				Location location = new Location();
				location = dbLocation.searchLocationByZipCode(employeeObj.getZipcode(), false);
				employeeObj.setZipcode(location.getZipCode());
				employeeObj.setCountry(location.getCountry());
			}
			else
			{
				employeeObj = null;
			}
		}
		catch(Exception e)
		{
			System.out.println("Single selection query exception: " + e);
		}
		
		return employeeObj;
	}
	
	//miscWhere is used when we want to select multiple objects
	private LinkedList<Employee> miscWhere (String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		LinkedList<Employee> employeeList=new LinkedList<Employee>();
		String query =  buildQuery(wClause);
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			while( results.next() )
			{
				Employee employeeObj=new Employee();
				employeeObj =buildEmployee(results);
				employeeList.add(employeeObj);
			}
			
			stmt.close();
			if(retrieveAssociation)
			{
				IFDBLocation dbLocation = new DBLocation();
				for(Employee employeeObj : employeeList)
				{
					Location location = new Location();
					location = dbLocation.searchLocationByZipCode(employeeObj.getZipcode(), false);
					employeeObj.setZipcode(location.getZipCode());
					employeeObj.setCountry(location.getCountry());
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Multiple selection query exception: " + e);
			e.printStackTrace();
		}
		
		return employeeList;
	}

	public LinkedList<Employee> getAllEmployees(boolean retrieveAssociation)
	{
		return miscWhere("", retrieveAssociation);
	}
	
	public Employee getEmployeeById(int id, boolean retrieveAssociation)
	{
		String wClause = "  id= '" + id + "'";
		return singleWhere(wClause, retrieveAssociation);
	}
	
	public int insertEmployee(Employee employeeObj) throws Exception
	{
		int result = -1;
		
		String query = "INSERT INTO Employee(salary, employeeType) VALUES ('" +
				employeeObj.getSalary() + "','" +
				employeeObj.getEmployeeType() +  "')";
		
		System.out.println("Insert query: " + query);
	    try
	    {
	    	Statement stmt = con.createStatement();
	    	stmt.setQueryTimeout(5);
	    	result = stmt.executeUpdate(query);
	    	stmt.close();
	    }
	    catch(SQLException e)
	    {
	    	System.out.println("Insert exception: " + e);
	    }
	    
	    return(result);
	}
	
	public int updateEmployee(Employee employeeObj)
	{
		Employee employeeNewObj=employeeObj;
		
		String query="UPDATE Employee SET " +
		"salary= '" + employeeNewObj.getSalary() + "', " +
		"employeeType= '" + employeeNewObj.getEmployeeType() + "' " + 
		"WHERE personId= '" + employeeNewObj.getId() + "'";
		
		int result=-1;
		System.out.println("Update query: " + query);
		
		try
		{
			Statement stmt = con.createStatement();		
	 		stmt.setQueryTimeout(5);
	 	 	result = stmt.executeUpdate(query);
	 	 	stmt.close();
		}
		catch(SQLException e)
		{
			System.out.println("Update exception: " + e);
		}
		
		return(result);
	}
	
	public int deleteEmployeeById(int id)
	{
		int result=-1;
		  
	  	String query="DELETE FROM Employee WHERE personId= '" + id + "'";
	  	System.out.println("Delete query: " + query);
	  	try
	  	{
	  		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 	  	result = stmt.executeUpdate(query);
	 	  	stmt.close();	  		
	  	}
	  	catch(SQLException e)
	  	{
	  		System.out.println("Delete exception: " + e);
	  	}	  	
	  	return(result);
	}
}
