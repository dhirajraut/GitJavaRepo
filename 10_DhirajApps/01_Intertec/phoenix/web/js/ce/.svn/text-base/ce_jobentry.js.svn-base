function closeAttachPopupWindow(divName){
	if(window.opener!=undefined)
	{
	   window.close();
	}
	else{
	top.hideAttachFile(divName);top.popupboxclose();
	}
}

function projectManagerPostAjax(){
	var text=document.getElementById('projectManagerFullName').value;
	var values = text.split('|');
	document.getElementById('projectManagerFullName').value = values[0].trim();
	document.getElementById('projectManagerName').value = values[1].trim();
}


//post function for service location autocomplete
function updateServiceLoc()
{
	var innerTextStr =document.getElementById('serviceLoc').value;
	var currTokens = innerTextStr.split('|');
	document.getElementById('serviceLoc').value = currTokens[0].trim();
	document.getElementById('serviceLocCode').value = currTokens[1].trim();
}


String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}

function updateWareHouseName(){
	var innerTextStr =document.getElementById('brnch').value;
	var currTokens = innerTextStr.split('~');
	document.getElementById('brnch').value = currTokens[0].trim();
	if(currTokens.length>1){
		var serviceLocationToken = currTokens[1].split('|');
		window.top.document.getElementById("serviceLoc").value = serviceLocationToken[0].trim();
		window.top.document.getElementById("serviceLocCode").value = serviceLocationToken[1].trim();						
	}
}
	
//related to job order notes 

function popAddNote() {
popup_show('addnote',	'addnote_drag','addnote_exit','screen-corner', 120, 20);
hideIt();
popupboxenable()
}

function updateCustomerAddNoteIframeSrc(contractId,jobNumber) {
	document.getElementById('customerNoteFr').setAttribute("src","phx_job_order_note_popup.htm?id="+contractId+"&jobNumber="+jobNumber+"&divName=note");
}

function onDeleteJobOrderNote(noteId) {
  document.getElementById("deleteNoteId").value=noteId;
  document.ceJobOrderNotePopUpForm.submit();
}

function onSelectJobOrderNote(noteId) {
document.getElementById("selectedNoteId").value=noteId;
	document.ceJobOrderNotePopUpForm.submit();
}

function onResetJobOrderNote() {
  document.getElementById("reset").value="reset";
	document.ceJobOrderNotePopUpForm.submit();

}

function confirmJobOrderNoteDelete() {
   var yesno = confirm ("Are you sure you want to delete this note?");
   if (yesno == true)
	 return true;
   else
     return false;
}

function beforeJobOrderNotesubmit() {
	if(document.ceJobOrderNotePopUpForm.noteSummary.value.length==0){
		confirm("Please fill Summary ");
		setFocus(document.ceJobOrderNotePopUpForm.notesummary);
	}else{
	document.ceJobOrderNotePopUpForm.requestAction.value="save";	
		document.ceJobOrderNotePopUpForm.submit();
	}
}

function setFocus(id) {
	try {
		id.focus();
	}
	catch (e){}
}

function addAttach(id,jobNumber) {
url="phx_job_order_attachfile_popup.htm?customerId="+id+"&jobNumber="+jobNumber+"&divName=attach&inputFieldId=attachedFileNames";
newwindow=window.open(url,'attach','scrollbars=1,height=300,width=700,resizable=no');
if (window.focus) {newwindow.focus()}
    return false;
}

//related to job order attachments

function popAttach() {
 popup_show('attach', 'attach_drag', 'attach_exit', 'screen-corner', 120,20);
 hideIt();
 popupboxenable()
}

function updateCustomerAttachIframeSrc(customerId,jobNumber) {
 document.getElementById('customerAttachFileFr').setAttribute("src","phx_job_order_attachfile_popup.htm?customerId="+customerId+"&jobNumber="+jobNumber+"&divName=attach&inputFieldId=attachedFileNames");
}

