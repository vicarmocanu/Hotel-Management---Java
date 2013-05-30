package Controller;

import java.util.Date;
import java.util.LinkedList;

import DBLayer.DBActivityBooking;
import DBLayer.DBActivityLine;
import DBLayer.DBActivityType;
import DBLayer.DBConnection1;
import DBLayer.DBFacility;
import DBLayer.DBGuest;
import DBLayer.DBInstructor;
import DBLayer.DBTeam;
import DBLayer.IFDBActivityBooking;
import DBLayer.IFDBActivityLine;
import DBLayer.IFDBActivityType;
import DBLayer.IFDBFacility;
import DBLayer.IFDBGuest;
import DBLayer.IFDBInstructor;
import DBLayer.IFDBTeam;
import Model.ActivityBooking;
import Model.ActivityLine;
import Model.ActivityType;
import Model.Facility;
import Model.Guest;
import Model.Instructor;
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
		
		ActivityBooking activityBookingObj= new ActivityBooking();
		
		if(teamObj != null)
		{
			activityBookingObj.setDate(date);
			activityBookingObj.setStatus(status);
			activityBookingObj.setGuest(guestObj);
			activityBookingObj.setTeam(teamObj);
		}
		else
		{
			activityBookingObj.setDate(date);
			activityBookingObj.setStatus(status);
			activityBookingObj.setGuest(guestObj);
		}
		
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
		
		ActivityBooking activityBookingObj = new ActivityBooking();
		
		if(teamObj != null)
		{
			activityBookingObj.setDate(date);
			activityBookingObj.setStatus(status);
			activityBookingObj.setGuest(guestObj);
			activityBookingObj.setTeam(teamObj);
		}
		else
		{
			activityBookingObj.setDate(date);
			activityBookingObj.setStatus(status);
			activityBookingObj.setGuest(guestObj);
		}
		
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		return dbActivityBooking.updateActivityBooking(activityBookingObj);
	}
	
	public int deleteActivityBooking(int id)
	{
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		return dbActivityBooking.deleteActivityBooking(id);
	}
	
	public ActivityLine getActivityLineById(int activityId,	int activityBookingId, boolean retrieveAssociation)
	{
		IFDBActivityLine dbActivityLine = new DBActivityLine();
		ActivityLine activityLineObj= new ActivityLine();
		activityLineObj = dbActivityLine.getActivityLineById(activityId, activityBookingId, true);
		return activityLineObj;
	}
	
	public LinkedList<ActivityLine> getActivityLinesForActivityBooking(int activityBookingId, boolean retrieveAssociation)
	{
		IFDBActivityLine dbActivityLine = new DBActivityLine();
		LinkedList<ActivityLine> bookingActivityLines = new LinkedList<ActivityLine>();
		bookingActivityLines = dbActivityLine.getActivityLinesForActivityBooking(activityBookingId, true);
		return bookingActivityLines;
	}
	
	
	public void insertActivityLine(int activityId, int instructorId, String date, String startHour, String endHour, int facilityId, int activityBookingId)
	{
		if(performCompleteCheck(activityBookingId, date, startHour) == true)
		{
			IFDBActivityType dbActivityType = new DBActivityType();
			ActivityType activityTypeObj = dbActivityType.getActivityTypeByID(activityId, false);
			
			IFDBInstructor dbInstructor  = new DBInstructor();
			Instructor instructorObj = dbInstructor.getInstructorById(instructorId, true);
			
			IFDBFacility dbFacility = new DBFacility();
			Facility facilityTypeObj = dbFacility.getFacilityById(facilityId, true);
			
			IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
			ActivityBooking activityBookingObj = dbActivityBooking.getActivityBookingById(activityBookingId, true);
			
			ActivityLine activityLineObj = new ActivityLine();
			
			if(instructorObj !=null)
			{
				activityLineObj.setActivity(activityTypeObj);
				activityLineObj.setActivityBooking(activityBookingObj);
				activityLineObj.setDate(date);
				activityLineObj.setStartHour(startHour);
				activityLineObj.setEndHour(endHour);
				activityLineObj.setFacility(facilityTypeObj);
				activityLineObj.setInstructor(instructorObj);
			}
			else
			{
				activityLineObj.setActivity(activityTypeObj);
				activityLineObj.setActivityBooking(activityBookingObj);
				activityLineObj.setDate(date);
				activityLineObj.setStartHour(startHour);
				activityLineObj.setEndHour(endHour);
				activityLineObj.setFacility(facilityTypeObj);
			}
			
			try
			{
				 DBConnection1.startTransaction();
				 DBActivityLine dbActivityLine = new DBActivityLine();
				 dbActivityLine.insertActivityLine(activityLineObj);
				 DBConnection1.commitTransaction();
			 }
			 catch(Exception e)
			 {
				 DBConnection1.rollbackTransaction();
			 }
		}
		else
		{
			System.out.println("Cannot book this activity.");
		}
	}
	
	public int updateActivityLine(int activityId, int instructorId, String date, String startHour, String endHour, int facilityId, int activityBookingId)
	{
		IFDBActivityType dbActivityType = new DBActivityType();
		ActivityType activityTypeObj = dbActivityType.getActivityTypeByID(activityId, false);
		
		IFDBInstructor dbInstructor  = new DBInstructor();
		Instructor instructorObj = dbInstructor.getInstructorById(instructorId, true);
		
		IFDBFacility dbFacility = new DBFacility();
		Facility facilityTypeObj = dbFacility.getFacilityById(facilityId, true);
		
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		ActivityBooking activityBookingObj = dbActivityBooking.getActivityBookingById(activityBookingId, true);
		
		ActivityLine activityLineObj = new ActivityLine();
		if(instructorObj !=null)
		{
			activityLineObj.setActivity(activityTypeObj);
			activityLineObj.setActivityBooking(activityBookingObj);
			activityLineObj.setDate(date);
			activityLineObj.setStartHour(startHour);
			activityLineObj.setEndHour(endHour);
			activityLineObj.setFacility(facilityTypeObj);
			activityLineObj.setInstructor(instructorObj);
		}
		else
		{
			activityLineObj.setActivity(activityTypeObj);
			activityLineObj.setActivityBooking(activityBookingObj);
			activityLineObj.setDate(date);
			activityLineObj.setStartHour(startHour);
			activityLineObj.setEndHour(endHour);
			activityLineObj.setFacility(facilityTypeObj);
		}		
		IFDBActivityLine dbActivityLine = new DBActivityLine();
		return dbActivityLine.updateActivityLine(activityLineObj);
	}
	
	public int deleteActivityLine(int activityId, int activityBookingId, Date startHour)
	{
		IFDBActivityLine dbActivityLine = new DBActivityLine();
		return dbActivityLine.deleteActivityLine(activityId, activityBookingId, startHour);
	}
	
	public int deleteBookingActivityLines(int activityBookingId)
	{
		IFDBActivityLine dbActivityline = new DBActivityLine();
		return dbActivityline.deleteBookingActivityLines(activityBookingId);
	}
	
	public boolean performCompleteCheck(int activityBookingId, String date, String startHour)
	{
		boolean check = false;
		
		if((performInstanceCheck(activityBookingId, date, startHour)<1) && (performMaxNumberCheck(activityBookingId, date)<4))
		{
			check = true;
		}
		
		else
		{
			check = false;
			
			int instances = performInstanceCheck(activityBookingId, date, startHour);
			System.out.println("Number of activity line instances: " + instances);
			
			int numbers = performMaxNumberCheck(activityBookingId, date);
			System.out.println("Number of activity lines per booking: " + numbers);
			
		}
		
		return check;
	}
	
	public int performInstanceCheck(int bookingId, String date, String startHour)
	{
		int instances = 0;
		IFDBActivityLine dbActivityLine = new DBActivityLine();
		instances = dbActivityLine.getActivityLineInstances(bookingId, date, startHour);		
		return instances;
		
	}
	
	public int performMaxNumberCheck(int bookingId, String date)
	{
		int numbers = 0;
		IFDBActivityLine dbActivityLine = new DBActivityLine();
		numbers = dbActivityLine.getNumberOfActivityLinesForBooking(bookingId, date);
		return numbers;
	}

}
