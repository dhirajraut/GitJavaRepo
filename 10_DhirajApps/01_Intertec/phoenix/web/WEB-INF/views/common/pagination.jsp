<c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
  <c:choose>
	<c:when test="${page.selected==true}">
		<span class="currentPageNum">${page.name}</span>&nbsp;&nbsp;
	</c:when>
	<c:otherwise>
		<a href="#start" onClick="submitSearch('${page.pageNumber}');">${page.name} </a>&nbsp;&nbsp;
	</c:otherwise>
  </c:choose>
</c:forEach>