function onDeleteAttach(attachId) {
  	    var yesno = confirm ("Are you sure you want to delete this attchment?");
	    if (yesno == true)
	    {
		    document.getElementById("deleteAttachId").value=attachId;
		    document.jobOrderAttachFilePopUpForm.submit();
		}
	    else
	      return false;
  }
  
function onSaveAttach() {
  document.getElementById("saveFlag").value='true';
  document.jobOrderAttachFilePopUpForm.submit(); 
} 

function submitEntryform(actionName) {
   if (canSubmitQuoteChange()){
    	if(actionName=="addJob"){
        document.createJobsCEForm.tabName.value="0";       
       }
        else {
        document.createJobsCEForm.tabName.value="1";       
        }
		document.createJobsCEForm.actionFlag.value=actionName;
		document.createJobsCEForm.submit();
	}
}

function canSubmitQuoteChange(){
	if (document.getElementById('quoteDate').value != ""){
		if (document.getElementById('quoteId').value != ""){
			return true;
		}else{
			confirm("Quote ID cannot be empty when Quote Issued Date is entered.");
			return false;
		}
	}
	return true;
}

function showParentJobSearch(rowIndex, buname) {
	document.getElementById('searchParentFr').setAttribute("src","phx_search.htm?searchType=job&targetFieldId=parentjob"+rowIndex+"&searchForm=createJobsCEForm&callerRowIndex="+rowIndex+"&divName=parentJob&divbody=parentbody"+rowIndex+"&buName="+buname);
}

function popJob() {
popup_show('parentJob',	'parent_drag', 'parent_exit', 'screen-corner', 120, 20);
hideIt();
popupboxenable();
showPopupDiv('parentJob','parentbody');
}

function showParentContractSearch(rowIndex, custCode) {
document.getElementById('searchParentContrFr').setAttribute("src","phx_search.htm?searchType=contractcust&targetFieldId=parentcontract"+rowIndex+"&searchForm=createJobsCEForm&callerRowIndex="+rowIndex+"&divName=parentcontract&divbody=parentcontractbody"+rowIndex+"&customerCode="+custCode);
 }
 
function popContract() {
popup_show('parentcontract','parentcontract_drag', 'parentcontract_exit', 'screen-corner', 120, 20);
hideIt();
popupboxenable();
showPopupDiv('parentcontract','parentcontractbody');
}




function goToNextPage(dateFormat,flag,jobType){
   if (!canSubmitQuoteChange()){
		return;
	}	
	var operation=document.getElementById("sel5").value;
	if(operation == "" || operation == null){
		confirm("Please select Operation.");
		return false;
	}	
	var salesRep=document.getElementById("salesRep").value;
	if(salesRep == "" || salesRep == null){
	if(jobType=="CE" || jobType=="CG"){
		confirm("Please select Sales Rep.");
		return false;
		}
	}	
	var servLoc=document.getElementById("serviceLoc").value;
	if(servLoc == "" || servLoc == null){
		confirm("Please select Servicelocation.");
		return false;
	}
	if(flag=='true'){
		confirm("Please select customer before you move to next page.");
		return false;
	}
	updateJobDescription(dateFormat);
	var form=document.createJobsCEForm;
	form.actionFlag.value="next";
	form.submit();
} 

