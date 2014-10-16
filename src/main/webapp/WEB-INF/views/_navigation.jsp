<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div id="logged-info">
	<ul>
		<li><a href="/">Accueil</a></li>
		<li><a href="/users">Page pour les utilisateurs</a></li>
		<sec:authorize access="hasRole('MANAGER')">
			<li><a href="/addEmployee">Ajouter un employ�</a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('MANAGER')">
			<li><a href="/deptManager">Assigner des t�ches � un employ�</a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('EMPLOYEE')">
			<li><a href="/vehicleExpenses">D�penses pour les v�hicules</a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('EMPLOYEE')">
			<li><a href="/employeeExpenses">D�penses des employ�s</a></li>
		</sec:authorize>
	</ul>
</div>