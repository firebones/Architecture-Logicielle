<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<jsp:include page="_meta.jsp" />
</head>
<body>
	<jsp:include page="_header.jsp" />

	<h1>Liste des semaines</h1>

	<div class="container">
		<div class="row">
			<table class="table col-lg-8">
				<thead>
					<tr>
						<th>Numéro de la semaine</th>
						<th>Statut</th>
						<th>Date de début</th>
						<th>Date de fin</th>
						<th colspan="8"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="entry" items="${weekEntries}">
						<c:url var="emailUrl" value="/${entry.getEmail()}" />
						<c:url var="weekUrl" value="/${entry.getWeekNumber()}" />
						<c:url var="yearUrl" value="/${entry.getYearNumber()}" />
						<tr>
							<td>${entry.getWeekNumber()}</td>
							<td>${entry.getState()}</td>
							<td>${entry.getStartDate()}</td>
							<td>${entry.getEndDate()}</td>
							<td><a
								href="${emailUrl}/${yearUrl}/${weekUrl}/vehicleExpenses">Dépenses
									pour véhicule</a></td>
							<td><a
								href="${emailUrl}/${yearUrl}/${weekUrl}/employeeExpenses">Dépenses
									de l'employé</a></td>
							<td><a href="${emailUrl}/${yearUrl}/${weekUrl}/workingHours">Heures
									réalisées</a></td>
							<td><c:choose>
									<c:when test="${entry.canSubmit()}">
										<a href="${emailUrl}${yearUrl}${weekUrl}/submitWeekEntry"
											class="col-lg-offset=2 btn btn-primary">Soumettre</a>
									</c:when>
									<c:otherwise>
										<button type="submit" class="btn btn-default" disabled>Soumettre</button>
									</c:otherwise>
								</c:choose></td>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row">
			<form:form method="POST" commandName="entry" action="weekEntriesList">
				<table>
					<tr>
						<td>Numéro de semaine :</td>
						<td><form:select path="weekNumber">
								<form:options items="${freeWeeks}" />
							</form:select></td>

						<td><input class="col-lg-offset=2 btn btn-primary"
							type="submit" value="Ajouter une semaine" /></td>
					</tr>
					<tr>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
</html>
