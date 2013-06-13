package DBLayer;

import java.util.LinkedList;

import Model.Facility;

public interface IFDBFacility
{
	//get facility by id
	public Facility getFacilityById(int id);
	
	//get all facilities
	public LinkedList<Facility> getAllFacilities();
	
	//get the facilities for a particular activity type
	public LinkedList<Facility> getFacilitiesForActivity(int activityId);
	
	//get facility by name
	public Facility getFacilityByName(String name);
	
	//get available facilities for activity
	public LinkedList<Facility> getAvailableFacilitiesForActivity(int activityId, String status);
	
	//insert facility
	public int insertFacility(Facility facility) throws Exception;
	
	//update facility
	public int updateFacility(Facility facility);
	
	//delete facility 
	public int deleteFacility(int id);

}
