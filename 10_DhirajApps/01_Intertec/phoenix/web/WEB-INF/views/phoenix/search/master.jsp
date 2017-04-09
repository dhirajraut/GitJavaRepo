<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page import="com.intertek.phoenix.search.SearchForm" %>
<script type="text/javascript" src="js/ce/genericlookup.js"></script>

<script language="javaScript">
function doSearch(form, pageNo){
	document.getElementById('pageNumber').value = pageNo;
	//document.getElementById('sortFlag').value = "";
	document.getElementById('excelFlag').value=false;
	document.getElementById('submitFlag').value="true";
	document.forms[0].submit();
}

function doSort(sortBy){
	if ("" == sortBy){
		alert("Not yet implemented");
		return false;
	}
	document.getElementById('sortBy').value=sortBy;
	var sortingOrder  = document.getElementById('sortFlag').value;
	if ("ASC" == sortingOrder || "asc" == sortingOrder ){
		document.getElementById('sortFlag').value = "DESC";
	}else{
		document.getElementById('sortFlag').value = "ASC";
	}
	document.getElementById('excelFlag').value=false;
	document.getElementById('submitFlag').value="true";
	document.forms[0].submit();
}

function submitxcel(){
	var form=document.searchForm;
	form.excelFlag.value=true;
	form.pageNumber.value = "1";
	form.sortFlag.value = "";
	form.submitFlag.value="true";
	form.submit();
}

</script>

<form:form name="searchForm" method="POST" action="phx_search.htm">	
	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
		<form:errors cssClass="error"/>
	</div>
	<form:hidden path="pageNumber" />
	<form:hidden path="sortFlag" />
	<form:hidden path="submitFlag" />
	<form:hidden path="searchType" />
	<form:hidden path="excelFlag" />
	<form:hidden path="searchForm" />
	<form:hidden path="targetFieldId"/>
	<form:hidden path="sortBy"/>
	
	

	<table border="0" cellpadding="0" cellspacing="0" class="MainTable" style="padding:-left:10px;padding-top:0px;">
		<tr>
			<td valign="top">
				<div id="tab1" class="innercontent" >
					<jsp:include page="${command.searchCriteriaPage}" flush="true" />
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
						<tr>
							<td>
							  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">																	
									<tr>
										<td><input name="button" type="button" onClick="doSearch(this.form, 1)" class="button1" value="<f:message key="search"/>"  /></td>
										<td><c:if test="${command.searchType == 'serviceLocation'}">
										 <a href="#" onClick="closePopup();"><f:message key="createNewServiceLocation" /><a></c:if>&nbsp;</td>
										<td style="text-align:right;"><a href="#start"><img id="xcel" src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a></td>
									</tr>									
								</table>
							</td>
						</tr>
					</table>
					<br />

<div id="searchresults"> 
<c:if test="${command.rows != null}">
	<strong><f:message key="searchResults"/></strong>
	<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
		<tr>
			<c:forEach items="${command.header.columns}" var="c" varStatus="status">
				<th>
					<c:if test="${c.jsFunctionName != null}">
						<a href="#start" onClick="${c.jsFunctionName}('${c.sortFieldName}')">
					</c:if>
						<span class="TDShade" nowrap><f:message key="${c.content}"/></span>
					<c:if test="${c.jsFunctionName != null}">
						</a>
					</c:if>
				</th>
			</c:forEach>
		</tr>
		
			<c:forEach items="${command.rows}" var="r" varStatus="status">
				<c:choose>
					<c:when test="${status.index%2==0}">
						<tr style="background-color:#FFFFFF;">
					</c:when>
					<c:otherwise>
						<tr style="background-color:#e7eeff;">                    
					</c:otherwise>
				</c:choose>
					<c:forEach items="${r.columns}" var="c" varStatus="status">
						<td>
							<c:if test="${c.jsFunctionName != null}">
								<a href="#start" onClick="${c.jsFunctionName}('${r.rowId}', '${command.targetFieldId}')">
							</c:if>
								${c.content}
							<c:if test="${c.jsFunctionName != null}">
								</a>
							</c:if>
						</td>
					</c:forEach>			
				</tr>
			</c:forEach>
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
			</c:forEach></td>
		</tr>
	</table>
</c:if>
</form:form>
