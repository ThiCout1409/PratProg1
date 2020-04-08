<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="ex_2.model.Pais, java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Lista de Paises</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<div id="main" class="container">
		<h3 class="page-header">Lista de Paises</h3>
		<div class="table-responsive">
			<table class="table table-striped table-sm">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nome</th>
						<th>Populacao</th>
						<th>Area</th>
					</tr>
				</thead>
				<%
					ArrayList<Pais> paises = (ArrayList<Pais>) request.getAttribute("paises");
					for (Pais pais : paises) {
				%>

				<tbody>
					<tr>
						<td><%=pais.getId()%><//td>
						<td><%=pais.getNome()%></td>
						<td><%=pais.getPopulacao()%></td>
						<td><%=pais.getArea()%></td>
					</tr>
				</tbody>

				<%
					}
				%>
			</table>
		</div>
		<div id="actions" class="row">
			<div class="col-md-12">
				<a href="index.html" class="btn btn-default">Voltar</a>
			</div>
		</div>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>