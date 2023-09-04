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

		<h1>
			Welcome,
			<c:out value="${usuario.nombre }"></c:out>
		</h1>
		<div class="text-end">
			<a href="/logout">Logout</a>
		</div>

		<hr>
		<h3>Here are some events in your state:</h3>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Name</th>
					<th>Date</th>
					<th>Location</th>
					<th>Province</th>
					<th>Host</th>
					<th>Action/Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${eventosPronvinciaUser }" var="eventoUser">
					<tr>
						<td> <a href=" /events/${eventoUser.id}"> <c:out value="${eventoUser.nombre }"></c:out></a> </td>

						<td><fmt:formatDate value="${eventoUser.fecha }"
								pattern="MMMM dd, yyyy" var="fechaFormateada" /> <c:out
								value="${fechaFormateada}"></c:out></td>
						<td><c:out value="${eventoUser.ubicacion }"></c:out></td>
						<td><c:out value="${eventoUser.provincia }"></c:out></td>
						<td><c:out value="${eventoUser.organizador.nombre }"></c:out></td>


						<td><c:choose>
								<c:when test="${eventoUser.organizador.id == usuario.id }">
									<a href="/events/${eventoUser.id}/edit"> Edit </a> |
									<form:form action="/events/${eventoUser.id}/delete"
										method="post">
										<input type="hidden" name="_method" value="delete" />
										<button class="btn btn-danger">Delete</button>
									</form:form>

								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${ eventoUser.asistentes.contains(usuario)}">
											<a href="/event/${eventoUser.id}/${usuario.id}/cancelar">
												Cancelar </a>
										</c:when>
										<c:otherwise>
											<a href="/event/${eventoUser.id}/${usuario.id}/unirse">
												Unirse </a>
										</c:otherwise>
									</c:choose>
								</c:otherwise>

							</c:choose></td>

					</tr>

				</c:forEach>

			</tbody>
		</table>


		<hr />

		<h3>Here are some events in other state:</h3>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Name</th>
					<th>Date</th>
					<th>Location</th>
					<th>Province</th>
					<th>Host</th>
					<th>Action/Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${eventosNoPronvinciaUser }" var="otroseventos">
					<tr>
						<td> <a href=" /events/${otroseventos.id}"> <c:out value="${otroseventos.nombre }"></c:out></a> </td>

						<td><fmt:formatDate value="${otroseventos.fecha }"
								pattern="MMMM dd, yyyy" var="fechaFormateada" /> <c:out
								value="${fechaFormateada}"></c:out></td>

						<td><c:out value="${otroseventos.ubicacion }"></c:out></td>
						<td><c:out value="${otroseventos.provincia }"></c:out></td>
						<td><c:out value="${otroseventos.organizador.nombre }"></c:out></td>
						<td><c:choose>
								<c:when test="${otroseventos.organizador.id == usuario.id }">
									<a href="/events/${otroseventos.id}/edit"> Edit </a> |
							<form:form action="/events/${otroseventos.id}/delete"
										method="post">
										<input type="hidden" name="_method" value="delete" />
										<button class="btn btn-danger">Delete</button>
									</form:form>
								</c:when>
							<c:otherwise>
									<c:choose>
										<c:when test="${ otroseventos.asistentes.contains(usuario)}">
											<a href="/event/${otroseventos.id}/${usuario.id}/cancelar">
												Cancelar </a>
										</c:when>
										<c:otherwise>
											<a href="/event/${otroseventos.id}/${usuario.id}/unirse">
												Unirse </a>
										</c:otherwise>
									</c:choose>
								</c:otherwise>

							</c:choose></td>

					</tr>
				</c:forEach>

			</tbody>
		</table>

		<div class="col-6">
			<form:errors class="text-danger" path="evento.*" />

			<form:form method="POST" action="/nuevo/evento"
				modelAttribute="evento">
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
				<input type="submit" value="Create Event!" />
			</form:form>
		</div>




	</div>


</body>
</html>