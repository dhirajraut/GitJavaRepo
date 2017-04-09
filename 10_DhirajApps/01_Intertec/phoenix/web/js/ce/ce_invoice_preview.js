function tab0DepositeInvoice() {
	document.getElementById("invpreview").src = "phx_ce_job_invoice_preview_tab0.htm";
	document.ceInvoicePreviewForm.tabSource.value = "phx_ce_job_invoice_preview_tab0.htm";
	document.getElementById("generatedepositinvoice").style.display = "block";
	document.getElementById("generatedepositinvoice").style.visibility = "visible";
	document.getElementById("generateinvoice").style.display = "none";
	document.getElementById("generateinvoice").style.visibility = "hidden";
    document.getElementById("jobDesc").style.display = "none";
    document.getElementById("depInvDesc").style.display = "";
	MM_swapImage('tab0','','images/tab0DepositInvoice_act.gif','tab1','','images/tab1Pricing_deact.gif','tab2','','images/tab2Tax_deact.gif','tab5','','images/tab5TestnService_deact.gif','tab4','','images/tab4Viewall_deact.gif',1);
}

function tab1Pricing() {
	document.getElementById("invpreview").src = "phx_ce_job_invoice_preview_tab1.htm";
	document.ceInvoicePreviewForm.tabSource.value = "phx_ce_job_invoice_preview_tab1.htm";
	document.getElementById("generateinvoice").style.display = "block";
	document.getElementById("generateinvoice").style.visibility = "visible";
	document.getElementById("generatedepositinvoice").style.display = "none";
	document.getElementById("generatedepositinvoice").style.visibility = "hidden";
	document.getElementById("depInvDesc").style.display = "none";
    document.getElementById("jobDesc").style.display = "";
	MM_swapImage('tab0','','images/tab0DepositInvoice_deact.gif','tab1','','images/tab1Pricing_act.gif','tab2','','images/tab2Tax_deact.gif','tab5','','images/tab5TestnService_deact.gif','tab4','','images/tab4Viewall_deact.gif',1);
}

function tab2Tax() {
	document.getElementById("invpreview").src = "phx_ce_job_invoice_preview_tab2.htm";
	document.ceInvoicePreviewForm.tabSource.value = "phx_ce_job_invoice_preview_tab2.htm";
	document.getElementById("generateinvoice").style.display = "block";
	document.getElementById("generateinvoice").style.visibility = "visible";
	document.getElementById("generatedepositinvoice").style.display = "none";
	document.getElementById("generatedepositinvoice").style.visibility = "hidden";
	document.getElementById("depInvDesc").style.display = "none";
    document.getElementById("jobDesc").style.display = "";
	MM_swapImage('tab0','','images/tab0DepositInvoice_deact.gif','tab1','','images/tab1Pricing_deact.gif','tab2','','images/tab2Tax_act.gif','tab5','','images/tab5TestnService_deact.gif','tab4','','images/tab4Viewall_deact.gif',1);
}

function tab3ApplyDepositInvoice() {
	document.getElementById("invpreview").src = "phx_ce_job_invoice_preview_tab3.htm";
	document.ceInvoicePreviewForm.tabSource.value = "phx_ce_job_invoice_preview_tab3.htm";
	document.getElementById("generateinvoice").style.display = "block";
	document.getElementById("generateinvoice").style.visibility = "visible";
	document.getElementById("generatedepositinvoice").style.display = "none";
	document.getElementById("generatedepositinvoice").style.visibility = "hidden";
	document.getElementById("depInvDesc").style.display = "none";
    document.getElementById("jobDesc").style.display = "";
	MM_swapImage('tab0','','images/tab0DepositInvoice_deact.gif','tab1','','images/tab1Pricing_deact.gif','tab2','','images/tab2Tax_deact.gif','tab5','','images/tab5ApplyDepositInvoice_act.gif','tab4','','images/tab4Viewall_deact.gif',1);	
}

