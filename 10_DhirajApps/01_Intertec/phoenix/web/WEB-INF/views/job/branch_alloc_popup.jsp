<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<script language="javascript">
  function onAddPrebillSplit()
  {
  
    document.branchAllocPopupForm.addOrDeletePrebillSplit.value = "add";
    document.branchAllocPopupForm.submit();
  }
  function onDeletePrebillSplit(index)
  {
  document.branchAllocPopupForm.addOrDeletePrebillSplit.value = "delete";
    document.branchAllocPopupForm.prebillSplitIndex.value = index;
    document.branchAllocPopupForm.submit();

  }
  function submitform()
  {
  document.branchAllocPopupForm.submit();
  }
  /*function updateBranchIframeSrc(inputFieldName,buname)
  {
  
  confirm(inputFieldName);
  document.getElementById('jobbu').setAttribute("src",src="search_branch_popup.htm?inputFieldId="+inputFieldName+"&div1=jobbranchcode&div2=jobbranchcodebody&formName=jobsForm");
  
  }*/
  function updatePctAlloc()
  {
  	document.branchAllocPopupForm.updatePctAllocFlag.value = "update";
    document.branchAllocPopupForm.submit();
  }  
  /*function populateBranchDesc()
  {
  	document.branchAllocPopupForm.updateBranchDescFlag.value = "update";
    document.branchAllocPopupForm.submit();
  }*/
  
</script>
<icb:list var="divisionBu">
  <icb:item>${command.orgName}</icb:item>
  <icb:item>${command.buName}</icb:item>
</icb:list>
<form:form name="branchAllocPopupForm" method="POST" action="branch_alloc_popup.htm">

     <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
        <form:errors cssClass="error"/>
      </div>


  
     <div style="width:auto;float:left;padding:5 5 5 40px;color:blue;">
        ${command.warnMsg }
      </div>   

 		

      <form:hidden path="addOrDeletePrebillSplit"/>
      <form:hidden path="prebillSplitIndex"/>
      <form:hidden path="updatePctAllocFlag"/>
      <!-- form:hidden path="updateBranchDescFlag"/ -->
      <form:hidden path="contractIndex"/>
     