function updateJobDescription(dateFormat,jobDescExist)
{
 var w = document.createJobsCEForm.sel5.selectedIndex;
 var ops_text = document.createJobsCEForm.sel5.options[w].text;

var etaDate=document.getElementById("etadate").value;
var finishDate=document.getElementById("finishdate").value;
var servLoc=document.getElementById("serviceLoc").value;
var date;
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
	
var desc = ops_text+" "+"of"+" "+" "+"on"+" "+"at"+" "+newServLoc+" "+"on"+" "+date;
if(jobDescExist == 'jobdescexist') {
  document.getElementById("jobDesc").value=desc;	 
   return desc; 
} 
  if(document.getElementById("jobDesc").value=="")  {
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
	
	function replceCodes(index)	{	
		var billingAddr = document.getElementById("billingContactAddress"+index).value;		
		billingAddr = replaceSpecialCharacterSymbols(billingAddr);
		billingAddr = replaceAll(billingAddr,"\\'","\'");
		var finalBillingAddressStr = replaceAll(billingAddr,"\\\"","\"");
		document.getElementById("billingContactAddress"+index).value = finalBillingAddressStr;
		
		var reportRecContactAddr = document.getElementById("reportReceivingContactAddress"+index).value;
		reportRecContactAddr = replaceSpecialCharacterSymbols(reportRecContactAddr);
		reportRecContactAddr = replaceAll(reportRecContactAddr,"\\'","\'");
		var finalreportRecContactAddr = replaceAll(reportRecContactAddr,"\\\"","\"");
		document.getElementById("reportReceivingContactAddress"+index).value = finalreportRecContactAddr;	
		
		var supplierContactAddr = document.getElementById("supplierContactAddress"+index).value;
		supplierContactAddr = replaceSpecialCharacterSymbols(supplierContactAddr);
		supplierContactAddr = replaceAll(supplierContactAddr,"\\'","\'");
		var finalsupplierContactAddr = replaceAll(supplierContactAddr,"\\\"","\"");
		document.getElementById("supplierContactAddress"+index).value = finalsupplierContactAddr;	
		
		var oemContactAddr = document.getElementById("oemContactAddress"+index).value;
		oemContactAddr = replaceSpecialCharacterSymbols(oemContactAddr);
		oemContactAddr = replaceAll(oemContactAddr,"\\'","\'");
		var finaloemContactAddr = replaceAll(oemContactAddr,"\\\"","\"");
		document.getElementById("oemContactAddress"+index).value = finaloemContactAddr;		
	    }
	
	   
		function differentCustData(index){
		if(!document.getElementById("differentCustomer"+index).checked){
		document.getElementById("custInfo"+index).style.visibility = "hidden";
		document.getElementById("custInfo").style.visibility = "hidden";
		}
		 else{
			document.getElementById("custInfo"+index).style.visibility = "visible";
			document.getElementById("custInfo").style.visibility = "visible";
			}
		}
		
		function rcvngDifferentCustData(index){
			if(!document.getElementById("differentReceiver"+index).checked){
			document.getElementById("recvngCustInfo"+index).style.visibility = "hidden";
			document.getElementById("recvngCustInfo").style.visibility = "hidden";
			}
			else{
			document.getElementById("recvngCustInfo"+index).style.visibility = "visible";
			document.getElementById("recvngCustInfo").style.visibility = "visible";
			}
        }
	function setBillAddressAsScheduler(index,billid,billname,billaddress)
	{
		if(document.getElementById('billAddressAsSched'+index).value == 1){
			document.getElementById('billingContactId'+index).value = billid;
			document.getElementById('billingContactName'+index).value = billname;
			document.getElementById('billingContactAddress'+index).value = replaceSpecialCharacterSymbols(billaddress);
			document.getElementById('billAddressAsSched'+index).value = 0;
		}else{
			document.getElementById('billingContactId'+index).value = 0;
			document.getElementById('billingContactName'+index).value = "";
			document.getElementById('billingContactAddress'+index).value = "";
			document.getElementById('billAddressAsSched'+index).value = 1 ;
			
		}
	}
	
	function setRecvAddressAsScheduler(index,recvId,recvName,recvAddress)
	{
		if(document.getElementById('recvAddressAsSched'+index).value == 1){
			document.getElementById('reportReceivingContactId'+index).value = recvId;
			document.getElementById('reportReceivingContactName'+index).value = recvName;
			document.getElementById('reportReceivingContactAddress'+index).value = replaceSpecialCharacterSymbols(recvAddress);
			document.getElementById('recvAddressAsSched'+index).value = 0;
		}else{
			document.getElementById('reportReceivingContactId'+index).value = 0;
			document.getElementById('reportReceivingContactName'+index).value = "";
			document.getElementById('reportReceivingContactAddress'+index).value = "";
			document.getElementById('recvAddressAsSched'+index).value = 1;
		}
	}
	
	
