<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%><head>
 <script language="javascript">
	   

   </script>
</head>

 <form:form name="vesselSearchPopUpForm" method="POST" action="search_vessel_popup.htm">
    
	<form:hidden path="searchType"/>
	<form:hidden path="searchForm"/>
	<form:hidden path="inputFieldId"/>
	<form:hidden path="rowNum"/>
	
<a href="#inst"></a>
	 <input type="hidden" name="pageNumber" value="1" />
	<div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
		<form:errors cssClass="error"/>
	</div>

	<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
		<tr>
			<td valign="top">
				<div id="tab1" class="innercontent" >
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
						
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="vesselType"/>:</td>
							<td width="80%" class="TDShadeBlue">
							
								 <form:input cssClass="inputBox" path="vesselType.value"/>
								 <form:errors path="vesselType.value"/> 
							</td>
						</tr>

						
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
						<tr>
							<td>
							  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td><input name="Submit" type="submit" class="button1" value="<f:message key="search"/>"  /></td>
										<td style="text-align:right"><a href="../jobs/ins_jobsinstructions.html"></a></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				<br />
			      <c:if test="${command.results != null}">
						<div id="vesselsearchresults"> 
							<strong><f:message key="searchResults"/></strong>
							<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
								<tr>
									<th><span class="TDShade"><f:message key="vesselName"/></span></th>
									<th><span class="TDShade"><f:message key="vesselSet"/></span></th>
								
								 </tr>
								 <c:forEach items="${command.results}" var="vessel" varStatus="status">
									 <c:choose>
					<c:when test="${status.index%2==0}">
				    <tr style="background-color:#FFFFFF;">
					  </c:when>
					  <c:otherwise>
					  <tr style="background-color:#e7eeff;">                    
					  </c:otherwise>
					  </c:choose>
										
				            <td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}', '${vessel.vesselTypeId.vesselType}');top.hidePopupDiv('vesselsearch${command.rowNum}','vesselsearchbody${command.rowNum}');top.popupboxclose();">${vessel.vesselTypeId.vesselType}</a></td>
									<td>${vessel.vesselTypeId.vesselSet}</td>
									
									</tr>


								</c:forEach> 
									 <tr>
					
									<td align="center">

									<c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
									<SCRIPT LANGUAGE="JavaScript">

									function submitSearch(pageNumber)
												  {	 
													 
													document.vesselSearchPopUpForm.pageNumber.value = pageNumber;
													document.vesselSearchPopUpForm.submit();
												  }	

									</SCRIPT>
										<a href="#start" onClick="submitSearch('${page.pageNumber}');">${page.name} </a>
									  </c:forEach>
									</td>
									</tr>
							</table>
						</div>
					</c:if> 
				
				</div>
			</td>
		</tr>
	</table>

</form:form>


