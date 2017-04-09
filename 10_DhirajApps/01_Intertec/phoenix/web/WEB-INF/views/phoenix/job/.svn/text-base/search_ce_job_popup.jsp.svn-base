<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn"
	uri="http://www.intertek.com/jsp/jstl/functions"%>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><head>
<script type="text/javascript" src="js/ce/ce_services.js"></script>
<script type="text/javascript" src="js/ce/headerSearch.js"></script>

<form:form name="cejobSearchPopUpForm" method="POST"
	action="phx_search_ce_job_popup.htm">
	<form:hidden path="pageNo" />
	<input type="hidden" name="pageNumber" value="1" />
	<form:hidden path="sortFlag" />
	<div class="redtext"><form:errors cssClass="error" /></div>

	<table border="0" cellpadding="0" cellspacing="0" align="center"
		class="MainTable" style="padding: -left : 10px; padding-top: 5px;">


		<table cellspacing="0" cellpadding="0" align="center"
			style="width: 100%;">
			
			<tr>
				<td valign="top" align="left" colspan="2">
				<table width="100%" cellspacing="0" cols="3" class="AppTable">
					<tbody>
						<tr valign="center">
							<th><a href="#start" ><f:message key="jobNumber" /></a></th>
							<th><a href="#start" ><f:message key="quote" /></a></th>
							<th><a href="#start" ><f:message key="invoiceNumber" /></a></th>
						</tr>						
					
					<c:if test="${command.results == null}">
					  <script LANGUAGE="JavaScript">					  	 				
					     top.quickSearchMessage();
					     top.closeSearchStatusWindow();	
					  </script>
					</c:if>
					
					<c:if test="${command.results != null}">											
					  <script LANGUAGE="JavaScript">
					  	 top.closeSearchStatusWindow();					
					  </script>
						<c:choose>
						  <c:when test="${fn:length(command.results)==1}">
						     <script LANGUAGE="JavaScript">
	                           <c:forEach items="${command.results}" var="jobOrder" varStatus="status">
			                       revertToEntry('${jobOrder.jobNumber}','${jobOrder.jobType}');			
	                           </c:forEach>
	                           top.closeSearchStatusWindow();
                            </script>
						  </c:when>
						<c:otherwise>
						  <c:if test="${fn:length(command.results)>1}">
						     <script LANGUAGE="JavaScript">									   					
						        top.popSearchDetails();									        			  
						     </script>						 
						      <div id="contractsearchresults">
							   <c:forEach items="${command.results}" var="ceJobOrder" varStatus="status">
								 <c:choose>
		                             <c:when test="${status.index%2==0}">
			                            <tr class="whitebg">
								      </c:when>
								     <c:otherwise>
									   <tr class="bluebg">                    
								     </c:otherwise>
							     </c:choose> 
									<td align="left" ><a href=#start onclick="revertToEntry('${ceJobOrder.jobNumber}'); top.hidePopupDiv('searchDetails','searchDetailsbody');
					                              top.popupboxclose();">${ceJobOrder.jobNumber}</td>
									<td align="left" >${ceJobOrder.quote.quoteNumber}</td>							
									<td>&nbsp;</td>															
							    </tr>
							   </c:forEach>
						     </div>
						     <script LANGUAGE="JavaScript">
						        top.closeSearchStatusWindow();			  
						     </script>
					       </c:if>
						 </c:otherwise>
						</c:choose>
					  </c:if>						
					</tbody>
				 </table>				
				</td>
			</tr>
		</table>
</form:form>

