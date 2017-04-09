<html>
<head>
<title>Intertek - Job Instructions</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="css/stylesheet.css" type="text/css">
<script type="text/javaScript" src="js/tabs.js"></script>
<script language="javaScript" src="js/flipmenu.js"></script>
<script type="text/javaScript" src="js/lookup.js"></script>
<script type="text/JavaScript" src="js/mm_menu.js"></script>
<script type="text/javascript" src="js/balloontip.js"></script>
<script type="text/javascript" src="js/globalFunctions.js"></script>
<link rel="stylesheet" href="css/calendar.css?random=20051112"
	media="screen"></link>
<link rel="stylesheet" href="css/mainmenustyle.css" media="screen"></link>
<script type="text/javascript" src="js/calendar.js?random=20060118"></script>
<script type="text/JavaScript" src="js/qm.js"></script>
<script type="text/JavaScript">var qmad = new Object();qmad.bvis="";qmad.bhide="";qmad.bhover="";</script>



<script type="text/javascript">
var xmlHttp;
function initXmlHttp(callback){
	try{  // Firefox, Opera 8.0+, Safari  
	  	xmlHttp=new XMLHttpRequest();  
	}
	catch (e){  // Internet Explorer  
		try{    
			xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");    
		}
		catch (e){
			try{      
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");      
			}
			catch (e){
				alert("Your browser does not support AJAX!");      
				return false;      
			}    
		}  
	}

	/*
	0 The request is not initialized 
	1 The request has been set up 
	2 The request has been sent 
	3 The request is in process 
	4 The request is complete 
*/	
	xmlHttp.onreadystatechange=callback;
}

function createProject(form){
	if(confirm('Are you sure you want to create the project? Have you verified the line items?')){
		var projectTypeIndex=form.projectType.selectedIndex;
		//form.projectNumber.value="12354648454";
		initXmlHttp(function(){
			if(xmlHttp.readyState==4){
				form.projectNumber.value=xmlHttp.responseText;
			}
		});
		xmlHttp.open("POST", "phx_create_project.htm", true);
		xmlHttp.send("hello=1&x=true");
		
	}
}


	
		/*******  Menu 0 Add-On Settings *******/
		var a = qmad.qm0 = new Object();

		// IE Over Select Fix Add On
		a.overselects_active = true;


	</script>

<!-- Add-On Script Files -->
<script type="text/JavaScript" src="js/qm_over_select.js"></script>


<script src="../js/dhtml.js"></script>

<script src="../js/dw_viewport.js" type="text/javascript"></script>
<script src="../js/dw_event.js" type="text/javascript"></script>
<script src="../js/dw_tooltip_sel.js" type="text/javascript"></script>
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
a:link {
	color: #33c
}

a:visited {
	color: #339
}

div.select {
	text-align: center;
	margin-bottom: 1.6em
}

/* This is where you can customize the appearance of the tooltip */
div#tipDiv {
	position: absolute;
	visibility: hidden;
	left: 0;
	top: 0;
	z-index: 1000;
	width: 400px;
	height: auto;
	padding: 3px;
	font-size: 11px;
	font-family: Arial, Helvetica, sans-serif;
	border-color: #b0c8f2;
	border-style: double;
	filter: progid : DXImageTransform . Microsoft .
		gradient(gradientType = 1, startColorstr = #ffffff, endColorstr =
		#dde7fa);
	background: url(../images/tooltipbg.jpg) repeat-y;
}
</style>

