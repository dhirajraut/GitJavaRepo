<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<HTML>
  <HEAD>
  <title>Intertek - Job Search Results</title>
  <script type="text/javascript" src="js/balloontip.js"></script>
  <script type="text/JavaScript" src="js/mm_menu.js"></script>
  <link rel="stylesheet" href="css/stylesheet.css" type="text/css">
  <link rel="stylesheet" type="text/css" href="css/ajaxtags.css" />
  <script src="js/dw_viewport.js" type="text/javascript"></script>
  <script src="js/dw_event.js" type="text/javascript"></script>
  <script src="js/dw_tooltip_sel.js" type="text/javascript"></script>
  <script type="text/javascript" src="scripts/prototype-1.4.0.js"></script>
  <script type="text/javascript" src="scripts/scriptaculous.js"></script>
  <script type="text/javascript" src="scripts/overlibmws.js"></script>
  <script type="text/javascript" src="scripts/ajaxtags-1.2-beta2.js"></script>
  <script type="text/javascript"  src="js/dw_event.js" ></script>

  <script type="text/javascript" src="js/dw_viewport.js"></script>
  <script type="text/javascript">
  // adjust horizontal and vertical offsets here
  // (distance from mouseover event which activates tooltip)
  Tooltip.offX = 4;  
  Tooltip.offY = 4;
  Tooltip.followMouse = false;  // must be turned off for hover-tip


  function doTooltip(e, msg) {
    if ( typeof Tooltip == "undefined" || !Tooltip.ready ) return;
    Tooltip.clearTimer();
    var tip = document.getElementById? document.getElementById(Tooltip.tipID): null;
    if ( tip && tip.onmouseout == null ) {
        tip.onmouseout = Tooltip.tipOutCheck;
        tip.onmouseover = Tooltip.clearTimer;
    }
    Tooltip.show(e, msg);
  }

  function hideTip() {
    if ( typeof Tooltip == "undefined" || !Tooltip.ready ) return;
    Tooltip.timerId = setTimeout("Tooltip.hide()", 300);
  }

  Tooltip.tipOutCheck = function(e) {
    e = dw_event.DOMit(e);
    // is element moused into contained by tooltip?
    var toEl = e.relatedTarget? e.relatedTarget: e.toElement;
    if ( this != toEl && !contained(toEl, this) ) Tooltip.hide();
  }

  // returns true of oNode is contained by oCont (container)
  function contained(oNode, oCont) {
    if (!oNode) return; // in case alt-tab away while hovering (prevent error)
    while ( oNode = oNode.parentNode ) if ( oNode == oCont ) return true;
    return false;
  }

  Tooltip.timerId = 0;
  Tooltip.clearTimer = function() {
    if (Tooltip.timerId) { clearTimeout(Tooltip.timerId); Tooltip.timerId = 0; }
  }

  Tooltip.unHookHover = function () {
      var tip = document.getElementById? document.getElementById(Tooltip.tipID): null;
      if (tip) {
          tip.onmouseover = null; 
          tip.onmouseout = null;
          tip = null;
      }
  }

  dw_event.add(window, "unload", Tooltip.unHookHover, true);

  </script>
  <style type="text/css">
  a:link { color:#33c }    
  a:visited { color:#339 }    
  div.select { text-align:center; margin-bottom:1.6em }

  /* This is where you can customize the appearance of the tooltip */
  div#tipDiv {
    position:absolute; visibility:hidden;
    left:0; top:0; z-index:1000;
    width:240px; height:auto; padding:3px; font-size:11px;
    font-family:Arial, Helvetica, sans-serif;
    border-color: #b0c8f2;
    border-style: double;
    filter:progid:DXImageTransform.Microsoft.gradient(gradientType=1, startColorstr=#ffffff, endColorstr=#dde7fa);
    background: url(images/tooltipbg.jpg) repeat-y;
    }

  </style>

  </HEAD>

  <BODY onLoad="Tooltip.init();">
    <tiles:insert attribute="body"/>
  </BODY>
</HTML>
