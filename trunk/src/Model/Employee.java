package Model;

public class Employee extends Person
{
	private double salary;

	public Employee(int id, String name, String address, int zipcode,	String country, String phoneNo, 
			String email, String personType, String password, double salary)
	{
		super(id, name, address, zipcode, country, phoneNo, email, personType, password);
		this.salary = salary;
	}
	
	public Employee()
	{}

	public double getSalary()
	{
		return salary;
	}
	public void setSalary(double salary)
	{
		this.salary = salary;
	}
}