</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0" onLoad="Tooltip.init()">
<!-- ---------- HEADER ---------------------------------------- -->
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="990" colspan="3" background="../images/intopnmainbg.jpg">
		<table width="1000" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="202"><img src="../images/Intertek_logo2.jpg"
					width="202" height="56" id="Image2"></td>
				<td width="347"><img src="../images/intopbg01.jpg" width="347"
					height="56"></td>
				<td width="451" align="right" background="../images/intopbg02.jpg"
					style="background-repeat: no-repeat">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="200">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align="right" class="headerText">Welcome <span
									class="username">Bill Rich !</span></td>
							</tr>
							<tr>
								<td align="right" class="headerText">Home | Announcements |
								Help</td>
							</tr>
						</table>
						</td>
						<td width="1" bgcolor="#B9C7E2"><img
							src="../images/spacer.gif" width="1" height="1"></td>
						<td width="250">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="headerText">&nbsp; <!-- -------------------- Current date script ------------------------- -->
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
				
				</script> <!-- ------------- Current date script End -------------------- -->
								</td>
							</tr>
							<tr>
								<td class="headerText">&nbsp;&nbsp;<a href="../login.html"
									class="smallLink1">Sign Out</a></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td height="23" colspan="3" background="../images/intopgreymenubg.jpg">
		<table width="1000" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="40" height="23"><img src="../images/spacer.gif"
					width="40" height="1"></td>
				<td width="640"><!-- TOP MENU START --><!-- #BeginLibraryItem "/Library/mainmenu.lbi" -->
				<div id='masthead'>
				<div id='container'>
				<div id="primary_nav">
				<ul id="navmenu">
					<li><a href="#"><IMG SRC="../images/medium_red_down.gif"
						BORDER="0" style="float: left; margin-top: 10px;">Quotes</a>
					<ul>
						<li><a href="#"><IMG
							SRC="../images/medium_red_right.gif" BORDER="0"
							style="float: left; margin-top: 4px;"><IMG
							SRC="../images/arrows_6_hl.gif" BORDER="0"
							style="float: right; margin-top: 3px;">Create</a>
						<ul style="width: 180px;">
							<li style="width: 180px;"><a href="#"><IMG
								SRC="../images/medium_red_right.gif" BORDER="0"
								style="float: left; margin-top: 4px;">OCA Inspection</a></li>
							<li style="width: 180px;"><a href="#"><IMG
								SRC="../images/medium_red_right.gif" BORDER="0"
								style="float: left; margin-top: 4px;">OCA Analytical
							Services</a></li>
							<li style="width: 180px;"><a href="#"><IMG
								SRC="../images/medium_red_right.gif" BORDER="0"
								style="float: left; margin-top: 4px;">OCA Outsourcing</a></li>
							<li style="width: 180px;"><a href="#"><IMG
								SRC="../images/medium_red_right.gif" BORDER="0"
								style="float: left; margin-top: 4px;">OCA Marine</a></li>
							<li style="width: 180px;"><a href="#"><IMG
								SRC="../images/medium_red_right.gif" BORDER="0"
								style="float: left; margin-top: 4px;">Consumer Goods</a></li>
							<li style="width: 180px;"><a href="#"><IMG
								SRC="../images/medium_red_right.gif" BORDER="0"
								style="float: left; margin-top: 4px;">Commercial and
							Electrical</a></li>
							<li style="width: 180px;"><a href="#"><IMG
								SRC="../images/medium_red_right.gif" BORDER="0"
								style="float: left; margin-top: 4px;">Government Services</a>
							</li>
						</ul>
						</li>

						<li><a href="#"><IMG
							SRC="../images/medium_red_right.gif" BORDER="0"
							style="float: left; margin-top: 4px;">Search</a></li>
					</ul>


					</li>


					<li><a href="#"><IMG SRC="../images/medium_red_down.gif"
						BORDER="0" style="float: left; margin-top: 10px;">PO</a>

					<ul>
						<li><a href="../projects/prj_createproject.html"><IMG
							SRC="../images/medium_red_right.gif" BORDER="0"
							style="float: left; margin-top: 4px;">Create</a></li>
						<li><a href="../projects/prj_projadvsearch.html"><IMG
							SRC="../images/medium_red_right.gif" BORDER="0"
							style="float: left; margin-top: 4px;">Search</a></li>
					</ul>
					</li>

					<li><a href="#"><IMG SRC="../images/medium_red_down.gif"
						BORDER="0" style="float: left; margin-top: 10px;">Jobs</a>
					<ul>
						<li><a href="#"><IMG
							SRC="../images/medium_red_right.gif" BORDER="0"
							style="float: left; margin-top: 4px;"><IMG
							SRC="../images/arrows_6_hl.gif" BORDER="0"
							style="float: right; margin-top: 3px;">Create</a>
						<ul style="width: 180px;">
							<li style="width: 180px;"><a href="ins_createjobs.html"><IMG
								SRC="../images/medium_red_right.gif" BORDER="0"
								style="float: left; margin-top: 4px;">OCA Inspection</a></li>
							<li style="width: 180px;"><a href="#"><IMG
								SRC="../images/medium_red_right.gif" BORDER="0"
								style="float: left; margin-top: 4px;">OCA Analytical
							Services</a></li>
							<li style="width: 180px;"><a href="#"><IMG
								SRC="../images/medium_red_right.gif" BORDER="0"
								style="float: left; margin-top: 4px;">OCA Outsourcing</a></li>
							<li style="width: 180px;"><a href="#"><IMG
								SRC="../images/medium_red_right.gif" BORDER="0"
								style="float: left; margin-top: 4px;">OCA Marine</a></li>
							<li style="width: 180px;"><a href="#"><IMG
								SRC="../images/medium_red_right.gif" BORDER="0"
								style="float: left; margin-top: 4px;">Consumer Goods</a></li>
							<li style="width: 180px;"><a href="CE_createjobs.html"><IMG
								SRC="../images/medium_red_right.gif" BORDER="0"
								style="float: left; margin-top: 4px;">Commercial and
							Electrical</a></li>
							<li style="width: 180px;"><a href="#"><IMG
								SRC="../images/medium_red_right.gif" BORDER="0"
								style="float: left; margin-top: 4px;">Government Services</a>
							</li>
						</ul>
						</li>

						<li><a href="#"><IMG
							SRC="../images/medium_red_right.gif" BORDER="0"
							style="float: left; margin-top: 4px;">Search</a></li>
						<li><a href="#"><IMG
							SRC="../images/medium_red_right.gif" BORDER="0"
							style="float: left; margin-top: 4px;"><IMG
							SRC="../images/arrows_6_hl.gif" BORDER="0"
							style="float: right; margin-top: 3px;">Log</a>
						<ul style="width: 180px;">
							<li style="width: 180px;"><a href="job_jobjadvsearch.html"><IMG
								SRC="../images/medium_red_right.gif" BORDER="0"
								style="float: left; margin-top: 4px;">OCNA</a></li>
							<li style="width: 180px;"><a href="job_jobjadvsearch.html"><IMG
								SRC="../images/medium_red_right.gif" BORDER="0"
								style="float: left; margin-top: 4px;">CE</a></li>
						</ul>
						</li>
						<li><a href="#"><IMG
							SRC="../images/medium_red_right.gif" BORDER="0"
							style="float: left; margin-top: 4px;"><IMG
							SRC="../images/arrows_6_hl.gif" BORDER="0"
							style="float: right; margin-top: 3px;">System</a>
						<ul>


							<li><a href="#"><IMG
								SRC="../images/medium_red_right.gif" BORDER="0"
								style="float: left; margin-top: 4px;">Shipping Agents</a></li>
							<li><a href="#"><IMG
								SRC="../images/medium_red_right.gif" BORDER="0"
								style="float: left; margin-top: 4px;">Towing Companies</a></li>
							<li><a href="#"><IMG
								SRC="../images/medium_red_right.gif" BORDER="0"
								style="float: left; margin-top: 4px;">Service Locations</a></li>
						</ul>

						</li>
						<li><a href="#" title="Consolidated Invoices"><IMG
							SRC="../images/medium_red_right.gif" BORDER="0"
							style="float: left; margin-top: 4px;">Consolidated Inv.</a></li>
					</ul>

					</li>

					<li><a href="#"><IMG SRC="../images/medium_red_down.gif"
						BORDER="0" style="float: left; margin-top: 10px;">Customers</a>
					<ul>
						<li><a href="#"><IMG
							SRC="../images/medium_red_right.gif" BORDER="0"
							style="float: left; margin-top: 4px;">Quick Create</a></li>
						<li><a href="#"><IMG
							SRC="../images/medium_red_right.gif" BORDER="0"
							style="float: left; margin-top: 4px;">Customers</a></li>
						<li><a href="#"><IMG
							SRC="../images/medium_red_right.gif" BORDER="0"
							style="float: left; margin-top: 4px;">Contact</a></li>
					</ul>
					</li>

					<li><a href="#">Contracts</a></li>

					<li><a href="#">Reports</a></li>

					<li><a href="#"><IMG SRC="../images/medium_red_down.gif"
						BORDER="0" style="float: left; margin-top: 10px;">Set-up</a>

					<ul>
						<li><a href="#"><IMG SRC="../images/medium_red_right.gif"
							BORDER="0" style="float: left; margin-top: 4px;">User</a></li>
						<li><a href="#"><IMG SRC="../images/medium_red_right.gif"
							BORDER="0" style="float: left; margin-top: 4px;">Permission
						Lists</a></li>
						<li><a href="#"><IMG SRC="../images/medium_red_right.gif"
							BORDER="0" style="float: left; margin-top: 4px;">Roles</a></li>
						<li><a href="#"><IMG SRC="../images/medium_red_right.gif"
							BORDER="0" style="float: left; margin-top: 4px;">Drop Down
						Values</a></li>
						<li><a href="#"><IMG SRC="../images/medium_red_right.gif"
							BORDER="0" style="float: left; margin-top: 4px;">Countries</a>
						</li>
						<li><a href="#"><IMG SRC="../images/medium_red_right.gif"
							BORDER="0" style="float: left; margin-top: 4px;">Business
						Unit</a></li>
						<li><a href="#"><IMG SRC="../images/medium_red_right.gif"
							BORDER="0" style="float: left; margin-top: 4px;">Branch</a></li>
						<li><a href="#"><IMG SRC="../images/medium_red_right.gif"
							BORDER="0" style="float: left; margin-top: 4px;">VAT Rates</a>
						</li>
						<li><a href="../setup/sys_controlnumber.html"><IMG
							SRC="../images/medium_red_right.gif" BORDER="0"
							style="float: left; margin-top: 4px;">Control Number</a></li>
					</ul>
					</li>


				</ul>
				<!-- Primary Navigation Bar Ends--></div>
				</div>
				</div>
				<!-- #EndLibraryItem --><!-- TOP MENU START END--></td>
				<td width="70" align="center"><!-- #BeginLibraryItem "/Library/see all menu.lbi" -->

				<div id="qm0" class="qmmc"><a href="#"><img
					src="../images/spacer.gif" height="12" align="absmiddle" border="0">
				</a>
				<div>

				<table cellpadding="0" cellspacing="0" class="allTableStyle"
					style="border: #DBE2F2 1px solid;">

					<tr>
						<td valign="top" style="padding-right: 10px;">
						<p><strong>Quotes</strong><a
							style="font-weight: bold; color: #1f55ba;"> Create</a> <a
							href="#" style="padding-left: 3px;">OCA Inspection</a> <a
							href="#" style="padding-left: 3px;">OCA Analytical Services</a> <a
							href="#" style="padding-left: 3px;">OCA Outsourcing</a> <a
							href="#" style="padding-left: 3px;">OCA Marine</a> <a href="#"
							style="padding-left: 3px;">Consumer Goods</a> <a href="#"
							style="padding-left: 3px;">Commercial and Electrical</a> <a
							href="#" style="padding-left: 3px;">Government Services</a> <br>
						<a href="#" style="color: #1f55ba;"><strong>Search</strong></a> <br>

						<strong>PO</strong> <a href="../projects/prj_createproject.html"
							style="padding-left: 3px;">Create</a> <a
							href="../projects/prj_projadvsearch.html"
							style="padding-left: 3px;"> Search</a></p>
						</td>
						<td valign="top" style="padding-right: 10px;"><strong>Jobs</strong>
						<a style="font-weight: bold; color: #1f55ba;">Create</a> <a
							href="ins_createjobs.html" style="padding-left: 3px;">OCA
						Inspection</a> <a href="#" style="padding-left: 3px;">OCA
						Analytical Services</a> <a href="#" style="padding-left: 3px;">OCA
						Outsourcing</a> <a href="#" style="padding-left: 3px;">OCA Marine</a>
						<a href="#" style="padding-left: 3px;">Consumer Goods</a> <a
							href="CE_createjobs.html" style="padding-left: 3px;">Commercial
						and Electrical</a> <a href="#" style="padding-left: 3px;">Government
						Services</a> <br>
						<a href="#" style="color: #1f55ba;"> <strong>Search</strong></a> <a
							href="#" style="color: #1f55ba;"><strong>Log</strong><br>
						</a> <a style="font-weight: bold; color: #1f55ba;">System</a> <a
							href="#" style="padding-left: 3px;">Shipping Agents</a> <a
							href="#" style="padding-left: 3px;">Towing Companies</a> <a
							href="#" style="padding-left: 3px;">Service Locations</a> <br>
						<a href="#" style="color: #1f55ba;"> <strong>Consolidated
						Invoice</strong></a></td>
						<td valign="top"><strong>Customers</strong><a href="#"
							style="padding-left: 3px;"> Quick Create </a> <a href="#"
							style="padding-left: 3px;">Customers</a> <a href="#"
							style="padding-left: 3px;">Contact</a> <a href="#"><strong><br>
						Contracts</strong></a> <a href="#"><strong><br>
						Reports</strong></a> <strong><br>
						Set Up</strong> <a href="#" style="padding-left: 3px;">User</a> <a href="#"
							style="padding-left: 3px;">Permission List</a> <a href="#"
							style="padding-left: 3px;">Roles</a> <a href="#"
							style="padding-left: 3px;">Drop Down Values</a> <a href="#"
							style="padding-left: 3px;">Countries</a> <a href="#"
							style="padding-left: 3px;">Business Unit</a> <a href="#"
							style="padding-left: 3px;">Branch</a> <a href="#"
							style="padding-left: 3px;">VAT Rates</a> <a
							href="../setup/sys_controlnumber.html" style="padding-left: 3px;">Control
						Number</a></td>
					</tr>
				</table>
				</div>


				<span class="qmclear">&nbsp;</span></div>

				<!--%%%%%%%%%%%% QuickMenu Create Menu (menu id, is vertical, show timer (ms), hide timer (ms), on click) %%%%%%%%%%%*-->
				<script type="text/JavaScript">qm_create(0,false,0,500,false)</script><!-- #EndLibraryItem --></td>
				<td width="250">
				<table width="250" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td style="padding-left: 10px; border-left: #999999 solid 1px;"><label>
						<input name="textfield" type="text" class="inputBox" size="13">
						</label></td>
						<td><select name="sel1" id="sel1" class="selectionBox">
							<option selected>Jobs</option>
							<option>Projects</option>
							<option>Customer</option>
							<option>Contracts</option>
							<option>Contacts</option>
						</select></td>
						<td><label> <input name="Submit" type="submit"
							class="button1" value="Search" style="border-width: 1px;">
						</label></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<!-- --------------------------------- HEADER END ------------------------------------------------ -->
