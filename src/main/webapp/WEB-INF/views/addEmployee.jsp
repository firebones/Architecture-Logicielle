<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Employees</title>
</head>
<body>
  <section>
    <div class="jumbotron">
      <div class="container">
        <h1>Employee</h1>
        <p>Add employee</p>
      </div>
    </div>
  </section>
  <section class="container">
    <form:form  modelAttribute="newEmploye" class="form-horizontal">
      <fieldset>
        <legend>Add new employe</legend>

        <div class="form-group">
          <label class="control-label col-lg-2 col-lg-2" for="lastName">Nom</label>
          <div class="col-lg-10">
            <form:input id="lastName" path="lastName" type="text" class="form:input-large"/>
          </div>
        </div>
        
        <div class="form-group">
          <label class="control-label col-lg-2 col-lg-2" for="firstName">Prénom</label>
          <div class="col-lg-10">
            <form:input id="firstName" path="firstName" type="text" class="form:input-large"/>
          </div>
        </div>
        
        <div class="form-group">
          <label class="control-label col-lg-2 col-lg-2" for="email">Email</label>
          <div class="col-lg-10">
            <form:input id="email" path="email" type="text" class="form:input-large"/>
          </div>
        </div>
        
        <div class="form-group">
          <label class="control-label col-lg-2 col-lg-2" for="address">Address</label>
          <div class="col-lg-10">
            <form:input id="address" path="address" type="text" class="form:input-large"/>
          </div>
        </div>
        <div class="form-group">
          <div class="col-lg-offset-2 col-lg-10">
            <input type="submit" id="btnAdd" class="btn btn-primary" value ="Add"/>
          </div>
        </div>
      </fieldset>
    </form:form>
  </section>
</body>
</html>