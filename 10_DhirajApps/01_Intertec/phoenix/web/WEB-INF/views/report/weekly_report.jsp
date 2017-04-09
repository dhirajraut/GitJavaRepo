<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script language="javascript">
function submitMyForm()
{
var asOfDt= document.getElementById("asOfDt").value;
    if(asOfDt == null || asOfDt.length==0)
		confirm("As of date is required");
	else
		document.weeklyReportForm.submit();
}

function makeBranchblank()
{
  document.getElementById("brnch").value="";
}

function updateBranchIframeSrc()
  {
  var buName= document.getElementById("sel20").value;
  if(buName!= "" && buName!= null)
  {
    document.getElementById('jobbu').setAttribute("src",src="search_branch_popup.htm?inputFieldId=branch.value&div1=jobbranchcode&div2=jobbranchcodebody&buName="+buName+"&formName=jobsForm");
  }
}
</script>

<icb:list var="divisions">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
</icb:list>

<icb:list var="divisionBu">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
 <icb:item>${command.businessUnit.value}</icb:item> 
</icb:list>


 <form:form name="weeklyReportForm" method="POST" action="weekly_report.htm">
 <div style="color:red;">
  <form:errors cssClass="error"/>
</div>

<table width="87%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
	
<!--------------------------------------------MAIN CONTAINER------------------------------------------------------->
<div id="MainContentContainer">
<!---------------------------------------------TABS CONTENTS------------------------------------------------------->

<div id="tabcontainer">
<div id="tabnav">
<ul>
<li><a href="#" rel="tab1"><span><f:message key="weeklyReport"/></span></a></li>
</ul>
</div>
<!-----------------------------------Sub Menus container. Do not remove-------------------------------------------->
          <div id="tab_inner">			
<!-----------------------------------------------TAB 1 CONTAINER--------------------------------------------------->
            <div id="tab1" class="innercontent" >
			  <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                <tr>
			        <td width="15%" class="TDShade">
			        <strong><f:message key="businessUnit" />:</strong>
			        </td>
					<td colspan="1" class="TDShadeBlue">
					<span class="id_input">
					<form:select cssClass="selectionBoxbig" id="sel20"  path="businessUnit.value"  items="${icbfn:dropdown('businessUnit', null)}" itemLabel="name" itemValue="value" onchange="makeBranchblank()" /> 
					<form:errors  path="businessUnit.value" cssClass="redstar"/>
					</span></td>
					 <td width="15%" class="TDShade"><f:message key="branchCode"/>:</td>
      				 <td colspan="1" class="TDShadeBlue">
        				<form:input id="brnch" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" cssClass="inputBox" path="branch.value" />
        				<form:errors path="branch.value" cssClass="redstar" />
         				<a href="#" onClick="javascript:updateBranchIframeSrc();popup_show('jobbranchcode', 'jobbranchcode_drag', 'jobbranchcode_exit', 'screen-corner', 80, 80);hideIt();showbranchcode('jobbranchcode','jobbranchcodebody');popupboxenable()">
         				<img src=" images/lookup.gif" width="13" alt="Lookup branch Code" height="13" border="0" />
         				</a>
         			</td>
			      </tr>
                  
                   <tr class="InnerApplTable">
                  <td width="15%" class="TDShade"><f:message key="asOfDate"/><strong> :</strong></td>
                  <td colspan="1" class="TDShadeBlue">
                  <form:input cssClass="inputBox" size="10" maxlength="10" path="asOfDate.value" id="asOfDt"/>
				  <form:errors path="asOfDate.value" cssClass="redstar"/>&nbsp;<a href="#" onClick="displayCalendar(document.forms[0].asOfDt,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
                 </td>
                 <td idth="15%" class="TDShade"><f:message key="currency"/>:</td>
					<td colspan="1" class="TDShadeBlue">
					<form:select id="sel5" cssClass="selectionBox" path="currency.value" items="${icbfn:dropdown('currency', null)}" itemLabel="name" itemValue="value"/>
            		<form:errors path="currency.value" cssClass="error"/>
				</td>
				</tr> 
				 </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td>
                      <input name="Search" type="button" class="button1" value="Search"  onclick="submitMyForm()"/>
                    </tr>
                  </table></td>
                </tr>
              </table>
              <br>
</div>
<!-------------------------------------------TAB1 CONTAINER END---------------------------------------------------->
</div>
</div>
<script type="text/javascript">
//tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
dolphintabs.init("tabnav", 0)
</script>
<!-------------------------------------------TAB CONTENT END------------------------------------------------------->
 </div>
 <ajax:autocomplete
  baseUrl="branch.htm"
  source="branch.value"
  target="branch.value"
  className="autocomplete"              
  parameters="branchName={branch.value},buName={businessUnit.value}"
  minimumCharacters="3"
 />
 
 <!-- ---------------------------  Branch Code Lookup ------------------------------------------------- -->
<div class="sample_popup" id="jobbranchcode" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="jobbranchcode_drag" style="width:750px; "> 
<a href="#"  onclick="resetBranchTypeFlag()">    <img class="menu_form_exit"   id="jobbranchcode_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Search Branch Code
  </div>
  <div class="menu_form_body" id="jobbranchcodebody" style="width:750px; height:530px;overflow-y:hidden;">
    <form method="post" action="popup.php">
     
            <iframe align="left" id="jobbu" frameborder="0" style="padding:10px;" height="530px;" width="100%"  scrolling="auto" id="searchBranchCodeFr" name="searchBranchCodeFr" allowtransparency="yes" src="blank.html"></iframe>
             
    </form>
  </div>
</div>
<!-- --------------------------------- Branch Code Lookup END ----------------------------------------- -->
 
<!------------------------------------------MAIN CONTAINER END----------------------------------------------------->
</td>
</tr>
</table>
</form:form>


