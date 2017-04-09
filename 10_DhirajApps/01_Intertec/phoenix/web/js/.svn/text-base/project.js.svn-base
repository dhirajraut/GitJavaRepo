 var xmlHttp;
   function initXmlHttp(callback){
   	try{  // Firefox, Opera 8.0+, Safari  
   	  	xmlHttp=new XMLHttpRequest();  
   	}
   	catch (e){  // Internet Explorer  
   		try{    
   			xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");    
   		}
   		catch (e){
   			try{      
   				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");      
   			}
   			catch (e){
   				alert("Your browser does not support AJAX.");      
   				return false;      
   			}    
   		}  
   	}

   	/*
   	0 The request is not initialized 
   	1 The request has been set up 
   	2 The request has been sent 
   	3 The request is in process 
   	4 The request is complete 
   */	
   	xmlHttp.onreadystatechange=callback;
   }

   function createProject(form){
   	if(confirm('Are you sure you want to create the project?')){
   		var projectTypeIndex=form.projectType.selectedIndex;

   		initXmlHttp(function(){
   			if(xmlHttp.readyState==4){
   				form.projectId.value=xmlHttp.responseText;
   			}
   		});
   		xmlHttp.open("POST", "phx_create_project.htm", true);
   		xmlHttp.send(getQueryString(form));
   		
   	}
   }
   
   /*
    * return the query string for the given form
    */
   function getQueryString(form){
	   return "hello=1&x=true";
   }
