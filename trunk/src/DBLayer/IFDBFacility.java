package DBLayer;

import java.util.LinkedList;

import Model.Facility;

public interface IFDBFacility
{
	//get facility by id
	public Facility getFacilityById(int id, boolean retrieveAssociation);
	
	//get all facilities
	public LinkedList<Facility> getAllFacilities(boolean retrieveAssociation);
	
	//get the facilities for a particular activity type
	public LinkedList<Facility> getFacilitiesForActivity(int activityId, boolean retrieveAssociation);
	
	//get facility by name
	public Facility getFacilityByName(String name, boolean retrieveAssociation);
	
	//get available facilities for activity
	public LinkedList<Facility> getAvailableFacilitiesForActivity(int activityId, String status, boolean retrieveAssociation);
	
	//insert facility
	public int insertFacility(Facility facility) throws Exception;
	
	//update facility
	public int updateFacility(Facility facility);
	
	//delete facility 
	public int deleteFacility(int id);

}
