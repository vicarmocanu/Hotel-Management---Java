
package DBLayer;
import Model.Guest;
import java.util.LinkedList;


public interface IFDBGuest {
	
	  //get all guests
    public LinkedList<Guest> getAllGuests();
    
    //find customer by ID
    public Guest searchGuestById(int personId);
    
    //find guest who was in this room on that date
	public Guest findGuestInRoom(String date, int roomNo, boolean retrieveAssociation);
    
    //insert new Customer
    public int insertGuest(Guest guest) throws Exception;
    
    //update informations for one Customer
    public int updateGuest(Guest gst);
    
    //delete Customer by Id    
    public int deleteGuest(int id);


}
