<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<jsp:include page="_meta.jsp" />
</head>
<jsp:include page="_header.jsp" />
<body>
	 <div class="container">
		<h1>Saisir ses déplacements en kilomètres</h1>
	    <form 
	    	class="form form-horizontal well" 
	    	method="POST" action="/vehicleExpenses" 
		    modelAttribute="daysOfWeek" 
		    modelAttribute="valuesOfWeek" 
		    modelAttribute="daysNameOfWeek">
			<c:forEach items="${daysNameOfWeek}" var="day" varStatus="status">
				<div class="control-group">
					<label class="control-label" for="${status.index}">${day} ${daysOfWeek[status.index]}</label>
					<input	class="input-block-level" id="${status.index}" type="text" value="${valuesOfWeek[status.index]}"/>
				</div>
			</c:forEach>
	        	<a href="<c:url value="/" />" class="col-lg-offset=2 btn btn-primary">Soumettre</a>
				<button type="submit" class="btn btn-default">Enregistrer</button>
				<a href="<c:url value="/" />" class="col-lg-offset=2 btn btn-primary">Annuler</a>
	    </form>
	 </div>
</body>
</html>