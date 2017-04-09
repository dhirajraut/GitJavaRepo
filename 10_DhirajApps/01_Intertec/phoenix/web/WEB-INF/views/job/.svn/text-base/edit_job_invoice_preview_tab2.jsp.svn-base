<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions"%>
<%@taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags"%>
<%@taglib prefix="ajax" uri="http://ajaxtags.org/tags/ajax"%> 


<script>
  function doSubmit()
  {
    debugger;
    document.jobOrderEditInvoicePreviewTab1Form.submit();
	document.jobOrderEditInvoicePreviewTab2Form.submit();
	
  }
   function onDeletePrebill(prebillId,contractCode)
  {
    top.document.forms[0].addOrDeletePrebill.value = "delete";
    top.document.forms[0].prebillId.value = prebillId;
    top.document.forms[0].contractCd.value = contractCode;
    top.document.forms[0].refreshing.value="del";
    top.document.forms[0].submit();

  }
   function onAddPrebill(contractCode)
  {
     top.document.forms[0].addOrDeletePrebill.value = "add";    
    top.document.forms[0].contractCd.value = contractCode;
    top.document.forms[0].refreshing.value="add";
    top.document.forms[0].submit();

  }  
  function onTaxCodeChange()
  {
	
  	top.document.forms[0].refreshing.value="calc";
    top.document.forms[0].tabSource.value="edit_job_invoice_preview_tab2.htm";
	document.jobOrderEditInvoicePreviewTab2Form.submit();  	
  	//top.document.forms[0].submit();
  }

  
</script>

<icb:list var="taxParams">
<icb:item>S</icb:item>
<icb:item>${command.jobOrder.branch.businessUnit.name}</icb:item>
  <icb:item>${command.jobOrder.serviceLocation.serviceLocationCode}</icb:item>
  <icb:item>${command.jobContract.custCode}</icb:item>
  <icb:item>${command.jobOrder.jobFinishDate}</icb:item>
  <icb:item>${command.jobContract.billingContact.id}</icb:item>
</icb:list>

<icb:list var="vatParams">
<icb:item>V</icb:item>
<icb:item>${command.jobOrder.branch.businessUnit.name}</icb:item>
  <icb:item>${command.jobOrder.serviceLocation.serviceLocationCode}</icb:item>
  <icb:item>${command.jobContract.custCode}</icb:item>
  <icb:item>${command.jobOrder.jobFinishDate}</icb:item>
  <icb:item>${command.jobContract.billingContact.id}</icb:item>
</icb:list>

<icb:list var="vatRegParams">
  <icb:item>${command.jobContract.custCode}</icb:item>
</icb:list>


