package Controller;

import java.util.LinkedList;

import DBLayer.DBConnection1;
import DBLayer.DBPerson;
import DBLayer.IFDBPerson;
import Model.Person;

public class PersonCtr
{
	public LinkedList<Person> getAllPerson()
	{
		 IFDBPerson dbPerson = new DBPerson();
		 LinkedList<Person> personList = new LinkedList<Person>();
		 personList = dbPerson.getAllPerson(true);
		 return personList;
	 }
	 
	 public Person searchPersonByName(String name)
	 {
		 IFDBPerson dbPerson = new DBPerson();
		 Person personObj = new Person();
		 personObj = dbPerson.searchPersonByName(name, true);
		 return personObj;
	 }
	 
	 public Person searchPersonById(int id)
	 {
		 IFDBPerson dbPerson = new DBPerson();
		 Person personObj = new Person();
		 personObj = dbPerson.searchPersonById(id, true);
		 return personObj;
	 }
	 
	 public void insertPerson(String name, String address, int zipcode,
			 String country, String phoneNo, String email, String personType, 
			 String password)
	 {
		 Person personObj = new Person();
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
	 
	 public int updatePerson(int id, String name, String address, int zipcode,
			 String country, String phoneNo, String email, String personType,
			 String password)
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
	 
	 public int deletePerson(int id)
	 {
		 IFDBPerson dbPerson = new DBPerson();
		 return dbPerson.deletePerson(id);
	 }
	 
	 public String getPersonPassword(String name, String zipcode, String country, String address)
	 {
		 String step1 = name.substring(0, 1);
		 String step2 = zipcode.substring(0, 3);
		 String step3 = country.substring(0, 2);
		 String step4 = address.substring(0, 2);
		 String endPassword = step1 + step2 + step3 + step4;
		 return endPassword;
	}
	 
	public boolean checkPersonInstanceCount(int id, String name, int zipcode, String country, String address)
	{
		boolean check = false;
		int instances = 0;
		
		IFDBPerson dbPerson = new DBPerson();
		instances = dbPerson.getPersonInstances(id, name, zipcode, country, address);
		
		if(instances == 0)
		{
			check = true;
		}
		else
		{
			check = false;
		}
		
		return check;
	}
}