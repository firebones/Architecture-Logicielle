<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form:form method="post" modelAttribute="newEmploye" class="form-horizontal">
	<fieldset>
		
		<legend>Add new employee</legend>

        <div class="form-group">
          <label class="control-label col-lg-2 col-lg-2" for="lastName">Nom</label>
          <div class="col-lg-10">
            <form:input id="lastName" path="lastName" type="text" class="form:input-large" />
            <form:errors path="lastName" cssClass="errorField"/>
          </div>
        </div>
        
        <div class="form-group">
          <label class="control-label col-lg-2 col-lg-2" for="firstName">Prenom</label>
          <div class="col-lg-10">
            <form:input id="firstName" path="firstName" type="text" class="form:input-large"/>
            <form:errors path="firstName" cssClass="errorField"/>
          </div>
        </div>
        
        <div class="form-group">
          <label class="control-label col-lg-2 col-lg-2" for="email">Email</label>
          <div class="col-lg-10">
            <form:input id="email" path="email" type="text" class="form:input-large"/>
            <form:errors path="email" cssClass="errorField"/>
          </div>
        </div>
        
        <div class="form-group">
          <label class="control-label col-lg-2 col-lg-2" for="address">Address</label>
          <div class="col-lg-10">
            <form:input id="address" path="address" type="text" class="form:input-large"/>
            <form:errors path="address" cssClass="errorField"/>
          </div>
        </div>
        <div class="form-group">
          <div class="col-lg-offset-2 col-lg-10">
            <input type="submit" id="btnAdd" class="btn btn-primary" value ="Add"/>
            <a href="<c:url value="empList" />" class="col-lg-offset=2 btn btn-primary">List</a>
          </div>
         </div>
             
		
	</fieldset>
</form:form>