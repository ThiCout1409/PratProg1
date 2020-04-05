<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex_1.model.Cliente, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de Clientes</title>
</head>
<body>
<%
	ArrayList<Cliente> clientes = (ArrayList<Cliente>)request.getAttribute("clientes");
	for(Cliente cliente:clientes){
%>
<p>
<label>ID:</label> <%=cliente.getId() %><br>
<label>Nome:</label> <%=cliente.getNome() %><br>
<label>Fone:</label> <%=cliente.getFone() %><br>
<label>Email:</label> <%=cliente.getEmail() %>
</p>
<%} %>
</body>
</html>