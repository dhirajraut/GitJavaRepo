<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html" 
import="com.intertek.util.SecurityUtil" %>
<%@page import="com.intertek.util.Constants" %>
<table width="1000" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="202"><img src="images/Intertek_logo2.jpg" width="202" height="56" id="Image2"></td>
    <td width="347"><img src="images/intopbg01.jpg" width="347" height="56"></td>
    <td width="451" align="right" background="images/intopbg02.jpg" style="background-repeat:no-repeat">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="200">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
              <td align="right" class="headerText">
                Welcome 
                  <span class="username">
                <%= SecurityUtil.getUser() != null ? SecurityUtil.getUser().getFirstName() : ""%> 
                <%= SecurityUtil.getUser() != null ? SecurityUtil.getUser().getLastName() : ""%>
              </span> 
              </td> 
              </tr>
              <tr>
                <td align="right" class="headerText">Home | Announcements | Help</td>
              </tr>
            </table>
          </td>
          <td width="1" bgcolor="#B9C7E2"><img src="images/spacer.gif" width="1" height="1"></td>
          <td width="250">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td class="headerText">&nbsp;
                  <!-- Current date script -->
                  <script>
                  
                  var mydate=new Date()
                  var year=mydate.getYear()
                  if (year < 1000)
                  year+=1900
                  var day=mydate.getDay()
                  var month=mydate.getMonth()
                  var daym=mydate.getDate()
                  if (daym<10)
                  daym="0"+daym
                  var dayarray=new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday")
                  var montharray=new Array("January","February","March","April","May","June","July","August","September","October","November","December")
                  document.write("<font color='ffffff' face='Verdana'>"+dayarray[day]+", "+montharray[month]+" "+daym+", "+year+"</font>")

                  </script>
                  <!-- Current date script End -->
                </td>
              </tr>
              <tr>                
				<td class="headerText">
					&nbsp;&nbsp;Environment: <%=Constants.machineName%>
					&nbsp;&nbsp;<a href="logout.htm" class="smallLink1">Sign Out</a> 
				</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
            
         
