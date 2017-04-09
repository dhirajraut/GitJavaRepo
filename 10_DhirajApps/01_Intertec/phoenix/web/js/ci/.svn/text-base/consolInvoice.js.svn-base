function submitConsol(){
	document.conslInvCreateForm.refreshing.value = "true";
	document.conslInvCreateForm.tabName.value = "2";	
	document.conslInvCreateForm.submit();
}

function submitxcel(){
document.conslInvCreateForm.cxcel.value="true";
document.conslInvCreateForm.sortFlag.value = "";
document.conslInvCreateForm.submit();
}

function sortByConsolInvColumnHeader(fldHdr){
	if ("" == fldHdr){
		confirm("Not yet implemented");
		return false;
	}
	document.getElementById('sortBy').value=fldHdr;
	var sortingOrder  = document.getElementById('sortFlag').value;
	if ("ASC" == sortingOrder || "asc" == sortingOrder ){
		document.getElementById('sortFlag').value = "DESC";
	}else{
		document.getElementById('sortFlag').value = "ASC";
	}
document.conslInvCreateForm.sortBy.value=fldHdr;
document.conslInvCreateForm.refreshing.value="true"
document.conslInvCreateForm.submit();
}

function sortBySearchConsolInvColumnHeader(fldHdr){
if ("" == fldHdr){
		confirm("Not yet implemented");
		return false;
	}
	document.getElementById('sortBy').value=fldHdr;
	var sortingOrder  = document.getElementById('sortFlag').value;
	if ("ASC" == sortingOrder || "asc" == sortingOrder ){
		document.getElementById('sortFlag').value = "DESC";
	}else{
		document.getElementById('sortFlag').value = "ASC";
	}
document.cgConslInvSearchForm.sortBy.value=fldHdr;
document.cgConslInvSearchForm.submit();
}

function submitSearchConsol(){
document.cgConslInvSearchForm.cxcel.value="false";
document.cgConslInvSearchForm.submit();
}

function submitSearchxcel(){
document.cgConslInvSearchForm.cxcel.value="true";
document.cgConslInvSearchForm.submit();
}

function updateBusinessUnitIframeSrc(){ 
document.conslInvCreateForm.cxcel.value="false";
document.getElementById('searchBusinessUnitFr').setAttribute("src", 'phx_search.htm?searchType=bu&targetFieldId=buName.value&div1=bu&div2=bubody&searchForm=conslInvCreateForm');
}

function showBankSearch(index,buname,currency){
document.getElementById('sremittoframe').setAttribute("src",src="phx_search.htm?searchType=bank&targetFieldId=remitto&buName="+buname+"&currency="+currency+"&searchForm=conslInvCreateForm");
}

function showBankAccountSearch(index, buname,currency){
 var bcode=document.getElementById("remitto").value;
 document.getElementById('saccountframe').setAttribute("src",src="phx_search.htm?searchType=bankaccount&targetFieldId=bankaccount"+ 
 		"&buName="+buname+"&currency="+currency+"&bankCode="+bcode+"&searchForm=createJobsCEForm");
}

function showCustomerSearchIdSearchLookup(){
    
    document.getElementById('searchParentCustomerFr').setAttribute("src",src="phx_search.htm?searchType=customer&targetFieldId=cust&div1=companyname&div2=companynamebody&searchForm=conslInvCreateForm");   	
    popup_show('companyname', 'companyname_drag', 'companyname_exit', 'screen-corner', 120, 20);        
	hideIt();
	showPopupDiv('companyname','companynamebody');
	popupboxenable();
}

function submitForm(){
document.conslInvCreateForm.submit();
}

function onSave(){
	document.conslInvCreateForm.refreshing.value='save';
}
  var invoice_array = new Array(); 
