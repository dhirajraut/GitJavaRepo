  // JavaScript Document

  // ENTRY Form - Functions ---------------------------------------------------------------
  /*function hideTable() {
    alert("Closing the menu!")
    document.getElementById("menu").style.visibility = "hidden";
    document.getElementById("content").style.position = "fixed";
    document.getElementById("content").style.left = "10px";

  }*/

 
  function showContract1() {
    //document.getElementById("contractdetails").style.visibility = "visible";
    //document.getElementById("contract1").style.visibility = "visible";
    document.getElementById("popup2").style.visibility = "hidden";
    //document.getElementById("contractdetails").style.display = "block";
    //document.getElementById("contract1").style.display = "block";
    showIt();
  }

  /*function showContract1() {
    document.getElementById("contractdetails").style.visibility = "visible";
    document.getElementById("contract1").style.visibility = "visible";
    document.getElementById("popup2").style.visibility = "hidden";
    document.getElementById("contractdetails").style.display = "block";
    document.getElementById("contract1").style.display = "block";
    showIt();
  }*/



  function showContract2() {
    document.getElementById("contractdetails").style.visibility = "visible";
    document.getElementById("contract2").style.visibility = "visible";
    document.getElementById("popup2").style.visibility = "hidden";
    document.getElementById("contractdetails").style.display = "block";
    document.getElementById("contract2").style.display = "block";
    showIt();
    
  }
  /*function delContract1() {
    
    if (confirm("Are you sure you want to delete the row?")==true)
    { document.getElementById("contract1").style.visibility = "hidden";
    document.getElementById("contract1").style.display = "none";
    }*/
  function delContract(contract) {
    if (confirm("Are you sure you want to delete the row?")==true)
    { document.getElementById(contract).style.visibility = "hidden";
    document.getElementById(contract).style.display = "none";
    }
    
    }
  function hideAddNote1() {
    document.getElementById("addnote").style.visibility = "hidden";
    }
  function hideAddNote(div1) {
    document.getElementById(div1).style.visibility = "hidden";
    }
    
  function hideAttach() {
    document.getElementById("attach").style.visibility = "hidden";
    document.getElementById("addnote").style.visibility = "visible";
    
    }
  /*function hideAttach2() {
    document.getElementById("attach").style.visibility = "hidden";
    }*/
  function hideAttachFile(div1) {
    document.getElementById(div1).style.visibility = "hidden";
    showIt();
    }
    //Shipping Agent
  function showFrameShipAgent() {
    document.getElementById("shpagframe").style.display = "block";
    }
    
  function hideShipAgent() {
    document.getElementById("shipagent").style.visibility = "hidden";
    document.getElementById("searchresultsshipag").style.visibility = "hidden";
    document.getElementById("searchresultsshipag").style.display = "none";
    document.getElementById("shipagentbody").style.height = "280";
    document.getElementById("shpagframe").style.display = "none";
    showIt();
    }
  function hideShipAgent2() {
    document.getElementById("shipagent").style.visibility = "hidden";
    document.getElementById("frame1").style.display = "block";
    document.getElementById("searchresultsshipag").style.visibility = "hidden";
    showIt();
    }
    
  function hideShipAgentCreate() {
    //alert("HELLO");
    document.getElementById("shipagentcreate").style.visibility = "hidden";
    document.getElementById("frame1").style.display = "none";
    document.getElementById("shpagframe").style.display = "none";
    document.getElementById("searchresultsshipag").style.visibility = "hidden";
    document.getElementById("shipagentbody").style.height = "180";
    //showIt();
    }

    function searchshipag(){
    document.getElementById("searchresultsshipag").style.visibility = "visible";
    document.getElementById("searchresultsshipag").style.display = "block";
    document.getElementById("shipagentbody").style.height = "440";
    }
    
    function searchShipaght(){
    document.getElementById("searchresultsshipag").style.visibility = "hidden";
    document.getElementById("searchresultsshipag").style.display = "none";
    document.getElementById("shipagentbody").style.height = "180";  
    }

    //Towing Company ----->
  function showFrameTowingCo() {
    document.getElementById("frame2").style.display = "block";
    }

  function hideTowingCo() {
    document.getElementById("towingco").style.visibility = "hidden";
    document.getElementById("frame2").style.display = "none";
    
    document.getElementById("searchresultstowing").style.visibility = "hidden";
    document.getElementById("searchresultstowing").style.display = "none";
    document.getElementById("towingbody").style.height = "180";
    showIt();
    }
    function hideTowingCompany() {
    confirm("hiding towing compant");
    document.getElementById("towingco").style.visibility = "hidden";
    document.getElementById("towingco_drag").style.visibility = "hidden";
    document.getElementById("towingbody").style.visibility = "hidden";
    
    
    }
  function hideTowingCo2() {
    document.getElementById("towingco").style.visibility = "hidden";
    document.getElementById("frame2").style.display = "block";
    showIt();
    }

  function showFrameTowingCoCreate() {
    document.getElementById("frame3").style.display = "block";
    } 
    
  function hideTowingCoCreate() {
    //alert("HELLO");
    document.getElementById("towingcocreate").style.visibility = "hidden";
    document.getElementById("frame3").style.display = "none";
    document.getElementById("frame2").style.display = "none";
    //showIt();
    }
    
    function searchtowing(){
    document.getElementById("searchresultstowing").style.visibility = "visible";
    document.getElementById("searchresultstowing").style.display = "block";
    document.getElementById("towingbody").style.height = "440";
    }
    function searchTowinght(){
    document.getElementById("searchresultstowing").style.visibility = "hidden";
    document.getElementById("searchresultstowing").style.display = "none";
    document.getElementById("towingbody").style.height = "180";
    }
    
    
    //Billing Contact



    //Billing Contact ----->
  function showFramebillilngcontact() {
    document.getElementById("billingcontactframe").style.display = "block";
    }

  function hidebillilngcontact() {
    document.getElementById("billilngcontact").style.visibility = "hidden";
    document.getElementById("billingcontactframe").style.display = "none";
    
    document.getElementById("searchresultsbillilngcontact").style.visibility = "hidden";
    document.getElementById("searchresultsbillilngcontact").style.display = "none";
    document.getElementById("billilngcontactbody").style.height = "180";
    showIt();
    }
  /* function hideTowingCo2() {
    document.getElementById("billilngcontact").style.visibility = "hidden";
    document.getElementById("frame2").style.display = "block";
    showIt();
    }

  function showFrameTowingCoCreate() {
    document.getElementById("frame3").style.display = "block";
    } 
    
  function hideTowingCoCreate() {
    //alert("HELLO");
    document.getElementById("towingcocreate").style.visibility = "hidden";
    document.getElementById("frame3").style.display = "none";
    document.getElementById("frame2").style.display = "none";
    //showIt();
    } */
    
    function searchbillilngcontact(){
    document.getElementById("searchresultsbillilngcontact").style.visibility = "visible";
    document.getElementById("searchresultsbillilngcontact").style.display = "block";
    document.getElementById("billilngcontactbody").style.height = "440";
    }
    function searchsearchTowinghtht(){
    document.getElementById("searchresultsbillilngcontact").style.visibility = "hidden";
    document.getElementById("searchresultsbillilngcontact").style.display = "none";
    document.getElementById("billilngcontactbody").style.height = "180";
    }
    
    
    //Billing Contact
    
    
    //Service Location ----->
  function showFrameServLoc() {
    document.getElementById("frame4").style.display = "block";
    }

  function hideServLoc() {
    document.getElementById("servloc").style.visibility = "hidden";
    document.getElementById("frame4").style.display = "none";
    
    document.getElementById("searchresultsservloc").style.visibility = "hidden";
    document.getElementById("searchresultsservloc").style.display = "none";
    document.getElementById("servlocbody").style.height = "180";
    showIt();
    }
  function hideServLoc2() {
    document.getElementById("servloc").style.visibility = "hidden";
    document.getElementById("frame4").style.display = "block";
    showIt();
    }

  function showFrameServLocCreate() {
    document.getElementById("frame5").style.display = "block";
    } 
    
  function hideServLocCreate() {
    //alert("HELLO");
    document.getElementById("servloccreate").style.visibility = "hidden";
    document.getElementById("frame5").style.display = "none";
    document.getElementById("frame4").style.display = "none";
    //showIt();
    }
    function searchservloc(){
    document.getElementById("searchresultsservloc").style.visibility = "visible";
    document.getElementById("searchresultsservloc").style.display = "block";
    document.getElementById("servlocbody").style.height = "440";
    }
    function searchTowinght(){
    document.getElementById("searchresultsservloc").style.visibility = "hidden";
    document.getElementById("searchresultsservloc").style.display = "none";
    document.getElementById("servlocbody").style.height = "180";
    }
    
    
    
    //Service Location



  /*function showOriginTable(){
    document.getElementById("origintable").style.visibility = "visible"; 
    document.getElementById("origintable").style.display = "block"; 
    document.getElementById("bluarrowdown").style.visibility = "visible"; 
    document.getElementById("bluarrowright").style.visibility = "hidden";
  }*/

  function showOriginTable(div1,div2,div3,contractCount){
    document.getElementById(div1).style.visibility = "visible"; 
    document.getElementById(div1).style.display = "block"; 
    document.getElementById(div2).style.visibility = "visible"; 
    document.getElementById(div3).style.visibility = "hidden";

  var tmpName = "addJobContracts[" + contractCount + "].displayStatus";
    var tmpElement = document.getElementById(tmpName);
    if(tmpElement != null)
    {
      tmpElement.value = 'SHOW';
    }    

  }
  /*function hideOriginTable(){
    document.getElementById("origintable").style.visibility = "hidden";
    document.getElementById("origintable").style.display = "none"; 
    document.getElementById("bluarrowright").style.visibility = "visible";
    document.getElementById("bluarrowdown").style.visibility = "hidden";
  }*/

  function hideOriginTable(div1,div2,div3,contractCount){
    document.getElementById(div1).style.visibility = "hidden";
    document.getElementById(div1).style.display = "none"; 
    document.getElementById(div2).style.visibility = "visible";
    document.getElementById(div3).style.visibility = "hidden";

  var tmpName = "addJobContracts[" + contractCount + "].displayStatus";
    var tmpElement = document.getElementById(tmpName);
    if(tmpElement != null)
    {
      tmpElement.value = 'HIDE';
    }    

  }

  function showOriginTable2(){
    document.getElementById("origintable2").style.visibility = "visible"; 
    document.getElementById("origintable2").style.display = "block"; 
    document.getElementById("bluarrowdownot2").style.visibility = "visible"; 
    document.getElementById("bluarrowrightot2").style.visibility = "hidden";
  }
  function hideOriginTable2(){
    document.getElementById("origintable2").style.visibility = "hidden";
    document.getElementById("origintable2").style.display = "none"; 
    document.getElementById("bluarrowrightot2").style.visibility = "visible";
    document.getElementById("bluarrowdownot2").style.visibility = "hidden";
  }
  // ---------------------------------------------------------------------------

  // Vessel Functions

  function copyproduct() {
    alert("Product Copied.");
    }
    


  function copysample() {
    alert("Sample Location Copied.");
    }



  function showFrmCopyProd(){
    document.getElementById("frmcopyprod01").style.display = "block";
    
  }

  function hidecopyprod() {
    //alert("HELLO");
    document.getElementById("copyprod").style.visibility = "hidden";
    document.getElementById("frmcopyprod01").style.display = "none";
  //  document.getElementById("frame4").style.display = "none";
    showIt();
    }

  function copyprod01(){
    document.getElementById("v1productTable2").style.visibility = "visible";
    document.getElementById("v1productTable2").style.display = "block";
  }

  // Vessel 1 Functions
  function showvessel1Table(){
    document.getElementById("vessel01Container").style.visibility = "visible"; 
    document.getElementById("vessel01Container").style.display = "block"; 
    document.getElementById("bluarrowdownVessel").style.visibility = "visible"; 
    document.getElementById("bluarrowrightVessel").style.visibility = "hidden";
  }

  function showvesselTable(rowIndex){
  
    document.getElementById("vessel"+rowIndex+"Container").style.visibility = "visible"; 
    document.getElementById("vessel"+rowIndex+"Container").style.display = "block"; 
    document.getElementById("bluarrowdownv"+rowIndex).style.visibility = "visible"; 
    document.getElementById("bluarrowrightv"+rowIndex).style.visibility = "hidden";

  var tmpName = "addJobVessels[" + rowIndex + "].displayStatus";
    var tmpElement = document.getElementById(tmpName);
    if(tmpElement != null)
    {
      tmpElement.value = 'SHOW';
    }    
  }

  function hidevessel1Table(){
    document.getElementById("vessel01Container").style.visibility = "hidden";
    document.getElementById("vessel01Container").style.display = "none"; 
    document.getElementById("bluarrowrightVessel").style.visibility = "visible";
    document.getElementById("bluarrowdownVessel").style.visibility = "hidden";
  }

  function hidevesselTable(rowIndex){
    document.getElementById("vessel"+rowIndex+"Container").style.visibility = "hidden";
    document.getElementById("vessel"+rowIndex+"Container").style.display = "none"; 
    document.getElementById("bluarrowrightv"+rowIndex).style.visibility = "visible";
    document.getElementById("bluarrowdownv"+rowIndex).style.visibility = "hidden";

    var tmpName = "addJobVessels[" + rowIndex + "].displayStatus";
    var tmpElement = document.getElementById(tmpName);
    if(tmpElement != null)
    {
      tmpElement.value = 'HIDE';
    }
  }

  function showv1product1Table(){
    document.getElementById("quantityContainerv1p1").style.visibility = "visible"; 
    document.getElementById("quantityContainerv1p1").style.display = "block"; 

    document.getElementById("inspectionContainerv1p1").style.visibility = "visible"; 
    document.getElementById("inspectionContainerv1p1").style.display = "block"; 

    document.getElementById("samplelocContainerv1p1").style.visibility = "visible"; 
    document.getElementById("samplelocContainerv1p1").style.display = "block"; 

    document.getElementById("bluarrowdownv1p1").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv1p1").style.visibility = "hidden";
  }

  function showvproductTable(vesselCount,productCount,jobType){
  
    if(jobType=="INSP" || jobType=="MAR")
    {
    document.getElementById("quantityContainerv"+vesselCount+"p"+productCount).style.visibility = "visible"; 
    document.getElementById("quantityContainerv"+vesselCount+"p"+productCount).style.display = "block"; 

    document.getElementById("quantitytablev"+vesselCount+"p"+productCount).style.visibility = "visible"; 
    document.getElementById("quantitytablev"+vesselCount+"p"+productCount).style.display = "block"; 

    }

    document.getElementById("inspectionContainerv"+vesselCount+"p"+productCount).style.visibility = "visible"; 
    document.getElementById("inspectionContainerv"+vesselCount+"p"+productCount).style.display = "block"; 

    document.getElementById("samplelocContainerv"+vesselCount+"p"+productCount).style.visibility = "visible"; 
    document.getElementById("samplelocContainerv"+vesselCount+"p"+productCount).style.display = "block"; 
    
    document.getElementById("productTablev"+vesselCount).style.visibility = "visible"; 
 document.getElementById("productTablev"+vesselCount).style.display = "block";   

     document.getElementById("product"+productCount+"Container").style.visibility = "visible"; 
    document.getElementById("product"+productCount+"Container").style.display = "block";  


    document.getElementById("bluarrowdownv"+vesselCount+"p"+productCount).style.visibility = "visible"; 
    document.getElementById("bluarrowrightv"+vesselCount+"p"+productCount).style.visibility = "hidden";

  var tmpName = "addJobVessels[" + vesselCount + "].addJobProds[" + productCount + "].displayStatus";
    var tmpElement = document.getElementById(tmpName);
    if(tmpElement != null)
    {
      tmpElement.value = 'SHOW';
    }    
    
  }

  function hidev1product1Table(){
    document.getElementById("quantityContainerv1p1").style.visibility = "hidden";
    document.getElementById("quantityContainerv1p1").style.display = "none"; 
    
    document.getElementById("inspectionContainerv1p1").style.visibility = "hidden";
    document.getElementById("inspectionContainerv1p1").style.display = "none";
    
    document.getElementById("samplelocContainerv1p1").style.visibility = "hidden";
    document.getElementById("samplelocContainerv1p1").style.display = "none";
    
    document.getElementById("bluarrowrightv1p1").style.visibility = "visible";
    document.getElementById("bluarrowdownv1p1").style.visibility = "hidden";
  }

  function hidevproductTable(vesselCount,productCount,jobType){
  if(jobType=="INSP" || jobType=="MAR")
    {
    document.getElementById("quantityContainerv"+vesselCount+"p"+productCount).style.visibility = "hidden";
    document.getElementById("quantityContainerv"+vesselCount+"p"+productCount).style.display = "none"; 

   document.getElementById("quantitytablev"+vesselCount+"p"+productCount).style.visibility = "hidden"; 
    document.getElementById("quantitytablev"+vesselCount+"p"+productCount).style.display = "none"; 

    }
    document.getElementById("inspectionContainerv"+vesselCount+"p"+productCount).style.visibility = "hidden";
    document.getElementById("inspectionContainerv"+vesselCount+"p"+productCount).style.display = "none";
    
    document.getElementById("samplelocContainerv"+vesselCount+"p"+productCount).style.visibility = "hidden";
    document.getElementById("samplelocContainerv"+vesselCount+"p"+productCount).style.display = "none";
    
   // document.getElementById("productTablev"+vesselCount).style.visibility = "hidden"; 
  //  document.getElementById("productTablev"+vesselCount).style.display = "none";  

    document.getElementById("product"+productCount+"Container").style.visibility = "hidden"; 
    document.getElementById("product"+productCount+"Container").style.display = "none";  

    
    document.getElementById("bluarrowrightv"+vesselCount+"p"+productCount).style.visibility = "visible";
    document.getElementById("bluarrowdownv"+vesselCount+"p"+productCount).style.visibility = "hidden";

//  document.getElementById("vessel"+vesselCount+"Container").style.visibility = "visible"; 
 //   document.getElementById("vessel"+vesselCount+"Container").style.display = "block"; 
//    document.getElementById("bluarrowdownv"+vesselCount).style.visibility = "visible"; 
//    document.getElementById("bluarrowrightv"+vesselCount).style.visibility = "hidden";


  var tmpName = "addJobVessels[" + vesselCount + "].addJobProds[" + productCount + "].displayStatus";
    var tmpElement = document.getElementById(tmpName);
    if(tmpElement != null)
    {
      tmpElement.value = 'HIDE';
    }    
  }

  function showv1lotTable(){
    //document.getElementById("quantityContainerv1p1").style.visibility = "visible"; 
    //document.getElementById("quantityContainerv1p1").style.display = "block"; 

    //document.getElementById("inspectionContainerv1p1").style.visibility = "visible"; 
    //document.getElementById("inspectionContainerv1p1").style.display = "block"; 

    document.getElementById("samplelocContainerv1p1").style.visibility = "visible"; 
    document.getElementById("samplelocContainerv1p1").style.display = "block"; 

    document.getElementById("bluarrowdownv1p1").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv1p1").style.visibility = "hidden";
  }

  function hidev1lotTable(){
    //document.getElementById("quantityContainerv1p1").style.visibility = "hidden";
    //document.getElementById("quantityContainerv1p1").style.display = "none"; 
    
    //document.getElementById("inspectionContainerv1p1").style.visibility = "hidden";
    //document.getElementById("inspectionContainerv1p1").style.display = "none";
    
    document.getElementById("samplelocContainerv1p1").style.visibility = "hidden";
    document.getElementById("samplelocContainerv1p1").style.display = "none";
    
    document.getElementById("bluarrowrightv1p1").style.visibility = "visible";
    document.getElementById("bluarrowdownv1p1").style.visibility = "hidden";
  }

  function showv1lot2Table(){
    //document.getElementById("quantityContainerv1p1").style.visibility = "visible"; 
    //document.getElementById("quantityContainerv1p1").style.display = "block"; 

    //document.getElementById("inspectionContainerv1p1").style.visibility = "visible"; 
    //document.getElementById("inspectionContainerv1p1").style.display = "block"; 

    document.getElementById("samplelocContainerv1p2").style.visibility = "visible"; 
    document.getElementById("samplelocContainerv1p2").style.display = "block"; 

    document.getElementById("bluarrowdownv1p2").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv1p2").style.visibility = "hidden";
  }

  function hidev1lot2Table(){
    //document.getElementById("quantityContainerv1p1").style.visibility = "hidden";
    //document.getElementById("quantityContainerv1p1").style.display = "none"; 
    
    //document.getElementById("inspectionContainerv1p1").style.visibility = "hidden";
    //document.getElementById("inspectionContainerv1p1").style.display = "none";
    
    document.getElementById("samplelocContainerv1p2").style.visibility = "hidden";
    document.getElementById("samplelocContainerv1p2").style.display = "none";
    
    document.getElementById("bluarrowrightv1p2").style.visibility = "visible";
    document.getElementById("bluarrowdownv1p2").style.visibility = "hidden";
  }

  function showv2lotTable(){
    //document.getElementById("quantityContainerv1p1").style.visibility = "visible"; 
    //document.getElementById("quantityContainerv1p1").style.display = "block"; 

    //document.getElementById("inspectionContainerv1p1").style.visibility = "visible"; 
    //document.getElementById("inspectionContainerv1p1").style.display = "block"; 

    document.getElementById("samplelocContainerv2p2").style.visibility = "visible"; 
    document.getElementById("samplelocContainerv2p2").style.display = "block"; 

    document.getElementById("bluarrowdownv2p2").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv2p2").style.visibility = "hidden";
  }

  function hidev2lotTable(){
    //document.getElementById("quantityContainerv1p1").style.visibility = "hidden";
    //document.getElementById("quantityContainerv1p1").style.display = "none"; 
    
    //document.getElementById("inspectionContainerv1p1").style.visibility = "hidden";
    //document.getElementById("inspectionContainerv1p1").style.display = "none";
    
    document.getElementById("samplelocContainerv2p2").style.visibility = "hidden";
    document.getElementById("samplelocContainerv2p2").style.display = "none";
    
    document.getElementById("bluarrowrightv2p2").style.visibility = "visible";
    document.getElementById("bluarrowdownv2p2").style.visibility = "hidden";
  }

  function showv2lot2Table(){
    //document.getElementById("quantityContainerv1p1").style.visibility = "visible"; 
    //document.getElementById("quantityContainerv1p1").style.display = "block"; 

    //document.getElementById("inspectionContainerv1p1").style.visibility = "visible"; 
    //document.getElementById("inspectionContainerv1p1").style.display = "block"; 

    document.getElementById("samplelocContainerv2lot2").style.visibility = "visible"; 
    document.getElementById("samplelocContainerv2lot2").style.display = "block"; 

    document.getElementById("bluarrowdownv2lot2").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv2lot2").style.visibility = "hidden";
  }

  function hidev2lot2Table(){
    //document.getElementById("quantityContainerv1p1").style.visibility = "hidden";
    //document.getElementById("quantityContainerv1p1").style.display = "none"; 
    
    //document.getElementById("inspectionContainerv1p1").style.visibility = "hidden";
    //document.getElementById("inspectionContainerv1p1").style.display = "none";
    
    document.getElementById("samplelocContainerv2lot2").style.visibility = "hidden";
    document.getElementById("samplelocContainerv2lot2").style.display = "none";
    
    document.getElementById("bluarrowrightv2lot2").style.visibility = "visible";
    document.getElementById("bluarrowdownv2lot2").style.visibility = "hidden";
  }

  //select charges - tab 2

  function showTvlotTable(cIndex, vIndex, pIndex){
    document.getElementById("T" + cIndex + "samplelocContainerv" + vIndex + "p" + pIndex).style.visibility = "visible"; 
    document.getElementById("T" + cIndex + "samplelocContainerv" + vIndex + "p" + pIndex).style.display = "block"; 

    document.getElementById("T" + cIndex + "bluarrowdownv" + vIndex + "p" + pIndex).style.visibility = "visible"; 
    document.getElementById("T" + cIndex + "bluarrowrightv" + vIndex + "p" + pIndex).style.visibility = "hidden";
    
    var tmpName = "addJobContracts[" + cIndex + "].addJobContractVessels[" + vIndex + "].addJobContractProds[" + pIndex + "].displayStatus";
    var tmpElement = document.getElementById(tmpName);
    if(tmpElement != null)
    {
      tmpElement.value = 'SHOW';
    }    
  }

  function hideTvlotTable(cIndex, vIndex, pIndex){  
    document.getElementById("T" + cIndex + "samplelocContainerv" + vIndex + "p" + pIndex).style.visibility = "hidden";
    document.getElementById("T" + cIndex + "samplelocContainerv" + vIndex + "p" + pIndex).style.display = "none";
    
    document.getElementById("T" + cIndex + "bluarrowrightv" + vIndex + "p" + pIndex).style.visibility = "visible";
    document.getElementById("T" + cIndex + "bluarrowdownv" + vIndex + "p" + pIndex).style.visibility = "hidden";

    var tmpName = "addJobContracts[" + cIndex + "].addJobContractVessels[" + vIndex + "].addJobContractProds[" + pIndex + "].displayStatus";
    var tmpElement = document.getElementById(tmpName);
    if(tmpElement != null)
    {
      tmpElement.value = 'HIDE';
    }    
  }

  function showTV(cIndex, vIndex)
  {
    document.getElementById("T" + cIndex + "productTablev" + vIndex).style.visibility = "visible"; 
    document.getElementById("T" + cIndex + "productTablev" + vIndex).style.display = "block"; 
    document.getElementById("T" + cIndex + "bluarrowdownv" + vIndex).style.visibility = "visible"; 
    document.getElementById("T" + cIndex + "bluarrowrightv" + vIndex).style.visibility = "hidden";

    var tmpName = "addJobContracts[" + cIndex + "].addJobContractVessels[" + vIndex + "].displayStatus";
    var tmpElement = document.getElementById(tmpName);
    if(tmpElement != null)
    {
      tmpElement.value = 'SHOW';
    }
  }

  function hideTV(cIndex, vIndex)
  {
    document.getElementById("T" + cIndex + "productTablev" + vIndex).style.visibility = "hidden"; 
    document.getElementById("T" + cIndex + "productTablev" + vIndex).style.display = "none"; 
    document.getElementById("T" + cIndex + "bluarrowdownv" + vIndex).style.visibility = "hidden"; 
    document.getElementById("T" + cIndex + "bluarrowrightv" + vIndex).style.visibility = "visible";

    var tmpName = "addJobContracts[" + cIndex + "].addJobContractVessels[" + vIndex + "].displayStatus";
    var tmpElement = document.getElementById(tmpName);
    if(tmpElement != null)
    {
      tmpElement.value = 'HIDE';
    }
  }
    
    
    function showT2Ve2(){
    
    document.getElementById("T2productTablev2").style.visibility = "visible"; 
    document.getElementById("T2productTablev2").style.display = "block"; 
    document.getElementById("T2bluarrowdownv2").style.visibility = "visible"; 
    document.getElementById("T2bluarrowrightv2").style.visibility = "hidden";
    }
    function hideT2Ve2(){
    document.getElementById("T2productTablev2").style.visibility = "hidden"; 
    document.getElementById("T2productTablev2").style.display = "none"; 
    document.getElementById("T2bluarrowdownv2").style.visibility = "hidden"; 
    document.getElementById("T2bluarrowrightv2").style.visibility = "visible";
    }

  //select charges - tab 2



  function showv1p1quantity1Table(){
    document.getElementById("quantitytablev1p1").style.visibility = "visible"; 
    document.getElementById("quantitytablev1p1").style.display = "block"; 
    
    document.getElementById("bluarrowdownv1p1q1").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv1p1q1").style.visibility = "hidden";
  }
  function hidev1p1quantity1Table(){
    document.getElementById("quantitytablev1p1").style.visibility = "hidden";
    document.getElementById("quantitytablev1p1").style.display = "none"; 
    
    document.getElementById("bluarrowrightv1p1q1").style.visibility = "visible";
    document.getElementById("bluarrowdownv1p1q1").style.visibility = "hidden";
  }

  function showvpquantityTable(vesselCount,productCount){
    document.getElementById("quantitytablev"+vesselCount+"p"+productCount).style.visibility = "visible"; 
    document.getElementById("quantitytablev"+vesselCount+"p"+productCount).style.display = "block"; 
    
    document.getElementById("bluarrowdownv"+vesselCount+"p"+productCount+"q1").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv"+vesselCount+"p"+productCount+"q1").style.visibility = "hidden";
  }
  function hidevpquantityTable(vesselCount,productCount){
    document.getElementById("quantitytablev"+vesselCount+"p"+productCount).style.visibility = "hidden";
    document.getElementById("quantitytablev"+vesselCount+"p"+productCount).style.display = "none"; 
    
    document.getElementById("bluarrowrightv"+vesselCount+"p"+productCount+"q1").style.visibility = "visible";
    document.getElementById("bluarrowdownv"+vesselCount+"p"+productCount+"q1").style.visibility = "hidden";
  }

  function showv1p1inspection1Table(){
    document.getElementById("inspectiontablev1p1").style.visibility = "visible"; 
    document.getElementById("inspectiontablev1p1").style.display = "block"; 
    
    document.getElementById("bluarrowdownv1p1in1").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv1p1in1").style.visibility = "hidden";
  }
  function hidev1p1inspection1Table(){
    document.getElementById("inspectiontablev1p1").style.visibility = "hidden";
    document.getElementById("inspectiontablev1p1").style.display = "none"; 
    
    document.getElementById("bluarrowrightv1p1in1").style.visibility = "visible";
    document.getElementById("bluarrowdownv1p1in1").style.visibility = "hidden";
  }

  function showvpinspectionTable(vesselCount,productCount){
  
    document.getElementById("inspectiontablev"+vesselCount+"p"+productCount).style.visibility = "visible"; 
    document.getElementById("inspectiontablev"+vesselCount+"p"+productCount).style.display = "block"; 
    
    document.getElementById("bluarrowdownv"+vesselCount+"p"+productCount+"in1").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv"+vesselCount+"p"+productCount+"in1").style.visibility = "hidden";
    
  }
  function hidevpinspectionTable(vesselCount,productCount){
  
    document.getElementById("inspectiontablev"+vesselCount+"p"+productCount).style.visibility = "hidden";
    document.getElementById("inspectiontablev"+vesselCount+"p"+productCount).style.display = "none"; 
    
    document.getElementById("bluarrowrightv"+vesselCount+"p"+productCount+"in1").style.visibility = "visible";
    document.getElementById("bluarrowdownv"+vesselCount+"p"+productCount+"in1").style.visibility = "hidden";
   
  }

  function showv1p1sample1Table(){
    document.getElementById("sampleloctablev1p1").style.visibility = "visible"; 
    document.getElementById("sampleloctablev1p1").style.display = "block"; 
    
    document.getElementById("bluarrowdownv1p1s1").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv1p1s1").style.visibility = "hidden";
  }
  function hidev1p1sample1Table(){
    document.getElementById("sampleloctablev1p1").style.visibility = "hidden";
    document.getElementById("sampleloctablev1p1").style.display = "none"; 
    
    document.getElementById("bluarrowrightv1p1s1").style.visibility = "visible";
    document.getElementById("bluarrowdownv1p1s1").style.visibility = "hidden";
  }

  function showvpsampleTable(vesselCount,productCount,sampleSize){

    for(j=0;j<sampleSize;j++) {
    
    document.getElementById("sampleloctablev"+vesselCount+"p"+productCount+"s"+j).style.visibility = "visible"; 
    
    document.getElementById("sampleloctablev"+vesselCount+"p"+productCount+"s"+j).style.display = "block"; 

    var tmpName = "addJobVessels[" + vesselCount + "].addJobProds[" + productCount + "].addJobProdSamples[" + j  + "].displayStatus";
    var tmpElement = document.getElementById(tmpName);
    if(tmpElement != null)
    {
      tmpElement.value = 'SHOW';
    } 

    
    
    }
    document.getElementById("bluarrowdownv"+vesselCount+"p"+productCount+"s1").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv"+vesselCount+"p"+productCount+"s1").style.visibility = "hidden";
  }
  function hidevpsampleTable(vesselCount,productCount, sampleSize){
    for(j=0;j<sampleSize;j++) {
    document.getElementById("sampleloctablev"+vesselCount+"p"+productCount+"s"+j).style.visibility = "hidden";
    document.getElementById("sampleloctablev"+vesselCount+"p"+productCount+"s"+j).style.display = "none"; 

    var tmpName = "addJobVessels[" + vesselCount + "].addJobProds[" + productCount + "].addJobProdSamples[" + j  + "].displayStatus";
    var tmpElement = document.getElementById(tmpName);
    if(tmpElement != null)
    {
      tmpElement.value = 'HIDE';
    }    

    }
    document.getElementById("bluarrowrightv"+vesselCount+"p"+productCount+"s1").style.visibility = "visible";
    document.getElementById("bluarrowdownv"+vesselCount+"p"+productCount+"s1").style.visibility = "hidden";
  }

  //Product 2
  function showv1product2(){
    document.getElementById("quantityContainerv1p2").style.visibility = "visible"; 
    document.getElementById("quantityContainerv1p2").style.display = "block"; 

    document.getElementById("inspectionContainerv1p2").style.visibility = "visible"; 
    document.getElementById("inspectionContainerv1p2").style.display = "block"; 

    document.getElementById("samplelocContainerv1p2").style.visibility = "visible"; 
    document.getElementById("samplelocContainerv1p2").style.display = "block"; 

    document.getElementById("bluarrowdownv1p2").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv1p2").style.visibility = "hidden";
  }
  function hidev1product2(){
    document.getElementById("quantityContainerv1p2").style.visibility = "hidden";
    document.getElementById("quantityContainerv1p2").style.display = "none"; 
    
    document.getElementById("inspectionContainerv1p2").style.visibility = "hidden";
    document.getElementById("inspectionContainerv1p2").style.display = "none";
    
    document.getElementById("samplelocContainerv1p2").style.visibility = "hidden";
    document.getElementById("samplelocContainerv1p2").style.display = "none";
    
    document.getElementById("bluarrowrightv1p2").style.visibility = "visible";
    document.getElementById("bluarrowdownv1p2").style.visibility = "hidden";
  }

  function showv1p2quantity1(){
    document.getElementById("quantityv1p2").style.visibility = "visible"; 
    document.getElementById("quantityv1p2").style.display = "block"; 
    
    document.getElementById("bluarrowdownv1p2q1").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv1p2q1").style.visibility = "hidden";
  }
  function hidev1p2quantity1(){
    document.getElementById("quantityv1p2").style.visibility = "hidden";
    document.getElementById("quantityv1p2").style.display = "none"; 
    
    document.getElementById("bluarrowrightv1p2q1").style.visibility = "visible";
    document.getElementById("bluarrowdownv1p2q1").style.visibility = "hidden";
  }


  function showv1p2inspection1(){
    document.getElementById("inspectionv1p2").style.visibility = "visible"; 
    document.getElementById("inspectionv1p2").style.display = "block"; 
    
    document.getElementById("bluarrowdownv1p2in1").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv1p2in1").style.visibility = "hidden";
  }
  function hidev1p2inspection1(){
    document.getElementById("inspectionv1p2").style.visibility = "hidden";
    document.getElementById("inspectionv1p2").style.display = "none"; 
    
    document.getElementById("bluarrowrightv1p2in1").style.visibility = "visible";
    document.getElementById("bluarrowdownv1p2in1").style.visibility = "hidden";
  }


  function showv1p2sample1(){
    document.getElementById("samplelocv1p2").style.visibility = "visible"; 
    document.getElementById("samplelocv1p2").style.display = "block"; 
    
    document.getElementById("bluarrowdownv1p2s1").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv1p2s1").style.visibility = "hidden";
  }
  function hidev1p2sample1(){
    document.getElementById("samplelocv1p2").style.visibility = "hidden";
    document.getElementById("samplelocv1p2").style.display = "none"; 
    
    document.getElementById("bluarrowrightv1p2s1").style.visibility = "visible";
    document.getElementById("bluarrowdownv1p2s1").style.visibility = "hidden";
  }



  //Product 2


  function expandAllv1(vesselIndex, productIndex,jobType) {

    for(j=0;j<productIndex;j++) {
   
    if(jobType=="INSP" || jobType=="MAR")
    {
    showvpquantityTable(vesselIndex,j);
    }
    showvpinspectionTable(vesselIndex,j);
    //showvpsampleTable(vesselIndex,j,5);
    showvproductTable(vesselIndex,j,jobType);
    }
    showvesselTable(vesselIndex);
    }
    
  function collapseAllv1(vesselIndex, productIndex, jobType) {

    for(j=0;j<productIndex;j++) {
    if(jobType=="INSP" || jobType=="MAR")
    {
    hidevpquantityTable(vesselIndex,j);
    }
    hidevpinspectionTable(vesselIndex,j);
    //hidevpsampleTable(vesselIndex,j,sampleCount);
    hidevproductTable(vesselIndex,j,jobType);
    
    }
    hidevesselTable(vesselIndex);
    } 

  //Product 2


  function SCexpandAllv(cIndex, vIndex, pSize) 
  {
    showTV(cIndex, vIndex);
    for(var pCount=0; pCount<pSize; pCount ++)
    {
    showTvlotTable(cIndex, vIndex, pCount);
    }
  }
    
  function SCcollapseAllv(cIndex, vIndex, pSize) 
  {
    hideTV(cIndex, vIndex);
    for(var pCount=0; pCount<pSize; pCount ++)
    {
    hideTvlotTable(cIndex, vIndex, pCount);
    } 
  }


  // Select Charges
    function showV1(){
    document.getElementById("productTablev1").style.visibility = "visible"; 
    document.getElementById("productTablev1").style.display = "block"; 
    document.getElementById("bluarrowdownv1").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv1").style.visibility = "hidden";
    }
    function hideV1(){
    document.getElementById("productTablev1").style.visibility = "hidden"; 
    document.getElementById("productTablev1").style.display = "none"; 
    document.getElementById("bluarrowdownv1").style.visibility = "hidden"; 
    document.getElementById("bluarrowrightv1").style.visibility = "visible";
    }
    
    
    function showVe2(){
    
    document.getElementById("productTablev2").style.visibility = "visible"; 
    document.getElementById("productTablev2").style.display = "block"; 
    document.getElementById("bluarrowdownv2").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv2").style.visibility = "hidden";
    }
    function hideVe2(){
    document.getElementById("productTablev2").style.visibility = "hidden"; 
    document.getElementById("productTablev2").style.display = "none"; 
    document.getElementById("bluarrowdownv2").style.visibility = "hidden"; 
    document.getElementById("bluarrowrightv2").style.visibility = "visible";
    }
  // Select Charges

  // For FST Instructions

    function fstexpandAllv1() {
    /*showv1p1quantity1Table();*/
    showv1p1inspection1Table();
    showv1p1sample1Table();
    showv1product1Table();
    
    /*showv1p2quantity1();*/
    showv1p2inspection1();
    showv1p2sample1();
    showv1product2();
    
    showvessel1Table();
    }
    
  function fstcollapseAllv1() {
    /*hidev1p1quantity1Table();*/
    hidev1p1inspection1Table();
    hidev1p1sample1Table();
    hidev1product1Table();
    
    /*hidev1p2quantity1();*/
    hidev1p2inspection1();
    hidev1p2sample1();
    hidev1product2();
    
    hidevessel1Table();
    }

    //vessel 2
    function fstexpandAllv2() {
    /*showv2p1quantity1Table();*/
    showv2p1inspection1Table();
    showv2p1sample1Table();
    showv2product1Table();
    showvessel2Table();
    }
    
    function fstcollapseAllv2() {
    /*hidev2p1quantity1Table();*/
    hidev2p1inspection1Table();
    hidev2p1sample1Table();
    hidev2product1Table();
    hidevessel2Table();
    }
    
    //vessel 2
    
    // vessel 3
    function fstexpandAllv3() {
    /*showquantity1Table();*/
    showinspection1Table();
    showsample1Table();
    showproduct1Table();
    showvessel3Table();
    }
    
    function fstcollapseAllv3() {
    /*hidequantity1Table();*/
    hideinspection1Table();
    hidesample1Table();
    hideproduct1Table();
    hidevessel3Table();
    }
    
    //vessel 3

  // For FST



  // Vessel 2 Functions
  function showvessel2Table(){
    document.getElementById("vessel02Container").style.visibility = "visible"; 
    document.getElementById("vessel02Container").style.display = "block"; 
    document.getElementById("bluarrowdownv2").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv2").style.visibility = "hidden";
  }
  function hidevessel2Table(){
    document.getElementById("vessel02Container").style.visibility = "hidden";
    document.getElementById("vessel02Container").style.display = "none"; 
    document.getElementById("bluarrowrightv2").style.visibility = "visible";
    document.getElementById("bluarrowdownv2").style.visibility = "hidden";
  }

  function showv2product1Table(){
    document.getElementById("quantityContainerv2p1").style.visibility = "visible"; 
    document.getElementById("quantityContainerv2p1").style.display = "block"; 

    document.getElementById("inspectionContainerv2p1").style.visibility = "visible"; 
    document.getElementById("inspectionContainerv2p1").style.display = "block"; 

    document.getElementById("samplelocContainerv2p1").style.visibility = "visible"; 
    document.getElementById("samplelocContainerv2p1").style.display = "block"; 

    document.getElementById("bluarrowdownv2p1").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv2p1").style.visibility = "hidden";
  }
  function hidev2product1Table(){
    document.getElementById("quantityContainerv2p1").style.visibility = "hidden";
    document.getElementById("quantityContainerv2p1").style.display = "none"; 
    
    document.getElementById("inspectionContainerv2p1").style.visibility = "hidden";
    document.getElementById("inspectionContainerv2p1").style.display = "none";
    
    document.getElementById("samplelocContainerv2p1").style.visibility = "hidden";
    document.getElementById("samplelocContainerv2p1").style.display = "none";
    
    document.getElementById("bluarrowrightv2p1").style.visibility = "visible";
    document.getElementById("bluarrowdownv2p1").style.visibility = "hidden";
  }
  function showv2p1quantity1Table(){
    document.getElementById("quantitytablev2p1").style.visibility = "visible"; 
    document.getElementById("quantitytablev2p1").style.display = "block"; 
    
    document.getElementById("bluarrowdownv2p1q1").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv2p1q1").style.visibility = "hidden";
  }
  function hidev2p1quantity1Table(){
    document.getElementById("quantitytablev2p1").style.visibility = "hidden";
    document.getElementById("quantitytablev2p1").style.display = "none"; 
    
    document.getElementById("bluarrowrightv2p1q1").style.visibility = "visible";
    document.getElementById("bluarrowdownv2p1q1").style.visibility = "hidden";
  }


  function showv2p1inspection1Table(){
    document.getElementById("inspectiontablev2p1").style.visibility = "visible"; 
    document.getElementById("inspectiontablev2p1").style.display = "block"; 
    
    document.getElementById("bluarrowdownv2p1in1").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv2p1in1").style.visibility = "hidden";
  }
  function hidev2p1inspection1Table(){
    document.getElementById("inspectiontablev2p1").style.visibility = "hidden";
    document.getElementById("inspectiontablev2p1").style.display = "none"; 
    
    document.getElementById("bluarrowrightv2p1in1").style.visibility = "visible";
    document.getElementById("bluarrowdownv2p1in1").style.visibility = "hidden";
  }


  function showv2p1sample1Table(){
    document.getElementById("sampleloctablev2p1").style.visibility = "visible"; 
    document.getElementById("sampleloctablev2p1").style.display = "block"; 
    
    document.getElementById("bluarrowdownv2p1s1").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv2p1s1").style.visibility = "hidden";
  }
  function hidev2p1sample1Table(){
    document.getElementById("sampleloctablev2p1").style.visibility = "hidden";
    document.getElementById("sampleloctablev2p1").style.display = "none"; 
    
    document.getElementById("bluarrowrightv2p1s1").style.visibility = "visible";
    document.getElementById("bluarrowdownv2p1s1").style.visibility = "hidden";
  }

  function expandAllv2() {
    showv2p1quantity1Table();
    showv2p1inspection1Table();
    showv2p1sample1Table();
    showv2product1Table();
    showvessel2Table();
    }
    
  function collapseAllv2() {
    hidev2p1quantity1Table();
    hidev2p1inspection1Table();
    hidev2p1sample1Table();
    hidev2product1Table();
    hidevessel2Table();
    }


  // Vessel 3 Functions 
  function showvessel3Table(){
    document.getElementById("vessel03Container").style.visibility = "visible"; 
    document.getElementById("vessel03Container").style.display = "block"; 
    document.getElementById("bluarrowdownv3").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv3").style.visibility = "hidden";
  }

  function hidevessel3Table(){
    document.getElementById("vessel03Container").style.visibility = "hidden";
    document.getElementById("vessel03Container").style.display = "none"; 
    document.getElementById("bluarrowrightv3").style.visibility = "visible";
    document.getElementById("bluarrowdownv3").style.visibility = "hidden";
  }

  function showproduct1Table(){
    document.getElementById("quantityContainer").style.visibility = "visible"; 
    document.getElementById("quantityContainer").style.display = "block"; 

    document.getElementById("inspectionContainer").style.visibility = "visible"; 
    document.getElementById("inspectionContainer").style.display = "block"; 

    document.getElementById("samplelocContainer").style.visibility = "visible"; 
    document.getElementById("samplelocContainer").style.display = "block"; 

    document.getElementById("bluarrowdownv3p1").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv3p1").style.visibility = "hidden";
  }
  function hideproduct1Table(){
    document.getElementById("quantityContainer").style.visibility = "hidden";
    document.getElementById("quantityContainer").style.display = "none"; 
    
    document.getElementById("inspectionContainer").style.visibility = "hidden";
    document.getElementById("inspectionContainer").style.display = "none";
    
    document.getElementById("samplelocContainer").style.visibility = "hidden";
    document.getElementById("samplelocContainer").style.display = "none";
    
    document.getElementById("bluarrowrightv3p1").style.visibility = "visible";
    document.getElementById("bluarrowdownv3p1").style.visibility = "hidden";
  }
  function showquantity1Table(){
    document.getElementById("quantitytable").style.visibility = "visible"; 
    document.getElementById("quantitytable").style.display = "block"; 
    
    document.getElementById("bluarrowdownv3p1q1").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv3p1q1").style.visibility = "hidden";
  }
  function hidequantity1Table(){
    document.getElementById("quantitytable").style.visibility = "hidden";
    document.getElementById("quantitytable").style.display = "none"; 
    
    document.getElementById("bluarrowrightv3p1q1").style.visibility = "visible";
    document.getElementById("bluarrowdownv3p1q1").style.visibility = "hidden";
  }


  function showinspection1Table(){
    document.getElementById("inspectiontable").style.visibility = "visible"; 
    document.getElementById("inspectiontable").style.display = "block"; 
    
    document.getElementById("bluarrowdownv3p1in1").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv3p1in1").style.visibility = "hidden";
  }
  function hideinspection1Table(){
    document.getElementById("inspectiontable").style.visibility = "hidden";
    document.getElementById("inspectiontable").style.display = "none"; 
    
    document.getElementById("bluarrowrightv3p1in1").style.visibility = "visible";
    document.getElementById("bluarrowdownv3p1in1").style.visibility = "hidden";
  }


  function showsample1Table(){
    document.getElementById("sampleloctable").style.visibility = "visible"; 
    document.getElementById("sampleloctable").style.display = "block"; 
    
    document.getElementById("bluarrowdownv3p1s1").style.visibility = "visible"; 
    document.getElementById("bluarrowrightv3p1s1").style.visibility = "hidden";
  }
  function hidesample1Table(){
    document.getElementById("sampleloctable").style.visibility = "hidden";
    document.getElementById("sampleloctable").style.display = "none"; 
    
    document.getElementById("bluarrowrightv3p1s1").style.visibility = "visible";
    document.getElementById("bluarrowdownv3p1s1").style.visibility = "hidden";
  }
  function expandAllv3() {
    showquantity1Table();
    showinspection1Table();
    showsample1Table();
    showproduct1Table();
    showvessel3Table();
    }
    
  function collapseAllv3() {
    hidequantity1Table();
    hideinspection1Table();
    hidesample1Table();
    hideproduct1Table();
    hidevessel3Table();
    }
    

  // --------------------------------------- //
  /*function hideTable() {
    //alert("Closing the menu!")
    document.getElementById("menu").style.visibility = "hidden";
    //document.getElementById("content").style.position = "fixed";
    document.getElementById("content").style.left = "10px";

  }
  function showTable() {
    //alert("Opening the menu!")
    document.getElementById("contractdetails").style.visibility = "visible";
    document.getElementById("popup2").style.visibility = "hidden";
    document.getElementById("contractdetails").style.display = "block";
    showIt();
    //document.getElementById("content").style.left = "120px";

  }
  function hideContractDetails() {
    
    if (confirm("Are you sure you want to delete the row?")==true)
    { document.getElementById("contractdetails").style.visibility = "hidden";
    document.getElementById("contractdetails").style.display = "none";
    }
    
    }
  function hideAddNote() {
    document.getElementById("addnote").style.visibility = "hidden";
    }
    
  function hideAttach() {
    document.getElementById("attach").style.visibility = "hidden";
    document.getElementById("addnote").style.visibility = "visible";
    
    }
  function hideAttach2() {
    document.getElementById("attach2").style.visibility = "hidden";
    }*/
    

  // Vessel Functions End



  // Vessel Instructions
  function showInstructions(){
    document.getElementById("instructionsContainer").style.visibility = "visible"; 
    document.getElementById("instructionsContainer").style.display = "block"; 
    document.getElementById("bluarrowdownins").style.visibility = "visible"; 
    document.getElementById("bluarrowrightins").style.visibility = "hidden";
    
    var tmpName = "instrDisplayFlag";
    var tmpElement = document.getElementById(tmpName);
    if(tmpElement != null)
    {
      tmpElement.value = 'true';
    }    

  }
  function hideInstructions(){
    document.getElementById("instructionsContainer").style.visibility = "hidden"; 
    document.getElementById("instructionsContainer").style.display = "none"; 
    document.getElementById("bluarrowrightins").style.visibility = "visible";
    document.getElementById("bluarrowdownins").style.visibility = "hidden";
    
    var tmpName = "instrDisplayFlag";
    var tmpElement = document.getElementById(tmpName);
    if(tmpElement != null)
    {
      tmpElement.value = 'false';
    }  

  }

  // Templates
  // ----------------------------------------------------------------------------
  function showTemplates(){
    document.getElementById("templatesContainer").style.visibility = "visible"; 
    document.getElementById("templatesContainer").style.display = "block"; 
    document.getElementById("bluarrowdowntmp").style.visibility = "visible"; 
    document.getElementById("bluarrowrighttmp").style.visibility = "hidden";

  }
  function hideTemplates(){
    document.getElementById("templatesContainer").style.visibility = "hidden"; 
    document.getElementById("templatesContainer").style.display = "none"; 
    document.getElementById("bluarrowrighttmp").style.visibility = "visible";
    document.getElementById("bluarrowdowntmp").style.visibility = "hidden";

  }
  // ----------------------------------------------------------------------------

  // ----------------------------------------------------------------------------

  function showdatetime(){
    document.getElementById("datetime").style.visibility = "visible";
  }

  function navenable(){
    document.getElementById("navbuttons").style.visibility = "visible";
  }

  function navdisable(){
    document.getElementById("navbuttons").style.visibility = "hidden";
  }


  //-----------------------------------------------------------------------------

  // invoice preview
  function hidebranchall() {
    document.getElementById("branchall").style.visibility = "hidden";
    document.getElementById("branchall").style.display = "block";
    }


  // invoice preview



  // Projects----------------------------------------------------------------------------

  function projType(obj) 
  {
    if (obj=="Automatic") {
    // document.getElementById("Sales").innerHTML="RESEARCH NAME"
     document.getElementById("automatic").style.visibility="visible";
     document.getElementById("automatic").style.display="block";
     //document.getElementById("Cost").innerHTML="COST NAME"
    }
    if (obj=="Manual") {
     //  document.getElementById("Sales").innerHTML="SALES NAME";
    document.getElementById("automatic").style.visibility="hidden";
    document.getElementById("automatic").style.display="none";
    //document.getElementById("Cost").innerHTML="COST NAME";
    }
  }

    function showsearchproj() {
    document.getElementById("sprojframe").style.display = "block";
    
    document.getElementById("searchresultsProjects").style.visibility="hidden";
    document.getElementById("searchresultsProjects").style.display = "none";
    document.getElementById("projectsbody").style.height = "180";
    }
    function hidesearchproj() {
    document.getElementById("searchproj").style.visibility="hidden";
    document.getElementById("sprojframe").style.display = "none";
    
    document.getElementById("searchresultsProjects").style.visibility="hidden";
    document.getElementById("searchresultsProjects").style.display = "none";
    document.getElementById("projectsbody").style.height = "180";
    showIt();
    }
    
    
    function searchProjects(){
    document.getElementById("searchresultsProjects").style.visibility="visible";
    document.getElementById("searchresultsProjects").style.display = "block";
    document.getElementById("projectsbody").style.height = "340";
    }
    
  //Projects----------------------------------------------------------------------------

  //Jobs----------------------------------------------------------------------------

  //FromJobId----------------------------------------------------------------------------
    function showsearchfromjobid() {
    document.getElementById("sfromjobidframe").style.display = "block";
    
    document.getElementById("searchresultsfromjobid").style.visibility="hidden";
    document.getElementById("searchresultsfromjobid").style.display = "none";
    document.getElementById("fromjobidbody").style.height = "180";
    }
    function hidesearchfromjobid() {
    document.getElementById("searchfromjobid").style.visibility="hidden";
    document.getElementById("sfromjobidframe").style.display = "none";
    
    document.getElementById("searchresultsfromjobid").style.visibility="hidden";
    document.getElementById("searchresultsfromjobid").style.display = "none";
    document.getElementById("fromjobidbody").style.height = "150";
    showIt();
    }
    
    
    function searchfromjobid(){
    document.getElementById("searchresultsfromjobid").style.visibility="visible";
    document.getElementById("searchresultsfromjobid").style.display = "block";
    document.getElementById("fromjobidbody").style.height = "275";
    }

  //ToJobId----------------------------------------------------------------------------


  //Billing Contact----------------------------------------------------------------------------
    function showsearchbillingcontact() {
    document.getElementById("sbillingcontactframe").style.display = "block";
    
    document.getElementById("searchresultsbillingcontact").style.visibility="hidden";
    document.getElementById("searchresultsbillingcontact").style.display = "none";
    document.getElementById("billingcontactbody").style.height = "180";
    }
    function hidesearchbillingcontact() {
    document.getElementById("searchbillingcontact").style.visibility="hidden";
    document.getElementById("sbillingcontactframe").style.display = "none";
    
    document.getElementById("searchresultsbillingcontact").style.visibility="hidden";
    document.getElementById("searchresultsbillingcontact").style.display = "none";
    document.getElementById("billingcontactbody").style.height = "150";
    showIt();
    }
    
    
    function searchbillingcontact(){
    document.getElementById("searchresultsbillingcontact").style.visibility="visible";
    document.getElementById("searchresultsbillingcontact").style.display = "block";
    document.getElementById("billingcontactbody").style.height = "275";
    }


  //Remit To----------------------------------------------------------------------------
    function showsearchremitto() {
    document.getElementById("sremittoframe").style.display = "block";
    
    document.getElementById("searchresultsremitto").style.visibility="hidden";
    document.getElementById("searchresultsremitto").style.display = "none";
    document.getElementById("remittobody").style.height = "180";
    }
    function hidesearchremitto() {
    document.getElementById("searchremitto").style.visibility="hidden";
    document.getElementById("sremittoframe").style.display = "none";
    
    document.getElementById("searchresultsremitto").style.visibility="hidden";
    document.getElementById("searchresultsremitto").style.display = "none";
    document.getElementById("remittobody").style.height = "150";
    showIt();
    }
    
    
    function searchremitto(){
    document.getElementById("searchresultsremitto").style.visibility="visible";
    document.getElementById("searchresultsremitto").style.display = "block";
    document.getElementById("remittobody").style.height = "275";
    }


  //Account Search----------------------------------------------------------------------------
    function showsearchaccount() {
    document.getElementById("saccountframe").style.display = "block";
    
    document.getElementById("searchresultsaccount").style.visibility="hidden";
    document.getElementById("searchresultsaccount").style.display = "none";
    document.getElementById("accountbody").style.height = "180";
    }
  /*  function hidesearchaccount() {
    document.getElementById("searchaccount").style.visibility="hidden";
    document.getElementById("saccountframe").style.display = "none";
    
    document.getElementById("searchresultsaccount").style.visibility="hidden";
    document.getElementById("searchresultsaccount").style.display = "none";
    document.getElementById("accountbody").style.height = "150";
    showIt();
    }*/
    
    
  function hideBank(divname1,divname2){
    
      document.getElementById(divname1).style.visibility = "hidden";
      document.getElementById(divname2).style.display = "none";
      showIt();
     }


    function searchaccount(){
    document.getElementById("searchresultsaccount").style.visibility="visible";
    document.getElementById("searchresultsaccount").style.display = "block";
    document.getElementById("accountbody").style.height = "275";
    }


  //Jobs----------------------------------------------------------------------------

  // Setup -------------------------------------------------------------------

  function showcountryframe() {
    document.getElementById("countryfr").style.display = "block";
    
  }
  function hidecountrylookup() {
    document.getElementById("country").style.visibility="hidden";
    document.getElementById("countryfr").style.display = "none";
    showIt();
  }


    //Branch----------
    function searchbranchcodeht(){
    document.getElementById("branchcodebody").style.height = "130"; 
    }
    
    function searchbranchcode() {
    document.getElementById("branchcodesearchresults").style.visibility="visible";
    document.getElementById("branchcodesearchresults").style.display = "block";
    document.getElementById("branchcodebody").style.height = "280";
    
    }

    function showbranchcode(div1,div2) {
    document.getElementById(div1).style.visibility="visible";
    document.getElementById(div2).style.display = "block";
    }
    
    function hidebranchcode(div1,div2) {


    document.getElementById(div1).style.visibility="hidden";
    
    //document.getElementById("branchcodesearchresults").style.visibility="hidden";
    //document.getElementById("branchcodesearchresults").style.display = "none";
    //document.getElementById("branchcodebody").style.height = "150";
    document.getElementById(div2).style.display = "none";
    showIt();

    }
    function hidebranchcodesearch() {
    document.getElementById("branchcodesearchresults").style.visibility="hidden";
    document.getElementById("branchcodesearchresults").style.display = "none";
    }
    
    // Logo name
    function hidelogolookup(){
    document.getElementById("logoname").style.visibility="hidden";
    showIt();
    }
    
    //logo name
    function hidePopupDiv(popupdiv1,popupdiv2) {


    document.getElementById(popupdiv1).style.visibility="hidden";
    
    //document.getElementById("branchcodesearchresults").style.visibility="hidden";
    //document.getElementById("branchcodesearchresults").style.display = "none";
    //document.getElementById("branchcodebody").style.height = "150";
    document.getElementById(popupdiv2).style.display = "none";
    showIt();

    }
    
    //---------- COmpany name
    function searchconameht(){
    document.getElementById("conamebody").style.height = "130"; 
    }
    
    function searchconame() {
    document.getElementById("conamesearchresults").style.visibility="visible";
    document.getElementById("conamesearchresults").style.display = "block";
    document.getElementById("conamebody").style.height = "220";
    
    }
    
    function hideconame() {
    document.getElementById("coname").style.visibility="hidden";

    
    document.getElementById("conamesearchresults").style.visibility="hidden";
    document.getElementById("conamesearchresults").style.display = "none";
    showIt();
    }
    function hideconamesearch() {
    document.getElementById("conamesearchresults").style.visibility="hidden";
    document.getElementById("conamesearchresults").style.display = "none";
    }
    //---------- COmpany name
    
    
    //Branch --------------

    
    // Business Unit
    function searchBUht(){
    document.getElementById("bubody").style.height = "300"; 
    }
    
    function searchBU() {
    document.getElementById("busearchresults").style.visibility="visible";
    document.getElementById("busearchresults").style.display = "block";
    document.getElementById("bubody").style.height = "280";
    
    }
    
    function hideBU() {
    document.getElementById("bu").style.visibility="hidden";

    
    document.getElementById("busearchresults").style.visibility="hidden";
    document.getElementById("busearchresults").style.display = "none";
    showIt();
    }
    function hideBUsearch() {
    document.getElementById("busearchresults").style.visibility="hidden";
    document.getElementById("busearchresults").style.display = "none";
    }
    
    
    //User


    function searchUserht(){
    document.getElementById("accountOwnerbody").style.height = "350"; 
    }
    
    function searchUser() {
    document.getElementById("usersearchresults").style.visibility="visible";
    document.getElementById("usersearchresults").style.display = "block";
    document.getElementById("accountOwnerbody").style.height = "280";
    
    }
    
    function hideUser() {
    document.getElementById("accountOwner").style.visibility="hidden";

    
    document.getElementById("usersearchresults").style.visibility="hidden";
    document.getElementById("usersearchresults").style.display = "none";
    showIt();
    }
    function hideUsersearch() {
    document.getElementById("usersearchresults").style.visibility="hidden";
    document.getElementById("usersearchresults").style.display = "none";
    }
    
    
    // User
    
    
    //User (Job Code)----------
    function searchjobcodeht(){
    document.getElementById("jobcodebody").style.height = "130";  
    }
    
    function showjobcode() {
    document.getElementById("jobcode").style.visibility="visible";
    document.getElementById("jobcodebody").style.display = "block";
    }
    function searchjobcode() {
    document.getElementById("jobcodesearchresults").style.visibility="visible";
    document.getElementById("jobcodesearchresults").style.display = "block";
    document.getElementById("jobcodebody").style.height = "280";
    
    }
    
    /*function hidejobcode() {
    document.getElementById("jobcode").style.visibility="hidden";

    
    document.getElementById("jobcodesearchresults").style.visibility="hidden";
    document.getElementById("jobcodesearchresults").style.display = "none";
    showIt();
    }*/

  function hidejobcode() {
    document.getElementById("jobcode").style.visibility="hidden";
    //document.getElementById("jobcodesearchresults").style.visibility="hidden";
    document.getElementById("jobcodebody").style.display = "none";
    showIt();
    }

    function hidejobcodesearch() {
    document.getElementById("jobcodesearchresults").style.visibility="hidden";
    document.getElementById("jobcodesearchresults").style.display = "none";
    }
      function showsupervisorid() {
    document.getElementById("supervisorid").style.visibility="visible";
    document.getElementById("supervisorbody").style.display = "block";
    }

  // Setup -------------------------------------------------------------------


  // Customer ------------------------------------------------------
    
    // parent customer
    function searchparentcustht(){
      document.getElementById("parentcustbody").style.height = "300"; 
    }
    
    function searchparentcust() {
      document.getElementById("parentcustsearchresults").style.visibility="visible";
      document.getElementById("parentcustsearchresults").style.display = "block";
      document.getElementById("parentcustbody").style.height = "210";
      
    }
    
    function hideparentcust() {
      document.getElementById("parentcust").style.visibility="hidden";
    
    
      document.getElementById("parentcustsearchresults").style.visibility="hidden";
      document.getElementById("parentcustsearchresults").style.display = "none";
      showIt();
    }
    function hideparentcustsearch() {
      document.getElementById("parentcustsearchresults").style.visibility="hidden";
      document.getElementById("parentcustsearchresults").style.display = "none";
    }
    // parent customer
    // credit analyst
    function searchcreditanht(){
      document.getElementById("creditanbody").style.height = "300"; 
    }
    
    function searchcreditan() {
      document.getElementById("creditansearchresults").style.visibility="visible";
      document.getElementById("creditansearchresults").style.display = "block";
      document.getElementById("creditanbody").style.height = "210";
      
    }
    
    function hidecreditan() {
      document.getElementById("creditan").style.visibility="hidden";
    
    
      document.getElementById("creditansearchresults").style.visibility="hidden";
      document.getElementById("creditansearchresults").style.display = "none";
      showIt();
    }
    function hidecreditansearch() {
      document.getElementById("creditansearchresults").style.visibility="hidden";
      document.getElementById("creditansearchresults").style.display = "none";
    }
    // credit analyst
    
    // collector
    function searchcollectorht(){
      document.getElementById("collectorbody").style.height = "300";  
    }
    
    function searchcollector() {
      document.getElementById("collectorsearchresults").style.visibility="visible";
      document.getElementById("collectorsearchresults").style.display = "block";
      document.getElementById("collectorbody").style.height = "210";
      
    }
    
    function hidecollector() {
      document.getElementById("collector").style.visibility="hidden";
    
    
      document.getElementById("collectorsearchresults").style.visibility="hidden";
      document.getElementById("collectorsearchresults").style.display = "none";
      showIt();
    }
    function hidecollectorsearch() {
      document.getElementById("collectorsearchresults").style.visibility="hidden";
      document.getElementById("collectorsearchresults").style.display = "none";
    }
    // collector
    // company namesearch

    
    function searchcomkpanynameht(){
      document.getElementById("companynamebody").style.height = "350";  
    }
    
    function searchcontact() {
      document.getElementById("companynamesearchresults").style.visibility="visible";
      document.getElementById("companynamesearchresults").style.display = "block";
      document.getElementById("companynamebody").style.height = "210";
      
    }
    
    function hidecontact() {
      document.getElementById("companyname").style.visibility="hidden";
    
    
      document.getElementById("companynamesearchresults").style.visibility="hidden";
      document.getElementById("companynamesearchresults").style.display = "none";
      showIt();
    }
    function hidecontactsearch() {
      document.getElementById("companynamesearchresults").style.visibility="hidden";
      document.getElementById("companynamesearchresults").style.display = "none";
    }
     
  // contact search
    function searchcontactht(){
      document.getElementById("contactbody").style.height = "350";  
    }
    
    function searchcontact() {
      document.getElementById("contactsearchresults").style.visibility="visible";
      document.getElementById("contactsearchresults").style.display = "block";
      document.getElementById("contactbody").style.height = "210";
      
    }
    
    function hidecontact() {
      document.getElementById("contact").style.visibility="hidden";
    
    
      document.getElementById("contactsearchresults").style.visibility="hidden";
      document.getElementById("contactsearchresults").style.display = "none";
      showIt();
    }
    function hidecontactsearch() {
      document.getElementById("contactsearchresults").style.visibility="hidden";
      document.getElementById("contactsearchresults").style.display = "none";
    }
    // contact search


    // contract search
    function searchcontractht(){
      document.getElementById("contractbody").style.height = "350"; 
    }
    
    function searchcontract() {
      document.getElementById("contractsearchresults").style.visibility="visible";
      document.getElementById("contractsearchresults").style.display = "block";
      document.getElementById("contractbody").style.height = "210";
      
    }
    
    function hidecontract() {
      document.getElementById("contract").style.visibility="hidden";
    
    
      document.getElementById("contractsearchresults").style.visibility="hidden";
      document.getElementById("contractsearchresults").style.display = "none";
      showIt();
    }
    function hidecontractsearch() {
      document.getElementById("contractsearchresults").style.visibility="hidden";
      document.getElementById("contractsearchresults").style.display = "none";
    }
    // contract search
    
    //new contact
    
    function hidenewcontact() {
      document.getElementById("newcontact").style.visibility="hidden";
      document.getElementById("newcontact").style.display = "none";
      
      
    }
    
    
    //new contact
    
    function hidenewcontract() {
      document.getElementById("newcontract").style.visibility="hidden";
      document.getElementById("newcontract").style.display = "none";
      
      
    }
    //vat code
    function hideVatcode(divName1,divName2){
      document.getElementById(divName1).style.visibility = "hidden";
      document.getElementById(divName2).style.display = "none";
      showIt();
     }
    
    
    //vat code
    

  //Customer ---------------------------------------------------

  // Contracts --------------

  function showcontract01(div1,div2,div3){
    //document.getElementById("contract01Container").style.visibility = "visible"; 
    //document.getElementById("contract01Container").style.display = "block"; 
    //document.getElementById("arrowdnc1").style.visibility = "visible"; 
    //document.getElementById("arrowrtc1").style.visibility = "hidden";

    document.getElementById(div1).style.visibility = "visible"; 
    document.getElementById(div1).style.display = "block"; 
    document.getElementById(div2).style.visibility = "visible"; 
    document.getElementById(div3).style.visibility = "hidden";
  }
  function hidecontract01(div1,div2,div3){
    //document.getElementById("contract01Container").style.visibility = "hidden"; 
    //document.getElementById("contract01Container").style.display = "none"; 
    //document.getElementById("arrowdnc1").style.visibility = "hidden"; 
    //document.getElementById("arrowrtc1").style.visibility = "visible";

    document.getElementById(div1).style.visibility = "hidden"; 
    document.getElementById(div1).style.display = "none"; 
    document.getElementById(div2).style.visibility = "hidden"; 
    document.getElementById(div3).style.visibility = "visible";
  }


  function showcontract02(){
    document.getElementById("contract02Container").style.visibility = "visible"; 
    document.getElementById("contract02Container").style.display = "block"; 
    document.getElementById("arrowdnc2").style.visibility = "visible"; 
    document.getElementById("arrowrtc2").style.visibility = "hidden";
  }
  function hidecontract02(){
    document.getElementById("contract02Container").style.visibility = "hidden"; 
    document.getElementById("contract02Container").style.display = "none"; 
    document.getElementById("arrowdnc2").style.visibility = "hidden"; 
    document.getElementById("arrowrtc2").style.visibility = "visible";
  }


  function showcontract03(){
    document.getElementById("contract03Container").style.visibility = "visible"; 
    document.getElementById("contract03Container").style.display = "block"; 
    document.getElementById("arrowdnc3").style.visibility = "visible"; 
    document.getElementById("arrowrtc3").style.visibility = "hidden";
  }
  function hidecontract03(){
    document.getElementById("contract03Container").style.visibility = "hidden"; 
    document.getElementById("contract03Container").style.display = "none"; 
    document.getElementById("arrowdnc3").style.visibility = "hidden"; 
    document.getElementById("arrowrtc3").style.visibility = "visible";
  }

  function showversion02(){
    document.getElementById("version02Container").style.visibility = "visible"; 
    document.getElementById("version02Container").style.display = "block"; 
    document.getElementById("adv2").style.visibility = "visible"; 
    document.getElementById("av2").style.visibility = "hidden";
  }
  function hideversion02(){
    document.getElementById("version02Container").style.visibility = "hidden"; 
    document.getElementById("version02Container").style.display = "none"; 
    document.getElementById("adv2").style.visibility = "hidden"; 
    document.getElementById("av2").style.visibility = "visible";
  }

  function showbegindatebyindex(index){
    document.getElementById("begindate" + index + "Container").style.visibility = "visible"; 
    document.getElementById("begindate" + index + "Container").style.display = "block"; 
    document.getElementById("arrowdnbd" + index + "").style.visibility = "visible"; 
    document.getElementById("arrowrtbd" + index + "").style.visibility = "hidden";
  }
  function hidebegindatebyindex(index){
    document.getElementById("begindate" + index + "Container").style.visibility = "hidden"; 
    document.getElementById("begindate" + index + "Container").style.display = "none"; 
    document.getElementById("arrowdnbd" + index).style.visibility = "hidden"; 
    document.getElementById("arrowrtbd" + index).style.visibility = "visible";
  }

  function showbegindate01(){
    document.getElementById("begindate01Container").style.visibility = "visible"; 
    document.getElementById("begindate01Container").style.display = "block"; 
    document.getElementById("arrowdnbd1").style.visibility = "visible"; 
    document.getElementById("arrowrtbd1").style.visibility = "hidden";
  }
  function hidebegindate01(){
    document.getElementById("begindate01Container").style.visibility = "hidden"; 
    document.getElementById("begindate01Container").style.display = "none"; 
    document.getElementById("arrowdnbd1").style.visibility = "hidden"; 
    document.getElementById("arrowrtbd1").style.visibility = "visible";
  }

  
  function contractExpandAll(myType, mySize)
  {
    if("zone" == myType)    
    {
      for(var ii=0; ii<mySize; ii++) showzoneWithIndex(ii);
    }
    else if("PriceBookTestPrice" == myType)
    {
      for(var ii=0; ii<mySize; ii++) showPriceBookTestPriceWithIndex(ii);
    }
    else if("ContractTestPrice" == myType)
    {
      for(var ii=0; ii<mySize; ii++) showContractTestPriceWithIndex(ii);
    }
    else if("PriceBookSlatePrice" == myType)
    {
      for(var ii=0; ii<mySize; ii++) showPriceBookSlatePriceWithIndex(ii);
    }
    else if("ContractSlatePrice" == myType)
    {
      for(var ii=0; ii<mySize; ii++) showContractSlatePriceWithIndex(ii);
    }
  }

  function contractCollapseAll(myType, mySize)
  {
    if("zone" == myType)    
    {
      for(var ii=0; ii<mySize; ii++) hidezoneWithIndex(ii);
    }
    else if("PriceBookTestPrice" == myType)
    {
      for(var ii=0; ii<mySize; ii++) hidePriceBookTestPriceWithIndex(ii);
    }
    else if("ContractTestPrice" == myType)
    {
      for(var ii=0; ii<mySize; ii++) hideContractTestPriceWithIndex(ii);
    }
    else if("PriceBookSlatePrice" == myType)
    {
      for(var ii=0; ii<mySize; ii++) hidePriceBookSlatePriceWithIndex(ii);
    }
    else if("ContractSlatePrice" == myType)
    {
      for(var ii=0; ii<mySize; ii++) hideContractSlatePriceWithIndex(ii);
    }
  }

  function showzoneWithIndex(index){
    document.getElementById("zone0" + index + "Container").style.visibility = "visible"; 
    document.getElementById("zone0" + index + "Container").style.display = "block"; 
    document.getElementById("arrowdnz" + index + "").style.visibility = "visible"; 
    document.getElementById("arrowrtz" + index + "").style.visibility = "hidden";
    
    var tmpName = "zoneExtList[" + index + "].displayStatus";
    var tmpElement = document.getElementById(tmpName);
    if(tmpElement != null)
    {
      tmpElement.value = 'SHOW';
    }    
  }
  
  function hidezoneWithIndex(index){
    document.getElementById("zone0" + index + "Container").style.visibility = "hidden"; 
    document.getElementById("zone0" + index + "Container").style.display = "none"; 
    document.getElementById("arrowdnz" + index + "").style.visibility = "hidden"; 
    document.getElementById("arrowrtz" + index + "").style.visibility = "visible";
    
    var tmpName = "zoneExtList[" + index + "].displayStatus";
    var tmpElement = document.getElementById(tmpName);
    if(tmpElement != null)
    {
      tmpElement.value = 'HIDE';
    }    
  }

  function showPriceBookTestPriceWithIndex(index){
    document.getElementById("priceBookTestPrice0" + index + "Container").style.visibility = "visible"; 
    document.getElementById("priceBookTestPrice0" + index + "Container").style.display = "block"; 
    document.getElementById("priceBookTestPricearrowdnz" + index + "").style.visibility = "visible"; 
    document.getElementById("priceBookTestPricearrowrtz" + index + "").style.visibility = "hidden";
  }
  function hidePriceBookTestPriceWithIndex(index){
    document.getElementById("priceBookTestPrice0" + index + "Container").style.visibility = "hidden"; 
    document.getElementById("priceBookTestPrice0" + index + "Container").style.display = "none"; 
    document.getElementById("priceBookTestPricearrowdnz" + index + "").style.visibility = "hidden"; 
    document.getElementById("priceBookTestPricearrowrtz" + index + "").style.visibility = "visible";
  }


  function showContractTestPriceWithIndex(index){
    document.getElementById("contractTestPrice0" + index + "Container").style.visibility = "visible"; 
    document.getElementById("contractTestPrice0" + index + "Container").style.display = "block"; 
    document.getElementById("contractTestPricearrowdnz" + index + "").style.visibility = "visible"; 
    document.getElementById("contractTestPricearrowrtz" + index + "").style.visibility = "hidden";
  }
  function hideContractTestPriceWithIndex(index){
    document.getElementById("contractTestPrice0" + index + "Container").style.visibility = "hidden"; 
    document.getElementById("contractTestPrice0" + index + "Container").style.display = "none"; 
    document.getElementById("contractTestPricearrowdnz" + index + "").style.visibility = "hidden"; 
    document.getElementById("contractTestPricearrowrtz" + index + "").style.visibility = "visible";
  }


  function showPriceBookSlatePriceWithIndex(index){
    document.getElementById("priceBookSlatePrice0" + index + "Container").style.visibility = "visible"; 
    document.getElementById("priceBookSlatePrice0" + index + "Container").style.display = "block"; 
    document.getElementById("priceBookSlatePricearrowdnz" + index + "").style.visibility = "visible"; 
    document.getElementById("priceBookSlatePricearrowrtz" + index + "").style.visibility = "hidden";
  }
  function hidePriceBookSlatePriceWithIndex(index){
    document.getElementById("priceBookSlatePrice0" + index + "Container").style.visibility = "hidden"; 
    document.getElementById("priceBookSlatePrice0" + index + "Container").style.display = "none"; 
    document.getElementById("priceBookSlatePricearrowdnz" + index + "").style.visibility = "hidden"; 
    document.getElementById("priceBookSlatePricearrowrtz" + index + "").style.visibility = "visible";
  }


  function showContractSlatePriceWithIndex(index){
    document.getElementById("contractSlatePrice0" + index + "Container").style.visibility = "visible"; 
    document.getElementById("contractSlatePrice0" + index + "Container").style.display = "block"; 
    document.getElementById("contractSlatePricearrowdnz" + index + "").style.visibility = "visible"; 
    document.getElementById("contractSlatePricearrowrtz" + index + "").style.visibility = "hidden";
  }
  function hideContractSlatePriceWithIndex(index){
    document.getElementById("contractSlatePrice0" + index + "Container").style.visibility = "hidden"; 
    document.getElementById("contractSlatePrice0" + index + "Container").style.display = "none"; 
    document.getElementById("contractSlatePricearrowdnz" + index + "").style.visibility = "hidden"; 
    document.getElementById("contractSlatePricearrowrtz" + index + "").style.visibility = "visible";
  }

  function showzone(){
    document.getElementById("zone01Container").style.visibility = "visible"; 
    document.getElementById("zone01Container").style.display = "block"; 
    document.getElementById("arrowdnz1").style.visibility = "visible"; 
    document.getElementById("arrowrtz1").style.visibility = "hidden";
  }
  function hidezone(){
    document.getElementById("zone01Container").style.visibility = "hidden"; 
    document.getElementById("zone01Container").style.display = "none"; 
    document.getElementById("arrowdnz1").style.visibility = "hidden"; 
    document.getElementById("arrowrtz1").style.visibility = "visible";
  }

  function showzone2(){
    document.getElementById("zone02Container").style.visibility = "visible"; 
    document.getElementById("zone02Container").style.display = "block"; 
    document.getElementById("arrowdnz2").style.visibility = "visible"; 
    document.getElementById("arrowrtz2").style.visibility = "hidden";
  }
  function hidezone2(){
    document.getElementById("zone02Container").style.visibility = "hidden"; 
    document.getElementById("zone02Container").style.display = "none"; 
    document.getElementById("arrowdnz2").style.visibility = "hidden"; 
    document.getElementById("arrowrtz2").style.visibility = "visible";
  }

  function showciWithIndex(myIndex){
    document.getElementById("ci0" + myIndex + "Container").style.visibility = "visible"; 
    document.getElementById("ci0" + myIndex + "Container").style.display = "block"; 
    document.getElementById("arrowdnci" + myIndex).style.visibility = "visible"; 
    document.getElementById("arrowrtci" + myIndex).style.visibility = "hidden";
  }
  function hideciWithIndex(myIndex){
    document.getElementById("ci0" + myIndex + "Container").style.visibility = "hidden"; 
    document.getElementById("ci0" + myIndex + "Container").style.display = "none"; 
    document.getElementById("arrowdnci" + myIndex).style.visibility = "hidden"; 
    document.getElementById("arrowrtci" + myIndex).style.visibility = "visible";
  }
    
    //attachments
    function hideattachpop() {
    document.getElementById("attachpop").style.visibility = "hidden";
    document.getElementById("attachpop").style.display = "none";
    
    //  document.getElementById("attachments").style.visibility = "visible";
    //  document.getElementById("attachments").style.display = "block";
    //  showIt();
    }
    
    function hideattachCan(){
    document.getElementById("attachpop").style.visibility = "hidden";
    document.getElementById("attachpop").style.display = "none";
    showIt();
    }
    
    
    
    //attachments
  function hidesupervisor() {
      document.getElementById("supervisorid").style.visibility="hidden";
       document.getElementById("supervisorbody").style.display = "none";
    showIt();

    }
    function showcurrency() {
    document.getElementById("currency").style.visibility="visible";
    document.getElementById("currencybody").style.display = "block";
    }

    function hidecurrency() {
    document.getElementById("currency").style.visibility="hidden";
    document.getElementById("currencybody").style.display = "none";
    showIt();
    }


    //new contact
    
    function hidenewcontact() {
      document.getElementById("newcontact").style.visibility="hidden";
      document.getElementById("newcontact").style.display = "none";
    }
    
    function showcontract() {
    document.getElementById("newcontract").style.visibility="visible";
    document.getElementById("newcontractbody").style.display = "block";
    }
    function hidecontract(divName1,divName2){
    
      document.getElementById(divName1).style.visibility = "hidden";
       document.getElementById(divName2).style.display = "none";
      
     }
     function showNominationTime() {
    document.getElementById("timezone").style.visibility="visible";
    document.getElementById("timezonebody").style.display = "block";
    }
    function showEtaTime() {
    document.getElementById("etatimezone").style.visibility="visible";
    document.getElementById("etatimezonebody").style.display = "block";
    }
    
    function showReceivedby() {
    document.getElementById("receivedby").style.visibility="visible";
    document.getElementById("receivedbybody").style.display = "block";
    }
    function showPopupDiv(popupdiv1,popupdiv2) {
    document.getElementById(popupdiv1).style.visibility="visible";
    document.getElementById(popupdiv2).style.display = "block";
    }
    function showShippingAgent() {
    document.getElementById("shippingagent").style.visibility="visible";
    document.getElementById("shippingagentbody").style.display = "block";
    }
    function showServiceLocation() {
    document.getElementById("servloc").style.visibility="visible";
    document.getElementById("servlocbody").style.display = "block";
    }
    
