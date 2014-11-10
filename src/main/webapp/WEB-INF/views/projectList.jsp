<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="/resources/css/main.css" media="all" />
	<title>M2 - Architecture Logicielle</title>
</head>
<body>
<jsp:include page="_header.jsp" />
<h1>
	Liste des projets
</h1>

<div class="container">
	<div class="row">
		<table class="table col-lg-8">
			<tbody>
				<c:forEach var="project" items="${projects}">
					<c:url var="projectUrl" value="/${project.id}"/>
					<tr>
						<td><strong>Projet: ${project.name}</strong></td>
						<td>
								<a href="${projectUrl}/editProject">Modifier</a>
							</td>
						<td>
							<a href="${projectUrl}/addTask">Créer une tâche</a>
						</td>
					</tr>
					<tr>
						<th>Nom</th>
						<th>Taux</th>
						<th colspan="4"></th>
					</tr>
					<c:forEach var="task" items="${project.getTasks()}">
						<c:url var="taskUrl" value="/${task.id}"/>

						<tr>
							<td>${task.name}</td>		
							<td>${task.rate}</td>
							<td>
								<a href="${taskUrl}/editTask">Modifier</a>
							</td>
						</tr>
					</c:forEach>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="row">
		<a href="<c:url value="addProject" />" class="col-lg-offset=2 btn btn-primary">Ajouter un projet</a>
	</div>
</div>


</body>
</html>
