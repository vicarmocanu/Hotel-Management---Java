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
		ActivityType activityTypeObj = new ActivityType();
		
		try
		{
			facilityObj.setId(results.getInt("id"));
			facilityObj.setName(results.getString("name"));
			
			activityTypeObj = dbActivity.getActivityTypeByID(results.getInt("forActivity"));
			facilityObj.setActivity(activityTypeObj);
			facilityObj.setStatus(results.getString("status"));
		}
		catch(Exception e)
		{
			System.out.println("Exception in building the facility object: " + e);
		}
		return facilityObj;
	}
	
	private Facility singleWhere(String wClause)
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
			else
			{
				facilityObj = null;
			}
		}
		catch(Exception e)
		{
			System.out.println("Single selection query exception: " + e);
		}
		return facilityObj;
	}
	
	private LinkedList<Facility> miscWhere(String wClause)
	{
		ResultSet results;
		LinkedList<Facility> facilityList=new LinkedList<Facility>();
		String query =  buildQuery(wClause);
		System.out.println(query);
		
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
		}
		catch(Exception e)
		{
			System.out.println("Multiple selection query exception: " + e);
			e.printStackTrace();
		}
		
		return facilityList;
	}

	@Override
	public Facility getFacilityById(int id)
	{
		String wClause = "  id= '" + id + "'";
		return singleWhere(wClause);
	}

	@Override
	public LinkedList<Facility> getAllFacilities()
	{
		return miscWhere("");
	}

	@Override
	public LinkedList<Facility> getFacilitiesForActivity(int activityId)
	{
		String wClause = "  forActivity= '" + activityId + "'";
		return miscWhere(wClause);
	}

	@Override
	public Facility getFacilityByName(String name)
	{
		String wClause = "  name= '" + name + "'";
		return singleWhere(wClause);
	}

	@Override
	public int insertFacility(Facility facility) throws Exception
	{
		//call to get the next facility id
		int nextFacilityId = GetMax.getMaxId("SELECT MAX(id) FROM Facility");
		nextFacilityId = nextFacilityId + 1;
		System.out.println("Next facility id = " + nextFacilityId);
		
		int result = -1;
		Facility facilityObj =facility;
		ActivityType activityTypeObj = facilityObj.getActivity();
		String query = new String();
		
		if(activityTypeObj!=null)
		{
			query = "INSERT INTO Facility(id, name, forActivity, status) VALUES ('" +
		nextFacilityId + "','" +
					facility.getName() + "','" +
		facility.getActivity().getID() + "','" +
					facility.getStatus() + "')";
		}
		else
		{
			System.out.println("Error! An inserted value may be invalid.");
		}
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
			System.out.println("Insert exception: " + e);
		}
		
		return(result);
	}

	@Override
	public int updateFacility(Facility facility)
	{
		Facility facilityObj=facility;
		ActivityType activityTypeObj = new ActivityType();
		activityTypeObj = facility.getActivity();
		String query = new String();
		
		if(activityTypeObj != null)
		{
			query="UPDATE Facility SET " +
		"name= '" + facilityObj.getName() + "', " +
					"forActivity= '" + facilityObj.getActivity().getID() + "', " +
		"status= '" + facilityObj.getStatus() + "' " +
					"WHERE id= '" + facilityObj.getId() + "'";
		}
		
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

	@Override
	public int deleteFacility(int id)
	{
		int result=-1;
		
		String query="DELETE FROM Facility WHERE id= '" + id + "'";
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

	@Override
	public LinkedList<Facility> getAvailableFacilitiesForActivity(int activityId, String status) 
	{
		String wClause = "  forActivity= '" + activityId + "' AND status='" + status + "'";
		return miscWhere(wClause);
	}
	
	@Override
	public int getFacilityInstances(int id, String name)
	{
		int instances = 0;
		
		ResultSet results;
		
		String query = "SELECT COUNT(*) AS facilityInstances FROM Facility " + 
		" WHERE id<> '" +  id + "' AND name= '" + name + "'";
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			while( results.next() )
			{
				instances = results.getInt("facilityInstances");
				System.out.println("Facility instances: " + instances);
			}
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception in returning the facility instance count: " + e);
		}
		return instances;
	}
}