<table width="97%" height="80%" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td valign="top"><!-- BREADCRUMB TRAIL  -->
		<div id="breadcrumbContainer">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			background="../images/intopbluetrailbg.jpg">
			<tr>
				<td width="25"><img src="../images/inlfttrailcorner.gif"
					width="8" height="22"></td>
				<td>
				<table height="22" border="0" align="left" cellpadding="0"
					cellspacing="0">
					<tr>
						<td class="breadcrumbtrailDeactive"
							style="background: none; padding-left: 5px;">&#9658; Entry</td>
						<td class="breadcrumbtrailActive">Job Instructions</td>
						<td class="breadcrumbtrailDeactive">Select Charges</td>
						<td class="breadcrumbtrailDeactive">Invoice Preview</td>
						<td class="breadcrumbtrailDeactive">View Invoice</td>
						<td align="right">&nbsp;</td>
					</tr>
				</table>
				</td>
				<td align="right"><img src="../images/inrttrailcorner.gif"
					width="7" height="22"></td>
			</tr>
		</table>
		</div>
		<!-- BREADCRUMB TRAIL END --> <!-- MAIN CONTAINER -->
		<div id="MainContentContainer"><!-- TABS CONTENTS -->
		<div id="tabcontainer">
		<div id="tabnav">
		<ul>
			<li><a href="#" rel="tab1"><span>Job Instructions</span></a></li>
		</ul>
		<label> <select name="sel5" id="sel5" class="SelectionBox"
			style="float: right" onChange="MM_jumpMenu('parent',this,1)">
			<option selected>Go to ... &gt;</option>
			<option value="CE_createjobs.html">Entry</option>
			<option>Notes</option>
			<option>History</option>
		</select> </label></div>
		<!-- Sub Menus container. Do not remove -->
		<div id="tab_inner"><!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
		<div id="tab1" class="innercontent">
		<table class="mainApplTable" cellspacing="0" cellpadding="0"
			style="width: 100%">
			<tbody>
				<tr>
					<th>Job Instructions <img src="../images/separator2.gif"
						width="2" height="27" align="absmiddle"
						style="padding-left: 10px; padding-right: 10px;" /> Nomination ID:
					US380-0000152</th>
					<th style="text-align: right">Billing Status:&nbsp;<select
						name="servicetype" id="servicetype" class="selectionBox"
						style="background-color: #f6fcff">
						<option selected>Open</option>
						<option>Invoiced</option>
						<option>Credited</option>
						<option>Rebilled</option>
						<option>Closed</option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Operational
					Status:&nbsp;<select name="servicetype" id="servicetype"
						class="selectionBox" style="background-color: #f6fcff">
						<option selected>Scheduled</option>
						<option>Unscheduled</option>
						<option>Hold</option>
					</select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#"
						onClick="javascript:popup_show('integrationlog', 'integrationlog_drag', 'integrationlog_exit', 'screen-corner', 40, 80); searchintegrationloght(); hideIt();popupboxenable();">Integration
					Log</a></th>
					<th style="text-align: right"><a
						href="../images/JobOrderEntryForm.jpg" target="_new"><img
						src="../images/ico_print.gif" alt="Print Job Order" width="18"
						height="16" hspace="2" border="0" align="absmiddle"
						title="Print Job Order"></a><a href="#"><img
						src="../images/icosendtooperationalsystem.gif"
						alt="Send to Operational System" hspace="2" border="0"
						align="absmiddle" title="Send to Operational System"></a><!--<a href="#"><img src="../images/icoflask.gif" alt="Send To LIMS" hspace="2" border="0" align="absmiddle" title="Send To LIMS"></a --><!--<a href="CE_jobsselectcharges.html"> --><a
						href="CE_jobsselectcharges.html"><img
						src="../images/savennextbluarrow.gif"
						alt="Save and Go to Next Page" width="14" height="14" hspace="4"
						border="0" align="absmiddle" title="Save and Go to Next Page"></a><a
						href="#"><img src="../images/icosave.gif" alt="Save"
						title="Save" width="14" height="14" border="0" align="absmiddle" /></a></th>
				</tr>
				<tr>
					<td colspan="3" style="padding: 0"><!--Start --> <!-- ---------------- Sample Location Container Table V1 --------------------- -->


					<div id="samplelocContainerv1p1" class="samplelocContainer">
					<table width="100%" border="0" align="center" cellpadding="00"
						cellspacing="0" class="orderTable" style="border: none;">
						<!--<tr>
                                        <th width="13" class="TDShadeGrey"> <div id="bluarrowrightv1p1s1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showv1p1sample1Table();"> <img src="../images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="6"/></a> </div>
                                          <div id="bluarrowdownv1p1s1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidev1p1sample1Table();"> <img src="../images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="6"/></a> </div></th>
                                        <th width="10%" class="TDShadeGrey" >Sample Loc</th>
                                        <th width="15%" ># of Sample(s)</th>
                                        <th width="15%" >Cust Sample Desc</th>
                                        <th width="12%" nowrap >Sample Vol. </th>
                                        <th width="13%" >Container Type </th>
                                        <th width="12%" >Retain/Tested</th>
										<th width="10%" nowrap >Priority</th>										
										<th width="10%" nowrap >&nbsp;Sort#</th>
                                        <th style=" width:40px;" >&nbsp;</th>
                                      </tr>
                                      <tr >
                                        <td rowspan="2" >&nbsp;</td>
                                        <td ><select id="sel3" name="sel3" class="selectionBox">
                                            <option selected="selected">FST</option>
                                            <option>Tank Truck</option>
                                            <option>Vessel</option>
                                          </select>                                        </td>
                                        <td ><input name="Input" class="inputBox" style="width:30px;" size="4"  maxlength="50" value="1"/></td>
                                        <td ><input name="RO_HEADER_LOCATION2"  class="inputBox" 
                                id="RO_HEADER_LOCATION2"  size="10" 
                                maxlength="50" style="text-align:right"/>                                        </td>
                                        <td ><select id="sel3" name="sel3" class="selectionBox">
                                            <option selected="selected">2 Gallons</option>
                                            <option>4 Gallons</option>
                                            <option>2 Liters</option>
                                            <option>4 Liters</option>
                                          </select>                                        </td>
                                        <td ><select id="sel3" name="sel3" class="selectionBox">
                                            <option selected="selected">Amber Bottles</option>
                                            <option>Clear Bottles</option>
                                          </select>                                        </td>
                                        <td ><select id="sel3" name="sel3" class="selectionBox">
                                            <option>Retain</option>
                                            <option>Tested</option>
                                          </select>                                        </td>
										<td style="text-align:left"><select id="priority000" name="addJobVessels[0].addJobProds[0].addJobProdSamples[0].jobProdSample.priority" class="selectionBox" onchange="checkForOtApprovedFlag('0','0','0')"><option value="" selected="selected"></option><option value="Overtime">Overtime</option><option value="Rush">Rush</option><option value="Standard">Standard</option></select></td>
										<td style="text-align:left"><input name="sort" class="inputBox" style="width:13px; text-align:right" value="1" size="2"  maxlength="2"/></td>

                                        <td><div id="div22" style="width:60px; text-align:center;"> <a href="#"><img src="../images/add.gif" alt="Add Sample" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="../images/delete.gif" alt="Delete Sample" width="13" height="12" hspace="1px" border="0"/></a> <a href="#" onClick="javascript:copysample();"><img src="../images/copy.gif" alt="Copy Sample Location" width="13" height="12" hspace="1px" border="0"/></a> </div></td>
                                      </tr> -->
						<tr>
							<td colspan="14">
							<div align="center" id="sampleloctablev1p1"
								style="visibility: visible; display: block;">
							<table border="0" cellpadding="0" cellspacing="0"
								style="width: 100%; border: none;">
								<tr>
									<td>Line #</td>
									<td>Test/Standard</td>
									<td>Description</td>
									<td align="center">Quantity</td>
									<td align="center">UOM</td>
									<td align="center">Quoted Price</td>
									<td align="center">List Price</td>
									<td align="center" nowrap>Billed Amount</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>
									<div align="right"><a href="#"><img
										src="../images/add.gif" alt="Add Test Rows" width="13"
										height="12" hspace="5" border="0" title="Add Test Rows" /></a></div>
									</td>
								</tr>
								<tr>
									<td width="5%">&nbsp;&nbsp;1</td>
									<td width="15%"><input name="RO_HEADER_LOCATION2142"
										class="inputBox" id="RO_HEADER_LOCATION2142"
										value="CENELEC EN 500201" size="20" /></td>
									<td width="26%"><input name="RO_HEADER_LOCATION232"
										class="inputBox" id="RO_HEADER_LOCATION232"
										value="Electrical Apparatus" size="50" /></td>
									<td width="10%" align="center" nowrap><input name="price"
										class="inputBox" id="price" value="1" size="4"
										style="text-align: right;" disabled />&nbsp;&nbsp;</td>
									<td width="10%" align="center" nowrap><select id="sel3"
										name="sel3" class="selectionBox">
										<option selected>EACH</option>
										<option>HOUR</option>
										<option>PER DAY</option>
										<option>SHIFT</option>
									</select>&nbsp;&nbsp;</td>
									<td width="12%" align="center" nowrap><input name="price"
										class="inputBox" id="price" value="200" size="8"
										style="text-align: right;" disabled /></td>
									<td width="12%" align="center" nowrap>200</td>
									<td width="12%" align="center" nowrap>200</td>
									<td width="15%" align="right" nowrap><a href="#"
										onClick="javascript:popup_show('others', 'others_drag', 'others_exit', 'screen-corner', 40, 80); hideIt();popupboxenable();"
										style="cursor: hand;" onMouseOver="doTooltip(event,'
									<table>
										<tr>
											<td width=120px><b>Start Date:</b></td>
											<td>03/10/2009</td>
										</tr>
										<tr>
											<td width=120px><b>End Date:</b></td>
											<td>04/10/2009</td>
										</tr>
										<tr>
											<td width=120px><b>Task Ready Date:</b></td>
											<td>04/10/2009</td>
										</tr>
										<tr>
											<td><b>Sample Description:</b></td>
											<td>Sample Description</td>
										</tr>
										<tr>
											<td><b>Service Type: </b></td>
											<td>SAF-LOW VOLTAGE DIRECTIVE</td>
										</tr>
										<tr>
											<td><b>P.O # </b></td>
											<td>1234, $12000</td>
										</tr>
										<tr>
											<td><b>Billing Status: </b></td>
											<td>Open</td>
										</tr>
										<tr>
											<td><b>Operational Status: </b></td>
											<td>Accrual Prior Month</td>
										</tr>
									</table>')" onMouseOut="hideTip()">Other</a></td>
									<td width="12%" align="right" nowrap><a href="#"><img
										src="../images/icoaddnote.gif" alt="Note" hspace="5"
										border="0" /></a></td>
									<td width="12%" align="right" nowrap><a href="#"><img
										src="../images/icoattach.gif" alt="Attachment" hspace="5"
										border="0" /></a></td>
									<td width="12%">
									<div align="right"><a href="#"><img
										src="../images/delete.gif" alt="Del Row" width="13"
										height="12" hspace="5" border="0" /></a></div>
									</td>
								</tr>
								<tr>
									<td>&nbsp;&nbsp;2</td>
									<td><input name="RO_HEADER_LOCATION2152" class="inputBox"
										id="RO_HEADER_LOCATION2152" value="CENELEC EN 500202"
										size="20" /></td>
									<td><input name="RO_HEADER_LOCATION23" class="inputBox"
										id="RO_HEADER_LOCATION23" value="Electrical Equipment"
										size="50" /></td>

									<td align="center"><input name="price" class="inputBox"
										id="price" value="1" size="4" style="text-align: right;"
										disabled />&nbsp;&nbsp;</td>
									<td align="center"><select id="sel3" name="sel3"
										class="selectionBox">
										<option selected>EACH</option>
										<option>HOUR</option>
										<option>PER DAY</option>
										<option>SHIFT</option>
									</select>&nbsp;&nbsp;</td>
									<td align="center" nowrap><input name="price"
										class="inputBox" id="price" value="200" size="8"
										style="text-align: right;" disabled /></td>
									<td align="center" nowrap>200</td>
									<td align="center" nowrap>200</td>
									<td align="right" nowrap><a href="#"
										onClick="javascript:popup_show('others', 'others_drag', 'others_exit', 'screen-corner', 40, 80); hideIt();popupboxenable();"
										style="cursor: hand;" onMouseOver="doTooltip(event,'
									<table>
										<tr>
											<td width=120px><b>Start Date:</b></td>
											<td>03/10/2009</td>
										</tr>
										<tr>
											<td width=120px><b>End Date:</b></td>
											<td>04/10/2009</td>
										</tr>
										<tr>
											<td width=120px><b>Task Ready Date:</b></td>
											<td>04/10/2009</td>
										</tr>
										<tr>
											<td>
										<tr>
											<td><b>Sample Description:</b></td>
											<td>Sample Description</td>
										</tr>
										<tr>
											<td><b>Service Type: </b></td>
											<td>SAF-LOW VOLTAGE DIRECTIVE</td>
										</tr>
										<tr>
											<td><b>P.O # </b></td>
											<td>1234, $12000</td>
										</tr>
										<tr>
											<td><b>Billing Status: </b></td>
											<td>Open</td>
										</tr>
										<tr>
											<td><b>Operational Status: </b></td>
											<td>Accrual Prior Month</td>
										</tr>
									</table>')" onMouseOut="hideTip()">Other</a></td>
									<td align="right" nowrap><a href="#"><img
										src="../images/icoaddnote.gif" alt="Notes" hspace="5"
										border="0" /></a></td>
									<td align="right" nowrap><a href="#"><img
										src="../images/icoattach.gif" alt="Attachment" hspace="5"
										border="0" /></a></td>
									<td>
									<div align="right"><a href="#"><img
										src="../images/delete.gif" alt="Del Row" width="13"
										height="12" hspace="5" border="0" /></a></div>
									</td>
								</tr>
								<!--<tr>
                                                <td>&nbsp;&nbsp;3</td>
                                                <td><input name="RO_HEADER_LOCATION2162"  class="inputBox" 
                                id="RO_HEADER_LOCATION2162" value="BPPRODF1"  size="20"/>                                                </td>
                                                <td><input name="RO_HEADER_LOCATION23"  class="inputBox" 
                                id="RO_HEADER_LOCATION23" value="F1 - Distillates - CARB Diesel"  size="50"/>                                                </td>
                                                <td align="center">
                                                  <select id="sel3" name="sel3" class="selectionBox">
                                                    <option>Yes</option>
                                                    <option>No</option>
                                                  </select>&nbsp;&nbsp;                                                </td>
												<td align="center">
                                                  <select id="sel3" name="sel3" class="selectionBox">
                                                    <option selected>HR</option>
                                                  </select>&nbsp;&nbsp;												  </td>
												<td width="12%" align="center" nowrap><input name="price"  class="inputBox" 
                                id="price" value="200"  size="8" style="text-align:right;"/>                                                </td>
												<td width="12%" align="center" nowrap>200                                                </td>
												<td width="12%" align="right" nowrap>&nbsp;&nbsp;                                                </td>
                                                <td><div align="right">
                                                  <a href="#"><img src="../images/delete.gif" alt="Del Row" width="13" height="12" hspace="5" border="0"/></a></div></td>
                                              </tr> -->
								<!--<tr>
                                                <td colspan="8"><table  border="0" cellpadding="0" cellspacing="0" style="width:100%">
                                                    <tr>
                                                      <td width="12%" valign="top" style="border-bottom:none">Sample Instructions: </td>
                                                      <td width="88%" style="border-bottom:none; border-right:none;"><label>
                                                        <textarea name="textarea9" cols="90" rows="4"></textarea>
                                                        </label></td>
                                                    </tr>
                                                  </table></td>
                                              </tr> -->
							</table>
							</div>
							</td>
						</tr>
					</table>
					</div>
					<!-- ----------------SAMPLE LOCATIONS TABLE V1 END -------------- -->
					<!--End --></td>
				</tr>

			</tbody>
		</table>
		<!-- ----------------- INSTRUCTIONS ----------------- -->
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="InnerApplTable">
			<tr>
				<td width="30">
				<div id="bluarrowright"
					style="visibility: visible; position: absolute; z-index: 2; margin-left: 4px">
				<a href="#instructions" onClick="javascript:showInstructions();">
				<img src="../images/bluarrowright.gif" width="8" height="16"
					border="0" vspace="4" /></a></div>
				<div id="bluarrowdown"
					style="visibility: hidden; position: relative; z-index: 1; margin-top: 6px">
				<a href="#instructions" onClick="javascript:hideInstructions();">
				<img src="../images/bluarrowdown.gif" width="16" height="8"
					border="0" vspace="4" /></a></div>
				</td>
				<td width="97%"><a name="instructions"></a>Instructions</td>
			</tr>
			<tr>
				<td colspan="2" style="padding: 0px;">
				<div id="instructionsContainer">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="orderTable">
					<tr>
						<th width="50%">Operations Instructions:</th>
						<th width="50%">Sample Instructions:</th>
					</tr>
					<tr>
						<td><label> <textarea name="textarea" cols="60"
							rows="4"></textarea> </label></td>
						<td><textarea name="textarea2" cols="60" rows="4"></textarea></td>
					</tr>
					<!--<tr>
                          <td colspan="2"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="orderTable" style="margin:0px;">
                              <tr>
                                <th colspan="6"> Laboratory Events </th>
                              </tr>
                              <tr>
                                <td>Retain   Period:</td>
                                <td><label>
                                  <input name="textfield" type="text" class="inputBox" size="4" />
                                  days</label></td>
                                <td><label>
                                  <input type="checkbox" name="checkbox" value="checkbox" />
                                  Lab Analysis </label></td>
                                <td><input type="checkbox" name="checkbox2" value="checkbox" />
                                  OT Approved </td>
                                <td>OT Approved   By:</td>
                                <td><label>
                                  <select name="select" class="selectionBox">
                                    <option>Select ...</option>
                                  </select>
                                  </label></td>
                              </tr>
                            </table></td>
                        </tr> -->
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<th>Lab Instructions:</th>
						<th>Shipping Instructions:</th>
					</tr>
					<tr>
						<td><textarea name="textarea3" cols="60" rows="4"></textarea></td>
						<td><textarea name="textarea4" cols="60" rows="4"></textarea></td>
					</tr>
					<tr>
						<th>Reporting Instructions:</th>
						<th>Billing Instructions:</th>
					</tr>
					<tr>
						<td><textarea name="textarea5" cols="60" rows="4"></textarea></td>
						<td><textarea name="textarea6" cols="60" rows="4"></textarea></td>
					</tr>
					<tr>
						<th>Other Instructions:</th>
						<th>&nbsp;</th>
					</tr>
					<tr>
						<td><textarea name="textarea7" cols="60" rows="4"></textarea></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2"><!--<table border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin:0px;">
            <tr>
              <td width="12%">Revision: 0 </td>
              <td width="38%">Date/Time: 
		  <div id="datetime" style="visibility:hidden; position:absolute; font-weight:normal;">
		  <script>

			/*Current date script credit*/
			
			var mydate=new Date()
			var year=mydate.getYear()
			if (year < 1000)
			year+=1900
			var day=mydate.getDay()
			var month=mydate.getMonth()+1
			if (month<10)
			month="0"+month
			var daym=mydate.getDate()
			if (daym<10)
			daym="0"+daym
			document.write("<font color='000000' face='Arial'>"+month+"/"+daym+"/"+year+"</font>")
			</script>
			  <script language="javascript" src="js/clock.js"></script>&nbsp;EDT 
			  </div>
			  
                </td>
              <td width="20%">Revision   Notes:</td>
              <td width="30%"><textarea name="textarea8" cols="50" rows="2"></textarea></td>
            </tr>
            <tr>
              <td height="40" colspan="4" style="padding-left:5px;"><input name="Button" type="button" class="button1" value="New Revision"  onClick="showdatetime();"/></td>
            </tr>
        </table>--></td>
					</tr>
				</table>
				</div>
				</td>
			</tr>
		</table>
		<!-- --------------------------------------- INSTRUCTIONS END -------------------------------------- -->


		<!-- ----------------- PROJECTS ----------------- -->
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="InnerApplTable">
			<form name="frm1">
			<tr>
				<td width="30">
				<div id="bluarrowrightprojects"
					style="visibility: visible; position: absolute; z-index: 2; margin-left: 4px">
				<a href="#projects" onClick="javascript:showProjects();"> <img
					src="../images/bluarrowright.gif" width="8" height="16" border="0"
					vspace="4" /></a></div>
				<div id="bluarrowdownprojects"
					style="visibility: hidden; position: relative; z-index: 1; margin-top: 6px">
				<a href="#projects" onClick="javascript:hideProjects();"> <img
					src="../images/bluarrowdown.gif" width="16" height="8" border="0"
					vspace="4" /></a></div>
				</td>
				<td width="97%"><a name="projects"></a>Projects</td>
			</tr>
			<tr>
				<td colspan="2" style="padding: 0px;">
				<div id="projectsContainer">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="orderTable">
					<tr>
						<td colspan="2">
					<tr>


						<table width="100%" cellpadding=0 cellspacing=0
							class="mainApplTable">
							<td class="TDShade"><strong>Project Number:</strong></td>
							<td class="TDShadeBlue"><input name="projectNumber"
								type="text" class="inputBox" size="52" maxlength="10" /></td>
							<td class="TDShade"><strong>Project Type:</strong></td>
							<td colspan="2" class="TDShadeBlue"><select
								name="projectType" class="selectionBoxbig" id="sel3">
								<option selected="selected">Non-project</option>
								<option value="ANT01">Only cost accumulation</option>
								<option value="ARU01">Project with billable expenses</option>
								<option value="AUS01">Project with billing and revenue
								plan</option>
							</select></td>
							</tr>
							<!--<tr>
                  <td class="TDShade"><strong>Begin Date:</strong></td>
                  <td class="TDShadeBlue"><input name="begindate" type="text" class="inputBox" size="10" maxlength="12" style="width:60px;">
                <a href="#" onClick="displayCalendar(document.forms[0].begindate,'mm/dd/yyyy',this)"> 
					<img src="../images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                  <td class="TDShade"><strong>End Date:</strong></td>
                  <td colspan="2" class="TDShadeBlue"><input name="enddate" type="text" class="inputBox" size="10" maxlength="12" style="width:60px;">
                <a href="#" onClick="displayCalendar(document.forms[0].enddate,'mm/dd/yyyy',this)"> 
					<img src="../images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                </tr> -->
							<tr>
								<td class="TDShade">&nbsp;</td>
								<td class="TDShadeBlue">&nbsp;</td>
								<td class="TDShade">&nbsp;</td>
								<td class="TDShadeBlue" colspan="2"><input name="Submit2"
									type="button" class="button1" value="Create Project"
									style="height: 18px;" onclick="createProject(this.form)"></td>
							</tr>
						</table>


						</td>
					</tr>
					<tr>
						<td colspan="2"><!--<table border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin:0px;">
            <tr>
              <td width="12%">Revision: 0 </td>
              <td width="38%">Date/Time: 
		  <div id="datetime" style="visibility:hidden; position:absolute; font-weight:normal;">
		  <script>

			/*Current date script credit*/
			
			var mydate=new Date()
			var year=mydate.getYear()
			if (year < 1000)
			year+=1900
			var day=mydate.getDay()
			var month=mydate.getMonth()+1
			if (month<10)
			month="0"+month
			var daym=mydate.getDate()
			if (daym<10)
			daym="0"+daym
			document.write("<font color='000000' face='Arial'>"+month+"/"+daym+"/"+year+"</font>")
			</script>
			  <script language="javascript" src="js/clock.js"></script>&nbsp;EDT 
			  </div>
			  
                </td>
              <td width="20%">Revision   Notes:</td>
              <td width="30%"><textarea name="textarea8" cols="50" rows="2"></textarea></td>
            </tr>
            <tr>
              <td height="40" colspan="4" style="padding-left:5px;"><input name="Button" type="button" class="button1" value="New Revision"  onClick="showdatetime();"/></td>
            </tr>
        </table>--></td>
					</tr>
				</table>
				</div>
				</td>
			</tr>
			</form>
		</table>
		<!-- --------------------------------------- PROJECTS END -------------------------------------- -->


		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="applTableBot">
			<tr>
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="right"></td>
						<td style="text-align: right"><a
							href="../images/JobOrderEntryForm.jpg" target="_new"><img
							src="../images/ico_print.gif" alt="Print Job Order" width="18"
							height="16" hspace="2" border="0" align="absmiddle"
							title="Print Job Order"></a><!--<a href="#"><img src="../images/icoflask.gif" alt="Send To LIMS" hspace="2" border="0" align="absmiddle" title="Send To LIMS"></a> --><!--<a href="CE_jobsselectcharges.html"> --><a
							href="CE_jobsselectcharges.html"><img
							src="../images/savennextbluarrow.gif"
							alt="Save and Go to Next Page" width="14" height="14" hspace="4"
							border="0" align="absmiddle" title="Save and Go to Next Page"></a><a
							href="#"><img src="../images/icosave.gif" alt="Save"
							title="Save" width="14" height="14" border="0" align="absmiddle" /></a></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</div>
		<!----------------- TAB 1 CONTAINER END ------------------------------ -->
		</div>
		</div>
		<script type="text/javascript">
			
			//tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
			dolphintabs.init("tabnav", 0)
			
			</script> <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
		<table width="100%" cellspacing="0">
			<tr>
				<td height="24" style="text-align: right; padding-right: 0px;"><select
					name="sel5" id="sel5" class="SelectionBox"
					onChange="MM_jumpMenu('parent',this,1)">
					<option selected>Go to ... &gt;</option>
					<option value="CE_createjobs.html">Entry</option>
					<option>Notes</option>
					<option>History</option>
				</select></td>
			</tr>
		</table>
		</div>
		<!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
		</td>
	</tr>
