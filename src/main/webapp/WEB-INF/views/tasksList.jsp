<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="_meta.jsp" />
</head>
<body>
	<jsp:include page="_header.jsp" />
	<h1>
		Liste des Tâches
	</h1>
	<div class="container">
		<div class="row">
			<table class="table col-lg-8">
				<tbody>
			<c:forEach var="task" items="${tasks}">
	
				<tr>
					<td>
						${task.name}
					</td>
				</tr>
			</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>