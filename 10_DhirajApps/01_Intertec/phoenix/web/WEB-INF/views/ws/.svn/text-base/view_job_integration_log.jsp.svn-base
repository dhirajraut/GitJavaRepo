<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>

<table width="98%" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:15px;">
  <tbody>    
    <tr>
      <th class="TDShade">Integration Type</th>
      <th class="TDShade">Integration User</th>
      <th class="TDShadeBlue">Date Time</th>
      <th class="TDShadeBlue">Integration Status</th>
      <th class="TDShadeBlue" width="20%">Result</th>
      <th class="TDShadeBlue" width="20%">Error Message</th>
    </tr>
    <c:forEach items="${command.wsEntities}" var="wsEntity" varStatus="status">
      <tr>
        <td class="TDShade">${wsEntity.type}</td>
        <td class="TDShade">${wsEntity.createUser}</td>
        <td class="TDShadeBlue">${wsEntity.createTime}</td>
        <td class="TDShadeBlue">${wsEntity.status}</td>
        <td class="TDShadeBlue">${wsEntity.result}</td>
        <td class="TDShadeBlue">${wsEntity.message}</td>
      </tr>    
    </c:forEach>
  </tbody>
</table>
