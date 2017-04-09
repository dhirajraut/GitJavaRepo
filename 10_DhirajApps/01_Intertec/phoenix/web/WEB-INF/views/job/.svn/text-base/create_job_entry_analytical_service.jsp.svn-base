<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
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
//top.document.forms[0].submit();
 document.createJobsAnalyticalServicesForm.submit();
}

function setform()
{
  var code=document.getElementById("ccode");
 //hideIt();
  if(code.value==null || code.value=="")
  {
    confirm("Please Enter search string(s) delimited by a semicolon (;) ");
    return false;
  } 
  if(code.value!=null && code.value!="")
  {
  if (isAdd(code.value) == false) {
     confirm("Please Enter a Valid Delimiters");
    document.createJobsAnalyticalServicesForm.ccode.focus();
  document.createJobsAnalyticalServicesForm.ccode.value="";
  return false;
    }
  if (isProper(code.value) == false) {
  confirm("Please Enter a Valid Contract Id(s)");
  document.createJobsAnalyticalServicesForm.ccode.focus();
  document.createJobsAnalyticalServicesForm.ccode.value="";
  return false;
    }
  if (isValid(code.value) == false) {
  confirm("Please Enter a Valid Contract Id(s)");
  document.createJobsAnalyticalServicesForm.ccode.focus();
  document.createJobsAnalyticalServicesForm.ccode.value="";
  return false;
    }
  document.createJobsAnalyticalServicesForm.uniqueFlag.value="uniqueFlag";
    document.createJobsAnalyticalServicesForm.submit();
  } 
}


function setDeleteflag(index)
{
if (confirm("Are you sure you want to delete the row?")==true)
{
 document.createJobsAnalyticalServicesForm.jobFlag.value="none";
 document.createJobsAnalyticalServicesForm.jobIndex.value = index;
 document.createJobsAnalyticalServicesForm.deleteFlag.value ="delval";
 document.createJobsAnalyticalServicesForm.submit();
}
}

function setsubflag()
{
document.createJobsAnalyticalServicesForm.jobValFlag.value="newjobval";
document.createJobsAnalyticalServicesForm.tabName.value = "1";
}

function setcontactflag(rowIndex)
{
  document.createJobsAnalyticalServicesForm.jobFlag.value="none";
  document.createJobsAnalyticalServicesForm.contactIndex.value=rowIndex;
  document.createJobsAnalyticalServicesForm.contactFlag.value="contFlag";
  document.createJobsAnalyticalServicesForm.tabName.value="1";
}

function setAttachFilesFlag(rowIndex)
{
  document.createJobsAnalyticalServicesForm.attachFilesFlag.value=rowIndex;
  document.createJobsAnalyticalServicesForm.tabName.value="1";
}

function setjobflag()
{
document.createJobsAnalyticalServicesForm.jobFlag.value="none";
 document.createJobsAnalyticalServicesForm.popFlag.value="none";
 document.createJobsAnalyticalServicesForm.contactFlag.value="none";
  document.createJobsAnalyticalServicesForm.uniqueFlag.value="none";
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
 document.createJobsAnalyticalServicesForm.uniqueFlag.value="uniqueFlag";
 document.createJobsAnalyticalServicesForm.submit();
}
else{
 document.createJobsAnalyticalServicesForm.contactFlag.value="none";
 document.createJobsAnalyticalServicesForm.jobFlag.value="none";
 document.createJobsAnalyticalServicesForm.popFlag.value="none";
 document.createJobsAnalyticalServicesForm.uniqueFlag.value="none";
 setpage();
}	
}