function attachInvoices(invoiceCount, vatCountryFlag, vatProvince) {
    if(vatCountryFlag !=null && vatCountryFlag == 'true' && (vatProvince == null || vatProvince=='')){
    	confirm("Please select vat province before attach");
    }else{
       for(var i=0;i<invoiceCount;i++){
       		var jobContractInvoiceId = document.getElementById("chk"+i).value;
       		if(document.getElementById("chk"+i).checked){
       			var idExists = '0';
       			for(var j=0;j<invoice_array.length;j++)	{
			        if(invoice_array[j] == jobContractInvoiceId){
			        	idExists = '1';
			        }
				}
				if(idExists == '0')	{
					invoice_array[invoice_array.length + 1] = jobContractInvoiceId;
				}
       			document.getElementById("chk"+i).checked = false;
       		}
       }
        var invoice="";
        for(var i=0;i<invoice_array.length;i++) {
            if(invoice_array[i]!= undefined) {              
              if(invoice == "")
                invoice = invoice_array[i];
              else
                invoice = invoice + ";" + invoice_array[i];
            }
          }  
          document.conslInvCreateForm.chosenInvoices.value=invoice;
       }  
}

function detachInvoices(invoiceCount) {
var dinvoice;
    for(var i=0;i<invoiceCount;i++)
    {
    		var jobContractInvoiceId = document.getElementById("chk"+i).value;
    		if(document.getElementById("chk"+i).checked){    		
    		    var invoiceIndex;    		    
    			for(var j=0;j<invoice_array.length;j++) {
      			  if(invoice_array[j] == jobContractInvoiceId) {      			 
      			    if(dinvoice ==""){      			   
      			    dinvoice=invoice_array[j];      			    
      			    }		          
      			    else{      			    
      			    dinvoice = dinvoice + ";" + invoice_array[j];
      			    }
		          invoiceIndex = j;		          
		          }      		
      			}
			invoice_array.splice(invoiceIndex,1);				
			document.getElementById("chk"+i).checked = false;       			
    		}    		
      }
       var invoice="";
       for(var i=0;i<invoice_array.length;i++) {
           if(invoice_array[i]!= undefined) {	              
             if(invoice == ""){
               invoice = invoice_array[i];
               }
             else{
               invoice = invoice + ";" + invoice_array[i];
              }
           }	            
         }  	          
         document.conslInvCreateForm.chosenInvoices.value=invoice;         
         document.conslInvCreateForm.detachedInvoices.value=dinvoice;         
}

function generateInvoice(){
	document.conslInvCreateForm.refreshing.value = 'generate';
	submitForm();
}

function showContactLookup(){
var ccode = document.getElementById("cust").value;
    document.getElementById('searchContactFr').setAttribute("src",src="phx_search.htm?searchType=contactCust&targetFieldId=attention&div1=addressseq&custCode="+ccode+"&div2=addressseqbody&searchForm=conslInvCreateForm");   	
    popup_show('contact', 'contact_drag', 'contact_exit', 'screen-corner', 120, 20);        
	hideIt();
	showPopupDiv('contact','contactbody');
	popupboxenable();
}

function showLocationLookup(){
var ccode = document.getElementById("cust").value;
    document.getElementById('addressseqFr').setAttribute("src",src="phx_search.htm?searchType=location&targetFieldId=location&custCode="+ccode+"&div1=addressseq&div2=addressseqbody&searchForm=conslInvCreateForm");   	
    popup_show('addressseq', 'addressseq_drag', 'addressseq_exit', 'screen-corner', 120, 20);        
	hideIt();
	popupboxenable();
}

function generateConsolInvoice(){
	document.conslInvCreateForm.refreshing.value  = 'generate';
	document.conslInvCreateForm.submit();
}

function regenerateInvoice(){
    document.conslInvCreateForm.refreshing.value='generate';
    document.conslInvCreateForm.submit();
  } 
function setCountable(invoiceFileId, countable){
    document.conslInvCreateForm.refreshing.value  = 'Countable';
    document.conslInvCreateForm.invoiceFileId.value=invoiceFileId;
    if(countable == null || countable == false){
    	document.conslInvCreateForm.invoiceFileCountable.value = "N";
    }
    if(countable == true){	
    	document.conslInvCreateForm.invoiceFileCountable.value = "Y";
    }
	document.conslInvCreateForm.submit();
}
