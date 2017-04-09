String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}
function taskManagerPostAjax(){
	var text=document.getElementById('taskManagerFullName').value;
	var values = text.split('|');
	document.getElementById('taskManagerFullName').value = values[0].trim();
	document.getElementById('taskManagerId').value = values[1].trim();
}

 function showServiceLocationSearch(){
  var serviceLocationName= document.getElementById("serviceLoc").value;	  
	  var branchName=document.getElementById("brnch").value;
	  document.createJobsCEForm.tabName.value = "0";
	    if(serviceLocationName != "" && serviceLocationName != null) {  
		  document.getElementById('serv').setAttribute("src",src="phx_search.htm?searchType=serviceLocation&targetFieldId=serviceLocationName|serviceLoc&div1=servloc&div2=servlocbody&serviceLcoationName="+serviceLocationName + "&branchName="+branchName+"&searchForm=createJobsCEForm&sortBy=name");
		  } else { 
			  document.getElementById('serv').setAttribute("src",src="phx_search.htm?searchType=serviceLocation&targetFieldId=serviceLoc|serviceLocCode&div1=servloc&div2=servlocbody&serviceLcoationName="+serviceLocationName + "&branchName="+branchName+"&searchForm=createJobsCEForm&sortBy=name");   
	      }
 }
   

   function setServiceLcoationFlag()
  {        
    document.createJobsCEForm.serviceLocationFlag.value="servicelocation";
    document.createJobsCEForm.tabName.value = "0";    
  }
  
  function formatServiceLoc(str){
  	document.getElementById("serviceLoc").value = replaceSpecialCharacterSymbols(str);
  }

  function popServiceLocation()
  {
	  popup_show('servloc','servloc_drag','servloc_exit', 'screen-corner', 12, 5);
	  hideIt();
	  showPopupDiv('servloc','servlocbody');
	  popupboxenable();
  }

	 function popUserDetails(){
	  popup_show('userDetails','userDetails_drag','userDetails_exit', 'screen-corner', 120,  20);
	  hideIt();	
	  showPopupDiv('userDetails','userDetailsbody');
	  popupboxenable();
	 }


	 function popEmployeeDetails(){
		  popup_show('employeeDetails','employeeDetails_drag','employeeDetails_exit', 'screen-corner', 120,  20);
		  hideIt();	
		  showPopupDiv('employeeDetails','employeeDetailsbody');
		  popupboxenable();
	 }
	 
  function enterButton() {
  if (event.keyCode == 13) {
   if(document.getElementById("ccode").value!="") {
    setform();
   }
  }
}

function showWarehouseSearch(){	
	var buName=document.getElementById("sel3").value;
	if(buName == "" || buName == null){
		confirm("Please select BusinessUnit!");
		return true;
  	}
	document.createJobsCEForm.tabName.value = "0";
    if(buName!= "" && buName!= null){
    	//document.getElementById('jobbu').setAttribute("src", src="phx_search.htm?searchType=warehouse&targetFieldId=warehouseName&div1=jobbranchcode&div2=jobbranchcodebody&buName="+buName+"&formName=createJobsCEForm");
    	document.getElementById('jobbu').setAttribute("src",src="phx_search.htm?searchType=warehouse&targetFieldId=warehouseName&div1=jobbranchcode&div2=jobbranchcodebody&buName="+buName+"&searchForm=createJobsCEForm");    	
        popup_show('jobbranchcode', 'jobbranchcode_drag', 'jobbranchcode_exit', 'screen-corner', 120, 20);
  	  	hideIt();
  	  	showbranchcode('jobbranchcode','jobbranchcodebody');
  	  	popupboxenable();
    }
  }
  
  
  
  
/*for generic popup*/
function showSalesRepSearch(){    
    document.getElementById('recievedFr').setAttribute("src",src="phx_search.htm?searchType=user&targetFieldId=salesPersonName&div1=userDetails&div2=userDetailsbody&searchForm=createJobsCEForm&role=salesRep&sortBy=loginName");
}

function showSecondarySalesRepSearch(){
    document.getElementById('recievedFr').setAttribute("src",src="phx_search.htm?searchType=user&targetFieldId=secondarySalesPersonName&div1=userDetails&div2=userDetailsbody&searchForm=createJobsCEForm&role=secondarySalesRep&sortBy=loginName");
}

function showProjectManagerSearch(){
   document.getElementById('searchEmployeeFrame').setAttribute("src",src="phx_search.htm?searchType=employee&targetFieldId=projectManagerFullName|projectManagerName&div1=employeeDetails&div2=employeeDetailsbody&searchForm=createJobsCEForm&sortBy=lastName,firstName");
}

function showBankSearch(rowIndex,buname,currency){
	document.getElementById('sremittoframe').setAttribute("src",src="phx_search.htm?searchType=bank&targetFieldId=remitto" +rowIndex +
		"&buName="+buname+"&currency="+currency+"&searchForm=createJobsCEForm");
}

