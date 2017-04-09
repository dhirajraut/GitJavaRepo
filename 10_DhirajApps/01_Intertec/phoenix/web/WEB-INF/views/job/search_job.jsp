<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script>
Tooltip.offX = 4;  
Tooltip.offY = 4;
Tooltip.followMouse = false;  // must be turned off for hover-tip

var jc_array = new Array();

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

 function sortByJobHeader(orderBy,tabname){
document.jobSearchForm.pageNumber.value = "1";
document.jobSearchForm.sortFlag.value = orderBy;
document.jobSearchForm.tabFlag.value = tabname;
document.jobSearchForm.showAllFlag.value ="false";
document.jobSearchForm.jxcel.value="false";
document.jobSearchForm.refreshing.value="false";
document.jobSearchForm.jmail.value="false";
document.jobSearchForm.columnFlag.value=orderBy;
document.jobSearchForm.submit();
}


function submitForm(tabName,pageNumber)
{
document.jobSearchForm.showAllFlag.value="false";
document.jobSearchForm.jxcel.value="false";
document.jobSearchForm.jmail.value="false";
document.jobSearchForm.refreshing.value="true";
document.jobSearchForm.sortFlag.value="";
document.jobSearchForm.tabFlag.value = tabName;
document.jobSearchForm.pageNumber.value = pageNumber;
top.document.jobSearchForm.submit();
}



function submitSearch(pageNumber)
{  
document.jobSearchForm.jxcel.value="false";
document.jobSearchForm.refreshing.value="false";
document.jobSearchForm.jmail.value="false";
document.jobSearchForm.pageNumber.value = pageNumber;
// START : #119240
document.jobSearchForm.checkPageSort.value = "true";
// END : #119240
document.jobSearchForm.submit();
} 
function submitxcel(){
document.jobSearchForm.showAllFlag.value="false";
document.jobSearchForm.jxcel.value="true";
document.jobSearchForm.refreshing.value="false";
document.jobSearchForm.jmail.value="false";
document.jobSearchForm.sortFlag.value="";
top.document.jobSearchForm.submit();
}

function submitmail(rowCount){
 document.jobSearchForm.showAllFlag.value="false";
document.jobSearchForm.jmail.value="true";
document.jobSearchForm.sortFlag.value="";
setChosenJobContracts(rowCount);
var chosenJCs = document.jobSearchForm.chosenJobContracts.value;
if(chosenJCs=='')
{
	confirm("Please select the Row(s) to send email");
	return;
}
else
{
	  document.getElementById('emailIframe').setAttribute("src","job_log_mail_popup.htm?jobContractId="+chosenJCs);
      popup_show('email', 'email_drag', 'email_exit', 'screen-corner', 120, 20); 
      hideIt();
      showPopupDiv('email','emailbody');
      popupboxenable();
}

}

  function setChosenJobContracts(rowCount) 
  {
  
  jc_array.splice(0,jc_array.length);
  
  for(i=0;i<rowCount;i++)
  {
  	var jcId = document.getElementById("mailCheck"+i).value;
  	
    if(document.getElementById("mailCheck"+i).checked)
    {
      
      jc_array[jc_array.length + 1] = jcId;
    }
 
    }
    	var JCs="";
        for(var k=0;k<jc_array.length;k++)
        {
            if(jc_array[k]!= undefined)
            {
              
              if(JCs == "")
                JCs = jc_array[k];
              else
                JCs = JCs + ";" + jc_array[k];
            }
            
        }  
          
          document.jobSearchForm.chosenJobContracts.value=JCs;
    	  
  }
  
function prevSearch(pageNumber){
document.jobSearchForm.jxcel.value="false";
document.jobSearchForm.refreshing.value="false";
document.jobSearchForm.jmail.value="false";
document.jobSearchForm.pageNumber.value = pageNumber.value;
document.jobSearchForm.submit();
}


function lastSearch(totalRecords,numInPages){
document.jobSearchForm.jxcel.value="false";
document.jobSearchForm.refreshing.value="false";
document.jobSearchForm.jmail.value="false";
var pageNumber;
var quotient=totalRecords/numInPages;
var remainder=totalRecords%numInPages;

if(remainder==0)
pageNumber=quotient;
else
pageNumber=Math.floor(quotient)+1;

document.jobSearchForm.pageNumber.value =pageNumber;
document.jobSearchForm.submit();
}


function nextSearch(pageNumber){
document.jobSearchForm.jxcel.value="false";
document.jobSearchForm.refreshing.value="false";
document.jobSearchForm.jmail.value="false";
var b=++pageNumber;
document.jobSearchForm.pageNumber.value = b;
document.jobSearchForm.submit();
}

function previousSearch(pageNumber){
document.jobSearchForm.jxcel.value="false";
document.jobSearchForm.refreshing.value="false";
document.jobSearchForm.jmail.value="false";
var b=--pageNumber;
document.jobSearchForm.pageNumber.value = b;
document.jobSearchForm.submit();
}

function updateFromJobIdIframeSrc(){
  var buName= document.getElementById("sel20").value;
  var branchName=document.getElementById("sel2").value;
  var fromJobId= document.getElementById("fromjobid").value;  document.getElementById('fromJobIds').setAttribute("src","search_jobid_popup.htm?inputFieldId=fromJobId.value&div1=searchfromjobid&div2=searchfromjobidbody&buName="+buName+"&branchName="+branchName+"&jobId="+fromJobId);
}

function updateToJobIdIframeSrc(){
  var buName= document.getElementById("sel20").value;
  var branchName=document.getElementById("sel2").value;
  var toJobId= document.getElementById("tojobid").value;  document.getElementById('toJobIds').setAttribute("src","search_jobid_popup.htm?inputFieldId=toJobId.value&div1=searchtojobid&div2=searchtojobidbody&buName="+buName+"&branchName="+branchName+"&jobId="+toJobId); 
}

function updateCreatedByIframeSrc(){
    document.getElementById('searchCreatedFr').setAttribute("src","search_user_popup.htm?inputFieldId=createdBy.value&div1=createdby&div2=createdbybody");
}


function updateModifiedByIframeSrc(){
  document.getElementById('searchModifiedFr').setAttribute("src","search_user_popup.htm?inputFieldId=modifiedBy.value&div1=modifiedby&div2=modifiedbybody");
}



function checkFromTO(fbox,tbox,dispbox,freebox1,freebox2,freebox3,freebox4)
{
document.jobSearchForm.pageNumber.value = "1"; 
document.jobSearchForm.reqFormFlag.value="jobSearch";
document.jobSearchForm.sortFlag.value="";
var status= document.getElementById("sel3").value;
var fromJobId= document.getElementById("fromjobid").value;
var toJobId= document.getElementById("tojobid").value;
var fromDate= document.getElementById("fdate").value;
var toDate= document.getElementById("tdate").value;
var branchCode=document.getElementById("sel2").value;
var noOfRows=document.getElementById("noOfRows").value;
branchCode = new String(branchCode)
branchCode = branchCode.toUpperCase()
fromJobId = new String(fromJobId)
fromJobId = fromJobId.toUpperCase()
toJobId = new String(toJobId)
toJobId = toJobId.toUpperCase()
document.jobSearchForm.jxcel.value="false";
document.jobSearchForm.refreshing.value="false";
document.jobSearchForm.jmail.value="false";
document.jobSearchForm.searchFlag.value="true";
document.jobSearchForm.tabFlag.value='main';
if(noOfRows !="")
{
	if(noOfRows>500)
	{
		confirm("Please choose less than or equal to 500 rows");
		return false;
	}
}
/*if(status==2 || status==3)
  {
    if((fromJobId=="" && toJobId=="") && (fromDate=="" && toDate==""))
    {
    confirm("select either From/To Date fields or From/To Job Id fields");
    }
  }else{*/

    if(fromJobId!="" || toJobId!="")
    {
      var hasJobid="";
      if(branchCode!="" && (fromJobId!="" || toJobId!=""))
      {
        if(fromJobId!=""){
          var fromJobIdBranch= fromJobId.split("-")[0]
            if(fromJobIdBranch!=branchCode)
            {
            confirm("Please Select Branch Associted FromJobId");
              hasJobid="false";
            }
        }
        if(toJobId!=""){
          var toJobIdBranch= toJobId.split("-")[0]
            if(toJobIdBranch!=branchCode)
            {
              confirm("Please Select Branch Associted ToJobId");
              hasJobid="false";
            }
        }
    if((fromJobIdBranch==branchCode||fromJobIdBranch=="")||(toJobIdBranch==branchCode||toJobIdBranch==""))
    {
       if(hasJobid=="")
	   createConf(fbox,tbox,dispbox,freebox1,freebox2,freebox3,freebox4);
      }
      } else
	  {
		if(branchCode=="" && fromJobId!="" && toJobId!="")
        {
        confirm("Please Select Job Associated Branch Code");
        }else
		createConf(fbox,tbox,dispbox,freebox1,freebox2,freebox3,freebox4);
	  }
    }else
      {
	createConf(fbox,tbox,dispbox,freebox1,freebox2,freebox3,freebox4);
      }
    // }
}

function setflag(rowIndex){ 
  document.jobSearchForm.rowNum.value=rowIndex;
 }


function updateBranchIframeSrc() {
  var buName= document.getElementById("sel20").value;
  if(buName!= "" && buName!= null)
  {    document.getElementById('searchBranchCodeFr').setAttribute("src","search_branch_popup.htm?inputFieldId=branch.value&div1=jobbranchcode&div2=jobbranchcodebody&buName="+buName+"&formName=jobsForm");  
  }  
}

function makeBranchblank()
{
  document.getElementById("sel2").value="";
}


function updateTimeZoneIframeSrc(index){
document.getElementById('frame4'+index).setAttribute("src","search_timezone_popup.htm?inputFieldId=results["+index+"].jobLog.dispatchTz&rowNum="+index+"&div1=timezone"+index+"&div2=timezonebody"+index);
}
 
function updateProcessTimeZoneIframeSrc(index){
document.getElementById('frame5'+index).setAttribute("src","search_timezone_popup.htm?inputFieldId=results["+index+"].jobLog.processTz&div1=ptimeZone"+index+"&div2=ptimezonebody"+index+"&rowNum="+index);
 }
function updateSession(hrefValue,jobNumber,reqForm){
 document.jobSearchForm.sessionFlag.value="true";
 document.jobSearchForm.hrefValue.value=hrefValue;
 document.jobSearchForm.hrefJobNumber.value=jobNumber;
 document.jobSearchForm.hrefForm.value=reqForm;
 document.jobSearchForm.submit();
 }

 function showAll(pageNumber)
 {
 document.jobSearchForm.pageNumber.value = pageNumber;
 document.jobSearchForm.showAllFlag.value="true";
 document.jobSearchForm.submit();
 }

  /*function navenable(tabno,showflagvalue){
	
	document.jobSearchForm.showAllFlag.value=showflagvalue;
	document.jobSearchForm.tabFlag.value=tabno;
	document.jobSearchForm.reload.value="true";
    document.getElementById("navbuttons").style.visibility = "visible";
	document.jobSearchForm.submit();
  }*/

  function navenable(tabno,load,showflagvalue,pageNumber){
	document.jobSearchForm.showAllFlag.value=showflagvalue;
    document.jobSearchForm.pageNumber.value = pageNumber;
	document.jobSearchForm.tabFlag.value=tabno;
	document.jobSearchForm.reload.value=load;
    document.getElementById("navbuttons").style.visibility = "visible";
	document.jobSearchForm.jxcel.value="false";
	document.jobSearchForm.submit();
  }


  function navdisable(tabno,tabname){
	document.jobSearchForm.tabFlag.value=tabno;
    document.getElementById("navbuttons").style.visibility = "hidden";
	document.jobSearchForm.submit();
  }

function searchConfigurationIframeSrc(){
var sc=document.getElementById("sconf").value;
 document.jobSearchForm.saveFlag.value="false";
 document.jobSearchForm.configFlag.value="true";
document.getElementById('configurationFr').setAttribute("src","search_configuration_popup.htm?inputFieldId=confName&div1=templatename&div2=templatenamebody&searchValue="+sc); 
}


function submitNote(index,tab,pageNumber)
{
document.jobSearchForm.noteFlag.value="addToNote";
document.jobSearchForm.pageNumber.value = pageNumber;
document.jobSearchForm.tabFlag.value=tab;
document.jobSearchForm.rowNum.value=index;
document.jobSearchForm.showAllFlag.value="false";
document.jobSearchForm.jxcel.value="false";
if(tab!=null && tab!='showall')
	{document.jobSearchForm.columnFlag.value="mH_col14";}
else{document.jobSearchForm.columnFlag.value="shownotes";}
document.jobSearchForm.submit();
}


function CopyOption(s,d,dspBox)
 { 
 var aryTempSourceOptions = new Array();  
 var x = 0; 
 var objSourceElement;
 var objTargetElement;
 var objDispBoxElement;

 objSourceElement=document.getElementById(s);
 objTargetElement=document.getElementById(d);
 objDispBoxElement=document.getElementById(dspBox);

 var objDispBoxElement;
 var dspArray = new Array();
 var disbox_Len = objDispBoxElement.options.length;

 var fxdTabCols = ['Main','Dispatch','Billing','Process Log','mH_Col1','dH_Col1','bH_Col1','pH_Col1'];

	if (disbox_Len>0) {
	   for(ei=0;ei<disbox_Len; ei++){
			dspArray[ei] = objDispBoxElement.options[ei].value;
	   }
	}
 
 //looping through source element to find selected options 
  for (var i = 0; i<objSourceElement.length; i++) {

  if (objSourceElement.options[i].selected) {
	  	var selCol = objSourceElement.options[i].value;
		if (fxdTabCols.indexOf(selCol) < 0 && dspArray.indexOf(selCol) < 0 ){
			  //need to move this option to target element
			  var intTargetLen ;
			  intTargetLen = objTargetElement.length++; 
			  objTargetElement.options[intTargetLen].text = objSourceElement.options[i].text; 
			  objTargetElement.options[intTargetLen].value = objSourceElement.options[i].value; 
			  objSourceElement.options[i].selected=false;
		}
  }
   else 
   { 
  //storing options that stay to recreate select element  

  var objTempValues = new Object();                
  objTempValues.text = objSourceElement.options[i].text;                
  objTempValues.value = objSourceElement.options[i].value;                
  aryTempSourceOptions[x] = objTempValues;                
  x++; 
  } 
  }
 }


function checkIfExist(ebox, dbox) {
 var exist = 0; 
  var ebox=document.getElementById(ebox);
var dbox=document.getElementById(dbox);
 if (ebox.options.length == 0) { addToPacket(ebox, dbox); }

 for (i=0; i < ebox.options.length; i++) { 
  if (ebox.options[ebox.selectedIndex].value == 'Main'||ebox.options[ebox.selectedIndex].value == 'Dispatch' ||
	ebox.options[ebox.selectedIndex].value == 'Billing'||ebox.options[ebox.selectedIndex].value == 'Process Log') {
   confirm("Cannot Move " + ebox.options[ebox.selectedIndex].value+ "!");
   exist = 1;
   break;
  } 
 }

 for (i=0; i < dbox.options.length; i++) { 
  if (ebox.options[ebox.selectedIndex].value ==  dbox.options[i].value ) {
   confirm("The " + dbox.options[i].text + " is already selected!");
   exist = 1;
   break;
  }   
 }
 if (exist != 1 ) {
  addToPacket(ebox, dbox);
 }  
}


function addToPacket(fbox,tbox) {
 var i = 0; 
 sl = fbox.selectedIndex; 
 if (sl != -1 && fbox.options.length != "0") {  
  if(fbox.options[fbox.selectedIndex].value != "") {
   var no = new Option();
   no.value = fbox.options[fbox.selectedIndex].value;
   no.text = fbox.options[fbox.selectedIndex].text;
   tbox.options[tbox.options.length] = no;
  }
 } else {
  confirm("Select column");
 }   
} 

function removeFromPack(gbox) {
 var gbox=document.getElementById(gbox);
 rem = gbox.selectedIndex;
 if (rem != -1 && gbox.options.length != "0") {
  remove(gbox);
 } else {
  confirm("No column is Selected");
 }
}

function remove(box) {
for(var i=0; i<box.options.length; i++) {
if(box.options[i].selected && box.options[i] != "") {
box.options[i].value = "";
box.options[i].text = "";
box.options[i].selected=false;
   }
}
BumpUp(box);
} 

function BumpUp(abox) {
for(var i = 0; i < abox.options.length; i++) {
if(abox.options[i].value == "")  {
for(var j = i; j < abox.options.length - 1; j++)  {
abox.options[j].value = abox.options[j + 1].value;
abox.options[j].text = abox.options[j + 1].text;
}
var ln = i;
break;
   }
}
if(ln < abox.options.length)  {
abox.options.length -= 1;
BumpUp(abox);
   }
}

	
function goAscending(gbox,tbox,disbox,freebox1,freebox2,freebox3,freebox4){	

var abox=document.getElementById(gbox);
var resultsort=document.getElementById(tbox);
var dispbox=document.getElementById(disbox);
var freemain=document.getElementById(freebox1);
var freeDisp=document.getElementById(freebox2);
var freeBill=document.getElementById(freebox3);
var freePL=document.getElementById(freebox4);

var frbox1_Len = freemain.options.length;
var frbox2_Len = freeDisp.options.length;
var frbox3_Len = freeBill.options.length;
var frbox4_Len = freePL.options.length;
var disbox4_Len = dispbox.options.length;

var myArray = new Array();
var frz1Array = new Array();
var frz2Array = new Array();
var frz3Array = new Array();
var frz4Array = new Array();
var dspArray = new Array();


	var fxdTabCols = ['Main','Dispatch','Billing','Process Log','mH_Col1','dH_Col1','bH_Col1','pH_Col1'];

	if (frbox1_Len>0 || frbox2_Len>0 || frbox3_Len>0 || frbox4_Len>0 || disbox4_Len>0) {

	   for(ai=0;ai<frbox1_Len; ai++){
			frz1Array[ai] = freemain.options[ai].value;
	   }
	   for(bi=0;bi<frbox2_Len; bi++){
			frz2Array[bi] = freeDisp.options[bi].value;
	   }
	   for(ci=0;ci<frbox3_Len; ci++){
			frz3Array[ci] = freeBill.options[ci].value;
	   }
	   for(di=0;di<frbox4_Len; di++){
			frz4Array[di] = freePL.options[di].value;
	   }
	   for(ei=0;ei<disbox4_Len; ei++){
			dspArray[ei] = dispbox.options[ei].value;
	   }
	}

	 var ind=0;
	 for (i=0; i < abox.options.length; i++) { 
		
		var colVal = abox.options[i].value;

		if ((abox.options[i].selected) && (fxdTabCols.indexOf(colVal) < 0) && (frz1Array.indexOf(colVal) < 0) && (frz2Array.indexOf(colVal) < 0) && (frz3Array.indexOf(colVal) < 0) && (frz4Array.indexOf(colVal) < 0) && (dspArray.indexOf(colVal) < 0)) {
			myArray[ind] = colVal;
			ind++;
		}
	 }



for (j=0;j< myArray.length;j++) {
	var selInd = 0;
	 for (k=0;k < abox.options.length;k++) {
		if (myArray[j] == abox.options[k].value){
			selInd = k;
		}
	 }

	
	var temp= "";
	var temp1= "";
	var selCol= "";
	var execFlg = "true";
	
	var col = abox.options[selInd].value;
	if (fxdTabCols.indexOf(col) != -1 || frz1Array.indexOf(col) != -1 || frz2Array.indexOf(col) != -1 || frz3Array.indexOf(col) != -1 || frz4Array.indexOf(col) != -1 || dspArray.indexOf(col) != -1) {
	 execFlg = "false";
	}

if (execFlg == 'true') {
	selCol = abox.options[selInd-1].value;
	if (fxdTabCols.indexOf(selCol) < 0 && frz1Array.indexOf(selCol) < 0 && frz2Array.indexOf(selCol) < 0 && frz3Array.indexOf(selCol) < 0 && frz4Array.indexOf(selCol) < 0 && dspArray.indexOf(selCol) < 0) {

			temp=abox.options[selInd-1].value;
			temp1=abox.options[selInd-1].text;
			abox.options[selInd-1].value = abox.options[selInd].value;
			abox.options[selInd-1].text = abox.options[selInd].text;
			abox.options[selInd].value=temp;
			abox.options[selInd].text=temp1;
			abox.options[selInd-1].selected=true;
			abox.options[selInd].selected=false;
	} else {
		for (i=selInd; i>freemain.options.length+2; i--) {
			selCol = abox.options[i-1].value;
			if (fxdTabCols.indexOf(selCol) < 0 && frz1Array.indexOf(selCol) < 0 && frz2Array.indexOf(selCol) < 0 && frz3Array.indexOf(selCol) < 0 && frz4Array.indexOf(selCol) < 0 && dspArray.indexOf(selCol) < 0) {
			 break;
			}
			temp=abox.options[i-1].value;
			temp1=abox.options[i-1].text;
			abox.options[i-1].value = abox.options[i].value;
			abox.options[i-1].text = abox.options[i].text;
			abox.options[i].value=temp;
			abox.options[i].text=temp1;
			abox.options[i-1].selected=true;
			abox.options[i].selected=false;

		}
	}
 } //end if - execFlg
}// myArray - end for
	highLightTabs(gbox);
	highLightColumns(gbox,tbox,disbox,freebox1,freebox2,freebox3,freebox4);

} // method goAscending

function goDescending(hbox,tbox,disbox,freebox1,freebox2,freebox3,freebox4) {

var abox=document.getElementById(hbox);
var resultsort=document.getElementById(tbox);
var dispbox=document.getElementById(disbox);
var freemain=document.getElementById(freebox1);
var freeDisp=document.getElementById(freebox2);
var freeBill=document.getElementById(freebox3);
var freePL=document.getElementById(freebox4);

var frbox1_Len = freemain.options.length;
var frbox2_Len = freeDisp.options.length;
var frbox3_Len = freeBill.options.length;
var frbox4_Len = freePL.options.length;
var disbox4_Len = dispbox.options.length;

var myArray = new Array();
var frz1Array = new Array();
var frz2Array = new Array();
var frz3Array = new Array();
var frz4Array = new Array();
var dspArray = new Array();


	var fxdTabCols = ['Main','Dispatch','Billing','Process Log','mH_Col1','dH_Col1','bH_Col1','pH_Col1'];

	if (frbox1_Len>0 || frbox2_Len>0 || frbox3_Len>0 || frbox4_Len>0 || disbox4_Len>0) {

	   for(ai=0;ai<frbox1_Len; ai++){
			frz1Array[ai] = freemain.options[ai].value;
	   }
	   for(bi=0;bi<frbox2_Len; bi++){
			frz2Array[bi] = freeDisp.options[bi].value;
	   }
	   for(ci=0;ci<frbox3_Len; ci++){
			frz3Array[ci] = freeBill.options[ci].value;
	   }
	   for(di=0;di<frbox4_Len; di++){
			frz4Array[di] = freePL.options[di].value;
	   }
	   for(ei=0;ei<disbox4_Len; ei++){
			dspArray[ei] = dispbox.options[ei].value;
	   }
	}


	 var ind=0;
	 for (i=0; i < abox.options.length; i++) { 
		
		var colVal = abox.options[i].value;

		if ((abox.options[i].selected) && (fxdTabCols.indexOf(colVal) < 0) && (frz1Array.indexOf(colVal) < 0) && (frz2Array.indexOf(colVal) < 0) && (frz3Array.indexOf(colVal) < 0) && (frz4Array.indexOf(colVal) < 0) && (dspArray.indexOf(colVal) < 0)) {
			myArray[ind] = colVal;
			ind++;
		}
	 }

for (j=myArray.length-1;j>=0;j--) {
	var selInd = 0;
	for (k=0;k < abox.options.length;k++) {
		if (myArray[j] == abox.options[k].value){
			selInd = k;
		}
	}

	var temp= "";
	var temp1= "";
	var selCol = "";
	var execFlg = "true";
	
	var col = abox.options[selInd].value;
	if (fxdTabCols.indexOf(col) != -1 || frz1Array.indexOf(col) != -1 || frz2Array.indexOf(col) != -1 || frz3Array.indexOf(col) != -1 || frz4Array.indexOf(col) != -1 || dspArray.indexOf(col) != -1) {
	 execFlg = "false";
	}

if (execFlg == 'true') {

	if (selInd<abox.options.length-1) {
		selCol = abox.options[selInd+1].value;
	}

	if (fxdTabCols.indexOf(selCol) < 0 && frz1Array.indexOf(selCol) < 0 && frz2Array.indexOf(selCol) < 0 && frz3Array.indexOf(selCol) < 0 && frz4Array.indexOf(selCol) < 0 && dspArray.indexOf(selCol) < 0) {
		if (selInd<abox.options.length-1) {
			temp=abox.options[selInd].value;
			temp1=abox.options[selInd].text;
			abox.options[selInd].value = abox.options[selInd+1].value;
			abox.options[selInd].text = abox.options[selInd+1].text;
			abox.options[selInd+1].value=temp;
			abox.options[selInd+1].text=temp1;
			abox.options[selInd+1].selected=true;
			abox.options[selInd].selected=false;
		}

	} else {
		for (i=selInd; i<abox.options.length-1 ; i++) {
			selCol = abox.options[i+1].value;
			if (fxdTabCols.indexOf(selCol) < 0 && frz1Array.indexOf(selCol) < 0 && frz2Array.indexOf(selCol) < 0 && frz3Array.indexOf(selCol) < 0 && frz4Array.indexOf(selCol) < 0 && dspArray.indexOf(selCol) < 0) {
			 break;
			}

			temp=abox.options[i].value;
			temp1=abox.options[i].text;
			abox.options[i].value = abox.options[i+1].value;
			abox.options[i].text = abox.options[i+1].text;
			abox.options[i+1].value=temp;
			abox.options[i+1].text=temp1;
			abox.options[i+1].selected=true;
			abox.options[i].selected=false;
		}
	}

}// end if - execFlg
}// myArray - end for
	highLightTabs(hbox);
	highLightColumns(hbox,tbox,disbox,freebox1,freebox2,freebox3,freebox4);

}// end Method goDescending


 function hideSelected(abox, tbox, bbox,freebox1,freebox2,freebox3,freebox4)
 {		
   var abox=document.getElementById(abox);
   var bbox=document.getElementById(bbox);
   var frzbox1 =document.getElementById(freebox1);
   var frzbox2 =document.getElementById(freebox2);
   var frzbox3 =document.getElementById(freebox3);
   var frzbox4 =document.getElementById(freebox4);
	   
	var tabInd = 0;
	var frzInd = 0;
	var colVal="";
	var tabArray = new Array();
	var freezedArray = new Array();

	 for (i=0; i < abox.options.length; i++) { 
		 colVal = abox.options[i].value;
		
	 if(abox.selectedIndex!=-1){

		if ((abox.options[i].selected) && (colVal == 'Main' || colVal == 'Dispatch' || colVal == 'Billing' || colVal == 'Process Log')) {
			tabArray[tabInd] = abox.options[i].value;
			tabInd++;
		} else if((abox.options[i].selected) && (colVal == 'mH_Col1' || colVal == 'dH_Col1' || colVal == 'bH_Col1' || colVal == 'pH_Col1')) {
			freezedArray[frzInd] = abox.options[i].value;
			frzInd++;
		} else if (abox.options[i].selected){
			if (frzbox1.options.length>0){
				 for (ii=0; ii < frzbox1.options.length; ii++) { 
					if (colVal == frzbox1.options[ii].value) {
						freezedArray[frzInd] = colVal;
						frzInd++;
						break;
					}
				 }
			}

			if (frzbox2.options.length>0){
				 for (jj=0; jj < frzbox2.options.length; jj++) { 
					if (colVal == frzbox2.options[jj].value) {
						freezedArray[frzInd] = colVal;
						frzInd++;
						break;
					}
				 }
			}

			if (frzbox3.options.length>0){
				 for (kk=0; kk < frzbox3.options.length; kk++) { 
					if (colVal == frzbox3.options[kk].value) {
						freezedArray[frzInd] = colVal;
						frzInd++;
						break;
					}
				 }
			}

			if (frzbox4.options.length>0){
				 for (ll=0; ll < frzbox4.options.length; ll++) { 
					if (colVal == frzbox4.options[ll].value) {
						freezedArray[frzInd] = colVal;
						frzInd++;
						break;
					}
				 }
			}


		} // else if
	 }// end if
	 }// end for

	   var selCol ="";
	   var strTabs="";
	   var strfrzCols="";
	   for(j=0;j<abox.options.length; j++)
	   {	
		   var exist=0;
		 if(abox.selectedIndex!=-1){
			 selCol = abox.options[abox.selectedIndex].value;
		  if((tabArray.length>0) || (freezedArray.length>0)) {
			  if (tabArray.indexOf(selCol)> -1) {
				  strTabs = strTabs + ", "+ abox.options[abox.selectedIndex].text;
	  			  abox.options[abox.selectedIndex].selected=false;
				  exist = 1;
			  } else if (freezedArray.indexOf(selCol)> -1) {
				  strfrzCols = strfrzCols + ", "+ abox.options[abox.selectedIndex].text;
	  			  abox.options[abox.selectedIndex].selected=false;
				  exist = 1;
			  }
		   }
		  if(exist != 1){
			checkHIde(abox, bbox);
		   abox.options[abox.options[abox.selectedIndex].index].style.color = "#E0E0E0";
		   abox.options[abox.options[abox.selectedIndex].index].style.backgroundColor="#A0B7E4";
		   abox.options[abox.selectedIndex].selected=false;          
		  }
		}
	  }
	  if (strTabs!='' || strfrzCols != '') {
		  strTabs = strTabs.substring(1);
		  strfrzCols = strfrzCols.substring(1);	
		  //confirm("Cannot hide selected Tab(s) or freezed column(s) \n\n Tab(s) :" +strTabs + 
		//	  "\n Freezed column(s) :"+ strfrzCols);
	  }
	if (bbox.options.length>0) {
		document.jobSearchForm.show.disabled=false;
	}
 }



 function unHideSelected(abox,bbox) {
	 var abox=document.getElementById(abox);
     var bbox=document.getElementById(bbox);
	 var hidenFldArray = new Array();
	if (bbox.options.length > 0) {	
	   for(i=0;i<bbox.options.length; i++){
			hidenFldArray[i] = bbox.options[i].value;
	   }
	   for(j=0;j<abox.options.length; j++){
		if(abox.selectedIndex!=-1) {
			if(hidenFldArray.indexOf(abox.options[abox.selectedIndex].value)!= -1){
				 abox.options[abox.options[abox.selectedIndex].index].style.color = "black";
				 abox.options[abox.options[abox.selectedIndex].index].style.backgroundColor="white";
				 uncheckHide(abox,bbox) 
				 abox.options[abox.selectedIndex].selected=false;
			  } else {
				 abox.options[abox.selectedIndex].selected=false;
			  }
			}
		}
	}
  
	if (bbox.options.length<=0) {
	document.jobSearchForm.show.disabled=true;
  }
 }



