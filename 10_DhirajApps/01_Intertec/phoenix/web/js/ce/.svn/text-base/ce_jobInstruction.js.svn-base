function closeAttachPopup(divName)
{
	if(window.opener!=undefined)
	{
	   window.close();
	}
	else{
		top.hideAttachFile(divName);
		top.popupboxclose();
		}
}

function copytoDepositAmount(nameFrfx){
   var src ="depositOrderLineItems["+nameFrfx+"].";
	//confirm(document.getElementById(src+"availableAmount").value);
	document.getElementById(src+"depositAmount").value=document.getElementById(src+"availableAmount").value
}

function updateInstrServiceLoc()
{
	var innerTextStr =document.getElementById('serviceLocationName').value;
	var currTokens = innerTextStr.split('|');
	document.getElementById('serviceLocationName').value = currTokens[0];
	document.getElementById('serviceLocationCode').value = currTokens[1];
}

function updateBranchAndInstrServiceLoc()
{

	var innerTextStr =document.getElementById('warehouseName').value;
	var currTokens = innerTextStr.split('~');
	document.getElementById('warehouseName').value = currTokens[0].trim();
	if(currTokens.length>1){
		var serviceLocationToken = currTokens[1].split('|');
		window.top.document.getElementById("serviceLocationName").value = serviceLocationToken[0].trim();
		window.top.document.getElementById("serviceLocationCode").value = serviceLocationToken[1].trim();						
	}
}


function updateOthersIframeSrc(productIndex,jobNumber,testId,splitIndex){	

/*    if(splIndex ==-1)	{
  	 document.getElementById('othersFr'+testIndex).setAttribute("src","phx_job_instruction_test_attribute_popup.htm?testIndex="+testIndex+"&splitIndex="+splIndex+"&div1=others"+testIndex+"&div2=othersbody"+testIndex);
	 }
	  else {
		document.getElementById('othersFr'+testIndex).setAttribute("src","phx_job_instruction_test_attribute_popup.htm?testIndex="+testIndex+"&splitIndex="+splIndex+"&div1=others"+testIndex+"&div2=othersbody"+testIndex);
		}
*/
  	 //document.getElementById('othersFr'+productIndex).setAttribute("src","phx_job_instruction_test_attribute_popup.htm?productIndex="+productIndex+"&testIndex="+testIndex+"&splitIndex="+splitIndex+"&div1=others"+productIndex+"&div2=othersbody"+productIndex);

		document.getElementById("testIndex").value=testId;
		document.getElementById("addOrDeleteTestLines").value="saveotherbefore";
		document.jobOrderOpInstrCEForm.prodIndex.value=productIndex;
		document.jobOrderOpInstrCEForm.splitIndex.value=splitIndex;		
		//document.getElementById('othersFr'+productIndex).setAttribute("src","phx_job_instruction_test_attribute_popup.htm?jobNumber="+jobNumber+"&testIndex="+testId+"&splitIndex="+splitIndex+"&div1=others"+productIndex+"&div2=othersbody"+productIndex);
		document.jobOrderOpInstrCEForm.submit();
	}

function showOthersFrame(jobNumber){
		//saveotherAfter
		if(document.getElementById("addOrDeleteTestLines").value=="saveotherbefore"){
		    //confirm('inside others');
	        productIndex=document.jobOrderOpInstrCEForm.prodIndex.value;
	        testId=document.getElementById("testIndex").value;
	        splitIndex=document.jobOrderOpInstrCEForm.splitIndex.value;	        
			document.getElementById("addOrDeleteTestLines").value="saveother";
			document.getElementById('othersFr0').setAttribute("src","phx_job_instruction_test_attribute_popup.htm?jobNumber="+jobNumber+"&testIndex="+testId+"&splitIndex="+splitIndex+"&div1=others"+productIndex+"&div2=othersbody"+productIndex);
			popup_show('others0', 'others_drag0', 'others_exit0', 'screen-corner', 120, 20);
			hideIt();
			showPopupDiv('others0','othersbody0');
			popupboxenable();
		}
	}


function saveTestAttrib(prodIndex,testIndex){
	    confirm('saveTest');		
		//top.document.forms[0].addOrDeleteTestLines.value="saveother";
		//top.document.forms[0].prodIndex.value=prodIndex;
		//top.document.forms[0].testIndex.value=testIndex;
		//document.jobOrderOpInstrCEForm.splitIndex.value=splIndex;
		top.document.forms[0].submit();			
}

function searchPopupBranch(inputField,buName) {    
 url="phx_search.htm?searchType=warehouse&targetFieldId="+inputField+"&searchForm=jobInstructionTestAttribForm&buName="+buName;
 newwindow=window.open(url,'lookup','height=630,width=600,resizable=no');
 if (window.focus) {newwindow.focus()}
    return false;
 }
   function showTestIFrameSrc(nomDate,jobNumber)  { 
  
    document.getElementById('testFr').setAttribute("src","phx_add_test.htm?jobNumber="+jobNumber+"&searchForm=jobOrderOpInstrCEForm&div1=test&div2=testbody&nomDate="+nomDate);
    document.getElementById('testFr').height = "500px";
  }
 
 function popTest(){ 
 popup_show('test', 'test_drag', 'test_exit', 'screen-corner', 40, 80);
	showPopupDiv('test','testbody');
	hideIt();
	popupboxenable();
 }
 
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

function addTest() {
 	document.addTestForm.pageNumber.value=document.addTestForm.pageNo.value;
 	document.addTestForm.operation.value="addTest";
	document.addTestForm.submit();
	top.document.forms[0].refreshing.value="addTest"; 
	top.document.forms[0].submit();
}

