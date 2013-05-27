package DBLayer;

import java.util.LinkedList;

import Model.TravelAgency;

public interface IFDBTravelAgency
{
	public LinkedList<TravelAgency> getAllTravelAgencies(boolean retrieveAssociation);
	
	public TravelAgency getTravelAgencyByCVR (int cvr, boolean retrieveAssociation);
	
	public TravelAgency getTravelAgencyByName(String name, boolean retrieveAssociation);
	
	public int insertTravelAgency(TravelAgency travelAgency) throws Exception;
	
	public int updateTravelAgency(TravelAgency travelAgency);
	
	public int deleteTravelAgencyByCvr(int cvr);
	
	public int deleteTravelAgencyByName(String name);

}
