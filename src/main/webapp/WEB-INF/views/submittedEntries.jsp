<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<jsp:include page="_meta.jsp" />
</head>
<body>
<jsp:include page="_header.jsp" />
<h1>
	Saisies soumises
</h1>
	<div class="container">
		<div class="row">
			<table class="table col-lg-8">
				<thead>
					<tr>
						<th>Num�ro de la semaine</th>
						<th>Courriel de l'employ�</th>
						<th>Date de d�but</th>
						<th>Date de fin</th>
						<th colspan="8"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="entry" items="${weekEntries}">
						<c:url var="emailUrl" value="${entry.getEmail()}" />
						<c:url var="weekUrl" value="${entry.getWeekNumber()}" />
						<c:url var="yearUrl" value="${entry.getYearNumber()}" />
						<tr>
							<td>${entry.getWeekNumber()}</td>
							<td>${entry.getEmail()}</td>
							<td>${entry.getStartDate()}</td>
							<td>${entry.getEndDate()}</td>
							<td>
								<a href="/${emailUrl}/${yearUrl}/${weekUrl}/vehicleExpenses">D�penses pour v�hicule</a>
							</td>
							<td>
								<a href="/${emailUrl}/${yearUrl}/${weekUrl}/employeeExpenses">D�penses de l'employ�</a>
							</td>
							<td>
								<a href="/${emailUrl}/${yearUrl}/${weekUrl}/workingHours">Heures r�alis�es</a>
							</td>
							<td>
								
								<a href="/manager/${emailUrl}/${yearUrl}/${weekUrl}/approved" class="col-lg-offset=2 btn btn-primary">Approuver</a>
							</td>
							<td>
								<a href="/manager/${emailUrl}/${yearUrl}/${weekUrl}/denied" class="col-lg-offset=2 btn btn-danger">Refuser</a>
								
							</td>
					</c:forEach>
				</tbody>
			</table>
		</div>


</body>
</html>
