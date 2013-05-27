package Model;

public class Instructor extends Employee {
	private double price;
	
	public Instructor(int id, String name, String address, int zipcode,	String country, String phoneNo, 
			String email, String personType, String password, double salary, String employeeType, double price) {
		super(id, name, address, zipcode, country, phoneNo, email, personType, password, salary, employeeType);
		this.price = price;
	}
	
	
//Getters and setters
	//Instructor's price
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
//end getters and setters
}
