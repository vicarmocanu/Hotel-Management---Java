package DBLayer;

import java.util.LinkedList;

import Model.Location;

public interface IFDBLocation {
	
	public Location searchLocationByCity(String city, boolean retriveAssociation);
	public Location searchLocationByZipCode(int zipCode,boolean retriveAssociation);
	public LinkedList<Location> getAllLocation(boolean retriveAssociation);
	public int deleteLocation(int loczipCode);
	public int updateLocation(Location loc);
	public int insertLocation(Location loc) throws Exception;

}
