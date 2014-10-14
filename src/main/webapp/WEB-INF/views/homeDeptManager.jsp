<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Department Manager Home</title>
	<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
	<script>
	function modifyUrlAssign(button) {
				var dropdownlist = document.getElementById('mySelection');
				var selectedId = dropdownlist.options[dropdownlist.selectedIndex].value;
				var concatenated = "";
				var test = concatenated.concat(selectedId, "/assignTasks");
				button.href = test;
			}		

	</script>
</head>
<body>
<h1>
	Accueil Gestionnaire
</h1>
<div class="container">
	<div class="row">
		<p>
			<tr>
				<a href="" class="col-lg-offset=2 btn btn-primary" onclick="modifyUrlAssign(this)">Assigner des tâches à un employé...</a>
			
				<select name="mySelection" id="mySelection">
			   	   <c:forEach var="employee" items="${employees}">
				        <option value="${employee.email}">${employee.name} - ${employee.email}</option>
				    </c:forEach>
				</select>
			</tr>
		</p>
		<p>
			<tr>
				<a href="/addEmployee" class="col-lg-offset=2 btn btn-primary">Créer, Modifier ou Supprimer un employé</a>
			</tr>
		</p>
	</div>
</div>

</body>
</html>


