package DBLayer;

import java.util.LinkedList;

import Model.ActivityBooking;

public interface IFDBActivityBooking
{
	//method to get all the activity bookings
	public LinkedList<ActivityBooking> getAllActivityBookings();
	
	//method to get an activity booking by its id
	public ActivityBooking getActivityBookingById(int id);
	
	//method to get an activity booking that occurred at a particular date for a guest
	public ActivityBooking getActivityBookingForDate(int guestId, String date, String status);
	
	//method to get all the activity bookings a guest has made
	public LinkedList<ActivityBooking> getActivityBookingsForGuest(int guestId);
	
	//method to insert a new activity booking
	public int insertActivityBooking(ActivityBooking activityBooking) throws Exception;
	
	//method to update/cancel an activity booking 
	public int updateActivityBookingStatus(ActivityBooking activityBooking);
	
	//method to delete an activity booking by its id
	public int deleteActivityBooking(int id);
	
	//method to delete an activity booking for a guest at a date
	public int deleteActivityBookingForDate(int guestId, String date);
	
	//method to avoid double activity booking per date
	public int getActivityBookingInstances(String date, int guestId);
	
}
