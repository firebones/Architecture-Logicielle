<c:choose>
	<c:when test="${entry.getState() == 'APPROVED'}">Approuvée</c:when>
	<c:when test="${entry.getState() == 'SUBMITTED'}">Soumise</c:when>
	<c:when test="${entry.getState() == 'INPROGRESS'}">En cours</c:when>
	<c:when test="${entry.getState() == 'REFUSED'}">Refusée</c:when>
</c:choose>