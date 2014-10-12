<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div id="logged-info">
	<ul>
		<li><a href="/">Home</a></li>
		<li><a href="/users">Page for users</a></li>
		<sec:authorize access="hasRole('MANAGER')">
			<li><a href="/add">Add Employee</a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('EMPLOYEE')">
			<li><a href="/vehicleExpenses">Vehicle Expenses</a></li>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<li><a href="/j_spring_security_logout">Logout</a></li>
		</sec:authorize>
	</ul>
</div>