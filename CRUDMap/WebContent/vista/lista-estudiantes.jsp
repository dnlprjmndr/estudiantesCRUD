<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap4.min.js"></script>

<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.css"
	rel="stylesheet">
<link href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet">
<link href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css"
	rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<title>Lista Estudiantes</title>
</head>
<body>
	<div class="container jumbotron">
		<h2>Estudiantes</h2>
		<br> <br>
		<!-- Formulario buscar Estudiante -->
		<form action="estudiante" method="get" id="buscarForm" role="form">
			<input type="hidden" id="buscarAction" name="buscarAction" value="buscarPorNombre">
			<div class="form-group col-xs-5">
				<input type="text" name="nombreEstudiante" id="nombreEstudiante" class="form-control"
					placeholder="Escriba el nombe o apellido del Estudiante" required />
			</div>
			<button type="submit" class="btn btn-info">
				<span class="glyphicon glyphicon-search"></span> Buscar
			</button>
		</form>

		<form action="estudiante" method="get">
			<input type="hidden" id="buscarAction" name="buscarAction" value="nuevo">
			<br></br>
			<button type="submit" class="btn btn-primary  btn-md">Nuevo estudiante</button>
		</form>

		<c:if test="${not empty message}">
			<div class="alert alert-success">${message}</div>
		</c:if>
		<form action="estudiante" method="post" id="estudianteForm" role="form">
			<input type="hidden" id="idEstudiante" name="idEstudiante">
			<input type="hidden" id="action" name="action">
			<c:choose>
				<c:when test="${not empty listaEstudiantes}">
					<table id="tableEstudiantes" class="table table-striped table-bordered dt-responsive nowrap">
						<thead>
							<tr>
								<th>Nombre</th>
								<th>Alias</th>
								<th>Apellido</th>
								<th>Fecha de Nacimiento</th>
								<th>Teléfono</th>
								<th>E-mail</th>
								<th>@</th>
							</tr>
						</thead>
						<c:forEach var="estudiante" items="${listaEstudiantes}">
							<c:set var="classSucess" value="" />
							<c:if test="${idEstudiante == estudiante.id}">
								<c:set var="classSucess" value="info" />
							</c:if>
							<tr class="${classSucess}">
								<td>${estudiante.nombre}</td>
								<td>${estudiante.alias}</td>
								<td>${estudiante.apellidos}</td>
								<td>${estudiante.fechaNacimiento}</td>
								<td>${estudiante.telefono}</td>
								<td>${estudiante.email}</td>
								<td><a href="#" id="eliminar"
										onclick="document.getElementById('idEstudiante').value='${estudiante.id}';
	            							 		document.getElementById('action').value='eliminar';
	            							 		document.getElementById('estudianteForm').submit();">
										<span class="glyphicon glyphicon-trash"></span>
									</a>|
									<a href="estudiante?idEstudiante=${estudiante.id}&buscarAction=buscarPorId">
									    <span class="glyphicon glyphicon-edit">
									</a>
								
                                </td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<br>
					<div class="alert alert-info">No se encontraron registros para la búsqueda</div>
				</c:otherwise>
			</c:choose>
		</form>
	</div>
	<script>
		$(document).ready(function() {
			$('#tableEstudiantes').DataTable();

		});
	</script>
</body>
</html>
