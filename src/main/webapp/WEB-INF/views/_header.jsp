<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<header>
<jsp:include page="_navigation.jsp" />
<sec:authorize access="isAnonymous()">
	<p>Vous n'�tes pas connect�. <a href="/users">Connexion</a></p>
</sec:authorize>
</header>