function showBankAccountSearch(rowIndex, buname,currency){
 var bcode=document.getElementById("remitto"+rowIndex).value;
 document.getElementById('saccountframe').setAttribute("src",src="phx_search.htm?searchType=bankaccount&targetFieldId=bankaccount" + rowIndex +
 		"&buName="+buname+"&currency="+currency+"&bankCode="+bcode+"&searchForm=createJobsCEForm");
}

function updateContactIframeSrc(rowIndex,customercode, contactType){
	var differentCustomer;
	if ("billing"==contactType){
		differentCustomer = document.getElementById('differentCustomer'+rowIndex).checked;
	}else if ("repreceiving"==contactType){
		differentCustomer = document.getElementById('differentReceiver'+rowIndex).checked;
	}
	var url = "phx_search.htm?searchType=contactCust&targetFieldId="+contactType+"&searchForm=createJobsCEForm&callerRowIndex="+rowIndex+"&divName=searchcontact&divbody=contactbody"+rowIndex+"&sortBy=c.customer.name,c.contact.lastName";
	//confirm("is it differntCustomer? " + differentCustomer);
	if (!differentCustomer){
		url += "&custCode="+customercode;
	}
	//confirm('url = ' + url);
	document.getElementById('searchContactFr').setAttribute("src",url);
}

/* up to here */  
  
 function popContact(){ 
  popup_show('searchcontact',	'searchcontact_drag', 'searchcontact_exit', 'screen-corner', 180,140);
  hideIt();
  showPopupDiv('searchcontact','contactbody'); 
  popupboxenable();
 } 
  
  
function updateBranchIframeSrc()
{	
var buName= document.getElementById("sel3").value;
if(buName == "" || buName == null){
	confirm("Please select BusinessUnit!");
	return true;
	}
  if(buName!= "" && buName!= null){
   document.getElementById('jobbu').setAttribute("src",src="search_branch_popup.htm?inputFieldId=warehouseName&div1=jobbranchcode&div2=jobbranchcodebody&buName="+buName+"&formName=createJobsCEForm");
      popup_show('jobbranchcode', 'jobbranchcode_drag', 'jobbranchcode_exit', 'screen-corner', 120, 20);
	  hideIt();
	  showbranchcode('jobbranchcode','jobbranchcodebody');
	  popupboxenable();
  }
}

function popJobBranch(){
 popup_show('jobbranchcode', 'jobbranchcode_drag', 'jobbranchcode_exit', 'screen-corner', 120, 20);
	  hideIt();
	  showbranchcode('jobbranchcode','jobbranchcodebody');
	  popupboxenable();
}

function popServiceOffering(){
 popup_show('serviceoff', 'serviceoff_drag', 'serviceoff_exit', 'screen-corner', 120, 20);
	  hideIt();
	  showbranchcode('serviceoff','serviceoffbody');
	  popupboxenable();
}




function makeBranchblank()
{
  document.getElementById("brnch").value="";
}

function showRequiredFields(){
	var myFrame=document.getElementById("requiredFieldsFrame");
	myFrame.src="required_fields.htm";
	popup_show('requiredFields', 'requiredFields_drag', 'requiredFields_exit', 'screen-corner', 120, 20);
	hideIt();
	popupboxenable();
}

/*remove the below function once the generic popup is working fine.
function updateBankAccountIframe(buname,currency){
var bcode=document.getElementById("remitto").value;
document.getElementById('saccountframe').setAttribute("src","search_bank_account_popup.htm?inputFieldId=remitToBankAccountNum&buName="+buname+"&currency="+currency+"&bankCode="+bcode);
}


function updateBankIframe(buname,currency){ 	
document.getElementById('sremittoframe').setAttribute("src","search_bank_popup.htm?inputFieldId=remitToCode&buName="+buname+"&currency="+currency);
}

 up to here*/

//these functions were moved to ce_jobentry.js file
/*function updateCustomerAddNoteIframeSrc(jobNumber){
	document.getElementById('customerNoteFr').setAttribute("src","phx_job_order_note_popup.htm?id="+jobNumber+"&divName=note");
}

function updateCustomerAttachIframeSrc(jobNumber){
document.getElementById('customerAttachFileFr').setAttribute("src","phx_job_order_attachfile_popup.htm?jobNumber="+jobNumber+"&divName=attach&inputFieldId=attachedFileNames");
}*/

function updateCustomerIframeSrc(customerExist) {
	if (customerExist){
		confirm("You cannot add more than one customer. Please delete the existing one and add again.");
		return true;
	}
	document.createJobsCEForm.tabName.value = "1";	
	var code = document.getElementById('ccode').value;
	if (code == null || code == "") {
		confirm("Please Enter search string(s)");
		return true;
	}
	if (code != null && code != ""){
		document.getElementById('customerFr').setAttribute("src", 'phx_search.htm?searchType=contractCustContact&targetFieldId=inputFieldIdValue&div1=customerSearch&div2=customerSearchbody&searchForm=createJobsCEForm&sortBy=c.contractCust.customer.name&searchValue=' + code);
		popup_show('customerSearch','customerSearch_drag','customerSearch_exit', 'screen-corner', 120,20);
		hideIt();
		showPopupDiv('customerSearch','customerSearchbody');
		popupboxenable();
	}
}

