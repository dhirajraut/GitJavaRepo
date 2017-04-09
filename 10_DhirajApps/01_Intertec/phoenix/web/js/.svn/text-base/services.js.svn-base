// JavaScript Document For Adding Services

String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}

// add job services
function popJobServices(index)
{
  popup_show('services_' + index, 'services_drag_' + index, 'services_exit_' + index, 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();
}

function createOCAProject(form){
	   if(confirm('Are you sure you want to create the project? Have you verified the line items?')){		   
		   form.createProject.value="true";
		   submitFunction();
	   }
}

function closeservices(index)
{
  popupboxclose();
    
  document.getElementById("addtest_" + index).style.visibility = "hidden";
  document.getElementById("addtest_" + index).style.display = "none";  

  document.getElementById("addmanualtest_" + index).style.visibility = "hidden";
  document.getElementById("addmanualtest_" + index).style.display = "none";

  document.getElementById("addslate_" + index).style.visibility = "hidden";
  document.getElementById("addslate_" + index).style.display = "none";

  document.getElementById("editlot_" + index).style.visibility = "hidden";
  document.getElementById("editlot_" + index).style.display = "none";

  document.getElementById("edittest_" + index).style.visibility = "hidden";
  document.getElementById("edittest_" + index).style.display = "none";

  document.getElementById("editslate_" + index).style.visibility = "hidden";
  document.getElementById("editslate_" + index).style.display = "none";

  document.getElementById("addvesselbox").style.visibility = "hidden";
  document.getElementById("addvesselbox").style.display = "none";

  document.getElementById("addeditserivce").style.visibility = "hidden";
  document.getElementById("addeditserivce").style.display = "none";
}

function services(obj, index, contractCode) 
{
  if (document.getElementById(obj).id=="selserv_" + index) 
  {
    for(i=0;i<document.getElementById("selserv_" + index).options.length;i++) 
    {
      if (document.getElementById("selserv_" + index).options[i].selected==true)
      {
        if (document.getElementById("selserv_" + index).options[i].value!="Job Services >>") 
        {
          document.getElementById("servBox_" + index).src="display_control_popup.htm?index=" + index + "&contractCode=" + contractCode + "&serviceName=" + document.getElementById("selserv_" + index).options[i].value + "&parentServiceId=Inspection&inputComponentId=JOB_SERVICE&serviceType=JobService";             
          document.getElementById("servBox_" + index).height = "250px";
        }        
      }
    }
  }
}
// add job services


// add standalone services
function popStandServices(index)
{
  popup_show('standservices_' + index, 'standservices_drag_' + index, 'standservices_exit_' + index, 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();
}

function Standservices(obj, index, contractCode) 
{
  if (document.getElementById(obj).id=="selstndserv_" + index) 
  {
    for(i=0;i<document.getElementById("selstndserv_" + index).options.length;i++) 
    {
      if (document.getElementById("selstndserv_" + index).options[i].selected==true)
      {
        if (document.getElementById("selstndserv_" + index).options[i].value!="Standalone Services >>") 
        {
          document.getElementById("stndservBox_" + index).src="display_control_popup.htm?index=" + index + "&contractCode=" + contractCode + "&serviceName=" + document.getElementById("selstndserv_" + index).options[i].value + "&parentServiceId=StandAlone&serviceType=StandAloneService";             
          document.getElementById("stndservBox_" + index).height = "250px";
        }        
      }
    }
  }
}

// add standalone services



// add calibration services
function popCalibServices(index)
{
  popup_show('calibservices_' + index, 'calibservices_drag_' + index, 'calibservices_exit_' + index, 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();
}

function Calibservices(obj, index, contractCode) 
{
  if (document.getElementById(obj).id=="selcalserv_" + index) 
  {
    for(i=0;i<document.getElementById("selcalserv_" + index).options.length;i++) 
    {
      if (document.getElementById("selcalserv_" + index).options[i].selected==true)
      {
        if (document.getElementById("selcalserv_" + index).options[i].value!="Calibration Services >>") 
        {
          document.getElementById("calservBox_" + index).src="display_control_popup.htm?index=" + index + "&contractCode=" + contractCode + "&serviceName=" + document.getElementById("selcalserv_" + index).options[i].value + "&parentServiceId=CAL&serviceType=CalService";             
          document.getElementById("calservBox_" + index).height = "250px";
        }
      }
    }
  }
}

// add calibration services



// add mtd services
function popMTDServices(index)
{
  popup_show('mtdservices_' + index, 'mtdservices_drag_' + index, 'mtdservices_exit_' + index, 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();
}

function MTDservices(obj, index, contractCode) 
{
  if (document.getElementById(obj).id=="selmtdserv_" + index) 
  {
    for(i=0;i<document.getElementById("selmtdserv_" + index).options.length;i++) 
    {
      if (document.getElementById("selmtdserv_" + index).options[i].selected==true)
      {
        if (document.getElementById("selmtdserv_" + index).options[i].value != "MTD Services >>") 
        {
          document.getElementById("mtdservBox_" + index).src="display_control_popup.htm?index=" + index + "&contractCode=" + contractCode + "&serviceName=" + document.getElementById("selmtdserv_" + index).options[i].value + "&parentServiceId=MTD&serviceType=MtdService";             
          document.getElementById("mtdservBox_" + index).height = "250px";
        }        
      }
    }
  }
}

// add mtd services

var selectedVesselIndex;

// add vessel services
function popVesServices(index, mySelectedVesselIndex)
{
  selectedVesselIndex = mySelectedVesselIndex;
  popup_show('vesservices_' + index, 'vesservices_drag_' + index, 'vesservices_exit_' + index, 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();
}

function Vesservices(obj, index, contractCode) 
{
  if (document.getElementById(obj).id=="selvesserv_" + index) 
  {
    for(i=0;i<document.getElementById("selvesserv_" + index).options.length;i++) 
    {
      if (document.getElementById("selvesserv_" + index).options[i].selected==true)
      {
        if (document.getElementById("selvesserv_" + index).options[i].value != "Vessel Services >>") 
        {
          document.getElementById("vesservBox_" + index).src="display_control_popup.htm?index=" + index + "&contractCode=" + contractCode + "&serviceName=" + document.getElementById("selvesserv_" + index).options[i].value + "&selectedVesselIndex=" + selectedVesselIndex + "&parentServiceId=Inspection&inputComponentId=VESSEL_SERVICE&serviceType=VesselService";             
          document.getElementById("vesservBox_" + index).height = "250px";
        }
      }
    }
  }
}

// add vessel services

var selectedProdIndex;

// add lab services
function popLabServices(index, mySelectedVesselIndex, mySelectedProdIndex)
{
  selectedVesselIndex = mySelectedVesselIndex;
  selectedProdIndex = mySelectedProdIndex;
  popup_show('labservices_' + index, 'labservices_drag_' + index, 'labservices_exit_' + index, 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();
}

function Labservices(obj, index, contractCode) 
{
  if (document.getElementById(obj).id=="sellabserv_" + index) 
  {
    for(i=0;i<document.getElementById("sellabserv_" + index).options.length;i++) 
    {
      if (document.getElementById("sellabserv_" + index).options[i].selected==true)
      {
        if (document.getElementById("sellabserv_" + index).options[i].value != "Lab Services >>") 
        {
          document.getElementById("labservBox_" + index).src="display_control_popup.htm?index=" + index + "&contractCode=" + contractCode + "&serviceName=" + document.getElementById("sellabserv_" + index).options[i].value + "&selectedVesselIndex=" + selectedVesselIndex + "&parentServiceId=Inspection&inputComponentId=LOT_SERVICE&serviceType=LotLabService&selectedProdIndex=" + selectedProdIndex;             
          document.getElementById("labservBox_" + index).height = "250px";
        }
      }
    }
  }
}

// add lab services


// add vmm services
function popVMMServices(index, mySelectedVesselIndex, mySelectedProdIndex)
{
  selectedVesselIndex = mySelectedVesselIndex;
  selectedProdIndex = mySelectedProdIndex;
  popup_show('vmmservices_' + index, 'vmmservices_drag_' + index, 'vmmservices_exit_' + index, 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();
}

function VMMservices(obj, index, contractCode) 
{
  if (document.getElementById(obj).id=="selvmmserv_" + index) 
  {
    for(i=0;i<document.getElementById("selvmmserv_" + index).options.length;i++) 
    {
      if (document.getElementById("selvmmserv_" + index).options[i].selected==true)
      {
        if (document.getElementById("selvmmserv_" + index).options[i].value != "VMM Services >>") 
        {
          document.getElementById("vmmservBox_" + index).src="display_control_popup.htm?index=" + index + "&contractCode=" + contractCode + "&serviceName=" + document.getElementById("selvmmserv_" + index).options[i].value + "&selectedVesselIndex=" + selectedVesselIndex + "&parentServiceId=Inspection&inputComponentId=LOT_SERVICE&serviceType=LotVMMService&selectedProdIndex=" + selectedProdIndex;             
          document.getElementById("vmmservBox_" + index).height = "250px";
        }
      }
    }
  }
}

// add vmm services


// add liquid services
function popLiqServices(index, mySelectedVesselIndex, mySelectedProdIndex)
{
  selectedVesselIndex = mySelectedVesselIndex;
  selectedProdIndex = mySelectedProdIndex;
  popup_show('liqservices_' + index, 'liqservices_drag_' + index, 'liqservices_exit_' + index, 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();
}

function Liqservices(obj, index, contractCode) 
{
  if (document.getElementById(obj).id=="selliqserv_" + index) 
  {
    for(i=0;i<document.getElementById("selliqserv_" + index).options.length;i++) 
    {
      if (document.getElementById("selliqserv_" + index).options[i].selected==true)
      {
        if (document.getElementById("selliqserv_" + index).options[i].value != "Liquid Services >>") 
        {
          document.getElementById("liqservBox_" + index).src="display_control_popup.htm?index=" + index + "&contractCode=" + contractCode + "&serviceName=" + document.getElementById("selliqserv_" + index).options[i].value + "&selectedVesselIndex=" + selectedVesselIndex + "&parentServiceId=Inspection&inputComponentId=LOT_SERVICE&serviceType=LotLiquidService&selectedProdIndex=" + selectedProdIndex;             
          document.getElementById("liqservBox_" + index).height = "250px";
        }
      }
    }
  }
}

// add liquid services


// add dry services
function popDryServices(index, mySelectedVesselIndex, mySelectedProdIndex)
{
  selectedVesselIndex = mySelectedVesselIndex;
  selectedProdIndex = mySelectedProdIndex;
  popup_show('dryservices_' + index, 'dryservices_drag_' + index, 'dryservices_exit_' + index, 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();
}

function Dryservices(obj, index, contractCode) 
{
  if (document.getElementById(obj).id=="seldryserv_" + index) 
  {
    for(i=0;i<document.getElementById("seldryserv_" + index).options.length;i++) 
    {
      if (document.getElementById("seldryserv_" + index).options[i].selected==true)
      {
        if (document.getElementById("seldryserv_" + index).options[i].value != "Dry Services >>") 
        {
          document.getElementById("dryservBox_" + index).src="display_control_popup.htm?index=" + index + "&contractCode=" + contractCode + "&serviceName=" + document.getElementById("seldryserv_" + index).options[i].value + "&selectedVesselIndex=" + selectedVesselIndex + "&parentServiceId=Inspection&inputComponentId=LOT_SERVICE&serviceType=LotDryService&selectedProdIndex=" + selectedProdIndex;             
          document.getElementById("dryservBox_" + index).height = "250px";
        }
      }
    }
  }
}

// add dry services


// add test

function popAddTest(index, contractCode, mySelectedVesselIndex, mySelectedProdIndex)
{
  popup_show('addtest_' + index, 'addtest_drag_' + index, 'addtest_exit_' + index, 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();

  document.getElementById("addtestbox_" + index).src="add_test_service_popup.htm?index=" + index + "&contractCode=" + contractCode + "&selectedVesselIndex=" + mySelectedVesselIndex + "&selectedProdIndex=" + mySelectedProdIndex;             
  document.getElementById("addtestbox_" + index).height = "350px";
}

function closeAddTest(index)
{
  document.getElementById("addtest_" + index).style.visibility = "hidden";
  document.getElementById("addtest_" + index).style.display = "none";  
}

// add test

// add manual test

function popAddManualTest(index, contractCode, mySelectedVesselIndex, mySelectedProdIndex, mySelectedManualTestIndex)
{  
  popup_show('addmanualtest_' + index, 'addmanualtest_drag_' + index, 'addmanualtest_exit_' + index, 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();

  document.getElementById("addmanualtestbox_" + index).src="add_manual_test_service_popup.htm?index=" + index + "&contractCode=" + contractCode + "&selectedVesselIndex=" + mySelectedVesselIndex + "&selectedProdIndex=" + mySelectedProdIndex + "&selectedManualTestIndex=" + mySelectedManualTestIndex;              
  document.getElementById("addmanualtestbox_" + index).height = "250px";
}

function closeAddManualTest(index)
{
  document.getElementById("addmanualtest_" + index).style.visibility = "hidden";
  document.getElementById("addmanualtest_" + index).style.display = "none";
}


// add manual test

// add slate test

function popAddSlate(index, contractCode, mySelectedVesselIndex, mySelectedProdIndex)
{
  popup_show('addslate_' + index, 'addslate_drag_' + index, 'addslate_exit_' + index, 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();

  document.getElementById("addslatebox_" + index).src="add_slate_service_popup.htm?index=" + index + "&contractCode=" + contractCode + "&selectedVesselIndex=" + mySelectedVesselIndex + "&selectedProdIndex=" + mySelectedProdIndex;             
  document.getElementById("addslatebox_" + index).height = "350px";
}

function closeAddSlate(index)
{ 
  document.getElementById("addslate_" + index).style.visibility = "hidden";
  document.getElementById("addslate_" + index).style.display = "none";
}

// add slate test

// edit lot test

function popEditLot(index, contractCode, mySelectedVesselIndex, mySelectedProdIndex, mySelectedInspectionIndex)
{
  popup_show('editlot_' + index, 'editlot_drag_' + index, 'editlot_exit_' + index, 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();

  document.getElementById("editlotbox_" + index).src="add_inspection_service_popup.htm?index=" + index + "&contractCode=" + contractCode + "&selectedVesselIndex=" + mySelectedVesselIndex + "&selectedProdIndex=" + mySelectedProdIndex + "&selectedInspectionIndex=" + mySelectedInspectionIndex;             
  document.getElementById("editlotbox_" + index).height = "450px";
}

function closeEditLot(index)
{
  document.getElementById("editlot_" + index).style.visibility = "hidden";
  document.getElementById("editlot_" + index).style.display = "none";
}
  

// edit lot test

// edit job service

function popEditJobService(index, contractCode, mySelectedServiceIndex)
{
  popup_show('editjobservice_' + index, 'editjobservice_drag_' + index, 'editjobservice_exit_' + index, 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();

  document.getElementById("editjobservicebox_" + index).src="display_control_popup.htm?index=" + index + "&contractCode=" + contractCode + "&selectedServiceIndex=" + mySelectedServiceIndex;             
  document.getElementById("editjobservicebox_" + index).height = "450px";
}  

// edit job service

// edit vessel service

function popEditVesselService(index, contractCode, mySelectedVesselIndex, mySelectedServiceIndex)
{
  popup_show('editvesselservice_' + index, 'editvesselservice_drag_' + index, 'editvesselservice_exit_' + index, 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();

  document.getElementById("editvesselservicebox_" + index).src="display_control_popup.htm?index=" + index + "&contractCode=" + contractCode + "&selectedServiceIndex=" + mySelectedServiceIndex + "&selectedVesselIndex=" + mySelectedVesselIndex;             
  document.getElementById("editvesselservicebox_" + index).height = "450px";
}  

// edit vessel service

// edit product service

function popEditProductService(index, contractCode, mySelectedVesselIndex, mySelectedProdIndex, mySelectedServiceIndex)
{
  popup_show('editproductservice_' + index, 'editproductservice_drag_' + index, 'editproductservice_exit_' + index, 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();

  document.getElementById("editproductservicebox_" + index).src="display_control_popup.htm?index=" + index + "&contractCode=" + contractCode + "&selectedServiceIndex=" + mySelectedServiceIndex + "&selectedVesselIndex=" + mySelectedVesselIndex + "&selectedProdIndex=" + mySelectedProdIndex;             
  document.getElementById("editproductservicebox_" + index).height = "450px";
}  

// edit product service

// edit test

function popEditTest(index, contractCode, mySelectedVesselIndex, mySelectedProdIndex, mySelectedTestIndex)
{
  popup_show('edittest_' + index, 'edittest_drag_' + index, 'edittest_exit_' + index, 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();

  document.getElementById("edittestbox_" + index).src="edit_test_service_popup.htm?index=" + index + "&contractCode=" + contractCode + "&selectedTestIndex=" + mySelectedTestIndex + "&selectedVesselIndex=" + mySelectedVesselIndex + "&selectedProdIndex=" + mySelectedProdIndex;             
  document.getElementById("edittestbox_" + index).height = "150px";
}  

// edit test

// edit slate

function popEditSlate(index, contractCode, mySelectedVesselIndex, mySelectedProdIndex, mySelectedSlateIndex)
{
  popup_show('editslate_' + index, 'editslate_drag_' + index, 'editslate_exit_' + index, 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();

  document.getElementById("editslatebox_" + index).src="edit_slate_service_popup.htm?index=" + index + "&contractCode=" + contractCode + "&selectedSlateIndex=" + mySelectedSlateIndex + "&selectedVesselIndex=" + mySelectedVesselIndex + "&selectedProdIndex=" + mySelectedProdIndex;             
  document.getElementById("editslatebox_" + index).height = "150px";
}  

// edit slate

// edit job contract vessel
function popAddVessel(index, contractCode, mySelectedVesselIndex)
{
  popup_show('addvessel', 'addvessel_drag', 'addvessel_exit', 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();

  document.getElementById("addvesselbox").src="add_job_contract_vessel_popup.htm?index=" + index + "&contractCode=" + contractCode + "&selectedVesselIndex=" + mySelectedVesselIndex;             
  document.getElementById("addvesselbox").height = "150px";
}  

// add/edit service
function popAddEditService(index, contractCode, mySelectedVesselIndex, mySelectedProdIndex, myServiceType, mySelectedServiceIndex)
{
  popup_show('addeditserivce', 'addeditserivce_drag', 'addeditserivce_exit', 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();

  document.getElementById("addeditserivcebox").src="display_control_popup.htm?index=" + index + "&contractCode=" + contractCode + "&selectedServiceIndex=" + mySelectedServiceIndex + "&selectedVesselIndex=" + mySelectedVesselIndex + "&selectedProdIndex=" + mySelectedProdIndex + "&serviceType=" + myServiceType;             
  document.getElementById("addeditserivcebox").height = "450px";
}  

function popupSearchLink(inputFieldId)
{
  popup_show('permissionlist', 'permissionlist_drag', 'permissionlist_exit', 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();

  document.getElementById("permissionlistbox").src="search_link_popup.htm?inputFieldId=" + inputFieldId;           
  document.getElementById("permissionlistbox").height = "450px";
}  

function closeSearchLink()
{
  popupboxclose();
    
  document.getElementById("permissionlist").style.visibility = "hidden";
  document.getElementById("permissionlist").style.display = "none";
}
