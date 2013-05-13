package DBLayer;
import Model.Guest;
import java.util.LinkedList;

public interface IFDBGuest {
	
	  //get all customers
    public LinkedList<Guest> getAllGuest(boolean retrieveAssociation);
   
    //find customer by name
    public Guest searchGuestByName(String name, boolean retrieveAssociation);
    
    //find customer by ID
    public Guest searchGuestById(int id, boolean retrieveAssociation);
    
    //insert new Customer
    public int insertGuest(Guest cust) throws Exception;
    
    //update informations for one Customer
    public int updateGuest(Guest cust);
    
    //delete Customer by Id    
    public int deleteGuest(int id);

}
