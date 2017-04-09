<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz"%>
<script language="javascript" src="js/ci/consolInvoice.js"></script>


<form:form name="conslInvCreateForm" method="POST" action="phx_cg_consolidate_invoice.htm">
<div style="color:red;"><form:errors cssClass="error"/></div>
<form:hidden path="tabName" />
<form:hidden path="sortFlag"/>
<form:hidden path="sortBy"/>
<form:hidden path="refreshing"/>
<form:hidden path="chosenInvoices"/>
<form:hidden path="detachedInvoices"/>
<input type="hidden" name="pageNumber" value="1" />
<input type="hidden" name="invoiceFileCountable" />
<input type="hidden" name="invoiceFileId" />
<c:set var="generated"  value="${!empty command.billStatus && command.billStatus == 'INV'}"/>
<table width="87%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">	
      <!-- MAIN CONTAINER -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="header"/></span></a></li>
			  <li><a href="#" rel="tab2"><span><f:message key="address"/></span></a></li>
			  <li><a href="#" rel="tab3" onClick="submitConsol();"><span><f:message key="attachDetach"/></span></a></li>
			   <c:if test ="${command.edit=='true'}">
			  <li><a href="#" rel="tab4"><span><f:message key="viewInvoice"/></span></a></li>
			  </c:if>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
			
			<!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th width="50%" colspan="2">
                    <c:choose>
                    <c:when test ="${command.edit=='true'}">
                    <f:message key="unit"/> : ${command.buName}
                    <img src="images/separator1.gif" width="2" height="22" align="absmiddle" style="margin-left:5px; margin-right:5px;">&nbsp;
                    <f:message key="invoice"/> : ${command.consolInvoiceNumber}
                    </c:when>
                    <c:otherwise>
                     <f:message key="invoice"/> : ${command.consolInvoiceNumber}
                    </c:otherwise>
                    </c:choose>
                    </th>
                    <th width="40%" colspan="2" align="right" ><f:message key="pretaxAmt"/> : ${command.preTaxAmount}&nbsp;${command.currencyCd }</th>
                    <th width="10%" bgcolor="#ffffff" style="text-align:right"><a href="phx_search_cg_consolidate_invoice.htm">
                    <img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <c:if test="${not generated}"><a href="#">
                    <input type="image" src="images/icosave.gif"  onClick="onSave()" alt="Save" width="14" height="14" border="0"  /></a></c:if></th>
                  </tr>
                  <tr>
                    <td colspan="5" style="padding:2px;">
					
					<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
					<tr>
					<c:if test ="${command.edit=='true'}">
					<c:choose>
					<c:when test="${command.vatCountryFlag != null && command.vatCountryFlag != 'false'}">
					<th colspan="2"><f:message key="billingControl"/></th> 
					<th colspan="2">
					<f:message key="vatProvince"/>:
					<form:select cssClass="selectionBox"  id="sel5" path="vatProvince" items="${command.vatProvinceList}" itemLabel="name" itemValue="value" disabled="${generated}"/>
					<form:errors path="vatProvince" cssClass="redstar"/>
					</th>
					</c:when>
					<c:otherwise>
					<th colspan="4"><f:message key="billingControl"/></th>
					</c:otherwise>
					</c:choose>
					 </c:if> 
					  
					</tr>
					<c:if test = "${command.edit!='true'}">
					<tr>
					  <td class="TDShade" width="20%"><f:message key="unit"/> : </td>
					  <td class="TDShadeBlue">					  
					  <form:select id="sel3" cssClass="selectionBoxbig" path="buName" items="${command.buNames}" itemLabel="name" itemValue="value" /> 
	                  <form:errors path="buName" cssClass="redstar"/>	
					  </td>
					  
					<c:choose>
					<c:when test="${command.vatCountryFlag != 'false'}">
					<td class="TDShade"><f:message key="vatProvince"/>:</td>
					<td colspan="2" class="TDShadeBlue">					
					<form:select cssClass="selectionBox" id="sel5" path="vatProvince" items="${command.vatProvinceList}" itemLabel="name" itemValue="value"/>
					<form:errors path="vatProvince" cssClass="redstar"/>
					</td>
					</c:when>
					<c:otherwise>
					<td class="TDShade" width="20%">&nbsp; </td>
					<td class="TDShadeBlue">
					&nbsp;
					</td>
					</c:otherwise>
					</c:choose>
					</tr>	
					</c:if>				
					<tr>
					  <td class="TDShade" width="20%"><f:message key="customer"/> : </td>
					  <td class="TDShadeBlue">
					  <form:input id="cust" cssClass="inputBox" size="14" path="custCode" disabled="${command.edit=='true'}" />
                      <form:errors path="custCode" cssClass="redstar"/>					  
					  &nbsp;
					  <c:if test = "${command.edit!='true'}">
					  <a href="#" onClick="javascript:showCustomerSearchIdSearchLookup();">
						<img src="images/lookup.gif" alt="lookup Customer" width="13" height="13" border="0"></a>
						</c:if>
						<span id="name">${command.name}</span>
						
						 
						
					  </td>
					  <td class="TDShade" width="20%"><f:message key="currency"/> : </td>
					  <td class="TDShadeBlue">
					<form:select id="sel6" cssClass="selectionBox" path="currencyCd" items="${command.currencyList}" itemLabel="name" itemValue="value" disabled="${generated || attached}"/>
					<form:errors path="" cssClass="redstar"/>
                       </td>
					</tr>
					<tr>
					  <td class="TDShade" width="20%"><f:message key="status"/> : </td>
					  <td class="TDShadeBlue">
					  <form:select cssClass="selectionBox" id ="sel1"  path="billStatus" items="${command.conslInvStatus}" itemLabel="name" itemValue="value" disabled="${generated}"/>
					  </td>
					  <td class="TDShade" width="20%"><f:message key="invoiceType"/> : </td>
					  <td class="TDShadeBlue">${command.invoiceType}</td>
					</tr>
					<tr>
					  <td class="TDShade"><f:message key="type"/> : </td>
					  <td class="TDShadeBlue">
					  <select name="sel3" size="1" class="selectionBox" id="sel3" style="width:84px;" <c:if test="${generated}">disabled</c:if>>
                           <option value="" selected>CON</option>
                       </select></td>
					  <td class="TDShade"><f:message key="dateBillAdded"/> : </td>
					  <td class="TDShadeBlue"><f:formatDate value="${command.billAddDt}" type="date" dateStyle="short" /></td>
					</tr>

                  <tr>
                    <td colspan="5" style="padding:2px;">
					
					<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
					<tr>
					  <th colspan="4"><f:message key="billTo"/></th>
					</tr>
					</table>	
					
					
					</td>
                  </tr>
                 <tr bgcolor=#ffffff>
	                   <td width="15%" class="TDShade"><f:message key="fromDate"/> : </td>
	                   <td width="35%" class="TDShadeBlue">
	        			<form:input id="fromDt" cssClass="inputBox" cssStyle="width:70px;" path="fromDt" disabled="${generated}"/>
	                     <form:errors path="fromDt" cssClass="redstar"/>
	                   &nbsp;<c:if test="${not generated}"><a href="#" onClick="displayCalendar(document.forms[0].fromDt,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></c:if></td>
	                   <td width="15%" class="TDShade" ><f:message key="toDate"/> : </td>
	                   <td colspan="2" class="TDShadeBlue" width="35%" >
	                   <form:input id="toDt" cssClass="inputBox" cssStyle="width:70px;" path="toDt" disabled="${generated}"/>
	                     <form:errors path="toDt" cssClass="redstar"/>
	                   &nbsp;<c:if test="${not generated}"><a href="#" onClick="displayCalendar(document.forms[0].toDt,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></c:if></td>
                 </tr>
                  
                 <tr bgcolor=#ffffff>
	                   <td class="TDShade"><f:message key="invoiceDate"/> : </td>
	                   <td class="TDShadeBlue">
	                  <form:input id="invoiceDt" cssClass="inputBox" cssStyle="width:70px;" path="invoiceDt" disabled="${generated}"/>
	                     <form:errors path="invoiceDt" cssClass="redstar"/>
	                   &nbsp;<c:if test="${not generated}"><a href="#" onClick="displayCalendar(document.forms[0].invoiceDt,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></c:if></td>
	                   <td class="TDShade" ><f:message key="paymentterms"/> : </td>
	                   <td colspan="2" class="TDShadeBlue" >
	                   <form:select id="sel4" cssClass="selectionBox" path="pymntTermsCd" items="${command.paymentTermsList}" itemLabel="name" itemValue="value" disabled="${generated}"/>
	                   </td>
                 </tr>
                  
                  <tr bgcolor=#ffffff>
	                    <td class="TDShade"><f:message key="remitTo"/>:</td>
	                    <td class="TDShadeBlue">
	                       <form:input id="remitto" cssClass="inputBox" maxlength="5" path="bankCd" disabled="${generated}"/>
	                          <form:errors path="bankCd" cssClass="redstar"/>
	                            <c:if test="${not generated}"><a href="#" onClick="javascript:showBankSearch('','${command.buName}','${command.currencyCd}');popup_show('searchremitto', 'searchremitto_drag', 'searchremitto_exit', 'screen-corner', 120, 20);hideIt();popupboxenable();showPopupDiv('searchremitto','remittobody');">
	                             <img src=" images/lookup.gif" width="13" alt="lookup remitTo" height="13" border="0"/></a></c:if>
	                     </td>
	                    
	                    <td class="TDShade" ><f:message key="bankAccount"/> : </td>
	                      <td colspan="2" class="TDShadeBlue" >
	 					    <form:input id="bankaccount" cssClass="inputBox" maxlength="5" path="bankAcctKey" disabled="${generated}"/>
	                            <form:errors path="bankAcctKey" cssClass="redstar"/>
	                               <c:if test="${not generated}"><a href="#" onClick="javascript:showBankAccountSearch('','${command.buName}','${command.currencyCd}');popup_show('searchaccount', 'searchaccount_drag', 'searchaccount_exit', 'screen-corner', 120, 20);hideIt();popupboxenable();showPopupDiv('searchaccount','accountbody');">
	                                <img src=" images/lookup.gif" width="13" alt="Lookup Account" height="13" border="0"/></a></c:if>
	                     </td>
                   </tr>
					</table>
					</td>
                  </tr>
                </tbody>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td>
                       
                        <a href="#" class="button1" style="text-decoration: none;color:black;"<c:if test="${generated}">disabled</c:if> onclick="javascript:if('${command.consolInvoiceNumber}'!=''){generateInvoice();}else{confirm('Please save first');}"><f:message key="generate"/></a>&nbsp;
                        
                        </td>
                        <td style="text-align:right">
                        
                        <a href="phx_search_cg_consolidate_invoice.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <c:if test="${not generated}"><a href="#"><input type="image" src="images/icosave.gif"  onClick="onSave()" alt="Save" width="14" height="14" border="0"  /></a></c:if></td>
                      </tr>
                    </table></td>
                </tr>
              </table>

              
            </div>
            <!----------------- TAB 1 CONTAINER END ------------------------------ -->
			
			
			<!-- ------------------------- TAB 2 CONTAINER ----------------------------------------- -->
            <div id="tab2" class="innercontent" >
            
              <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th width="50%" colspan="2"> <f:message key="invoice"/> : ${command.consolInvoiceNumber} </th>
                    <th width="40%" colspan="2" align="right" ><f:message key="pretaxAmt"/> :${command.preTaxAmount}&nbsp;${command.currencyCd } </th>
                    <th width="10%" bgcolor="#ffffff" style="text-align:right"><a href="phx_search_cg_consolidate_invoice.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <c:if test="${not generated}"><a href="#"><input type="image" src="images/icosave.gif"  onClick="onSave()" alt="Save" width="14" height="14" border="0"  /></a></c:if> </th>
                  </tr>
                  <tr>
                    <td colspan="5" style="padding:2px;">
					
					<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
					<tr>
					  <th colspan="4"><f:message key="billTo"/></th>
					</tr>
					<tr>
					  <td class="TDShade" width="20%"><f:message key="attentionTo"/> : </td>
					  <td class="TDShadeBlue">
					   <form:input id="attention" cssClass="inputBox" path="contactId" disabled="${generated}"/>
                     <form:errors path="contactId" cssClass="redstar"/>
					  &nbsp;
					  <c:if test="${not generated}"><a href="#" >
          			<img src="images/lookup.gif" alt="lookup contact" width="13" height="13" border="0" onClick="javascript:showContactLookup();"></a>
					 </c:if></td>
					  <td class="TDShade" width="20%"><f:message key="name"/> : </td>
					 <td class="TDShadeBlue"><span id="contactName"></span>${command.contactName}</td>
					</tr>
					<tr>
					  <td class="TDShade"><f:message key="location"/> : </td>
					  <td class="TDShadeBlue">
					   <form:input id="location" cssClass="inputBox" path="locationNumber" disabled="${generated}"/>
                     <form:errors path="locationNumber" cssClass="redstar"/>&nbsp;
					  <c:if test="${not generated}"><a href="#" >  
					  <img src="images/lookup.gif" alt="lookup location Number" width="13" height="13" border="0" onClick="javascript:showLocationLookup();"></a></c:if>
					  </td>
					  <td class="TDShade">&nbsp; </td>
					  <td class="TDShadeBlue">&nbsp;</td>
					</tr>
					<tr>
					  <td class="TDShade" height="22"><f:message key="country"/> : </td>
					  <td class="TDShadeBlue"><span id="country"></span>${command.country }</td>
					  <td class="TDShade"><f:message key="state"/> : </td>
					  <td class="TDShadeBlue"><span id="state"></span>${command.state }</td>
					</tr>
					<tr>
					  <td class="TDShade" height="22"><f:message key="address1"/> : </td>
					  <td class="TDShadeBlue"><span id="address1"></span>${command.address1 }</td>
					  <td class="TDShade"><f:message key="address2"/> : </td>
					  <td class="TDShadeBlue"><span id="address2"></span>${command.address2 }</td>
					</tr>
					<tr>
					  <td class="TDShade" height="22"><f:message key="address3"/> : </td>
					  <td class="TDShadeBlue"><span id="address3"></span>${command.address3 }</td>
					  <td class="TDShade"><f:message key="address4"/> : </td>
					  <td class="TDShadeBlue"><span id="address4"></span>${command.address4 }</td>
					</tr>
					<tr>
					  <td class="TDShade" height="22"><f:message key="city"/> : </td>
					  <td class="TDShadeBlue"><span id="city"></span>${command.city }</td>
					  <td class="TDShade"><f:message key="postal"/> : </td>
					  <td class="TDShadeBlue"><span id="postal"></span>${command.postal }</td>
					</tr>
					
					</table>	
					
					
					</td>
                  </tr>
                </tbody>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td>&nbsp;</td>
                        <td style="text-align:right"><a href="phx_search_cg_consolidate_invoice.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp<c:if test="${not generated}"> <a href="#"><input type="image" src="images/icosave.gif"  onClick="onSave()"  alt="Save" width="14" height="14" border="0"  /></a></c:if></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
              
            </div>
            <!----------------- TAB 2 CONTAINER END ------------------------------ -->
			
			
			<!-- ------------------------- TAB 3 CONTAINER ----------------------------------------- -->
            <div id="tab3" class="innercontent" >
            <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr>
                    <th width="50%" colspan="2"> <f:message key="invoice"/> : ${command.consolInvoiceNumber} </th>
                    <th width="40%" colspan="2" align="right" ><f:message key="pretaxAmt"/> : ${command.preTaxAmount}&nbsp;${command.currencyCd }</th>
                    <th width="10%" bgcolor="#ffffff" style="text-align:right"> <a href="phx_search_cg_consolidate_invoice.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <c:if test="${not generated}"><a href="#"><input type="image" src="images/icosave.gif" onClick="onSave()" alt="Save" width="14" height="14" border="0"/></a></c:if></th>
                  </tr>
                  <tr>
                    <td colspan="5" style="padding:2px;">
					
					<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
					<tr>
					  <th colspan="4"><f:message key="invoiceAttachDetach"/></th>
					</tr>
					<tr>
					  <td class="TDShade" colspan="4" style="margin-left:0px;margin-right:0px;padding-left:0px;padding-right:0px; text-align:right;">
					  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin:0px;width:100%;">
                              <tr>
                                <th style="text-align:center;"><f:message key="select"/></th>
                                <th style="text-align:center;"><a href="#start" onClick="sortByConsolInvColumnHeader('${command.searchInfo.billToField}');" ><f:message key="billTo"/></a></th>
                                <th style="text-align:center;"><a href="#start" onClick="sortByConsolInvColumnHeader('${command.searchInfo.buUnitNameField}');" ><f:message key="unit"/></a></th>
                                <th style="text-align:center;"><a href="#start" onClick="sortByConsolInvColumnHeader('${command.searchInfo.jobNumberField}');" ><f:message key="JobNumber"/></a></th>
                                <th style="text-align:center;"><a href="#start" onClick="sortByConsolInvColumnHeader('${command.searchInfo.consolInvoiceNoField}');" ><f:message key="consolInv"/></a></th>
                                <th style="text-align:center;"><a href="#start" onClick="sortByConsolInvColumnHeader('${command.searchInfo.invoiceField}');" ><f:message key="invoice"/></a></th>
                                <th style="text-align:center;"><a href="#start" onClick="sortByConsolInvColumnHeader('${command.searchInfo.InvoiceDateField}');" ><f:message key="invoiceDate"/></a></th>
                                <th style="text-align:center;"><a href="#start" onClick="sortByConsolInvColumnHeader('${command.searchInfo.invAmountField}');" ><f:message key="invoiceAmt"/></a></th>
                                <th style="text-align:center;"><a href="#start" onClick="sortByConsolInvColumnHeader('${command.searchInfo.currencyField}');" ><f:message key="currency"/></a></th>
                                <th style="text-align:center;"><a href="#start" onClick="sortByConsolInvColumnHeader('${command.searchInfo.vatNameField}');" ><f:message key="vatProvince"/></a></th>
                                <th style="text-align:center;">&nbsp;</th>
                              </tr>
                             
                            <tr>                              
							  <c:if test="${command.results != null}">
							  <div id="busearchresults"> 
							  <c:forEach items="${command.results}" var="ci" varStatus="status">
							  <tr>
							 <td align="center">
							  <c:choose>
							  <c:when test="${ci.consolInvoiceNo != ''}">
							  <input type="checkbox" id="chk${status.index }" name="chk${status.index }" value="${ci.invoiceId}" disabled>
							  </c:when>
							  <c:otherwise>
							  <input type="checkbox" id="chk${status.index }" name="chk${status.index }" value="${ci.invoiceId}" >
							  </c:otherwise>
							  </c:choose>
							 <%--  <c:choose>
							  <c:when test="${ci.consolInvoiceNo != ''}">
							     <c:choose>
							          <c:when test="${ci.consolInvoiceNo == command.consolInvoiceNumber}">
							           <input type="checkbox" id="chk${status.index }" name="chk${status.index }" value="${ci.invoiceId}" <c:if test="${ci.billStatus=='INV'}"> disabled</c:if>>
							           </c:when>
							           <c:otherwise>
							            <input type="checkbox" id="chk${status.index }" name="chk${status.index }" value="${ci.invoiceId}" disabled>
							           </c:otherwise>
							        </c:choose>							  
							  </c:when>
							  <c:otherwise>
							  <input type="checkbox" id="chk${status.index }" name="chk${status.index }" value="${ci.invoiceId}" >
							  </c:otherwise>
							  </c:choose>	--%>						  
							  </td>							 
							    
							    <td align='left' valign="middle" nowrap><span>&nbsp;${ci.billTo}</span></td>
							    <td align='left' valign="middle" nowrap><span>&nbsp;${ci.buUnitName}</span></td>
							    <td align='left' valign="middle" nowrap><span>&nbsp;${ci.jobNumber}</span></td>
							    <td align='left' valign="middle" nowrap><span>&nbsp;${ci.consolInvoiceNo}</span></td>
							    <td align='left' valign="middle" nowrap><span>&nbsp;${ci.invoice}</span></td>
							    <td align='left' valign="middle" nowrap><span>&nbsp;${ci.invoiceDate}</span></td>
							    <td align='left' valign="middle" nowrap><span>&nbsp;${ci.invoiceAmount}</span></td>
							    <td align='left' valign="middle" nowrap><span>&nbsp;${ci.currency}</span></td>
							    <td align='left' valign="middle" nowrap><span>&nbsp;${ci.vatState}</span></td> 						    
							    
							    </tr>
							   </c:forEach>
							  </div>
							  </c:if>
                            </table>
					      </td>
					    </tr>
					</table>
					</td>
                  </tr>
                </tbody>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><input type="button" class="button1" value="Attach" <c:if test="${generated}">disabled</c:if> onClick="attachInvoices(${fn:length(command.results)},'${command.vatCountryFlag}', '${command.vatProvince}')"/>&nbsp;&nbsp;
                        <input type="button" class="button1" value="Detach" <c:if test="${generated}">disabled</c:if> onClick="detachInvoices(${fn:length(command.results)})"/></td>
                        <td align="right" nowrap ><table cellspacing="0" cellpadding="0" border="0" style="text-align:right">
                          <tr>
						      <td><a href="#" onClick="prevSearch(pageNumber)"><IMG SRC="images/navfirst.gif" ALT="First Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
						      <td><a href="#" onClick="previousSearch('${command.pagination.currentPageNum}')">
						      <IMG SRC="images/navprevious.gif" ALT="Previous Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
						      <td><select name="goto" size="1" class="selectionBox" id="sel3" style="width:84px;" onchange="submitSearch(this.value)" >
						      <option value="Go to page"><c:out value="Go to page" />
					          <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
					          <option value="${page.pageNumber}"><c:out value="${page.name}" />
					           </c:forEach>
						      </select></td>
						      <td><a href="#" onClick="nextSearch('${command.pagination.currentPageNum}')"><IMG SRC="images/navnext.gif" ALT="Next Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
						      <td><a href="#" onClick="lastSearch('${command.pagination.totalRecord}','${command.pagination.numInPage}')"><IMG SRC="images/navlast.gif" ALT="Last Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
                          </tr>
                        </table></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
            </div> 
            <!----------------- TAB 3 CONTAINER END ------------------------------ -->
			 <!----------------- TAB 4 CONTAINER  ------------------------------ -->
        <div id="tab4" class="innercontent" >
          <c:if test ="${command.edit=='true'}">
              <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
                    <th width="50%" colspan="2"><f:message key="invoiceSummary"/><img src=" images/separator2.gif" width="2" height="27"
          			align="absmiddle" style="margin-left:5px;margin-right:5px;" />
          			<f:message key="unit"/> :${command.buName}<img src="images/separator1.gif" width="2" height="22" align="absmiddle" style="margin-left:5px; margin-right:5px;">
          			<f:message key="invoice"/> : ${command.consolInvoiceNumber}</th>
                    <th width="40%" colspan="2" align="right" ><f:message key="pretaxAmt"/> : ${command.preTaxAmount}&nbsp;${command.currencyCd }</th>
                    <th width="10%" bgcolor="#ffffff" style="text-align:right"><a href="phx_search_cg_consolidate_invoice.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <c:if test="${not generated}"> <a href="#"><input type="image" src="images/icosave.gif"  onClick="onSave()"  alt="Save" width="14" height="14" border="0"  /></a></c:if></th>
                  </tr>	
                  
                
                <tr>
                  <td colspan="2"  class="TDShade"><f:message key="company"/>:</td>
                  <td colspan="3"  class="TDShadeBlue">${command.name}</td>
                </tr>
                
                <tr>
                  <td colspan="2" class="TDShade"><f:message key="billingContact"/>: </td>
                  <td colspan="3" class="TDShadeBlue">${command.contactName}</td>
                </tr>
                
  				<tr>
                  <td colspan="2" class="TDShade"><f:message key="consolidatedInvoicePDF"/>: </td>
                  <td colspan="3" class="TDShadeBlue">&nbsp;</td>
                </tr>
                
                <tr>
                  <td colspan="5" class="TDShade" style="padding:2px;">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
                      <tr>
                        <th width="2%">&nbsp;</th>
                        <th><f:message key="invoice"/></th>
                        <th>&nbsp;</th>
                        <th><f:message key="status"/></th>
                        <th><f:message key="generatedOn"/> </th>
                        <th width="15%"><f:message key="generatedBy"/></th>
                        <th>&nbsp;</th>
                      </tr>
                        <c:if test="${generated}">
                        <tr>
                          <td class="TDShadeBlue">&nbsp;</td>
                          <td class="TDShadeBlue">${command.consolInvoiceNumber}</td>
	                          <td align="center" class="TDShadeBlue">
	                            <a href="invoice_view.htm?invoicetype=consol&invoice=${command.invoiceFileName}" target="_blank">
	                               <img src="images/icoviewinvoice.gif" alt="View Invoice" width="16" height="18" border="0">
	                            </a>
	                            <%--
	                            <c:forEach items="${command.consolidatedInvoice.consolInvoiceFiles}" var="invoiceFile" >
		                            <a href="invoice_view.htm?invoicetype=consol&invoice=${invoiceFile.invoiceFileName}" target="_blank">
		                                  <img src="images/icoviewinvoice.gif" alt="View ${invoiceFile.invoiceFileType.invoiceFileType} Invoice" width="16" height="18" border="0">
		                            </a>
		                            <input type="checkbox" name="countable" onchange="javascript:setCountable('${invoiceFile.id}' , this.checked);" <c:if test="${invoiceFile.countable}">checked</c:if> alt="countable"/>
	                            </c:forEach>
	                            --%>
	                            
	                            <c:if test="${not empty command.consolidatedInvoice.pdfGeneratedFlag && not command.consolidatedInvoice.pdfGeneratedFlag}">
	                              <a href="#" class="smallbutton" style="text-decoration: none;color:black;" onclick="javascript:regenerateInvoice();"><f:message key="regenerate"/></a>
	                            </c:if>
	                           </td>
                          <td class="TDShadeBlue"><f:message key="success"/></td>
                          <td class="TDShadeBlue">${command.consolidatedInvoice.generateTime} </td>
                          <td class="TDShadeBlue">${command.generatedByUserName}</td>
                          <td class="TDShadeBlue">&nbsp;</td>
                        </tr>
                       </c:if>
                    </table>
                  </td>
                </tr>
                  
                  
               </tbody>
               </table>
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
            <tr>
               <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td>&nbsp;</td>
                       <td style="text-align:right"><a href="phx_search_cg_consolidate_invoice.htm"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a>&nbsp <c:if test="${not generated}"><a href="#"><input type="image" src="images/icosave.gif" onClick="onSave()" alt="Save" width="14" height="14" border="0"  /></a></c:if></td>
                      </tr>
                    </table>
                 </td>
               </tr>
             </table>
          </c:if>
       </div>
            <!----------------- TAB 4 CONTAINER END ------------------------------ -->  
			
           </div>
        </div>
        <script type="text/javascript">
      	var pageNoVar = "${command.tabName}"
      	dolphintabs.init("tabnav", pageNoVar)      
      </script>   
        <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
      </div>
      <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
    </td>
  </tr>
