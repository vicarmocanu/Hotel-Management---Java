package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Model.ActivityType;
import Model.Facility;

public class DBFacility implements IFDBFacility
{
	private Connection con;
	
	public DBFacility()
	{
		con=DBConnection1.getInstance().getDBcon();
	}
	
	private String buildQuery(String wClause)
	{
		String query="SELECT * FROM Facility";
		if (wClause.length()>0)
		{
			query=query+" WHERE "+ wClause;
		}
		return query;
	}
	
	private Facility buildFacility(ResultSet results)
	{
		Facility facilityObj=new Facility();
		
		IFDBActivityType dbActivity=new DBActivityType();
		
		try
		{
			facilityObj.setId(results.getInt("id"));
			facilityObj.setName(results.getString("name"));
			facilityObj.setActivity(dbActivity.getActivityTypeByID(results.getInt("forActivity"), false));
			facilityObj.setStatus(results.getString("status"));
		}
		catch(Exception e)
		{
			System.out.println("Error in building the facility object.");
		}
		return facilityObj;
	}
	
	private Facility singleWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		Facility facilityObj=new Facility();
		
		String query = buildQuery(wClause);
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			if( results.next() )
			{
				facilityObj=buildFacility(results);
				stmt.close();
			}
			if(retrieveAssociation)
			{//the activity type is to be built as well
				IFDBActivityType dbActivityType=new DBActivityType();
				ActivityType activityTypeObj=dbActivityType.getActivityTypeByID(facilityObj.getActivity().getID(), false);
				System.out.println("Activity type is selected.");
				facilityObj.setActivity(activityTypeObj);
			}
			else
			{
				facilityObj=null;
			}
		}
		catch(Exception e)
		{
			System.out.println("Single selection query exception: " + e);
		}
		return facilityObj;
	}
	
	private LinkedList<Facility> miscWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		LinkedList<Facility> facilityList=new LinkedList<Facility>();
		String query =  buildQuery(wClause);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			while( results.next() )
			{
				Facility facilityObj=new Facility();
				facilityObj=buildFacility(results);
				facilityList.add(facilityObj);
			}
			stmt.close();
			if(retrieveAssociation)
			{//the activity line is to be selected as well
				IFDBActivityType dbActivityType=new DBActivityType();
				for(Facility facilityObj : facilityList)
				{
					ActivityType activityTypeObj = dbActivityType.getActivityTypeByID(facilityObj.getActivity().getID(), false);
					System.out.println("Activity line is selected.");
					facilityObj.setActivity(activityTypeObj);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Multiple selection query exception: " + e);
			e.printStackTrace();
		}
		
		return facilityList;
	}

	@Override
	public Facility getFacilityById(int id, boolean retrieveAssociation)
	{
		String wClause = "  id= '" + id + "'";
		return singleWhere(wClause, retrieveAssociation);
	}

	@Override
	public LinkedList<Facility> getAllFacilities(boolean retrieveAssociation)
	{
		return miscWhere("", retrieveAssociation);
	}

	@Override
	public Facility getFacilityForActivity(int activityId, boolean retrieveAssociation)
	{
		String wClause = "  forActivity= '" + activityId + "'";
		return singleWhere(wClause, retrieveAssociation);
	}

	@Override
	public Facility getFacilityByName(String name, boolean retrieveAssociation)
	{
		String wClause = "  name= '" + name + "'";
		return singleWhere(wClause, retrieveAssociation);
	}

	@Override
	public int insertFacility(Facility facility) throws Exception
	{
		int result = -1;
		
		String query = "INSERT INTO Facility(id, name, forActivity, status) VALUES ('" +
		facility.getId() + "','" +
				facility.getName() + "','" +
		facility.getActivity().getID() + "','" +
				facility.getStatus() + "')'";
		
		System.out.println("Insertion query: " + query);
	    try
	    {
	    	Statement stmt = con.createStatement();
	    	stmt.setQueryTimeout(5);
	    	result = stmt.executeUpdate(query);
	    	stmt.close();
	    }
	    catch(SQLException e)
	    {
	    	System.out.println("Facility has not been inserted correctly. Exception: " + e);
	    }
	    
	    return(result);
	}

	@Override
	public int updateFacility(Facility facility)
	{
		Facility facilityObj=facility;
		
		String query="UPDATE Facility SET " + 
		"id= '" + facilityObj.getId() + "', " + 
		"name= '" + facilityObj.getName() + "', " +
		"forActivity= '" + facilityObj.getActivity().getID() + "', " +
		"status= '" + facilityObj.getStatus() + "'";
		
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
			System.out.println("Facility has not been updated correctly. Exception: " + e);
		}
		
		return(result);
	}

	@Override
	public int deleteFacility(int id)
	{
		int result=-1;
		  
	  	String query="DELETE FROM Facility WHERE id= '" + id + "'";
	  	System.out.println("Delete by id query: " + query);
	  	try
	  	{
	  		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 	  	result = stmt.executeUpdate(query);
	 	  	stmt.close();	  		
	  	}
	  	catch(SQLException e)
	  	{
	  		System.out.println("Facility has not been deleted successfully. Exception: " + e);
	  	}
	  	
	  	return(result);
	}
}
