package Model;

public class Guest extends Person{
	private String type;

	public Guest()
	{
		super();
	}
	
	public Guest(int id)
	{
		super(id);
	}
	
	public Guest(int id, String name, String address, int zipcode, String city,
			String country, String phoneNo, String email, String type) {
		super(id, name, address, zipcode, city, country, phoneNo, email);
		this.type = type;
	}

//Getters and setters
	//type
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}	
//end getters and setters
}