</table>
<!-- ajax call for BankCode and Bank Account on change of Business Unit and currency-->
	<ajax:updateField baseUrl="phx_cg_consol_ajax.htm" source="sel3"
		target="remitto,bankaccount" action="sel3"
		parameters="currencyCd={currencyCd},buName={buName}"
		parser="new ResponseXmlParser()" />

	<ajax:updateField baseUrl="phx_cg_consol_ajax.htm" source="sel6"
		target="remitto,bankaccount" action="sel6"
		parameters="currencyCd={currencyCd},buName={buName}"
		parser="new ResponseXmlParser()" />

	<ajax:updateField baseUrl="phx_cg_consol_ajax.htm" source="remitto"
		target="bankaccount" action="remitto"
		parameters="currencyCd={currencyCd},buName={buName},bankCode={bankCd}"
		parser="new ResponseXmlParser()" />
</form:form>


<!------------------------------------------------Company Name Lookup--------------------------------------------->
<div class="sample_popup" id="companyname" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="companyname_drag" style="width:750px;height:auto;"> <img class="menu_form_exit"   id="companyname_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchCustomer"/></div>
<div class="menu_form_body" id="companynamebody" style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" id="searchParentCustomerFr" name="searchParentCustomerFr" allowtransparency="yes" src="blank.html"></iframe>    
</div></div>
<!-------------------------------------------Company Name Lookup END---------------------------------------------->