function tab4ViewAll() {
	document.getElementById("invpreview").src = "phx_ce_job_invoice_preview_tab4.htm";
	document.ceInvoicePreviewForm.tabSource.value = "phx_ce_job_invoice_preview_tab4.htm";
	document.getElementById("generateinvoice").style.display = "block";
	document.getElementById("generateinvoice").style.visibility = "visible";
	document.getElementById("generatedepositinvoice").style.display = "none";
	document.getElementById("generatedepositinvoice").style.visibility = "hidden";
	document.getElementById("depInvDesc").style.display = "none";
    document.getElementById("jobDesc").style.display = "";
	MM_swapImage('tab0','','images/tab0DepositInvoice_deact.gif','tab1','','images/tab1Pricing_deact.gif','tab2','','images/tab2Tax_deact.gif','tab5','','images/tab5TestnService_deact.gif','tab4','','images/tab4Viewall_act.gif',1);
}

function selecttab() {
	if(document.getElementById("tabSource").value == "phx_ce_job_invoice_preview_tab0.htm") {
		tab0DepositeInvoice();
	}
	if(document.getElementById("tabSource").value == "phx_ce_job_invoice_preview_tab1.htm") {
		tab1Pricing();
	}
	if(document.getElementById("tabSource").value == "phx_ce_job_invoice_preview_tab2.htm") {
		tab2Tax();
	}
	if(document.getElementById("tabSource").value == "phx_ce_job_invoice_preview_tab3.htm") {
		tab3ApplyDepositInvoice();
	}
	if(document.getElementById("tabSource").value == "phx_ce_job_invoice_preview_tab4.htm") {
		tab4ViewAll();
	}


}

function previewInvoice() {
    top.document.ceInvoicePreviewForm.refreshing.value="previewInvoice";
    submitActiveTabForm();
    top.document.ceInvoicePreviewForm.submit();
}  

/*function save() {
    top.document.ceInvoicePreviewForm.refreshing.value="save";
    top.document.ceInvoicePreviewForm.submit();
}*/

function generateInvoice() {
		if(confirm("Are you sure you want to generate invoice?")==false) {
			document.ceInvoicePreviewForm.GenerateInv.disabled = false;
			
			return false;
		}
		else {
			submitActiveTabForm();		
			doParentSubmit('generateInvoice');
		  }	
}

function generateDepositInvoice() {
	if(confirm("Are you sure you want to generate deposit invoice?")==false) {
		document.ceInvoicePreviewForm.GenerateDepositInv.disabled = false;
		return false;
	}
	else {
		document.ceInvoicePreviewForm.GenerateDepositInv.disabled = true;
		top.document.invpreview.ceJobInvoicePreviewTab0Form.submit();
		doParentSubmit('generateDepositInvoice');
	}
}
 
function doParentSubmit(refreshing) {
	document.ceInvoicePreviewForm.refreshing.value=refreshing;
	tabsrc = document.ceInvoicePreviewForm.tabSource.value;
    document.ceInvoicePreviewForm.submit();
}  

function submitActiveTabForm() {

	if(top.document.invpreview.ceJobInvoicePreviewTab0Form!=null) {
		top.document.invpreview.ceJobInvoicePreviewTab0Form.submit();
	}
	if(top.document.invpreview.ceJobInvoicePreviewTab1Form!=null) {	
		top.document.invpreview.ceJobInvoicePreviewTab1Form.submit();
	}
	if(top.document.invpreview.ceJobInvoicePreviewTab2Form!=null) {
		top.document.invpreview.ceJobInvoicePreviewTab2Form.submit();
	}
	if(top.document.invpreview.ceJobInvoicePreviewTab3Form!=null) {
		top.document.invpreview.ceJobInvoicePreviewTab3Form.submit();
	}
	if(top.document.invpreview.ceJobInvoicePreviewTab4Form!=null) {
		top.document.invpreview.ceJobInvoicePreviewTab4Form.submit();
	}
}
 
