<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>

<script language="javascript">

function doReleaseAll(form){
	if(confirm("Are you sure you want to release ALL current locked jobs?")){
		form.releaseAll.value=true;
		form.submit();
	}
}

function releaseJobLock(form, jobNumber){
	if(confirm("Are you sure you want to release the lock for this job ("+jobNumber+")?")){
		form.jobNumberToRelease.value=jobNumber;
		form.submit();
	}
}

function submitForm(form){
	form.submit();
}
</script>

<form:form name="mainForm" method="POST" action="job_locks.htm">
	<input type="hidden" name="jobNumberToRelease" value=""/>
	<input type="hidden" name="releaseAll" value=""/>

<c:if test="${not empty requestScope['message']}">
<div style="color:${messageColor};">
  <f:message key="${message}"/>
</div>
</c:if>

<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
      <div id="MainContentContainer">
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="jobLocks"/></span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td>
						<input id="submitButton1" type="button" value="Refresh" name="submitButton2" class="button1" onClick="submitForm(this.form)"/>
						<c:if test="${!empty myLocks}">
						<input id="submitButton2" type="button" value="Release ALL" name="submitButton2" class="button1" onClick="doReleaseAll(this.form)"/>
						</c:if>
					  </td>
                    </tr>
                  </table></td>
                </tr>
              </table>

              <table width="100%" border="0" align="center" cellpadding="1" cellspacing="0" class="InnerApplTable">
	              	<tr>
						<th width="15%" class="TDShade" nowrap><f:message key="jobNumber"/></th>
						<th width="25%" class="TDShade" nowrap><f:message key="lockedByUser"/></th>
						<th width="10%" class="TDShade" nowrap><f:message key="remainTime"/></th>
						<th width="50%" class="TDShade" nowrap>&nbsp;</th>
					</tr>
					<c:forEach items="${myLocks}" var="item" varStatus="itemStatus">
						<c:choose>
						<c:when test="${itemStatus.index%2==0}">
							<tr style="background-color:#FFFFFF;">
						</c:when>
						<c:otherwise>
							<tr style="background-color:#e7eeff;">                    
						</c:otherwise>
						</c:choose>
						<td class="TDShade">${item.applicationLevelLockId.key}</td>
						<td class="TDShade">${item.lockedByUser}</td>
						<td class="TDShade">${icbfn:getJobRemainTime(item.lockedDateTime)}</td>
						<td class="TDShade"><input name="releaseButton" type="button" class="button1" value="Release" style="height:18px;" onClick="javascript:releaseJobLock(this.form, '${item.applicationLevelLockId.key}');"/></td>
					</tr>    
					</c:forEach>
              </table>
            </div>
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

