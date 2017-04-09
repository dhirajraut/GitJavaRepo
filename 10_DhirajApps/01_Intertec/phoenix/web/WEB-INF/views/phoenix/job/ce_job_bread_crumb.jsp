<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="js/ce/ce_services.js"></script>

<table width="100%" height="22" border="0" align="left" cellpadding="0"	cellspacing="0">
	<tr><td width="70%">
	<table>
		<tr>
		<td class="breadcrumbtrailDeactive"
			style="background: none; padding-left: 5px;">&#9658; <a
			href="phx_job_entry_ce.htm?jobNumber=<c:out value="${param.jobNumber}"/> ">
		<f:message key="entry" /> </a></td>
		<td class="breadcrumbtrailDeactive"><c:choose>
			<c:when test="${param.pageNumber>=2}">
				<a
					href="phx_job_operational_info_ce.htm?jobNumber=<c:out value="${param.jobNumber}"/> ">
				<f:message key="jobInstructions" /> </a>
			</c:when>

			<c:otherwise>
				<f:message key="jobInstructions" />
			</c:otherwise>
		</c:choose></td>
		<td class="breadcrumbtrailDeactive"><c:choose>
			<c:when test="${param.pageNumber>=3}">
				<a
					href="phx_ce_job_select_charges.htm?jobNumber=<c:out value="${param.jobNumber}"/> ">
				<f:message key="selectCharges" /> </a>
			</c:when>
			<c:otherwise>
				<f:message key="selectCharges" />
			</c:otherwise>
		</c:choose></td>
		<td class="breadcrumbtrailDeactive"><c:choose>
			<c:when test="${param.pageNumber>=4}">
				<a
					href="phx_ce_job_invoice_preview.htm?jobNumber=<c:out value="${param.jobNumber}"/> ">
				<f:message key="invoicePreview" /> </a>
			</c:when>
			<c:otherwise>
				<f:message key="invoicePreview" />
			</c:otherwise>
		</c:choose></td>

		<td class="breadcrumbtrailDeactive"><c:choose>
			<c:when test="${param.pageNumber>=5}">
				<a
					href="phx_job_view_ce_invoice.htm?jobNumber=<c:out value="${param.jobNumber}"/> ">
				<f:message key="viewInvoice" /> </a>
			</c:when>
			<c:otherwise>
				<f:message key="viewInvoice" />
			</c:otherwise>
		</c:choose></td>
		</tr>
		</table></td>
		
		<td align="right">&nbsp;</td>
		
		<td align="right"><a href="#" onClick="javascript:showRequiredFields()">
			<f:message key="requiredFields" /></a></td>
		<td align="right"><img src="images/inrttrailcorner.gif" width="7" height="22"></td>
	</tr>
</table>
