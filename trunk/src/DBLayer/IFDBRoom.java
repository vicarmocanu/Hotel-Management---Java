package DBLayer;

import java.util.ArrayList;

import Model.Room;

public interface IFDBRoom {
	
	//get a specific room by it's number
	public Room findRoom(int number, boolean retrieveAssociation);
	
	//get all available rooms by type
	public ArrayList<Room> findAvailableRooms(int arrival, int departure, String type, boolean retrieveAssociation);
	
	//get all available rooms
	public ArrayList<Room> findAvailableRooms(int arrival, int departure, boolean retrieveAssociation);
}
