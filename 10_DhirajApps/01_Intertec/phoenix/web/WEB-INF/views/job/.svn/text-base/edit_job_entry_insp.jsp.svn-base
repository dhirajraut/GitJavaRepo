<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn"
  uri="http://www.intertek.com/jsp/jstl/functions"%>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>


<script type="text/javascript">
function showRequiredFields(){
	var myFrame=document.getElementById("requiredFieldsFrame");
	myFrame.src="required_fields.htm";
	popup_show('requiredFields', 'requiredFields_drag', 'requiredFields_exit', 'screen-corner', 120, 20);
	hideIt();
	popupboxenable();
}

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

function submitform()
{
top.document.forms[0].submit();
}
function setTowingCoFlag()
{
//document.editJobsInspForm.towingCompFlag.value="towingCompany";
}
function setShippingFlag()
{
//document.editJobsInspForm.shippingAgentFlag.value="shippingAgent";
}
function setServiceLcoationFlag()
{
document.editJobsInspForm.serviceLocationFlag.value="servicelocation";
}
function updateTowCoIframeSrc()
{
var towingCoName= document.getElementById("tco").value;
var branchName=document.getElementById("brnch").value;


if(towingCoName!= "" && towingCoName!= null)
{
document.getElementById('tow').setAttribute("src","search_towingcompany_popup.htm?inputFieldId=jobOrder.towingCompany.name&tcoName="+towingCoName+"&branchName="+branchName);
}else
{
document.getElementById('tow').setAttribute("src","search_towingcompany_popup.htm?inputFieldId=jobOrder.towingCompany.name&tcoName="+towingCoName+"&branchName="+branchName);
}
}

function updateShipAgentIframeSrc()
{
var shippingAgentId= document.getElementById("shipagnt").value;
var branchName=document.getElementById("brnch").value;
if(shippingAgentId != "" && shippingAgentId != null)
{
document.getElementById('ship').setAttribute("src","search_shippingagent_popup.htm?inputFieldId=jobOrder.shippingAgent.name&shipAgentName="+shippingAgentId+"&branchName="+branchName);
}else
{
document.getElementById('ship').setAttribute("src","search_shippingagent_popup.htm?inputFieldId=jobOrder.shippingAgent.name&shipAgentName="+shippingAgentId+"&branchName="+branchName);
}
}

function updateServiceLocationIframeSrc()
{
var serviceLocationName= document.getElementById("serviceLoc").value;
var branchName=document.getElementById("brnch").value;
var portLocationName= document.getElementById("portLoc").value;
if(serviceLocationName != "" && serviceLocationName != null)
{
document.getElementById('serv').setAttribute("src","search_serviceLocation_popup.htm?inputFieldId=jobOrder.serviceLocation.name&div1=servloc&div2=servlocbody&serviceLcoationName="+serviceLocationName + "&portLocationName=" + portLocationName+"&branchName="+branchName+"&reqForm=jobsForm");
 }else
{
document.getElementById('serv').setAttribute("src","search_serviceLocation_popup.htm?inputFieldId=jobOrder.serviceLocation.name&div1=servloc&div2=servlocbody&serviceLcoationName="+serviceLocationName + "&portLocationName=" + portLocationName+"&branchName="+branchName+"&reqForm=jobsForm");
}
}
function updatePortIframeSrc()  {
var serviceLocationName= document.getElementById("serviceLoc").value;
var portLocationName= document.getElementById("portLoc").value;
var branchName=document.getElementById("brnch").value;
if(serviceLocationName != "" && serviceLocationName != null)
{     document.getElementById('portfr').setAttribute("src","search_serviceLocation_popup.htm?inputFieldId=jobOrder.serviceLocation.city&div1=port&div2=portbody&serviceLcoationName="+serviceLocationName + "&portLocationName=" + portLocationName+"&branchName="+branchName+"&reqForm=portJobsForm");   
} else
  { document.getElementById('portfr').setAttribute("src","search_serviceLocation_popup.htm?inputFieldId=jobOrder.serviceLocation.city&div1=port&div2=portbody&serviceLcoationName="+serviceLocationName + "&portLocationName=" + portLocationName+"&branchName="+branchName+"&reqForm=portJobsForm");   
}
}
 function setPortLcoationFlag()
{   
 document.editJobsInspForm.portLocationFlag.value="portlocation";
 document.editJobsInspForm.tabName.value = "0";
}
function updateBranchIframeSrc()
{
var buName= document.getElementById("sel3").value;
if(buName!= "" && buName!= null)
{
document.getElementById('jobbu').setAttribute("src",src="search_branch_popup.htm?inputFieldId=jobOrder.branchName&div1=jobbranchcode&div2=jobbranchcodebody&buName="+buName+"&formName=jobsForm");
}
}
function makeBranchblank()
{
document.getElementById("brnch").value="";
}

function setform()
{
  var code=document.getElementById("ccode");
  if(code.value==null || code.value=="")
  {
    confirm("Please Enter search string(s) delimited by a semicolon (;) ");
    return false;
  } 
  if(code.value!=null && code.value!="")
  {
  if (isAdd(code.value) == false) {
     confirm("Please Enter a Valid Delimiters");
    document.editJobsInspForm.ccode.focus();
  document.editJobsInspForm.ccode.value="";
  return false;
    }
  if (isProper(code.value) == false) {
  confirm("Please Enter a Valid Contract Id(s)");
  document.editJobsInspForm.ccode.focus();
  document.editJobsInspForm.ccode.value="";
  return false;
    }
  if (isValid(code.value) == false) {
  confirm("Please Enter a Valid Contract Id(s)");
  document.editJobsInspForm.ccode.focus();
  document.editJobsInspForm.ccode.value="";
  return false;
    }
  document.editJobsInspForm.uniqueFlag.value="uniqueFlag";
    document.editJobsInspForm.submit();
    } 
}


function setDeleteflag(index)
{
if (confirm("Are you sure you want to delete the row?")==true)
{
document.editJobsInspForm.jobFlag.value="none";
document.editJobsInspForm.jobIndex.value = index;
document.editJobsInspForm.deleteFlag.value ="delval";
document.editJobsInspForm.submit();
}
}

function setsubflag()
{
document.editJobsInspForm.jobValFlag.value="newjobval";
document.editJobsInspForm.tabName.value = "1";
}

function setcontactflag(rowIndex)
{
document.editJobsInspForm.jobFlag.value="none";
document.editJobsInspForm.contactIndex.value=rowIndex;
document.editJobsInspForm.contactFlag.value="contFlag";
document.editJobsInspForm.tabName.value="1";
}

function setAttachFilesFlag(rowIndex)
{
document.editJobsInspForm.attachFilesFlag.value=rowIndex;
document.editJobsInspForm.tabName.value="1";
}

function isAdd(string) 
{
if (!string) return false;
var iChars = "*|,\":<>[]{}`\()$#./!@~?><";
for (var i = 0; i < string.length; i++) {
if (iChars.indexOf(string.charAt(i)) != -1)
return false;
}
return true;
}


function isProper(string)
{
if (!string) return false;
var iChars = ";";
var i=string.length-1;
if (iChars.lastIndexOf(string.charAt(i)) != -1)
{    return false;}
return true;
} 

function isValid(string)
{
if (!string) return false;
var iChars = ";";
var i=string.length-1;
if (iChars.lastIndexOf(string.charAt(0)) != -1)
{    return false;}
return true;
} 
function goToNextPage(count,dateFormat,userTimeFormat)
{
var servLoc=document.getElementById("serviceLoc").value;
var portLoc=document.getElementById("portLoc").value;
if(servLoc == "" || servLoc == null)
{
   confirm("Please select Servicelocation!");
   return false;
}
if(portLoc == "" || portLoc == null)
{
   confirm("Please select Portlocation!");
   return false;
}
document.editJobsInspForm.nextPageFlag.value = "1";
updateJobDescription(dateFormat);
checkcount(count,dateFormat,userTimeFormat)

}

function nextList()
{
//document.editJobsInspForm.nextListFlag.value="next";
document.editJobsInspForm.showNextListFlag.value="next";
// START : #119240
document.editJobsInspForm.shippingAgentFlag.value="none";
document.editJobsInspForm.towingCompFlag.value="none";
document.editJobsInspForm.serviceLocationFlag.value="none";
document.editJobsInspForm.portLocationFlag.value="none";
document.editJobsInspForm.jobFlag.value="none";
document.editJobsInspForm.uniqueFlag.value="none";
document.editJobsInspForm.shipAgentPhoneFlag.value="none";
document.editJobsInspForm.towCoPhoneFlag.value="none";
document.editJobsInspForm.operationFlag.value="none";
// END : #119240
document.editJobsInspForm.submit();
}

function prevList()
{
//document.editJobsInspForm.prevListFlag.value="prev";
document.editJobsInspForm.showPrevListFlag.value="prev";
// START : #119240
document.editJobsInspForm.shippingAgentFlag.value="none";
document.editJobsInspForm.towingCompFlag.value="none";
document.editJobsInspForm.serviceLocationFlag.value="none";
document.editJobsInspForm.portLocationFlag.value="none";
document.editJobsInspForm.jobFlag.value="none";
document.editJobsInspForm.uniqueFlag.value="none";
document.editJobsInspForm.shipAgentPhoneFlag.value="none";
document.editJobsInspForm.towCoPhoneFlag.value="none";
document.editJobsInspForm.operationFlag.value="none";
// END : #119240

document.editJobsInspForm.submit();
}

// START : #119240
function noPrevList()
{
	document.editJobsInspForm.noPrevJob.value="true";
	document.editJobsInspForm.submit();
}

function noNextList()
{
	document.editJobsInspForm.noNextJob.value="true";
	document.editJobsInspForm.submit();
}

// END : #119240

function checkSearchField()
{
if(document.getElementById("jobSearch").value=="")
{
confirm("Please enter a search string");
return false;
}
else
  {
    document.editJobsInspForm.goFlag.value="true";  
      document.editJobsInspForm.operationFlag.value = "operation";
      submitform();
  }
}
function checkDate(dateFormat,userTimeFormat)
  {
 
  var jobFinshDate = document.getElementById("finishdate").value;
  var dayfield;
  var monthfield;
  var yearfield;
 
  if('dd/MM/yyyy'== dateFormat)
  {
   dayfield=jobFinshDate.split("/")[0]
   monthfield=jobFinshDate.split("/")[1]
   yearfield=jobFinshDate.split("/")[2]
  }
   if('MM/dd/yyyy'== dateFormat)
  {
   dayfield=jobFinshDate.split("/")[1]
   monthfield=jobFinshDate.split("/")[0]
   yearfield=jobFinshDate.split("/")[2]
  }
   if('dd/MMM/yyyy'== dateFormat)
  {
   dayfield = jobFinshDate.split("/")[0]
   monthfield = jobFinshDate.split("/")[1]
   if(monthfield == 'Jan')
  monthfield = 01;  
   if(monthfield == 'Feb')
  monthfield = 02;
   if( monthfield == 'Mar')
  monthfield = 03;
   if( monthfield == 'Apr')
  monthfield = 04;
   if(monthfield == 'May')
    monthfield = 05;
   if(monthfield == 'jun')
    monthfield = 06;
   if(monthfield == 'jul')
    monthfield = 07;
   if(monthfield == 'Aug')
    monthfield = 08;
   if( monthfield == 'Sep')
      monthfield = 09;
   if( monthfield == 'Oct')
    monthfield = 10;
   if(monthfield == 'Nov')
    monthfield = 11;
   if( monthfield == 'Dec')
    monthfield = 12;
   yearfield = jobFinshDate.split("/")[2]
  }
  var currentTime = new Date();
  var month = currentTime.getMonth() + 1;
  var day = currentTime.getDate();
  var year = currentTime.getFullYear();
 if(day<10 && month<10)
    {
    var currentdate="0"+day + "/" +"0"+ month + "/" + year;
      }
    else if(day<10)
    {
       var currentdate="0"+day + "/" + month + "/" + year;
     }
     else if(month<10)
      {
         var currentdate=day + "/" +"0"+ month + "/" + year;
      }
     else
       {
     var currentdate=day + "/" + month + "/" + year;
         }
  if('dd/MMM/yyyy'== dateFormat)
  {
  if(monthfield<10)
  jobFinshDate=dayfield+"/"+"0"+monthfield+"/"+yearfield;
  else
  jobFinshDate = dayfield+"/"+ monthfield+"/"+yearfield;
  }else
  jobFinshDate=dayfield+"/"+ monthfield+"/"+yearfield;
   var str1  =jobFinshDate ;
   var str2  =currentdate;
   var dt1   = parseInt(str1.substring(0,2),10); 
   var mon1  = parseInt(str1.substring(3,5),10);
   var yr1   = parseInt(str1.substring(6,10),10); 
   var dt2   = parseInt(str2.substring(0,2),10); 
   var mon2  = parseInt(str2.substring(3,5),10); 
   var yr2   = parseInt(str2.substring(6,10),10); 
   var jFdate = new Date(yr1, mon1-1, dt1); 
   var toDate = new Date(yr2, mon2-1, dt2);
   if(jFdate > toDate )
   {
      confirm("Job Finish Date cannot be later than today.");
    document.getElementById("finishdate").value="";
      return false; 
   } 
   else 
   { 
     if(checkNomAndEtaTime(userTimeFormat))
    submitform();
   } 
}

 function checkNomAndEtaTime(userTimeFormat)
{
  
var nomTime = document.getElementById("nomTimes").value;
var etaTime = document.getElementById("etaTimes").value;
if(userTimeFormat == 'AM/PM')
{
var nomTimeFormat = document.getElementById("sel14").value;
var etaTimeFormat = document.getElementById("sel15").value;
}
var timeCheck = false;
var nomHour;
var nomMinute;

if(nomTime != '' && nomTime.indexOf(":") == -1)
{
confirm("Please enter nomination time in HH:MM:SS format.");
document.getElementById("nomTimes").value=" ";
return false;
}
if(etaTime != '' && etaTime.indexOf(":") == -1)
{
confirm("Please enter eta time in HH:MM:SS format.");
document.getElementById("etaTimes").value = "";
return false;
}
if(nomTime != '' && nomTime.indexOf(":") != -1)
{
 nomHour = nomTime.split(":")[0]
 nomMinute = nomTime.split(":")[1]
  
if(userTimeFormat != 'AM/PM')
  {
  if(nomHour < 0 || nomHour >=25)
   {
     confirm("Please enter nomination hour between 0-24.");
     document.getElementById("nomTimes").value=" "; 
     timeCheck = false;
   }else
  {
     timeCheck = true;
  }
 }
 if(nomMinute >= 60){
 confirm("Please enter nomination minute between 0-59.");
 document.getElementById("nomTimes").value=" ";
 timeCheck = false;
 return false;
}
}else
  {
  timeCheck = true;
  }

if(etaTime != '' && etaTime.indexOf(":") != -1)
{
 var etaHour = etaTime.split(":")[0]
 var etaMinute = etaTime.split(":")[1]
 if(userTimeFormat != 'AM/PM')
  {
  if(etaHour < 0 || etaHour >=25)
   {
     confirm("Please enter eta hour between 0-24.");
     document.getElementById("etaTimes").value = "";
     timeCheck = false;
   }else
   {
     timeCheck = true;
   }
 }
 if(etaMinute >= 60){
  confirm("Please enter eta minute  between 0-59.");
  document.getElementById("etaTimes").value = "";
  timeCheck = false;
  return false;
 }
}else
  {
  timeCheck = true;
  }
if(userTimeFormat == 'AM/PM')
{
  if(nomTimeFormat != '' && nomTimeFormat != null)
  {
  if(nomTime != ''&& nomTime != null)
  {
   nomTime = nomTime.split(":")[0]

   if(nomTime >=12 && (nomMinute > 00 || nomMinute >=60))
   {
    confirm("Please enter nomination time between 0-12 hour and 0-59 minute.");
    document.getElementById("nomTimes").value=" ";
    return false;
   }else
   {
    timeCheck = true;
   }
  }else
  {
  timeCheck = true;
  }
  }else
  {
  timeCheck = true;
  }
  if(etaTimeFormat != '' && etaTimeFormat != null)
  {
  if(etaTime != ''&& etaTime != null )
  {
  etaTime = etaTime.split(":")[0]

  if(etaTime >=12 && (etaMinute > 00  || etaMinute >= 60))
  {
    confirm("Please enter eta time between 0-12 hour and 0-59 minute.");
    document.getElementById("etaTimes").value = "";
    timeCheck = false;
    return false;

  }else
  {
    timeCheck = true;
  }
  }else
  {
  timeCheck = true;
  }
  }else
  {
  timeCheck = true;
  }
}
if(timeCheck)
return true;
else
return false;
}
function resetJobValFlag()
{
 document.editJobsInspForm.jobFlag.value="none";
 document.editJobsInspForm.popFlag.value="none";
 document.editJobsInspForm.contactFlag.value="none";
  document.editJobsInspForm.uniqueFlag.value="none";
  setpage();
}

