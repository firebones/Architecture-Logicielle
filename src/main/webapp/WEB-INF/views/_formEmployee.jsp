<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<form:form method="post" class="form-horizontal" modelAttribute="entry">
	<div class="form-group">
		<label for="name" class="col-lg-2 control-label">Name</label>
		<div class="col-lg-10">
			<form:input class="form-control" path="name" disabled="${fn:length(entry.name) > 0}" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="lastname" class="col-lg-2 control-label">Last Name</label>
		<div class="col-lg-10">
			<form:input class="form-control" path="lastname" disabled="${fn:length(entry.lastname) > 0}" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="phone" class="col-lg-2 control-label">Phone</label>
		<div class="col-lg-10">
			<form:input class="form-control" path="phone" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="address" class="col-lg-2 control-label">Address</label>
		<div class="col-lg-10">
			<form:input class="form-control" path="address" />
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-lg-offset-2 col-lg-10">
			<button type="submit" class="btn btn-default">Submit</button>
		</div>
	</div>
</form:form>	