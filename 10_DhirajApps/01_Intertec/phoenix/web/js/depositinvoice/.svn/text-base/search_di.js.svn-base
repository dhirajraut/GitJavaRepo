function submitform(val)
{   
    if(val=='update')
    {
    document.depositInvoiceSearchForm.updateFlag.value='true';
    }
	document.depositInvoiceSearchForm.submit();
}

function showWarehouseSearch(){	
	var buName=document.getElementById("sel3").value;
	if(buName == "" || buName == null){
		confirm("Please select BusinessUnit!");
		return true;
  	}

    if(buName!= "" && buName!= null){
    	document.getElementById('jobbu').setAttribute("src",src="search.htm?searchType=warehouse&targetFieldId=branchId.value&div1=jobbranchcode&div2=jobbranchcodebody&buName="+buName+"&searchForm=createJobsCEForm");    	
        popup_show('jobbranchcode', 'jobbranchcode_drag', 'jobbranchcode_exit', 'screen-corner', 120, 20);
  	  	hideIt();
  	  	showbranchcode('jobbranchcode','jobbranchcodebody');
  	  	popupboxenable();
    }
    
   }
function submitSearch(pageNumber){
		document.depositInvoiceSearchForm.pageNumber.value = pageNumber;
		document.depositInvoiceSearchForm.submit();
	}