function onTaxCodeorDateChange() {  
  	top.document.forms[0].refreshing.value="calc";
    top.document.forms[0].tabSource.value="phx_ce_job_invoice_preview_tab2.htm";
	top.document.invpreview.ceJobInvoicePreviewTab2Form.submit();   	
}
function onTaxCodeViewAllDateChange() {  
  	top.document.forms[0].refreshing.value="calc";
    top.document.forms[0].tabSource.value="phx_ce_job_invoice_preview_tab4.htm";
	top.document.invpreview.ceJobInvoicePreviewTab4Form.submit();   	
}

function updateApplyDepositIframeSrc(lineItemNumber){
	top.document.getElementById('applyDepositFrId').setAttribute("src", "phx_apply_deposit_popup.htm?lineItemNumber="+lineItemNumber);
}

function updateRevenueSegregationIframeSrc(lineItemNumber){
	top.document.getElementById('revenueSegregationFrId').setAttribute("src", "phx_revenue_segregation_popup.htm?lineItemNumber="+lineItemNumber);
}

function showapplydeposit() {
	document.getElementById("applydeposit").style.display = "block";
	
}

function hideapplydeposit() {
	document.getElementById("applydeposit").style.visibility="hidden";
	document.getElementById("applydeposit").style.display = "none";
	showIt();
}

function showrevenuesegregation() {
	document.getElementById("revenuesegregation").style.display = "block";
	
}
function hiderevenuesegregation() {
	document.getElementById("revenuesegregation").style.visibility="hidden";
	document.getElementById("revenuesegregation").style.display = "none";
	showIt();
}

function addSplitLineItem(invLineItemIndex) {
    top.document.forms[0].invLineItemIndex.value = invLineItemIndex;
    top.document.forms[0].refreshing.value="addSplitLineItem";
    top.document.forms[0].submit();
}

function deleteLineItem(invLineItemIndex) {
    top.document.forms[0].invLineItemIndex.value = invLineItemIndex;
    top.document.forms[0].refreshing.value="deleteLineItem";
    top.document.forms[0].submit();
}

function deleteSplitLineItem(invLineItemIndex, splitItemIndex) {
    top.document.forms[0].invLineItemIndex.value = invLineItemIndex;
    top.document.forms[0].splitLineItemIndex.value = splitItemIndex;
    top.document.forms[0].refreshing.value="deleteSplitLineItem";
    top.document.forms[0].submit();
}

function saveNext() {
	document.forms[0].refreshing.value="next";
	submitActiveTabForm();
	doParentSubmit('next');
}

function saveip() {
    document.forms[0].refreshing.value="save";
	submitActiveTabForm();		
	doParentSubmit('save');
}


function doMySubmit(refreshing)
  {
  	var tabsrc = document.ceInvoicePreviewForm.tabSource.value;
  	if(tabsrc == 'phx_ce_job_invoice_preview_tab0.htm'){
      	tab0DepositeInvoice();
      }
    if(tabsrc == 'phx_ce_job_invoice_preview_tab1.htm')
      tab1Pricing();
    if(tabsrc == 'phx_ce_job_invoice_preview_tab2.htm'){
    	top.document.invpreview.ceJobInvoicePreviewTab2Form.refreshing.value=refreshing;
     	tab2Tax();
     	top.document.invpreview.ceJobInvoicePreviewTab2Form.submit();
     }
    if(tabsrc == 'phx_ce_job_invoice_preview_tab3.htm')
     tab3ApplyDepositInvoice();
    if(tabsrc == 'phx_ce_job_invoice_preview_tab4.htm'){
    	top.document.invpreview.ceJobInvoicePreviewTab4Form.refreshing.value=refreshing;
        tab4ViewAll();
     	top.document.invpreview.ceJobInvoicePreviewTab4Form.submit();
       
       }
  }  

  function updateInvoicePreviewDescription(description) {
    if(document.getElementById("depInvDesc").value==''){
   		document.getElementById("depInvDesc").value=description;
   }
   else
   if(document.getElementById("jobDesc").value==''){
   		document.getElementById("jobDesc").value=description;
   }
  }
