<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script language="javascript">
function viewWSLog(){
	var form=document.searchWebserviceLogForm;
  	form.submit();
}

function viewLogDetail(pageNum){
	var form=document.searchWebserviceLogDetailForm;
	form.elements['pagination.currentPageNum'].value=pageNum;
	form.submit();
}

function sortLogDetail(sortBy){
	var form=document.searchWebserviceLogDetailForm;
	if(form.sortDesc.value=="true"){
		form.sortDesc.value="false";
	}
	else{
		form.sortDesc.value="true";
	}
	form.sortBy.value=sortBy;
	form.submit();
}

function showPopupDetail(dataId, column){
	var bound=document.searchWebserviceLogDetailForm.inOutBoundInd.value;
	var frame=document.getElementById("webServiceLogDetailPopupFr");
	frame.src="/phoenix/search_webservice_log_xml_detail.htm?inOutBoundInd="+bound+"&id="+dataId+"&column="+column;
	popup_show('webServiceLogDetailPopup','webServiceLogDetailPopup_drag','webServiceLogDetailPopup_exit', 'screen-corner', 120, 20);
	hideIt();
	showPopupDiv('webServiceLogDetailPopup','webServiceLogDetailPopupBody');
	popupboxenable();
}

function resendWebService(entityType, entityKey){
	var form=document.resendWebServiceForm;
	form.entityKey.value=entityKey;
	form.entityType.value=entityType;
	form.submit();
}
</script>

<BR>

<form:form name="resendWebServiceForm" method="POST" action="resend_webservice.htm">
	<form:hidden path="fromDate"/>
	<form:hidden path="toDate"/>
	<form:hidden path="key"/>
	<form:hidden path="inOutBoundInd"/>
	<form:hidden path="type"/>
	<form:hidden path="status"/>
	<form:hidden path="pagination.totalRecord"/>
	<form:hidden path="pagination.currentPageNum"/>
	<form:hidden path="sortBy"/>
	<form:hidden path="sortDesc"/>
	<input type="hidden" name="entityKey">
	<input type="hidden" name="entityType">
</form:form>

<form:form name="searchWebserviceLogForm" method="POST" action="search_webservice_log.htm">
	<form:hidden path="fromDate"/>
	<form:hidden path="toDate"/>
	<form:hidden path="key"/>
</form:form>

<form:form name="searchWebserviceLogDetailForm" method="POST" action="search_webservice_log_detail.htm">
	<form:hidden path="fromDate"/>
	<form:hidden path="toDate"/>
	<form:hidden path="key"/>
	<form:hidden path="inOutBoundInd"/>
	<form:hidden path="type"/>
	<form:hidden path="status"/>
	<form:hidden path="pagination.totalRecord"/>
	<form:hidden path="pagination.currentPageNum"/>
	<form:hidden path="sortBy"/>
	<form:hidden path="sortDesc"/>
</form:form>
<div style="color:red;">
  <form:errors cssClass="error"/>
</div>
<c:if test="${not empty requestScope['message']}">
  <div style="color:${requestScope['messageColor']}">
    <f:message key="${requestScope['message']}" />
  </div>
