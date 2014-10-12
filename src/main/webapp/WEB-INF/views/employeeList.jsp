<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Employee List</title>
	<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
	<meta http-equiv="cache-control" content="no-cache">
</head>
<body>
<jsp:include page="_header.jsp" />
<h1>
	Employee entries list  
</h1>

<div class="container">
	<div class="row">
		<table class="table col-lg-8">
			<thead>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Address</th>
					<th>Email</th>
					<th colspan="4"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="entry" items="${entries}">
					<c:url var="entryUrl" value="/${entry.email}" />
					<tr>
						<td>${entry.lastName}</td>
						<td>${entry.firstName}</td>
						<td>${entry.address}</td>
						<td>${entry.email}</td>
						<td>
							<a href="${entryUrl}">Details</a>
						</td>
						<td>
							<a href="${entryUrl}/edit">Edit</a>
						</td>
						<td>
							<a href="${entryUrl}/delete">Delete</a>
						</td>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="row">
		<a href="<c:url value="add" />" class="col-lg-offset=2 btn btn-primary">Add an entry</a>
	</div>
</div>


</body>
</html>
