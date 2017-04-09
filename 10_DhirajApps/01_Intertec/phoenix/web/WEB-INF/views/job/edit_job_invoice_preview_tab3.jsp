<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

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

  
</script>

<form:form name="jobOrderEditInvoicePreviewTab3Form" method="POST" action="edit_job_invoice_preview_tab3.htm">
  <input type="hidden" name="refreshing" value="true" />
  <input type="hidden" name="tabSrc" value="edit_job_invoice_preview_tab3.htm" />
  <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style="border-left-width:0px">
    <tr>
      <th nowrap>&nbsp;</th>
      <th nowrap>&nbsp;</th>
      <th nowrap>&nbsp;</th>
      <th nowrap>&nbsp;</th>
      <th nowrap>&nbsp;</th>
      <th nowrap>&nbsp;</th>
      <th nowrap>&nbsp;</th>
      <th style="width:60px;" nowrap>&nbsp;</th>
    </tr>
    <tr valign='center'>
      <td height="21" align='left' valign="middle" >&nbsp;</td>
      <td align='left' valign="middle" nowrap >&nbsp;</td>
      <td valign="middle">&nbsp;</td>
      <td align='left' valign="middle" nowrap >&nbsp;</td>
      <td align='left' valign="middle" ><span >&nbsp;</span></td>
      <td align='left' valign="middle"  nowrap>&nbsp;</td>
      <td align='left' valign="middle"  nowrap>&nbsp;</td>
      <td align='left' valign="middle"  nowrap='nowrap'><div id="tablefunction" style="width:50px; text-align:center; margin-right:0;"> </div></td>
    </tr>
    <tr>
      <th nowrap><f:message key="vessel"/></th>
      <th nowrap><f:message key="product"/></th>
      <th nowrap><f:message key="account"/></th>
      <th nowrap><f:message key="productGroup"/></th>
      <th nowrap><f:message key="deptId"/></th>
      <th nowrap><f:message key="busStream"/></th>
      <th nowrap><f:message key="subBusStream"/></th>
      
      <%--
      added for the issue 126610
      --%>
      <th width="60" style="width:60px;" nowrap>&nbsp;&nbsp;&nbsp;&nbsp;</th>
      <%--
    	commented for the issue #126610 
    	
    	<th width="60" style="width:60px;" nowrap>&nbsp;&nbsp;&nbsp;&nbsp;
 		<c:if test="${command.jobContract.jobContractStatus!='6000' && command.jobContract.jobContractStatus!='7200'}">				
      		<a href="#">
             <img src="images/add.gif" alt="Add" width="13" height="12" hspace="1px" border="0" onClick="onAddPrebill('${command.jobContract.contractCode }')"/>
            </a>
		</c:if>
                
      </th>
    --%></tr>

<c:forEach items="${command.jobContract.prebills}" var="prebill" varStatus="prebillStatus">
          <tr valign='center'>
            <td height="25"  align='left' valign="middle" nowrap>${prebill.level0}</td>
            <td height="25" align='left' valign="middle" nowrap >${prebill.level1}</td>
            <td height="25" valign="middle" nowrap>${prebill.account}</td>
            <td height="25" align='left' valign="middle" nowrap >${prebill.productGroup}</td>
            <td height="25" align='left' valign="middle" nowrap><span >${prebill.deptid}</span></td>
            <td height="25" align='left' valign="middle"  nowrap>${prebill.busStreamCode}</td>
            <td height="25" align='left' valign="middle"  nowrap>&nbsp;</td>
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
            
            </div></td>
          </tr>
        </c:forEach>
    
    <tr valign='center'>
      <td height="26" align='left' valign="middle" >&nbsp;</td>
      <td align='left' valign="middle" >&nbsp;</td>
      <td valign="middle">&nbsp;</td>
      <td align='left' valign="middle" >&nbsp;</td>
      <td align='left' valign="middle" >&nbsp;</td>
      <td align='left' valign="middle"  nowrap>&nbsp;</td>
      <td align='left' valign="middle"  nowrap>&nbsp;</td>
      <td align='left' valign="middle"  nowrap='nowrap'>&nbsp;</td>
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
