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
		
		IFDBActivityType dbActivityType = new DBActivityType();
		ActivityType activityTypeObj = new ActivityType();
		
		try
		{
			int employeeId = results.getInt("employeeId");
			instructorObj.setId(results.getInt("employeeId"));
			
			int activityTypeId = results.getInt("activityType");
			if(activityTypeId == 0)
			{
				instructorObj.setActivityType(null);
			}
			else
			{
				activityTypeObj = dbActivityType.getActivityTypeByID(activityTypeId);
				instructorObj.setActivityType(activityTypeObj);
			}
			
			instructorObj.setPrice(results.getDouble("price"));
			instructorObj.setStatus(results.getString("status"));
			
			employeeObj = dbEmployee.getEmployeeById(employeeId);
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
	private Instructor singleWhere (String wClause)
	{
		ResultSet results;
		Instructor instructorObj=new Instructor();		
		String query = buildQuery(wClause);
		System.out.println("Query: " + query);
		
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
	private LinkedList<Instructor> miscWhere (String wClause)
	{
		ResultSet results;
		LinkedList<Instructor> instructorList=new LinkedList<Instructor>();
		String query =  buildQuery(wClause);
		System.out.println("Query: " + query);
		
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

	public LinkedList<Instructor> getAllInstructors()
	{
		return miscWhere("");
	}
	
	public Instructor getInstructorById(int employeeId)
	{
		String wClause = "  employeeId= '" + employeeId + "'";
		return singleWhere(wClause);
	}
	
	public int insertInstructor(Instructor instructorObj) throws Exception
	{
		int result = -1;
		
		String query = new String();
		ActivityType activityTypeObj = new ActivityType();
		activityTypeObj = instructorObj.getActivityType();
		
		if(activityTypeObj != null)
		{
			query = "INSERT INTO Instructor(employeeId, activityType, price, status) VALUES ('" +
					instructorObj.getId() + "','" + instructorObj.getActivityType().getID() + "','" +
					instructorObj.getPrice() + "','" + instructorObj.getStatus() + "')";
		}
		else
		{
			query = "INSERT INTO Instructor(employeeId, price, status) VALUES ('" +
					instructorObj.getId() + "','" + instructorObj.getPrice() + "','" +
					instructorObj.getStatus() + "')";
		}
		
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
		int result=-1;
		String query = new String();
		ActivityType activityTypeObj = new ActivityType();
		activityTypeObj = instructorObj.getActivityType();
		
		if(activityTypeObj != null)
		{
			query = "UPDATE Instructor SET activityType= '" + instructorObj.getActivityType().getID() + "', " +
		"price= '" + instructorObj.getPrice() + "', " +
					"status= '" + instructorObj.getStatus() + "' " +
		"WHERE employeeId= '" + instructorObj.getId() + "'";
		}
		else
		{
			query = "UPDATE Instructor SET activityType= '0', " +
					"price= '" + instructorObj.getPrice() + "', " +
								"status= '" + instructorObj.getStatus() + "' " +
					"WHERE employeeId= '" + instructorObj.getId() + "'";
		}
		
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
	
	public LinkedList<Instructor> getActivityAvailableInstructors(int activityTypeId, String status)
	{
		String wClause = "  activityType= '" + activityTypeId + "' AND status='" + status + "'";
		return miscWhere(wClause);
	}
}