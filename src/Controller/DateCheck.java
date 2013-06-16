package Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class DateCheck 
{
	public DateCheck(){}
	
	final static String DATE_FORMAT = "dd-MM-yyyy";
	
	public static boolean isDateValid(String date)
	{
		try
		{
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			df.setLenient(false);
			df.parse(date);
			return true;
		}
		catch (ParseException e)
		{
			return false;
		}
	}
	
	public boolean checkIfDateIsOlder(String date)
	{
		boolean check = false;
		
		String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try
		{
			Date currentDate = sdf.parse(timeStamp);
			System.out.println("Current day: " + currentDate);
			
			Date myDate = sdf.parse(date);
			System.out.println("My day: " + myDate);
			
			if(myDate.compareTo(currentDate) <0)
			{
				check = false;
			}
			if(myDate.compareTo(currentDate) == 0)
			{
				check = true;
			}
			if(myDate.compareTo(currentDate) >0)
			{
				check = true;
			}
		}
		catch (ParseException e)
		{
			System.out.println("excepyion: " + e);
		}
		return check;
	}
	
	public boolean checkIfTimeIsOlder(String time)
	{
		boolean check = false;
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss SSS");
		Date currentDate = Calendar.getInstance().getTime();
		String stringCurrentDate = df.format(currentDate);
		Date parsedCurrentDate = new Date();
		try
		{
			parsedCurrentDate = df.parse(stringCurrentDate);
			System.out.println("Current time: " + parsedCurrentDate);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		String myTime = time + ":00 000";
		Date parseMyTime = new Date();
		try
		{
			parseMyTime = df.parse(myTime);
			System.out.println("My time: " + parseMyTime);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		if(parseMyTime.compareTo(parsedCurrentDate)<0)
		{
			check = false;
		}
		if(parseMyTime.compareTo(parsedCurrentDate) == 0)
		{
			check = true;
		}
		if(parseMyTime.compareTo(parsedCurrentDate)>0)
		{
			check = true;
		}
		
		return check;
	}
	
	public LinkedList<String> getDays()
	{
		LinkedList<String> dayList = new LinkedList<String>();
		
		dayList.add("01");
		dayList.add("02");
		dayList.add("03");
		dayList.add("04");
		dayList.add("05");
		dayList.add("06");
		dayList.add("07");
		dayList.add("08");
		dayList.add("09");
		dayList.add("10");
		dayList.add("11");
		dayList.add("12");
		dayList.add("13");
		dayList.add("14");
		dayList.add("15");
		dayList.add("16");
		dayList.add("17");
		dayList.add("18");
		dayList.add("19");
		dayList.add("20");
		dayList.add("21");
		dayList.add("22");
		dayList.add("23");
		dayList.add("24");
		dayList.add("25");
		dayList.add("26");
		dayList.add("27");
		dayList.add("28");
		dayList.add("29");
		dayList.add("30");
		dayList.add("31");
		
		return dayList;
	}
	
	public LinkedList<String> getMonths()
	{
		LinkedList<String> monthList = new LinkedList<String>();
		
		monthList.add("01");
		monthList.add("02");
		monthList.add("03");
		monthList.add("04");
		monthList.add("05");
		monthList.add("06");
		monthList.add("07");
		monthList.add("08");
		monthList.add("09");
		monthList.add("10");
		monthList.add("11");
		monthList.add("12");
		
		return monthList;
	}
	
	public LinkedList<String> getYears()
	{
		LinkedList<String> yearList = new LinkedList<String>();
		
		yearList.add("2013");
		yearList.add("2014");
		yearList.add("2015");
		yearList.add("2016");
		
		return yearList;
	}
	
	public LinkedList<String> getStartHours()
	{
		LinkedList<String> startHourList = new LinkedList<String>();
		
		startHourList.add("08:00");
		startHourList.add("09:00");
		startHourList.add("10:00");
		startHourList.add("11:00");
		startHourList.add("12:00");
		startHourList.add("13:00");
		startHourList.add("14:00");
		startHourList.add("15:00");
		startHourList.add("16:00");
		startHourList.add("17:00");
		startHourList.add("18:00");
		startHourList.add("19:00");
		
		return startHourList;
	}
	
	public String getEndHour(String startHour)
	{
		String returnedEndHour = new String();
		
		String h1 = startHour.substring(0,1);
		int intH1 = Integer.parseInt(h1);
		String h2 = startHour.substring(1,2);
		int intH2 = Integer.parseInt(h2);
		
		int intStartHour = intH1 + intH2*10;
		int intEndHour = intStartHour + 1;
		String endHour = String.valueOf(intEndHour);
		
		if(endHour.length()==1)
		{
			returnedEndHour = "0" + endHour + ":00";
		}
		else
		{
			returnedEndHour = endHour + ":00";
		}
		
		return returnedEndHour;
	}
}