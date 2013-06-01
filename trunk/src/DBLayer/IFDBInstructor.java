package DBLayer;

import java.util.LinkedList;
import Model.Instructor;

public interface IFDBInstructor {
	
	public LinkedList<Instructor> getAllInstructors(boolean retrieveAssociation);
	public Instructor getInstructorById(int employeeId, boolean retrieveAssociation);
	public LinkedList<Instructor> getActivityAvailableInstructors(int activityTypeId, String status, boolean retrieveAssociation);
	public int insertInstructor(Instructor instructorObj) throws Exception;
	public int updateInstructor(Instructor instructorObj);
	public int deleteInstructorById(int employeeId);

}
