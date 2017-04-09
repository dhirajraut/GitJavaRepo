<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<form:form name="contractCreateForm" method="POST" action="create_contract.htm">
<div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
  <form:errors cssClass="error"/>
</div>

<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
  
      <!-- MAIN CONTAINER -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span>Contract </span></a></li>
              <li><a href="#" rel="tab2"><span>Contract Customers</span></a></li>
              <li><a href="#" rel="tab3"><span>Contract Attachments</span></a></li>
              <li><a href="#" rel="tab4"><span>Zones</span></a></li>
              <li><a href="#" rel="tab5"><span>Cargo Inspection</span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
            <!-- ------------------------- TAB 1 CONTAINER ----------------------------------------- -->
            <div id="tab1" class="innercontent" >
              <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
                <tbody>
                  <tr>
                    <th class="TDShade" colspan="5">Contract ID: New <img src="images/separator2.gif" width="2" height="27"  align="absmiddle"/> Description: Contract Description&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status: Active&nbsp;|&nbsp;Working PB :&nbsp;<select name="sel4" class="selectionBox" id="sel4" >
                        <option selected>USAUSD06</option>
                        <option>USAUSD07</option>
                    </select>&nbsp;&nbsp;|&nbsp;&nbsp;Working UOM :&nbsp;<select name="sel4" class="selectionBox" id="sel4" >
                        <option selected>Long Tons</option>
                        <option>Metric Tons</option>
                    </select>&nbsp;&nbsp; |&nbsp;Master List Date :25/04/2006&nbsp;&nbsp;</th>
                    
                  </tr>

                  <tr>
                    <td class="TDShade">Status Date :</td>
                    <td class="TDShadeBlue" colspan="3">
                      <form:input cssClass="inputBox" path="statusDate" size="10" maxlength="12"/>
                      &nbsp;<a href="#" onClick="displayCalendar(document.forms[0].statusDate,'mm/dd/yyyy',this)"><img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
                    </td>
                  </tr>
                  <tr>
                    <td class="TDShade">Invoice Type: </td>
                    <td class="TDShadeBlue"><select name="sel4" class="selectionBox" id="sel4" >
                        <option selected>Regular</option>
                        <option>Consolidated</option>
                    </select></td>
                    <td class="TDShade">Payment Terms:</td>
                    <td class="TDShadeBlue"><select name="sel4" class="selectionBox" id="sel4" >
                        <option selected>Due on Receipt</option>
                        <option>NET30</option>
                    </select></td>
                  <tr>
                    <td class="TDShade">Versions :</td>
                    <td class="TDShadeBlue" colspan="5">&nbsp;</td>
                  </tr>

                  <!-- Begin Date Div Start -->
                  <tr>
                    <td colspan="6" style="padding:0;">
          
                        <!-- ------------ Contract 2 CONTAINER ------------- -->
                        <div id="begindate01" class="contracts">
                          <table cellpadding="0" cellspacing="0" class="mainApplTable" >
                            <tbody>
                              <tr>
                                <th class="TDShade" style="background-image:url(images/arrowblubg.gif);"> <div id="arrowrtbd1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showbegindate01();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div>
                                    <div id="arrowdnbd1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidebegindate01();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>
                                <td width="19%" class="TDShade" >Begin Date : </td>
                                <td width="36%" class="TDShadeBlue"><input name="theDate4" type="text" class="inputBox" value="13/07/2007" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate4,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                                <td width="22%" class="TDShade" ><strong>End Date :</strong></td>
                                <td colspan="2" width="24%" class="TDShadeBlue"><input name="theDate5" type="text" class="inputBox" value="13/07/2007" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate5,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                                <td width="60" class="TDShadeBlue" align="right"><div id="tablefunction" style="width:60px; text-align:center; margin-right:0;"> <a href="#"><img src="images/add.gif" alt="Add Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#" onClick="javascript:copyvessel();"></a> </div></td>
                              </tr>
                            </tbody>
                          </table>
                          
                          <!-- ----------------------------CONTRACT 2  ---------------------------------------- -->
                          <div id="begindate01Container" class="contractContainer">
                           <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                      <tr>
                                            <td width="15%">Currency: </td>
                                            <td><select name="sel4" class="selectionBox" id="sel4" >
                        <option selected>USD&nbsp;&nbsp;&nbsp;&nbsp;</option>
                        <option>CAD</option>
            <option>EUR</option>
            <option>GBP</option>
                    </select></td>
                                            <td width="15%">UOM:</td>
                      <td><select name="sel4" class="selectionBox" id="sel4" >
                        <option selected>Long Tons</option>
                        <option>Metric Tons</option>
                    </select></td>
                                          </tr>
                                          <tr>
                                            <td>Pricebook Id:</td>
                                            <td><select name="sel4" class="selectionBox" id="sel4" >
                        <option selected>Current</option>
                        <option>USAUSD07</option>
                    </select></td>
                                            <td>Series:</td>
                      <td><select name="sel4" class="selectionBox" id="sel4" >
                        <option selected>USAUSD</option> <option>EAMEUSD</option></select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Editable?: &nbsp;<INPUT TYPE="checkbox" NAME="chk1"></td>
                                          </tr>

                                          <tr>
                                            <td>Annual Escalator:</td>
                      <td><input name="Input112" class="inputBox"/></td>
                                            <td>Escalator Date: </td>
                      <td><input name="theDate6" type="text" class="inputBox" value="13/07/2007" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate6,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                                          </tr>
                                          <tr>
                                            <td>Discount Pct:</td>
                      <td><input name="Input112" class="inputBox"/></td>
                                            <td>Insp Discount Pct: </td>
                      <td><input name="Input112" class="inputBox"/></td>
                                          </tr>
                                          <tr>
                                            <td>Test Discount Pct:</td>
                      <td><input name="Input112" class="inputBox"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hide Test Discount:&nbsp;<INPUT TYPE="checkbox" NAME="chk1"></td>
                                            <td>Slate Discount Pct: </td>
                      <td><input name="Input112" class="inputBox"/></td>
                                          </tr>
                                          <tr>
                                            <td>Ops Discount Pct:</td>
                      <td><input name="Input112" class="inputBox"/></td>
                                            <td>Lab Discount Pct:</td>
                      <td><input name="Input112" class="inputBox"/></td>
                                          </tr>
                                          <tr>
                                            <td>Product Group Set:</td>
                      <td><select name="sel4" class="selectionBox" id="sel4" >
                        <option selected>Pricebook</option>
            <option>HC_Pricebook</option>
            <option>DRY_Pricebook</option>
            <option>AG_Pricebook</option>
            <option>LIQ_Pricebook</option>
            </select>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">Edit</a></td>
                                            <td>Vessel Type Set:</td>
                      <td><select name="sel4" class="selectionBox" id="sel4" >
                        <option selected>Pricebook</option>
            <option>Liq_Vessels</option>
            <option>Mstr_VesselType</option>
            <option>Dry_Vessels</option>
            </select>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">Edit</a></td>
                                          </tr>
                            </table>
                          </div><!-- -------------CONTRACT 2 END ----------------------- -->
                        </div>
                      <!-- -------------------------- CONTRACT 2 CONTAINER END ---------------------------- -->           </td>
                  </tr>
                  <!-- Begin Date Div End -->

                  <!-- Begin Date Div 2 Start -->

                  <tr>
                    <td colspan="6" style="padding:0;">
          
          <!-- ------------ Contract 2 CONTAINER ------------- -->
                        <div id="version02" class="contracts">
                          <table cellpadding="0" cellspacing="0" class="mainApplTable" >
                            <tbody>
                              <tr>
                                <th class="TDShade" style="background-image:url(images/arrowblubg.gif);"> <div id="av2" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"><a href="#" onClick="showversion02();"><img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a></div>
                                    <div id="adv2" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hideversion02();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>
                                <td width="19%" class="TDShade" >Begin Date : </td>
                                <td width="36%" class="TDShadeBlue"><input name="theDate4" type="text" class="inputBox" value="1/1/2007" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate4,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                                <td width="22%" class="TDShade" ><strong>End Date :</strong></td>
                                <td colspan="2" width="24%" class="TDShadeBlue"><input name="theDate5" type="text" class="inputBox" value="12/31/2999" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate5,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                                <td width="60" class="TDShadeBlue" align="right"><div id="tablefunction" style="width:60px; text-align:center; margin-right:0;"> <a href="#"><img src="images/add.gif" alt="Add Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"></a> </div></td>
                              </tr>
                            </tbody>
                          </table>
                          
                          <!-- ----------------------------CONTRACT 2  ---------------------------------------- -->
                          <div id="version02Container" class="contractContainer">
                           <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                      <tr>
                                            <td width="15%">Currency: </td>
                                            <td><select name="sel4" class="selectionBox" id="sel4" >
                        <option selected>USD&nbsp;&nbsp;&nbsp;&nbsp;</option>
                        <option>CAD</option>
            <option>EUR</option>
            <option>GBP</option>
                    </select></td>
                                            <td width="15%">UOM:</td>
                      <td><select name="sel4" class="selectionBox" id="sel4" >
                        <option selected>Long Tons</option>
                        <option>Metric Tons</option>
                    </select></td>
                                          </tr>
                                          <tr>
                                            <td>Pricebook Id:</td>
                                            <td><select name="sel4" class="selectionBox" id="sel4" >
                        <option selected>Current</option>
                        <option>USAUSD07</option>
                    </select></td>
                                            <td>Series:</td>
                      <td><select name="sel4" class="selectionBox" id="sel4" >
                        <option selected>USAUSD</option> <option>EAMEUSD</option></select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Editable?: &nbsp;<INPUT TYPE="checkbox" NAME="chk1"></td>
                                          </tr>

                                          <tr>
                                            <td>Annual Escalator:</td>
                      <td><input name="Input112" class="inputBox"/></td>
                                            <td>Escalator Date: </td>
                      <td><input name="theDate6" type="text" class="inputBox" value="13/07/2007" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate6,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                                          </tr>
                                          <tr>
                                            <td>Discount Pct:</td>
                      <td><input name="Input112" class="inputBox"/></td>
                                            <td>Insp Discount Pct: </td>
                      <td><input name="Input112" class="inputBox"/></td>
                                          </tr>
                                          <tr>
                                            <td>Test Discount Pct:</td>
                      <td><input name="Input112" class="inputBox"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hide Test Discount:&nbsp;<INPUT TYPE="checkbox" NAME="chk1"></td>
                                            <td>Slate Discount Pct: </td>
                      <td><input name="Input112" class="inputBox"/></td>
                                          </tr>
                                          <tr>
                                            <td>Ops Discount Pct:</td>
                      <td><input name="Input112" class="inputBox"/></td>
                                            <td>Lab Discount Pct:</td>
                      <td><input name="Input112" class="inputBox"/></td>
                                          </tr>
                                          <tr>
                                            <td>Product Group Set:</td>
                      <td><select name="sel4" class="selectionBox" id="sel4" >
                        <option selected>Pricebook</option>
            <option>HC_Pricebook</option>
            <option>DRY_Pricebook</option>
            <option>AG_Pricebook</option>
            <option>LIQ_Pricebook</option>
            </select>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">Edit</a></td>
                                            <td>Vessel Type Set:</td>
                      <td><select name="sel4" class="selectionBox" id="sel4" >
                        <option selected>Pricebook</option>
            <option>Liq_Vessels</option>
            <option>Mstr_VesselType</option>
            <option>Dry_Vessels</option>
            </select>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">Edit</a></td>
                                          </tr>
                            </table>
                          </div><!-- -------------CONTRACT 2 END ----------------------- -->
                        </div>
                      <!-- -------------------------- CONTRACT 2 CONTAINER END ---------------------------- -->           </td>
                  </tr>
