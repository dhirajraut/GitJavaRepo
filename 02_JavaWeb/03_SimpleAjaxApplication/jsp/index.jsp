<html>
	<head>
		<script type="text/javascript">
		var xmlHttp = null;
		function ajaxFunction() {
			try {
				// Firefox, Opera 8.0+, Safari
				xmlHttp=new XMLHttpRequest();
			}
			catch (e) {
				// Internet Explorer
				try {
					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
					alert("IE");
				}
				catch (e) {
					try {
						xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
					}
					catch (e) {
						alert("Your browser does not support AJAX!");
						return false;
					}
				}
			}
			var url = "time.jsp?inputChar=" + ajaxForm.elements.username.value;
//			alert (url);
			xmlHttp.open("GET", url,true);
			xmlHttp.onreadystatechange = listen;
			xmlHttp.send(null);
		}

		function listen() {
			if(xmlHttp.readyState==4){
				if (xmlHttp.status == 200) {
					ajaxForm.elements.guess.value = xmlHttp.responseText;
					document.getElementById("hint").innerHTML = xmlHttp.responseText;
				}
			}
		}
		</script>
	</head>
	<body>
		<form name = "ajaxForm">
			Name: <input type="text" onkeyup="ajaxFunction();" name="username" value=""/>
			Guess: <input type="text" name="guess" value=""/>
			<p>
				<span id="hint"></span>
			</p>
		</form>
	</body>
</html>