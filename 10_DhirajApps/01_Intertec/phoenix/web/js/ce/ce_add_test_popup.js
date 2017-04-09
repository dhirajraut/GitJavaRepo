 function searchTests() {
	document.addTestForm.pageNumber.value = "1";
	document.addTestForm.sortFlag.value = "";
	document.addTestForm.operation.value = "searchTests"; 
	document.addTestForm.submit();
}

function selectAllTests(rowCount) {
	for(i=0;i<rowCount;i++)	{
	 var elemName = "chk" + i;
	 document.getElementById(elemName).checked = true;
	 }	 
}

function clearAllTests(rowCount) {
	for(i=0;i<rowCount;i++) {
	 var elemName = "chk" + i;
	 document.getElementById(elemName).checked = false;
	 }
}

function addTest(operation,rowCount) {
// 	document.addTestForm.pageNumber.value=document.addTestForm.pageNo.value;
 	//document.addTestForm.operation.value="addTest";
 	if(operation=='addTest' || operation=='addTestReturn'){
 	    flg=false;
		for(i=0;i<rowCount;i++) {
			 var elemName = "chk" + i;
			 if(document.getElementById(elemName).checked == true)
			 {
				flg =true;		 
			 }
		} 	    
		
		if(flg==false){
			flg = confirm('No rows are selected, Do you want to continue?');
		}
		if(flg==true)
		{
		 	document.addTestForm.operation.value=operation;
			document.addTestForm.submit();
		}
	}
	if(operation=='return'){
		document.addTestForm.operation.value = "addTestReturn";
		refreshParent();
	}
}

function submitform(){
 var chosenVal = document.addTestForm.chosenTestIds.value;
 top.document.forms[0].chosenTestIds.value = chosenVal;
 top.document.forms[0].submit();			
}

function refreshParent() {
	if(document.addTestForm.operation.value == "addTestReturn") {
		document.addTestForm.operation.value = "";
		top.document.forms[0].refreshing.value="addTest";
		top.document.forms[0].submit();
	}
}
function submitManualTest(operation)
{
    document.addManualTestForm.operation.value=operation;
	document.addManualTestForm.submit();
} 
 
function refreshParent1() {
	if(document.addManualTestForm.operation.value == "addManualTest") {
	    document.addManualTestForm.manualTest.value="true";		
		top.document.forms[0].refreshing.value="addManualTest";
		top.document.forms[0].submit();
	}
} 
 
