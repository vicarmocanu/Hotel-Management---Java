package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.RoomType;

public class DBRoomType implements IFDBRoomType {
	private Connection con;
	
	public DBRoomType()
	{
		con = DBConnection1.getInstance().getDBcon();
	}
	
	public RoomType findRoomTypeByCategory(String category, boolean retrieveAssiciation) {
		return singleWhere("category='"+category+"'", retrieveAssiciation);
	}
	
	public ArrayList<RoomType> findAllRoomTypes(boolean retrieveAssociation)
	{
		return miscWhere("", retrieveAssociation);
	}
	
	public int insertRoomType(RoomType rt) throws Exception
	{
		String query = "INSERT INTO RoomType(category, typeDescription, price)" +
				" VALUES('" +
				rt.getCategory()+"','"+
				rt.getDescription()+"','"+
				rt.getPrice()+ "')";
		
		int rc = -1;
		System.out.println("insert: " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("RoomType is not inserted");
	        throw new Exception ("RoomType is not inserted correctly!");
		}		
		return rc;
	}
	
	public int deleteRoomType(String category)
	{
		int rc=-1;
		String query = "DELETE FROM RoomType WHERE category='"+category+"'";
		System.out.println("delete: " + query);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("Delete exception in RoomType db.");
		}
		return rc;
	}
	
	public int updateRoomType(RoomType roomType)
	{
		RoomType rt = roomType;
		int rc = -1;
		
		String query = "UPDATE RoomType SET " +
				"typeDescription='" + rt.getDescription()+"', "+
				"price="+rt.getPrice()+
						" WHERE category='" + rt.getCategory()+"'";
		System.out.println("Update query: " + query);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc=stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception e) {
			System.out.println("Update exception in Room: " + e);
		}		
		return rc;
	}
	//end interface methods
	
	//start private methods
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
			roomTypeObj.setDescription(result.getString("typeDescription"));
			roomTypeObj.setPrice(result.getFloat("price"));
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
	
	private ArrayList<RoomType> miscWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		ArrayList<RoomType> roomTypeList = new ArrayList<RoomType>();
		String query = buildQuery(wClause);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results=stmt.executeQuery(query);
			
			while(results.next())
			{
				RoomType roomTypeObj = buildRoomType(results);
				roomTypeList.add(roomTypeObj);
			}
			stmt.close();			
		} catch (Exception e) {
			System.out.println("Query exception - select RoomType : "+e);
			e.printStackTrace();
		}
		return roomTypeList;
	}
}
