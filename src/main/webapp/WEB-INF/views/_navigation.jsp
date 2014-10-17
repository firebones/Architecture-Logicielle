<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div id="logged-info">
	<ul>
		<sec:authorize access="hasRole('MANAGER')">
			<li><a href="/addEmployee">Ajouter un employ�</a></li>
			<li><a href="/deptManager">Assigner des t�ches � un employ�</a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('EMPLOYEE')">
			<li><a href="/vehicleExpenses">D�penses pour les v�hicules</a></li>
			<li><a href="/employeeExpenses">D�penses des employ�s</a></li>
		</sec:authorize>
	</ul>
</div>