<!-- Begin Date Div 2 End -->

          <tr>
                                            <td class="TDShade" style="border-top:#CCCCCC dashed 1px;">Invoice Type: </td>
                                            <td class="TDShadeBlue" style="border-top:#CCCCCC dashed 1px;"><select name="sel4" class="selectionBox" id="sel4" >
                        <option selected>Regular</option>
                        <option>Consolidated</option>
                    </select></td>
                                            <td class="TDShade" style="border-top:#CCCCCC dashed 1px;">Expiration Date:</td>
                      <td class="TDShadeBlue" colspan="3" style="border-top:#CCCCCC dashed 1px;"><input name="theDate4" type="text" class="inputBox" value="12/31/2999" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate4,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                      </tr>
                                          <tr>
                                            <td class="TDShade">Originator:</td>
                                            <td class="TDShadeBlue"><input name="Input112" class="inputBox"/></td>
                                            <td class="TDShade">Signatory:</td>
                      <td class="TDShadeBlue" colspan="3"><input name="Input112" class="inputBox"/></td>
                                          </tr>

                                          <tr>
                                            <td class="TDShade">Originator Email:</td>
                      <td class="TDShadeBlue"><input name="Input112" class="inputBox" readonly/></td>
                                            <td class="TDShade">Signatory Email: </td>
                      <td class="TDShadeBlue" colspan="3"><input name="Input112" class="inputBox" readonly/></td>
                                          </tr>
                                          <tr>
                                            <td class="TDShade">Payment Terms:</td>
                      <td class="TDShadeBlue" colspan="5"><select name="sel4" class="selectionBox" id="sel4" >
                        <option selected>Due on Receipt</option>
                        <option>NET30</option>
                    </select></td>
                    </tr>

                </tbody>
</table>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><input name="Submit" type="submit" class="button1" value="Submit" />&nbsp;<input name="Approve" type="button" class="button1" value="Approve" />&nbsp;<input name="Deactivate" type="button" class="button1" value="Deactivate" /></td>
                        <td style="text-align:right"><!-- <a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a> --></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
            </div>
            <!----------------- TAB 1 CONTAINER END ------------------------------ -->
      
      <!-- ------------------------- TAB 2 CONTAINER ----------------------------------------- -->
            <div id="tab2" class="innercontent" >
             <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
       <tr><th width="70%">Contract ID <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> Description: Contract Description </th>
         <th width="25%"><span style="text-align:right">Status:
                  Active |
