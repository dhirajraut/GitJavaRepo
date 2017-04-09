// JavaScript Document For Create/Edit Contracts

function closeContractPopups()
{
  popupboxclose();
    
  var obj = document.getElementById("editproductgroupset");
  if(obj) 
  {
    obj.style.visibility = "hidden";
    obj.style.display = "none";
  }

  obj = document.getElementById("editvesseltypeset");
  if(obj) 
  {
    obj.style.visibility = "hidden";
    obj.style.display = "none";
  }

  obj = document.getElementById("copypbtestprice");
  if(obj) 
  {
    obj.style.visibility = "hidden";
    obj.style.display = "none";
  }

  obj = document.getElementById("modifytestprices");
  if(obj) 
  {
    obj.style.visibility = "hidden";
    obj.style.display = "none";
  }

  obj = document.getElementById("createcontracttest");
  if(obj) 
  {
    obj.style.visibility = "hidden";
    obj.style.display = "none";
  }


  obj = document.getElementById("copypbslateprice");
  if(obj) 
  {
    obj.style.visibility = "hidden";
    obj.style.display = "none";
  }

  obj = document.getElementById("modifyslateprices");
  if(obj) 
  {
    obj.style.visibility = "hidden";
    obj.style.display = "none";
  }

  obj = document.getElementById("createcontractslate");
  if(obj) 
  {
    obj.style.visibility = "hidden";
    obj.style.display = "none";
  }
}

// edit product group set
function popEditProductGroupSet(index)
{
  popup_show('editproductgroupset', 'editproductgroupset_drag', 'editproductgroupset_exit', 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();

  document.getElementById("editproductgroupsetbox").src="edit_product_group_set_popup.htm?index=" + index;             
  document.getElementById("editproductgroupsetbox").height = "450px";
}  

// edit vessel type set
function popEditVesselTypeSet(index)
{
  popup_show('editvesseltypeset', 'editvesseltypeset_drag', 'editvesseltypeset_exit', 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();

  document.getElementById("editvesseltypesetbox").src="edit_vessel_type_set_popup.htm?index=" + index;             
  document.getElementById("editvesseltypesetbox").height = "450px";
}  

// copy pb test price
function popCopyPbTestPrice(priceBookId, contractCode, pageNumber)
{
  popup_show('copypbtestprice', 'copypbtestprice_drag', 'copypbtestprice_exit', 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();

  document.getElementById("copypbtestpricebox").src="copy_pb_test_price_popup.htm?priceBookId=" + priceBookId + "&contractCode=" + contractCode + "&pageNumber=" + pageNumber;             
  document.getElementById("copypbtestpricebox").height = "450px";
}  

// copy pb test price
function popModifyTestPrice(priceBookId, contractCode, testId, priceType, pageNumber)
{
  popup_show('modifytestprices', 'modifytestprices_drag', 'modifytestprices_exit', 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();

  document.getElementById("modifytestpricesbox").src="modify_test_price_popup.htm?priceBookId=" + priceBookId + "&contractCode=" + contractCode + "&testId=" + testId + "&priceType=" + priceType + "&pageNumber=" + pageNumber;             
  document.getElementById("modifytestpricesbox").height = "500px";
}  

// copy pb test price
function popCreateContractTest(contractCode, pageNumber)
{
  popup_show('createcontracttest', 'createcontracttest_drag', 'createcontracttest_exit', 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();

  document.getElementById("createcontracttestbox").src="create_contract_test_popup.htm?contractCode=" + contractCode + "&pageNumber=" + pageNumber;             
  document.getElementById("createcontracttestbox").height = "450px";
}  


// copy pb slate price
function popCopyPbSlatePrice(priceBookId, contractCode, pageNumber)
{
  popup_show('copypbslateprice', 'copypbslateprice_drag', 'copypbslateprice_exit', 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();

  document.getElementById("copypbslatepricebox").src="copy_pb_slate_price_popup.htm?priceBookId=" + priceBookId + "&contractCode=" + contractCode + "&pageNumber=" + pageNumber;             
  document.getElementById("copypbslatepricebox").height = "450px";
}  

// copy pb slate price
function popModifySlatePrice(priceBookId, contractCode, slateId, priceType, pageNumber)
{
  popup_show('modifyslateprices', 'modifyslateprices_drag', 'modifyslateprices_exit', 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();

  document.getElementById("modifyslatepricesbox").src="modify_slate_price_popup.htm?priceBookId=" + priceBookId + "&contractCode=" + contractCode + "&slateId=" + slateId + "&priceType=" + priceType + "&pageNumber=" + pageNumber;             
  document.getElementById("modifyslatepricesbox").height = "500px";
}  

// copy pb slate price
function popCreateContractSlate(contractCode, pageNumber)
{
  popup_show('createcontractslate', 'createcontractslate_drag', 'createcontractslate_exit', 'screen-corner', 40, 80);
  hideIt();
  popupboxenable();

  document.getElementById("createcontractslatebox").src="create_contract_slate_popup.htm?contractCode=" + contractCode + "&pageNumber=" + pageNumber;             
  document.getElementById("createcontractslatebox").height = "450px";
}  