function billingContactAjax()
{
  document.createJobsCEForm.jobFlag.value="none";
  document.createJobsCEForm.deleteFlag.value ="none";
  document.createJobsCEForm.contactFlag.value="contFlag";
  document.createJobsCEForm.tabName.value="1";
  document.createJobsCEForm.submit();
}

function setbankFlag(index){
document.createJobsCEForm.bankFlag.value="bankFlag";
document.createJobsCEForm.tabName.value="1";
document.createJobsCEForm.bankIndex.value=index;
document.createJobsCEForm.submit();
}

function replaceHtmlCode(address){
	var newAddr;
	newAddr = address.replace(/&#045;/gi, "-");
	newAddr = newAddr.replace(/&#035;/gi, "#");
	return newAddr;
}

function showError(ecode,sc)
{
if(sc!="")
{	
 confirm("some or all of the entered criteria resulted in error. Review the errors below.\n No products were find matching the entered criteria "+ ecode +". change the criteria and try again");
 document.getElementById("ccode").value=sc;
 document.createJobsCEForm.submit();
}
else{
confirm("some or all of the entered criteria resulted in error. Review the errors below.\n No products were find matching the entered criteria "+ ecode +". change the criteria and try again");
document.createJobsCEForm.ccode.focus();
document.createJobsCEForm.ccode.value="";
}
}



function setform()
{
  var code=document.getElementById("ccode");
  confirm("Searching CECustomer by " + code.value);
  if(code.value==null || code.value=="")
  {
    confirm("Please Enter A customer name or contact name");
    return false;
  }
  document.createJobsCEForm.submit();
   
}

function setDeleteflag(index, contract)
{
if (confirm("Are you sure you want to delete customer associated with contract " + contract + "?"))
{
// document.createJobsCEForm.jobFlag.value="none";
 document.createJobsCEForm.contractIndex.value = index;
 document.createJobsCEForm.actionFlag.value ="deleteCustomer";
 document.createJobsCEForm.tabName.value = "1";
 document.createJobsCEForm.submit();
}
}
function resetJobValFlag()
{
 document.createJobsCEForm.contactFlag.value="none";
 document.createJobsCEForm.jobFlag.value="none";
 document.createJobsCEForm.popFlag.value="none";
  document.createJobsCEForm.uniqueFlag.value="none";
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
 document.createJobsCEForm.submit();
}
else{ 
 setpage();
}	
}



function isAdd(string) 
{
   if (!string) return false;
   var iChars = "*|,\":<>[]{}`\()&$#%./!@~?><";
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


//quick search funciton
function checkSearchField()
{
  if(document.getElementById("searchValue").value=="")
  {
  confirm("Please enter a search string");
  return false;
  }
  else{
	    if (confirm("All the modifications you have done will not be saved. Do you want to continue?")==true)
	    {
	    
	    var w = document.getElementById('sel1').selectedIndex;
        var ops_text = document.getElementById('sel1').value;	    
        var svalue=document.getElementById("searchValue").value; 
        document.getElementById('searchFr').setAttribute("src","phx_search_ce_job_popup.htm?searchString="+ops_text+"&searchValue="+svalue+"&div1=searchDetails&div2=searchDetailsbody");       
	    }
	    else{
	      return false;
	    }  
	}
}
//quick search funciton
function popSearchDetails(){
	  parent.popup_show('searchDetails','searchDetails_drag','searchDetails_exit', 'screen-corner', 120,  20);	 
	  parent.hideIt();	
	  parent.showPopupDiv('searchDetails','searchDetailsbody');	  
	  parent.popupboxenable();	  
	 }	 

//quick search function.
function revertToEntry(jobNumber,jobType){
if(jobType=='CE'|| jobType=='FST' || jobType=='CG')
window.parent.location ='phx_job_entry_ce.htm?jobNumber='+jobNumber;
else if(jobType=='INSP')
window.parent.location ='edit_job_entry_insp.htm?jobNumber='+jobNumber;
else if(jobType=='FST')
window.parent.location ='edit_job_entry_analytical_service.htm?jobNumber='+jobNumber;
else if(jobType=='MAR')
window.parent.location ='edit_job_entry_marine.htm?jobNumber='+jobNumber;
else if(jobType=='OUT')
window.parent.location ='edit_job_entry_outsourcing.htm?jobNumber='+jobNumber;
//window.parent.location ='phx_job_entry_ce.htm?jobNumber='+jobNumber;


}


function quickSearchMessage(){
confirm("The search string does not have any records! ");
document.getElementById("searchValue").focus();
document.getElementById("searchValue").value="";
}

function setsubflag()
{
//document.createJobsCEForm.jobValFlag.value="newjobval";
//document.createJobsCEForm.tabName.value = "1";
}


function setAttachFilesFlag()
{
  //document.createJobsCEForm.attachFilesFlag.value=rowIndex;
  //document.createJobsCEForm.tabName.value="1";
}

function submitform()
    {
      top.document.forms[0].submit();  
    }


function setpage()
{
document.createJobsCEForm.ccode.value="";
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

function changeVisibleStyle(){
    document.getElementById("serviceLoc").value="";
}


 
  function contractAttach(contractCode,index)
  {
   document.getElementById('contractFr'+index).setAttribute("src","view_contract_attach_popup.htm?contractCode="+contractCode);
  }
 
function selectJobDateBoolean(flag)
 {
 document.createJobsCEForm.submit();
 }
 


function setflag(scode,pageNumber)
{  
//document.createJobsCEForm.jobFlag.value="jobval";
//document.createJobsCEForm.tabName.value = "1";
var buname=document.getElementById("sel3").value;
var pageN0=pageNumber;
var code= scode;
if(scode.charAt("/"))
{ code=scode.replace("/","'");}
if(code!= "" && code!= null)
{
document.getElementById("pu").setAttribute("src","search_ce_customer_popup.htm?inputFieldId=inputFieldIdValue&searchForm=createJobsCEForm&code="+code+"&pageNumber="+pageN0+"&buName="+buname+"&fromPage=1");
}
}

function setCode(scode)
{  
var code=scode;
if(scode.charAt("/"))
{ code=scode.replace("/","'");}
document.createJobsCEForm.ccode.value=code;
//document.createJobsCEForm.uniqueFlag.value="uniqueFlag";
document.createJobsCEForm.submit();
}

function setNomCheck(index)
{
  document.getElementById("nom").checked = true;
}

function submitSearch(pageNumber){	 
//	document.ceCustomerSearchPopUpForm.pageNumber.value = pageNumber;
//	document.ceCustomerSearchPopUpForm.excel.value="false";
//	document.ceCustomerSearchPopUpForm.submit();
	var url = document.location;
	var urlString = url.toString();

	var pageNoPos = urlString.indexOf("pageNo");
	if ( pageNoPos != -1){
		var partOne = urlString.substr(0, pageNoPos);
		var partTwo = urlString.substr(pageNoPos, urlString.length);
		url = partOne + "pageNo=" + pageNumber;
	}else{
		url += "&pageNo="+pageNumber;
	}
	document.location = url;
}	


/* for job instructions*/

	function onSaveLines()
	{
		document.jobOrderOpInstrCEForm.addOrDeleteTestLines.value="save";	  
		document.jobOrderOpInstrCEForm.scrollFlag.value="testLine";
		top.document.forms[0].submit(); 
	}

	function onSaveLinesAndNext(projectType,projectId)
	{
	    
		var flg = true;
		if(projectType=='TYPE_1' && projectId==''){
			flg=confirm('Are you sure no project is required?');
		}
		if(flg==true){
			document.jobOrderOpInstrCEForm.addOrDeleteTestLines.value="saveandnext";	  
			document.jobOrderOpInstrCEForm.scrollFlag.value="testLine";
			top.document.forms[0].submit();
		} 
	}


	
	function onProject()
	{
	  if(confirm('Are you sure you want to create the project? Have you verified the line items?')==true){
             document.jobOrderOpInstrCEForm.projectFlag.value="true";	
	          submitform();
      }
	
	}
	
	function onSendWebService(webServiceType)
	{
	document.jobOrderOpInstrCEForm.webServiceFlag.value = webServiceType;
	submitform();
	} 

//	function updateNoteIframeSrc(index,jobNumber,lineId,splitId){
//	document.getElementById('jobTestNoteFr'+index).setAttribute("src","job_instruction_note_popup.htm?JobOrderNumber="+jobNumber+"&orderLineId="+lineId+"&splitLineId="+splitId+"&divName=note"+index);
//	}

	//function updateAttachIframeSrc(index,jobNumber,lineId,splitId){	
	//document.getElementById('jobContractAttachFileFr'+index).setAttribute("src","job_instruction_attachfile_popup.htm?JobOrderNumber="+jobNumber+"&orderLineId="+lineId+"&splitLineId="+splitId+"&divName=attach"+index+"&inputFieldId=attachedFileNames");
	//}


		function onDelete(noteId){
			document.getElementById("deleteNoteId").value=noteId;
			document.NotePopUpForm.submit();
		}

		function onSelectNote(noteId){
			document.getElementById("selectedNoteId").value=noteId;
			document.NotePopUpForm.submit();
		 }

		function onReset(){
			document.getElementById("reset").value="reset";
			document.NotePopUpForm.submit();
		}

		function confirmDelete(){
			var yesno = confirm ("Are you sure you want to delete this note?");
			if (yesno == true)
				return true;
				else
			return false;
		 }

		function beforesubmit(){
			if(document.NotePopUpForm.notesummary.value.length==0){
				confirm("Please fill Summary ");
				setFocus(document.NotePopUpForm.notesummary);
				}else{
			document.NotePopUpForm.submit();
			}
		}
          function jobInstructionNoteSubmit(){
			if(document.NotePopUpForm.noteSummary.value.length==0){
				confirm("Please fill Summary ");
				setFocus(document.NotePopUpForm.noteSummary);
				}else{
			document.NotePopUpForm.requestAction.value="save";	
			document.NotePopUpForm.submit();
			}
		}

		//Set Focus
		function setFocus(id){
		try {
		id.focus();
		}
		catch (e){}
		}

function onDeleteAttach(attachId)
  {
  	    var yesno = confirm ("Are you sure you want to delete this attchment?");
	    if (yesno == true)
	    {
		    document.getElementById("deleteAttachId").value=attachId;
		    document.jobAttachFilePopUpForm.submit();
		}
	    else
	      return false;
  }
  
  function onSaveAttach(attachId, desc, type)
  {
    document.getElementById("saveFlag").value='true';
    document.jobAttachFilePopUpForm.submit();
  }

/*	function saveTestAttrib(prodIndex,testIndex){
	    confirm('saveTest');		
		document.jobOrderOpInstrCEForm.addOrDeleteTestLines.value="saveother";
		document.jobOrderOpInstrCEForm.prodIndex.value=prodIndex;
		document.jobOrderOpInstrCEForm.testIndex.value=testIndex;
		//document.jobOrderOpInstrCEForm.splitIndex.value=splIndex;
		top.document.jobOrderOpInstrCEForm.submit(); 	
	}
*/	


	function searchPopups(entity,inputField)
   {
	url="";
	var callingFunction="";
	if(entity=='branchid')
	{
      url="phx_search.htm?searchType=warehouse&targetFieldId="+inputField+"&searchForm=jobInstructionTestAttribForm";
	}
	else if(entity=='servicelocation')
	{
     url="phx_search.htm?searchType=serviceLocation&targetFieldId="+inputField+"&searchForm=jobInstructionTestAttribForm";
	} 
	else if(entity=='ponumber')
	{
     url="phx_search.htm?searchType=po&targetFieldId="+inputField+"&searchForm=jobInstructionTestAttribForm";
	}
	else if(entity=='taskManagerLookup'){
	
     url="phx_search.htm?searchType=employee&targetFieldId="+inputField+"&searchForm=jobInstructionTestAttribForm";
     
	}	
	else if(entity=='taskmanage')
	{
	
     url="phx_search.htm?searchType=user&targetFieldId="+inputField+"&searchForm=jobInstructionTestAttribForm";
     
	}
	//joblogCE window...............
	else if(entity=='projectManager')
	{
    url="phx_search.htm?searchType=user&targetFieldId="+inputField+"&searchForm=jobCeSearchForm";
	}
	else if(entity=='serviceLocation')
	{
     url="phx_search.htm?searchType=serviceLocation&targetFieldId="+inputField+"&searchForm=jobCeSearchForm";
	} 
	else if(entity=='taskManager' )
	{
    url="phx_search.htm?searchType=user&targetFieldId="+inputField+"&searchForm=jobCeSearchForm";
	}
	else if(entity=='salesRep' )
	{	
    url="phx_search.htm?searchType=user&targetFieldId="+inputField+"&searchForm=jobCeSearchForm";
	}
	else if(entity=='reviewerName' )
	{
    url="phx_search.htm?searchType=user&targetFieldId="+inputField+"&searchForm=jobCeSearchForm";       
	}
	else if(entity=='taskOwningOrg' )
	{
     url="phx_search.htm?searchType=warehouse&targetFieldId="+inputField+"&searchForm=jobCeSearchForm";
	} 	
	//newwindow=window.open(url,entity,'height=630,width=600,resizable=yes');
	newwindow=window.open(url,'lookup','height=630,width=600,resizable=no');
	if (window.focus) {newwindow.focus()}
	return false;
}

function showSearchPageinIFrame(iFrameName,html,fieldId,div1name,div2name,formName)
{
 url = +"'"+html+"?inputFieldId="+fieldId+"&div1="+div1name+"&div2="+div2name+"&formName="+formName + "'";
 document.getElementById(iFrameName).setAttribute("src",src=url); 
}

function doSubmit(flag,index)
{
 document.jobInstructionTestAttribForm.workertimeFlag.value=flag;
 document.jobInstructionTestAttribForm.rowNum.value=index;
 document.jobInstructionTestAttribForm.submit(); 
}


function submitPOSearch(pageNumber){  
	document.purchaseOrderPopupForm.pageNumber.value = pageNumber;
	document.purchaseOrderPopupForm.submitFlag.value="true";
	document.purchaseOrderPopupForm.submit();
}	

function submitFunction(){
	document.purchaseOrderPopupForm.pageNumber.value = "1";
	document.purchaseOrderPopupForm.sortFlag.value = "";
	document.purchaseOrderPopupForm.submitFlag.value="true";
	document.purchaseOrderPopupForm.submit();
}

function sortPOByNumber(){
	document.purchaseOrderPopupForm.pageNumber.value = "1";
	document.purchaseOrderPopupForm.sortFlag.value = "poNumber";
	document.purchaseOrderPopupForm.submit();
}
function sortPOByBalanceAmount(){
	document.purchaseOrderPopupForm.pageNumber.value = "1";
	document.purchaseOrderPopupForm.sortFlag.value = "balanceAmount";
	document.purchaseOrderPopupForm.submit();
}

function goToNext(){
	 document.jobOrderOpInstrCEForm.nextPageFlag.value = "2";
	 document.jobOrderOpInstrCEForm.submit();
}
function save()
{
  document.jobInstructionTestAttribForm.workertimeFlag.value='save';
  document.jobInstructionTestAttribForm.submit();
}

function searchContact(){
	document.ceContactSearchPopUpForm.submit();
}

function submitFrameForm(frameName)
{
	window.frames[frameName].document.getElementById("requestAction").value="save";
	window.frames[frameName].document.forms[0].submit();
}

function searchjob()
{
	
  document.getElementById("requestAction").value="search";
  document.getElementById("tabNavigationTo").value="main";
  document.jobCeSearchForm.submit();
}

function navenable(tabNavigableTo)
{
  if(tabNavigableTo!='goto')
  {
   if(tabNavigableTo=='new search')
   {	
	document.getElementById("requestAction").value="new search";
	document.jobCeSearchForm.method="GET";
	tabNavigableTo='searchCriteria';	
   }
   else
   {
   	document.getElementById("requestAction").value="navigation";
   }
   document.getElementById("tabNavigationTo").value=tabNavigableTo;
   document.jobCeSearchForm.submit();
  }
}

function addRow(tableID,inputName)
{
            var table = document.getElementById(tableID);   
  			var rowCount = table.rows.length;
			fldName= inputName+"["+rowCount+"]";
			var size=42;
					
			tmpStr1='';
			if(tableID=='TaskTable' ||tableID=='ProjectManagerTable' || tableID=='salesRepTable')
			{
			  
				tmpStr1="&nbsp;<a href='#pm' onClick=\"javascript:lookupFunction('"+fldName+"','"+tableID+"');popUserDetails();\">";
			}else 
			if(tableID=='taskOwingTable' ||tableID=='ProjectOwning' )
			{
				tmpStr1="&nbsp;<a href='#pm' onClick=\"javascript:lookupFunction('"+fldName+"','"+tableID+"');popJobBranch();\">";
			}else 
			if(tableID=='ServiceOfferingTable')
			{
				tmpStr1="&nbsp;<a href='#pm' onClick=\"javascript:lookupFunction('"+fldName+"','"+tableID+"');popServiceOffering();\">";
			}
			else
			{
			    //tmpStr1="&nbsp;<a href='#pm' onClick=\"javascript:lookupFunction('"+fldName+"','"+tableID+"');popup_show('receivedby','receivedby_drag','receivedby_exit', 'screen-corner',	120,20);hideIt();showPopupDiv('receivedby','receivedbybody');popupboxenable();\">";
			}
			
			if(tableID=='ProjectManagerTable' || tableID=='taskOwingTable' )
			{
				size=38
			}
			if(tableID=='ServiceOfferingTable')
			{
			 tmpStr="<INPUT type=text id='"+fldName+"' class='inputBoxblue' name='"+fldName+"' size='"+size+"' maxlength='42'/>";
			}
			else
			{
			 tmpStr="<INPUT type=text id='"+fldName+"' class='inputBoxblue' name='"+fldName+"' size='"+size+"' maxlength='42'/>";
			}
			tmpStr=tmpStr+tmpStr1;
			tmpStr=tmpStr+"<img src='images/lookup.gif' alt='lookup Task Manager' width='13' height='13' border='0'></a>";
            var row = table.insertRow(rowCount);     
            var cell1 = row.insertCell(0);   			
			cell1.style.padding='0px';
			cell1.innerHTML =tmpStr;
			fld = inputName+rowCount;
			//if(tableID!='ServiceOfferingTable')
			{
				//includeAjaxObj(tableID,fld);
				includeAjaxObj(tableID,fldName);				
			}
}

function includeAjaxObj(tableID,fld)
{
		url='';				
		if(	tableID=='TaskTable')
		{
			receivedBy="entity=com.intertek.entity.User,textAttribute=loginName,valueAttribute=loginName,~loginName={"+fld+"}";
		}
		else 
		if(	tableID=='ProjectManagerTable')
		{
			receivedBy="entity=com.intertek.entity.User,textAttribute=loginName,valueAttribute=loginName,~loginName={"+fld+"}";
		}
		else 
		if(	tableID=='salesRepTable')
		{
			receivedBy="entity=com.intertek.entity.User,textAttribute=loginName,valueAttribute=loginName,~loginName={"+fld+"}";
		}
		else 
		if(	tableID=='taskOwingTable')
		{
			receivedBy="entity=com.intertek.phoenix.entity.Warehouse,textAttribute=name,valueAttribute=name,~name={"+fld+"}";
		}
		else
		if(	tableID=='ProjectOwning')
		{
			receivedBy="entity=com.intertek.phoenix.entity.Warehouse,textAttribute=name,valueAttribute=name,~name={"+fld+"}";
		}
		else
		if(	tableID=='ServiceOfferingTable')
		{
			receivedBy="entity=com.intertek.phoenix.entity.ServiceOffering,textAttribute=description,valueAttribute=description,~description={"+fld+"}";
		}
		
		new AjaxJspTag.Autocomplete(
		"phx_ajax.htm", {
		source: fld,
		target: fld,
		minimumCharacters: "3",
		className: "autocomplete",
		parameters:receivedBy 
		});
}

function showLookup(arg1,arg2,arg3,arg4)
{
	popup_show(arg1, arg2, arg3, arg4, 40, 80); 
	searchtaskmanagerht(); 
	hidetaskmanagersearch(); 
	hideIt();
	popupboxenable();
}
 
function lookupFunction(fieldName,lookupName)
{

	if(lookupName=='TaskTable' || lookupName=='ProjectManagerTable' || lookupName=='salesRepTable')
	{
		 document.getElementById('recievedFr').setAttribute("src","phx_search.htm?searchType=user&targetFieldId="+fieldName+"&searchForm=jobInstructionTestAttribForm");
	}
	else if(lookupName=='taskOwingTable' || lookupName== 'ProjectOwning')
	{		
		document.getElementById('jobbu').setAttribute("src","phx_search.htm?searchType=warehouse&targetFieldId="+fieldName+"&searchForm=jobInstructionTestAttribForm");
	}	
	else if(lookupName=='stream')
	{
		document.getElementById('buStreamFr').setAttribute("src","phx_search.htm?searchType=stream&targetFieldId="+fieldName+"&div1=buStream&div2=buStreambody");
	}
	else if(lookupName=='ServiceOfferingTable')
	{
		document.getElementById('serviceoffIdLookup').setAttribute("src",src="phx_search.htm?searchType=serviceoffering&targetFieldId="+fieldName+"&searchForm=jobCeSearchForm");   	
	}
} 
 
function gotoPage(pageNumber)
{

	if(pageNumber=='First')
	{
	   pageNumber="1";
	}
	else if(pageNumber=='Prev')
	{
	   if(document.jobCeSearchForm.pageNumber.value>0)
	   {
	   	pageNumber=document.jobCeSearchForm.pageNumber.value-1;
	   }
	}
	else if(pageNumber=='Next')
	{
	   if(document.jobCeSearchForm.pageNumber.value>0)
	   {
	   	pageNumber=document.jobCeSearchForm.pageNumber.value+1;
	   }
	}
	
	document.getElementById("requestAction").value="page";
	document.jobCeSearchForm.pageNumber.value = pageNumber;
	document.jobCeSearchForm.submit();
}

function sortByPage(fieldName)
{
	document.getElementById("requestAction").value="sortBy";
	document.getElemenntById("sortField").value=fieldName;
	document.jobCeSearchForm.pageNumber.value = 1;
	document.jobCeSearchForm.submit();
}

function lastSearch(totalRecords,numInPages)
{
	var pageNumber;
	var quotient=totalRecords/numInPages;
	var remainder=totalRecords%numInPages;	
	if(remainder==0)
	pageNumber=quotient;
	else
	pageNumber=Math.floor(quotient)+1;
	gotoPage(pageNumber);
}

function shortByField(fieldName)
{
  document.getElementById("requestAction").value="sort";
  document.getElementById("sortField").value=fieldName;
  //document.getElementById("tabNavigationTo").value="main";
  document.jobCeSearchForm.submit();
}

function exportToExcel()
{
  document.getElementById("requestAction").value="excelupload";
  document.jobCeSearchForm.submit();
}


function popCreditReason(index, invoiceId, viewFlag)
{  
  document.jobOrderEditViewCEInvoiceForm.index.value=index;
  popup_show('creditreason_' + index, 'creditreason_drag_' + index, 'creditreason_exit_' + index, 'screen-corner', 120, 20);
  hideIt();
  popupboxenable();
  document.getElementById("creditreasonbox_" + index).src="phx_credit_reason_ce_popup.htm?invoiceId=" + invoiceId +"&viewFlag=" +viewFlag +"&contractIndex="+index;              
  document.getElementById("creditreasonbox_" + index).height = "250px";
}

function onSave()
{
//document.jobOrderEditViewCEInvoiceForm.contractIndex.value=myIndex;
document.jobOrderEditViewCEInvoiceForm.submit();
}

function popCreditFlag(){
 document.jobOrderEditViewCEInvoiceForm.creditFlag.value="credit";
}
 function submitceform()
  {
  	top.document.forms[0].creditFlag.value = "credit";
  	top.document.forms[0].creditReasonNote.value = document.getElementById("note").value;
  	top.document.forms[0].creditDescription.value =document.getElementById("creditdesc").value;
  	top.document.forms[0].creditReasonUser.value = document.getElementById("user").value;
	top.document.forms[0].submit();
  }
  
  function validateFields(div1,div2,viewFlag)
  {
  
  	if(viewFlag != '' && viewFlag == 'readonly')
  	{
  			  	top.hidePopupDiv(div1,div2);
	  			top.popupboxclose();
	  			
  	}
  	else
  	{
	  	if(document.getElementById("note").value == '')
	  	{
	  		confirm("Please enter the credit reason note");
	  		return false;
	  	}
	  	  	if(document.getElementById("user").value == '')
	  	{
	  		confirm("Please enter the credit reason approval manager");
	  		return false;
	  	}
	  	
	  	submitceform();
	  	top.hidePopupDiv(div1,div2);
	  	popupboxclose();
	  	
  	}
  	
  }


   function formfocus() {
       document.getElementById('note').focus();
   }

   
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

   function createOrUpdateProject(form, jobNumber){
	   if(confirm('Are you sure you want to create the project? Have you verified the line items?')){
		   form.createProject.value="true";
		   onSaveLines();
	   }
   }
   
   function createOrUpdateProjectAjax(form, jobNumber){
   	if(confirm('Are you sure you want to create the project? Have you verified the line items?')){
   		var projectTypeIndex=form.projectType.selectedIndex;

   		initXmlHttp(function(){
   			if(xmlHttp.readyState==4){
   				var repo=xmlHttp.responseText;
   				if(repo!=jobNumber){
   					confirm("Failed to create project");
   				}
   				else{
   					form.projectId.value=repo;
   				}
   			}
   		});
   		xmlHttp.open("POST", "phx_create_project.htm", true);
   		var params="jobNumber="+jobNumber+"&projectType="+form.projectType.value;
   		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   		xmlHttp.setRequestHeader("Content-length", params.length);
   		xmlHttp.setRequestHeader("Connection", "close");   		
   		xmlHttp.send(params);
   	}
   }
   
   /*
    * return the query string for the given form
    */
   function getQueryString(form){
	   return "hello=1&x=true";
   }

function updateAddTestIframeSrc(jobNumber) {
   document.getElementById('addTestId').setAttribute("src", "phx_add_test.htm?jobNumber="+jobNumber+"&div1=addTest&div2=testbody");
   document.getElementById('addTestId').height = "500px";
}

function popAddTest() {
	popup_show('addTest', 'addTest_drag', 'addTest_exit', 'screen-corner', 40, 80);	
	showPopupDiv('addTest','testbody')
	//showAddTest();
	hideIt();
	popupboxenable();
}
function replaceAll(source,stringToFind,stringToReplace){
 var localVar = source;
 var index = localVar.indexOf(stringToFind);
 while(index != -1){
        localVar = localVar.replace(stringToFind,stringToReplace);
        index = localVar.indexOf(stringToFind);
  }
  return localVar;
}

function replaceSpecialCharacterSymbols(fileName)
{
	var multiSpecialCodes =new Array("amp;", "lt;", "gt;", "quot;", "035;", "037;", "039;", "040;", "041;", "043;", "045;", "059;");
	  for(var i = 0;i<multiSpecialCodes.length;i++) 
	  {
		  if(fileName.indexOf(multiSpecialCodes[i]) != -1) 
		  {
				if(multiSpecialCodes[i]=="amp;"){
                    fileName = replaceAll(fileName,"&amp;", "&");
                }
                else if(multiSpecialCodes[i]=="gt;"){
                    fileName = replaceAll(fileName,"&gt;", ">");
                }
                else if(multiSpecialCodes[i]=="quot;") {
                    fileName = replaceAll(fileName,"&quot;", "\"");
                }
                else if(multiSpecialCodes[i]=="lt;") {
                    fileName = replaceAll(fileName,"&lt;", "<");
                }
                else if(multiSpecialCodes[i]=="035;") {
                    fileName = replaceAll(fileName,"&#035;", "#");
                }
                else if(multiSpecialCodes[i]=="037;") {
                    fileName = replaceAll(fileName,"&#037;", "%");
                }
                else if(multiSpecialCodes[i]=="039;") {
                    fileName = replaceAll(fileName,"&#039;", "\'");
                }
                else if(multiSpecialCodes[i]=="040;") {
                    fileName = replaceAll(fileName,"&#040;", "(");
                }
                else if(multiSpecialCodes[i]=="041;") {
                    fileName = replaceAll(fileName,"&#041;", ")");
                }
                else if(multiSpecialCodes[i]=="043;") {
                    fileName = replaceAll(fileName,"&#043;", "+");
                }
                else if(multiSpecialCodes[i]=="045;") {
                    fileName = replaceAll(fileName,"&#045;", "-");
                }
                else if(multiSpecialCodes[i]=="059;") {
                    fileName = replaceAll(fileName,"&#059;", ";");
                }
            }
        }
      return fileName;
	}

function updateSampleTrckingIframeSrc(jobNumber)
{
		document.getElementById('sampletrackingFr').setAttribute("src", 'phx_sampletracking.htm?jobNumber='+jobNumber);
		sampleTrackPopup();
}

function addSampleTrckingIframeSrc()
{
        document.sampleTrackingCGForm.refreshing.value="addRow"; 
		document.sampleTrackingCGForm.submit();
		sampleTrackPopup();
}

function sampleTrackPopup()
{
		popup_show('sampletracking','sampletracking_drag', 'sampletracking_exit', 'screen-corner', 100, 30);
		hideIt();
		popupboxenable();
		showPopupDiv('sampletracking','contactbody');
}

function submitSampleTrackForm(){
	document.sampleTrackingCGForm.refreshing.value="save"; 
	document.sampleTrackingCGForm.submit();
}
