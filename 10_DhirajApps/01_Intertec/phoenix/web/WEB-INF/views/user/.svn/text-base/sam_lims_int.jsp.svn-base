<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src=" js/calendar.js?random=20060118"></script> 

<script language="javascript">
function onBranchCodeAdd()
{

document.createSamLimsIntForm.addOrDeleteBranchCode.value = "add";
document.createSamLimsIntForm.vxcel.value="false";
document.createSamLimsIntForm.submit();
}

function onBranchCodeDelete(index)
{
document.createSamLimsIntForm.addOrDeleteBranchCode.value = "delete";
document.createSamLimsIntForm.branchCodeIndex.value = index;
document.createSamLimsIntForm.vxcel.value="false";
document.createSamLimsIntForm.submit();
}

function subform()
{
document.createSamLimsIntForm.vxcel.value="false";
document.createSamLimsIntForm.submit();
}

function setflag(rowIndex)
{  
document.createSamLimsIntForm.branchFlag.value="newval";
document.createSamLimsIntForm.rowNum.value=rowIndex;
document.createSamLimsIntForm.vxcel.value="false";
}
function submitSearch(pageNumber)
{  
document.createSamLimsIntForm.pageNumber.value = pageNumber;
document.createSamLimsIntForm.pageFlag.value="pageFlag";
document.createSamLimsIntForm.vxcel.value="false";
document.createSamLimsIntForm.submit();
} 

function updateBranchIframeSrc(index,buElem){ 

var buName = document.getElementById(buElem).value;
document.createSamLimsIntForm.vxcel.value="false";
document.getElementById('searchBranchFr'+index).setAttribute("src","search_branch_popup.htm?inputFieldId=branches["+index+"].branchName&formName=createSamLimsIntForm&rowNum="+index+"&div1=jobbranchcode"+index+"&div2=jobbranchcodebody"+index+"&buName="+buName);
}
function submitxcel(){
document.createSamLimsIntForm.vxcel.value="true";
top.document.createSamLimsIntForm.submit();
}

function makeBranchblank(index)
{
  document.getElementById("brnch"+index).value="";
}

function sortByColumnHeader(colName){
document.createSamLimsIntForm.pageNumber.value = "1";
document.createSamLimsIntForm.sortFlag.value = colName;
document.createSamLimsIntForm.vxcel.value="false";
document.createSamLimsIntForm.submit();
}
  </script>


 <form:form name="createSamLimsIntForm" method="POST" action="sam_lims_int.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
 <form:hidden path="addOrDeleteBranchCode"/>
 <form:hidden path="branchCodeIndex"/>
 <form:hidden path="branchCount"/>
 <form:hidden path="branchFlag"/>
 <form:hidden path="rowNum" />
 <form:hidden path="pageFlag"/>
 <form:hidden path="sortFlag"/>
 <input type="hidden" name="vxcel" value="false"/>
<input type="hidden" name="pageNumber" value="1" />
<table width="87%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
<!----------------------------------------------MAIN CONTAINER----------------------------------------------------->
      <div id="MainContentContainer">
<!-----------------------------------------------TABS CONTENTS----------------------------------------------------->

<div id="tabcontainer">
<div id="tabnav">
<ul>
<li><a href="#" rel="tab1"><span><f:message key="samLimsBranches"/></span></a></li>
</ul>
</div>

<!-----------------------------------------Sub Menus container. Do not remove-------------------------------------->
          <div id="tab_inner">      
<!---------------------------------------------TAB 1 CONTAINER ---------------------------------------------------->
            <div id="tab1" class="innercontent" >
        <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>

<th width="30%"><f:message key="samLimsBranches"/></th>

                    <th style="text-align:right;" >&nbsp;</th>
                    <th  style="text-align:right;" ><a href="#"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><a href="#"><img src=" images/icosave.gif" alt="Save" width="14" height="14" border="0"  onclick="subform()"/ ></a></th>
                  </tr>
                  <tr bgcolor=#ffffff>
                    <td colspan="3" class="TDShade" style="padding:2px;">
          
          <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" >
                      <tr>
                        <th width="25"><f:message key="no"/></th>

<th width="35%"><a href="#start" onClick="sortByColumnHeader('br.buName');" ><f:message key="businessUnitName"/></a></th>


<th width="35%"><a href="#start" onClick="sortByColumnHeader('bi.branchName');" ><f:message key="branch"/></a></th>

