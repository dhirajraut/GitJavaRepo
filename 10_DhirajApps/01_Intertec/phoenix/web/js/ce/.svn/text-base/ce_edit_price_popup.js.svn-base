function submitEditPriceForm(){			
	document.editPricePopupForm.operation.value="updateRS";
	document.editPricePopupForm.submit();
}

function refreshParent() {
	if(document.editPricePopupForm.operation.value == "refreshParent") {
		document.editPricePopupForm.operation.value = "";
		top.document.forms[0].refreshing.value="refresh";
		top.document.forms[0].submit();
	}
}
 