function resetJob(index,ccc)
{
if(index>1)
{
var cc=ccc;
if(ccc.charAt("/"))
{ cc=ccc.replace("/","'");}
document.getElementById("ccode").value=cc;
 document.editJobsInspForm.uniqueFlag.value="uniqueFlag";
 document.editJobsInspForm.submit();
}
else{
 document.editJobsInspForm.contactFlag.value="none";
 document.editJobsInspForm.jobFlag.value="none";
 document.editJobsInspForm.popFlag.value="none";
 document.editJobsInspForm.uniqueFlag.value="none";
 setpage();
} 
}





  function updateJobDescription(dateFormat,jobDescExist)
  {
   var w = document.editJobsInspForm.sel5.selectedIndex;
   var ops_text = document.editJobsInspForm.sel5.options[w].text;
 
  var etaDate=document.getElementById("etadates").value;
  var finishDate=document.getElementById("finishdate").value;
  var servLoc=document.getElementById("serviceLoc").value;
  var portLoc=document.getElementById("portLoc").value;
  var product=document.getElementById("product").value;
  var vessel=document.getElementById("vessel").value;
  var date=null;
  if(finishDate != "" && finishDate != null)
   {
     date=jobDescDate(finishDate,dateFormat);
   }
 else
   {
    date = jobDescDate(etaDate,dateFormat);
   }

   if(date == null)
   date="";
  var svLoc= new Array();
  svLoc = servLoc.split(",")
  var newServLoc="";
  var i=0;
  while(i<svLoc.length)
  {
    if(i==0)
      newServLoc = trimAll(svLoc[i]);
    else
    newServLoc = newServLoc+","+" "+trimAll(svLoc[i]);
    i++;
  }
  var desc = ops_text+" "+"of"+" "+product+" "+"on"+" "+vessel+" "+"at"+" "+newServLoc+" "+"on"+" "+date;
  if(jobDescExist == 'jobdescexist')
  {
   document.getElementById("jobDesc").value=desc;
     return desc; 
  }

 //new code start
    var multiProduct = new Array();
  var newMultiProduct="";
  
  if(product.indexOf(',') != -1)
    {
    var j=0;
    multiProduct = product.split(",")
    while(j<multiProduct.length)
    {
      var mproduct = multiProduct[j];
      var prod = new Array();
      if(mproduct.indexOf('-') != -1)
        {
      prod = mproduct.split("-")
      var length = prod.length;
      var newProduct="";
      var i=0;
      while(i<length-1)
      {
        newProduct=newProduct+prod[i];
        if(i!= length-2)
        newProduct = newProduct+"-";
        i++;
      }
      if(newMultiProduct == '')
      newMultiProduct = newProduct;
      else
      newMultiProduct = newMultiProduct+","+newProduct;
      }else
      {
        if(j==0)
          newMultiProduct = newMultiProduct+mproduct;
        else
        newMultiProduct = newMultiProduct+","+mproduct;
      }
      
      j++;
    }
    
    }else
    {
     if(product.indexOf('-') != -1)
      {
      prod = product.split("-")
      var length = prod.length;
      var newProduct="";
      var i=0;
      while(i<length-1)
      {
        newProduct=newProduct+prod[i];
        if(i!= length-2)
        newProduct = newProduct+"-";
        i++;
      }
      newMultiProduct = newProduct;
      }else
      newMultiProduct = product;
    }

    //new code end
      
  var addCustomerdesc = ops_text+" "+"of"+" "+newMultiProduct+" "+"on"+" "+vessel+" "+"at"+" "+newServLoc+" "+"on"+" "+date;
 if(document.getElementById("jobDesc").value=="")
   {
     document.getElementById("jobDesc").value=desc;
     return addCustomerdesc; 
  } else
    //return document.getElementById("jobDesc").value;
 return addCustomerdesc;
  }




function trimAll(sString) 
{
while (sString.substring(0,1) == ' ') 
{ 
sString = sString.substring(1, sString.length); 
} 
while (sString.substring(sString.length-1, sString.length) == ' ') 
{ 
sString = sString.substring(0,sString.length-1); 
} 
return sString; 
}



function jobDescDate(date,dateFormat)
{
   var dayfield;
     var monthfield;
     var yearfield;
 
  if('dd/MM/yyyy'== dateFormat)
  {
   dayfield=date.split("/")[0]
   monthfield=date.split("/")[1]
   yearfield=date.split("/")[2]
  }
   if('MM/dd/yyyy'== dateFormat)
  {
   dayfield=date.split("/")[1]
   monthfield=date.split("/")[0]
   yearfield=date.split("/")[2]
  }
   if('dd/MMM/yyyy'== dateFormat)
  {
   dayfield=date.split("/")[0]
   monthfield=date.split("/")[1]
   yearfield=date.split("/")[2]
  }
  
     if(monthfield == 01 || monthfield == 1 || monthfield == 'Jan')
     {
     if('dd/MM/yyyy'== dateFormat || 'dd/MMM/yyyy'== dateFormat)
     date=dayfield+" "+"January"+","+" "+yearfield;
     if('MM/dd/yyyy'== dateFormat)
     date="January"+" "+dayfield+","+" "+yearfield;
     }
     if(monthfield == 02 || monthfield == 2 || monthfield == 'Feb')
     {
       if('dd/MM/yyyy'== dateFormat || 'dd/MMM/yyyy'== dateFormat)
     date=dayfield+" "+"February"+","+" "+yearfield;
     if('MM/dd/yyyy'== dateFormat)
     date="February"+" "+dayfield+","+" "+yearfield;
     }
     if(monthfield == 03 || monthfield == 3 || monthfield == 'Mar')
     {
       if('dd/MM/yyyy'== dateFormat || 'dd/MMM/yyyy'== dateFormat)
     date=dayfield+" "+"March"+","+" "+yearfield;
     if('MM/dd/yyyy'== dateFormat)
     date="March"+" "+dayfield+","+" "+yearfield;
     }
     
     if(monthfield == 04 || monthfield == 4 || monthfield == 'Apr')
    {
       if('dd/MM/yyyy'== dateFormat || 'dd/MMM/yyyy'== dateFormat)
     date=dayfield+" "+"April"+","+" "+yearfield;
     if('MM/dd/yyyy'== dateFormat)
     date="April"+" "+dayfield+","+" "+yearfield;
     }
       
     if(monthfield == 05 || monthfield == 5 || monthfield == 'May')
     {
       if('dd/MM/yyyy'== dateFormat || 'dd/MMM/yyyy'== dateFormat)
     date=dayfield+" "+"May"+","+" "+yearfield;
     if('MM/dd/yyyy'== dateFormat)
     date="May"+" "+dayfield+","+" "+yearfield;
     }
     if(monthfield == 06 || monthfield == 6 || monthfield == 'jun')
     {
       if('dd/MM/yyyy'== dateFormat || 'dd/MMM/yyyy'== dateFormat)
     date=dayfield+" "+"June"+","+" "+yearfield;
     if('MM/dd/yyyy'== dateFormat)
     date="June"+" "+dayfield+","+" "+yearfield;
     }
     
     if(monthfield == 07 || monthfield == 7 || monthfield == 'jul')
     {
       if('dd/MM/yyyy'== dateFormat || 'dd/MMM/yyyy'== dateFormat)
     date=dayfield+" "+"July"+","+" "+yearfield;
     if('MM/dd/yyyy'== dateFormat)
     date="July"+" "+dayfield+","+" "+yearfield;
     }
      
     if(monthfield == 08 || monthfield == 8 || monthfield == 'Aug')
     {
       if('dd/MM/yyyy'== dateFormat || 'dd/MMM/yyyy'== dateFormat)
     date=dayfield+" "+"August"+","+" "+yearfield;
     if('MM/dd/yyyy'== dateFormat)
     date="August"+" "+dayfield+","+" "+yearfield;
     }
      
     if(monthfield == 09 || monthfield == 9 || monthfield == 'Sep')
     {
       if('dd/MM/yyyy'== dateFormat || 'dd/MMM/yyyy'== dateFormat)
     date=dayfield+" "+"September"+","+" "+yearfield;
     if('MM/dd/yyyy'== dateFormat)
     date="September"+" "+dayfield+","+" "+yearfield;
     }
      
     if(monthfield == 10 || monthfield == 'Oct')
     {
       if('dd/MM/yyyy'== dateFormat || 'dd/MMM/yyyy'== dateFormat)
     date=dayfield+" "+"October"+","+" "+yearfield;
     if('MM/dd/yyyy'== dateFormat)
     date="October"+" "+dayfield+","+" "+yearfield;
     }
      
     if(monthfield == 11 || monthfield == 'Nov')
     {
       if('dd/MM/yyyy'== dateFormat || 'dd/MMM/yyyy'== dateFormat)
     date=dayfield+" "+"November"+","+" "+yearfield;
     if('MM/dd/yyyy'== dateFormat)
     date="November"+" "+dayfield+","+" "+yearfield;
     }
      
      
     if(monthfield == 12 || monthfield == 'Dec')
     {
       if('dd/MM/yyyy'== dateFormat || 'dd/MMM/yyyy'== dateFormat)
     date=dayfield+" "+"December"+","+" "+yearfield;
     if('MM/dd/yyyy'== dateFormat)
     date="December"+" "+dayfield+","+" "+yearfield;
     }
  
     return date;
}
function setpage(){
document.editJobsInspForm.ccode.value="";
}

function setTabName(val){
document.editJobsInspForm.tabName.value=val;
}

function enterButton() {
if (event.keyCode == 13) {
  if(document.getElementById("ccode").value!="")
  {
    setform();
  }
   }
}

function updateDescription(index,dateFormat){
  if(document.getElementById("invoiceDescrdetails"+index).value=="")
  {
  var description="General service charge in connection with the"+" "+updateJobDescription(dateFormat)+".";
  document.getElementById("invoiceDescrdetails"+index).value=description;
  }
  else  if(document.getElementById("invoiceDescrdetails"+index).value!="")
  {
    document.getElementById("invoiceDescrdetails"+index).value="General service charge in connection with the"+" "+updateJobDescription(dateFormat)+".";
    }
}


 function updateBankAccountIframe(index,buname,currency){
var bcode=document.getElementById("remitto"+index).value;
document.getElementById('saccountframe'+index).setAttribute("src","search_bank_account_popup.htm?inputFieldId=addJobContracts["+index+"].jobContract.bankAcctKey&rowNum="+index+"&buName="+buname+"&currency="+currency+"&bankCode="+bcode);
}

function updateBankIframe(index,buname,currency){ 
document.getElementById('sremittoframe'+index).setAttribute("src","search_bank_popup.htm?inputFieldId=addJobContracts["+index+"].jobContract.bankCd&rowNum="+index+"&buName="+buname+"&currency="+currency);
}

function updateBillingContactIframeSrc(index,customercode,contractcode){
document.getElementById('searchContactFr'+index).setAttribute("src","search_contact_popup.htm?inputFieldId=addJobContracts["+index+"].jobContract.billingContact.id&rowNum="+index+"&searchForm=editJobsInspForm&divName=searchbillingcontact"+index+"&divbody=contactbody"+index+"&custCode="+customercode+"&contractCode="+contractcode);
}

function updateMonthlyJobIdIframeSrc(index,buname,branchname,contractCode,pcode){
document.getElementById('searchmonthlyJob'+index).setAttribute("src","search_monthly_job_popup.htm?inputFieldId=addJobContracts["+index+"].jobContract.monthlyJobNumber&buName="+buname+"&branchName="+branchname+"&rowNum="+index+"&searchForm=editJobsInspForm&divName=monthlyJob"+index+"&divbody=monthlyJobbody"+index+"&contractCode="+contractCode+"&pcustCode="+pcode);
}


function checkContactIds(contactcount)
{
  for(i=0;i<contactcount;i++)
  {
    var elemname = "billingcontactid"+i;
    if(document.getElementById(elemname).value == '')
    {
      document.getElementById(elemname).value = "0";
    }
  }
}

function setbankFlag(index){
document.editJobsInspForm.bankFlag.value="bankFlag";
document.editJobsInspForm.tabName.value="1";
document.editJobsInspForm.bankIndex.value=index;
document.editJobsInspForm.submit();
}

function monjobnumberFlag(index,jobnumber){
  
  if(document.getElementById("monFlag"+index).checked)
     {
    document.getElementById("mjobid"+index).className="unprotected";
    document.getElementById("monjobnumber"+index).value =jobnumber ;  
    document.getElementById("monjobnumber"+index).contentEditable="false";  
  document.getElementById("anchor"+index).style.visibility="hidden";
     }

      else  if(!document.getElementById("monFlag"+index).checked)
    {
      document.editJobsInspForm.monthlyJobFlag.value="monthlyid";
      document.editJobsInspForm.rowNum.value=index;
      document.getElementById("monjobnumber"+index).value ="" ;
      document.getElementById("anchor"+index).style.visibility="visible";
          document.editJobsInspForm.submit();
    }
  }

function checkForRebill(elemid,index,contractCode,jobContractId)
{
  var status = document.getElementById(elemid).value;
  var msg;
  if(status=='7100')
  {
	  msg="Are you Sure you want to rebill the invoice?";
  }
  else if(status=='7200')
	{
	  msg="Are you Sure you want to credit the invoice?";
	}
	if(status!='6000'){
 if(confirm(msg)==true)
	{
    document.editJobsInspForm.rebillFlag.value="rebill";
    document.editJobsInspForm.contractIndex.value=index;
    popCreditReason(index,contractCode,jobContractId);
  }
  else
	{
	  document.getElementById(elemid).value='6000';
	}
	}
}

function popCreditReason(index, contractCode, jobContractId)
{  
  
  popup_show('creditreason_' + index, 'creditreason_drag_' + index, 'creditreason_exit_' + index, 'screen-corner', 120, 20);
  hideIt();
  popupboxenable();

  document.getElementById("creditreasonbox_" + index).src="credit_reason_popup.htm?contractCode=" + contractCode + "&jobContractId=" + jobContractId + "&contractIndex=" + index+ "&controllerName=EditJobOrderInspFormController";                            
  document.getElementById("creditreasonbox_" + index).height = "250px";
}

function popCancelReason(jobNumber)
{  
  var status =  document.getElementById("sel1").value;
  
  if(status != null && status =='1')
  {
  popup_show('cancelreason', 'cancelreason_drag', 'cancelreason_exit', 'screen-corner', 120, 20);
  hideIt();
  popupboxenable();

  document.getElementById("cancelreasonbox").src="job_cancel_reason_popup.htm?jobNumber="+jobNumber;                            
  document.getElementById("cancelreasonbox").height = "250px";
  }
}

function changeVisibleStyle(){
document.getElementById("serviceLoc").value="";
}


function showError(ecode,sc)
{
if(sc!="")
{ 
 confirm("some or all of the entered criteria resulted in error. Review the errors below.\n No products were find matching the entered criteria "+ ecode +". change the criteria and try again");
 document.editJobsInspForm.errorFlag.value="none";
 document.editJobsInspForm.uniqueFlag.value="uniqueFlag";
 document.getElementById("ccode").value=sc;
 document.editJobsInspForm.submit();
}
else{
confirm("some or all of the entered criteria resulted in error. Review the errors below.\n No products were find matching the entered criteria "+ ecode +". change the criteria and try again");
 document.editJobsInspForm.errorFlag.value="none";
 document.editJobsInspForm.ccode.focus();
 document.editJobsInspForm.ccode.value="";
}
}


function billingContactAjax()
{
  document.editJobsInspForm.jobFlag.value="none";
  document.editJobsInspForm.deleteFlag.value ="none";
  document.editJobsInspForm.contactFlag.value="contFlag";
  document.editJobsInspForm.tabName.value="1";
  document.editJobsInspForm.submit();
}

/*function creCheck(index){    
     if(!document.getElementById("creapp"+index).checked)
      {
           if (confirm("If you uncheck this then invoice cannot be generated")==true)
               {
         document.getElementById("creapp"+index).checked=false;
               }
         else
            {
            document.getElementById("creapp"+index).checked=true;
            }
     }   
  }
*/

/* function checkcount(count,dateFormat,userTimeFormat) {
   if(count>0)
   {
   var a='false';
   for(var index=0;index<count;index++)
   {
  if(!document.getElementById("creapp"+index).checked){
    a='true';
    }
    }
    if(a=='true')
    {
    if(confirm("unchecked credit application approved checkbox cannot generate the invoice")==true)
      {
        checkDate(dateFormat,userTimeFormat);
      }
      else
          {a='false';}
   }   
   else
   {
    a='false';
    checkDate(dateFormat,userTimeFormat);
    }
   }
   else
   {checkDate(dateFormat,userTimeFormat);
   }
 }
*/
function checkcount(count,dateFormat,userTimeFormat) {
  checkDate(dateFormat,userTimeFormat);
}

 function contractAttach(contractCode,index)
  {
   document.getElementById('contractFr'+index).setAttribute("src","view_contract_attach_popup.htm?contractCode="+contractCode);
  }
  
function selectJobDateBoolean(flag)
 {
 document.editJobsInspForm.jobDateFlag.value="jobDate";
 document.editJobsInspForm.dateFlag.value=flag;
 if(flag=='true')
  document.editJobsInspForm.openPeriodsFlag.value="true";
 else
 document.editJobsInspForm.openPeriodsFlag.value="false";
 document.editJobsInspForm.submit();
 }
 
