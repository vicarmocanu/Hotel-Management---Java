package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Model.Guest;

public class DBGuest implements IFDBGuest {
	private Connection con;
	
	public DBGuest()
	{
		con = DBConnection1.getInstance().getDBcon();
	}
	
	@Override
	public Guest findGuestById(int id, boolean retrieveAssociation) {
		return singleWhere("personId='"+id+"'", retrieveAssociation);
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
		Guest guestObj = new Guest();
		
		try {
			guestObj.setId(results.getInt("personId"));
			guestObj.setType(results.getString("type"));
		} catch (Exception e) {
			// create object: handle exception
			System.out.println("Error in building the Guest object.");
		}
		
		return guestObj;
	}
	
	private Guest singleWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		Guest guestObj = new Guest();
		String query = buildQuery(wClause);
		System.out.println("Query: "+query);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			if (results.next()) {
				guestObj = buildGuest(results);
				stmt.close();
			}
			else
			{
				guestObj = null;
			}			
		} catch (Exception e) {
			// select guest: handle exception
		}
		
		return guestObj;
	}
}
