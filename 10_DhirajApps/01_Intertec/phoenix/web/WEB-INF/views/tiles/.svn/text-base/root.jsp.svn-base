<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>

<html>
<head>
  <title>Intertek - Job Management System</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="css/stylesheet.css" type="text/css">
  <script type="text/javaScript" src="js/tabs.js"></script>
  <script language="javaScript" src="js/flipmenu.js"></script>
  <script type="text/javaScript" src="js/lookup.js"></script>
  <script type="text/JavaScript" src="js/mm_menu.js"></script>
  <script type="text/javascript" src="js/balloontip.js"></script>
  <script type="text/javascript" src="js/globalFunctions.js"></script>
  <script type="text/javascript" src="js/services.js"></script>
  <script type="text/javascript" src="js/contracts.js"></script>
  <link rel="stylesheet" href="css/calendar.css?random=20051112" media="screen"></link>
  <link rel="stylesheet" href="css/mainmenustyle.css" media="screen"></link>
  <script type="text/javascript" src="js/calendar.js?random=20060118"></script>
	<script type="text/javascript" src="js/headerSearch.js"></script>

  <script type="text/javascript" src="scripts/prototype-1.4.0.js"></script>
  <script type="text/javascript" src="scripts/scriptaculous.js"></script>
  <script type="text/javascript" src="scripts/overlibmws.js"></script>
  <script type="text/javascript" src="scripts/ajaxtags-1.2-beta2.js"></script>
  <script type="text/javascript"  src="js/dw_event.js" ></script>

  <script type="text/javascript" src="js/dw_viewport.js"></script>


  <link rel="stylesheet" type="text/css" href="css/ajaxtags.css" />

  <script type="text/JavaScript" src="js/qm.js"></script>
  <script type="text/javascript"  src="js/dw_tooltip_sel.js"></script>
  
  <script type="text/JavaScript">var qmad = new Object();qmad.bvis="";qmad.bhide="";qmad.bhover="";</script>
    
  <script type="text/JavaScript">

    /*******  Menu 0 Add-On Settings *******/
    var a = qmad.qm0 = new Object();

    // IE Over Select Fix Add On
    a.overselects_active = true;


  </script> 

  <!-- Add-On Script Files -->
  <script type="text/JavaScript" src="js/qm_over_select.js"></script>


  <script src="js/dhtml.js"></script>

  <style type="text/css">
    a:link { color:#33c }    
    a:visited { color:#339 }    
    div.select { text-align:center; margin-bottom:1.6em }

    /* This is where you can customize the appearance of the tooltip */
    div#tipDiv {
      position:absolute; visibility:hidden;
      left:0; top:0; z-index:1000;
      width:150px; height:auto; padding:3px; font-size:11px;
      font-family:Arial, Helvetica, sans-serif;
      border-color: #cccccc;
      border-style: solid;
      border-width: 2px;
      filter:progid:DXImageTransform.Microsoft.gradient(gradientType=0, startColorstr=#ffffff, endColorstr=#dde7fa);
      background-image:url(images/submenubg.jpg);
      }

    .hrnew {
      color: #cccccc;
      background-color: #f00;
      height: solid 1px;
    }
  </style>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="MM_preloadImages('images/seeall_ovr.gif');Tooltip.init();">
  <div id="faderPane" style="visibility:hidden; display:none; z-index:1;Position : Absolute ;Left : 1px ;Top : 1px ;">
    <IMG src="images/fake-alpha-999.gif"> 
  </div>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="1008" colspan="3" background="images/intopnmainbg.jpg">
        <tiles:insert attribute="header"/>
      </td>
    </tr>
    <tr>
      <td height="23" colspan="3" background="images/intopgreymenubg.jpg">
        <tiles:insert attribute="menu"/>
      </td>
    </tr>
  </table>
  <tiles:insert attribute="body"/>
  <tiles:insert attribute="footer"/>
</body>
<!---------------------------------------Search Status Window START------------------------------------------------->
<div class="sample_popup" id="searchStatus"
	style="visibility: hidden; display: none;">
<div class="menu_form_header" id="searchStatus_drag" style="width: 400px;">&nbsp;&nbsp;&nbsp; Please Wait</div>
<div class="menu_form_body" id="searchStatusbody"
	style="width: 400px; height: 230px; padding-left: 4px; overflow-y: hidden;">
<html>	
<BR><BR><BR><BR>&nbsp;&nbsp;&nbsp;&nbsp; <img src="images/loading.gif">&nbsp;Please wait.............<BR>&nbsp;
</html>
</div>
</div>
<!------------------------------------Search Status Window END----------------------------------------------------->

</html>
