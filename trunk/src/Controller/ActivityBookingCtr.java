package Controller;

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
		activityBookingsList = dbActivityBooking.getAllActivityBookings();
		return activityBookingsList;
	}
	
	public ActivityBooking getActivityBookingById(int id)
	{
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		ActivityBooking activityBookingObj = new ActivityBooking();
		activityBookingObj = dbActivityBooking.getActivityBookingById(id);
		return activityBookingObj;
	}
	
	public ActivityBooking getActivityBookingForDate(int guestId, String date, String status)
	{
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		ActivityBooking activityBookingObj = new ActivityBooking();
		activityBookingObj = dbActivityBooking.getActivityBookingForDate(guestId, date, status);
		return activityBookingObj;
	}
	
	public LinkedList<ActivityBooking> getActivityBookingsForGuest(int guestId)
	{
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		LinkedList<ActivityBooking> activityBookingList = new LinkedList<ActivityBooking>();
		activityBookingList = dbActivityBooking.getActivityBookingsForGuest(guestId);
		return activityBookingList;
	}
	
	public boolean activityBookingCompleteCheck(String date, int guestId)
	{
		boolean check = false;
		int instances = 0;
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		instances = dbActivityBooking.getActivityBookingInstances(date, guestId);
		
		if(instances<1)
		{
			check = true;
		}
		else
		{
			check = false;
		}
		
		return check;
	}
	
	public void insertActivityBooking(int guestId, String date, String status)
	{
		IFDBGuest dbGuest = new DBGuest();
		Guest guestObj = new Guest();
		guestObj = dbGuest.searchGuestById(guestId);
		
		ActivityBooking activityBookingObj = new ActivityBooking(date, status, guestObj);
		
		try
		{
			DBConnection1.startTransaction();
			DBActivityBooking dbActivityBooking = new DBActivityBooking();
			dbActivityBooking.insertActivityBooking(activityBookingObj);
			DBConnection1.commitTransaction();
		}
		catch(Exception e)
		{
			DBConnection1.rollbackTransaction();
		}
	}	
	
	
	public int updateActivityBooking(int id, int guestId, String date, String status)
	{
		IFDBGuest dbGuest = new DBGuest();
		Guest guestObj = new Guest();
		guestObj = dbGuest.searchGuestById(guestId);
		
		ActivityBooking activityBookingObj = new ActivityBooking();
		
		activityBookingObj.setDate(date);
		activityBookingObj.setGuest(guestObj);
		activityBookingObj.setStatus(status);
		
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		return dbActivityBooking.updateActivityBookingStatus(activityBookingObj);
	}
	
	public int deleteActivityBooking(int id)
	{
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		return dbActivityBooking.deleteActivityBooking(id);
	}
	
	public int deleteActivityBookingForDate(int guestId, String date)
	{
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		return dbActivityBooking.deleteActivityBookingForDate(guestId, date);
	}
	
	public ActivityLine getActivityLine(int activityId, int bookingId, int facilityId)
	{
		IFDBActivityLine dbActivityLine = new DBActivityLine();
		ActivityLine activityLineObj= new ActivityLine();
		activityLineObj = dbActivityLine.getActivityLine(activityId, bookingId, facilityId);
		return activityLineObj;
	}
	
	public LinkedList<ActivityLine> getActivityLinesForActivityBooking(int bookingId)
	{
		IFDBActivityLine dbActivityLine = new DBActivityLine();
		LinkedList<ActivityLine> bookingActivityLines = new LinkedList<ActivityLine>();
		bookingActivityLines = dbActivityLine.getActivityLinesForActivityBooking(bookingId);
		return bookingActivityLines;
	}
	
	public boolean checkActivityLineInstances1(int activityId, int bookingId, int facilityId)
	{
		boolean check = false;
		int instances = 0;
		IFDBActivityLine dbActivityLine = new DBActivityLine();
		instances = dbActivityLine.getActivityLineInstances1(activityId, bookingId, facilityId);
		
		if(instances<1)
		{
			check = true;
		}
		else
		{
			check = false;
		}
		
		return check;
	}
	
	public boolean checkActivityLineInstances2(String date, String startHour, int instructorId)
	{
		boolean check = false;
		int instances = 0;
		IFDBActivityLine dbActivityLine = new DBActivityLine();
		instances = dbActivityLine.getActivityLineInstances2(date, startHour, instructorId);		
		if(instances<1)
		{
			check = true;
		}
		else
		{
			check = false;
		}
		
		return check;
	}
	
	public boolean checkActivityLineInstances3(int activityId, String date, String startHour, int facilityId)
	{
		boolean check = false;
		int instances = 0;
		IFDBActivityLine dbActivityLine = new DBActivityLine();
		instances = dbActivityLine.getActivityLineInstances3(activityId, date, startHour, facilityId);
		if(instances<1)
		{
			check = true;
		}
		else
		{
			check = false;
		}
		
		return check;
	}
	
	public boolean checkActivityLineInstances4(int bookingId, String date, String startHour)
	{
		boolean check = false;
		int instances = 0;
		IFDBActivityLine dbActivityLine = new DBActivityLine();
		instances = dbActivityLine.getActivityLineInstances4(bookingId, date, startHour);
		if(instances<1)
		{
			check = true;
		}
		else
		{
			check = false;
		}
		
		return check;
	}
	
	public boolean checkNumberOfActivityLinesInActivityBooking(int bookingId, String date)
	{
		boolean check = false;
		int numbers = 0;
		IFDBActivityLine dbActivityLine = new DBActivityLine();
		numbers = dbActivityLine.getNumberOfActivityLinesInActivityBooking(bookingId, date);
		if(numbers<4)
		{
			check = true;
		}
		else
		{
			check = false;
		}
		
		return check;
	}
	
	public void insertSimpleActivityLine(int activityId, int bookingId, String date, String startHour, int facilityId, String status)
	{
		IFDBActivityType dbActivityType = new DBActivityType();
		ActivityType activityTypeObj = dbActivityType.getActivityTypeByID(activityId);
		
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		ActivityBooking activityBookingObj = dbActivityBooking.getActivityBookingById(bookingId);
		
		IFDBFacility dbFacility = new DBFacility();
		Facility facilityObj = dbFacility.getFacilityById(facilityId);
		
		DateCheck dateCheck = new DateCheck();
		String endHour = dateCheck.getEndHour(startHour);
		
		
		ActivityLine activityLineObj = new ActivityLine();
		activityLineObj.setActivity(activityTypeObj);
		activityLineObj.setActivityBooking(activityBookingObj);
		activityLineObj.setDate(date);
		activityLineObj.setStartHour(startHour);
		activityLineObj.setEndHour(endHour);
		activityLineObj.setFacility(facilityObj);
		activityLineObj.setStatus(status);
		
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
	
	public void insertInstructorActivityLine(int activityId, int bookingId, String date, String startHour, int facilityId, int instructorId, String status)
	{
		IFDBActivityType dbActivityType = new DBActivityType();
		ActivityType activityTypeObj = dbActivityType.getActivityTypeByID(activityId);
		
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		ActivityBooking activityBookingObj = dbActivityBooking.getActivityBookingById(bookingId);
		
		IFDBFacility dbFacility = new DBFacility();
		Facility facilityObj = dbFacility.getFacilityById(facilityId);
		
		IFDBInstructor dbInstructor = new DBInstructor();
		Instructor instructorObj = dbInstructor.getInstructorById(instructorId);
		
		DateCheck dateCheck = new DateCheck();
		String endHour = dateCheck.getEndHour(startHour);
		
		ActivityLine activityLineObj = new ActivityLine();
		activityLineObj.setActivity(activityTypeObj);
		activityLineObj.setActivityBooking(activityBookingObj);
		activityLineObj.setDate(date);
		activityLineObj.setStartHour(startHour);
		activityLineObj.setEndHour(endHour);
		activityLineObj.setFacility(facilityObj);
		activityLineObj.setStatus(status);
		activityLineObj.setInstructor(instructorObj);
		
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
	
	public void insertTeamActivityLine(int activityId, int bookingId, String date, String startHour, int facilityId, int teamId, String status)
	{
		IFDBActivityType dbActivityType = new DBActivityType();
		ActivityType activityTypeObj = dbActivityType.getActivityTypeByID(activityId);
		
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		ActivityBooking activityBookingObj = dbActivityBooking.getActivityBookingById(bookingId);
		
		IFDBFacility dbFacility = new DBFacility();
		Facility facilityObj = dbFacility.getFacilityById(facilityId);
		
		IFDBTeam dbTeam = new DBTeam();
		Team teamObj = dbTeam.getTeamById(teamId);
		
		DateCheck dateCheck = new DateCheck();
		String endHour = dateCheck.getEndHour(startHour);
		
		ActivityLine activityLineObj = new ActivityLine();
		activityLineObj.setActivity(activityTypeObj);
		activityLineObj.setActivityBooking(activityBookingObj);
		activityLineObj.setDate(date);
		activityLineObj.setStartHour(startHour);
		activityLineObj.setEndHour(endHour);
		activityLineObj.setFacility(facilityObj);
		activityLineObj.setStatus(status);
		activityLineObj.setTeam(teamObj);
		
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
	
	public int updateActivityLine(int activityId, int bookingId, int facilityId, String status)
	{
		IFDBActivityType dbActivityType = new DBActivityType();
		ActivityType activityTypeObj = dbActivityType.getActivityTypeByID(activityId);
		
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		ActivityBooking activityBookingObj = dbActivityBooking.getActivityBookingById(bookingId);
		
		IFDBFacility dbFacility = new DBFacility();
		Facility facilityObj = dbFacility.getFacilityById(facilityId);
		
		ActivityLine activityLineObj = new ActivityLine();
		activityLineObj.setActivity(activityTypeObj);
		activityLineObj.setActivityBooking(activityBookingObj);
		activityLineObj.setFacility(facilityObj);
		activityLineObj.setStatus(status);
		
		IFDBActivityLine dbActivityLine = new DBActivityLine();
		return dbActivityLine.updateActivityLine(activityLineObj);
	}
	
	public int deleteActivityLine(int activityId, int bookingId, String date, String startHour)
	{
		IFDBActivityLine dbActivityLine = new DBActivityLine();
		return dbActivityLine.deleteActivityLine(activityId, bookingId, date, startHour);
	}
	
	public int deleteActivityLinesFromActivityBooking(int bookingId)
	{
		IFDBActivityLine dbActivityLine = new DBActivityLine();
		return dbActivityLine.deleteActivityLinesFromActivityBooking(bookingId);
	}
	
	public LinkedList<ActivityLine> getDateActivityLines(String date)
	{
		IFDBActivityLine dbActivityLine = new DBActivityLine();
		LinkedList<ActivityLine> dateActivityLines = new LinkedList<ActivityLine>();
		dateActivityLines = dbActivityLine.getDateActivityLines(date);
		return dateActivityLines;
	}
	
	public LinkedList<ActivityLine> getInstructorActivityLinesSchedule(String date, int instructorId)
	{
		IFDBActivityLine dbActivityLine = new DBActivityLine();
		LinkedList<ActivityLine> instructorActivityLineSchedule = new LinkedList<ActivityLine>();
		instructorActivityLineSchedule = dbActivityLine.getInstructorActivityLinesSchedule(date, instructorId);
		return instructorActivityLineSchedule;
	}
}