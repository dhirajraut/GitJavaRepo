<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions"%>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags"%>
<%@ taglib prefix="ajax" uri="http://ajaxtags.org/tags/ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script>
  function doSubmit() {
    debugger;
    document.jobOrderEditInvoicePreviewTab1Form.submit();
    document.jobOrderEditInvoicePreviewTab2Form.submit();

  }
  function onDeletePrebill(prebillId, contractCode) {
    top.document.forms[0].addOrDeletePrebill.value = "delete";
    top.document.forms[0].prebillId.value = prebillId;
    top.document.forms[0].contractCd.value = contractCode;
    top.document.forms[0].refreshing.value = "del";
    top.document.forms[0].submit();
  }
  function onAddPrebill(contractCode) {
    top.document.forms[0].addOrDeletePrebill.value = "add";
    top.document.forms[0].contractCd.value = contractCode;
    top.document.forms[0].refreshing.value = "add";
    top.document.forms[0].submit();
  }
</script>

<icb:list var="branch">
  <icb:item>${command.jobContract.jobOrder.branchName}</icb:item>
</icb:list>

<form:form name="jobOrderEditInvoicePreviewTab1Form" method="POST"
  action="edit_job_invoice_preview_tab1.htm">
  <input type="hidden" name="refreshing" value="true" />
  <input type="hidden" name="tabSrc" value="edit_job_invoice_preview_tab1.htm" />
  <table width="100%" cellpadding="0" cellspacing="0"
    class="InnerApplTable" style="border-left-width: 0px;">
    <tr>
      <th style="width: 125px;">&nbsp;</th>
      <th nowrap>&nbsp;</th>
      <th nowrap>&nbsp;</th>
      <th nowrap>&nbsp;</th>
      <th nowrap>&nbsp;</th>
      <th nowrap>&nbsp;</th>
      <th nowrap>&nbsp;</th>
      <th nowrap>&nbsp;</th>
      <th nowrap>&nbsp;</th>
    </tr>
    <tr>
      <td height="21" colspan="3" nowrap>
        <div align="right"></div>
      </td>
      <td height="21" nowrap>&nbsp;</td>
      <!-- <td height="21" nowrap>&nbsp;</td> add for quantity column -->
      <td height="21" nowrap>&nbsp;</td>
      <td height="21" nowrap>&nbsp;</td>
      <td height="21" nowrap>&nbsp;</td>
      <td height="21" nowrap>&nbsp;</td>
      <td height="21" nowrap>&nbsp;</td>
    </tr>
    <tr>
      <th style="width: 125px;"><f:message key="price" /></th>
      <th nowrap><f:message key="Split" /></th>
      <th nowrap><f:message key="dscnt" /></th>
      <th nowrap><f:message key="extPrice" /></th>
      <th nowrap><f:message key="primaryBranch" /></th>
      <th nowrap><f:message key="busStream" /></th>
      <th nowrap><f:message key="serviceType" /></th>
      <th nowrap><f:message key="comment" /></th>
      <th nowrap align="center">&nbsp;&nbsp;&nbsp;&nbsp; 
        <c:if test="${command.jobContract.jobContractStatus!='6000' && command.jobContract.jobContractStatus!='7200'}">
          <a href="#"> <!-- img src="images/add.gif" alt="Add" width="13" height="12" hspace="1px" border="0" onClick="onAddPrebill('${command.jobContract.contractCode }')"/ -->
          </a>
        </c:if>
      </th>
    </tr>

    <c:forEach items="${command.jobContract.prebills}" var="prebill" varStatus="prebillStatus">
      <tr valign='center'>
        <td height="25" align='left' nowrap>
            <c:set var="str" value="PB" />
	        <c:set var="strLineDesc" value="${prebill.lineDescription}" /> 
	        <c:choose>
	          <c:when test="${command.jobContract.jobContractStatus=='6000' || command.jobContract.jobContractStatus=='7200' }">
	            <form:input cssClass="inputBox"
	              path="jobContract.prebills[${prebillStatus.index}].unitPrice"
	              size="5" disabled="true" />
	          </c:when>
	          <c:otherwise>
	            <form:input cssClass="inputBox"
	              path="jobContract.prebills[${prebillStatus.index}].unitPrice"
	              size="5" disabled="${!prebill.editable}" />
	          </c:otherwise>
	        </c:choose> 
	        <form:errors path="jobContract.prebills[${prebillStatus.index}].unitPrice" cssClass="redstar" />
        </td>
        <td align='left' valign="middle">
	        <c:choose>
	          <c:when test="${command.jobContract.jobContractStatus=='6000' || command.jobContract.jobContractStatus=='7200'}">
	            <form:input cssClass="inputBox"
	              path="jobContract.prebills[${prebillStatus.index}].splitPct"
	              size="2" disabled="true" />
	          </c:when>
	          <c:otherwise>
	            <form:input cssClass="inputBox"
	              path="jobContract.prebills[${prebillStatus.index}].splitPct"
	              size="2" disabled="${!prebill.editable}" />
	          </c:otherwise>
	        </c:choose> 
	        <form:errors path="jobContract.prebills[${prebillStatus.index}].splitPct" cssClass="redstar" />
        </td>
        <td align='left' valign="middle" nowrap>
	        <c:choose>
	          <c:when test="${command.jobContract.jobContractStatus=='6000' || command.jobContract.jobContractStatus=='7200'}">
	            <form:input cssClass="inputBox"
	              path="jobContract.prebills[${prebillStatus.index}].discountPct"
	              size="2" disabled="true" />
	          </c:when>
	          <c:otherwise>
	            <form:input cssClass="inputBox"
	              path="jobContract.prebills[${prebillStatus.index}].discountPct"
	              size="2" disabled="${!prebill.editable}" />
	          </c:otherwise>
	        </c:choose> 
	        <form:errors path="jobContract.prebills[${prebillStatus.index}].discountPct" cssClass="redstar" />
	    </td>
        <td align='left' valign="middle">
	        <c:choose>
	          <c:when
	            test="${command.jobContract.jobContractStatus=='6000' || command.jobContract.jobContractStatus=='7200'}">
	            <form:input cssClass="inputBox"
	              path="jobContract.prebills[${prebillStatus.index}].netPrice"
	              size="6" disabled="true" />
	          </c:when>
	          <c:otherwise>
	            <form:input cssClass="inputBox"
	              path="jobContract.prebills[${prebillStatus.index}].netPrice"
	              size="6" disabled="${!prebill.editable}" />
	          </c:otherwise>
	        </c:choose> 
	        <form:errors path="jobContract.prebills[${prebillStatus.index}].netPrice" cssClass="redstar" />
	    </td>
        <td align='center' valign="middle">${prebill.primaryBranchCd}</td>
        <td>
	        <c:choose>
	          <c:when test="${command.jobContract.jobContractStatus=='6000' || command.jobContract.jobContractStatus=='7200'}"> 
	                ${prebill.busStreamCode}      
	          </c:when>
	          <c:otherwise>
	            <form:select cssClass="selectionBox" id="busstreamsel"
	              path="jobContract.prebills[${prebillStatus.index}].busStreamCode"
	              items="${icbfn:dropdown('busStream', branch)}" itemLabel="name"
	              itemValue="value" />
	          </c:otherwise>
	        </c:choose>
        </td>
        <td align='left' valign="middle">${prebill.serviceType}</td>
        <td align='left' valign="middle">
	        <c:choose>
	          <c:when test="${command.jobContract.jobContractStatus=='6000' || command.jobContract.jobContractStatus=='7200'}">
	            <form:input cssClass="inputBox"
	              path="jobContract.prebills[${prebillStatus.index}].comments"
	              size="60" maxlength="1024" disabled="true" htmlEscape="true" />
	          </c:when>
	          <c:otherwise>
	            <form:input cssClass="inputBox"
	              path="jobContract.prebills[${prebillStatus.index}].comments"
	              size="60" maxlength="1024" htmlEscape="true" />
	          </c:otherwise>
	        </c:choose>
        </td>
        <td align='left' valign="middle" nowrap='nowrap'>
	        <div id="tablefunction" style="width: 50px; text-align: center; margin-right: 0;">
	            <!--  
	                <a href="#">
	                  <img src="images/add.gif" alt="Add" width="13" height="12" hspace="1px" border="0"/>
	                </a> 
	            --> 
		        <c:if
		          test="${command.jobContract.jobContractStatus!='6000' && command.jobContract.jobContractStatus!='7200'}">
		          <a href="#"> 
		            <img src="images/delete.gif" alt="Delete" width="13" height="12" hspace="1px" border="0"
		                 onClick="onDeletePrebill('${prebill.id}','${command.jobContract.contractCode }')" />
		          </a>
		        </c:if>
	        </div>
        </td>
      </tr>
    </c:forEach>
    <tr valign='center'>
      <td height="26" align='left'>&nbsp;</td>
      <td align='left' valign="middle">&nbsp;</td>
      <td valign="middle" style="text-align: right"><f:message key="total" />:</td>
      <td align='left' valign="middle" nowrap>
        <div align="left">
          <form:input cssClass="inputBox" path="invoiceTotalAmt" disabled="true" size="8" />
        </div>
      </td>
      <td align='left' valign="middle">&nbsp;</td>
      <td align='left' valign="middle">&nbsp;</td>
    </tr>
  </table>
  <!-- -----------------------------------------Job Description Details --------------------------------- -->
  <div id="jobdescr" class="balloonstyle">
  <table width="210" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
    <tr>
      <td>Submitted Sample(s) and Sampling of gasoline Station at
      Varies Location of Prem Gasoline,3/15/2007 to 3/30/07 at Baltimore,
      MD, USA, Baltimore.</td>
    </tr>
  </table>
  </div>
  <!-- -------------------------- Job Description Details END ------------------------------------------ -->
</form:form>

