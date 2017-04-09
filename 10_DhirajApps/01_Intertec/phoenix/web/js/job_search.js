function popJob() {
popup_show('parentJob',	'parent_drag', 'parent_exit', 'screen-corner', 120, 20);
hideIt();
popupboxenable();
showPopupDiv('parentJob','parentbody');
}
function showJobSearch(id) {
	var buName=document.getElementById("sel3").value;
	if(buName == "" || buName == null){
		confirm("Please select BusinessUnit!");
		return true;
  	}
	document.getElementById('searchParentFr').setAttribute("src","phx_search.htm?searchType=job&targetFieldId="+id+"&searchForm=commonJobSearchForm&divName=parentJob&divbody=parentbody&buName="+buName);
}
function userSearch(id){    
    document.getElementById('recievedFr').setAttribute("src",src="phx_search.htm?searchType=user&targetFieldId="+id+"&div1=userDetails&div2=userDetailsbody&searchForm=commonJobSearchForm&sortBy=loginName");
}
function operatingUnitSearch(){	
var buName=document.getElementById("sel3").value;
	if(buName == "" || buName == null){
		confirm("Please select BusinessUnit.");
		return true;
  	}

    if(buName!= "" && buName!= null){
    	document.getElementById('jobbu').setAttribute("src",src="phx_search.htm?searchType=warehouse&targetFieldId=operatingUnit.value&div1=jobbranchcode&div2=jobbranchcodebody&buName="+buName+"&searchForm=commonJobSearchForm");    	
        popup_show('jobbranchcode', 'jobbranchcode_drag', 'jobbranchcode_exit', 'screen-corner', 120, 20);
  	  	hideIt();
  	  	showbranchcode('jobbranchcode','jobbranchcodebody');
  	  	popupboxenable();
    }
    }
function makeBranchblank()
{
document.getElementById('brnch').value='';
}
   
function sortByJobSearchHeader(fldHdr)
{
	if ("" == fldHdr){
		alert("Not yet implemented");
		return false;
	}
	document.getElementById('sortBy').value=fldHdr;
	var sortingOrder  = document.getElementById('sortFlag').value;
	if ("ASC" == sortingOrder || "asc" == sortingOrder ){
		document.getElementById('sortFlag').value = "DESC";
	}else{
		document.getElementById('sortFlag').value = "ASC";
	}
document.commonJobSearchForm.sortBy.value=fldHdr;
document.commonJobSearchForm.submit();
}

function jobLink(jobNumber,jobType)
{
if(jobType=='CE')
window.parent.location ='phx_job_entry_ce.htm?jobNumber='+jobNumber;
else if(jobType=='INSP')
window.parent.location ='edit_job_entry_insp.htm?jobNumber='+jobNumber;
else if(jobType=='FST')
window.parent.location ='edit_job_entry_analytical_service.htm?jobNumber='+jobNumber;
else if(jobType=='MAR')
window.parent.location ='edit_job_entry_marine.htm?jobNumber='+jobNumber;
else if(jobType=='OUT')
window.parent.location ='edit_job_entry_outsourcing.htm?jobNumber='+jobNumber;
}


function doSaveCriteria(){
}

function doSetAsDefaultCriteria()
{

}
			
function 			doSaveAsCriteria(){
}