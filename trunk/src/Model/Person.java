package Model;

public class Person {
	private int id;
	private String name;
	private int zipcode;
	private String country;
	private String address;
	private String phoneNo;
	private String email;
	private String personType;
	private String password;
		
	//Constructor 
	public Person()
	{}
	
	public Person(int id)
	{
		this.id = id;
	}
	
	public Person(int id, String name, String address, int zipcode,	String country, String phoneNo, 
			String email, String personType, String password) {
		this.id = id;
		this.name = name;
		this.zipcode = zipcode;
		this.country = country;
		this.address = address;
		this.phoneNo = phoneNo;
		this.email = email;
		this.personType = personType;
		this.password=password;
	}

	//Getters and setters
	//ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	//Name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//Address
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	//zipcode
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
	//Country
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	//PhoneNo
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	//email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	//password
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPersonType()
	{
		return this.personType;
	}
	public void setPersonType(String personType)
	{
		this.personType = personType;
	}
	
	public boolean isGuest()
	{
		boolean check = false;
		if(this instanceof Guest)
		{
			check = true;
		}
		else
		{
			check = false;
			
		}
		return check;
	}
//end getters and setters
}
