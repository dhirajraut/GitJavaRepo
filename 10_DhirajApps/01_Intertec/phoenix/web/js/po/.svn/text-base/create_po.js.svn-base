// JavaScript Document For Create Purchase Order

function onSave(){
	document.purchaseOrderCreateForm.submit();
}

function setCustomerSearchFlag(){
    document.purchaseOrderCreateForm.custFlag.value = "custval";
}
function setCustomerSearchFlagAjax(){
    document.purchaseOrderCreateForm.custFlag.value = "custval";
    document.purchaseOrderCreateForm.submit();
}
function sortPODByJobNumber(){
    document.purchaseOrderCreateForm.sortPODFlag.value = "jobOrderId";
    document.purchaseOrderCreateForm.submit();
}

function sortPODByDate(){
    document.purchaseOrderCreateForm.sortPODFlag.value = "taskReadyDate";
    document.purchaseOrderCreateForm.submit();
}

function sortPODByCommittedAmnt(){
    document.purchaseOrderCreateForm.sortPODFlag.value = "committedAmount";
    document.purchaseOrderCreateForm.submit();
}

function sortPODByBilledAmnt(){
    document.purchaseOrderCreateForm.sortPODFlag.value = "revenue";
    document.purchaseOrderCreateForm.submit();
}
function sortPODByLineNo(){
    document.purchaseOrderCreateForm.sortPODFlag.value = "lineNumber";
    document.purchaseOrderCreateForm.submit();
}
function editJob(jobNumber){  
	document.purchaseOrderCreateForm.editJobNumber.value = jobNumber;
	document.purchaseOrderCreateForm.submit();
}


function showCustomerSearchLookup(){       
        document.getElementById('custIdLookup').setAttribute("src",src="phx_search.htm?searchType=customer&targetFieldId=custCode&div1=customerid&div2=customeridbody&searchForm=purchaseOrderCreateForm");   	
        popup_show('customerid', 'customerid_drag', 'customerid_exit', 'screen-corner', 120, 20);        
  	  	hideIt();
  	  	showbranchcode('customerid','customeridbody');
  	  	popupboxenable();
} 

function customerSelected(custValue, targetFieldId){
	window.top.document.getElementById(targetFieldId).value = custValue;
	window.top.document.purchaseOrderCreateForm.custFlag.value = "custval";
	window.top.document.purchaseOrderCreateForm.submit();
}


// Edit PO JOLI section: Job ID Search
function showjobid(val){    
	document.getElementById("jobidContainers"+val).style.visibility = "visible"; 
	document.getElementById("jobidContainers"+val).style.display = "block"; 
	document.getElementById("bluarrowdown"+val).style.visibility = "visible"; 
	document.getElementById("bluarrowright"+val).style.visibility = "hidden";

}
function hidejobid(val){    
	document.getElementById("jobidContainers"+val).style.visibility = "hidden"; 
	document.getElementById("jobidContainers"+val).style.display = "none"; 
	document.getElementById("bluarrowright"+val).style.visibility = "visible";
	document.getElementById("bluarrowdown"+val).style.visibility = "hidden";

}

function showtotal(){
	document.getElementById("totalContainers").style.visibility = "visible"; 
	document.getElementById("totalContainers").style.display = "block"; 
	document.getElementById("bluarrowdownTotal").style.visibility = "visible"; 
	document.getElementById("bluarrowrightTotal").style.visibility = "hidden";

}
function hidetotal(){
	document.getElementById("totalContainers").style.visibility = "hidden"; 
	document.getElementById("totalContainers").style.display = "none"; 
	document.getElementById("bluarrowrightTotal").style.visibility = "visible";
	document.getElementById("bluarrowdownTotal").style.visibility = "hidden";

}
function makeBranchblank()
{
	document.getElementById('sel4').value='';
}