<form:form name="jobOrderEditInvoicePreviewTab2Form" method="POST" action="edit_job_invoice_preview_tab2.htm">
  <input type="hidden" name="refreshing" value="true" />
  <input type="hidden" name="tabSrc" value="edit_job_invoice_preview_tab2.htm" />

  
  <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style="border-left-width:0px">
    <tr>
      <th nowrap><f:message key="taxCode"/></th>
      <th nowrap><f:message key="tax"/></th>
      <th nowrap><f:message key="belgiumTax"/></th>
      <th nowrap><f:message key="vatCode"/></th>
      <th nowrap><f:message key="vat"/></th>
      <th nowrap >&nbsp;</th>
      <th nowrap>&nbsp;</th>
      <th nowrap>&nbsp;</th>
      </tr>
    <tr>
      <td nowrap>
      
									<c:choose>
									<c:when test="${command.jobContract.jobContractStatus=='6000' || command.jobContract.jobContractStatus=='7200'}">				
									<form:select id="contractLvlTaxCode" cssClass="selectionBox" path="contractLvlTaxCode" items="${icbfn:dropdown('taxCodes',taxParams)}" itemLabel="name" itemValue="value" disabled="true"/>
									</c:when>
									<c:otherwise>
									<form:select id="contractLvlTaxCode" cssClass="selectionBox" path="contractLvlTaxCode" items="${icbfn:dropdown('taxCodes',taxParams)}" itemLabel="name" itemValue="value" onchange="javascript:onTaxCodeChange();"/>
									</c:otherwise>
									</c:choose>  
									      
      
      <form:errors path="contractLvlTaxCode" cssClass="error"/>
      </td>
	              <icb:list var="ctrTaxCodeList">
			<icb:item>${command.contractLvlTaxCode}</icb:item>
			<icb:item>S</icb:item>
			<icb:item>${command.jobOrder.jobNumber }</icb:item>
			</icb:list>	

      <td nowrap><form:select id="contractLvlTaxPct" cssClass="selectionBox" path="contractLvlTaxPct" items="${icbfn:dropdown('taxPct', ctrTaxCodeList)}" itemLabel="name" itemValue="value" disabled="true"/></td>
      <td nowrap>
	<c:choose>
	<c:when test="${command.jobContract.jobContractStatus=='6000' || command.jobContract.jobContractStatus=='7200'}">				
   <form:select id="taxArticleCode" cssClass="selectionBox" path="taxArticleCode" items="${icbfn:dropdown('taxArticles', null)}" itemLabel="name" itemValue="value" disabled="true"/>
   </c:when>
   <c:otherwise>
	<form:select id="taxArticleCode" cssClass="selectionBox" path="taxArticleCode" items="${icbfn:dropdown('taxArticles', null)}" itemLabel="name" itemValue="value"/>   
   </c:otherwise>
   </c:choose>
   </td>
      <td nowrap> 
									<c:choose>
									<c:when test="${command.jobContract.jobContractStatus=='6000' || command.jobContract.jobContractStatus=='7200'}">				
									<form:select id="contractLvlVatCode" cssClass="selectionBox" path="contractLvlVatCode" items="${icbfn:dropdown('taxCodes',vatParams)}" itemLabel="name" itemValue="value" disabled="true"/>
									</c:when>
									<c:otherwise>
									<form:select id="contractLvlVatCode" cssClass="selectionBox" path="contractLvlVatCode" items="${icbfn:dropdown('taxCodes',vatParams)}" itemLabel="name" itemValue="value" onchange="javascript:onTaxCodeChange();"/>
									</c:otherwise>
									</c:choose>        
      
      
      <form:errors path="contractLvlVatCode" cssClass="error"/>
	  </td>
	  <icb:list var="ctrVatCodeList">
			<icb:item>${command.contractLvlVatCode}</icb:item>
			<icb:item>V</icb:item>
			<icb:item>${command.jobOrder.jobNumber }</icb:item>
			</icb:list>
      <td nowrap ><form:select id="contractLvlVatPct" cssClass="selectionBox" path="contractLvlVatPct" items="${icbfn:dropdown('taxPct', ctrVatCodeList)}" itemLabel="name" itemValue="value" disabled="true"/></td>
     
	  <td nowrap >&nbsp; </td>

      <td nowrap><div align="right">&nbsp;</td>
      <td nowrap>&nbsp;</td>
     