&nbsp;25/04/2006</span></th>
         <th width="5%" style="text-align:right"><a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a></th>
       </tr>
       
       <tr><td colspan="3" style="padding:2px;">
        <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:100%" >
                <tbody>
                  <tr>
                    <th>Contract Customers</th>
                  </tr>
                  <tr>
                    <td style="padding:0"><!-- -------------------------------------- VESSEL 1 CONTAINER -------------------------------------------------- -->
                        <div id="customeridContainer" class="customerid">
                          <table cellpadding="0" cellspacing="0" class="mainApplTable" >
                            <tbody>
                              <tr>
                                <th class="TDShade" style="background-image:url(images/arrowblubg.gif);"> <div id="arrowrtc1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showcontract01();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div>
                                    <div id="arrowdnc1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidecontract01();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>
                                <td width="18%" class="TDShade" >Customer ID : </td>
                                <td width="32%" class="TDShadeBlue"><input name="Input" class="inputBox"  size="20"/>
                                  <a href="#"><img src="images/lookup.gif" alt="lookup Customer ID" width="13" height="13" border="0" align="absmiddle" /></a> </td>
                                <td width="15%" class="TDShade" ><strong>Name:</strong></td>
                                <td colspan="2" width="35%" class="TDShadeBlue">&nbsp;</td>
                                <td width="35%" class="TDShadeBlue">Status:</td>
                                <td width="35%" class="TDShadeBlue"><span style="text-align:right">
                                  <select name="sel3" class="selectionBox" id="sel3" style="background-color:#f6fcff">
                                    <option value="6000">Active</option>
                                    <option value="1000">Inactive</option>
                                  </select>
                                </span></td>
                                <td width="60" class="TDShadeBlue" align="right"><div id="tablefunction" style="width:60px; text-align:center; margin-right:0;"> <a href="#"><img src="images/add.gif" alt="Add Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#" onClick="javascript:copyvessel();"></a> </div></td>
                              </tr>
                            </tbody>
                          </table>
                          <!-- ----------------------------CONTRACT 1  ---------------------------------------- -->
                          <div id="contract01Container" class="contractContainer">
                           <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                                          <tr>
                                            <th width="30%">Schedulers: </th>
                                            <th width="45%">&nbsp;</th>
                                            <th width="20%">&nbsp;</th>
                                            <th width="50">&nbsp;</th>
                                          </tr>
                                          <tr>
                                            <td>Contact ID:
                                              <input name="Input112" class="inputBox" size="15"/>
                                            <img src="images/lookup.gif" alt="lookup Contact ID" width="13" height="13" border="0"></td>
                                            <td><a href="#"></a> Name: Name 01 </td>
                                            <td>Status:<span style="text-align:right">
                                              <select name="sel4" class="selectionBox" id="sel4" >
                                                <option value="6000">Active</option>
                                                <option value="1000">Inactive</option>
                                              </select>
                                            </span></td>
                                            <td width="50"><div id="div2" style="text-align:center; width:50px;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                                          <tr>
                                            <td>Contact ID:
                                              <input name="Input1122" class="inputBox" size="15"/>
                                            <img src="images/lookup.gif" alt="lookup Contact ID" width="13" height="13" border="0"></td>
                                            <td>Name: Name 02 </td>
                                            <td>Status:<span style="text-align:right">
                                              <select name="sel5" class="selectionBox" id="sel5" >
                                                <option value="6000">Active</option>
                                                <option value="1000">Inactive</option>
                                              </select>
                                            </span></td>
                                            <td><div id="div2" style="text-align:center"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                            </table>
                                        <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                                          <tr>
                                            <th width="30%">Billing Contacts </th>
                                            <th width="45%">&nbsp;</th>
                                            <th width="20%">&nbsp;</th>
                                            <th width="50">&nbsp;</th>
                                          </tr>
                                          <tr>
                                            <td>Contact ID:
                                              <input name="Input112" class="inputBox" size="15"/>
                                            <img src="images/lookup.gif" alt="lookup Contact ID" width="13" height="13" border="0"></td>
                                            <td><a href="#"></a> Name: Name 01 </td>
                                            <td>Status:<span style="text-align:right">
                                              <select name="sel6" class="selectionBox" id="sel6" >
                                                <option value="6000">Active</option>
                                                <option value="1000">Inactive</option>
                                              </select>
                                            </span></td>
                                            <td width="50"><div id="div2" style="text-align:center; width:50px;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                                          <tr>
                                            <td>Contact ID:
                                              <input name="Input1122" class="inputBox" size="15"/>
                                            <img src="images/lookup.gif" alt="lookup Contact ID" width="13" height="13" border="0"></td>
                                            <td>Name: Name 02 </td>
                                            <td>Status:<span style="text-align:right">
                                              <select name="select18" class="selectionBox" id="select5" >
                                                <option value="6000">Active</option>
                                                <option value="1000">Inactive</option>
                                              </select>
                                            </span></td>
                                            <td><div id="div2" style="text-align:center"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                            </table>
                                        <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                                          <tr>
                                            <th width="30%">Reporting Contacts </th>
                                            <th width="45%">&nbsp;</th>
                                            <th width="20%">&nbsp;</th>
                                            <th width="50">&nbsp;</th>
                                          </tr>
                                          <tr>
                                            <td>Contact ID:
                                              <input name="Input112" class="inputBox" size="15"/>
                                            <img src="images/lookup.gif" alt="lookup Contact ID" width="13" height="13" border="0"></td>
                                            <td><a href="#"></a> Name: Name 01 </td>
                                            <td>Status:<span style="text-align:right">
                                              <select name="select11" class="selectionBox" id="select4" >
                                                <option value="6000">Active</option>
                                                <option value="1000">Inactive</option>
                                              </select>
                                            </span></td>
                                            <td width="50"><div id="div2" style="text-align:center; width:50px;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                                          <tr>
                                            <td>Contact ID:
                                              <input name="Input1122" class="inputBox" size="15"/>
                                            <img src="images/lookup.gif" alt="lookup Contact ID" width="13" height="13" border="0"></td>
                                            <td>Name: Name 02 </td>
                                            <td>Status:<span style="text-align:right">
                                              <select name="select4" class="selectionBox" id="select7" >
                                                <option value="6000">Active</option>
                                                <option value="1000">Inactive</option>
                                              </select>
                                            </span></td>
                                            <td><div id="div2" style="text-align:center"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                            </table>
                          </div><!-- -------------CONTRACT 1 END ----------------------- -->
                        </div>
                      <!-- -------------------------------------- Contract 1 Container END --------------------------------------- -->                    </td>
                  </tr>
                  <tr>
                    <td style="padding:0;">
          
          <!-- ------------ Contract 2 CONTAINER ------------- -->
                        <div id="contract02" class="contracts">
                          <table cellpadding="0" cellspacing="0" class="mainApplTable" >
                            <tbody>
                              <tr>
                                <th class="TDShade" style="background-image:url(images/arrowblubg.gif);"> <div id="arrowrtc2" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showcontract02();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div>
                                    <div id="arrowdnc2" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidecontract02();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>
                                <td width="18%" class="TDShade" >Customer ID : </td>
                                <td width="32%" class="TDShadeBlue"><input name="Input" class="inputBox"  size="20"/>
                                  <a href="#"><img src="images/lookup.gif" alt="lookup Customer ID" width="13" height="13" border="0" align="absmiddle" /></a> </td>
                                <td width="15%" class="TDShade" ><strong>Name:</strong></td>
                                <td colspan="2" width="35%" class="TDShadeBlue">&nbsp;</td>
                                <td width="35%" class="TDShadeBlue">Status:</td>
                                <td width="35%" class="TDShadeBlue"><span style="text-align:right">
                                  <select name="sel7" class="selectionBox" id="sel7" style="background-color:#f6fcff">
                                    <option value="6000">Active</option>
                                    <option value="1000">Inactive</option>
                                  </select>
                                </span></td>
                                <td width="60" class="TDShadeBlue" align="right"><div id="tablefunction" style="width:60px; text-align:center; margin-right:0;"> <a href="#"><img src="images/add.gif" alt="Add Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#" onClick="javascript:copyvessel();"></a> </div></td>
                              </tr>
                            </tbody>
                          </table>
                          
                          <!-- ----------------------------CONTRACT 2  ---------------------------------------- -->
                          <div id="contract02Container" class="contractContainer">
                           <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                                          <tr>
                                            <th width="30%">Schedulers: </th>
                                            <th width="45%">&nbsp;</th>
                                            <th width="20%">&nbsp;</th>
                                            <th>&nbsp;</th>
                                          </tr>
                                          <tr>
                                            <td>Contact ID:
                                              <input name="Input112" class="inputBox" size="15"/>
                                            <img src="images/lookup.gif" alt="lookup Contact ID" width="13" height="13" border="0"></td>
                                            <td><a href="#"></a> Name: Name 01 </td>
                                            <td>Status:<span style="text-align:right">
                                              <select name="select5" class="selectionBox" id="select8" >
                                                <option value="6000">Active</option>
                                                <option value="1000">Inactive</option>
                                              </select>
                                            </span></td>
                                            <td width="50"><div id="div2" style="text-align:center; width:50px;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                                          <tr>
                                            <td>Contact ID:
                                              <input name="Input1122" class="inputBox" size="15"/>
                                            <img src="images/lookup.gif" alt="lookup Contact ID" width="13" height="13" border="0"></td>
                                            <td>Name: Name 02 </td>
                                            <td>Status:<span style="text-align:right">
                                              <select name="select6" class="selectionBox" id="select9" >
                                                <option value="6000">Active</option>
                                                <option value="1000">Inactive</option>
                                              </select>
                                            </span></td>
                                            <td><div id="div2" style="text-align:center"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                            </table>
                                        <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                                          <tr>
                                            <th width="30%">Billing Contacts </th>
                                            <th width="45%">&nbsp;</th>
                                            <th width="20%">&nbsp;</th>
                                            <th width="50">&nbsp;</th>
                                          </tr>
                                          <tr>
                                            <td>Contact ID:
                                              <input name="Input112" class="inputBox" size="15"/>
                                            <img src="images/lookup.gif" alt="lookup Contact ID" width="13" height="13" border="0"></td>
                                            <td><a href="#"></a> Name: Name 01 </td>
                                            <td>Status:<span style="text-align:right">
                                              <select name="select7" class="selectionBox" id="select10" >
                                                <option value="6000">Active</option>
                                                <option value="1000">Inactive</option>
                                              </select>
                                            </span></td>
                                            <td width="50"><div id="div2" style="text-align:center; width:50px;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                                          <tr>
                                            <td>Contact ID:
                                              <input name="Input1122" class="inputBox" size="15"/>
                                            <img src="images/lookup.gif" alt="lookup Contact ID" width="13" height="13" border="0"></td>
                                            <td>Name: Name 02 </td>
                                            <td>Status:<span style="text-align:right">
                                              <select name="select8" class="selectionBox" id="select11" >
                                                <option value="6000">Active</option>
                                                <option value="1000">Inactive</option>
                                              </select>
                                            </span></td>
                                            <td><div id="div2" style="text-align:center"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                            </table>
                                        <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                                          <tr>
                                            <th width="30%">Reporting Contacts </th>
                                            <th width="45%">&nbsp;</th>
                                            <th width="20%">&nbsp;</th>
                                            <th width="50">&nbsp;</th>
                                          </tr>
                                          <tr>
                                            <td>Contact ID:
                                              <input name="Input112" class="inputBox" size="15"/>
                                            <img src="images/lookup.gif" alt="lookup Contact ID" width="13" height="13" border="0"></td>
                                            <td><a href="#"></a> Name: Name 01 </td>
                                            <td>Status:<span style="text-align:right">
                                              <select name="select9" class="selectionBox" id="select12" >
                                                <option value="6000">Active</option>
                                                <option value="1000">Inactive</option>
                                              </select>
                                            </span></td>
                                            <td width="50"><div id="div2" style="text-align:center; width:50px;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                                          <tr>
                                            <td>Contact ID:
                                              <input name="Input1122" class="inputBox" size="15"/>
                                            <img src="images/lookup.gif" alt="lookup Contact ID" width="13" height="13" border="0"></td>
                                            <td>Name: Name 02 </td>
                                            <td>Status:<span style="text-align:right">
                                              <select name="select12" class="selectionBox" id="select13" >
                                                <option value="6000">Active</option>
                                                <option value="1000">Inactive</option>
                                              </select>
                                            </span></td>
                                            <td><div id="div2" style="text-align:center"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                            </table>
                          </div><!-- -------------CONTRACT 2 END ----------------------- -->
                        </div>
                      <!-- -------------------------- CONTRACT 2 CONTAINER END ---------------------------- -->           </td>
                  </tr>
                  <tr>
                    <td style="padding:0;">
          <!-- ------------------------------- Contract 3 CONTAINER ------------------------------ -->
                        <div id="contract03" class="contracts">
                          <table cellpadding="0" cellspacing="0" class="mainApplTable" >
                            <tbody>
                              <tr>
                                <th class="TDShade" style="background-image:url(images/arrowblubg.gif);"> <div id="arrowrtc3" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showcontract03();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div>
                                    <div id="arrowdnc3" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidecontract03();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>
                                <td width="18%" class="TDShade" >Customer ID : </td>
                                <td width="32%" class="TDShadeBlue"><input name="Input" class="inputBox"  size="20"/>
                                  <a href="#"><img src="images/lookup.gif" alt="lookup Customer ID" width="13" height="13" border="0" align="absmiddle" /></a> </td>
                                <td width="15%" class="TDShade" ><strong>Name:</strong></td>
                                <td colspan="2" width="35%" class="TDShadeBlue">&nbsp;</td>
                                <td width="35%" class="TDShadeBlue">Status:</td>
                                <td width="35%" class="TDShadeBlue"><span style="text-align:right">
                                  <select name="sel8" class="selectionBox" id="sel8" style="background-color:#f6fcff">
                                    <option value="6000">Active</option>
                                    <option value="1000">Inactive</option>
                                  </select>
                                </span></td>
                                <td width="60" class="TDShadeBlue" align="right"><div id="tablefunction" style="width:60px; text-align:center; margin-right:0;"> <a href="#"><img src="images/add.gif" alt="Add Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#" onClick="javascript:copyvessel();"></a> </div></td>
                              </tr>
                            </tbody>
                          </table>
                          <!-- ----------------------------CONTRACT 3  ---------------------------------------- -->
                          <div id="contract03Container" class="contractContainer">
                           <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                                          <tr>
                                            <th width="30%">Schedulers: </th>
                                            <th width="45%">&nbsp;</th>
                                            <th width="20%">&nbsp;</th>
                                            <th width="50">&nbsp;</th>
                                          </tr>
                                          <tr>
                                            <td>Contact ID:
                                              <input name="Input112" class="inputBox" size="15"/>
                                            <img src="images/lookup.gif" alt="lookup Contact ID" width="13" height="13" border="0"></td>
                                            <td><a href="#"></a> Name: Name 01 </td>
                                            <td>Status:<span style="text-align:right">
                                              <select name="select13" class="selectionBox" id="select14" >
                                                <option value="6000">Active</option>
                                                <option value="1000">Inactive</option>
                                              </select>
                                            </span></td>
                                            <td width="50"><div id="div2" style="text-align:center; width:50px;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                                          <tr>
                                            <td>Contact ID:
                                              <input name="Input1122" class="inputBox" size="15"/>
                                            <img src="images/lookup.gif" alt="lookup Contact ID" width="13" height="13" border="0"></td>
                                            <td>Name: Name 02 </td>
                                            <td>Status:<span style="text-align:right">
                                              <select name="select14" class="selectionBox" id="select15" >
                                                <option value="6000">Active</option>
                                                <option value="1000">Inactive</option>
                                              </select>
                                            </span></td>
                                            <td><div id="div2" style="text-align:center"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                            </table>
                                        <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                                          <tr>
                                            <th width="30%">Billing Contacts </th>
                                            <th width="45%">&nbsp;</th>
                                            <th width="20%">&nbsp;</th>
                                            <th width="50">&nbsp;</th>
                                          </tr>
                                          <tr>
                                            <td>Contact ID:
                                              <input name="Input112" class="inputBox" size="15"/>
                                            <img src="images/lookup.gif" alt="lookup Contact ID" width="13" height="13" border="0"></td>
                                            <td><a href="#"></a> Name: Name 01 </td>
                                            <td>Status:<span style="text-align:right">
                                              <select name="select15" class="selectionBox" id="select16" >
                                                <option value="6000">Active</option>
                                                <option value="1000">Inactive</option>
                                              </select>
                                            </span></td>
                                            <td width="50"><div id="div2" style="text-align:center; width:50px;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                                          <tr>
                                            <td>Contact ID:
                                              <input name="Input1122" class="inputBox" size="15"/>
                                            <img src="images/lookup.gif" alt="lookup Contact ID" width="13" height="13" border="0"></td>
                                            <td>Name: Name 02 </td>
                                            <td>Status:<span style="text-align:right">
                                              <select name="select16" class="selectionBox" id="select17" >
                                                <option value="6000">Active</option>
                                                <option value="1000">Inactive</option>
                                              </select>
                                            </span></td>
                                            <td><div id="div2" style="text-align:center"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                            </table>
                                        <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                                          <tr>
                                            <th width="30%">Reporting Contacts </th>
                                            <th width="45%">&nbsp;</th>
                                            <th width="20%">&nbsp;</th>
                                            <th width="50">&nbsp;</th>
                                          </tr>
                                          <tr>
                                            <td>Contact ID:
                                              <input name="Input112" class="inputBox" size="15"/>
                                            <img src="images/lookup.gif" alt="lookup Contact ID" width="13" height="13" border="0"></td>
                                            <td><a href="#"></a> Name: Name 01 </td>
                                            <td>Status:<span style="text-align:right">
                                              <select name="select17" class="selectionBox" id="select18" >
                                                <option value="6000">Active</option>
                                                <option value="1000">Inactive</option>
                                              </select>
                                            </span></td>
                                            <td width="50"><div id="div2" style="text-align:center; width:50px;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                                          <tr>
                                            <td>Contact ID:
                                              <input name="Input1122" class="inputBox" size="15"/>
                                            <img src="images/lookup.gif" alt="lookup Contact ID" width="13" height="13" border="0"></td>
                                            <td>Name: Name 02 </td>
                                            <td>Status:<span style="text-align:right">
                                              <select name="select19" class="selectionBox" id="select19" >
                                                <option value="6000">Active</option>
                                                <option value="1000">Inactive</option>
                                              </select>
                                            </span></td>
                                            <td><div id="div2" style="text-align:center"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                            </table>
                          </div><!-- -------------CONTRACT 3 END ----------------------- -->
                        </div><!-- -----------CONTRACT 3 CONTAINER END ----------------- -->                    </td>
                  </tr>
                </tbody>
              </table>
        
        </td></tr>
        </table>
        
        
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td>&nbsp;</td>
                        <td style="text-align:right"><a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
      </div> <!-- ------------------------- TAB 2 CONTAINER END ----------------------------------------- -->
      
      <!-- ------------------------- TAB 3 CONTAINER ----------------------------------------- -->
            <div id="tab3" class="innercontent" >
      <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
      <tr><th width="70%">Contract ID <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> Description: Contract Description </th>
        <th width="25%"><span style="text-align:right">Status:
                  Active |