</table>
<!-- -------------------------------------- FOOTER ------------------------------------------------- -->
<div id="footer">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
		<table width="96%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td class="bottomTextLeft">Home | Announcements | Help</td>
				<td align="right" class="bottomTextRight">Copyright &copy; 2006
				<strong>Intertek</strong>. All rights reserved. Best viewed in 1024
				x 768 Res. &nbsp;| &nbsp;Disclaimer</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</div>
<!-- --------------------------------- FOOTER END --------------------------------------------- -->
<!-- --------------------------- Copy Product Pop ------------------------------------------------- -->
<div class="sample_popup" id="copyprod"
	style="visibility: hidden; display: none; z-index: 1;">
<div class="menu_form_header" id="copyprod_drag" style="width: 475px;">
<img class="menu_form_exit" id="copyprod_exit"
	src="../images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Copy Product</div>
<div class="menu_form_body"
	style="width: 475px; height: 140px; z-index: 10">
<form method="post" action="../popup.php">
<table width="95%" border="0" align="center" class="InnerApplTable">
	<tr>
		<td valign="middle" colspan="2"><iframe align="left"
			frameborder="0" style="padding: 0px; height: 80px;" width="100%"
			src="inc_copyproduct.html" scrolling="no" id="frame1"
			name="frmcopyprod01" allowtransparency="yes"> </iframe></td>
	</tr>
	<tr>
		<td><input type="button" name="Submit" value="Ok"
			onClick="hidecopyprod();copyprod01();showIt();" /> &nbsp;&nbsp; <input
			name="cancel" type="reset" id="cancel" value="Cancel"
			onClick="hidecopyprod();showIt();" /></td>
	</tr>