function setTabName(val)
{
document.createJobsAnalyticalServicesForm.tabName.value=val;
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

//function goToNextPage(dateFormat,userTimeFormat)
function goToNextPage(count,dateFormat,userTimeFormat)
{
  document.createJobsAnalyticalServicesForm.nextPageFlag.value = "1";
  updateJobDescription(dateFormat);
  checkcount(count,dateFormat,userTimeFormat)
//  checkDate(dateFormat,userTimeFormat);
}
 function updateJobDescription(dateFormat,jobDescExist)
  {
   
   var w = document.createJobsAnalyticalServicesForm.sel5.selectedIndex;
   var ops_text = document.createJobsAnalyticalServicesForm.sel5.options[w].text;
 
   var etaDate=document.getElementById("etadate").value;
   var finishDate=document.getElementById("finishdate").value;
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
 //  var desc=ops_text+" "+"of"+" "+","+" "+"at"+" "+"on"+" "+date+".";
   var desc=ops_text+" "+"of"+" "+" "+"at"+" "+"on"+" "+date;
  if(jobDescExist == 'jobdescexist')
  {
	 document.getElementById("jobDesc").value=desc;
     return desc; 
  }
   if(document.getElementById("jobDesc").value=="")
     {
     document.getElementById("jobDesc").value=desc;
     return desc; 
    }
    else
    return document.getElementById("jobDesc").value;
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
function updateDescription(index,dateFormat)
{
if(document.getElementById("invoiceDescrdetails"+index).value=="")
  {
  var description="General service charge in connection with the"+" "+updateJobDescription(dateFormat)+".";
  document.getElementById("invoiceDescrdetails"+index).value=description;
  }
else
  {
  var description=document.getElementById("invoiceDescrdetails"+index).value;
  document.getElementById("invoiceDescrdetails"+index).value=description
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
 /* if(month<10)
  var currentdate=day + "/" +"0"+ month + "/" + year;
  else
  var currentdate=day + "/" + month + "/" + year;*/
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
 function updateBranchIframeSrc()
  {
  var buName= document.getElementById("sel3").value;
   document.createJobsAnalyticalServicesForm.jobDateFlag.value="jobDate";
  if(buName!= "" && buName!= null)
  {
    /*document.getElementById('jobbu').setAttribute("src",src="search_branch_popup.htm?inputFieldId=jobOrder.branchName&div1=jobbranchcode&div2=jobbranchcodebody&buName="+buName+"&formName=jobsForm");*/
  document.getElementById('jobbu').setAttribute("src",src="search_branch_popup.htm?inputFieldId=jobOrder.branchName&div1=jobbranchcode&div2=jobbranchcodebody&buName="+buName+"&formName=createJobsAnalyticalServicesForm");
  }
  
}
function makeBranchblank()
{
  document.getElementById("brnch").value="";
}
function checkSearchField()
{

  if(document.getElementById("jobSearch").value=="")
  {
  confirm("Please enter a search string");
  return false;
  }
  else
		{
	    document.createJobsAnalyticalServicesForm.goFlag.value="true";
		document.createJobsAnalyticalServicesForm.operationFlag.value = "operation";
		submitform();
		}
}

function submitform()
    {
      
      top.document.forms[0].submit();
      
    }
function setpage()
{
document.createJobsAnalyticalServicesForm.ccode.value="";
}
function enterButton() {
if (event.keyCode == 13) {
  if(document.getElementById("ccode").value!="")
  {
setform();
}
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
document.getElementById('searchContactFr'+index).setAttribute("src","search_contact_popup.htm?inputFieldId=addJobContracts["+index+"].jobContract.billingContact.id&rowNum="+index+"&searchForm=createJobsAnalyticalServicesForm&divName=searchbillingcontact"+index+"&divbody=contactbody"+index+"&custCode="+customercode+"&contractCode="+contractcode);
}


function setbankFlag(index){
document.createJobsAnalyticalServicesForm.bankFlag.value="bankFlag";
document.createJobsAnalyticalServicesForm.tabName.value="1";
document.createJobsAnalyticalServicesForm.bankIndex.value=index;
 document.createJobsAnalyticalServicesForm.submit();
}


function showError(ecode,sc)
{
if(sc!="")
{	
 confirm("some or all of the entered criteria resulted in error. Review the errors below.\n No products were find matching the entered criteria "+ ecode +". change the criteria and try again");
 document.createJobsAnalyticalServicesForm.errorFlag.value="none";
 document.createJobsAnalyticalServicesForm.uniqueFlag.value="uniqueFlag";
 document.getElementById("ccode").value=sc;
 document.createJobsAnalyticalServicesForm.submit();
}
else{
confirm("some or all of the entered criteria resulted in error. Review the errors below.\n No products were find matching the entered criteria "+ ecode +". change the criteria and try again");
 document.createJobsAnalyticalServicesForm.errorFlag.value="none";
 document.createJobsAnalyticalServicesForm.ccode.focus();
 document.createJobsAnalyticalServicesForm.ccode.value="";
}
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
  }*/


 /*function checkcount(count,dateFormat,userTimeFormat) {
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
 }*/

function checkcount(count,dateFormat,userTimeFormat) {
 checkDate(dateFormat,userTimeFormat);
 }

 function contractAttach(contractCode,index)
  {
   document.getElementById('contractFr'+index).setAttribute("src","view_contract_attach_popup.htm?contractCode="+contractCode);
  }
 
function selectJobDateBoolean(flag)
 {
 document.createJobsAnalyticalServicesForm.jobDateFlag.value="jobDate";
 document.createJobsAnalyticalServicesForm.dateFlag.value=flag;
 if(flag=='true')
 document.createJobsAnalyticalServicesForm.openPeriodsFlag.value="true";
 else
 document.createJobsAnalyticalServicesForm.openPeriodsFlag.value="false";
 document.createJobsAnalyticalServicesForm.submit();
 }
 
 function selectJobDate(flag,elemId)
  {
  document.createJobsAnalyticalServicesForm.jobDateFlag.value="jobDate";
  document.createJobsAnalyticalServicesForm.dateFlag.value=flag;
  
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
  document.createJobsAnalyticalServicesForm.openPeriodsFlag.value="true";
  else
  document.createJobsAnalyticalServicesForm.openPeriodsFlag.value="false";
  document.createJobsAnalyticalServicesForm.submit();
 }
 
 function showInvoiceWarn()
{
confirm("Prior month GL is now closed. Your invoice will now be posted to the current month.");
document.createJobsAnalyticalServicesForm.showWarn.value="false";
}

function billingContactAjax()
{
  document.createJobsAnalyticalServicesForm.jobFlag.value="none";
  document.createJobsAnalyticalServicesForm.deleteFlag.value ="none";
  document.createJobsAnalyticalServicesForm.contactFlag.value="contFlag";
  document.createJobsAnalyticalServicesForm.tabName.value="1";
  document.createJobsAnalyticalServicesForm.submit();
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
  background: url(images/tooltipbg.jpg) repeat-y;
  }
</style>
<icb:list var="divisions">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
</icb:list>

<icb:list var="divisionBu">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
  <icb:item>${command.jobOrder.branch.businessUnit.name}</icb:item>
</icb:list>

<icb:list var="jobStatus">
  <icb:item>jobStatus</icb:item>
  <icb:item>new</icb:item>
  <icb:item>0</icb:item>
  <icb:item>false</icb:item>
  <icb:item>0</icb:item>
  <icb:item>${command.jobOrder.jobFinishDate}</icb:item>
</icb:list>
<icb:list var="searchfields">
  <icb:item>searchfields</icb:item>
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

<form:form name="createJobsAnalyticalServicesForm" method="POST" action="create_job_entry_analytical_service.htm">

<div style="color:red;"> <form:errors cssClass="error"/></div>
<form:hidden path="towingCompFlag" />
  <form:hidden path="shippingAgentFlag" />
  <form:hidden path="serviceLocationFlag" />
  <form:hidden path="tabName"/>
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
  <form:hidden path="nextPageFlag"/>
  <form:hidden path="uniqueFlag"/>
  <form:hidden path="popFlag"/>
  <form:hidden path="bankIndex"/>
  <form:hidden path="bankFlag"/>
  <form:hidden path="bankCode"/>
  <form:hidden path="operationFlag"/>
  <form:hidden path="errorFlag"/>
  <form:hidden path="errorCode"/>
  <form:hidden path="contrCode"/>
  <form:hidden path="contrFlag"/>
  <form:hidden path="jobDateFlag"/>
  <form:hidden path="goFlag"/>
  <form:hidden path="dateFlag"/>
   <form:hidden path="openPeriodsFlag"/>
   <form:hidden path="showWarn"/>
<!-------------------------------------------MAIN CONTAINER------------------------------------------------------->
  <table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
        <td valign="top">
<!---------------------------------------------BREADCRUMB TRAIL--------------------------------------------------->
      <div id="breadcrumbContainer" >
        <table width="100%" border="0" cellpadding="0" cellspacing="0" background="images/intopbluetrailbg.jpg">
          <tr>
            <td width="25"><img src="images/inlfttrailcorner.gif" width="8" height="22"></td>
            <td><table height="22" border="0" align="left" cellpadding="0" cellspacing="0">
                <tr>
                  <td class="breadcrumbtrailActive" style="background:none; padding-left:5px;">&#9658;<f:message key="entry"/></td>
                  <td class="breadcrumbtrailDeactive"><f:message key="jobInstructions"/></td>
                  <td class="breadcrumbtrailDeactive"><f:message key="selectCharges"/></td>
                  <td class="breadcrumbtrailDeactive"><f:message key="preview"/></td>
                  <td class="breadcrumbtrailDeactive"><f:message key="invoice"/></td>
                  <td align="right">&nbsp;</td>
                </tr>
              </table></td>
			<td align="right">
				<a href="#" onClick="javascript:showRequiredFields()"><f:message key="requiredFields"/></a>
			</td>
            <td align="right"><img src="images/inrttrailcorner.gif" width="7" height="22"></td>
          </tr>
        </table>
      </div>
<!-----------------------------------------------BREADCRUMB TRAIL END--------------------------------------------->
      <div id="MainContentContainer">
<!---------------------------------------------------TABS CONTENTS------------------------------------------------>
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="entryForm"/></span></a></li>
          <li><a href="#" rel="tab2"><span><f:message key="addCustomers" /></span></a></li>     
            </ul>
          </div>
<!---------------------------------------Sub Menus container. Do not remove--------------------------------------->
          <div id="tab_inner">
<!-----------------------------------------------TAB 1 CONTAINER-------------------------------------------------->
           <div id="tab1" class="innercontent">
          <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
            <tbody>
              <tr bgcolor=#ffffff>
                <th width="45%" colspan="2" nowrap><f:message key="headerDetails"/> <img src=" images/separator2.gif" width="2" height="27"  align="absmiddle" style="margin-left:5px;margin-right:5px;"/><f:message key="jobId"/> :<img src=" images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left:5px;margin-right:5px;"/><f:message key="jobTypeOCAASMNLaboratory"/></th>
                <th colspan=3 width="60%" colspan="1" style="text-align:right">
        <table width="101%" align="right" cellpadding="0" cellspacing="0">
        <tr>                        <th nowrap
              style="border-bottom:none; padding-left:0px; padding-right:0px;">
            <f:message key="status" />: <form:select id="sel1"
              cssClass="selectionBox" path="jobOrder.jobStatus"
              items="${icbfn:dropdown('staticDropdown',jobStatus)}" itemLabel="name"
              itemValue="value" /> <form:errors path="jobOrder.jobStatus"
              cssClass="redstar" /> &nbsp;&nbsp; 
              <!-- form:select
              cssClass="selectionBox" id="sel2" path="searchField"
              items="${icbfn:dropdown('staticDropdown',searchfields)}" itemLabel="name"
              itemValue="value" /> <form:errors path="searchField"
              cssClass="redstar" /> &nbsp; <f:message key="id" />:&nbsp; <form:input
              id="jobSearch" size="12" cssClass="inputBox" path="searchString" onkeypress="if(event.keyCode==13) {checkSearchField();}" /> 
              <form:errors path="searchString" cssClass="redstar" /> <input name="Submit2"
              type="button" class="button1" value="Go" style="height:18px;"
              onclick="javascript:checkSearchField();" /-->

	<f:message key="projectType" />:&nbsp; 
	<form:select id="projectTypes" cssClass="selectionBox" path="phxProjectType" items="${command.projectTypes}" itemLabel="name" itemValue="value" disabled="${command.projectTypeViewOnly}" />
</th>
              <th style="padding-right:0px; border-bottom:none"><a
              href="#"   onClick="goToNextPage('${command.custCount}','${command.dateFormat}','${command.userTimeFormat}')"><img
              src="images/savennextbluarrow.gif" alt="Save and Go to Next Page"
              width="14" height="14" hspace="4" border="0" align="absmiddle"
              title="Save and Go to Next Page"></a><a href="#" onclick="javascript:updateJobDescription('${command.dateFormat}');checkcount('${command.custCount}','${command.dateFormat}','${command.userTimeFormat}');<%--checkDate('${command.dateFormat}','${command.userTimeFormat}');--%>"><img src="images/icosave.gif" alt="Save" title="Save" width="14" height="14" border="0" align="absmiddle" /></a></th>
          </tr>
          </table>
          
          </th>
                </tr>
                <tr>
                <tr>
                <td width="15%" class="TDShade"><strong><f:message key="businessUnit"/>:</strong><span class="redstar">*</span> </td>
                <td width="35%" class="TDShadeBlue"><span class="id_input">
                   <form:select id="sel3" cssClass="selectionBoxbig" path="jobOrder.buName" items="${icbfn:dropdown('businessUnit', null)}" itemLabel="name" itemValue="value" onchange="makeBranchblank()"/>
           <form:errors path="jobOrder.buName" cssClass="redstar"/>
                 </span></td>
         <td width="15%" class="TDShade"><strong><f:message key="branchCode" />:<span class="redstar">*</span></strong></td>
         <td width="35%" colspan="2" class="TDShadeBlue">
        <form:input id="brnch" size="52" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" cssClass="inputBox" path="jobOrder.branchName" onchange="selectJobDateBoolean('false');"/>
        <form:errors path="jobOrder.branchName"  cssClass="redstar" />
         <a href="#" onClick="javascript:updateBranchIframeSrc();popup_show('jobbranchcode', 'jobbranchcode_drag', 'jobbranchcode_exit', 'screen-corner', 120, 20);hideIt();showbranchcode('jobbranchcode','jobbranchcodebody');popupboxenable()"><img
          src=" images/lookup.gif" alt="Lookup Branch Code" width="13" height="13" border="0" />
         </a></td>
              </tr>
                   <icb:list var="operations">
<icb:item>${command.jobOrder.jobType}</icb:item>
</icb:list>

<tr>
<td class="TDShade"><strong><f:message key="operation" />:<span class="redstar">*</span></strong></td>
<td class="TDShadeBlue"><form:select id="sel5"  cssClass="selectionBoxbig" path="jobOrder.operation"  items="${icbfn:dropdown('jobOperations', operations)}" itemLabel="name" itemValue="value" /> <form:errors path="jobOrder.operation" cssClass="redstar" /></td>

        </td>
                  <td class="TDShade"><strong><f:message key="salesRep"/>: </strong></td>
                  <td width="40%" colspan="4" class="TDShadeBlue"> 
          <form:input cssClass="inputBox" maxlength="128" size="52" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="jobOrder.receivedByUserName"/>
         <form:errors path="jobOrder.receivedByUserName" cssClass="redstar"/>
                   <a href="#" onClick="javascript:popup_show('salesrep','salesrep_drag','salesrep_exit', 'screen-corner', 120,20);hideIt();showPopupDiv('salesrep','salesrepbody');popupboxenable();"> <img src=" images/lookup.gif" alt="Lookup salesRep" width="13" height="13" border="0" /></a></td>
                </tr>
                <tr>
                <td class="TDShade"><f:message key="quoteIssuedDate"/>:</td>
                <td class="TDShadeBlue">
        <input name="textfield92" type="text" class="inputBox" size="20" disabled="true"/>
        
        <a href="#" onClick="displayCalendar(document.forms[0].quotedate,'dd/mm/yyyy',this)"> <img src=" images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a> &nbsp;&nbsp;<strong><f:message key="quoteID"/>:</strong></td>


          <td class="TDShade"><f:message key="jobFinishedDate"/>: </td>
                <td class="TDShadeBlue" width="30%" ><form:input id="finishdate" cssClass="inputBox" path="jobOrder.jobFinishDate" onchange="selectJobDate('true','finishdate');"/>
        <form:errors path="jobOrder.jobFinishDate" cssClass="redstar"/>
                  <a href="#" onClick="displayCalendar(document.forms[0].finishdate,'${command.dateFormat}',this)" > <img src=" images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>

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
        <td class="TDShade"><strong><f:message key="nominationDate"/>:</strong></td>
                <td class="TDShadeBlue"><form:input id="nomdate" cssClass="inputBox" size="20" path="jobOrder.nominationDate"/>
        <form:errors path="jobOrder.nominationDate" cssClass="redstar"/>
         <a href="#" onClick="displayCalendar(document.forms[0].nomdate,'${command.dateFormat}',this)"> <img src=" images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>&nbsp;&nbsp;<f:message key="time"/>:&nbsp
        <form:input size="4" id="nomTimes" cssClass="inputBox" path="uiNominationTime" /> 
         <form:errors path="uiNominationTime" cssClass="redstar" />
         <form:input size="4" cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="jobOrder.nominationTimeTz"/>
         <form:errors path="jobOrder.nominationTimeTz" cssClass="redstar"/>
               <a href="#" onClick="javascript:popup_show('timezone','timezone_drag','timezone_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('timezone','timezonebody');popupboxenable();"> <img src=" images/lookup.gif" alt="Lookup Time Zone" width="13" height="13" border="0" /></a>
        
                  <div id="debug"></div></td>

				  </c:when>
		<c:otherwise>
		 <td class="TDShade"><strong><f:message
          key="nominationDate" />:</strong></td>
          <td class="TDShadeBlue"><form:input id="nomdate" size="11"
          cssClass="inputBox" path="jobOrder.nominationDate" /> <form:errors
          path="jobOrder.nominationDate" cssClass="redstar" /> <a href="#"
           onClick="displayCalendar(document.forms[0].nomdate,'${command.dateFormat}',this)">
          <img src=" images/calendar.gif" width="15" height="17"
          align="absmiddle" border="0" /></a>&nbsp
		  <f:message
          key="time"/>:&nbsp<form:input size="5" id="nomTimes" cssClass="inputBox"
          path="uiNominationTime" /> <form:errors
          path="uiNominationTime" cssClass="redstar" />
		   <form:select
              cssClass="selectionBox" id="sel14" path="nomTimeFormat"
              items="${icbfn:dropdown('staticDropdown',jobTimeFormat)}" itemLabel="name"
              itemValue="value" /> <form:errors path="nomTimeFormat"
              cssClass="redstar" />&nbsp
		  <form:input
          size="2" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" cssClass="inputBox" path="jobOrder.nominationTimeTz" /> <form:errors
          path="jobOrder.nominationTimeTz" cssClass="redstar" /><a href="#"
          onClick="javascript:popup_show('timezone','timezone_drag','timezone_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('timezone','timezonebody');popupboxenable();">
        <img src=" images/lookup.gif" alt="Lookup Time Zone" width="13" height="13" border="0" /></a>

        <div id="debug"></div>
        </td>
		
		 </c:otherwise>
		</c:choose> 
		<c:choose>
		<c:when test="${command.userTimeFormat != 'AM/PM'}">
                <td class="TDShade"><strong><f:message key="eta"/>:</strong></td>
                <td width="45%" colspan="4" class="TDShadeBlue"><form:input id="etadate"  size="20" cssClass="inputBox" path="jobOrder.etaDate" onchange="selectJobDate('false','etadate');"/>
        <form:errors path="jobOrder.etaDate" cssClass="redstar"/>
                  <a href="#" onClick="displayCalendar(document.forms[0].etadate,'${command.dateFormat}',this)"> <img src=" images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>&nbsp;&nbsp;<f:message key="time"/>:&nbsp
         <form:input size="4" id="etaTimes" cssClass="inputBox" path="uiEtaTime" /> 
         <form:errors path="uiEtaTime" cssClass="redstar" />
         <form:input size="4" cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="jobOrder.etaTimeTz"/>
         <form:errors path="jobOrder.etaTimeTz" cssClass="redstar"/>
         <a href="#" onClick="javascript:popup_show('etatimezone','etatimezone_drag','etatimezone_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('etatimezone','etatimezonebody');popupboxenable();"> <img src=" images/lookup.gif" alt="Lookup Time Zone" width="13" height="13" border="0" /></a>
                 </td>
				 </c:when>
		<c:otherwise>
		<td class="TDShade"><strong><f:message key="eta" />:</strong></td>
        <td colspan="2" class="TDShadeBlue"><form:input id="etadate" size="11"
          cssClass="inputBox" path="jobOrder.etaDate" onchange="selectJobDate('false','etadate');"/> <form:errors
          path="jobOrder.etaDate" cssClass="redstar" /> <a href="#"
          onClick="displayCalendar(document.forms[0].etadate,'${command.dateFormat}',this)">
        <img src=" images/calendar.gif" width="15" height="17"
          align="absmiddle" border="0" /></a>&nbsp<f:message
          key="time" />:&nbsp<form:input size="5" id="etaTimes" cssClass="inputBox"
          path="uiEtaTime" /> <form:errors path="uiEtaTime"
          cssClass="redstar" />
		  <form:select
              cssClass="selectionBox" id="sel15" path="etaTimeFormat"
              items="${icbfn:dropdown('staticDropdown',jobTimeFormat)}" itemLabel="name"
              itemValue="value" /> <form:errors path="etaTimeFormat"
              cssClass="redstar" />&nbsp
		  <form:input size="3" cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;"
          path="jobOrder.etaTimeTz" /> <form:errors
          path="jobOrder.etaTimeTz" cssClass="redstar" /> <a href="#"
          onClick="javascript:popup_show('etatimezone','etatimezone_drag','etatimezone_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('etatimezone','etatimezonebody');popupboxenable();">
        <img src=" images/lookup.gif" alt="Lookup Time Zone" width="13" height="13" border="0" /></a>
        </td>
		</c:otherwise>
		</c:choose>  
              </tr>
         <tr>
                
                  <td class="TDShade"><f:message key="turnaroundTime"/>:</td>
                  <td class="TDShadeBlue" width="20%" colspan="1"> <form:input cssClass="inputBox" size="52" maxlength="100" path="jobOrder.turnaroundTime"/>
         <form:errors path="jobOrder.turnaroundTime" cssClass="redstar"/></td>
          <td class="TDShade"><f:message key="adminContact"/>:</td>
                  <td width="45%" colspan="4" class="TDShadeBlue">
         
         <form:input cssClass="inputBox" size="52" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="jobOrder.adminContactUserName"/>
          <form:errors path="jobOrder.adminContactUserName" cssClass="redstar"/> 
                   <a href="#" onClick="javascript:popup_show('admincontact','admincontact_drag','admincontact_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('admincontact','admincontactbody');popupboxenable();"> <img src=" images/lookup.gif" width="13" alt="Lookup Admin Contact" height="13" border="0" /></a></td>
                 
                </tr>
               
                
                <tr>
                  <td class="TDShade" style="border-bottom:#CCCCCC dashed 1px;"><strong> <f:message key="sampleType"/>:</strong></td>
                  <td class="TDShadeBlue" style="border-bottom:#CCCCCC dashed 1px;">&nbsp;</td>
                  <td class="TDShade" style="border-bottom:#CCCCCC dashed 1px;"><strong> <f:message key="product"/>:</strong></td>
                  <td width="45%" colspan="4" class="TDShadeBlue" style="border-bottom:#CCCCCC dashed 1px;">${command.jobOrder.productNames}</td>
                </tr>
                
                <tr>
                <td valign="top" class="TDShade" ><strong><a href="#" onClick="javascript:updateJobDescription('${command.dateFormat}','jobdescexist');"><f:message key="jobOrderDescription"/>:</a></strong></td>
                <td width="45%" colspan="6" class="TDShadeBlue"><form:textarea path="jobOrder.jobDescription" id="jobDesc" rows="5" cols="100"/>
         <form:errors path="jobOrder.jobDescription" cssClass="redstar"/> </td>
        
              </tr>
              </table>
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
            <tr>
              <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><strong ><span class="redstar">*</span></strong> <span class="Font11pt"><f:message key="markedfiledsaremdtry"/></span> </td>
                    <td style="text-align:right"><a href="#" onClick="goToNextPage('${command.custCount}','${command.dateFormat}','${command.userTimeFormat}')"><img src=" images/savennextbluarrow.gif" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page"></a><a href="#" onclick="javascript:updateJobDescription('${command.dateFormat}');checkcount('${command.custCount}','${command.dateFormat}','${command.userTimeFormat}');
			  <%--checkDate('${command.dateFormat}','${command.userTimeFormat}');--%>"><img src=" images/icosave.gif" alt="Save" title="Save" width="14" height="14" border="0" align="absmiddle" /></a></td>
                  </tr>
                </table></td>
            </tr>
          </table>
           </div>
    
          

  <ajax:autocomplete
  baseUrl="joborder.htm"
  source="jobOrder.nominationTimeTz"
  target="jobOrder.nominationTimeTz"
  className="autocomplete"
  parameters="nominationTimeZone={jobOrder.nominationTimeTz}"
  minimumCharacters="3"
   /> 
    <ajax:autocomplete
  baseUrl="joborder.htm"
  source="jobOrder.etaTimeTz"
  target="jobOrder.etaTimeTz"
  className="autocomplete"
  parameters="etaTimeZone={jobOrder.etaTimeTz}"
  minimumCharacters="3"
   /> 


<ajax:autocomplete
  baseUrl="joborder.htm"
  source="jobOrder.receivedByUserName"
  target="jobOrder.receivedByUserName"
  className="autocomplete"
  parameters="recivedBy={jobOrder.receivedByUserName}"
  minimumCharacters="3"
   /> 
   <ajax:autocomplete
  baseUrl="joborder.htm"
  source="jobOrder.adminContactUserName"
  target="jobOrder.adminContactUserName"
  className="autocomplete"
  parameters="recivedBy={jobOrder.adminContactUserName}"
  minimumCharacters="3"
   /> 
  <ajax:autocomplete
  baseUrl="branch.htm"
  source="jobOrder.branchName"
  target="jobOrder.branchName"
  className="autocomplete"              
  parameters="branchName={jobOrder.branchName},buName={jobOrder.buName}"
  minimumCharacters="3"
  postFunction="selectJobDateBoolean"
 />
<!-------------------------------------------------------------TAB 1 CONTAINER END----------------------------------------------------->
<!--------------------------------------------------------------TAB 2 CONTAINER-------------------------------------------------------->
<div id="tab2" class="innercontent" >
<table class=mainApplTable cellspacing=0 cellpadding=0 width="100%">
<tbody>

<tr bgcolor=#ffffff>
<th><f:message key="contractEntry"/>
<img src=" images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left:5px;margin-right:5px;align:left;" />
<f:message key="jobId" /> :<img src=" images/separator2.gif" width="2"  height="27" align="absmiddle"
style="margin-left:5px;margin-right:5px;" />
<f:message key="jobTypeOCAASMNLaboratory"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<f:message key="status" />: <form:select id="sel1"
              cssClass="selectionBox" path="jobOrder.jobStatus"
              items="${icbfn:dropdown('staticDropdown',jobStatus)}" itemLabel="name"
              itemValue="value" disabled="true"/> <form:errors path="jobOrder.jobStatus"
              cssClass="redstar" /> &nbsp;&nbsp; 


</th>




<th style="text-align:right"><a href="#" onClick="goToNextPage('${command.custCount}','${command.dateFormat}','${command.userTimeFormat}')"><img src=" images/savennextbluarrow.gif" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page"></a><a href="#" onClick="updateJobDescription('${command.dateFormat}');setTabName('1');checkcount('${command.custCount}','${command.dateFormat}','${command.userTimeFormat}');"
<%--checkDate('${command.dateFormat}','${command.userTimeFormat}');"--%>/><img src=" images/icosave.gif" alt="Save" title="Save" width="14" height="14" border="0" align="absmiddle" <%--onClick="updateJobDescription('${command.dateFormat}');top.document.createJobsAnalyticalServicesForm.submit();" --%>/></a></th>
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

<!-- ----------------------------------------Job Contract Lookup-------------------------------------------------->
<div class="sample_popup"     id="popup2" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="popup_drag2" style="width:900px;">
<a href="#"  onclick="resetJob('${command.uniqueCount}','${command.contrCode}')"><img class="menu_form_exit"   id="popup_exit2" src=" images/form_exit.png" /></a> &nbsp;&nbsp;&nbsp;<f:message key="contractSearchResults"/></div>
<div class="menu_form_body"  id="popup2body "   style="height:680px; width:900px;overflow-y:hidden;">
<table cellspacing="0" cellpadding="0" align="center" style="width:95%;" >
<tr>
<iframe id="pu" align="left" frameborder="0" style="padding:0px;" height="670px;" width="100%" scrolling="auto" id="searchjobContractFr" src="blank.html" name="searchjobContractFr" allowtransparency="yes" ></iframe>
</tr>
</table>
</div>
</div>
<!--------------------------------------------Job Contract Lookup End--------------------------------------------->
<c:choose>
<c:when test="${command.popFlag=='true'}">
<script type="text/javascript">
setflag("${command.searchCode}",'${command.pageNumber}');
popup_show('popup2', 'popup_drag2', 'popup_exit2', 'screen-corner', 120, 20);
hideIt();
popupboxenable();
function setflag(scode,pageNumber)
{  
document.createJobsAnalyticalServicesForm.jobFlag.value="jobval";
document.createJobsAnalyticalServicesForm.tabName.value = "1";
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
document.getElementById('pu').setAttribute("src","search_job_contract_insp_popup.htm?inputFieldId=inputFieldIdValue&searchForm=createJobsAnalyticalServicesForm&code="+code+"&pageNumber="+pageN0+"&buName="+buname+"&fromPage=0");
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
document.createJobsAnalyticalServicesForm.ccode.value=code;
document.createJobsAnalyticalServicesForm.uniqueFlag.value="uniqueFlag";
document.createJobsAnalyticalServicesForm.submit();
}
</script>
</c:when>
</c:choose>


<!------------------------------------------Job Contract Lookup END----------------------------------------------->

<div id="contractdetails"><br>
<table width="100%" cellpadding="0" cellspacing="0" cols="1" dir="ltr" class="InnerApplTable" id="RO_LINEDISP_WRK$scroll$0">
<tr>
<td colspan="2" style="padding-bottom:6px;padding-left:1px;">
<!-----------------------------------------Contract Details Container--------------------------------------------->

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
<td nowrap="nowrap" align="center"><form:checkbox id="nom"  path="addJobContracts[${counter.index}].jobContract.nominationFlag"  value="Y"/></td>
<script type="text/javascript">
setNomCheck('${counter.index}');
function setNomCheck(index)
{
  document.getElementById("nom").checked = true;
}
</script>
</c:when>
<c:otherwise>
<td nowrap="nowrap" align="center"><form:checkbox id="nom1"  path="addJobContracts[${counter.index}].jobContract.nominationFlag"  value="Y"/></td>
</c:otherwise>
</c:choose>

<td width="150" align="left">${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.customer.name}</td>

<td align="left"><a href="#" style="cursor:hand;" onMouseOver="doTooltip(event, '<table><tr><td width=120px><b>Scheduler ID:</b></td><td>${command.addJobContracts[counter.index].jobContract.contractCustContact.contact.id} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Company ID:</b>${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.customer.custCode}</td></tr><tr><td valign=top><b>Scheduler Address:</b></td> <td>${command.addJobContracts[counter.index].schedulerAddress}</td></tr><tr><td><b>Telephone:</b></td>  <td>${command.addJobContracts[counter.index].jobContract.contractCustContact.contact.workPhone}</td></tr><tr><td><b>Email Address:</b></td><td>${command.addJobContracts[counter.index].jobContract.contractCustContact.contact.workEmail}</td></tr></table>')" onMouseOut="hideTip()">${command.addJobContracts[counter.index].jobContract.contractCustContact.contact.firstName} ${command.addJobContracts[counter.index].jobContract.contractCustContact.contact.lastName}</a></td>

<td align="left"><a href="#" onclick="contractAttach('${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode}','${counter.index}');popup_show('contractattach${counter.index}', 'contractattach_drag${counter.index}', 'contractattach_exit${counter.index}', 'screen-corner', 80, 35);hideIt();popupboxenable();">${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode}</a></td>

<td align="left">${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.description}</td>

<td align="left">   
<icb:list var="jobContractStatus">
  <icb:item>jobContractStatus</icb:item>
  <icb:item>${command.addJobContracts[counter.index].jobContract.jobContractStatus}</icb:item>
</icb:list>    
<form:select id="sel3" cssClass="selectionBox" path="addJobContracts[${counter.index}].jobContract.jobContractStatus" items="${icbfn:dropdown('staticDropdown',jobContractStatus)}"  itemLabel="name" itemValue="value" disabled="true"/> 
<form:errors path="addJobContracts[${counter.index}].jobContract.jobContractStatus" cssClass="redstar" />
</td>
<td align="center"><a href="#" onClick="javascript:confirm('Please save first');"><img src=" images/icoaddnote.gif" alt="Add a note" width="18" height="15" border="0" /></a></td>

<td align="center">&nbsp;
<authz:authorize ifAnyGranted="FileUpload,FileUploadNoDel">
<a href="#" onClick="javascript:if(${command.addJobContracts[counter.index].jobContract.id>0}){setAttachFilesFlag(${counter.index });popup_show('attach${counter.index}', 'attach_drag${counter.index}', 'attach_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable();}else{confirm('Please save first');}"><img src=" images/icoattach.gif" alt="Add an attachment" width="13" height="16" border="0" /></a>
</authz:authorize>
</td>

<td align="center"><%--<a href="#" onClick="javascript:delContract('contract${counter.index}');">--%>
<a href="#" onClick="javascript:setDeleteflag('${counter.index}');">
<img src="images/icodel.gif" alt="Delete row" width="12" height="14" border="0" /></a></td>

</tr>
</table>
</div>

<!-----------------------------------------------Attach FileLookup------------------------------------------------>
<div class="sample_popup" id="attach${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="attach_drag${counter.index}" style="width:700px; "> 
<img class="menu_form_exit" id="attach_exit${counter.index}" src="images/form_exit.png"/>&nbsp;&nbsp;&nbsp;
<f:message key="attachFiles"/></div>
<div class="menu_form_body" style="width:700px; height:300px;">
<table cellspacing="0" cellpadding="0" align="center" style="width:95%;" >
<tr><iframe align="left" frameborder="0" style="padding:0px;" height="300px;" width="100%" src="create_job_add_customer_attachfile_popup.htm?contractCode=${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode}&divName=attach${counter.index}&inputFieldId=attachedFileNames" scrolling="auto" id="jobContractAttachFileFr" name="jobContractAttachFileFr" allowtransparency="yes" ></iframe></tr></table></div></div>
<!-----------------------------------------Attach File Lookup End------------------------------------------------->

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
<td width="20" ><img src=" images/spacer.gif" width="18" height="1"/></td>
<td colspan="8" style="padding:0px;">
<table border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="width:98%; margin:1px; border:none">

<tr>
<c:choose>
<c:when test="${command.jobOrder.jobType=='Insp'}">
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:when test="${command.jobOrder.jobType=='Mrin'}">
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:otherwise>
<td class="TDShadeGrey"><strong><f:message key="selectProject"/>:</strong></td>
<td><form:input cssClass="inputBox" size="43" maxlength="128" path="addJobContracts[${counter.index}].jobContract.projectNumber"/>
 <form:errors path="addJobContracts[${counter.index}].jobContract.monthlyJobNumber" cssClass="redstar"/>
  <a href="#" onClick="javascript:popup_show('searchprojects${counter.index}','searchprojects_drag${counter.index}','searchprojects_exit${counter.index}', 'screen-corner', 120, 20);showPopupDiv('searchprojects${counter.index}','searchprojectsbody${counter.index}');hideIt();popupboxenable();"><img src="images/lookup.gif" width="13" height="13" alt="Lookup Project" border="0"/></a></td>
</c:otherwise>
</c:choose>
<td class="TDShadeGrey"><f:message key="monthlyJobId"/>:</td>
<td> <form:input cssClass="inputBox" size="32" maxlength="128" path="addJobContracts[${counter.index}].jobContract.monthlyJobNumber" disabled="true"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.monthlyJobNumber" cssClass="redstar"/>
<form:checkbox  path="addJobContracts[${counter.index}].jobContract.monthlyFlag" value="Y" disabled="true" /></td>
</tr>


<tr>
<td width="18%" class="TDShadeGrey"><f:message key="origin"/><span class="redstar">*</span></td>
<td width="26%"><form:select id="sel6" cssClass="selectionBoxbig" path="addJobContracts[${counter.index}].jobContract.custSentBy" items="${icbfn:dropdown('staticDropdown',origin)}" itemLabel="name" itemValue="value"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.custSentBy" cssClass="redstar"/></td>
<td width="12%" class="TDShadeGrey"><f:message key="custRefNum"/><span class="redstar">*</span></td>
<td width="19%"><form:input cssClass="inputBox" size="38" maxlength="40" path="addJobContracts[${counter.index}].jobContract.custRefNum"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.custRefNum" cssClass="redstar"/></td>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="selectedLanguage"/></td>
<td><form:select id="sel7" cssClass="selectionBoxbig" path="addJobContracts[${counter.index}].jobContract.language" items="${icbfn:dropdown('staticDropdown',selectedLanguage)}" itemLabel="name" itemValue="value"/></td>

<c:choose>
<c:when test="${command.addJobContracts[counter.index].jobContract.invoiceLabel1==null}">
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:otherwise>
<td class="TDShadeGrey">${command.addJobContracts[counter.index].jobContract.invoiceLabel1}</td>
<td><form:input cssClass="inputBox" size="38" maxlength="64" path="addJobContracts[${counter.index}].jobContract.invoiceValue1"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.invoiceValue1" cssClass="redstar"/></td>
</c:otherwise>
</c:choose>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="contractCurrency"/></td>
<td>${command.addJobContracts[counter.index].cfgContract.currencyCD}</td>

<c:choose>
<c:when test="${command.addJobContracts[counter.index].jobContract.invoiceLabel2==null}">
<td  class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:otherwise>
<td  class="TDShadeGrey" >${command.addJobContracts[counter.index].jobContract.invoiceLabel2}</td>
<td><form:input cssClass="inputBox" size="38" maxlength="64" path="addJobContracts[${counter.index}].jobContract.invoiceValue2"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.invoiceValue2" cssClass="redstar"/></td>
</c:otherwise>
</c:choose>
</tr>

<tr>
<td nowrap class="TDShadeGrey"><f:message key="transactionCurrencyMultiplier"/></td>
<td><form:select id="sel8" cssClass="selectionBoxbig" path="addJobContracts[${counter.index}].jobContract.transactionCurrencyCd" items="${icbfn:dropdown('currencyTrans', currencies)}" itemLabel="name" itemValue="value" onchange="setbankFlag(${counter.index});"/>
<c:if test="${command.addJobContracts[counter.index].cfgContract.overridable}">
	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<f:message key="overrideRate"/>
	 <form:input cssClass="inputBox" size="5" maxlength="15" path="addJobContracts[${counter.index}].jobContract.overrideCurrRate" />
</c:if>
</td>

<c:choose>
<c:when test="${command.addJobContracts[counter.index].jobContract.invoiceLabel3==null}">
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:otherwise>
<td class="TDShadeGrey">${command.addJobContracts[counter.index].jobContract.invoiceLabel3}</td>
<td><form:input cssClass="inputBox" size="38" maxlength="64" path="addJobContracts[${counter.index}].jobContract.invoiceValue3"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.invoiceValue3" cssClass="redstar"/></td>
</c:otherwise>
</c:choose>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="productType"/></td>
<td><form:select id="sel9" cssClass="selectionBoxbig" path="addJobContracts[${counter.index}].jobContract.productType" items="${icbfn:dropdown('staticDropdown', productType)}" itemLabel="name" itemValue="value"/></td>

<c:choose>
<c:when test="${command.addJobContracts[counter.index].jobContract.invoiceLabel4==null}">
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:otherwise>
<td class="TDShadeGrey">${command.addJobContracts[counter.index].jobContract.invoiceLabel4}</td>
<td><form:input cssClass="inputBox" size="38" maxlength="64" path="addJobContracts[${counter.index}].jobContract.invoiceValue4"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.invoiceValue4" cssClass="redstar"/></td>
</c:otherwise>
</c:choose>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="zoneDescription"/></td>
<td><form:select id="sel10" cssClass="selectionBoxbig" path="addJobContracts[${counter.index}].jobContract.locationDesc" items="${icbfn:dropdown('portCode', contracts)}" itemLabel="name" itemValue="value" /></td> 

<c:choose>
<c:when test="${command.addJobContracts[counter.index].jobContract.invoiceLabel5==null}">
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</c:when>
<c:otherwise>
<td class="TDShadeGrey">${command.addJobContracts[counter.index].jobContract.invoiceLabel5}</td>
<td><form:input cssClass="inputBox" size="38" maxlength="64" path="addJobContracts[${counter.index}].jobContract.invoiceValue5" />
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
<form:select id="sel11" cssClass="selectionBoxbig" path="addJobContracts[${counter.index}].jobContract.zone" items="${icbfn:dropdown('zoneId', pcodes)}" itemLabel="name" itemValue="value"/>
</td>
<td class="TDShadeGrey" style="border-bottom:#5B5B5B dashed 1px; "><f:message key="vatRegId"/>:</td>
<%-- <td  style="border-bottom:#5B5B5B dashed 1px; ">${command.addJobContracts[counter.index].vatRateCountry}</td> --%>
<td>
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
<td><form:input id="billingContactId${counter.index}" cssClass="inputBox"  size="14" maxlength="19" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="addJobContracts[${counter.index}].jobContract.billingContact.id"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.billingContact.id" cssClass="redstar"/>
&nbsp;<a href="#" onClick="javascript:setcontactflag(${counter.index});updateBillingContactIframeSrc('${counter.index}','${command.addJobContracts[counter.index].jobContract.custCode}','${command.addJobContracts[counter.index].jobContract.contractCode}');popup_show('searchbillingcontact${counter.index}', 'searchbillingcontact_drag${counter.index}', 'searchbillingcontact_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable();showPopupDiv('searchbillingcontact${counter.index}','contactbody${counter.index}');"><img src=" images/lookup.gif" width="13" alt="Lookup Billing Contact" height="13" border="0" /></a>&nbsp;&nbsp;&nbsp;  
<form:input id="billingContactName${counter.index}"  cssClass="inputBox"  size="26" maxlength="50" path="addJobContracts[${counter.index}].jobContract.billingContactName" disabled="true"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.billingContactName" cssClass="redstar"/></td>
<td class="TDShadeGrey"><f:message key="invoiceType"/>:</td>
<td>
<form:select id="sel12" cssClass="selectionBoxint" path="addJobContracts[${counter.index}].jobContract.invoiceType" items="${icbfn:dropdown('staticDropdown',invoiceType)}" itemLabel="name" itemValue="value"/> </td>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="billingAddress"/>: </td>
<td colspan="3"><form:input  id="billingContactAddress${counter.index}"  cssClass="inputBox"  size="127" path="addJobContracts[${counter.index}].billingAddress" disabled="true"/></td>
</tr>

<tr>
<td valign="top" class="TDShadeGrey"><f:message key="remitTo"/>:<span class="redstar">*</span></td>
<td><form:input id="remitto${counter.index}" cssClass="inputBox" size="12" maxlength="0" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="addJobContracts[${counter.index}].jobContract.bankCd"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.bankCd" cssClass="redstar"/>
&nbsp;&nbsp;<a href="#" onClick="javascript:updateBankIframe('${counter.index}','${command.jobOrder.buName}','${command.addJobContracts[counter.index].jobContract.transactionCurrencyCd}');popup_show('searchremitto${counter.index}', 'searchremitto_drag${counter.index}', 'searchremitto_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable();showPopupDiv('searchremitto${counter.index}','remittobody${counter.index}');"><img src=" images/lookup.gif" width="13" alt="Lookup remitTo" height="13" border="0" /></a>&nbsp;
<f:message key="acct"/>:
<form:input cssClass="inputBox" size="20"  maxlength="0" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="addJobContracts[${counter.index}].jobContract.bankAcctKey"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.bankAcctKey" cssClass="redstar"/>
<a href="#" onClick="javascript:updateBankAccountIframe('${counter.index}','${command.jobOrder.buName}','${command.addJobContracts[counter.index].jobContract.transactionCurrencyCd}');popup_show('searchaccount${counter.index}', 'searchaccount_drag${counter.index}', 'searchaccount_exit${counter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable();showPopupDiv('searchaccount${counter.index}','accountbody${counter.index}');"><img src=" images/lookup.gif" width="13" alt="Lookup Account" height="13" border="0"/></a></td>

<td nowrap class="TDShadeGrey"><f:message key="depositIssuedByBank"/></td>
<td> <form:input cssClass="inputBox" size="38" maxlength="4"path="addJobContracts[${counter.index}].jobContract.depositNo"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.depositNo" cssClass="redstar"/></td>

</tr>

<tr>
<td class="TDShadeGrey"><f:message key="paymentType"/>:</td>
<td><form:select id="sel13" cssClass="selectionBoxbig" path="addJobContracts[${counter.index}].jobContract.pymntType" items="${icbfn:dropdown('staticDropdown',paymentType)}" itemLabel="name" itemValue="value"/></td>
<td class="TDShadeGrey"><f:message key="paymentterms"/>:</td>
<%-- 
<td><form:select id="sel14" cssClass="selectionBoxint" path="addJobContracts[${counter.index}].jobContract.pymntTermsCd" items="${icbfn:dropdown('paymentTerms', null)}" itemLabel="name" itemValue="value" disabled="true"/></td>
--%>
<c:choose>
<c:when test="${empty command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.customer.paymentTerms}">
<td><form:select id="sel14" cssClass="selectionBoxint" path="addJobContracts[${counter.index}].jobContract.pymntTermsCd" items="${icbfn:dropdown('paymentTerms', null)}" itemLabel="name" itemValue="value" disabled="true"/></td>
</c:when>
<c:otherwise>
<td>
<form:select id="sel14" cssClass="selectionBoxint" path="addJobContracts[${counter.index}].jobContract.pymntTermsCd" itemLabel="name" itemValue="value" disabled="true">
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
<td>&nbsp;
<%--<form:textarea path="addJobContracts[${counter.index}].jobContract.payByCreditCardInd"  cols="41"  id="creApplicationdetails${counter.index}" tabindex="49"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.payByCreditCardInd" cssClass="redstar"/>--%>  
</td>
</tr>

<tr>
<td class="TDShadeGrey"><f:message key="establishedAccountPoNo"/>:</td>
<td><form:input cssClass="inputBox" size="52" maxlength="12" path="addJobContracts[${counter.index}].jobContract.payByEstablishedAcctInd"/>
<form:errors path="addJobContracts[${counter.index}].jobContract.payByEstablishedAcctInd" cssClass="redstar"/>  
</td>
<td class="TDShadeGrey">&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td class="TDShadeGrey"><a href="#" onClick="javascript:updateDescription(${counter.index},'${command.dateFormat}');"><f:message key="invoiceDesc"/></a>:</td>
<td colspan="3">
<form:textarea path="addJobContracts[${counter.index}].jobContract.invoiceDescr"  cols="127"  id="invoiceDescrdetails${counter.index}" tabindex="49"/>
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
<!--------------------------------------------Billing Contact Lookup---------------------------------------------->
<div class="sample_popup" id="searchbillingcontact${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="searchbillingcontact_drag${counter.index}" style="width:750px;;height:auto; ">
<a href="#"  onclick="setjobflag()"><img class="menu_form_exit"   id="searchbillingcontact_exit${counter.index}" src="images/form_exit.png" /></a> &nbsp;&nbsp;&nbsp;<f:message key="searchContact"/></div>
<div class="menu_form_body" id="contactbody${counter.index}"   style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="blank.html" id="searchContactFr${counter.index}" name="searchContactFr${counter.index}" allowtransparency="yes"></iframe></div></div>
<!---------------------------------------Billing Contact Lookup End----------------------------------------------->
<!------------------------------------------Search Bank Lookup---------------------------------------------------->
<div class="sample_popup"  id="searchremitto${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="searchremitto_drag${counter.index}" style="width:750px;height:auto;"> 
<img class="menu_form_exit" id="searchremitto_exit${counter.index}" src="images/form_exit.png"/>&nbsp;&nbsp;&nbsp;<f:message key="remitToSearch"/></div><div class="menu_form_body" id="remittobody${counter.index}"  style="width:750px; height:530px;overflow-y:hidden;">
<table cellspacing="0" cellpadding="0" align="center" style="width:95%;" >
<tr><iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="blank.html" scrolling="auto" id="sremittoframe${counter.index}" name="sremittoframe${counter.index}" allowtransparency="yes" ></iframe>
</tr></table></div></div>
<!--------------------------------------------Search Bank Lookup End---------------------------------------------->
<!-------------------------------------------Search Account Lookup------------------------------------------------>
<div class="sample_popup" id="searchaccount${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="searchaccount_drag${counter.index}" style="width:750px;height:auto;"> 
<img class="menu_form_exit"   id="searchaccount_exit${counter.index}" src="images/form_exit.png" border="0" />&nbsp;&nbsp;&nbsp;<f:message key="accountSearch"/></div>
<div class="menu_form_body" id="accountbody${counter.index}"   style="width:750px; height:530px;overflow-y:hidden;">
<table cellspacing="0" cellpadding="0" align="center" style="width:95%;" >
<tr><iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%" src="blank.html" scrolling="auto" id="saccountframe${counter.index}" name="saccountframe${counter.index}" allowtransparency="yes" ></iframe>
</tr></table></div></div>
<!-------------------------------------------Search Account Lookup End-------------------------------------------->
<!---------------------------------------------Select Project Lookup---------------------------------------------->
<div class="sample_popup" id="searchprojects${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="searchprojects_drag${counter.index}" style="width:750px; "> 
<img class="menu_form_exit"   id="searchprojects_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchProjects"/> </div>
<div class="menu_form_body" id="searchprojectsbody${counter.index}"  style="width:750px; height:530px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px; height:530px;" width="100%" src="search_project_popup.htm?inputFieldId=addJobContracts[${counter.index}].jobContract.projectNumber&custCode=${command.addJobContracts[counter.index].jobContract.custCode}&rowNum=${counter.index}" scrolling="no" id="sprojframe" name="sprojframe" allowtransparency="yes" ></iframe></div></div>
<!--------------------------------------------Select Project Lookup END------------------------------------------->
<!------------------------------------  view Contract Attach Popup--------------------------------------------------->
<div class="sample_popup" id="contractattach${counter.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="contractattach_drag${counter.index}" style="width:900px;height:auto;"> 
<img class="menu_form_exit"   id="contractattach_exit${counter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="contractAttachments"/></div>                                               
<div class="menu_form_body" id="contractattachbody${counter.index}" style="width:900px; height:300px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:10px;" height="280px;" width="100%"  scrolling="auto" id="contractFr${counter.index}" name="contractFr${counter.index}" allowtransparency="yes" src="blank.html"></iframe></div></div>
<!---------------------------------------View Contract Attach Lookup End-------------------------------------------------->
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
              source="addJobContracts[${counter.index}].jobContract.projectNumber"
              target="addJobContracts[${counter.index}].jobContract.projectNumber"
              className="autocomplete"              parameters="projectNumber={addJobContracts[${counter.index}].jobContract.projectNumber},custCode=${command.addJobContracts[counter.index].jobContract.custCode}"
              minimumCharacters="1"
               /> 

<ajax:select
              baseUrl="joborder.htm"
              source="addJobContracts[${counter.index}].jobContract.locationDesc"
              target="addJobContracts[${counter.index}].jobContract.zone"              parameters="portCode={addJobContracts[${counter.index}].jobContract.locationDesc},contractCode=${command.addJobContracts[counter.index].jobContract.contractCustContact.contractCust.contract.contractCode},branchCode=${command.jobOrder.branchName},jdate=${command.jobOrder.jobFinishDate},edate=${command.jobOrder.etaDate},priceBookId=${command.addJobContracts[counter.index].priceBookId}"
              parser="new ResponseXmlParser()" 
              />
</c:forEach>    
</table>
</div>
<!-- ------------------------------------Contract Details Container End------------------------------------------>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
            <tr>
              <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><strong><span class="redstar">*</span></strong> <span
                     class="Font11pt"><f:message key="markedfiledsaremdtry" /></span></td>
                    <td style="text-align:right"><a href="#"  onclick="goToNextPage('${command.custCount}','${command.dateFormat}','${command.userTimeFormat}')"><img src="images/savennextbluarrow.gif" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page"></a><a href="#" onClick="updateJobDescription('${command.dateFormat}');setTabName('1');checkcount('${command.custCount}','${command.dateFormat}','${command.userTimeFormat}');"
<%--checkDate('${command.dateFormat}','${command.userTimeFormat}');"--%>><img src="images/icosave.gif" alt="Save" title="Save" width="14" height="14" border="0" /></a></td>
                  </tr>
                </table>
             </td>
         </tr>
     </table>
  </div>

<!---------------------------------------------TAB 2 CONTAINER END ----------------------------------------------->
</div>
</div>
<script type="text/javascript">
var pageNoVar = "${command.tabName}"
dolphintabs.init("tabnav", pageNoVar)      
</script>
<!---------------------------------------------TAB CONTENT END---------------------------------------------------->
</div>
<!---------------------------------------MAIN CONTAINER END------------------------------------------------------->
</td>
</tr>
</table>
<!---------------------------------MAIN OUTSIDE TABLE HOLDER END-------------------------------------------------->
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
<!-------------------------------------NominationTimezone Lookup START-------------------------------------------->
<div class="sample_popup" id="timezone" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="timezone_drag" style="width:750px; "> 
<img class="menu_form_exit"   id="timezone_exit" src=" images/form_exit.png" />&nbsp;&nbsp;&nbsp; <f:message key="selectTimeZone"/> </div>
<div class="menu_form_body" id="timezonebody" style="width:750px; height:520px;overflow-y:hidden;">
<iframe align="middle" frameborder="0" style="padding:1px; height:520px;" width="100%" src="search_timezone_popup.htm?inputFieldId=jobOrder.nominationTimeTz&div1=timezone&div2=timezonebody" id="frame4" name="frame4" allowtransparency="yes" ></iframe></div></div>
<!-------------------------------------------Nomination Timezone  Lookup END-------------------------------------->
<!---------------------------------------Eta Timezone Lookup START------------------------------------------------>
<div class="sample_popup" id="etatimezone" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="etatimezone_drag" style="width:750px; "> 
<img class="menu_form_exit" id="etatimezone_exit" src=" images/form_exit.png" />&nbsp;&nbsp;&nbsp; <f:message key="selectTimeZone"/> </div>
<div class="menu_form_body" id="etatimezonebody" style="width:750px; height:520px;overflow-y:hidden;">
<iframe align="left:2px" frameborder="0" style="padding:2px; height:520px;" width="100%" src="search_timezone_popup.htm?inputFieldId=jobOrder.etaTimeTz&div1=etatimezone&div2=etatimezonebody" id="frame5" name="frame5" allowtransparency="yes" ></iframe></div></div>
<!--------------------------------------------------- Eta Timezone  Lookup END ----------------------------------------------------------------------->
<!------------------------------------------------------------------ Sales Rep START------------------------------------------------------------------>
<div class="sample_popup" id="salesrep" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="salesrep_drag" style="width:750px;"> <img class="menu_form_exit"   id="salesrep_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchUser"/></div>
<div class="menu_form_body" id="salesrepbody" style="width:750px; height:530px;overflow-y:hidden;padding-left:4px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px" width="100%" src="search_user_popup.htm?inputFieldId=jobOrder.receivedByUserName&div1=salesrep&div2=salesrepbody" scrolling="auto" id="searchAccountOwnerFr" name="searchAccountOwnerFr" allowtransparency="yes" ></iframe></div></div>
<!-------------------------------------Sales Rep Lookup END ------------------------------------------------------>
<!--------------------------------------------------- Admin Conatct START-------------------------------------------------------------->
<div class="sample_popup" id="admincontact" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="admincontact_drag" style="width:750px;"> <img class="menu_form_exit"   id="admincontact_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchUser"/></div>
<div class="menu_form_body" id="admincontactbody" style="width:750px; height:530px;overflow-y:hidden;padding-left:4px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px" width="100%" src="search_user_popup.htm?inputFieldId=jobOrder.adminContactUserName&div1=admincontact&div2=admincontactbody" scrolling="auto" id="searchAccountOwnerFr" name="searchAccountOwnerFr" allowtransparency="yes"></iframe></div></div>
<!------------------------------ Admin Conatct Lookup END--------------------------------------------------------->
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

<!------------------------------  Branch Code Lookup ------------------------------------------------------------->
<div class="sample_popup" id="jobbranchcode" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="jobbranchcode_drag" style="width:750px; "> 
<a href="#"  onclick="resetBranchTypeFlag()">    <img class="menu_form_exit"   id="jobbranchcode_exit" src="images/form_exit.png"/>&nbsp;&nbsp;&nbsp; <f:message key="searchBranchCode"/></div>
<div class="menu_form_body" id="jobbranchcodebody"  style="width:750px; height:530px;overflow-y:hidden;">
<iframe align="left" id="jobbu" frameborder="0" style="padding:10px;" height="530px;" width="100%"  scrolling="auto" id="searchBranchCodeFr" name="searchBranchCodeFr" allowtransparency="yes" src="blank.html"></iframe></div></div>
<!------------------------------------ Branch Code Lookup END -------------------------------------------->
<div id="faderPane" style="visibility:hidden; display:none; z-index:1;Position : Absolute ;Left : 1px ;Top : 1px ;">
<IMG src="images/fake-alpha-999.gif"> </div>
