<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="/resources/css/main.css" media="all" />
	<title>M2 - Architecture Logicielle</title>
	<script>
	function modifyTasksCheckboxStatus(checkboxProject) {
				
				var checkboxesTask = document.getElementsByName("tasks");
				
			    for (var i=0; i < checkboxesTask.length; i++){
			     	var checkboxTask = checkboxesTask[i];
			     	var projectId = checkboxTask.id.substr(0, checkboxTask.id.indexOf('-')); 
			     	if(checkboxTask.type == "checkbox" && projectId == checkboxProject.id)
			        	checkboxTask.checked = checkboxProject.checked;
			    }	
			}
			
	function modifyProjectCheckboxStatus(checkboxTask) {
				
				var allChecked = true;
				var projectId = checkboxTask.id.substr(0, checkboxTask.id.indexOf('-')); 
				
				var checkboxProjet = document.getElementById(projectId);
				if(checkboxTask.checked == false)
					checkboxProjet.checked = false;
				else
					var checkboxTasks = document.getElementsByName("tasks");
		    		for (var i=0; i < checkboxTasks.length; i++)
		    		{
				    	var checkboxTaski = checkboxTasks[i];
				    	var taskBelongToId = checkboxTaski.id.substr(0, checkboxTaski.id.indexOf('-'));
				     	if(taskBelongToId == projectId && checkboxTaski.type == "checkbox" && checkboxTaski.checked == false)
				        	allChecked=false;
				  	}	
				  	
				    checkboxProjet.checked = allChecked;

			}

	</script>
</head>
<body>
<form method="POST" class="form-horizontal">
	<h1 id="demo">
		Assigner des tâches à l'employé : ${assignTasksView.userId}
	</h1>
	<div class="container">
		<div class="row">
			<c:forEach var="project" varStatus="status" items="${assignTasksView.getProjects()}" >
				<table class="table table-striped">
					<tbody>
						<tr>
							<th colspan="2" path="project.name"><strong>${project.name}</strong></th>
							<td><INPUT TYPE="CHECKBOX" NAME="${project.id}" value="${project.id}" id="${project.id}" onclick="modifyTasksCheckboxStatus(this)"</td>
						</tr>
						<c:forEach var="task" items="${project.getTasks()}" varStatus="j" >
						     <c:if test="${selectedTasks.tasks.contains(task)}"/>
							 
							<tr>
								<td></td>
								<td>${task.name}</td>
								<td><input type="checkbox" name="tasks" value="${task.id}" id="${project.id}-${task.id}" onclick="modifyProjectCheckboxStatus(this)" <c:if test="${task.isAssigned == true}">checked = "Checked"</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:forEach>
		</div>
		<div class="form-group">
			<div class="row">
				<button type="submit" class="btn btn-default">Enregistrer</button>
				<a href="<c:url value="assignTasksCancel" />" class="col-lg-offset=2 btn btn-primary">Annuler</a>
			</div>
		</div>
	</div>
</form>	

</body>
</html>
