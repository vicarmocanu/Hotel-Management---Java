package DBLayer;
import java.util.LinkedList;
import Model.ActivityType;

public interface IFDBActivityType 
{
	//get all activity types
	public LinkedList<ActivityType> getAllActivityTypes(boolean retrieveAssociation);
	
	//get activity type by id
	public ActivityType getActivityTypeByID(int id, boolean retrieveAssociation);
	
	//get activity type by name
	public ActivityType getActivityTypeByName(String name, boolean retrieveAssociation);
	
	//insert activity type
	public int insertActivityType(ActivityType activityTypeObj) throws Exception;
	
	//update activity type
	public int updateActivityType(ActivityType activityTypeObj);
	
	//delete activity type by id
	public int deleteActivityTypeByID(int id);
	
	//delete activity type by name
	public int deleteActivityTypeByName(String name);
}
