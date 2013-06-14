package DBLayer;

import java.util.LinkedList;

import Model.Location;

public interface IFDBLocation
{
	public Location getLocation(int zipcode, String country);
	public LinkedList<Location> getAllLocations();
	public int updateLocation(Location loc);
	public int deleteLocation(int zipcode, String country);
	public int insertLocation(Location loc) throws Exception;
	public Location getCompleteLocation(int zipcode, String country, String city);
}