function checkHIde(ebox, dbox) {
 var exist = 0; 
 if (ebox.options.length == 0) { addToPacket(ebox, dbox); }
 
 for (i=0; i < dbox.options.length; i++) { 
  if (ebox.options[ebox.selectedIndex].value ==  dbox.options[i].value ) {
   confirm("The " + dbox.options[i].text + " is already hidden!");
   exist = 1;
   break;
  }   
 }
 if (exist != 1 ) {
  addToPacket(ebox, dbox);
 }  
}


function uncheckHide(abox,gbox) {
 for(j=0;j<gbox.options.length; j++){
	 if(abox.selectedIndex!=-1){
		if(abox.options[abox.selectedIndex].value ==  gbox.options[j].value){
		gbox.options[j].selected=true;
		remove(gbox);
		} 
     }
  }
}


function addTofreeze(fbox,tbox) {
 var i = 0; 
 sl = fbox.selectedIndex; 
 if (sl != -1 && fbox.options.length != "0") {  
  if(fbox.options[fbox.selectedIndex].value != "") {
   var no = new Option();
   no.value = fbox.options[fbox.selectedIndex].value;
   no.text = fbox.options[fbox.selectedIndex].text;
   tbox.options[tbox.options.length] = no;
  }
 } 
} 

function highLightTabs(fbox){
	var hideAndSort=document.getElementById(fbox);
	var index1 = 0;
	var index2 = 0;
	var index3 = 0;
	var index4 = 0;

	var index5 = 0;
	var index6 = 0;
	var index7 = 0;
	var index8 = 0;


	for (ii=0; ii < hideAndSort.options.length; ii++) { 


		if(hideAndSort.options[ii].value=='Main')
		 {index1=ii;}
		else if(hideAndSort.options[ii].value=='Dispatch')
		 {index2=ii;}
		else if(hideAndSort.options[ii].value=='Billing')
		 {index3=ii;}
		else if(hideAndSort.options[ii].value=='Process Log')
		 {index4=ii;}
		else{
     	document.jobSearchForm.hideAndSort.options[ii].style.backgroundColor = "white";
        document.jobSearchForm.hideAndSort.options[ii].style.color = "black";
		}

	}


document.jobSearchForm.hideAndSort.options[index1].style.backgroundColor = "blue";
document.jobSearchForm.hideAndSort.options[index1+1].style.backgroundColor = "#E0E0E0";
document.jobSearchForm.hideAndSort.options[index1].style.color = "white";
document.jobSearchForm.hideAndSort.options[index2].style.backgroundColor = "blue";
document.jobSearchForm.hideAndSort.options[index2+1].style.backgroundColor = "#E0E0E0";
document.jobSearchForm.hideAndSort.options[index2].style.color = "white";
document.jobSearchForm.hideAndSort.options[index3].style.backgroundColor = "blue";
document.jobSearchForm.hideAndSort.options[index3+1].style.backgroundColor = "#E0E0E0";
document.jobSearchForm.hideAndSort.options[index3].style.color = "white";
document.jobSearchForm.hideAndSort.options[index4].style.backgroundColor = "blue";
document.jobSearchForm.hideAndSort.options[index4+1].style.backgroundColor = "#E0E0E0";
document.jobSearchForm.hideAndSort.options[index4].style.color = "white";
}


function highLightColumns(fbox,tbox,dispbox,freebox1,freebox2,freebox3,freebox4){
	var hideandsort=document.getElementById(fbox);
	var resultsort=document.getElementById(tbox);
	var dispbox=document.getElementById(dispbox);
	var freemain=document.getElementById(freebox1);
	var freeDisp=document.getElementById(freebox2);
	var freeBill=document.getElementById(freebox3);
	var freePL=document.getElementById(freebox4);

	for(i=0; i<dispbox.options.length; i++)
	{
		for(j=0;j<hideandsort.options.length; j++)
		{	
		  if(dispbox.options[i].value == hideandsort.options[j].value)
		  {
			 document.jobSearchForm.hideAndSort.options[j].style.color = "#E0E0E0";
			 document.jobSearchForm.hideAndSort.options[j].style.backgroundColor="#A0B7E4";

		  }
	    }
 	 }


for(i=0; i<freemain.options.length; i++)
	{
		for(j=0;j<hideandsort.options.length; j++)
		{	
		  if(freemain.options[i].value == hideandsort.options[j].value)
		  {
			 document.jobSearchForm.hideAndSort.options[j].style.backgroundColor = "#E0E0E0";

		  }
	    }
 	 }

for(i=0; i<freeDisp.options.length; i++)
	{
		for(j=0;j<hideandsort.options.length; j++)
		{	
		  if(freeDisp.options[i].value == hideandsort.options[j].value)
		  {
			 document.jobSearchForm.hideAndSort.options[j].style.backgroundColor = "#E0E0E0";

		  }
	    }
 	 }

for(i=0; i<freeBill.options.length; i++)
	{
		for(j=0;j<hideandsort.options.length; j++)
		{	
		  if(freeBill.options[i].value == hideandsort.options[j].value)
		  {
			 document.jobSearchForm.hideAndSort.options[j].style.backgroundColor = "#E0E0E0";

		  }
	    }
 	 }


for(i=0; i<freePL.options.length; i++)
	{
		for(j=0;j<hideandsort.options.length; j++)
		{	
		  if(freePL.options[i].value == hideandsort.options[j].value)
		  {
			 document.jobSearchForm.hideAndSort.options[j].style.backgroundColor = "#E0E0E0";

		  }
	    }
 	 }

	for(j=0;j<hideandsort.options.length; j++)
	{	
		var selCol = hideandsort.options[j].value;
	  if((selCol == "mH_Col1")||(selCol == "dH_Col1")||(selCol == "bH_Col1")||(selCol == "pH_Col1"))
	  {
		 document.jobSearchForm.hideAndSort.options[j].style.backgroundColor = "#E0E0E0";

	  }
	}

 if (freemain.options.length>0 ||freeDisp.options.length>0 ||freeBill.options.length>0 ||freePL.options.length>0 ) {
	document.jobSearchForm.unFreeze.disabled=false;
 }

 if (dispbox.options.length>0) {
	document.jobSearchForm.show.disabled=false;
 }

}


function freezeOrder(fbox,tbox,dispbox,freelist1,freelist2,freelist3,freelist4){


var abox=document.getElementById(fbox);
var ltbox=document.getElementById(tbox);
var ldispbox=document.getElementById(dispbox);
var frbox1=document.getElementById(freelist1);
var frbox2=document.getElementById(freelist2);
var frbox3=document.getElementById(freelist3);
var frbox4=document.getElementById(freelist4);
var dspArray = new Array();

var index1;
var index2;
var index3;
var index4;
var index5=abox.options.length;
var disbox_Len = ldispbox.options.length;
for (i=0; i < abox.options.length; i++) {

	if(abox.options[i].value=='Main')
	 {index1=i;
	} else if(abox.options[i].value=='Dispatch')
	 {index2=i;
	}else if(abox.options[i].value=='Billing')
	 {index3=i;
	}else if(abox.options[i].value=='Process Log')
	 {index4=i;
	} else {
		abox.options[i].style.backgroundColor = "white";
		abox.options[i].style.color = "black";
	}


	if (disbox_Len>0) {
	   for(ei=0;ei<disbox_Len; ei++){
			dspArray[ei] = ldispbox.options[ei].value;
	   }
	}



if (abox.selectedIndex != -1 && dspArray.indexOf(abox.options[abox.selectedIndex].value) < 0) {	

 if(abox.options[abox.selectedIndex].index>index1 && abox.options[abox.selectedIndex].index<index2){
   
   
   var exist=0;
  for (i=0; i < frbox1.options.length; i++) { 
  if (abox.options[abox.selectedIndex].value ==  frbox1.options[i].value ) {
   exist = 1;
   break;
  }   
 }
 if (exist != 1 ) {
  addTofreeze(abox, frbox1);
 }  
 
	for(j=abox.options[abox.selectedIndex].index; j>index1+2; j--){
         var temp=abox.options[j-1].value;
			var temp1=abox.options[j-1].text;

			abox.options[j-1].value = abox.options[j].value;
			abox.options[j-1].text = abox.options[j].text;
			abox.options[j].value=temp;
			abox.options[j].text=temp1;			
	}
	   index1++;
   	abox.options[abox.selectedIndex].selected=false;

  }
}

if (abox.selectedIndex != -1) {	

if(abox.options[abox.selectedIndex].index>index2 && abox.options[abox.selectedIndex].index<index3){

   var exist=0;

  for (i=0; i < frbox2.options.length; i++) { 
  if (abox.options[abox.selectedIndex].value ==  frbox2.options[i].value ) {
   exist = 1;
   break;
  }   
 }
 if (exist != 1 ) {
  addTofreeze(abox, frbox2);
 }  

	for(j=abox.options[abox.selectedIndex].index; j>index2+2; j--){
 	
         var temp=abox.options[j-1].value;
			var temp1=abox.options[j-1].text;
			abox.options[j-1].value = abox.options[j].value;
			abox.options[j-1].text = abox.options[j].text;
			abox.options[j].value=temp;
			abox.options[j].text=temp1;
			
  	}
	index2++;
	abox.options[abox.selectedIndex].selected=false;

}
}

if (abox.selectedIndex != -1) {	

if(abox.options[abox.selectedIndex].index>index3 && abox.options[abox.selectedIndex].index<index4){

	 var exist=0;
  for (i=0; i < frbox3.options.length; i++) { 

  if (abox.options[abox.selectedIndex].value ==  frbox3.options[i].value ) {
   exist = 1;
   break;
  }   
 }
 if (exist != 1 ) {
  addTofreeze(abox, frbox3);
 }   
	for(j=abox.options[abox.selectedIndex].index; j>index3+2; j--){
 	
         var temp=abox.options[j-1].value;
			var temp1=abox.options[j-1].text;
			abox.options[j-1].value = abox.options[j].value;
			abox.options[j-1].text = abox.options[j].text;
			abox.options[j].value=temp;
			abox.options[j].text=temp1;
	}
	index3++;
	abox.options[abox.selectedIndex].selected=false;
}
}

if (abox.selectedIndex != -1) {	

if(abox.options[abox.selectedIndex].index > index4 &&abox.options[abox.selectedIndex].index<index5){
		 var exist=0;
  for (k=0;k< frbox4.options.length;k++) { 

  if (abox.options[abox.selectedIndex].value ==  frbox4.options[k].value){
   exist = 1;
   break;
     }   
   }

 if(exist != 1 ){
  addTofreeze(abox, frbox4);
 }   
 
	for(j=abox.options[abox.selectedIndex].index; j>index4+2; j--){ 	
         var temp=abox.options[j-1].value;
			var temp1=abox.options[j-1].text;
			abox.options[j-1].value = abox.options[j].value;
			abox.options[j-1].text = abox.options[j].text;
			abox.options[j].value=temp;
			abox.options[j].text=temp1;
	}
	index4++;
	if (abox.options[abox.selectedIndex].selected) {
     abox.options[abox.selectedIndex].selected=false;

	}
  }
 }
}

highLightColumns(fbox,tbox,dispbox,freelist1,freelist2,freelist3,freelist4);

 if (frbox1.options.length>0 ||frbox2.options.length>0 ||frbox3.options.length>0 ||frbox4.options.length>0 ) {
	document.jobSearchForm.unFreeze.disabled=false;
 }
}




function unfreezeOrder(fbox,tbox,dispbox,freelist1,freelist2,freelist3,freelist4){

var abox=document.getElementById(fbox);
var frbox1=document.getElementById(freelist1);
var frbox2=document.getElementById(freelist2);
var frbox3=document.getElementById(freelist3);
var frbox4=document.getElementById(freelist4);
var dbox=document.getElementById(dispbox);
var index1;
var index2;
var index3;
var index4;
var index5=abox.options.length;
var myArray = new Array();
var frbox1_Len = frbox1.options.length;
var frbox2_Len = frbox2.options.length;
var frbox3_Len = frbox3.options.length;
var frbox4_Len = frbox4.options.length;

if (abox.selectedIndex != -1) {

	var ind = 0;
	var colVal="";
	var fxdTabCols = ['Main','Dispatch','Billing','Process Log','mH_Col1','dH_Col1','bH_Col1','pH_Col1'];
	
	var fTC_Len = fxdTabCols.length;
	if (dbox.options.length>0) {
	   for(ai=0;ai<dbox.options.length; ai++){
			fxdTabCols[fTC_Len] = dbox.options[ai].value;
			fTC_Len++;
	   }
	}


	 for (i=0; i < abox.options.length; i++) { 
		colVal = abox.options[i].value;
		if ((abox.options[i].selected) && (fxdTabCols.indexOf(colVal) < 0)) {
			myArray[ind] = colVal;
			abox.options[i].style.backgroundColor = "white";
			abox.options[i].style.color = "black";
			ind++;
		} else {
			if(colVal=='Main')
			 {index1=i;}
			else if(colVal=='Dispatch')
			 {index2=i;}
			else if(colVal=='Billing')
			 {index3=i;}
			else if(colVal=='Process Log')
			 {index4=i;}
			else {
				abox.options[i].style.backgroundColor = "white";
				abox.options[i].style.color = "black";
			}
		}
	 }

	var myArrLen = myArray.length;
	for (ii=0; ii < abox.options.length; ii++) { 
			abox.options[ii].selected=false;	
	}

	for (i=0; i<myArrLen; i++) {
		var selIndex = 0;
		var selCol = myArray[i];
		for (j=0; j < abox.options.length; j++) { 
			if (selCol == abox.options[j].value) {
				selIndex = j;
			}
			
		}
		if((selIndex>index1) && (selIndex<index2)){
			for (k=0; k < frbox1.options.length; k++) {
				if (myArray[i] == frbox1.options[k].value){
				  frbox1.options[k].selected=true;
				  remove(frbox1);

					var temp=abox.options[selIndex].value;
					var temp1=abox.options[selIndex].text;
					for(jj=abox.options[selIndex].index; jj<index2-1; jj++){
						abox.options[jj].value = abox.options[jj+1].value;
						abox.options[jj].text = abox.options[jj+1].text;
						abox.options[jj+1].value=temp;
						abox.options[jj+1].text=temp1;
					}
					  abox.options[selIndex].selected=false;
					  break;
				 }//end if
			 }//End for
	 } else	if((selIndex>index2) && (selIndex<index3)){
			for (k=0; k < frbox2.options.length; k++) {
				if (myArray[i] == frbox2.options[k].value){
				  frbox2.options[k].selected=true;
				  remove(frbox2);

					var temp=abox.options[selIndex].value;
					var temp1=abox.options[selIndex].text;
					for(jj=abox.options[selIndex].index; jj<index3-1; jj++){
						abox.options[jj].value = abox.options[jj+1].value;
						abox.options[jj].text = abox.options[jj+1].text;
						abox.options[jj+1].value=temp;
						abox.options[jj+1].text=temp1;
					}
					  abox.options[selIndex].selected=false;
					  break;
				 }//end if
			 }//End for
		 } else	if((selIndex>index3) && (selIndex<index4)){
			for (k=0; k < frbox3.options.length; k++) {
				if (myArray[i] == frbox3.options[k].value){
				  frbox3.options[k].selected=true;
				  remove(frbox3);

					var temp=abox.options[selIndex].value;
					var temp1=abox.options[selIndex].text;
					for(jj=abox.options[selIndex].index; jj<index4-1; jj++){
						abox.options[jj].value = abox.options[jj+1].value;
						abox.options[jj].text = abox.options[jj+1].text;
						abox.options[jj+1].value=temp;
						abox.options[jj+1].text=temp1;
					}
					  abox.options[selIndex].selected=false;
					  break;
				 }//end if
			 }//End for
		 } else if((selIndex>index4) && (selIndex<index5)){
			for (k=0; k < frbox4.options.length; k++) {
				if (myArray[i] == frbox4.options[k].value){
				  frbox4.options[k].selected=true;
				  remove(frbox4);

					var temp=abox.options[selIndex].value;
					var temp1=abox.options[selIndex].text;
					for(jj=abox.options[selIndex].index; jj<index5-1; jj++){
						abox.options[jj].value = abox.options[jj+1].value;
						abox.options[jj].text = abox.options[jj+1].text;
						abox.options[jj+1].value=temp;
						abox.options[jj+1].text=temp1;
					}
					  abox.options[selIndex].selected=false;
					  break;
				 }//end if
			 }//End for
		 } else {
			 abox.options[selIndex].selected=false;
		 }

	}// end for

 highLightColumns(fbox,tbox,dispbox,freelist1,freelist2,freelist3,freelist4);


 if (frbox1.options.length<=0 && frbox2.options.length<=0 && frbox3.options.length<=0 && frbox4.options.length<=0) {
	document.jobSearchForm.unFreeze.disabled=true;
 }

}// end if selected index chk

}// method


function createConf(fbox,tbox,dispbox,freebox1,freebox2,freebox3,freebox4)
{
var abox=document.getElementById(fbox);
var bbox=document.getElementById(tbox);
var dbox=document.getElementById(dispbox);
var frbox1=document.getElementById(freebox1);
var frbox2=document.getElementById(freebox2);
var frbox3=document.getElementById(freebox3);
var frbox4=document.getElementById(freebox4);

 for(i=0; i < abox.options.length; i++) {
  abox.options[i].selected = "true";
 }

 for(i=0; i < bbox.options.length; i++) {

  bbox.options[i].selected = "true";
 }

 for(i=0; i < dbox.options.length; i++) {
  dbox.options[i].selected = "true";
 }

 for(i=0; i < frbox1.options.length; i++) {
  frbox1.options[i].selected = "true";
 }


 for(i=0; i < frbox2.options.length; i++) {
  frbox2.options[i].selected = "true";
 }

 for(i=0; i < frbox3.options.length; i++) {
  frbox3.options[i].selected = "true";
 }

for(i=0; i < frbox4.options.length; i++) {
  frbox4.options[i].selected = "true";
 }
 
 document.jobSearchForm.configFlag.value="false";
 document.jobSearchForm.saveFlag.value="true";
 document.jobSearchForm.submit();

}
function loadConfig()
 {
	 document.jobSearchForm.configFlag.value="true";
	 document.jobSearchForm.submit();
 }
