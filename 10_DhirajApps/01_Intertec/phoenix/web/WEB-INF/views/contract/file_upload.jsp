<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script language="javascript">
<c:if test="${requestScope['success']=='true'}">
	top.document.${command.formName}.submit();
</c:if>
</script>

<form:form name="fileUploadForm" method="POST" action="file_upload.htm" enctype="multipart/form-data" onsubmit="javascript:top.return_popup_search_result('${command.inputFieldId}',  document.fileUploadForm.file.value);top.hideattachpop();top.popupboxclose();">
<form:hidden path="inputFieldId" />
<table width="95%" align="center" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
        <tr>
		  <td width="50%"  style="padding-top:10px;">
		    <table border="0" cellpadding="0" cellspacing="0">

            <input type="file" id="file" name="file" class="inputBox" size="50"/>
            <tr>
			<input id="upload" type="submit" value="Upload" name="upload" class="button1" onClick="hideattachpop();"/>
            &nbsp;&nbsp;
            <input id="cancel" type="button" value="Cancel" name="cancel" class="button1" onClick="hideattachCan();"/>
             </tr>
		    </table>
		  </td>
		 </tr>
	  </table>
</form:form>
