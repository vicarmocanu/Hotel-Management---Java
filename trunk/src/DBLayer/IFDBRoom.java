package DBLayer;

import java.util.LinkedList;

import Model.Room;

public interface IFDBRoom {
	
	//get a specific room by it's number
	public Room findRoom(int number, boolean retrieveAssociation);
	
	public LinkedList<Room> findRoomsFromType(String type, boolean retrieveAssociation);
	
/*	//get all available rooms by type
	public ArrayList<Room> findAvailableRooms(int arrival, int departure, String type, boolean retrieveAssociation);
*/	
	//get all different rooms
	public LinkedList<Room> findDifferentRooms(int roomNo, boolean retrieveAssociation);
	
	//get all rooms
	public LinkedList<Room> findAllRooms(boolean retrieveAssociation); 
	
	
	//insert new room
	public int insertRoom(Room room) throws Exception;
	//update a room
	public int updateRoom(Room room);
	//delete room
	public int deleteRoom(int number);
}
