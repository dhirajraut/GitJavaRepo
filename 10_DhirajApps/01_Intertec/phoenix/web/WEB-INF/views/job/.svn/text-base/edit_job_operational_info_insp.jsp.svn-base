<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><head>
<script type="text/javascript" src="js/project.js"></script>
<script language="javascript">
	function showRequiredFields(){
		var myFrame=document.getElementById("requiredFieldsFrame");
		myFrame.src="required_fields.htm";
		popup_show('requiredFields', 'requiredFields_drag', 'requiredFields_exit', 'screen-corner', 120, 20);
		hideIt();
		popupboxenable();
	}
	
  function onAddVessel(index)
  {  
    document.jobOrderEditOpInstrForm.addOrDeleteVessel.value = "add";
	var a=++index;
    document.jobOrderEditOpInstrForm.scrollFlag.value="addVess"+a;	
    document.jobOrderEditOpInstrForm.submit();
  }
  function onDeleteVessel(index)
  {
    document.jobOrderEditOpInstrForm.addOrDeleteVessel.value = "delete";
    document.jobOrderEditOpInstrForm.vesselIndex.value = index;	
	if(index!=0)
	{
	document.jobOrderEditOpInstrForm.scrollFlag.value="delvessel"+(index-1);
	}
	else
	  {
		document.jobOrderEditOpInstrForm.scrollFlag.value="addVess";	
	  }	
    document.jobOrderEditOpInstrForm.submit();

  }
  function onAddProduct(index,count)
  {
    document.jobOrderEditOpInstrForm.addOrDeleteProduct.value = "add";
    document.jobOrderEditOpInstrForm.vesselIndex.value = index;
	var a=++count;	
	document.jobOrderEditOpInstrForm.scrollFlag.value="addProd"+index+a;	
    document.jobOrderEditOpInstrForm.submit();
  }
  function onDeleteProduct(vesselIndex,productIndex)
  {
    document.jobOrderEditOpInstrForm.addOrDeleteProduct.value = "delete";
    document.jobOrderEditOpInstrForm.productIndex.value = productIndex;
    document.jobOrderEditOpInstrForm.vesselIndex.value = vesselIndex;
	document.jobOrderEditOpInstrForm.scrollFlag.value="delProd"+vesselIndex;
    document.jobOrderEditOpInstrForm.submit();

  } 
  function onAddEvent(vesselIndex,productIndex)
  {
    document.jobOrderEditOpInstrForm.addOrDeleteEvent.value = "add";
    document.jobOrderEditOpInstrForm.productIndex.value = productIndex;
    document.jobOrderEditOpInstrForm.vesselIndex.value = vesselIndex;
    document.jobOrderEditOpInstrForm.scrollFlag.value="addEventRow"+vesselIndex+productIndex;
    document.jobOrderEditOpInstrForm.submit();
  }
  function onDeleteEvent(vesselIndex,productIndex,eventIndex)
  {
    document.jobOrderEditOpInstrForm.addOrDeleteEvent.value = "delete";
    document.jobOrderEditOpInstrForm.productIndex.value = productIndex;
    document.jobOrderEditOpInstrForm.vesselIndex.value = vesselIndex;
    document.jobOrderEditOpInstrForm.eventIndex.value = eventIndex;
    document.jobOrderEditOpInstrForm.scrollFlag.value="delEventRow"+vesselIndex+productIndex;	
    document.jobOrderEditOpInstrForm.submit();

  } 
  
  function onAddSampleLoc(vesselIndex,productIndex,count)
  {
    var a=++count;
    document.jobOrderEditOpInstrForm.addOrDeleteSampleLoc.value = "add";
    document.jobOrderEditOpInstrForm.productIndex.value = productIndex;
    document.jobOrderEditOpInstrForm.vesselIndex.value = vesselIndex;   
	document.jobOrderEditOpInstrForm.scrollFlag.value="addSampleRow"+vesselIndex+productIndex+a;
	document.getElementById('sampleLocationFr'+vesselIndex+productIndex).setAttribute("src","sample_location_popup.htm?searchForm=jobOrderEditOpInstrForm&div1=sampleLoc"+vesselIndex+productIndex+"&div2=sampleLocBody"+vesselIndex+productIndex);
  }
  
	function resetJob()
	{ 
	 document.jobOrderEditOpInstrForm.addOrDeleteSampleLoc.value = "none";
	 document.jobOrderEditOpInstrForm.scrollFlag.value="none";
	}
  
  
  function onDeleteSampleLoc(vesselIndex,productIndex,sampleLocIndex)
  {
   document.jobOrderEditOpInstrForm.addOrDeleteSampleLoc.value = "delete";
   document.jobOrderEditOpInstrForm.productIndex.value = productIndex;
   document.jobOrderEditOpInstrForm.vesselIndex.value = vesselIndex;       
   document.jobOrderEditOpInstrForm.sampleLocIndex.value = sampleLocIndex;
   document.jobOrderEditOpInstrForm.scrollFlag.value="delRowSample"+vesselIndex+productIndex;	
   document.jobOrderEditOpInstrForm.submit();

  } 
  function onAddQty(vesselIndex,productIndex)
  {
    document.jobOrderEditOpInstrForm.addOrDeleteQty.value = "add";
    document.jobOrderEditOpInstrForm.productIndex.value = productIndex;
    document.jobOrderEditOpInstrForm.vesselIndex.value = vesselIndex;  
	document.jobOrderEditOpInstrForm.scrollFlag.value="addQuantity"+vesselIndex+productIndex;	
    document.jobOrderEditOpInstrForm.submit();
  }
  function onUpdateQty(vesselIndex,productIndex)
  {
  
    document.jobOrderEditOpInstrForm.addOrDeleteQty.value = "update";
    document.jobOrderEditOpInstrForm.productIndex.value = productIndex;
    document.jobOrderEditOpInstrForm.vesselIndex.value = vesselIndex;   
    document.jobOrderEditOpInstrForm.submit();
  }    
  function onDeleteQty(vesselIndex,productIndex,qtyIndex)
  {
    document.jobOrderEditOpInstrForm.addOrDeleteQty.value = "delete";
    document.jobOrderEditOpInstrForm.productIndex.value = productIndex;
    document.jobOrderEditOpInstrForm.vesselIndex.value = vesselIndex;       
    document.jobOrderEditOpInstrForm.qtyIndex.value = qtyIndex;
    document.jobOrderEditOpInstrForm.scrollFlag.value="delQuantity"+vesselIndex+productIndex;	
    document.jobOrderEditOpInstrForm.submit();

  } 
  function onDeleteTest(vesselIndex,productIndex,sampleLocIndex,testIndex)
  {
    document.jobOrderEditOpInstrForm.addOrDeleteTest.value = "delete";
    document.jobOrderEditOpInstrForm.productIndex.value = productIndex;
    document.jobOrderEditOpInstrForm.vesselIndex.value = vesselIndex;       
    document.jobOrderEditOpInstrForm.sampleLocIndex.value = sampleLocIndex;
    document.jobOrderEditOpInstrForm.testIndex.value = testIndex;  
	document.jobOrderEditOpInstrForm.scrollFlag.value="delRowTest"+vesselIndex+productIndex+sampleLocIndex;
    document.jobOrderEditOpInstrForm.submit();

  } 

    function onDeleteManualTest(vesselIndex,productIndex,sampleLocIndex,testIndex)
  {
    document.jobOrderEditOpInstrForm.addOrDeleteTest.value = "deleteManual";
    document.jobOrderEditOpInstrForm.productIndex.value = productIndex;
    document.jobOrderEditOpInstrForm.vesselIndex.value = vesselIndex;       
    document.jobOrderEditOpInstrForm.sampleLocIndex.value = sampleLocIndex;
    document.jobOrderEditOpInstrForm.testIndex.value = testIndex;  
	document.jobOrderEditOpInstrForm.scrollFlag.value="delRowTest"+vesselIndex+productIndex+sampleLocIndex;
    document.jobOrderEditOpInstrForm.submit();

  }

    function onAddTest(vesselIndex,productIndex,sampleLocIndex,testSize,manualTestSize,slateSize)
  {
    
    document.jobOrderEditOpInstrForm.productIndex.value = productIndex;
    document.jobOrderEditOpInstrForm.vesselIndex.value = vesselIndex;       
    document.jobOrderEditOpInstrForm.sampleLocIndex.value = sampleLocIndex;
	document.jobOrderEditOpInstrForm.scrollFlag.value="testAdd"+vesselIndex+productIndex+sampleLocIndex;
    var massOTElemName = "massOT" + vesselIndex + productIndex + sampleLocIndex;
    document.getElementById(massOTElemName).style.visibility="hidden";

    for(var i=0;i<testSize;i++)
    {
      var testElemName = "testot" + vesselIndex + productIndex + sampleLocIndex + i;
    document.getElementById(testElemName).style.visibility="hidden";
  }
  for(var k=0;k<manualTestSize;k++)
    {
      var testElemName = "testot" + vesselIndex + productIndex + sampleLocIndex + k;
    document.getElementById(testElemName).style.visibility="hidden";
  }
    for(var j=0;j<slateSize;j++)
    {
      var slateElemName = "slateot" + vesselIndex + productIndex + sampleLocIndex + j;
    document.getElementById(slateElemName).style.visibility="hidden";
  }
  }
  function onAddManualTest(vesselIndex,productIndex,sampleLocIndex,testSize,manualTestSize,slateSize)
  {
    
    document.jobOrderEditOpInstrForm.productIndex.value = productIndex;
    document.jobOrderEditOpInstrForm.vesselIndex.value = vesselIndex;       
    document.jobOrderEditOpInstrForm.sampleLocIndex.value = sampleLocIndex;
    document.jobOrderEditOpInstrForm.scrollFlag.value="testAdd"+vesselIndex+productIndex+sampleLocIndex;
		var massOTElemName = "massOT" + vesselIndex + productIndex + sampleLocIndex;
		document.getElementById(massOTElemName).style.visibility="hidden";

		for(var i=0;i<testSize;i++)
    {
    	var testElemName = "testot" + vesselIndex + productIndex + sampleLocIndex + i;
		document.getElementById(testElemName).style.visibility="hidden";
	}

	for(var k=0;k<manualTestSize;k++)
    {
		var testElemName = "testot" + vesselIndex + productIndex + sampleLocIndex + k;
		document.getElementById(testElemName).style.visibility="hidden";
	}

		for(var j=0;j<slateSize;j++)
    {
    	var slateElemName = "slateot" + vesselIndex + productIndex + sampleLocIndex + j;
		document.getElementById(slateElemName).style.visibility="hidden";
	}

  }
  function onDeleteSlate(vesselIndex,productIndex,sampleLocIndex,slateIndex)
  {
    document.jobOrderEditOpInstrForm.addOrDeleteSlate.value = "delete";
    document.jobOrderEditOpInstrForm.productIndex.value = productIndex;
    document.jobOrderEditOpInstrForm.vesselIndex.value = vesselIndex;       
    document.jobOrderEditOpInstrForm.sampleLocIndex.value = sampleLocIndex;
    document.jobOrderEditOpInstrForm.testIndex.value = slateIndex; 
	document.jobOrderEditOpInstrForm.scrollFlag.value="delRowSlate"+vesselIndex+productIndex+sampleLocIndex;
    document.jobOrderEditOpInstrForm.submit();
  }

    function onAddSlate(vesselIndex,productIndex,sampleLocIndex,testSize,manualTestSize,slateSize)
  {
    
    document.jobOrderEditOpInstrForm.productIndex.value = productIndex;
    document.jobOrderEditOpInstrForm.vesselIndex.value = vesselIndex;       
    document.jobOrderEditOpInstrForm.sampleLocIndex.value = sampleLocIndex;
	document.jobOrderEditOpInstrForm.scrollFlag.value="slateAdd"+vesselIndex+productIndex+sampleLocIndex;
    var massOTElemName = "massOT" + vesselIndex + productIndex + sampleLocIndex;
    document.getElementById(massOTElemName).style.visibility="hidden";

      for(var i=0;i<testSize;i++)
    {
      var testElemName = "testot" + vesselIndex + productIndex + sampleLocIndex + i;
    document.getElementById(testElemName).style.visibility="hidden";
  }

  for(var k=0;k<manualTestSize;k++)
    {
		var testElemName = "testot" + vesselIndex + productIndex + sampleLocIndex + k;
		document.getElementById(testElemName).style.visibility="hidden";
	}

    for(var j=0;j<slateSize;j++)
    {
      var slateElemName = "slateot" + vesselIndex + productIndex + sampleLocIndex + j;
    document.getElementById(slateElemName).style.visibility="hidden";
  }
  }     
function onCopyVessel(vesselIndex,count) {
  confirm("Vessel Copied.");
  var a=++count;  
  document.jobOrderEditOpInstrForm.copyFlag.value = "copyVessel";
  document.jobOrderEditOpInstrForm.vesselIndex.value = vesselIndex;
  document.jobOrderEditOpInstrForm.scrollFlag.value="copyVess"+a;
  document.jobOrderEditOpInstrForm.submit();
  }   
function onCopyProduct() {
  document.jobOrderEditOpInstrForm.copyFlag.value = "copyProduct"; 
 }       
function onCopySample(vesselIndex,productIndex,sampleLocIndex,count) {
  confirm("Sample Location Copied.");
  var a=++count;
  document.jobOrderEditOpInstrForm.copyFlag.value = "copySample";
  document.jobOrderEditOpInstrForm.vesselIndex.value = vesselIndex;
  document.jobOrderEditOpInstrForm.productIndex.value = productIndex;
  document.jobOrderEditOpInstrForm.sampleLocIndex.value = sampleLocIndex;
  document.jobOrderEditOpInstrForm.scrollFlag.value = "copySampleLoc"+vesselIndex+productIndex+a;
  document.jobOrderEditOpInstrForm.submit();
  }     
  function setChosenContracts(vesselIndex, productIndex, contractCount)
  {
  
  for(i=0;i<contractCount;i++)
  {
  
    var elemName = "appchk" + vesselIndex + productIndex + i ;
    
    if(document.getElementById(elemName).checked )
    {
      
      if(document.jobOrderEditOpInstrForm.chosenContracts.value == "")
      {
        document.jobOrderEditOpInstrForm.chosenContracts.value = document.getElementById(elemName).value;
      }
      else
      {
        var existingVal = document.jobOrderEditOpInstrForm.chosenContracts.value;
        document.jobOrderEditOpInstrForm.chosenContracts.value = existingVal + ";" + document.getElementById(elemName).value;
      }
      
      
    }
    
  }
    
  
  
  }   
  function goToNextPage()
{
  document.jobOrderEditOpInstrForm.nextPageFlag.value = "1";
  document.jobOrderEditOpInstrForm.scrollFlag.value = "none"
  document.jobOrderEditOpInstrForm.submit();
  
}
function setTemplateSearchFlag(){

  document.jobOrderEditOpInstrForm.tmpSearchFlag.value = "tmpSearchFlag";
}

  function populateJobInst()
  {
  document.jobOrderEditOpInstrForm.tmpSearchFlag.value = "tmpSearchFlag";
    document.jobOrderEditOpInstrForm.submit();
  }

   function updateTemplateIFrameSrc(buname,branch,tempname)
  {
  
    var template = document.getElementById('searchTemplate').value;
    document.getElementById('searchTemplatenameFr').setAttribute("src","search_template_popup.htm?inputFieldId=templateNum&buName="+buname+"&branchName="+branch+"&templateName="+template);
    document.getElementById('searchTemplatenameFr').height = "500px";
  }
////////////////////////////////////////////////////////// Template

   function updateTestIFrameSrc(vesselCnt,productCnt,SampleCnt,nomDate)
  {
  
      chosenContractList = document.jobOrderEditOpInstrForm.chosenContracts.value;
      
    document.getElementById('searchtestpopup').setAttribute("src","search_test_popup.htm?inputFieldId=addJobVessels["+vesselCnt+"].jobVessel.vesselName&rowNum="+vesselCnt+"&searchForm=jobOrderEditOpInstrForm&chosenContracts="+chosenContractList+"&div1=test"+vesselCnt+productCnt+SampleCnt+"&div2=testbody"+vesselCnt+productCnt+SampleCnt+"&nomDate="+nomDate);
    document.getElementById('searchtestpopup').height = "500px";
  }
  
  function updateManualTestIFrameSrc(vesselCnt,productCnt,SampleCnt,nomDate,jobnumber)
  {
  
      chosenContractList = document.jobOrderEditOpInstrForm.chosenContracts.value;
      
    document.getElementById('searchtestpopup').setAttribute("src","add_manual_test.htm?inputFieldId=addJobVessels["+vesselCnt+"].jobVessel.vesselName&rowNum="+vesselCnt+"&searchForm=jobOrderEditOpInstrForm&chosenContracts="+chosenContractList+"&div1=test"+vesselCnt+productCnt+SampleCnt+"&div2=testbody"+vesselCnt+productCnt+SampleCnt+"&nomDate="+nomDate+"&productCnt="+productCnt+"&sampleCnt="+SampleCnt+"&jobnumber="+jobnumber);
    document.getElementById('searchtestpopup').height = "500px";
  }

   function updateSlateIFrameSrc(vesselCnt,productCnt,SampleCnt,nomDate)
  {
  
      chosenContractList = document.jobOrderEditOpInstrForm.chosenContracts.value;
      
    document.getElementById('searchslatepopup').setAttribute("src","search_slate_popup.htm?inputFieldId=addJobVessels["+vesselCnt+"].jobVessel.vesselName&rowNum="+vesselCnt+"&searchForm=jobOrderEditOpInstrForm&chosenContracts="+chosenContractList+"&div1=slate"+vesselCnt+productCnt+SampleCnt+"&div2=slatebody"+vesselCnt+productCnt+SampleCnt+"&nomDate="+nomDate);
    document.getElementById('searchslatepopup').height = "500px";
  }  

