<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form:form method="post" class="form-horizontal" modelAttribute="newEmployee">
       <div class="form-group">
         <label for="name" class="control-label col-lg-2">Nom</label>
         <div class="col-lg-10">
           <form:input class="form-control" path="name" />
         </div>
       </div>
       
       <div class="form-group">
         <label for="email" class="control-label col-lg-2">Email</label>
         <div class="col-lg-10">
           <form:input class="form-control" path="email" disabled="${fn:length(newEmployee.email) > 0}" />
         </div>
       </div>
       
       <div class="form-group">
         <label for="role" class="control-label col-lg-2">R�le</label>
         <div class="col-lg-10">
           <form:input class="form-control" path="role" disabled="true" value="EMPLOYEE" />
         </div>
       </div>
       
       <div class="form-group">
         <div class="col-lg-offset-2 col-lg-10">
           <button type="submit" class="btn btn-default">Ajouter</button>
           <a href="<c:url value="employeeList" />" class="col-lg-offset=2 btn btn-primary">Annuler</a>
         </div>
        </div>
</form:form>