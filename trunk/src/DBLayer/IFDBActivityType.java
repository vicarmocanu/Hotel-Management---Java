package DBLayer;
import java.util.LinkedList;
import Model.ActivityType;

public interface IFDBActivityType 
{
	//get all activity types
	public LinkedList<ActivityType> getAllActivityTypes();
	
	//get activity type by id
	public ActivityType getActivityTypeByID(int id);
	
	//get activity type by name
	public ActivityType getActivityTypeByName(String name);
	
	//insert activity type
	public int insertActivityType(ActivityType activityTypeObj) throws Exception;
	
	//update activity type
	public int updateActivityType(ActivityType activityTypeObj);
	
	//delete activity type by id
	public int deleteActivityTypeByID(int id);
	
	//delete activity type by name
	public int deleteActivityTypeByName(String name);
	
	//get the activity type instances by name and different id
	public int getActivityTypeInstances(int id, String name);
}
