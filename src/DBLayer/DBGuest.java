package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Model.Guest;
import Model.Person;
import Model.TravelAgency;

public class DBGuest implements IFDBGuest
{
	private Connection con;
	
	public DBGuest()
	{
		con = DBConnection1.getInstance().getDBcon();
	}
	
	@Override
	public int insertGuest(Guest guest) throws Exception
	{
		int rc = -1;
		
		Guest guestObj = guest;
		TravelAgency travelAgencyObj = new TravelAgency();
		travelAgencyObj = guest.getTravelAgency();
		String query = new String();
		
		if(travelAgencyObj != null)
		{
			query = "INSERT INTO Guest(personId, guestType, travelAgency) VALUES('" +
		guestObj.getId() + "','" + 	guestObj.getGuestType() + "','" +	guestObj.getTravelAgency().getCVR() + "')";
		}
		else
		{
			query = "INSERT INTO Guest(personId, guestType) VALUES('" + guestObj.getId() + "','" + 	guestObj.getGuestType() +  "')";
		}
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
	public int updateGuest(Guest gst)
	{
		int rc = -1;
		
		Guest guest = gst;
		TravelAgency travelAgencyObj = new TravelAgency();
		travelAgencyObj =guest.getTravelAgency();
		String query = new String();
		
		if(travelAgencyObj !=null)
		{
			query = "UPDATE Guest SET " +
		"guestType= '" + guest.getGuestType() + "', " +
					"travelAgency= '" + guest.getTravelAgency().getCVR() + "' " +
		"WHERE personId= '" + guest.getId() + "'";
		}
		else
		{
			query = "UPDATE Guest SET " +
		"guestType= '" + guest.getGuestType() + "', " +
		"travelAgency=  '0' " +
					"WHERE personId= '" + guest.getId() + "'";
		}
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
	public int deleteGuest(int guestId)
	{
		int rc=-1;
		  
	  	String query="DELETE FROM Guest WHERE personId= '" + guestId + "'";
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
	
	private String buildQuery(String wClause)
	{
		String query = "SELECT * FROM Guest";
		
		if(wClause.length()>0)
		{
			query = query + " WHERE " + wClause;
		}
		
		return query;
	}
	
	private Guest buildGuest(ResultSet results)
	{
		Guest rbObj = new Guest();
		IFDBPerson dbPerson = new DBPerson();
		Person personObj = new Person();
		IFDBTravelAgency dbTravelAgency = new DBTravelAgency();
		TravelAgency travelAgencyObj = new TravelAgency();
		
		try
		{
			int id = results.getInt("personId");
			rbObj.setId(id);
			
			int travelAgencyCVR = results.getInt("travelAgency");
			if(travelAgencyCVR != 0)
			{
				travelAgencyObj = dbTravelAgency.getTravelAgencyByCVR(travelAgencyCVR, true);
				rbObj.setTravelAgency(travelAgencyObj);
			}
			else
			{
				rbObj.setTravelAgency(null);
			}
			
			rbObj.setGuestType(results.getString("guestType"));
			personObj = dbPerson.searchPersonById(id, true);
			rbObj.setName(personObj.getName());
			rbObj.setZipCode(personObj.getZipcode());
			rbObj.setCountry(personObj.getCountry());
			rbObj.setAddress(personObj.getAddress());
			rbObj.setPhoneNo(personObj.getPhoneNo());
			rbObj.setEmail(personObj.getEmail());
			rbObj.setPersonType(personObj.getPersonType());
			rbObj.setPassword(personObj.getPassword());			
		}
		catch (Exception e)
		{
			System.out.println("Exception in building the guest object: " + e);
		}
		
		return rbObj;
	}
	
	//single where selection
	private Guest singleWhere(String wClause)
	{
		ResultSet results;
		Guest rbObj = new Guest();
		String query = buildQuery(wClause);
		System.out.println("Query: "+query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			if (results.next())
			{
				rbObj = buildGuest(results);
				stmt.close();
			}
			else
			{
				rbObj = null;
			}
		}
		catch (Exception e)
		{
			System.out.println("Single selection query exception: " + e);
		}
		
		return rbObj;
	}
	
	//misc where selection
	private LinkedList<Guest> miscWhere(String wClause)
	{
		ResultSet results;
		LinkedList<Guest> list = new LinkedList<Guest>();
		String query = buildQuery(wClause);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			while(results.next())
			{
				Guest rbObj = new Guest();
				rbObj = buildGuest(results);
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
	
	@Override
	public LinkedList<Guest> getAllGuests()
	{
		return miscWhere("");
	}

	@Override
	public Guest searchGuestById(int personId)
	{
		String wClause = " personId= '" + personId + "'";
		return singleWhere(wClause);
	}
	
	@Override
	public Guest findGuestInRoom(String date, int roomNo, boolean retrieveAssociation)
	{
		return singleWhere("personId=(SELECT guestId FROM RoomLine WHERE booking=(SELECT id FROM RoomBooking " +
				"WHERE arrivalDate<='"+date+" AND departureDate>='"+date+") AND roomNo='"+roomNo+"')");
	}
}
