package Model;

public class Employee extends Person {
	private double salary;
	private String employeeType;

	//Constructor
	public Employee(int id, String name, String address, int zipcode,	String country, String phoneNo, 
			String email, String personType, String password, double salary, String employeeType)
	{
		super(id, name, address, zipcode, country, phoneNo, email, personType, password);
		this.salary = salary;
		this.employeeType = employeeType;
	}

//Getters and setters
	//salary
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public void setEmployeeType(String employeeType)
	{
		this.employeeType = employeeType;
	}
	public String getEmployeeType()
	{
		return this.employeeType;
	}
//end getters and setters
}
