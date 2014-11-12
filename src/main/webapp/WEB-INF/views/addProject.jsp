<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<jsp:include page="_meta.jsp" />
</head>
<jsp:include page="_header.jsp" />
<body>
<div class="container">
<h1>
	Ajouter un projet
</h1>
	<div class="row">
		<jsp:include page="_formProject.jsp" />
	</div>
</div>

</body>
</html>