function deselectColumns(fbox,tbox,dispbox,freebox1,freebox2,freebox3,freebox4){
var abox=document.getElementById(fbox);
var bbox=document.getElementById(tbox);
var dbox=document.getElementById(dispbox);
var frbox1=document.getElementById(freebox1);
var frbox2=document.getElementById(freebox2);
var frbox3=document.getElementById(freebox3);
var frbox4=document.getElementById(freebox4);

 for(i=0; i < abox.options.length; i++) {
  abox.options[i].selected = false;
 }

 for(i=0; i < bbox.options.length; i++) {

  bbox.options[i].selected = false;
 }

 for(i=0; i < dbox.options.length; i++) {
  dbox.options[i].selected = false;
 }

 for(i=0; i < frbox1.options.length; i++) {
  frbox1.options[i].selected = false;
 }


 for(i=0; i < frbox2.options.length; i++) {
  frbox2.options[i].selected = false;
 }

 for(i=0; i < frbox3.options.length; i++) {
  frbox3.options[i].selected = false;
 }

for(i=0; i < frbox4.options.length; i++) {
  frbox4.options[i].selected = false;
 }
 
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

<c:if test="${command.tabFlag=='criteria'}">

<icb:list var="divisions">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
</icb:list>

<icb:list var="divisionBu">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
  <icb:item>${command.businessUnit.value}</icb:item>
</icb:list>

<icb:list var="joblogStatus">
  <icb:item>joblogStatus</icb:item>
</icb:list>
<icb:list var="jobType">
  <icb:item>jobType</icb:item>
</icb:list>
<icb:list var="operator">
  <icb:item>operator</icb:item>
</icb:list>
<icb:list var="contractStatus">
  <icb:item>contractStatus</icb:item>
</icb:list>
</c:if>
<icb:list var="dispatchStatus">
  <icb:item>dispatchStatus</icb:item>
</icb:list>
<form:form name="jobSearchForm" method="POST" action="search_job.htm">
 <form:hidden path="rowNum"/>
 <form:hidden path="reqFormFlag"/>
 <form:hidden path="chosenJobContracts"/> 
 <form:hidden path="sortFlag"/>
 <form:hidden path="sessionFlag"/>
 <form:hidden path="hrefValue"/>
 <form:hidden path="hrefJobNumber"/>
 <form:hidden path="hrefForm"/>
 <form:hidden path="tabFlag"/>
 <form:hidden path="showAllFlag"/>
 <form:hidden path="saveFlag"/>    
 <form:hidden path="searchFlag"/> 
 <form:hidden path="configFlag"/>
 <form:hidden path="noteFlag"/>
 <!-- START : #119240  -->
<input type="hidden" name="checkPageSort" value="" />
<!-- END : #119240 -->
<div style="color:red;"><form:errors cssClass="error" /></div>
<input type="hidden" name="refreshing" value="false" />
<input type="hidden" name="pageNumber" value="1" />
<input type="hidden" name="jxcel" value="false"/>
<input type="hidden" name="jmail" value="false"/>
<input type="hidden" name="reload" value="false"/>

<form:hidden path="columnFlag"/>
<table width="97%" border="0" cellpadding="0" cellspacing="0" class="MainTable">
<tr>
<td valign="top">
<div id="MainContentContainer">
 <!-------------------------------------------TABS CONTENTS------------------------------------------------------->
<div id="tabcontainer">
<div id="tabnav">
  <%--<ul>
   <li><a href="#" onClick="navdisable('criteria','false');" rel="tab1"><span>Criteria</span></a></li>
  <c:if test="${command.mainTabFlag=='true'}"> <li><a href="#" onClick="navenable('main','false');" rel="tab2"><span>Main</span></a></li></c:if>

  <c:if test="${command.dispatchTabFlag=='true'}"> <li><a href="#" onClick="navenable('dispatch','false');" rel="tab3"><span>Dispatch</span></a></li></c:if>

  <c:if test="${command.billingTabFlag=='true'}"> <li><a href="#" onClick="navenable('billing','false');" rel="tab4"><span>Billing</span></a></li></c:if>

  <c:if test="${command.processLogTabFlag=='true'}"> <li><a href="#" onClick="navenable('processlog','false');" rel="tab5"><span>Process Log</span></a></li></c:if>
	
	<c:if test="${command.showAllFlag=='true'}"> <li><a href="#" onClick="navenable('showall','true');" rel="tab6"><span>Show All</span></a></li> </c:if>
	
  </ul>--%>
<ul>
   <li><a href="#" onClick="navdisable('criteria','false');" rel="tab1"><span><f:message key="criteria"/></span></a></li>
  <c:if test="${command.mainTabFlag=='true'}"> <li><a href="#" onClick="navenable('main','true','false','${command.pagination.currentPageNum}');" rel="tab2"><span><f:message key="main"/></span></a></li></c:if>

  <c:if test="${command.dispatchTabFlag=='true'}"> <li><a href="#" onClick="navenable('dispatch','true','false','${command.pagination.currentPageNum}');" rel="tab3"><span><f:message key="dispatch"/></span></a></li></c:if>

  <c:if test="${command.billingTabFlag=='true'}"> <li><a href="#" onClick="navenable('billing','true','false','${command.pagination.currentPageNum}');" rel="tab4"><span><f:message key="billing"/></span></a></li></c:if>

  <c:if test="${command.processLogTabFlag=='true'}"> <li><a href="#" onClick="navenable('processlog','true','false','${command.pagination.currentPageNum}');" rel="tab5"><span><f:message key="processLog"/></span></a></li></c:if>
	
  <c:if test="${command.showAllTabFlag=='true'}"> <li><a href="#" onClick="navenable('showall','false','true','${command.pagination.currentPageNum}');" rel="tab6"><span><f:message key="showAll"/></span></a></li> </c:if>

	
	
  </ul>
<div align="right">
  <table cellspacing="0" cellpadding="0" border="0">
      <tr>
         <td>
             <select name="sel5" id="sel5" class="SelectionBox" style="float:right" onChange="MM_jumpMenu('parent',this,1)">
                <option selected>Go to ... &gt;</option>
                <option value="search_job.htm"><f:message key="jobSearch"/></option>
             </select>
           </td>
        </tr>
     </table>
    </div>
</div>
<!----------------------------------------Sub Menus container. Do not remove-------------------------------------->
<div id="tab_inner1">
<!---------------------------------------------------------------------------------------------------------------->
<!---------------------------------------------------TAB 1 CONTAINER---------------------------------------------->
  <%--  <div id="tab1" class="innercontent1" >
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
      <tr>
      <th colspan="5"><f:message key="searchCriteria"/>
      </tr>--%>
	
<div id="tab1" class="innercontent1" >
<c:if test="${command.tabFlag =='criteria'}">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable" border="1">
    <tr>
       <td colspan="5" style="padding-bottom:2px;padding-left:1px;">

<!--start of the collapsable div-->

<%--<div id="log0">
  <table border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="margin-bottom:0px;" border="1" >
    <tr valign="center">
      <th  width=16 class="TDShade" style="background-image:url(images/arrowblubg.gif);"> 
        <div id="bluarrowright0" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> 
          <a href="#" onClick="javascript:showOriginTable('origintable0','bluarrowdown0','bluarrowright0',0);"> 
            <img src=" images/bluarrowright.gif" width="8" height="16" border="0" vspace="6"/></a> </div>
              <div id="bluarrowdown0" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px ">
		     <a href="#" onClick="javascript:hideOriginTable('origintable0','bluarrowright0','bluarrowdown0',0);"> 
           <img src=" images/bluarrowdown.gif" width="16" height="8" border="0" vspace="6"/></a></div></th>
         <th colspan="5" class="TDShade"><f:message key="searchCriteria"/>
       </tr>
     </table>
</div>--%>

<div id="log0">
	  <table border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="margin-bottom:0px;" border="1" >
		<tr valign="center">
		  <th  width=16 class="TDShade" style="background-image:url(images/arrowblubg.gif);">      
		   <div id="bluarrowdown0" style="visibility:visible;position:absolute; z-index: 1; margin-top:4px ">
			  <a href="#" onClick="javascript:hideOriginTable('origintable0','bluarrowright0','bluarrowdown0',0);"> 
				<img src=" images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a></div>
				 <div id="bluarrowright0" style="visibility:hidden; position:relative; z-index: 1; margin-left:4px"> 
			  <a href="#" onClick="javascript:showOriginTable('origintable0','bluarrowdown0','bluarrowright0',0);"> 
		   <img src=" images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div></th>
		 <th colspan="5" class="TDShade"><f:message key="searchCriteria"/></th>
	  </tr>
	</table>
 </div>


<!-- end of the collapsable div -->


<!-- search criteria content  of the collapsable div -->

<div id="origintable0" style="padding:0px; visibility:visible;">
<table border="0" cellpadding="0" cellspacing="0" cols="11" class="mainApplTable" style="margin-top:0px; border-top:none" >
	 
  <tr> 
    <td width="15%" class="TDShade"><label for="businessUnitName"><f:message key="businessUnitName" />: </label><span class="redstar">*</span> </td>
        <td width="30%" class="TDShadeBlue">
         <%--  <form:select cssClass="selectionBox" id="sel20" path="businessUnit.value" items="${icbfn:dropdown('businessUnit', divisions)}" itemLabel="name" itemValue="value"/> --%>
		  <form:select cssClass="selectionBox" id="sel20" path="businessUnit.value" items="${icbfn:dropdown('businessUnit', null)}" itemLabel="name" itemValue="value" onchange="makeBranchblank()" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/>
		 <form:errors path="businessUnit.value" cssClass="error"/></td>
               <td width="15%" class="TDShade"><label for="branchName"><f:message key="branchName" />: </label></td>
           <td width="30%" colspan="2" class="TDShadeBlue">
       
		<%-- <form:select cssClass="selectionBox" id="sel2" path="branch.value" items="${icbfn:dropdown('branch', divisionBu)}" itemLabel="name" itemValue="value"/><form:errors path="branch.value" cssClass="error"/> --%>
       
	 <form:input id="sel2" cssStyle="text-align:left;background-color:#d2e1ff; color:#000099;" size="42" cssClass="inputBox" path="branch.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/>
        <form:errors path="branch.value"  cssClass="redstar" />

		 <a href="#" onClick="javascript:updateBranchIframeSrc();popup_show('jobbranchcode', 'jobbranchcode_drag', 'jobbranchcode_exit', 'screen-corner', 80, 80);hideIt();showbranchcode('jobbranchcode','jobbranchcodebody');popupboxenable()"><img
          src=" images/lookup.gif" width="13" alt="Lookup branch Code" height="13" border="0"/></a> 
     </td>
  </tr>

  <tr>
	 <td width="15%" class="TDShade"><f:message key="status"/>:<span class="redstar">*</span> </td>
	   <td width="30%" class="TDShadeBlue">
		 <form:select cssClass="selectionBox" id="sel3" path="status.value" items="${icbfn:dropdown('staticDropdown',joblogStatus)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="status.value" cssClass="error"/>         
		   </td>
		  <td class="TDShade"><f:message key="jobType" />: </td>
	   <td class="TDShadeBlue" colspan="2">
	 <form:select cssClass="selectionBox" id="sel4" path="jobType.value" items="${icbfn:dropdown('staticDropdown',jobType)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="jobType.value" cssClass="error"/>
   </td>
</tr>
      

  <tr>
     <td class="TDShade"><f:message key="fromJobId"/>:</td>
         <td class="TDShadeBlue"><form:input id="fromjobid"cssClass="inputBox" path="fromJobId.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="fromJobId.value" cssClass="error"/> &nbsp;<a href="#" onClick="javascript:updateFromJobIdIframeSrc();popup_show('searchfromjobid', 'searchfromjobid_drag','searchfromjobid_exit', 'screen-corner', 120,20);showPopupDiv('searchfromjobid','searchfromjobidbody');hideIt();popupboxenable();"><img src="images/lookup.gif" alt="Lookup jobId" width="13" height="13" border="0" /></a></td>
        <td class="TDShade"><f:message key="toJobId"/>:</td>
         <td class="TDShadeBlue" colspan="2"><form:input id="tojobid" cssClass="inputBox" path="toJobId.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="toJobId.value" cssClass="error"/>&nbsp;<a href="#" onClick="javascript:updateToJobIdIframeSrc();popup_show('searchtojobid', 'searchtojobid_drag', 'searchtojobid_exit', 'screen-corner', 120, 20);hideIt();showPopupDiv('searchtojobid','searchtojobidbody');popupboxenable();"><img src="images/lookup.gif" width="13"  alt="Lookup jobId"height="13" border="0" /></a>
	 </td>
  </tr>
      
	  <tr>
		 <td class="TDShade"><f:message key="fromJobFinishDate"/>: </td>
			<td class="TDShadeBlue"><form:input id="fdate" cssClass="inputBox" path="fromJobFinshDate.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/>
			<form:errors path="fromJobFinshDate.value" cssClass="error"/><a href="#" onClick="displayCalendar(document.forms[0].fdate,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
			<td class="TDShade"><f:message key="toJobFinishDate"/>: </td>
			<td class="TDShadeBlue" colspan="2"><form:input id="tdate" cssClass="inputBox" path="toJobFinshDate.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/>
			<form:errors path="toJobFinshDate.value" cssClass="error"/><a href="#" onClick="displayCalendar(document.forms[0].tdate,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
		 </td>
     </tr>
      
	  <tr>
         <td class="TDShade"><f:message key="etaFrom"/>: </td>
		  <td class="TDShadeBlue"><form:input id="etaFrom" cssClass="inputBox" path="etaFrom.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="etaFrom.value" cssClass="error"/>&nbsp;<a href="#" onClick="displayCalendar(document.forms[0].etaFrom,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
		  <td class="TDShade"><f:message key="etaTo"/>: </td>
		  <td class="TDShadeBlue" colspan="2"><form:input id="etaTo" cssClass="inputBox" path="etaTo.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="etaTo.value" cssClass="error"/>&nbsp;<a href="#" onClick="displayCalendar(document.forms[0].etaTo,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
	     </td>
      </tr>
      
	  <tr>
		 <td class="TDShade"><f:message key="nomDtFrom"/>: </td>
			<td class="TDShadeBlue"><form:input id="ndFrom" cssClass="inputBox" path="nomDateFrom.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="nomDateFrom.value" cssClass="error"/>&nbsp;<a href="#" onClick="displayCalendar(document.forms[0].ndFrom,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
			<td class="TDShade"><f:message key="nomDtTo"/>: </td>
			<td class="TDShadeBlue" colspan="2"><form:input id="nomDtTo" cssClass="inputBox" path="nomDateTo.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="nomDateTo.value" cssClass="error"/>&nbsp;<a href="#" onClick="displayCalendar(document.forms[0].nomDtTo,'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
		 </td>
      </tr>
      
	<tr>
		<td class="TDShade"><f:message key="vessel"/>: </td>
			<td class="TDShadeBlue">
			<form:select cssClass="selectionBox" id="sel5" path="vessel.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/>
			<form:input cssClass="inputBox" path="vessel.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="vessel.value" cssClass="error"/>
			</td>
			<td class="TDShade"><f:message key="product"/>: </td>
			<td class="TDShadeBlue" colspan="2">
			<form:select cssClass="selectionBox" id="sel6" path="product.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/>
			<form:input cssClass="inputBox" path="product.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="product.value" cssClass="error"/>
		</td>
	</tr>
      
	<tr>
	    <td class="TDShade"><f:message key="custRefNum"/>: </td>
			<td class="TDShadeBlue"><form:input cssClass="inputBox" path="custRefNum.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/></td>
			<td class="TDShade"><f:message key="icbRef"/>: </td>
			<td class="TDShadeBlue" colspan="2"><form:input cssClass="inputBox" path="icbRefNum.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="icbRefNum.value" cssClass="error"/>
		</td>
	</tr>

		<tr>
		   <td class="TDShade"><f:message key="invoice"/>: </td>
				<td class="TDShadeBlue"><form:input cssClass="inputBox" path="invoice.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/></td>
				<icb:list var="jobContractStatus">
				<icb:item>jobContractStatus</icb:item>
				<icb:item>invoiceStatus.value</icb:item>
				</icb:list> 
				<td class="TDShade"><f:message key="invoiceStatus"/>: </td>
				<td class="TDShadeBlue">  <form:select cssClass="selectionBox" id="sel3" path="invoiceStatus.value"  items="${icbfn:dropdown('staticDropdown',jobContractStatus)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="invoiceStatus.value" cssClass="error"/>
			</td>
		</tr>

	  <tr>
		 <td class="TDShade"><f:message key="contractId"/>: </td>
			<td class="TDShadeBlue">
			<form:select cssClass="selectionBox" id="sel11" path="contractId.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/>
			<form:input cssClass="inputBox" path="contractId.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="contractId.value" cssClass="error"/>
			</td>
			<td class="TDShade"><f:message key="contractDescription"/>: </td>
			<td class="TDShadeBlue"><form:input cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="contractDescription.value" size="35" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/>
		</td>
	</tr>
       
	 <tr>
        <td class="TDShade"><f:message key="createdBy"/>: </td>
		  <td class="TDShadeBlue"><form:input cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="createdBy.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/>
		  <a href="#" onClick="javascript:updateCreatedByIframeSrc();popup_show('createdby','createdby_drag','createdby_exit', 'screen-corner', 120,20);hideIt();showPopupDiv('createdby','createdbybody');popupboxenable();">
		   <img src=" images/lookup.gif"  alt="Lookup createdBy" width="13" height="13" border="0"/></a></td>
		  <td class="TDShade"><f:message key="modifiedBy"/>: </td>
		  <td class="TDShadeBlue"><form:input cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="modifiedBy.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/>
		  <a href="#" onClick="javascript:updateModifiedByIframeSrc();popup_show('modifiedby','modifiedby_drag','modifiedby_exit', 'screen-corner', 120,20);hideIt();showPopupDiv('modifiedby','modifiedbybody');popupboxenable();">
		   <img src=" images/lookup.gif"  alt="Lookup modifiedBy" width="13" height="13" border="0"/></a>
	    </td>
     </tr>
      
	  <tr>
		 <td class="TDShade">&nbsp;</td>
		    <td class="TDShadeBlue">&nbsp;</td>
			<td class="TDShade"><f:message key="dispatchStatus"/>: </td>
			<td class="TDShadeBlue" colspan="2"><form:select cssClass="selectionBox" id="sel15" path="dispatchStatus.value" items="${icbfn:dropdown('staticDropdown',dispatchStatus)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="dispatchStatus.value" cssClass="error"/>
		 </td>       
      </tr>

      <tr>
		 <td class="TDShade"><f:message key="port"/>: </td>
			<td class="TDShadeBlue"> <form:select cssClass="selectionBox" id="sel7" path="port.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/>
			<form:input cssClass="inputBox" path="port.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="port.value" cssClass="error"/> </td>
			<td class="TDShade"><f:message key="svcLocation"/>: </td>
			<td class="TDShadeBlue" colspan="2"><form:select cssClass="selectionBox" id="sel8" path="svcLocation.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/>
			<form:input cssClass="inputBox" path="svcLocation.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="svcLocation.value" cssClass="error"/>
		</td>
      </tr>

		  <tr>
			  <td class="TDShade"><f:message key="companyId"/>: </td>
				<td class="TDShadeBlue">
				<form:select cssClass="selectionBox" id="sel9" path="companyId.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/>
				<form:input cssClass="inputBox" path="companyId.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="companyId.value" cssClass="error"/>
				</td>
				<td class="TDShade"><f:message key="company"/>: </td>
				<td class="TDShadeBlue" colspan="2">
				<form:select cssClass="selectionBox" id="sel10" path="company.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/>
				<form:input cssClass="inputBox" path="company.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="company.value" cssClass="error"/>
			  </td>
		 </tr>


	<tr>
	  <td class="TDShade"><f:message key="contactID"/>: </td>
			<td class="TDShadeBlue">
			<form:select cssClass="selectionBox" id="sel12" path="billingContactId.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/>
			<form:input cssClass="inputBox" path="billingContactId.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="billingContactId.value" cssClass="error"/>
			</td>
			<td class="TDShade"><f:message key="billingContact"/>: </td>
			<td class="TDShadeBlue" colspan="2">
			<form:select cssClass="selectionBox" id="sel15" path="billingContact.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/>
			<form:input cssClass="inputBox" path="billingContact.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="billingContact.value" cssClass="error"/>
	  </td>
	</tr>

		<tr>
			<td class="TDShade"><f:message key="schedulerId"/>: </td>
				<td class="TDShadeBlue">
				<form:select cssClass="selectionBox" id="sel13" path="schedulerId.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/>
				<form:input cssClass="inputBox" path="schedulerId.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="schedulerId.value" cssClass="error"/>
				</td>
				<td class="TDShade"><f:message key="scheduler"/>: </td>
				<td class="TDShadeBlue" colspan="2">
				<form:select cssClass="selectionBox" id="sel14" path="scheduler.operator" items="${icbfn:dropdown('staticDropdown',operator)}" itemLabel="name" itemValue="value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/>
				<form:input cssClass="inputBox" path="scheduler.value" onkeypress="if(event.keyCode==13) {checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');}"/><form:errors path="scheduler.value" cssClass="error"/>
			</td>
		</tr>

		<tr>
		  <td class="TDShade"><f:message key="numberofRows"/>: </td>
			<td class="TDShadeBlue">
			<form:input cssClass="inputBox" id="noOfRows" path="searchResults.value" /><form:errors path="searchResults.value" cssClass="error"/>
			</td>
			<td class="TDShade"> </td>
		  <td class="TDShadeBlue" colspan="2">
		</tr>

</table>
</div>

 <!-- search criteria content collapsable div End-->

<!-- Configure Columns content collapsable div-->


 <div id="log1">
  <table border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="margin-bottom:0px;" border="1" >
    <tr valign="center">
      <th  width=16 class="TDShade" style="background-image:url(images/arrowblubg.gif);"> 
        <div id="bluarrowright1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> 
          <a href="#" onClick="javascript:showOriginTable('origintable1','bluarrowdown1','bluarrowright1',1);highLightTabs('fbox');highLightColumns('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');deselectColumns('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');"> 
            <img src=" images/bluarrowright.gif" width="8" height="16" border="0" vspace="6"/></a> </div>
              <div id="bluarrowdown1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px ">
		     <a href="#" onClick="javascript:hideOriginTable('origintable1','bluarrowright1','bluarrowdown1',1);"> 
           <img src=" images/bluarrowdown.gif" width="16" height="8" border="0" vspace="6"/></a></div></th>
         <th colspan="5" class="TDShade"><f:message key="configureCols"/>
       </tr>
     </table>
</div>

<div id="origintable1" style="padding:0px; visibility:hidden; display:none;">
   <table border="0" cellpadding="0" cellspacing="0" colspan="4" class="mainApplTable" style="margin-top:0px; border-top:none" >
      <tr>
	      <td class="TDShade"  style="text-align:right"><f:message key="columnsHideandSort"/></td> 
	        <td class="TDShade" >&nbsp;</td>
		      <td  class="TDShade">&nbsp;</td>
			    <td  class="TDShade" >&nbsp;</td>
                   <td  class="TDShade" >&nbsp;</td>
                       <td class="TDShade" style="text-align:left"><f:message key="resultSortOrder"/></td>
		 	       <td  class="TDShade" >&nbsp;</td>
			    <td  class="TDShade" >&nbsp;</td>
			  <td  class="TDShade" >&nbsp;</td>
	 	   <td  class="TDShade" >&nbsp;</td>
		 <td  class="TDShade">&nbsp;</td>
	  </tr>

	<tr>

	    <icb:list var="confIds">
		<icb:item>${command.confId}</icb:item>
		</icb:list>

		<td class="TDShadeBlue" style="text-align:right">
		<form:select cssClass="multiSelectBox" id="fbox" path="hideAndSort"  multiple="true" size="10" items="${icbfn:dropdown('hideandSort', confIds)}" itemLabel="name" itemValue="value"/></td>
       
       	<td class="TDShadeBlue" style="text-align:left">
		<input type = "button" value="" class="upButton" id = "btnMoveUp" onclick = "goAscending('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');"> </br></br></br>&nbsp;&nbsp;</br>
		<input type = "button" value="" class="dwnButton" id = "btnMoveDown"  onclick = "goDescending('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');"></td>
		
		<td class="TDShadeBlue" style="text-align:right">
		<input type = "button"   name ="hide" value="Hide " class="fixLengthButton" id = "btnMoveRight" onclick = "hideSelected('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');"></br>
		<input type = "button" name="show"  value="Show" class="fixLengthButton" id = "btnMoveLeft"  onclick = "unHideSelected('fbox','dispbox');" disabled></td>

		<td class="TDShadeBlue" style="text-align:center">
        <input type = "button" name="add" value="&gt;&gt;" class="rightButton" onclick="CopyOption('fbox', 'tbox','dispbox');" ID="Button1"></br>
		<input type = "button" name="Delete" value="&lt;&lt;" class="leftButton" onclick="removeFromPack('tbox');" ID="Button2"></td>

		<td class="TDShadeBlue" style="text-align:left">
		<input type = "button" alt="It lets the column appear on all tabs" name="freeze" value="Freeze" class="fixLengthButton" onclick="freezeOrder('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');" ID="Button1"></br>
		<input type = "button" name="unFreeze" value="UnFreeze" class="fixLengthButton" onclick="unfreezeOrder('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');" ID="Button2" disabled></td>
 
		
		<td class="TDShadeBlue" style="text-align:left">
		<form:select cssClass="multiSelectBox" id="tbox" path="resultSortOrder"  multiple="true" size="10" items="${icbfn:dropdown('sortDropDown',confIds)}" itemLabel="name" itemValue="value"/>
		</td>
      
		<td class="TDShadeBlue" style="text-align:left:"><div style="visibility:hidden;display:none;">
		<form:select cssClass="multiSelectBox" id="freebox1" path="freezedList1"  multiple="true" size="10" items="${icbfn:dropdown('firstFreezeDropDown',confIds)}" itemLabel="name" itemValue="value"/>
		</div></td>

		

		<td class="TDShadeBlue" style="text-align:left:"><div style="visibility:hidden;display:none;">
		<form:select cssClass="inputBox" id="freebox2" path="freezedList2"  multiple="true" size="10" items="${icbfn:dropdown('secondFreezeDropDown',confIds)}" itemLabel="name" itemValue="value"/>
		</div></td>

         
		
		<td class="TDShadeBlue" style="text-align:left:"><div style="visibility:hidden;display:none;">
		<form:select cssClass="inputBox" id="freebox3" path="freezedList3"  multiple="true" size="10" items="${icbfn:dropdown('thirdFreezeDropDown',confIds)}" itemLabel="name" itemValue="value"/>
		</div></td>

        
		<td class="TDShadeBlue" style="text-align:left:"><div style="visibility:hidden;display:none;">
		<form:select cssClass="inputBox" id="freebox4" path="freezedList4"  multiple="true" size="10" items="${icbfn:dropdown('fourthFreezeDropDown',confIds)}" itemLabel="name" itemValue="value"/>
		</div></td>


		<td class="TDShadeBlue"  style="text-align:left;"><div style="visibility:hidden;display:none;">
		<form:select cssClass="inputBox" id="dispbox" path="displayOrder"  multiple="true" size="10" items="${icbfn:dropdown('hideDropDown',confIds)}" itemLabel="name" itemValue="value"/>
		</div></td>
		
		
	</tr>


	<tr colspan="4">
	  <table border="0" cellpadding="0" cellspacing="0" colspan="4" class="mainApplTable" style="margin-top:0px; border-top:none" >
	    <td class="TDShade" width="12%"><f:message key="configureName"/>:<span class="redstar">*</span></td>
	      <td class="TDShade" width="15%"> <form:input id="tmpNm" cssClass="inputBox" size="35" path="logConfigList.confListName" maxlength="128"/>
	    
	           </td>
	             <td class="TDShade" width="15%">
	               <input name="Button" type="button" class="button1" value="Create Configuration" style="border-width:1px;" onClick="createConf('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');"/>
	                </td>
					<td class="TDShade" style="text-align:left" width="15%">
						<form:checkbox id="access"  path="logConfigList.accessScope" value="logConfigList.accessScope"/>&nbsp;<f:message key="private"/>
						<form:checkbox id="defaultList"  path="logConfigList.defaultList" value="logConfigList.defaultList"/>&nbsp;<f:message key="default"/>
					</td>
	              <td class="TDShade" width="35%"><f:message key="searchConfiguration"/>:
	           <form:input cssClass="inputBox" id="sconf" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="confName" size="35" maxlength="128"/>
			  <form:errors path="confName" cssClass="redstar"/> 									
    	   <a href="#" onClick="javascript:searchConfigurationIframeSrc();popup_show('templatename', 'templatename_drag', 'templatename_exit', 'screen-corner', 120, 20);hideIt();popupboxenable();"><img src="images/lookup.gif" alt="lookup Template Name" width="13" height="13" border="0"></a></td>
	 </tr>
  </tr>




  </table>
</div>	 

<!-- Configure Columns content collapsable div End-->
 </td>
    </tr>
     </table>

	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">     
	  <tr>      
	    <td>
		  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">        
		    <tr>
              <td><input name="Search" type="button" class="button1" value="Search/Refresh" onclick="checkFromTO('fbox','tbox','dispbox','freebox1','freebox2','freebox3','freebox4');"/></td>
               <td style="text-align:right"></td>
             </tr>
           </table>
         </td>
      </tr>
    </table>
   <br>
 </div>

 <%-- <ajax:select
    baseUrl="business_unit.htm"
    source="businessUnit.value"
    target="branch.value"
    parameters="branch.businessUnit.name={businessUnit.value}"
    parser="new ResponseXmlParser()"
    /> --%>

  <ajax:autocomplete
  baseUrl="branch.htm"
  source="branch.value"
  target="branch.value"
  className="autocomplete"              
  parameters="branchName={branch.value},buName={businessUnit.value}"
  minimumCharacters="3"
 />

 <ajax:autocomplete 
          baseUrl="joborder.htm"
          source="createdBy.value"
          target="createdBy.value"
		  className="autocomplete"
          parameters="createdby={createdBy.value}"
          minimumCharacters="1" />


<ajax:autocomplete 
          baseUrl="joborder.htm"
          source="modifiedBy.value"
          target="modifiedBy.value"
		  className="autocomplete"
          parameters="modifiedby={modifiedBy.value}"
          minimumCharacters="1" />

<ajax:autocomplete 
          baseUrl="joborder.htm"
          source="contractDescription.value"
          target="contractDescription.value"
		  className="autocomplete"
          parameters="description={contractDescription.value}"
          minimumCharacters="3" />
  <ajax:autocomplete 
          baseUrl="joborder.htm"
          source="confName"
          target="confName"
		  className="autocomplete"
          parameters="confName={confName}"
		  postFunction="loadConfig"
          minimumCharacters="1" />
		 
<!-------------------------------------Search From Job Id Lookup START-------------------------------------------->
<div class="sample_popup" id="searchfromjobid" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="searchfromjobid_drag" style="width:750px;"> 
<img class="menu_form_exit"   id="searchfromjobid_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchJobId"/></div>
<div class="menu_form_body" id="searchfromjobidbody"   style="width:750px; height:530px;overflow-y:hidden;">
<iframe id="fromJobIds" align="left" style="padding:0px; height:530px;" width="100%" src="blank.html" 
scrolling="auto" name="frame1" allowtransparency="yes"></iframe></div></div>
<!---------------------------------------Search From Job Id Lookup END-------------------------------------------->
<!--------------------------------------- Search To Job Id Lookup Start------------------------------------------->
<div class="sample_popup" id="searchtojobid" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="searchtojobid_drag" style="width:750px; "> 
<img class="menu_form_exit"   id="searchtojobid_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchJobId"/></div>
<div class="menu_form_body" id="searchtojobidbody"   style="width:750px; height:530px;overflow-y:hidden;">
<iframe id="toJobIds"  align="left"	style="padding:0px; height:530px;" width="100%" src="blank.html" 
scrolling="auto" name="frame1" allowtransparency="yes"></iframe></div></div>
<!----------------------------------------Search To Job Id Lookup END--------------------------------------------->

<!---------------------------------------created By Lookup START------------------------------------------------->
<div class="sample_popup" id="createdby"  style="visibility: hidden; display: none;">
<div class="menu_form_header" id="createdby_drag" style="width:750px;">
<img class="menu_form_exit" id="createdby_exit" src="images/form_exit.png"/> &nbsp;&nbsp;&nbsp;<f:message key="searchUser" /></div>
<div class="menu_form_body" id="createdbybody" style="width:750px; height:530px;padding-left:4px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px"  width="100%" src="blank.html" 
 scrolling="auto" id="searchCreatedFr" name="searchCreatedFr" allowtransparency="yes"></iframe></div></div>
<!------------------------------------ created By Lookup END----------------------------------------------------->

<!---------------------------------------modified By Lookup START------------------------------------------------->
<div class="sample_popup" id="modifiedby"  style="visibility: hidden; display: none;">
<div class="menu_form_header" id="modifiedby_drag" style="width:750px;">
<img class="menu_form_exit" id="modifiedby_exit" src="images/form_exit.png"/> &nbsp;&nbsp;&nbsp;<f:message key="searchUser" /></div>
<div class="menu_form_body" id="modifiedbybody" style="width:750px; height:530px;padding-left:4px;overflow-y:hidden;">
<iframe align="left" frameborder="0" style="padding:0px;" height="530px"  width="100%" src="blank.html" 
 scrolling="auto" id="searchModifiedFr" name="searchModifiedFr" allowtransparency="yes"></iframe></div></div>
<!------------------------------------ modified By Lookup END----------------------------------------------------->

<!------------------------------------------------configuration Search Lookup ---------------------------------->
<div class="sample_popup" id="templatename" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="templatename_drag" style="width:750px;height:auto;"> <img class="menu_form_exit"   id="templatename_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchConfiguration"/></div>
<div class="menu_form_body" id="templatenamebody" style="width:750px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe id="configurationFr"  width="100%" height="530px" scrolling="auto" frameborder="0" allowtransparency="yes"  src="blank.html"></iframe></div>
</c:if></div>
<!-------------------------------------------configuration Search Lookup END ---------------------------------->
<!-- ---------------------------  Branch Code Lookup ------------------------------------------------- -->
<div class="sample_popup" id="jobbranchcode" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="jobbranchcode_drag" style="width:750px; "> 
<img class="menu_form_exit"   id="jobbranchcode_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchBranchCode"/></div>
<div class="menu_form_body" id="jobbranchcodebody" style="width:750px; height:550px;overflow-y:hidden;">    
<iframe align="left" frameborder="0" style="padding:10px;" height="540px;" width="100%"  scrolling="auto" id="searchBranchCodeFr" name="searchBranchCodeFr" allowtransparency="yes" src="blank.html"></iframe></div></div>
<!-- --------------------------------- Branch Code Lookup END ----------------------------------------- -->

<!--------------------------------------------TAB 1 CONTAINER END-------------------------------------------------> <!------------------------------------------- TAB 2 CONTAINER----------------------------------------------------->
<div id="tab2" class="innercontent1">
<%--<c:if test="${command.showAllFlag=='false'}">--%>
 <c:if test="${command.tabFlag=='main'}">
<table width="100%" cellpadding=0 cellspacing=0 class="mainApplTable">
<tbody>
<tr bgcolor=#ffffff>
<th width="65%"><f:message key="searchResultsmain"/></th>
<th width="15%" style="text-align:right"><a href="#"><img src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><%--<a href="#" onClick="showAll('${command.pagination.currentPageNum}');"><IMG SRC="images/icoshowall.gif" ALT="Show all columns" hspace="5" BORDER="0" align="absmiddle"></a>--%>
<a href="#"><IMG SRC="images/icorefresh.gif" ALT="Search/Refresh" WIDTH="14" HEIGHT="14" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;"></a>
<a href="#"><IMG SRC="images/icoemail.gif" ALT="Please save the changes(if any) & then send email" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;" onClick="submitmail(${fn:length(command.results)});"></a>
<a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" hspace="5" border="0" align="absmiddle" onClick="submitForm('main','${command.pagination.currentPageNum}');" /></a></th>
</tr>


<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" border="0">
<tr>
<td valign="top" width="125" style=" padding:0px;text-align:left">

<c:if test="${command.results != null}">
<tr>
<td colspan="0" style="padding:0px;">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" align="left">
<tr>
<td valign="top" style=" padding:0px;">


<div style="width:100%; vertical-align:top;">


<div style="width:11%; float:left;#DBE2F2 1px solid;background:url(images/intablehdrblubg2.gif) repeat-x;"">
<table width="98%" cellpadding="0" cellspacing="0" class="InnerApplTable" align="left" border="0">
<tr><th nowrap><a href="#start" onClick="sortByJobHeader('MH_COL1','main');" ><f:message key="jobId"/></a></th></tr>
<c:forEach items="${command.results}" var="job" varStatus="status">

 <tr><td align='CENTER' nowrap='nowrap' height="25" valign="middle"><a href="#" onClick="updateSession('${job.herfJobType}','${job.jobOrder.jobNumber}','${job.reqFrom}')" title="Transfer to Nomination" >${job.jobOrder.jobNumber}</a></td></tr> 
</c:forEach>
</table>
</div>

<div style="float:left;width:89%;" style="visibility: visible;overflow-x:scroll;height:auto;overflow-y:hidden;#DBE2F2 1px solid;">
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" frameborder="0" border="0" style="border-left-width:0px;#DBE2F2 1px solid;">
<tr>
 <c:forEach items="${command.mainColumns}" var="mcols" varStatus="status">
<th nowrap><a href="#start" onClick="sortByJobHeader('${mcols}','main');" ><f:message key="${mcols}"/></a><a name='${mcols}'></a></th>
 <c:if test="${mcols=='mH_Col13'}">
 <th nowrap>&nbsp;</th>
 </c:if>
</c:forEach>
<th><a href="#start"></a>&nbsp;</a></th>
  </tr>
 
   <icb:list var="maindispatchStatusDropDownItems" items="${icbfn:dropdown('staticDropdown',dispatchStatus)}" />
  <c:forEach items="${command.results}" var="job" varStatus="status">
<tr valign='center'>
<c:forEach items="${command.mainHeaderColumns}" var="mcols" varStatus="mstatus">
<c:choose>
<c:when test="${mcols=='MH_COL2'}">
  <td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="etadate${status.index}" disabled="${job.etaDateTimeFlag}" cssClass="inputBox" path="results[${status.index}].jobOrder.etaDate" size="10" maxlength="12"/>
  <a href="#" onClick="displayCalendar(document.forms[0].etadate${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>
  </td>
</c:when>

<c:when test="${mcols=='MH_COL3'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><form:input cssClass="inputBox" size="12" disabled="${job.etaDateTimeFlag}" path="results[${status.index}].etaTime"/>
</td>
</c:when>

<c:when test="${mcols=='MH_COL4'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <span>${job.jobOrder.etaTimeTz}</span>
</td>
</c:when>

<c:when test="${mcols=='MH_COL5'}">   
<td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.operation} </span></td> </span>
</td>
</c:when> 

