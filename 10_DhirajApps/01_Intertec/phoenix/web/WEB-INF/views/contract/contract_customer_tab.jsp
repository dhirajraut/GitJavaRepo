<table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
  <tr>
    <th width="60%">
      <f:message key="contractid"/>: ${command.contract.contractCode}
      <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/>
      <f:message key="description"/> : ${command.contract.description} 
    </th>
    <th width="25%">
      <span style="text-align:right">
        <f:message key="status"/><strong>:</strong> 
        <f:message key="${command.contract.status}" /> | &nbsp;
        <f:formatDate pattern="${userDateFormat}" value="${command.contract.statusDate}" />
      </span>
    </th>
    <th width="15%" style="text-align:right">
      <c:if test="${command.contractSearch != null}">
        <a href="#" onClick="javascript:doOperation('searchContract');">
          <img src="images/icofindjob.gif" alt="Back to Search Contract" width="16" height="14" border="0" align="absmiddle">
        </a>&nbsp;&nbsp;
        <a href="#" onClick="javascript:doOperation('prevContract');">
          <img src="images/prevleftarrow.gif" alt="Go to Previous Contract" width="13" height="12" hspace="1px" border="0"/>
        </a> &nbsp;&nbsp;
        <a href="#" onClick="javascript:doOperation('nextContract');">
          <img src="images/nextrtarrow.gif" alt="Go to Next Contract" width="13" height="12" hspace="1px" border="0"/>
        </a> &nbsp;&nbsp;
      </c:if>
      <c:if test="${not command.viewOnly}">
        <a href="#"  onClick="resetform();doOperation('saveContract');">
          <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" />
        </a>
      </c:if>
    </th>
  </tr>
  <tr>
  <td colspan="3" style="padding:0px;">
  <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable" >
	  <tbody>
		<tr>
		  <th class="TDShade" style="background-image:url(images/arrowblubg.gif);" width="2%"> <div id="bluarrowrighcc1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showcontractcustTable();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div>
			<div id="bluarrowdowncc1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidecontractcustTable();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>
		  <th colspan="4"><a name="vessel1"></a>Search Criteria </td>
		</tr>
	  </tbody>
	</table>

  </td>
  </tr>
  <tr id="divcontractcustomers" style="visibility:hidden;display:none;">
  <td colspan="3" style="padding:0px;">
  		<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%">
        <tbody>
        	<tr>
				<td width="15%" class="TDShade"><f:message key="customerID"/> : 
				</td>
				 <td class="TDShadeBlue">
                    <form:select cssClass="selectionBox" path="customerSearchOperator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value"/>
                  </td>
				<td width="35%" class="TDShadeBlue">
                    <form:input size="15" cssClass="inputBox" path="searchCustomerId" />
                    <a href="#" onClick="updateCustomerSearchIframeSrc();popup_show('dsearchCustId', 'dsearchCustId_drag', 'dsearchCustId_exit', 'screen-corner', 120, 20); hideIt();popupboxenable();">
                     <img src="images/lookup.gif" alt="customer Id" width="13" height="13" border="0">
                    </a>
                </td>
                <td width="15%" class="TDShade"><f:message key="customerName"/>: </td>
                <td width="35%" class="TDShadeBlue">
                    <form:input size="15" cssClass="inputBox" path="searchCustomerName" />					                    
                 </td>
              </tr> 
              
        	<tr>
				<td width="15%" class="TDShade">Contact ID : 
				</td>
				 <td class="TDShadeBlue">
                    <form:select cssClass="selectionBox" path="contactSearchOperator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value"/>
                  </td>
				<td width="35%" class="TDShadeBlue">
                    <form:input size="15" cssClass="inputBox" path="searchContactId"  />
					<a href="#" onClick="updateContactSearchIframeSrc();popup_show('dsearchContactId', 'dsearchContactId_drag', 'dsearchContactId_exit', 'screen-corner', 120, 20); hideIt();popupboxenable();">
                     <img src="images/lookup.gif" alt="customer Id" width="13" height="13" border="0">
                    </a>                    
                </td>
                <td width="15%" class="TDShade">Contact Name: </td>
                <td width="35%" class="TDShadeBlue">
                    <form:input size="15" cssClass="inputBox" path="searchContactName" />
                 </td>
              </tr>
              <tr>
              <td colspan="6" class="TDShade"><input name="button" type="button" onClick="loadContractCustData()" class="button1" value="Show Results" /></td>			  
        	  </tr>
        </tbody>
       </table> 
  	</td>
  	  <ajax:autocomplete
                        baseUrl="customer.htm"
                        source="searchCustomerId"
                        target="searchCustomerId"
                        className="autocomplete" parameters="custCode={searchCustomerId},custContractFlag=true,contractCode=${command.contract.contractCode}"                        
                        minimumCharacters="1"
                        />
  	  <ajax:autocomplete
                        baseUrl="customer.htm"
                        source="searchContactId"
                        target="searchContactId"
                        className="autocomplete" parameters="contactId={searchContactId},custCode={searchCustomerId},contactCustContractFlag=true,contractCode=${command.contract.contractCode}"
                        minimumCharacters="1"
                        />                        
  </tr> 
  <tr>
    <td colspan="3" style="padding:2px;">
      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" border="0" style="width:100%">
        <tbody>
          <tr>
            <th><f:message key="contractCustomers"/></th>
            <th class="TDShade">&nbsp;</th>
            <th class="TDShade" style="text-align:right;" >
              <a href="#">
                <c:if test="${not command.viewOnly}">              
                  <img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0" onclick="javascript:onAdd()"/>
                </c:if>
              </a> 
            </th>
          </tr> 
          
          <icb:list var="activeStatusDropDownItems" items="${icbfn:dropdown('staticDropdown',activeStatus)}" />
          
          <c:forEach items="${command.addContractCusts}" var="arrContractCusts" varStatus="counter">
            <tr width="100%">
              <td width="100%" style="padding:0" colspan="3">
                <!-- -------------------- VESSEL 1 CONTAINER ----------------------- -->
                <div id="customeridContainer" class="customerid"  width="100%">
                  <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable" border="0" >
                    <tbody width="100%">
                      <tr width="100%">
                        <th class="TDShade" style="background-image:url(images/arrowblubg.gif);"> 
                          <div id="arrowrtc${counter.index+1}" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> 
                            <a href="#" onClick="javascript:showcontract01('contact${counter.index+1}Container','arrowdnc${counter.index+1}','arrowrtc${counter.index+1}');">
                              <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/>
                            </a> 
                          </div>
                          <div id="arrowdnc${counter.index+1}" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> 
                            <a href="#" onClick="javascript:hidecontract01('contact${counter.index+1}Container','arrowdnc${counter.index+1}','arrowrtc${counter.index+1}');"> 
                              <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/>
                            </a> 
                          </div>
                        </th>
                        <td  class="TDShade" ><f:message key="customerid"/> : </td>
                        <td  class="TDShadeBlue">
                          <form:input cssClass="inputBox" size="15" path="addContractCusts[${counter.index}].contractCust.contractCustId.custCode" disabled="${command.viewOnly}"/> 
                          <form:errors path="addContractCusts[${counter.index}].contractCust.contractCustId.custCode" cssClass="redstar"/>
                          <a href="#" onClick="javascript:setflagvalue(${counter.index});updateCustomerIframeSrc(${counter.index});popup_show('customerid${counter.index}', 'customerid_drag${counter.index}', 'customerid_exit${counter.index}', 'screen-corner', 120, 20); hideIt();popupboxenable();"> 
                            <img src="images/lookup.gif" alt="customer Id" width="13" height="13" border="0">
                          </a>
                        </td>
                        <td  class="TDShade" ><strong><f:message key="name"/>:</strong></td>
                        <td colspan="2"  class="TDShadeBlue">           
                          <form:input cssClass="inputBox" size="40" maxlength="40" path="addContractCusts[${counter.index}].contractCust.customer.name" disabled="true"/> 
                          <form:errors path="addContractCusts[${counter.index}].contractCust.customer.name" cssClass="redstar"/>
                        </td>
                        <td  class="TDShadeBlue"><f:message key="status"/>:</td>
                        <td  class="TDShadeBlue">
                          <span style="text-align:right">
                            <authz:authorize ifAnyGranted="ContractCustContStatus">
                                <form:select cssClass="selectionBox" path="addContractCusts[${counter.index}].contractCust.status" items="${activeStatusDropDownItems}" itemLabel="name" itemValue="value" />&nbsp;
                            </authz:authorize>
                            <authz:authorize ifNotGranted="ContractCustContStatus">
                                <form:select cssClass="selectionBox" path="addContractCusts[${counter.index}].contractCust.status" items="${activeStatusDropDownItems}" itemLabel="name" itemValue="value" disabled="true" />&nbsp;
                            </authz:authorize>
                          </span>
                        </td>
						<!-- For iTrack #103082 START -->
						<td class="TDShadeBlue" align="left">
						<c:choose>
						<c:when test="${command.customerNotes[counter.index] =='T'}"> 
						<a href="#" onClick="javascript:updateCustomerNotesIframeSrc(${counter.index},${command.addContractCusts[counter.index].contractCust.contractCustId.custCode});popup_show('viewCustomerNote${counter.index}', 'viewCustomerNote_drag${counter.index}', 'viewCustomerNote_exit${counter.index}', 'screen-corner', 120, 20); hideIt();showPopupDiv('viewCustomerNote${counter.index}','viewCustomerNotebody${counter.index}');popupboxenable();"> 
                            <img src=" images/icoaddnote.gif" alt="Notes" width="18" height="15" border="0" />
                          </a>
						</c:when>
						<c:otherwise>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</c:otherwise>
						</c:choose>
						</td>
						<!-- For iTrack #103082 END -->
                        <td  class="TDShadeBlue" align="left">
                          <c:if test="${not command.viewOnly}">
                            <img src="images/delete.gif" align="center" alt="Delete Row" hspace="1px" border="0" onclick="onDelete('${counter.index}')" />
                          </c:if>
                        </td>

