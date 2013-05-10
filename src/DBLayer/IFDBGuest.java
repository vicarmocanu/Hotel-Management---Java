package DBLayer;

import Model.Guest;

public interface IFDBGuest {
	//find a guest by id
	public Guest findGuestById(int id, boolean retrieveAssociation);
}
