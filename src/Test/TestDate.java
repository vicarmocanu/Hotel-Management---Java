package Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Controller.DateCheck;

public class TestDate
{
	final static String DATE_FORMAT = "dd-MM-yyyy";

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		DateCheck datecheck = new DateCheck();
		
		/*
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss SSS");
		
		Date currentDate = Calendar.getInstance().getTime();
		String stringCurrentDate = df.format(currentDate);
		Date parsedCurrentDate = new Date();
		try
		{
			parsedCurrentDate = df.parse(stringCurrentDate);
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String stringMyDate = new String ("16-06-2013 01:00:00 000");
		Date parseMyDate = new Date();
		try
		{
			parseMyDate = df.parse(stringMyDate);
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Original date format: " + currentDate);
		System.out.println("String Formatted date format:" + stringCurrentDate);
		System.out.println("Formatted date format: " + parsedCurrentDate);		
		System.out.println("First check: " + currentDate.compareTo(parsedCurrentDate));
		System.out.println("\n");
		System.out.println("Formatted myDate format: " + parseMyDate);
		
		int check = 1;
		if(parseMyDate.after(parsedCurrentDate))
		{
			check = 0;
		}
		
		System.out.println("Second check: " + check);
		*/
		
		String date = new String("11-06-2013");
		if(datecheck.checkIfDateIsOlder(date) == false)
		{
			System.out.println("ok");
		}
		
		String time = new String("16-06-2013 22:00");
		if(datecheck.checkIfTimeIsOlder(time) == false)
		{
			System.out.println("ok");
		}
	}
	
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
	
	public static String dateConverter(Date date)
	{
		String stringDate = new String();
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		stringDate = df.format(date);
		return stringDate;
	}
}
