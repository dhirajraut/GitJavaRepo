<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<title>Intertek - Job Search Results</title>
<link rel="stylesheet" href="css/stylesheet.css" type="text/css">
</HEAD>

<BODY>
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style="border-left-width:0px">
  <tr>
    <th nowrap>Coordinator</th>
    <th nowrap>Inspector</th>
    <th nowrap>*Time Zone</th>
    <th nowrap>Inspector Notified</th>
    <th nowrap>Time</th>
    <th nowrap>Inspector Arrived</th>
    <th nowrap>Time</th>
    <th nowrap>Arrive Date</th>
    <th nowrap>Time</th>
    <th nowrap>Dock Date</th>
    <th nowrap>Time</th>
    <th nowrap>Hose On Date</th>
    <th nowrap>Time</th>
    <th nowrap>Est Start Date</th>
    <th nowrap>Time</th>
    <th nowrap>Commence Date</th>
    <th nowrap>Time</th>
    <th nowrap>Delay Date</th>
    <th nowrap>Time</th>
    <th nowrap>Delay End Date</th>
    <th nowrap>Time</th>
    <th nowrap>Est Complete Dt</th>
    <th nowrap>Time</th>
    <th nowrap>Complete Date</th>
    <th nowrap>Time</th>
    <th nowrap>Hose Off Date</th>
    <th nowrap>Time</th>
    <th nowrap>Released Date</th>
    <th nowrap>Time</th>
    <th nowrap>Summary Sent</th>
    <th nowrap>Agent</th>
    <th nowrap>Phone No.</th>
    <th nowrap>Towing Company</th>
    <th nowrap>Phone No.</th>
    <th nowrap>Job Finish Date</th>
  </tr>
  <c:forEach items="${jobs}" var="job" varStatus="status">
    <tr valign='center'>
      <td  align='left' valign="middle"  nowrap>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_COORDINATOR$0' value="" style="width:112px; " maxlength='35' class="inputBox"/></td>
      <td align='left' valign="middle"  nowrap>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_INSPECTOR$0' value="" style="width:119px; " maxlength='30' class="inputBox"  /></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_TIMEZONE$0' value="CST" style="width:49px; " maxlength='9' class="inputBox"  />&nbsp;<a name='ITS_NOM_LOG_WRK_TIMEZONE$prompt$0' href="#"><img src="images/lookup.gif" alt='Look up Time Zone' title='Look up Time Zone' border='0' align='absmiddle' /></a></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_INSP_NOTIFY_DT$0' value="" style="width:108px; " maxlength='10' class="inputBox" />&nbsp;<a name='ITS_NOM_LOG_WRK_ITS_INSP_NOTIFY_DT$prompt$0' href="#"><img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' border='0' align='absmiddle' /></a></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_INSP_NOTIFY_TM$0' value="" style="width:60px; " maxlength='7' class="inputBox"  /></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_INSP_ARRIVE_DT$0' value="" style="width:80px; " maxlength='10' class="inputBox"  />&nbsp;<a name='ITS_NOM_LOG_WRK_ITS_INSP_ARRIVE_DT$prompt$0' href="#"><img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' border='0' align='absmiddle' /></a></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_INSP_ARRIVE_TM$0' value="" style="width:56px; " maxlength='7' class="inputBox"  /></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_ARRIVE_DT$0' value="" style="width:70px; " maxlength='10' class="inputBox" />&nbsp;<a name='ITS_NOM_LOG_WRK_ITS_ARRIVE_DT$prompt$0' href="#"><img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' border='0' align='absmiddle' /></a></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_ARRIVE_TM$0' maxlength='7' class="inputBox"  /></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_DOCK_DT$0' value="" style="width:68px; " maxlength='10' class="inputBox"/>&nbsp;<a name='ITS_NOM_LOG_WRK_ITS_DOCK_DT$prompt$0' href="#"><img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' border='0' align='absmiddle' /></a></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_DOCK_TM$0' value="" style="width:53px; " maxlength='7' class="inputBox"  /></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_HOSE_ON_DT$0' value="" style="width:71px; " maxlength='10' class="inputBox" />&nbsp;<a name='ITS_NOM_LOG_WRK_ITS_HOSE_ON_DT$prompt$0' href="#"><img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' border='0' align='absmiddle' /></a></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_HOSE_ON_TM$0' value="" style="width:53px; " maxlength='7' class="inputBox"  /></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_EST_START_DT$0' value="" style="width:71px; " maxlength='10' class="inputBox"/>&nbsp;<a name='ITS_NOM_LOG_WRK_ITS_EST_START_DT$prompt$0' tabindex='371' href="#"><img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' border='0' align='absmiddle' /></a></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_EST_START_TM$0' value="" style="width:53px; " maxlength='7' class="inputBox"  /></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_COMMENCE_DT$0' value="" style="width:79px; " maxlength='10' class="inputBox"/>&nbsp;<a name='ITS_NOM_LOG_WRK_ITS_COMMENCE_DT$prompt$0' href="#"><img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' border='0' align='absmiddle' /></a></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_COMMENCE_TM$0' value="" style="width:53px; " maxlength='7' class="inputBox"  /></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_DELAY_DT$0' value="" style="width:73px; " maxlength='10' class="inputBox"/>&nbsp;<a name='ITS_NOM_LOG_WRK_ITS_DELAY_DT$prompt$0' href="#"><img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' border='0' align='absmiddle' /></a></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_DELAY_TM$0' value="" style="width:53px; " maxlength='7' class="inputBox"  /></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_DELAY_END_DT$0' value="" style="width:71px; " maxlength='10' class="inputBox"/>&nbsp;<a name='ITS_NOM_LOG_WRK_ITS_DELAY_END_DT$prompt$0' href="#"><img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' border='0' align='absmiddle' /></a></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_DELAY_END_TM$0' value="" style="width:53px; " maxlength='7' class="inputBox"  /></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_EST_COMPLET_DT$0' value="" style="width:78px; " maxlength='10' class="inputBox"/>&nbsp;<a name='ITS_NOM_LOG_WRK_ITS_EST_COMPLET_DT$prompt$0' href="#"><img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' border='0' align='absmiddle' /></a></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_EST_COMPLET_TM$0' value="" style="width:53px; " maxlength='7' class="inputBox"  /></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_COMPLETE_DT$0' value="" style="width:71px; " maxlength='10' class="inputBox"/>&nbsp;<a name='ITS_NOM_LOG_WRK_ITS_COMPLETE_DT$prompt$0' href="#"><img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' border='0' align='absmiddle' /></a></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_COMPLETE_TM$0' value="" style="width:53px; " maxlength='7' class="inputBox"  /></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_HOSE_OFF_DT$0' value="" style="width:73px; " maxlength='10' class="inputBox"/>&nbsp;<a name='ITS_NOM_LOG_WRK_ITS_HOSE_OFF_DT$prompt$0' href="#"><img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' border='0' align='absmiddle' /></a></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_HOSE_OFF_TM$0' value="" style="width:53px; " maxlength='7' class="inputBox"  /></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_RELEASED_DT$0' value="" style="width:71px; " maxlength='10' class="inputBox"/>&nbsp;<a name='ITS_NOM_LOG_WRK_ITS_RELEASED_DT$prompt$0' href="#"><img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' border='0' align='absmiddle' /></a></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_RELEASED_TM$0' value="" style="width:53px; " maxlength='7' class="inputBox"  /></td>
      <td align='left' valign="middle"  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_JOBCLOS_SUM_DT$0' value="" style="width:65px; " maxlength='10' class="inputBox"/>&nbsp;<a name='ITS_NOM_LOG_WRK_ITS_JOBCLOS_SUM_DT$prompt$0' href="#"><img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' border='0' align='absmiddle' /></a></td>
      <td align='left' valign="middle" >
      <span >&nbsp;</span></td>
      <td align='left' valign="middle" >
      <span >&nbsp;</span></td>
      <td align='left' valign="middle" >
      <span >&nbsp;</span></td>
      <td align='left' valign="middle" >
      <span >&nbsp;</span></td>
      <td height="25" align='left' valign="middle" >
      <span >&nbsp;</span></td>
    </tr>
  </c:forEach>
</table>
</BODY>
</HTML>


