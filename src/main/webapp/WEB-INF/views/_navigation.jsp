<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<ul class="nav navbar-nav">
			<sec:authorize access="hasRole('MANAGER')">
				<li><a href="/manager/employeeList">Liste des employés</a></li>
				<li><a href="/manager/projectList">Liste des projets</a></li>
				<li><a href="/manager/submittedEntryList">Saisies soumises</a></li>
			</sec:authorize>
			<sec:authorize access="hasAnyRole('EMPLOYEE, MANAGER')">
				<li><a href="/weekEntriesList">Liste des semaines</a></li>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<li><a href="/j_spring_security_logout"> Bienvenue <sec:authentication
							property="principal.name" /> <%-- This corresponds to employee.getName() --%>- Déconnexion
				</a></li>
			</sec:authorize>
		</ul>
	</div>
</nav>