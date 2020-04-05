<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex_1.model.Cliente" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cliente</title>
</head>
<body>
<%
	Cliente cliente = (Cliente)request.getAttribute("cliente");  
%>
<p>
ID: <%=cliente.getId() %><br>
Nome: <%=cliente.getNome() %><br>
Fone: <%=cliente.getFone() %><br>
Email: <%=cliente.getEmail() %>
</p>
</body>
</html>