</table>
</form>
</div>
</div>
</div>
<!-- --------------------------------- Copy Product Pop END ----------------------------------------- -->

<!-- --------------------------- Sales Rep Lookup ------------------------------------------------- -->
<div class="sample_popup" id="salesrep"
	style="visibility: hidden; display: none; z-index: 1000;">
<div class="menu_form_header" id="salesrep_drag" style="width: 450px;">
<img class="menu_form_exit" id="salesrep_exit"
	src="../images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Search User</div>
<div class="menu_form_body" id="salesrepbody"
	style="width: 450px; height: 130px;">
<form method="post" action="popup.php">
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" class="InnerApplTable">
	<tr>
		<td><strong>Login Name: </strong></td>
		<td><input name="textfield2" type="text" class="inputBox"
			size="30" /></td>
	</tr>
	<tr>
		<td><strong>First Name:</strong></td>
		<td><input name="textfield3" type="text" class="inputBox"
			size="30" /></td>
	</tr>
	<tr>
		<td><strong>Last Name:</strong></td>
		<td><input name="textfield3" type="text" class="inputBox"
			size="30" /></td>
	</tr>
	<tr>
		<td colspan="2"><input name="Button" type="button"
			class="button1" value="Search" onClick="searchsalesrep();" />
		&nbsp;&nbsp; <input name="Button" type="button" class="button1"
			value="Cancel" onClick="hidesalesrep();" /></td>
	</tr>