<!--<ajax:select
            baseUrl="taxrate.htm"
            source="contractLvlTaxCode"
            target="contractLvlTaxPct"            
			parameters="taxcode={contractLvlTaxCode}"
            parser="new ResponseXmlParser()" />
            <ajax:select
            baseUrl="taxrate.htm"
            source="contractLvlVatCode"
            target="contractLvlVatPct"
            parameters="taxcode={contractLvlVatCode}"
            parser="new ResponseXmlParser()" />    -->        
    </tr>
    <tr>
      <th nowrap><f:message key="taxCode"/></th>
      <th nowrap><f:message key="tax"/></th>
      <th nowrap><f:message key="taxAmt"/> </th>
      <th nowrap><f:message key="vatCode"/></th>
      <th nowrap><f:message key="vat"/></th>
      <th nowrap><f:message key="vatAmt"/></th>
      <th nowrap><f:message key="netPrice"/></th>
      <%--
      added for the issue 126610
      --%>
      <th nowrap>&nbsp;&nbsp;&nbsp;&nbsp;</th>
      <%--
    	commented for the issue #126610 
      
      <th nowrap>&nbsp;&nbsp;&nbsp;&nbsp;
      	<c:if test="${command.jobContract.jobContractStatus!='6000' && command.jobContract.jobContractStatus!='7200'}">				
      <a href="#">
                  <img src="images/add.gif" alt="Add" width="13" height="12" hspace="1px" border="0" onClick="onAddPrebill('${command.jobContract.contractCode }')"/>
                </a>
	</c:if>
	</th>
	
    --%></tr>

 <c:forEach items="${command.jobContract.prebills}" var="prebill" varStatus="prebillStatus">
          <tr valign='center'>
            <td align='left' valign="middle" height="25">
            
									<c:choose>
									<c:when test="${command.jobContract.jobContractStatus=='6000' || command.jobContract.jobContractStatus=='7200'}">				
									<form:select id="sel1"  cssClass="selectionBox" path="jobContract.prebills[${prebillStatus.index}].taxCode" items="${icbfn:dropdown('taxCodes',taxParams)}" itemLabel="name" itemValue="value" disabled="true"/>
									</c:when>
									<c:otherwise>
									<form:select id="sel1"  cssClass="selectionBox" path="jobContract.prebills[${prebillStatus.index}].taxCode" items="${icbfn:dropdown('taxCodes',taxParams)}" itemLabel="name" itemValue="value" />
									</c:otherwise>
									</c:choose>  
									            

      		<form:errors path="jobContract.prebills[${prebillStatus.index}].taxCode" cssClass="error"/>
      	
            </td>
            <td align='left' valign="middle" ><span >&nbsp;
            <icb:list var="taxCodeList">
			<icb:item>${prebill.taxCode}</icb:item>
			<icb:item>S</icb:item>
			<icb:item>${command.jobOrder.jobNumber }</icb:item>
			</icb:list>	
            <form:select id="sel4" cssClass="selectionBox" path="jobContract.prebills[${prebillStatus.index}].taxPct" items="${icbfn:dropdown('taxPct', taxCodeList)}" itemLabel="name" itemValue="value" disabled="true"/>
			<form:errors path="jobContract.prebills[${prebillStatus.index}].taxPct" cssClass="error"/>
            </span>
            </td>
            <td align='left' valign="middle" >
			<form:input cssClass="inputBox" path="jobContract.prebills[${prebillStatus.index}].taxAmt" disabled="true" size="6"/>
			<form:errors path="jobContract.prebills[${prebillStatus.index}].taxAmt" cssClass="error"/>
            </td>
            <td align='left' valign="middle" >
            
									<c:choose>
									<c:when test="${command.jobContract.jobContractStatus=='6000' || command.jobContract.jobContractStatus=='7200'}">				
									<form:select id="sel2"  cssClass="selectionBox" path="jobContract.prebills[${prebillStatus.index}].vatCode" items="${icbfn:dropdown('taxCodes',vatParams)}" itemLabel="name" itemValue="value" disabled="true" />
									</c:when>
									<c:otherwise>
									<form:select id="sel2"  cssClass="selectionBox" path="jobContract.prebills[${prebillStatus.index}].vatCode" items="${icbfn:dropdown('taxCodes',vatParams)}" itemLabel="name" itemValue="value" />
									</c:otherwise>
									</c:choose>  
									            
            
      		<form:errors path="jobContract.prebills[${prebillStatus.index}].vatCode" cssClass="error"/>
			</td>
            <td align='center' valign="middle"  nowrap='nowrap'>
            <icb:list var="vatCodeList">
			<icb:item>${prebill.vatCode}</icb:item>
			<icb:item>V</icb:item>
			<icb:item>${command.jobOrder.jobNumber }</icb:item>
			</icb:list>
			<form:select id="sel4" cssClass="selectionBox" path="jobContract.prebills[${prebillStatus.index}].vatPct" items="${icbfn:dropdown('taxPct', vatCodeList)}" itemLabel="name" itemValue="value" disabled="true"/>
			<form:errors path="jobContract.prebills[${prebillStatus.index}].vatPct" cssClass="error"/>
			</td>
            <td align='left' valign="middle" nowrap >
			<form:input cssClass="inputBox" path="jobContract.prebills[${prebillStatus.index}].vatAmt" disabled="true" size="6"/>
			<form:errors path="jobContract.prebills[${prebillStatus.index}].vatAmt" cssClass="error"/>
			</td>
			<td align='left' valign="middle"  nowrap='nowrap'>
			<f:formatNumber type="number" maxFractionDigits="2"
            value="${prebill.netPrice + prebill.taxAmt + prebill.vatAmt}" />
			 
			</td>
            <td align='left' valign="middle"  nowrap='nowrap'>
            <div id="tablefunction" style="width:50px; text-align:center; margin-right:0;"> 
            <!-- <a href="#">
            <img src="images/add.gif" alt="Add" width="13" height="12" hspace="1px" border="0"/>
            </a>  -->
	<c:if test="${command.jobContract.jobContractStatus!='6000' && command.jobContract.jobContractStatus!='7200'}">				
      <a href="#">
                  <img src="images/delete.gif" alt="Delete" width="13" height="12" hspace="1px" border="0" onClick="onDeletePrebill('${prebill.id}','${command.jobContract.contractCode }')"/>
                </a>
	</c:if>   
            
            </div>
            </td>
            <ajax:select
            baseUrl="taxrate.htm"
            source="jobContract.prebills[${prebillStatus.index}].taxCode"
            target="jobContract.prebills[${prebillStatus.index}].taxPct"
            parameters="taxcode={jobContract.prebills[${prebillStatus.index}].taxCode},taxType=S,jobNumber=${command.jobOrder.jobNumber}"
            parser="new ResponseXmlParser()" />
            <ajax:select
            baseUrl="taxrate.htm"
            source="jobContract.prebills[${prebillStatus.index}].vatCode"
            target="jobContract.prebills[${prebillStatus.index}].vatPct"
            parameters="taxcode={jobContract.prebills[${prebillStatus.index}].vatCode},taxType=V,jobNumber=${command.jobOrder.jobNumber}"
            parser="new ResponseXmlParser()" />            
          </tr>
        </c:forEach>
  <tr valign='center'>
      <td height="26" align='right' valign="middle">
		<f:message key="vatRegId"/>: 
		<c:choose>
		<c:when test="${command.jobContract.jobContractStatus=='6000' || command.jobContract.jobContractStatus=='7200'}">				      
		<form:select id="sel4" cssClass="selectionBox" path="jobContract.vatRegId" items="${icbfn:dropdown('vatRegIds', vatRegParams)}" itemLabel="name" itemValue="value" disabled="true"/>
		</c:when>
		<c:otherwise>
		<form:select id="sel4" cssClass="selectionBox" path="jobContract.vatRegId" items="${icbfn:dropdown('vatRegIds', vatRegParams)}" itemLabel="name" itemValue="value"/>
		</c:otherwise>
		</c:choose>
		<form:errors path="jobContract.vatRegId" cssClass="error"/>
	</td>
    <td align='left' valign="middle" ></td>
    <td align='left' valign="middle"><form:input cssClass="inputBox" path="taxTotalAmt"  disabled="true" size="6"/></td>
    <td align='left' valign="middle" nowrap ></td>
    <td align='left' valign="middle" nowrap >&nbsp;</td>
    <td align='left' valign="middle" ><form:input cssClass="inputBox" path="vatTotalAmt"  disabled="true" size="6"/></td>
    <td align='left' valign="middle" ><f:formatNumber type="number" maxFractionDigits="2" value="${command.taxTotalAmt + command.vatTotalAmt + command.invoiceTotalAmt}" /></td>
    <td align='left' valign="middle" nowrap >&nbsp;</td>
  </tr>     

  </table>
  <!-- -----------------------------------------Job Description Details --------------------------------- -->
  <div id="jobdescr" class="balloonstyle">
    <table width="210" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
      <tr>
        <td>Submitted Sample(s) and Sampling of gasoline Station at Varies Location of Prem Gasoline,3/15/2007 to 3/30/07 at Baltimore, MD, USA, Baltimore.</td>
      </tr>
    </table>
  </div>
  <!-- -------------------------- Job Description Details END ------------------------------------------ -->
</form:form>

