<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<script	type="text/javascript" src="js/ce/ce_services.js"></script>
<script	type="text/javascript" src="js/ce/ce_jobInstruction.js"></script>
<script type="text/javascript"	src="js/lookup.js"></script>

<form:form name="jobAttachFilePopUpForm" method="POST" action="phx_job_instruction_attachfile_popup.htm" enctype="multipart/form-data">
    <form:hidden path="divName"/> 
	<form:hidden path="inputFieldId"/>
    <form:hidden path="deleteAttachId" />
    <input type="hidden" name="saveFlag" />
    <form:hidden path="orderId"/>
	
 	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
		<form:errors cssClass="error"/>
	</div>

	<table width="95%" align="center" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
        <tr valign="top">
          <td style="bborder-bottom:#999999 dashed 1px; padding-top:10px;">
			  		<span id="test1">
			  		<input id="file1" type="file" name="file1"/>
			  		<input type="text" id="desc1" title="Attachment Description" name="desc1" class="inputBox" size="40" maxlength="100" />
			  		
			  		</span></td></tr>
		      <tr><td><span id="test2">
		      		  <input id="file2" type="file" name="file2"/>
		      		  <input type="text" id="desc2"  title="Attachment Description" name="desc2" maxlength="100" size="40" class="inputBox"/>
		      		 
		      </span></td></tr>
			  <tr><td><span id="test3">
			  			<input id="file3" type="file" name="file3"/>
			  			<input type="text" id="desc3" title="Attachment Description" name="desc3" maxlength="100" size="40" class="inputBox"/>
			  			
			  		  </span>
			  </td></tr>
			  <tr><td><span id="test4">
				  	<input  id="file4" type="file" name="file4"/>
				  	<input type="text" id="desc4" title="Attachment Description" name="desc4" maxlength="100" size="40" class="inputBox"/>
				  	
			  </span></td></tr>
			  <tr><td><span id="test5">
			  			<input id="file5"  type="file" name="file5"/>
			  			<input type="text" id="desc5" title="Attachment Description" name="desc5" maxlength="100" size="40" class="inputBox"/>		      		  			 </span></td></tr>
			  <tr>
           <td><input id="upload" type="submit" value="Upload" name="upload" class="button1" onclick="javascript:return_popup_search_result('${command.inputFieldId}',  document.attachFilePopUpForm.file1.value + ';' + document.attachFilePopUpForm.file2.value + ';' + document.attachFilePopUpForm.file3.value + ';' + document.attachFilePopUpForm.file4.value + ';' + document.attachFilePopUpForm.file5.value);top.hideAttachFile('${command.divName}');top.popupboxclose();top.document.attachFilePopUpForm.submit();"/>
           <input id="cancel" type="reset"  value="Cancel" name="cancel"  class="button1"  onclick="javascript:closeAttachPopup('${command.divName}')"/>
		  </td>
		 </tr>
	</table>
	<br>
	<table width="95%" align="center" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
		 <tr>
           <td colspan="2"><b><c:if test="${!empty command.attachmentList}"><f:message key="attachedFiles"/>:</c:if><c:if test="${empty command.attachmentList}"><f:message key="noAttachedFiles"/></c:if></b></td>
		 </tr>
			<c:forEach items="${command.attachmentList}" var="attachs" varStatus="counter">
	        	<tr valign="center">
	        		<td width="40%" align="left">
	        			<a href="view_attach_popup.htm?systemFileName=${command.attachmentList[counter.index].filename}" target="_blank">${command.attachmentList[counter.index].filename}</a>
		        	</td>
		        	<td align="left">
		        		<form:input cssClass="inputBox" id="attachmentList[${counter.index}].description" maxlength="100" size="40" path="attachmentList[${counter.index}].description" />
						<form:errors path="attachmentList[${counter.index}].description" cssClass="redstar"/>	        	
			        	
			        	<authz:authorize ifAnyGranted="FileUpload">
			        		<a href="#"><img src="images/icodel.gif" alt="Delete Attachment" width="12" height="14" border="0" onclick="onDeleteAttach('${command.attachmentList[counter.index].id}'); "></a>
			        	</authz:authorize>
			        	<authz:authorize ifAnyGranted="FileUploadNoDel">
			        		<a href="#"><img src="images/icodel.gif" alt="Delete Attachment" width="12" height="14" border="0" onclick=" onDeleteAttach'${command.attachmentList[counter.index].id}'); "></a>
			        	</authz:authorize>
	        		</td>
	        	</tr>
	        </c:forEach>
	        <c:if test="${command.attachmentList != null && not empty command.attachmentList}" >
	        	<tr><td colspan="2">
	        		<input type="button" value="Save" class="button1" onclick="onSaveAttach();"/>
	       		</td></tr>
	        </c:if>
  		</table>
	   </form:form>
