<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><head>
<script language="javascript">
function submitForm(form){
	form.submit();
}

// START : #119240 : 29/06/09
 function doOperation(myOperationType)
  {
    document.editBankAccountForm.operationType.value = myOperationType;
    document.editBankAccountForm.submit();
  }
 // END : #119240 : 29/06/09 

</script>

<form:form name="editBankAccountForm" method="POST" action="edit_bank_account.htm">
	<form:hidden path="BU"/>
	<input type="hidden" name="numOfGroups" value="${fn:length(command.results)}">

<!-- START : #119240 : 29/06/09 -->
<input type="hidden" name="operationType" value="" />

<c:if test="${param.saved_message != null}">
  <div style="color:green;">
    ${param.saved_message}
  </div>
</c:if>
 <!-- END : #119240 : 29/06/09 -->

<c:if test="${requestScope['saved_message'] != null}">
  <div style="color:green;">
    <f:message key="${requestScope['saved_message']}"/>
  </div>
</c:if>
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
      <div id="MainContentContainer">
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="bankAccount"/></span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" cellpadding=0 cellspacing=0 border="0" class=InnerApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th width="50%" colspan="4"><f:message key="editBankAccounts"/></th>

                  <th width="45%" colspan="3" >&nbsp;</th>
                  
						<!-- START : #119240 : 29/06/09 -->
						<%--	
						  <th width="20%" bgcolor="#ffffff" style="text-align:right">
						<a href="search_bank_account.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp 
							<a href="#"><input type="image" src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a>  --%>
					  <th width="30%" bgcolor="#ffffff" style="text-align:right" nowrap>
							  <a href="#" onClick="javascript:doOperation('searchBank');">
					            <img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle">
					          </a>&nbsp; 

						<!-- START for ITrack note : 27-Jul-2009 -->	  							
						<c:if test="${sessionScope.bankAccountSearch.BUListSize > 1}">	
						<!-- END for ITrack note : 27-Jul-2009 -->
					          <a href="#" onClick="javascript:doOperation('prevBank');">
					            <img src="images/prevleftarrow.gif" alt="Go to Previous Bank" width="13" height="12" hspace="1px" border="0"/>
					          </a> &nbsp;
					          <a href="#" onClick="javascript:doOperation('nextBank');">
					            <img src="images/nextrtarrow.gif" alt="Go to Next Bank" width="13" height="12" hspace="1px" border="0"/>
					          </a> &nbsp;
						<!-- START for ITrack note : 27-Jul-2009 -->	  
							</c:if>	
						<!-- END for ITrack note : 27-Jul-2009 -->
					     
					          <a href="#"  onClick="javascript:doOperation('saveBank');">
					            <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" />
					          </a>
						 
						<!-- END : #119240 : 29/06/09 -->
					</th>
                  </tr>
					<tr>
						<th width="50" class="TDShade"><f:message key="primary"/></th>
						<th width="200" class="TDShade"><f:message key="bankName"/></th>
						<th width="80" class="TDShade"><f:message key="businessUnit"/></th>
						<th width="80" class="TDShade"><f:message key="currency"/></th>
						<th width="80" class="TDShade"><f:message key="bankCode"/></th>						
						<th width="80" class="TDShade"><f:message key="accountCode"/></th>
						<th width="80" class="TDShade"><f:message key="bankIDNBR"/></th>
						<th width="80" class="TDShade"><f:message key="BI"/></th>
					</tr>
					<c:forEach items="${command.results}" var="accountGroup" varStatus="accountGroupStatus">
						<c:forEach items="${accountGroup}" var="row" varStatus="rowStatus">
							<c:choose>
								<c:when test="${accountGroupStatus.index%2==0}">
									<tr bgcolor=#FFFFFF>
								</c:when>
								<c:otherwise>
									<tr bgcolor="#e7eeff">
								</c:otherwise>
							</c:choose>
							<td class="TDShade">
								<input type="radio" name="primary_${accountGroupStatus.index}" value="${row.BU}_;_${row.bankCode}_;_${row.bankAccountCode}_;_${row.currencyCode}" ${row.primary=='Y'?'checked':''}>
							</td>
							<td class="TDShade">${row.bankDesc}</td>
							<td class="TDShade">${row.BU}</td>
							<td class="TDShade">${row.currencyCode}</td>
							<td class="TDShade">${row.bankCode}</td>
							<td class="TDShade">${row.bankAccountCode}</td>
							<td class="TDShade">${row.bankIdNbr}</td>
							<td class="TDShade">${row.BI}</td>
						</tr>
						</c:forEach>
					</c:forEach>
					</tbody>
				</table>
            </div>
            <!----------------- TAB 1 CONTAINER END ------------------------------ -->
          </div>
        </div>
        <script type="text/javascript">
        //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
        dolphintabs.init("tabnav", 0)

        </script>
        <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
      </div>
      <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
    </td>
  </tr>
</table>
</form:form>