function selectJobDate(flag,elemId)
 {
 document.editJobsInspForm.jobDateFlag.value="jobDate";
 document.editJobsInspForm.dateFlag.value=flag;
 
 var jobDate = document.getElementById(elemId).value;
 
        
        var firstIndex = jobDate.indexOf("/");
         var lastIndex = jobDate.lastIndexOf("/");
         var strLen = jobDate.length;
         var yearSubStr = jobDate.substr(lastIndex+1,strLen);
         var newYearSubStr;
         var newDateStr;
         var dayMonthStr = jobDate.substr(0,lastIndex+1);
         
         if(yearSubStr.length == 2)
         {
          newYearSubStr = "20" + yearSubStr;
          newDateStr = dayMonthStr + newYearSubStr;
          document.getElementById(elemId).value = newDateStr;
      }
      
 if(flag=='true')
  document.editJobsInspForm.openPeriodsFlag.value="true";
 else
 document.editJobsInspForm.openPeriodsFlag.value="false";
 document.editJobsInspForm.submit();
 }
 
 
function showInvoiceWarn()
{
confirm("Prior month GL is now closed. Your invoice will now be posted to the current month.");
document.editJobsInspForm.showWarn.value="false";
}
 
 function warnUser(navigationUrl)
{
  if(document.editJobsInspForm.warnUserFlag.value == 'warn' || document.editJobsInspForm.warnUserFlag.value == 'warned')
    document.editJobsInspForm.warnUserFlag.value = 'navigate';
  else
    document.editJobsInspForm.warnUserFlag.value = "warn";
    
  document.editJobsInspForm.navigationUrl.value = navigationUrl;
  document.editJobsInspForm.submit();
}
 function setBillingAsScheduler(index, contact_id, contact_name, address){
		//confirm('here ' + index + ' contact id = ' + contact_id + ' name = ' + contact_name + ' address = ' + address);
		var sameBilling = document.getElementById('sameBillingAsScheduler'+index).checked;
		if (sameBilling){
			document.getElementById('billingContactId'+index).value=contact_id;
			document.getElementById('billingContactName'+index).value=contact_name;
			document.getElementById('billingContactAddress'+index).value=replaceHtmlCode(address);
		}else{
			document.getElementById('billingContactId'+index).value=0;
			document.getElementById('billingContactName'+index).value='';
			document.getElementById('billingContactAddress'+index).value='';
		}
	}
	function replaceHtmlCode(address){
		var newAddr;
		newAddr = address.replace(/&#045;/gi, "-");
		newAddr = newAddr.replace(/&#035;/gi, "#");
		return newAddr;
	}

</script>
<style type="text/css">
   
div.select { text-align:center; margin-bottom:1.6em }

/* This is where you can customize the appearance of the tooltip */
div#tipDiv {
  position:absolute; visibility:hidden;
  left:0; top:0; z-index:1000;
  width:auto; height:auto; padding:3px; font-size:11px;
  font-family:Arial, Helvetica, sans-serif;
  border-color: #b0c8f2;
  border-style: double;
  filter:progid:DXImageTransform.Microsoft.gradient(gradientType=1, startColorstr=#ffffff, endColorstr=#dde7fa);
  background: url(../images/tooltipbg.jpg) repeat-y;
  }
</style>

<icb:list var="divisions">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
</icb:list>

<icb:list var="divisionBu">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
  <icb:item>${command.jobOrder.branch.businessUnit.name}</icb:item>
</icb:list>

<icb:list var="searchfields">
  <icb:item>searchfields</icb:item>
</icb:list>
<icb:list var="jobContractStatus">
  <icb:item>jobContractStatus</icb:item>
</icb:list>
<icb:list var="origin">
  <icb:item>origin</icb:item>
</icb:list>
<icb:list var="selectedLanguage">
  <icb:item>selectedLanguage</icb:item>
</icb:list>
<icb:list var="invoiceType">
  <icb:item>invoiceType</icb:item>
</icb:list>
<icb:list var="paymentType">
  <icb:item>paymentType</icb:item>
</icb:list>
<icb:list var="jobTimeFormat">
  <icb:item>jobTimeFormat</icb:item>
</icb:list>
<icb:list var="productType">
  <icb:item>productType</icb:item>
</icb:list>
<body onkeyup="enterButton()"> 
<form:form name="editJobsInspForm" method="POST"  action="edit_job_entry_insp.htm">

  <div style="color:red;"><form:errors cssClass="error" /></div>

  <form:hidden path="towingCompFlag" />
  <form:hidden path="shippingAgentFlag" />
  <form:hidden path="serviceLocationFlag" />
  <form:hidden path="portLocationFlag" />
  <form:hidden path="tabName" />
    <form:hidden path="jobFlag"/>
  <form:hidden path="addOrDeleteJob"/>
  <form:hidden path="jobIndex"/>
  <form:hidden path="jobCount"/>
  <form:hidden path="inputFieldIdValue"/>
  <form:hidden path="deleteFlag"/>
  <form:hidden path="rowNum" />
  <form:hidden path="jobValFlag"/>
  <form:hidden path="contactIndex"/>
  <form:hidden path="contactFlag"/>
  <form:hidden path="attachedFileNames"/>
  <form:hidden path="attachFilesFlag"/>
  <form:hidden path="nextPageFlag" />
<!-- START : #119240 -->
 <!--  <input type="hidden" name="nextListFlag" value=""/> -->
 <!--  <input type="hidden" name="prevListFlag" value=""/> -->
  <input type="hidden" name="showNextListFlag" value=""/>
   <input type="hidden" name="showPrevListFlag" value=""/> 	
<form:hidden path="nextListFlag" />
<form:hidden path="prevListFlag" />
  <input type="hidden" name="noPrevJob" value=""/>
  <input type="hidden" name="noNextJob" value=""/> 	
 <!-- END : #119240 -->
  <form:hidden path="uniqueFlag"/>
  <form:hidden path="popFlag"/>
   <form:hidden path="shipAgentPhoneFlag"/> 
  <form:hidden path="towCoPhoneFlag"/>
    <form:hidden path="bankIndex"/>
  <form:hidden path="bankFlag"/>
  <form:hidden path="bankCode"/>
  <form:hidden path="rebillFlag"/>
  <form:hidden path="contractIndex"/>
  <form:hidden path="creditReasonNote"/>
  <form:hidden path="creditReasonUser"/>
  <form:hidden path="operationFlag"/>
   <form:hidden path="errorFlag"/>
  <form:hidden path="errorCode"/>
  <form:hidden path="contrCode"/>
  <form:hidden path="contrFlag"/>
  <form:hidden path="jobDateFlag"/>
<form:hidden path="navigationUrl" />
<form:hidden path="warnUserFlag"/>  
<form:hidden path="monthlyJobFlag"/>
<form:hidden path="monthlyCheck"/>
 <form:hidden path="openPeriodsFlag"/>
  <form:hidden path="showWarn"/>
  <input type="hidden" name="refreshing" value="true" />
  <form:hidden path="goFlag"/>
  <form:hidden path="dateFlag"/>
  <form:hidden path="goForm"/>
  <form:hidden path="jobCancelReasonNote"/>
  <form:hidden path="jobCancelReasonUser"/>


<c:if test="${requestScope['noJobMessage'] != null}">  
  <div style="color:green;">
    ${requestScope.noJobMessage}
  </div>
</c:if>


<c:set var="urlSuffix" value="${icbfn:urlSuffixByJobType(command.jobOrder.jobType)}" scope="request" />    
<c:set var="urlPrefix" value="${icbfn:urlPrefixByJobStatus(command.jobOrder.jobStatus)}" scope="request" />  
<c:set var="jobStatusByInvoiceType" value="${icbfn:jobStatusByInvoiceType(command.jobOrder.jobNumber)}" scope="request" />
<c:set var="reopenViewOnly"  value="${!empty command.jobOrder.jobStatus && command.jobOrder.jobStatus  == '7000'}"/>
    <icb:list var="jobStatus">
    <icb:item>jobStatus</icb:item>
    <icb:item>${command.jobOrder.jobStatus}</icb:item>
    <icb:item>${icbfn:jobContractCount(command.jobOrder.jobNumber)}</icb:item>
    <icb:item>${icbfn:invGeneratedFlag(command.jobOrder.jobNumber)}</icb:item>	
	<icb:item>${command.jobOrder.jobNumber}</icb:item>
	<icb:item>${command.jobOrder.jobFinishDate}</icb:item>
  </icb:list>  
  <!-- --------------------------------------- MAIN CONTAINER -------------------------------------------- -->
  <table border="0" cellpadding="0" cellspacing="0" class="MainTable">
    <tr>
            <td valign="top"><!-- BREADCRUMB TRAIL  -->
      <div id="breadcrumbContainer" >
        <table width="100%" border="0" cellpadding="0" cellspacing="0" background="images/intopbluetrailbg.jpg">
          <tr>
            <td width="25"><img src="images/inlfttrailcorner.gif" width="8" height="22"></td>
            <td>
              <table height="22" border="0" align="left" cellpadding="0" cellspacing="0">
                <tr>
                  <td class="breadcrumbtrailDeactive" style="background:none; padding-left:5px;">&#9658; 
                     <a href="${urlPrefix}_job_entry_${urlSuffix}.htm?jobNumber=${command.jobOrder.jobNumber}"> 
                    <!--<a href="#" onClick="warnUser('${urlPrefix}_job_entry_${urlSuffix}.htm?jobNumber=${command.jobOrder.jobNumber}')">-->
                      <f:message key="entry"/>
                    </a>
                  </td>
                  <td class="breadcrumbtrailDeactive">
                    <c:choose>
                <c:when test="${command.jobOrder.pageNumber >= 2}">               
                    <a href="${urlPrefix}_job_operational_info_insp.htm?jobNumber=${command.jobOrder.jobNumber}"> 
                     <!--<a href="#" onClick="warnUser('${urlPrefix}_job_operational_info_insp.htm?jobNumber=${command.jobOrder.jobNumber}')">-->
                      <f:message key="jobInstructions"/>
                    </a>
                    </c:when>
                    <c:otherwise>
                    <f:message key="jobInstructions"/>
                    </c:otherwise>
                    </c:choose>
                    </td>
                    <td class="breadcrumbtrailDeactive"> 
                    <c:choose>
                <c:when test="${command.jobOrder.pageNumber >= 3 }">               
                     <a href="edit_job_select_charges.htm?jobNumber=${command.jobOrder.jobNumber}"> 
                    <!--<a href="#" onClick="warnUser('edit_job_select_charges.htm?jobNumber=${command.jobOrder.jobNumber}')">-->
                      <f:message key="selectCharges"/>
                    </a>
                    </c:when>
                    <c:otherwise>
                    <f:message key="selectCharges"/>
                    </c:otherwise>
                    </c:choose>
                    </td>
                  <td class="breadcrumbtrailDeactive"> 
                    <c:choose>
                <c:when test="${command.jobOrder.pageNumber >= 4}">               
                     <a href="edit_job_invoice_preview.htm?jobNumber=${command.jobOrder.jobNumber}"> 
                    <!--<a href="#" onClick="warnUser('edit_job_invoice_preview.htm?jobNumber=${command.jobOrder.jobNumber}')">-->
                     <f:message key="preview"/>
                    </a>
                    </c:when>
                    <c:otherwise>
                     <f:message key="preview"/>
                    </c:otherwise>
                    </c:choose>
                    </td>               
 
              <td class="breadcrumbtrailDeactive"> 
                   <c:choose>
                <c:when test="${command.jobOrder.pageNumber >= 5}">               
                    <a href="edit_job_view_invoice.htm?jobNumber=${command.jobOrder.jobNumber}"> 
                   <!-- <a href="#" onClick="warnUser('edit_job_view_invoice.htm?jobNumber=${command.jobOrder.jobNumber}')">-->
                      <f:message key="invoice"/>
                    </a>
                    </c:when>
                    <c:otherwise>
                     <f:message key="invoice"/> 
                    </c:otherwise>
                    </c:choose>  
                    </td>           
 
         
                   
                  
                  <td align="right">&nbsp;</td>
                </tr>
              </table>
            </td>
			<td align="right">
				<a href="#" onClick="javascript:showRequiredFields()"><f:message key="requiredFields"/></a>
			</td>

            <td align="right"><img src="images/inrttrailcorner.gif" width="7" height="22"></td>
          </tr>
        </table>
      </div>

      <!-- BREADCRUMB TRAIL END -->
  <div id="MainContentContainer"><!-- TABS CONTENTS -->
  <div id="tabcontainer">
  <div id="tabnav">
  <ul>
    <li><a href="#" rel="tab1"><span><f:message key="entryForm"/></span></a></li>
    <li><a href="#" rel="tab2"><span><f:message key="addCustomers"/></span></a></li>
  </ul>
  </div>
  <!-- Sub Menus container. Do not remove -->
  <div id="tab_inner"><!-- ----------- TAB 1 CONTAINER ------------------ -->

  <!-- ----------- TAB 1 CONTAINER ------------------ -->

  <div id="tab1" class="innercontent">
  <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
    <tbody>
      <tr bgcolor=#ffffff>
        <th width="50%" colspan=2 nowrap><f:message key="headerDetails" />
        <img src=" images/separator2.gif" width="2" height="27"
          align="absmiddle" style="margin-left:5px;margin-right:5px;" /><f:message
          key="jobId" />: ${command.jobOrder.jobNumber}<img
          src=" images/separator2.gif" width="2" height="27"
          align="absmiddle" style="margin-left:5px;margin-right:5px;" /><f:message
          key="jobTypeOCAMNInspectionTesting" /></th>
        <th colspan=3 width="50%" style="text-align:right">
        <table width="100%" align="right" cellpadding="0" cellspacing="0">
          <tr>
            <th nowrap
              style="border-bottom:none; padding-left:0px; padding-right:0px;">
            <f:message key="status" />: <form:select id="sel1"
              cssClass="selectionBox" path="jobOrder.jobStatus" 
			  onchange="popCancelReason('${command.jobOrder.jobNumber}')"
              items="${icbfn:dropdown('staticDropdown',jobStatus)}" itemLabel="name"
              itemValue="value" /> <form:errors path="jobOrder.jobStatus"
              cssClass="redstar" /> &nbsp;&nbsp; <!--form:select
              cssClass="selectionBox" id="sel2" path="searchField"
              items="${icbfn:dropdown('staticDropdown',searchfields)}" itemLabel="name"
              itemValue="value" /> <form:errors path="searchField"
              cssClass="redstar" /> &nbsp; <f:message key="id" />:&nbsp; <form:input
              id="jobSearch" size="12" cssClass="inputBox" path="searchString" onkeypress="if(event.keyCode==13) {checkSearchField();}"/> <form:errors
              path="searchString" cssClass="redstar" /> <input name="Submit2"
              type="button" class="button1" value="Go" style="height:18px;"
              onclick="javascript:checkSearchField();" /-->
<f:message key="projectType" />:&nbsp; 
<form:select id="projectTypes" cssClass="selectionBox" path="phxProjectType" items="${command.projectTypes}" itemLabel="name" itemValue="value" disabled="${command.projectTypeViewOnly}" />

</th>
              <th style="padding-right:0px; border-bottom:none">
		<!-- START : #119240 -->
		  <c:choose>
            <c:when test="${command.originateFromSearchJob==null}">
				&nbsp;
            </c:when>
            <c:otherwise>
              <a href="${command.originateFromSearchJob}?jobNum=${command.jobNumber}">
			   <img src="images/icofindjob.gif" alt="Return to Search" width="16" height="14" border="0" align="absmiddle">			  
			  </a>
			</c:otherwise>
           </c:choose>        
		  <c:choose>
			<c:when test="${command.prevListFlag=='true'}">
              <a href="#" onClick="javascript:prevList();">
			  <img src="images/prevleftarrow.gif" alt="Go to Previous Job" width="13" height="12" hspace="2" border="0"/>
			  </a>
             </c:when>
            <c:otherwise>
		      <c:if test="${command.originateFromSearchJob!=null}">
				  <a href="#" onClick="javascript:noPrevList();">
				  <img src="images/prevleftarrow.gif" alt="Go to Previous Job" width="13" height="12" hspace="2" border="0"/>
				  </a>
			   </c:if>	
	        </c:otherwise>
        </c:choose>   
	    <c:choose>
          <c:when test="${command.nextListFlag=='true'}">
              <a href="#" onClick="javascript:nextList();">
			  <img src="images/nextrtarrow.gif" alt="Go to Next Job" width="13" height="12" hspace="2" border="0"/></a>
          </c:when>
          <c:otherwise>
			  <c:if test="${command.originateFromSearchJob!=null}">
				 <a href="#" onClick="javascript:noNextList();">
				  <img src="images/nextrtarrow.gif" alt="Go to Next Job" width="13" height="12" hspace="2" border="0"/></a>
			  </c:if>
          </c:otherwise>
        </c:choose>
		<!-- END : #119240 -->	  
			  <a
              href="#" onClick="javascript:checkContactIds('${fn:length(command.addJobContracts)}');goToNextPage('${command.custCount}','${command.dateFormat}','${command.userTimeFormat}')"><img
              src="images/savennextbluarrow.gif" alt="Save and Go to Next Page"
              width="14" height="14" hspace="4" border="0" align="absmiddle"
              title="Save and Go to Next Page"></a><a href="#"
              onclick="javascript:updateJobDescription('${command.dateFormat}');setTabName('0');checkContactIds('${fn:length(command.addJobContracts)}');checkcount('${command.custCount}','${command.dateFormat}','${command.userTimeFormat}');
        <%--checkDate('${command.dateFormat}','${command.userTimeFormat}');--%>"><img src="images/icosave.gif"
              alt="Save" title="Save" width="14" height="14" border="0"
              align="absmiddle" /></a></th>
          </tr>
        </table>

        </th>
      </tr>
      <tr>
        <td width="15%" class="TDShade"><strong><f:message
          key="businessUnit" />:</strong><span class="redstar">*</span></td>
        <td width="35%" class="TDShadeBlue"><span class="id_input">
        <form:select id="sel3" disabled="true" cssClass="selectionBoxbig"
          path="jobOrder.buName"
          items="${icbfn:dropdown('businessUnit', null)}"
          itemLabel="name" itemValue="value" onchange="makeBranchblank()"/> <form:errors
          path="jobOrder.buName" cssClass="redstar" /> </span></td>
        <td width="13%" class="TDShade"><strong><f:message
          key="branchCode" />:<span class="redstar">*</span></strong></td>
        <td width="35%" colspan="2" class="TDShadeBlue">
        <form:input id="brnch" size="52" disabled="true" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;"
          cssClass="inputBox" path="jobOrder.branchName"/>
        <form:errors path="jobOrder.branchName"
          cssClass="redstar" />
         <a href="#" style="visibility:hidden" onClick="javascript:updateBranchIframeSrc();popup_show('jobbranchcode', 'jobbranchcode_drag', 'jobbranchcode_exit', 'screen-corner', 80, 80);hideIt();showbranchcode('jobbranchcode','jobbranchcodebody');popupboxenable()"><img
          style="visibility:hidden" src=" images/lookup.gif" width="13" height="13" border="0" />
         </a></td>
      </tr>
      <icb:list var="operations">
<icb:item>${command.jobOrder.jobType}</icb:item>
</icb:list>

<tr>
<td class="TDShade"><strong><f:message key="operation" />:<span class="redstar">*</span></strong></td>
<td class="TDShadeBlue"><form:select id="sel5"  cssClass="selectionBoxbig" path="jobOrder.operation"  items="${icbfn:dropdown('jobOperations', operations)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly && !(reopenViewOnly && (empty command.jobOrder.operation || empty fn:trim(command.jobOrder.operation)))}"/> <form:errors path="jobOrder.operation" cssClass="redstar" /></td>



        <td class="TDShade"><strong><f:message
          key="receivedBy" />: </strong></td>
        <td colspan="2" class="TDShadeBlue"><form:input
          cssClass="inputBox" maxlength="128" size="52" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;"
          path="jobOrder.receivedByUserName" disabled="${command.viewOnly}"/> <form:errors
          path="jobOrder.receivedByUserName" cssClass="redstar" /> 
          <c:if test="${!command.viewOnly}">
          <a href="#" onClick="javascript:popup_show('receivedby','receivedby_drag','receivedby_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('receivedby','receivedbybody');popupboxenable();">
        <img src="images/lookup.gif" alt="Lookup receivedby" width="13" height="13" border="0" /></a>
          </c:if>
        </td>
      </tr>
      <tr>
        <td class="TDShade"><f:message key="quoteIssuedDate" />:</td>
        <td class="TDShadeBlue"><input name="textfield92" type="text"
          class="inputBox" size="20" disabled="true" /> <a href="#"
          onClick="displayCalendar(document.forms[0].quotedate,'${command.dateFormat}',this)">
        <img src=" images/calendar.gif" width="15" height="17"
          align="absmiddle" border="0" /></a> &nbsp;&nbsp;&nbsp;<strong><f:message
          key="quoteID" />:</strong></td>
        <td class="TDShade"><f:message key="jobFinishedDate" />:</td>
        <td colspan="2" class="TDShadeBlue"><form:input id="finishdate"
          cssClass="inputBox" path="jobOrder.jobFinishDate" onchange="selectJobDate('true','finishdate');" disabled="${command.viewOnly && !(reopenViewOnly && empty command.jobOrder.jobFinishDate)}"/> <form:errors
          path="jobOrder.jobFinishDate" cssClass="redstar" /> 
          <c:if test="${!command.viewOnly || (reopenViewOnly && empty command.jobOrder.jobFinishDate)}">
          <a href="#"
          onClick="javascript:displayCalendar(document.forms[0].finishdate,'${command.dateFormat}',this);">
        <img src=" images/calendar.gif" width="15" height="17"
          align="absmiddle" border="0" /></a>
          </c:if>
          </td>
      <c:choose>
    <c:when test="${command.showWarn=='true'&&command.jobOrder.jobFinishDate!=''}">
    <script type="text/javascript">
    showInvoiceWarn();
    </script>
    </c:when>
    </c:choose>
      </tr>
      <tr>
    <c:choose>
    <c:when test="${command.userTimeFormat != 'AM/PM'}">
        <td class="TDShade"><strong><f:message
          key="nominationDate" />:</strong></td>
        <td class="TDShadeBlue"><form:input id="nomdate"
          cssClass="inputBox" path="jobOrder.nominationDate" disabled="${command.viewOnly && !(reopenViewOnly && empty command.jobOrder.nominationDate)}"/> <form:errors
          path="jobOrder.nominationDate" cssClass="redstar" /> 
          <c:if test="${!command.viewOnly || (reopenViewOnly && empty command.jobOrder.nominationDate)}">
          <a href="#"
          onClick="displayCalendar(document.forms[0].nomdate,'${command.dateFormat}',this)">
        <img src=" images/calendar.gif" width="15" height="17"
          align="absmiddle" border="0" /></a>
          </c:if>
          &nbsp;&nbsp;&nbsp;<f:message
          key="time" />: <form:input size="4" cssClass="inputBox" id="nomTimes" 
          path="uiNominationTime" disabled="${command.viewOnly}"/> <form:errors
          path="uiNominationTime" cssClass="redstar" /> <form:input cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;"
          size="4" cssClass="inputBox" path="jobOrder.nominationTimeTz" disabled="${command.viewOnly  && !(reopenViewOnly && empty command.jobOrder.nominationTimeTz)}"/> <form:errors
          path="jobOrder.nominationTimeTz" cssClass="redstar" /> 
          <c:if test="${!command.viewOnly  || (reopenViewOnly && empty command.jobOrder.nominationTimeTz)}">
          <a href="#"
          onClick="javascript:popup_show('timezone','timezone_drag','timezone_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('timezone','timezonebody');popupboxenable();">
        <img src=" images/lookup.gif" alt="Lookup Time Zone" width="13" height="13" border="0" /></a>
      </c:if>
        <div id="debug"></div>
        </td>
    </c:when>
    <c:otherwise>
    
     <td class="TDShade"><strong><f:message
          key="nominationDate" />:</strong></td>
          <td class="TDShadeBlue"><form:input id="nomdate" size="11"
          cssClass="inputBox" path="jobOrder.nominationDate" disabled="${command.viewOnly && !(reopenViewOnly && empty command.jobOrder.nominationDate)}"/> <form:errors
          path="jobOrder.nominationDate" cssClass="redstar" /> 
          <c:if test="${!command.viewOnly || (reopenViewOnly && empty command.jobOrder.nominationDate)}">
          <a href="#"
           onClick="displayCalendar(document.forms[0].nomdate,'${command.dateFormat}',this)">
          <img src=" images/calendar.gif" width="15" height="17"
          align="absmiddle" border="0" /></a>
          </c:if>
          &nbsp;
      <f:message
          key="time"/>:&nbsp;<form:input size="5" id="nomTimes" cssClass="inputBox"
          path="uiNominationTime" disabled="${command.viewOnly}"/> <form:errors
          path="uiNominationTime" cssClass="redstar" />
       <form:select
              cssClass="selectionBox" id="sel14" path="nomTimeFormat"
              items="${icbfn:dropdown('staticDropdown',jobTimeFormat)}" itemLabel="name"
              itemValue="value" disabled="${command.viewOnly}"/> <form:errors path="nomTimeFormat"
              cssClass="redstar" />&nbsp;
      <form:input
          size="2" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" cssClass="inputBox" path="jobOrder.nominationTimeTz" disabled="${command.viewOnly  && !(reopenViewOnly && empty command.jobOrder.nominationTimeTz)}"/> <form:errors
          path="jobOrder.nominationTimeTz" cssClass="redstar" />
          <c:if test="${!command.viewOnly  || (reopenViewOnly && empty command.jobOrder.nominationTimeTz)}">
          <a href="#"
          onClick="javascript:popup_show('timezone','timezone_drag','timezone_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('timezone','timezonebody');popupboxenable();">
        <img src=" images/lookup.gif" alt="Lookup Time Zone" width="13" height="13" border="0" /></a>
      </c:if>
        <div id="debug"></div>
        </td>
    
     </c:otherwise>
    </c:choose>  
    <c:choose>
    <c:when test="${command.userTimeFormat != 'AM/PM'}">
        <td class="TDShade"><strong><f:message key="eta" />:</strong></td>
        <td colspan="2" class="TDShadeBlue"><form:input id="etadates"
          cssClass="inputBox" path="jobOrder.etaDate" onchange="selectJobDate('false','etadates');" disabled="${command.viewOnly && !(reopenViewOnly && empty command.jobOrder.etaDate)}"/> <form:errors
          path="jobOrder.etaDate" cssClass="redstar" /> <a href="#"
          onClick="displayCalendar(document.forms[0].etadates,'${command.dateFormat}',this)">
        <img src=" images/calendar.gif" width="15" height="17"
          align="absmiddle" border="0" /></a>&nbsp;&nbsp;&nbsp;<f:message
          key="time" />: <form:input size="4" cssClass="inputBox" id="etaTimes"
          path="uiEtaTime" disabled="${command.viewOnly}"/> <form:errors path="uiEtaTime"
          cssClass="redstar" />  <form:input size="4" cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;"
          path="jobOrder.etaTimeTz" disabled="${command.viewOnly && !(reopenViewOnly && empty command.jobOrder.etaTimeTz)}"/> <form:errors path="jobOrder.etaTimeTz"
          cssClass="redstar" /> 
          <!-- START: 118717 Fix for new requirement - 02 June 2009 -->
          <c:if test="${!command.viewOnly || (not empty command.jobOrder.jobFinishDate) || (reopenViewOnly && empty command.jobOrder.etaTimeTz)}">
          <!-- END: 118717 Fix for new requirement - 02 June 2009 -->
          <a href="#"
          onClick="javascript:popup_show('etatimezone','etatimezone_drag','etatimezone_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('etatimezone','etatimezonebody');popupboxenable();">
        <img src=" images/lookup.gif" alt="Lookup Tome Zone" width="13" height="13" border="0" /></a>
        </c:if>
        </td>
    </c:when>
    <c:otherwise>
    <td class="TDShade"><strong><f:message key="eta" />:</strong></td>
        <td colspan="2" class="TDShadeBlue"><form:input id="etadates" size="11"
          cssClass="inputBox" path="jobOrder.etaDate" onchange="selectJobDate('false','etadates');" disabled="${command.viewOnly && !(reopenViewOnly && empty command.jobOrder.etaDate)}"/> <form:errors
          path="jobOrder.etaDate" cssClass="redstar" />
          <!-- START: 118717,119283 Fix for new requirement - 04 June 2009 --> 
          <c:if test="${!command.viewOnly || (not empty command.jobOrder.jobFinishDate) || (reopenViewOnly && empty command.jobOrder.etaDate)}">
		  <!-- END: 118717,119283 Fix for new requirement - 04 June 2009 -->          
          <a href="#"
          onClick="displayCalendar(document.forms[0].etadates,'${command.dateFormat}',this)">
        <img src=" images/calendar.gif" width="15" height="17"
          align="absmiddle" border="0" /></a>
          </c:if>
          &nbsp;<f:message
          key="time" />:&nbsp;<form:input size="5" id="etaTimes" cssClass="inputBox"
          path="uiEtaTime" disabled="${command.viewOnly}"/> <form:errors path="uiEtaTime"
          cssClass="redstar" />
      <form:select
              cssClass="selectionBox" id="sel15" path="etaTimeFormat"
              items="${icbfn:dropdown('staticDropdown',jobTimeFormat)}" itemLabel="name"
              itemValue="value" disabled="${command.viewOnly}" /> <form:errors path="etaTimeFormat"
              cssClass="redstar" />&nbsp
      <form:input size="3" cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;"
          path="jobOrder.etaTimeTz" disabled="${command.viewOnly && !(reopenViewOnly && empty command.jobOrder.etaTimeTz)}"/> <form:errors
          path="jobOrder.etaTimeTz" cssClass="redstar" /> 
          <!-- START: 118717 Fix for new requirement - 02 June 2009 -->
          <c:if test="${!command.viewOnly || (not empty command.jobOrder.jobFinishDate) || (reopenViewOnly && empty command.jobOrder.etaTimeTz)}">
          <!-- END: 118717 Fix for new requirement - 02 June 2009 -->
          <a href="#"
          onClick="javascript:popup_show('etatimezone','etatimezone_drag','etatimezone_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('etatimezone','etatimezonebody');popupboxenable();">
        <img src=" images/lookup.gif" alt="Lookup Time Zone" width="13" height="13" border="0" /></a>
        </c:if>
        </td>
    </c:otherwise>
    </c:choose>  
      </tr>
      <tr>
        <td class="TDShade"><strong><f:message
          key="towingCompany" />:</strong></td>
        <td class="TDShadeBlue"><form:input id="tco" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" size="52"
          cssClass="inputBox" maxlength="128"
          path="jobOrder.towingCompany.name" disabled="${command.viewOnly && !(reopenViewOnly && (empty command.jobOrder.towingCompany || empty command.jobOrder.towingCompany.name))}"/> <form:errors
          path="jobOrder.towingCompany.name" cssClass="redstar" /> 
          <c:if test="${!command.viewOnly || (reopenViewOnly && (empty command.jobOrder.towingCompany || empty command.jobOrder.towingCompany.name))}">
          <a href="#"
          onClick="javascript:updateTowCoIframeSrc();setTowingCoFlag();popup_show('towingco',
         'towingco_drag','towingco_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('towingco','towingbody');popupboxenable();"><img
          src=" images/lookup.gif" alt="Lookup Towing Company" width="13" height="13" border="0" /></a>
          </c:if>
          </td>
        <td class="TDShade"><strong><f:message
          key="telephoneNo" /></strong></td>
        <td class="TDShadeBlue"><form:input id="tphone" 
          cssClass="inputBox" size="52" path="jobOrder.towingCompanyPhone" disabled="${command.viewOnly && !reopenViewOnly}"/> <form:errors
          path="jobOrder.towingCompanyPhone" cssClass="redstar" /></td>
      </tr>
      <tr>
        <td class="TDShade"><strong><f:message
          key="shippingAgent" />:</strong></td>
        <td class="TDShadeBlue"><form:input id="shipagnt"
          cssClass="inputBox" maxlength="128" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" size="52"
          path="jobOrder.shippingAgent.name" disabled="${command.viewOnly && !(reopenViewOnly && (empty command.jobOrder.shippingAgent || empty command.jobOrder.shippingAgent.name))}"/> <form:errors
          path="jobOrder.shippingAgent.name" cssClass="redstar" /> 
          <c:if test="${!command.viewOnly || (reopenViewOnly && (empty command.jobOrder.shippingAgent || empty command.jobOrder.shippingAgent.name))}">
          <a href="#"
          onClick="javascript:updateShipAgentIframeSrc();setShippingFlag();popup_show('shippingagent', 'shippingagent_drag', 'shippingagent_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('shippingagent','shippingagentbody');popupboxenable();">
        <img src=" images/lookup.gif" width="13" alt="Lookup Shipping Agent" height="13" border="0" /></a>
        </c:if>
        </td>
        <td class="TDShade"><strong><f:message
          key="telephoneNo" /></strong></td>
        <td class="TDShadeBlue"><form:input id="sphone"
          cssClass="inputBox" size="52" path="jobOrder.shippingAgentPhone" disabled="${command.viewOnly && !reopenViewOnly}"/> <form:errors
          path="jobOrder.shippingAgentPhone" cssClass="redstar" /></td>
      </tr>
      <tr>
                <td class="TDShade"><strong><f:message key="portLocation"/>:<span class="redstar">*</span></strong></td>
                <td class="TDShadeBlue">
         <form:input id="portLoc" cssClass="inputBox" onchange="javascript:changeVisibleStyle();" maxlength="30" size="52" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="jobOrder.serviceLocation.city" disabled="${command.viewOnly && !(reopenViewOnly && (empty command.jobOrder.serviceLocation || empty command.jobOrder.serviceLocation.city))}"/>
         <form:errors path="jobOrder.serviceLocation.city" cssClass="redstar"/> 
         <c:if test="${!command.viewOnly  || (reopenViewOnly && (empty command.jobOrder.serviceLocation || empty command.jobOrder.serviceLocation.city))}">
         <a
          href="#"  onClick="javascript:updatePortIframeSrc();setPortLcoationFlag();popup_show('port','port_drag','port_exit', 'screen-corner', 12, 5);hideIt();showPopupDiv('port','portbody');popupboxenable();">
        <img src="images/lookup.gif" id="lookup"  width="13" height="13" border="0" /></a>
        </c:if>
                  </td>
          <td class="TDShade"><strong><f:message
          key="serviceLocation" />:<span class="redstar">*</span></strong></td>
                <td colspan="2" class="TDShadeBlue"><form:input id="serviceLoc" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" size="52"
          cssClass="inputBox" maxlength="254" 
          path="jobOrder.serviceLocation.name" disabled="${command.viewOnly && !(reopenViewOnly && (empty command.jobOrder.serviceLocation || empty command.jobOrder.serviceLocation.name))}"/> <form:errors
          path="jobOrder.serviceLocation.name" cssClass="redstar" /> 
          <c:if test="${!command.viewOnly || (reopenViewOnly && (empty command.jobOrder.serviceLocation || empty command.jobOrder.serviceLocation.name))}">
          <a
          href="#" id="anker"   onClick="javascript:updateServiceLocationIframeSrc();setServiceLcoationFlag();popup_show('servloc','servloc_drag','servloc_exit', 'screen-corner', 12, 5);hideIt();showPopupDiv('servloc','servlocbody');popupboxenable();">
        <img src="images/lookup.gif" id="lookup" width="13" height="13" border="0" /></a>
        </c:if>
        </td>
      </tr>
      <tr>
        <td class="TDShade" style="border-bottom:#7c92be dashed 1px; "><strong><f:message
          key="vessel" />:</strong></td>
      <%-- <td class="TDShadeBlue" colspan="1" style="border-bottom:#7c92be dashed 1px;">${command.jobOrder.vesselNames}</td>--%>

 <td class="TDShadeBlue" colspan="1"  style="border-bottom:#7c92be dashed 1px;width:300;wrap:auto;text-wrap:hard-wrap;word-wrap:break-word;">${command.jobOrder.vesselNames}</td>
	  
	  
        <div style="visibility: hidden; display: none;"> <form:input id="vessel" size="45"
          cssClass="inputBox" maxlength="254"
          path="jobOrder.vesselNames"/></div>
        <td class="TDShade" style="border-bottom:#7c92be dashed 1px;"><strong><f:message
          key="product" />:</strong></td>
       <%-- <td colspan="2" class="TDShadeBlue"
          style="border-bottom:#7c92be dashed 1px;"> ${command.displayProductNames}</td>--%>

     <td colspan="2" class="TDShadeBlue"
         style="border-bottom:#7c92be dashed 1px;width:300;wrap:auto;text-wrap:hard-wrap;word-wrap:break-word;"> ${command.displayProductNames}</td>

          <div style="visibility: hidden; display: none;"> <form:input id="product" size="45"
          cssClass="inputBox" maxlength="254"
          path="displayProductNames"/></div>
      </tr>
      <tr>
        <td valign="top" class="TDShade"><strong>
        <c:choose>
        <c:when test="${command.viewOnly}">
        <f:message
          key="jobOrderDescription" />:
        </c:when>
        <c:otherwise>
        <a href="#" onClick="javascript:updateJobDescription('${command.dateFormat}','jobdescexist');"><f:message
          key="jobOrderDescription" />:</a>
        </c:otherwise>
        </c:choose>
        </strong></td>
        <td colspan="4" class="TDShadeBlue"><form:textarea id="jobDesc"
          path="jobOrder.jobDescription" rows="5" cols="100" disabled="${command.viewOnly}"/> <form:errors
          path="jobOrder.jobDescription" cssClass="redstar" /></td>

      </tr>
    </tbody>
  </table>

  <table width="100%" border="0" cellpadding="0" cellspacing="0"
    class="applTableBot">
    <tr>
      <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><strong><span class="redstar">*</span></strong> <span
            class="Font11pt"><f:message key="markedfiledsaremdtry" /></span></td>
		<%--	<c:choose>
            <c:when test="${command.originateFromSearchJob==null}">
            <td>&nbsp;</td>
            </c:when>
            <c:otherwise>
              <td><a href="${command.originateFromSearchJob}?jobNum=${command.jobNumber}"><f:message key="returnToSearch" /></a></td>
         </c:otherwise>
            </c:choose>
        <c:choose>
          <c:when test="${command.nextListFlag=='true'}">
              <td><a href="#" onClick="javascript:nextList();"><f:message key="nextInList"/></a></td>
         </c:when>
            <c:otherwise>
      <td>&nbsp;</td>
       </c:otherwise>
            </c:choose>
       <c:choose>
        <c:when test="${command.prevListFlag=='true'}">
              <td><a href="#" onClick="javascript:prevList();"><f:message key="previousInList"/></a></td>
             </c:when>
            <c:otherwise>
      <td>&nbsp;</td>
       </c:otherwise>
       </c:choose> --%>
	   
          <td style="text-align:right">
		  <!-- START : #119240 -->
	    <c:choose>
            <c:when test="${command.originateFromSearchJob==null}">
				&nbsp;
            </c:when>
            <c:otherwise>
              <a href="${command.originateFromSearchJob}?jobNum=${command.jobNumber}">
			   <img src="images/icofindjob.gif" alt="Return to Search" width="16" height="14" border="0" align="absmiddle">			  
			  </a>
			</c:otherwise>
           </c:choose>        
		  <c:choose>
			<c:when test="${command.prevListFlag=='true'}">
              <a href="#" onClick="javascript:prevList();">
			  <img src="images/prevleftarrow.gif" alt="Go to Previous Job" width="13" height="12" hspace="2" border="0"/>
			  </a>
             </c:when>
            <c:otherwise>
		      <c:if test="${command.originateFromSearchJob!=null}">
				  <a href="#" onClick="javascript:noPrevList();">
				  <img src="images/prevleftarrow.gif" alt="Go to Previous Job" width="13" height="12" hspace="2" border="0"/>
				  </a>
				</c:if>	
	        </c:otherwise>
        </c:choose>   
	    <c:choose>
          <c:when test="${command.nextListFlag=='true'}">
              <a href="#" onClick="javascript:nextList();">
			  <img src="images/nextrtarrow.gif" alt="Go to Next Job" width="13" height="12" hspace="2" border="0"/></a>
          </c:when>
          <c:otherwise>
			<c:if test="${command.originateFromSearchJob!=null}">
			 <a href="#" onClick="javascript:noNextList();">
			  <img src="images/nextrtarrow.gif" alt="Go to Next Job" width="13" height="12" hspace="2" border="0"/></a>
			</c:if>	
          </c:otherwise>
        </c:choose>
	<!-- END : #119240 -->
		  <a href="#" onClick="goToNextPage('${command.custCount}','${command.dateFormat}','${command.userTimeFormat}')"><img
            src=" images/savennextbluarrow.gif" alt="Save and Go to Next Page"
            width="14" height="14" hspace="4" border="0" align="absmiddle"
            title="Save and Go to Next Page"></a><a href="#"
            onclick="javascript:updateJobDescription('${command.dateFormat}');checkcount('${command.custCount}','${command.dateFormat}','${command.userTimeFormat}');
        <%--checkDate('${command.dateFormat}','${command.userTimeFormat}');--%>"><img src=" images/icosave.gif"
            alt="Save" title="Save" width="14" height="14" border="0"
            align="absmiddle" /></a></td>
        </tr>
      </table>
      </td>
    </tr>
  </table>
  </div>

  <!-- ---------------------- TAB 1 CONTAINER END ---------------------------- -->
  <ajax:autocomplete 
          baseUrl="towingco.htm"
          source="jobOrder.towingCompany.name"
          target="jobOrder.towingCompanyPhone" className="autocomplete"
          parameters="towingCompany.name={jobOrder.towingCompany.name}"
          postFunction="resetTowingCo"
          minimumCharacters="3" />
  <ajax:autocomplete
          baseUrl="shipping_agent.htm" source="jobOrder.shippingAgent.name"
          target="jobOrder.shippingAgentPhone" className="autocomplete"
          parameters="shippingAgent.name={jobOrder.shippingAgent.name}"
          postFunction="resetShipAgent"
          minimumCharacters="3" /> 
<ajax:autocomplete
  baseUrl="servicelocation.htm" source="jobOrder.serviceLocation.name"
  target="jobOrder.serviceLocation.city" className="autocomplete"
  parameters="serviceLocation.serviceLocationName={jobOrder.serviceLocation.name},serviceLocation.serviceLocationCity={jobOrder.serviceLocation.city}"
  minimumCharacters="3" />

  <ajax:autocomplete
          baseUrl="servicelocation.htm" source="jobOrder.serviceLocation.city"
          target=" jobOrder.serviceLocation.name" className="autocomplete"
          parameters="serviceLocation.serviceLocationCity={jobOrder.serviceLocation.city}"
          minimumCharacters="3"/>
  <ajax:autocomplete
          baseUrl="customer.htm" source="jobOrder.receivedByUserName"
          target="jobOrder.receivedByUserName" className="autocomplete"
          parameters="accountOwner={jobOrder.receivedByUserName}"
          minimumCharacters="3" /> 
  <ajax:autocomplete
          baseUrl="joborder.htm" source="jobOrder.nominationTimeTz"
          target="jobOrder.nominationTimeTz" className="autocomplete"
          parameters="nominationTimeZone={jobOrder.nominationTimeTz}"
          minimumCharacters="3" /> 
  <ajax:autocomplete
          baseUrl="joborder.htm" source="jobOrder.etaTimeTz"
          target="jobOrder.etaTimeTz" className="autocomplete"
          parameters="etaTimeZone={jobOrder.etaTimeTz}" minimumCharacters="1" />
  <ajax:autocomplete
          baseUrl="branch.htm"
          source="jobOrder.branchName"
          target="jobOrder.branchName"
          className="autocomplete"              
          parameters="branchName={jobOrder.branchName},buName={jobOrder.buName}"
           minimumCharacters="3"
       postFunction="selectJobDateBoolean"
       />
<!-- ---------------------- TAB 1 CONTAINER END ---------------------------- -->
<!-- ------------------------- TAB 2 CONTAINER ------------------------------- -->
<div id="tab2" class="innercontent" >
<table class=mainApplTable cellspacing=0 cellpadding=0 width="100%">
<tbody>
<tr bgcolor=#ffffff>
<th><f:message key="contractEntry"/>

 <img src=" images/separator2.gif" width="2" height="27"
          align="absmiddle" style="margin-left:5px;margin-right:5px;" />
      <f:message key="jobId" />: ${command.jobOrder.jobNumber}<img
          src=" images/separator2.gif" width="2" height="27"
          align="absmiddle" style="margin-left:5px;margin-right:5px;" /><f:message
          key="jobTypeOCAMNInspectionTesting" />
      &nbsp;&nbsp;&nbsp;
<f:message key="status" />: <form:select id="sel1"
              cssClass="selectionBox" path="jobOrder.jobStatus"
              items="${icbfn:dropdown('staticDropdown',jobStatus)}" itemLabel="name"
              itemValue="value" disabled="true"/> <form:errors path="jobOrder.jobStatus"
              cssClass="redstar" /> &nbsp;&nbsp;           
          </th>

<th style="text-align:right"><a href="#" onClick="goToNextPage('${command.custCount}','${command.dateFormat}','${command.userTimeFormat}')"><img src=" images/savennextbluarrow.gif" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page"></a><a href="#"><img src=" images/icosave.gif" alt="Save" title="Save" width="14" height="14" border="0" align="absmiddle"  
onclick="javascript:updateJobDescription('${command.dateFormat}');setTabName('1');checkcount('${command.custCount}','${command.dateFormat}','${command.userTimeFormat}'); <%--checkDate('${command.dateFormat}','${command.userTimeFormat}');--%>"/></a></th>

</tr>

<tr>
<td colspan="4" class="TDShade" style="font-weight:normal;"><f:message key="contractIDDescription"/></td>
</tr>

<tr>
<td colspan="4" class="TDShade"><strong><f:message key="addCustomer"/>:</strong>
<form:input  id="ccode" cssClass="inputBox" path="contractCode.value" size="50" maxlength="100"/>
<form:errors path="contractCode.value" cssClass="redstar"/> 
<label>
<input name="Add" type="button" class="button1" value="Add"  onClick="setform();"/>
</label>
</td>
</tr>
<c:choose>
<c:when test="${command.popFlag=='none'}">
<script type="text/javascript">
setpage();
</script>
</c:when>
</c:choose>
<c:choose>
<c:when test="${command.errorFlag=='true'}">
<script type="text/javascript">
showError("${command.errorCode}","${command.searchCode}");
</script>
</c:when>
</c:choose>
</tbody>
</table>

<!------------------------------------------------Job Contract Lookup ----------------------------------------------------------------->
<div class="sample_popup"     id="popup2" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="popup_drag2" style="width:900px;">
<a href="#"  onclick="resetJob('${command.uniqueCount}','${command.contrCode}')"><img class="menu_form_exit"   id="popup_exit2" src=" images/form_exit.png" /></a> &nbsp;&nbsp;&nbsp;<f:message key="contractSearchResults"/></div>
<div class="menu_form_body"  id="popup2body "   style="height:680px; width:900px;overflow-y:hidden;">
<table cellspacing="0" cellpadding="0" align="center" style="width:95%;" >
<tr>
<iframe id="pu" align="left" frameborder="0" style="padding:0px;" height="670px;" width="100%"  scrolling="auto" id="searchjobContractFr" src="blank.html" name="searchjobContractFr" allowtransparency="yes" ></iframe>
</tr>
</table>
</div>
</div>
<!----------------------------------------------- Job Contract Lookup END ------------------------------------------------------------->
<c:choose>
<c:when test="${command.popFlag=='true'}">
<script type="text/javascript">
setflag("${command.searchCode}",'${command.pageNumber}');
popup_show('popup2', 'popup_drag2', 'popup_exit2', 'screen-corner', 120, 20);
hideIt();
popupboxenable();
function setflag(scode,pageNumber)
{  
document.editJobsInspForm.jobFlag.value="jobval";
document.editJobsInspForm.tabName.value = "1";
var buname=document.getElementById("sel3").value;
var code= scode;
var pageN0=pageNumber;
if(scode.charAt("/"))
{ code=scode.replace("/","'");}
if(code!= "" && code!= null)
{
//START: To fix issue 110817
code = escape(code);
//END: To fix issue 110817
document.getElementById("pu").setAttribute("src","search_job_contract_insp_popup.htm?inputFieldId=inputFieldIdValue&searchForm=editJobsInspForm&code="+code+"&pageNumber="+pageN0+"&buName="+buname+"&fromPage=5");
}
}
</script>
</c:when>
</c:choose>

<c:choose>
<c:when test="${command.contrFlag=='true' && command.uniqueCount>1}">
<script type="text/javascript">
setCode("${command.contrCode}");
popup_show('popup2', 'popup_drag2', 'popup_exit2', 'screen-corner', 120, 20);
hideIt();
popupboxenable();
function setCode(scode)
{  
var code=scode;
if(scode.charAt("/"))
{ code=scode.replace("/","'");}
document.editJobsInspForm.ccode.value=code;
document.editJobsInspForm.uniqueFlag.value="uniqueFlag";
document.editJobsInspForm.submit();
}
</script>
</c:when>
</c:choose>
<!--------------------------------------------- Contract Details Container ------------------------------------------------------------>
<div id="contractdetails"><br>
<table width="100%" cellpadding="0" cellspacing="0" cols="1" dir="ltr" class="InnerApplTable" id="RO_LINEDISP_WRK$scroll$0">
<tr>
<td colspan="2" style="padding-bottom:6px;padding-left:1px;">
<!-- Contract 1 -->

<c:forEach items="${command.addJobContracts}" var="addJobContracts" varStatus="counter">
<form:hidden path="addJobContracts[${counter.index}].displayStatus" />
<div id="contract${counter.index}">

<table border="0" cellpadding="0" cellspacing="0" cols="11" class="secAppltable" style="margin-bottom:0px;" >
<tr valign="center">
<th width="16" rowspan="2" align="center" valign="top" scope="col" style="background-image:url( images/arrowblubg.gif);"> 

<div id="bluarrowright${counter.index+1}" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> 

<a href="#" onClick="javascript:showOriginTable('origintable${counter.index+1}','bluarrowdown${counter.index+1}','bluarrowright${counter.index+1}',${counter.index});"> 

<img src=" images/bluarrowright.gif" width="8" height="16" border="0" vspace="6"/></a> </div>

<div id="bluarrowdown${counter.index+1}" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hideOriginTable('origintable${counter.index+1}','bluarrowright${counter.index+1}','bluarrowdown${counter.index+1}',${counter.index});"> 

<img src=" images/bluarrowdown.gif" width="16" height="8" border="0" vspace="6"/></a></div></th>

<th scope="col" align="left">&nbsp;</th>
<th width="30" scope="col" align="left"><f:message key="nom"/></th>
<th width="150" scope="col"  align="left"><f:message key="customerName"/></th>
<th width="100" scope="col" align="left"><f:message key="contact"/></th>
<th width="100" scope="col" align="left"><f:message key="contractID"/></th>
<th width="150" scope="col" align="left"><f:message key="contractDescription"/></th>
<th align="left" width="5%"><f:message key="status"/></th>
<th width="20" align="left" scope="col">&nbsp;</th>
<th width="20" align="left" scope="col">&nbsp;</th>
<th width="20" align="left" scope="col">&nbsp;</th>
</tr>

<tr valign="center">
<td align="center" width="18">${counter.index+1}</td>

<c:choose>
<c:when test="${counter.index==0}">
<td nowrap="nowrap" align="center"><form:checkbox id="nom"  path="addJobContracts[${counter.index}].jobContract.nominationFlag"  value="Y" disabled="${command.addJobContracts[counter.index].contractViewOnly}"/></td>
<script type="text/javascript">
setNomCheck('${counter.index}');
function setNomCheck(index)
{
  document.getElementById("nom").checked = true;
}
</script>
</c:when>
<c:otherwise>
<td nowrap="nowrap" align="center"><form:checkbox id="nom1"  path="addJobContracts[${counter.index}].jobContract.nominationFlag"  value="Y" disabled="${command.addJobContracts[counter.index].contractViewOnly}"/></td>
</c:otherwise>
</c:choose>

<td width="150" align="left">${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.customer.name}</td>


<td align="left"><a href="#" style="cursor:hand;" onMouseOver="doTooltip(event, '<table><tr><td width=120px><b>Scheduler ID:</b></td><td>${command.addJobContracts[counter.index].jobContract.contractCustContact.contact.id} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Company ID:</b>${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.customer.custCode}</td></tr><tr><td valign=top><b>Scheduler Address:</b></td> <td>${command.addJobContracts[counter.index].schedulerAddress}</td></tr><tr><td><b>Telephone:</b></td>  <td>${command.addJobContracts[counter.index].jobContract.contractCustContact.contact.workPhone}</td></tr><tr><td><b>Email Address:</b></td><td>${command.addJobContracts[counter.index].jobContract.contractCustContact.contact.workEmail}</td></tr></table>')" onMouseOut="hideTip()">${command.addJobContracts[counter.index].jobContract.contractCustContact.contact.firstName} ${command.addJobContracts[counter.index].jobContract.contractCustContact.contact.lastName}</a></td>

<td align="left">
<a href="#" onclick="contractAttach('${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode}','${counter.index}');popup_show('contractattach${counter.index}', 'contractattach_drag${counter.index}', 'contractattach_exit${counter.index}', 'screen-corner', 80, 35);hideIt();popupboxenable();">${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode}</a>
</td>

<td align="left">${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.description}</td>
<td align="left">
<icb:list var="jobContractStatus">
  <icb:item>jobContractStatus</icb:item>
  <icb:item>${command.addJobContracts[counter.index].jobContract.jobContractStatus}</icb:item>
</icb:list>
<c:choose>
<c:when test="${command.jobOrder.jobStatus=='7000' && command.addJobContracts[counter.index].jobContract.jobContractStatus=='6000'}">       
<form:select id="jobContractStatus${counter.index}" cssClass="selectionBox" path="addJobContracts[${counter.index}].jobContractStatus" items="${icbfn:dropdown('staticDropdown',jobContractStatus)}"   itemLabel="name" itemValue="value" onchange="checkForRebill('jobContractStatus${counter.index}','${counter.index}','${command.addJobContracts[counter.index].jobContract.contractCode}','${command.addJobContracts[counter.index].jobContract.id}')"/> 
</c:when>
<c:otherwise>
<form:select id="sel3" cssClass="selectionBox" path="addJobContracts[${counter.index}].jobContractStatus" items="${icbfn:dropdown('staticDropdown',jobContractStatus)}"  itemLabel="name" itemValue="value" disabled="true"/> 
</c:otherwise>
</c:choose>
<form:errors path="addJobContracts[${counter.index}].jobContract.jobContractStatus" cssClass="redstar" />
</td>

<td align="center"><a href="#" onClick="javascript:if(${command.addJobContracts[counter.index].jobContract.id}>0){popup_show('addnote${counter.index}', 'addnote_drag${counter.index}', 'addnote_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable();} else {confirm('Please save first');}"><img src=" images/icoaddnote.gif" alt="Add a note" width="18" height="15" border="0" /></a></td>

<td align="center">&nbsp;
<authz:authorize ifAnyGranted="FileUpload,FileUploadNoDel">
<a href="#" onClick="javascript:if(${command.addJobContracts[counter.index].jobContract.id}>0){setAttachFilesFlag(${counter.index });popup_show('attach${counter.index}', 'attach_drag${counter.index}', 'attach_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable();} else {confirm('Please save first');}"><img src=" images/icoattach.gif" alt="Add an attachment" width="13" height="16" border="0" /></a>
</authz:authorize>
</td>

<td align="center"><%--<a href="#" onClick="javascript:delContract('contract${counter.index}');">--%>
<c:if test="${command.addJobContracts[counter.index].jobContractStatus != null && command.addJobContracts[counter.index].jobContractStatus eq '1000'}">
<a href="#" onClick="javascript:setDeleteflag('${counter.index}');">
<img src="images/icodel.gif" alt="Delete row" width="12" height="14" border="0" /></a>
</c:if>
</td>

</tr>
</table>
</div>



<!------------------------------------------------- Job Contract Add Note  Lookup ----------------------------------------------------->
<div class="sample_popup"     id="addnote${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="addnote_drag${counter.index}" style="width:640px; ">
<img class="menu_form_exit"   id="addnote_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="addNotes"/></div>
<div class="menu_form_body" style="width:640px; height:290px;">
<table cellspacing="0" cellpadding="0" align="center" style="width:95%;" ><tr>
<iframe align="left" frameborder="0" style="padding:0px;" height="290px;" width="100%" src="create_job_add_customer_note_popup.htm?jobContractId=${command.addJobContracts[counter.index].jobContract.id}&divName=note${counter.index}" scrolling="auto" id="jobContractAttachFileFr" name="jobContractAttachFileFr" allowtransparency="yes" ></iframe></tr></table></div></div>
<!----------------------------------------------- Job Contract Add Note  Lookup End---------------------------------------------------->
<!------------------------------------------------------------ Attach File Lookup  ---------------------------------------------------->
<div class="sample_popup" id="attach${counter.index}" style="visibility:hidden;display:none;">
<div class="menu_form_header" id="attach_drag${counter.index}" style="width:700px; "> 
<img class="menu_form_exit" id="attach_exit${counter.index}" src="images/form_exit.png"/>&nbsp;&nbsp;&nbsp;<f:message key="attachFiles"/></div>
<div class="menu_form_body" style="width:700px; height:300px;">
<table cellspacing="0" cellpadding="0" align="center" style="width:95%;" ><tr>
<iframe align="left" frameborder="0" style="padding:0px;" height="300px;" width="100%" src="create_job_add_customer_attachfile_popup.htm?contractCode=${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode}&jobContractId=${command.addJobContracts[counter.index].jobContract.id}&jobNumber=${command.addJobContracts[counter.index].jobContract.jobNumber}&divName=attach${counter.index}&inputFieldId=attachedFileNames" scrolling="auto" id="jobContractAttachFileFr" name="jobContractAttachFileFr" allowtransparency="yes" ></iframe></tr></table></div></div>
<!--------------------------------------------------------- Attach File Lookup End----------------------------------------------------->

<icb:list var="contracts">
<icb:item>${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode}</icb:item>
<icb:item>${command.jobOrder.jobFinishDate}</icb:item>
<icb:item>${command.jobOrder.etaDate}</icb:item> 
<icb:item>${command.addJobContracts[counter.index].priceBookId}</icb:item>

</icb:list>
                      
<icb:list var="currencies">
<icb:item>${command.addJobContracts[counter.index].cfgContract.currencyCD}</icb:item>
<icb:item><f:formatDate value="${command.jobOrder.jobFinishDate}" type="date" dateStyle="short" /></icb:item>
</icb:list>

<div id="origintable${counter.index+1}" style="padding:0px; visibility:hidden; display:none;">
<table border="0" cellpadding="0" cellspacing="0" cols="11" class="secAppltable" style="margin-top:0px; border-top:none" >

<tr valign="center">
<td width="20"><img src=" images/spacer.gif" width="18" height="1" /></td> 
<%-- <td width="18" style="border-right:none;"><img src=" images/spacer.gif" width="15" height="1" /></td>
<td width="38"><img src=" images/spacer.gif" width="31" height="1" /></td>  --%>
<td colspan="8" style="padding:0px;">
<table border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="width:98%; margin:1px; border:none">

<tr>
<c:choose>
<c:when test="${command.jobOrder.jobType=='INSP'}">
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:when test="${command.jobOrder.jobType=='MAR'}">
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:otherwise>
<td class="TDShadeGrey"><strong><f:message key="selectProject"/>:</strong></td>
<td>
<c:choose>
<c:when test="${command.addJobContracts[counter.index].jobContract.jobContractStatus=='7100'}">
<form:input cssClass="inputBox" size="43" maxlength="128" path="addJobContracts[${counter.index}].jobContract.projectNumber" disabled="true"/>
</c:when>
<c:otherwise>
<form:input cssClass="inputBox" size="43" maxlength="128" path="addJobContracts[${counter.index}].jobContract.projectNumber" disabled="${command.addJobContracts[counter.index].contractViewOnly}"/>
</c:otherwise>
</c:choose>
 <form:errors path="addJobContracts[${counter.index}].jobContract.projectNumber" cssClass="redstar"/>
 <c:if test="${!command.addJobContracts[counter.index].contractViewOnly && command.addJobContracts[counter.index].jobContract.jobContractStatus!='7100'}">
  <a href="#" onClick="javascript:popup_show('searchprojects${counter.index}','searchprojects_drag${counter.index}','searchprojects_exit${counter.index}', 'screen-corner', 120, 20);showPopupDiv('searchprojects${counter.index}','searchprojectsbody${counter.index}');hideIt();popupboxenable();"><img src="images/lookup.gif" width="13" alt="Lookup Project" height="13" border="0"/></a>
  </c:if>
  </td>
</c:otherwise>
</c:choose>


<td id="mjobid${counter.index}" class="TDShadeGrey"><f:message key="monthlyJobId"/>:</td>
<td> 

<form:input id="monjobnumber${counter.index}" cssClass="inputBox" size="30" maxlength="128" path="addJobContracts[${counter.index}].jobContract.monthlyJobNumber" disabled="${command.addJobContracts[counter.index].contractViewOnly}"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.monthlyJobNumber" cssClass="redstar"/>
<a href="#" id="anchor${counter.index}" onClick="javascript:updateMonthlyJobIdIframeSrc('${counter.index}','${command.jobOrder.buName}','${command.jobOrder.branchName}','${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode}','${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.customer.parentCustCode}');popup_show('monthlyJob${counter.index}', 'monthlyJob_drag${counter.index}', 'monthlyJob_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable();showPopupDiv('monthlyJob${counter.index}','monthlyJobbody${counter.index}');"><img src=" images/lookup.gif" width="13" alt="Lookup Account" height="13" border="0"/></a>

<form:checkbox id="monFlag${counter.index}"   path="addJobContracts[${counter.index}].jobContract.monthlyFlag" value="Y"  onclick="monjobnumberFlag('${counter.index}','${command.jobOrder.jobNumber}')" disabled="${command.addJobContracts[counter.index].contractViewOnly}"/></td></tr>


<c:choose>
<c:when test="${command.jobOrder.pageNumber >=3}">
<script type="text/javascript">
disablemonth('${counter.index}')
function disablemonth(index)
{  
  document.getElementById("mjobid"+index).className="unprotected";
  document.getElementById("monjobnumber"+index).contentEditable="false";
  document.getElementById("anchor"+index).style.visibility="hidden";  
}
</script>
</c:when>
</c:choose>



<script type="text/javascript">
check('${counter.index}');
function check(index)
{
  if(document.getElementById("monFlag"+index).checked)
  {
    document.getElementById("mjobid"+index).className="unprotected";
  document.getElementById("monjobnumber"+index).contentEditable="false";
  document.getElementById("anchor"+index).style.visibility="hidden";  
  }
}
</script>


<c:choose>
<c:when test="${command.monthlyCheck=='true'&& command.rowNum==counter.index}">
<script type="text/javascript">
monthlyJobcheck('${command.rowNum}','${command.jobOrder.jobNumber}');
function monthlyJobcheck(index,jobnumber)
{
 if(confirm("You cannot deselect this row as a monthly header since jobs are attached.")==true)
  {      
        document.getElementById("monFlag"+index).checked=true;
        document.getElementById("mjobid"+index).className="unprotected";
        document.getElementById("monjobnumber"+index).value =jobnumber ;  
        document.getElementById("monjobnumber"+index).contentEditable="false";
    document.getElementById("anchor"+index).style.visibility="hidden";
      }
         else
        {
        document.getElementById("monFlag"+index).checked=true;
      document.getElementById("mjobid"+index).className="unprotected";
        document.getElementById("monjobnumber"+index).value =jobnumber ;  
        document.getElementById("monjobnumber"+index).contentEditable="false";
    document.getElementById("anchor"+index).style.visibility="hidden";
        }
    document.editJobsInspForm.monthlyCheck.value="false";
}
</script>
</c:when>
</c:choose>


<tr>
<td width="18%" class="TDShadeGrey"><f:message key="origin"/><span class="redstar">*</span></td>
<td width="26%"><form:select id="sel1" cssClass="selectionBoxbig" path="addJobContracts[${counter.index}].jobContract.custSentBy" items="${icbfn:dropdown('staticDropdown',origin)}" itemLabel="name" itemValue="value" disabled="${command.addJobContracts[counter.index].contractViewOnly && !(reopenViewOnly && empty fn:trim(command.addJobContracts[counter.index].jobContract.custSentBy))}"/><form:errors path="addJobContracts[${counter.index}].jobContract.custSentBy" cssClass="redstar"/></td>
<td width="12%" class="TDShadeGrey"><f:message key="custRefNum"/><span class="redstar">*</span></td>
<td width="19%"><form:input cssClass="inputBox" size="38" maxlength="40" path="addJobContracts[${counter.index}].jobContract.custRefNum" disabled="${command.addJobContracts[counter.index].contractViewOnly && !(reopenViewOnly && empty command.addJobContracts[counter.index].jobContract.custRefNum)}"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.custRefNum" cssClass="redstar"/></td>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="selectedLanguage"/></td>
<td><form:select id="sel6" cssClass="selectionBoxbig" path="addJobContracts[${counter.index}].jobContract.language" items="${icbfn:dropdown('staticDropdown',selectedLanguage)}" itemLabel="name" itemValue="value" disabled="${command.addJobContracts[counter.index].contractViewOnly}"/></td>

<c:choose>
<c:when test="${command.addJobContracts[counter.index].jobContract.invoiceLabel1==null}">
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:otherwise>
<td class="TDShadeGrey">${command.addJobContracts[counter.index].jobContract.invoiceLabel1}</td>
<td><form:input cssClass="inputBox" size="38" maxlength="64" path="addJobContracts[${counter.index}].jobContract.invoiceValue1" disabled="${command.addJobContracts[counter.index].contractViewOnly}"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.invoiceValue1" cssClass="redstar"/></td>
</c:otherwise>
</c:choose>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="contractCurrency"/></td>
<td> ${command.addJobContracts[counter.index].cfgContract.currencyCD}</td>

<c:choose>
<c:when test="${command.addJobContracts[counter.index].jobContract.invoiceLabel2==null}">
<td  class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:otherwise>
<td  class="TDShadeGrey" >${command.addJobContracts[counter.index].jobContract.invoiceLabel2}</td>
<td><form:input cssClass="inputBox" size="38" maxlength="64" path="addJobContracts[${counter.index}].jobContract.invoiceValue2" disabled="${command.addJobContracts[counter.index].contractViewOnly}"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.invoiceValue2" cssClass="redstar"/></td>
</c:otherwise>
</c:choose>
</tr>

<tr>
<td nowrap class="TDShadeGrey"><f:message key="transactionCurrencyMultiplier"/></td>
<td>
<!--<form:select id="sel3" cssClass="selectionBoxbig" path="addJobContracts[${counter.index}].jobContract.transactionCurrencyCd" items="${icbfn:dropdown('currencyTrans', currencies)}" itemLabel="name" itemValue="value" onchange="setbankFlag(${counter.index});" disabled="${command.addJobContracts[counter.index].contractViewOnly}"/>-->
	<c:if test="${empty disabledTrans}">
		<form:select id="sel3" cssClass="selectionBox" path="addJobContracts[${counter.index}].jobContract.transactionCurrencyCd" items="${icbfn:dropdown('currencyTrans', currencies)}" itemLabel="name" itemValue="value" onchange="setbankFlag(${counter.index});" disabled="${command.addJobContracts[counter.index].contractViewOnly && !(reopenViewOnly && empty command.addJobContracts[counter.index].jobContract.transactionCurrencyCd)}"/>
	</c:if>
	<c:set var="flag" value="false"/>
	<c:forEach items="${disabledTrans}" var="map">
		<c:choose>
			<c:when test="${map.value eq true && map.key eq command.addJobContracts[counter.index].jobContract.id}">
				<form:select id="sel3" cssClass="selectionBox" path="addJobContracts[${counter.index}].jobContract.transactionCurrencyCd" items="${icbfn:dropdown('currencyTrans', currencies)}" itemLabel="name" itemValue="value" onchange="setbankFlag(${counter.index});" disabled="${command.addJobContracts[counter.index].contractViewOnly && !(reopenViewOnly && empty command.addJobContracts[counter.index].jobContract.transactionCurrencyCd)|| true}"/>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${flag eq false && map.value eq false && map.key == command.addJobContracts[counter.index].jobContract.id}">
						<c:set var="flag" value="true"/>
						<form:select id="sel3" cssClass="selectionBox" path="addJobContracts[${counter.index}].jobContract.transactionCurrencyCd" items="${icbfn:dropdown('currencyTrans', currencies)}" itemLabel="name" itemValue="value" onchange="setbankFlag(${counter.index});" disabled="${command.addJobContracts[counter.index].contractViewOnly && !(reopenViewOnly && empty command.addJobContracts[counter.index].jobContract.transactionCurrencyCd)}"/>
					</c:when>
					<c:otherwise>
						<c:if test="${flag eq false && (map.value eq false || empty map.value) && (map.key eq 0 || empty map.key) && command.addJobContracts[counter.index].jobContract.id eq 0}">
							<form:select id="sel3" cssClass="selectionBox" path="addJobContracts[${counter.index}].jobContract.transactionCurrencyCd" items="${icbfn:dropdown('currencyTrans', currencies)}" itemLabel="name" itemValue="value" onchange="setbankFlag(${counter.index});" disabled="${command.addJobContracts[counter.index].contractViewOnly && !(reopenViewOnly && empty command.addJobContracts[counter.index].jobContract.transactionCurrencyCd)}"/>
							<c:set var="flag" value="true"/>
						</c:if>	
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</c:forEach>

<c:if test="${command.addJobContracts[counter.index].cfgContract.overridable}">
	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<f:message key="overrideRate"/>
	 <form:input cssClass="inputBox" size="5" maxlength="15" path="addJobContracts[${counter.index}].jobContract.overrideCurrRate" disabled="${command.addJobContracts[counter.index].contractViewOnly}"/>
</c:if>
</td>

<c:choose>
<c:when test="${command.addJobContracts[counter.index].jobContract.invoiceLabel3==null}">
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:otherwise>
<td class="TDShadeGrey">${command.addJobContracts[counter.index].jobContract.invoiceLabel3}</td>
<td><form:input cssClass="inputBox" size="38" maxlength="64" path="addJobContracts[${counter.index}].jobContract.invoiceValue3" disabled="${command.addJobContracts[counter.index].contractViewOnly}"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.invoiceValue3" cssClass="redstar"/></td>
</c:otherwise>
</c:choose>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="productType"/></td>
<td>
<c:choose>
<c:when test="${command.addJobContracts[counter.index].jobContract.jobContractStatus=='7100'}">
<form:select id="sel4" cssClass="selectionBoxbig" path="addJobContracts[${counter.index}].jobContract.productType" items="${icbfn:dropdown('staticDropdown', productType)}" itemLabel="name" itemValue="value" />
</c:when>
<c:otherwise>
<form:select id="sel4" cssClass="selectionBoxbig" path="addJobContracts[${counter.index}].jobContract.productType" items="${icbfn:dropdown('staticDropdown', productType)}" itemLabel="name" itemValue="value" disabled="${command.addJobContracts[counter.index].contractViewOnly}"/>
</c:otherwise>
</c:choose>
</td>
<c:choose>
<c:when test="${command.addJobContracts[counter.index].jobContract.invoiceLabel4==null}">
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:otherwise>
<td class="TDShadeGrey">${command.addJobContracts[counter.index].jobContract.invoiceLabel4}</td>
<td><form:input cssClass="inputBox" size="38" maxlength="64" path="addJobContracts[${counter.index}].jobContract.invoiceValue4" disabled="${command.addJobContracts[counter.index].contractViewOnly}"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.invoiceValue4" cssClass="redstar"/></td>
</c:otherwise>
</c:choose>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="zoneDescription"/></td>
<td>

<form:select id="sel10" cssClass="selectionBoxbig" path="addJobContracts[${counter.index}].jobContract.locationDesc" items="${icbfn:dropdown('portCode', contracts)}" itemLabel="name" itemValue="value" disabled="${command.addJobContracts[counter.index].contractViewOnly && !(reopenViewOnly && empty command.addJobContracts[counter.index].jobContract.locationDesc)}"/>

</td> 

<c:choose>
<c:when test="${command.addJobContracts[counter.index].jobContract.invoiceLabel5==null}">
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:otherwise>
<td class="TDShadeGrey">${command.addJobContracts[counter.index].jobContract.invoiceLabel5}</td>
<td><form:input cssClass="inputBox" size="38" maxlength="64" path="addJobContracts[${counter.index}].jobContract.invoiceValue5" disabled="${command.addJobContracts[counter.index].contractViewOnly}"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.invoiceValue4" cssClass="redstar"/></td>
</c:otherwise>
</c:choose>
</tr>

<icb:list var="pcodes">
  <icb:item>${command.addJobContracts[counter.index].jobContract.locationDesc}</icb:item>
  <icb:item>${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode}</icb:item>
  <icb:item>${command.addJobContracts[counter.index].jobContract.zone}</icb:item>
  <icb:item>${command.jobOrder.jobFinishDate}</icb:item>
<icb:item>${command.jobOrder.etaDate}</icb:item> 
<icb:item>${command.addJobContracts[counter.index].priceBookId}</icb:item>

 </icb:list>


<tr>
<td class="TDShadeGrey" style="border-bottom:#5B5B5B dashed 1px; "><f:message key="zoneId"/>: </td>
<td style="border-bottom:#5B5B5B dashed 1px; ">
<form:select id="sel9" cssClass="selectionBoxbig" path="addJobContracts[${counter.index}].jobContract.zone" items="${icbfn:dropdown('zoneId', pcodes)}" itemLabel="name" itemValue="value" disabled="${command.addJobContracts[counter.index].contractViewOnly}"/>
</td>
<td class="TDShadeGrey" style="border-bottom:#5B5B5B dashed 1px; "><f:message key="vatRegId"/>:</td>
<%-- <td  style="border-bottom:#5B5B5B dashed 1px; ">${command.addJobContracts[counter.index].vatRateCountry}</td> --%>
<td  style="border-bottom:#5B5B5B dashed 1px; ">
<icb:list var="vatRegParams">
<icb:item>${command.addJobContracts[counter.index].jobContract.custCode}</icb:item>
</icb:list>
<c:choose>
<c:when test="${command.addJobContracts[counter.index].jobContract.jobContractStatus=='6000' || command.addJobContracts[counter.index].jobContract.jobContractStatus=='7200'}">				      
<form:select id="sel4" cssClass="selectionBoxint" path="addJobContracts[${counter.index}].jobContract.vatRegId" items="${icbfn:dropdown('vatRegIds', vatRegParams)}" itemLabel="name" itemValue="value" disabled="true"/>
</c:when>
<c:otherwise>
<form:select id="sel4" cssClass="selectionBoxint" path="addJobContracts[${counter.index}].jobContract.vatRegId" items="${icbfn:dropdown('vatRegIds', vatRegParams)}" itemLabel="name" itemValue="value"/>
</c:otherwise>
</c:choose>
<form:errors path="addJobContracts[${counter.index}].jobContract.vatRegId" cssClass="error"/>
</td>
</tr>


<tr>
<td class="TDShadeGrey"><f:message key="billingContact"/>:<span class="redstar">*</span>
<c:if test="${command.addJobContracts[counter.index].billingSameAsScheduler}">
<form:checkbox id="sameBillingAsScheduler${counter.index}" value="1" path="" label="Same as Scheduler" 
	onclick="setBillingAsScheduler('${counter.index}',
	'${command.addJobContracts[counter.index].jobContract.contractCustContact.contact.id}',
	'${command.addJobContracts[counter.index].jobContract.contractCustContact.contact.firstName} ${command.addJobContracts[counter.index].jobContract.contractCustContact.contact.lastName}',
	'${command.addJobContracts[counter.index].schedulerAddress}'
	);" />
	</c:if>
</td>
<td><form:input id="billingContactId${counter.index}" cssClass="inputBox"  size="14" maxlength="19" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="addJobContracts[${counter.index}].jobContract.billingContact.id" disabled="${command.addJobContracts[counter.index].contractViewOnly}"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.billingContact.id" cssClass="redstar"/>

<c:if test="${!command.addJobContracts[counter.index].contractViewOnly}">
<a href="#" onClick="javascript:setcontactflag(${counter.index});updateBillingContactIframeSrc('${counter.index}','${command.addJobContracts[counter.index].jobContract.custCode}','${command.addJobContracts[counter.index].jobContract.contractCode}');popup_show('searchbillingcontact${counter.index}', 'searchbillingcontact_drag${counter.index}', 'searchbillingcontact_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable();showPopupDiv('searchbillingcontact${counter.index}','contactbody${counter.index}');"><img src=" images/lookup.gif" alt="Lookup Billing Contact" width="13" height="13" border="0"/></a>
</c:if>
&nbsp;&nbsp;
<form:input id="billingContactName${counter.index}" cssClass="inputBox"  size="22" maxlength="50" path="addJobContracts[${counter.index}].jobContract.billingContactName" disabled="true"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.billingContactName" cssClass="redstar"/></td>
<td class="TDShadeGrey"><f:message key="invoiceType"/>:</td>
<td>
<form:select id="sel6" cssClass="selectionBoxint" path="addJobContracts[${counter.index}].jobContract.invoiceType" items="${icbfn:dropdown('staticDropdown',invoiceType)}" itemLabel="name" itemValue="value" disabled="${command.addJobContracts[counter.index].contractViewOnly}"/> </td>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="billingAddress"/>: </td>
<td colspan="3"><form:input id="billingContactAddress${counter.index}" cssClass="inputBox"   size="127" path="addJobContracts[${counter.index}].billingAddress" disabled="true"/></td>
</tr>

<tr>
<td valign="top" class="TDShadeGrey"><f:message key="remitTo"/>:<span class="redstar">*</span></td>
<td><form:input id="remitto${counter.index}" cssClass="inputBox" size="14" maxlength="0" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;"  path="addJobContracts[${counter.index}].jobContract.bankCd" disabled="${command.addJobContracts[counter.index].contractViewOnly && !(reopenViewOnly && empty command.addJobContracts[counter.index].jobContract.bankCd)}"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.bankCd" cssClass="redstar"/>

<c:if test="${!command.addJobContracts[counter.index].contractViewOnly || (reopenViewOnly && empty command.addJobContracts[counter.index].jobContract.bankCd)}">
<a href="#" onClick="javascript:updateBankIframe('${counter.index}','${command.jobOrder.buName}','${command.addJobContracts[counter.index].jobContract.transactionCurrencyCd}');popup_show('searchremitto${counter.index}', 'searchremitto_drag${counter.index}', 'searchremitto_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable();showPopupDiv('searchremitto${counter.index}','remittobody${counter.index}');"><img src=" images/lookup.gif" width="13" alt="lookup remitTo" height="13" border="0"/></a>
</c:if>
&nbsp;


<f:message key="acct"/>:
<form:input cssClass="inputBox" size="20" maxlength="0" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="addJobContracts[${counter.index}].jobContract.bankAcctKey" disabled="${command.addJobContracts[counter.index].contractViewOnly && !(reopenViewOnly && empty command.addJobContracts[counter.index].jobContract.bankAcctKey)}"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.bankAcctKey" cssClass="redstar"/>
<c:if test="${!command.addJobContracts[counter.index].contractViewOnly || (reopenViewOnly && empty command.addJobContracts[counter.index].jobContract.bankAcctKey)}">
<a href="#" onClick="javascript:updateBankAccountIframe('${counter.index}','${command.jobOrder.buName}','${command.addJobContracts[counter.index].jobContract.transactionCurrencyCd}');popup_show('searchaccount${counter.index}', 'searchaccount_drag${counter.index}', 'searchaccount_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable();showPopupDiv('searchaccount${counter.index}','accountbody${counter.index}');"><img src=" images/lookup.gif" width="13" alt="Lookup Account" height="13" border="0"/></a>
</c:if>
</td>

<td nowrap class="TDShadeGrey"><f:message key="depositIssuedByBank"/></td>
<td> <form:input cssClass="inputBox" size="38" maxlength="4" path="addJobContracts[${counter.index}].jobContract.depositNo" disabled="${command.addJobContracts[counter.index].contractViewOnly}"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.depositNo" cssClass="redstar"/></td>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="paymentType"/>:</td>
<td><form:select id="sel7" cssClass="selectionBoxbig" path="addJobContracts[${counter.index}].jobContract.pymntType" items="${icbfn:dropdown('staticDropdown',paymentType)}" itemLabel="name" itemValue="value" disabled="${command.addJobContracts[counter.index].contractViewOnly && !(reopenViewOnly && empty command.addJobContracts[counter.index].jobContract.pymntType)}"/></td>
<td class="TDShadeGrey"><f:message key="paymentterms"/>:</td>
<%-- <td><form:select id="sel8" cssClass="selectionBoxint" path="addJobContracts[${counter.index}].jobContract.pymntTermsCd" items="${icbfn:dropdown('paymentTerms', null)}" itemLabel="name" itemValue="value" disabled="true"/></td> --%>
<c:choose>
<c:when test="${empty command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.customer.paymentTerms}">
<td><form:select id="sel8" cssClass="selectionBoxint" path="addJobContracts[${counter.index}].jobContract.pymntTermsCd" items="${icbfn:dropdown('paymentTerms', null)}" itemLabel="name" itemValue="value" disabled="true"/></td>
</c:when>
<c:otherwise>
<td>
<form:select id="sel8" cssClass="selectionBoxint" path="addJobContracts[${counter.index}].jobContract.pymntTermsCd" itemLabel="name" itemValue="value" disabled="true">
<form:option value="${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.customer.paymentTerms}" label="${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.customer.paymentTerms}"/>
</form:select>
</td>
</c:otherwise>
</c:choose>
</tr>

<tr>

<td class="TDShadeGrey"><f:message key="creditApplicationApproved"/>:<span class="redstar">*</span></td>
<td>
<form:checkbox id="creapp${counter.index}" path="addJobContracts[${counter.index}].jobContract.contractCustContact.contractCust.customer.creditApproved" value="1" disabled="true"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.contractCustContact.contractCust.customer.creditApproved" cssClass="redstar"/>

<%--<form:checkbox id="creapp${counter.index}" path="addJobContracts[${counter.index}].jobContract.payByCreditAppInd" value="1" disabled="true"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.payByCreditAppInd" cssClass="redstar"/>  --%>
</td>

<td class="TDShadeGrey"><a href="http://www.intertek-cb.com/terms/840-07" target="_blank"><f:message key="creditApplication"/>:</td>
<td>&nbsp;</td>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="establishedAccountPoNo"/>:</td>
<td><form:input cssClass="inputBox" size="52" maxlength="12" path="addJobContracts[${counter.index}].jobContract.payByEstablishedAcctInd" disabled="${command.addJobContracts[counter.index].contractViewOnly}"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.payByEstablishedAcctInd" cssClass="redstar"/>  
</td>
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</tr>


<tr>
<td class="TDShadeGrey">
<c:choose>
<c:when test="${!command.addJobContracts[counter.index].contractViewOnly}">
<a href="#" onClick="javascript:updateDescription(${counter.index},'${command.dateFormat}');"><f:message key="invoiceDesc"/>:</a>
</c:when>
<c:otherwise>
<f:message key="invoiceDesc"/>
</c:otherwise>
</c:choose>
</td>
<td colspan="3">
<form:textarea path="addJobContracts[${counter.index}].jobContract.invoiceDescr"  cols="127"  id="invoiceDescrdetails${counter.index}" tabindex="49" disabled="${command.addJobContracts[counter.index].contractViewOnly}"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.invoiceDescr" /></td>

</tr>
<c:choose>
<c:when test="${command.popFlag=='true'}">
<script type="text/javascript">
hideIt();
</script>
</c:when>
</c:choose>
</table>
</table>
</div>

<!------------------------------------------------------- Billing Contact Lookup  ----------------------------------------------------->
<div class="sample_popup" id="searchbillingcontact${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="searchbillingcontact_drag${counter.index}" style="width:750px;;height:auto; ">
<a href="#"  onclick="resetJobValFlag()"><img class="menu_form_exit"   id="searchbillingcontact_exit${counter.index}" src="images/form_exit.png"/></a> &nbsp;&nbsp;&nbsp;<f:message key="searchContact"/></div>
<div class="menu_form_body" id="contactbody${counter.index}"   style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="blank.html" id="searchContactFr${counter.index}" name="searchContactFr${counter.index}" allowtransparency="yes"></iframe>
</div>
</div>
<!----------------------------------------------------- Billing Contact Lookup  ------------------------------------------------------->
<!-------------------------------------------------------- Search Bank Lookup  -------------------------------------------------------->
<div class="sample_popup"     id="searchremitto${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="searchremitto_drag${counter.index}" style="width:750px;height:auto;"> 
<img class="menu_form_exit"   id="searchremitto_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="remitToSearch"/></div>
<div class="menu_form_body" id="remittobody${counter.index}"   style="width:750px; height:530px;overflow-y:hidden;">
<table cellspacing="0" cellpadding="0" align="center" style="width:95%;" >
<tr>
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="blank.html" scrolling="auto" id="sremittoframe${counter.index}" name="sremittoframe${counter.index}" allowtransparency="yes" ></iframe>
</tr>
</table>
</div>
</div>
<!-------------------------------------------------------- Search Bank Lookup End------------------------------------------------------>
<!------------------------------------------------------- Search Account Lookup  ------------------------------------------------------>
<div class="sample_popup" id="searchaccount${counter.index}" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="searchaccount_drag${counter.index}" style="width:750px;height:auto;"> 
  <img class="menu_form_exit"   id="searchaccount_exit${counter.index}" src="images/form_exit.png" border="0" />&nbsp;&nbsp;&nbsp;<f:message key="accountSearch"/></div>
  <div class="menu_form_body" id="accountbody${counter.index}"  style="width:750px; height:530px;overflow-y:hidden;">
<table cellspacing="0" cellpadding="0" align="center" style="width:95%;" >
<tr>
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="blank.html" scrolling="auto" id="saccountframe${counter.index}" name="saccountframe${counter.index}" allowtransparency="yes" ></iframe>
</tr>
</table>
</div>
</div>
<!-------------------------------------------------------- Search Account Lookup End--------------------------------------------------->
<!------------------------------------------------------ Select Project Lookup START -------------------------------------------------->
<div class="sample_popup" id="searchprojects${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="searchprojects_drag${counter.index}" style="width:750px; "> 
<img class="menu_form_exit"   id="searchprojects_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchProjects"/> </div>
<div class="menu_form_body" id="searchprojectsbody${counter.index}"   style="width:750px; height:530px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px; height:530px;" width="100%" src="search_project_popup.htm?inputFieldId=addJobContracts[${counter.index}].jobContract.projectNumber&custCode=${command.addJobContracts[counter.index].jobContract.custCode}&rowNum=${counter.index}" scrolling="no" id="sprojframe" name="sprojframe" allowtransparency="yes" ></iframe> 
</div>
</div>
<!------------------------------------------------------ Select Project Lookup END ---------------------------------------------------->
<!------------------------------------  view Contract Attach Popup--------------------------------------------------->
<div class="sample_popup" id="contractattach${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="contractattach_drag${counter.index}" style="width:900px;height:auto;"> 
<img class="menu_form_exit"   id="contractattach_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="contractAttachments"/></div>                                               
<div class="menu_form_body" id="contractattachbody${counter.index}" style="width:900px; height:300px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:10px;" height="280px;" width="100%"  scrolling="auto" id="contractFr${counter.index}" name="contractFr${counter.index}" allowtransparency="yes" src="blank.html"></iframe>
</div>
</div>
<!---------------------------------------View Contract Attach Lookup End-------------------------------------------------->
<!------------------------------------------------------- Monthly Job Id Lookup  ----------------------------------------------------->
<div class="sample_popup" id="monthlyJob${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="monthlyJob_drag${counter.index}" style="width:880px;;height:auto; ">
<a href="#"  onclick="resetJobValFlag()"><img class="menu_form_exit"   id="monthlyJob_exit${counter.index}" src="images/form_exit.png"/></a> &nbsp;&nbsp;&nbsp;<f:message key="monthlyJobIdSearch"/></div>
<div class="menu_form_body" id="monthlyJobbody${counter.index}" style="width:880px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="520px;" width="100%" src="blank.html" id="searchmonthlyJob${counter.index}" name="searchmonthlyJob${counter.index}" allowtransparency="yes"></iframe>
</div>
</div>
<!----------------------------------------------------- Monthly Job Id Lookup End  ------------------------------------------------------->
  
<ajax:autocomplete
              baseUrl="customer.htm"
              source="addJobContracts[${counter.index}].jobContract.billingContact.id"
              target="contactIndex"
              className="autocomplete"                          parameters="job.contactId={addJobContracts[${counter.index}].jobContract.billingContact.id},job.custCode=${command.addJobContracts[counter.index].jobContract.custCode},job.contactIndex=${counter.index},job.contractCode=${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode}"
        postFunction="billingContactAjax"
              minimumCharacters="1"
               />

<ajax:autocomplete
              baseUrl="joborder.htm"
              source="addJobContracts[${counter.index}].jobContract.bankCd"
              target="addJobContracts[${counter.index}].jobContract.bankCd"
              className="autocomplete"              
              parameters="bankDesc={addJobContracts[${counter.index}].jobContract.bankCd},buName=${command.jobOrder.buName},currency= ${command.addJobContracts[counter.index].cfgContract.currencyCD}"
              minimumCharacters="1"
               />
<ajax:autocomplete
              baseUrl="joborder.htm"
              source="addJobContracts[${counter.index}].jobContract.bankAcctKey"
              target="addJobContracts[${counter.index}].jobContract.bankAcctKey"
              className="autocomplete"              
              parameters="bankAccountDesc={addJobContracts[${counter.index}].jobContract.bankAcctKey},buName=${command.jobOrder.buName},currency= ${command.addJobContracts[counter.index].cfgContract.currencyCD},bankCode={addJobContracts[${counter.index}].jobContract.bankCd}"  
              minimumCharacters="1"
               />

 <ajax:autocomplete
              baseUrl="joborder.htm"
              source="addJobContracts[${counter.index}].jobContract.projectNumber"
              target="addJobContracts[${counter.index}].jobContract.projectNumber"
              className="autocomplete"              parameters="projectNumber={addJobContracts[${counter.index}].jobContract.projectNumber},custCode=${command.addJobContracts[counter.index].jobContract.custCode}"
              minimumCharacters="1"
               /> 

<ajax:select
              baseUrl="joborder.htm"
              source="addJobContracts[${counter.index}].jobContract.locationDesc"
              target="addJobContracts[${counter.index}].jobContract.zone"              
              parameters="zonesearch=1,portCode={addJobContracts[${counter.index}].jobContract.locationDesc},contractCode=${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode},branchCode=${command.jobOrder.branchName},jdate=${command.jobOrder.jobFinishDate},edate=${command.jobOrder.etaDate},priceBookId=${command.addJobContracts[counter.index].priceBookId}"
              parser="new ResponseXmlParser()" 
              />
            

 <ajax:autocomplete
              baseUrl="joborder.htm"
              source="addJobContracts[${counter.index}].jobContract.monthlyJobNumber"
              target="addJobContracts[${counter.index}].jobContract.monthlyJobNumber"
              className="autocomplete"        parameters="monthlyJobId={addJobContracts[${counter.index}].jobContract.monthlyJobNumber},branchName=${command.jobOrder.branchName},contractCode=${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode},parentCode=${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.customer.parentCustCode}"
              minimumCharacters="3"
               /> 





</c:forEach>    
<!-- Contract 1 END -->
</table>
</div>

<!-- Contract Details Container End -->

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
            <tr>
              <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><strong><span class="redstar">*</span></strong> <span
            class="Font11pt"><f:message key="markedfiledsaremdtry" /></span></td>
                    <td style="text-align:right">
          <a href="#" onClick="javascript:checkContactIds('${fn:length(command.addJobContracts)}');goToNextPage('${command.custCount}','${command.dateFormat}','${command.userTimeFormat}')"><img src="images/savennextbluarrow.gif" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page"></a><a href="#"><img src="images/icosave.gif" alt="Save" title="Save" width="14" height="14" border="0" onClick="javascript:updateJobDescription('${command.dateFormat}');setTabName('1');checkcount('${command.custCount}','${command.dateFormat}','${command.userTimeFormat}');<%--checkDate('${command.dateFormat}','${command.userTimeFormat}');--%>;
      checkContactIds('${fn:length(command.addJobContracts)}')" /></a></td>
                  </tr>
                </table>
      </td>
         </tr>
     </table>
  </div>

<!------------------------------------------------------------- TAB 2 CONTAINER END --------------------------------------------------->

  </div>
  </div>
  <script type="text/javascript">
        
        
      var pageNoVar = "${command.tabName}"
      //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
      dolphintabs.init("tabnav", pageNoVar)      
</script>
<!------------------------------------------------------------------ TAB CONTENT END -------------------------------------------------->
  </div>
<!------------------------------------------------------------- MAIN CONTAINER END ---------------------------------------------------->
  </td>
  </tr>
  </table>
<!----------------------------------------------- MAIN OUTSIDE TABLE HOLDER END ------------------------------------------------------->

</form:form>


<script type="text/javascript">
  <c:forEach items="${command.addJobContracts}" var="addJobContract" varStatus="counter">

      <c:choose>
        <c:when test="${addJobContract.displayStatus == 'HIDE'}">
          hideOriginTable('origintable${counter.index+1}','bluarrowright${counter.index+1}','bluarrowdown${counter.index+1}',${counter.index});
        </c:when>
        <c:otherwise>
          showOriginTable('origintable${counter.index+1}','bluarrowdown${counter.index+1}','bluarrowright${counter.index+1}',${counter.index});
        </c:otherwise>
      </c:choose>
  </c:forEach>
</script>

<!-- ----------------------------------- Towing Company Lookup ------------------------------------------------- -->
<div class="sample_popup" id="towingco" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="towingco_drag" style="width:750px;">
<img class="menu_form_exit" id="towingco_exit" src="images/form_exit.png"/>&nbsp;&nbsp;&nbsp;
<f:message  key="selectTowingCompany"/></div>
<div class="menu_form_body" id="towingbody" style="width:750px; height:530px;overflow-y:hidden;"><iframe id="tow" align="left"
style="padding:0px; height:530px;" width="100%" src="search_towingcompany_popup.htm?inputFieldId=jobOrder.towingCompany.name"
scrolling="auto" name="frame1" allowtransparency="yes"></iframe></div></div>
<!-------------------------------------- Towing Company Lookup End---------------------------------------------------->
<!------------------------------------ Shipping Agent Lookup  -------------------------------------------->
<div class="sample_popup" id="shippingagent" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="shippingagent_drag" style="width:750px;"><img class="menu_form_exit" id="shippingagent_exit" src=" images/form_exit.png"/>&nbsp;&nbsp;&nbsp;<f:message key="selectShippingAgent"/></div>
<div class="menu_form_body" id="shippingagentbody" style="width:750px; height:530px;overflow-y:hidden;"><iframe id="ship" align="left"
frameborder="0" style="padding:0px; height:530px;" width="100%" src="search_shippingagent_popup.htm?inputFieldId=jobOrder.shippingAgent.name"
scrolling="auto" id="frame2" name="frame2" allowtransparency="yes"></iframe></div></div>
<!-- --------------------------------- Shipping Agent Lookup END ----------------------------------------- -->
<!-- --------------------------------- ServiceLocation Lookup START ----------------------------------------- -->
<div class="sample_popup" id="servloc" style="visibility: hidden;display: none;">
<div class="menu_form_header" id="servloc_drag" style="width:980px;">
<img class="menu_form_exit" id="servloc_exit" src=" images/form_exit.png"/>&nbsp;&nbsp;&nbsp;
<f:message  key="selectServiceLocation"/></div>
<div class="menu_form_body" id="servlocbody" style="width:980px; height:980px;overflow-y:hidden;">
<iframe id="serv" align="left"  frameborder="0" style="padding:0px; height:980px;  width:980px" width="100%"
src="search_serviceLocation_popup.htm?inputFieldId=jobOrder.serviceLocation.name" scrolling="auto" id="frame3" name="frame3" allowtransparency="yes"></iframe></div></div>
<!-- --------------------------------- ServiceLocation Lookup END ----------------------------------------- -->
<!-- --------------------------------- Port Lookup START ----------------------------------------- -->
<div class="sample_popup" id="port"  style="visibility: hidden;display: none;">
<div class="menu_form_header" id="port_drag" style="width:980px;"><img class="menu_form_exit" id="port_exit"
src=" images/form_exit.png" />&nbsp;&nbsp;&nbsp; <f:message key="selectPortLocation" /></div>
<div class="menu_form_body" id="portbody" style="width:980px; height:980px;overflow-y:hidden;">
<iframe id="portfr" align="left" src="blank.html"  frameborder="0" style="padding:0px; height:980px; width:980px" width="100%"
scrolling="auto" id="frame3" name="frame3" allowtransparency="yes"></iframe></div></div>
<!-- --------------------------------- Port Lookup END ----------------------------------------- -->
<!------------------------------------ NominationTimezone Lookup START -------------------------------------------->
<div class="sample_popup" id="timezone" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="timezone_drag" style="width:750px;">
<img class="menu_form_exit" id="timezone_exit" src=" images/form_exit.png"/>&nbsp;&nbsp;&nbsp;
<f:message key="selectTimeZone"/></div>
<div class="menu_form_body" id="timezonebody" style="width:750px; height:530px;overflow-y:hidden;">
<iframe align="middle" frameborder="0" style="padding:1px; height:530px;" width="100%"
src="search_timezone_popup.htm?inputFieldId=jobOrder.nominationTimeTz&div1=timezone&div2=timezonebody"
id="frame4" name="frame4" allowtransparency="yes"></iframe></div></div>
<!------------------------------------ Nomination Timezone  Lookup END -------------------------------------------->
<!-- ---------------------------------- Eta Timezone Lookup START -------------------------------------------->
<div class="sample_popup" id="etatimezone"  style="visibility: hidden; display: none;">
<div class="menu_form_header" id="etatimezone_drag" style="width:750px; "><img class="menu_form_exit"
id="etatimezone_exit" src=" images/form_exit.png"/>&nbsp;&nbsp;&nbsp;<f:message key="selectTimeZone" /></div>
<div class="menu_form_body" id="etatimezonebody"  style="width:750px; height:530px;overflow-y:hidden;">
<iframe align="left:2px" frameborder="0" style="padding:2px; height:530px;" width="100%"
src="search_timezone_popup.htm?inputFieldId=jobOrder.etaTimeTz&div1=etatimezone&div2=etatimezonebody"
id="frame5" name="frame5" allowtransparency="yes"></iframe></div></div>
<!------------------------------------ Eta Timezone  Lookup END -------------------------------------------->
<!------------------------------------ Received By Lookup START-------------------------------------------->
<div class="sample_popup" id="receivedby"  style="visibility: hidden; display: none;">
<div class="menu_form_header" id="receivedby_drag" style="width:750px;">
<img class="menu_form_exit" id="receivedby_exit" src="images/form_exit.png"/>&nbsp;&nbsp;&nbsp;
<f:message  key="searchUser"/></div>
<div class="menu_form_body" id="receivedbybody" style="width:750px; height:530px;overflow-y:hidden;padding-left:4px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px"  width="100%"
src="search_user_popup.htm?inputFieldId=jobOrder.receivedByUserName&div1=receivedby&div2=receivedbybody" scrolling="auto" id="searchAccountOwnerFr" name="searchAccountOwnerFr" allowtransparency="yes"></iframe></div></div>
<!-- --------------------------- Received By Lookup END ------------------------------------------------- -->
<!-- ---------------------------  Branch Code Lookup ------------------------------------------------- -->
<div class="sample_popup" id="jobbranchcode" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="jobbranchcode_drag" style="width:750px;"> 
<a href="#"  onclick="resetBranchTypeFlag()"><img class="menu_form_exit" id="jobbranchcode_exit"src="images/form_exit.png"/>&nbsp;&nbsp;&nbsp;
<f:message key="searchBranchCode"/></div>
<div class="menu_form_body" id="jobbranchcodebody" style="width:750px; height:530px;overflow-y:hidden;">  
<iframe align="left" id="jobbu" frameborder="0" style="padding:10px;" height="530px;" width="100%" scrolling="auto" id="searchBranchCodeFr" name="searchBranchCodeFr" allowtransparency="yes" src="blank.html"></iframe></div></div>
<!-- --------------------------------- Branch Code Lookup END ----------------------------------------- -->
<!-- ----------------------------------- Credit Reason Lookup ------------------------------------------------- -->
<c:forEach items="${command.addJobContracts}" var="addJobContract" varStatus="addJobContractCounter">
<div class="sample_popup" id="creditreason_${addJobContractCounter.index}" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="creditreason_drag_${addJobContractCounter.index}" style="width:800px; "> 
  <img class="menu_form_exit"   id="creditreason_exit_${addJobContractCounter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="creditReason"/></div>
  <div class="menu_form_body" id="creditreason_${addJobContractCounter.index}"   style="width:800px; height:auto">    
    <table width="95%" align="center" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td align="center">
          <iframe id="creditreasonbox_${addJobContractCounter.index}" width="100%" height="200px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>
        </td>
      </tr>
    </table>  
  </div>
</div>
</c:forEach>
<!----------------------------------- Credit Reason Lookup END ----------------------------------------- -->
<!-- ----------------------------------- Job Cancel Reason Note Lookup ------------------------------------------------- -->
<div class="sample_popup" id="cancelreason" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="cancelreason_drag" style="width:800px; "> 
  <img class="menu_form_exit"   id="cancelreason_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="cancelReason"/></div>
  <div class="menu_form_body" id="cancelreason"   style="width:800px; height:auto">    
    <table width="95%" align="center" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td align="center">
          <iframe id="cancelreasonbox" width="100%" height="200px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>
        </td>
      </tr>
    </table>  
  </div>
</div>
<!----------------------------------- Job Cancel Reason Note Lookup END ----------------------------------------- -->
<div class="sample_popup" id="requiredFields" style="visibility: hidden; display: none; ">
  <div class="menu_form_header" id="requiredFields_drag" style="width:625px; "> 
  <img class="menu_form_exit"   id="requiredFields_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="requiredFields"/></div>
    <div class="menu_form_body" style="width:625px; height:auto;">
    <table width="98%" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:15px;">
      <tbody>
        <tr>
          <td>
            <iframe id="requiredFieldsFrame" width="100%" height="600px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" 
              src="about:blank">
            </iframe>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</div>

<div id="faderPane" style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left : 1px ;Top : 1px ;">
<IMG src="images/fake-alpha-999.gif"></div>

<!-- START: 118717 Fix for new requirement - 02 June 2009 -->
<script>
try{
			objFnshDate = document.getElementById('jobOrder.jobFinishDate');
		    objEtaDate = document.getElementById('jobOrder.etaDate');
		    objEtaTimeTz= document.getElementById('jobOrder.etaTimeTz');
			if('' != objFnshDate.value && objFnshDate.value.length > 0){						
				document.editJobsInspForm.uiEtaTime.disabled = false;
				if(document.editJobsInspForm.etaTimeFormat){
					document.editJobsInspForm.etaTimeFormat.disabled = false;
				}					
				objEtaDate.disabled = false;
				objEtaTimeTz.disabled = false;
			}
	}
catch(e){}
</script>
<!-- END: 118717 Fix for new requirement - 02 June 2009 -->
