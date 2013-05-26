package DBLayer;

import java.util.Date;
import java.util.LinkedList;

import Model.ActivityBooking;

public interface IFDBActivityBooking
{
	//method to get all the activity bookings
	public LinkedList<ActivityBooking> getAllActivityBookings(boolean retrieveAssociation);
	
	//method to get a particular activity booking by its id
	public ActivityBooking getActivityBookingById(int id, boolean retrieveAssociation);
	
	//method to get all the activity bookings that have occurred at a particular date
	public LinkedList<ActivityBooking> getActivityBookingsByDate(Date date, boolean retrieveAssociation);
	
	//method to get all the activity bookings a guest has made
	public LinkedList<ActivityBooking> getActivityBookingsForGuest(int guestId, boolean retrieveAssociation);
	
	//method to insert a new activity booking
	public int insertActivityBookig(ActivityBooking activityBooking) throws Exception;
	
	//method to update a activity booking
	public int updateActivityBooking(ActivityBooking activityBooking);
	
	//method to delete a particular activity booking
	public int deleteActivityBooking(int id);

}
