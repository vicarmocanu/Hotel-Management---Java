package DBLayer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import Model.Employee;
import Model.Person;

public class DBEmployee implements IFDBEmployee
{
	private Connection con;
	
	//constructor - connection
	public DBEmployee()
	{
		con=DBConnection1.getInstance().getDBcon();
	}
	
	private String buildQuery(String wClause)
	{
		String query="SELECT * FROM Employee";
		
		if (wClause.length()>0)
		{
			query=query+" WHERE "+ wClause;
		}
		return query;
	}
	
	private Employee buildEmployee(ResultSet results)
	{
		Employee employeeObj= new Employee();
		IFDBPerson dbPerson = new DBPerson();
		Person personObj = new Person();
		
		try
		{
			int personId = results.getInt("personID");
			employeeObj.setId(results.getInt("personId"));
			employeeObj.setSalary(results.getDouble("salary"));
			
			personObj = dbPerson.searchPersonById(personId, true);
			employeeObj.setName(personObj.getName());
			employeeObj.setZipcode(personObj.getZipcode());
			employeeObj.setCountry(personObj.getCountry());
			employeeObj.setAddress(personObj.getAddress());
			employeeObj.setPhoneNo(personObj.getPhoneNo());
			employeeObj.setEmail(personObj.getEmail());
			employeeObj.setPersonType(personObj.getPersonType());
			employeeObj.setPassword(personObj.getPassword());
		}
		catch(Exception e)
		{
			System.out.println("Exception in building the employee object: " +e);
		}
		
		return employeeObj;
	}
	
	//singleWhere is used when we select only one object
	private Employee singleWhere (String wClause)
	{
		ResultSet results;
		
		Employee employeeObj=new Employee();		
		String query = buildQuery(wClause);
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			if( results.next() )
			{
				employeeObj = buildEmployee(results);
				stmt.close();
			}
			
			else
			{
				employeeObj = null;
			}
		}
		catch(Exception e)
		{
			System.out.println("Single selection query exception: " + e);
		}
		
		return employeeObj;
	}
	
	//miscWhere is used when we want to select multiple objects
	private LinkedList<Employee> miscWhere (String wClause)
	{
		ResultSet results;
		
		LinkedList<Employee> employeeList=new LinkedList<Employee>();
		String query =  buildQuery(wClause);
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			while( results.next() )
			{
				Employee employeeObj=new Employee();
				employeeObj =buildEmployee(results);
				employeeList.add(employeeObj);
			}
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println("Multiple selection query exception: " + e);
			e.printStackTrace();
		}
		
		return employeeList;
	}

	@Override
	public LinkedList<Employee> getAllEmployees()
	{
		return miscWhere("");
	}
	
	@Override
	public Employee getEmployeeById(int personId)
	{
		String wClause = "  personId= '" + personId + "'";
		return singleWhere(wClause);
	}
	
	@Override
	public int insertEmployee(Employee employeeObj) throws Exception
	{
		int result = -1;
		
		String query = "INSERT INTO Employee(personId, salary) VALUES ('" +
		employeeObj.getId() + "','" + employeeObj.getSalary() +   "')";
		System.out.println("Insertion query: " + query);
		
	    try
	    {
	    	Statement stmt = con.createStatement();
	    	stmt.setQueryTimeout(5);
	    	result = stmt.executeUpdate(query);
	    	stmt.close();
	    }
	    catch(SQLException e)
	    {
	    	System.out.println("Insertion exception: " + e);
	    }
	    
	    return(result);
	}
	
	@Override
	public int updateEmployee(Employee employeeObj)
	{
		int result=-1;
		
		Employee employeeNewObj=employeeObj;
		
		String query="UPDATE Employee SET " +
		"salary= '" + employeeNewObj.getSalary() + "' " +		
		"WHERE personId= '" + employeeNewObj.getId() + "'";
		System.out.println("Update query: " + query);
		
		try
		{
			Statement stmt = con.createStatement();		
	 		stmt.setQueryTimeout(5);
	 	 	result = stmt.executeUpdate(query);
	 	 	stmt.close();
		}
		catch(SQLException e)
		{
			System.out.println("Update exception: " + e);
		}
		
		return(result);
	}
	
	@Override
	public int deleteEmployeeById(int id)
	{
		int result=-1;
		  
	  	String query="DELETE FROM Employee WHERE personId= '" + id + "'";
	  	System.out.println("Delete query: " + query);
	  	
	  	try
	  	{
	  		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 	  	result = stmt.executeUpdate(query);
	 	  	stmt.close();	  		
	  	}
	  	catch(SQLException e)
	  	{
	  		System.out.println("Delete exception: " + e);
	  	}
	  	
	  	return(result);
	}
}