<c:when test="${mcols=='MH_COL6'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;" 
onMouseOver='doTooltip(event,"${job.jobOrder.jobDescription}")' 
onMouseOut="hideTip()">${job.jobOrder.jobDescription}</a></span>
</td>
</c:when> 

<c:when test="${mcols=='MH_COL7'}">
<td nowrap='nowrap' height="25">
<form:select cssClass="selectionBox" id ="sel1"  path="results[${status.index}].jobLog.dispatchStatus" disabled="${job.invoiceFlag}" items="${maindispatchStatusDropDownItems}" itemLabel="name" itemValue="value"/> 
</td>
</c:when>

<c:when test="${mcols=='MH_COL8'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobType}</span></td>
</c:when>

<c:when test="${mcols=='MH_COL9'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobOrder.serviceLocation.city}</span></td>
</c:when>

<c:when test="${mcols=='MH_COL10'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <span>${job.jobOrder.serviceLocation.name}</span></td>
</c:when>

<c:when test="${mcols=='MH_COL11'}">  
<td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobOrder.vesselNames}</span></td>
</c:when>

<c:when test="${mcols=='MH_COL12'}">
      <td nowrap='nowrap' height="25" align='left' valign="middle" >  <span >${job.jobOrder.productNames}</span></td>
</c:when>

<c:when test="${mcols=='MH_COL13'}">
  <c:choose>
<c:when test="${job.jobContract.jobNumber!=null}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="notedetails${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].appendNote" size="20" maxlength="500"/></td> 
<td nowrap='nowrap' height="25" align='center' valign="middle" ><a href="#"><img src='images/icoeditviewnotes.gif' alt='Add Notes' title='Add Notes' border='0' onClick="submitNote('${status.index}','main','${command.pagination.currentPageNum}');"/></a></td>

</c:when>
<c:otherwise>
<td nowrap='nowrap' height="25" align='left' valign="middle">&nbsp;</td>
<td nowrap='nowrap' height="25" align='center' valign="middle">&nbsp;</td>

</c:otherwise>
</c:choose> 
</c:when>

<c:when test="${mcols=='MH_COL14'}">
<td nowrap='nowrap' height="25" align='left' valign="top"  ><span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;"
onMouseOver="doTooltip(event,'${job.addNote}')" onMouseOut="hideTip()">${job.addUpdatedNote}</a></span>
</td>

 </c:when>

<c:when test="${mcols=='MH_COL15'}">
<c:choose>
<c:when test="${job.jobContract.jobNumber!=null}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <span >${job.jobContract.custCode}</span></td>
</c:when>
<c:otherwise>
<td nowrap='nowrap' height="25" align='left' valign="middle" > <span >&nbsp;</span></td>
</c:otherwise>
</c:choose>
  </c:when> 

	<c:when test="${mcols=='MH_COL16'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" ><span>${job.jobContract.customer.name}</span></td>
	 </c:when>

      <c:when test="${mcols=='MH_COL17'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle"  > <span > ${job.jobContract.contactId}</span></td>
	 </c:when>

<c:when test="${mcols=='MH_COL18'}">
   <td nowrap='nowrap' height="25" align='left' valign="middle" > <span >${job.jobContract.contact.firstName} ${job.jobContract.contact.lastName}</span></td>
   
   </c:when>

 <c:when test="${mcols=='MH_COL19'}">
	  <td nowrap='nowrap' height="25" valign="middle">&nbsp;
	  <c:choose>
	  <c:when test="${job.jobContract != null && job.jobContract.id > 0}">
	  <input type='checkbox' id="mailCheck${status.index}" name="mailCheck${status.index}" value="${job.jobContract.id}" align='right'/>
	  </c:when>
	  <c:otherwise>
	  <input type='checkbox' id="mailCheck${status.index}" name="mailCheck${status.index}" value="${job.jobContract.id}" align='right' disabled/>
	  </c:otherwise>
	  </c:choose>
	  </td>
	  </c:when>
	

     <c:when test="${mcols=='MH_COL20'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle"  > <span >${job.jobContract.custRefNum}</span></td>
	</c:when>

<c:when test="${mcols=='MH_COL21'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle"> <span></span>${job.jobContract.invoiceValue5}</td> 
	</c:when>

   <c:when test="${mcols=='MH_COL22'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobOrder.shippingAgent.name}</span></td>
      </c:when>

   <c:when test="${mcols=='MH_COL23'}">
     <td nowrap='nowrap' height="25" align='left' valign="middle" > <span >${job.jobOrder.shippingAgentPhone}</span></td> 
	 </c:when>

	 <c:when test="${mcols=='MH_COL24'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobOrder.towingCompany.name}</span></td>
	</c:when>

	<c:when test="${mcols=='MH_COL25'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobOrder.towingCompanyPhone}</span></td>
	</c:when>

    <c:when test="${mcols=='MH_COL26'}">
    <td nowrap='nowrap' height="25" height="25" align='left' valign="middle" ><span><f:formatDate value="${job.jobOrder.jobFinishDate}" type="date" pattern="${command.dateFormat}"/> </span></td>
	</c:when>

	<c:when test="${mcols=='MH_COL27'}">
	 <td nowrap='nowrap' height="25" align='left' valign="middle" > ${job.jobContract.contract.description}</td></c:when>

	 <c:when test="${mcols=='MH_COL28'}">
	<td nowrap='nowrap' height="25" align='left' valign="middle" > ${job.contractStatus}</td>
	</c:when>

	<c:when test="${mcols=='MH_COL29'}">
	<td nowrap='nowrap' height="25" align='left' valign="middle" > ${job.jobOrder.createdByUserName}</td>
	</c:when>

	<c:when test="${mcols=='MH_COL30'}">
	<td nowrap='nowrap' height="25" align='left' valign="middle" > ${job.jobOrder.updatedByUserName}</td>
	</c:when>

	<c:when test="${mcols=='MH_COL31'}">
	<td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobStatus}</span></td>
	</c:when>
	<c:when test="${mcols=='MH_COL32'}">
	<td nowrap='nowrap' height="25" align='left' valign="top"  ><span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;"
	onMouseOver="doTooltip(event,'${job.customerNoteDetails}')" onMouseOut="hideTip()">${job.customerNote}</a></span>
	</td>
 	</c:when>

	<%-- START : #127441 --%>
	<c:when test="${mcols=='MH_COL33'}">
	<td nowrap='nowrap' height="25" align='left' valign="top" >
	<span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;"
	onMouseOver="doTooltip(event,'${job.cancelNoteDetails}')" onMouseOut="hideTip()">
		${job.cancelNote}</a></span>
	</td>
	</c:when>
	<%-- END : #127441 --%>
	<%-- Dispatch Tab Data start --%>

	<c:when test="${mcols=='DH_COL2'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].jobLog.coordinator"/></td>
</c:when>

<c:when test="${mcols=='DH_COL3'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].jobLog.inspector"/></td>
</c:when>

<%--<c:when test="${mcols=='DH_COL4'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><form:input cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="results[${status.index}].jobLog.dispatchTz"/>&nbsp;<a href="#"
onClick="javascript:updateTimeZoneIframeSrc(${status.index});popup_show('timezone${status.index}','timezone_drag${status.index}','timezone_exit${status.index}', 'screen-corner', 120,20);hideIt();showPopupDiv('timezone${status.index}','timezonebody${status.index}');popupboxenable();"><img src="images/lookup.gif" width="13" alt="Lookup Time Zone" height="13" border="0"/></a></td>
</c:when>--%>

<c:when test="${mcols=='DH_COL4'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"><span>${job.jobOrder.nominationTimeTz}</span>
</c:when>

<c:when test="${mcols=='DH_COL5'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><a name="a">
<form:input id="inspectorNotifyDt${status.index}" cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].jobLog.inspectorNotifyDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].inspectorNotifyDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${mcols=='DH_COL6'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  >
<form:input cssClass="inputBox" disabled="${job.invoiceFlag}" maxlength="11" path="results[${status.index}].inspectorNotifyTime"/></td>
</c:when>

<c:when test="${mcols=='DH_COL7'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="inspectorArriveDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.inspectorArriveDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].inspectorArriveDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${mcols=='DH_COL8'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].inspectorArriveTime"/></td>
</c:when>

<c:when test="${mcols=='DH_COL9'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="arriveDt${status.index}" cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].jobLog.arriveDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].arriveDt${status.index},'${command.dateFormat}',this)" style="cursor:hand;"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${mcols=='DH_COL10'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].arriveTime"/></td>
</c:when>

<c:when test="${mcols=='DH_COL11'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="dockDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.dockDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].dockDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${mcols=='DH_COL12'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].dockTime"/></td>
</c:when>

<c:when test="${mcols=='DH_COL13'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="hoseOnDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.hoseOnDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].hoseOnDt${status.index},'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17" border="0" style="cursor:hand;"/></a></td>
</c:when>

<c:when test="${mcols=='DH_COL14'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].hoseOnTime"/></td>
</c:when>

<c:when test="${mcols=='DH_COL15'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="estStartDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.estStartDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].estStartDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${mcols=='DH_COL16'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].estStartTime"/></td>
</c:when>

<c:when test="${mcols=='DH_COL17'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="commenceDt${status.index}" cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].jobLog.commenceDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].commenceDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${mcols=='DH_COL18'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].commenceTime"/></td>
</c:when>

<c:when test="${mcols=='DH_COL19'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="delayDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.delayDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].delayDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${mcols=='DH_COL20'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].delayTime"/></td>
</c:when>

<c:when test="${mcols=='DH_COL21'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><form:input id="delayEndDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.delayEndDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].delayEndDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${mcols=='DH_COL22'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].delayEndTime"/></td>
</c:when>

<c:when test="${mcols=='DH_COL23'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="estCompleteDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.estCompleteDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].estCompleteDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${mcols=='DH_COL24'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].estCompleteTime"/></td>
</c:when>

<c:when test="${mcols=='DH_COL25'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="completeDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.completeDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].completeDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${mcols=='DH_COL26'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].completeTime"/></td>
</c:when>

<c:when test="${mcols=='DH_COL27'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><a name="hoseOffDt${status.index}"></a><form:input disabled="${job.invoiceFlag}" id="hoseOffDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.hoseOffDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].hoseOffDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${mcols=='DH_COL28'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].hoseOffTime"/></td>
</c:when>

<c:when test="${mcols=='DH_COL29'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="releaseDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.releaseDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].releaseDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${mcols=='DH_COL30'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].releaseTime"/></td>
</c:when>

<c:when test="${mcols=='DH_COL31'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="summaryDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.summaryDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].summaryDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>
<%-- dispatch tab data end --%>
<%-- billing tab data start --%>

<c:when test="${mcols=='BH_COL2'}">
<td nowrap='nowrap' height="25" align='left' >${job.jobContract.billingContact.id}</td>
 </c:when> 
 <c:when test="${mcols=='BH_COL3'}"> 
<td nowrap='nowrap' height="25" align='left' > ${job.jobContract.billingContact.firstName} ${job.jobContract.billingContact.lastName}</td>
 </c:when> 
<c:when test="${mcols=='BH_COL4'}">  
    <td nowrap='nowrap' height="25" align='left' > ${job.jobContract.billStatus}</td>
	</c:when> 
     
<c:when test="${mcols=='BH_COL5'}"> 
	   <td nowrap='nowrap' height="25" align='left' > ${job.latestJobContractInvoice.invoice}</td>
	 </c:when> 
 <c:when test="${mcols=='BH_COL6'}"> 
      <td nowrap='nowrap' height="25" align='right' >${job.jobContract.invoiceAmt}</td>
	  </c:when> 
	 <c:when test="${mcols=='BH_COL7'}">  
      <td nowrap='nowrap' height="25" align='left' ><f:formatDate value="${job.jobContract.invoiceDate}" type="date" pattern="${command.dateFormat}" /></td>
 </c:when>  
	 <c:when test="${mcols=='BH_COL8'}"> 
      <td nowrap='nowrap' height="25" align='left' ><f:formatDate value="${job.latestJobContractInvoice.generatedDateTime}" type="date" pattern="${command.dateFormat}" /></td>
	 </c:when>  
  <c:when test="${mcols=='BH_COL9'}">  
      <td nowrap='nowrap' height="25" align='left' >${job.jobContract.creditInd}</td>
	 </c:when> 
	 <c:when test="${mcols=='BH_COL10'}"> 
      <td nowrap='nowrap' height="25" align='left' ><f:formatDate value="${job.jobOrder.reOpenDate}" type="date" pattern="${command.dateFormat}"/></td>
	 </c:when>
<%-- billing tab data end --%>
<%-- process tab data start --%>
<%--<c:when test="${mcols=='PH_COL2'}">
	<td nowrap='nowrap' height="25" align='left'  >
      <form:input cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="results[${status.index}].jobLog.processTz"/>&nbsp;<a href="#"
onClick="javascript:updateProcessTimeZoneIframeSrc(${status.index});popup_show('ptimezone${status.index}','ptimezone_drag${status.index}','ptimezone_exit${status.index}', 'screen-corner', 120,20);hideIt();showPopupDiv('ptimezone${status.index}','ptimezonebody${status.index}');popupboxenable();"><img src="images/lookup.gif" width="13" height="13" alt="Lookup Time Zone" border="0" align="absmiddle"/></a></td>
 </c:when>--%>

<c:when test="${mcols=='PH_COL2'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"><span>${job.jobOrder.nominationTimeTz}</span>
</c:when>

<c:when test="${mcols=='PH_COL3'}">
      <td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="sampleReceiveDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.sampleReceiveDt" size="12" maxlength="10"/>
<a href="#" onClick="displayCalendar(document.forms[0].sampleReceiveDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${mcols=='PH_COL4'}">
  <td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].sampleReceiveTime"/></td>
  </c:when>
 <c:when test="${mcols=='PH_COL5'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="prelimReportDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.prelimReportDt" size="12" maxlength="10"/>
<a href="#" onClick="displayCalendar(document.forms[0].prelimReportDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>
    <c:when test="${mcols=='PH_COL6'}">  
  <td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].prelimReportTime"/></td>
  </c:when>
   <c:when test="${mcols=='PH_COL7'}">   
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="sampleShipDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.sampleShipDt" size="12" maxlength="10"/>
<a href="#" onClick="displayCalendar(document.forms[0].sampleShipDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>
   <c:when test="${mcols=='PH_COL8'}"> 
  <td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].sampleShipTime"/></td>
  </c:when>
<c:when test="${mcols=='PH_COL9'}">   
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="finalReportDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.finalReportDt" size="12" maxlength="10"/>
<a href="#" onClick="displayCalendar(document.forms[0].finalReportDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>
<c:when test="${mcols=='PH_COL10'}">
  <td nowrap='nowrap' height="25" align='left' valign="middle" ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].finalReportTime"/></td>
  </c:when>
  <c:when test="${mcols=='PH_COL11'}">    
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="distributeDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.distributeDt" size="12" maxlength="10"/>
<a href="#" onClick="displayCalendar(document.forms[0].distributeDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>
  <c:when test="${mcols=='PH_COL12'}">    
  <td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].distributeTime"/></td>
  </c:when>
<%-- process tab data end --%>
</c:choose>
	</c:forEach> 
	<td>&nbsp;</td>
    </tr>
<ajax:autocomplete 
baseUrl="joborder.htm"
source="results[${status.index}].jobLog.dispatchTz"
target="results[${status.index}].jobLog.dispatchTz"
className="autocomplete"
parameters="dTimeZone={results[${status.index}].jobLog.dispatchTz}"
minimumCharacters="1" /> 

<SCRIPT LANGUAGE="JavaScript">
location.href="#${command.columnFlag}"	
</SCRIPT>

</c:forEach>
</table>
</div>
<br style="clear:both;" />
</div>	

</td>
</tr>
</table>
</td>
</tr>
</c:if>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
<tr>
<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>
<!-- START : #119240 -->
<%-- <table cellspacing="0" cellpadding="0" border="0">
    <tr>
      <td><a href="#start" onClick="prevSearch(pageNumber)"><IMG SRC="images/navfirst.gif" ALT="First Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
      <td><a href="#start" onClick="previousSearch('${command.pagination.currentPageNum}')"><IMG SRC="images/navprevious.gif" ALT="Previous Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
      
      <td><select name="goto" size="1" class="selectionBox" id="sel3" style="width:84px;" onchange="submitSearch(this.value)" >
      <option value="Go to page"><c:out value="Go to page" />
      <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
      <option value="${page.pageNumber}"><c:out value="${page.name}" />
      </c:forEach>
      </select></td>
      
      <td><a href="#start" onClick="nextSearch('${command.pagination.currentPageNum}')"><IMG SRC="images/navnext.gif" ALT="Next Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
      <td><a href="#start" onClick="lastSearch('${command.pagination.totalRecord}','${command.pagination.numInPage}')"><IMG SRC="images/navlast.gif" ALT="Last Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
  </tr>
</table> --%>

<%@ include file="../common/pagination.jsp" %>
<!-- END : #119240 -->
</td>

