<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src=" js/calendar.js?random=20060118"></script>

<script language="javascript">


function sortByJobNumber(){
document.monthlyJobLogForm.pageNumber.value = "1";
document.monthlyJobLogForm.sortFlag.value = "jobNumber";
document.userSearchForm.submit();
}

function sortByJobStatus(){
document.monthlyJobLogForm.pageNumber.value = "1";
document.monthlyJobLogForm.sortFlag.value = "jobStatus";
document.userSearchForm.submit();
}
function sortByJobType(){
document.monthlyJobLogForm.pageNumber.value = "1";
document.monthlyJobLogForm.sortFlag.value = "jobType";
document.monthlyJobLogForm.submit();
}

function sortByCreatedDate(){
document.monthlyJobLogForm.pageNumber.value = "1";
document.monthlyJobLogForm.sortFlag.value = "createTime";
document.monthlyJobLogForm.submit();
}

function subform()
{
document.monthlyJobLogForm.submit();
}


function submitSearch(pageNumber)
{	 
document.monthlyJobLogForm.pageNumber.value = pageNumber;
document.monthlyJobLogForm.submit();
}	


  </script>

<icb:list var="activeStatus">
  <icb:item>activeStatus</icb:item>
</icb:list>
 <form:form name="monthlyJobLogForm" method="POST" action="monthly_job_log.htm">
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<form:hidden path="sortFlag"/>
 <input type="hidden" name="pageNumber" value="1" />
<table width="87%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
	
<!--------------------------------------------MAIN CONTAINER------------------------------------------------------->
<div id="MainContentContainer">
<!---------------------------------------------TABS CONTENTS------------------------------------------------------->

<div id="tabcontainer">
<div id="tabnav">
<ul>
<li><a href="#" rel="tab1"><span><f:message key="monthlyJobDetails"/></span></a></li>
</ul>
</div>
<!-----------------------------------Sub Menus container. Do not remove-------------------------------------------->
          <div id="tab_inner">			
<!-----------------------------------------------TAB 1 CONTAINER--------------------------------------------------->
            <div id="tab1" class="innercontent" >
			  <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr bgcolor=#ffffff>
<th colspan="9"><f:message key="searchMonthlyJobs"/></th>                   
                  </tr>
                   <tr class="InnerApplTable">
                  <td width="20%" class="TDShade"><f:message key="fromDate"/><strong> :</strong></td>
                  <td colspan="1" class="TDShadeBlue">
                   <form:input cssClass="inputBox" size="10" maxlength="10" path="fromDate" />
					<a href="#" onClick="displayCalendar(document.getElementById('fromDate'),'MM/dd/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
                    <form:errors path="fromDate" cssClass="redstar"/>
                 </td>
        
                 <td class="TDShade"><f:message key="toDate"/><strong> :</strong></td>
                 <td colspan="2" class="TDShadeBlue">
                    <form:input cssClass="inputBox" size="10" maxlength="10" path="toDate" />
					<a href="#" onClick="displayCalendar(document.getElementById('toDate'),'MM/dd/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
                    <form:errors path="toDate" cssClass="redstar"/>
                  </td>              
				</tr> 
				 </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td>
						<input id="search" type="button" value="Search" name="search" class="button1" onClick="subform()"/></td>
                    </tr>
                  </table></td>
                </tr>
              </table>
              <br>




  <c:if test="${command.results != null}">
                <div id="monthlyjobdetailresults"> 
                  <strong><f:message key="searchResults"/>
                  </strong>
                  <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                    <tr>
                      <th><a href="#" onClick="sortByJobNumber();" ><span class="TDShade"><f:message key="JobNumber"/></span></a></th>
                      <th><a href="#" onClick="sortByJobStatus();" ><span class="TDShade"><f:message key="jobStatus"/></span></a></th>
                      <th><a href="#" onClick="sortByJobType();" ><span class="TDShade"><f:message key="jobType"/></span></a></th>
					  <th><a href="#" onClick="sortByCreatedDate();" ><span class="TDShade"><f:message key="dateCreated"/></span></a></th>
					
                    </tr>
                    <c:forEach items="${command.results}" var="jobContract" varStatus="status">
                      <c:choose>
					<c:when test="${status.index%2==0}">
				    <tr style="background-color:#FFFFFF;">
					  </c:when>
					  <c:otherwise>
					  <tr style="background-color:#e7eeff;">                    
					  </c:otherwise>
					  </c:choose>
                       <td>${jobContract.jobOrder.jobNumber} </td>
                       	<td><f:message key="jobstatus_${jobContract.jobOrder.jobStatus}"/></td>
						<td><f:message key="jobtype_${jobContract.jobOrder.jobType}"/></td>
						<td>${jobContract.jobOrder.createTime}</td>
					 </tr>
                    </c:forEach>
                    <tr>
                      <td>&nbsp;</td>
                      <td align="center">
                        <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
                          <a href="#start" onClick="submitSearch('${page.pageNumber}')">${page.name} </a>&nbsp;
                        </c:forEach>
                      </td>
                    </tr>
                  </table>
                </div>
              </c:if>
</td>
</tr>
</tbody>
</table>

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
<!------------------------------------------MAIN CONTAINER END----------------------------------------------------->
</td>
</tr>
</table>

</form:form>

