package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Model.Location;

public class DBLocation implements IFDBLocation{
	
private Connection con;
	
	public DBLocation()
	{
		con = DBConnection1.getInstance().getDBcon();
	}
	
	public int insertLocation(Location loc) throws Exception
	{
		String query = "INSERT INTO Location(zipcode, country, city) VALUES('" +
				loc.getZipCode()+ "','"+
				loc.getCountry()+"','"+
				loc.getCity()+"')";
		
		int rc = -1;
		
		System.out.println("Insert query: " + query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (SQLException ex)
		{
			System.out.println("Insert exception: " + ex);
		}
		
		return rc;
	}

	
	public int updateLocation(Location loc)
	{
		int rc = -1;
		
		String query = "UPDATE Location SET " +
				"zipCode= '"+loc.getZipCode()+"', "+
				"city= '"+loc.getCity()+"' "+
						"WHERE country= '" +loc.getCountry()+"'";
		System.out.println("Update query: " + query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc=stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("Update exception: " + e);
		}
		
		return rc;
	}

	@Override
	public int deleteLocation(int zipcode, String country)
	{
		int rc=-1;
		  
	  	String query="DELETE FROM Location WHERE zipCode= '" + zipcode + "' AND country= '" + country + "'";
	  	System.out.println("Delete query: " + query);
	  	
	  	try
	  	{
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 	  	rc = stmt.executeUpdate(query);
	 	  	stmt.close();
  		}
   	    catch(Exception ex)
   	    {
   	    
	 	  	System.out.println("Delete exception: "+ex);
   	    }
	
		return(rc);
	}
	
	
	private String buildQuery(String wClause)
	{
		String query = "SELECT * FROM Location";
		
		if(wClause.length()>0)
		{
			query = query + " WHERE " + wClause;
		}
		
		return query;
	}
	
	private Location buildLocation(ResultSet results)
	{
		Location rbObj = new Location();
		
		try
		{
			rbObj.setZipCode(results.getInt("zipcode"));
			rbObj.setCountry(results.getString("country"));
			rbObj.setCity(results.getString("city"));
			
		}
		catch (Exception e)
		{
			System.out.println("Exception in building the location object: " + e);
		}
		
		return rbObj;
	}
	
	private Location singleWhere(String wClause)
	{
		ResultSet results;
		
		Location rbObj = new Location();
		String query = buildQuery(wClause);
		System.out.println("Query: "+query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			if (results.next())
			{
				rbObj = buildLocation(results);
				stmt.close();
			}
			else
			{
				rbObj = null;
			}			
		}
		catch (Exception e)
		{
			System.out.println("Single selection query exception: "+e);
			e.printStackTrace();
		}
		
		return rbObj;
	}
	
	private LinkedList<Location> miscWhere(String wClause)
	{
		ResultSet results;
		
		LinkedList<Location> list = new LinkedList<Location>();
		String query = buildQuery(wClause);
		System.out.println("Query: "+query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			while(results.next())
			{
				Location rbObj = new Location();
				rbObj = buildLocation(results);
				list.add(rbObj);
			}
			stmt.close();
		}
		catch (Exception e)
		{
			System.out.println("Multiple selection query exception: "+e);
			e.printStackTrace();
		}
		return list;
	}
	
	public LinkedList<Location> getAllLocations()
	{
		return miscWhere("");
	}
	
	@Override
	public Location getLocation(int zipcode, String country)
	{
		String wClause = " zipcode= '" + zipcode + "' AND country= '" + country + "'";
		return singleWhere(wClause);
	}

	@Override
	public Location getCompleteLocation(int zipcode, String country, String city)
	{
		String wClause = " zipcode= '" + zipcode + "' AND country= '" + country + "' AND city= '" + city + "'";
		return singleWhere(wClause);
	}
}
