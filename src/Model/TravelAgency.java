package Model;

public class TravelAgency
{
	private int cvr;
	private String name;
	private int zipcode;
	private String country;
	private String address;
	private String phoneNo;
	private String email;
	
	public TravelAgency()
	{}
	
	public int getCVR()
	{
		return this.cvr;
	}
	public void setCVR(int cvr)
	{
		this.cvr = cvr;
	}
	
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getZipCode()
	{
		return this.zipcode;
	}
	public void setZipCode(int zipCode)
	{
		this.zipcode = zipCode;
	}
	
	public String getCountry()
	{
		return this.country;
	}
	public void setCountry(String country)
	{
		this.country = country;
	}
	
	public String getAddress()
	{
		return this.address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public String getPhoneNo()
	{
		return this.phoneNo;
	}
	public void setPhoneNo(String phoneNo)
	{
		this.phoneNo = phoneNo;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
}
