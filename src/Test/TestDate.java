package Test;

import Controller.DateCheck;

public class TestDate
{

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
		
		String time = new String("16-06-2013 02:20");
		if(datecheck.checkIfTimeIsOlder(time) == false)
		{
			System.out.println("ok");
		}
	}
}