function submitform(){			
 var chosenVal = document.addTestForm.chosenTestIds.value;
 top.document.forms[0].chosenTestIds.value = chosenVal;
 top.document.forms[0].submit();			
}

	function onAddProduct()
	{
		document.jobOrderOpInstrCEForm.addOrDeleteTestLines.value="addProduct";	  
		document.jobOrderOpInstrCEForm.addOrDeleteDepositLines.value="";
		document.jobOrderOpInstrCEForm.addOrDeleteSplitLines.value="";
		document.jobOrderOpInstrCEForm.scrollFlag.value="product";
		top.document.forms[0].submit(); 
	}

	function onDeleteProduct(prodIndex)
	{
		document.jobOrderOpInstrCEForm.prodIndex.value=prodIndex;
		document.jobOrderOpInstrCEForm.addOrDeleteTestLines.value="deleteProduct";	  
		document.jobOrderOpInstrCEForm.addOrDeleteDepositLines.value="";
		document.jobOrderOpInstrCEForm.addOrDeleteSplitLines.value="";
		document.jobOrderOpInstrCEForm.scrollFlag.value="product";
		top.document.forms[0].submit(); 
	}

	function onAddTestLines(prodIndex,jobNumber)
	{
		document.jobOrderOpInstrCEForm.addOrDeleteDepositLines.value="";
		document.jobOrderOpInstrCEForm.addOrDeleteSplitLines.value="";
		document.jobOrderOpInstrCEForm.prodIndex.value=prodIndex;
		document.jobOrderOpInstrCEForm.addOrDeleteTestLines.value="addTest";	  
		document.jobOrderOpInstrCEForm.scrollFlag.value="testLine";
		updateAddTestIframeSrc(jobNumber);
		//top.document.forms[0].submit(); 
	}

	function onDelTestLines(prodIndex,testIndex)
	{
		document.jobOrderOpInstrCEForm.addOrDeleteDepositLines.value="";
		document.jobOrderOpInstrCEForm.addOrDeleteSplitLines.value="";
		document.jobOrderOpInstrCEForm.addOrDeleteTestLines.value="deleteTest";	  
		document.jobOrderOpInstrCEForm.prodIndex.value=prodIndex;
		document.jobOrderOpInstrCEForm.testIndex.value=testIndex;
		document.jobOrderOpInstrCEForm.scrollFlag.value="testLine";
		top.document.forms[0].submit(); 
	}
	
	
	function onAddDepositLines(){
	  document.jobOrderOpInstrCEForm.addOrDeleteTestLines.value="none";	 
		document.jobOrderOpInstrCEForm.addOrDeleteSplitLines.value="none";
	  document.jobOrderOpInstrCEForm.addOrDeleteDepositLines.value="adddep";
	  top.document.forms[0].submit();
	}
	
	function onDelDepositLines(index)
	{
 	    document.jobOrderOpInstrCEForm.addOrDeleteTestLines.value="none";	 
		document.jobOrderOpInstrCEForm.addOrDeleteSplitLines.value="none";
		document.jobOrderOpInstrCEForm.addOrDeleteDepositLines.value="deldep";	  
		document.jobOrderOpInstrCEForm.depIndex.value=index;
		top.document.forms[0].submit(); 
	}
	
	
	function onAddSplitTestLines(jobNumber,prodIndex,testIndex,splitType){
		document.jobOrderOpInstrCEForm.addOrDeleteTestLines.value="addspl";	 
		document.jobOrderOpInstrCEForm.addOrDeleteDepositLines.value="none";
		//document.jobOrderOpInstrCEForm.addOrDeleteSplitLines.value="addspl";
		document.jobOrderOpInstrCEForm.prodIndex.value=prodIndex;
		document.jobOrderOpInstrCEForm.testIndex.value=testIndex;	
		//document.jobOrderOpInstrCEForm.splitIndex.value=index;
		if(splitType=="newTest"){
			document.jobOrderOpInstrCEForm.actionFlag.value="newTest";
			updateAddTestIframeSrc(jobNumber);
		}else
		{
			document.jobOrderOpInstrCEForm.actionFlag.value="";
			document.forms[0].submit();
		}
		//top.document.forms[0].submit(); 
	}
	
	function onDelSplitTestLines(prodIndex,testIndex,sIndex){
	document.jobOrderOpInstrCEForm.addOrDeleteTestLines.value="delspl";	 
	document.jobOrderOpInstrCEForm.addOrDeleteDepositLines.value="none";
	//document.jobOrderOpInstrCEForm.addOrDeleteSplitLines.value="delspl";
	document.jobOrderOpInstrCEForm.prodIndex.value=prodIndex;
	document.jobOrderOpInstrCEForm.testIndex.value=testIndex;
	document.jobOrderOpInstrCEForm.splitIndex.value=sIndex;
	top.document.forms[0].submit(); 
	}
	
	function updateNoteIframeSrc(jobNumber,testId){
		document.getElementById('jobNoteFr').setAttribute("src","phx_job_instruction_note_popup.htm?JobOrderNumber="+jobNumber+"&jobTestId="+testId+"&divName=note");
	}
	
   function updateAttachIframeSrc(jobNumber,testId){	
	   document.getElementById('jobAttchFr').setAttribute("src","phx_job_instruction_attachfile_popup.htm?JobOrderNumber="+jobNumber+"&jobTestId="+testId+"&divName=addattach");
	}

	function addEstimation(){
	 document.jobInstructionTestAttribForm.workertimeFlag.value="add";	 
	 document.jobInstructionTestAttribForm.submit(); 
	
	}
	
	function deleteEstimation(estimationId){
	 document.jobInstructionTestAttribForm.workertimeFlag.value="delete";
	 document.jobInstructionTestAttribForm.estimationId.value=estimationId;
	 document.jobInstructionTestAttribForm.submit(); 
	}
	
	
	
