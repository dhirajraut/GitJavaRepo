function submitEditDepositAmountForm(){			
	document.editDepositAmountPopupForm.operation.value="updateAmount";
	document.editDepositAmountPopupForm.submit();
}

function refreshParent() {
	if(document.editDepositAmountPopupForm.operation.value == "refreshParent") {
		document.editDepositAmountPopupForm.operation.value = "";
		top.document.forms[0].refreshing.value="refresh";
		top.document.forms[0].submit();
	}
}
 