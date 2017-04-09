<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="css/stylesheet_timeselect.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/ui.theme.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/jquery.ptTimeSelect.css" media="screen" />
<script type="text/javascript" src="js/st/jquery.js"></script>
<script type="text/javascript" src="js/st/jquery.ptTimeSelect.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/ce/ce_services.js"></script>
<script type="text/javaScript">
		$(document).ready(
			function () {
				$('code').each(
					function() {
						eval($(this).html());
					}
				)
			}
		);
</script>

<body>
<form:form name="sampleTrackingCGForm" method="POST" action="phx_sampletracking.htm">
<form:hidden path="refreshing"/>
<div id="trckingtab" class="innercontent">

<table id="sampletracktableid" border=0 cellpadding=0 cellspacing=0 class="mainApplTable">
	<tbody>
	<!-- ROW # 1-->			
	<tr>	
			<th colspan="2" class="TDShadeGrey" style="border-right:#7c92be dashed 1px;">&nbsp;</th>
			<th colspan="3" class="TDShadeGrey" style="border-right:#7c92be dashed 1px;"><f:message key="cg.st.sent.info" /></th>
			<th colspan="3" class="TDShadeGrey" style="border-right:#7c92be dashed 1px;"><f:message key="cg.st.sent.recvd" /></th>
			<th colspan="2" class="TDShadeGrey" style="align:right">&nbsp;</th>
			<th class="TDShadeGrey" style="align:right"><a href="#i" onClick="javascript:addSampleTrckingIframeSrc()"><img src="images/add.gif" alt="Add Sample Track" height="12" border="0" /></a></th>
	</tr>
	<!-- ROW # 2-->
	<tr>
			<th class="TDShadeGrey" style="border-right:#7c92be dashed 1px;"><f:message key="cg.st.dest" /></th>
			<th class="TDShadeGrey" style="border-right:#7c92be dashed 1px;"><f:message key="cg.st.code" /></th>
			<th class="TDShadeGrey" style="border-right:#7c92be dashed 1px;"><f:message key="cg.st.sent.qty" /></th>
			<th class="TDShadeGrey" style="border-right:#7c92be dashed 1px;"><f:message key="cg.st.sent.date" /></th>
			<th class="TDShadeGrey" style="border-right:#7c92be dashed 1px;"><f:message key="cg.st.sent.time" /></th>
			<th class="TDShadeGrey" style="border-right:#7c92be dashed 1px;"><f:message key="cg.st.recvd" /></th>
			<th class="TDShadeGrey" style="border-right:#7c92be dashed 1px;"><f:message key="cg.st.recvd.date" /></th>
			<th class="TDShadeGrey" style="border-right:#7c92be dashed 1px;"><f:message key="cg.st.recvd.time" /></th>
			<th class="TDShadeGrey" style="border-right:#7c92be dashed 1px;"><f:message key="cg.st.recvd.storage" /></th>
			<th class="TDShadeGrey" style="border-right:#7c92be dashed 1px;"><f:message key="cg.st.recvd.modified" /></th>
			<th class="TDShadeGrey" ><f:message key="cg.st.recvd.timestamp" /></th>
	</tr>
		<!-- ROW # N DATA STARTS LOOP -->
	<c:forEach items="${command.stDetailFormList}" var="sampleTracks" varStatus="counter">
	<tr>
			<td class="TDShadeBlue" style="border-right:#7c92be dashed 1px;"><form:input id="stDestination${counter.index}" cssClass="inputBox" path="stDetailFormList[${counter.index}].description" /></td>
			<td class="TDShadeBlue" style="border-right:#7c92be dashed 1px;"><form:input id="stCode${counter.index}" cssClass="inputBox" path="stDetailFormList[${counter.index}].sampleCode" /></td>
			<td class="TDShadeBlue" style="border-right:#7c92be dashed 1px;"><form:input id="stNumOfSamplesSent${counter.index}" cssClass="inputBox" path="stDetailFormList[${counter.index}].countSent" size="4" /></td>
			<td class="TDShadeBlue" style="border-right:#7c92be dashed 1px;"><form:input id="stDateSent${counter.index}" cssClass="inputBox" path="stDetailFormList[${counter.index}].dateSent" size="8"/>&nbsp;&nbsp;<a href="#aa" onClick="displayCalendar(document.sampleTrackingCGForm.stDateSent${counter.index},'dd/m/yyyy',this)"><img src=" images/calendar.gif" width="15" height="17" align="middle" border="0" /></a></td>
			<td class="TDShadeBlue" style="border-right:#7c92be dashed 1px;">
			<div id="timeSentDiv">
				<code>$('#timeSentDiv input').ptTimeSelect();</code>
				<form:input id="stCode${counter.index}" cssClass="inputBox" path="stDetailFormList[${counter.index}].timeSent" size="6"/>
			</div>
			</td>
			<td class="TDShadeBlue" style="border-right:#7c92be dashed 1px;"><form:input id="stNumOfSamplesReceived${counter.index}" cssClass="inputBox" path="stDetailFormList[${counter.index}].countReceived" size="4" /></td>
			<td class="TDShadeBlue" style="border-right:#7c92be dashed 1px;"><form:input id="stDateReceived${counter.index}" cssClass="inputBox" path="stDetailFormList[${counter.index}].dateReceived" size="8"/>&nbsp;&nbsp;<a href="#kk" onClick="displayCalendar(document.sampleTrackingCGForm.stDateReceived${counter.index},'dd/m/yyyy',this)"><img src=" images/calendar.gif" width="15" height="17" align="middle" border="0" /></a></td>
			<td class="TDShadeBlue" style="border-right:#7c92be dashed 1px;">
				<div id="timeReceivedDiv">
					<code>$('#timeReceivedDiv input').ptTimeSelect();</code>
					<form:input id="stCode${counter.index}" cssClass="inputBox" path="stDetailFormList[${counter.index}].timeReceived" size="6"/>
				</div>
			</td>
			<td class="TDShadeBlue" style="border-right:#7c92be dashed 1px;"><form:input id="stStorageLoc${counter.index}" cssClass="inputBox" path="stDetailFormList[${counter.index}].storageLocation" /></td>
			<td class="TDShadeBlue" style="border-right:#7c92be dashed 1px;">&nbsp;${sampleTracks.lastModifiedBy}</td>
			<td class="TDShadeBlue">&nbsp;${sampleTracks.lastModified}</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan=11>
			<table width="100%" cellpadding=0 cellspacing=0 class="applTableBot"><tr><td>
				<input name="Save" type="button" class="button1" value="Save" onclick="submitSampleTrackForm()"/></td>
			</tr></table>
		</td>
	</tr>
	</tbody>
</table>
</div>
</form:form>
</body>
</html>