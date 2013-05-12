package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Person;

public class DBPerson implements IFDBPerson{
	
private Connection con;
	
	public DBPerson()
	{
		con = DBConnection1.getInstance().getDBcon();
	}
	
	public int insertPerson(Person prs) throws Exception {
		String query = "INSERT INTO Person(id, name, address, zipcode, city, country, phoneNo, email, password)" +
				" VALUES('" +
				prs.getId()+"','"+
				prs.getName()+"','"+
				prs.getAddress()+"','"+
				prs.getZipcode()+ "')"+
				prs.getCity()+","+
				prs.getCountry()+","+
				prs.getPhoneNo()+","+
				prs.getEmail()+","+
				prs.getPassword()+",";
		
		int rc = -1;
		System.out.println("insert: " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("Person is not inserted");
	        throw new Exception ("Person is not inserted correctly!");
		}
		
		return rc;
	}

	
	public int updatePerson(Person prs) {
		int rc = -1;
		
		String query = "UPDATE Person SET " +
				"id='" + prs.getId() +"', "+
				"name='"+prs.getName()+"', "+
				"address='"+prs.getAddress()+"', "+
				"zipcode='"+prs.getZipcode()+"' "+
				"city='"+prs.getCity()+"' "+
				"country='"+prs.getCountry()+"' "+
				"phoneNo='"+prs.getPhoneNo()+"' "+
				"email='"+prs.getEmail()+"' "+
				"password='"+prs.getPassword()+"' "+
						"WHERE id='" +prs.getId()+"'";
		System.out.println("Update query: " + query);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc=stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception e) {
			System.out.println("Update exception in Person: " + e);
		}
		
		return rc;

	}

	
	public int deletePerson(int prsId) {
		int rc=-1;
		  
	  	String query="DELETE FROM Person WHERE id = '" +
				prsId + "'";
                System.out.println(query);
	  	try{ 
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 	  	rc = stmt.executeUpdate(query);
	 	  	stmt.close();
  		}
   	    catch(Exception ex){
	 	  	System.out.println("Delete exception in Person: "+ex);
   	    }
		return(rc);
	}
	
	
	private String buildQuery(String wClause)
	{
		String query = "SELECT * FROM Person";
		
		if(wClause.length()>0)
		{
			query = query + " WHERE " + wClause;
		}
		
		return query;
	}
	
	private Person buildPerson(ResultSet results)
	{
		Person rbObj = new Person();		
		
		try {
			rbObj.setId(results.getInt("id"));
			rbObj.setName(results.getString("name"));
			rbObj.setAddress(results.getString("address"));
			rbObj.setZipcode(results.getInt("zipcode"));
			rbObj.setCity(results.getString("city"));
			rbObj.setCountry(results.getString("country"));
			rbObj.setPhoneNo(results.getString("phoneNo"));
			rbObj.setEmail(results.getString("email"));
			rbObj.setPassword(results.getString("password"));
		} catch (Exception e) {
			System.out.println("Error in building the Person object!");
		}
		
		return rbObj;
	}
	
	private Person singleWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		Person rbObj = new Person();
		String query = buildQuery(wClause);
		System.out.println("Query: "+query);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			if (results.next()) {
				rbObj = buildPerson(results);
				System.out.println("Person build successfully!");
				stmt.close();
			}
			else
			{
				rbObj = null;
			}			
		} catch (Exception e) {
			System.out.println("Query exception - select Person : "+e);
			e.printStackTrace();
		}
		
		return rbObj;
	}
	
	private ArrayList<Person> miscWhere(String wClause, boolean retrieveAssiciation)
	{
		ResultSet results;
		ArrayList<Person> list = new ArrayList<Person>();
		String query = buildQuery(wClause);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			while(results.next())
			{
				Person rbObj = new Person();
				rbObj = buildPerson(results);
				list.add(rbObj);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("Query exception - select Person : "+e);
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Person> getAllPerson(boolean retriveAssociation) {
		return miscWhere("", retriveAssociation);
	}

	public Person searchPersonById(int id,
			boolean retriveAssociation) {
		String wClause = "  Guest ID: = '" + id + "'";
		return singleWhere(wClause, retriveAssociation);
	}

	
	public Person searchPersonByName(String name, boolean retriveAssociation) {
		String wClause = "Name: " + name + ",";
		System.out.println("Person " + wClause);
		return singleWhere(wClause, retriveAssociation);
	}

}
