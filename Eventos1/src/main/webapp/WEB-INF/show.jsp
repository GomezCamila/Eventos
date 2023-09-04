<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formateo fechas (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- Formulario form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- para errores de renderizado en rutas PUT -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AQUI EL TITULO DE TU PLNATILLA</title>
<!-- BOOTSTRAP  -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- cambiar para que coincida con Tu archivo/estructura de nombres -->
<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
<div class="container row">

		<a href="/events"> Go back</a>
		<h1>
			<c:out value="${evento.nombre }"></c:out>
		</h1>

		<div class="col-6">
			<h4>
				Host:
				<c:out value="${evento.organizador.nombre }"></c:out>
			</h4>

			<p>
				Date:
				<fmt:formatDate value="${evento.fecha }" pattern="MMMM dd, yyyy"
					var="fechaFormateada" />
				<c:out value="${fechaFormateada}"></c:out>
			</p>
			<p>
				Locacion:
				<c:out value="${evento.ubicacion }"></c:out>
			</p>
			<p>
				Asistentes totales:
				<c:out value="${evento.asistentes.size() }"></c:out>
			</p>

			<!-- 			usar forEach para loopear la lista de asistentes -->

		</div>

		<div class="col-6">
			<h3>Message wall</h3>

			
			<form action="/events/${evento.id }/comentario" method="post"> 
			<div class="form-group">
					<label> Agregar comentario</label>
					<textarea name="comment" class="form-control"> </textarea>
					<button> Enviar Comentario</button>
				</div>
			</form>

		</div>


	</div>

</body>
</html>