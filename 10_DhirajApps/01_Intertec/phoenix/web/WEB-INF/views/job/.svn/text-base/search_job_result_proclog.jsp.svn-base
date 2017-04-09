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
    <th nowrap>*Time Zone</th>
    <th nowrap style="width:150px;">Sample Received Date</th>
    <th nowrap>Time</th>
    <th nowrap>Prelim Report Date</th>
    <th nowrap>Time</th>
    <th nowrap>Sample Shipped Date</th>
    <th nowrap>Time</th>
    <th nowrap>Final Report Date</th>
    <th nowrap>Time</th>
    <th nowrap>Distributed Date</th>
    <th nowrap>Time</th>
  </tr>
  <c:forEach items="${jobs}" var="job" varStatus="status">
    <tr valign='center'>
      <td height="25" align='left'  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_TIMEZONE1$0' value="CST" style="width:54px; " maxlength='9' class="inputBox"  />&nbsp;<a name='ITS_NOM_LOG_WRK_TIMEZONE1$prompt$0' href="#"><img src="images/lookup.gif" alt='Look up Time Zone' title='Look up Time Zone' name='ITS_NOM_LOG_WRK_TIMEZONE1$prompt$img$0' border='0' align='absmiddle' /></a></td>
      <td align='left'  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_SAMPLE_REC_DT$0' value="" style="width:117px; " maxlength='10' class="inputBox" />&nbsp;<a name='ITS_NOM_LOG_WRK_ITS_SAMPLE_REC_DT$prompt$0' href="#"><img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' name='ITS_NOM_LOG_WRK_ITS_SAMPLE_REC_DT$prompt$img$0' border='0' align='absmiddle' /></a></td>
      <td align='left'  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_SAMPLE_REC_TM$0' value="" style="width:58px; " maxlength='5' class="inputBox"  /></td>
      <td align='left'  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_PRELIM_RPT_DT$0' value="" style="width:87px; " maxlength='10' class="inputBox"/>&nbsp;<a name='ITS_NOM_LOG_WRK_ITS_PRELIM_RPT_DT$prompt$0' href="#"><img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' name='ITS_NOM_LOG_WRK_ITS_PRELIM_RPT_DT$prompt$img$0' border='0' align='absmiddle' /></a></td>
      <td align='left'  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_PRELIM_RPT_TM$0' value="" style="width:75px; " maxlength='5' class="inputBox"  /></td>
      <td align='left'  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_SAMP_SHIP_DT$0' value="" style="width:112px; " maxlength='10' class="inputBox"/>&nbsp;<a name='ITS_NOM_LOG_WRK_ITS_SAMP_SHIP_DT$prompt$0' href="#"><img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' name='ITS_NOM_LOG_WRK_ITS_SAMP_SHIP_DT$prompt$img$0' border='0' align='absmiddle' /></a></td>
      <td align='left'  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_SAMP_SHIP_TM$0' value="" style="width:75px; " maxlength='5' class="inputBox"  /></td>
      <td align='left'  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_FINAL_RPT_DT$0' value="" style="width:97px; " maxlength='10' class="inputBox" />&nbsp;<a name='ITS_NOM_LOG_WRK_ITS_FINAL_RPT_DT$prompt$0' href="#"><img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' name='ITS_NOM_LOG_WRK_ITS_FINAL_RPT_DT$prompt$img$0' border='0' align='absmiddle' /></a></td>
      <td align='left'  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_FINAL_RPT_TM$0' value="" style="width:75px; " maxlength='5' class="inputBox"  /></td>
      <td align='left'  nowrap='nowrap'>
      <input type='text' name='ITS_NOM_LOG_WRK_ITS_DISTRIBUTED_DT$0' tabindex='360' value="" style="width:80px; " maxlength='10' class="inputBox" />&nbsp;<a name='ITS_NOM_LOG_WRK_ITS_DISTRIBUTED_DT$prompt$0' href="#"><img src="images/calendar.gif" width="15" height="17" alt='Choose a date' title='Choose a date' name='ITS_NOM_LOG_WRK_ITS_DISTRIBUTED_DT$prompt$img$0' border='0' align='absmiddle' /></a></td>
      <td align='right'  nowrap='nowrap'><input type='text' name='ITS_NOM_LOG_WRK_ITS_DISTRIBUTED_TM$0' value="" style="width:75px; " maxlength='5' class="inputBox"/></td>
    </tr>
  </c:forEach>
</table>

</BODY>
</HTML>