function setJobContractCodeVal(elemId,vesselIndex,productIndex,jobContractCode)
{
  
  if(document.getElementById(elemId).checked == true)
  {
    document.jobOrderEditOpInstrForm.chosenContracts.value = jobContractCode;
    document.jobOrderEditOpInstrForm.applicableContractFlag.value = "add";
    document.jobOrderEditOpInstrForm.vesselIndex.value = vesselIndex;
    document.jobOrderEditOpInstrForm.productIndex.value = productIndex;
  }
  else
  {
    document.jobOrderEditOpInstrForm.chosenContracts.value = jobContractCode;
    document.jobOrderEditOpInstrForm.applicableContractFlag.value = "delete";
    document.jobOrderEditOpInstrForm.vesselIndex.value = vesselIndex;
    document.jobOrderEditOpInstrForm.productIndex.value = productIndex;
  }
  document.jobOrderEditOpInstrForm.submit();
}

function setLabAnalysisFlag(index)
{
  document.jobOrderEditOpInstrForm.labAnalysisFlag.value = "Y";
  document.jobOrderEditOpInstrForm.scrollFlag.value = "retainScroll"+index; 
  document.jobOrderEditOpInstrForm.submit();
}
function setOtApprovedFlag(testIndex,index)
{
  document.jobOrderEditOpInstrForm.otApprovedFlag.value = "Y";
  document.jobOrderEditOpInstrForm.otApproveCheckFlag.value = "N";
  //document.jobOrderEditOpInstrForm.testIndexFlag.value = testIndex;
    
  
  if(index=='ind')
	{
	 if(testIndex != 'no')
     document.jobOrderEditOpInstrForm.scrollFlag.value = "priorityScroll"+testIndex; 
	 document.jobOrderEditOpInstrForm.testIndexFlag.value = testIndex;
	}
	else if(index=='massOT')
	{
		document.jobOrderEditOpInstrForm.scrollFlag.value = "massOtScroll"+testIndex;
	}
	else
	{
    document.jobOrderEditOpInstrForm.testIndexFlag.value = testIndex;
	document.jobOrderEditOpInstrForm.scrollFlag.value = "testOtScroll"+testIndex;
	}  
  document.jobOrderEditOpInstrForm.submit();
}
function checkForOtApprovedFlag(vesselIndex,productIndex,sampleIndex,jobTests,jobManualTests,jobSlates)
{
  var elemname = "priority" + vesselIndex+productIndex+sampleIndex;
  var no=vesselIndex+productIndex+sampleIndex;
  var elemval = document.getElementById(elemname).value;
    if(elemval == 'Overtime' || elemval == 'Rush') {
		document.jobOrderEditOpInstrForm.scrollFlag.value = "priorityScroll"+vesselIndex+productIndex+sampleIndex; 
		if((jobTests != null && jobTests =='') && (jobManualTests != null && jobManualTests =='') && (jobSlates != null && jobSlates =='') ) {
			if(confirm("Do you wish to AddTests or AddSlates")) {
			return false;
			} else {				
				setOtApprovedFlag('no','ind');			
			}
		} else {			
			setOtApprovedFlag('no','ind');
		}
	}
	else
    {      
	  document.jobOrderEditOpInstrForm.otApproveCheckFlag.value = "Y";	 
	  document.jobOrderEditOpInstrForm.scrollFlag.value = "priorityScroll"+vesselIndex+productIndex+sampleIndex; 
	  document.jobOrderEditOpInstrForm.submit();
    }

}
function populateInstructions(vesselCnt,productCnt,eventCnt,eventCode)
{
    document.jobOrderEditOpInstrForm.vesselIndex.value = vesselCnt;
    document.jobOrderEditOpInstrForm.productIndex.value = productCnt;
    document.jobOrderEditOpInstrForm.eventIndex.value = eventCnt;
    document.jobOrderEditOpInstrForm.instructionFlag.value = eventCode;
	document.jobOrderEditOpInstrForm.scrollFlag.value="popEventInstr"+vesselCnt+productCnt;
    document.jobOrderEditOpInstrForm.submit();
}

  function onSendWebService(webServiceType)
  {
    document.jobOrderEditOpInstrForm.webServiceFlag.value = webServiceType;
    document.jobOrderEditOpInstrForm.submit();
  } 
function updateMassQty(vesselCnt,productCnt,sampleCnt,testSize,slateSize)
{
  var elemName = "massqty" + vesselCnt + productCnt + sampleCnt;
  var massQuantity = document.getElementById(elemName).value;
  
  
  for(var i=0;i<testSize;i++)
    {
      testElemName = "test" + vesselCnt + productCnt + sampleCnt + i;
      testQtyElemName = "testqty" + vesselCnt + productCnt + sampleCnt + i;
      
        if(document.getElementById(testElemName).checked == true)
        {
          document.getElementById(testQtyElemName).value = massQuantity;
          document.getElementById(testElemName).checked = false;
        }
    }
  for(var j=0;j<slateSize;j++)
    {
      slateElemName = "slate" + vesselCnt + productCnt + sampleCnt + j;
      slateQtyElemName = "slateqty" + vesselCnt + productCnt + sampleCnt + j;
      
        if(document.getElementById(slateElemName).checked == true)
        {
          document.getElementById(slateQtyElemName).value = massQuantity;
          document.getElementById(slateElemName).checked = false;
        }
    }
      var selectAllElemName = "selectAll"+vesselCnt+productCnt+sampleCnt;
          if(document.getElementById(selectAllElemName).checked == true)
        {
        document.getElementById(selectAllElemName).checked = false;
      }

}

function updateMassOT(vesselCnt,productCnt,sampleCnt,testSize,slateSize)
{
  var elemName = "massOT" + vesselCnt + productCnt + sampleCnt;
  var massOTime = document.getElementById(elemName).value;
  
  
  for(var i=0;i<testSize;i++)
    {
      testElemName = "test" + vesselCnt + productCnt + sampleCnt + i;
      testOTElemName = "testot" + vesselCnt + productCnt + sampleCnt + i;
      
        if(document.getElementById(testElemName).checked == true)
        {
          document.getElementById(testOTElemName).value = massOTime;
          document.getElementById(testElemName).checked = false;
        }
    }
  for(var j=0;j<slateSize;j++)
    {
      slateElemName = "slate" + vesselCnt + productCnt + sampleCnt + j;
      slateOTElemName = "slateot" + vesselCnt + productCnt + sampleCnt + j;
      
        if(document.getElementById(slateElemName).checked == true)
        {
          document.getElementById(slateOTElemName).value = massOTime;
          document.getElementById(slateElemName).checked = false;
        }
    } 
      var selectAllElemName = "selectAll"+vesselCnt+productCnt+sampleCnt;
          if(document.getElementById(selectAllElemName).checked == true)
        {
        document.getElementById(selectAllElemName).checked = false;
      }

}

function selectAllTestsSlates(vesselCnt,productCnt,sampleCnt,testSize,manualTestSize,slateSize)    
{
  selectAllElemName = "selectAll"+vesselCnt+productCnt+sampleCnt;
  if(document.getElementById(selectAllElemName).checked)
  {
    for(i=0;i<testSize;i++)
     {
     var testelemName = "test" + vesselCnt+productCnt+sampleCnt + i;
     document.getElementById(testelemName).checked = true;
     }
	 for(k=0;k<manualTestSize;k++)
     {
     var testelemName = "manualTest" + vesselCnt+productCnt+sampleCnt + k;
     document.getElementById(testelemName).checked = true;
     }
  for(j=0;j<slateSize;j++)
     {
     var slateelemName = "slate" + vesselCnt+productCnt+sampleCnt + j;
     document.getElementById(slateelemName).checked = true;
     }     
   }
   else
    clearAllTestsSlates(vesselCnt,productCnt,sampleCnt,testSize,manualTestSize,slateSize);
   
}

function clearAllTestsSlates(vesselCnt,productCnt,sampleCnt,testSize,manualTestSize,slateSize)   
{
  for(i=0;i<testSize;i++)
   {
   var testelemName = "test" + vesselCnt+productCnt+sampleCnt + i;
   document.getElementById(testelemName).checked = false;
   }
   for(k=0;k<manualTestSize;k++)
     {
     var testelemName = "manualTest" + vesselCnt+productCnt+sampleCnt + k;
     document.getElementById(testelemName).checked = false;
     }
  for(j=0;j<slateSize;j++)
   {
   var slateelemName = "slate" + vesselCnt+productCnt+sampleCnt + j;
   document.getElementById(slateelemName).checked = false;
   }
   
}
function warnUser(navigationUrl)
{
  if(document.jobOrderEditOpInstrForm.warnUserFlag.value == 'warn' || document.jobOrderEditOpInstrForm.warnUserFlag.value == 'warned')
    document.jobOrderEditOpInstrForm.warnUserFlag.value = 'navigate';
  else
    document.jobOrderEditOpInstrForm.warnUserFlag.value = "warn";
    
  document.jobOrderEditOpInstrForm.navigationUrl.value = navigationUrl;
  document.jobOrderEditOpInstrForm.submit();
}

////////////////////////////////////////////////////////// Template
 function createTemp()
{
  var tName=document.getElementById("tmpNm");
  if(tName.value==null || tName.value=="")
  {
    confirm("Please Enter Template Name ");

  document.jobOrderEditOpInstrForm.tmpNm.focus();
  return false;
  }
  document.jobOrderEditOpInstrForm.templateFlag.value = "yes";
  top.document.forms[0].submit();
}

function onMassDeleteTestSlate(vesselCnt,productCnt,sampleCnt,testSize,manualTestSize,slateSize)
{
    document.getElementById('chosenTestIds').value='';
    var chosenTests = '';
	var chosenManualTests = '';
    var chosenSlates = '';
        for(var i=0;i<testSize;i++)
     {
       var testelemName = "test" + vesselCnt+productCnt+sampleCnt + i;
       
       if(document.getElementById(testelemName).checked==true)
       {
         
          chosenTests = chosenTests + ',' + i;
      
        
       }
       
       
     }
	 for(var k=0;k<manualTestSize;k++)
     {
       var testelemName = "manualTest" + vesselCnt+productCnt+sampleCnt + k;
       
       if(document.getElementById(testelemName).checked==true)
       {
         
          chosenManualTests = chosenManualTests + ',' + k;
      
        
       }
       
       
     }
    for(var j=0;j<slateSize;j++)
     {
       var slateelemName = "slate" + vesselCnt+productCnt+sampleCnt + j;
       if(document.getElementById(slateelemName).checked== true)
       {
          
            chosenSlates = chosenSlates + ',' + j;
       }
     }
    chosenTests = chosenTests.substring(1,chosenTests.length );
    chosenSlates = chosenSlates.substring(1,chosenSlates.length );
	chosenManualTests = chosenManualTests.substring(1,chosenManualTests.length );
      document.getElementById('chosenTestIds').value=chosenTests;
	  document.getElementById('chosenManualTestIds').value=chosenManualTests;
      document.getElementById('chosenSlateIds').value=chosenSlates;
      
      
      document.jobOrderEditOpInstrForm.vesselIndex.value = vesselCnt;
      document.jobOrderEditOpInstrForm.productIndex.value = productCnt;
      document.jobOrderEditOpInstrForm.sampleLocIndex.value = sampleCnt;
      document.jobOrderEditOpInstrForm.massTestSlateDeleteFlag.value = 'massDelete';
	  document.jobOrderEditOpInstrForm.scrollFlag.value = 'delTotTest'+vesselCnt+productCnt+sampleCnt;	 
      document.jobOrderEditOpInstrForm.submit();
      
}


/*function setTemplateSearchFlag(){
  document.jobOrderEditOpInstrForm.tmpSearchFlag.value = "Y";
}*/

  function populateJobInst()
  {
  document.jobOrderEditOpInstrForm.tmpSearchFlag.value = "tmpSearchFlag";
    document.jobOrderEditOpInstrForm.submit();
  }

////////////////////////////////////////////////////////// Template
function updateBranchIframeSrc(buName)
{
if(buName!= "" && buName!= null)
{
  document.getElementById('jobbu').setAttribute("src",src="search_branch_popup.htm?limsFlag=true&inputFieldId=jobOrder.limsBranchId&div1=jobbranchcode&div2=jobbranchcodebody&buName="+buName+"&formName=opInstrForm");
}
}

function popCancelReason(jobNumber)
{  
  var status =  document.getElementById("sel1").value;
  
  if(status != null && status =='1')
  {
	  
  popup_show('cancelreason', 'cancelreason_drag', 'cancelreason_exit', 'screen-corner', 120, 20);
  hideIt();
  popupboxenable();

  document.getElementById("cancelreasonbox").src="job_cancel_reason_popup.htm?jobNumber="+jobNumber;                            
  document.getElementById("cancelreasonbox").height = "250px";
  }
}
function submitFunction(){
	 document.jobOrderEditOpInstrForm.scrollFlag.value = "none";
	 document.jobOrderEditOpInstrForm.submit();
}

	function updatePortIframeSrc(vesselIndex, productIndex, branchName, destinationId, serviceLocationId)  {
		var serviceLocationName= document.getElementById(serviceLocationId).value;
		var serviceLocationCity = document.getElementById(destinationId).value;
		var portLocationName= document.getElementById(destinationId).value;
	    document.getElementById('portfr').setAttribute("src","search_serviceLocation_popup.htm?inputFieldId="+serviceLocationId+"&div1=port&div2=portbody&serviceLcoationName="+serviceLocationName + "&portLocationName=" + portLocationName+"&branchName="+branchName+"&reqForm=destinationForm");   
	}

  function setPortLcoationFlag(vesselIndex, productIndex)
  {
    document.jobOrderEditOpInstrForm.portLocationFlag.value="portlocation";
    document.jobOrderEditOpInstrForm.vesselIndex.value=vesselIndex;
    document.jobOrderEditOpInstrForm.productIndex.value=productIndex;
  }

// START : #119240
function nextList()
{
document.jobOrderEditOpInstrForm.showNextListFlag.value="next";
document.jobOrderEditOpInstrForm.submit();
}

function prevList()
{
document.jobOrderEditOpInstrForm.showPrevListFlag.value="prev";
document.jobOrderEditOpInstrForm.submit();
}

function noPrevList()
{
	document.jobOrderEditOpInstrForm.noPrevJob.value="true";
	document.jobOrderEditOpInstrForm.submit();
}

function noNextList()
{
	document.jobOrderEditOpInstrForm.noNextJob.value="true";
	document.jobOrderEditOpInstrForm.submit();
}
// END : #119240



</script>
</head>
<icb:list var="divisions">
  <icb:item> 
${icbfn:user().branch.businessUnit.organization.name}</icb:item>
</icb:list>

<icb:list var="divisionBu">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
  <icb:item>${command.jobOrder.branch.businessUnit.name}</icb:item>
</icb:list>
<icb:list var="jobDetail">
  <icb:item>${command.jobOrder.jobNumber}</icb:item>
  <icb:item>${command.jobOrder.otApproved}</icb:item>
</icb:list>
<icb:list var="operationCode">
  <icb:item>${command.jobOrder.operation}</icb:item>
</icb:list>

<icb:list var="UOM">
  <icb:item>UOM</icb:item>
</icb:list>
<icb:list var="option">
  <icb:item>option</icb:item>
</icb:list>
<icb:list var="plusMinus">
  <icb:item>plusMinus</icb:item>
</icb:list>
<icb:list var="sampleLocation">
  <icb:item>sampleLocation</icb:item>
</icb:list>
<icb:list var="sampleTiming">
  <icb:item>sampleTiming</icb:item>