&nbsp;25/04/2006</span></th>
        <th width="5%" style="text-align:right"><a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a></th>
      </tr>
      <tr><td colspan="3" style="padding:2px;">
      <div id="attachments" style="visibility:hidden; display:none">
      <table cellpadding="0" cellspacing="0" width="100%" class="InnerApplTable">
      <tr><th>
      Attachment Details
      </th></tr>
      <tr><td style="padding-bottom:6px;padding-left:1px;">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" cols="11" class="secAppltable" style="margin-bottom:0px;" >
                    <tr valign="center">
                      <th width="15" scope="col">&nbsp;</th>
                      <th width="25%" align="left" scope="col">File Name </th>
                      <th scope="col" align="left">Type</th>
                      <th scope="col" align="left">Description</th>
                      <th width="80" align="left" scope="col">Required</th>
                      <th align="left" scope="col">Audience</th>
                      <th align="left" scope="col">Added By </th>
                      <th align="left" scope="col">Date/Time Added </th>
                      <th width="50" align="left" scope="col">&nbsp;</th>
                    </tr>
                    <tr valign="center">
                      <td align="center">1 </td>
                      <td align="left"><a href="#">Phoenix Development.doc</a> </td>
                      <td align="left"><select name="sel2" id="sel2" class="selectionBox">
                        <option selected>Admin</option>
                        <option>Billing</option>
                        <option>Forms</option>
                        <option>Operations</option>
                      </select>                      </td>
                      <td align="left"><a href="#">
                        <input name="textfield5" type="text" class="inputBox" size="20">
                      </a> </td>
                      <td align="left"><input type="checkbox" name="checkbox" value="checkbox"></td>
                      <td align="center"><select name="sel5" id="sel5" class="inputBox">
                        <option selected>All</option>
                        <option>Internal</option>
                        <option>External</option>
                        <option>None</option>
                      </select></td>
                      <td align="center">-</td>
                      <td>22/05/2006 10:05am </td>
                      <td style="text-align:center"><a href="#"><img src="images/icodel.gif" alt="Delete Attachment" width="12" height="14" border="0"></a></td>
                    </tr>
                </table>
        </td></tr>
        </table>  
      </div>
      </td></tr>
      </table>
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
            <tr>
              <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                <td><input name="Submit2" type="button" class="button1" value="Attach a File" onClick="popup_show('attachpop', 'attachpop_drag', 'attachpop_exit', 'screen-corner', 120, 20); hideIt();")>
                </td>
              </tr>
              </table></td>
            </tr>
        </table>          
      </div><!-- --------- TAB 3 CONTAINER END ------------- -->

            
      <!-- ------------------------- TAB 4 CONTAINER ----------------------------------------- -->
            <div id="tab4" class="innercontent" >
             <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
       <tr><th width="70%">Contract ID <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> Description: Contract Description </th>
         <th width="25%"><span style="text-align:right">Status:
                  Active |
