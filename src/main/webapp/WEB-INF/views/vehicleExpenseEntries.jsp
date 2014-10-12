<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Employees</title>
</head>
<body>
<h1>Saisir ses déplacements en kilomètres</h1>
  <section class="container">
    <form method="POST" action="/vehicleExpenses" modelAttribute="daysOfWeek" modelAttribute="valuesOfWeek" modelAttribute="daysNameOfWeek" class="form-horizontal >

        

        <div class="form-group">
        <table class="table table-striped">
        	<tr>
			   	<c:forEach items="${daysNameOfWeek}" var="day" varStatus="status">
				   	<th>${day} ${daysOfWeek[status.index]}</th>
		   		</c:forEach>
	   		</tr>
		   	<tr>
		   		<c:forEach items="${valuesOfWeek}" var="value" varStatus="statusKm">
				   	<td><input name="assignedKilometers" type="text" value="${value}"/></td>
		   		</c:forEach>
		   	</tr>
		   </table>
        </div>
        <div class="row">
        	<a href="<c:url value="/" />" class="col-lg-offset=2 btn btn-primary">Soumettre</a>
			<button type="submit" class="btn btn-default">Enregistrer</button>
			<a href="<c:url value="/" />" class="col-lg-offset=2 btn btn-primary">Annuler</a>
		</div>
    </form>
  </section>
</body>
</html>