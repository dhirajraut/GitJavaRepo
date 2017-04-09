<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<script type="text/javascript" src="js/ce/ce_add_test_popup.js"></script>

<form:form name="addTestForm" method="POST" action="phx_add_test.htm">
	<form:hidden path="operation"/>
   <form:hidden path="searchType"/>
	<form:hidden path="searchForm"/>
	<form:hidden path="inputFieldId"/>
	<form:hidden path="rowNum"/>
	<form:hidden path="chosenContracts"/>
	<form:hidden path="chosenTestIds"/>
	<form:hidden path="divName1"/>
	<form:hidden path="divName2"/>
	<form:hidden path="submitFlag"/>
	<!-- form:hidden path="pageNumber"/ -->
	<form:hidden path="sortFlag"/>	 
	 <input type="hidden" name="pageNumber" value="1" />
	<div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
		<form:errors cssClass="error"/>
	</div>

      
    
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="mainApplTable" style="margin-bottom:10px;">
      
        <tr>
          <td nowrap><strong><f:message key="selectGroup"/>:</strong></td>
          
          <td>         
          <form:select id="sel1" cssClass="selectionBox" path="productGroup" items="${command.productGroups}" itemLabel="name" itemValue="value" /> 
		  &nbsp;</td>
          
          <td nowrap> <form:radiobutton path="criteria1" value="PB"/> <f:message key="statusPBK"/>&nbsp;</td>
          
          <td nowrap><form:radiobutton path="criteria1" value="CONTR"/><f:message key="contract"/>&nbsp;&nbsp;</td>
		  
		  <td nowrap>
            <form:select id="sel2" cssClass="selectionBox" path="jobContractId" items="${command.jobContracts}" itemLabel="name" itemValue="value" />&nbsp;&nbsp; 
          </td>
          
          <td nowrap> <form:radiobutton path="criteria1" value="BOTH"/> <f:message key="both"/></td>
        </tr>
        
        
        <tr>
          <td><strong><f:message key="searchFor"/> : </strong></td>
          <td>
            <form:input cssClass="inputBox" size="25" path="testSearchVal"/>
		    <form:errors path="testSearchVal" cssClass="redstar"/>&nbsp;&nbsp;<f:message key="in"/>&nbsp;&nbsp;</td>
          
          <td nowrap>
          <form:radiobutton path="criteria2" value="ID"/> <f:message key="methodology"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
          
          <td nowrap><form:radiobutton path="criteria2" value="DESC"/><f:message key="description"/></td>
          
			<td>&nbsp;&nbsp;</td>
			
          <td nowrap><form:radiobutton path="criteria2" value="BOTH"/>
          
          <f:message key="both"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="5">
          	<input name="button" type="button" class="button1" onClick="searchTests()" value="<f:message key="search"/>"  />
            &nbsp;&nbsp;
            <input name="Button" type="button" class="button1" value="Cancel" onClick="top.hidePopupDiv('addTest','testbody');top.popupboxclose();" /></td>
        </tr>
	    </table>	    

    
    
	
	<c:if test="${command.results != null}">       
	  <div id="testssearchresults"><!--Search Results -->
	  <br>&nbsp;&nbsp;<strong><f:message key="searchResults"/>(<span style="color:green;"><f:message key="numberoftestsreturnedislimitedto300"/></span>)</strong>

	  <table width="100%" align="center" cellpadding="0" cellspacing="0">
	  		<tr>
          <td colspan="4">
          
          <input name="Button" type="button" class="button1" value="Add Test" onClick="addTest('addTest',${fn:length(command.results)})"/>&nbsp;
           <input name="Button" type="button" class="button1" value="Return" onClick="addTest('return',${fn:length(command.results)})"/>&nbsp;
          <input name="Button" type="button" class="button1" value="Add and Go To Summary"  onClick="addTest('addTestReturn',${fn:length(command.results)})"/>&nbsp;
          &nbsp;
          <a href="phx_add_manual_test.htm?div1=${command.divName1 }&div2=${ command.divName2}&manualTest=true"><f:message key="addManualTest"/></a>&nbsp;
          <input name="Button" type="button" class="button1" value="Report Missing Test" onClick="javascript:top.hidePopupDiv('addTest','testbody');top.popupboxclose();"/></td>
        </tr>
	  	        <tr>
          <th><a href="#start" onClick="sortTestSearcById();" ><f:message key="methodology"/></a></th>
          <th><a href="#start" onClick="sortTestSearcByDescription();" ><f:message key="description"/></a></th>
          <th><f:message key="quantity"/></th>          
		  <th><a href="#start" onClick="sortTestSearcByContractRef();" ><f:message key="pB/CONT"/></a></th>
          </tr>

          
        <tr>
          <td colspan="4"><a href="#" onClick="selectAllTests(${fn:length(command.results)})"><f:message key="checkAll"/></a>&nbsp;&nbsp;
          <a href="#" onClick="clearAllTests(${fn:length(command.results)})"><f:message key="clearAll"/></a></td>
          </tr>
          <c:forEach items="${command.results}" var="row" varStatus="counter">
			  <c:choose>
				<c:when test="${counter.index%2==0}">
				   <tr style="background-color:#FFFFFF;">
				</c:when>
				<c:otherwise>
				  <tr style="background-color:#e7eeff;">                    
				</c:otherwise>
		  </c:choose>
		  
	          <td>
	          	<form:checkbox id="chk${counter.index}" path="results[${counter.index}].selected" />
	          	${row.test.testId}
	          </td>
	          <td>${row.test.testDescription }</td>		
	          <td>
              <form:input path="results[${counter.index}].qty" size="10"/>
              <form:errors path="results[${counter.index}].qty" cssClass="redstar"/>
            </td>	 
			  <td>&nbsp;</td>
		  </tr>
		</c:forEach>
		

        <tr>
          <td colspan="4" height="26"><f:message key="selectanaction"/> :</td>
        </tr>
		
		<tr>
          <td colspan="4">
          
          <input name="Button" type="button" class="button1" value="Add Test" onClick="addTest('addTest',${fn:length(command.results)})"/>&nbsp;
           <input name="Button" type="button" class="button1" value="Return" onClick="addTest('return',${fn:length(command.results)})"/>&nbsp;
          <input name="Button" type="button" class="button1" value="Add and Go To Summary"  onClick="addTest('addTestReturn',${fn:length(command.results)})"/>&nbsp;
          &nbsp;
          <a href="phx_add_manual_test.htm?div1=${command.divName1 }&div2=${ command.divName2}&manualTest=true"><f:message key="addManualTest"/></a>&nbsp;
          <input name="Button" type="button" class="button1" value="Report Missing Test" onClick="javascript:top.hidePopupDiv('addTest','testbody');top.popupboxclose();"/></td>
        </tr>
	 	
	 	<tr>
		 <br><br>	
		 <td>&nbsp;</td>
	 	 <td align="center"></td>
	    </tr>
	              
      </table>
      
      <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
		<tr>
	    	<td><f:message key="totalRecords"/>: ${command.pagination.totalRecord}</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td align="center">
				<c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
					<c:choose>
						<c:when test="${command.pagination.currentPageNum == page.pageNumber}"><font color="red">${page.name}</font>&nbsp;&nbsp;</c:when>
						<c:otherwise>
							<a href="#start" onClick="doSearch(this, ${page.pageNumber})">${page.name}</a>&nbsp;&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</td>
		</tr>
 	  </table>
      
</c:if>

</form:form>

<SCRIPT>
	refreshParent();
</SCRIPT>

<div id="faderPane" style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left : 1px ;Top : 1px ;">
<IMG src=" images/fake-alpha-999.gif"> </div>