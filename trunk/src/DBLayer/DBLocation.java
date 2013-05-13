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
	
	public int insertLocation(Location loc) throws Exception {
		String query = "INSERT INTO Location(zipcode,country, city)" +
				" VALUES('" +
				loc.getZipCode()+ "','"+
				loc.getCountry()+"','"+
				loc.getCity()+"')";
				
				
		
		int rc = -1;
		System.out.println("insert: " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("Location is not inserted");
	        throw new Exception ("Location is not inserted correctly!");
		}
		
		return rc;
	}

	
	public int updateLocation(Location loc) {
		int rc = -1;
		
		String query = "UPDATE Location SET " +
				"zipCode= '"+loc.getZipCode()+"', "+
				"country= '"+loc.getCountry()+"', "+
				"city= '"+loc.getCity()+"' "+
						"WHERE zipCode= '" +loc.getZipCode()+"'";
		System.out.println("Update query: " + query);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc=stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception e) {
			System.out.println("Update exception in Location: " + e);
		}
		
		return rc;

	}

	
	public int deleteLocation(int loczipCode) {
		int rc=-1;
		  
	  	String query="DELETE FROM Location WHERE zipCode = '" +
				loczipCode + "'";
                System.out.println(query);
	  	try{ 
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 	  	rc = stmt.executeUpdate(query);
	 	  	stmt.close();
  		}
   	    catch(Exception ex){
	 	  	System.out.println("Delete exception in Location: "+ex);
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
		
		try {
			rbObj.setZipCode(results.getInt("zipcode"));
			rbObj.setCountry(results.getString("country"));
			rbObj.setCity(results.getString("city"));
			
		} catch (Exception e) {
			System.out.println("Error in building the Location object!");
		}
		
		return rbObj;
	}
	
	private Location singleWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		Location rbObj = new Location();
		String query = buildQuery(wClause);
		System.out.println("Query: "+query);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			if (results.next()) {
				rbObj = buildLocation(results);
				System.out.println("Location build successfully!");
				stmt.close();
			}
			else
			{
				rbObj = null;
			}			
		} catch (Exception e) {
			System.out.println("Query exception - select Location : "+e);
			e.printStackTrace();
		}
		
		return rbObj;
	}
	
	private LinkedList<Location> miscWhere(String wClause, boolean retrieveAssiciation)
	{
		ResultSet results;
		LinkedList<Location> list = new LinkedList<Location>();
		String query = buildQuery(wClause);
		
		try {
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
		} catch (Exception e) {
			System.out.println("Query exception - select Location : "+e);
			e.printStackTrace();
		}
		return list;
	}
	
	public LinkedList<Location> getAllLocation(boolean retriveAssociation) {
		return miscWhere("", retriveAssociation);
	}

	public Location searchLocationByZipCode(int zipCode,
			boolean retriveAssociation) {
		String wClause = " zipCode= '" + zipCode + "'";
		return singleWhere(wClause, retriveAssociation);
	}

	
	public Location searchLocationByCity(String city, boolean retriveAssociation) {
		String wClause = "city= " + city + ",";
		System.out.println("Location " + wClause);
		return singleWhere(wClause, retriveAssociation);
	}

}
