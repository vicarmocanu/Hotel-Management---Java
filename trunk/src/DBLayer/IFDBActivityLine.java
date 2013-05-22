package DBLayer;

import java.util.Date;
import java.util.LinkedList;

import Model.ActivityLine;

public interface IFDBActivityLine
{
	//get activity line by activity id and booking id
	public ActivityLine getActivityLineById(int activityId, int activityBookingId, boolean retrieveAssociation);
	
	//get all activity lines for a booking
	public LinkedList<ActivityLine> getActivityLineForActivityBooking(int activityBookingId, boolean retrieveAssociation);
	
	//insert activity line
	public int insertActivityLine(ActivityLine activityLine) throws Exception;
	
	//update activity line by activity booking id
	public int updateActivityLine(ActivityLine activityLine);
	
	//delete activity line by activity booking id, activity id and start hour
	public int deleteActivityLine(int activityId, int activityBookingId, Date startHour);
}