</table>

<div id="salesrepsearchresults"
	style="visibility: hidden; display: none;"><!--Search Results -->
<br>
&nbsp;&nbsp;<strong>Search Results</strong>
<table width="98%" align="center" cellpadding="0" cellspacing="0"
	class="InnerApplTable" style="width: 98%">
	<tr>
		<th>Login Name</th>
		<th>Name</th>
		<th>Employee Status</th>
	</tr>
	<tr>
		<td><a href="#">@intertek.com</a></td>
		<td>ICB Template</td>
		<td>Active</td>
	</tr>
	<tr>
		<td><a href="#">AADAMS</a></td>
		<td>&nbsp;</td>
		<td>Terminated</td>
	</tr>
	<tr>
		<td><a href="#">ABHUIYAN</a></td>
		<td>&nbsp;</td>
		<td>Terminated</td>
	</tr>
	<tr>
		<td><a href="#">ABURTON</a></td>
		<td>&nbsp;</td>
		<td>Terminated</td>
	</tr>
	<tr>
		<td><a href="#">ADAM.HARRIS</a></td>
		<td>&nbsp;</td>
		<td>Terminated</td>
	</tr>

</table>
</div>
<!--Search Results --></form>
</div>
</div>
</div>
<!-- --------------------------------- Sales Rep Lookup END ----------------------------------------- -->

<!-- --------------------------- Others Lookup ------------------------------------------------- -->
<div class="sample_popup" id="others"
	style="visibility: hidden; display: none;">
<div class="menu_form_header" id="others_drag" style="width: 450px;">
<img class="menu_form_exit" id="others_exit"
	src="../images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Additional
Information</div>
<div class="menu_form_body" id="othersbody"
	style="width: 450px; height: 320px;">
