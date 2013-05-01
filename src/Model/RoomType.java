package Model;

public class RoomType {
	private String category;
	private String description;
	private double price;
	
	//Constructor
	public RoomType()
	{}
	
	public RoomType(String category)
	{
		this.category = category;
	}
	
	public RoomType(String category, String description, double price) {
		this.category = category;
		this.description = description;
		this.price = price;
	}

	//Getters and setters
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}		
}
