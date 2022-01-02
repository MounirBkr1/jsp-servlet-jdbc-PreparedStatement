<%@page import="ma.course.pojo.Employee"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border ="1" width="500" align="center">
<tr bgcolor="00FF7F">
<th>Id</th>
<th>Prénom</th>
<th>Nom</th>
<th>Département</th>
</tr>
<jsp:useBean id="employees" beanName="employee" scope="request" 
             type="java.util.List<ma.course.pojo.Employee>"/>
<%
for(Employee employee : employees){%>
	<tr>	
		<td><%=employee.getId() %></td>
		<td><%=employee.getFirstName() %></td>
		<td><%=employee.getLastName() %></td>
		<td><%=employee.getDepartment() %></td>
	</tr>
<%
}
%>
</table>
<br/><br/>
<a href="<%=getServletContext().getContextPath()%>">Accueil</a>
</body>
</html>