</icb:list>
<icb:list var="sampleType">
  <icb:item>sampleType</icb:item>
</icb:list>
<icb:list var="sampleVolume">
  <icb:item>sampleVolume</icb:item>
</icb:list>
<icb:list var="containerType">
  <icb:item>containerType</icb:item>
</icb:list>
<icb:list var="retainTested">
  <icb:item>retainTested</icb:item>
</icb:list>
<icb:list var="yesNo">
  <icb:item>yesNo</icb:item>
</icb:list>
<icb:list var="priority">
  <icb:item>priority</icb:item>
</icb:list>

<icb:list var="projectType">
  <icb:item>projectType</icb:item>
</icb:list>


<form:form name="jobOrderEditOpInstrForm" method="POST" action="edit_job_operational_info_insp.htm">
<div style="color:red;">
  <form:errors cssClass="error" />
  <c:if test="${not empty requestScope['error_msg']}">
    <f:message key="${requestScope['error_msg']}" />
  </c:if>
</div>
<c:if test="${not empty requestScope['message']}">
  <div style="color:${requestScope['messageColor']}">
    <f:message key="${requestScope['message']}" />
  </div>
</c:if>
      <input type="hidden" name="refreshing" value="false" />
	  <input type="hidden" name="createProject" value="false" />
	  <input type="hidden" name="jobNumber" value="${command.jobOrder.jobNumber}" />
      <input type="hidden" name="_page" value="1" />
      <form:hidden path="addOrDeleteVessel"/>
      <form:hidden path="vesselIndex"/>
      <form:hidden path="targetVesselIndex"/>
      <form:hidden path="addOrDeleteProduct"/>
      <form:hidden path="productIndex"/>
      <form:hidden path="addOrDeleteEvent"/>
      <form:hidden path="eventIndex"/>
      <form:hidden path="addOrDeleteSampleLoc"/>
      <form:hidden path="sampleLocIndex"/>
      <form:hidden path="addOrDeleteTest"/>      
      <form:hidden path="testIndex"/>
      <form:hidden path="addOrDeleteSlate"/>      
      <form:hidden path="slateIndex"/>
      <form:hidden path="addOrDeleteQty"/>
      <form:hidden path="qtyIndex"/> 
      <form:hidden path="copyFlag"/>        
      <form:hidden path="chosenContracts"/>  
      <form:hidden path="chosenTestIds"/>
            <%-- 101771 START --%>
			<form:hidden path="chosenManualTestIds"/>
      <form:hidden path="testDesc"/>  
	  <form:hidden path="manualTestQty"/> 
	  <form:hidden path="manualTestPrice"/>
       <%-- 101771 START --%>        
      <form:hidden path="chosenSlateIds"/> 
      <form:hidden path="nextPageFlag"/>
      <form:hidden path="applicableContractFlag"/>
      <form:hidden path="otApprovedFlag"/>
      <form:hidden path="labAnalysisFlag"/>
      <form:hidden path="instructionFlag"/>
      <form:hidden path="navigationUrl" />
      <form:hidden path="warnUserFlag" />
      <form:hidden path="templateFlag" />
      <form:hidden path="tmpSearchFlag" />
      <form:hidden path="templateNum" />
      <form:hidden path="instrDisplayFlag" />
	  <form:hidden path="massTestSlateDeleteFlag" />
      <form:hidden path="otApproveCheckFlag"/>
      <form:hidden path="goForm"/>
      <form:hidden path="scrollFlag"/>
      <form:hidden path="jobCancelReasonNote"/>
      <form:hidden path="jobCancelReasonUser"/>
      <form:hidden path="testIndexFlag"/>
      <form:hidden path="portLocationFlag" />
      <form:hidden path="sampleLocCount"/>
	  <!-- START : #119240 -->
	  <!--  <input type="hidden" name="nextListFlag" value=""/> -->
 <!--  <input type="hidden" name="prevListFlag" value=""/> -->
  <input type="hidden" name="showNextListFlag" value=""/>
   <input type="hidden" name="showPrevListFlag" value=""/> 	
<form:hidden path="nextListFlag" />
<form:hidden path="prevListFlag" />
<input type="hidden" name="noPrevJob" value=""/>
<input type="hidden" name="noNextJob" value=""/> 	
<!-- END : #119240 -->
      <input type="hidden" name="webServiceFlag" value="" />

<c:if test="${requestScope['noJobMessage'] != null}">  
  <div style="color:green;">
    ${requestScope.noJobMessage}
  </div>
</c:if>

       <c:set var="reopenViewOnly"  value="${!empty command.jobOrder.jobStatus && command.jobOrder.jobStatus  == '7000'}"/>
<c:set var="urlSuffix" value="${icbfn:urlSuffixByJobType(command.jobOrder.jobType)}" scope="request" />                
    <c:set var="urlPrefix" value="${icbfn:urlPrefixByJobStatus(command.jobOrder.jobStatus)}" scope="request" />
   
    <icb:list var="jobStatus">
    <icb:item>jobStatus</icb:item>
    <icb:item>${command.jobOrder.jobStatus}</icb:item>
    <icb:item>${icbfn:jobContractCount(command.jobOrder.jobNumber)}</icb:item>
    <icb:item>${icbfn:invGeneratedFlag(command.jobOrder.jobNumber)}</icb:item>
	<icb:item>${command.jobOrder.jobNumber}</icb:item>
    <icb:item>${command.jobOrder.jobFinishDate}</icb:item>
  </icb:list>
           
<table border="0" cellpadding="0" cellspacing="0" class="MainTable">

  <tr>
    <td valign="top"><!-- BREADCRUMB TRAIL  -->
      <div id="breadcrumbContainer" >
        <table width="100%" border="0" cellpadding="0" cellspacing="0" background="images/intopbluetrailbg.jpg">
          <tr>
            <td width="25"><img src="images/inlfttrailcorner.gif" width="8" height="22"></td>
            <td>
              <table height="22" border="0" align="left" cellpadding="0" cellspacing="0">
                <tr>
                  <td class="breadcrumbtrailDeactive" style="background:none; padding-left:5px;">&#9658; 
                     <a href="${urlPrefix}_job_entry_${urlSuffix}.htm?jobNumber=${command.jobOrder.jobNumber}">
                    <!-- <a href="#" onClick="warnUser('${urlPrefix}_job_entry_${urlSuffix}.htm?jobNumber=${command.jobOrder.jobNumber}')">-->
                      <f:message key="entry"/>  
                    </a>
                  </td>
                  <td class="breadcrumbtrailActive">
                    <f:message key="jobInstructions"/>
                  </td>
                  <td class="breadcrumbtrailDeactive"> 
                    <c:choose>
                      <c:when test="${command.jobOrder.pageNumber >= 3}">               
                         <a href="edit_job_select_charges.htm?jobNumber=${command.jobOrder.jobNumber}"> 
                        <!--<a href="#" onClick="warnUser('edit_job_select_charges.htm?jobNumber=${command.jobOrder.jobNumber}')">-->
                          <f:message key="selectCharges"/>
                        </a>
                      </c:when>
                      <c:otherwise>
                        <f:message key="selectCharges"/>
                      </c:otherwise>
                    </c:choose>
                  </td>
                  <td class="breadcrumbtrailDeactive"> 
                    <c:choose>
                      <c:when test="${command.jobOrder.pageNumber >= 4}">               
                         <a href="edit_job_invoice_preview.htm?jobNumber=${command.jobOrder.jobNumber}"> 
                        <!--<a href="#" onClick="warnUser('edit_job_invoice_preview.htm?jobNumber=${command.jobOrder.jobNumber}')">-->
                         <f:message key="preview"/>
                        </a>
                      </c:when>
                      <c:otherwise>
                        <f:message key="preview"/>
                      </c:otherwise>
                    </c:choose>
                  </td>               
 
                  <td class="breadcrumbtrailDeactive"> 
                    <c:choose>
                      <c:when test="${command.jobOrder.pageNumber >= 5}">               
                         <a href="edit_job_view_invoice.htm?jobNumber=${command.jobOrder.jobNumber}"> 
                        <!--<a href="#" onClick="warnUser('edit_job_view_invoice.htm?jobNumber=${command.jobOrder.jobNumber}')">-->
                          <f:message key="invoice"/>
                        </a>
                      </c:when>
                      <c:otherwise>
                       <f:message key="invoice"/>
                      </c:otherwise>
                    </c:choose>  
                  </td>                               
                  
                  <td align="right">&nbsp;</td>
                </tr>

              </table>
            </td>
			<td align="right">
				<a href="#" onClick="javascript:showRequiredFields()"><f:message key="requiredFields"/></a>
			</td>
            <td align="right"><img src="images/inrttrailcorner.gif" width="7" height="22"></td>
          </tr>
        </table>
      </div>      <!-- BREADCRUMB TRAIL END -->
      

      
               

      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainerji">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span><f:message key="jobInstructions"/></span></a></li>
            </ul>
            <label>
            <select name="sel5" id="sel5" class="SelectionBox" style="float:right" onChange="MM_jumpMenu('parent',this,1)">
              <option selected>Go to ... &gt;</option>
              <option value="edit_job_entry_${urlSuffix}.htm?jobNumber=${command.jobOrder.jobNumber}"><f:message key="entry"/></option>
            </select>
            </label>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                <tbody>
                  <tr>
                    <th><f:message key="jobInstructions"/><img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/><f:message key="jobId"/>: ${command.jobOrder.jobNumber }</th>
                    <th style="text-align:right"><f:message key="jobStatus"/>:
              <form:select id="sel1" cssClass="selectionBox" onchange="popCancelReason('${command.jobOrder.jobNumber}')" path="jobOrder.jobStatus" items="${icbfn:dropdown('staticDropdown',jobStatus)}" itemLabel="name" itemValue="value" />
                          <form:errors path="jobOrder.jobStatus" cssClass="error"/>
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onClick="javascript:popup_show('integrationlog', 'integrationlog_drag', 'integrationlog_exit', 'screen-corner', 120, 20);hideIt();popupboxenable();"><f:message key="integrationLog"/></a>
                    </th>
                    <th style="text-align:right">
           <c:if test="${!command.viewOnly}">
          <a href="#"><img src="images/add.gif" alt="Add Vessel" width="13" height="12" hspace="1px" border="0" onclick="onAddVessel('${fn:length(command.addJobVessels)}')"/></a>
          </c:if>&nbsp;
		 <!-- START : #119240 -->
		   <c:choose>
            <c:when test="${command.originateFromSearchJob==null}">
				&nbsp;
            </c:when>
            <c:otherwise>
              <a href="${command.originateFromSearchJob}?jobNum=${command.jobNumber}">
			   <img src="images/icofindjob.gif" alt="Return to Search" width="16" height="14" border="0" align="absmiddle">			  
			  </a>
			</c:otherwise>
           </c:choose>        
		  <c:choose>
			<c:when test="${command.prevListFlag=='true'}">
              <a href="#" onClick="javascript:prevList();">
			  <img src="images/prevleftarrow.gif" alt="Go to Previous Job" width="13" height="12" hspace="2" border="0"/>
			  </a>
             </c:when>
            <c:otherwise>
		      <c:if test="${command.originateFromSearchJob!=null}">
				  <a href="#" onClick="javascript:noPrevList();">
				  <img src="images/prevleftarrow.gif" alt="Go to Previous Job" width="13" height="12" hspace="2" border="0"/>
				  </a>
				</c:if>
	        </c:otherwise>
        </c:choose>   
	    <c:choose>
          <c:when test="${command.nextListFlag=='true'}">
              <a href="#" onClick="javascript:nextList();">
			  <img src="images/nextrtarrow.gif" alt="Go to Next Job" width="13" height="12" hspace="2" border="0"/></a>
          </c:when>
          <c:otherwise>
			<c:if test="${command.originateFromSearchJob!=null}">
			 <a href="#" onClick="javascript:noNextList();">
			  <img src="images/nextrtarrow.gif" alt="Go to Next Job" width="13" height="12" hspace="2" border="0"/></a>
			</c:if>
          </c:otherwise>
        </c:choose>
		<!-- END : #119240 -->  
		  <a href="job_order_pdf_view.htm?jobNumber=${command.jobOrder.jobNumber}" target="_blank"><img src="images/ico_print.gif" alt="Print Job Order" width="18" height="16" hspace="2" border="0" align="absmiddle" title="Print Job Order"></a>
          <c:if test="${command.samBranchFlag}">
          <a href="#" onclick="javascript:onSendWebService('SAM');"><img src="images/icoship.gif" alt="Send To SAM" hspace="2" border="0" align="absmiddle" title="Send To SAM"></a>
          </c:if>
          <c:if test="${command.limsBranchFlag}">
          <a href="#" onclick="javascript:onSendWebService('LIMS');"><img src="images/icoflask.gif" alt="Send To LIMS" hspace="2" border="0" align="absmiddle" title="Send To LIMS"></a>
          </c:if>
          <a href="#" onclick="goToNextPage()"><img src="images/savennextbluarrow.gif" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page"></a><a href="#"><img src="images/icosave.gif" alt="Save" title="Save" width="14" height="14" border="0" align="absmiddle" onclick="javascript:submitFunction();"/></a></th>
                  </tr>
                  <tr>
                    <td colspan="3" style="padding:0">

          
    <!-- ------------------------ TEMPLATES -------------------------------------------------- -->
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable" >
    
    <tr>
      <td width="16" class="TDShade"> <div id="bluarrowrighttmp" style="visibility:hidden; position:absolute; z-index: 2; margin-left:4px"> <a href="#templates" onClick="javascript:showTemplates();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4" style="margin-top:2px;"/></a> </div>
      <div id="bluarrowdowntmp" style="visibility:visible;position:relative; z-index: 1; margin-top:6px "> <a href="#templates" onClick="javascript:hideTemplates();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></td>
      <td width="871" class="TDShade"><a name="templates" id="templates"></a><f:message key="templates"/></td>
      <td  width="64" class="TDShade">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="3" style="padding:0px;" class="TDShade"><div id="templatesContainer">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="TemplateTable" style="width:100%;">
        <tr>
        <c:if test="${command.templateHideFlg == 'YES'}"> 
          <c:if test="${(command.jobOrder.tempName != null) && (command.jobOrder.tempName != '')}">
           <th>
            <f:message key="templateName"/>:
            <label>
              <form:input id="tmpNm" cssClass="inputBox" size="30" path="tempName" maxlength="128" disabled="true" />
              <form:errors path="tempName" cssClass="redstar"/>
            </label>
            &nbsp;&nbsp;
            <label>
              <input name="Button" type="button" class="button1" value="Create Template" style="border-width:1px;" onClick="createTemp();" disabled="true" />
            </label>
          </th>
          </c:if>
          <c:if test="${(command.jobOrder.tempName == null) || (command.jobOrder.tempName == '')}">
           <th>
            <f:message key="templateName"/>:
            <label>
              <form:input id="tmpNm" cssClass="inputBox" size="30" path="tempName" maxlength="128"/>
              <form:errors path="tempName" cssClass="redstar"/>
            </label>
            &nbsp;&nbsp;
            <label>
              <input name="Button" type="button" class="button1" value="Create Template" style="border-width:1px;" onClick="createTemp();"/>
            </label>
          </th>
          </c:if>

            <th>
            <f:message key="searchTemplate"/>:
            <label>
                <form:input cssClass="inputBox" path="searchTemplate" size="50" maxlength="128" disabled="true"/>
                <form:errors path="searchTemplate" cssClass="redstar"/>       
            </label>
            
         <!--a href="#" onClick="javascript:setTemplateSearchFlag();popup_show('templatename', 'templatename_drag', 'templatename_exit', 'screen-corner', 120, 20);hideIt();popupboxenable();"> <img src="images/lookup.gif" alt="lookup Template Name" width="13" height="13" border="0"></a-->

           </th>
        </c:if>

        <c:if test="${command.templateHideFlg == 'NO'}"> 
           <th>
            <f:message key="templateName"/>:
            <label>
              <form:input id="tmpNm" cssClass="inputBox" size="30" path="tempName" maxlength="128" />
              <form:errors path="tempName" cssClass="redstar"/>
            </label>
            &nbsp;&nbsp;
            <label>
              <input name="Button" type="button" class="button1" value="Create Template" style="border-width:1px;" onClick="createTemp();"/>
            </label>
            </th>
            <th>
            <f:message key="searchTemplate"/>:
            <label>
                <form:input cssClass="inputBox" id="searchTemplate" path="searchTemplate" size="50" maxlength="128"/>
                <form:errors path="searchTemplate" cssClass="redstar"/>       
            </label>
            
            <!--input name="GO" type="button" class="button1" value="GO"  onClick="searchTemp();"/--> 

         <a href="#" onClick="javascript:setTemplateSearchFlag();popup_show('templatename', 'templatename_drag', 'templatename_exit', 'screen-corner', 120, 20);hideIt();popupboxenable();updateTemplateIFrameSrc('${command.jobOrder.buName }','${command.jobOrder.branchName }','${command.searchTemplate }');"> <img src="images/lookup.gif" alt="lookup Template Name" width="13" height="13" border="0"></a>

           </th>
        </c:if>

        </tr>
        </table>
      </div></td>
    </tr>
    </table>

     <table>
        <tr><td></td></tr>
        </table>
    <!-- --------------------------------------- TEMPLATES END -------------------------------------- -->


              <!-- ------------------------ VESSELS -------------------------------------------------- -->
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainApplTable">
                

     <c:forEach items="${command.addJobVessels}" var="arrJobVessel" varStatus="vesselCounter">                               
 <form:hidden path="addJobVessels[${vesselCounter.index}].displayStatus" />                        <tr>
                    <td width="98%" colspan="3" style="padding:0">
          
          <!-- -------------------- VESSEL 1 CONTAINER -------------------------- -->
                      <div id="vessel${vesselCounter.index}" class="vessels" style="z-index:1;visibility:visible;">
                        <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable" >
                          <tbody>
                            <tr>
                              <td class="TDShade" > 
                              
                              
  
              
              
                              <div id="bluarrowrightv${vesselCounter.index}" style="visibility:hidden; position:absolute; z-index: 2; margin-left:4px"> 
                              <a href="#hvess${vesselCounter.index}"> 
                              <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/ style="margin-top:2px;" onClick="javascript:showvesselTable('${vesselCounter.index}');">                              </a>                              </div>
                              
                              <div id="bluarrowdownv${vesselCounter.index}" style="visibility:visible;position:relative; z-index: 1; margin-top:6px "> 
                              <a href="#svess${vesselCounter.index}"> 
                              <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"  onClick="javascript:hidevesselTable('${vesselCounter.index}');"/>                              </a>                              </div>                              </td>
                              <c:if test="${command.jobOrder.jobType == 'INSP' || command.jobOrder.jobType=='MAR'}">
                              <td width="12%" class="TDShade" ><a name="vessel1"></a><f:message key="vessel"/> ${vesselCounter.index + 1}:<span class="redstar">*</span> </td>
                              </c:if>
                               <c:if test="${command.jobOrder.jobType == 'FST' || command.jobOrder.jobType=='OUT'}">
                              <td width="10%" class="TDShade" ><a name="vessel1"></a><f:message key="location"/> ${vesselCounter.index + 1}:<span class="redstar">*</span> </td>
                              </c:if>
                              
                              
                              <td width="20%" class="TDShadeBlue">
                              <form:input cssClass="inputBox" size="20" path="addJobVessels[${vesselCounter.index}].jobVessel.vesselName" maxlength="256" disabled="${command.viewOnly && !(reopenViewOnly && empty fn:trim(addJobVessels[vesselCounter.index].jobVessel.vesselName)) }"/>
                              <form:errors path="addJobVessels[${vesselCounter.index}].jobVessel.vesselName" cssClass="redstar"/>
                          <!--  
                              <a href="#" onClick="javascript:popup_show('vesselsearch${vesselCounter.index}', 'vesselsearch_drag${vesselCounter.index}', 'vesselsearch_exit${vesselCounter.index}', 'screen-corner', 120, 20); popupboxenable();hideIt();">
                              <img src="images/lookup.gif" width="13" height="13" border="0" align="absmiddle" />
                              </a> -->                              </td>
                              
                              <td width="40%" nowrap class="TDShade" >
                              <c:if test="${command.jobOrder.jobType == 'INSP' || command.jobOrder.jobType=='MAR'}">
                              <strong><f:message key="vesselType"/>:<span class="redstar">*</span></strong>                              
                          <form:select id="sel2" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].jobVessel.type" items="${icbfn:dropdown('vesselType', null)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly && !(reopenViewOnly && empty addJobVessels[vesselCounter.index].jobVessel.type) }"/>
                              </c:if>                              </td>
                              <td colspan="2" width="20%" class="TDShadeBlue">&nbsp;</td><a name="addVess${vesselCounter.index+1}"></a>
                              <td width="62" align="right" nowrap  class="TDShadeBlue"><div id="tablefunction" style="width:100px; text-align:center; margin-right:0;"><a href="#expan${vesselCounter.index}" onclick="javascript:expandAllv1(${vesselCounter.index},${fn:length(arrJobVessel.addJobProds)},'${command.jobOrder.jobType}');"><img src="images/icoexpandall.gif" alt="Expand All" width="20" height="17" border="0"/></a>&nbsp; <a href="#collap${vesselCounter.index}" onclick="javascript:collapseAllv1(${vesselCounter.index},${fn:length(arrJobVessel.addJobProds)},'${command.jobOrder.jobType}');"><img src="images/icocollapseall.gif" alt="Collapse All" width="20" height="17" border="0"/></a> 
                <c:if test="${!command.viewOnly}">
                <a href="#" onclick="onAddProduct('${vesselCounter.index}','${fn:length(arrJobVessel.addJobProds)}')"><img src="images/add.gif" alt="Add Product" width="13" height="12" border="0"/></a><a name="addProd${vesselCounter.index}"></a><a name="delProd${vesselCounter.index}"></a><a href="#" onclick="javascript:onCopyVessel('${vesselCounter.index }','${fn:length(command.addJobVessels)}');"><img src="images/copy.gif" alt="Copy Vessel" width="13" height="12" hspace="1px" border="0"/></a><a name="copyVess${vesselCounter.index+1}"></a><a href="#"><img src="images/delete.gif" alt="Delete Vessel" width="13" height="12" hspace="1px" border="0"   onclick="onDeleteVessel(${vesselCounter.index })"/></a><a name="delvessel${vesselCounter.index}"></a>
                </c:if>&nbsp;</td>
                            </tr>
                          </tbody>
                        </table>
                        <!-- ------------------------VESSEL 1 Data ------------------------- -->


                              
                        <div id="vessel${vesselCounter.index}Container" class="vesselContainer">
            
                          <!-- -------------------VESSEL 1 Product 1 --------------------------- -->
                  
                  
                          <div id="productTablev${vesselCounter.index}" class="productContainer" >
                                    
              <table width="100%" border="0" cellspacing="0" cellpadding="0" > <!-- Product Table Holder -->
                  
                            
               <c:forEach items="${arrJobVessel.addJobProds}" var="arrJobProd" varStatus="productCounter">   
                   <form:hidden path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].displayStatus" />     
                <tr>
                <td align="right" style="background-image:url(images/vesselcontainerbg.jpg); background-repeat:repeat-x; background-color:#fafeff;"> 
                
                  <div style="width:100%; text-align:left;visibility:visible;" >
                  <f:message key="applicableContracts"/>: 
                  <c:forEach items="${arrJobProd.applicableContracts}" var="arrAppJobContracts" varStatus="appContractCounter"> 
                <input type="checkbox" id="appchk${vesselCounter.index }${productCounter.index }${appContractCounter.index }" name="chk${vesselCounter.index }${productCounter.index }${appContractCounter.index }" value="${command.addJobVessels[vesselCounter.index].addJobProds[productCounter.index].applicableContracts[appContractCounter.index].jobContract.id}" onClick="setJobContractCodeVal('chk${vesselCounter.index }${productCounter.index }${appContractCounter.index }','${vesselCounter.index }','${productCounter.index }','${command.addJobVessels[vesselCounter.index].addJobProds[productCounter.index].applicableContracts[appContractCounter.index].jobContract.id}')" checked>
                 ${command.addJobVessels[vesselCounter.index].addJobProds[productCounter.index].applicableContracts[appContractCounter.index].jobContract.contractCode}&nbsp;&nbsp;${command.addJobVessels[vesselCounter.index].addJobProds[productCounter.index].applicableContracts[appContractCounter.index].jobContractIndex}