<!-- --------------------------- Branch Allocation Lookup Start ------------------------------------------------- -->

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" >  
  <tr>
    <th width="23%" style="text-align:center" nowrap>Branch Code </th>
    <th width="7%" style="text-align:center" nowrap>Allocation % </th>
    <th width="23%" style="text-align:center" nowrap>Updated By </th>
    <th width="10%" style="text-align:center" nowrap>Updated On</th>&nbsp;&nbsp;&nbsp;
    <!-- <th width="3%" style="text-align:center"><a href="#start"><img src="images/add.gif" alt="Add Branch" width="13" height="12" hspace="1px" border="0" onClick="onAddPrebillSplit()"/></a> </th> -->
  <!--START: To fix issue  109072 -->
    <th width="3%" style="text-align:center">
	    <c:if test="${command.disableEditBrachAlloc == false}" >
		<a href="#start"><img src="images/add.gif" alt="Add Branch" width="13" height="12" hspace="1px" border="0" onClick="onAddPrebillSplit()"/></a> 
        </c:if>
	    <c:if test="${command.disableEditBrachAlloc == true}" >
		&nbsp;
        </c:if>
	</th>

    <!--END: To fix issue  109072 -->
  </tr>
  <tr>
  <td colspan="6">
  <div style="width:auto;float:left;padding:5 5 5 40px;color:blue;">
        <f:message key="applicableBranchMsg"/>
       </div>
  </td>
  </tr>
  <c:set var="str" value="${command.prebill.jobContract.jobOrder.branchName}" />   
          

                    
          <c:forEach items="${command.prebillSplits}" var="prebillSplit" varStatus="prebillSplitCounter">     
          <c:if test="${command.prebillSplits[prebillSplitCounter.index].branchName == str}"> 
           <tr>
            <td class="TDShadeBlue" width="23%" nowrap><span class="redstar">*</span>
            <!-- 
            <form:input id="branch${prebillSplitCounter.index}" cssClass="inputBox" size="45" cssStyle="text-align:center; background-color:#d2e1ff; color:#000099;" path="prebillSplits[${prebillSplitCounter.index}].branchName" disabled="${command.disabledFlag}"/>            
            -->
            <form:select id="branch${prebillSplitCounter.index}" cssClass="selectionBox" path="prebillSplits[${prebillSplitCounter.index}].branchName" items="${icbfn:dropdown('branch', divisionBu)}" itemLabel="name" itemValue="value" onchange="onBranchChange()" disabled="${command.disabledFlag}"/>
             <form:errors path="prebillSplits[${prebillSplitCounter.index}].branchName" cssClass="redstar"/></td>
            
            </td>
			            
            <td style="text-align:center" nowrap><span class="redstar">*</span>
             <form:input cssClass="inputBox" size="8" path="prebillSplits[${prebillSplitCounter.index}].allocPct" disabled="true"/>
            <form:errors path="prebillSplits[${prebillSplitCounter.index}].allocPct" cssClass="redstar"/>             
            </td>
            <td style="text-align:center" nowrap>${command.prebillSplits[prebillSplitCounter.index].updatedByUserName}</td>
            <td style="text-align:center" nowrap><f:formatDate value="${command.prebillSplits[prebillSplitCounter.index].updateTime}" type="date" dateStyle="long" />
            </td>    
            <td>&nbsp;</td>        
            </tr>
          </c:if>
          </c:forEach>
          
			<c:forEach items="${command.prebillSplits}" var="prebillSplit" varStatus="prebillSplitCounter"> 
            
            <c:if test="${command.prebillSplits[prebillSplitCounter.index].branchName != str}"> 
            <tr>
            <td class="TDShadeBlue" width="23%" nowrap><span class="redstar">*</span>
            <!-- 
            <form:input id="branch${prebillSplitCounter.index}" cssClass="inputBox" size="45" cssStyle="text-align:center; background-color:#d2e1ff; color:#000099;" path="prebillSplits[${prebillSplitCounter.index}].branchName" disabled="${command.disabledFlag}"/>            
            <form:errors path="prebillSplits[${prebillSplitCounter.index}].branchName" cssClass="redstar"/>  
            -->
            <form:select id="branch${prebillSplitCounter.index}" cssClass="selectionBox" path="prebillSplits[${prebillSplitCounter.index}].branchName" items="${icbfn:dropdown('branch', divisionBu)}" itemLabel="name" itemValue="value" onchange="onBranchChange()" disabled="${command.disabledFlag}"/>
             <form:errors path="prebillSplits[${prebillSplitCounter.index}].branchName" cssClass="redstar"/></td>
         
            <!-- <a href="#" onClick="javascript:updateBranchIframeSrc('prebillSplits[${prebillSplitCounter.index}].branchName','${command.buName }');popup_show('jobbranchcode', 'jobbranchcode_drag', 'jobbranchcode_exit', 'screen-corner', 120, 20);hideIt();showbranchcode('jobbranchcode','jobbranchcodebody');popupboxenable()"><img
          src=" images/lookup.gif" alt="Lookup Branch" width="13" height="13" border="0" />
        	 </a> -->
            </td>
            
            <td style="text-align:center" nowrap><span class="redstar">*</span>
             <form:input cssClass="inputBox" size="8" path="prebillSplits[${prebillSplitCounter.index}].allocPct" onchange="updatePctAlloc()" disabled="${command.disabledFlag}"/>
            <form:errors path="prebillSplits[${prebillSplitCounter.index}].allocPct" cssClass="redstar"/>             
            </td>
            <td style="text-align:center" nowrap>${command.prebillSplits[prebillSplitCounter.index].updatedByUserName}</td>
            <td style="text-align:center" nowrap><f:formatDate value="${command.prebillSplits[prebillSplitCounter.index].updateTime}" type="date" dateStyle="long" />
            </td>
            <td >
            <div id="div" style="width:20px; text-align:center;">                        
            <!-- <a href="#start"><img src="images/delete.gif" alt="Delete Branch" width="13" height="12" hspace="1px" border="0" onClick="onDeletePrebillSplit(${prebillSplitCounter.index})"/> -->
            <c:if test="${command.disableEditBrachAlloc == false}" >
				 <a href="#start"><img src="images/delete.gif" alt="Delete Branch" width="13" height="12" hspace="1px" border="0" onClick="onDeletePrebillSplit(${prebillSplitCounter.index})"/></a>
        	</c:if>
	    	<c:if test="${command.disableEditBrachAlloc == true}" >
				&nbsp;
        	</c:if>            
            </div>
            </td>   
            <!-- 
            <ajax:autocomplete 
              baseUrl="branch.htm"
              source="prebillSplits[${prebillSplitCounter.index}].branchName"
              target="prebillSplits[${prebillSplitCounter.index}].branchName"
              className="autocomplete"              
              parameters="branchName={prebillSplits[${prebillSplitCounter.index}].branchName},primaryBranch=${command.prebill.primaryBranchCd},branchSearch=prebill,branchType=${command.prebill.serviceType }"
              minimumCharacters="3"
              postFunction="populateBranchDesc"/>
              -->
            </tr>                        
            </c:if>

  
            <!-- <a href="#" onClick="javascript:updateBranchIframeSrc('branch${prebillSplitCounter.index}');popup_show('jobbranchcode', 'jobbranchcode_drag', 'jobbranchcode_exit', 'screen-corner', 80, 80);hideIt();showbranchcode('jobbranchcode','jobbranchcodebody');popupboxenable()"><img
            src="images/lookup.gif" width="13" height="13" border="0" />
            </a>   -->        


          
      
          </c:forEach>
          <tr><td>&nbsp;</td></tr>
          <tr><td>&nbsp;</td></tr>
          <tr>
            <td colspan="2">
                        
            <!-- <input name="Submit2" type="button" class="button1" value="Ok" onClick="submitform();hidebranchall();popupboxclose();"> &nbsp;&nbsp;&nbsp;&nbsp; -->
            <!--START: To fix issue  109072 -->
            <c:if test="${command.disableEditBrachAlloc == true}" >
            	<input name="Submit2" type="button" class="button1" value="Ok" onClick="submitform();hidebranchall();popupboxclose();" disabled="disabled"> &nbsp;&nbsp;&nbsp;&nbsp;
			</c:if>
			<c:if test="${command.disableEditBrachAlloc == false}" >
					<input name="Submit2" type="button" class="button1" value="Ok" onClick="submitform();hidebranchall();popupboxclose();" > &nbsp;&nbsp;&nbsp;&nbsp;
			</c:if>      		  
            <!--END: To fix issue  109072 -->
            
            <input name="Cancel" type="button" class="button1" value="Cancel"   onClick="javascript:top.hidePopupDiv('branchall','branchallbody');top.popupboxclose();" /></td>
            
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            </tr>
                     

