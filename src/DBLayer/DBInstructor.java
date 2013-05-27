package DBLayer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
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
		
		try
		{
			instructorObj.setId(results.getInt("employeeId"));
			instructorObj.setActivityType(results.getString("activityType"));
			instructorObj.setPrice(results.getDouble("price"));
		}
		catch(Exception e)
		{
			System.out.println("Exception in building the employee object: " +e);
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
	
	public Employee getInstructorById(int id, boolean retrieveAssociation)
	{
		String wClause = "  id= '" + id + "'";
		return singleWhere(wClause, retrieveAssociation);
	}
	
	public int insertInstructor(Instructor instructorObj) throws Exception
	{
		int result = -1;
		
		String query = "INSERT INTO Instructor(activityType, price) VALUES ('" +
				instructorObj.getActivityType() + "','" +
				instructorObj.getPrice() +  "')";
		
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
	
	public int deleteInstructorById(int id)
	{
		int result=-1;
		  
	  	String query="DELETE FROM Instructor WHERE employeeId= '" + id + "'";
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