<!---------------------------------- Customer Id Sequence Popup --------------------------------------------------->
<div class="sample_popup" id="customerid${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="customerid_drag${counter.index}" style="width:750px;height:auto;"> 
<a href="#" onclick="resetCustomer()"><img class="menu_form_exit"   id="customerid_exit${counter.index}" src="images/form_exit.png" /></a> &nbsp;&nbsp;&nbsp;<f:message key="searchCustomers"/>
</div>                                                           
<div class="menu_form_body" id="customeridbody${counter.index}"   style="width:750px; height:530px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" <%--src="search_customer_id_popup.htm?inputFieldId=addContractCusts[${counter.index}].contractCust.contractCustId.custCode&rowNum=${counter.index}&searchForm=contractEditForm&divName1=customerid${counter.index}&divName2=customeridbody${counter.index}" --%> scrolling="auto" id="customeridFr${counter.index}" name="customeridFr${counter.index}" allowtransparency="yes" ></iframe>
</div>
</div>
<!--------------------------------------- Customer Id Lookup End -------------------------------------------------->
<!---------------------------------- View CustomerNotes Popup START ---------------------------------------------------->
<div class="sample_popup" id="viewCustomerNote${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="viewCustomerNote_drag${counter.index}" style="width:800px;"> 
<img class="menu_form_exit"   id="viewCustomerNote_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="viewCustomerNotes"/></div>
<div class="menu_form_body" id="viewCustomerNotebody${counter.index}"  style="width:800px; height:350px;">
<iframe id="viewCustomerNotesFr${counter.index}" align="center" frameborder="0" style="padding:10px;" height="350px;" width="100%" name="viewCustomerNoteFr" allowtransparency="yes"></iframe>
</div>
</div>
<!-- -------------------------------- View CustomerNotes Popup END -----------------------------------------------------> 


                        <!-- -------------CONTRACT 1 END ----------------------- -->

                        <!-- ---------------------- Contract 1 Container END ------------------ -->                    

                        <ajax:autocomplete
                        baseUrl="customer.htm"
                        source="addContractCusts[${counter.index}].contractCust.contractCustId.custCode"
                        target="addContractCusts[${counter.index}].contractCust.customer.name"
                        className="autocomplete"              
                        parameters="custCode={addContractCusts[${counter.index}].contractCust.contractCustId.custCode}"
                        minimumCharacters="1"
                        />
                      </tr>
                    </tbody>
                  </table>  

                  <div id="contact${counter.index + 1}Container" class="contractContainer" align="right">
                    <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; ">
                      <tr>
                        <th colspan="6">Contacts </th>
                        <th align="right">
                          <c:if test="${not command.viewOnly}">
                            <a href="#"><img src="images/add.gif" align="right" alt="Add row" width="13" height="12" hspace="1px" border="0" onclick="javascript:onAddContact('${counter.index}')"/></a>              
                          </c:if>
                        </th>
                      </tr>
                      <c:forEach items="${arrContractCusts.contractCustContacts}" var="contractContacts" varStatus="contractcontactcounter">
                        <tr>                                            
                          <td>
                            <f:message key="contactID"/>:
                            <form:input cssClass="inputBox" size="15" path="addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].contractCustContactId.contactId" disabled="${command.viewOnly}"/>
                            <form:errors path="addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].contractCustContactId.contactId" cssClass="redstar"/>
                            <a href="#" onClick="javascript:setcontactflag(${counter.index},${contractcontactcounter.index});updateContactIframeSrc('${counter.index}','${contractcontactcounter.index}','${command.addContractCusts[counter.index].contractCust.contractCustId.custCode}');popup_show('contactid${counter.index}${contractcontactcounter.index}', 'contact_drag${counter.index}${contractcontactcounter.index}', 'contact_exit${counter.index}${contractcontactcounter.index}', 'screen-corner', 120, 20);popupboxenable();hideIt();hidecollectorsearch();">
                              <img src="images/lookup.gif" alt="contactID" width="13" height="13" border="0">
                            </a>
                          </td>
                          <td>
                            <a href="#"></a> 
                            <f:message key="name"/>: 
                            <form:input cssClass="inputBox" size="15"  path="addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].contact.firstName" disabled="true"/>
                            <form:errors path="addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].contact.firstName" cssClass="redstar"/>                     
                          </td>
                          <td>
                            <f:message key="status"/>:
                            <span style="text-align:right">
                              <authz:authorize ifAnyGranted="ContractCustContStatus">
                                  <form:select cssClass="selectionBox" id="statusval${counter.index}${contractcontactcounter.index}" path="addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].status" items="${activeStatusDropDownItems}" itemLabel="name" itemValue="value"/>
								 
                              </authz:authorize>
                              <authz:authorize ifNotGranted="ContractCustContStatus">
                                  <form:select cssClass="selectionBox" path="addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].status" items="${activeStatusDropDownItems}" itemLabel="name" itemValue="value" disabled="true"/>
                              </authz:authorize>
                            </span>
                          </td>

                          <td><f:message key="schedulerContact"/>:
                       <form:checkbox id="scont${counter.index}${contractcontactcounter.index}" path="addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].schedulerContactFlag" value="addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].schedulerContactFlag"/></td>

					   <td><f:message key="billingContact"/>:
                       <form:checkbox id="bcont${counter.index}${contractcontactcounter.index}" path="addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].billContactFlag" value="addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].billContactFlag" /></td>

					   <td><f:message key="reportingContact"/>:
                       <form:checkbox id="rcont${counter.index}${contractcontactcounter.index}" path="addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].reportContactFlag" value="addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].reportContactFlag" /></td>

                          <td width="10" align="right">
                            <c:if test="${not command.viewOnly}">
                              <div id="div2" style="text-align:center; width:10px;">
                                <a href="#"><img src="images/delete.gif" alt="Delete Row" hspace="4" border="0" onclick="onDeleteContact('${counter.index}','${contractcontactcounter.index}')" /></a>
                              </div>
                            </c:if>
                          </td>
                          <ajax:autocomplete
                            baseUrl="customer.htm"
                            source="addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].contractCustContactId.contactId"
                            target="addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].contact.firstName"
                            className="autocomplete"              
                            parameters="contactId={addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].contractCustContactId.contactId},custCode={addContractCusts[${counter.index}].contractCust.contractCustId.custCode}"
                            minimumCharacters="1"
                          />
                        </tr>
						<%-- For iTrack 135193-START --%>
						<tr>
						<td><f:message key="invoiceLabelLanguage"/>:&nbsp&nbsp&nbsp<form:select cssClass="selectionBox" id="sel10" path="addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].invoiceLabelLanguage" items="${icbfn:dropdown('staticDropdown',InvoiceLanguage)}" itemLabel="name" itemValue="value"/>
						<form:errors path="addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].invoiceLabelLanguage" cssClass="error"/></td>
						<td><f:message key="invoiceLineItemLanguage"/>:&nbsp&nbsp&nbsp<form:select cssClass="selectionBox" id="sel11" path="addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].invoiceLineItemLanguage" items="${icbfn:dropdown('staticDropdown',InvoiceLanguage)}" itemLabel="name" itemValue="value"/><form:errors path="addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].invoiceLineItemLanguage" cssClass="error"/></td>
					<td colspan="5"><f:message key="invoiceDeliveryMethod"/>:&nbsp&nbsp&nbsp
				    <form:select cssClass="selectionBox" id="sel8" path="addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].invoiceDeliveryMethod" items="${icbfn:dropdown('staticDropdown',invDeliveryMethod)}" itemLabel="name" itemValue="value"/><form:errors path="addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].invoiceDeliveryMethod" cssClass="error"/>
				    </td>
					</tr>
				   <%-- For iTrack 135193-END --%>
