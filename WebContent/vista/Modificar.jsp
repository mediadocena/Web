<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  
<%@ page import="Dep.Departamento, java.util.*"%>
<html><head><title>LISTADO DE DEPARTAMENTOS</title></head>
<body>
<center> 
<h2>LISTADO DE DEPARTAMENTOS</h2>
<table border='1'>
<tr><th>Departamento</th><th>Nombre</th><th>Localidad</th></tr>
<%
ArrayList listadep=
(ArrayList)request.getAttribute("departamentos");
if(listadep!=null)
  for(int i=0;i<listadep.size();i++){
   Departamento d=(Departamento)listadep.get(i); %>  
   <form action ="/Web/Controlador?accion=eliminar" method="post" >
   <tr><td><%=d.getDeptno()%></td>
   <td><%=d.getDnombre()%></td>
   <td><%=d.getLoc()%></td>
   <%System.out.println(d.getDeptno()); %>
  	<td><a href="Controlador?accion=eliminar&id=<c:out value="<%=d.getDeptno()%>"/>">Eliminar</a></td>
  	<td><a href="Controlador?accion=modificacion&id=<c:out value="<%=d.getDeptno()%>"/>">Modificar</a></td>
   	</form>
   </tr>
   <%}%>
</table><br/><br/>
<a href="index.jsp">Inicio</a>
</center>
</body>
</html>