</table>
<!-- --------------------------------- Branch Allocation Lookup END ----------------------------------------- -->

<!-- ---------------------------  Branch Code Lookup ------------------------------------------------- -->
<div class="sample_popup" id="jobbranchcode" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="jobbranchcode_drag" style="width:440px; "> 
<a href="#"  onclick="resetBranchTypeFlag()">    <img class="menu_form_exit"   id="jobbranchcode_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Search Branch Code
</div>
<div class="menu_form_body" id="jobbranchcodebody"   style="width:440px;height:340px;">
<iframe align="left" id="jobbu" frameborder="0" style="padding:10px;" height="340px;" width="100%"  scrolling="auto" id="searchBranchCodeFr" name="searchBranchCodeFr" allowtransparency="yes" src="blank.html"></iframe>
</div>
</div>
</form:form>

<div id="faderPane"
style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left : 1px ;Top : 1px ;">
<IMG src="images/fake-alpha-999.gif"></div>

<!-- START: To fix issue # 121408, 124727 -->
<script>
try{
	if(document.getElementById("Submit2")){
		if('none' == document.branchAllocPopupForm.updatePctAllocFlag.value){
			document.getElementById("Submit2").disabled = "false";
			document.getElementById("Submit2").disabled = "";
		}
		else{
			document.getElementById("Submit2").disabled = "true";
		}
	}
}
catch(e){}
</script>
<!-- END: To fix issue # 121408, 124727 -->