<div class="sample_popup" id="contactid${counter.index}${contractcontactcounter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="contact_drag${counter.index}${contractcontactcounter.index}" style="width:750px;height:auto; ">
<a href="#"  onclick="resetContact()"><img class="menu_form_exit"   id="contact_exit${counter.index}${contractcontactcounter.index}" src="images/form_exit.png" /></a> &nbsp;&nbsp;&nbsp;<f:message key="searchContact"/></div>
<div class="menu_form_body" id="contactbody${counter.index}${contractcontactcounter.index}"   style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" <%--src="search_contact_popup.htm?inputFieldId=addContractCusts[${counter.index}].contractCustContacts[${contractcontactcounter.index}].contractCustContactId.contactId&rowNum=${counter.index}&rowNumContact=${contractcontactcounter.index}&searchForm=contractEditForm&divName=contactid${counter.index}${contractcontactcounter.index}&divbody=contactbody${counter.index}${contractcontactcounter.index}&custCode=${command.addContractCusts[counter.index].contractCust.contractCustId.custCode}"--%> id="searchContactFr${counter.index}${contractcontactcounter.index}" name="searchContactFr${counter.index}${contractcontactcounter.index}" allowtransparency="yes"></iframe>
</div>
</div>
                      </c:forEach>
                    </table>
                  </div>
                </div>
              </td>
            </tr>
          <!-- -------------------- VESSEL 1 CONTAINER ----------------------- -->
          </c:forEach>

        </tbody>
      </table>  
    </td>
  </tr>
 <c:if test="${pagination.totalPages > 1}">
    <tr>
      <td>
        <table cellspacing="0" cellpadding="0" border="0">
          <tr>
			<td>
			<a href="#" onclick="javascript:pageNavigate('${command.contract.contractCode}','1');">
			<IMG SRC="images/navfirst.gif" ALT="First Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
            <td>
			<a href="#" onclick="javascript:pageNavigate('${command.contract.contractCode}','${pagination.currentPageNum - 1}');">
            <IMG SRC="images/navprevious.gif" ALT="Previous Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
            <td><select name="goto" size="1" class="selectionBox" id="sel3" style="width:84px;" onchange="javascript:pageNavigate('${command.contract.contractCode}',this.value);">
            <option value="Go to page"><c:out value="Go to page" />
            <c:forEach items="${pagination.pages}" var="page" varStatus="status">
            <option value="${page.pageNumber}"><c:out value="${page.name}" />
            </c:forEach>
            </select></td>
			<td>
			<c:choose>
			<c:when test="${pagination.currentPageNum == pagination.totalPages}"> 
			<a href="#" onclick="javascript:pageNavigate('${command.contract.contractCode}','${pagination.currentPageNum}');">
			<IMG  SRC="images/navnext.gif" ALT="Next Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a>
			</c:when>
			<c:otherwise>
			<a href="#" onclick="javascript:pageNavigate('${command.contract.contractCode}','${pagination.currentPageNum + 1}');">
			<IMG  SRC="images/navnext.gif" ALT="Next Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a>
			</c:otherwise>
            </c:choose>
			</td>
            <td>
			<a href="#" onclick="javascript:pageNavigate('${command.contract.contractCode}','${pagination.totalPages}');">
			<IMG SRC="images/navlast.gif" ALT="Last Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
         </tr>
      </table>
      </td>
    </tr>
  </c:if> 
  <tr>
    <th colspan="2">&nbsp;</th>
    <th width="15%" style="text-align:right">
      <c:if test="${command.contractSearch != null}">
        <a href="#" onClick="javascript:doOperation('searchContract');">
          <img src="images/icofindjob.gif" alt="Back to Search Contract" width="16" height="14" border="0" align="absmiddle">
        </a>&nbsp;&nbsp;
        <a href="#" onClick="javascript:doOperation('prevContract');">
          <img src="images/prevleftarrow.gif" alt="Go to Previous Contract" width="13" height="12" hspace="1px" border="0"/>
        </a> &nbsp;&nbsp;
        <a href="#" onClick="javascript:doOperation('nextContract');">
          <img src="images/nextrtarrow.gif" alt="Go to Next Contract" width="13" height="12" hspace="1px" border="0"/>
        </a> &nbsp;&nbsp;
      </c:if>
      <c:if test="${not command.viewOnly}">
        <a href="#"  onClick="resetform();doOperation('saveContract');">
          <img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" />
        </a>
      </c:if>
    </th>
  </tr>
