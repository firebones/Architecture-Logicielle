<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form:form method="post" class="form-horizontal" modelAttribute="task">
       
       <div class="form-group">
         <label for="name" class="control-label col-lg-2">Nom</label>
         <div class="col-lg-10">
           <form:input class="form-control" path="name" />
         </div>
       </div>
       
       <div class="form-group">
         <label for="rate" class="control-label col-lg-2">Taux</label>
         <div class="col-lg-10">
           <form:input class="form-control" path="rate" />
         </div>
       </div>
       
       <div class="form-group">
         <div class="col-lg-offset-2 col-lg-10">
           <button type="submit" class="btn btn-default">Soumettre</button>
           <a href="<c:url value="cancel"/>" class="col-lg-offset=2 btn btn-primary">Annuler</a>
         </div>
       </div>
</form:form>