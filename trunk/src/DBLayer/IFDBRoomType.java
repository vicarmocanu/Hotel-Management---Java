package DBLayer;

import java.util.ArrayList;

import Model.RoomType;

public interface IFDBRoomType {
	//find room type by it's category
	public RoomType findRoomTypeByCategory(String category, boolean retrieveAssiciation);	
	//find all room types
	public ArrayList<RoomType> findAllRoomTypes(boolean retrieveAssociation);
	
	
	//create new room type
	public int insertRoomType(RoomType rt) throws Exception;
	//update room type
	public int updateRoomType(RoomType rt);
	//delete room type
	public int deleteRoomType(String category);
}
