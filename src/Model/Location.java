package Model;

public class Location 
{
	private int zipCode;
	private String country;
	private String city;
	
	
public Location(int zipCode,String city, String country)
{
	this.zipCode = zipCode;
	this.city = city;
	this.country = country;
	
}
	

	public Location() {
	// TODO Auto-generated constructor stub
}
	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

}
