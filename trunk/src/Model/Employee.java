package Model;

public class Employee extends Person {
	private double salary;

	//Constructor
	public Employee(int id, String name, String address, int zipcode,
			String city, String country, String phoneNo, String email, double salary) {
		super(id, name, address, zipcode, city, country, phoneNo, email);		
		this.salary = salary;
	}

//Getters and setters
	//salary
	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
//end getters and setters
}
