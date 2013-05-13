package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Model.Guest;

public class DBGuest implements IFDBGuest{
	
private Connection con;
	
	public DBGuest()
	{
		con = DBConnection1.getInstance().getDBcon();
	}
	
	public int insertGuest(Guest gt) throws Exception {
		String query = "INSERT INTO Guest(id, name, address, zipcode, city, country, phoneNo, email, password, type)" +
				" VALUES('" +
				gt.getId()+"','"+
				gt.getName()+"','"+
				gt.getAddress()+"','"+
				gt.getZipCode()+ "')"+
				gt.getCity()+","+
				gt.getCountry()+","+
				gt.getPhoneNo()+","+
				gt.getEmail()+","+
				gt.getPassword()+","+
				gt.getType();
		
		int rc = -1;
		System.out.println("insert: " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("Guest is not inserted");
	        throw new Exception ("Guest is not inserted correctly!");
		}
		
		return rc;
	}

	
	public int updateGuest(Guest gst) {
		Guest guest = gst;
		int rc = -1;
		
		String query = "UPDATE Guest SET " +
				"id='" + guest.getId() +"', "+
				"name='"+guest.getName()+"', "+
				"address='"+guest.getAddress()+"', "+
				"zipcode='"+guest.getZipCode()+"' "+
				"city='"+guest.getCity()+"' "+
				"country='"+guest.getCountry()+"' "+
				"phoneNo='"+guest.getPhoneNo()+"' "+
				"email='"+guest.getEmail()+"' "+
				"password='"+guest.getPassword()+"' "+
				"type='"+guest.getType()+"' "+
						"WHERE id='" +guest.getId()+"'";
		System.out.println("Update query: " + query);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc=stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception e) {
			System.out.println("Update exception in Guest: " + e);
		}
		
		return rc;

	}

	
	public int deleteGuest(int guestId) {
		int rc=-1;
		  
	  	String query="DELETE FROM Guest WHERE id = '" +
				guestId + "'";
                System.out.println(query);
	  	try{ 
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 	  	rc = stmt.executeUpdate(query);
	 	  	stmt.close();
  		}
   	    catch(Exception ex){
	 	  	System.out.println("Delete exception in Guest: "+ex);
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
		
		try {
			rbObj.setId(results.getInt("id"));
			rbObj.setName(results.getString("name"));
			rbObj.setAddress(results.getString("address"));
			rbObj.setZipCode(results.getInt("zipcode"));
			rbObj.setCity(results.getString("city"));
			rbObj.setCountry(results.getString("country"));
			rbObj.setPhoneNo(results.getString("phoneNo"));
			rbObj.setEmail(results.getString("email"));
			rbObj.setPassword(results.getString("password"));
			rbObj.setType(results.getString("type"));
		} catch (Exception e) {
			System.out.println("Error in building the Guest object!");
		}
		
		return rbObj;
	}
	
	private Guest singleWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		Guest rbObj = new Guest();
		String query = buildQuery(wClause);
		System.out.println("Query: "+query);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			if (results.next()) {
				rbObj = buildGuest(results);
				System.out.println("Guest build successfully!");
				stmt.close();
			}
			else
			{
				rbObj = null;
			}			
		} catch (Exception e) {
			System.out.println("Query exception - select Guest : "+e);
			e.printStackTrace();
		}
		
		return rbObj;
	}
	
	private LinkedList<Guest> miscWhere(String wClause, boolean retrieveAssiciation)
	{
		ResultSet results;
		LinkedList<Guest> list = new LinkedList<Guest>();
		String query = buildQuery(wClause);
		
		try {
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
		} catch (Exception e) {
			System.out.println("Query exception - select Guest : "+e);
			e.printStackTrace();
		}
		return list;
	}
	
	public LinkedList<Guest> getAllGuest(boolean retriveAssociation) {
		return miscWhere("", retriveAssociation);
	}

	public Guest searchGuestById(int id,
			boolean retriveAssociation) {
		String wClause = "  Guest ID: = '" + id + "'";
		return singleWhere(wClause, retriveAssociation);
	}

	
	public Guest searchGuestByName(String name, boolean retriveAssociation) {
		String wClause = "Name: " + name + ",";
		System.out.println("Guest " + wClause);
		return singleWhere(wClause, retriveAssociation);
	}

}