<td style="text-align:right"><f:message key="numberofRows"/>:&nbsp${command.noOfJobs} &nbsp <a href="#"><img src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><%--<a href="#" onClick="showAll('${command.pagination.currentPageNum}');"><IMG SRC="images/icoshowall.gif" ALT="Show all columns" hspace="5" BORDER="0" align="absmiddle"></a>--%><a href="#"><IMG SRC="images/icorefresh.gif" ALT="Search/Refresh" WIDTH="14" HEIGHT="14" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;"></a><a href="#"><IMG SRC="images/icoemail.gif" ALT="Please save the changes(if any) & then send email" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;" onClick="submitmail(${fn:length(command.results)});"></a><a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" hspace="5" border="0" align="absmiddle" onClick="submitForm('main','${command.pagination.currentPageNum}');"/></a></td>
</td>
</tr>
</table>
</tr>
</tbody>
</table>
</c:if> 
<%--</c:if>--%>
</div>
<!----------------------------------------------TAB 2 CONTAINER END----------------------------------------------->
<!-------------------------------------------------TAB 3 CONTAINER------------------------------------------------>
<div id="tab3" class="innercontent1" >
<%--<c:if test="${command.showAllFlag=='false'}">--%>
   <c:if test="${command.tabFlag=='dispatch'}">
<table width="100%" cellpadding=0 cellspacing=0 class="mainApplTable">
<tbody>
<tr bgcolor=#ffffff>
<th width="65%"><f:message key="searchResultsdispatch"/></th>
<th width="15%" style="text-align:right"><a href="#"><img src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><%--<a href="#" onClick="showAll('${command.pagination.currentPageNum}');"><IMG SRC="images/icoshowall.gif" ALT="Show all columns" hspace="5" BORDER="0" align="absmiddle"></a>--%><a href="#"><IMG SRC="images/icorefresh.gif" ALT="Search/Refresh" WIDTH="14" HEIGHT="14" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;"><IMG SRC="images/icoemail.gif" ALT="Please save the changes(if any) & then Send email" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;"  onClick="submitmail(${fn:length(command.results)});"><img src="images/icosave.gif" alt="Save" width="14" height="14" hspace="5" border="0" align="absmiddle" onClick="submitForm('dispatch','${command.pagination.currentPageNum}');" /></a></th>
</tr>
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
<tr>
<td valign="top" width="125" style=" padding:0px;text-align:left">

<c:if test="${command.results != null}">
<tr>
<td colspan="0" style="padding:0px;">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" align="left">
<tr>
<td valign="top" style=" padding:0px;">

<div style="width:100%; vertical-align:top;">

<div style="width:11%; float:left;#DBE2F2 1px solid;background:url(images/intablehdrblubg2.gif) repeat-x;"">

<table width="98%" cellpadding="0" cellspacing="0" class="InnerApplTable" align="left" border="0">
<tr><th nowrap><a href="#start" onClick="sortByJobHeader('DH_COL1','main');"><f:message key="jobId"/></a></th></tr>
<c:forEach items="${command.results}" var="job" varStatus="status">
<tr><td align='CENTER' nowrap='nowrap' height="25" valign="middle"><a href="#" onClick="updateSession('${job.herfJobType}','${job.jobOrder.jobNumber}','${job.reqFrom}')" title="Transfer to Nomination" >${job.jobOrder.jobNumber}</a></td></tr>
</c:forEach>
</table>
</div>

<div style="width:89%; float:left;"  style="visibility: visible;overflow-x:scroll;overflow-y:hidden;#DBE2F2 1px solid;">
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" frameborder="0" border="0" style="border-left-width:0px;#DBE2F2 1px solid;">
 <tr>
 <c:forEach items="${command.dispatchColumns}" var="dcols" varStatus="status">
<th nowrap><a href="#start" onClick="sortByJobHeader('${dcols}','dispatch');" ><f:message key="${dcols}"/></a><a name='${dcols}'></a></th>
 <c:if test="${dcols=='mH_Col13'}">
 <th nowrap>&nbsp;</th>
 </c:if>
</c:forEach>
<th><a href="#start"></a>&nbsp;</a></th>
 </tr>
  <icb:list var="dispStatusDropDownItems" items="${icbfn:dropdown('staticDropdown',dispatchStatus)}" />
<c:forEach items="${command.results}" var="job" varStatus="status">
<tr valign='center'> 

<c:forEach items="${command.dispatchHeaderColumns}" var="dcols" varStatus="dstatus">
<c:choose>
	<%-- Dispatch Tab Data start --%>

	<c:when test="${dcols=='DH_COL2'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].jobLog.coordinator"/></td>
</c:when>

<c:when test="${dcols=='DH_COL3'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].jobLog.inspector"/></td>
</c:when>

<%--<c:when test="${dcols=='DH_COL4'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="results[${status.index}].jobLog.dispatchTz"/>&nbsp;<a href="#"
onClick="javascript:updateTimeZoneIframeSrc(${status.index});popup_show('timezone${status.index}','timezone_drag${status.index}','timezone_exit${status.index}', 'screen-corner', 120,20);hideIt();showPopupDiv('timezone${status.index}','timezonebody${status.index}');popupboxenable();"><img src="images/lookup.gif" width="13" alt="Lookup Time Zone" height="13" border="0"/></a></td>
</c:when>--%>

<c:when test="${dcols=='DH_COL4'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"><span>${job.jobOrder.nominationTimeTz}</span>
</c:when>

<c:when test="${dcols=='DH_COL5'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><a name="a">
<form:input id="inspectorNotifyDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.inspectorNotifyDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].inspectorNotifyDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${dcols=='DH_COL6'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  >
<form:input cssClass="inputBox" disabled="${job.invoiceFlag}" maxlength="11" path="results[${status.index}].inspectorNotifyTime"/></td>
</c:when>

<c:when test="${dcols=='DH_COL7'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="inspectorArriveDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.inspectorArriveDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].inspectorArriveDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${dcols=='DH_COL8'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><form:input disabled="${job.invoiceFlag}" cssClass="inputBox"  path="results[${status.index}].inspectorArriveTime"/></td>
</c:when>

<c:when test="${dcols=='DH_COL9'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="arriveDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.arriveDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].arriveDt${status.index},'${command.dateFormat}',this)" style="cursor:hand;"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${dcols=='DH_COL10'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input disabled="${job.invoiceFlag}" cssClass="inputBox"  path="results[${status.index}].arriveTime"/></td>
</c:when>

<c:when test="${dcols=='DH_COL11'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="dockDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.dockDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].dockDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${dcols=='DH_COL12'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].dockTime"/></td>
</c:when>

<c:when test="${dcols=='DH_COL13'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="hoseOnDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.hoseOnDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].hoseOnDt${status.index},'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17" border="0" style="cursor:hand;"/></a></td>
</c:when>

<c:when test="${dcols=='DH_COL14'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].hoseOnTime"/></td>
</c:when>

<c:when test="${dcols=='DH_COL15'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="estStartDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.estStartDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].estStartDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${dcols=='DH_COL16'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><form:input disabled="${job.invoiceFlag}" cssClass="inputBox"  path="results[${status.index}].estStartTime"/></td>
</c:when>

<c:when test="${dcols=='DH_COL17'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="commenceDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.commenceDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].commenceDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${dcols=='DH_COL18'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input disabled="${job.invoiceFlag}" cssClass="inputBox"  path="results[${status.index}].commenceTime"/></td>
</c:when>

<c:when test="${dcols=='DH_COL19'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input disabled="${job.invoiceFlag}" id="delayDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.delayDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].delayDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${dcols=='DH_COL20'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><form:input disabled="${job.invoiceFlag}" cssClass="inputBox"  path="results[${status.index}].delayTime"/></td>
</c:when>

<c:when test="${dcols=='DH_COL21'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><form:input disabled="${job.invoiceFlag}" id="delayEndDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.delayEndDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].delayEndDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${dcols=='DH_COL22'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input disabled="${job.invoiceFlag}" cssClass="inputBox"  path="results[${status.index}].delayEndTime"/></td>
</c:when>

<c:when test="${dcols=='DH_COL23'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="estCompleteDt${status.index}" cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].jobLog.estCompleteDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].estCompleteDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${dcols=='DH_COL24'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input disabled="${job.invoiceFlag}" cssClass="inputBox"  path="results[${status.index}].estCompleteTime"/></td>
</c:when>

<c:when test="${dcols=='DH_COL25'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input disabled="${job.invoiceFlag}" id="completeDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.completeDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].completeDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${dcols=='DH_COL26'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input disabled="${job.invoiceFlag}" cssClass="inputBox"  path="results[${status.index}].completeTime"/></td>
</c:when>

<c:when test="${dcols=='DH_COL27'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><a name="hoseOffDt${status.index}"></a><form:input id="hoseOffDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.hoseOffDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].hoseOffDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${dcols=='DH_COL28'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].hoseOffTime"/></td>
</c:when>

<c:when test="${dcols=='DH_COL29'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="releaseDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.releaseDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].releaseDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${dcols=='DH_COL30'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}"  path="results[${status.index}].releaseTime"/></td>
</c:when>

<c:when test="${dcols=='DH_COL31'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="summaryDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.summaryDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].summaryDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>
<%-- dispatch tab data end --%>
<%-- main tab data start --%>
<c:when test="${dcols=='MH_COL2'}">
  <td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="etadate${status.index}" disabled="${job.etaDateTimeFlag}" cssClass="inputBox" path="results[${status.index}].jobOrder.etaDate" size="10" maxlength="12"/>
  <a href="#" onClick="displayCalendar(document.forms[0].etadate${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>
  </td>
</c:when>


<c:when test="${dcols=='MH_COL3'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" size="12" disabled="${job.etaDateTimeFlag}" path="results[${status.index}].etaTime"/>
</td>
</c:when>

<c:when test="${dcols=='MH_COL4'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <span>${job.jobOrder.etaTimeTz}</span>
</td>
</c:when>

<c:when test="${dcols=='MH_COL5'}">   
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><span >${job.operation} </span></td> </span>
</td>
</c:when> 

<c:when test="${dcols=='MH_COL6'}">
<td nowrap='nowrap' height="25" align='left' valign="top"  ><span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;" 
onMouseOver='doTooltip(event,"${job.jobOrder.jobDescription}")' 
onMouseOut="hideTip()">${job.jobOrder.jobDescription}</a></span>
</td>
</c:when> 
<c:when test="${dcols=='MH_COL7'}">
<td nowrap='nowrap' height="25">
<form:select cssClass="selectionBox" id ="sel1"  path="results[${status.index}].jobLog.dispatchStatus" disabled="${job.invoiceFlag}" items="${dispStatusDropDownItems}" itemLabel="name" itemValue="value"/> 
</td>
</c:when>

<c:when test="${dcols=='MH_COL8'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><span >${job.jobType}</span></td>
</c:when>

<c:when test="${dcols=='MH_COL9'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobOrder.serviceLocation.city}</span></td>
</c:when>

<c:when test="${dcols=='MH_COL10'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><span>${job.jobOrder.serviceLocation.name}</span></td>
</c:when>

<c:when test="${dcols=='MH_COL11'}">  
<td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobOrder.vesselNames}</span></td>
</c:when>

<c:when test="${dcols=='MH_COL12'}">
      <td nowrap='nowrap' height="25" align='left' valign="middle" >  <span >${job.jobOrder.productNames}</span></td>
</c:when>

<c:when test="${dcols=='MH_COL13'}">
  <c:choose>
<c:when test="${job.jobContract.jobNumber!=null}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="notedetails${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].appendNote" size="20" maxlength="128"/></td> 
<td nowrap='nowrap' height="25" align='center' valign="middle" ><a href="#"><img src='images/icoeditviewnotes.gif' alt='Edit/View Notes' title='Edit/View Notes' border='0' onClick="submitNote('${status.index}','dispatch','${command.pagination.currentPageNum}');"/></a></td>

</c:when>
<c:otherwise>
<td nowrap='nowrap' height="25" align='left' valign="middle">&nbsp;</td>
<td nowrap='nowrap' height="25" align='center' valign="middle"  >&nbsp;</td>

</c:otherwise>
</c:choose> 
</c:when>

<c:when test="${dcols=='MH_COL14'}">
<td nowrap='nowrap' height="25" align='left' valign="top"  ><span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;"
onMouseOver="doTooltip(event,'${job.addNote}')" 
onMouseOut="hideTip()">${job.addUpdatedNote}</a></span>
</td>
 </c:when>

<c:when test="${dcols=='MH_COL15'}">
<c:choose>
<c:when test="${job.jobContract.jobNumber!=null}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <span >${job.jobContract.custCode}</span></td>
</c:when>
<c:otherwise>
<td nowrap='nowrap' height="25" align='left' valign="middle" > <span >&nbsp;</span></td>
</c:otherwise>
</c:choose>
  </c:when> 

	<c:when test="${dcols=='MH_COL16'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" ><span>${job.jobContract.customer.name}</span></td>
	 </c:when>

      <c:when test="${dcols=='MH_COL17'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle"  > <span > ${job.jobContract.contactId}</span></td>
	 </c:when>

<c:when test="${dcols=='MH_COL18'}">
   <td nowrap='nowrap' height="25" align='left' valign="middle"  > <span >${job.jobContract.contact.firstName} ${job.jobContract.contact.lastName}</span></td>
   
   </c:when>

 <c:when test="${dcols=='MH_COL19'}">
	  <td nowrap='nowrap' height="25" valign="middle">&nbsp;
	  <c:choose>
	  <c:when test="${job.jobContract != null && job.jobContract.id > 0}">
	  <input type='checkbox' id="mailCheck${status.index}" name="mailCheck${status.index}" value="${job.jobContract.id}" align='right'/>
	  </c:when>
	  <c:otherwise>
	  <input type='checkbox' id="mailCheck${status.index}" name="mailCheck${status.index}" value="${job.jobContract.id}" align='right' disabled/>
	  </c:otherwise>
	  </c:choose>
	  </td>
	</c:when> 

     <c:when test="${dcols=='MH_COL20'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle"  > <span >${job.jobContract.custRefNum}</span></td>
	</c:when>

<c:when test="${dcols=='MH_COL21'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" ><span></span>${job.jobContract.invoiceValue5}</td> 
	</c:when>

   <c:when test="${dcols=='MH_COL22'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobOrder.shippingAgent.name}</span></td>
      </c:when>

   <c:when test="${dcols=='MH_COL23'}">
     <td nowrap='nowrap' height="25" align='left' valign="middle" > <span >${job.jobOrder.shippingAgentPhone}</span></td> 
	 </c:when>

	 <c:when test="${dcols=='MH_COL24'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobOrder.towingCompany.name}</span></td>
	</c:when>

	<c:when test="${dcols=='MH_COL25'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobOrder.towingCompanyPhone}</span></td>
	</c:when>

    <c:when test="${dcols=='MH_COL26'}">
    <td nowrap='nowrap' height="25"  align='left' valign="middle" ><span><f:formatDate value="${job.jobOrder.jobFinishDate}" type="date" pattern="${command.dateFormat}"/> </span></td>
	</c:when>

	<c:when test="${dcols=='MH_COL27'}">
	 <td nowrap='nowrap' height="25" align='left' valign="middle"  > ${job.jobContract.contract.description}</td></c:when>

	 <c:when test="${dcols=='MH_COL28'}">
	<td nowrap='nowrap' height="25" align='left' valign="middle"  > ${job.contractStatus}</td>
	</c:when>

	<c:when test="${dcols=='MH_COL29'}">
	<td nowrap='nowrap' height="25" align='left' valign="middle"  > ${job.jobOrder.createdByUserName}</td>
	</c:when>

	<c:when test="${dcols=='MH_COL30'}">
	<td nowrap='nowrap' height="25" align='left' valign="middle"  > ${job.jobOrder.updatedByUserName}</td>
	</c:when>
	<c:when test="${dcols=='MH_COL31'}">
	<td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobStatus}</span></td>
	</c:when>
	<c:when test="${mcols=='MH_COL32'}">
	<td nowrap='nowrap' height="25" align='left' valign="top"  ><span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;"
	onMouseOver="doTooltip(event,'${job.customerNoteDetails}')" onMouseOut="hideTip()">${job.customerNote}</a></span>
	</td>
 	</c:when>
	
	<%-- START : #127441 --%>
	<c:when test="${dcols=='MH_COL33'}">
	<td nowrap='nowrap' height="25" align='left' valign="top" >
	<span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;"
	onMouseOver="doTooltip(event,'${job.cancelNoteDetails}')" onMouseOut="hideTip()">
		${job.cancelNote}</a></span>
	</td>
	</c:when>
	<%-- END : #127441 --%>

<%-- main tab data end --%>
<%-- billing tab data start --%>

<c:when test="${dcols=='BH_COL2'}">
<td nowrap='nowrap' height="25" align='left' >${job.jobContract.billingContact.id}</td>
 </c:when> 
 <c:when test="${dcols=='BH_COL3'}"> 
<td nowrap='nowrap' height="25" align='left' > ${job.jobContract.billingContact.firstName} ${job.jobContract.billingContact.lastName}</td>
 </c:when> 
<c:when test="${dcols=='BH_COL4'}">  
    <td nowrap='nowrap' height="25" align='left' > ${job.jobContract.billStatus}</td>
	</c:when> 
     
<c:when test="${dcols=='BH_COL5'}"> 
	   <td nowrap='nowrap' height="25" align='left'  > ${job.latestJobContractInvoice.invoice}</td>
	 </c:when> 
 <c:when test="${dcols=='BH_COL6'}"> 
      <td nowrap='nowrap' height="25" align='right' >${job.jobContract.invoiceAmt}</td>
	  </c:when> 
	 <c:when test="${dcols=='BH_COL7'}">  
      <td nowrap='nowrap' height="25" align='left' ><f:formatDate value="${job.jobContract.invoiceDate}" type="date" pattern="${command.dateFormat}" /></td>
 </c:when>  
	 <c:when test="${dcols=='BH_COL8'}"> 
      <td nowrap='nowrap' height="25" align='left' ><f:formatDate value="${job.latestJobContractInvoice.generatedDateTime}" type="date" pattern="${command.dateFormat}" /></td>
	 </c:when>  
  <c:when test="${dcols=='BH_COL9'}">  
      <td nowrap='nowrap' height="25" align='left' >${job.jobContract.creditInd}</td>
	 </c:when> 
	 <c:when test="${dcols=='BH_COL10'}"> 
      <td nowrap='nowrap' height="25"  align='left' ><f:formatDate value="${job.jobOrder.reOpenDate}" type="date" pattern="${command.dateFormat}"/></td>
	 </c:when>
<%-- billing tab data end --%>
<%-- process tab data start --%>
<%--<c:when test="${dcols=='PH_COL2'}">
	<td nowrap='nowrap' height="25"  align='left'  >
      <form:input cssClass="inputBox" disabled="${job.invoiceFlag}" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="results[${status.index}].jobLog.processTz"/>&nbsp;<a href="#"
onClick="javascript:updateProcessTimeZoneIframeSrc(${status.index});popup_show('ptimezone${status.index}','ptimezone_drag${status.index}','ptimezone_exit${status.index}', 'screen-corner', 120,20);hideIt();showPopupDiv('ptimezone${status.index}','ptimezonebody${status.index}');popupboxenable();"><img src="images/lookup.gif" width="13" height="13" alt="Lookup Time Zone" border="0" align="absmiddle"/></a></td>
 </c:when>--%>
 <c:when test="${dcols=='PH_COL2'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"><span>${job.jobOrder.nominationTimeTz}</span>
</c:when>
<c:when test="${dcols=='PH_COL3'}">
      <td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input disabled="${job.invoiceFlag}" id="sampleReceiveDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.sampleReceiveDt" size="12" maxlength="10"/>
<a href="#" onClick="displayCalendar(document.forms[0].sampleReceiveDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${dcols=='PH_COL4'}">
  <td nowrap='nowrap' height="25" align='left' valign="middle" ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].sampleReceiveTime"/></td>
  </c:when>
 <c:when test="${dcols=='PH_COL5'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input disabled="${job.invoiceFlag}" id="prelimReportDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.prelimReportDt" size="12" maxlength="10"/>
<a href="#" onClick="displayCalendar(document.forms[0].prelimReportDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>
    <c:when test="${dcols=='PH_COL6'}">  
  <td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].prelimReportTime"/></td>
  </c:when>
   <c:when test="${dcols=='PH_COL7'}">   
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="sampleShipDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.sampleShipDt" size="12" maxlength="10"/>
<a href="#" onClick="displayCalendar(document.forms[0].sampleShipDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>
   <c:when test="${dcols=='PH_COL8'}"> 
  <td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].sampleShipTime"/></td>
  </c:when>
<c:when test="${dcols=='PH_COL9'}">   
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input disabled="${job.invoiceFlag}" id="finalReportDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.finalReportDt" size="12" maxlength="10"/>
<a href="#" onClick="displayCalendar(document.forms[0].finalReportDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>
<c:when test="${dcols=='PH_COL10'}">
  <td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].finalReportTime"/></td>
  </c:when>
  <c:when test="${dcols=='PH_COL11'}">    
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="distributeDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.distributeDt" size="12" maxlength="10"/>
<a href="#" onClick="displayCalendar(document.forms[0].distributeDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>
  <c:when test="${dcols=='PH_COL12'}">    
  <td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].distributeTime"/></td>
  </c:when>
 </c:choose>

</c:forEach>

<%--<ajax:autocomplete 
baseUrl="joborder.htm"
source="results[${status.index}].jobLog.dispatchTz"
target="results[${status.index}].jobLog.dispatchTz"
className="autocomplete"
parameters="dTimeZone={results[${status.index}].jobLog.dispatchTz}"
minimumCharacters="1" />--%>


<SCRIPT LANGUAGE="JavaScript">
location.href="#${command.columnFlag}"	
</SCRIPT>
</c:forEach>
</tr>
</table>
</div>
<br style="clear:both;" />
</div>
<%--
<c:forEach items="${command.results}" var="job" varStatus="status">
<!--------------------------------------------DispatchTimezone Lookup START--------------------------------------->
<div class="sample_popup" id="timezone${status.index}"  style="visibility: hidden; display:none;">
<div class="menu_form_header" id="timezone_drag${status.index}" style="width:750px; ">
<img class="menu_form_exit" id="timezone_exit${status.index}" src=" images/form_exit.png" />&nbsp;&nbsp;&nbsp; <f:message key="selectTimeZone"/></div>
<div class="menu_form_body" id="timezonebody${status.index}" style="width:750px; height:530px;overflow-y:hidden;">
<iframe align="middle"  frameborder="0" style="padding:1px; height:530px;" width="100%" id="frame4${status.index}" name="frame4${status.index}" allowtransparency="yes" src="blank.html"></iframe></div></div>
<!--------------------------------------------Dispatch Timezone  Lookup END--------------------------------------->

</c:forEach>
--%>
</td>
</tr>
</table>
</td>
</tr>
</c:if>
</td>
</tr>
</table>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
<tr>
<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>

<td>
<!-- START : #119240 -->
<%-- <table cellspacing="0" cellpadding="0" border="0">

  <tr>
          <td><a href="#" onClick="prevSearch(pageNumber)"><IMG SRC="images/navfirst.gif" ALT="First Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
          <td><a href="#" onClick="previousSearch('${command.pagination.currentPageNum}')"><IMG SRC="images/navprevious.gif" ALT="Previous Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
          
          <td><select name="goto" size="1" class="selectionBox" id="sel3" style="width:84px;" onchange="submitSearch(this.value)" >
          <option value="Go to page"><c:out value="Go to page" />
          <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
          <option value="${page.pageNumber}"><c:out value="${page.name}" />
          </c:forEach>
          </select></td>
                    
          <td><a href="#" onClick="nextSearch('${command.pagination.currentPageNum}')"><IMG SRC="images/navnext.gif" ALT="Next Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
          <td><a href="#" onClick="lastSearch('${command.pagination.totalRecord}','${command.pagination.numInPage}')"><IMG  SRC="images/navlast.gif" ALT="Last Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
  </tr>

</table> --%>

<%@ include file="../common/pagination.jsp" %>
<!-- END : #119240 -->
</td>
<td style="text-align:right"><f:message key="numberofRows"/>:&nbsp${command.noOfJobs} &nbsp<a href="#"><img src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><%--<a href="#" onClick="showAll('${command.pagination.currentPageNum}');"><IMG SRC="images/icoshowall.gif" ALT="Show all columns" hspace="5" BORDER="0" align="absmiddle"></a>--%><a href="#"><IMG SRC="images/icorefresh.gif" ALT="Search/Refresh" WIDTH="14" HEIGHT="14" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;"></a><a href="#"><IMG SRC="images/icoemail.gif" ALT="Please save the changes(if any) & then Send email" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;" onClick="submitmail(${fn:length(command.results)});"></a><a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" hspace="5" border="0" align="absmiddle" onClick="submitForm('dispatch','${command.pagination.currentPageNum}');"/></a></td>
</tr>

</table></td>
</tr>
</table>
</c:if>
<%--</c:if>--%>
</div>
<!--------------------------------------------TAB 3 CONTAINER END-------------------------------------------------> <!----------------------------------------------TAB 4 CONTAINER-------------------------------------------------->
  <div id="tab4" class="innercontent1" >
  <%-- <c:if test="${command.showAllFlag=='false'}">--%>
  <c:if test="${command.tabFlag=='billing'}">
            <table width="100%" cellpadding=0 cellspacing=0 class="mainApplTable">
              <tbody>
                <tr bgcolor=#ffffff>
                  <th width="65%"><f:message key="searchResultsbilling"/></th>

<th width="15%" style="text-align:right"><a href="#"><img src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><%--<a href="#" onClick="showAll('${command.pagination.currentPageNum}');"><IMG SRC="images/icoshowall.gif" ALT="Show all columns" hspace="5" BORDER="0" align="absmiddle"></a>--%><a href="#"><IMG SRC="images/icorefresh.gif" ALT="Search/Refresh" WIDTH="14" HEIGHT="14" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;"><IMG SRC="images/icoemail.gif" ALT="Please save the changes(if any) & then Send email" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;"  onClick="submitmail(${fn:length(command.results)});"><img src="images/icosave.gif" alt="Save" width="14" height="14" hspace="5" border="0" align="absmiddle" onClick="submitForm('billing','${command.pagination.currentPageNum}');" /></a></th>
       
 </tr>
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
<tr>
<td valign="top" width="125" style=" padding:0px;text-align:left">

