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
						<c:url var="entryUrl" value="/${entry.weekNumber}" />
						<tr>
							<td>${entry.weekNumber}</td>
							<td>${entry.state}</td>
							<td>${entry.startDate}</td>
							<td>${entry.endDate}</td>
							<td>
								<a href="${entryUrl}/vehicleExpenseEntries">Dépenses véhicule</a>
							</td>
							<td>
								<a href="${entryUrl}/employeeExpenseEntries">Dépenses employé</a>
							</td>
							<td>
								<a href="${entryUrl}/workingHourEntries">Heures réalisées</a>
							</td>
							<td>
								<a href="${entryUrl}/submittedEntries" class="col-lg-offset=2 btn btn-primary">Envoyer</a>
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
