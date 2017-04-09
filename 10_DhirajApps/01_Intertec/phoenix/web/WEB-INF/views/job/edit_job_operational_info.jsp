<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>

<icb:list var="divisions">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
</icb:list>

<icb:list var="divisionBu">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
  <icb:item>${command.branch.businessUnit.name}</icb:item>
</icb:list>

<c:set var="jobMeta" value="${icbfn:objectMeta(icbfn:user().branch.businessUnit.organization.name, command.jobType, command.class)}" />

<table width="97%" height="80%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top"><!-- BREADCRUMB TRAIL  -->
      <div id="breadcrumbContainer" >
        <table width="100%" border="0" cellpadding="0" cellspacing="0" background="images/intopbluetrailbg.jpg">
          <tr>
            <td width="25"><img src="images/inlfttrailcorner.gif" width="8" height="22"></td>
            <td><table height="22" border="0" align="left" cellpadding="0" cellspacing="0">
                <tr>
                  <td class="breadcrumbtrailDeactive" style="background:none; padding-left:5px;">&#9658; Entry </td>
                  <td class="breadcrumbtrailActive"> Job Instructions </td>
                  <td class="breadcrumbtrailDeactive"> Select Charges </td>
                  <td class="breadcrumbtrailDeactive">Invoice Preview </td>
                  <td class="breadcrumbtrailDeactive"> View Invoice </td>
                  <td align="right">&nbsp;</td>
                </tr>
              </table></td>
            <td align="right"><img src="images/inrttrailcorner.gif" width="7" height="22"></td>
          </tr>
        </table>
      </div>
      <!-- BREADCRUMB TRAIL END -->

      <form:form name="jobOrderEditForm" method="POST" action="edit_job.htm">
      <input type="hidden" name="refreshing" value="false" />
      <form:hidden path="jobNumber" />
      <input type="hidden" name="_page" value="1" />

      <div>
        <form:errors cssClass="error"/>
      </div>
      <!-- MAIN CONTAINER -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span>Operational Instructions</span></a></li>
            </ul>
            <label>
            <select name="sel5" id="sel5" class="SelectionBox" style="float:right" onChange="MM_jumpMenu('parent',this,1)">
              <option selected>Go to ... &gt;</option>
              <option value="edit_job.htm?_target0=Next&jobNumber=${command.jobNumber}">Entry</option>
            </select>
            </label>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            
            <div id="tab1" class="innercontent" >
              <table class="mainApplTable" cellspacing="0" cellpadding="0" style="width:100%" >
                <tbody>
                  <tr>
                    <th>Job Instructions <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> Job ID: ${command.jobNumber}</th>
                    <th style="text-align:right"><span style="text-align:right">
                      <c:set var="fieldMeta" value="${icbfn:fieldMeta(jobMeta, 'jobStatus')}" />
                      <c:if test="${fieldMeta != null}">
                        <label for="${fieldMeta.name}"><f:message key="${fieldMeta.label}" />: </label>
                        <form:select cssClass="selectionBox" path="${fieldMeta.name}" items="${icbfn:dropdown('status', null)}" itemLabel="name" itemValue="value"/>
                        <form:errors path="${fieldMeta.name}" cssClass="error"/>
                      </c:if>
                    </th>
                    <th style="text-align:right"><a href="#"><input type="image" src="images/left.gif" name="_target0" alt="Prev" width="14" height="14" border="0" /><input type="image" src="images/icosave.gif" name="_finish" alt="Save" width="14" height="14" border="0" /></th>
                  </tr>
                  <tr>
                    <td colspan="3" style="padding:0">
          <!-- -------------------------------------- VESSEL 1 CONTAINER -------------------------------------------------- -->
                      <div id="vessel01" class="vessels">
                        <table cellpadding="0" cellspacing="0" class="mainApplTable" >
                          <tbody>
                            <tr>
                              <th class="TDShade" style="background-image:url(images/arrowblubg.gif);"> <div id="bluarrowrightv1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showvessel1Table();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div>
                                <div id="bluarrowdownv1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidevessel1Table();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>
                              <td width="18%" class="TDShade" >Vessel 1/Location1: </td>
                              <td width="32%" class="TDShadeBlue"><input class="inputBox" value="STATE OF MAIN"  size="20"/>
                                <a href="#"><img src="images/lookup.gif" width="13" height="13" border="0" align="absmiddle" /></a> </td>
                              <td width="15%" class="TDShade" ><strong>Vessel Type:</strong></td>
                              <td colspan="2" width="35%" class="TDShadeBlue"><div style="float:right; font-size:10px; font-weight:bold;"> <a href="#" onClick="javascript:expandAllv1();">Expand All</a> <br>
                                  <a href="#" onClick="javascript:collapseAllv1();">Collapse All</a> </div>
                                <select name="sel3" size="1" class="selectionBox" id="sel3" >
                                  <option value="" selected>Select ...</option>
                                  <option>TANKER</option>
                                  <option>SEAGOING BARGE</option>
                                  <option>INLAND BARGE</option>
                                  <option>SHORE TANK</option>
                                  <option>OTHER</option>
                                </select>                              </td>
                              <td width="60" class="TDShadeBlue" align="right"><div id="tablefunction" style="width:60px; text-align:center; margin-right:0;"> <a href="#"><img src="images/add.gif" alt="Add Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#" onClick="javascript:copyvessel();"><img src="images/copy.gif" alt="Copy Vessel" width="13" height="12" hspace="1px" border="0"/></a> </div></td>
                            </tr>
                          </tbody>
                        </table>
                        <!-- ----------------------------VESSEL 1 Data ---------------------------------------- -->
                        <div id="vessel01Container" class="vesselContainer">
                          <!-- ----------------------------VESSEL 1 Product 1 ---------------------------------------- -->
                          <div id="productTablev1" class="productTable">
                            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin-right:0px;">
                              <tr>
                                <th width="16" rowspan="2" valign="top" style="background-image:url(images/arrowblubg.gif); background-repeat:repeat-x"> <div id="bluarrowrightv1p1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showv1product1Table();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div>
                                  <div id="bluarrowdownv1p1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidev1product1Table();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>
                                <td width="18%" class="TDShadeGrey">Product 1: </td>
                                <td width="32%" class="TDShadeGrey" ><input class="inputBox" value="DIESEL"  size="20" maxlength="10"/>
                                  <a href="#"><img src="images/lookup.gif" width="13" height="13" border="0" align="absmiddle" /></a> </td>
                                <td width="15%" class="TDShadeGrey" ><strong>Product Type:</strong></td>
                                <td width="35%" class="TDShadeGrey" ><select name="sel3" size="1" class="selectionBox" id="sel3" >
                                    <option value="" selected="selected">Select ...</option>
                                    <option>TANKER</option>
                                    <option>SEAGOING BARGE</option>
                                    <option>INLAND BARGE</option>
                                    <option>SHORE TANK</option>
                                    <option>OTHER</option>
                                  </select>                                </td>
                                <td align="center" class="TDShadeGreyDark" ><div id="div2" style="width:60px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add Product" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Product" width="13" height="12" hspace="1px" border="0"/></a> <a href="#" onClick="popup_show('copyprod', 'copyprod_drag', 'copyprod_exit', 'screen-corner', 40, 80);showFrmCopyProd();hideIt();"><img src="images/copy.gif" alt="Copy Product" width="13" height="12" hspace="1px" border="0"/></a> </div></td>
                              </tr>
                              <tr>
                                <td colspan="7" ><!-- ----------------------------QUANTITY CONTAINER V1 ---------------------------------------- -->
                                  <div id="quantityContainerv1p1" class="quantityContainer" >
                                    <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:3px;">
                                      <tr>
                                        <th width="130"> Applicable Contracts: </th>
                                        <td><input type="checkbox" name="checkbox3" value="checkbox" />
                                          SASOL291521011&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                          <input type="checkbox" name="checkbox32" value="checkbox" />
                                          BP111758022 </td>
                                      </tr>
                                    </table>
                                    <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" class="InnerApplTable" style="border-bottom:none;">
                                      <tr>
                                        <th width="13" rowspan="2" valign="top" style="background-image:url(images/arrowblubg.gif); background-repeat:repeat-x"> <div id="bluarrowrightv1p1q1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showv1p1quantity1Table();"> <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="6"/></a> </div>
                                          <div id="bluarrowdownv1p1q1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidev1p1quantity1Table();"> <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="6"/></a> </div></th>
                                        <th width="3%" ><img src="images/spacer.gif" width="1" height="1" /></th>
                                        <th width="20%" >Quantity</th>
                                        <th width="15%" >UOM</th>
                                        <th width="13%" >Option</th>
                                        <th width="7%" ><b>+/-</b></th>
                                        <th width="7%" >&nbsp;</th>
                                        <th width="18%" >Vessel/ Drafts </th>
                                        <th width="12%" >Tanks</th>
                                        <td width="40" class="TDGreyDark" ><div id="div14" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add Quantity" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Quantity" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                      </tr>
                                      <tr>
                                        <td >1.0</td>
                                        <td ><input name="RO_HEADER_LOCATION224"  class="inputBox" 
                                id="RO_HEADER_LOCATION224" value="1000000"  size="15"/>                                        </td>
                                        <td ><select name="sel4"  id="sel4" class="selectionBox">
                                            <option>BBL (Barrels)</option>
                                            <option>MT(Metric Tons)</option>
                                            <option>LT(Long Tons)</option>
                                          </select>                                        </td>
                                        <td ><select name="sel5" id="sel5" class="selectionBox">
                                            <option>APPROX</option>
                                            <option>MIN</option>
                                            <option>MAX</option>
                                            <option>MIN/MAX</option>
                                          </select>                                        </td>
                                        <td ><select name="sel6" id="sel6" class="selectionBox">
                                            <option>+</option>
                                            <option>-</option>
                                            <option>+/-</option>
                                          </select>                                        </td>
                                        <td ><input name="RO_HEADER_LOCATION2223"  class="inputBox" 
                                id="RO_HEADER_LOCATION224"  size="3" style="width:20px;"/>
                                          %</td>
                                        <td ><input name="RO_HEADER_LOCATION228"  class="inputBox" 
                                id="RO_HEADER_LOCATION228"  size="10"/>                                        </td>
                                        <td ><input name="RO_HEADER_LOCATION2212"  class="inputBox" 
                                id="RO_HEADER_LOCATION2212"  size="10"/>                                        </td>
                                        <td class="TDGreyDark" ><div id="div15" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                      </tr>
                                    </table>
                                    <div id="quantitytablev1p1" style="display:none; visibility:hidden;">
                                      <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" class="InnerApplTable" style="border-top:none;">
                                        <tr >
                                          <td width="18" rowspan="3" ><img src="images/spacer.gif" width="18" height="1" /></td>
                                          <td width="3%" >1.1</td>
                                          <td width="20%" ><input name="RO_HEADER_LOCATION2223"  class="inputBox" 
                                id="RO_HEADER_LOCATION224" value="50000"  size="15"/>                                          </td>
                                          <td width="15%" ><input name="RO_HEADER_LOCATION2252"  class="inputBox" 
                                id="RO_HEADER_LOCATION2252"  size="10"/>                                          </td>
                                          <td width="13%" ><select name="sel7" id="sel7" class="selectionBox">
                                              <option selected="selected">APPROX</option>
                                              <option>MIN</option>
                                              <option>MAX</option>
                                              <option>MIN/MAX</option>
                                            </select>                                          </td>
                                          <td width="7%" ><input name="RO_HEADER_LOCATION242"  class="inputBox" 
                                id="RO_HEADER_LOCATION242"  size="3" style="width:20px;"/>                                          </td>
                                          <td width="7%" ><input name="RO_HEADER_LOCATION2223"  class="inputBox" 
                                id="RO_HEADER_LOCATION224"  size="3" style="width:20px;"/>
                                            %</td>
                                          <td width="18%" ><input name="RO_HEADER_LOCATION2292"  class="inputBox" 
                                id="RO_HEADER_LOCATION2292"  size="10"/>                                          </td>
                                          <td width="12%" ><input name="RO_HEADER_LOCATION22132"  class="inputBox" 
                                id="RO_HEADER_LOCATION22132"  size="10"/>                                          </td>
                                          <td width="40" class="TDGreyDark" ><div id="div16" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                        <tr >
                                          <td >1.2</td>
                                          <td ><input name="RO_HEADER_LOCATION2223"  class="inputBox" 
                                id="RO_HEADER_LOCATION2223" value="30000"  size="15"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION2262"  class="inputBox" 
                                id="RO_HEADER_LOCATION2262"  size="10"/>                                          </td>
                                          <td ><select name="sel8" id="sel8" class="selectionBox">
                                              <option>APPROX</option>
                                              <option selected="selected">MIN</option>
                                              <option>MAX</option>
                                              <option>MIN/MAX</option>
                                            </select>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION252"  class="inputBox" 
                                id="RO_HEADER_LOCATION252"  size="3" style="width:20px;"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION2223"  class="inputBox" 
                                id="RO_HEADER_LOCATION224"  size="3" style="width:20px;"/>
                                            %</td>
                                          <td ><input name="RO_HEADER_LOCATION22102"  class="inputBox" 
                                id="RO_HEADER_LOCATION22102"  size="10"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION22142"  class="inputBox" 
                                id="RO_HEADER_LOCATION22142"  size="10"/>                                          </td>
                                          <td class="TDGreyDark" ><div id="div17" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                        <tr >
                                          <td >1.3</td>
                                          <td ><input name="RO_HEADER_LOCATION2232"  class="inputBox" 
                                id="RO_HEADER_LOCATION2232" value="20000"  size="15"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION2272"  class="inputBox" 
                                id="RO_HEADER_LOCATION2272"  size="10"/>                                          </td>
                                          <td ><select name="sel9" id="sel9" class="selectionBox">
                                              <option>APPROX</option>
                                              <option>MIN</option>
                                              <option>MAX</option>
                                              <option>MIN/MAX</option>
                                            </select>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION262"  class="inputBox" 
                                id="RO_HEADER_LOCATION262"  size="3" style="width:20px;"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION2223"  class="inputBox" 
                                id="RO_HEADER_LOCATION224"  size="3" style="width:20px;"/>
                                            %</td>
                                          <td ><input name="RO_HEADER_LOCATION22112"  class="inputBox" 
                                id="RO_HEADER_LOCATION22112"  size="10"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION22152"  class="inputBox" 
                                id="RO_HEADER_LOCATION22152"  size="10"/>                                          </td>
                                          <td class="TDGreyDark" ><div id="div18" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                      </table>
                                    </div>
                                  </div>
                                  <!-- ----------------------------QUANTITY CONTAINER V1 END ---------------------------------------- -->
                                  <!-- ----------------------------INSPECTION CONTAINER V1 ---------------------------------------- -->
                                  <div id="inspectionContainerv1p1" class="inspectionContainer">
                                    <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" class="InnerApplTable">
                                      <tr >
                                        <th width="13" style="background-image:url(images/arrowblubg.gif); background-repeat:repeat-x"> <div id="bluarrowrightv1p1in1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showv1p1inspection1Table();"> <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="6"/></a> </div>
                                          <div id="bluarrowdownv1p1in1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidev1p1inspection1Table();"> <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="6"/></a> </div></th>
                                        <th width="34%" >Inspection Events</th>
                                        <th width="66%" >Inspection Event Instructions</th>
                                      </tr>
                                    </table>
                                    <div id="inspectiontablev1p1" style="visibility:hidden; display:none;">
                                      <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" class="InnerApplTable">
                                        <tr >
                                          <td width="13" rowspan="3" ><img src="images/spacer.gif" width="18" height="1" /></td>
                                          <td width="35%" ><select name="sel2" id="sel2" class="selectionBox">
                                              <option>Sealing Required</option>
                                              <option>Line Displacement</option>
                                              <option>Wall Wash</option>
                                              <option>Purging Required</option>
                                            </select>                                          </td>
                                          <td width="65%" ><input name="RO_HEADER_LOCATION224"  class="inputBox" 
                                id="RO_HEADER_LOCATION23" value="1.5 time the volume of the line"  size="50"/></td>
                                          <td class="TDGreyDark" width="40"><div id="div19" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                        <tr >
                                          <td ><select name="sel3" id="sel3" class="selectionBox">
                                              <option selected="selected">Sealing Required</option>
                                              <option>Line Displacement</option>
                                              <option>Wall Wash</option>
                                              <option>Purging Required</option>
                                            </select></td>
                                          <td ><input name="RO_HEADER_LOCATION224"  class="inputBox" 
                                id="RO_HEADER_LOCATION23" value="1P&amp;S, 4P, 5S"  size="50"/>                                          </td>
                                          <td class="TDGreyDark" ><div id="div20" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                        <tr >
                                          <td ><select name="sel4" id="sel4" class="selectionBox">
                                              <option>Sealing Required</option>
                                              <option>Line Displacement</option>
                                              <option selected="selected">Wall Wash</option>
                                              <option>Purging Required</option>
                                            </select></td>
                                          <td ><input name="RO_HEADER_LOCATION224"  class="inputBox" 
                                id="RO_HEADER_LOCATION23" value="2P&amp;S, 4S, 5P"  size="50"/>                                          </td>
                                          <td class="TDGreyDark" ><div id="div21" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                      </table>
                                    </div>
                                  </div>
                                  <!-- -----------------Inspection Table V1 END -------------------------------------------------------- -->
                                  <!-- ---------------- Sample Location Container Table V1 --------------------------------------------- -->
                                  <div id="samplelocContainerv1p1" class="samplelocContainer" >
                                    <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" class="InnerApplTable">
                                      <tr >
                                        <th width="13" style="background-image:url(images/arrowblubg.gif); background-repeat:repeat-x"> <div id="bluarrowrightv1p1s1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showv1p1sample1Table();"> <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="6"/></a> </div>
                                          <div id="bluarrowdownv1p1s1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidev1p1sample1Table();"> <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="6"/></a> </div></th>
                                        <th width="20%" >Sample Locations</th>
                                        <th width="13%" >Tank#</th>
                                        <th width="13%" >Sample Timing </th>
                                        <th width="13%" >Samples Type </th>
                                        <th width="13%" ><b>Sample Vol.</b></th>
                                        <th width="13%" >Container Type</th>
                                        <th width="13%" >Retain/Tested</th>
                                        <th width="50" >&nbsp;</th>
                                      </tr>
                                      <tr >
                                        <td rowspan="2" >&nbsp;</td>
                                        <td ><select name="select3" class="selectionBox">
                                            <option selected="selected">Shore Tank</option>
                                            <option>Tank Truck</option>
                                            <option>Vessel</option>
                                          </select>                                        </td>
                                        <td ><input name="RO_HEADER_LOCATION2"  class="inputBox" 
                                id="RO_HEADER_LOCATION2"  size="10" 
                                maxlength="50"/>                                        </td>
                                        <td ><select name="select3" class="selectionBox">
                                            <option selected="selected">Before</option>
                                            <option>During</option>
                                            <option>After</option>
                                          </select>                                        </td>
                                        <td ><select name="select" class="selectionBox">
                                            <option selected>Upper</option>
                                            <option>Middle</option>
                                            <option>Lower</option>
                                            <option>Dead Bottom</option>
                                          </select>                                        </td>
                                        <td ><select name="select3" class="selectionBox">
                                            <option selected="selected">2 Gallons</option>
                                            <option>4 Gallons</option>
                                            <option>2 Liters</option>
                                            <option>4 Liters</option>
                                          </select>                                        </td>
                                        <td ><select name="select3" class="selectionBox">
                                            <option selected="selected">Amber Bottles</option>
                                            <option>Clear Bottles</option>
                                          </select>                                        </td>
                                        <td ><select name="select" class="selectionBox">
                                            <option>Retain</option>
                                            <option>Tested</option>
                                          </select>                                        </td>
                                        <td class="TDGreyDark" ><div id="div22" style="width:60px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add Sample" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Sample" width="13" height="12" hspace="1px" border="0"/></a> <a href="#" onClick="javascript:copysample();"><img src="images/copy.gif" alt="Copy Sample Location" width="13" height="12" hspace="1px" border="0"/></a> </div></td>
                                      </tr>
                                      <tr >
                                        <td colspan="8" ><div align="center" id="sampleloctablev1p1" style="visibility:hidden; display:none;">
                                            <table  border="0" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:100%">
                                              <tr>
                                                <td width="15%">Test1:</td>
                                                <td width="30%"><input name="RO_HEADER_LOCATION2142"  class="inputBox" 
                                id="RO_HEADER_LOCATION2142" value="Method 1"  size="15"/>                                                </td>
                                                <td width="40%"><input name="RO_HEADER_LOCATION23"  class="inputBox" 
                                id="RO_HEADER_LOCATION23" value="Description"  size="30"/>                                                </td>
                                                <td width="15%">OT
                                                  <select name="select3" class="selectionBox">
                                                    <option>Yes</option>
                                                    <option>No</option>
                                                  </select>                                                </td>
                                                <td width="40" class="TDGreyDark"><div id="div23" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                              </tr>
                                              <tr>
                                                <td>Test2:</td>
                                                <td><input name="RO_HEADER_LOCATION2152"  class="inputBox" 
                                id="RO_HEADER_LOCATION2152" value="Method 2"  size="15"/>                                                </td>
                                                <td><input name="RO_HEADER_LOCATION23"  class="inputBox" 
                                id="RO_HEADER_LOCATION23" value="Description"  size="30"/>                                                </td>
                                                <td>OT
                                                  <select name="select3" class="selectionBox">
                                                    <option>Yes</option>
                                                    <option>No</option>
                                                  </select>                                                </td>
                                                <td class="TDGreyDark"><div id="div24" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                              </tr>
                                              <tr>
                                                <td>State1:</td>
                                                <td><input name="RO_HEADER_LOCATION2162"  class="inputBox" 
                                id="RO_HEADER_LOCATION2162" value="Method 3"  size="15"/>                                                </td>
                                                <td><input name="RO_HEADER_LOCATION23"  class="inputBox" 
                                id="RO_HEADER_LOCATION23" value="Description"  size="30"/>                                                </td>
                                                <td>OT
                                                  <select name="select3" class="selectionBox">
                                                    <option>Yes</option>
                                                    <option>No</option>
                                                  </select>                                                </td>
                                                <td class="TDGreyDark"><div id="div25" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                              </tr>
                                              <tr>
                                                <td colspan="5"><table  border="0" cellpadding="0" cellspacing="0" style="width:100%">
                                                    <tr>
                                                      <td width="14%" valign="top" style="border-bottom:none">Sample Instructions: </td>
                                                      <td colspan="4" width="85%" style="border-bottom:none; border-right:none;"><label>
                                                        <textarea name="textarea9" cols="90" rows="4"></textarea>
                                                        </label></td>
                                                    </tr>
                                                  </table></td>
                                              </tr>
                                            </table>
                                          </div></td>
                                      </tr>
                                    </table>
                                  </div>
                                  <!-- -------------------------SAMPLE LOCATIONS TABLE V1 END ------------------------------------------ -->                                </td>
                              </tr>
                            </table>
                          </div>
                          <!-- ----------------------------VESSEL 1 PRODUCT 1 END ---------------------------------------- -->
                          <!-- *********************************************************************************************************************** -->
                          <!-- ----------------------------VESSEL 1 Product 2 ---------------------------------------- -->
                          <div id="v1productTable2" class="productTable" style="visibility:hidden; display:none">
                            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin-right:0px;">
                              <tr>
                                <th width="16" rowspan="2" valign="top" style="background-image:url(images/arrowblubg.gif); background-repeat:repeat-x"> <div id="bluarrowrightv1p2" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showv1product2();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div>
                                  <div id="bluarrowdownv1p2" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidev1product2();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>
                                <td width="18%" class="TDShadeGrey">Product 2: </td>
                                <td width="32%" class="TDShadeGrey" ><input class="inputBox" value="PETROL"  size="20" maxlength="10"/>
                                  <a href="#"><img src="images/lookup.gif" width="13" height="13" border="0" align="absmiddle" /></a> </td>
                                <td width="15%" class="TDShadeGrey" ><strong>Product Type:</strong></td>
                                <td width="35%" class="TDShadeGrey" ><select name="sel3" size="1" class="selectionBox" id="sel3" >
                                    <option value="" selected="selected">Select ...</option>
                                    <option>TANKER</option>
                                    <option>SEAGOING BARGE</option>
                                    <option>INLAND BARGE</option>
                                    <option>SHORE TANK</option>
                                    <option>OTHER</option>
                                  </select>                                </td>
                                <td align="center" class="TDShadeGreyDark" ><div style="width:60px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add Product" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Product" width="13" height="12" hspace="1px" border="0"/></a> <a href="#" onClick="javascript:copyproduct();"><img src="images/copy.gif" alt="Copy Product" width="13" height="12" hspace="1px" border="0"/></a> </div></td>
                              </tr>
                              <tr>
                                <td colspan="7" ><!-- ----------------------------QUANTITY CONTAINER V1 ---------------------------------------- -->
                                  <div id="quantityContainerv1p2" class="quantityContainer" >
                                    <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:3px;">
                                      <tr>
                                        <th width="130"> Applicable Contracts: </th>
                                        <td><input type="checkbox" name="checkbox3" value="checkbox" />
                                          SASOL291521011&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                          <input type="checkbox" name="checkbox32" value="checkbox" />
                                          BP111758022 </td>
                                      </tr>
                                    </table>
                                    <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" class="InnerApplTable" style="border-bottom:none;">
                                      <tr>
                                        <th width="13" rowspan="2" valign="top" style="background-image:url(images/arrowblubg.gif); background-repeat:repeat-x"> <div id="bluarrowrightv1p2q1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showv1p2quantity1();"> <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="6"/></a> </div>
                                          <div id="bluarrowdownv1p2q1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidev1p2quantity1();"> <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="6"/></a> </div></th>
                                        <th width="3%" ><img src="images/spacer.gif" width="1" height="1" /></th>
                                        <th width="20%" >Quantity</th>
                                        <th width="15%" >UOM</th>
                                        <th width="13%" >Option</th>
                                        <th width="7%" ><b>+/-</b></th>
                                        <th width="7%" >&nbsp;</th>
                                        <th width="18%" >Vessel/ Drafts </th>
                                        <th width="12%" >Tanks</th>
                                        <td width="40" class="TDGreyDark" ><div style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add Quantity" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Quantity" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                      </tr>
                                      <tr>
                                        <td >1.0</td>
                                        <td ><input name="RO_HEADER_LOCATION2217"  class="inputBox" 
                                id="RO_HEADER_LOCATION2217" value="1000000"  size="15"/>                                        </td>
                                        <td ><select name="select12" class="selectionBox">
                                            <option>BBL (Barrels)</option>
                                            <option>MT(Metric Tons)</option>
                                            <option>LT(Long Tons)</option>
                                          </select></td>
                                        <td ><select name="select9" class="selectionBox">
                                            <option>APPROX</option>
                                            <option>MIN</option>
                                            <option>MAX</option>
                                            <option>MIN/MAX</option>
                                          </select>                                        </td>
                                        <td ><select name="select15" class="selectionBox">
                                            <option>+</option>
                                            <option>-</option>
                                            <option>+/-</option>
                                          </select></td>
                                        <td ><input name="RO_HEADER_LOCATION2224"  class="inputBox" 
                                id="RO_HEADER_LOCATION2217"  size="3" style="width:20px;"/>
                                          %</td>
                                        <td ><input name="RO_HEADER_LOCATION2283"  class="inputBox" 
                                id="RO_HEADER_LOCATION2283"  size="10"/>                                        </td>
                                        <td ><input name="RO_HEADER_LOCATION22123"  class="inputBox" 
                                id="RO_HEADER_LOCATION22123"  size="10"/>                                        </td>
                                        <td class="TDGreyDark" ><div style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                      </tr>
                                    </table>
                                    <div id="quantityv1p2" style="display:none; visibility:hidden;">
                                      <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" class="InnerApplTable" style="border-top:none;">
                                        <tr >
                                          <td width="18" rowspan="3" ><img src="images/spacer.gif" width="18" height="1" /></td>
                                          <td width="3%" >1.1</td>
                                          <td width="20%" ><input name="RO_HEADER_LOCATION2224"  class="inputBox" 
                                id="RO_HEADER_LOCATION2217" value="50000"  size="15"/>                                          </td>
                                          <td width="15%" ><input name="RO_HEADER_LOCATION2253"  class="inputBox" 
                                id="RO_HEADER_LOCATION2253"  size="10"/>                                          </td>
                                          <td width="13%" ><select name="select9" class="selectionBox">
                                              <option selected="selected">APPROX</option>
                                              <option>MIN</option>
                                              <option>MAX</option>
                                              <option>MIN/MAX</option>
                                            </select>                                          </td>
                                          <td width="7%" ><input name="RO_HEADER_LOCATION243"  class="inputBox" 
                                id="RO_HEADER_LOCATION243"  size="3" style="width:20px;"/>                                          </td>
                                          <td width="7%" ><input name="RO_HEADER_LOCATION2224"  class="inputBox" 
                                id="RO_HEADER_LOCATION2217"  size="3" style="width:20px;"/>
                                            %</td>
                                          <td width="18%" ><input name="RO_HEADER_LOCATION2293"  class="inputBox" 
                                id="RO_HEADER_LOCATION2293"  size="10"/>                                          </td>
                                          <td width="12%" ><input name="RO_HEADER_LOCATION22133"  class="inputBox" 
                                id="RO_HEADER_LOCATION22133"  size="10"/>                                          </td>
                                          <td width="40" class="TDGreyDark" ><div id="div33" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                        <tr >
                                          <td >1.2</td>
                                          <td ><input name="RO_HEADER_LOCATION2224"  class="inputBox" 
                                id="RO_HEADER_LOCATION2224" value="30000"  size="15"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION2263"  class="inputBox" 
                                id="RO_HEADER_LOCATION2263"  size="10"/>                                          </td>
                                          <td ><select name="select9" class="selectionBox">
                                              <option>APPROX</option>
                                              <option selected="selected">MIN</option>
                                              <option>MAX</option>
                                              <option>MIN/MAX</option>
                                            </select>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION253"  class="inputBox" 
                                id="RO_HEADER_LOCATION253"  size="3" style="width:20px;"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION2224"  class="inputBox" 
                                id="RO_HEADER_LOCATION2217"  size="3" style="width:20px;"/>
                                            %</td>
                                          <td ><input name="RO_HEADER_LOCATION22103"  class="inputBox" 
                                id="RO_HEADER_LOCATION22103"  size="10"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION22143"  class="inputBox" 
                                id="RO_HEADER_LOCATION22143"  size="10"/>                                          </td>
                                          <td class="TDGreyDark" ><div id="div34" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                        <tr >
                                          <td >1.3</td>
                                          <td ><input name="RO_HEADER_LOCATION2233"  class="inputBox" 
                                id="RO_HEADER_LOCATION2233" value="20000"  size="15"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION2273"  class="inputBox" 
                                id="RO_HEADER_LOCATION2273"  size="10"/>                                          </td>
                                          <td ><select name="select9" class="selectionBox">
                                              <option>APPROX</option>
                                              <option>MIN</option>
                                              <option>MAX</option>
                                              <option>MIN/MAX</option>
                                            </select>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION263"  class="inputBox" 
                                id="RO_HEADER_LOCATION263"  size="3" style="width:20px;"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION2224"  class="inputBox" 
                                id="RO_HEADER_LOCATION2217"  size="3" style="width:20px;"/>
                                            %</td>
                                          <td ><input name="RO_HEADER_LOCATION22113"  class="inputBox" 
                                id="RO_HEADER_LOCATION22113"  size="10"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION22153"  class="inputBox" 
                                id="RO_HEADER_LOCATION22153"  size="10"/>                                          </td>
                                          <td class="TDGreyDark" ><div id="div35" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                      </table>
                                    </div>
                                  </div>
                                  <!-- ----------------------------QUANTITY CONTAINER V1 END ---------------------------------------- -->
                                  <!-- ----------------------------INSPECTION CONTAINER V1 ---------------------------------------- -->
                                  <div id="inspectionContainerv1p2" class="inspectionContainer">
                                    <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" class="InnerApplTable">
                                      <tr >
                                        <th width="13" style="background-image:url(images/arrowblubg.gif); background-repeat:repeat-x"> <div id="bluarrowrightv1p2in1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showv1p2inspection1();"> <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="6"/></a> </div>
                                          <div id="bluarrowdownv1p2in1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidev1p2inspection1();"> <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="6"/></a> </div></th>
                                        <th width="34%" >Inspection Events</th>
                                        <th width="66%" >Inspection Event Instructions</th>
                                      </tr>
                                    </table>
                                    <div id="inspectionv1p2" style="visibility:hidden; display:none;">
                                      <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" class="InnerApplTable">
                                        <tr >
                                          <td width="18" rowspan="3" ><img src="images/spacer.gif" width="18" height="1" /></td>
                                          <td width="35%" ><select name="select21" class="selectionBox">
                                              <option>Sealing Required</option>
                                              <option>Line Displacement</option>
                                              <option>Wall Wash</option>
                                              <option>Purging Required</option>
                                            </select></td>
                                          <td width="65%" ><input name="RO_HEADER_LOCATION2217"  class="inputBox" 
                                id="RO_HEADER_LOCATION27" value="1.5 time the volume of the line"  size="50"/>                                          </td>
                                          <td class="TDGreyDark" ><div id="div39" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                        <tr >
                                          <td ><select name="select22" class="selectionBox">
                                              <option selected="selected">Sealing Required</option>
                                              <option>Line Displacement</option>
                                              <option>Wall Wash</option>
                                              <option>Purging Required</option>
                                            </select></td>
                                          <td ><input name="RO_HEADER_LOCATION2217"  class="inputBox" 
                                id="RO_HEADER_LOCATION27" value="1P&amp;S, 4P, 5S"  size="50"/>                                          </td>
                                          <td class="TDGreyDark" ><div id="div40" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                        <tr >
                                          <td ><select name="select23" class="selectionBox">
                                              <option>Sealing Required</option>
                                              <option>Line Displacement</option>
                                              <option selected="selected">Wall Wash</option>
                                              <option>Purging Required</option>
                                            </select></td>
                                          <td ><input name="RO_HEADER_LOCATION2217"  class="inputBox" 
                                id="RO_HEADER_LOCATION27" value="2P&amp;S, 4S, 5P"  size="50"/>                                          </td>
                                          <td class="TDGreyDark" ><div id="div41" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                      </table>
                                    </div>
                                  </div>
                                  <!-- -----------------Inspection Table V1 END -------------------------------------------------------- -->
                                  <!-- ---------------- Sample Location Container Table V1 --------------------------------------------- -->
                                  <div id="samplelocContainerv1p2" class="samplelocContainer" >
                                    <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" class="InnerApplTable">
                                      <tr >
                                        <th width="13" style="background-image:url(images/arrowblubg.gif); background-repeat:repeat-x"> <div id="bluarrowrightv1p2s1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showv1p2sample1();"> <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="6"/></a> </div>
                                          <div id="bluarrowdownv1p2s1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidev1p2sample1();"> <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="6"/></a> </div></th>
                                        <th width="20%" >Sample Locations</th>
                                        <th width="13%" >Tank#</th>
                                        <th width="13%" >Sample Timing </th>
                                        <th width="13%" >Samples Type </th>
                                        <th width="13%" ><b>Sample Vol.</b></th>
                                        <th width="13%" >Container Type</th>
                                        <th width="13%" >Retain/Tested</th>
                                        <th width="50" >&nbsp;</th>
                                      </tr>
                                      <tr >
                                        <td rowspan="2" >&nbsp;</td>
                                        <td ><select name="select3" class="selectionBox">
                                            <option selected="selected">Shore Tank</option>
                                            <option>Tank Truck</option>
                                            <option>Vessel</option>
                                          </select>                                        </td>
                                        <td ><input name="RO_HEADER_LOCATION2"  class="inputBox" 
                                id="RO_HEADER_LOCATION2"  size="10" 
                                maxlength="50"/>                                        </td>
                                        <td ><select name="select3" class="selectionBox">
                                            <option selected="selected">Before</option>
                                            <option>During</option>
                                            <option>After</option>
                                          </select>                                        </td>
                                        <td ><select name="select" class="selectionBox">
                                            <option selected>Upper</option>
                                            <option>Middle</option>
                                            <option>Lower</option>
                                            <option>Dead Bottom</option>
                                          </select>                                        </td>
                                        <td ><select name="select3" class="selectionBox">
                                            <option selected="selected">2 Gallons</option>
                                            <option>4 Gallons</option>
                                            <option>2 Liters</option>
                                            <option>4 Liters</option>
                                          </select>                                        </td>
                                        <td ><select name="select3" class="selectionBox">
                                            <option selected="selected">Amber Bottles</option>
                                            <option>Clear Bottles</option>
                                          </select>                                        </td>
                                        <td ><select name="select" class="selectionBox">
                                            <option>Retain</option>
                                            <option>Tested</option>
                                          </select>                                        </td>
                                        <td class="TDGreyDark" ><div style="width:60px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add Sample" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Sample" width="13" height="12" hspace="1px" border="0"/></a> <a href="#" onClick="javascript:copysample();"><img src="images/copy.gif" alt="Copy Sample Location" width="13" height="12" hspace="1px" border="0"/></a> </div></td>
                                      </tr>
                                      <tr >
                                        <td colspan="8" ><div align="center" id="samplelocv1p2" style="visibility:hidden; display:none;">
                                            <table  border="0" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:100%">
                                              <tr>
                                                <td width="15%">Test1:</td>
                                                <td width="30%"><input name="RO_HEADER_LOCATION2143"  class="inputBox" 
                                id="RO_HEADER_LOCATION2143" value="Method 1"  size="15"/>                                                </td>
                                                <td width="40%"><input name="RO_HEADER_LOCATION27"  class="inputBox" 
                                id="RO_HEADER_LOCATION27" value="Description"  size="30"/>                                                </td>
                                                <td width="15%">OT
                                                  <select name="select9" class="selectionBox">
                                                    <option>Yes</option>
                                                    <option>No</option>
                                                  </select>                                                </td>
                                                <td width="40" class="TDGreyDark"><div id="div46" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                              </tr>
                                              <tr>
                                                <td>Test2:</td>
                                                <td><input name="RO_HEADER_LOCATION2153"  class="inputBox" 
                                id="RO_HEADER_LOCATION2153" value="Method 2"  size="15"/>                                                </td>
                                                <td><input name="RO_HEADER_LOCATION27"  class="inputBox" 
                                id="RO_HEADER_LOCATION27" value="Description"  size="30"/>                                                </td>
                                                <td>OT
                                                  <select name="select9" class="selectionBox">
                                                    <option>Yes</option>
                                                    <option>No</option>
                                                  </select>                                                </td>
                                                <td class="TDGreyDark"><div id="div47" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                              </tr>
                                              <tr>
                                                <td>State1:</td>
                                                <td><input name="RO_HEADER_LOCATION2163"  class="inputBox" 
                                id="RO_HEADER_LOCATION2163" value="Method 3"  size="15"/>                                                </td>
                                                <td><input name="RO_HEADER_LOCATION27"  class="inputBox" 
                                id="RO_HEADER_LOCATION27" value="Description"  size="30"/>                                                </td>
                                                <td>OT
                                                  <select name="select9" class="selectionBox">
                                                    <option>Yes</option>
                                                    <option>No</option>
                                                  </select>                                                </td>
                                                <td class="TDGreyDark"><div id="div48" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                              </tr>
                                              <tr>
                                                <td colspan="5"><table  border="0" cellpadding="0" cellspacing="0" style="width:100%">
                                                    <tr>
                                                      <td width="14%" valign="top" style="border-bottom:none">Sample Instructions: </td>
                                                      <td colspan="4" width="85%" style="border-bottom:none; border-right:none;"><label>
                                                        <textarea name="textarea10" cols="90" rows="4"></textarea>
                                                        </label></td>
                                                    </tr>
                                                  </table></td>
                                              </tr>
                                            </table>
                                          </div></td>
                                      </tr>
                                    </table>
                                  </div>
                                  <!-- -------------------------SAMPLE LOCATIONS TABLE V1 END ------------------------------------------ -->                                </td>
                              </tr>
                            </table>
                          </div>
                          <!-- ----------------------------VESSEL 1 PRODUCT 2 END ---------------------------------------- -->
                        </div>
                        <!-- ----------------------------VESSEL 1 Data END ---------------------------------------- -->
                      </div>
                      <!-- -------------------------------------- VESSEL 1 Container END --------------------------------------- -->
                    </td>
                  </tr>
                  <tr>
                    <td colspan="3" style="padding:0;"><!-- --------------------------------------- VESSEL 2 CONTAINER ------------------------------------------------- -->
                      <div id="vessel02" class="vessels">
                        <table cellpadding="0" cellspacing="0" class="mainApplTable">
                          <tbody>
                            <tr>
                              <th width="16" class="TDShade" style="background-image:url(images/arrowblubg.gif);"> <div id="bluarrowrightv2" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showvessel2Table();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div>
                                <div id="bluarrowdownv2" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidevessel2Table();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>
                              <td width="18%" class="TDShade" >Vessel 2/Location2: </td>
                              <td width="32%" class="TDShadeBlue"><input name="RO_HEADER_LOCATION2"  class="inputBox" 
                                id="RO_HEADER_LOCATION2" value="STATE OF MAIN"  size="20"/>
                                <a href="#"><img 
                                
                                src="images/lookup.gif" width="13" height="13" border="0" align="absmiddle" /></a></td>
                              <td width="15%" class="TDShade"><strong><strong>Vessel Type:</strong></strong></td>
                              <td colspan="2" width="35%" class="TDShadeBlue"><div style="float:right; font-size:10px; font-weight:bold;"> <a href="#" onClick="javascript:expandAllv2();">Expand All</a> <br>
                                  <a href="#" onClick="javascript:collapseAllv2();">Collapse All</a> </div>
                                <select name="select4" size="1" class="selectionBox" id="sel4" >
                                  <option value="" selected>Select ...</option>
                                  <option>TANKER</option>
                                  <option>SEAGOING BARGE</option>
                                  <option>INLAND BARGE</option>
                                  <option>SHORE TANK</option>
                                  <option>OTHER</option>
                                </select>                              </td>
                              <td width="60" class="TDShadeBlue" align="right"><div id="tablefunction" style="width:60px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#" onClick="javascript:copyvessel();"><img src="images/copy.gif" alt="Copy Vessel" width="13" height="12" hspace="1px" border="0"/></a> </div></td>
                            </tr>
                          </tbody>
                        </table>
                        <!-- ----------------------------VESSEL 2 Data ---------------------------------------- -->
                        <div id="vessel02Container" class="vesselContainer">
                          <div id="productTablev2" class="productTable">
                            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin-right:0px;">
                              <tr>
                                <th width="16" rowspan="2" valign="top" style="background-image:url(images/arrowblubg.gif); background-repeat:repeat-x"> <div id="bluarrowrightv2p1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showv2product1Table();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div>
                                  <div id="bluarrowdownv2p1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidev2product1Table();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>
                                <td width="18%" class="TDShadeGrey">Product 1: </td>
                                <td width="32%" class="TDShadeGrey" ><input name="RO_HEADER_LOCATION2"  class="inputBox" 
                                id="RO_HEADER_LOCATION2" value="DIESEL"  size="20" 
                                maxlength="10"/>
                                  <a href="#"><img 
                                
                                src="images/lookup.gif" width="13" height="13" border="0" align="absmiddle" /></a> </td>
                                <td width="15%" class="TDShadeGrey" ><strong>Product Type:</strong></td>
                                <td width="35%" class="TDShadeGrey" ><select name="select5" 
                                size="1" class="selectionBox" 
                                id="select3" >
                                    <option value="" selected>Select ...</option>
                                    <option>TANKER</option>
                                    <option>SEAGOING BARGE</option>
                                    <option>INLAND BARGE</option>
                                    <option>SHORE TANK</option>
                                    <option>OTHER</option>
                                  </select>                                </td>
                                <td align="center" class="TDShadeGreyDark" ><div id="tablefunction" style="width:60px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add Product" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Product" width="13" height="12" hspace="1px" border="0"/></a> <a href="#" onClick="javascript:copyproduct();"><img src="images/copy.gif" alt="Copy Product" width="13" height="12" hspace="1px" border="0"/></a> </div></td>
                              </tr>
                              <tr>
                                <td colspan="7" ><!-- ----------------------------QUANTITY CONTAINER ---------------------------------------- -->
                                  <div id="quantityContainerv2p1" class="quantityContainer">
                                    <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:3px;">
                                      <tr>
                                        <th width="130"> Applicable Contracts: </th>
                                        <td><input type="checkbox" name="checkbox3" value="checkbox" />
                                          SASOL291521011&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                          <input type="checkbox" name="checkbox32" value="checkbox" />
                                          BP111758022 </td>
                                      </tr>
                                    </table>
                                    <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" class="InnerApplTable" style="border-bottom:none;">
                                      <tr>
                                        <th width="13" rowspan="2" valign="top" style="background-image:url(images/arrowblubg.gif); background-repeat:repeat-x"> <div id="bluarrowrightv2p1q1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showv2p1quantity1Table();"> <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="6"/></a> </div>
                                          <div id="bluarrowdownv2p1q1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidev2p1quantity1Table();"> <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="6"/></a> </div></th>
                                        <th width="3%" ><img src="images/spacer.gif" width="1" height="1" /></th>
                                        <th width="20%" >Quantity</th>
                                        <th width="15%" >UOM</th>
                                        <th width="13%" >Option</th>
                                        <th width="7%" ><b>+/-</b></th>
                                        <th width="7%" >&nbsp;</th>
                                        <th width="18%" >Vessel/ Drafts </th>
                                        <th width="12%" >Tanks</th>
                                        <td width="40" class="TDGreyDark" ><div id="div" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add Quantity" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Quantity" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                      </tr>
                                      <tr>
                                        <td >1.0</td>
                                        <td ><input name="RO_HEADER_LOCATION2216"  class="inputBox" 
                                id="RO_HEADER_LOCATION2216" value="1000000"  size="15"/>                                        </td>
                                        <td ><select name="select13" class="selectionBox">
                                            <option>BBL (Barrels)</option>
                                            <option>MT(Metric Tons)</option>
                                            <option>LT(Long Tons)</option>
                                          </select></td>
                                        <td ><select name="select2" class="selectionBox">
                                            <option>APPROX</option>
                                            <option>MIN</option>
                                            <option>MAX</option>
                                            <option>MIN/MAX</option>
                                          </select>                                        </td>
                                        <td ><select name="select16" class="selectionBox">
                                            <option>+</option>
                                            <option>-</option>
                                            <option>+/-</option>
                                          </select></td>
                                        <td ><input name="RO_HEADER_LOCATION2222"  class="inputBox" 
                                id="RO_HEADER_LOCATION2222"  size="3" style="width:20px;"/>
                                          %</td>
                                        <td ><input name="RO_HEADER_LOCATION2282"  class="inputBox" 
                                id="RO_HEADER_LOCATION2282"  size="10"/>                                        </td>
                                        <td ><input name="RO_HEADER_LOCATION22122"  class="inputBox" 
                                id="RO_HEADER_LOCATION22122"  size="10"/>                                        </td>
                                        <td class="TDGreyDark" ><div id="div13" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                      </tr>
                                    </table>
                                    <div id="quantitytablev2p1" style="display:none; visibility:hidden;">
                                      <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" class="InnerApplTable" style="border-top:none;">
                                        <tr >
                                          <td width="18" rowspan="3" ><img src="images/spacer.gif" width="18" height="1" /></td>
                                          <td width="3%" >1.1</td>
                                          <td width="20%" ><input name="RO_HEADER_LOCATION222"  class="inputBox" 
                                id="RO_HEADER_LOCATION22" value="50000"  size="15"/>                                          </td>
                                          <td width="15%" ><input name="RO_HEADER_LOCATION225"  class="inputBox" 
                                id="RO_HEADER_LOCATION225"  size="10"/>                                          </td>
                                          <td width="13%" ><select name="select6" class="selectionBox">
                                              <option selected="selected">APPROX</option>
                                              <option>MIN</option>
                                              <option>MAX</option>
                                              <option>MIN/MAX</option>
                                            </select>                                          </td>
                                          <td width="7%" ><input name="RO_HEADER_LOCATION24"  class="inputBox" 
                                id="RO_HEADER_LOCATION24"  size="3" style="width:20px;"/>                                          </td>
                                          <td width="7%" ><input name="RO_HEADER_LOCATION222"  class="inputBox" 
                                id="RO_HEADER_LOCATION22"  size="3" style="width:20px;"/>
                                            %</td>
                                          <td width="18%" ><input name="RO_HEADER_LOCATION229"  class="inputBox" 
                                id="RO_HEADER_LOCATION229"  size="10"/>                                          </td>
                                          <td width="12%" ><input name="RO_HEADER_LOCATION2213"  class="inputBox" 
                                id="RO_HEADER_LOCATION2213"  size="10"/>                                          </td>
                                          <td width="40" class="TDGreyDark" ><div id="div3" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                        <tr >
                                          <td >1.2</td>
                                          <td ><input name="RO_HEADER_LOCATION222"  class="inputBox" 
                                id="RO_HEADER_LOCATION222" value="30000"  size="15"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION226"  class="inputBox" 
                                id="RO_HEADER_LOCATION226"  size="10"/>                                          </td>
                                          <td ><select name="select7" class="selectionBox">
                                              <option>APPROX</option>
                                              <option selected="selected">MIN</option>
                                              <option>MAX</option>
                                              <option>MIN/MAX</option>
                                            </select>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION25"  class="inputBox" 
                                id="RO_HEADER_LOCATION25"  size="3" style="width:20px;"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION222"  class="inputBox" 
                                id="RO_HEADER_LOCATION22"  size="3" style="width:20px;"/>
                                            %</td>
                                          <td ><input name="RO_HEADER_LOCATION2210"  class="inputBox" 
                                id="RO_HEADER_LOCATION2210"  size="10"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION2214"  class="inputBox" 
                                id="RO_HEADER_LOCATION2214"  size="10"/>                                          </td>
                                          <td class="TDGreyDark" ><div id="div4" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                        <tr >
                                          <td >1.3</td>
                                          <td ><input name="RO_HEADER_LOCATION223"  class="inputBox" 
                                id="RO_HEADER_LOCATION223" value="20000"  size="15"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION227"  class="inputBox" 
                                id="RO_HEADER_LOCATION227"  size="10"/>                                          </td>
                                          <td ><select name="select8" class="selectionBox">
                                              <option>APPROX</option>
                                              <option>MIN</option>
                                              <option>MAX</option>
                                              <option>MIN/MAX</option>
                                            </select>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION26"  class="inputBox" 
                                id="RO_HEADER_LOCATION26"  size="3" style="width:20px;"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION222"  class="inputBox" 
                                id="RO_HEADER_LOCATION22"  size="3" style="width:20px;"/>
                                            %</td>
                                          <td ><input name="RO_HEADER_LOCATION2211"  class="inputBox" 
                                id="RO_HEADER_LOCATION2211"  size="10"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION2215"  class="inputBox" 
                                id="RO_HEADER_LOCATION2215"  size="10"/>                                          </td>
                                          <td class="TDGreyDark" ><div id="div5" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                      </table>
                                    </div>
                                  </div>
                                  <!-- ----------------------------QUANTITY CONTAINER END ---------------------------------------- -->
                                  <!-- ----------------------------INSPECTION CONTAINER  ---------------------------------------- -->
                                  <div id="inspectionContainerv2p1" class="inspectionContainer">
                                    <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" class="InnerApplTable">
                                      <tr >
                                        <th width="13" style="background-image:url(images/arrowblubg.gif); background-repeat:repeat-x"> <div id="bluarrowrightv2p1in1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showv2p1inspection1Table();"> <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="6"/></a> </div>
                                          <div id="bluarrowdownv2p1in1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidev2p1inspection1Table();"> <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="6"/></a> </div></th>
                                        <th width="34%" >Inspection Events</th>
                                        <th width="66%" >Inspection Event Instructions</th>
                                      </tr>
                                    </table>
                                    <div id="inspectiontablev2p1" style="visibility:hidden; display:none;">
                                      <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" class="InnerApplTable">
                                        <tr >
                                          <td width="18" rowspan="3" ><img src="images/spacer.gif" width="18" height="1" /></td>
                                          <td width="35%" ><select name="select24" class="selectionBox">
                                              <option>Sealing Required</option>
                                              <option>Line Displacement</option>
                                              <option>Wall Wash</option>
                                              <option>Purging Required</option>
                                            </select></td>
                                          <td width="65%" ><input name="RO_HEADER_LOCATION22"  class="inputBox" 
                                id="RO_HEADER_LOCATION2" value="1.5 time the volume of the line"  size="50"/>                                          </td>
                                          <td class="TDGreyDark" ><div id="div6" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                        <tr >
                                          <td ><select name="select25" class="selectionBox">
                                              <option>Sealing Required</option>
                                              <option>Line Displacement</option>
                                              <option selected="selected">Wall Wash</option>
                                              <option>Purging Required</option>
                                            </select></td>
                                          <td ><input name="RO_HEADER_LOCATION22"  class="inputBox" 
                                id="RO_HEADER_LOCATION2" value="1P&amp;S, 4P, 5S"  size="50"/>                                          </td>
                                          <td class="TDGreyDark" ><div id="div7" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                        <tr >
                                          <td ><select name="select26" class="selectionBox">
                                              <option>Sealing Required</option>
                                              <option>Line Displacement</option>
                                              <option>Wall Wash</option>
                                              <option selected="selected">Purging Required</option>
                                            </select></td>
                                          <td ><input name="RO_HEADER_LOCATION22"  class="inputBox" 
                                id="RO_HEADER_LOCATION2" value="2P&amp;S, 4S, 5P"  size="50"/>                                          </td>
                                          <td class="TDGreyDark" ><div id="div8" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                      </table>
                                    </div>
                                  </div>
                                  <!-- -----------------Inspection Table END -------------------------------------------------------- -->
                                  <!-- ---------------- Sample Location Container Table --------------------------------------------- -->
                                  <div id="samplelocContainerv2p1" class="samplelocContainer">
                                    <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" class="InnerApplTable">
                                      <tr >
                                        <th width="13" style="background-image:url(images/arrowblubg.gif); background-repeat:repeat-x"> <div id="bluarrowrightv2p1s1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showv2p1sample1Table();"> <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="6"/></a> </div>
                                          <div id="bluarrowdownv2p1s1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidev2p1sample1Table();"> <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="6"/></a> </div></th>
                                        <th width="20%" >Sample Locations</th>
                                        <th width="13%" >Tank#</th>
                                        <th width="13%" >Sample Timing </th>
                                        <th width="13%" >Samples Type </th>
                                        <th width="13%" ><b>Sample Vol.</b></th>
                                        <th width="13%" >Container Type</th>
                                        <th width="13%" >Retain/Tested</th>
                                        <th width="50" >&nbsp;</th>
                                      </tr>
                                      <tr >
                                        <td rowspan="2" >&nbsp;</td>
                                        <td ><select name="select3" class="selectionBox">
                                            <option selected="selected">Shore Tank</option>
                                            <option>Tank Truck</option>
                                            <option>Vessel</option>
                                          </select>                                        </td>
                                        <td ><input name="RO_HEADER_LOCATION2"  class="inputBox" 
                                id="RO_HEADER_LOCATION2"  size="10" 
                                maxlength="50"/>                                        </td>
                                        <td ><select name="select3" class="selectionBox">
                                            <option selected="selected">Before</option>
                                            <option>During</option>
                                            <option>After</option>
                                          </select>                                        </td>
                                        <td ><select name="select" class="selectionBox">
                                            <option selected>Upper</option>
                                            <option>Middle</option>
                                            <option>Lower</option>
                                            <option>Dead Bottom</option>
                                          </select>                                        </td>
                                        <td ><select name="select3" class="selectionBox">
                                            <option selected="selected">2 Gallons</option>
                                            <option>4 Gallons</option>
                                            <option>2 Liters</option>
                                            <option>4 Liters</option>
                                          </select>                                        </td>
                                        <td ><select name="select3" class="selectionBox">
                                            <option selected="selected">Amber Bottles</option>
                                            <option>Clear Bottles</option>
                                          </select>                                        </td>
                                        <td ><select name="select" class="selectionBox">
                                            <option>Retain</option>
                                            <option>Tested</option>
                                          </select>                                        </td>
                                        <td class="TDGreyDark" ><div id="div12" style="width:60px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add Sample" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Sample" width="13" height="12" hspace="1px" border="0"/></a> <a href="#" onClick="javascript:copysample();"><img src="images/copy.gif" alt="Copy Sample Location" width="13" height="12" hspace="1px" border="0"/></a> </div></td>
                                      </tr>
                                      <tr >
                                        <td colspan="8" ><div align="center" id="sampleloctablev2p1" style="visibility:hidden; display:none;">
                                            <table  border="0" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:100%">
                                              <tr>
                                                <td width="15%">Test1:</td>
                                                <td width="30%"><input name="RO_HEADER_LOCATION214"  class="inputBox" 
                                id="RO_HEADER_LOCATION214" value="Method 1"  size="15"/>                                                </td>
                                                <td width="40%"><input name="RO_HEADER_LOCATION2"  class="inputBox" 
                                id="RO_HEADER_LOCATION2" value="Description"  size="30"/>                                                </td>
                                                <td width="15%">OT
                                                  <select name="select" class="selectionBox">
                                                    <option>Yes</option>
                                                    <option>No</option>
                                                  </select>                                                </td>
                                                <td width="40" class="TDGreyDark"><div id="div9" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                              </tr>
                                              <tr>
                                                <td>Test2:</td>
                                                <td><input name="RO_HEADER_LOCATION215"  class="inputBox" 
                                id="RO_HEADER_LOCATION215" value="Method 2"  size="15"/>                                                </td>
                                                <td><input name="RO_HEADER_LOCATION2"  class="inputBox" 
                                id="RO_HEADER_LOCATION2" value="Description"  size="30"/>                                                </td>
                                                <td>OT
                                                  <select name="select" class="selectionBox">
                                                    <option>Yes</option>
                                                    <option>No</option>
                                                  </select>                                                </td>
                                                <td class="TDGreyDark"><div id="div10" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                              </tr>
                                              <tr>
                                                <td>State1:</td>
                                                <td><input name="RO_HEADER_LOCATION216"  class="inputBox" 
                                id="RO_HEADER_LOCATION216" value="Method 3"  size="15"/>                                                </td>
                                                <td><input name="RO_HEADER_LOCATION2"  class="inputBox" 
                                id="RO_HEADER_LOCATION2" value="Description"  size="30"/>                                                </td>
                                                <td>OT
                                                  <select name="select" class="selectionBox">
                                                    <option>Yes</option>
                                                    <option>No</option>
                                                  </select>                                                </td>
                                                <td class="TDGreyDark"><div id="div11" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                              </tr>
                                              <tr>
                                                <td colspan="5"><table  border="0" cellpadding="0" cellspacing="0" style="width:100%">
                                                    <tr>
                                                      <td width="14%" valign="top" style="border-bottom:none">Sample Instructions: </td>
                                                      <td colspan="4" width="85%" style="border-bottom:none; border-right:none;"><label>
                                                        <textarea name="textarea" cols="90" rows="4"></textarea>
                                                        </label></td>
                                                    </tr>
                                                  </table></td>
                                              </tr>
                                            </table>
                                          </div></td>
                                      </tr>
                                    </table>
                                  </div></td>
                              </tr>
                            </table>
                          </div>
                          <!-- -------------------------SAMPLE LOCATIONS TABLE END ------------------------------------------ -->
                        </div>
                      </div>
                      <!-- -------------------------------------- VESSEL 2 CONTAINER END --------------------------------------- --></td>
                  </tr>
                  <tr>
                    <td colspan="3" style="padding:0;"><!-- ---------------------------------------- VESSEL 3 CONTAINER --------------------------------------- -->
                      <div id="vessel03" class="vessels">
                        <table cellpadding="0" cellspacing="0" class="mainApplTable">
                          <tbody>
                            <tr>
                              <th width="16" class="TDShade" style="background-image:url(images/arrowblubg.gif);"> <div id="bluarrowrightv3" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showvessel3Table();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div>
                                <div id="bluarrowdownv3" style="visibility:hidden; position:relative; z-index: 1; margin-top:6px; "> <a href="#" onClick="javascript:hidevessel3Table();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>
                              <td width="18%" class="TDShade"><strong>Vessel 3/Location3:</strong></td>
                              <td width="32%" class="TDShadeBlue"><input name="RO_HEADER_LOCATION2"  class="inputBox" 
                                id="RO_HEADER_LOCATION2" value="STATE OF MAIN"  size="20"/>
                                <a href="#"><img 
                                
                                src="images/lookup.gif" width="13" height="13" border="0" align="absmiddle" /></a> </td>
                              <td width="15%" class="TDShade"><strong><strong>Vessel Type:</strong></strong></td>
                              <td colspan="2" width="35%" class="TDShadeBlue"><div style="float:right; font-size:10px; font-weight:bold;"> <a href="#" onClick="javascript:expandAllv3();">Expand All</a> <br>
                                  <a href="#" onClick="javascript:collapseAllv3();">Collapse All</a> </div>
                                <select name="sel5" size="1" class="selectionBox" id="sel5" >
                                  <option value="" selected>Select ...</option>
                                  <option>TANKER</option>
                                  <option>SEAGOING BARGE</option>
                                  <option>INLAND BARGE</option>
                                  <option>SHORE TANK</option>
                                  <option>OTHER</option>
                                </select>                              </td>
                              <td width="60" class="TDShadeBlue" align="right"><div id="tablefunction" style="width:60px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#" onClick="javascript:copyvessel();"><img src="images/copy.gif" alt="Copy Vessel" width="13" height="12" hspace="1px" border="0"/></a> </div></td>
                            </tr>
                          </tbody>
                        </table>
                        <!-- -------------------------------- VESSEL 3 DATA ------------------------------------------------ -->
                        <div id="vessel03Container" class="vesselContainer">
                          <div id="productTable" class="productTable">
                            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin-right:0px;">
                              <tr>
                                <th width="16" rowspan="2" valign="top" style="background-image:url(images/arrowblubg.gif); background-repeat:repeat-x"> <div id="bluarrowrightv3p1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showproduct1Table();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div>
                                  <div id="bluarrowdownv3p1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hideproduct1Table();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>
                                <td width="18%" class="TDShadeGrey">Product 1: </td>
                                <td width="32%" class="TDShadeGrey" ><input name="RO_HEADER_LOCATION2"  class="inputBox" 
                                id="RO_HEADER_LOCATION2" value="DIESEL"  size="20" 
                                maxlength="10"/>
                                  <a href="#"><img 
                                
                                src="images/lookup.gif" width="13" height="13" border="0" align="absmiddle" /></a> </td>
                                <td width="15%" class="TDShadeGrey" ><strong>Product Type:</strong></td>
                                <td width="35%" class="TDShadeGrey" ><select name="select5" 
                                size="1" class="selectionBox" 
                                id="select3" >
                                    <option value="" selected>Select ...</option>
                                    <option>TANKER</option>
                                    <option>SEAGOING BARGE</option>
                                    <option>INLAND BARGE</option>
                                    <option>SHORE TANK</option>
                                    <option>OTHER</option>
                                  </select>                                </td>
                                <td align="center" class="TDShadeGreyDark" ><div id="tablefunction" style="width:60px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add Product" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Product" width="13" height="12" hspace="1px" border="0"/></a> <a href="#" onClick="javascript:copyproduct();"><img src="images/copy.gif" alt="Copy Product" width="13" height="12" hspace="1px" border="0"/></a> </div></td>
                              </tr>
                              <tr>
                                <td colspan="7" ><!-- ----------------------------QUANTITY CONTAINER V3 ---------------------------------------- -->
                                  <div id="quantityContainer" class="quantityContainer" >
                                    <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style="margin-bottom:3px;">
                                      <tr>
                                        <th width="130"> Applicable Contracts: </th>
                                        <td><input type="checkbox" name="checkbox3" value="checkbox" />
                                          SASOL291521011&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                          <input type="checkbox" name="checkbox32" value="checkbox" />
                                          BP111758022 </td>
                                      </tr>
                                    </table>
                                    <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" class="InnerApplTable" style="border-bottom:none;">
                                      <tr>
                                        <th width="13" rowspan="2" valign="top" style="background-image:url(images/arrowblubg.gif); background-repeat:repeat-x"> <div id="bluarrowrightv3p1q1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showquantity1Table();"> <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="6"/></a> </div>
                                          <div id="bluarrowdownv3p1q1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidequantity1Table();"> <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="6"/></a> </div></th>
                                        <th width="3%" ><img src="images/spacer.gif" width="1" height="1" /></th>
                                        <th width="20%" >Quantity</th>
                                        <th width="15%" >UOM</th>
                                        <th width="13%" >Option</th>
                                        <th width="7%" ><b>+/-</b></th>
                                        <th width="7%" >&nbsp;</th>
                                        <th width="18%" >Vessel/ Drafts </th>
                                        <th width="12%" >Tanks</th>
                                        <td width="40" class="TDGreyDark" ><div id="div" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add Quantity" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Quantity" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                      </tr>
                                      <tr>
                                        <td >1.0</td>
                                        <td ><input name="RO_HEADER_LOCATION2216"  class="inputBox" 
                                id="RO_HEADER_LOCATION2216" value="1000000"  size="15"/>                                        </td>
                                        <td ><select name="select14" class="selectionBox">
                                            <option>BBL (Barrels)</option>
                                            <option>MT(Metric Tons)</option>
                                            <option>LT(Long Tons)</option>
                                          </select></td>
                                        <td ><select name="select2" class="selectionBox">
                                            <option>APPROX</option>
                                            <option>MIN</option>
                                            <option>MAX</option>
                                            <option>MIN/MAX</option>
                                          </select>                                        </td>
                                        <td ><select name="select17" class="selectionBox">
                                            <option>+</option>
                                            <option>-</option>
                                            <option>+/-</option>
                                          </select></td>
                                        <td ><input name="RO_HEADER_LOCATION2222"  class="inputBox" 
                                id="RO_HEADER_LOCATION2222"  size="3" style="width:20px;"/>
                                          %</td>
                                        <td ><input name="RO_HEADER_LOCATION2282"  class="inputBox" 
                                id="RO_HEADER_LOCATION2282"  size="10"/>                                        </td>
                                        <td ><input name="RO_HEADER_LOCATION22122"  class="inputBox" 
                                id="RO_HEADER_LOCATION22122"  size="10"/>                                        </td>
                                        <td class="TDGreyDark" ><div id="div13" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                      </tr>
                                    </table>
                                    <div id="quantitytable" style="display:none; visibility:hidden;">
                                      <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" class="InnerApplTable" style="border-top:none;">
                                        <tr >
                                          <td width="18" rowspan="3" ><img src="images/spacer.gif" width="18" height="1" /></td>
                                          <td width="3%" >1.1</td>
                                          <td width="20%" ><input name="RO_HEADER_LOCATION222"  class="inputBox" 
                                id="RO_HEADER_LOCATION22" value="50000"  size="15"/>                                          </td>
                                          <td width="15%" ><input name="RO_HEADER_LOCATION225"  class="inputBox" 
                                id="RO_HEADER_LOCATION225"  size="10"/>                                          </td>
                                          <td width="13%" ><select name="select6" class="selectionBox">
                                              <option selected="selected">APPROX</option>
                                              <option>MIN</option>
                                              <option>MAX</option>
                                              <option>MIN/MAX</option>
                                            </select>                                          </td>
                                          <td width="7%" ><input name="RO_HEADER_LOCATION24"  class="inputBox" 
                                id="RO_HEADER_LOCATION24"  size="3" style="width:20px;"/>                                          </td>
                                          <td width="7%" ><input name="RO_HEADER_LOCATION222"  class="inputBox" 
                                id="RO_HEADER_LOCATION22"  size="3" style="width:20px;"/>
                                            %</td>
                                          <td width="18%" ><input name="RO_HEADER_LOCATION229"  class="inputBox" 
                                id="RO_HEADER_LOCATION229"  size="10"/>                                          </td>
                                          <td width="12%" ><input name="RO_HEADER_LOCATION2213"  class="inputBox" 
                                id="RO_HEADER_LOCATION2213"  size="10"/>                                          </td>
                                          <td width="40" class="TDGreyDark" ><div id="div3" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                        <tr >
                                          <td >1.2</td>
                                          <td ><input name="RO_HEADER_LOCATION222"  class="inputBox" 
                                id="RO_HEADER_LOCATION222" value="30000"  size="15"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION226"  class="inputBox" 
                                id="RO_HEADER_LOCATION226"  size="10"/>                                          </td>
                                          <td ><select name="select7" class="selectionBox">
                                              <option>APPROX</option>
                                              <option selected="selected">MIN</option>
                                              <option>MAX</option>
                                              <option>MIN/MAX</option>
                                            </select>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION25"  class="inputBox" 
                                id="RO_HEADER_LOCATION25"  size="3" style="width:20px;"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION222"  class="inputBox" 
                                id="RO_HEADER_LOCATION22"  size="3" style="width:20px;"/>
                                            %</td>
                                          <td ><input name="RO_HEADER_LOCATION2210"  class="inputBox" 
                                id="RO_HEADER_LOCATION2210"  size="10"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION2214"  class="inputBox" 
                                id="RO_HEADER_LOCATION2214"  size="10"/>                                          </td>
                                          <td class="TDGreyDark" ><div id="div4" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                        <tr >
                                          <td >1.3</td>
                                          <td ><input name="RO_HEADER_LOCATION223"  class="inputBox" 
                                id="RO_HEADER_LOCATION223" value="20000"  size="15"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION227"  class="inputBox" 
                                id="RO_HEADER_LOCATION227"  size="10"/>                                          </td>
                                          <td ><select name="select8" class="selectionBox">
                                              <option>APPROX</option>
                                              <option>MIN</option>
                                              <option>MAX</option>
                                              <option>MIN/MAX</option>
                                            </select>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION26"  class="inputBox" 
                                id="RO_HEADER_LOCATION26"  size="3" style="width:20px;"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION222"  class="inputBox" 
                                id="RO_HEADER_LOCATION22"  size="3" style="width:20px;"/>
                                            %</td>
                                          <td ><input name="RO_HEADER_LOCATION2211"  class="inputBox" 
                                id="RO_HEADER_LOCATION2211"  size="10"/>                                          </td>
                                          <td ><input name="RO_HEADER_LOCATION2215"  class="inputBox" 
                                id="RO_HEADER_LOCATION2215"  size="10"/>                                          </td>
                                          <td class="TDGreyDark" ><div id="div5" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                      </table>
                                    </div>
                                  </div>
                                  <!-- ----------------------------QUANTITY CONTAINER END ---------------------------------------- -->
                                  <!-- ----------------------------INSPECTION CONTAINER V3 ------------------------------------------- -->
                                  <div id="inspectionContainer" class="inspectionContainer" >
                                    <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" class="InnerApplTable">
                                      <tr >
                                        <th width="13" style="background-image:url(images/arrowblubg.gif); background-repeat:repeat-x"> <div id="bluarrowrightv3p1in1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showinspection1Table();"> <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="6"/></a> </div>
                                          <div id="bluarrowdownv3p1in1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hideinspection1Table();"> <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="6"/></a> </div></th>
                                        <th width="34%" >Inspection Events</th>
                                        <th width="66%" >Inspection Event Instructions</th>
                                      </tr>
                                    </table>
                                    <div id="inspectiontable" style="visibility:hidden; display:none;">
                                      <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" class="InnerApplTable">
                                        <tr >
                                          <td width="18" rowspan="3" ><img src="images/spacer.gif" width="18" height="1" /></td>
                                          <td width="35%" ><select name="select27" class="selectionBox">
                                              <option>Sealing Required</option>
                                              <option>Line Displacement</option>
                                              <option>Wall Wash</option>
                                              <option selected="selected">Purging Required</option>
                                            </select></td>
                                          <td width="65%" ><input name="RO_HEADER_LOCATION22"  class="inputBox" 
                                id="RO_HEADER_LOCATION2" value="1.5 time the volume of the line"  size="50"/>                                          </td>
                                          <td class="TDGreyDark" ><div id="div6" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                        <tr >
                                          <td ><select name="select28" class="selectionBox">
                                              <option>Sealing Required</option>
                                              <option>Line Displacement</option>
                                              <option selected="selected">Wall Wash</option>
                                              <option>Purging Required</option>
                                            </select></td>
                                          <td ><input name="RO_HEADER_LOCATION22"  class="inputBox" 
                                id="RO_HEADER_LOCATION2" value="1P&amp;S, 4P, 5S"  size="50"/>                                          </td>
                                          <td class="TDGreyDark" ><div id="div7" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                        <tr >
                                          <td ><select name="select29" class="selectionBox">
                                              <option>Sealing Required</option>
                                              <option>Line Displacement</option>
                                              <option>Wall Wash</option>
                                              <option>Purging Required</option>
                                            </select></td>
                                          <td ><input name="RO_HEADER_LOCATION22"  class="inputBox" 
                                id="RO_HEADER_LOCATION2" value="2P&amp;S, 4S, 5P"  size="50"/>                                          </td>
                                          <td class="TDGreyDark" ><div id="div8" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                        </tr>
                                      </table>
                                    </div>
                                  </div>
                                  <!-- -----------------Inspection Table END -------------------------------------------------------- -->
                                  <!-- ---------------- Sample Location Container Table V3 --------------------------------------------- -->
                                  <div id="samplelocContainer" class="samplelocContainer">
                                    <table width="100%"  border="0" align="center" cellpadding="00" cellspacing="0" class="InnerApplTable">
                                      <tr >
                                        <th width="13" style="background-image:url(images/arrowblubg.gif); background-repeat:repeat-x"> <div id="bluarrowrightv3p1s1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showsample1Table();"> <img src="images/bluarrowrightsml.gif" width="7" height="13" border="0" vspace="6"/></a> </div>
                                          <div id="bluarrowdownv3p1s1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidesample1Table();"> <img src="images/bluarrowdownsml.gif" width="13" height="7" border="0" vspace="6"/></a> </div></th>
                                        <th width="20%" >Sample Locations</th>
                                        <th width="13%" >Tank#</th>
                                        <th width="13%" >Sample Timing </th>
                                        <th width="13%" >Samples Type </th>
                                        <th width="13%" ><b>Sample Vol.</b></th>
                                        <th width="13%" >Container Type</th>
                                        <th width="13%" >Retain/Tested</th>
                                        <th width="50" >&nbsp;</th>
                                      </tr>
                                      <tr >
                                        <td rowspan="2" >&nbsp;</td>
                                        <td ><select name="select3" class="selectionBox">
                                            <option selected="selected">Shore Tank</option>
                                            <option>Tank Truck</option>
                                            <option>Vessel</option>
                                          </select>                                        </td>
                                        <td ><input name="RO_HEADER_LOCATION2"  class="inputBox" 
                                id="RO_HEADER_LOCATION2"  size="10" 
                                maxlength="50"/>                                        </td>
                                        <td ><select name="select3" class="selectionBox">
                                            <option selected="selected">Before</option>
                                            <option>During</option>
                                            <option>After</option>
                                          </select>                                        </td>
                                        <td ><select name="select" class="selectionBox">
                                            <option selected>Upper</option>
                                            <option>Middle</option>
                                            <option>Lower</option>
                                            <option>Dead Bottom</option>
                                          </select>                                        </td>
                                        <td ><select name="select3" class="selectionBox">
                                            <option selected="selected">2 Gallons</option>
                                            <option>4 Gallons</option>
                                            <option>2 Liters</option>
                                            <option>4 Liters</option>
                                          </select>                                        </td>
                                        <td ><select name="select3" class="selectionBox">
                                            <option selected="selected">Amber Bottles</option>
                                            <option>Clear Bottles</option>
                                          </select>                                        </td>
                                        <td ><select name="select" class="selectionBox">
                                            <option>Retain</option>
                                            <option>Tested</option>
                                          </select>                                        </td>
                                        <td class="TDGreyDark" ><div id="div12" style="width:60px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add Sample" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Sample" width="13" height="12" hspace="1px" border="0"/></a> <a href="#" onClick="javascript:copysample();"><img src="images/copy.gif" alt="Copy Sample Location" width="13" height="12" hspace="1px" border="0"/></a> </div></td>
                                      </tr>
                                      <tr >
                                        <td colspan="8" ><div align="center" id="sampleloctable" style="visibility:hidden; display:none;">
                                            <table  border="0" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:100%">
                                              <tr>
                                                <td width="15%">Test1:</td>
                                                <td width="30%"><input name="RO_HEADER_LOCATION214"  class="inputBox" 
                                id="RO_HEADER_LOCATION214" value="Method 1"  size="15"/>                                                </td>
                                                <td width="40%"><input name="RO_HEADER_LOCATION2"  class="inputBox" 
                                id="RO_HEADER_LOCATION2" value="Description"  size="30"/>                                                </td>
                                                <td width="15%">OT
                                                  <select name="select" class="selectionBox">
                                                    <option>Yes</option>
                                                    <option>No</option>
                                                  </select>                                                </td>
                                                <td width="40" class="TDGreyDark"><div id="div9" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                              </tr>
                                              <tr>
                                                <td>Test2:</td>
                                                <td><input name="RO_HEADER_LOCATION215"  class="inputBox" 
                                id="RO_HEADER_LOCATION215" value="Method 2"  size="15"/>                                                </td>
                                                <td><input name="RO_HEADER_LOCATION2"  class="inputBox" 
                                id="RO_HEADER_LOCATION2" value="Description"  size="30"/>                                                </td>
                                                <td>OT
                                                  <select name="select" class="selectionBox">
                                                    <option>Yes</option>
                                                    <option>No</option>
                                                  </select>                                                </td>
                                                <td class="TDGreyDark"><div id="div10" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                              </tr>
                                              <tr>
                                                <td>State1:</td>
                                                <td><input name="RO_HEADER_LOCATION216"  class="inputBox" 
                                id="RO_HEADER_LOCATION216" value="Method 3"  size="15"/>                                                </td>
                                                <td><input name="RO_HEADER_LOCATION2"  class="inputBox" 
                                id="RO_HEADER_LOCATION2" value="Description"  size="30"/>                                                </td>
                                                <td>OT
                                                  <select name="select" class="selectionBox">
                                                    <option>Yes</option>
                                                    <option>No</option>
                                                  </select>                                                </td>
                                                <td class="TDGreyDark"><div id="div11" style="width:40px; text-align:center;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                              </tr>
                                              <tr>
                                                <td colspan="5"><table  border="0" cellpadding="0" cellspacing="0" style="width:100%">
                                                    <tr>
                                                      <td width="14%" valign="top" style="border-bottom:none">Sample Instructions: </td>
                                                      <td colspan="4" width="85%" style="border-bottom:none; border-right:none;"><label>
                                                        <textarea name="textarea" cols="90" rows="4"></textarea>
                                                        </label></td>
                                                    </tr>
                                                  </table></td>
                                              </tr>
                                            </table>
                                          </div></td>
                                      </tr>
                                    </table>
                                  </div></td>
                              </tr>
                            </table>
                          </div>
                          <!-- -------------------------SAMPLE LOCATIONS TABLE END ------------------------------------------ -->
                        </div>
                      </div>
                      <!-- -----------------------------VESSEL 3 CONTAINER END -------------------------------------------- -->
                    </td>
                  </tr>
                </tbody>
              </table>
              <!-- ------------------------ INSTRUCTIONS -------------------------------------------------- -->
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
                <tr>
                  <th width="30" style="background-image:url(images/arrowblubg.gif);"> <div id="bluarrowright" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showInstructions();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div>
                    <div id="bluarrowdown" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hideInstructions();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>
                  <th width="97%">Instructions</th>
                </tr>
                <tr>
                  <td colspan="2" style="padding:0px;"><div id="instructionsContainer">
                      <table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="50%">Inspection   Instructions:</td>
                          <td width="50%">Sample   Instructions:</td>
                        </tr>
                        <tr>
                          <td><label>
                            <textarea name="textarea" cols="60" rows="4"></textarea>
                            </label></td>
                          <td><textarea name="textarea2" cols="60" rows="4"></textarea></td>
                        </tr>
                        <tr>
                          <td colspan="2"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin:0px;">
                              <tr>
                                <th colspan="6"> Laboratory Events </th>
                              </tr>
                              <tr>
                                <td>Retain   Period:</td>
                                <td><label>
                                  <input name="textfield" type="text" class="inputBox" size="4" />
                                  days</label></td>
                                <td><label>
                                  <input type="checkbox" name="checkbox" value="checkbox" />
                                  Lab Analysis </label></td>
                                <td><input type="checkbox" name="checkbox2" value="checkbox" />
                                  OT Approved </td>
                                <td style="border-left:#CCCCCC solid 2px;">OT Approved   By:</td>
                                <td><label>
                                  <select name="select" class="selectionBox">
                                    <option>Select ...</option>
                                  </select>
                                  </label></td>
                              </tr>
                            </table></td>
                        </tr>
                        <tr>
                          <td>&nbsp;</td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td>Lab   Instructions:</td>
                          <td>Shipping   Instructions:</td>
                        </tr>
                        <tr>
                          <td><textarea name="textarea3" cols="60" rows="4"></textarea></td>
                          <td><textarea name="textarea4" cols="60" rows="4"></textarea></td>
                        </tr>
                        <tr>
                          <td>Reporting   Instructions:</td>
                          <td>Billing   Instructions:</td>
                        </tr>
                        <tr>
                          <td><textarea name="textarea5" cols="60" rows="4"></textarea></td>
                          <td><textarea name="textarea6" cols="60" rows="4"></textarea></td>
                        </tr>
                        <tr>
                          <td>Other   Instructions:</td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td><textarea name="textarea7" cols="60" rows="4"></textarea></td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td>&nbsp;</td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td colspan="2"><!--<table border="0" cellpadding="0" cellspacing="0" class="secAppltable" style="margin:0px;">
            <tr>
              <td width="12%">Revision: 0 </td>
              <td width="38%">Date/Time: 
      <div id="datetime" style="visibility:hidden; position:absolute; font-weight:normal;">
      <script>

      /*Current date script credit*/
      
      var mydate=new Date()
      var year=mydate.getYear()
      if (year < 1000)
      year+=1900
      var day=mydate.getDay()
      var month=mydate.getMonth()+1
      if (month<10)
      month="0"+month
      var daym=mydate.getDate()
      if (daym<10)
      daym="0"+daym
      document.write("<font color='000000' face='Arial'>"+month+"/"+daym+"/"+year+"</font>")
      </script>
        <script language="javascript" src="js/clock.js"></script>&nbsp;EDT 
        </div>
        
                </td>
              <td width="20%">Revision   Notes:</td>
              <td width="30%"><textarea name="textarea8" cols="50" rows="2"></textarea></td>
            </tr>
            <tr>
              <td height="40" colspan="4" style="padding-left:5px;"><input name="Button" type="button" class="button1" value="New Revision"  onClick="showdatetime();"/></td>
            </tr>
        </table>--></td>
                        </tr>
                      </table>
                    </div></td>
                </tr>
              </table>
              <!-- --------------------------------------- INSTRUCTIONS END -------------------------------------- -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
		<tr>
			<td>
				<table>
					<tr>
						<td class="TDShade"><strong><f:message key="projectNumber" />:</strong></td>
						<td class="TDShadeBlue"><form:input path="phxProjectNumber" cssClass="inputBox" disabled="true" /> <form:errors
							path="projectId" cssClass="redstar" /></td>
						<td class="TDShade">&nbsp;</td>
						<c:if test="${command.allowCreateUpdateProject}">
							<td class="TDShadeBlue">
								<input name="Submit2" type="button" class="button1" value="Create Project"
								onClick="createOCAProject(this.form);">
							</td>
						</c:if>
					</tr>
				</table>
			</td>
		</tr>
	</table>

              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td align="right"></td>
                        <td style="text-align:right"><input type="image" src="images/left.gif" name="_target0" alt="Prev" width="14" height="14" border="0" /><input type="image" src="images/icosave.gif" name="_finish" alt="Save" width="14" height="14" border="0" /></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
            </div>
            <!----------------- TAB 1 CONTAINER END ------------------------------ -->
          </div>
        </div>
        <script type="text/javascript">
      
      //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
      dolphintabs.init("tabnav", 0)
      
      </script>
        <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
        <table width="100%" cellspacing="0">
          <tr>
            <td height="24" style="text-align:right; padding-right:0px;"><select name="sel5" id="sel5" class="SelectionBox" onChange="MM_jumpMenu('parent',this,1)">
                <option selected>Go to ... &gt;</option>
                <option value="create_job.htm?_target0">Entry</option>
              </select>
            </td>
          </tr>
        </table>
      </div>
      <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
      </form:form>
    </td>
  </tr>
