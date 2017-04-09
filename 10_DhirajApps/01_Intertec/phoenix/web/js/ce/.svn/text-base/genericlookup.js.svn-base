function userSelected(userValue, targetFieldId){
    if(window.name!='lookup') {       
		top.popupboxclose();
		top.hidePopupDiv('userDetails','userDetailsbody');	
		window.top.document.getElementById(targetFieldId).value = userValue;	
		}
		else{
			return_popup_result(userValue, targetFieldId);
		 }
}

function employeeSelected(employeeValues, targetFieldIds){
	var values = employeeValues.split('|');
	var fields = targetFieldIds.split('|');
	
	if(window.name=='lookup') {
		parent.opener.document.getElementById(fields[1]).value = values[1].trim();
		parent.opener.document.getElementById(fields[0]).value = values[0].trim();		
		parent.opener.document.getElementById(fields[0]).focus();
		window.close();
	}
	else{
		top.popupboxclose();
		top.hidePopupDiv('employeeDetails','employeeDetailsbody');
		window.top.document.getElementById(fields[1]).value = values[1].trim();
		window.top.document.getElementById(fields[0]).value = values[0].trim();
	}
}

function warehouseSelected(warehouseValue, targetFieldId){	
    if(window.name!='lookup') {    
		top.popupboxclose();
		top.hidePopupDiv('jobbranchcode','jobbranchcodebody');
		var innerTextStr =warehouseValue;
		var currTokens = innerTextStr.split('~');
		window.top.document.getElementById(targetFieldId).value = currTokens[0].trim();
			if(currTokens.length>1){
				var serviceLocationToken = currTokens[1].split('|');
				window.top.document.getElementById("serviceLoc").value = serviceLocationToken[0].trim();
				window.top.document.getElementById("serviceLocCode").value = serviceLocationToken[1].trim();						
			}
		}
		else {
		var innerTextStr =warehouseValue;
		var currTokens = innerTextStr.split('~');
		if(currTokens.length>1){
			var serviceLocationToken = currTokens[1].split('|');
			parent.opener.document.getElementById("serviceLocationName").value = serviceLocationToken[0].trim();
			parent.opener.document.getElementById("serviceLocationCode").value = serviceLocationToken[1].trim();						
		}
		return_popup_result(currTokens[0].trim(), targetFieldId);
		}
}

function servicelocationSelected(serviceLocationValue,targetFieldId) {
  if(window.name!='lookup') {
	top.popupboxclose();   
	top.hidePopupDiv('servloc','servlocbody');
	var innerTextStr =serviceLocationValue;
	var currTokens = innerTextStr.split('|');
	var servicelocationField=targetFieldId.split('|');
	window.top.document.getElementById("serviceLocCode").value = currTokens[1].trim();
	window.top.document.getElementById("serviceLoc").value = currTokens[0].trim();
	//confirm(window.top.document.getElementById("serviceLocCode").value);
  }
  else{
	return_popup_result_serviceLocation(serviceLocationValue, targetFieldId);
  }
}

String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}

function return_popup_result_serviceLocation(selectedValue,inputfield) {
	var innerTextStr =selectedValue;
	var currTokens = innerTextStr.split('|');
	var servicelocationField=inputfield.split('|');
	parent.opener.document.getElementById(servicelocationField[0]).value = currTokens[0].trim();
	parent.opener.document.getElementById(servicelocationField[1]).value = currTokens[1].trim();
	parent.opener.document.getElementById(servicelocationField[0]).focus();	
	window.close();
}


function poSelected(poNumberValue, targetFieldId) {
    if(window.name!='lookup') {    
     top.popupboxclose();   
     top.hidePopupDiv('searchaccount','accountbody');  
     window.top.document.getElementById(targetFieldId).value=poNumberValue;
	 }
		else {
			return_popup_result(poNumberValue, targetFieldId);
		}
}

function contactSelected(contactValue, targetFieldId) {       
		top.popupboxclose();   
	    top.hidePopupDiv('searchcontact','contactbody');  
	    window.top.document.getElementById(targetFieldId).value=contactValue;	
}

function return_popup_result(selectedValue,inputfield) {     
	  parent.opener.document.getElementById(inputfield).value=selectedValue;
	  parent.opener.document.getElementById(inputfield).focus();
	  window.close();
}

function serviceofferingSelected(serOffValue, targetFieldId) {
    top.popupboxclose();	
    top.hidePopupDiv('serviceoff','serviceoffbody');	
	window.top.document.getElementById(targetFieldId).value = serOffValue;
}

function buStreamSelected(buStreamCode, targetFieldId) {
    top.popupboxclose();	
    top.hidePopupDiv('buStream','buStreambody');	
	window.top.document.getElementById(targetFieldId).value = buStreamCode;
}

function jobSelected(jobNumber, targetFieldId) {
	top.popupboxclose();   
    top.hidePopupDiv('parentJob','parentbody');  
    window.top.document.getElementById(targetFieldId).value=jobNumber;
    window.top.document.getElementById(targetFieldId).focus();	
}
function closePopup(){
    top.popupboxclose();   
    top.hidePopupDiv('servloc','servlocbody');
    window.parent.location ='create_service_location.htm';
}

function buSelected(name, targetFieldId) {
	top.popupboxclose();   
    top.hidePopupDiv('bu','bubody');  
    window.top.document.getElementById(targetFieldId).value=name;
    window.top.document.getElementById(targetFieldId).focus();	
}

