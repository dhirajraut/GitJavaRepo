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
	form.csv.value=false;
	form.submit();
}
function exportToCSV(){
	var form=document.searchBankAccountForm;
	form.csv.value=true;
	form.submit();
}
</script>
<icb:list var="yesNo">
  <icb:item>yesNo</icb:item>
</icb:list>

<form:form name="searchBankAccountForm" method="POST" action="search_bank_account.htm">
	<input type="hidden" name="csv" value="false">
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
              <table border="0" align="center" cellpadding="1" cellspacing="0" class="mainApplTable">
                <tr>
                  <th colspan="4">
                    <f:message key="searchBankAccount"/>
                  </th>
                </tr>
                <tr class="InnerApplTable">
                  	<td width="100" class="TDShade" nowrap><f:message key="businessUnit"/><strong> :</strong></td>
                  	<td width="35%" class="TDShadeBlue" nowrap>
						<!-- START : #119240 -->
						<%-- <form:select id="bu" disabled="false" cssClass="selectionBoxbig" path="BU" items="${icbfn:dropdown('businessUnit', null)}" itemLabel="name" itemValue="value"/> --%>
						<form:select id="bu" disabled="false" cssClass="selectionBoxbig" path="BU" items="${icbfn:dropdown('businessUnit', null)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {submitForm(this.form);}"/>
						<!-- END : #119240 -->
					</td>
					<td width="100" class="TDShade" nowrap>&nbsp;</td>
					<td class="TDShadeBlue" nowrap>&nbsp;</td>
				</tr>
				<tr class="InnerApplTable">
                  	<td class="TDShade" nowrap><f:message key="bankCode"/><strong> :</strong></td>
                  	<td class="TDShadeBlue" nowrap>
                    	<!-- START : #119240 -->
						<%-- <form:input cssClass="inputBox" size="10" maxlength="50" path="bankCode" /> --%>
						<form:input cssClass="inputBox" size="10" maxlength="50" path="bankCode" onkeypress="if(event.keyCode==13) {submitForm(this.form);}"/>
						<!-- END : #119240 -->
                    	<form:errors path="bankCode" cssClass="redstar"/>
					</td>
                  	<td class="TDShade" nowrap><f:message key="bankName"/><strong> :</strong></td>
                  	<td class="TDShadeBlue" nowrap>
						<!-- START : #119240 -->
                    	<%-- <form:input cssClass="inputBox" size="45" maxlength="200" path="bankName" /> --%>
						<form:input cssClass="inputBox" size="45" maxlength="200" path="bankName" onkeypress="if(event.keyCode==13) {submitForm(this.form);}"/>
						<!-- END : #119240 -->
                    	<form:errors path="bankName" cssClass="redstar"/>
					</td>
				</tr> 
				<tr class="InnerApplTable">
                  	<td class="TDShade" nowrap><f:message key="accountCode"/><strong> :</strong></td>
                  	<td class="TDShadeBlue" nowrap>
						<!-- START : #119240 -->
                    	<%-- <form:input cssClass="inputBox" size="10" maxlength="50" path="accountCode" /> --%>
						<form:input cssClass="inputBox" size="10" maxlength="50" path="accountCode" onkeypress="if(event.keyCode==13) {submitForm(this.form);}"/>
						<!-- END : #119240 -->
                    	<form:errors path="accountCode" cssClass="redstar"/>
					</td>
                  	<td class="TDShade" nowrap><f:message key="BI"/><strong> :</strong></td>
                  	<td class="TDShadeBlue" nowrap>
						<!-- START : #119240 -->
						<%-- <form:select cssClass="selectionBox" path="BI">
							<form:option value=" " label="All" />
							<form:options items="${icbfn:dropdown('staticDropdown',yesNo)}" itemLabel="name" itemValue="value" />
						</form:select> --%>
						<form:select cssClass="selectionBox" path="BI" onkeypress="if(event.keyCode==13) {submitForm(this.form);}">
							<form:option value=" " label="All" />
							<form:options items="${icbfn:dropdown('staticDropdown',yesNo)}" itemLabel="name" itemValue="value" />
						</form:select>
						<!-- END : #119240 -->
					</td>
				</tr> 

              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td>
						<input id="search" type="button" value="Search" name="search" class="button1" onClick="submitForm(this.form)"/></td>
					  <td align="right">
				  			<a href="javascript:exportToCSV();"><img src="images/ico_excel.gif" border="0"></a>
					  </td>
                    </tr>
                  </table></td>
                </tr>
              </table>
              <br>
              <c:if test="${command.results != null}">
                <div id="bankAccountResultsDIV"> 
                  <strong><f:message key="searchResults"/></strong>
				<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
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
					<c:forEach items="${command.results}" var="row" varStatus="rowStatus">
						<c:choose>
							<c:when test="${rowStatus.index%2==0}">
								<tr style="background-color:#FFFFFF;">
							</c:when>
							<c:otherwise>
								<tr style="background-color:#e7eeff;">                    
							</c:otherwise>
						</c:choose>
						<td class="TDShade">${row.primary}</td>
						<td class="TDShade">${row.bankDesc}</td>
						<td class="TDShade"><a href="edit_bank_account.htm?BU=${row.BU}">${row.BU}</a></td>
						<td class="TDShade">${row.currencyCode}</td>
						<td class="TDShade">${row.bankCode}</td>
						<td class="TDShade">${row.bankAccountCode}</td>
						<td class="TDShade">${row.bankIdNbr}</td>
						<td class="TDShade">${row.BI}</td>
					</tr>    
					</c:forEach>
				</table>
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