</table>

<!-- --------------------------- Copy Product Pop ------------------------------------------------- -->
<div class="sample_popup" id="copyprod" style="visibility: hidden; display: none;z-index:10;">
  <div class="menu_form_header" id="copyprod_drag" style="width:475px; "> <img class="menu_form_exit"   id="copyprod_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Copy Product </div>
  <div class="menu_form_body"   style="width:475px; height:140px; z-index:10">
    <form method="post" action="popup.php">
      <table width="95%" border="0" align="center" class="InnerApplTable">
        <tr>
          <td valign="middle"  colspan="2">
      
      <iframe align="left" frameborder="0" style="padding:0px; height:80px;" width="100%" src="inc_copyproduct.html" scrolling="no" id="frame1" name="frmcopyprod01" allowtransparency="yes" >
      </iframe>
      
      </td>
        </tr>
        <tr>
          <td>
      <input type="button" name="Submit" value="Ok" onClick="hidecopyprod();copyprod01();showIt();"/> &nbsp;&nbsp;
    <input name="cancel" type="reset" id="cancel" value="Cancel" onClick="hidecopyprod();showIt();"/>
     </td>
        </tr>
      </table>
    </form>
  </div>
</div>
</div>
<!-- --------------------------------- Copy Product Pop END ----------------------------------------- -->



