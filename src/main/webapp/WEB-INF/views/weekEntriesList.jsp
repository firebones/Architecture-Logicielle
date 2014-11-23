<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<jsp:include page="_meta.jsp" />
</head>
<body>
	<jsp:include page="_header.jsp" />
	
	<h1>
		Liste des semaines
	</h1>
	
	<div class="container">
		<div class="row">
			<table class="table col-lg-8">
				<thead>
					<tr>
						<th>Numéro Semaine</th>
						<th>Statut</th>
						<th>Date Début</th>
						<th>Date Fin</th>
						<th colspan="8"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="entry" items="${weekEntries}">
						<c:url var="weekUrl" value="/${entry.getWeekNumber()}" />
						<c:url var="yearUrl" value="/${entry.getYearNumber()}" />
						<tr>
							<td>${entry.getWeekNumber()}</td>
							<td>${entry.getState()}</td>
							<td>${entry.getStartDate()}</td>
							<td>${entry.getEndDate()}</td>
							<td>
								<a href="${yearUrl}/${weekUrl}/vehicleExpenses">Dépenses véhicule</a>
							</td>
							<td>
								<a href="${yearUrl}/${weekUrl}/employeeExpenses">Dépenses employé</a>
							</td>
							<td>
								<a href="${yearUrl}/${weekUrl}/workingHours">Heures réalisées</a>
							</td>
							<td>
								<c:choose>
									<c:when test="${entry.canSubmit()}"><a href="${yearUrl}/${weekUrl}/submitWeekEntry" class="col-lg-offset=2 btn btn-primary">Soumettre</a></c:when>
									<c:otherwise><button type="submit" class="btn btn-default" disabled>Soumettre</button></c:otherwise>
								</c:choose>
							</td>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row">
			<a href="<c:url value="addWeekEntry" />" class="col-lg-offset=2 btn btn-primary">Ajouter une semaine</a>
		</div>
	</div>
</body>
</html>
