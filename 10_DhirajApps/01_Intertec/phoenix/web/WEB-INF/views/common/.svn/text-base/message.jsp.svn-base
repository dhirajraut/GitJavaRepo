<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html" 
import="com.intertek.util.SecurityUtil" %>

<p>Hello <%= SecurityUtil.getUser().getFirstName()%> <%= SecurityUtil.getUser().getLastName() %>:</p>

<p>${description} <c:if test="${backUrl != null}"><a href="${backUrl}" style="color:blue;">${backUrlDescription}</a></c:if></p>

<c:if test="${jobLockedBy != null}"><h3>The job (${jobNumber}) is currently being edited by: ${jobLockedBy}</h3></c:if>

<br><br><br><br>