<form>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" class="InnerApplTable">
	<tr>
		<td><strong>Start Date: </strong></td>
		<td><input name="theDatepop" type="text" class="inputBox"
			value="03/10/2009" size="30" maxlength="12"> <!--<a href="#" onClick="displayCalendar(document.forms[0].theDatepop,'mm/dd/yyyy',this)"> -->
		<img src="../images/calendar.gif" width="15" height="17"
			align="absmiddle" border="0" /><!--</a> --></td>
	</tr>
	<tr>
		<td><strong>End Date: </strong></td>
		<td>
		<form><input name="theDatetest" type="text" class="inputBox"
			size="30" maxlength="12" value="03/10/2009"> <!--<a href="#" onClick="displayCalendar(document.forms[0].theDatetest,'mm/dd/yyyy',this)"> -->
		<img src="../images/calendar.gif" width="15" height="17"
			align="absmiddle" border="0" /><!--</a> --></form>
		</td>
	</tr>
	<tr>
		<td><strong>Task Ready Date: </strong></td>
		<td><input name="DateTR" type="text" class="inputBox"
			value="03/10/2009" size="30" maxlength="12"> <!--<a href="#" onClick="displayCalendar(document.forms[0].DateTR,'mm/dd/yyyy',this)"> -->
		<img src="../images/calendar.gif" width="15" height="17"
			align="absmiddle" border="0" /><!--</a> --></td>
	</tr>
	<!--<tr>
          <td><strong>Sales Rep:</strong></td>
          <td><input name="Input123" class="inputBoxblue" value="Brian Lerche" size="30"/>
                            <a href="#" onClick="javascript:popup_show('salesrep', 'salesrep_drag', 'salesrep_exit', 'screen-corner', 40, 80); searchsalesrepht(); hidesalesrepsearch(); hideIt();popupboxenable();">
						  <img src="../images/lookup.gif" alt="lookup Sales Rep" width="13" height="13" border="0"></a></td>
        </tr> -->
	<tr>
		<td><strong>Warehouse:</strong></td>
		<td><input name="Input122" class="inputBoxblue" size="30"
			value="USA01" /> <a href="#"
			onClick="javascript:popup_show('branchcode', 'branchcode_drag', 'branchcode_exit', 'screen-corner', 40, 80); searchbranchcodeht(); hidebranchcodesearch(); hideIt();popupboxenable();">
		<img src="../images/lookup.gif" alt="lookup country" width="13"
			height="13" border="0"></a></td>
	</tr>
	<tr>
		<td><strong>Sample Description:</strong></td>
		<td><input name="textfield3" type="text" class="inputBox"
			size="30" value="Customer Product" /></td>
	</tr>
	<tr>
		<td><strong>Service Type:</strong></td>
		<td><select name="servicetype" id="servicetype"
			class="selectionBox" style="width: 180px;">
			<option selected>SAF-LOW VOLTAGE DIRECTIVE</option>
			<option>SAF-ATEX/EX Directive</option>
			<option>SAF-Prod Safety</option>
		</select></td>
	</tr>
	<tr>
		<td><strong>Service Location:</strong></td>
		<td><input name="serviceloc" type="text" class="inputBox"
			size="30" maxlength="12">&nbsp;<a href="#"
			onClick="javascript:popup_show('servloc', 'servloc_drag', 'servloc_exit', 'screen-corner', 40, 80);showFrameServLoc();searchTowinght(); hideIt();popupboxenable();"><img
			src="../images/lookup.gif" width="13" height="13" border="0" /></a></td>
	</tr>
	<tr>
		<td><strong>P.O. #:</strong></td>
		<td><input name="Input122" class="inputBox" size="30" /> <a
			href="#"
			onClick="javascript:popup_show('ponumber', 'ponumber_drag', 'ponumber_exit', 'screen-corner', 40, 80); searchponumberht(); hideponumbersearch(); hideIt();popupboxenable();">
		<img src="../images/lookup.gif" alt="lookup country" width="13"
			height="13" border="0"></a></td>
	</tr>
	<tr>
		<td><strong>Billing Status:</strong></td>
		<td><select name="servicetype" id="servicetype"
			class="selectionBox" style="width: 180px;">
			<option selected>Open</option>
			<option>Invoiced</option>
			<option>Credited</option>
			<option>Rebilled</option>
			<option>Closed</option>
		</select></td>
	</tr>
	<tr>
		<td><strong>Operational Status:</strong></td>
		<td><select name="servicetype" id="servicetype"
			class="selectionBox" style="width: 180px;">
			<option selected>Accrual Prior Month</option>
			<option>Completed</option>
			<option>Data Collected</option>
			<option>Fail</option>
			<option>Hold</option>
			<option>In Process</option>
			<option>Not Applicable</option>
			<option>Pass</option>
			<option>Pending</option>
			<option>Program - Billed</option>
			<option>Scheduled</option>
			<option>Subcontract</option>
			<option>Subcontract - India</option>
			<option>Subcontract - Consumer Goods</option>
			<option>Subcontract - OCA</option>
		</select></td>
	</tr>
	<tr>
		<td><strong>Estimate Hours:</strong></td>
		<td><select name="servicetype" id="servicetype"
			class="selectionBox" style="width: 100px;">
			<option selected>Manager</option>
			<option>Engineer</option>
			<option>Admin</option>
		</select>&nbsp;<input name="Input122" class="inputBox" size="10" /> <a href="#"><img
			src="../images/add.gif" alt="Add" width="13" height="12" hspace="1px"
			border="0" /></a> <a href="#"><img src="../images/delete.gif"
			alt="Delete" width="13" height="12" hspace="1px" border="0" /></a></td>
	</tr>
	<tr>
		<td><strong>Task Manager:</strong></td>
		<td><input name="Input122" class="inputBox" size="30" /> <a
			href="#"
			onClick="javascript:popup_show('taskmanager', 'taskmanager_drag', 'taskmanager_exit', 'screen-corner', 40, 80); searchtaskmanagerht(); hidetaskmanagersearch(); hideIt();popupboxenable();">
		<img src="../images/lookup.gif" alt="lookup country" width="13"
			height="13" border="0"></a></td>
	</tr>
	<tr>
		<td colspan="2"><input name="Button" type="button"
			class="button1" value="Submit"
			onClick="hideothers();popupboxclose();" /> &nbsp;&nbsp; <input
			name="Button" type="button" class="button1" value="Cancel"
			onClick="hideothers();popupboxclose();" /></td>
	</tr>
</table>

<!--Search Results --></form>
</div>
</div>
</div>
<!-- --------------------------------- Others Lookup END ----------------------------------------- -->

<!-- --------------------------- Branch Code Lookup ------------------------------------------------- -->
<div class="sample_popup" id="branchcode"
	style="visibility: hidden; display: none;">
<div class="menu_form_header" id="branchcode_drag" style="width: 450px;">
<img class="menu_form_exit" id="branchcode_exit"
	src="../images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Search Branch Code</div>
<div class="menu_form_body" id="branchcodebody"
	style="width: 450px; height: 130px;">
<form method="post" action="popup.php">
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" class="InnerApplTable">
	<tr>
		<td width="25%"><strong>Business Unit: </strong></td>
		<td><input name="textfield" type="text" class="inputBox"
			size="30" /></td>
	</tr>
	<tr>
		<td><strong>Branch Code: </strong></td>
		<td><input name="textfield2" type="text" class="inputBox"
			size="30" /></td>
	</tr>
	<tr>
		<td><strong>Description:</strong></td>
		<td><input name="textfield3" type="text" class="inputBox"
			size="30" /></td>
	</tr>
	<tr>
		<td colspan="2"><input name="Button" type="button"
			class="button1" value="Search" onClick="searchbranchcode();" />
		&nbsp;&nbsp; <input name="Button" type="button" class="button1"
			value="Cancel" onClick="hidebranchcode();popupboxclose1();" /></td>
	</tr>
</table>

<div id="branchcodesearchresults"
	style="visibility: hidden; display: none;"><!--Search Results -->
<br>
&nbsp;&nbsp;<strong>Search Results</strong>
<table width="98%" align="center" cellpadding="0" cellspacing="0"
	class="InnerApplTable" style="width: 98%">
	<tr>
		<th>Business Unit</th>
		<th>Branch Code</th>
		<th>Description</th>
	</tr>
	<tr>
		<td><a href="#" onClick="hidebranchcode();popupboxclose1();">USA01</a></td>
		<td><a href="#" onClick="hidebranchcode();popupboxclose1();">US100</a></td>
		<td>Baltimore, MD, USA</td>
	</tr>
	<tr>
		<td><a href="#" onClick="hidebranchcode();popupboxclose1();">USA01</a></td>
		<td><a href="#" onClick="hidebranchcode();popupboxclose1();">US120</a></td>
		<td>Boston, MA, USA</td>
	</tr>
	<tr>
		<td><a href="#" onClick="hidebranchcode();popupboxclose1();">USA01</a></td>
		<td><a href="#" onClick="hidebranchcode();popupboxclose1();">US140</a></td>
		<td>Channelview, TX, USA</td>
	</tr>

</table>
</div>
<!--Search Results --></form>
</div>
</div>
</div>
<!-- --------------------------------- Branch Code Lookup END ----------------------------------------- -->



<!-- --------------------------- Integration Log Lookup ------------------------------------------------- -->
<div class="sample_popup" id="integrationlog"
	style="visibility: hidden; display: none;">
<div class="menu_form_header" id="integrationlog_drag"
	style="width: 650px;"><img class="menu_form_exit"
	id="integrationlog_exit" src="../images/form_exit.png" />
&nbsp;&nbsp;&nbsp;Integration Log</div>
<div class="menu_form_body" id="integrationlogbody"
	style="width: 650px; height: 130px;">
<form method="post" action="popup.php"><!--Search Results -->
<table width="98%" align="center" cellpadding="0" cellspacing="0"
	class="InnerApplTable" style="width: 98%">
	<tr>
		<th>Integration Type</th>
		<th>Integration User</th>
		<th>Date Time</th>
		<th>Integration Status</th>
		<th>Error Message</th>
	</tr>
	<tr>
		<td>External Operation System</td>
		<td>R. Sulaiman</td>
		<td>03/17/2009<br>
		8:18PM EDT</td>
		<td>Success</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td><input name="Button" type="button" class="button1" value="Ok"
			onClick="hideintegrationlog();popupboxclose();" /></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>

</table>
<!--Search Results --></form>
</div>
</div>
</div>
<!-- --------------------------------- Integration Log Lookup END ----------------------------------------- -->

<!-- ----------------------------------- Service Location Lookup ------------------------------------------------- -->
<div class="sample_popup" id="servloc"
	style="visibility: hidden; display: none;">