<c:if test="${command.results != null}">
<tr>
<td colspan="0" style="padding:0px;">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" align="left">
<tr>
<td valign="top" style=" padding:0px;">

<div style="width:100%; vertical-align:top;">

<div style="width:11%; float:left;#DBE2F2 1px solid;background:url(images/intablehdrblubg2.gif) repeat-x;"">

<table width="98%" cellpadding="0" cellspacing="0" class="InnerApplTable" align="left" border="0">
<tr><th nowrap><a href="#start" onClick="sortByJobHeader('BH_COL1','main');" ><f:message key="jobId"/></a></th></tr>
<c:forEach items="${command.results}" var="job" varStatus="status">
<tr><td align='CENTER' nowrap='nowrap' height="25" valign="middle"><a href="#" onClick="updateSession('${job.herfJobType}','${job.jobOrder.jobNumber}','${job.reqFrom}')" title="Transfer to Nomination" >${job.jobOrder.jobNumber}</a></td></tr>
</c:forEach>
</table>
</div>

<div style="float:left;width:89%;" style="visibility: visible;overflow-x:scroll;height:auto;overflow-y:hidden;#DBE2F2 1px solid;">
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" frameborder="0" border="0" style="border-left-width:0px;#DBE2F2 1px solid;">
 <tr>
 <c:forEach items="${command.billingColumns}" var="bcols" varStatus="status">
<th nowrap><a href="#start" onClick="sortByJobHeader('${bcols}','billing');" ><f:message key="${bcols}"/></a><a name='${bcols}'></a></th>
 <c:if test="${bcols=='mH_Col13'}">
 <th nowrap>&nbsp;</th>
 </c:if>
</c:forEach> 
<th><a href="#start"></a>&nbsp;</a></th>
  </tr>
   <icb:list var="billdisStatusDropDownItems" items="${icbfn:dropdown('staticDropdown',dispatchStatus)}" />
 <c:forEach items="${command.results}" var="job" varStatus="status">
<tr valign='center'>
 <c:forEach items="${command.billingHeaderColumns}" var="bcols" varStatus="bstatus"> 
 <c:choose>
 <%-- billing tab data start --%>

<c:when test="${bcols=='BH_COL2'}">
<td nowrap='nowrap' height="25" align='left' >${job.jobContract.billingContact.id}</td>
 </c:when> 
 <c:when test="${bcols=='BH_COL3'}"> 
<td nowrap='nowrap' height="25" align='left' > ${job.jobContract.billingContact.firstName} ${job.jobContract.billingContact.lastName}</td>
 </c:when> 
<c:when test="${bcols=='BH_COL4'}">  
    <td nowrap='nowrap' height="25" align='left' > ${job.jobContract.billStatus}</td>
	</c:when> 
     
<c:when test="${bcols=='BH_COL5'}"> 
	   <td nowrap='nowrap' height="25" align='left'  > ${job.latestJobContractInvoice.invoice}</td>
	 </c:when> 
 <c:when test="${bcols=='BH_COL6'}"> 
      <td nowrap='nowrap' height="25" align='right' >${job.jobContract.invoiceAmt}</td>
	  </c:when> 
	 <c:when test="${bcols=='BH_COL7'}">  
      <td nowrap='nowrap' height="25" align='left' ><f:formatDate value="${job.jobContract.invoiceDate}" type="date" pattern="${command.dateFormat}" /></td>
 </c:when>  
	 <c:when test="${bcols=='BH_COL8'}"> 
      <td nowrap='nowrap' height="25" align='left' ><f:formatDate value="${job.latestJobContractInvoice.generatedDateTime}" type="date" pattern="${command.dateFormat}"/></td>
	 </c:when>  
  <c:when test="${bcols=='BH_COL9'}">  
      <td nowrap='nowrap' height="25" align='left' >${job.jobContract.creditInd}</td>
	 </c:when> 
	 <c:when test="${bcols=='BH_COL10'}"> 
      <td nowrap='nowrap' height="25" align='left' ><f:formatDate value="${job.jobOrder.reOpenDate}" type="date" pattern="${command.dateFormat}"/></td>
	 </c:when>
<%-- billing tab data end --%>
<%-- Dispatch Tab Data start --%>

	<c:when test="${bcols=='DH_COL2'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].jobLog.coordinator"/></td>
</c:when>

<c:when test="${bcols=='DH_COL3'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].jobLog.inspector"/></td>
</c:when>

<%--<c:when test="${bcols=='DH_COL4'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="results[${status.index}].jobLog.dispatchTz"/>&nbsp;<a href="#"
onClick="javascript:updateTimeZoneIframeSrc(${status.index});popup_show('timezone${status.index}','timezone_drag${status.index}','timezone_exit${status.index}', 'screen-corner', 120,20);hideIt();showPopupDiv('timezone${status.index}','timezonebody${status.index}');popupboxenable();"><img src="images/lookup.gif" width="13" alt="Lookup Time Zone" height="13" border="0"/></a></td>
</c:when>--%>

<c:when test="${bcols=='DH_COL4'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"><span>${job.jobOrder.nominationTimeTz}</span>
</c:when>

<c:when test="${bcols=='DH_COL5'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><a name="a">
<form:input id="inspectorNotifyDt${status.index}" cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].jobLog.inspectorNotifyDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].inspectorNotifyDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${bcols=='DH_COL6'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  >
<form:input cssClass="inputBox" disabled="${job.invoiceFlag}" maxlength="11" path="results[${status.index}].inspectorNotifyTime"/></td>
</c:when>

<c:when test="${bcols=='DH_COL7'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="inspectorArriveDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.inspectorArriveDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].inspectorArriveDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${bcols=='DH_COL8'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].inspectorArriveTime"/></td>
</c:when>

<c:when test="${bcols=='DH_COL9'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="arriveDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.arriveDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].arriveDt${status.index},'${command.dateFormat}',this)" style="cursor:hand;"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${bcols=='DH_COL10'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].arriveTime"/></td>
</c:when>

<c:when test="${bcols=='DH_COL11'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="dockDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.dockDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].dockDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${bcols=='DH_COL12'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].dockTime"/></td>
</c:when>

<c:when test="${bcols=='DH_COL13'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="hoseOnDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.hoseOnDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].hoseOnDt${status.index},'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17" border="0" style="cursor:hand;"/></a></td>
</c:when>

<c:when test="${bcols=='DH_COL14'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].hoseOnTime"/></td>
</c:when>

<c:when test="${bcols=='DH_COL15'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="estStartDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.estStartDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].estStartDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${bcols=='DH_COL16'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].estStartTime"/></td>
</c:when>

<c:when test="${bcols=='DH_COL17'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="commenceDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.commenceDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].commenceDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${bcols=='DH_COL18'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input disabled="${job.invoiceFlag}" cssClass="inputBox"  path="results[${status.index}].commenceTime"/></td>
</c:when>

<c:when test="${bcols=='DH_COL19'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input disabled="${job.invoiceFlag}" id="delayDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.delayDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].delayDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${bcols=='DH_COL20'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].delayTime"/></td>
</c:when>

<c:when test="${bcols=='DH_COL21'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><form:input disabled="${job.invoiceFlag}" id="delayEndDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.delayEndDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].delayEndDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${bcols=='DH_COL22'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].delayEndTime"/></td>
</c:when>

<c:when test="${bcols=='DH_COL23'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="estCompleteDt${status.index}"disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.estCompleteDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].estCompleteDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${bcols=='DH_COL24'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].estCompleteTime"/></td>
</c:when>

<c:when test="${bcols=='DH_COL25'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="completeDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.completeDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].completeDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${bcols=='DH_COL26'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].completeTime"/></td>
</c:when>

<c:when test="${bcols=='DH_COL27'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><a name="hoseOffDt${status.index}"></a><form:input disabled="${job.invoiceFlag}" id="hoseOffDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.hoseOffDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].hoseOffDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${bcols=='DH_COL28'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].hoseOffTime"/></td>
</c:when>

<c:when test="${bcols=='DH_COL29'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="releaseDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.releaseDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].releaseDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${bcols=='DH_COL30'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].releaseTime"/></td>
</c:when>

<c:when test="${bcols=='DH_COL31'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="summaryDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.summaryDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].summaryDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>
<%-- dispatch tab data end --%>
<%-- process tab data start --%>
<%--<c:when test="${bcols=='PH_COL2'}">
	<td nowrap='nowrap' height="25"  align='left' >
      <form:input cssClass="inputBox" disabled="${job.invoiceFlag}" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="results[${status.index}].jobLog.processTz"/>&nbsp;<a href="#"
onClick="javascript:updateProcessTimeZoneIframeSrc(${status.index});popup_show('ptimezone${status.index}','ptimezone_drag${status.index}','ptimezone_exit${status.index}', 'screen-corner', 120,20);hideIt();showPopupDiv('ptimezone${status.index}','ptimezonebody${status.index}');popupboxenable();"><img src="images/lookup.gif" width="13" height="13" alt="Lookup Time Zone" border="0" align="absmiddle"/></a></td>
 </c:when>--%>


<c:when test="${bcols=='PH_COL2'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"><span>${job.jobOrder.nominationTimeTz}</span>
</c:when>


<c:when test="${bcols=='PH_COL3'}">
      <td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input disabled="${job.invoiceFlag}" id="sampleReceiveDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.sampleReceiveDt" size="12" maxlength="10"/>
<a href="#" onClick="displayCalendar(document.forms[0].sampleReceiveDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${bcols=='PH_COL4'}">
  <td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].sampleReceiveTime"/></td>
  </c:when>
 <c:when test="${bcols=='PH_COL5'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input disabled="${job.invoiceFlag}" id="prelimReportDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.prelimReportDt" size="12" maxlength="10"/>
<a href="#" onClick="displayCalendar(document.forms[0].prelimReportDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>
    <c:when test="${bcols=='PH_COL6'}">  
  <td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input disabled="${job.invoiceFlag}" cssClass="inputBox"  path="results[${status.index}].prelimReportTime"/></td>
  </c:when>
   <c:when test="${bcols=='PH_COL7'}">   
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input disabled="${job.invoiceFlag}" id="sampleShipDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.sampleShipDt" size="12" maxlength="10"/>
<a href="#" onClick="displayCalendar(document.forms[0].sampleShipDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>
   <c:when test="${bcols=='PH_COL8'}"> 
  <td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input disabled="${job.invoiceFlag}" cssClass="inputBox"  path="results[${status.index}].sampleShipTime"/></td>
  </c:when>
<c:when test="${bcols=='PH_COL9'}">   
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input disabled="${job.invoiceFlag}" id="finalReportDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.finalReportDt" size="12" maxlength="10"/>
<a href="#" onClick="displayCalendar(document.forms[0].finalReportDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>
<c:when test="${bcols=='PH_COL10'}">
  <td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].finalReportTime"/></td>
  </c:when>
  <c:when test="${bcols=='PH_COL11'}">    
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input disabled="${job.invoiceFlag}" id="distributeDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.distributeDt" size="12" maxlength="10"/>
<a href="#" onClick="displayCalendar(document.forms[0].distributeDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>
  <c:when test="${bcols=='PH_COL12'}">    
  <td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input disabled="${job.invoiceFlag}" cssClass="inputBox"  path="results[${status.index}].distributeTime"/></td>
  </c:when>
   <%-- process tab data end --%>
 <%-- main tab data start --%>
<c:when test="${bcols=='MH_COL2'}">
  <td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="etadate${status.index}" disabled="${job.etaDateTimeFlag}" cssClass="inputBox" path="results[${status.index}].jobOrder.etaDate" size="10" maxlength="12"/>
  <a href="#" onClick="displayCalendar(document.forms[0].etadate${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>
  </td>
</c:when>

<c:when test="${bcols=='MH_COL3'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" size="12" disabled="${job.etaDateTimeFlag}" path="results[${status.index}].etaTime"/>
</td>
</c:when>

<c:when test="${bcols=='MH_COL4'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <span>${job.jobOrder.etaTimeTz}</span>
</td>
</c:when>

<c:when test="${bcols=='MH_COL5'}">   
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><span >${job.operation} </span></td> </span>
</td>
</c:when> 

<c:when test="${bcols=='MH_COL6'}">
<td nowrap='nowrap' height="25" align='left' valign="top"  ><span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;" 
onMouseOver='doTooltip(event,"${job.jobOrder.jobDescription}")' 
onMouseOut="hideTip()">${job.jobOrder.jobDescription}</a></span>
</td>
</c:when> 

<c:when test="${bcols=='MH_COL7'}">
<td nowrap='nowrap' height="25">
<form:select cssClass="selectionBox" id ="sel1"  path="results[${status.index}].jobLog.dispatchStatus" disabled="${job.invoiceFlag}" items="${billdisStatusDropDownItems}" itemLabel="name" itemValue="value"/> 
</td>
</c:when>

<c:when test="${bcols=='MH_COL8'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><span >${job.jobType}</span></td>
</c:when>

<c:when test="${bcols=='MH_COL9'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobOrder.serviceLocation.city}</span></td>
</c:when>

<c:when test="${bcols=='MH_COL10'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"> <span>${job.jobOrder.serviceLocation.name}</span></td>
</c:when>

<c:when test="${bcols=='MH_COL11'}">  
<td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobOrder.vesselNames}</span></td>
</c:when>

<c:when test="${bcols=='MH_COL12'}">
      <td nowrap='nowrap' height="25" align='left' valign="middle" >  <span >${job.jobOrder.productNames}</span></td>
</c:when>

<c:when test="${bcols=='MH_COL13'}">
  <c:choose>
<c:when test="${job.jobContract.jobNumber!=null}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="notedetails${status.index}" cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].appendNote" size="20" maxlength="128"/></td> 
<td nowrap='nowrap' height="25" align='center' valign="middle"  ><a href="#"><img src='images/icoeditviewnotes.gif' alt='Edit/View Notes' title='Edit/View Notes' border='0' onClick="submitNote('${status.index}','billing','${command.pagination.currentPageNum}');"/></a></td>

</c:when>
<c:otherwise>
<td nowrap='nowrap' height="25" align='left' valign="middle">&nbsp;</td>
<td nowrap='nowrap' height="25" align='center' valign="middle"  >&nbsp;</td>

</c:otherwise>
</c:choose> 
</c:when>

<c:when test="${bcols=='MH_COL14'}">
<td nowrap='nowrap' height="25" align='left' valign="top"  ><span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;"
onMouseOver="doTooltip(event,'${job.addNote}')" 
onMouseOut="hideTip()">${job.addUpdatedNote}</a></span>
</td>
 </c:when>

<c:when test="${bcols=='MH_COL15'}">
<c:choose>
<c:when test="${job.jobContract.jobNumber!=null}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <span >${job.jobContract.custCode}</span></td>
</c:when>
<c:otherwise>
<td nowrap='nowrap' height="25" align='left' valign="middle" > <span >&nbsp;</span></td>
</c:otherwise>
</c:choose>
  </c:when> 

	<c:when test="${bcols=='MH_COL16'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" ><span>${job.jobContract.customer.name}</span></td>
	 </c:when>

      <c:when test="${bcols=='MH_COL17'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle"  > <span > ${job.jobContract.contactId}</span></td>
	 </c:when>

<c:when test="${bcols=='MH_COL18'}">
   <td nowrap='nowrap' height="25" align='left' valign="middle"  > <span >${job.jobContract.contact.firstName} ${job.jobContract.contact.lastName}</span></td>
   
   </c:when>

 <c:when test="${bcols=='MH_COL19'}">
	  <td nowrap='nowrap' height="25" valign="middle">&nbsp;
	  <c:choose>
	  <c:when test="${job.jobContract != null && job.jobContract.id > 0}">
	  <input type='checkbox' id="mailCheck${status.index}" name="mailCheck${status.index}" value="${job.jobContract.id}" align='right'/>
	  </c:when>
	  <c:otherwise>
	  <input type='checkbox' id="mailCheck${status.index}" name="mailCheck${status.index}" value="${job.jobContract.id}" align='right' disabled/>
	  </c:otherwise>
	  </c:choose>
	  </td>
	</c:when> 

     <c:when test="${bcols=='MH_COL20'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle"  > <span >${job.jobContract.custRefNum}</span></td>
	</c:when>

<c:when test="${bcols=='MH_COL21'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" ><span></span>${job.jobContract.invoiceValue5}</td> 
	</c:when>

   <c:when test="${bcols=='MH_COL22'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobOrder.shippingAgent.name}</span></td>
      </c:when>

   <c:when test="${bcols=='MH_COL23'}">
     <td nowrap='nowrap' height="25" align='left' valign="middle" > <span >${job.jobOrder.shippingAgentPhone}</span></td> 
	 </c:when>

	 <c:when test="${bcols=='MH_COL24'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobOrder.towingCompany.name}</span></td>
	</c:when>

	<c:when test="${bcols=='MH_COL25'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobOrder.towingCompanyPhone}</span></td>
	</c:when>

    <c:when test="${bcols=='MH_COL26'}">
    <td nowrap='nowrap' height="25"  align='left' valign="middle" ><span><f:formatDate value="${job.jobOrder.jobFinishDate}" type="date" pattern="${command.dateFormat}"/> </span></td>
	</c:when>

	<c:when test="${bcols=='MH_COL27'}">
	 <td nowrap='nowrap' height="25" align='left' valign="middle"  > ${job.jobContract.contract.description}</td></c:when>

	 <c:when test="${bcols=='MH_COL28'}">
	<td nowrap='nowrap' height="25" align='left' valign="middle"  > ${job.contractStatus}</td>
	</c:when>

	<c:when test="${bcols=='MH_COL29'}">
	<td nowrap='nowrap' height="25" align='left' valign="middle"  > ${job.jobOrder.createdByUserName}</td>
	</c:when>

	<c:when test="${bcols=='MH_COL30'}">
	<td nowrap='nowrap' height="25" align='left' valign="middle"  > ${job.jobOrder.updatedByUserName}</td>
	</c:when>
	<c:when test="${bcols=='MH_COL31'}">
	<td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobStatus}</span></td>
	</c:when>
	<c:when test="${mcols=='MH_COL32'}">
	<td nowrap='nowrap' height="25" align='left' valign="top"  ><span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;"
	  onMouseOver="doTooltip(event,'${job.customerNoteDetails}')" onMouseOut="hideTip()">${job.customerNote}</a></span>
	</td>
 	</c:when>
	<%-- START : #127441 --%>
	<c:when test="${bcols=='MH_COL33'}">
	<td nowrap='nowrap' height="25" align='left' valign="top" >
	<span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;"
	onMouseOver="doTooltip(event,'${job.cancelNoteDetails}')" onMouseOut="hideTip()">
		${job.cancelNote}</a></span>
	</td>
	</c:when>
	<%-- END : #127441 --%>
 <%-- main tab data end --%>
 </c:choose>
	  </c:forEach>  
      </tr>

<SCRIPT LANGUAGE="JavaScript">
location.href="#${command.columnFlag}"	
</SCRIPT>

  </c:forEach>

 </table>
</div>

<br style="clear:both;" />
</div>

</td>
</tr>
</table>
</c:if>

</td>
</tr>
</table>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
<tr>
<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>

<td>
<!-- START : #119240 -->
<%-- <table cellspacing="0" cellpadding="0" border="0">

    <tr>
          <td><a href="#" onClick="prevSearch(pageNumber)"><IMG SRC="images/navfirst.gif" ALT="First Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
          <td><a href="#" onClick="previousSearch('${command.pagination.currentPageNum}')"><IMG SRC="images/navprevious.gif" ALT="Previous Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
          
          <td><select name="goto" size="1" class="selectionBox" id="sel3" style="width:84px;" onchange="submitSearch(this.value)" >
          <option value="Go to page"><c:out value="Go to page" />
          <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
          <option value="${page.pageNumber}"><c:out value="${page.name}" />
          </c:forEach>
          </select></td>
                    
          <td><a href="#" onClick="nextSearch('${command.pagination.currentPageNum}')"><IMG SRC="images/navnext.gif" ALT="Next Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
          <td><a href="#" onClick="lastSearch('${command.pagination.totalRecord}','${command.pagination.numInPage}')"><IMG  SRC="images/navlast.gif" ALT="Last Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
  </tr>

</table> --%>

<%@ include file="../common/pagination.jsp" %>
<!-- END : #119240 -->
</td>
<td style="text-align:right"><f:message key="numberofRows"/>:&nbsp${command.noOfJobs} &nbsp<a href="#"><img src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><%--<a href="#" onClick="showAll('${command.pagination.currentPageNum}');"><IMG SRC="images/icoshowall.gif" ALT="Show all columns" hspace="5" BORDER="0" align="absmiddle"></a>--%><a href="#"><IMG SRC="images/icorefresh.gif" ALT="Search/Refresh" WIDTH="14" HEIGHT="14" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;"></a><a href="#"><IMG SRC="images/icoemail.gif" ALT="Please save the changes(if any) & then Send email" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;" onClick="submitmail(${fn:length(command.results)});"></a><a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" hspace="5" border="0" align="absmiddle" onClick="submitForm('billing','${command.pagination.currentPageNum}');"/></a></td>

</tr>
</table></td>
</tr>
</table>
</c:if>  
<%--</c:if>--%>
</div>
<!-----------------------------------------------TAB 4 CONTAINER END---------------------------------------------->
<!-------------------------------------------------TAB 5 CONTAINER------------------------------------------------>
          <div id="tab5" class="innercontent1" >
		 <%--  <c:if test="${command.showAllFlag=='false'}">--%>
  <c:if test="${command.tabFlag=='processlog'}">
            <table width="100%" cellpadding=0 cellspacing=0 class="mainApplTable">
              <tbody>
<tr bgcolor=#ffffff>
  <th width="65%"><f:message key="searchResultsplog"/></th>
      <th width="15%" style="text-align:right"><a href="#"><img src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><%--<a href="#" onClick="showAll('${command.pagination.currentPageNum}');"><IMG SRC="images/icoshowall.gif" ALT="Show all columns" hspace="5" BORDER="0" align="absmiddle"></a>--%><a href="#"><IMG SRC="images/icorefresh.gif" ALT="Search/Refresh" WIDTH="14" HEIGHT="14" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;"><IMG SRC="images/icoemail.gif" ALT="Please save the changes(if any) & then Send email" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;"  onClick="submitmail(${fn:length(command.results)});"><img src="images/icosave.gif" alt="Save" width="14" height="14" hspace="5" border="0" align="absmiddle" onClick="submitForm('processlog','${command.pagination.currentPageNum}');" /></a></th>
  </tr>
  <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
    <tr>
<td valign="top" width="125" style=" padding:0px;text-align:left">
<c:if test="${command.results!= null}">
<tr>
<td colspan="0" style="padding:0px;">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" align="left">
<tr>
<td valign="top" style=" padding:0px;">

<div style="width:100%; vertical-align:top;background:url(images/intablehdrblubg2.gif) repeat-x;"">

<div style="width:11%; float:left;#DBE2F2 1px solid;">

<table width="98%" cellpadding="0" cellspacing="0" class="InnerApplTable" align="left" border="0">
<tr><th nowrap><a href="#start" onClick="sortByJobHeader('PH_COL1','main');" ><f:message key="jobId"/></a></th></tr>
<c:forEach items="${command.results}" var="job" varStatus="status">
<tr><td align='CENTER' nowrap='nowrap' height="25" valign="middle"><a href="#" onClick="updateSession('${job.herfJobType}','${job.jobOrder.jobNumber}','${job.reqFrom}')" title="Transfer to Nomination" >${job.jobOrder.jobNumber}</a></td></tr>
</c:forEach>
</table>
</div>

<div style="float:left;width:89%;" style="visibility: visible;overflow-x:scroll;height:auto;overflow-y:hidden;#DBE2F2 1px solid;">
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" frameborder="0" border="0" style="border-left-width:0px;#DBE2F2 1px solid;">
<tr>
 <c:forEach items="${command.processColumns}" var="pcols" varStatus="status">
<th nowrap><a href="#start" onClick="sortByJobHeader('${pcols}','processlog');" ><f:message key="${pcols}"/></a><a name='${pcols}'></a></th>
 <c:if test="${pcols=='mH_Col13'}">
 <th nowrap>&nbsp;</th>
 </c:if>
</c:forEach> 
 <th><a href="#start"></a>&nbsp;</a></th>
  </tr>
<c:forEach items="${command.results}" var="job" varStatus="status">
    <tr valign='center'>
	 <icb:list var="processdisStatusDropDownItems" items="${icbfn:dropdown('staticDropdown',dispatchStatus)}" />
<c:forEach items="${command.processHeaderColumns}" var="pcols" varStatus="pstatus">
 <c:choose>
 <%-- process tab data start --%>

<%--<c:when test="${pcols=='PH_COL2'}">
	<td nowrap='nowrap' height="25"  align='left' >
      <form:input disabled="${job.invoiceFlag}" cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="results[${status.index}].jobLog.processTz"/>&nbsp;<a href="#"
onClick="javascript:updateProcessTimeZoneIframeSrc(${status.index});popup_show('ptimezone${status.index}','ptimezone_drag${status.index}','ptimezone_exit${status.index}', 'screen-corner', 120,20);hideIt();showPopupDiv('ptimezone${status.index}','ptimezonebody${status.index}');popupboxenable();"><img src="images/lookup.gif" width="13" height="13" alt="Lookup Time Zone" border="0" align="absmiddle"/></a></td>
 </c:when>--%>

 <c:when test="${pcols=='PH_COL2'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"><span>${job.jobOrder.nominationTimeTz}</span>
</c:when>


<c:when test="${pcols=='PH_COL3'}">
      <td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input disabled="${job.invoiceFlag}" id="sampleReceiveDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.sampleReceiveDt" size="12" maxlength="10"/>
<a href="#" onClick="displayCalendar(document.forms[0].sampleReceiveDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${pcols=='PH_COL4'}">
  <td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input disabled="${job.invoiceFlag}" cssClass="inputBox"  path="results[${status.index}].sampleReceiveTime"/></td>
  </c:when>
 <c:when test="${pcols=='PH_COL5'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input disabled="${job.invoiceFlag}" id="prelimReportDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.prelimReportDt" size="12" maxlength="10"/>
