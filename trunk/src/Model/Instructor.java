package Model;

public class Instructor extends Employee
{
	private double price;
	private ActivityType activityType;
	
	public Instructor(int id, String name, String address, int zipcode,	String country, String phoneNo, 
			String email, String personType, String password, double salary, String employeeType, double price, ActivityType activityType) {
		super(id, name, address, zipcode, country, phoneNo, email, personType, password, salary, employeeType);
		this.price = price;
		this.activityType = activityType;
	}
	
	public Instructor(){}
	
	
//Getters and setters
	//Instructor's price
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setActivityType(ActivityType activityType)
	{
		this.activityType = activityType;
	}
	public ActivityType getActivityType()
	{
		return this.activityType;
	}
//end getters and setters
}
