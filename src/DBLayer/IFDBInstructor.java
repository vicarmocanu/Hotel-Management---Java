package DBLayer;

import java.util.LinkedList;
import Model.Instructor;

public interface IFDBInstructor {
	
	public LinkedList<Instructor> getAllInstructors();
	public Instructor getInstructorById(int employeeId);
	public LinkedList<Instructor> getActivityAvailableInstructors(int activityTypeId, String status);
	public int insertInstructor(Instructor instructorObj) throws Exception;
	public int updateInstructor(Instructor instructorObj);
	public int deleteInstructorById(int employeeId);

}