<!-- --------------------------- Parent Customer Lookup ------------------------------------------------- -->
<div class="sample_popup" id="parentcust" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="parentcust_drag" style="width:750px;;height:auto; "> <img class="menu_form_exit"   id="parentcust_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchCustomer"/></div>
<div class="menu_form_body" id="parentcustbody"    style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%"  id="searchParentCustomerFr" name="searchParentCustomerFr" allowtransparency="yes" src="blank.html"></iframe>    
</div></div>
<!-- --------------------------- Parent Customer Lookup End------------------------------------------------- -->

<!--------------------------------------------Search Bank Lookup-------------------------------------------------->
<div class="sample_popup"     id="searchremitto" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="searchremitto_drag" style="width:750px;height:auto;"> 
<img class="menu_form_exit"   id="searchremitto_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="remitToSearch"/></div><div class="menu_form_body" id="remittobody"   style="width:750px; height:500px;overflow-y:hidden;">
<table cellspacing="0" cellpadding="0" align="center" style="width:95%;" >
<tr><iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="blank.html" scrolling="auto" id="sremittoframe" name="sremittoframe" allowtransparency="yes" ></iframe>
</tr></table></div></div>
<!--------------------------------------------Search Bank Lookup End---------------------------------------------->
<!----------------------------------------------Search Account Lookup--------------------------------------------->
<div class="sample_popup" id="searchaccount" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="searchaccount_drag" style="width:750px;height:auto;"> 
  <img class="menu_form_exit"   id="searchaccount_exit" src="images/form_exit.png" border="0" />&nbsp;&nbsp;&nbsp;<f:message key="accountSearch"/> </div>
  <div class="menu_form_body" id="accountbody"   style="width:750px; height:530px;overflow-y:hidden;">