&nbsp;&nbsp;&nbsp;                </c:forEach>
                <c:forEach items="${arrJobProd.notApplicableContracts}" var="arrJobContractCodes" varStatus="jobContractCounter"> 
                <input type="checkbox" id="chk${vesselCounter.index }${productCounter.index }${jobContractCounter.index }" name="chk${vesselCounter.index }${productCounter.index }${jobContractCounter.index }" value="" onClick="setJobContractCodeVal('chk${vesselCounter.index }${productCounter.index }${jobContractCounter.index }','${vesselCounter.index }','${productCounter.index }','${command.addJobVessels[vesselCounter.index].addJobProds[productCounter.index].notApplicableContracts[jobContractCounter.index].jobContract.id}')">
                 ${command.addJobVessels[vesselCounter.index].addJobProds[productCounter.index].notApplicableContracts[jobContractCounter.index].jobContract.contractCode}&nbsp;&nbsp;${command.addJobVessels[vesselCounter.index].addJobProds[productCounter.index].notApplicableContracts[jobContractCounter.index].jobContractIndex}&nbsp;
&nbsp;&nbsp;&nbsp;                </c:forEach>
                    </div>
                <!-- Product Table Start -->
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="OperationsTable" style="width:100%;">
                              <tr>
                              
                                <th width="13" rowspan="2" valign="top" style="border-right:none;" > 
                                <div id="bluarrowrightv${vesselCounter.index }p${productCounter.index }" style="visibility:hidden; position:absolute; z-index: 5; margin-left:4px"> 
                                <a href="#sp${vesselCounter.index}${productCounter.index}"> 
                                <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="4" style="margin-top:2px;" onClick="javascript:showvproductTable(${vesselCounter.index },${productCounter.index },'${command.jobOrder.jobType}');"/>                                </a>                                </div>
                                
                                <div id="bluarrowdownv${vesselCounter.index}p${productCounter.index }" style="visibility:visible;position:relative; z-index: 5; margin-top:6px "> 
                                <a href="#hp${vesselCounter.index}${productCounter.index}"> 
                                <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="4" onClick="javascript:hidevproductTable(${vesselCounter.index },${productCounter.index },'${command.jobOrder.jobType}');"/></a>                                </div>                                </th>
                                
                                <th width="248" class="TDShadeGrey"><f:message key="product"/> ${ productCounter.index + 1}:<span class="redstar">*</span> <a name="addProd${vesselCounter.index}${productCounter.index+1}"></a>
                          <form:input cssClass="inputBox" size="10" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.jobProductName" maxlength="256" disabled="${command.viewOnly  && !(reopenViewOnly && empty fn:trim(addJobVessels[vesselCounter.index].addJobProds[productCounter.index].jobProd.jobProductName)) }"/>
                <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.jobProductName" cssClass="redstar"/>                                </th>
                                
                                <th width="470" align="left" nowrap class="TDShadeGrey" ><strong><f:message key="productGroup"/>:<span class="redstar">*</span></strong>
                    <form:select id="sel3" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.group" items="${icbfn:getProductGroups('productGroup', command.jobOrder)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly && !(reopenViewOnly && empty addJobVessels[vesselCounter.index].addJobProds[productCounter.index].jobProd.group)}"/></th>
                                  
                                <th width="187" align="left" nowrap="nowrap" class="TDShadeGrey" cssStyle="z-index:5;">
      <c:if test="${command.jobOrder.jobType == 'INSP' || command.jobOrder.jobType=='MAR'}">
      <f:message key="destination"/>: 
      <form:input cssClass="inputBox" cssStyle="z-index:5;" size="25" maxlength="256" id="V${vesselCounter.index}P${productCounter.index}Destination"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.destination" disabled="${command.viewOnly}"/>
      <!-- form:hidden id="V${vesselCounter.index}P${productCounter.index}ServiceLocation"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].serviceLocation" />
      <a href="#"  onClick="javascript:updatePortIframeSrc('${vesselCounter.index}','${productCounter.index}','${command.jobOrder.branchName}','V${vesselCounter.index }P${productCounter.index}Destination', 'V${vesselCounter.index }P${productCounter.index}ServiceLocation');setPortLcoationFlag('${vesselCounter.index}','${productCounter.index}');popup_show('port','port_drag','port_exit', 'screen-corner', 12, 5);hideIt();showPopupDiv('port','portbody');popupboxenable();">
      <img src="images/lookup.gif" id="lookup"  width="13" height="13" border="0" />
      </a -->

	  <form:hidden id="V${vesselCounter.index}P${productCounter.index}ServiceLocation"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].serviceLocation" />
      <a href="#"  onClick="javascript:updatePortIframeSrc('${vesselCounter.index}','${productCounter.index}','${command.jobOrder.branchName}','V${vesselCounter.index }P${productCounter.index}Destination', 'V${vesselCounter.index }P${productCounter.index}ServiceLocation');setPortLcoationFlag('${vesselCounter.index}','${productCounter.index}');popup_show('port','port_drag','port_exit', 'screen-corner', 12, 5);hideIt();showPopupDiv('port','portbody');popupboxenable();">
      <img src="images/lookup.gif" id="lookup"  width="13" height="13" border="0" />
      </a> 
      <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.destination" cssClass="redstar"/>
	 <ajax:autocomplete
          baseUrl="servicelocation.htm" source="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.destination" 
          target="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.destination" className="autocomplete"
          parameters="serviceLocation.serviceLocationCity={addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.destination},jobOprInstrFrom=jobOperationlInstr"
          minimumCharacters="3"/>
	  </c:if></th>
	  <!-- ajax:autocomplete
          baseUrl="servicelocation.htm" source="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.destination" 
          target="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.destination" className="autocomplete"
          parameters="serviceLocation.serviceLocationCity={addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.destination}"
          minimumCharacters="3"/ --> 
                                <th width="38"  align="right" nowrap="nowrap" class="TDShadeGreyDark" ><span style="text-align:right;"> 
                <c:if test="${!command.viewOnly}">
				
                <a href="#cprod${vesselCounter.index}${productCounter.index}" onclick="javascript:onCopyProduct();popup_show('copyprod${vesselCounter.index }${productCounter.index }', 'copyprod_drag${vesselCounter.index }${productCounter.index }', 'copyprod_exit${vesselCounter.index }${productCounter.index }', 'screen-corner', 120, 20);showFrmCopyProd();hideIt();"> <img src="images/copy.gif" alt="Copy Product" width="13" height="12" hspace="1px" border="0"/><a name="copyProduct${vesselCounter.index}${productCounter.index+1}"></a><a href="#"><img src="images/delete.gif" alt="Delete Product" width="13" height="12" hspace="1px" border="0" onclick="onDeleteProduct(${vesselCounter.index},${productCounter.index })"/></a>
                </c:if></span></th>
                              </tr>
                              <div id="product${productCounter.index}Container" style="z-index:1;">
                              <tr>
                                <td colspan="6" style="border-right:#eeeeee 1px solid;">
                
                <!-- ------------------QUANTITY CONTAINER V1 ----------------------- -->
                        <c:if test="${command.jobOrder.jobType == 'INSP' || command.jobOrder.jobType=='MAR'}">
                                  <div id="quantityContainerv${vesselCounter.index}p${productCounter.index}" class="quantityContainer" style="visibility:visible;" style="z-index:1;">
                                    <table width="100%"  align="center" cellpadding="0" cellspacing="0" class="orderTable" style="border:none;z-index:2;">
                                      <tr>
                                        <th width="13" valign="top"  style="border-bottom:none;"> <div id="bluarrowrightv${vesselCounter.index }p${productCounter.index }q1" style="visibility:hidden; position:absolute; z-index:2; margin-left:4px"> <a href="#soq${vesselCounter.index }${productCounter.index}"> <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="6" onclick="javascript:showvpquantityTable(${vesselCounter.index },${productCounter.index });"/> </a> </div>
                                            <div id="bluarrowdownv${vesselCounter.index }p${productCounter.index }q1" style="visibility:visible;position:relative; z-index:1; margin-top:6px "> <a href="#hoq${vesselCounter.index }${productCounter.index}"> <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="6"  onclick="javascript:hidevpquantityTable(${vesselCounter.index },${productCounter.index });"/> </a> </div></th>
                                        <c:if test="${command.jobOrder.jobType == 'INSP' || command.jobOrder.jobType=='MAR'}">
                                        <th  style="background-color:#f2f1f1;"><img src="images/spacer.gif" width="1" height="1" /></th>
                                        <th width="10%" align="left" style="background-color:#f2f1f1;" ><f:message key="orderedQty"/></th>
                                        <th width="10%" align="left" style="background-color:#f2f1f1;" ><f:message key="uom"/></th>
                                        <th width="13%" align="left" style="background-color:#f2f1f1;" ><f:message key="option"/></th>
                                        <th width="7%" align="left" style="background-color:#f2f1f1;" ><b><f:message key="+"/></b></th>
                                        <th width="7%" style="background-color:#f2f1f1;" >&nbsp;</th>
                                        <th width="18%" align="left" style="background-color:#f2f1f1;" ><f:message key="vesselorDrafts"/></th>
                                        <th width="12%" align="left" style="background-color:#f2f1f1;" ><f:message key="tanks"/></th>
                                        <th width="10%" align="right" style="border-right:none"><div id="div14" style="text-align:right;"> 
                    <c:if test="${!command.viewOnly}">
                    <a href="#"><img src="images/add.gif" alt="Add Quantity" width="13" height="12" hspace="1px" align="right" border="0" onclick="onAddQty(${vesselCounter.index},${productCounter.index})"/></a><a name="addQuantity${vesselCounter.index}${productCounter.index}"></a><a name="delQuantity${vesselCounter.index}${productCounter.index}"></a>
                    </c:if>
                                                </c:if>
                                        </div></th>
                                      </tr>
                                      <c:if test="${command.jobOrder.jobType == 'INSP' || command.jobOrder.jobType=='MAR'}">
                                        <tr>
                                          <td width="13" valign="top"  style="border-bottom:none;">&nbsp;</td>
                                          <td width="4%">${productCounter.index + 1 }.0</td>
                                          <td width="15%" >
                      <c:choose>
                      <c:when test="${fn:length(arrJobProd.jobProdQtys) > 0}">
                      <form:input cssClass="inputBox" cssStyle="text-align:right; background-color:#d2e1ff; color:#000099;" size="15" maxlength="15"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.productQty" disabled="true"/>
                      </c:when>
                      <c:otherwise>
                      <form:input cssClass="inputBox" cssStyle="text-align:right; background-color:#d2e1ff; color:#000099;" size="15" maxlength="15"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.productQty" disabled="${command.viewOnly}"/>
                      </c:otherwise>
                      </c:choose>   
                                              <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.productQty" cssClass="redstar">
                        <div style="color:red;"><f:message key="orderedQtyError"/></div>
                        </form:errors>
                        </td>
                                          <td width="10%" align="left" ><form:select id="sel4" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.uom" items="${icbfn:dropdown('staticDropdown',UOM)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/>
                                              <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.uom" cssClass="redstar"/></td>
                                          <td width="10%" align="left"><form:select id="sel5" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.option" items="${icbfn:dropdown('staticDropdown',option)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/>
                                              <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.option" cssClass="redstar"/></td>
                                          <td width="7%" align="left"><form:select id="sel6" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.plusMinus" items="${icbfn:dropdown('staticDropdown',plusMinus)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/>
                                              <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.plusMinus" cssClass="redstar"/>                                          </td>
                                          <td width="7%" align="left"><form:input cssClass="selectionBox" cssStyle="width:20px;" size="3"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.plusMinusPct" disabled="${command.viewOnly}"/>
                                              <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.plusMinusPct" cssClass="redstar"/>
                                              <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.plusMinusPct" cssClass="redstar"/>
                                            %</td>
                                          <td width="7%" align="left"><form:input cssClass="inputBox"  size="10" maxlength="64"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.drafts" disabled="${command.viewOnly}"/>
                                              <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.drafts" cssClass="redstar"/></td>
                                          <td width="12%" align="left"><form:input cssClass="inputBox" size="10" maxlength="256"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.tanks" disabled="${command.viewOnly}"/>
                                              <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProd.tanks" cssClass="redstar"/>                                          </td>
                                          <td align="left" class="TDGreyDark" style="border-right:none" ></td>
                      
                                        </tr>
                                      </c:if>
                                    </table>
                                    <div id="quantitytablev${vesselCounter.index }p${productCounter.index}" style="display:block; visibility:visible;" style="z-index:1;">
                                      <table width="100%" class="orderTable"  border="0" align="center" cellpadding="00" cellspacing="0"  style="border:none;">
                                      
                                      <c:forEach items="${arrJobProd.jobProdQtys}" var="arrJobProdQty" varStatus="qtyCounter">   
                                      
                                        <tr >

                                         <td width="13">
                                           <img src="images/spacer.gif" width="13" height="1" />                                          </td> 
                                         <td width="5%" align="left">&nbsp;&nbsp;${productCounter.index + 1 }.${qtyCounter.index + 1 }                                          </td>
                                        <td width="15%" align="left"><form:input cssClass="inputBox"  size="15" maxlength="15"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].productQty" onchange="onUpdateQty(${vesselCounter.index},${productCounter.index})"/>
                    <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].productQty" cssClass="redstar">
          <div style="color:red;"><f:message key="orderedQtyError"/></div>
          </form:errors>
          </td>
                                        <td width="10%" align="left">&nbsp;${arrJobProdQty.uom}
                    <%--<form:select id="sel4" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].uom" items="${icbfn:dropdown('UOM', null)}" itemLabel="name" itemValue="value" />--%>                                        </td>
                                        <td width="13%" align="left">${arrJobProd.optionDesc}
                                        <%--<form:select id="sel5" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].option" items="${icbfn:dropdown('option', null)}" itemLabel="name" itemValue="value" /> --%>                                       </td>
                                        <td width="7%" align="left">${arrJobProdQty.plusMinus}
                                        <%--<form:select id="sel6" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].plusMinus" items="${icbfn:dropdown('plusMinus', null)}" itemLabel="name" itemValue="value" />--%></td>
                                        <td width="7%" align="left">${arrJobProdQty.plusMinusPct}
                                       <%-- <form:input cssClass="inputBox" cssStyle="width:20px;" size="3" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].plusMinusPct" />
                    <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].plusMinusPct" cssClass="redstar"/>     --%>                                    
                                          %                                        </td>
                                      <td width="18%" align="left"><form:input cssClass="inputBox"  size="10" maxlength="64"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].drafts" disabled="${command.viewOnly}"/>
                    <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].drafts" cssClass="redstar"/>                                    </td>
                                    <td width="12%" align="left"><form:input cssClass="inputBox" size="10" maxlength="256"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].tanks" disabled="${command.viewOnly}"/>
                    <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobProdQtys[${qtyCounter.index}].tanks" cssClass="redstar"/>                                    </td>
                                          <td width="10%" align="right" style="text-align:right; border-right:none;">
                                          <div id="div19" style="text-align:ri">
                      <c:if test="${!command.viewOnly}">
                      <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1" border="0" onClick="onDeleteQty(${vesselCounter.index},${productCounter.index},${qtyCounter.index })"/></a>
                      </c:if>&nbsp;                                          </div>                                          </td>
                                        </tr>
                                        </c:forEach>
                                      </table>
                                    </div>
                                  </div>
                                  </c:if>
                  <!-- ----------------QUANTITY CONTAINER V1 END ----------------------- -->
                  
                                  <!-- ---------------------INSPECTION CONTAINER V1 ------------------------ -->
                  
                                  <div id="inspectionContainerv${vesselCounter.index }p${productCounter.index }" class="inspectionContainer" style="z-index:1;">
                                    <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" style="border:none;">
                                      <tr >
                                        <th width="47" style="background-color:#f2f1f1;" > <div id="bluarrowrightv${vesselCounter.index }p${productCounter.index}in1" style="visibility:hidden; position:absolute; z-index: 2; margin-left:4px"> <a href="#sei${vesselCounter.index}${productCounter.index}" onClick="javascript:showvpinspectionTable(${vesselCounter.index },${productCounter.index });"> <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="4"/></a> </div>
                                          <div id="bluarrowdownv${vesselCounter.index }p${productCounter.index }in1" style="visibility:visible;position:relative; z-index: 1; margin-top:5px "> <a href="#hei${vesselCounter.index}${productCounter.index}" onClick="javascript:hidevpinspectionTable(${vesselCounter.index },${productCounter.index });"> <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="4"/></a> </div></th>
                                        <th width="188" style="background-color:#f2f1f1;" > <f:message key="events"/></th>
                                        <th width="583" style="background-color:#f2f1f1;" > <f:message key="eventInstructions"/></th>
                                        <th width="69" align="left" nowrap="nowrap" style="background-color:#f2f1f1; text-align:left;"><f:message key="sort"/>#</th>
                                        <th width="56" align="right" style="border-right:none;text-align:right;"> <div id="div19" style="width:25px;">
                    <c:if test="${!command.viewOnly}">
                    <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0" align="right" onclick="onAddEvent(${vesselCounter.index },${productCounter.index })"/></a><a name="addEventRow${vesselCounter.index }${productCounter.index }"></a><a name="delEventRow${vesselCounter.index }${productCounter.index }"><a name="popEventInstr${vesselCounter.index }${productCounter.index }"></a></a>
                    </c:if></div></th>
                                      </tr>
                                    </table>
                                    <div id="inspectiontablev${vesselCounter.index }p${productCounter.index }" style="visibility:visible; display:block;width:100%">
                                      <table width="100%" class="orderTable"  border="0" align="center" cellpadding="00" cellspacing="0" style="border:none;">
                                        <c:forEach items="${arrJobProd.jobEvents}" var="arrJobEvents" varStatus="eventCounter">
                                          <tr >
                                            <td width="37"><img src="images/spacer.gif" width="15" height="1" /></td>
                                            <!-- <td width="13" rowspan="3" >
                                          <img src="images/spacer.gif" width="18" height="1" />
                                          </td> -->
                                            <td width="207"><span class="redstar">*</span>
                                                <form:select id="sel7" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobEvents[${eventCounter.index}].event.eventCode" items="${icbfn:dropdown('events', operationCode)}" itemLabel="name" itemValue="value" onchange="populateInstructions(${vesselCounter.index },${productCounter.index},${eventCounter.index },'instr')" disabled="${command.viewOnly}"/>
                                            </td>
                                            <td width="553" ><form:input cssClass="inputBox" size="60" maxlength="200"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobEvents[${eventCounter.index}].eventInstructions" disabled="${command.viewOnly}"/>
                                                <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobEvents[${eventCounter.index}].eventInstructions" cssClass="redstar"/>
                                            </td>
                                            <td width="96" align="left" style="text-align:left"><form:input cssClass="inputBox" cssStyle="width:13px; text-align:right" size="2" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobEvents[${eventCounter.index}].sortOrder" disabled="${command.viewOnly}"/>
                                                <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].jobEvents[${eventCounter.index}].sortOrder" cssClass="redstar"/></td>
                                            <td width="50" align="right" style="text-align:right; border-right:none;"><div id="div"> 
                      <c:if test="${!command.viewOnly}">
                      <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1" border="0" onclick="onDeleteEvent(${vesselCounter.index},${productCounter.index},${eventCounter.index})"/></a>
                    </c:if>&nbsp;</div></td>
                                          </tr>
                                        </c:forEach>
                                      </table>
                                    </div>
                                  </div>
                                  <!-- -----------------Inspection Table V1 END ---------------------- -->
                  
                                  
                  <!-- ---------------- Sample Location Container Table V1 --------------------- -->
                  
                                  <div id="samplelocContainerv${vesselCounter.index }p${productCounter.index }" class="samplelocContainer" style="visibility:visible;">
                                    <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" style="border:none;">
                                      <tr >
                                        <th width="3%" style="background-color:#f2f1f1;"> <div id="bluarrowrightv${vesselCounter.index }p${productCounter.index }s1" style="visibility:hidden; position:absolute; z-index: 2; margin-left:4px"> <a href="#show${vesselCounter.index}${productCounter.index}" onClick="javascript:showvpsampleTable(${vesselCounter.index },${productCounter.index },${fn:length(arrJobProd.addJobProdSamples)});"> <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="6"/></a> </div>
                                          <div id="bluarrowdownv${vesselCounter.index }p${productCounter.index }s1" style="visibility:visible;position:relative; z-index: 1; margin-top:6px "> <a href="#hide${vesselCounter.index}${productCounter.index}" onClick="javascript:hidevpsampleTable(${vesselCounter.index },${productCounter.index },${fn:length(arrJobProd.addJobProdSamples)});"> <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="6"/></a> </div></th>
                                        <th width="13%" align="left" nowrap="nowrap" style="background-color:#f2f1f1; text-align:left" ><f:message key="sampleLoc"/></th>
                                        <th width="7%" align="left" style="background-color:#f2f1f1;" ><f:message key="ofSamps"/></th>
      <c:choose>
      <c:when test="${command.jobOrder.jobType == 'INSP' || command.jobOrder.jobType=='MAR'}">                
                        <th width="7%" align="left" style="background-color:#f2f1f1;" ><f:message key="tank"/>#</th>
      <th width="11%" align="left" nowrap="nowrap" style="background-color:#f2f1f1;" ><f:message key="sample"/>&nbsp;<f:message key="timing"/></th>
                        <th width="10%"  align="left" nowrap="nowrap" style="background-color:#f2f1f1;" ><f:message key="samplesType"/></th>
      </c:when>
      <c:otherwise>
      <th width="7%" align="left" style="background-color:#f2f1f1;" ><f:message key="custSampDesc"/></th>
      </c:otherwise>
      </c:choose>
                            
                      
                                        <th width="9%" align="left" nowrap="nowrap" style="background-color:#f2f1f1;" ><f:message key="sampleVol"/></th>
                                        <th width="11%" align="left" nowrap="nowrap" style="background-color:#f2f1f1;" ><f:message key="containerType"/></th>
                                        <th width="12%" align="left" nowrap="nowrap" style="background-color:#f2f1f1;" ><f:message key="retainTested"/></th>
          <th width="9%" align="left" nowrap="nowrap" style="background-color:#f2f1f1;" ><f:message key="priority"/></th>
                                        <th width="9%" align="left" style="background-color:#f2f1f1;" nowrap ><f:message key="sort"/>#</th>
                                       <%--  <th width="6%" align="right" style="background-color:#f2f1f1;" >
                    <c:if test="${!command.viewOnly}">
                    <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0" align="right" onClick="onAddSampleLoc(${vesselCounter.index },${productCounter.index},${fn:length(arrJobProd.addJobProdSamples)})"/></a>
					<a name="delRowSample${vesselCounter.index}${productCounter.index}"></a>
                    </c:if></th>--%>
                    <th width="6%" style="background-color:#f2f1f1;" >
                    <c:if test="${!command.viewOnly}">
                    <a href="#addsamp${vesselCounter.index}${productCounter.index}"  onclick="onAddSampleLoc(${vesselCounter.index },${productCounter.index},${fn:length(arrJobProd.addJobProdSamples)}); popup_show('sampleLoc${vesselCounter.index}${productCounter.index}', 'sampleLoc_drag${vesselCounter.index}${productCounter.index}', 'sampleLoc_exit${vesselCounter.index}${productCounter.index}', 'screen-corner', 120, 20);hideIt();popupboxenable();showPopupDiv('sampleLoc${vesselCounter.index}${productCounter.index}','sampleLocBody${vesselCounter.index}${productCounter.index}');"><img src="images/add.gif" alt="Add Sample Location(s)" width="13" height="12" hspace="1px" border="0" align="right"/></a>
				    <a name="delRowSample${vesselCounter.index}${productCounter.index}"></a>
					</c:if>
					 </th>
                     
                    
                                      </tr>
                    </table>

                    <div id="sampleloctablev${vesselCounter.index }p${productCounter.index }" style="visibility:visible; display:block;width:100%">
                      <table width="100%"  class="orderTable"  border="0" align="center" cellpadding="00" cellspacing="0" style="border:none;">
                        <c:forEach items="${arrJobProd.addJobProdSamples}" var="arrAddJobProdSamples" varStatus="sampleCounter">
      <form:hidden path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].displayStatus" />   
                          <tr >
                            <td width="3%"><img src="images/spacer.gif" width="15" height="1" /></td>
                            <td width="13%" align="left" nowrap="nowrap"><span class="redstar">*</span>
                                <form:select id="sel8" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.jobSampleLocation" items="${icbfn:dropdown('staticDropdown',sampleLocation)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/></td>
                            <td width="7%" align="left"><form:input cssClass="inputBox"  size="5" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.sampleNumber" disabled="${command.viewOnly}"/>
                                <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.sampleNumber" cssClass="redstar"/></td>
                            <td  width="7%" align="left"><form:input cssClass="inputBox" cssStyle="text-align:right;" size="5" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.tankNumber" maxlength="254" disabled="${command.viewOnly}"/>
                                <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.tankNumber" cssClass="redstar"/></td>
        <c:if test="${command.jobOrder.jobType == 'INSP' || command.jobOrder.jobType=='MAR'}">                            
        <td  width="12%" align="left"><form:select id="sel9" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.sampleTiming" items="${icbfn:dropdown('staticDropdown',sampleTiming)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/></td>
                              <td  width="11%" align="left"><form:select id="sel10" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.sampleType" items="${icbfn:dropdown('staticDropdown',sampleType)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/></td>
        </c:if>
                            <td  width="9%" align="left"><form:select id="sel11" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.sampleVolume" items="${icbfn:dropdown('staticDropdown',sampleVolume)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/></td>
                            <td  width="11%"  align="left" ><form:select id="sel12" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.containerType" items="${icbfn:dropdown('staticDropdown',containerType)}" itemLabel="name" itemValue="value" disabled="${command.viewOnly}"/></td>
                            
							<td align="left"  width="12%"><form:select id="sel13" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.retainTested" items="${icbfn:dropdown('staticDropdown',retainTested)}" itemLabel="name" itemValue="value" onchange="setLabAnalysisFlag('${vesselCounter.index}${productCounter.index}${sampleCounter.index}')" disabled="${command.viewOnly}"/></td>
							<a name="retainScroll${vesselCounter.index}${productCounter.index}${sampleCounter.index}"></a>
                
				
				<td align="left"  width="9%">
				<form:select id="priority${vesselCounter.index}${productCounter.index}${sampleCounter.index}" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.priority" items="${icbfn:dropdown('staticDropdown',priority)}" itemLabel="name" itemValue="value" onchange="checkForOtApprovedFlag('${vesselCounter.index}','${productCounter.index}','${sampleCounter.index}','${arrAddJobProdSamples.jobTests}','${arrAddJobProdSamples.jobManualTests}','${arrAddJobProdSamples.jobSlates}')" disabled="${command.viewOnly}"/></td><a name="priorityScroll${vesselCounter.index}${productCounter.index}${sampleCounter.index}"></a>



                            <td  width="9%" align="left" ><form:input cssClass="inputBox" cssStyle="width:13px; text-align:right" size="2" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.sortOrder" disabled="${command.viewOnly}"/>
                                <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.sortOrder" cssClass="redstar"/></td>
								<a name="addSampleRow${vesselCounter.index}${productCounter.index}${sampleCounter.index+1}"></a>
                            <td width="6%" align="right" style="text-align:right"><div id="div22" style="width:29px; "> 
        <c:if test="${!command.viewOnly}">
        <a href="#" onClick="javascript:onCopySample(${vesselCounter.index },${productCounter.index},${sampleCounter.index },${fn:length(arrJobProd.addJobProdSamples)});"><img src="images/copy.gif" alt="Copy Sample Location" width="13" height="12" hspace="1px" border="0" /></a><a name="copySampleLoc${vesselCounter.index }${productCounter.index}${sampleCounter.index+1}"></a>
		<a name="delRowTest${vesselCounter.index}${productCounter.index}${sampleCounter.index}"></a>
        <a href="#"><img src="images/delete.gif" alt="Delete Sample"  width="12" height="12" hspace="1px" border="0" onclick="onDeleteSampleLoc(${vesselCounter.index},${productCounter.index},${sampleCounter.index })"/></a>
    </c:if>&nbsp;</div></td>
                          </tr>
                          <tr >
                            <td colspan="11" align="center" ><div align="center" id="sampleloctablev${vesselCounter.index}p${productCounter.index}s${sampleCounter.index }" style="visibility:visible; display:block;">
                                <table width="80%"  border="0" align="center" cellpadding="0" cellspacing="0" style="width:100%; border:none;">
                                  <tr>
                                    <td>
                  <c:if test="${!command.viewOnly}">
                  <input type="checkbox"  id="selectAll${vesselCounter.index}${productCounter.index}${sampleCounter.index}" onClick="selectAllTestsSlates('${vesselCounter.index}','${productCounter.index}','${sampleCounter.index}',${fn:length(command.addJobVessels[vesselCounter.index].addJobProds[productCounter.index].addJobProdSamples[sampleCounter.index].jobTests)},${fn:length(command.addJobVessels[vesselCounter.index].addJobProds[productCounter.index].addJobProdSamples[sampleCounter.index].jobManualTests)},${fn:length(command.addJobVessels[vesselCounter.index].addJobProds[productCounter.index].addJobProdSamples[sampleCounter.index].jobSlates)})" />
                  </c:if>
                  </td>
                                    <td>
                  <c:if test="${!command.viewOnly}">
                  <a href="#" onclick="javascript:onAddTest(${vesselCounter.index},${productCounter.index},${sampleCounter.index },${fn:length(arrAddJobProdSamples.jobTests)},${fn:length(arrAddJobProdSamples.jobManualTests)},${fn:length(arrAddJobProdSamples.jobSlates)});setChosenContracts(${vesselCounter.index},${productCounter.index},${fn:length(arrJobProd.applicableContracts)});popup_show('test', 'test_drag', 'test_exit', 'screen-corner', 120, 20);hideIt();popupboxenable();updateTestIFrameSrc('${vesselCounter.index}','${productCounter.index}','${sampleCounter.index }','${command.jobOrder.nominationDate }')"><f:message key="addTests"/></a><a name="testAdd${vesselCounter.index}${productCounter.index}${sampleCounter.index}"></a>
                  </c:if></td>

				<td>
				<c:if test="${!command.viewOnly}">
				<a href="#" onclick="javascript:onAddManualTest(${vesselCounter.index},${productCounter.index},${sampleCounter.index },${fn:length(arrAddJobProdSamples.jobTests)},${fn:length(arrAddJobProdSamples.jobManualTests)},${fn:length(arrAddJobProdSamples.jobSlates)});setChosenContracts(${vesselCounter.index},${productCounter.index},${fn:length(arrJobProd.applicableContracts)});popup_show('test', 'test_drag', 'test_exit', 'screen-corner', 120, 20);hideIt();popupboxenable();updateManualTestIFrameSrc('${vesselCounter.index}','${productCounter.index}','${sampleCounter.index }','${command.jobOrder.nominationDate }','${command.jobOrder.jobNumber}')"><f:message key="addManualTest"/></a>
				<a name="testAdd${vesselCounter.index}${productCounter.index}${sampleCounter.index}"></a>
				</c:if></td>



                                    <td>
                  <c:if test="${!command.viewOnly}">
                  <a href="#" onclick="javascript:onAddSlate(${vesselCounter.index},${productCounter.index},${sampleCounter.index },${fn:length(arrAddJobProdSamples.jobTests)},${fn:length(arrAddJobProdSamples.jobManualTests)},${fn:length(arrAddJobProdSamples.jobSlates)});setChosenContracts(${vesselCounter.index},${productCounter.index},${fn:length(arrJobProd.applicableContracts)});popup_show('slate', 'slate_drag', 'slate_exit', 'screen-corner', 120, 20);hideIt();popupboxenable();updateSlateIFrameSrc('${vesselCounter.index}','${productCounter.index}','${sampleCounter.index }','${command.jobOrder.nominationDate }')"><f:message key="addSlates"/></a><a name="slateAdd${vesselCounter.index}${productCounter.index}${sampleCounter.index}"></a>
                  </c:if></td>
                  <td><a href="#"><img src="images/icodel.gif" alt="Delete Tests/Slates"  width="12" height="12" hspace="1px" border="0" onclick="onMassDeleteTestSlate(${vesselCounter.index},${productCounter.index},${sampleCounter.index },${fn:length(command.addJobVessels[vesselCounter.index].addJobProds[productCounter.index].addJobProdSamples[sampleCounter.index].jobTests)},${fn:length(command.addJobVessels[vesselCounter.index].addJobProds[productCounter.index].addJobProdSamples[sampleCounter.index].jobManualTests)},${fn:length(command.addJobVessels[vesselCounter.index].addJobProds[productCounter.index].addJobProdSamples[sampleCounter.index].jobSlates)})"/></a><a name="delTotTest${vesselCounter.index}${productCounter.index}${sampleCounter.index}"></a>
                                        
                                        </td>
                                    <td>                                        
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<f:message key="massQty"/> : 
                                        <form:input cssClass="inputBox" size="4" id="massqty${vesselCounter.index}${productCounter.index}${sampleCounter.index}" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].massQty" onchange="updateMassQty(${vesselCounter.index},${productCounter.index},${sampleCounter.index},${fn:length(arrAddJobProdSamples.jobTests)},${fn:length(arrAddJobProdSamples.jobSlates)})" disabled="${command.viewOnly}"/>
                                        <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].massQty"/>
                                        </td>
                                    <td width="11%" nowrap><f:message key="massOT"/> :
                                      									
										<form:select cssClass="selectionBox" id="massOT${vesselCounter.index}${productCounter.index}${sampleCounter.index}" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].massOT" items="${icbfn:dropdown('staticDropdown',yesNo)}" itemLabel="name" itemValue="value" onchange="javascript:updateMassOT(${vesselCounter.index},${productCounter.index},${sampleCounter.index},${fn:length(arrAddJobProdSamples.jobTests)},${fn:length(arrAddJobProdSamples.jobSlates)});setOtApprovedFlag('${vesselCounter.index}${productCounter.index}${sampleCounter.index}','massOT')" disabled="${command.viewOnly}"/>
                                        <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].massOT"/>
                                        </td>										
										<a name="massOtScroll${vesselCounter.index}${productCounter.index}${sampleCounter.index}"></a>
                                    <td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                  </tr>
                                  <c:forEach items="${arrAddJobProdSamples.jobTests}" var="arrJobTests" varStatus="jobTestCounter">
                                    <tr>
                                      <td width="6%"><input type="checkbox" id="test${vesselCounter.index }${productCounter.index }${sampleCounter.index }${jobTestCounter.index }" name="chk${vesselCounter.index }${productCounter.index }${sampleCounter.index }${jobTestCounter.index}" value="${command.addJobVessels[vesselCounter.index].addJobProds[productCounter.index].addJobProdSamples[sampleCounter.index].jobTests[jobTestCounter.index].test.testId}"></td>
                                          <td width="13%"><f:message key="test"/> ${jobTestCounter.index +1 }:</td>
                                      <td width="11%"><form:input cssClass="inputBox" size="20" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobTests[${jobTestCounter.index }].test.testId" disabled="true"/>
                                          <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobTests[${jobTestCounter.index }].test.testId" cssClass="redstar"/>
                                      </td>
                                      <td width="20%"><form:input cssClass="inputBox" size="50" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobTests[${jobTestCounter.index }].test.testDescription" disabled="true"/>
                                          <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobTests[${jobTestCounter.index }].test.testDescription" cssClass="redstar"/>
                                      </td>
                                      <td width="5%" nowrap="nowrap" align="center">&nbsp;&nbsp;&nbsp;&nbsp;</td>
				      <td width="22%" nowrap="nowrap" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Qty.&nbsp;&nbsp;
                                          <form:input cssClass="inputBox" size="4" id="testqty${vesselCounter.index }${productCounter.index }${sampleCounter.index }${jobTestCounter.index }" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobTests[${jobTestCounter.index }].quantity" maxlength="8" disabled="${command.viewOnly}"/>
                                          <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobTests[${jobTestCounter.index }].quantity" cssClass="redstar"/>
                                      </td>
                                      <td width="16%" nowrap="nowrap" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;OT
                                        <form:select id="testot${vesselCounter.index }${productCounter.index }${sampleCounter.index }${jobTestCounter.index }" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobTests[${jobTestCounter.index }].ot" items="${icbfn:dropdown('staticDropdown',yesNo)}" itemLabel="name" itemValue="value" onchange="setOtApprovedFlag('${vesselCounter.index}${productCounter.index}${sampleCounter.index}${jobTestCounter.index }','')" disabled="${command.viewOnly}"/>
                                        &nbsp;&nbsp; </td><a name="testOtScroll${vesselCounter.index }${productCounter.index }${sampleCounter.index }${jobTestCounter.index}"></a>

                                      <td width="14%" ><div align="right"> 
                    <c:if test="${!command.viewOnly}">
                    <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1" border="0" onclick="onDeleteTest(${vesselCounter.index},${productCounter.index},${sampleCounter.index },${jobTestCounter.index })"/></a>
                    </c:if>&nbsp;</div></td>
                  <td nowrap colspan="3">&nbsp;&nbsp;</td>                                    </tr>
                                  </c:forEach>

