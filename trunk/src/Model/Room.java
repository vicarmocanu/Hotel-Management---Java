package Model;

public class Room {
	private int number;
	
	private RoomType roomType; 
	
	//Constructors
	public Room()
	{}
	
	public Room(int number)
	{
		this.number = number;
		
		this.roomType = new RoomType();
	}

	//Getters and setters
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
}
