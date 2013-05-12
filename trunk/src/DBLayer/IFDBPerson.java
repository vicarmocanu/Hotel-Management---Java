package DBLayer;

import java.util.ArrayList;

import Model.Person;

public interface IFDBPerson {
	
	//get all customers
    public ArrayList<Person> getAllPerson(boolean retrieveAssociation);
   
    //find customer by name
    public Person searchPersonByName(String name, boolean retrieveAssociation);
    
    //find customer by ID
    public Person searchPersonById(int id, boolean retrieveAssociation);
    
    //insert new Customer
    public int insertPerson(Person prs) throws Exception;
    
    //update informations for one Customer
    public int updatePerson(Person prs);
    
    //delete Customer by Id    
    public int deletePerson(int id);

}
