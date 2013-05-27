package DBLayer;

import java.util.LinkedList;

import Model.Employee;
import Model.Instructor;

public interface IFDBInstructor {
	
	public LinkedList<Instructor> getAllInstructors(boolean retrieveAssociation);
	public Employee getInstructorById(int id, boolean retrieveAssociation);
	public int insertInstructor(Instructor instructorObj) throws Exception;
	public int updateInstructor(Instructor instructorObj);
	public int deleteInstructorById(int id);

}
