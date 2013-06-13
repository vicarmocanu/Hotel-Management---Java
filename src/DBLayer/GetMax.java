package DBLayer;

import java.sql.ResultSet;
import java.sql.Statement;

public class GetMax
{
	public GetMax()
	{}
	
	//getMax is for the primary key in the table
	public static int getMaxId(String query)
	{
		ResultSet results;
		int id = -1;
		
		try
		{
			Statement stmt = DBConnection1.getInstance().getDBcon().createStatement();
			results = stmt.executeQuery(query);
			if( results.next() )
			{
				id = results.getInt(1);
			}
		}
		catch(Exception e)
		{
			System.out.println("Query exception: Error in reading maxid" + e);
		}
		return id;
    }
}
