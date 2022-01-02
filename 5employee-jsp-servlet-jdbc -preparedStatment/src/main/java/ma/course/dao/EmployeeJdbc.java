package ma.course.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ma.course.pojo.Employee;

public class EmployeeJdbc {

	
	private static String dbURL = "jdbc:h2:tcp://localhost:9092/~/employeeDB";
	private static String username = "sa";
	private static String password = "";
		
	private static Connection connection=null;
	static {
		try {
			Class.forName("org.h2.Driver");
			//Class.forName("com.mysql.jdbc.Driver");
			
		    connection = DriverManager.getConnection(dbURL, username, password);
		 
		    if (connection != null) {
		        System.out.println("Connected");
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}

	public List<Employee> getAllEmployees() {
		List<Employee> employees=new ArrayList();
		Employee employee=null;
		try {
			PreparedStatement ps=connection.prepareStatement("select * from employee");
			ResultSet result=ps.executeQuery();
			
			while(result.next())
			{
				int id = result.getInt(1);
			    String firstName = result.getString(2);
			    String lastName = result.getString(3);
			    String department = result.getString(4);
			    employee= new Employee(id, firstName, lastName, department);
			    employees.add(employee);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return employees;			
	}
	
	public Employee getEmployee(int id) {
		Employee employee=null;
		try {
			PreparedStatement ps=connection.prepareStatement("select * from employee where id=?");
			ps.setInt(1, id);
			//Cette méthode permet d'exécuter une requête de type interrogation et renvoie un objet de type ResultSet qui contient 
			//les données issues de l'exécution de la requête

			ResultSet result=ps.executeQuery();
			if(result.next())
			{
				String firstName = result.getString(1);
			    String lastName = result.getString(2);
			    String department = result.getString(3);
			    employee= new Employee(id, firstName, lastName, department);
			}else
			{
			    System.out.println("No record found");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return employee;		
	}
	
	public boolean deleteEmployee(int id) {
		try {
			PreparedStatement ps=connection.prepareStatement("delete from employee where id=?");
			ps.setInt(1, id);
			//Cette méthode permet d'exécuter une requête de type mise à jour et renvoie un entier qui contient le nombre d'occurrences 
			//impactées par la mise à jour

			ps.executeUpdate();
			System.out.println("Deleted successfully");
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean createEmployee(int id, String firstName, String lastName, String department) {
		try {
			//String sql = "INSERT INTO employee (id, firstName, lastName, department) VALUES ("+id+", '"+firstName+"', '"+lastName+"', '"+department+"')";
			PreparedStatement ps=connection.prepareStatement("INSERT INTO employee (id, firstName, lastName, department) VALUES (?, ?, ?, ?)");
			ps.setInt(1, id);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, department);
			ps.executeUpdate();
			System.out.println("Created successfully");
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean updateEmployee(int id, String firstName, String lastName, String department) {
		
		try {
			/*
			String sql = "update employee"
					+ " set firstName='"+firstName+"', "
					+ "lastName='"+lastName+"', "
					+ "department='"+department+"' "
					+ "where id="+id;*/
			
			PreparedStatement ps=connection.prepareStatement("update employee set firstName=?, lastName=?, department=?,where id=?");
			ps.setInt(1, id);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, department);
			ps.executeUpdate();

			System.out.println("Updated successfully");
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
