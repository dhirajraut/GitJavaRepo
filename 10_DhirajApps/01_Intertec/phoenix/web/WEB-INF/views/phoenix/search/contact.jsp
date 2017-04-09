<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script	type="text/javascript" src="js/ce/genericlookup.js"></script>


<table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="padding:right:5px;">
<tr>
	<td width="20%" class="TDShade" nowrap><f:message key="contactID"/>:</td>
	<td width="80%" class="TDShadeBlue">

	 <form:input cssClass="inputBox" path="contactId"/>
	 <form:errors path="contactId"/> 
	</td>
</tr>

        <tr>
           <td width="20%" class="TDShade" nowrap><f:message key="firstName"/>:</td>
            <td width="80%" class="TDShadeBlue">
             <form:input cssClass="inputBox" maxlength="128" path="firstName" />
            </td>
         </tr>
         
          <tr>
              <td width="20%" class="TDShade" nowrap><f:message key="lastName"/>:</td>
              <td width="80%" class="TDShadeBlue">
             <form:input cssClass="inputBox" maxlength="128" path="lastName" />
           </td>				 
         </tr>

</table>