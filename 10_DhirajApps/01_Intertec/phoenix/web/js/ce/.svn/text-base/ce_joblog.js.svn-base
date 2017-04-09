  function reDisplay(refreshing,emailIndex)
  {
    
    document.jobLogmailPopUpForm.refreshing.value=refreshing;
    document.jobLogmailPopUpForm.emailIndex.value=emailIndex;
    document.jobLogmailPopUpForm.submit();
  }  

function sendEmail(emailindex)
{
    document.jobLogmailPopUpForm.refreshing.value='sendEmail';
    document.jobLogmailPopUpForm.emailIndex.value=emailindex;
    document.jobLogmailPopUpForm.submit();
}
  
function showJobDetails(divCount)
{
	for(i=0;i<divCount;i++)
	{
		document.getElementById("details"+i).style.visibility = "visible"; 
    	document.getElementById("details"+i).style.display = "block";
    	
    }
    document.getElementById("bluarrowdownv").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv").style.visibility = "hidden";
}

function hideJobDetails(divCount)
{
	for(i=0;i<divCount;i++)
	{
		document.getElementById("details"+i).style.visibility = "hidden"; 
    	document.getElementById("details"+i).style.display = "none";
    	
    }
    document.getElementById("bluarrowdownv").style.visibility = "hidden"; 
    document.getElementById("bluarrowrightv").style.visibility = "visible";
}

function selectDeselect()
{
  emailcheck = document.getElementsByName('mailCheck');
  if(document.forms[0].checkAll.checked==true)
  {
    for(i=0;i<emailcheck.length;i++){
	    emailcheck[i].checked=true;	    
    }
  }
  else
  {
    for(i=0;i<emailcheck.length;i++){
	    emailcheck[i].checked=false;	    
    }
  }
}

function showEmailPopup()
{
    emailcheck = document.getElementsByName('mailCheck');
    var checkIndex="";
    for(i=0;i<emailcheck.length;i++){
	    if(emailcheck[i].checked==true){
	    	checkIndex=checkIndex+i+"~";
	    }
    }
    
    if(checkIndex=="")
    {
     confirm("Please select at least one row.");
    }
    else
    {
		document.getElementById('sendemailFrame').setAttribute("src","phx_job_log_ce_mail.htm?selectedRow="+checkIndex);
	}
}

function openJobEntry(jobNumber){
	if(jobNumber=='null' || jobNumber==''){
	  confirm('job number is null, Select another job');
	}
	else{  
		window.parent.location ='phx_job_entry_ce.htm?jobNumber='+jobNumber;
	}
}
