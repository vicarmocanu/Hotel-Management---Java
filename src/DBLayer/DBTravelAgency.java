package DBLayer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import Model.Location;
import Model.TravelAgency;

public class DBTravelAgency implements IFDBTravelAgency
{
	private Connection con;
	
	public DBTravelAgency()
	{
		con=DBConnection1.getInstance().getDBcon();
	}
	
	private String buildQuery(String wClause)
	{
		String query="SELECT * FROM TravelAgency";
		
		if (wClause.length()>0)
		{
			query=query+" WHERE "+ wClause;
		}
		return query;
	}
	
	private TravelAgency buildTravelAgency(ResultSet results)
	{
		TravelAgency travelAgencyObj = new TravelAgency();
		
		try
		{
			travelAgencyObj.setCVR(results.getInt("cvr"));
			travelAgencyObj.setName(results.getString("name"));
			travelAgencyObj.setZipCode(results.getInt("zipcode"));
			travelAgencyObj.setCountry(results.getString("country"));
			travelAgencyObj.setAddress(results.getString("address"));
			travelAgencyObj.setPhoneNo(results.getString("phoneNo"));
			travelAgencyObj.setEmail(results.getString("email"));
		}
		catch(Exception e)
		{
			System.out.println("Exception in building the travel agency object: " + e);
		}
		
		return travelAgencyObj;
	}
	
	//single where selection
	private TravelAgency singleWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		
		TravelAgency travelAgencyObj = new TravelAgency();
		String query = buildQuery(wClause);
		System.out.println("Query: "+query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			if (results.next())
			{
				travelAgencyObj = buildTravelAgency(results);
				stmt.close();
				if(retrieveAssociation)
				{//location selection
					IFDBLocation dbLocation = new DBLocation();
					Location location = new Location();
					location = dbLocation.getLocation(travelAgencyObj.getZipCode(), travelAgencyObj.getCountry());
					if(location != null)
					{
						travelAgencyObj.setZipCode(location.getZipCode());
						travelAgencyObj.setCountry(location.getCountry());
						System.out.println("Location selection.");
					}
				}
			}
			else
			{
				travelAgencyObj = null;
			}
		}
		catch (Exception e)
		{
			System.out.println("Single selection query exception: "+e);
		}
		
		return travelAgencyObj;
	}
	
	//misc where selection
	private LinkedList<TravelAgency> miscWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		
		LinkedList<TravelAgency> list = new LinkedList<TravelAgency>();
		String query = buildQuery(wClause);
		System.out.println("Query: "+query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			while(results.next())
			{
				TravelAgency travelAgencyObj = new TravelAgency();
				travelAgencyObj = buildTravelAgency(results);
				list.add(travelAgencyObj);
			}
			stmt.close();
			
			if(retrieveAssociation)
			{
				IFDBLocation dbLocation = new DBLocation();
				for(TravelAgency travelAgencyObject : list)
				{
					Location location = new Location();
					location = dbLocation.getLocation(travelAgencyObject.getZipCode(), travelAgencyObject.getCountry());
					if(location != null)
					{
						travelAgencyObject.setZipCode(location.getZipCode());
						travelAgencyObject.setCountry(location.getCountry());
						System.out.println("Location selection.");
					}
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Multiple selection query exception: "+e);
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public LinkedList<TravelAgency> getAllTravelAgencies(boolean retrieveAssociation)
	{
		return miscWhere("", retrieveAssociation);
	}
	
	@Override
	public TravelAgency getTravelAgencyByCVR(int cvr, boolean retrieveAssociation)
	{
		String wClause = " cvr= '" + cvr + "'";
		return singleWhere(wClause, retrieveAssociation);
	}
	
	@Override
	public TravelAgency getTravelAgencyByName(String name, boolean retrieveAssociation)
	{
		String wClause = " name= '" + name + "'";
		return singleWhere(wClause, retrieveAssociation);
	}
	
	@Override
	public int insertTravelAgency(TravelAgency travelAgency) throws Exception
	{
		int rc = -1;
		
		String query = "INSERT INTO TravelAgency(cvr, name, zipcode, country, address, phoneNo, email) VALUES('" +
		travelAgency.getCVR()+"','"+
				travelAgency.getName()+"','"+
		travelAgency.getZipCode()+ "','"+
				travelAgency.getCountry()+"','"+
		travelAgency.getAddress()+"','"+
				travelAgency.getPhoneNo()+"','"+
		travelAgency.getEmail()+"')";
		System.out.println("Insertion query: " + query);

		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch (SQLException ex)
		{
			System.out.println("Insertion exception: " + ex);
		}
		
		return rc;
	}
	
	@Override
	public int updateTravelAgency(TravelAgency travelAgency)
	{
		int rc = -1;
		
		TravelAgency travelAgencytravelAgency = travelAgency;
		
		String query = "UPDATE TravelAgency SET " +
		"name='"+travelAgencytravelAgency.getName()+"', "+
				"zipcode='"+travelAgencytravelAgency.getZipCode()+"', "+
		"country='"+travelAgencytravelAgency.getCountry()+"', "+
				"address='"+travelAgencytravelAgency.getAddress()+"', "+
		"phoneNo='"+travelAgencytravelAgency.getPhoneNo()+"', "+
				"email='"+travelAgencytravelAgency.getEmail()+"' "+
		"WHERE cvr='" + travelAgencytravelAgency.getCVR() + "'";
		
		System.out.println("Update query: " + query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc=stmt.executeUpdate(query);
			stmt.close();
		}
		catch (SQLException e)
		{
			System.out.println("Update exception: " + e);
		}
		
		return rc;
	}
	
	@Override
	public int deleteTravelAgencyByCvr(int cvr)
	{
		int rc=-1;
		
		String query="DELETE FROM TravelAgency WHERE cvr= '" + cvr + "'";
		System.out.println("Delete query: " + query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch(SQLException ex)
		{
			System.out.println("Delete exception: "+ex);
		}
		
		return(rc);
	}
	
	@Override
	public int deleteTravelAgencyByName(String name)
	{
		int rc=-1;
		
		String query="DELETE FROM TravelAgency WHERE name= '" + name + "'";
		System.out.println("Delete query: " + query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}
		catch(SQLException ex)
		{
			System.out.println("Delete exception: "+ex);
		}
		return(rc);
	}
	
	@Override
	public int getTravelAgencyInstances(int cvr, String name, int zipcode, String country, String address)
	{
		int instances = 0;
		
		ResultSet results;
		
		String query = "SELECT COUNT(*) AS travelAgencyInstances FROM TravelAgency " + 
		" WHERE cvr<> '" +  cvr + "' AND name= '" + name + "' AND zipcode= '" + zipcode + 
		"' AND country= '" + country + "' AND address= '" + address + "'";
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			while( results.next() )
			{
				instances = results.getInt("travelAgencyInstances");
				System.out.println("Travel agency instances: " + instances);
			}
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception in returning the travel agency instance count: " + e);
		}
		return instances;
	}
}