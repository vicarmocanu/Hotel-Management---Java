package DBLayer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Model.ActivityType;
import Model.Employee;
import Model.Instructor;

public class DBInstructor implements IFDBInstructor
{
	private Connection con;
	
	//constructor - connection
	public DBInstructor()
	{
		con=DBConnection1.getInstance().getDBcon();
	}
	
	private String buildQuery(String wClause)
	{
		String query="SELECT * FROM Instructor";
		
		if (wClause.length()>0)
		{
			query=query+" WHERE "+ wClause;
		}
		return query;
	}
	
	private Instructor buildInstructor(ResultSet results)
	{
		Instructor instructorObj= new Instructor();
		IFDBEmployee dbEmployee = new DBEmployee();
		Employee employeeObj = new Employee();
		IFDBActivityType dbActivivityType = new DBActivityType();
		ActivityType activityTypeObj = new ActivityType();
		
		try
		{
			int employeeId = results.getInt("employeeId");
			instructorObj.setId(results.getInt("employeeId"));
			
			activityTypeObj = dbActivivityType.getActivityTypeByID(results.getInt("activityType"), false);
			instructorObj.setActivityType(activityTypeObj);	
			
			instructorObj.setPrice(results.getDouble("price"));
			
			employeeObj = dbEmployee.getEmployeeById(employeeId, true);
			
			instructorObj.setName(employeeObj.getName());
			instructorObj.setZipcode(employeeObj.getZipcode());
			instructorObj.setCountry(employeeObj.getCountry());
			instructorObj.setAddress(employeeObj.getAddress());
			instructorObj.setPhoneNo(employeeObj.getPhoneNo());
			instructorObj.setEmail(employeeObj.getEmail());
			instructorObj.setPersonType(employeeObj.getPersonType());
			instructorObj.setPassword(employeeObj.getPassword());
			instructorObj.setSalary(employeeObj.getSalary());
			
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in building the instructor object: " +e);
		}
		
		return instructorObj;
	}
	
	//singleWhere is used when we select only one object
	private Instructor singleWhere (String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		Instructor instructorObj=new Instructor();		
		String query = buildQuery(wClause);
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			if( results.next() )
			{
				instructorObj = buildInstructor(results);
				stmt.close();
			}
			
			else
			{
				instructorObj = null;
			}
		}
		catch(Exception e)
		{
			System.out.println("Single selection query exception: " + e);
		}
		
		return instructorObj;
	}
	
	//miscWhere is used when we want to select multiple objects
	private LinkedList<Instructor> miscWhere (String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		LinkedList<Instructor> instructorList=new LinkedList<Instructor>();
		String query =  buildQuery(wClause);
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			while( results.next() )
			{
				Instructor instructorObj=new Instructor();
				instructorObj =buildInstructor(results);
				instructorList.add(instructorObj);
			}
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println("Multiple selection query exception: " + e);
			e.printStackTrace();
		}
		
		return instructorList;
	}

	public LinkedList<Instructor> getAllInstructors(boolean retrieveAssociation)
	{
		return miscWhere("", retrieveAssociation);
	}
	
	public Instructor getInstructorById(int employeeId, boolean retrieveAssociation)
	{
		String wClause = "  employeeId= '" + employeeId + "'";
		return singleWhere(wClause, retrieveAssociation);
	}
	
	public int insertInstructor(Instructor instructorObj) throws Exception
	{
		int result = -1;
		
		String query = "INSERT INTO Instructor(employeeId, activityType, price, status) VALUES ('" +
		instructorObj.getId() + "," + 
				instructorObj.getActivityType() + "','" +
		instructorObj.getPrice() + "','" +
				instructorObj.getStatus() + "')";
		
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
	
	public int updateInstructor(Instructor instructorObj)
	{
		Instructor instructorNewObj= instructorObj;
		
		String query="UPDATE Instructor SET " +
		"activityType= '" + instructorNewObj.getActivityType() + "', " +
		"price= '" + instructorNewObj.getPrice() + "' " +
		"status= '" + instructorNewObj.getStatus() + "' " +
		"WHERE employeeId= '" + instructorNewObj.getId() + "'";
		
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
	
	public int deleteInstructorById(int employeeId)
	{
		int result=-1;
		  
	  	String query="DELETE FROM Instructor WHERE employeeId= '" + employeeId + "'";
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
	
	public LinkedList<Instructor> getActivityAvailableInstructors(int activityTypeId, String status, boolean retrieveAssociation)
	{
		String wClause = "  activityType= '" + activityTypeId + "' AND status='" + status + "'";
		return miscWhere(wClause, retrieveAssociation);
	}
}