<table cellspacing="0" cellpadding="0" align="center" style="width:95%;" >
<tr><iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="blank.html" scrolling="auto" id="saccountframe" name="saccountframe" allowtransparency="yes"></iframe>
</tr></table></div></div>
<!---------------------------------------------Search Account Lookup End------------------------------------------>
<!------------------------- Search Contact  Popup Start--------------------------------------------------------->
<div class="sample_popup" id="contact" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="contact_drag" style="width:750px;;height:auto; ">
<img class="menu_form_exit"   id="contact_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchContact"/></div>
<div class="menu_form_body" id="contactbody"    style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%"  id="searchContactFr" name="searchContactFr" allowtransparency="yes" src="blank.html"></iframe>      
</div></div>
<!----------------------- Search Contact  Popup End--------------------------------------------------------->

<!------------------------------------------------------------------------ Search Address Sequence Popup ---------------------------------------------------------------------------------------->
<div class="sample_popup" id="addressseq" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="addressseq_drag" style="width:750px;height:auto;"> 
<img class="menu_form_exit"   id="addressseq_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchAddressSequence"/></div>                                                            
<div class="menu_form_body" id="addressseqbody"    style="width:750px; height:530px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%"  scrolling="auto" id="addressseqFr" name="addressseqFr" allowtransparency="yes" src="blank.html"></iframe>

</div>
</div>
<!---------------------------------------------------------------------------  Search Address Sequence Lookup End -------------------------------------------------------------------------------------->

