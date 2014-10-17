<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div id="logged-info">
	<ul>
		<sec:authorize access="hasRole('MANAGER')">
			<li><a href="/employeeList">Liste des employés</a></li>
			<li><a href="/deptManager">Assigner des tâches à un employé</a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('EMPLOYEE')">
			<li><a href="/vehicleExpenses">Dépenses pour les véhicules</a></li>
			<li><a href="/employeeExpenses">Dépenses des employés</a></li>
		</sec:authorize>
	</ul>
</div>