package ma.course.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.course.dao.EmployeeJdbc;
import ma.course.pojo.Employee;

public class EmployeeServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	
	EmployeeJdbc jdbc=null;
	
	@Override
	public void init() {
		jdbc=new EmployeeJdbc();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		List<Employee> employees=jdbc.getAllEmployees();
		request.setAttribute("employees", employees);
		RequestDispatcher rd = request.getRequestDispatcher("/pages/employees.jsp");
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String department=request.getParameter("department");
		
		jdbc.createEmployee(id, firstName, lastName, department);
		
		doGet(request, response);
	}

}
