<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="/resources/css/main.css" media="all" />
	<title>M2 - Architecture Logicielle</title>
</head>
<body>
<jsp:include page="_header.jsp" />
<h1>
	Ajouter une t�che
</h1>
<div class="container">
	<div class="row">
		<jsp:include page="_formTask.jsp" />
	</div>
</div>

</body>
</html>