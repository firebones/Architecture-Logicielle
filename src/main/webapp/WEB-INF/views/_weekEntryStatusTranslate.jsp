<c:choose>
	<c:when test="${entry.getState() == 'APPROVED'}">Approuv�e</c:when>
	<c:when test="${entry.getState() == 'SUBMITTED'}">Soumise</c:when>
	<c:when test="${entry.getState() == 'INPROGRESS'}">En cours</c:when>
	<c:when test="${entry.getState() == 'REFUSED'}">Refus�e</c:when>
</c:choose>