&nbsp;25/04/2006</span></th>
         <th>&nbsp;</th>
         <th>&nbsp;</th>
         <th width="5%" style="text-align:right"><a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a></th>
       </tr>
       
       <tr><td colspan="5" style="padding:2px;">
        <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:100%" >
                <tbody>
                  <tr>
                    <th>Zones</th>
                  </tr>
                  <tr>
                    <td style="padding:0"><!-- -------------------------------------- VESSEL 1 CONTAINER -------------------------------------------------- -->
                        <div id="zoneContainer" class="customerid">
                          <table cellpadding="0" cellspacing="0" class="mainApplTable" >
                            <tbody>
                              <tr>
                                <th class="TDShade" style="background-image:url(images/arrowblubg.gif);" width="2%"> <div id="arrowrtz1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showzone();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div>
                                    <div id="arrowdnz1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidezone();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>

                                <td width="18%" class="TDShade" >Zone Name : </td>
                                <td width="32%" class="TDShadeBlue"><INPUT TYPE="radio" NAME="custom">Custom&nbsp;&nbsp;<input name="Input" class="inputBox"  size="20"/>                                </td>
                                <td class="TDShadeBlue" ><INPUT TYPE="radio" NAME="custom"><strong>Pricebook&nbsp;&nbsp;<select name="sel3" class="selectionBox" id="sel3" style="background-color:#f6fcff">
                                    <option value="6000">ZONEE01</option>
                                    <option value="1000">ZONEE02</option>
                                  </select></strong></td>
                                <td width="60" class="TDShadeBlue" align="right"><div id="tablefunction" style="width:60px; text-align:center; margin-right:0;"> <a href="#"><img src="images/add.gif" alt="Add Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#" onClick="javascript:copyvessel();"></a> </div></td>
                              </tr>
                            </tbody>
                          </table>
                          <!-- ----------------------------CONTRACT 1  ---------------------------------------- -->
                          <div id="zone01Container" class="contractContainer">
                           <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
               
                                          <tr>
                                            <th>Zone Adjustments: </th>
                                            <th>&nbsp;</th>
                                            <th>&nbsp;</th>
                                            <th width="50" colspan="5">&nbsp;</th>
                                          </tr>
                                          <tr>
                                            <td>Begin Date</td>
                                            <td>End Date</td>
                                            <td>Addl Discount</td>
                                            <td>Zone Discount</td>
                                            <td>Insp Discount</td>
                                            <td>Test Discount</td>
                                            <td>State Discount</td>
                                            <td width="50">&nbsp;</td>
                                          </tr>
                                          <tr>
                                            <td><input name="theDate4" type="text" class="inputBox" value="13/07/2007" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate4,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
            <div id="debug"></div></td>
                                            <td><input name="theDate5" type="text" class="inputBox" value="12/31/2999" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate5,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                                            <td><input name="Input" class="inputBox"  size="15"/></td>
                                            <td><input name="Input" class="inputBox"  size="15"/></td>
                                            <td><input name="Input" class="inputBox"  size="15"/></td>
                                            <td><input name="Input" class="inputBox"  size="15"/></td>
                                            <td><input name="Input" class="inputBox"  size="15"/></td>
                                            <td width="50"><div id="div2" style="text-align:center; width:50px;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
              
                            </table>
                           <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                                          <tr>
                                            <th>Branches: </th>
                                            <th>&nbsp;</th>
                                            <th>&nbsp;</th>
                                            <th width="50" colspan="3">&nbsp;</th>
                                          </tr>
                                          <tr>
                                            <td>Business Unit</td>
                                            <td>Branch Code</td>
                                            <td>Description</td>
                                            <td>Begin Date</td>
                                            <td>End Date</td>
                                            <td width="50">&nbsp;</td>
                                          </tr>
                                          <tr>
                                            <td><input name="Input112" class="inputBox" size="15"/>
                                            <a href="#" onClick="javascript:popup_show('bu', 'bu_drag', 'bu_exit', 'screen-corner', 120, 20); searchBUht(); hideBUsearch(); hideIt();popupboxenable();"><img src="images/lookup.gif" alt="Business Unit" width="13" height="13" border="0"></a></td>
                                            <td><input name="Input112" class="inputBox" size="15"/>
                                            <a href="#" onClick="javascript:popup_show('branchcode', 'branchcode_drag', 'branchcode_exit', 'screen-corner', 120, 20); searchbranchcodeht(); hidebranchcodesearch(); hideIt();popupboxenable();"><img src="images/lookup.gif" alt="Branch Code" width="13" height="13" border="0"></a></td>
                                            <td><input name="Input" class="inputBox"  size="20" readonly/></td>
                                            <td><input name="theDate4" type="text" class="inputBox" value="12/31/2999" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate4,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                                            <td><input name="theDate5" type="text" class="inputBox" value="12/31/2999" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate5,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                                            <td width="50"><div id="div2" style="text-align:center; width:50px;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                            </table>
                           <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                                          <tr>
                                            <th>Ports: </th>
                                            <th>&nbsp;</th>
                                            <th>&nbsp;</th>
                                            <th width="50" colspan="3">&nbsp;</th>
                                          </tr>
                                          <tr>
                                            <td>Port Code</td>
                                            <td>Begin Date</td>
                                            <td>End Date</td>
                                            <td width="50">&nbsp;</td>
                                          </tr>
                                          <tr>
                                            <td><input name="Input112" class="inputBox" size="20"/></td>
                                            <td><input name="theDate4" type="text" class="inputBox" value="12/31/2999" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate4,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                                            <td><input name="theDate5" type="text" class="inputBox" value="12/31/2999" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate5,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                                            <td width="50"><div id="div2" style="text-align:center; width:50px;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                            </table>
                          </div><!-- -------------CONTRACT 1 END ----------------------- -->
                        </div>
                      <!-- -------------------------------------- Contract 1 Container END --------------------------------------- -->                 

