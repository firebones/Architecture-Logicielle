<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Employee</title>
	<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
</head>
<body>
<jsp:include page="_header.jsp" />
<h1>
	Create a new entry
</h1>
<div class="container">
	<div class="row">
		<jsp:include page="_formEmployee.jsp" />
	</div>
</div>

</body>
</html>