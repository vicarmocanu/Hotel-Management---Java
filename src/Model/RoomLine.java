package Model;

public class RoomLine {
	private Room room;
	private Guest guest;
	private RoomBooking booking;
	
	public RoomLine()
	{
		room=new Room();
		guest = new Guest();
		booking = new RoomBooking();
	}
	
	public RoomLine(Room room, Guest guest, RoomBooking booking) {
		this.room = room;
		this.guest = guest;
		this.booking = booking;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public RoomBooking getBooking() {
		return booking;
	}

	public void setBooking(RoomBooking booking) {
		this.booking = booking;
	}
}
