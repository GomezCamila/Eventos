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
	<div class="container">
	<h1> <c:out value="${evento.nombre }"> </c:out> </h1>
	<br />
	<h3> Edit event</h3>
		<div class="col-6">
			<form:form method="POST" action="/events/${evento.id}/edit"
				modelAttribute="evento">
				<input type="hidden" name="_method" value="put" />
				<form:input type="hidden" path="organizador" value="${usuario.id}" />
				<div>
					<form:label path="nombre">Event Name:</form:label>
					<form:input type="text" path="nombre" />
					<form:errors class="text-danger" path="nombre" />
				</div>
				<div>
					<form:label path="fecha">Event date:</form:label>
					<form:input type="date" path="fecha" />
					<form:errors class="text-danger" path="fecha" />
				</div>
				<div>
					<form:label path="ubicacion">Location:</form:label>
					<form:input type="text" path="ubicacion" />
					<form:errors class="text-danger" path="ubicacion" />
				</div>
				<div>
					<form:label path="provincia">State:</form:label>
					<form:select path="provincia" type="text" class="form-control">
						<c:forEach items="${provincias}" var="provincia">
							<form:option value="${provincia }"></form:option>
						</c:forEach>
					</form:select>
					<form:errors class="text-danger" path="provincia" />
				</div>
				<input type="submit" value="Edit Event!" />
			</form:form>
		</div>
	</div>


</body>
</html>