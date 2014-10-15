<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div id="logged-info">
	<ul>
		<li><a href="/">Accueil</a></li>
		<li><a href="/users">Page pour les utilisateurs</a></li>
		<sec:authorize access="hasRole('MANAGER')">
			<li><a href="/addEmployee">Ajouter un employé</a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('EMPLOYEE')">
			<li><a href="/vehicleExpenses">Dépenses pour les véhicules</a></li>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<li><a href="/j_spring_security_logout">Déconnexion</a></li>
		</sec:authorize>
	</ul>
</div>