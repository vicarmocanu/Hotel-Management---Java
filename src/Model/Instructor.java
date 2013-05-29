package Model;
import Model.Employee;

public class Instructor extends Employee  {
	
	private ActivityType activityType;
	private double price;
	private String status; 
	
	public Instructor(int id, String name, String address, int zipcode,	String country, String phoneNo, 
			String email, String personType, String password, ActivityType activityType, double price, String status, double salary)
	{
		super(id, name, address, zipcode, country, phoneNo, email, personType, password, salary);
		this.activityType = activityType;
		this.price = price;
		this.status = status;
	}
	
	public Instructor()
	{}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ActivityType getActivityType() {
		return activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
