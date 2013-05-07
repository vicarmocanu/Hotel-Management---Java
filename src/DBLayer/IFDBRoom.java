package DBLayer;

import java.util.ArrayList;

import Model.Room;

public interface IFDBRoom {
	
	//get a specific room by it's number
	public Room findRoom(int number, boolean retrieveAssociation);
	
	//get all available rooms
	public ArrayList<Room> findAvailableRooms(String arrival, String departure, String type, boolean retrieveAssociation);
}
