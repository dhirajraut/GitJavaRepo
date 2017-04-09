<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions"%>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script language="javascript" src="js/po/create_po.js">
</script>

 
<form:form name="purchaseOrderCreateForm" method="POST" action="phx_create_purchase_order.htm">
  <form:hidden path="custFlag" />
  <form:hidden path="sortPODFlag"/>
  <form:hidden path="editJobNumber"/>

  <!-- --------------------------------- HEADER END ------------------------------------------------ -->
  <table width="87%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
    <tr>
      <td valign="top">
        <!-- MAIN CONTAINER -->
        <div id="MainContentContainer">
          <!-- TABS CONTENTS -->
          <div id="tabcontainer">
            <div id="tabnav">
              <ul>
                <li>
                  <c:choose>
                    <c:when test="${command.edit=='true'}">
                      <a href="#" rel="tab1">  
                        <span>
                          <f:message key="editPO"/> 
                        </span>  </a>
                    </c:when>
                    <c:otherwise>
                      <a href="#" rel="tab1">  
                        <span>
                          <f:message key="createPO"/> 
                        </span>  </a>
                    </c:otherwise>
                  </c:choose>
                </li>
              </ul>
            </div>
            <!-- Sub Menus container. Do not remove -->
            <div id="tab_inner">
              <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
              <div id="tab1" class="innercontent" >
              	<div class="redtext">
    			<form:errors cssClass="redstar"/>
  		</div>
                <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                  <tbody>
                
                    <tr>
                      <th colspan=4 width="95%">
                        <f:message key="poTitle"/>
                      </th>
                      <th width="35%" class="thr">
                        <a href="javascript:onSave()"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a>
                      </th>
                    </tr>
                
                    <tr>
                      <td nowrap class="TDShade"><strong><f:message key="businessUnit" />:</strong>
                        <span class="redstar">*</span> 
                      </td>
                      <td class="TDShadeBlue">
                        <span class="id_input">
                          <form:select id="sel3" cssClass="selectionBox" path="buName" items="${command.buNames}" itemLabel="name" itemValue="value"  onchange="makeBranchblank()" disabled="${command.disableEdit}"/>
                          <span class="redtext"><form:errors path="buName" cssClass="error"/></span>
                        </span>
                      </td>
                  
                      <td class="TDShade"><strong><f:message key="operationUnit" />:
                          <span class="redstar">*</span></strong></td>
                      <td colspan="2" class="TDShadeBlue">
                      <span class="id_input">
                        <form:select id="sel4" cssClass="selectionBox" path="branchCode" items="${command.branchNames}" itemLabel="name" itemValue="value" disabled="${command.disableEdit}"/>  
                        <span class="redtext"><form:errors path="branchCode" cssClass="error"/></span>
                        <span class="id_input">
                      </td>
                    </tr>
                    <tr>
                      <td width="15%" nowrap class="TDShade"><strong><f:message key="poNo" /> :</strong><span class="redstar">*</span>
                      </td>
                      <td width="35%" nowrap class="TDShadeBlue">
                        <form:input cssClass="inputBox" size="42"path="poNo" disabled="${command.disableEdit}"/>
                        <span class="redtext"><form:errors path="poNo" cssClass="error"/></span>
                      </td>
                      <td width="20%" nowrap class="TDShade"><strong><f:message key="expCcompleteDate" />:</strong></td>
                      <td colspan="2" width="35%" class="TDShadeBlue">
                        <form:input id="expCcompleteDate" cssClass="inputBox" path="expCompleteDate" onchange="selectJobDate('true','expCcompleteDate');"/>
                       <span class="redtext"><form:errors path="expCompleteDate" cssClass="error"/></span>
                        <a href="#" onClick="displayCalendar(document.forms[0].expCcompleteDate,'${command.dateFormat}',this)">
                          <img src="images/calendar.gif" width="15" height="17" align="absmiddle" border="0" /></a> 
                      </td>
                    </tr>
                    <tr>
                      <td nowrap class="TDShade">
                        <f:message key="customerId" />: <span class="redstar">*</span>
                      </td>
                      <td class="TDShadeBlue">
                        <form:input cssClass="inputBox" size="42" path="custCode" disabled="${command.disableEdit}"/>
                        <span class="redtext"><form:errors path="custCode" cssClass="error"/></span>
                  
                        <a href="#" onClick="javascript:showCustomerSearchLookup();">
                          <img src="images/lookup.gif" alt="customer Id" width="13" height="13" border="0">
                        </a>
                  
                      </td>
                      <td class="TDShade">
                        <f:message key="customerName" />: </td>
                      <td colspan="2" class="TDShadeBlue">
                        <form:input cssClass="inputBox" path="custName" size="42" disabled="true"/>
                        <span class="redtext"><form:errors path="custName" cssClass="error"/></span>
                      </td>
                    </tr>
                    <tr>
                      <td nowrap class="TDShade">
                        <f:message key="BeginDate" />: </td>
                      <td class="TDShadeBlue">
                        <form:input id="beginDate" cssClass="inputBox" path="beginDate" onchange="selectJobDate('true','beginDate');" disabled="${command.disableEdit}"/>
                        <span class="redtext"><form:errors path="beginDate" cssClass="erroe"/></span>
                        <c:choose>
                          <c:when test="${command.edit=='true'}">
                            <img src="images/calendar.gif" width="15" height="17" align="absmiddle" border="0" />
                          </c:when>
                          <c:otherwise>
                            <a href="#" onClick="displayCalendar(document.forms[0].beginDate,'${command.dateFormat}',this)">
                              <img src="images/calendar.gif" width="15" height="17" align="absmiddle" border="0" />
                            </a>
                          </c:otherwise>
                        </c:choose>
                      </td>
                      <td class="TDShade">
                        <f:message key="EndDate" />: </td>
                      <td colspan="2" class="TDShadeBlue">
                        <form:input id="endDate" cssClass="inputBox" path="endDate" onchange="selectJobDate('true','endDate');"/>
                        <span class="redtext"><form:errors path="endDate" cssClass="error"/></span>
                        <a href="#" onClick="displayCalendar(document.forms[0].endDate,'${command.dateFormat}',this)">
                          <img src="images/calendar.gif" width="15" height="17" align="absmiddle" border="0" /></a>
                      </td>
                    </tr>
                    <tr>
                      <td class="TDShade"><strong><f:message key="maxAmount" />:<span class="redstar">*</span></strong></td>
                      <td class="TDShadeBlue">
                        <form:input cssClass="inputBox" size="42" path="maxAmount" disabled="${command.disableEdit}"/>
                        <span class="redtext"><form:errors path="maxAmount" cssClass="error"/></span>
                      <td class="TDShade"><strong><f:message key="currency" />:</strong></td>
                      <td colspan="2" class="TDShadeBlue">
                        <form:select id="sel2" cssClass="selectionBox" path="currency" items="${command.currencyVal}" itemLabel="name" itemValue="value" disabled="${command.disableEdit}"/>
                      </td>
                    </tr>
                    <tr>
                      <td class="TDShade"><strong><f:message key="poName" />: </strong></td>
                      <td class="TDShadeBlue">
                        <form:input cssClass="inputBox" size="42" path="poName" disabled="${command.disableEdit}"/>
                        <form:errors path="poName" cssClass="redstar"/>
                      </td>
                      <td class="TDShade"><strong><f:message key="Description" />:</strong></td>
                      <td colspan="2" class="TDShadeBlue">
                        <form:textarea id="jobDesc" path="description" rows="2" cols="44"/> 
                        <form:errors path="description" cssClass="redstar" />
                      </td>
                    </tr>
                  </tbody>            
                  <tr>
                    <td colspan="5" class="TDShadeTopDottedline">             
                    </td> 
                  </tr>     
                </table>                	
	
                <br>	
	
                <!---- edit table starts here -->
	            <c:if test="${command.edit=='true'}">
                <c:forEach items="${command.poJobDetailForms}" var="poJobDetail" varStatus="status">
                  <!-- ------------------------ Job Id1 -------------------------------------------------- -->
                  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
                    <tr>
                      <th width="30" style="background-image:url(images/arrowblubg.gif);"> 
                        <div id="bluarrowright${status.index}" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#instructions" onClick="javascript:showjobid(${status.index});"> 
                            <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4" style="margin-top:2px;"/></a> 
                        </div>
                        <div id="bluarrowdown${status.index}" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#instructions" onClick="javascript:hidejobid(${status.index});"> 
                            <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> 
                        </div>
                      </th>
                      <th width="97%"><a name="instructions"></a>${poJobDetail.jobNumber}</th>
                    </tr>
                    <tr>
                      <td colspan="2" style="padding:0px;">
                        <div id="jobidContainers${status.index}" class="jobidContainers">
                          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable">	                
                            <tr>
                              <th width="12%">
                                <f:message key="orderLineNo"/>
                              </th>
                              <th width="28%">
                                <f:message key="description"/>
                              </th>
                              <th width="12%" align="center" style="text-align:center">
                                <f:message key="date"/>
                              </th>
                              <th width="14%" align="center" style="text-align:center">
                                <f:message key="invoiceNo"/>
                              </th>
                              <th width="14%" align="center" style="text-align:center">
                                <f:message key="amount"/>
                              </th>
                            </tr> 
                          <c:forEach items="${poJobDetail.ceJobLineItemForms}" var="jobLineItem" varStatus="status1">
                                <tr>
                                <td valign="top" rowspan="${fn:length(jobLineItem.iliForms)+1}">${jobLineItem.lineNumber}</td>
                                <td valign="top" rowspan="${fn:length(jobLineItem.iliForms)+1}">${jobLineItem.description}</td>
                                
                                <c:forEach items="${jobLineItem.iliForms}" var="invoiceLineItem" varStatus="status2">
                                <tr>
                                <td align="center">&nbsp;${invoiceLineItem.date}</td>
                                <td align="center">${invoiceLineItem.invoiceId}</td>
                                <td align="right" class="tdr">${invoiceLineItem.totalAmount}</td>
                                </tr>
                                </c:forEach>
                                </tr>                                                                         
                            </c:forEach>
                          </table>
                        </div>
                      </td>
                    </tr>
                  </table>
                  <!-- --------------------------------------- Job Id1 -------------------------------------- -->
                </c:forEach>
                <!-- ------------------------ Total Amount -------------------------------------------------- -->
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
                  <tr>
                    <th width="30" style="background-image:url(images/arrowblubg.gif);"> 
                      <div id="bluarrowrightTotal" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#instructions" onClick="javascript:showtotal();"> 
                          <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4" style="margin-top:2px;"/></a> 
                      </div>
                      <div id="bluarrowdownTotal" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#instructions" onClick="javascript:hidetotal();"> 
                          <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> 
                      </div>
                    </th>
                    <th width="97%"><a name="instructions"></a><f:message key="totalAndBalanceAmount"/>
                    </th>
                  </tr>
                  <tr>
                    <td colspan="2" style="padding:0px;">
                      <div id="totalContainers">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable">
                          <tr>
                            <td width="52%" colspan="4">&nbsp;</td>
                            <td width="14%" align="right">
                              <f:message key="poAmount"/>
                            </td>
                            <td width="14%" class="tdr">${command.maxAmount}</td>
                          </tr>
                          <tr>
                            <td colspan="4">&nbsp;</td>
                            <td align="right">
                              <f:message key="balanceCommitted"/>
                            </td>
                            <td class="tdr">${command.balanceCommitted}</td>
                          </tr>
                          <tr>
                            <td colspan="4">&nbsp;</td>
                            <td align="right">
                              <f:message key="totalInvoicedAmount"/>
                            </td>
                            <td class="tdr">${command.totalInvoicedAmount}</td>
                          </tr>
                          <tr>
                            <td colspan="4">&nbsp;</td>
                            <td align="right">
                              <f:message key="balance"/>
                            </td>
                            <td class="tdr">${command.balance}</td>
                          </tr>
                        </table>
                      </div>
                    </td>
                  </tr>
                </table>
                <!-- --------------------------------------- Total Amount -------------------------------------- -->
		
	            </c:if>
                <!---- edit table ends here -->		  
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                  <tr>
                    <td>
                      <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td><strong ><span class="redstar">*</span></strong> 
                            <span class="Font11pt">marked fields are mandatory</span> 
                          </td>
                          <td class="tdr">
                            <a href="javascript:onSave()"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a>
                          </td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                </table>
              </div>
              <ajax:autocomplete
               baseUrl="customer.htm"
               source="custCode"
               target="custCode"
               className="autocomplete"
               parameters="custCode={custCode},onlyCustCode=true"
               minimumCharacters="1" 
               postFunction="setCustomerSearchFlagAjax"
               />
              <!--   
               <ajax:select
               baseUrl="business_unit.htm"
               source="purchaseOrder.buName"
               target="purchaseOrder.branchCode"
               parameters="branch.businessUnit.name={purchaseOrder.buName}"
               parser="new ResponseXmlParser()"/>

               -->
              <!---------------------------------- Customer Id Sequence Popup --------------------------------------------------->
              <div class="sample_popup" id="customerid">
                <div class="menu_form_header" id="customerid_drag" style="width:750px;height:auto;"> 
                  <img class="menu_form_exit"   id="customerid_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchCustomers"/>
                </div>                                                           
                <div class="menu_form_body" id="customeridbody"   style="width:750px; height:530px;overflow-y:hidden;">
                  <iframe align="left" id="custIdLookup" frameborder="0" style="padding: 10px;"
                   height="530px;" width="100%" scrolling="auto" allowtransparency="yes"
                   src="blank.html"> 
                  </iframe>
                </div>
              </div>
              <!-- -------------- ------------------------------------------------------------ -->  

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

