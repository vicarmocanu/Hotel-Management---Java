package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Model.Location;
import Model.Person;

public class DBPerson implements IFDBPerson{
	
private Connection con;
	
	public DBPerson()
	{
		con = DBConnection1.getInstance().getDBcon();
	}
	
	@Override
	public int insertPerson(Person prs) throws Exception
	{
		//call to get the next person id
		int nextPersonId = GetMax.getMaxId("SELECT MAX(id) FROM Person");
		nextPersonId = nextPersonId + 1;
		System.out.println("Next person id = " + nextPersonId);
		
		int rc = -1;
		
		String query = "INSERT INTO Person(id, name, zipcode, country, address, phoneNo, email, personType, password) VALUES('" +
				nextPersonId + "','" +
				prs.getName()+"','"+
				prs.getZipcode()+ "','"+
				prs.getCountry()+"','"+
				prs.getAddress()+"','"+
				prs.getPhoneNo()+"','"+
				prs.getEmail()+"','"+
				prs.getPersonType()+"','"+
				prs.getPassword()+"')";
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
	public int updatePerson(Person prs)
	{
		int rc = -1;
		
		String query = "UPDATE Person SET " +
				"name='"+prs.getName()+"', "+
				"zipcode='"+prs.getZipcode()+"', "+
				"country='"+prs.getCountry()+"', "+
				"address='"+prs.getAddress()+"', "+
				"phoneNo='"+prs.getPhoneNo()+"', "+
				"email='"+prs.getEmail()+"', "+
				"personType='" + prs.getPersonType() + "', " +
				"password='"+prs.getPassword()+"' "+
						"WHERE id='" + prs.getId() + "'";
		System.out.println("Update query: " + query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc=stmt.executeUpdate(query);
			stmt.close();
		}
		catch (Exception e)
		{
			System.out.println("Update exception: " + e);
		}
		
		return rc;
	}
	
	@Override
	public int deletePerson(int prsId)
	{
		int rc=-1;
		  
	  	String query="DELETE FROM Person WHERE id= '" +
				prsId + "'";
                System.out.println("Delete query: " + query);
	  	try
	  	{ 
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 	  	rc = stmt.executeUpdate(query);
	 	  	stmt.close();
  		}
   	    catch(Exception ex){
	 	  	System.out.println("Delete exception: "+ex);
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
		
		try
		{
			rbObj.setId(results.getInt(1));
			rbObj.setName(results.getString(2));
			rbObj.setZipcode(results.getInt(3));
			rbObj.setCountry(results.getString(4));
			rbObj.setAddress(results.getString(5));
			rbObj.setPhoneNo(results.getString(6));
			rbObj.setEmail(results.getString(7));
			rbObj.setPersonType(results.getString(8));
			rbObj.setPassword(results.getString(9));
		}
		catch (Exception e)
		{
			System.out.println("Exception in building the person object: " + e);
		}
		
		return rbObj;
	}
	
	//single where selection
	private Person singleWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		
		Person rbObj = new Person();
		String query = buildQuery(wClause);
		System.out.println("Query: "+query);
		
		try 
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			if (results.next())
			{
				rbObj = buildPerson(results);
				stmt.close();
				if(retrieveAssociation)
				{//location selection
					IFDBLocation dbLocation = new DBLocation();
					Location location = new Location();
					location = dbLocation.getLocation(rbObj.getZipcode(), rbObj.getCountry());
					if(location != null)
					{
						rbObj.setZipcode(location.getZipCode());
						rbObj.setCountry(location.getCountry());
						System.out.println("Location selection.");
					}
				}
			}
			else
			{
				rbObj = null;
			}
		}
		catch (Exception e)
		{
			System.out.println("Single selection query exception: " + e);
			e.printStackTrace();
		}
		
		return rbObj;
	}
	
	//misc where selection
	private LinkedList<Person> miscWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		
		LinkedList<Person> list = new LinkedList<Person>();
		String query = buildQuery(wClause);
		
		try 
		{
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
			
			if(retrieveAssociation == true)
			{
				IFDBLocation dbLocation = new DBLocation();
				for(Person personObj : list)
				{
					Location location = new Location();
					location = dbLocation.getLocation(personObj.getZipcode(), personObj.getCountry());
					if(location != null)
					{
						personObj.setZipcode(location.getZipCode());
						personObj.setCountry(location.getCountry());
						System.out.println("Location selection.");
					}
				}
			}
			
		}
		catch (Exception e)
		{
			System.out.println("Multiple query selection exception: "+e);
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public LinkedList<Person> getAllPerson(boolean retriveAssociation)
	{
		return miscWhere("", retriveAssociation);
	}

	@Override
	public Person searchPersonById(int id,
			boolean retriveAssociation)
	{
		String wClause = " id= '" + id + "'";
		return singleWhere(wClause, retriveAssociation);
	}

	@Override
	public Person searchPersonByName(String name, boolean retriveAssociation)
	{
		String wClause = " name= '" + name + "'";
		return singleWhere(wClause, retriveAssociation);
	}
	
	@Override
	public int getPersonInstances(int id, String name, int zipcode, String country, String address)
	{
		int instances = 0;
		
		ResultSet results;
		
		String query = "SELECT COUNT(*) AS personInstances FROM Person " + 
		" WHERE id<> '" +  id + "' AND name= '" + name + "' AND zipcode= '" + zipcode + 
		"' AND country= '" + country + "' AND address= '" + address + "'";
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			while( results.next() )
			{
				instances = results.getInt("personInstances");
				System.out.println("Person instances: " + instances);
			}
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception in returning the person instance count: " + e);
		}
		return instances;
	}
}
