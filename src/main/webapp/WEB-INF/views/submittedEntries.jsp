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
	<c:forEach items="${weekEntries}" var="entry">
		<h2>${entry.email} : Semaine ${entry.weekNumber} Année ${entry.yearNumber}</h2>
		<h3>${entry.state}</h3>
		<div class="row">
			<table class="table col-lg-8">
				<thead>
					<tr>
						<th>Jour</th>
						<th>Heures</th>
						<th>Dépenses</th>
						<th>Kilomètres</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${daysNameOfWeek}" var="day" varStatus="status">
						<tr>
							<td>${day}</td>
							<td>${entry.hoursEntries[status.index]}</td>
							<td>3 ${entry.employeeExpensesEntries[status.index]}</td>
							<td>${entry.kilometersEntries[status.index]}
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<button type="submit" class="btn btn-primary">Approuver</button>
			<button type="submit" class="btn btn-danger">Refuser</button>
			<br/>
			<br/>
			<br/>
		</div>
	</c:forEach>

	
</div>


</body>
</html>