function resetTowingCo()
{
  var towname = document.getElementById("tco").value;
  var towname_array=towname.split(",");
  var newTowName ='';
  if(towname_array.length > 1)
  {
    for(var i=0;i<(towname_array.length-1);i++)
    {
    if(newTowName=='')
      newTowName = towname_array[i];
    else
      newTowName = newTowName + "," + towname_array[i];
    }
  }
  document.getElementById("tco").value = newTowName;
  
}

function resetShipAgent()
{
  var shipagent = document.getElementById("shipagnt").value;
  var shipagent_array=shipagent.split(",");
  var newShipAgent ='';
  if(shipagent_array.length > 1)
  {
    for(var i=0;i<(shipagent_array.length-1);i++)
    {
    if(newShipAgent=='')
      newShipAgent = shipagent_array[i];
    else
      newShipAgent = newShipAgent + "," + shipagent_array[i];
    }
  }
  document.getElementById("shipagnt").value = newShipAgent;
  
}
    
  // Contract -----------------


  function copyname(obj) {
  if (document.getElementById(obj).id=="selcountry") {
    for(i=0;i<document.getElementById("selcountry").options.length;i++) {
       if (document.getElementById("selcountry").options[i].selected==true){
     //      if (document.getElementById("selVersion").options[i].text=="20A APPRENTICEA") {
      //   document.getElementById("selname").options.length=0; 
           //    }
         if (document.getElementById("selcountry").options[i].text=="ABW - Aruba") {
        document.getElementById("selstate").options.length=0; 
         document.getElementById("selstate").options[0]=new Option("No State Found.", "No State Found.")  ;            
          }
           else if (document.getElementById("selcountry").options[i].text=="AFG - Afghanistan") {
         document.getElementById("selstate").options.length=0;  
         document.getElementById("selstate").options[0]=new Option("No State Found.", "No State Found.")  ;            
          }
        else if (document.getElementById("selcountry").options[i].text=="AGO - Angola") {
        document.getElementById("selstate").options.length=0; 
        document.getElementById("selstate").options[0]=new Option("No State Found.","No State Found.");  
              }
         else if (document.getElementById("selcountry").options[i].text=="AIA - Anguilla") {
        document.getElementById("selstate").options.length=0; 
        document.getElementById("selstate").options[0]=new Option("No State Found.", "No State Found.") ; 
             // document.getElementById("selstate").options[1]=new Option("State 2", "State 2")  ;
            //document.getElementById("selstate").options[2]=new Option("State 3" , "State 3");
           }
        else if (document.getElementById("selcountry").options[i].text=="ALB - Albania") {
        document.getElementById("selstate").options.length=0; 
        document.getElementById("selstate").options[0]=new Option("No State Found.","No State Found.");  
              }
        
        else if (document.getElementById("selcountry").options[i].text=="AND - Andorra") {
        document.getElementById("selstate").options.length=0; 
        document.getElementById("selstate").options[0]=new Option("No State Found.","No State Found.");  
              }
        
      else if (document.getElementById("selcountry").options[i].text=="ANT - Netherlands Antilles") {
        document.getElementById("selstate").options.length=0; 
        document.getElementById("selstate").options[0]=new Option("No State Found.","No State Found.");  
              }
        
      else if (document.getElementById("selcountry").options[i].text=="ARE - United Arab Emirates") {
        document.getElementById("selstate").options.length=0; 
        document.getElementById("selstate").options[0]=new Option("No State Found.","No State Found.");  
              }   
      else if (document.getElementById("selcountry").options[i].text=="ARG - Argentina") {
        document.getElementById("selstate").options.length=0; 
        document.getElementById("selstate").options[0]=new Option("BA - Buenos Aires","BA - Buenos Aires"); 
        document.getElementById("selstate").options[1]=new Option("CB - Chubut","CB - Chubut");
        document.getElementById("selstate").options[2]=new Option("CF - Capital Federal","CF - Capital Federal");
        document.getElementById("selstate").options[3]=new Option("CH - Chaco","CH - Chaco");
        document.getElementById("selstate").options[4]=new Option("CO - Cordoba","CO - Cordoba");  
        document.getElementById("selstate").options[5]=new Option("CR - Corrientes","CR - Corrientes");
        document.getElementById("selstate").options[6]=new Option("CT - Catamarca","CT - Catamarca");
        document.getElementById("selstate").options[7]=new Option("ER - Entre Rios","ER - Entre Rios");
        document.getElementById("selstate").options[8]=new Option("FO - Formosa","FO - Formosa");
        document.getElementById("selstate").options[9]=new Option("JU - Jujuy","JU - Jujuy");
        document.getElementById("selstate").options[10]=new Option("LP - La Pampa","LP - La Pampa");
        document.getElementById("selstate").options[11]=new Option("LR - La Rioja","LR - La Rioja");
        document.getElementById("selstate").options[12]=new Option("MI - Misiones","MI - Misiones");
        document.getElementById("selstate").options[13]=new Option("MZ - Mendoza","MZ - Mendoza");
        document.getElementById("selstate").options[14]=new Option("NQ - Neuquen","NQ - Neuquen");
        document.getElementById("selstate").options[15]=new Option("RN - Rio Negro","RN - Rio Negro");
        document.getElementById("selstate").options[16]=new Option("SA - Salta","SA - Salta");
        document.getElementById("selstate").options[17]=new Option("SC - Santa Cruz","SC - Santa Cruz");
        document.getElementById("selstate").options[18]=new Option("SE - Santiago del Estero","SE - Santiago del Estero");
        document.getElementById("selstate").options[19]=new Option("SF - Santa Fe","SF - Santa Fe");
        document.getElementById("selstate").options[20]=new Option("SJ - San Juan","SJ - San Juan");
        document.getElementById("selstate").options[21]=new Option("SL - San Luis","SL - San Luis");
        document.getElementById("selstate").options[22]=new Option("TF - Tierra del Fuego","TF - Tierra del Fuego");
        document.getElementById("selstate").options[23]=new Option("TU - Tucuman","TU - Tucuman");
              } 
        
      else if (document.getElementById("selcountry").options[i].text=="ARM - Armenia") {
        document.getElementById("selstate").options.length=0; 
        document.getElementById("selstate").options[0]=new Option("No State Found.","No State Found.");  
              }
      else if (document.getElementById("selcountry").options[i].text=="ASM - American Samoa") {
        document.getElementById("selstate").options.length=0; 
        document.getElementById("selstate").options[0]=new Option("No State Found.","No State Found.");  
              }
      else if (document.getElementById("selcountry").options[i].text=="ATA - Antarctica") {
        document.getElementById("selstate").options.length=0; 
        document.getElementById("selstate").options[0]=new Option("No State Found.","No State Found.");  
              }
      else if (document.getElementById("selcountry").options[i].text=="ATF - French Southern Territories") {
        document.getElementById("selstate").options.length=0; 
        document.getElementById("selstate").options[0]=new Option("No State Found.","No State Found.");  
              }
      else if (document.getElementById("selcountry").options[i].text=="ATG - Antigua and Barbuda") {
        document.getElementById("selstate").options.length=0; 
        document.getElementById("selstate").options[0]=new Option("No State Found.","No State Found.");  
              }
        
      else if (document.getElementById("selcountry").options[i].text=="AUS - Australia") {
        document.getElementById("selstate").options.length=0; 
        document.getElementById("selstate").options[0]=new Option("ACT - Austl. Cap. Terr.","ACT - Austl. Cap. Terr."); 
        document.getElementById("selstate").options[1]=new Option("NSW - New South Wales","NSW - New South Wales");
        document.getElementById("selstate").options[2]=new Option("NT - Northern Territory","NT - Northern Territory");
        document.getElementById("selstate").options[3]=new Option("QLD - Queensland","QLD - Queensland");
        document.getElementById("selstate").options[4]=new Option("SA - South Australia ","SA - South Australia ");  
        document.getElementById("selstate").options[5]=new Option("TAS - Tasmania","TAS - Tasmania");
        document.getElementById("selstate").options[6]=new Option("VIC - Victoria","VIC - Victoria");
        document.getElementById("selstate").options[7]=new Option("WA - Western Australia","WA - Western Australia");
        
              } 
      else if (document.getElementById("selcountry").options[i].text=="AUT - Austria") {
        document.getElementById("selstate").options.length=0; 
        document.getElementById("selstate").options[0]=new Option("No State Found.","No State Found.");  
              }    
           else if (document.getElementById("selcountry").options[i].text=="AZE - Azerbaijan") {
        document.getElementById("selstate").options.length=0; 
        document.getElementById("selstate").options[0]=new Option("No State Found.", "No State Found.") ; 
           }
      
      else if (document.getElementById("selcountry").options[i].text=="USA - United States of America") {
        document.getElementById("selstate").options.length=0; 
        document.getElementById("selstate").options[0]=new Option("AK - Alaska", "AK - Alaska") ; 
        document.getElementById("selstate").options[1]=new Option("AR – Arkansas", "AR – Arkansas") ; 
        document.getElementById("selstate").options[2]=new Option("TX – Texas", "TX – Texas") ; 
           }
      
        }
       }
      }
      }
      