<!-- Zone 2 -->
                        <div id="zone1Container" class="customerid">
                          <table cellpadding="0" cellspacing="0" class="mainApplTable" >
                            <tbody>
                              <tr>
                                <th class="TDShade" style="background-image:url(images/arrowblubg.gif);" width="2%"> <div id="arrowrtz2" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showzone2();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div>
                                    <div id="arrowdnz2" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hidezone2();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>

                                <td width="18%" class="TDShade" >Zone Name : </td>
                                <td width="32%" class="TDShadeBlue"><INPUT TYPE="radio" NAME="custom">Custom&nbsp;&nbsp;<input name="Input" class="inputBox"  size="20"/>                                </td>
                                <td class="TDShadeBlue" ><INPUT TYPE="radio" NAME="custom"><strong>Pricebook&nbsp;&nbsp;<select name="sel3" class="selectionBox" id="sel3" style="background-color:#f6fcff">
                                    <option value="6000">ZONEE01</option>
                                    <option value="1000">ZONEE02</option>
                                  </select></strong></td>
                                <td width="60" class="TDShadeBlue" align="right"><div id="tablefunction" style="width:60px; text-align:center; margin-right:0;"> <a href="#"><img src="images/add.gif" alt="Add Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#" onClick="javascript:copyvessel();"></a> </div></td>
                              </tr>
                            </tbody>
                          </table>
                          <!-- ----------------------------CONTRACT 1  ---------------------------------------- -->
                          <div id="zone02Container" class="contractContainer">
                           <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
               
                                          <tr>
                                            <th>Zone Adjustments: </th>
                                            <th>&nbsp;</th>
                                            <th>&nbsp;</th>
                                            <th width="50" colspan="5">&nbsp;</th>
                                          </tr>
                                          <tr>
                                            <td>Begin Date</td>
                                            <td>End Date</td>
                                            <td>Addl Discount</td>
                                            <td>Zone Discount</td>
                                            <td>Insp Discount</td>
                                            <td>Test Discount</td>
                                            <td>State Discount</td>
                                            <td width="50">&nbsp;</td>
                                          </tr>
                                          <tr>
                                            <td><input name="theDate4" type="text" class="inputBox" value="13/07/2007" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate4,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a>
            <div id="debug"></div></td>
                                            <td><input name="theDate5" type="text" class="inputBox" value="12/31/2999" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate5,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                                            <td><input name="Input" class="inputBox"  size="15"/></td>
                                            <td><input name="Input" class="inputBox"  size="15"/></td>
                                            <td><input name="Input" class="inputBox"  size="15"/></td>
                                            <td><input name="Input" class="inputBox"  size="15"/></td>
                                            <td><input name="Input" class="inputBox"  size="15"/></td>
                                            <td width="50"><div id="div2" style="text-align:center; width:50px;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
              
                            </table>
                           <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                                          <tr>
                                            <th>Branches: </th>
                                            <th>&nbsp;</th>
                                            <th>&nbsp;</th>
                                            <th width="50" colspan="3">&nbsp;</th>
                                          </tr>
                                          <tr>
                                            <td>Business Unit</td>
                                            <td>Branch Code</td>
                                            <td>Description</td>
                                            <td>Begin Date</td>
                                            <td>End Date</td>
                                            <td width="50">&nbsp;</td>
                                          </tr>
                                          <tr>
                                            <td><input name="Input112" class="inputBox" size="15"/>
                                            <a href="#" onClick="javascript:popup_show('bu', 'bu_drag', 'bu_exit', 'screen-corner', 120, 20); searchBUht(); hideBUsearch(); hideIt();popupboxenable();"><img src="images/lookup.gif" alt="Business Unit" width="13" height="13" border="0"></a></td>
                                            <td><input name="Input112" class="inputBox" size="15"/>
                                            <a href="#" onClick="javascript:popup_show('branchcode', 'branchcode_drag', 'branchcode_exit', 'screen-corner', 120, 20); searchbranchcodeht(); hidebranchcodesearch(); hideIt();popupboxenable();"><img src="images/lookup.gif" alt="Branch Code" width="13" height="13" border="0"></a></td>
                                            <td><input name="Input" class="inputBox"  size="20" readonly/></td>
                                            <td><input name="theDate4" type="text" class="inputBox" value="12/31/2999" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate4,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                                            <td><input name="theDate5" type="text" class="inputBox" value="12/31/2999" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate5,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                                            <td width="50"><div id="div2" style="text-align:center; width:50px;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                            </table>
                           <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
                                          <tr>
                                            <th>Ports: </th>
                                            <th>&nbsp;</th>
                                            <th>&nbsp;</th>
                                            <th width="50" colspan="3">&nbsp;</th>
                                          </tr>
                                          <tr>
                                            <td>Port Code</td>
                                            <td>Begin Date</td>
                                            <td>End Date</td>
                                            <td width="50">&nbsp;</td>
                                          </tr>
                                          <tr>
                                            <td><input name="Input112" class="inputBox" size="20"/></td>
                                            <td><input name="theDate4" type="text" class="inputBox" value="12/31/2999" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate4,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                                            <td><input name="theDate5" type="text" class="inputBox" value="12/31/2999" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate5,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                                            <td width="50"><div id="div2" style="text-align:center; width:50px;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                            </table>
                          </div><!-- -------------CONTRACT 1 END ----------------------- -->
                        </div>
<!-- Zone 2 End -->

          </td>
                  </tr>
                </tbody>
              </table>
        
        </td></tr>
        </table>
        
        
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td>&nbsp;</td>
                        <td style="text-align:right"><a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
      </div> <!-- ------------------------- TAB 4 CONTAINER END ----------------------------------------- -->

      <!-- ------------------------- TAB 5 CONTAINER ----------------------------------------- -->
            <div id="tab5" class="innercontent" >
             <table width="100%" cellpadding="0" cellspacing="0" class="mainApplTable">
       <tr><th width="70%">Contract ID <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="padding-left:10px;padding-right:10px;"/> Description: Contract Description </th>
         <th width="25%"><span style="text-align:right">Status:
                  Active |
