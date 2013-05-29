package Controller;

import java.util.LinkedList;

import DBLayer.DBConnection1;
import DBLayer.DBPerson;
import DBLayer.IFDBPerson;
import Model.Person;


public class PersonCtr {
	
	 public LinkedList<Person> getAllPerson()
	 {
		 IFDBPerson dbPerson = new DBPerson();
			LinkedList<Person> personList = new LinkedList<Person>();
			personList = dbPerson.getAllPerson(true);
			return personList;
	 }
	   
	    //find customer by name
	    public Person searchPersonByName(String name)
	    {
	    	IFDBPerson dbPerson = new DBPerson();
			Person personObj = new Person();
			personObj = dbPerson.searchPersonByName(name, true);
			return personObj;
	    }
	    
	    //find customer by ID
	    public Person searchPersonById(int id)
	    {
	    	IFDBPerson dbPerson = new DBPerson();
			Person personObj = new Person();
			personObj = dbPerson.searchPersonById(id, true);
			return personObj;
	    }
	    
	    //insert new Customer
	    public void insertPerson(int id, String name, String address, int zipcode,String country, String phoneNo, 
				String email, String personType, String password)
	    {
	    	Person personObj = new Person();
	    	personObj.setId(id);
	    	personObj.setName(name);
	    	personObj.setAddress(address);
	    	personObj.setZipcode(zipcode);
	    	personObj.setCountry(country);
	    	personObj.setPhoneNo(phoneNo);
	    	personObj.setEmail(email);
	    	personObj.setPersonType(personType);
	    	personObj.setPassword(password);
	    	
	    	try
			 {
				 DBConnection1.startTransaction();
				 DBPerson dbPerson = new DBPerson();
				 dbPerson.insertPerson(personObj);
				 DBConnection1.commitTransaction();
			 }
			 catch(Exception e)
			 {
				 DBConnection1.rollbackTransaction();
			 }
	    }
	    
	    //update informations for one Customer
	    public int updatePerson(int id, String name, String address, int zipcode,
				String country, String phoneNo, String email, String personType, String password)
	    {
	    	IFDBPerson dbPerson = new DBPerson();
			Person personObj = new Person();
	    	personObj.setId(id);
	    	personObj.setName(name);
	    	personObj.setAddress(address);
	    	personObj.setZipcode(zipcode);
	    	personObj.setCountry(country);
	    	personObj.setPhoneNo(phoneNo);
	    	personObj.setEmail(email);
	    	personObj.setPersonType(personType);
	    	personObj.setPassword(password);
	    	
	    	return dbPerson.updatePerson(personObj);
	    }
	    
	    //delete Customer by Id    
	    public int deletePerson(int id)
	    {
	    	IFDBPerson dbPerson = new DBPerson();
			return dbPerson.deletePerson(id);
	    }

}
