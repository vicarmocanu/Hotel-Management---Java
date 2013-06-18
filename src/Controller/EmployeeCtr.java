package Controller;

import java.util.ArrayList;
import java.util.LinkedList;

import DBLayer.DBConnection1;
import DBLayer.DBEmployee;
import DBLayer.DBPerson;
import DBLayer.DBRoom;
import DBLayer.DBRoomType;
import DBLayer.IFDBEmployee;
import DBLayer.IFDBPerson;
import DBLayer.IFDBRoom;
import DBLayer.IFDBRoomType;
import Model.Employee;
import Model.Person;
import Model.Room;
import Model.RoomType;

public class EmployeeCtr
{
	public EmployeeCtr(){}
	
	public LinkedList<Employee> getAllEmployees()
	{
		IFDBEmployee dbEmployee = new DBEmployee();
		LinkedList<Employee> employeeList = new LinkedList<Employee>();
		employeeList = dbEmployee.getAllEmployees();
		return employeeList;
	}
	
	public Employee getEmployeeById(int id)
	{
		IFDBEmployee dbEmployee = new DBEmployee();
		Employee employeeObj = new Employee();
		employeeObj = dbEmployee.getEmployeeById(id);
		return employeeObj;
	}
	
	public void insertEmployee(String employeeName, double salary)
	{
		IFDBPerson dbPerson = new DBPerson();
		Person personReferenceObj = new Person();
		personReferenceObj = dbPerson.searchPersonByName(employeeName, true);
		int referenceId = personReferenceObj.getId();
		
		Employee employeeObj = new Employee();
		employeeObj.setId(referenceId);
		employeeObj.setSalary(salary);
		
		try
		{
			DBConnection1.startTransaction();
			DBEmployee dbEmployee = new DBEmployee();
			dbEmployee.insertEmployee(employeeObj);
			DBConnection1.commitTransaction();
		}
		catch(Exception e)
		{
			DBConnection1.rollbackTransaction();
		}
	}
	
	public int updateEmployee(int id, double salary)
	{
		IFDBEmployee dbEmployee = new DBEmployee();
		Employee employeeObj = new Employee();
		employeeObj.setId(id);
		employeeObj.setSalary(salary);
		return dbEmployee.updateEmployee(employeeObj);
	}
	
	public int deleteEmployeeById(int id)
	{
		IFDBEmployee dbEmployee = new DBEmployee();
		return dbEmployee.deleteEmployeeById(id);
	}
	
	
	public Room getRoomByNumber(int number)
	{
		IFDBRoom dbroom = new DBRoom();
		Room room = dbroom.findRoom(number, true);
		
		return room;
	}
	
	public LinkedList<Room> getRoomsByType(String type)
	{
		IFDBRoom dbroom = new DBRoom();
		LinkedList<Room> rooms = dbroom.findRoomsFromType(type, true);
		return rooms;
	}
	
	public void createRoom(int number, String type)
	{
		IFDBRoomType dbroomType = new DBRoomType();
		RoomType rt = dbroomType.findRoomTypeByCategory(type, false);
		
		IFDBRoom dbroom = new DBRoom();
		Room room = new Room();
		
		room.setNumber(number);
		room.setRoomType(rt);
		
		try
		{
			DBConnection1.startTransaction();
			dbroom.insertRoom(room);
			DBConnection1.commitTransaction();
		}
		catch(Exception e)
		{
			DBConnection1.rollbackTransaction();
		}
	}
	
	public int updateRoom(int number, String type)
	{
		IFDBRoomType dbroomType = new DBRoomType();
		RoomType rt = dbroomType.findRoomTypeByCategory(type, false);
		
		IFDBRoom dbroom = new DBRoom();
		Room room = new Room();
		
		room.setNumber(number);
		room.setRoomType(rt);
		
		return dbroom.updateRoom(room);
	}
	
	public boolean checkRoomType(String type)
	{
		IFDBRoomType dbroomtype = new DBRoomType();
		ArrayList<RoomType> roomTypes = dbroomtype.findAllRoomTypes(false);
		boolean exists = false;
		
		int i=0;
		while(!exists || i<roomTypes.size())
		{
			if(type == roomTypes.get(i).getCategory())
			{
				exists=true;
			}
			i++;
		}
		return exists;
	}
	
	public RoomType getRoomType(String category)
	{
		IFDBRoomType dbrt = new DBRoomType();
		RoomType rt = dbrt.findRoomTypeByCategory(category, false);
		
		return rt;
	}
	
	public void createRoomType(String category, String description, float price)
	{
		IFDBRoomType dbrt = new DBRoomType();
		RoomType rt = new RoomType();
		
		rt.setCategory(category);
		rt.setDescription(description);
		rt.setPrice(price);
		
		try
		{
			DBConnection1.startTransaction();
			dbrt.insertRoomType(rt);
			DBConnection1.commitTransaction();
		}
		catch(Exception e)
		{
			DBConnection1.rollbackTransaction();
		}
	}
	
	public int updateRoomType(String category, String description, float price)
	{
		IFDBRoomType dbrt = new DBRoomType();
		RoomType rt = new RoomType();
		
		rt.setCategory(category);
		rt.setDescription(description);
		rt.setPrice(price);
		
		return dbrt.updateRoomType(rt);
	}
}