<a href="#" onClick="displayCalendar(document.forms[0].prelimReportDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>
    <c:when test="${pcols=='PH_COL6'}">  
  <td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].prelimReportTime"/></td>
  </c:when>
   <c:when test="${pcols=='PH_COL7'}">   
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="sampleShipDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.sampleShipDt" size="12" maxlength="10"/>
<a href="#" onClick="displayCalendar(document.forms[0].sampleShipDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>
   <c:when test="${pcols=='PH_COL8'}"> 
  <td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].sampleShipTime"/></td>
  </c:when>
<c:when test="${pcols=='PH_COL9'}">   
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="finalReportDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.finalReportDt" size="12" maxlength="10"/>
<a href="#" onClick="displayCalendar(document.forms[0].finalReportDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>
<c:when test="${pcols=='PH_COL10'}">
  <td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].finalReportTime"/></td>
  </c:when>
  <c:when test="${pcols=='PH_COL11'}">    
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input disabled="${job.invoiceFlag}" id="distributeDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.distributeDt" size="12" maxlength="10"/>
<a href="#" onClick="displayCalendar(document.forms[0].distributeDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>
  <c:when test="${pcols=='PH_COL12'}">    
  <td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].distributeTime"/></td>
  </c:when>
	<%-- process Tab Data end --%>
<%-- billing tab data start --%>

<c:when test="${pcols=='BH_COL2'}">
<td nowrap='nowrap' height="25" align='left' >${job.jobContract.billingContact.id}</td>
 </c:when> 
 <c:when test="${pcols=='BH_COL3'}"> 
<td nowrap='nowrap' height="25" align='left' > ${job.jobContract.billingContact.firstName} ${job.jobContract.billingContact.lastName}</td>
 </c:when> 
<c:when test="${pcols=='BH_COL4'}">  
    <td nowrap='nowrap' height="25" align='left' > ${job.jobContract.billStatus}</td>
	</c:when> 
     
<c:when test="${pcols=='BH_COL5'}"> 
	   <td nowrap='nowrap' height="25" align='left'  > ${job.latestJobContractInvoice.invoice}</td>
	 </c:when> 
 <c:when test="${pcols=='BH_COL6'}"> 
      <td nowrap='nowrap' height="25" align='right' >${job.jobContract.invoiceAmt}</td>
	  </c:when> 
	 <c:when test="${pcols=='BH_COL7'}">  
      <td nowrap='nowrap' height="25" align='left' ><f:formatDate value="${job.jobContract.invoiceDate}" type="date" pattern="${command.dateFormat}" /></td>
 </c:when>  
	 <c:when test="${pcols=='BH_COL8'}"> 
      <td nowrap='nowrap' height="25" align='left' ><f:formatDate value="${job.latestJobContractInvoice.generatedDateTime}" type="date" pattern="${command.dateFormat}" /></td>
	 </c:when>  
  <c:when test="${pcols=='BH_COL9'}">  
      <td nowrap='nowrap' height="25" align='left' >${job.jobContract.creditInd}</td>
	 </c:when> 
	 <c:when test="${pcols=='BH_COL10'}"> 
      <td nowrap='nowrap' height="25"  align='left' ><f:formatDate value="${job.jobOrder.reOpenDate}" type="date" pattern="${command.dateFormat}"/></td>
	 </c:when>
<%-- billing tab data end --%>
	<%-- Dispatch Tab Data start --%>

	<c:when test="${pcols=='DH_COL2'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].jobLog.coordinator"/></td>
</c:when>

<c:when test="${pcols=='DH_COL3'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].jobLog.inspector"/></td>
</c:when>

<%--<c:when test="${pcols=='DH_COL4'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="results[${status.index}].jobLog.dispatchTz"/>&nbsp;<a href="#"
onClick="javascript:updateTimeZoneIframeSrc(${status.index});popup_show('timezone${status.index}','timezone_drag${status.index}','timezone_exit${status.index}', 'screen-corner', 120,20);hideIt();showPopupDiv('timezone${status.index}','timezonebody${status.index}');popupboxenable();"><img src="images/lookup.gif" width="13" alt="Lookup Time Zone" height="13" border="0"/></a></td>
</c:when>--%>

<c:when test="${pcols=='DH_COL4'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"><span>${job.jobOrder.nominationTimeTz}</span>
</c:when>

<c:when test="${pcols=='DH_COL5'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><a name="a">
<form:input id="inspectorNotifyDt${status.index}" cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].jobLog.inspectorNotifyDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].inspectorNotifyDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${pcols=='DH_COL6'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  >
<form:input cssClass="inputBox"  maxlength="11" disabled="${job.invoiceFlag}" path="results[${status.index}].inspectorNotifyTime"/></td>
</c:when>

<c:when test="${pcols=='DH_COL7'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="inspectorArriveDt${status.index}" cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].jobLog.inspectorArriveDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].inspectorArriveDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${pcols=='DH_COL8'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].inspectorArriveTime"/></td>
</c:when>

<c:when test="${pcols=='DH_COL9'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="arriveDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.arriveDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].arriveDt${status.index},'${command.dateFormat}',this)" style="cursor:hand;"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${pcols=='DH_COL10'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].arriveTime"/></td>
</c:when>

<c:when test="${pcols=='DH_COL11'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="dockDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.dockDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].dockDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${pcols=='DH_COL12'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].dockTime"/></td>
</c:when>

<c:when test="${pcols=='DH_COL13'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input disabled="${job.invoiceFlag}" id="hoseOnDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.hoseOnDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].hoseOnDt${status.index},'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17" border="0" style="cursor:hand;"/></a></td>
</c:when>

<c:when test="${pcols=='DH_COL14'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].hoseOnTime"/></td>
</c:when>

<c:when test="${pcols=='DH_COL15'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="estStartDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.estStartDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].estStartDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${pcols=='DH_COL16'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].estStartTime"/></td>
</c:when>

<c:when test="${pcols=='DH_COL17'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="commenceDt${status.index}" cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].jobLog.commenceDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].commenceDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${pcols=='DH_COL18'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].commenceTime"/></td>
</c:when>

<c:when test="${pcols=='DH_COL19'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="delayDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.delayDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].delayDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${pcols=='DH_COL20'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].delayTime"/></td>
</c:when>

<c:when test="${pcols=='DH_COL21'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><form:input id="delayEndDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.delayEndDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].delayEndDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${pcols=='DH_COL22'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].delayEndTime"/></td>
</c:when>

<c:when test="${pcols=='DH_COL23'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="estCompleteDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.estCompleteDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].estCompleteDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${pcols=='DH_COL24'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].estCompleteTime"/></td>
</c:when>

<c:when test="${pcols=='DH_COL25'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="completeDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.completeDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].completeDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${pcols=='DH_COL26'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].completeTime"/></td>
</c:when>

<c:when test="${pcols=='DH_COL27'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><a name="hoseOffDt${status.index}"></a><form:input disabled="${job.invoiceFlag}" id="hoseOffDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.hoseOffDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].hoseOffDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${pcols=='DH_COL28'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].hoseOffTime"/></td>
</c:when>

<c:when test="${pcols=='DH_COL29'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="releaseDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.releaseDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].releaseDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>

<c:when test="${pcols=='DH_COL30'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].releaseTime"/></td>
</c:when>

<c:when test="${pcols=='DH_COL31'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="summaryDt${status.index}" disabled="${job.invoiceFlag}" cssClass="inputBox" path="results[${status.index}].jobLog.summaryDt" size="10" maxlength="12"/>
<a onClick="displayCalendar(document.forms[0].summaryDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a></td>
</c:when>
<%-- dispatch tab data end --%>
<%-- main tab data start --%>
<c:when test="${pcols=='MH_COL2'}">
  <td nowrap='nowrap' height="25" align='left' valign="middle" > <form:input id="etadate${status.index}" disabled="${job.etaDateTimeFlag}" cssClass="inputBox" path="results[${status.index}].jobOrder.etaDate" size="10" maxlength="12"/>
  <a href="#" onClick="displayCalendar(document.forms[0].etadate${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>
  </td>
</c:when>

<c:when test="${pcols=='MH_COL3'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><form:input cssClass="inputBox" size="12" disabled="${job.etaDateTimeFlag}" path="results[${status.index}].etaTime"/>
</td>
</c:when>

<c:when test="${pcols=='MH_COL4'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <span>${job.jobOrder.etaTimeTz}</span>
</td>
</c:when>

<c:when test="${pcols=='MH_COL5'}">   
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><span >${job.operation} </span></td> </span>
</td>
</c:when> 

<c:when test="${pcols=='MH_COL6'}">
<td nowrap='nowrap' height="25" align='left' valign="top"  ><span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;" 
onMouseOver='doTooltip(event,"${job.jobOrder.jobDescription}")' 
onMouseOut="hideTip()">${job.jobOrder.jobDescription}</a></span>
</td>
</c:when> 

<c:when test="${pcols=='MH_COL7'}">
<td nowrap='nowrap' height="25">
<form:select cssClass="selectionBox" id ="sel1"  path="results[${status.index}].jobLog.dispatchStatus" disabled="${job.invoiceFlag}" items="${processdisStatusDropDownItems}" itemLabel="name" itemValue="value"/> 
</td>
</c:when>

<c:when test="${pcols=='MH_COL8'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"  ><span >${job.jobType}</span></td>
</c:when>

<c:when test="${pcols=='MH_COL9'}">
<td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobOrder.serviceLocation.city}</span></td>
</c:when>

<c:when test="${pcols=='MH_COL10'}">
<td nowrap='nowrap' height="25" align='left' valign="middle"><span>${job.jobOrder.serviceLocation.name}</span></td>
</c:when>

<c:when test="${pcols=='MH_COL11'}">  
<td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobOrder.vesselNames}</span></td>
</c:when>

<c:when test="${pcols=='MH_COL12'}">
      <td nowrap='nowrap' height="25" align='left' valign="middle" >  <span >${job.jobOrder.productNames}</span></td>
</c:when>

<c:when test="${pcols=='MH_COL13'}">
  <c:choose>
<c:when test="${job.jobContract.jobNumber!=null}">
<td nowrap='nowrap' height="25" align='left' valign="middle" >
<form:input id="notedetails${status.index}" cssClass="inputBox" disabled="${job.invoiceFlag}" path="results[${status.index}].appendNote" size="20" maxlength="128"/></td> 
<td nowrap='nowrap' height="25" align='center' valign="middle"  ><a href="#"><img src='images/icoeditviewnotes.gif' alt='Edit/View Notes' title='Edit/View Notes' border='0' onClick="submitNote('${status.index}','processlog','${command.pagination.currentPageNum}');"/></a></td>

</c:when>
<c:otherwise>
<td nowrap='nowrap' height="25" align='left' valign="middle">&nbsp;</td>
<td nowrap='nowrap' height="25" align='center' valign="middle"  >&nbsp;</td>

</c:otherwise>
</c:choose> 
</c:when>

<c:when test="${pcols=='MH_COL14'}">
<td nowrap='nowrap' height="25" align='left' valign="top"  ><span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;"
onMouseOver="doTooltip(event,'${job.addNote}')" 
onMouseOut="hideTip()">${job.addUpdatedNote}</a></span>
</td>
 </c:when>

<c:when test="${pcols=='MH_COL15'}">
<c:choose>
<c:when test="${job.jobContract.jobNumber!=null}">
<td nowrap='nowrap' height="25" align='left' valign="middle" > <span >${job.jobContract.custCode}</span></td>
</c:when>
<c:otherwise>
<td nowrap='nowrap' height="25" align='left' valign="middle" > <span >&nbsp;</span></td>
</c:otherwise>
</c:choose>
  </c:when> 

	<c:when test="${pcols=='MH_COL16'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle"><span>${job.jobContract.customer.name}</span></td>
	 </c:when>

      <c:when test="${pcols=='MH_COL17'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle"  > <span > ${job.jobContract.contactId}</span></td>
	 </c:when>

<c:when test="${pcols=='MH_COL18'}">
   <td nowrap='nowrap' height="25" align='left' valign="middle" > <span >${job.jobContract.contact.firstName} ${job.jobContract.contact.lastName}</span></td>
   
   </c:when>

 <c:when test="${pcols=='MH_COL19'}">
	  <td nowrap='nowrap' height="25" valign="middle">&nbsp;
	  <c:choose>
	  <c:when test="${job.jobContract != null && job.jobContract.id > 0}">
	  <input type='checkbox' id="mailCheck${status.index}" name="mailCheck${status.index}" value="${job.jobContract.id}" align='right'/>
	  </c:when>
	  <c:otherwise>
	  <input type='checkbox' id="mailCheck${status.index}" name="mailCheck${status.index}" value="${job.jobContract.id}" align='right' disabled/>
	  </c:otherwise>
	  </c:choose>
	  </td>
	</c:when> 

     <c:when test="${pcols=='MH_COL20'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" > <span >${job.jobContract.custRefNum}</span></td>
	</c:when>

<c:when test="${pcols=='MH_COL21'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle"><span></span>${job.jobContract.invoiceValue5}</td> 
	</c:when>

   <c:when test="${pcols=='MH_COL22'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobOrder.shippingAgent.name}</span></td>
      </c:when>

   <c:when test="${pcols=='MH_COL23'}">
     <td nowrap='nowrap' height="25" align='left' valign="middle" > <span >${job.jobOrder.shippingAgentPhone}</span></td> 
	 </c:when>

	 <c:when test="${pcols=='MH_COL24'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobOrder.towingCompany.name}</span></td>
	</c:when>

	<c:when test="${pcols=='MH_COL25'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobOrder.towingCompanyPhone}</span></td>
	</c:when>

    <c:when test="${pcols=='MH_COL26'}">
    <td nowrap='nowrap' height="25" align='left' valign="middle" ><span><f:formatDate value="${job.jobOrder.jobFinishDate}" type="date" pattern="${command.dateFormat}"/> </span></td>
	</c:when>

	<c:when test="${pcols=='MH_COL27'}">
	 <td nowrap='nowrap' height="25" align='left' valign="middle" > ${job.jobContract.contract.description}</td></c:when>

	 <c:when test="${pcols=='MH_COL28'}">
	<td nowrap='nowrap' height="25" align='left' valign="middle" > ${job.contractStatus}</td>
	</c:when>

	<c:when test="${pcols=='MH_COL29'}">
	<td nowrap='nowrap' height="25" align='left' valign="middle" > ${job.jobOrder.createdByUserName}</td>
	</c:when>

	<c:when test="${pcols=='MH_COL30'}">
	<td nowrap='nowrap' height="25" align='left' valign="middle" > ${job.jobOrder.updatedByUserName}</td>
	</c:when>
	<c:when test="${pcols=='MH_COL31'}">
	<td nowrap='nowrap' height="25" align='left' valign="middle" ><span >${job.jobStatus}</span></td>
	</c:when>
	<c:when test="${mcols=='MH_COL32'}">
	<td nowrap='nowrap' height="25" align='left' valign="top"  ><span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;"
	onMouseOver="doTooltip(event,'${job.customerNoteDetails}')" onMouseOut="hideTip()">${job.customerNote}</a></span>
	</td>
 	</c:when>
	<%-- START : #127441 --%>
	<c:when test="${pcols=='MH_COL33'}">
	<td nowrap='nowrap' height="25" align='left' valign="top" >
	<span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;"
	onMouseOver="doTooltip(event,'${job.cancelNoteDetails}')" onMouseOut="hideTip()">
		${job.cancelNote}</a></span>
	</td>
	</c:when>
	<%-- END : #127441 --%>
	
<%-- main tab data end --%>
 </c:choose>

</c:forEach>

<%--<ajax:autocomplete 
  baseUrl="joborder.htm"
    source="results[${status.index}].jobLog.processTz"
    target="results[${status.index}].jobLog.processTz"
  className="autocomplete" 
  parameters="pTimeZone={results[${status.index}].jobLog.processTz}"
  minimumCharacters="1"/> --%>

<SCRIPT LANGUAGE="JavaScript">
location.href="#${command.columnFlag}"	
</SCRIPT>
  </c:forEach>
    </tr>
 </table>
</div>
<br style="clear:both;" />
</div>
<%--
<c:forEach items="${command.results}" var="job" varStatus="status">
<!-------------------------------------Process Timezone Lookup Start---------------------------------------------->
<div class="sample_popup" id="ptimezone${status.index}" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="ptimezone_drag${status.index}" style="width:750px; "><img class="menu_form_exit" id="ptimezone_exit${status.index}" src=" images/form_exit.png"/>&nbsp;&nbsp;&nbsp;<f:message key="selectTimeZone"/></div><div class="menu_form_body" id="ptimezonebody${status.index}" style="width:750px; height:530px;overflow-y:hidden;"><iframe align="left:2px" frameborder="0" style="padding:2px; height:530px;" width="100%" id="frame5${status.index}" name="frame5${status.index}" allowtransparency="yes" src="blank.html"></iframe></div></div>
<!---------------------------------------Process Timezone Lookup End---------------------------------------------->
</c:forEach>
--%>
</td>
</tr>
</table>
</c:if>
</td>
</tr>
</table>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
<tr>
<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>

<td>
<!-- START : #119240 -->
<%-- <table cellspacing="0" cellpadding="0" border="0">

  <tr>
          <td><a href="#" onClick="prevSearch(pageNumber)"><IMG SRC="images/navfirst.gif" ALT="First Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
          <td><a href="#" onClick="previousSearch('${command.pagination.currentPageNum}')"><IMG SRC="images/navprevious.gif" ALT="Previous Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
          
          <td><select name="goto" size="1" class="selectionBox" id="sel3" style="width:84px;" onchange="submitSearch(this.value)" >
          <option value="Go to page"><c:out value="Go to page" />
          <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
          <option value="${page.pageNumber}"><c:out value="${page.name}" />
          </c:forEach>
          </select></td>
                    
          <td><a href="#" onClick="nextSearch('${command.pagination.currentPageNum}')"><IMG SRC="images/navnext.gif" ALT="Next Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
          <td><a href="#" onClick="lastSearch('${command.pagination.totalRecord}','${command.pagination.numInPage}')"><IMG  SRC="images/navlast.gif" ALT="Last Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
  </tr>
</table> --%>

<%@ include file="../common/pagination.jsp" %>
<!-- END : #119240 -->
</td>

<td style="text-align:right"><f:message key="numberofRows"/>:&nbsp${command.noOfJobs} &nbsp
<a href="#"><img src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><%--<a href="#" onClick="showAll('${command.pagination.currentPageNum}');"><IMG SRC="images/icoshowall.gif" ALT="Show all columns" hspace="5" BORDER="0" align="absmiddle"></a>--%>
<a href="#"><IMG SRC="images/icorefresh.gif" ALT="Search/Refresh" WIDTH="14" HEIGHT="14" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;"></a>
<a href="#"><IMG SRC="images/icoemail.gif" ALT="Please save the changes(if any) & then Send email" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;" onClick="submitmail(${fn:length(command.results)});"></a>
<a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" hspace="5" border="0" align="absmiddle" onClick="submitForm('processlog','${command.pagination.currentPageNum}');" /></a></th>
</td>

</tr>
</table></td>
</tr>
</table>
</c:if>
<%--</c:if>--%>
</div>
<!-------------------------------------------------TAB 5 CONTAINER END-------------------------------------------->
 

 <!------------------------------------------- TAB 6 CONTAINER----------------------------------------------------->

<div id="tab6" class="innercontent1">
 <c:if test="${command.showAllFlag=='true'}">
<table width="100%" cellpadding=0 cellspacing=0 class="mainApplTable">
<tbody>
<tr bgcolor=#ffffff>
<th width="65%"><f:message key="searchResultsShowAll"/></th>
<th width="15%" style="text-align:right"><a href="#"><img src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><%--<a href="#" onClick="showAll('${command.pagination.currentPageNum}');"><IMG SRC="images/icoshowall.gif" ALT="Show all columns" hspace="5" BORDER="0" align="absmiddle"></a>--%>
<a href="#"><IMG SRC="images/icorefresh.gif" ALT="Search/Refresh" WIDTH="14" HEIGHT="14" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;"></a>
<a href="#"><IMG SRC="images/icoemail.gif" ALT="Please save the changes(if any) & then Send email" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;" onClick="submitmail(${fn:length(command.results)});"></a>
<a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" hspace="5" border="0" align="absmiddle" onClick="submitForm('showall','${command.pagination.currentPageNum}');" /></a></th>
</tr>


<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" border="0">
<tr>
<td valign="top" width="125" style=" padding:0px;text-align:left">

<c:if test="${command.results != null}">
<tr>
<td colspan="0" style="padding:0px;">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" align="left">
<tr>
<td valign="top" style=" padding:0px;">

<div style="width:100%; vertical-align:top;">

<div style="width:11%; float:left;#DBE2F2 1px solid;background:url(images/intablehdrblubg2.gif) repeat-x;"">

<table width="98%" cellpadding="0" cellspacing="0" class="InnerApplTable" align="left" border="0">
<tr><th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col1','showall');" ><f:message key="jobId"/></a></th></tr>
<c:forEach items="${command.results}" var="job" varStatus="status">
<%-- <tr><td align='CENTER' nowrap='nowrap' height="25" valign="middle"><a href="${job.herfJobType}?jobNumber=${job.jobOrder.jobNumber}&reqFrom=${job.reqFrom}" title="Transfer to Nomination" >${job.jobOrder.jobNumber}</a></td></tr> --%>

 <tr><td align='CENTER' nowrap='nowrap' height="25" valign="middle"><a href="#" onClick="updateSession('${job.herfJobType}','${job.jobOrder.jobNumber}','${job.reqFrom}')" title="Transfer to Nomination" >${job.jobOrder.jobNumber}</a></td></tr> 
</c:forEach>
</table>
</div>

<div style="float:left;width:89%;" style="visibility: visible;overflow-x:scroll;height:auto;overflow-y:hidden;#DBE2F2 1px solid;">
<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" frameborder="0" border="0" style="border-left-width:0px;#DBE2F2 1px solid;">
<tr>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col2','showall');" ><f:message key="ETADate"/></a><a name="mH_Col2"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col3','showall');" ><f:message key="ETATime"/></a><a name="mH_Col3"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col4','showall');" ><f:message key="timeZone"/></a><a name="mH_Col4"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col5','showall');" ><f:message key="operation"/></a><a name="mH_Col5"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col6','showall');" ><f:message key="jobDescription"/></a><a name="mH_Col6"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col7','showall');" ><f:message key="mH_Col7"/></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col8','showall');" ><f:message key="jobType"/></a><a name="mH_Col8"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col9','showall');" ><f:message key="portLocation"/></a><a name="mH_Col9"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col10','showall');" ><f:message key="serviceLocation"/></a><a name="mH_Col10"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col11','showall');" ><f:message key="vessel"/></a><a name="mH_Col11"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col12','showall');" ><f:message key="product"/></a><a name="mH_Col12"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col13','showall');" ><f:message key="noteToAdd"/></a><a name="mH_Col13"></a></th>
     <th nowrap>&nbsp;</th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col14','showall');" ><f:message key="notes"/></a><a name="shownotes"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col15','showall');" ><f:message key="companyId"/></a><a name="mH_Col15"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col16','showall');" ><f:message key="companyName"/></a><a name="mH_Col16"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col17','showall');" ><f:message key="schedulerId"/></a><a name="mH_Col17"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col18','showall');" ><f:message key="scheduler"/></a><a name="mH_Col18"></a></th>
    <th nowrap><f:message key="email"/></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col20','showall');" ><f:message key="custRefNum"/></a><a name="mH_Col20"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col21','showall');" ><f:message key="icbReference"/></a><a name="mH_Col21"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col22','showall');" ><f:message key="agent"/></a><a name="mH_Col22"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col23','showall');" ><f:message key="phoneno"/>.</a><a name="mH_Col23"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col24','showall');" ><f:message key="towingCompany"/></a><a name="mH_Col24"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col25','showall');" ><f:message key="phoneno"/>.</a><a name="mH_Col25"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col25','showall');" ><f:message key="jobFinishDate"/></a><a name="mH_Col25"></a></th>
	<th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col27','showall');" ><f:message key="contractDescription"/></a><a name="mH_Col27"></a></th>
	<th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col28','showall');" ><f:message key="contractStatus"/></a><a name="mH_Col28"></a></th>
	<th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col29','showall');" ><f:message key="createdBy"/></a><a name="mH_Col29"></a></th>

	<th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col30','showall');" ><f:message key="modifiedBy"/></a><a name="mH_Col30"></a></th>