</c:if>
<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
      <!-- MAIN CONTAINER -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="webServiceLogDetail"/> - <f:message key="${command.inOutBoundInd}"/>  </span></a></li>
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
                      <td>&nbsp;</td>
					  <td>
						<c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
							<c:choose>
							<c:when test="${page.selected}">
								${page.name}
							</c:when>
							<c:otherwise>
								<a href="#" onClick="viewLogDetail('${page.pageNumber}')">${page.name}</a>
							</c:otherwise>
							</c:choose>
							&nbsp;&nbsp;
						</c:forEach>
					  </td>
					  <td>&nbsp;</td>
                      <td style="text-align:right"><a href="#" onclick="viewWSLog()"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a> 
                      </td>
                    </tr>
                  </table></td>
                </tr>
              </table>

              <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
                <tr>
					<th class="TDShade">&nbsp;</th>
					<th class="TDShade"><f:message key="type"/></th>
					<th class="TDShade"><a href="#" onClick="sortLogDetail('status')"><f:message key="status"/></a></th>
					<c:if test="${command.inOutBoundInd!='Inbound'}">
					<!-- th class="TDShade">&nbsp;</th-->
					</c:if>
					<th class="TDShade" nowrap><a href="#" onClick="sortLogDetail('createTime')"><f:message key="createTime"/></a></th>
					<c:if test="${command.inOutBoundInd!='Inbound'}">
					<th class="TDShade"><a href="#" onClick="sortLogDetail('createUser')"><f:message key="createdBy"/></a></th>
					</c:if>
					<th class="TDShade"><a href="#" onClick="sortLogDetail('entityKey')"><f:message key="key"/></a></th>
					<th class="TDShade"><f:message key="content"/></th>
					<th class="TDShade"><f:message key="result"/></th>
					<th class="TDShade"><f:message key="message"/></th>
                </tr>
				<c:forEach items="${command.results}" var="row" varStatus="status">
					<c:choose>
						<c:when test="${status.index%2==0}">
						    <tr style="background-color:#FFFFFF;">
						</c:when>
						<c:otherwise>
							<tr style="background-color:#e7eeff;">                    
						</c:otherwise>
					</c:choose>
						<td class="TDShade" align="right">${(status.index+1)+((command.pagination.currentPageNum-1)*command.pagination.numInPage)}</td>
						<td class="TDShade">${row.type}</td>
						<td class="TDShade">
							<f:message key="webserviceStatus_${row.status==null?false:row.status}"/>
						</td>
						<c:if test="${command.inOutBoundInd!='Inbound'}">
						<!-- td class="TDShade">
							<a href="#" onclick="resendWebService('${row.type}', '${row.entityKey}')">Resend</a>
						</td-->
						</c:if>
						<td class="TDShade">${row.createTime}</td>
						<c:if test="${command.inOutBoundInd!='Inbound'}">
						<td class="TDShade">
							${row.createUser}
						</td>
						</c:if>
						<td class="TDShade">
							${row.entityKey}
						</td>
						<td class="TDShade">
							<c:if test="${row.content!=null}">
								<a href="#" onClick="showPopupDetail('${row.id}', 'content')"><f:message key="content"/></a>
							</c:if>
						</td>
						<td class="TDShade">
							<c:if test="${row.result!=null}">
								<a href="#" onClick="showPopupDetail('${row.id}', 'result')"><f:message key="result"/></a>
							</c:if>
						</td>
						<td class="TDShade">
							<c:if test="${row.message!=null}">
								<a href="#" onClick="showPopupDetail('${row.id}', 'message')"><f:message key="message"/></a>
							</c:if>
						</td>
					</tr>	
				</c:forEach>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td>&nbsp;</td>
					  <td>
						<c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
							<c:choose>
							<c:when test="${page.selected}">
								${page.name}
							</c:when>
							<c:otherwise>
								<a href="#" onClick="viewLogDetail('${page.pageNumber}')">${page.name}</a>
							</c:otherwise>
							</c:choose>
							&nbsp;&nbsp;
						</c:forEach>
					  </td>
					  <td>&nbsp;</td>
                      <td style="text-align:right"><a href="#" onclick="viewWSLog()"><img src="images/icofindjob.gif" alt="Search" width="16" height="14" border="0" align="absmiddle"></a> 
                      </td>
                    </tr>
                  </table></td>
                </tr>
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
<div class="sample_popup" id="webServiceLogDetailPopup" style="visibility: hidden; display: none;">
     <div class="menu_form_header" id="webServiceLogDetailPopup_drag" style="width:750px; "> 
             <img class="menu_form_exit" id="webServiceLogDetailPopup_exit" src="images/form_exit.png"/> &nbsp;&nbsp;&nbsp;
                <f:message key="webServiceLogDetail"/></div>
	<div class="menu_form_body" id="webServiceLogDetailPopupBody"   style="width:750px; height:530px;overflow-y:hidden;padding-left:4px;">
		<form method="post" action="popup.php">     
			<iframe align="left" frameborder="0" style="padding:10px;" height="530px;" width="100%"  scrolling="auto" id="webServiceLogDetailPopupFr" name="webServiceLogDetailPopupFr" allowtransparency="yes" src="blank.html">
			</iframe>
		</form>
	</div>
</div>
<BR>