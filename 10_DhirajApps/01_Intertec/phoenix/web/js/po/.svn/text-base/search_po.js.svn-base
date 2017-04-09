// JavaScript Document For Search Purchase Order

function submitSearch(pageNumber){
	document.searchOrderCreateForm.cxcel.value=false;  
	document.searchOrderCreateForm.pageNumber.value = pageNumber;
	document.searchOrderCreateForm.submitFlag.value="true";
	document.searchOrderCreateForm.submit();
}	

function submitFunction(){
	document.searchOrderCreateForm.cxcel.value=false;
	document.searchOrderCreateForm.pageNumber.value = "1";
	document.searchOrderCreateForm.sortFlag.value = "";
	document.searchOrderCreateForm.submitFlag.value="true";
	document.searchOrderCreateForm.submit();
}

function sortPOByCustCode(){
	document.searchOrderCreateForm.pageNumber.value = "1";
	document.searchOrderCreateForm.sortFlag.value = "custCode";
	document.searchOrderCreateForm.submit();
}

function sortPOByCustName(){
	document.searchOrderCreateForm.pageNumber.value = "1";
	document.searchOrderCreateForm.sortFlag.value = "customer.name";
	document.searchOrderCreateForm.submit();
}

function sortPOByNumber(){
	document.searchOrderCreateForm.pageNumber.value = "1";
	document.searchOrderCreateForm.sortFlag.value = "poNumber";
	document.searchOrderCreateForm.submit();
}

// Customer Search - id

function customerSearchSelectedId(custValue, targetFieldId){
    
    top.popupboxclose();	
    top.hidePopupDiv('customerid','customeridbody');	
	window.top.document.getElementById(targetFieldId).value = custValue;
}

function showCustomerSearchIdSearchLookup(){
    
    document.getElementById('custIdSearchLookup').setAttribute("src",src="phx_search.htm?searchType=customer&targetFieldId=customerId.value&div1=customerid&div2=customeridbody&searchForm=searchOrderCreateForm");   	
    popup_show('customerid', 'customerid_drag', 'customerid_exit', 'screen-corner', 120, 20);        
	hideIt();
	showbranchcode('customerid','customeridbody');
	popupboxenable();
}

// Customer Search - name
function customerSearchSelectedName(custValue, targetFieldId){
    
    top.popupboxclose();	
    top.hidePopupDiv('companyname','companynamebody');	
	window.top.document.getElementById(targetFieldId).value = custValue;
}

function showCustomerSearchNameSearchLookup(){
    document.getElementById('custNameSearchLookup').setAttribute("src",src="phx_search.htm?searchType=customer&targetFieldId=customerName.value&div1=companyname&div2=companynamebody&searchForm=searchOrderCreateForm");   	
    popup_show('companyname', 'companyname_drag', 'companyname_exit', 'screen-corner', 120, 20);        
	hideIt();
	showbranchcode('companyname','companynamebody');
	popupboxenable();
}
function exportToXcel(){
	var form=document.searchOrderCreateForm;
	form.cxcel.value=true;
	form.submit();
}
