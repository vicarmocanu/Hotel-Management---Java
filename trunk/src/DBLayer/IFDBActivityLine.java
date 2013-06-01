package DBLayer;

import java.util.LinkedList;
import Model.ActivityLine;

public interface IFDBActivityLine
{
	//get an activity line
	public ActivityLine getActivityLine(int bookingId, String startHour, boolean retrieveAssociation);
	
	//get all activity lines for a booking
	public LinkedList<ActivityLine> getActivityLinesForActivityBooking(int bookingId, boolean retrieveAssociation);
	
	//insert activity line
	public int insertActivityLine(ActivityLine activityLine) throws Exception;
	
	//update activity line
	public int updateActivityLine(ActivityLine activityLine);
	
	//delete activity line
	public int deleteActivityLine(int activityId, int bookingId, String date, String startHour);
	
	//delete all activity lines in a booking
	public int deleteActivityLinesFromActivityBooking(int bookingId);
	
	//method to avoid activity line double booking - general
	public int getActivityLineInstances1(int activityId, int bookingId, String date, String startHour, int facilityId);
	
	//method to avoid activity line -instructor- double booking
	public int getActivityLineInstances2(String date, String startHour, int instructorId);
	
	//method to avoid more than 4 activity lines in a booking
	public int getNumberOfActivityLinesInActivityBooking(int bookingId, String date);
}
