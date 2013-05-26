package Controller;

import java.util.Date;
import java.util.LinkedList;

import DBLayer.DBActivityBooking;
import DBLayer.DBConnection1;
import DBLayer.DBGuest;
import DBLayer.DBTeam;
import DBLayer.IFDBActivityBooking;
import DBLayer.IFDBGuest;
import DBLayer.IFDBTeam;
import Model.ActivityBooking;
import Model.Guest;
import Model.Team;

public class ActivityBookingCtr
{
	public ActivityBookingCtr(){}
	
	public LinkedList<ActivityBooking> getAllActivityBookings()
	{
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		LinkedList<ActivityBooking> activityBookingsList = new LinkedList<ActivityBooking>();
		activityBookingsList = dbActivityBooking.getAllActivityBookings(true);
		return activityBookingsList;
	}
	
	public ActivityBooking getActivityBookingById(int id)
	{
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		ActivityBooking activityBookingObj = new ActivityBooking();
		activityBookingObj = dbActivityBooking.getActivityBookingById(id, true);
		return activityBookingObj;
	}
	
	public LinkedList<ActivityBooking> getActivityBookingsByDate(Date date)
	{
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		LinkedList<ActivityBooking> activityBookingList = new LinkedList<ActivityBooking>();
		activityBookingList = dbActivityBooking.getActivityBookingsByDate(date, true);
		return activityBookingList;
	}
	
	public LinkedList<ActivityBooking> getActivityBookingsForGuest(int guestId)
	{
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		LinkedList<ActivityBooking> activityBookingList = new LinkedList<ActivityBooking>();
		activityBookingList = dbActivityBooking.getActivityBookingsForGuest(guestId, true);
		return activityBookingList;
	}
	
	public void insertActivityBookig(String date, String status, int guestId, int teamId)
	{
		IFDBGuest dbGuest = new DBGuest();
		Guest guestObj = new Guest();
		guestObj = dbGuest.searchGuestById(guestId, false);
		
		IFDBTeam dbTeam = new DBTeam();
		Team teamObj = new Team();
		teamObj = dbTeam.getTeamById(teamId, false);	
		
		ActivityBooking activityBookingObj= new ActivityBooking(date, status, guestObj, teamObj);
		
		try
		 {
			 DBConnection1.startTransaction();
			 DBActivityBooking dbActivityBooking = new DBActivityBooking();
			 dbActivityBooking.insertActivityBookig(activityBookingObj);
			 DBConnection1.commitTransaction();
		 }
		 catch(Exception e)
		 {
			 DBConnection1.rollbackTransaction();
		 }
	}
	
	public int updateActivityBooking(int id, String date, String status, int guestId, int teamId)
	{
		IFDBGuest dbGuest = new DBGuest();
		Guest guestObj = new Guest();
		guestObj = dbGuest.searchGuestById(guestId, false);
		
		IFDBTeam dbTeam = new DBTeam();
		Team teamObj = new Team();
		teamObj = dbTeam.getTeamById(teamId, false);	
		
		ActivityBooking activityBookingObj = new ActivityBooking(id, date, status, guestObj, teamObj);
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		return dbActivityBooking.updateActivityBooking(activityBookingObj);
	}
	
	public int deleteActivityBooking(int id)
	{
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		return dbActivityBooking.deleteActivityBooking(id);
	}

}
