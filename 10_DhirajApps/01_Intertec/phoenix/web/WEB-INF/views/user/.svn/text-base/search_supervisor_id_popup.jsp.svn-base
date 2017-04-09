<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script>
 function submitSearch(pageNumber)
			  {	 
				document.supervisorIdSearchPopUpForm.pageNumber.value = pageNumber;
				document.supervisorIdSearchPopUpForm.submit();
			  }	
</script>   
   <form:form name="supervisorIdSearchPopUpForm" method="POST" action="search_supervisor_id_popup.htm">
<form:hidden path="searchForm" />
<input type="hidden" name="pageNumber" value="1" />
<form:hidden path="inputFieldId" />
	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
	<form:errors cssClass="error"/>
	</div>

	<table border="0" cellpadding="0" cellspacing="0" align="center" class="MainTable" style="padding:-left:10px;padding-top:5px;">
		<tr>	 <td valign="top">
				<div id="tab1" class="innercontent" >
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="padding:-left:5px;">
						<tr>
							<th colspan="2"><f:message key="supervisorSearch"/></th>
						</tr>
						<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="supervisorId"/>: </td>
							<td width="80%" class="TDShadeBlue"><form:input cssClass="inputBox" path="id.value" />
									<form:errors path="id.value" cssClass="redstar"/></td>
					</tr>
					<tr>
							<td width="20%" class="TDShade" nowrap><f:message key="branchName"/>: </td>
							<td width="80%" class="TDShadeBlue"><form:input cssClass="inputBox" path="branchName.value" />
									<form:errors path="branchName.value" cssClass="redstar"/></td>
					</tr>


				</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
						<tr>
							<td>
						
							  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td><input name="Submit" type="submit" class="button1" value="<f:message key="search"/>"  /></td>
													</tr>
								</table>
							</td>
						</tr>
					</table> 
					<br />
					<c:if test="${command.results != null}">
					<div id="supervisoridsearchresults"> 
					<strong><f:message key="searchResults"/></strong>
					<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
					<tr>
									<th><span class="TDShade"><f:message key="supervisorId"/></span></th>
									<th><span class="TDShade"><f:message key="branchName"/></span></th>
									</tr>
									<c:forEach items="${command.results}" var="employee" varStatus="status">
									<c:choose>
						<c:when test="${status.index%2==0}">
						<tr style="background-color:#FFFFFF;">
						</c:when>
						<c:otherwise>
						<tr style="background-color:#e7eeff;">                    
						</c:otherwise>
						</c:choose>
									<td><a href="#" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}',
							 '${employee.id}');top.hidesupervisor();top.popupboxclose();">${employee.id}</td>
							 <td>${employee.branchName}</td>
									</tr>
								</c:forEach>
								<tr>
					           <td></td>
                             <td align="center">
   					     <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
 					<a href="#start" onClick="submitSearch('${page.pageNumber}')">${page.name} </a>&nbsp;&nbsp;
	                  </c:forEach>
					</td>
					</tr> 
					<tr></tr>
								</table>
								</div>
								</c:if>		
								</div>
			</td>
		</tr>
	</table>
</form:form>		  