function LnkInspection() {
  document.getElementById("Inspection").style.visibility = "visible";
  document.getElementById("Inspection").style.display = "block";
  }
function QtManHours(){
  document.getElementById("Services").style.visibility = "hidden";
  document.getElementById("Services").style.display = "none";
  
  document.getElementById("ServiceRates").style.visibility = "visible";
  document.getElementById("ServiceRates").style.display = "block";
  
  
  document.getElementById("servicesHd").style.visibility = "hidden";
  document.getElementById("servicesHd").style.display = "none";
  
  document.getElementById("serviceRatesHd").style.visibility = "visible";
  document.getElementById("serviceRatesHd").style.display = "block";
}


function QtManHoursCancel(){
  document.getElementById("Services").style.visibility = "visible";
  document.getElementById("Services").style.display = "block";
  
  document.getElementById("ServiceRates").style.visibility = "hidden";
  document.getElementById("ServiceRates").style.display = "none";
  
  
  document.getElementById("servicesHd").style.visibility = "visible";
  document.getElementById("servicesHd").style.display = "block";
  
  document.getElementById("serviceRatesHd").style.visibility = "hidden";
  document.getElementById("serviceRatesHd").style.display = "none";
}

 


function ZonePricebookUnChk() {
  document.getElementById("zonepricebook").style.visibility = "visible";
  document.getElementById("zonepricebook").style.display = "block";
  
}

function ZonePricebookChk() {
  document.getElementById("zonepricebook").style.visibility = "hidden";
  document.getElementById("zonepricebook").style.display = "none";
  
}

function toggle(){
var pricebook = document.getElementById("pricebook");
if (pricebook.checked == true){
  ZonePricebookUnChk();

} else {
  ZonePricebookChk();
  
} // end if
} // end toggle

// Projects
function showProjects(){
	document.getElementById("projectsContainer").style.visibility = "visible"; 
	document.getElementById("projectsContainer").style.display = "block"; 
	document.getElementById("bluarrowdownprojects").style.visibility = "visible"; 
	document.getElementById("bluarrowrightprojects").style.visibility = "hidden";

}
function hideProjects(){
	document.getElementById("projectsContainer").style.visibility = "hidden"; 
	document.getElementById("projectsContainer").style.display = "none"; 
	document.getElementById("bluarrowrightprojects").style.visibility = "visible";
	document.getElementById("bluarrowdownprojects").style.visibility = "hidden";

}
// ----------------------------------------------------------------------------

