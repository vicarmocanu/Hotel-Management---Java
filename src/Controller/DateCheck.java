package Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
		System.out.println("Current day: " + timeStamp);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try
		{
			Date currentDate = sdf.parse(date);
			Date myDate = sdf.parse(timeStamp);
			
			if(myDate.before(currentDate) == true)
			{
				check = false;
			}
			if(myDate.equals(myDate) == true)
			{
				check = true;
			}
			if(myDate.after(currentDate))
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

}
