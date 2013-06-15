package DBLayer;

import Model.RoomType;

public interface IFDBRoomType {
	//find room type by it's category
	public RoomType findRoomTypeByCategory(String category, boolean retrieveAssiciation);
}
