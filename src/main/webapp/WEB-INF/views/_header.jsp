<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div id="login-info">
	<sec:authorize access="isAnonymous()">
		<p>You are not logged in</p>
	</sec:authorize>

	<sec:authorize access="isAuthenticated()">
		<p>
			Bienvenue,
			<sec:authentication property="principal.name" /> <%-- This corresponds to employee.getName() --%>
		</p>
	</sec:authorize>
</div>