<c:choose>
	<c:when test="${entry.role == 'EMPLOYEE'}">Employ�</c:when>
	<c:when test="${entry.role == 'MANAGER'}">Gestionnaire</c:when>
	<c:when test="${entry.role == 'ADMIN'}">Administrateur</c:when>
	<c:when test="${entry.role == 'COMPANY'}">Entreprise</c:when>
</c:choose>