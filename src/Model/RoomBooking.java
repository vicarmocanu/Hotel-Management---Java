package Model;

public class RoomBooking {
	private int id;
	private String arrivalDate;
	private String departureDate;
	private String status;//booked,checked-in,payed,checked-out;
	private int numberOfChildren;

	//Constructor
	public RoomBooking()
	{}
	
	public RoomBooking(int id)
	{
		this.id = id;
	}
	
	public RoomBooking(int id, String arrivalDate, String departureDate,
			String status, int numberOfChildren) {
		this.id = id;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.status = status;
		this.numberOfChildren = numberOfChildren;
	}

	//Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}
//end getters setters
}