<th width="15%"><a href="#start" onClick="sortByColumnHeader('bi.samInd');" ><f:message key="samIndicator"/></a> </th>
<th width="15%"><a href="#start" onClick="sortByColumnHeader('bi.limsInd');" ><f:message key="limsIndicator"/></a></th>
<th style="text-align:center" width="50">&nbsp;&nbsp;&nbsp;<img src="images/add.gif" alt="Add row" width="13" height="12"  onclick="javascript:onBranchCodeAdd()"/></th>
</tr>
            <c:forEach items="${command.branches}" var="branches" varStatus="counter">
             <tr>
			<td class="TDShadeBlue">${command.pagination.currentPageNum*20-20+counter.index+1}</td>
			<td class="TDShadeBlue">
			<c:choose>
			<c:when test="${command.branches[counter.index].branch.buName!=null}">
			<form:select cssClass="selectionBox" id="sel${counter.index}"
            path="branches[${counter.index}].branch.buName"
            items="${icbfn:dropdown('businessUnit', null)}" onchange="javascript:makeBranchblank(${counter.index});"
            itemLabel="name" itemValue="value" disabled="true"/> 
            </c:when>
            <c:otherwise>
            <form:select cssClass="selectionBox" id="sel${counter.index}"
            path="branches[${counter.index}].branch.buName"
            items="${icbfn:dropdown('businessUnit', null)}" onchange="javascript:makeBranchblank(${counter.index});"
            itemLabel="name" itemValue="value"/> 
            </c:otherwise>
            </c:choose>
            <form:errors path="branches[${counter.index}].branch.buName" cssClass="redstar" /> 
            </td>
            <td>
            <c:choose>
			<c:when test="${command.branches[counter.index].branchName!=null}">
            <form:input cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" id="brnch${counter.index}"
          	cssClass="inputBox" maxlength="128" size="32" path="branches[${counter.index}].branchName" disabled="true"/>
        	<form:errors path="branches[${counter.index}].branchName"
          	cssClass="redstar" />
         	</c:when>
         <c:otherwise>
         <form:input cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" id="brnch${counter.index}"
          	cssClass="inputBox" maxlength="128" size="32" path="branches[${counter.index}].branchName"/>
        	<form:errors path="branches[${counter.index}].branchName"
          	cssClass="redstar" />
         <a href="#" onClick="javascript:updateBranchIframeSrc(${counter.index},'sel${counter.index}');popup_show('jobbranchcode${counter.index }', 'jobbranchcode_drag${counter.index }', 'jobbranchcode_exit${counter.index }', 'screen-corner', 120, 20);hideIt();showbranchcode('jobbranchcode${counter.index }','jobbranchcodebody${counter.index }');popupboxenable()"><img
          src=" images/lookup.gif" alt="Lookup Branch" width="13" height="13" border="0" />
         </c:otherwise>
         </c:choose>
         </td>
           
           <td class="TDShadeBlue"><form:checkbox id="nom1"  path="branches[${counter.index}].samInd"  value="Y"/></td>
           <td class="TDShadeBlue"><form:checkbox id="nom2"  path="branches[${counter.index}].limsInd"  value="Y"/></td>


                     <td width="50" class="TDShadeBlue" style="text-align:right;"><div id="div3" style="width:50px; text-align:center; margin-right:0;"> <img src="images/delete.gif" alt="Delete Row" width="13" height="12" hspace="1px" border="0" onclick="onBranchCodeDelete('${counter.index}')" /></div>
                  </td>
 		<ajax:autocomplete
           baseUrl="branch.htm"
           source="branches[${counter.index}].branchName"
           target="branches[${counter.index}].branchName"
           className="autocomplete"             
          parameters="branchName={branches[${counter.index}].branchName},buName={branches[${counter.index}].branch.buName}"
          minimumCharacters="3"
		 />


 
   </tr>         
<!-----------------------------------------Branch Code Lookup----------------------------------------------------->
<div class="sample_popup" id="jobbranchcode${counter.index}" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="jobbranchcode_drag${counter.index}" style="width:750px; "> 
<img class="menu_form_exit"   id="jobbranchcode_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchBranchCode"/>
  </div>
  <div class="menu_form_body" id="jobbranchcodebody${counter.index}"   style="width:750px; height:530px;padding-left:4px;overflow-y:hidden;">
 
            <iframe align="left" frameborder="0" style="padding:10px;" height="530px;" width="100%"  scrolling="auto" id="searchBranchFr${counter.index}" name="searchBranchFr${counter.index}" allowtransparency="yes" src="blank.html"></iframe>
             
 
  </div>
</div>

<!-----------------------------------------Branch Code Lookup END------------------------------------------------->

</c:forEach>

 <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
 <td>&nbsp;</td>
<td align="center">
<c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
<a href="#start" onClick="submitSearch('${page.pageNumber}')">${page.name} </a>&nbsp;&nbsp;
</c:forEach>
</td>
<tr></tr>
</table>
</td>
</tr>
</tbody>
</table>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
<tr>
<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td style="text-align:right"><a href="#"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><a href="#"><img src=" images/icosave.gif" alt="Save" width="14" height="14" border="0" onclick="subform()"/></a></td>
</tr>
</table></td>
</tr>
</table>
</div>
<!-----------------------------------------------TAB1 CONTAINER END------------------------------------------------>
      
      
          </div>
        </div>
        <script type="text/javascript">
      
      //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
      dolphintabs.init("tabnav", 0)
      
      </script>
<!---------------------------------------------TAB CONTENT END----------------------------------------------------->
      </div>
<!---------------------------------------MAIN CONTAINER END ------------------------------------------------------->
    </td>
  </tr>
</table>
<div id="faderPane" style="visibility:hidden; display:none; z-index:1;Position : Absolute ;Left : 1px ;Top : 1px ;">
<IMG src="images/fake-alpha-999.gif"> </div>

</form:form>

