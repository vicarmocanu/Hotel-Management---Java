package Model;

public class Instructor extends Employee {
	private double price;
	
	public Instructor(int id, String name, String address, int zipcode,
			String city, String country, String phoneNo, String email, String password,
			double salary, double price) {
		super(id, name, address, zipcode, city, country, phoneNo, email, password, salary);
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
