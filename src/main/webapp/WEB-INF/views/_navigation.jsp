<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">  
		<ul class="nav navbar-nav">
			<sec:authorize access="hasRole('MANAGER')">
				<li><a href="/employeeList">Liste des employ�s</a></li>
				<li><a href="/projectList">Liste des projets</a></li>
			</sec:authorize>
			<sec:authorize access="hasRole('EMPLOYEE')">
				<li><a href="/vehicleExpenses">D�penses pour les v�hicules</a></li>
				<li><a href="/employeeExpenses">D�penses des employ�s</a></li>
				<li><a href="/workingHours">Heures r�alis�es</a></li>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<li>
					<a href="/j_spring_security_logout">
						Bienvenue,
						<sec:authentication property="principal.name" /> <%-- This corresponds to employee.getName() --%>
						D�connexion
					</a>
				</li>
			</sec:authorize>
		</ul>
	</div>
</nav>