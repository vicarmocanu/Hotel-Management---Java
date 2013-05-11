package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Model.RoomType;

public class DBRoomType implements IFDBRoomType {
	private Connection con;
	
	public DBRoomType()
	{
		con = DBConnection1.getInstance().getDBcon();
	}
	
	public RoomType findRoomTypeByCategory(String category) {
		return singleWhere("category='"+category+"'", false);
	}
	
	private String buildQuery(String wClause)
	{
		String query = "SELECT * FROM RoomType";
		
		if(wClause.length()>0)
		{
			query = query + " WHERE " + wClause;
		}
		
		return query;
	}
	
	private RoomType buildRoomType(ResultSet result)
	{
		RoomType roomTypeObj = new RoomType();
		
		try {
			roomTypeObj.setCategory(result.getString("category"));
			roomTypeObj.setDescription(result.getString("description"));
			roomTypeObj.setPrice(result.getDouble("price"));
		} catch (Exception e) {
			System.out.println("Error in building the room type object");
		}
		
		return roomTypeObj;
	}
	
	private RoomType singleWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		RoomType roomTypeObj = new RoomType();
		String query = buildQuery(wClause);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results=stmt.executeQuery(query);
			
			if(results.next())
			{
				roomTypeObj = buildRoomType(results);
				stmt.close();
			}
			else
			{
				roomTypeObj = null;
			}
			
		} catch (Exception e) {
			System.out.println("Query exception - select RoomType : "+e);
			e.printStackTrace();
		}
		return roomTypeObj;
	}
}
