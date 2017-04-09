function validateSearchField(){
  if(document.getElementById("searchValue").value==""){
	  confirm("Please enter a search string");
	  return false;
  }
  else{
	    if (confirm("All the modifications you have done will not be saved. Do you want to continue?")==true){
		    var w = document.getElementById('sel1').selectedIndex;
	        var ops_text = document.getElementById('sel1').value;	    
	        var svalue=document.getElementById("searchValue").value;
	        svalue= svalue.trim();
	        document.getElementById("searchValue").value=svalue;
	        document.getElementById('searchFr').setAttribute("src","phx_search_ce_job_popup.htm?searchString="+ops_text+"&searchValue="+svalue+"&div1=searchDetails&div2=searchDetailsbody");       
			popShowStatus();
	    }
	    else{
	      return false;
	    }  
	}
}

String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}


function popShowStatus() {
   var left = 150;
   var top = 190;
	popup_show('searchStatus', 'searchStatus_drag', 'searchStatus_exit', 'screen-corner', top, left);	
	showPopupDiv('searchStatus','searchStatusbody')
	hideIt();
	popupboxenable();	
}

function closeSearchStatusWindow(){
/*
	if( typeof newwin !='undefined' )
	{
	    if(false == newwin.closed){	
	    //newwin.close();
	    }
	}
*/
		top.popupboxclose();
		top.hidePopupDiv('searchStatus','searchStatusbody');	

}