<div class="menu_form_header" id="servloc_drag" style="width: 550px;">
<img class="menu_form_exit" id="servloc_exit"
	src="../images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Select Service
Location</div>
<div class="menu_form_body" id="servlocbody"
	style="width: 550px; height: 180px;">
<form method="post" action="popup.php">
<table width="95%" border="0" align="center" class="InnerApplTable">
	<tr>
		<td valign="middle" colspan="2"><iframe align="left"
			frameborder="0" style="padding: 0px; height: 80px;" width="100%"
			src="inc_serviceloc_lookup.html" scrolling="no" id="frame4"
			name="frame4" allowtransparency="yes"></iframe></td>
	</tr>
	<tr>
		<td><input id="search" type="button" value="Search" name="search"
			class="button1" onClick="searchservloc();" /> &nbsp;&nbsp; <input
			id="cancel" type="button" value="Cancel" name="cancel"
			class="button1" onClick="hideServLoc();popupboxclose1();" />
		&nbsp;&nbsp; | &nbsp;<a href="#"
			onClick="popup_show('servloccreate', 'servloccreate_drag', 'servloccreate_exit', 'screen-corner', 40, 80);hideServLoc2();showFrameServLocCreate();hideServLoc();hideIt();"><strong>Create
		New Service Location </strong> </a></td>
	</tr>

</table>
</form>
<br>
<div id="searchresultsservloc" style="visibility: hidden; display: none"><!--search results -->
&nbsp;&nbsp;&nbsp;&nbsp;<strong>Search Results</strong>
<table width="95%" border="0" align="center" cellpadding="0"
	cellspacing="0" class="InnerApplTable">


	<tr>
		<th width="40%">Service Location</th>
		<th width="20%">City</th>
		<th width="40%">Address</th>
	</tr>
	<tr>
		<td><a href="#">Petroleum Fuel &amp; Terminal Co.</a></td>
		<td>Baltimore</td>
		<td>5101 Erdman Avenue, Baltimore, MD, 21205, USA</td>
	</tr>
	<tr>
		<td><a href="#">Petroleum Fuel &amp; Terminal Co.</a></td>
		<td>Baltimore</td>
		<td>1622 South Clinton Street, Baltimore, MD, 21224, USA</td>
	</tr>
</table>
</div>
<!--search results --></div>
</div>
</div>
<!-- --------------------------------- Service Location Lookup END ----------------------------------------- -->

<!-- ----------------------------------- Service Location Create Lookup ------------------------------------------------- -->
<div class="sample_popup" id="servloccreate"
	style="visibility: hidden; display: none;">
<div class="menu_form_header" id="servloccreate_drag"
	style="width: 670px;"><img class="menu_form_exit"
	id="servloccreate_exit" src="../images/form_exit.png" />
&nbsp;&nbsp;&nbsp;Create New Service Location</div>
<div class="menu_form_body" style="width: 670px; height: 310px;">
<form method="post" action="popup.php">
<table width="95%" border="0" align="center" class="InnerApplTable">
	<tr>
		<td valign="middle" colspan="2"><iframe align="left"
			frameborder="0" style="padding: 0px; height: 260px;" width="100%"
			src="inc_create_newserviceloc.html" scrolling="no" id="frame5"
			name="frame5" allowtransparency="yes"></iframe></td>
	</tr>
	<tr>
		<td><input id="ok" type="button" value="OK" name="ok"
			class="button1"
			onClick="hideServLocCreate(); showIt();popupboxclose1();" />
		&nbsp;&nbsp; <input id="cancel" type="button" value="Cancel"
			name="cancel" class="button1"
			onClick="popup_show('servloc', 'servloc_drag', 'servloc_exit', 'screen-corner', 40, 80);hideServLocCreate();showFrameServLoc();" />
		&nbsp;&nbsp; <input id="cancel2" type="button" value="Apply"
			name="cancel2" class="button1" /></td>
	</tr>

</table>
</form>
</div>
</div>
</div>
<!-- --------------------------------- Service Location Create Lookup END ----------------------------------------- -->

<!-- --------------------------- PO # Lookup ------------------------------------------------- -->
<div class="sample_popup" id="ponumber"
	style="visibility: hidden; display: none;">
<div class="menu_form_header" id="ponumber_drag" style="width: 450px;">
<img class="menu_form_exit" id="ponumber_exit"
	src="../images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Search P.O #</div>
<div class="menu_form_body" id="ponumberbody"
	style="width: 450px; height: 130px;">
<form method="post" action="popup.php">
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" class="InnerApplTable">
	<tr>
		<td width="25%"><strong>Customer ID: </strong></td>
		<td><input name="textfield" type="text" class="inputBox"
			size="30" value="GEEL72703701" /></td>
	</tr>
	<tr>
		<td><strong>Customer Name: </strong></td>
		<td><input name="textfield2" type="text" class="inputBox"
			size="30" value="GE Electrical" /></td>
	</tr>
	<tr>
		<td><strong>P.O #</strong></td>
		<td><input name="textfield3" type="text" class="inputBox"
			size="30" /></td>
	</tr>
	<tr>
		<td colspan="2"><input name="Button" type="button"
			class="button1" value="Search" onClick="searchponumber();" />
		&nbsp;&nbsp; <input name="Button" type="button" class="button1"
			value="Cancel" onClick="hideponumber();popupboxclose1();" /></td>
	</tr>
</table>

<div id="ponumbersearchresults"
	style="visibility: hidden; display: none;"><!--Search Results -->
<br>
&nbsp;&nbsp;<strong>Search Results</strong>
<table width="98%" align="center" cellpadding="0" cellspacing="0"
	class="InnerApplTable" style="width: 98%">
	<tr>
		<th>P.O #</th>
		<th>Balance Amount</th>
	</tr>
	<tr>
		<td><a href="#" onClick="hideponumber();popupboxclose1();">1234</a></td>
		<td>12000</td>
	</tr>
	<tr>
		<td><a href="#" onClick="hideponumber();popupboxclose1();">3456</a></td>
		<td>10000</td>
	</tr>

</table>
</div>
<!--Search Results --></form>
</div>
</div>
</div>
<!-- --------------------------------- PO # Lookup END ----------------------------------------- -->

<!-- --------------------------- Task Manager Lookup ------------------------------------------------- -->
<div class="sample_popup" id="taskmanager"
	style="visibility: hidden; display: none;">
<div class="menu_form_header" id="taskmanager_drag"
	style="width: 450px;"><img class="menu_form_exit"
	id="taskmanager_exit" src="../images/form_exit.png" />
&nbsp;&nbsp;&nbsp;Search Task Manager</div>
<div class="menu_form_body" id="taskmanagerbody"
	style="width: 450px; height: 130px;">
<form method="post" action="popup.php">
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" class="InnerApplTable">
	<tr>
		<td width="25%"><strong>Login ID: </strong></td>
		<td><input name="textfield" type="text" class="inputBox"
			size="30" /></td>
	</tr>
	<tr>
		<td><strong>First Name: </strong></td>
		<td><input name="textfield2" type="text" class="inputBox"
			size="30" /></td>
	</tr>
	<tr>
		<td><strong>Last Name:</strong></td>
		<td><input name="textfield3" type="text" class="inputBox"
			size="30" /></td>
	</tr>
	<tr>
		<td colspan="2"><input name="Button" type="button"
			class="button1" value="Search" onClick="searchtaskmanager();" />
		&nbsp;&nbsp; <input name="Button" type="button" class="button1"
			value="Cancel" onClick="hidetaskmanager();popupboxclose1();" /></td>
	</tr>
</table>

<div id="taskmanagersearchresults"
	style="visibility: hidden; display: none;"><!--Search Results -->
<br>
&nbsp;&nbsp;<strong>Search Results</strong>
<table width="98%" align="center" cellpadding="0" cellspacing="0"
	class="InnerApplTable" style="width: 98%">
	<tr>
		<th>Login ID</th>
		<th>First Name</th>
		<th>Last Name</th>
	</tr>
	<tr>
		<td><a href="#" onClick="hidetaskmanager();popupboxclose1();">Login001</a></td>
		<td>ABC</td>
		<td>PQR</td>
	</tr>
	<tr>
		<td><a href="#" onClick="hidetaskmanager();popupboxclose1();">Login002</a></td>
		<td>XYZ</td>
		<td>LMN</td>
	</tr>

</table>
</div>
<!--Search Results --></form>
</div>
</div>
</div>
<!-- --------------------------------- PO # Lookup END ----------------------------------------- -->



<div id="faderPane"
	style="visibility: hidden; display: none; z-index: 1; Position: Absolute; Left: 1px; Top: 1px;">
<IMG src="../images/fake-alpha-999.gif"></div>
</body>
</html>
