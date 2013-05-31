package DBLayer;

import java.util.Date;
import java.util.LinkedList;

import Model.ActivityLine;

public interface IFDBActivityLine
{
	//get activity line by activity id and booking id
	public ActivityLine getActivityLineById(int activityId, int activityBookingId, boolean retrieveAssociation);
	
	//get all activity lines for a booking
	public LinkedList<ActivityLine> getActivityLinesForActivityBooking(int activityBookingId, boolean retrieveAssociation);
	
	//insert activity line
	public int insertActivityLine(ActivityLine activityLine) throws Exception;
	
	//update activity line by activity booking id
	public int updateActivityLine(ActivityLine activityLine);
	
	//delete activity line by activity booking id, activity id and start hour
	public int deleteActivityLine(int activityId, int activityBookingId, Date startHour);
	
	//delete all activity lines in a booking
	public int deleteBookingActivityLines(int activityBookingId);
	
	//method to avoid activity line -activity- double booking
	public int getActivityLineInstances1(int bookingId, String date, String startHour);
	
	//method to avoid activity line -facility- double booking
	public int getActivityLineInstances2(String date, String startHour, int facilityId);
	
	//method to avoid activity line -instructor- double booking
	public int getActivityLineInstances3(String date, String startHour, int instructorId);
	
	//method to avoid more than 4 activity lines
	public int getNumberOfActivityLinesForBooking(int bookingId, String date);
}
