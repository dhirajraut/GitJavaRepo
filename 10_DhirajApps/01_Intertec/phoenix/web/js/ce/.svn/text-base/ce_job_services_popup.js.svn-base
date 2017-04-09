function onSelectService() {
    document.ceServicePopupForm.operation.value = "selectService";
    top.document.getElementById('addJSId').height = "450px";
    document.ceServicePopupForm.submit();
}

function addService() {
    document.ceServicePopupForm.operation.value = "addService";
    document.ceServicePopupForm.submit();
}

function refreshParent() {
	if(document.ceServicePopupForm.operation.value == "addService") {
		document.ceServicePopupForm.operation.value = "";
		top.document.forms[0].refreshing.value="refresh";
		top.document.forms[0].submit();
	}
}

