/** JavaScript **/


		try {
  			document.execCommand("BackgroundImageCache", false, true);
		} catch(err) {}



ieFocus = function() {

if(document.getElementById("navmenu") != null){
var ieULs = document.getElementById('navmenu').getElementsByTagName('ul');
/** IE script to cover <select> elements with <iframe>s **/
for (j=0; j<ieULs.length; j++) {
ieULs[j].innerHTML = ('<iframe src="javascript:false;" scrolling="no" frameborder="0"></iframe>' + ieULs[j].innerHTML);
/*ieULs[j].innerHTML = ('<iframe id="iePad' + j + '" src="/ecare/images/clearpixel.gif" scrolling="no" frameborder="0" style=""></iframe>' + ieULs[j].innerHTML);
	var ieMat = document.getElementById('iePad' + j + '');*/
//	var ieMat = ieULs[j].childNodes[0];  alert(ieMat.nodeName); // also works...
	var ieMat = ieULs[j].firstChild;
		ieMat.style.width=ieULs[j].offsetWidth+"px";
		ieMat.style.height=ieULs[j].offsetHeight+"px";	
		ieULs[j].style.zIndex="99";
		
}
/** IE script to change class on mouseover **/
	var index1 = 0;
	var index2 = 0;

	var ieLIs = document.getElementById('navmenu').getElementsByTagName('li');
	for (var i=0; i<ieLIs.length; i++) {
		if (ieLIs[i]) 
		{
			ieLIs[i].onclick=function() {

				/* To Collapse the Expanded Menu */
				if (index1 == 0) {
					var ieLIsz = document.getElementById('navmenu').getElementsByTagName('li');
				} else {

					var ieLIsz = this.parentNode.childNodes;
				}
					if (this.title == '' )
					{

						for (var k11=0; k11<ieLIsz.length; k11++) {
							ieLIsz[k11].title=ieLIsz[k11].title.replace('hide', '');
						}
						this.title="hide";
					} else {

						if (index2 == 0)
						{
							for (var k11=0; k11<ieLIsz.length; k11++) {
								ieLIsz[k11].title=ieLIsz[k11].title.replace('hide', '');
								ieLIsz[k11].className=ieLIsz[k11].className.replace(' iefocus', '');
							}
							index1 = 0;
							return;
						}
					}


				/* To Collapse the Expanded Menu */

				if (this.parentNode.id !='')
				{

					if (index1 == 0)
					{
						index1 = 1;
						var ieLIsy = document.getElementById('navmenu').getElementsByTagName('li');

					} else {
						index1 = 0;
						var ieLIsy = this.parentNode.childNodes;
					}

					for (var k1=0; k1<ieLIsy.length; k1++) {
						ieLIsy[k1].className=ieLIsy[k1].className.replace(' iefocus', '');
					}
					this.className+=" iefocus";
					index2 = 0;

				} else {



					index1 = 1;
					index2 = 1;
					var ieLIsx = this.parentNode.childNodes;
					for (var k2=0; k2<ieLIsx.length; k2++) {
						ieLIsx[k2].className=ieLIsx[k2].className.replace(' iefocus', '');
					}
					this.className+=" iefocus";
				}
			}
		}

	}//first for ends here
	
	
	}}
if (window.attachEvent) window.attachEvent('onload', ieFocus);
/** end **/

function billPayOpenNewWindow(valBillPay)
	{
		paymentwindow = window.open(valBillPay,'','screenX=0,screenY=0,left=0,top=0,width=800,height=550,resizable=yes,scrollbars=yes,toolbar=no,locationbar=no,personalbar=no,menubar=no');
		paymentwindow.focus();
	}