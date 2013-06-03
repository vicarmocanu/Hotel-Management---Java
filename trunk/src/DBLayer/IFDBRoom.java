package DBLayer;

import java.util.ArrayList;

import Model.Room;

public interface IFDBRoom {
	
	//get a specific room by it's number
	public Room findRoom(int number, boolean retrieveAssociation);
	
/*	//get all available rooms by type
	public ArrayList<Room> findAvailableRooms(int arrival, int departure, String type, boolean retrieveAssociation);
*/	
	//get all different rooms
	public ArrayList<Room> findDifferentRooms(int roomNo, boolean retrieveAssociation);
	
	//get all rooms
	public ArrayList<Room> findAllRooms(boolean retrieveAssociation); 
}
