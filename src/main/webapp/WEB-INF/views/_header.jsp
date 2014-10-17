<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div id="login-info">
	<sec:authorize access="isAnonymous()">
		<p>Vous n'êtes pas connecté. <a href="/users">Connexion</a></p>
	</sec:authorize>

	<sec:authorize access="isAuthenticated()">
		<p>
			Bienvenue,
			<sec:authentication property="principal.name" /> <%-- This corresponds to employee.getName() --%>
			<a href="/j_spring_security_logout">Déconnexion</a>
		</p>
	</sec:authorize>
</div>
<div>
	<a href="/">Accueil</a>
</div>