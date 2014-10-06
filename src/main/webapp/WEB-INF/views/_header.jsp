<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div id="login-info">
	<sec:authorize access="isAnonymous()">
		<p>You are not logged in</p>
	</sec:authorize>

	<sec:authorize access="isAuthenticated()">
		<p>
			Welcome,
			<sec:authentication property="principal.name" /> <%-- This corresponds to employee.getName() --%>
		</p>
	</sec:authorize>

	<sec:authorize access="hasRole('ADMIN')">
		<p>This can only be seen by admin!</p>
	</sec:authorize>
	
	<sec:authorize ifAnyGranted="ADMIN">
		<p>This too.</p>
	</sec:authorize>
</div>