<th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col32','showall');" ><f:message key="customerNotes"/></a><a name="mH_Col32"></a></th>
	
	<%-- START : #127441 --%>
	<th nowrap><a href="#start" onClick="sortByJobHeader('mH_Col33','showall');" ><f:message key="cancelledNote"/></a><a name="mH_Col33"></a></th>
	<%-- END : #127441 --%>
	
	<th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col2','showall');" ><f:message key="coordinator"/></a><a name="dH_Col2"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col3','showall');" ><f:message key="inspector"/></a><a name="dH_Col3"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col4','showall');" ><f:message key="timeZone"/></a><a name="dH_Col4"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col5','showall');" ><f:message key="inspectorNotified"/></a><a name="dH_Col5"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col6','showall');" ><f:message key="time"/></a><a name="dH_Col6"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col7','showall');" ><f:message key="inspectorArrived"/></a><a name="dH_Col7"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col8','showall');" ><f:message key="time"/></a><a name="dH_Col8"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col9','showall');" ><f:message key="arriveDate"/></a><a name="dH_Col9"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col10','showall');" ><f:message key="time"/></a><a name="dH_Col10"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col11','showall');" ><f:message key="dockDate"/></a><a name="dH_Col11"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col12','showall');" ><f:message key="time"/></a><a name="dH_Col12"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col13','showall');" ><f:message key="hoseOnDate"/></a><a name="dH_Col13"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col14','showall');" ><f:message key="time"/></a><a name="dH_Col14"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col15','showall');" ><f:message key="estStartDate"/></a><a name="dH_Col15"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col16','showall');" ><f:message key="time"/></a><a name="dH_Col16"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col17','showall');" ><f:message key="commenceDate"/></a><a name="dH_Col17"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col18','showall');" ><f:message key="time"/></a><a name="dH_Col18"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col19','showall');" ><f:message key="delayDate"/></a><a name="dH_Col19"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col20','showall');" ><f:message key="time"/></a><a name="dH_Col20"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col21','showall');" ><f:message key="delayEndDate"/></a><a name="dH_Col21"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col22','showall');" ><f:message key="time"/></a><a name="dH_Col22"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col23','showall');" ><f:message key="estCompleteDate"/></a><a name="dH_Col23"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col24','showall');" ><f:message key="time"/></a><a name="dH_Col24"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col25','showall');" ><f:message key="completeDate"/></a><a name="dH_Col25"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col26','showall');" ><f:message key="time"/></a><a name="dH_Col26"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col27','showall');" ><f:message key="hoseOffDate"/></a><a name="dH_Col27"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col28','showall');" ><f:message key="time"/></a><a name="dH_Col28"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col29','showall');" ><f:message key="releasedDate"/></a><a name="dH_Col29"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col30','showall');" ><f:message key="time"/></a><a name="dH_Col30"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('dH_Col31','showall');" ><f:message key="summarySent"/></a><a name="dH_Col31"></a></th>
	 <th nowrap><a href="#start" onClick="sortByJobHeader('bH_Col2','showall');" ><f:message key="contactID"/></a><a name="joblog.summaryDt"><a name="bH_Col2"></a></th>
    <th nowrap style="width:150px;"><a href="#start" onClick="sortByJobHeader('bH_Col3','showall');" ><f:message key="billingContact"/></a><a name="bH_Col3"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('bH_Col4','showall');" ><f:message key="status"/></a><a name="bH_Col4"></a></th>
    <th nowrap style="width:100px;"><a href="#start" onClick="sortByJobHeader('bH_Col5','showall');" ><f:message key="invoice"/></a><a name="bH_Col5"></a></th>
   
	<th nowrap><a href="#start" onClick="sortByJobHeader('bH_Col6','showall');" ><f:message key="totalPrice"/></a><a name="bH_Col6"></a></th>
    
	<th nowrap><a href="#start" onClick="sortByJobHeader('bH_Col7','showall');" ><f:message key="invoiceDate"/></a><a name="bH_Col7"></a></th>
   
	<th nowrap><a href="#start" onClick="sortByJobHeader('bH_Col8','showall');" ><f:message key="dateInvoiced"/></a><a name="bH_Col8"></a></th>
  
	<th nowrap><a href="#start" onClick="sortByJobHeader('bH_Col9','showall');" ><f:message key="invoicecredited"/></a><a name="bH_Col9"></a></th>
   
	<th nowrap><a href="#start" onClick="sortByJobHeader('bH_Col10','showall');" ><f:message key="reopenDate"/></a><a name="jobc.creditInd"></a></th>
	
	 <th nowrap><a href="#start" onClick="sortByJobHeader('pH_Co2','showall');" ><f:message key="timeZone"/></a><a name="pH_Co2"></a></th>
    <th nowrap style="width:150px;"><a href="#start" onClick="sortByJobHeader('pH_Col3','showall');" ><f:message key="sampleReceivedDate"/></a><a name="pH_Col3"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('pH_Col4','showall');" ><f:message key="time"/></a><a name="pH_Col4"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('pH_Col5','showall');" ><f:message key="prelimReportDate"/></a><a name="pH_Col5"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('pH_Col6','showall');" ><f:message key="time"/></a><a name="pH_Col6"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('pH_Col7','showall');" >
	<f:message key="sampleShippedDate"/></a><a name="pH_Col7"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('pH_Col8','showall');" ><f:message key="time"/></a><a name="pH_Col8"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('pH_Col9','showall');" ><f:message key="finalReportDate"/></a><a name="pH_Col9"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('pH_Col10','showall');" ><f:message key="time"/></a><a name="pH_Col10"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('pH_Col11','showall');" ><f:message key="distributedDate"/></a><a name="pH_Col11"></a></th>
    <th nowrap><a href="#start" onClick="sortByJobHeader('pH_Col12','showall');" ><f:message key="time"/></a><a name="pH_Col12"></a></th>
	<th><a href="#start"></a>&nbsp;<a name="joblog.distributeTime"></a></th>

  </tr>
   <icb:list var="showalldisStatusDropDownItems" items="${icbfn:dropdown('staticDropdown',dispatchStatus)}" />
  <c:forEach items="${command.results}" var="job" varStatus="status">
  <tr valign='center'>
   <td align='left' valign="middle" nowrap> <form:input id="etadate${status.index}" cssClass="inputBox" path="results[${status.index}].jobOrder.etaDate" size="10" maxlength="12" disabled="true"/>
<%--<a href="#" onClick="displayCalendar(document.forms[0].etadate${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>--%></td>


    <td align='left' valign="middle"  nowrap='nowrap'><form:input cssClass="inputBox" size="12" path="results[${status.index}].etaTime"  disabled="true"/></td>

    <td align='left' valign="middle" > <span>${job.jobOrder.etaTimeTz}</span></td>
      
     <td align='left' valign="middle" nowrap ><span >${job.operation} </span></td> </span></td>
 


<td align='left' valign="top"  nowrap><span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;" 
onMouseOver='doTooltip(event,"${job.jobOrder.jobDescription}")' 
onMouseOut="hideTip()">${job.jobOrder.jobDescription}</a></span>
</td>
      
<c:choose>
<c:when test="${job.jobContract.jobNumber!=null}">
<td>
<form:select cssClass="selectionBox" id ="sel1"  path="results[${status.index}].jobLog.dispatchStatus" items="${icbfn:dropdown('staticDropdown',dispatchStatus)}" itemLabel="name" itemValue="value"  disabled="true"/> 
</td></c:when>
<c:otherwise>
<td>
<form:select cssClass="selectionBox" id ="sel1"  path="results[${status.index}].jobLog.dispatchStatus" items="${showalldisStatusDropDownItems}" itemLabel="name" itemValue="value" disabled="true"/> 
</td></c:otherwise>
</c:choose>

    <td align='left' valign="middle" nowrap ><span >${job.jobType}</span></td>

    <td align='left' valign="middle" nowrap><span >${job.jobOrder.serviceLocation.city}</span></td>

    <td align='left' valign="middle" nowrap ><span>${job.jobOrder.serviceLocation.name}</span></td>
      
    <td align='left' valign="middle" nowrap><span >${job.jobOrder.vesselNames}</span></td>

    <td align='left' valign="middle" nowrap><span >${job.jobOrder.productNames}</span></td>

    
<c:choose>
<c:when test="${job.jobContract.jobNumber!=null}">
<td align='left' valign="middle" >
<form:input id="notedetails${status.index}" cssClass="inputBox" path="results[${status.index}].appendNote" size="20" maxlength="128"  disabled="true"/></td>
<td align='center' valign="middle"  nowrap='nowrap'><a href="#"><img src='images/icoeditviewnotes.gif' alt='Add Notes' title='Add Notes' border='0'/></a></td>
</c:when>
<c:otherwise>
<td align='left' valign="middle">&nbsp;</td>
<td align='center' valign="middle"  nowrap='nowrap'>&nbsp;</td>
</c:otherwise>
</c:choose>
	  
	  


<td align='left' valign="top"  nowrap><span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;" <%-- onMouseOver="doTooltip(event, '<table><tr><td width=170px><b>${job.addNote}</b></td></tr></table>')"--%>
onMouseOver="doTooltip(event,'${job.addNote}')" 
onMouseOut="hideTip()">${job.addUpdatedNote}</a></span>
</td>

<c:choose>
<c:when test="${job.jobContract.jobNumber!=null}">
<td align='left' valign="middle" > <span >${job.jobContract.custCode}</span></td>
</c:when>
<c:otherwise>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
</c:otherwise>
</c:choose>
    
    <td align='left' valign="middle" nowrap><span>${job.jobContract.customer.name}	</span></td>
      
    <td align='left' valign="middle"  nowrap><span >${job.jobContract.contactId}</span></td>

   <td align='left' valign="middle" nowrap > <span >${job.jobContract.contact.firstName} ${job.jobContract.contact.lastName}</span></td>

	  <td valign="middle">&nbsp;
	  <c:choose>
	  <c:when test="${job.jobContract != null && job.jobContract.id > 0}">
	  <input type='checkbox' id="mailCheck${status.index}" name="mailCheck${status.index}" value="${job.jobContract.id}" align='right' disabled/>
	  </c:when>
	  <c:otherwise>
	  <input type='checkbox' id="mailCheck${status.index}" name="mailCheck${status.index}" value="${job.jobContract.id}" align='right' disabled/>
	  </c:otherwise>
	  </c:choose>
	  </td>
      
    <td align='left' valign="middle" nowrap > <span >${job.jobContract.custRefNum}</span></td>

    <td align='left' valign="middle" nowrap><span></span>${job.jobContract.invoiceValue5}</td>      
   
    <td align='left' valign="middle" nowrap><span >${job.jobOrder.shippingAgent.name}</span></td>
      
     <td align='left' valign="middle" nowrap> <span >${job.jobOrder.shippingAgentPhone}</span></td> 
    <td align='left' valign="middle" nowrap><span >${job.jobOrder.towingCompany.name}</span></td>

      <td align='left' valign="middle" nowrap><span >${job.jobOrder.towingCompanyPhone}</span></td>
      
    <td height="25" align='left' valign="middle" nowrap><span><f:formatDate value="${job.jobOrder.jobFinishDate}" type="date" pattern="${command.dateFormat}"/> </span></td>
	 <td align='left' valign="middle" nowrap > ${job.jobContract.contract.description}</td>
	<td align='left' valign="middle" nowrap > ${job.contractStatus}</td>
	<td align='left' valign="middle" nowrap > ${job.jobOrder.createdByUserName}</td>
	<td align='left' valign="middle" nowrap > ${job.jobOrder.updatedByUserName}</td>

<td nowrap='nowrap' height="25" align='left' valign="top"  ><span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;"
	onMouseOver="doTooltip(event,'${job.customerNoteDetails}')" onMouseOut="hideTip()">${job.customerNote}</a></span>
	</td>	
	<%-- START : #127441 --%>
	<td nowrap='nowrap' height="25" align='left' valign="top" >
	<span style="word-wrap:break-word;overflow:hidden;width:150px;"><a href="#" style="cursor:hand;"
	onMouseOver="doTooltip(event,'${job.cancelNoteDetails}')" onMouseOut="hideTip()">
		${job.cancelNote} </a></span>
	</td>
	<%-- END : #127441 --%>
<c:choose>
<c:when test="${job.jobContract.jobNumber!=null}">
<td align='left' valign="middle"  height="25" nowrap='nowrap'><form:input cssClass="inputBox"  path="results[${status.index}].jobLog.coordinator" disabled="true"/></td>
<td align='left' valign="middle"  nowrap='nowrap'><form:input cssClass="inputBox"  path="results[${status.index}].jobLog.inspector"  disabled="true"/></td>

<%--<td align='left' valign="middle"  nowrap='nowrap'><form:input cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="results[${status.index}].jobLog.dispatchTz"  disabled="true"/>&nbsp;<a href="#"
onClick="javascript:updateTimeZoneIframeSrc(${status.index});popup_show('timezone${status.index}','timezone_drag${status.index}','timezone_exit${status.index}', 'screen-corner', 120,20);hideIt();showPopupDiv('timezone${status.index}','timezonebody${status.index}');popupboxenable();"><img src="images/lookup.gif" width="13" alt="Lookup Time Zone" height="13" border="0"/></a></td>--%>

<td align='left' valign="middle" nowrap > ${job.jobOrder.nominationTimeTz}</td>

<td align='left' valign="middle" nowrap><a name="a">
<form:input id="inspectorNotifyDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.inspectorNotifyDt" size="10" maxlength="12"  disabled="true"/>
<%--<a onClick="displayCalendar(document.forms[0].inspectorNotifyDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0" /></a>--%></td>

<td align='left' valign="middle"  nowrap='nowrap'>
<form:input cssClass="inputBox"  maxlength="11" path="results[${status.index}].inspectorNotifyTime"  disabled="true"/></td>

<td align='left' valign="middle" nowrap>
<form:input id="inspectorArriveDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.inspectorArriveDt" size="10" maxlength="12"  disabled="true"/>
<%--<a onClick="displayCalendar(document.forms[0].inspectorArriveDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>--%></td>


<td align='left' valign="middle"  nowrap='nowrap'><form:input cssClass="inputBox"  path="results[${status.index}].inspectorArriveTime"  disabled="true"/></td>

<td align='left' valign="middle" nowrap>
<form:input id="arriveDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.arriveDt" size="10" maxlength="12"  disabled="true"/>
<%--<a onClick="displayCalendar(document.forms[0].arriveDt${status.index},'${command.dateFormat}',this)" style="cursor:hand;"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>--%></td>

<td align='left' valign="middle"  nowrap='nowrap'><form:input cssClass="inputBox"  path="results[${status.index}].arriveTime"  disabled="true"/></td>


<td align='left' valign="middle" nowrap>
<form:input id="dockDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.dockDt" size="10" maxlength="12"  disabled="true"/>
<%--<a onClick="displayCalendar(document.forms[0].dockDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>--%></td>


<td align='left' valign="middle"  nowrap='nowrap'><form:input cssClass="inputBox"  path="results[${status.index}].dockTime"  disabled="true"/></td>

<td align='left' valign="middle" nowrap>
<form:input id="hoseOnDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.hoseOnDt" size="10" maxlength="12"  disabled="true"/>
<%--<a onClick="displayCalendar(document.forms[0].hoseOnDt${status.index},'${command.dateFormat}',this)"><img src="images/calendar.gif" width="15" height="17" border="0" style="cursor:hand;"/></a>--%></td>
<td align='left' valign="middle"  nowrap='nowrap'><form:input cssClass="inputBox"  path="results[${status.index}].hoseOnTime"  disabled="true"/></td>

<td align='left' valign="middle" nowrap>
<form:input id="estStartDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.estStartDt" size="10" maxlength="12"  disabled="true"/>
<%--<a onClick="displayCalendar(document.forms[0].estStartDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>--%></td>


<td align='left' valign="middle"  nowrap='nowrap'><form:input cssClass="inputBox"  path="results[${status.index}].estStartTime"  disabled="true"/></td>

<td align='left' valign="middle" nowrap>
<form:input id="commenceDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.commenceDt" size="10" maxlength="12"  disabled="true"/>
<%--<a onClick="displayCalendar(document.forms[0].commenceDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>--%></td>

<td align='left' valign="middle"  nowrap='nowrap'><form:input cssClass="inputBox"  path="results[${status.index}].commenceTime"  disabled="true"/></td>


<td align='left' valign="middle" nowrap> <form:input id="delayDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.delayDt" size="10" maxlength="12"  disabled="true"/>
<%--<a onClick="displayCalendar(document.forms[0].delayDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>--%></td>


<td align='left' valign="middle"  nowrap='nowrap'><form:input cssClass="inputBox"  path="results[${status.index}].delayTime"  disabled="true"/></td>

<td align='left' valign="middle" nowrap><form:input id="delayEndDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.delayEndDt" size="10" maxlength="12"  disabled="true"/>
<%--<a onClick="displayCalendar(document.forms[0].delayEndDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>--%></td>

<td align='left' valign="middle"  nowrap='nowrap'><form:input cssClass="inputBox"  path="results[${status.index}].delayEndTime"  disabled="true"/></td>

<td align='left' valign="middle" nowrap> <form:input id="estCompleteDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.estCompleteDt" size="10" maxlength="12"  disabled="true"/>
<%--<a onClick="displayCalendar(document.forms[0].estCompleteDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>--%></td>

<td align='left' valign="middle"  nowrap='nowrap'><form:input cssClass="inputBox"  path="results[${status.index}].estCompleteTime"  disabled="true"/></td>

<td align='left' valign="middle" nowrap> <form:input id="completeDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.completeDt" size="10" maxlength="12"  disabled="true"/>
<%--<a onClick="displayCalendar(document.forms[0].completeDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>--%></td>


<td align='left' valign="middle"  nowrap='nowrap'><form:input cssClass="inputBox"  path="results[${status.index}].completeTime"  disabled="true"/></td>

<td align='left' valign="middle" nowrap><a name="hoseOffDt${status.index}"></a><form:input id="hoseOffDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.hoseOffDt" size="10" maxlength="12"  disabled="true"/>
<%--<a onClick="displayCalendar(document.forms[0].hoseOffDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>--%></td>

<td align='left' valign="middle"  nowrap='nowrap'><form:input cssClass="inputBox"  path="results[${status.index}].hoseOffTime"  disabled="true"/></td>

<td align='left' valign="middle" nowrap> <form:input id="releaseDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.releaseDt" size="10" maxlength="12"  disabled="true"/>
<%--<a onClick="displayCalendar(document.forms[0].releaseDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>--%></td>

<td align='left' valign="middle"  nowrap='nowrap'><form:input cssClass="inputBox"  path="results[${status.index}].releaseTime"  disabled="true"/></td>

<td align='left' valign="middle" nowrap> <form:input id="summaryDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.summaryDt" size="10" maxlength="12"  disabled="true"/>
<%--<a onClick="displayCalendar(document.forms[0].summaryDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>--%></td>

</c:when>
<c:otherwise>
<td align='left' valign="middle" height="25"> <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
</c:otherwise>
</c:choose>
		<td align='left' >${job.jobContract.billingContact.id}</td>
		<td align='left' nowrap > ${job.jobContract.billingContact.firstName} ${job.jobContract.billingContact.lastName}</td>
		<td align='left' > ${job.jobContract.billStatus}</td>
		<td align='left' nowrap > ${job.latestJobContractInvoice.invoice}</td>
		<td align='right' >${job.jobContract.invoiceAmt}</td>
		<td align='left' ><f:formatDate value="${job.jobContract.invoiceDate}" type="date" pattern="${command.dateFormat}" /></td>
		<td align='left' ><f:formatDate value="${job.latestJobContractInvoice.generatedDateTime}" type="date" pattern="${command.dateFormat}" /></td>
		<td align='left' >${job.jobContract.creditInd}</td>
		<td height="25" align='left' ><f:formatDate value="${job.jobOrder.reOpenDate}" type="date" pattern="${command.dateFormat}"/></td>
	  <c:choose>
<c:when test="${job.jobContract.jobNumber!=null}">

	<%--<td height="25" align='left'  nowrap='nowrap'>
      <form:input cssClass="inputBox" cssStyle="text-align:left; background-color:#d2e1ff; color:#000099;" path="results[${status.index}].jobLog.processTz"  disabled="true"/>&nbsp;<a href="#"
onClick="javascript:updateProcessTimeZoneIframeSrc(${status.index});popup_show('ptimezone${status.index}','ptimezone_drag${status.index}','ptimezone_exit${status.index}', 'screen-corner', 120,20);hideIt();showPopupDiv('ptimezone${status.index}','ptimezonebody${status.index}');popupboxenable();"><img src="images/lookup.gif" width="13" height="13" alt="Lookup Time Zone" border="0" align="absmiddle"/></a></td>--%>

<td height="25" align='left' valign="middle" nowrap > ${job.jobOrder.nominationTimeTz}</td>
 

      <td align='left' valign="middle" nowrap> <form:input id="sampleReceiveDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.sampleReceiveDt" size="12" maxlength="10"  disabled="true"/>
<%--<a href="#" onClick="displayCalendar(document.forms[0].sampleReceiveDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>--%></td>


  <td align='left' valign="middle"  nowrap='nowrap'><form:input cssClass="inputBox"  path="results[${status.index}].sampleReceiveTime"  disabled="true"/></td>
 
    <td align='left' valign="middle" nowrap> <form:input id="prelimReportDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.prelimReportDt" size="12" maxlength="10"  disabled="true"/>
<%--<a href="#" onClick="displayCalendar(document.forms[0].prelimReportDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>--%></td>
      
  <td align='left' valign="middle"  nowrap='nowrap'><form:input cssClass="inputBox"  path="results[${status.index}].prelimReportTime"  disabled="true"/></td>
      
<td align='left' valign="middle" nowrap> <form:input id="sampleShipDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.sampleShipDt" size="12" maxlength="10"  disabled="true"/>
<%--<a href="#" onClick="displayCalendar(document.forms[0].sampleShipDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>--%></td>
    
  <td align='left' valign="middle"  nowrap='nowrap'><form:input cssClass="inputBox"  path="results[${status.index}].sampleShipTime"  disabled="true"/></td>
      
<td align='left' valign="middle" nowrap> <form:input id="finalReportDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.finalReportDt" size="12" maxlength="10"  disabled="true"/>
<%--<a href="#" onClick="displayCalendar(document.forms[0].finalReportDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>--%></td>

  <td align='left' valign="middle"  nowrap='nowrap'><form:input cssClass="inputBox"  path="results[${status.index}].finalReportTime"  disabled="true"/></td>
      
<td align='left' valign="middle" nowrap> <form:input id="distributeDt${status.index}" cssClass="inputBox" path="results[${status.index}].jobLog.distributeDt" size="12" maxlength="10"  disabled="true"/>
<%--<a href="#" onClick="displayCalendar(document.forms[0].distributeDt${status.index},'${command.dateFormat}',this)"><img src=" images/calendar.gif" width="15" height="17" align="absmiddle" border="0"/></a>--%></td>
      
  <td align='left' valign="middle"  nowrap='nowrap'><form:input cssClass="inputBox"  path="results[${status.index}].distributeTime"  disabled="true"/></td>
</c:when>
<c:otherwise>
<td align='left' valign="middle" height="25"> <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
<td align='left' valign="middle" > <span >&nbsp;</span></td>
</c:otherwise>
</c:choose>

    </tr>


<SCRIPT LANGUAGE="JavaScript">
location.href="#${command.columnFlag}"	
</SCRIPT>

</c:forEach>
</table>
</div>
<br style="clear:both;" />
</div>	

</td>
</tr>
</table>
</td>
</tr>
</c:if>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
<tr>
<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>
<!-- START : #119240 -->
<%-- <table cellspacing="0" cellpadding="0" border="0">
    <tr>
      <td><a href="#start" onClick="prevSearch(pageNumber)"><IMG SRC="images/navfirst.gif" ALT="First Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
      <td><a href="#start" onClick="previousSearch('${command.pagination.currentPageNum}')"><IMG SRC="images/navprevious.gif" ALT="Previous Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
      
      <td><select name="goto" size="1" class="selectionBox" id="sel3" style="width:84px;" onchange="submitSearch(this.value)" >
      <option value="Go to page"><c:out value="Go to page" />
      <c:forEach items="${command.pagination.pages}" var="page" varStatus="status">
      <option value="${page.pageNumber}"><c:out value="${page.name}" />
      </c:forEach>
      </select></td>
      
      <td><a href="#start" onClick="nextSearch('${command.pagination.currentPageNum}')"><IMG SRC="images/navnext.gif" ALT="Next Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
      <td><a href="#start" onClick="lastSearch('${command.pagination.totalRecord}','${command.pagination.numInPage}')"><IMG SRC="images/navlast.gif" ALT="Last Page" WIDTH="21" HEIGHT="16" hspace="2" BORDER="0"></a></td>
  </tr>
</table> --%>

<%@ include file="../common/pagination.jsp" %>
<!-- END : #119240 -->
</td>

<td style="text-align:right"><f:message key="numberofRows"/>:&nbsp${command.noOfJobs} &nbsp <a href="#"><img src="images/ico_excel.gif" alt="Downlaod to Excel" width="18" height="18" hspace="5" border="0" align="absmiddle" onClick="submitxcel();"></a><%--<a href="#" onClick="showAll('${command.pagination.currentPageNum}');"><IMG SRC="images/icoshowall.gif" ALT="Show all columns" hspace="5" BORDER="0" align="absmiddle"></a>--%><a href="#"><IMG SRC="images/icorefresh.gif" ALT="Search/Refresh" WIDTH="14" HEIGHT="14" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;"></a><a href="#"><IMG SRC="images/icoemail.gif" ALT="Please save the changes(if any) & then Send email" hspace="5" BORDER="0" align="absmiddle" style="cursor:hand;" onClick="submitmail(${fn:length(command.results)});"></a><a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" hspace="5" border="0" align="absmiddle" onClick="submitForm('showall','${command.pagination.currentPageNum}');"/></a></td>
</td>
</tr>
</table>
</tr>
</tbody>
</table>
</c:if>
</div>

<!----------------------------------------------TAB 6 CONTAINER END----------------------------------------------->
  
  
  </div>
  </div>
  <script type="text/javascript">
  var tabnavIndex = 0;
  if('main' == '${searchResult}') tabnavIndex = 1;
  dolphintabs.init("tabnav", tabnavIndex)
  if('dispatch' == '${searchResult}') tabnavIndex = 2;
  dolphintabs.init("tabnav", tabnavIndex)
   if('billing' == '${searchResult}') tabnavIndex = 3;
  dolphintabs.init("tabnav", tabnavIndex)
   if('processlog' == '${searchResult}') tabnavIndex = 4;
  dolphintabs.init("tabnav", tabnavIndex)
   if('showall' == '${searchResult}') tabnavIndex = 5;
  dolphintabs.init("tabnav", tabnavIndex)
  
  </script>
<!--------------------------------------------------TAB CONTENT END----------------------------------------------->
  <table width="100%" cellspacing="0">
  <tr>
  <td width="90%" height="24" align="right">
  <div id="navbuttons"></div>
  </td>
  <td height="24" style="text-align:right; padding-right:0px;"><select name="sel5" id="sel5" class="SelectionBox" style="float:right" onChange="MM_jumpMenu('parent',this,1)">
  <option selected>Go to ... &gt;</option>
  <option value="search_job.htm">Job Search</option>
  </select>
  </td>
  </tr>
  </table>
  </div>
<!--------------------------------------------------BREADCRUMB TRAIL END------------------------------------------>
<!----------------------------------------------------MAIN CONTAINER END------------------------------------------>
</td>
</tr>
</table>

</form:form>
<div id="faderPane" style="visibility:hidden; display:none; z-index:1; Position : Absolute ;Left:1px;Top:1px;">
<IMG src="images/fake-alpha-999.gif"></div>


<!-- --------------------------- Email Lookup Start ------------------------------------------------- -->

<div class="sample_popup" id="email" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="email_drag" style="width:850px; "> 
 <img class="menu_form_exit"   id="email_exit" src="images/form_exit.png" border="0" /></a> &nbsp;&nbsp;&nbsp;<f:message key="jobOrderEmailSummaries"/>  </div>
    <div class="menu_form_body" id="emailbody"   style="width:850px; height:550px;overflow-y:hidden;">
  <iframe id="emailIframe" align="left" src="blank.html"
  style="padding:0px;" width="100%" height="550px;"
  scrolling="auto" name="frame1" allowtransparency="yes"></iframe>

    </div>
  </div>