<!--MANUAL TEST START 101771	-->

					<c:forEach items="${arrAddJobProdSamples.jobManualTests}" var="arrJobTests" varStatus="jobManualTestCounter">
                                        <tr>
                                          <td width="6%"><input type="checkbox" id="manualTest${vesselCounter.index }${productCounter.index }${sampleCounter.index }${jobManualTestCounter.index }" name="chk${vesselCounter.index }${productCounter.index }${sampleCounter.index }${jobManualTestCounter.index}" value="${command.addJobVessels[vesselCounter.index].addJobProds[productCounter.index].addJobProdSamples[sampleCounter.index].jobManualTests[jobManualTestCounter.index].testId}"></td>
                                          <td width="13%"><f:message key="manualTest"/> ${jobManualTestCounter.index +1 }:</td>
                                          <td width="11%"><form:input cssClass="inputBox" size="20" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobManualTests[${jobManualTestCounter.index }].testId" disabled="true"/>
                                              <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobManualTests[${jobManualTestCounter.index }].testId" cssClass="redstar"/>                                          </td>
                                          <td width="20%"><form:input cssClass="inputBox" size="50" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobManualTests[${jobManualTestCounter.index }].testDescription" disabled="true"/>
                                              <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobManualTests[${jobManualTestCounter.index }].testDescription" cssClass="redstar"/>                                          </td>
					  <td width="5%" nowrap="nowrap" align="center">&nbsp;&nbsp;&nbsp;&nbsp;</td>
					  <td width="22%" nowrap="nowrap" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Qty.&nbsp;&nbsp;
                                              <form:input cssClass="inputBox" size="4" id="testqty${vesselCounter.index }${productCounter.index }${sampleCounter.index }${jobManualTestCounter.index }" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobManualTests[${jobManualTestCounter.index }].quantity" maxlength="8"/>
                                              <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobManualTests[${jobManualTestCounter.index }].quantity" cssClass="redstar"/>                                          </td>
                                          <td width="16%" nowrap="nowrap" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;OT
                                            <form:select cssClass="selectionBox" id="testot${vesselCounter.index }${productCounter.index }${sampleCounter.index }${jobManualTestCounter.index }" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobManualTests[${jobManualTestCounter.index }].ot" items="${icbfn:dropdown('staticDropdown',yesNo)}" itemLabel="name" itemValue="value" onchange="setOtApprovedFlag('${vesselCounter.index}${productCounter.index}${sampleCounter.index}${jobManualTestCounter.index }','')"/>
                                            &nbsp;&nbsp; </td><a name="testOtScroll${vesselCounter.index }${productCounter.index }${sampleCounter.index }${jobManualTestCounter.index}"></a>
                                          <td width="14%" ><div align="right"> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1" border="0" onclick="onDeleteManualTest(${vesselCounter.index},${productCounter.index},${sampleCounter.index },${jobManualTestCounter.index })"/></a>&nbsp;</div></td>
										  <td nowrap>&nbsp;&nbsp;</td>
                                        </tr>
                                      </c:forEach>



