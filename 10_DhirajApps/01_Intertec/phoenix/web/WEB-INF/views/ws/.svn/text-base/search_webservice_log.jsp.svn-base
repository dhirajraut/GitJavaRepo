<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<script language="javascript">
function submitForm(form){
	form.action="search_webservice_log.htm";
	form.submit();
}

function viewLogDetail(inOutBoundInd, type, status, totalRecords){
	if(totalRecords<=0){
		return;
	}
	var form=document.searchWebserviceLogForm;
	form.action="search_webservice_log_detail.htm";
	form.inOutBoundInd.value=inOutBoundInd;
	form.type.value=type;
	form.status.value=status;
	document.getElementById('pagination.totalRecord').value=totalRecords;
	form.submit();
}
</script>

<form:form name="searchWebserviceLogForm" method="POST" action="search_webservice_log.htm">
	<input type="hidden" name="inOutBoundInd"/>
	<input type="hidden" name="type"/>
	<input type="hidden" name="status"/>
	<form:hidden path="pagination.totalRecord"/>

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
              <li><a href="#" rel="tab1"><span><f:message key="webServiceLogStatuses"/></span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table border="0" align="center" cellpadding="1" cellspacing="0" class="mainApplTable">
                <tr>
                  <th colspan="8">
                    <f:message key="searchWebServiceLogStatuses"/>
                  </th>
                </tr>
                <tr class="InnerApplTable">
                  <td width="20%" class="TDShade"><f:message key="fromDate"/><strong> :</strong></td>
                  <td colspan="1" class="TDShadeBlue">
                    <form:input cssClass="inputBox" size="10" maxlength="10" path="fromDate" />
					<a href="#" onClick="displayCalendar(document.getElementById('fromDate'),'MM/dd/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
                    <form:errors path="fromDate" cssClass="redstar"/>
                 </td>
        
                 <td class="TDShade"><f:message key="toDate"/><strong> :</strong></td>
                 <td colspan="2" class="TDShadeBlue">
                    <form:input cssClass="inputBox" size="10" maxlength="10" path="toDate" />
					<a href="#" onClick="displayCalendar(document.getElementById('toDate'),'MM/dd/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
                    <form:errors path="toDate" cssClass="redstar"/>
                  </td>

                 <td class="TDShade"><f:message key="key"/><strong> :</strong></td>
                 <td colspan="2" class="TDShadeBlue">
                    <form:input cssClass="inputBox" size="20" path="key" />
                    <form:errors path="key" cssClass="redstar"/>
                  </td>
				</tr> 
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td>
						<input id="search" type="button" value="Search" name="search" class="button1" onClick="submitForm(this.form)"/></td>
                    </tr>
                  </table></td>
                </tr>
              </table>
              <br>
              <c:if test="${command.results != null}">
                <div id="webserviceLogResults"> 
                  <strong><f:message key="searchResults"/>
                  </strong>
					<c:forEach items="${command.results}" var="group" varStatus="groupStatus">
						<c:set var="bound" value="Outbound"/>
						<c:if test="${groupStatus.index==1}">
							<c:set var="bound" value="Inbound"/>
						</c:if>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
							<tr>
								<th><f:message key="${bound}"/></th>
						  	</tr>
						</table>
						<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
	                    <tr>
							<th width="25%" class="TDShade"><f:message key="type"/></th>
							<th width="10%" class="TDShade"><f:message key="success"/></th>
							<th width="10%" class="TDShade"><f:message key="fail"/></th>
							<th width="55%" class="TDShade"><f:message key="total"/></th>
	                    </tr>
					    <c:forEach items="${group}" var="row" varStatus="rowStatus">
							<c:choose>
								<c:when test="${rowStatus.index%2==0}">
								    <tr style="background-color:#FFFFFF;">
								</c:when>
								<c:otherwise>
									<tr style="background-color:#e7eeff;">                    
								</c:otherwise>
							</c:choose>
							<td class="TDShade">${row[0]}</td>
							<td class="TDShade"><a href="#" onClick="viewLogDetail('${bound}', '${row[0]}', 'true', '${row[1]}')">${row[1]}</a></td>
							<td class="TDShade"><a href="#" onClick="viewLogDetail('${bound}', '${row[0]}', 'false', '${row[2]}')">${row[2]}</a></td>
							<td class="TDShade"><a href="#" onClick="viewLogDetail('${bound}', '${row[0]}', 'all', '${row[1]+row[2]}')">${row[1]+row[2]}</a></td>
						</tr>    
					    </c:forEach>
					</table>
					<br>
					</c:forEach>
                </div>
              </c:if>
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

