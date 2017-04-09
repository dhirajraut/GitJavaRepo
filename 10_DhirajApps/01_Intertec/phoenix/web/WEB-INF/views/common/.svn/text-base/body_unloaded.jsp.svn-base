<html>
<script language="JavaScript">
function myBodyUnloaded(){
	if(window.event.clientY < 0 && (window.event.clientX > (document.documentElement.clientWidth - 5) || window.event.clientX < 15) ) {
		document.forms[0].submit();
	}	
}

</script>
<body onUnload="myBodyUnloaded()">
	<form name="myUnloadFormName" method="POST" action="body_unloaded.htm">
	</form>
</body>
</html>