&nbsp;25/04/2006</span></th>
         <th>&nbsp;</th>
         <th>&nbsp;</th>
         <th width="5%" style="text-align:right">&nbsp;<!-- <a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a> --></th>
       </tr>
       
       <tr><td colspan="5" style="padding:2px;">
        <table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:100%" >
                <tbody>
                  <tr>
                    <th>Cargo Inspection</th>
                  </tr>
                  <tr>
                    <td style="padding:0"><!-- -------------------------------------- VESSEL 1 CONTAINER -------------------------------------------------- -->
                        <div id="ciContainer" class="customerid">
                          <table cellpadding="0" cellspacing="0" class="mainApplTable" >
                            <tbody>

                              <tr>
                                <th class="TDShade" style="background-image:url(images/arrowblubg.gif);"> <div id="arrowrtci1" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showci();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div>
                                    <div id="arrowdnci1" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hideci();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>
                                <td width="18%" class="TDShade" >Begin Date : </td>
                                <td width="32%" class="TDShadeBlue"><input name="theDate6" type="text" class="inputBox" value="13/07/2007" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate6,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                                <td width="21%" class="TDShade" ><strong>End Date :</strong></td>
                                <td colspan="2" width="25%" class="TDShadeBlue"><input name="theDate6" type="text" class="inputBox" value="13/07/2007" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate6,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                                <td width="60" class="TDShadeBlue" align="right"><div id="tablefunction" style="width:60px; text-align:center; margin-right:0;"> <a href="#"><img src="images/add.gif" alt="Add Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#" onClick="javascript:copyvessel();"></a> </div></td>
                              </tr>               
                
                            </tbody>
                          </table>
                          <!-- ----------------------------CONTRACT 1  ---------------------------------------- -->
                          <div id="ci01Container" class="contractContainer">
                           <table width="100%" cellpadding="0" cellspacing="0" border="0" align="center">
               <tr>
               <td class="TDShade" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Contract Specific&nbsp;<INPUT TYPE="checkbox" NAME="chk1"></td>
               <td class="TDShade">Vessel Max&nbsp;<INPUT TYPE="checkbox" NAME="chk1"></td>
               <td class="TDShade">Additional Vessel&nbsp;<INPUT TYPE="checkbox" NAME="chk1"></td>
               <td class="TDShade">Tow Max&nbsp;<INPUT TYPE="checkbox" NAME="chk1"></td>
               <td class="TDShade">Total # of Grades&nbsp;<INPUT TYPE="checkbox" NAME="chk1"></td>
               </tr>
               </table>
                           <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
               
                                          <tr>
                                            <th colspan="14">Rates: </th>
                                          </tr>
                                          <tr>
                                            <td colspan="14">Vessel Type: &nbsp;&nbsp;<select name="sel3" class="selectionBox" id="sel3">
                                    <option selected>values Inland Barge</option>
                                    <option>Seagoing Barge</option>
                                    <option>Tanker</option>
                                  </select></td>
                                          </tr>
                                          <tr>
                                            <td>Product Group</td>
                      <td>Zone</td>
                      <td>Base Price</td>
                      <td>Unit Price</td>
                      <td>Min Price</td>
                      <td>Addl Grade</td>
                      <td>&nbsp;</td>
                      <td>Units Included</td>
                      <td>Max Price</td>
                      <td>Min Range</td>
                      <td>Max Range</td>
                      <td>UOM</td>
                      <td>Contract Ref</td>
                      <td width="50">&nbsp;</td>
                                          </tr>
                                          <tr>
                                            <td><select name="sel3" class="selectionBox" id="sel3">
                                    <option selected>Crude Oil</option>
                                    <option>Ethanol</option>
                                  </select></td>
                      <td><select name="sel3" class="selectionBox" id="sel3">
                                    <option selected>All Zones</option>
                                    <option>ZONE01</option>
                                  </select></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><INPUT TYPE="checkbox" NAME="chk1"></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td width="50"><div id="div2" style="text-align:center; width:50px;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                                          <tr>
                                            <td><select name="sel3" class="selectionBox" id="sel3">
                                    <option selected>Crude Oil</option>
                                    <option>Ethanol</option>
                                  </select></td>
                      <td><select name="sel3" class="selectionBox" id="sel3">
                                    <option selected>All Zones</option>
                                    <option>ZONE01</option>
                                  </select></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><INPUT TYPE="checkbox" NAME="chk1"></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td width="50"><div id="div2" style="text-align:center; width:50px;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
              
                            </table>
                          </div><!-- -------------CONTRACT 1 END ----------------------- -->
                        </div>
                      <!-- -------------------------------------- Contract 1 Container END --------------------------------------- -->                 
   <!-- ci02 Container -->
            <div id="ci2Container" class="customerid">
                          <table cellpadding="0" cellspacing="0" class="mainApplTable" >
                            <tbody>

                              <tr>
                                <th class="TDShade" style="background-image:url(images/arrowblubg.gif);"> <div id="arrowrtci2" style="visibility:visible; position:absolute; z-index: 2; margin-left:4px"> <a href="#" onClick="javascript:showci2();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="4"/></a> </div>
                                    <div id="arrowdnci2" style="visibility:hidden;position:relative; z-index: 1; margin-top:6px "> <a href="#" onClick="javascript:hideci2();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="4"/></a> </div></th>
                                <td width="18%" class="TDShade" >Begin Date : </td>
                                <td width="32%" class="TDShadeBlue"><input name="theDate4" type="text" class="inputBox" value="13/07/2007" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate4,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                                <td width="21%" class="TDShade" ><strong>End Date :</strong></td>
                                <td colspan="2" width="25%" class="TDShadeBlue"><input name="theDate5" type="text" class="inputBox" value="13/07/2007" size="10" maxlength="12" style="width:60px;" />
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate5,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a></td>
                                <td width="60" class="TDShadeBlue" align="right"><div id="tablefunction" style="width:60px; text-align:center; margin-right:0;"> <a href="#"><img src="images/add.gif" alt="Add Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Delete Vessel" width="13" height="12" hspace="1px" border="0"/></a> <a href="#" onClick="javascript:copyvessel();"></a> </div></td>
                              </tr>               
                
                            </tbody>
                          </table>
                          <!-- ----------------------------CI1  ---------------------------------------- -->
                          <div id="ci02Container" class="contractContainer">
                           <table width="100%" cellpadding="0" cellspacing="0" border="0" align="center">
               <tr>
               <td class="TDShade" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Contract Specific&nbsp;<INPUT TYPE="checkbox" NAME="chk1"></td>
               <td class="TDShade">Vessel Max&nbsp;<INPUT TYPE="checkbox" NAME="chk1"></td>
               <td class="TDShade">Additional Vessel&nbsp;<INPUT TYPE="checkbox" NAME="chk1"></td>
               <td class="TDShade">Tow Max&nbsp;<INPUT TYPE="checkbox" NAME="chk1"></td>
               <td class="TDShade">Total # of Grades&nbsp;<INPUT TYPE="checkbox" NAME="chk1"></td>
               </tr>
               </table>
                           <table width="100%" cellpadding="0" cellspacing="0" class="secAppltable" style="width:96%; float:right;">
               
                                          <tr>
                                            <th colspan="14">Rates: </th>
                                          </tr>
                                          <tr>
                                            <td colspan="14">Vessel Type: &nbsp;&nbsp;<select name="sel3" class="selectionBox" id="sel3">
                                    <option selected>values Inland Barge</option>
                                    <option>Seagoing Barge</option>
                                    <option>Tanker</option>
                                  </select></td>
                                          </tr>
                                          <tr>
                                            <td>Product Group</td>
                      <td>Zone</td>
                      <td>Base Price</td>
                      <td>Unit Price</td>
                      <td>Min Price</td>
                      <td>Addl Grade</td>
                      <td>&nbsp;</td>
                      <td>Units Included</td>
                      <td>Max Price</td>
                      <td>Min Range</td>
                      <td>Max Range</td>
                      <td>UOM</td>
                      <td>Contract Ref</td>
                      <td width="50">&nbsp;</td>
                                          </tr>
                                          <tr>
                                            <td><select name="sel3" class="selectionBox" id="sel3">
                                    <option selected>Crude Oil</option>
                                    <option>Ethanol</option>
                                  </select></td>
                      <td><select name="sel3" class="selectionBox" id="sel3">
                                    <option selected>All Zones</option>
                                    <option>ZONE01</option>
                                  </select></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><INPUT TYPE="checkbox" NAME="chk1"></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td width="50"><div id="div2" style="text-align:center; width:50px;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
                                          <tr>
                                            <td><select name="sel3" class="selectionBox" id="sel3">
                                    <option selected>Crude Oil</option>
                                    <option>Ethanol</option>
                                  </select></td>
                      <td><select name="sel3" class="selectionBox" id="sel3">
                                    <option selected>All Zones</option>
                                    <option>ZONE01</option>
                                  </select></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><INPUT TYPE="checkbox" NAME="chk1"></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td><input name="Input112" class="inputBox" size="10"/></td>
                      <td width="50"><div id="div2" style="text-align:center; width:50px;"> <a href="#"><img src="images/add.gif" alt="Add row" width="13" height="12" hspace="1px" border="0"/></a> <a href="#"><img src="images/delete.gif" alt="Del Row" width="13" height="12" hspace="1px" border="0"/></a></div></td>
                                          </tr>
              
                            </table>
                          </div><!-- -------------CI1 END ----------------------- -->
                        </div>
