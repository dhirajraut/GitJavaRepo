function updateSCAddManualTestIframeSrc(jobNumber, productId){
   document.getElementById('addTestId').setAttribute("src", "phx_add_manual_test.htm?jobNumber="+jobNumber+"&productId="+productId+"&div1=addTest&div2=testbody");
   document.getElementById('addTestId').height = "500px";
}


function updateSCAddTestIframeSrc(jobNumber, productId) {
   document.getElementById('addTestId').setAttribute("src", "phx_add_test.htm?jobNumber="+jobNumber+"&productId="+productId+"&div1=addTest&div2=testbody");
   document.getElementById('addTestId').height = "500px";
}

function popAddTest() {
	popup_show('addTest', 'addTest_drag', 'addTest_exit', 'screen-corner', 40, 80);
	showAddTest();
	hideIt();
	popupboxenable();
}

function updateJobServicesIframeSrc(jobNumber, productId) {
   document.getElementById('addJSId').setAttribute("src", "phx_add_job_services.htm?jobNumber="+jobNumber+"&productId="+productId);
   document.getElementById('addJSId').height = "90px";
}

function popJobServices() {
	popup_show('addJS', 'addJS_drag', 'addJS_exit', 'screen-corner', 40, 80);
	showAddJS();
	hideIt();
	popupboxenable();
}

function saveNextSelectCharges() {
	document.ceJobSelectChargesForm.refreshing.value="next";
	document.ceJobSelectChargesForm.submit();
}

function saveSelectCharges() {
    document.ceJobSelectChargesForm.refreshing.value="save";
	document.ceJobSelectChargesForm.submit();
}

function updateEditPriceIframeSrc(joliId) {
   document.getElementById('editPriceFrId').setAttribute(
   "src", "phx_edit_price_popup.htm?joliId="+joliId);
}

function showEditPrice() {
	document.getElementById("editprice").style.display = "block";
}

function updateEditAmountIframeSrc(depInvIndex) {
   document.getElementById('editAmountFrId').setAttribute(
   "src", "phx_edit_dep_amount_popup.htm?depInvIndex="+depInvIndex);
}

function showEditAmount() {
	document.getElementById("editamount").style.display = "block";
}

//for deleting the product
function onDeleteProduct(index)
{
	document.ceJobSelectChargesForm.productIndex.value = index;
	document.ceJobSelectChargesForm.refreshing.value = "deleteProduct";
	document.ceJobSelectChargesForm.submit();
}

function onDeleteJoli(joliId)
{
	var flag = confirm('Are you sure you want to delete the line item?');
	if(flag==true)
	{
		document.ceJobSelectChargesForm.refreshing.value = "deleteJoli";
		document.ceJobSelectChargesForm.joliIndex.value = joliId;
		document.ceJobSelectChargesForm.submit();
	}
	else
	{
		document.ceJobSelectChargesForm.refreshing.value = "";
		document.ceJobSelectChargesForm.joliIndex.value = "";
	}
}

// Add Test

function showAddTest() {
	document.getElementById("addTest").style.display = "block";
}

//Add Job Services

function showAddJS() {
	document.getElementById("addJS").style.display = "block";
}

// Add Test

function showAddTest() {
	document.getElementById("addTest").style.display = "block";
}

function onAddProduct() {
	document.ceJobSelectChargesForm.refreshing.value="addProduct";
	document.ceJobSelectChargesForm.submit();
}



function showv1p1sample1Table(val){    
	document.getElementById("descriptionContainer"+val).style.visibility = "visible"; 
	document.getElementById("descriptionContainer"+val).style.display = "block"; 
	document.getElementById("totalContainer"+val).style.visibility = "visible"; 
	document.getElementById("totalContainer"+val).style.display = "block"; 
	document.getElementById("bluarrowrightv1p1s1"+val).style.visibility = "hidden"; 
	document.getElementById("bluarrowrightv1p1s1"+val).style.display = "none";
	document.getElementById("bluarrowdownv1p1s1"+val).style.visibility = "visible";
	document.getElementById("bluarrowdownv1p1s1"+val).style.display = "block";
	

}

function hidev1p1sample1Table(val){    
	document.getElementById("descriptionContainer"+val).style.visibility = "hidden"; 
	document.getElementById("descriptionContainer"+val).style.display = "none";
	document.getElementById("totalContainer"+val).style.visibility = "hidden"; 
	document.getElementById("totalContainer"+val).style.display = "none";  
	document.getElementById("bluarrowdownv1p1s1"+val).style.visibility = "hidden"; 
	document.getElementById("bluarrowdownv1p1s1"+val).style.display = "none";
	document.getElementById("bluarrowrightv1p1s1"+val).style.visibility = "visible";
	document.getElementById("bluarrowrightv1p1s1"+val).style.display = "block";
	
}

function sortForm(index){
	document.ceJobSelectChargesForm.productIndex.value = index;
	document.ceJobSelectChargesForm.refreshing.value="sort";
	document.ceJobSelectChargesForm.submit();
}


function sortDepositInv(){
	document.ceJobSelectChargesForm.refreshing.value="sortDepositInvoices";
	document.ceJobSelectChargesForm.submit();
}


