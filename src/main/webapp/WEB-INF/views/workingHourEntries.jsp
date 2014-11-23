<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<jsp:include page="_meta.jsp" />
</head>
<jsp:include page="_header.jsp" />
<body>
  <div class="container">
	<h1>Saisir ses heures de travail réalisées</h1>
    <form method="POST" action="/workingHours" 
    modelAttribute="weekEntry" 
    modelAttribute="errorMessage" 
    class="form form-horizontal well" >
    	<c:set var="datesOfWeek" value="${weekEntry.getDatesOfWeek()}" />
    	<c:set var="entries" value="${weekEntry.getEntries()}" />
    	<c:set var="daysOfWeek" value="${weekEntry.getDaysOfWeek()}" />
		<c:if test="${not empty errorMessage}">
			<label class="errorBlock">${errorMessage}</label>
		</c:if>
		<c:forEach items="${daysOfWeek}" var="day" varStatus="status">
			<div class="control-group">
				<label class="control-label" for="${status.index}">${day} ${datesOfWeek[status.index]}</label>
				<c:choose>
				   <c:when test="${weekEntry.getIsReadOnly()}"><input name="hours" class="input-block-level" id="${status.index}" type="text" value="${entries[status.index]}" disabled/></c:when>
				   <c:otherwise><input name="hours" class="input-block-level" id="${status.index}" type="text" value="${entries[status.index]}" enabled/></c:otherwise>
				</c:choose></div>
		</c:forEach>
		<c:choose>
			<c:when test="${weekEntry.getIsReadOnly()}"><button type="submit" class="btn btn-default" disabled>Enregistrer</button></c:when>
			<c:otherwise><button type="submit" class="btn btn-default" enabled>Enregistrer</button></c:otherwise>
		</c:choose>
		<a href="<c:url value="/" />" class="col-lg-offset=2 btn btn-primary">Retour</a>
    </form>
  </div>
</body>
</html>