<!-- CI02Container End -->

          </td>
                  </tr>
                </tbody>
              </table>
        
        </td></tr>
        </table>
        
        
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td>&nbsp;</td>
                        <td style="text-align:right"><!-- <a href="#"><img src="images/icosave.gif" alt="Save" width="14" height="14" border="0" /></a> -->&nbsp;</td>
                      </tr>
                    </table></td>
                </tr>
              </table>
      </div> <!-- ------------------------- TAB 5 CONTAINER END ----------------------------------------- -->


          </div>
        </div>
        <script type="text/javascript">
      
      //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
      dolphintabs.init("tabnav", 0)
      
      </script>
        <!-- -------------------------------- TAB CONTENT END ---------------------------------- -->
      </div>
      <!-- ------------------------ MAIN CONTAINER END --------------------------------------------------- -->
    </td>
  </tr>
</table>
</form:form>

<!-- --------------------------- Country Lookup ------------------------------------------------- -->
<div class="sample_popup" id="country" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="country_drag" style="width:430px; "> <img class="menu_form_exit"   id="country_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Select Country</div>
  <div class="menu_form_body"   style="width:430px; height:280px;">
    <form method="post" action="setup/popup.php">
      <table width="95%" border="0" align="center" class="InnerApplTable">
        <tr>
          <td valign="middle"  colspan="2"><iframe align="left" frameborder="0" style="padding:0px; height:230px;" width="100%" src="inc_countrylookup.html" scrolling="no" id="countryfr" name="countryfr" allowtransparency="yes" ></iframe></td>
        </tr>
        <tr>
          <td><input id="ok" type="button" value="OK" name="ok" class="button1" onClick="hidecountrylookup();"/>
            &nbsp;&nbsp;
            <input id="cancel" type="button" value="Cancel" name="cancel" class="button1" onClick="hidecountrylookup();"/>
          </td>
        </tr>
      </table>
    </form>
  </div>
</div>
</div>
<!-- --------------------------------- Country Lookup END ----------------------------------------- -->

<!-- --------------------------- Branch Code Lookup ------------------------------------------------- -->
<div class="sample_popup" id="branchcode" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="branchcode_drag" style="width:450px; "> <img class="menu_form_exit"   id="branchcode_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Search Branch Code</div>
  <div class="menu_form_body" id="branchcodebody"   style="width:450px; height:130px;">
    <form method="post" action="setup/popup.php">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
        <tr>
          <td width="25%"><strong>Business Unit: </strong></td>
          <td><input name="textfield" type="text" class="inputBox" size="30" /></td>
        </tr>
        <tr>
          <td><strong>Branch Code: </strong></td>
          <td><input name="textfield2" type="text" class="inputBox" size="30" /></td>
        </tr>
        <tr>
          <td><strong>Description:</strong></td>
          <td><input name="textfield3" type="text" class="inputBox" size="30" /></td>
        </tr>
        <tr>
          <td colspan="2"><input name="Button" type="button" class="button1" value="Search" onClick="searchbranchcode();"/>
            &nbsp;&nbsp;
            <input name="Button" type="button" class="button1" value="Cancel" onClick="hidebranchcode();popupboxclose();" /></td>
        </tr>
      </table>
       
    <div id="branchcodesearchresults" style="visibility:hidden; display:none;"><!--Search Results -->
    <br>&nbsp;&nbsp;<strong>Search Results</strong>
    <table width="98%" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:98%">
        <tr>
          <th>Business Unit </th>
          <th>Branch Code </th>
          <th>Description</th>
        </tr>
        <tr>
          <td><a href="#">US120-000001</a></td>
          <td><a href="#">US120-000001</a></td>
          <td>----------------</td>
        </tr>
        <tr>
          <td><a href="#">US120-000002</a></td>
          <td><a href="#">US120-000002</a></td>
          <td>----------------</td>
        </tr>
        <tr>
          <td><a href="#">US120-000003</a></td>
          <td><a href="#">US120-000003</a></td>
          <td>----------------</td>
        </tr>
        <tr>
          <td><a href="#">US120-000004</a></td>
          <td><a href="#">US120-000004</a></td>
          <td>----------------</td>
        </tr>
        <tr>
          <td><a href="#">US120-000005</a></td>
          <td><a href="#">US120-000005</a></td>
          <td>----------------</td>
        </tr>
      </table>
    </div><!--Search Results -->
      
    </form>
  </div>
</div>
</div>
<!-- --------------------------------- Branch Code Lookup END ----------------------------------------- -->

<!-- --------------------------- Business Unit Lookup ------------------------------------------------- -->
<div class="sample_popup" id="bu" style="visibility: hidden; display: none; z-index:2;">
  <div class="menu_form_header" id="bu_drag" style="width:450px; z-index:2; "> <img class="menu_form_exit"   id="bu_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Search Business Unit</div>
  <div class="menu_form_body" id="bubody"   style="width:450px; height:130px;">
    <form method="post" action="popup.php">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
        <tr>
          <td width="25%"><strong>Business Unit: </strong></td>
          <td><input name="textfield" type="text" class="inputBox" size="30" /></td>
        </tr>
        
        <tr>
          <td><strong>Company Name:</strong></td>
          <td><input name="textfield3" type="text" class="inputBox" size="30" /></td>
        </tr>
        <tr>
          <td colspan="2"><input name="Button" type="button" class="button1" value="Search" onClick="searchBU();"/>
            &nbsp;&nbsp;
            <input name="Button" type="button" class="button1" value="Cancel" onClick="hideBU();popupboxclose();" /></td>
        </tr>
      </table>
       
    <div id="busearchresults" style="visibility:hidden; display:none;"><!--Search Results -->
    <br>&nbsp;&nbsp;<strong>Search Results</strong>
    <table width="98%" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable" style="width:98%">
        <tr>
          <th width="30%">Business Unit </th>
          <th width="70%">Company Name </th>
        </tr>
        <tr>
          <td><a href="#">USA01</a></td>
          <td>Caleb Brett USA, Inc</td>
        </tr>
        <tr>
          <td><a href="#">UK001</a></td>
          <td>ITS Testing Services (UK), Limited</td>
        </tr>
      </table>
    </div><!--Search Results -->
      
    </form>
  </div>
</div>
</div>
<!-- --------------------------------- Business Unit Lookup END ----------------------------------------- -->

<!-- --------------------------- Attach Popup ------------------------------------------------- -->
<div class="sample_popup" id="attachpop" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="attachpop_drag" style="width:430px; "> <img class="menu_form_exit"   id="attachpop_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Attach a File</div>
  <div class="menu_form_body"   style="width:430px; height:100px;">
    <form action="setup/popup.php" method="post" enctype="multipart/form-data">
      <table width="95%" border="0" align="center" class="InnerApplTable">
        <tr>
          <td><input name="file" type="file" class="inputBox" size="50"></td>
        </tr>
        <tr>
          <td><input id="upload" type="button" value="Upload" name="upload" class="button1" onClick="hideattachpop();"/>
            &nbsp;&nbsp;
            <input id="cancel" type="button" value="Cancel" name="cancel" class="button1" onClick="hideattachCan();"/>
          </td>
        </tr>
      </table>
    </form>
  </div>
</div>
</div>