<!--MANUAL TEST END 101771	-->










                                  <!-- 
                                              <tr>
                                                <td width="19%">Test1:</td>
                                                <td width="25%"><input name="RO_HEADER_LOCATION2142"  class="inputBox" 
                                id="RO_HEADER_LOCATION2142" value="ASTMD1839"  size="20"/>                                                </td>
                                                <td width="35%"><input name="RO_HEADER_LOCATION232"  class="inputBox" 
                                id="RO_HEADER_LOCATION232" value="Amyl Nitrate in Diesel Fuels"  size="50"/></td>
                                                <td width="19%">OT
                                                  <select id="sel3" name="sel3" class="selectionBox">
                                                    <option>Yes</option>
                                                    <option>No</option>
                                                  </select>&nbsp;&nbsp;                                                </td>
                                                <td width="2%" > <div align="right">
                                                  <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1" border="0"/></a></div></td>
                                              </tr>
                                              <tr>
                                                <td>Test2:</td>
                                                <td><input name="RO_HEADER_LOCATION2152"  class="inputBox" 
                                id="RO_HEADER_LOCATION2152" value="ASTMD5186"  size="20"/>                                                </td>
                                                <td><input name="RO_HEADER_LOCATION23"  class="inputBox" 
                                id="RO_HEADER_LOCATION23" value="Aromatics in Diesel by SFC"  size="50"/>                                                </td>
                                                <td>OT
                                                  <select id="sel3" name="sel3" class="selectionBox">
                                                    <option>Yes</option>
                                                    <option>No</option>
                                                  </select>&nbsp;&nbsp;                                                </td>
                                                <td ><div align="right">
                                                  <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1" border="0"/></a></div></td>
                                              </tr>
                                              <tr>
                                                <td>State1:</td>
                                                <td><input name="RO_HEADER_LOCATION2162"  class="inputBox" 
                                id="RO_HEADER_LOCATION2162" value="BPPRODF1"  size="20"/>                                                </td>
                                                <td><input name="RO_HEADER_LOCATION23"  class="inputBox" 
                                id="RO_HEADER_LOCATION23" value="F1 - Distillates - CARB Diesel"  size="50"/>                                                </td>
                                                <td>OT
                                                  <select id="sel3" name="sel3" class="selectionBox">
                                                    <option>Yes</option>
                                                    <option>No</option>
                                                  </select>&nbsp;&nbsp;                         </td>
                                                <td><div align="right">
                                                  <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1" border="0"/></a></div></td>
                                              </tr> -->
                                  <c:forEach items="${arrAddJobProdSamples.jobSlates}" var="arrJobSlates" varStatus="jobSlateCounter">
                                    <tr>
                                      <td width="4%"><input type="checkbox" id="slate${vesselCounter.index }${productCounter.index }${sampleCounter.index }${jobSlateCounter.index }" name="chk${vesselCounter.index }${productCounter.index }${sampleCounter.index }${jobTestCounter.index}" value="${command.addJobVessels[vesselCounter.index].addJobProds[productCounter.index].addJobProdSamples[sampleCounter.index].jobSlates[jobSlateCounter.index].slate.slateId}"></td>
                                      <td width="13%"><f:message key="slate"/> ${jobSlateCounter.index +1 }:</td>
                                      <td width="11%"><form:input cssClass="inputBox" size="20" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobSlates[${jobSlateCounter.index }].slate.slateId" disabled="true"/>
                                          <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobSlates[${jobSlateCounter.index }].slate.slateId" cssClass="redstar"/>
                                      </td>
                                      <td width="20%"><form:input cssClass="inputBox" size="50" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobSlates[${jobSlateCounter.index }].slate.slateDescription" disabled="true"/>
                                          <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobSlates[${jobSlateCounter.index }].slate.slateDescription" cssClass="redstar"/>
                                      </td>
				      <td width="5%" nowrap="nowrap" align="center">&nbsp;&nbsp;&nbsp;&nbsp;</td>
				      <td width="22%" nowrap="nowrap" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<f:message key="qty"/>.&nbsp;&nbsp;
                                          <form:input cssClass="inputBox" size="4" id="slateqty${vesselCounter.index }${productCounter.index }${sampleCounter.index }${jobSlateCounter.index }"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobSlates[${jobSlateCounter.index }].quantity" maxlength="8" disabled="${command.viewOnly}"/>
                                          <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobSlates[${jobSlateCounter.index }].quantity" cssClass="redstar"/>
                                      </td>
                                      <td width="16%" nowrap="nowrap" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<f:message key="ot"/>
                                        <form:select id="slateot${vesselCounter.index }${productCounter.index }${sampleCounter.index }${jobSlateCounter.index }" cssClass="selectionBox" path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobSlates[${jobSlateCounter.index }].ot" items="${icbfn:dropdown('staticDropdown',yesNo)}" itemLabel="name" itemValue="value" onchange="setOtApprovedFlag('${vesselCounter.index}${productCounter.index}${sampleCounter.index}${jobSlateCounter.index}','')" disabled="${command.viewOnly}"/>
                                        &nbsp;&nbsp; </td>
										<a name="testOtScroll${vesselCounter.index }${productCounter.index }${sampleCounter.index }${jobSlateCounter.index}"></a>
                                      <td width="14%" ><div align="right"> 
                    <c:if test="${!command.viewOnly}">
                    <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1" border="0" onclick="onDeleteSlate(${vesselCounter.index},${productCounter.index},${sampleCounter.index },${jobSlateCounter.index })"/></a><a name="delRowSlate${vesselCounter.index}${productCounter.index}${sampleCounter.index}${jobSlateCounter.index }"></a>
                    </c:if>&nbsp;</div></td>
          <td nowrap>&nbsp;&nbsp;</td>                                    
          </tr>
                                  </c:forEach>
                                  <tr>
                                    <td colspan="6" align="left"><table  border="0" cellpadding="0" cellspacing="0" style="width:100%">
                                        <tr>
                                          <td width="6%" valign="top" nowrap="nowrap" style="border-bottom:none">&nbsp;</td>
                                          <td width="10%" valign="top" nowrap="nowrap" style="border-bottom:none"><f:message key="sampleInstructions"/>: </td>
                                          <td colspan="5" width="85%" style="border-bottom:none; border-right:none;"><label>
                                            <form:textarea cols="90" rows="4"  path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.sampleInstructions" disabled="${command.viewOnly}"/>
                                            <form:errors path="addJobVessels[${vesselCounter.index}].addJobProds[${productCounter.index}].addJobProdSamples[${sampleCounter.index}].jobProdSample.sampleInstructions" cssClass="redstar"/>
                                          </label></td>
                      <td>&nbsp;</td>
                                        </tr>
                                    </table></td>
                                  </tr>
                                </table>
                            </div></td>
                          </tr>
                        </c:forEach>
                      </table>
                    </div>
                                  <!-- ----------------SAMPLE LOCATIONS TABLE V1 END -------------- -->                               </td>
                              </tr>
                            </table>                </td>
                </tr>
