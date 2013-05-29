package Controller;
import DBLayer.DBPerson;
import DBLayer.IFDBPerson;
import Model.Person;

public class LoginCtr
{
	public LoginCtr(){}
	
	public boolean checkGuest(int id)
	{
		boolean check = false;
		Person personToCheck = new Person();
		IFDBPerson dbPerson = new DBPerson();
		personToCheck = dbPerson.searchPersonById(id, false);
		
		if(personToCheck !=null)
		{
			String personType = personToCheck.getPersonType();
			if(personType == "Guest" && personToCheck.isGuest() == true)
			{
				check = true;
			}
			else
			{
				check = false;
							
			}
		}
		
		
		return check;
	}
	
	public boolean checkPassword(int id, String password)
	{
		boolean check = false;
		Person personToCheck = new Person();
		IFDBPerson dbPerson = new DBPerson();
		personToCheck = dbPerson.searchPersonById(id, false);
		if(personToCheck !=null)
		{
			String correctPassword = personToCheck.getPassword();
			if(correctPassword.equals("")!=true && password.equals(correctPassword)==true)
			{
				check = true;
			}
			else
			{
				check = false;
			}
		}
		return check;
	}
}
