<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/resources/css/main.css" media="all" />
    <title>Login</title>
</head>
<body>
    <c:if test="${'fail' eq param.auth}">
        <div id="error">Echec d'authentification : 
            ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
        </div>
    </c:if>
    
    <div id="login">
        <h1>Login</h1>
        <form action="${pageContext.request.contextPath}/authentification" method="post">
            <input type='text' name='username' placeholder="Nom d'utilisateur" />
            <input type="password" name="password" placeholder="Mot de passe" />
            <input type="submit" name="submit" value="Submit" />
        </form>
    </div>

</body>
</html>