<!-- --------------------------- Copy Product Pop ------------------------------------------------- -->


<div class="sample_popup" id="copyprod${vesselCounter.index}${productCounter.index}" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="copyprod_drag${vesselCounter.index}${productCounter.index}" style="width:475px; "> 
  <img class="menu_form_exit"   id="copyprod_exit${vesselCounter.index}${productCounter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="copyProduct"/></div>
  <div class="menu_form_body" id="copyprodbody${vesselCounter.index}${productCounter.index}"   style="width:475px; height:140px;z-index:10">
      <iframe align="left" frameborder="0" style="padding:0px; height:130px;" width="100%" src="copy_product_popup.htm?searchForm=jobOrderEditOpInstrForm&vesselIndex=${vesselCounter.index }&productIndex=${productCounter.index }&vesselCount=${fn:length(command.addJobVessels)}" scrolling="no" id="frame1" name="frmcopyprod01" allowtransparency="yes" >      </iframe>
</div>
</div>


<!-- --------------------------------- Copy Product Pop END ----------------------------------------- -->
<!------------------------------------ Port Lookup START -------------------------------------------->
<div class="sample_popup" id="port"  style="visibility: hidden;display: none;">
   <div class="menu_form_header" id="port_drag" style="width:680px;">
     <img class="menu_form_exit" id="port_exit" src=" images/form_exit.png"/>&nbsp;&nbsp;&nbsp;
       <f:message key="selectDestination"/></div>
           <div class="menu_form_body" id="portbody" style="width:680px; height:550px;overflow-y:hidden;">
           <iframe id="portfr" align="left" src="blank.html" frameborder="0" style="padding:0px; height:550px; width:680px" width="100%"
           scrolling="auto" id="frame3" name="frame3" allowtransparency="yes">
       </iframe>
    </div>
</div>
<!-- --------------------------------- Port Lookup END ----------------------------------------- -->

<!-------------------------------------------------- Sample Location Lookup START ----------------------------------------------------->
<div class="sample_popup" id="sampleLoc${vesselCounter.index}${productCounter.index}"  style="visibility: hidden;display: none;">
   <div class="menu_form_header" id="sampleLoc_drag${vesselCounter.index}${productCounter.index}" style="width:600px;">
     <a href="#"  onclick="resetJob()"><img class="menu_form_exit" id="sampleLoc_exit${vesselCounter.index}${productCounter.index}" src="images/form_exit.png"/></a>&nbsp;&nbsp;&nbsp;
       <f:message key="selectSampleLocation"/></div>
           <div class="menu_form_body" id="sampleLocBody${vesselCounter.index}${productCounter.index}" style="width:600px; height:300px;overflow-y:hidden;overflow-x:hidden">
           <iframe id="sampleLocationFr${vesselCounter.index }${productCounter.index}" align="left" src="blank.html" frameborder="0" style="padding:0px; height:300px; width:600px" width="100%" scrolling="auto" id="frame3" name="frame3" allowtransparency="yes">
       </iframe>
    </div>
</div>
<!------------------------------------------------------- Sample Location Lookup END -------------------------------------------------->


      </c:forEach>
                </div>
              </table>
                          </div>
                          <!-- -------------------VESSEL 1 PRODUCT 1 END --------------------- -->
                        </div><!-- ----VESSEL 1 Data END ---------- -->
                      </div><!-- ---------- VESSEL 1 Container END ---------- -->                    </td>
                  </tr>
<!-- --------------------------- Search Vessel Popup ------------------------------------------------- -->
<div class="sample_popup" id="vesselsearch${vesselCounter.index}" style="visibility: hidden; display: none; z-index:2;">
<div class="menu_form_header" id="vesselsearch_drag${vesselCounter.index}" style="width:450px;height:auto;z-index:2; "> 
<img class="menu_form_exit"   id="vesselsearch_exit${vesselCounter.index}" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchVessel"/></div>                                                            
<div class="menu_form_body" id="vesselsearchbody${vesselCounter.index}" style="width:450px; height:300px;">
<iframe align="left" frameborder="0" style="padding:0px;" height="300px;" width="100%" src="search_vessel_popup.htm?inputFieldId=addJobVessels[${vesselCounter.index}].jobVessel.vesselName&rowNum=${vesselCounter.index}&searchForm=jobOrderEditOpInstrForm" scrolling="auto" id="searchcontractFr" name="searchcontractFr" allowtransparency="yes" ></iframe>
</div>
</div>
<!-- --------------------------- Search Vessel Popup ------------------------------------------------- -->
              <SCRIPT LANGUAGE="JavaScript">
location.href="#${command.scrollFlag}"	
</SCRIPT>
				</c:forEach>  
               </table>

                 
                </tbody>
              </table>
         <table>
        <tr><td></td></tr>
        </table>
              <!-- ------------------------ INSTRUCTIONS -------------------------------------------------- -->
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
                <tr>
                  <td width="16" > <div id="bluarrowrightins" style="visibility:hidden; position:absolute; z-index: 2; margin-left:4px"> <a href="#instructions" onClick="javascript:showInstructions();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4" style="margin-top:2px;"/></a> </div>
                    <div id="bluarrowdownins" style="visibility:visible;position:relative; z-index: 1; margin-top:6px "> <a href="#instructions" onClick="javascript:hideInstructions();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></td>
                  <td width="871" class="TDShade"><a name="instructions"></a><f:message key="instructions"/></td>
          <td  width="64" class="TDShade">&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="3" style="padding:0px;"><div id="instructionsContainer" style="visibility:visible;display:block">
                      <table class="orderTable" width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <th width="50%"><f:message key="inspectionInstructions"/>:</th>
                          <th width="50%"><f:message key="sampleInstructions"/>:</th>
                        </tr>
                        <tr>
                          <td align="center"><label>
                         <form:textarea cols="60" rows="4" path="jobOrder.inspInstructions" disabled="${command.viewOnly}"/>
             <form:errors path="jobOrder.inspInstructions" cssClass="redstar"/>                                                                        
                          </label></td>
                          
                          <td align="center">
                          <form:textarea cols="60" rows="4" path="jobOrder.sampInstructions" disabled="${command.viewOnly}"/>
              <form:errors path="jobOrder.sampInstructions" cssClass="redstar"/>                          </td>
                        </tr>
                        <tr>
                          <td colspan="2">
                          <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="orderTable" style="margin:0px;">
                              <tr>
                                <th colspan="8"><f:message key="labEvents"/></th>
                              </tr>
                              <tr>
                                <td><f:message key="retainPeriod"/>:</td>
                                <td><label>
                                <form:input cssClass="inputBox" size="4" path="jobOrder.retainPeriod" maxlength="4" disabled="${command.viewOnly}"/>
                <form:errors path="jobOrder.retainPeriod" cssClass="redstar"/>                                               
                                  <f:message key="days"/></label></td>
                                  <td><f:message key="limsBranchId"/>:</td>
                <td><label>
                                  <form:input cssClass="inputBox" size="8" maxlength="8" path="jobOrder.limsBranchId"/>
                                  <form:errors path="jobOrder.limsBranchId" cssClass="redstar"/>
                                  &nbsp;<a href="#" onClick="javascript:updateBranchIframeSrc('${command.jobOrder.buName }');popup_show('jobbranchcode', 'jobbranchcode_drag', 'jobbranchcode_exit', 'screen-corner', 120, 20);hideIt();showbranchcode('jobbranchcode','jobbranchcodebody');popupboxenable()"><img
                      src=" images/lookup.gif" alt="Lookup Branch" width="13" height="13" border="0" />
                     </a>
                                  </label> 
                                    </td>                               
                                <td><label>
                                <c:choose> 
                          <c:when test="${command.jobOrder.labAnalysis}">  
                          <form:checkbox id="labAnalysis" path="jobOrder.labAnalysis"  disabled="true" />
                          </c:when>
                          <c:otherwise> 
                          <form:checkbox id="labAnalysis" path="jobOrder.labAnalysis"  disabled="true" />
                          </c:otherwise>
                          </c:choose>
            
                                
                <form:errors path="jobOrder.labAnalysis" cssClass="redstar"/>
                                  <f:message key="labAnalysis"/></label></td>
                                <td>
                                
                                <c:choose> 
                          <c:when test="${command.jobOrder.otApproved}">  
                          <form:checkbox id="otApproved" path="jobOrder.otApproved"  disabled="true"/>
                          </c:when>
                          <c:otherwise> 
                          <form:checkbox id="otApproved" path="jobOrder.otApproved"  disabled="true"/>
                          </c:otherwise>
                          </c:choose>
                          
                                
                <form:errors path="jobOrder.otApproved" cssClass="redstar"/>
                                 <f:message key="otApproved"/></td>
                                <td ><f:message key="otApprovedBy"/>:</td>
                                <td><label>
                                  <form:select id="sel1" cssClass="selectionBox" path="jobOrder.otApprovedby" items="${icbfn:dropdown('scheduler', jobDetail)}" itemLabel="name" itemValue="value" multiple="true" disabled="${command.viewOnly}"/>
                                <form:errors path="jobOrder.otApprovedby" cssClass="error"/>

                                  </label></td>
                              </tr>
                            </table></td>
                        </tr>
                        <tr>
                          <td>&nbsp;</td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                           <th> <f:message key="labInstructions"/>:</th>
                          <th> <f:message key="shipInstructions"/>:</th>
                        </tr>
                        <tr>
                          <td align="center">
                          <form:textarea cols="60" rows="4" path="jobOrder.labInstructions" disabled="${command.viewOnly}"/>
              <form:errors path="jobOrder.labInstructions" cssClass="redstar"/>                          </td>
                          <td align="center">
                          <form:textarea cols="60" rows="4" path="jobOrder.shipInstructions" disabled="${command.viewOnly}"/>
              <form:errors path="jobOrder.shipInstructions" cssClass="redstar"/>                          </td>
                        </tr>
                        <tr>
                          <th><f:message key="reportInstructions"/>:</th>
                          <th><f:message key="billInstructions"/>:</th>
                        </tr>
                        <tr>
                          <td align="center">
                          <form:textarea cols="60" rows="4" path="jobOrder.reptInstructions" disabled="${command.viewOnly}"/>
              <form:errors path="jobOrder.reptInstructions" cssClass="redstar"/>                          </td>
                          <td align="center">
                          <form:textarea cols="60" rows="4" path="jobOrder.billInstructions" disabled="${command.viewOnly}"/>
              <form:errors path="jobOrder.billInstructions" cssClass="redstar"/>                          </td>
                        </tr>
                        <tr>
                         <th><f:message key="otherInstructions"/>:</th>
                          <th>&nbsp;</th>
                        </tr>
                        <tr>
                          <td>
                          <form:textarea cols="60" rows="4" path="jobOrder.otherInstructions" disabled="${command.viewOnly}"/>
              <form:errors path="jobOrder.otherInstructions" cssClass="redstar"/>                          </td>
                          <td>&nbsp;</td>
                        </tr>
<%-- START: Issue # 75052  --%>                        
                        <tr>
                         <th>Revision Notes:</th>
                          <th>&nbsp;</th>
                        </tr>
			<tr>
				<td colspan="2">
					<table border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin:0px;">
	            			<tr>
							<th width="5%">Revision#</th>
              				<th width="35%">Date/Time</th>
              				<th width="20%">Added By</th>
              				<th width="40%">Notes</th>
	            			</tr>
	            			<tr>
	            				<td colspan="3">&nbsp;</td>				
								<td><form:textarea cols="80" rows="2" path="uiRevisionNote" /></td>
							</tr>
		<c:if test="${command.arrRevisionNotes != null}">															                   	
			<c:forEach items="${command.arrRevisionNotes}" var="arrRevisionNote" varStatus="revNotesCounter">
	            			<tr>
							<td width="5%">${arrRevisionNote.revisionNoteId.revisionNumber}</td>
              				<td width="35%">${arrRevisionNote.revisionDateTime}</td>
              				<td width="20%">${arrRevisionNote.addedByUserName}</td>
              				<td width="40%"><textarea name="textarea8" cols="80" disabled='disabled' rows="2">${arrRevisionNote.revisionNote}</textarea></td>
	            			</tr>                   	
			</c:forEach>
		</c:if> 
			</table>
		</td>
	</tr>			

