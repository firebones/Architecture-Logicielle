<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form:form method="post" class="form-horizontal" modelAttribute="employee">
       
       <div class="form-group">
         <label for="name" class="control-label col-lg-2">Nom</label>
         <div class="col-lg-10">
           <form:input class="form-control" path="name" disabled="${fn:length(employee.name) > 0}"/>
         </div>
       </div>
       
       <div class="form-group">
         <label for="email" class="control-label col-lg-2">Courriel</label>
         <div class="col-lg-10">
           <form:input class="form-control" path="email" disabled="${fn:length(employee.email) > 0}"/>
         </div>
       </div>
       
       <div class="form-group">
         <label for="rateHour" class="control-label col-lg-2">Taux horaire</label>
         <div class="col-lg-10">
           <form:input class="form-control" path="rateHour" />
         </div>
       </div>
       
       <div class="form-group">
         <div class="col-lg-offset-2 col-lg-10">
           <button type="submit" class="btn btn-default">Soumettre</button>
           <a href="<c:url value="employeeList"/>" class="col-lg-offset=2 btn btn-primary">Annuler</a>
         </div>
       </div>
</form:form>