</table> <!-- MainAppl Table end -->

    <!------------------------------------------------------- START: CUSTOMER ID SEARCH  ----------------------------------------->      
	<div class="sample_popup" id="dsearchCustId"  style="visibility: hidden; display: none;">
		<div class="menu_form_header" id="dsearchCustId_drag" style="width:750px;">
			<img class="menu_form_exit" id="dsearchCustId_exit" src="images/form_exit.png"/> &nbsp;&nbsp;&nbsp;Search Customer
		</div>
		<div class="menu_form_body" id="dsearchCustIdbody" style="width:750px; height:530px;padding-left:4px;overflow-y:hidden;">
			<iframe align="left" frameborder="0" style="padding:0px;" height="530px"  width="100%" src="blank.html" scrolling="auto" id="searchCustIdFr" name="searchCustIdFr" allowtransparency="yes"></iframe>
		</div>
	</div>
	<!------------------------------------------------------- END: CUSTOMER ID SEARCH ----------------------------------------->
	
	<!------------------------------------------------------- START: CONTACT ID SEARCH ----------------------------------------->      
	<div class="sample_popup" id="dsearchContactId"  style="visibility: hidden; display: none;">
		<div class="menu_form_header" id="dsearchContactId_drag" style="width:750px;">
			<img class="menu_form_exit" id="dsearchContactId_exit" src="images/form_exit.png"/> &nbsp;&nbsp;&nbsp;Search Contact
		</div>
		<div class="menu_form_body" id="dsearchContactIdbody" style="width:750px; height:530px;padding-left:4px;overflow-y:hidden;">
			<iframe align="left" frameborder="0" style="padding:0px;" height="530px"  width="100%" src="blank.html" scrolling="auto" id="searchContactIdFr" name="searchContactIdFr" allowtransparency="yes"></iframe>
		</div>
	</div>
	<!------------------------------------------------------- END: CONTACT ID SEARCH END ----------------------------------------->
