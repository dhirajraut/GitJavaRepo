// JavaScript Document

<!--
function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_showHideLayers() { //v6.0
  var i,p,v,obj,args=MM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) if ((obj=MM_findObj(args[i]))!=null) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v=='hide')?'hidden':v; }
    obj.visibility=v; }
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
   }

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

// function to hide combo box 
var isDOM = (document.getElementById ? true : false);
var isIE4 = ((document.all && !isDOM) ? true : false);
var isNS4 = (document.layers ? true : false);
n=document.layers
ie=document.all


function hideIt(){
	
	var selArray
	if(ie)
		selArray=document.all.tags("SELECT")
		else
		selArray=document.getElementsByTagName("SELECT")
		if (selArray.length==0 && document.getElementById("frame1")) {
	          selArray= document.frames("frame1").document.all.tags("SELECT")
         
	}
	if(selArray && selArray.length){
		for(i=0;i<selArray.length;i++){
			if(selArray[i].id == "sel0" || selArray[i].id == "sel1" || selArray[i].id == "sel2" || selArray[i].id == "sel3" || selArray[i].id == "sel4" || selArray[i].id == "sel5" || selArray[i].id == "sel6" || selArray[i].id == "sel7" || selArray[i].id == "sel8" || selArray[i].id == "sel9" || selArray[i].id == "sel10" || selArray[i].id == "sel11" || selArray[i].id == "sel12"  || selArray[i].id == "sel13" || selArray[i].id == "sel14" || selArray[i].id == "sel15" || selArray[i].id == "sel20" || selArray[i].id == "selcountry" || selArray[i].id == "selstate" || selArray[i].id == "jobContractStatus0" || selArray[i].id == "jobContractStatus1" || selArray[i].id == "jobContractStatus2" || selArray[i].id == "jobContractStatus3" || selArray[i].id == "jobContractStatus4"){
				selArray[i].style.visibility="hidden"
			}
		}
	}
}


// function to Show combo box 
function showIt(){
	//divCopyGridDataStep2 has two dropdowns and both are visible in step2.To avoid that the if(obj.visibility=="hidden") condition is added.
	var obj= document.getElementById("divCopyGridDataStep2");
	if(obj){
		if(obj.style.visibility=="hidden"){				
				var selArray
				if(ie)
					selArray=document.all.tags("SELECT")
				else
					selArray=document.getElementsByTagName("SELECT")
						if (selArray.length==0 && document.getElementById("frame1")) {

	          selArray= document.frames("frame1").document.all.tags("SELECT")
             
	}
	                  			if(selArray && selArray.length){
					for(i=0;i<selArray.length;i++){
							if(selArray[i].id == "sel0" || selArray[i].id == "sel1" || selArray[i].id == "sel2" || selArray[i].id == "sel3" || selArray[i].id == "sel4" || selArray[i].id == "sel5" || selArray[i].id == "sel5" || selArray[i].id == "sel6" || selArray[i].id == "sel7" || selArray[i].id == "sel8" || selArray[i].id == "sel9" || selArray[i].id == "sel10" || selArray[i].id == "sel11" || selArray[i].id == "sel12" || selArray[i].id == "sel13"|| selArray[i].id == "sel14" || selArray[i].id == "sel15" || selArray[i].id == "sel20" || selArray[i].id == "selcountry" || selArray[i].id == "selstate" || selArray[i].id == "jobContractStatus0" || selArray[i].id == "jobContractStatus1" || selArray[i].id == "jobContractStatus2" || selArray[i].id == "jobContractStatus3" || selArray[i].id == "jobContractStatus4"){
							selArray[i].style.visibility="visible"
						}
					}
                }
		}
	}
	else{
				var selArray
				if(ie)
					selArray=document.all.tags("SELECT")
				else
					selArray=document.getElementsByTagName("SELECT")
					if (selArray.length==0 && document.getElementById("frame1")) {
	          selArray= document.frames("frame1").document.all.tags("SELECT")
         
	}
						if(selArray && selArray.length){
					for(i=0;i<selArray.length;i++){
							if(selArray[i].id == "sel0" || selArray[i].id == "sel1" || selArray[i].id == "sel2" || selArray[i].id == "sel3" || selArray[i].id == "sel4" || selArray[i].id == "sel5" || selArray[i].id == "sel5" || selArray[i].id == "sel6" || selArray[i].id == "sel7" || selArray[i].id == "sel8" || selArray[i].id == "sel9" || selArray[i].id == "sel10" || selArray[i].id == "sel11" || selArray[i].id == "sel12" || selArray[i].id == "sel13" || selArray[i].id == "sel14" || selArray[i].id == "sel15" || selArray[i].id == "sel20" || selArray[i].id == "selcountry" || selArray[i].id == "selstate" || selArray[i].id == "jobContractStatus0" || selArray[i].id == "jobContractStatus1" || selArray[i].id == "jobContractStatus2" || selArray[i].id == "jobContractStatus3" || selArray[i].id == "jobContractStatus4"){
							selArray[i].style.visibility="visible"
						}
					}
                }
	}
}

//-->	


function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}