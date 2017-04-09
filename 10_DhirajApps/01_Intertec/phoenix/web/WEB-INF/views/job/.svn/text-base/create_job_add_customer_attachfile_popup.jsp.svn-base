<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>

<script>

function onDelete(attachId)
  {
    document.getElementById("deleteAttachId").value=attachId;
    document.jobAttachFilePopUpForm.submit();
  }
  
  function onSave(attachId, desc, type)
  {
    document.getElementById("saveFlag").value='true';
    document.jobAttachFilePopUpForm.submit();
  }

	function confirmDelete(){
	    var yesno = confirm ("Are you sure you want to delete this attchment?");
	    if (yesno == true)
		  return true;
	    else
	      return false;
	}
</script>
<form:form name="jobAttachFilePopUpForm" method="POST" action="create_job_add_customer_attachfile_popup.htm" enctype="multipart/form-data">
    <form:hidden path="divName"/>
    <form:hidden path="jobCount"/>
	<form:hidden path="inputFieldId"/>
    <form:hidden path="deleteAttachId" />
    <input type="hidden" name="saveFlag" />
	<input type="hidden" name="jobContractId" value="${command.jobContract.id}" />

 	<div style="width:auto;padding:-left:5px;padding-top:5px;color:red;">
		<form:errors cssClass="error"/>
	</div>

	<table width="95%" align="center" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
        <tr valign="top">
          <td style="bborder-bottom:#999999 dashed 1px; padding-top:10px;">
			  		<span id="test1">
			  		<input id="file1" type="file" name="file1"/>
			  		<input type="text" id="desc1" title="Attachment Description" name="desc1" class="inputBox" size="40" maxlength="100" />
			  		<select id="type1" name="type1" class="selectionBox">
				  		<c:forEach items="${icbfn:dropdown('jobContractAttachType',null)}" var="attType" >
				  			<option value="${attType.value}"><c:out value="${attType.value}" /></option>
	  					</c:forEach>
  					</select>
			  		</span></td></tr>
		      <tr><td><span id="test2">
		      		  <input id="file2" type="file" name="file2"/>
		      		  <input type="text" id="desc2"  title="Attachment Description" name="desc2" maxlength="100" size="40" class="inputBox"/>
		      		  <select id="type2" name="type2" class="selectionBox">
				  		  <c:forEach items="${icbfn:dropdown('jobContractAttachType',null)}" var="attType" >
				  			  <option value="${attType.value}"><c:out value="${attType.value}" /></option>
	  					  </c:forEach>
  					  </select>
		      </span></td></tr>
			  <tr><td><span id="test3">
			  			<input id="file3" type="file" name="file3"/>
			  			<input type="text" id="desc3" title="Attachment Description" name="desc3" maxlength="100" size="40" class="inputBox"/>
			  			<select id="type3" name="type3" class="selectionBox">
				  		  <c:forEach items="${icbfn:dropdown('jobContractAttachType',null)}" var="attType" >
				  			  <option value="${attType.value}"><c:out value="${attType.value}" /></option>
	  					  </c:forEach>
  					  </select>
			  		  </span>
			  </td></tr>
			  <tr><td><span id="test4">
				  	<input  id="file4" type="file" name="file4"/>
				  	<input type="text" id="desc4" title="Attachment Description" name="desc4" maxlength="100" size="40" class="inputBox"/>
				  	<select id="type4" name="type4" class="selectionBox">
				  		  <c:forEach items="${icbfn:dropdown('jobContractAttachType',null)}" var="attType" >
				  			  <option value="${attType.value}"><c:out value="${attType.value}" /></option>
	  					  </c:forEach>
  					</select>
			  </span></td></tr>
			  <tr><td><span id="test5">
			  			<input id="file5"  type="file" name="file5"/>
			  			<input type="text" id="desc5" title="Attachment Description" name="desc5" maxlength="100" size="40" class="inputBox"/>
		      		  	<select id="type5" name="type5" class="selectionBox">
				  		 	 <c:forEach items="${icbfn:dropdown('jobContractAttachType',null)}" var="attType" >
				  				  <option value="${attType.value}"><c:out value="${attType.value}" /></option>
	  					 	 </c:forEach>
  					 	</select>			  		
			  </span></td></tr>
			  <tr>
           <td><input id="upload" type="submit" value="Upload" name="upload" class="button1" onclick="javascript:top.return_popup_search_result('${command.inputFieldId}',  document.jobAttachFilePopUpForm.file1.value + ';' + document.jobAttachFilePopUpForm.file2.value + ';' + document.jobAttachFilePopUpForm.file3.value + ';' + document.jobAttachFilePopUpForm.file4.value + ';' + document.jobAttachFilePopUpForm.file5.value);top.hideAttachFile('${command.divName}');top.popupboxclose();top.document.createJobsInspForm.submit();"/>
           <input id="cancel" type="reset"  value="Cancel" name="cancel"  class="button1"  onclick="javascript:top.hideAttachFile('${command.divName}');top.popupboxclose();"/>
		  </td>
		 </tr>
	</table>
	<br>
	<table width="95%" align="center" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
		 <tr>
           <td colspan="2"><b><c:if test="${!empty command.jobContractAttachs}"><f:message key="attachedFiles"/>:</c:if><c:if test="${empty command.jobContractAttachs}"><f:message key="noAttachedFiles"/></c:if></b></td>
		 </tr>
			<c:forEach items="${command.jobContractAttachs}" var="jobContractAttach" varStatus="counter">
	        	<tr valign="center">
	        		<td width="40%" align="left">
	        			<a href="view_jobcontract_attach_popup.htm?systemFileName=${command.jobContractAttachs[counter.index].systemFileName}" target="_blank">${command.jobContractAttachs[counter.index].fileName}</a>
		        	</td>
		        	<td align="left">
		        		<form:input cssClass="inputBox" id="descInput${counter}" maxlength="100" size="40" path="jobContractAttachs[${counter.index}].description" />
						<form:errors path="jobContractAttachs[${counter.index}].description" cssClass="redstar"/>
			        	<form:select cssClass="selectionBox" id="typeSel${counter}" path="jobContractAttachs[${counter.index}].type" items="${icbfn:dropdown('jobContractAttachType',null)}" itemLabel="name" itemValue="value"/>
			        	<form:errors path="jobContractAttachs[${counter.index}].type" cssClass="redstar"/>
			        	<authz:authorize ifAnyGranted="FileUpload">
			        		<a href="#"><img src="images/icodel.gif" alt="Delete Attachment" width="12" height="14" border="0" onclick="if(confirmDelete()){ onDelete('${command.jobContractAttachs[counter.index].id}'); }"></a>
			        	</authz:authorize>
			        	<authz:authorize ifAnyGranted="FileUploadNoDel">
			        		<a href="#"><img src="images/icodel.gif" alt="Delete Attachment" width="12" height="14" border="0" onclick="if(confirmDelete()){ onDelete('${command.jobContractAttachs[counter.index].id}'); }"></a>
			        	</authz:authorize>
	        		</td>
	        	</tr>
	        </c:forEach>
	        <c:if test="${command.jobContractAttachs != null && not empty command.jobContractAttachs}" >
	        	<tr><td colspan="2">
	        		<input type="button" value="Save" class="button1" onclick="onSave();"/>
	       		</td></tr>
	        </c:if>
  		</table>
	   </form:form>