<%-- END: Issue # 75052  --%>
                      </table>
                    </div></td>
                </tr>
              </table>
           <!-- --------------------------------------- INSTRUCTIONS END ---------------------------------------->

	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
		<tr>
			<td>
				<table>
					<tr>
						<td class="TDShade"><strong><f:message key="projectNumber" />:</strong></td>
						<td class="TDShadeBlue"><form:input path="phxProjectNumber" cssClass="inputBox" disabled="true" /> <form:errors
							path="projectId" cssClass="redstar" /></td>
						<td class="TDShade">&nbsp;</td>
						<c:if test="${command.allowCreateUpdateProject}">
							<td class="TDShadeBlue">
								<input name="Submit2" type="button" class="button1" value="Create Project"
								onClick="createOCAProject(this.form);">
							</td>
						</c:if>
					</tr>
				</table>
			</td>
		</tr>
	</table>

              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
        
						<td align="right"></td>
                        <td style="text-align:right">
		 <!-- START : #119240 -->
		  <c:choose>
            <c:when test="${command.originateFromSearchJob==null}">
				&nbsp;
            </c:when>
            <c:otherwise>
              <a href="${command.originateFromSearchJob}?jobNum=${command.jobNumber}">
			   <img src="images/icofindjob.gif" alt="Return to Search" width="16" height="14" border="0" align="absmiddle">			  
			  </a>
			</c:otherwise>
           </c:choose>        
		  <c:choose>
			<c:when test="${command.prevListFlag=='true'}">
              <a href="#" onClick="javascript:prevList();">
			  <img src="images/prevleftarrow.gif" alt="Go to Previous Job" width="13" height="12" hspace="2" border="0"/>
			  </a>
             </c:when>
            <c:otherwise>
		      <c:if test="${command.originateFromSearchJob!=null}">
			  <a href="#" onClick="javascript:noPrevList();">
			  <img src="images/prevleftarrow.gif" alt="Go to Previous Job" width="13" height="12" hspace="2" border="0"/>
			  </a>
			  </c:if>
	        </c:otherwise>
        </c:choose>   
	    <c:choose>
          <c:when test="${command.nextListFlag=='true'}">
              <a href="#" onClick="javascript:nextList();">
			  <img src="images/nextrtarrow.gif" alt="Go to Next Job" width="13" height="12" hspace="2" border="0"/></a>
          </c:when>
          <c:otherwise>
			  <c:if test="${command.originateFromSearchJob!=null}">
				 <a href="#" onClick="javascript:noNextList();">
				  <img src="images/nextrtarrow.gif" alt="Go to Next Job" width="13" height="12" hspace="2" border="0"/></a>
			  </c:if>	
          </c:otherwise>
        </c:choose>
		 <!-- END : #119240 -->
						
						<a href="job_order_pdf_view.htm?jobNumber=${command.jobOrder.jobNumber}" target="_blank"><img src="images/ico_print.gif" alt="Print Job Order" width="18" height="16" hspace="2" border="0" align="absmiddle" title="Print Job Order"></a>
                        <c:if test="${command.samBranchFlag}">
                        <a href="#" onclick="javascript:onSendWebService('SAM');" ><img src="images/icoship.gif" alt="Send To SAM" hspace="2" border="0" align="absmiddle" title="Send To SAM"></a>
                        </c:if>
                        <c:if test="${command.limsBranchFlag}">
                        <a href="#" onclick="javascript:onSendWebService('LIMS');"><img src="images/icoflask.gif" alt="Send To LIMS" hspace="2" border="0" align="absmiddle" title="Send To LIMS"></a>
                        </c:if>
                        <a href="#" onclick="goToNextPage()"><img src="images/savennextbluarrow.gif" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page"></a><a href="#"><img src="images/icosave.gif" alt="Save" title="Save" width="14" height="14" border="0" align="absmiddle" onclick="javascript:submitFunction();"/></a></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
        <div>
         <!-- ------------------------ goto --------------------------------------------------- -->
          <table width="100%" cellspacing="0">
          <tr>
            <td height="24" style="text-align:right; padding-right:0px;"><select name="sel5" id="sel5" class="SelectionBox" onChange="MM_jumpMenu('parent',this,1)">
                <option selected>Go to ... &gt;</option>
                <option value="edit_job_entry_${urlSuffix}.htm?jobNumber=${command.jobOrder.jobNumber}"><f:message key="entry"/></option>
              </select>
            </td>
          </tr>
        </table>
      
      <!-- ------------------------ goto END --------------------------------------------------- -->
        </div>
            </div>
            <!----------------- TAB 1 CONTAINER END ------------------------------ -->
          </div>
        </div>
     </div>
        <script type="text/javascript">
      
      //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
      dolphintabs.init("tabnav", 0)
      
      </script>
        <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
    <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
        <!-- --------------------------- Integration Log Popup ------------------------------------------------- -->
<div class="sample_popup" id="integrationlog" style="visibility: hidden; display: none; ">
  <div class="menu_form_header" id="integrationlog_drag" style="width:625px; "> 
  <img class="menu_form_exit"   id="integrationlog_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="integrationLog"/></div>
    <div class="menu_form_body" style="width:625px; height:auto;">
    <table width="98%" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:15px;">
      <tbody>
        <tr>
          <td>
            <iframe id="addeditserivcebox" width="100%" height="290px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" 
              src="view_job_integration_log.htm?jobNumber=${command.jobOrder.jobNumber}">
            </iframe>
          </td>
        </tr>
      </tbody>
    </table>

  </div>
</div>

<div class="sample_popup" id="requiredFields" style="visibility: hidden; display: none; ">
  <div class="menu_form_header" id="requiredFields_drag" style="width:625px; "> 
  <img class="menu_form_exit"   id="requiredFields_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="requiredFields"/></div>
    <div class="menu_form_body" style="width:625px; height:auto;">
    <table width="98%" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:15px;">
      <tbody>
        <tr>
          <td>
            <iframe id="requiredFieldsFrame" width="100%" height="600px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" 
              src="about:blank">
            </iframe>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</div>

<!-- --------------------------------- Integration Log Popup END ----------------------------------------- -->
<!-- --------------------------- Search Test Popup ------------------------------------------------- -->
<div class="sample_popup" id="test" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="test_drag" style="width:850px;"> 
<img class="menu_form_exit"   id="test_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="tests"/></div>                                                           
<div class="menu_form_body" id="testbody" style="width:850px; height:auto;">
<iframe id="searchtestpopup"  width="99%" height="1px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>

</div>
</div>
<!-- --------------------------- Search Test Popup End------------------------------------------------- -->       

<!-- --------------------------- Add Slates Lookup ------------------------------------------------- -->
<div class="sample_popup" id="slate" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="slate_drag" style="width:850px; "> 
  <img class="menu_form_exit"   id="slate_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="slates"/> </div>
  <div class="menu_form_body" id="slatebody"   style="width:850px; height:auto;">
  <iframe id="searchslatepopup"  width="99%" height="1px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>

</div>
</div>
<!-- --------------------------------- Add Slates Lookup END ----------------------------------------- -->

<!-- --------------------------- Add Tests Lookup ------------------------------------------------- -->
<div class="sample_popup" id="tests" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="tests_drag" style="width:750px; "> <img class="menu_form_exit"   id="tests_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="tests"/> </div>
  <div class="menu_form_body" id="testsbody"   style="width:750px; height:auto;">
    <form method="post" action="../setup/popup.php">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:10px;">
        <tr>
          <td width="25%"><strong><f:message key="selectGroup"/> : </strong></td>
          <td width="100"><select name="sel1" id="select" class="selectionBox">
                  <option selected>PRD GRP - Residual Fuels</option>
                </select>&nbsp;</td>
          <td>
            <INPUT TYPE="radio" NAME="r1">
          <f:message key="statusPBK"/>&nbsp;</td>
          <td><INPUT TYPE="radio" NAME="r1">
<f:message key="contract"/>&nbsp;&nbsp;</td>
          <td><INPUT TYPE="radio" NAME="r1">
<f:message key="both"/></td>
        </tr>
        <tr>
          <td><strong><f:message key="searchFor"/>: </strong></td>
          <td><input name="textfield2" type="text" class="inputBox" size="25" value="ASTMD44" />&nbsp;&nbsp;in&nbsp;&nbsp;</td>
          <td><INPUT TYPE="radio" NAME="r2">
          <f:message key="methodology"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td><INPUT TYPE="radio" NAME="r2">
<f:message key="description"/></td>
          <td><INPUT TYPE="radio" NAME="r2">
<f:message key="both"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="5"><input name="Button" type="button" class="button1" value="Search" onClick="searchtests();"/>
            &nbsp;&nbsp;
            <input name="Button" type="button" class="button1" value="Cancel" onClick="hidetests();popupboxclose();" /></td>
        </tr>
      </table>
       
    <div id="testssearchresults" style="visibility:hidden; display:none;"><!--Search Results -->
    <br>&nbsp;&nbsp;<strong><f:message key="searchResults"/></strong>
    <table width="98%" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:98%; margin-bottom:15px;">
        <tr>
          <th><f:message key="methodology"/></th>
          <th><f:message key="description"/></th>
       <th><f:message key="quantity"/></th>
      <th><f:message key="pB/CONT"/></th>
          </tr>
        <tr>
          <td colspan="4"><a href="#"><f:message key="checkAll"/></a>&nbsp;&nbsp;<a href="#"><f:message key="clearAll"/></a></td>
          </tr>
    <tr>
          <td><INPUT TYPE="checkbox" NAME="chk1">ASTMD445</td>
          <td>Viscosity - Kinematic at 40°C(104°F), 50°C(122°F), 100°C(212°F), 98.9°C(210°F), 37.8°C(100°F)</td>
      <td><input name="Input243" class="inputBox" style="width:50px;" value="1"  maxlength="2"/></td>
      <td>PB07</td>
          </tr>
        <tr>
          <td><INPUT TYPE="checkbox" NAME="chk1">ASTMD445_other</td>
          <td>Viscosity - Kinematic at non-routine Temperatures</td>
      <td><input name="Input243" class="inputBox" style="width:50px;" value="1"  maxlength="2"/></td>
      <td>PB07</td>
          </tr>
        <tr>
          <td><INPUT TYPE="checkbox" NAME="chk1">ASTMD445_sayb</td>
          <td>Vescosity - Saybolt</td>
      <td><input name="Input243" class="inputBox" style="width:50px;" value="1"  maxlength="2"/></td>
      <td>PB07</td>
          </tr>
        <tr>
          <td colspan="4" height="26"><f:message key="selectanaction"/> :</td>
          </tr>
    <tr>
          <td colspan="4"><input name="Button" type="button" class="button1" value="Add Test" />&nbsp;&nbsp;<input name="Button" type="button" class="button1" value="Add and Go To Summary" />&nbsp;&nbsp;<a href="#"><f:message key="addManualTest"/></a></td>
          </tr>
      </table>
    </div><!--Search Results -->
      
    </form>
  </div>
</div>
<!-- --------------------------------- Add Tests Lookup END ----------------------------------------- -->


<div id="faderPane" style="visibility:hidden; display:none; z-index:1;Position : Absolute ;Left : 1px ;Top : 1px ;">
<IMG src="images/fake-alpha-999.gif"> </div>

      
    
   </td>
  </tr>
</table> 
      </form:form> 

<!------------------------------------------------Template Search Lookup ---------------------------------->
<div class="sample_popup" id="templatename" style="visibility: hidden; display: none;">
<div class="menu_form_header" id="templatename_drag" style="width:800px;height:auto;"> <img class="menu_form_exit"   id="templatename_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchTemplateName"/></div>
<div class="menu_form_body" id="templatenamebody" style="width:800px; height:530px;overflow-y:hidden;padding-left:15px;">
<iframe id="searchTemplatenameFr"  width="99%" height="1px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>
<!-- <iframe align="left" frameborder="0" style="padding:0px;" height="530px;" width="100%"  scrolling="auto" id="searchTemplatenameFr" name="searchTemplatenameFr" allowtransparency="yes"></iframe> --></div></div>
<!-------------------------------------------Template Search Lookup END ---------------------------------->

<!-----------------------------------------Branch Code Lookup----------------------------------------------------->
<div class="sample_popup" id="jobbranchcode" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="jobbranchcode_drag" style="width:750px; "> 
<img class="menu_form_exit"   id="jobbranchcode_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="searchBranchCode"/>
  </div>
  <div class="menu_form_body" id="jobbranchcodebody"   style="width:750px; height:530px;padding-left:4px;overflow-y:hidden;">
    <form method="post" action="popup.php">
     
            <iframe align="left" id="jobbu" frameborder="0" style="padding:10px;" height="530px;" width="100%"  scrolling="auto" id="searchBranchCodeFr" name="searchBranchCodeFr" allowtransparency="yes" src="blank.html"></iframe>
             
    </form>
  </div>
</div>

<!-----------------------------------------Branch Code Lookup END------------------------------------------------->

<!-- -----------------------------------  Job Cancel Reason Note Lookup ------------------------------------------------- -->
<div class="sample_popup" id="cancelreason" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="cancelreason_drag" style="width:800px; "> 
  <img class="menu_form_exit"   id="cancelreason_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="cancelReason"/></div>
  <div class="menu_form_body" id="cancelreason"   style="width:800px; height:auto">    
    <table width="95%" align="center" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td align="center">
          <iframe id="cancelreasonbox" width="100%" height="200px" scrolling="auto" frameborder="0" allowtransparency="yes" style="border:0px; background:none" src="blank.html"></iframe>
        </td>
      </tr>
    </table>  
  </div>
</div>
<!-----------------------------------  Job Cancel Reason Note Lookup END ----------------------------------------- -->

      
<script type="text/javascript">

  <c:forEach items="${command.addJobVessels}" var="addJobVessel" varStatus="addJobVesselCounter">
      <c:choose>
        <c:when test="${addJobVessel.displayStatus == 'HIDE'}">
          hidevesselTable('${addJobVesselCounter.index}');
        </c:when>
        <c:otherwise>
          showvesselTable('${addJobVesselCounter.index}');
        </c:otherwise>
      </c:choose>

      <c:forEach items="${addJobVessel.addJobProds}" var="addJobProd" varStatus="addJobProdCounter">   
      <c:choose>
        <c:when test="${addJobVessel.addJobProds[addJobProdCounter.index].displayStatus == 'HIDE'}">
          hidevproductTable(${addJobVesselCounter.index },${addJobProdCounter.index },'${command.jobOrder.jobType}');
        </c:when>
        <c:otherwise>
          showvproductTable(${addJobVesselCounter.index },${addJobProdCounter.index },'${command.jobOrder.jobType}');
        </c:otherwise>
      </c:choose>
      
        <c:forEach items="${addJobProd.addJobProdSamples}" var="addJobProdSample" varStatus="addJobProdSampleCounter">        
              <c:choose>
            <c:when test="${addJobProdSample.displayStatus == 'HIDE'}">
              hidevpsampleTable(${addJobVesselCounter.index },${addJobProdCounter.index },${fn:length(addJobProd.addJobProdSamples)});
            </c:when>
            <c:otherwise>
              showvpsampleTable(${addJobVesselCounter.index },${addJobProdCounter.index },${fn:length(addJobProd.addJobProdSamples)});
            </c:otherwise>
          </c:choose>
      
        </c:forEach>
        
    </c:forEach>
  </c:forEach>
  <c:choose>
  <c:when test="${command.instrDisplayFlag}">
    showInstructions();
  </c:when>
  <c:otherwise>
    hideInstructions();
  </c:otherwise>
 </c:choose>
</script>             

 <ajax:autocomplete
  baseUrl="branch.htm" 
  source="jobOrder.limsBranchId" 
   target="jobOrder.limsBranchId" 
   className="autocomplete"             
  parameters="buName=${command.jobOrder.buName},labBranchSearch={jobOrder.limsBranchId}"
     minimumCharacters="3"
      />

<ajax:autocomplete
  baseUrl="template.htm"
  source="searchTemplate"
  target="templateNum"
  className="autocomplete"
  parameters="searchTmp={searchTemplate},branchNam=${command.jobOrder.branchName}"
  minimumCharacters="1"
  postFunction="populateJobInst"/>
