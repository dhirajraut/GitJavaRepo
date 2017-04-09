<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<icb:list var="divisions">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
</icb:list>

<icb:list var="divisionBu">
  <icb:item>${icbfn:user().branch.businessUnit.organization.name}</icb:item>
  <icb:item>${command.branch.businessUnit.name}</icb:item>
</icb:list>

<c:set var="jobMeta" value="${icbfn:objectMeta(icbfn:user().branch.businessUnit.organization.name, command.jobType, command.class)}" />

<!-- ------------------------- MAIN OUTSIDE TABLE HOLDER --------------------------------------------------------- -->
<table border="0" cellpadding="0" cellspacing="0" class="MainTable">
  <tr>
    <td valign="top">
  <!-- ------------------------------------------ BREADCRUMB TRAIL ------------------------------------------------ -->
      <div id="breadcrumbContainer" >
        <table width="100%" border="0" cellpadding="0" cellspacing="0" background="images/intopbluetrailbg.jpg">
          <tr>
            <td width="25"><img src="images/inlfttrailcorner.gif" width="8" height="22"></td>
            <td><table height="22" border="0" align="left" cellpadding="0" cellspacing="0">
                <tr>
                  <td class="breadcrumbtrailActive" style="background:none; padding-left:5px;">&#9658; Entry </td>
                  <td class="breadcrumbtrailDeactive"> Job Instructions </td>
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
      <!-- ------------------------------------ BREADCRUMB TRAIL END ---------------------------------------- -->

      <form:form name="jobOrderEditForm" method="POST" action="edit_job.htm">
      <input type="hidden" name="refreshing" value="false" />
      <input type="hidden" name="_page" value="0" />

      <div style="width:auto;float:left;padding:5 5 5 40px;color:red;">
        <form:errors cssClass="error"/>
      </div>
      <!-- --------------------------------------- MAIN CONTAINER -------------------------------------------- -->
      <div id="MainContentContainer">
        <!-- TABS CONTENTS -->
        <div id="tabcontainer">
          <div id="tabnav">
            <ul>
              <li><a href="#" rel="tab1"><span>Entry Form</span></a></li>
              <li><a href="#" rel="tab2"><span>Add Customers </span></a></li>
            </ul>
          </div>
          <!-- Sub Menus container. Do not remove -->
          <div id="tab_inner">
              <!-- ----------- TAB 1 CONTAINER ------------------ -->
              <div id="tab1" class="innercontent">
                <table width="100%" cellpadding=0 cellspacing=0 class=mainApplTable>
                  <tbody>
                    <tr bgcolor=#ffffff>
                      <th colspan=3 width="65%">Header Details <img src="images/separator2.gif" width="2" height="27"  align="absmiddle" style="margin-left:10px;margin-right:10px;"/> Job ID: ${command.jobNumber} <form:hidden path="jobNumber" /><img src="images/separator2.gif" width="2" height="27" align="absmiddle" style="margin-left:10px; margin-right:10px;"/>Job Type: ${command.jobType} <form:hidden path="jobType" /></th>
                      <th width="30%" bgcolor="#ffffff" style="text-align:right">
                        <c:set var="fieldMeta" value="${icbfn:fieldMeta(jobMeta, 'jobStatus')}" />
                        <c:if test="${fieldMeta != null}">
                          <label for="${fieldMeta.name}"><f:message key="${fieldMeta.label}" />: </label>
                          <form:select cssClass="selectionBox" path="${fieldMeta.name}" items="${icbfn:dropdown('status', null)}" itemLabel="name" itemValue="value"/>
                          <form:errors path="${fieldMeta.name}" cssClass="error"/>
                        </c:if>
                      </th>
                      <th width="5%" bgcolor="#ffffff" style="text-align:right"><input type="image" src="images/right.gif" name="_target1" alt="Next" width="14" height="14" border="0" /></th>
                    </tr>
                    <tr>
                      <c:set var="fieldMeta" value="${icbfn:fieldMeta(jobMeta, 'branch.businessUnit.name')}" />
                      <c:if test="${fieldMeta != null}">
                        <td width="15%" class="TDShade"><strong><label for="${fieldMeta.name}"><f:message key="${fieldMeta.label}" />: </label></strong> </td>
                        <td width="35%" class="TDShadeBlue">
                          <span class="id_input">
                            ${command.branch.businessUnit.name}
                          </span>
                        </td>
                      </c:if>
                      <c:set var="fieldMeta" value="${icbfn:fieldMeta(jobMeta, 'branch.name')}" />
                      <c:if test="${fieldMeta != null}">
                        <td width="15%" class="TDShade"><strong><label for="${fieldMeta.name}"><f:message key="${fieldMeta.label}" />: </label></strong></td>
                        <td width="35%" colspan="2" class="TDShadeBlue">
                          ${command.branch.name}
                        </td>
                      </c:if>
                    </tr>
                    <tr>
                      <td class="TDShade"><strong>Operation:</strong></td>
                      <td class="TDShadeBlue"><select name="sel4" 
                                      size="1" class="selectionBox" 
                                      id="sel4" style="WIDTH: 240px">
                          <option value="" selected="selected">Select ...</option>
                          <option 
                                      value="ADDT">Additivation</option>
                          <option 
                                      value="AONY">Analysis only</option>
                          <option 
                                      value="ATMO">Atmospheric monitoring</option>
                          <option value="BUNK">Bunkering</option>
                          <option 
                                      value="CALB">Calibration</option>
                          <option 
                                      value="CONS">Consulting</option>
                          <option value="MARR">Control on MARPOL regulations</option>
                          <option value="CUCL">Customs clearance</option>
                          <option value="DEOP">Depot operations</option>
                          <option 
                                      value="DIS">Discharging</option>
                          <option 
                                      value="DKSP">Dock Supervision</option>
                          <option 
                                      value="DOCC">Document control</option>
                          <option 
                                      value="DRFT">Draft Survey</option>
                          <option 
                                      value="EXPD">Expediting</option>
                          <option 
                                      value="GONL">Gauging Only</option>
                          <option value="HBAN">Handblend &amp; Analysis</option>
                          <option value="HLDC">Hold Condition Survey</option>
                          <option value="HLDI">Hold Inspection</option>
                          <option 
                                      value="INV">Inventory</option>
                          <option 
                                      value="JESE">Jetty services</option>
                          <option 
                                      value="LIGH">Lightering</option>
                          <option 
                                      value="LNDP">Line Displacement</option>
                          <option 
                                      value="LOAD">Loading</option>
                          <option 
                                      value="PIPE">Pipeline</option>
                          <option 
                                      value="SAFE">Safety</option>
                          <option 
                                      value="SFCH">Safety checklist</option>
                          <option value="SAMP">Sample &amp; Analysis</option>
                          <option value="SS">Sample &amp; Shipping</option>
                          <option value="SPUD">Sample Pick Up / Delivery</option>
                          <option value="SASH">Sample storage &amp; handling</option>
                          <option 
                                      value="SONY">Sampling Only</option>
                          <option 
                                      value="SUBM">Submitted Sample(s)</option>
                          <option 
                                      value="TTSL">Tank Truck Sealing</option>
                          <option 
                                      value="TEAS">Technical assistance</option>
                          <option 
                                      value="TSTK">Test Kit(s)</option>
                          <option 
                                      value="TRAN">Transfer</option>
                          <option 
                                      value="TRNS">Transhipment</option>
                          <option value="VIS">Visual Inspection</option>
                        </select></td>
                      <td class="TDShade"><strong>Received 
                        By: </strong></td>
                      <td colspan="2" class="TDShadeBlue"><input class="inputBox" value="Brian Lerche" style="width:220px;"/>
                        <img  
                                      src="images/lookup.gif" width="13" height="13" /></td>
                    </tr>
                    <tr>
                      <td class="TDShade"><strong>Nomination Date:<span class="redstar">*</span></strong></td>
                      <td class="TDShadeBlue"><form:input cssClass="inputBox" path="jobFinishDate" size="10" maxlength="12"/>
                        <a href="#" onClick="displayCalendar(document.forms[0].jobFinishDate,'mm/dd/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a> &nbsp;&nbsp;&nbsp;&nbsp;Time:
                        <input name="textfield3" type="text" class="inputBox" size="8" maxlength="10" style="width:50px;" />
                        <input name="textfield22" type="text" class="inputBox" size="8" maxlength="10"  style="width:40px;"/>
                        <a href="#" ><img src="images/lookup.gif" width="13" height="13" border="0" /></a>
                        <div id="debug"></div></td>
                      <td class="TDShade"><strong>ETA:</strong></td>
                      <td colspan="2" class="TDShadeBlue"><input name="theDate2" type="text" class="inputBox" value="13/07/2007" size="10" maxlength="12" style="width:60px;">
                        <a href="#" onClick="displayCalendar(document.forms[0].theDate2,'dd/mm/yyyy',this)"> <img src="images/calendar.gif" width="15" height="17"  align="absmiddle" border="0"/></a> &nbsp;&nbsp;&nbsp;&nbsp;Time:
                        <input name="textfield" type="text" class="inputBox" size="8" maxlength="10" style="width:50px;"/>
                        <input name="textfield2" type="text" class="inputBox" size="8" maxlength="10"  style="width:40px;"/>
                        <a href="#" ><img src="images/lookup.gif" width="13" height="13" border="0" /></a> </td>
                    </tr>
                    <tr>
                      <td class="TDShade" ><strong>Towing 
                        Company:</strong></td>
                      <td class="TDShadeBlue" ><input name="ITS_NOM_WRK_ITS_AGENT2" type="text" class="inputBox" 
                                      id="ITS_NOM_WRK_ITS_AGENT2" size="20" 
                                      maxlength="30"/>
                        <a href="#" onClick="javascript:popup_show('towingco', 'towingco_drag', 'towingco_exit', 'screen-corner', 40, 80);showFrameTowingCo();searchTowinght();hideIt()"> <img src="images/lookup.gif" width="13" height="13" border="0" /></a></td>
                      <td class="TDShade"><strong>Telephone No.:</strong></td>
                      <td colspan="2" class="TDShadeBlue">&nbsp;</td>
                    </tr>
                    <tr>
                      <td class="TDShade"><strong>Shipping Agent:</strong></td>
                      <td class="TDShadeBlue">
                        <form:input path="shippingAgentName" cssClass="inputBox" size="20" maxlength="30"/>
                        <a href="#" onClick="javascript:popup_show('shipagent', 'shipagent_drag', 'shipagent_exit', 'screen-corner', 40, 80); searchShipaght();hideIt()"> <img src="images/lookup.gif" width="13" height="13" border="0"/></a> </td>
                      <td class="TDShade"><strong>Telephone No.:</strong></td>
                      <td colspan="2" class="TDShadeBlue">&nbsp;</td>
                    </tr>
                    <tr>
                      <td class="TDShade"><strong>Port/Location:</strong></td>
                      <td class="TDShadeBlue">
                        <input name="ITS_NOM_WRK_ITS_AGENT" class="inputBox" id="ITS_NOM_WRK_ITS_AGENT" size="20" maxlength="30"/>
                        </td>
                      <td class="TDShade"><strong>Service 
                        Location:</strong></td>
                      <td colspan="2" class="TDShadeBlue">
                        <input name="ITS_RECEIVED_VW_OPRDEFNDESC" class="inputBox" id="ITS_RECEIVED_VW_OPRDEFNDESC" size="20" maxlength="30"/>
                        <a href="#" onClick="javascript:popup_show('servloc', 'servloc_drag', 'servloc_exit', 'screen-corner', 40, 80);showFrameServLoc();searchTowinght(); hideIt();"> <img src="images/lookup.gif" width="13" height="13" border="0"/></a></td>
                    </tr>
                    <tr>
                      <td class="TDShade" style="border-bottom:#7c92be dashed 1px; "><strong>Vessel:</strong></td>
                      <td class="TDShadeBlue" style="border-bottom:#7c92be dashed 1px;">&nbsp;</td>
                      <td class="TDShade" style="border-bottom:#7c92be dashed 1px;"><strong>Product:</strong></td>
                      <td colspan="2" class="TDShadeBlue" style="border-bottom:#7c92be dashed 1px;">&nbsp;</td>
                    </tr>
                    <tr>
                      <td valign="top" class="TDShade" ><strong>Job 
                        Description:</strong></td>
                      <td colspan="4" class="TDShadeBlue"><textarea cols="90" rows="4" wrap="virtual" style="WIDTH: 645px;">loading / discharging / lightering / transfer / sample &amp; analysis of _________, _________, _________ at _________.</textarea></td>
                    </tr>
                  </tbody>
                </table>
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                  <tr>
                    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td><strong ><span class="redstar">*</span></strong> <span class="Font11pt">marked fields are mandatory</span> </td>
                          <td style="text-align:right"><input type="image" src="images/right.gif" name="_target1" alt="Next" width="14" height="14" border="0" /></td>
                        </tr>
                      </table></td>
                  </tr>
                </table>
              </div>
              <!-- ---------------------- TAB 1 CONTAINER END ---------------------------- -->
              <!-- ---------------------------------------------------------------------------------------------------------------------- -->
              <!-- ------------------------- TAB 2 CONTAINER ------------------------------- -->
              <div id="tab2" class="innercontent" >
                <table class=mainApplTable cellspacing=0 
                                      cellpadding=0 width="100%">
                  <tbody>
                    <tr bgcolor=#ffffff>
                      <th>Contract Entry </th>
                      <th style="text-align:right"><input type="image" src="images/right.gif" name="_target1" alt="Next" width="14" height="14" border="0" /></th>
                    </tr>
                    <tr>
                      <td colspan="4" class="TDShade" style="font-weight:normal;">Enter Contract ID or Description - Add   multiple Contracts by placing a semicolon between values
                        </td>
                    </tr>
                    <tr>
                      <td colspan="4" class="TDShade"><strong>Add Contract(s): </strong>
                        <input name="ITS_NOM_WRK_ITS_AGENT22" type="text" class="inputBox" 
                                      id="ITS_NOM_WRK_ITS_AGENT22" size="50" maxlength="100"/>
                        <label>
                        <input name="Submit" type="button" class="button1" value="Add"  onClick="popup_show('popup2', 'popup_drag2', 'popup_exit2', 'screen-corner', 40, 80);hideIt()");/>
                        </label>
                        </span></td>
                    </tr>
                  </tbody>
                </table>
                <!-- --------- Contract Details COntainer --------------------------------- -->
                <div id="contractdetails" style="visibility:hidden; display:none; " > <br>
                  <table width="100%" cellpadding="0" cellspacing="0" cols="1" dir="ltr" class="InnerApplTable" id="RO_LINEDISP_WRK$scroll$0">
                    <tbody>
                      <tr>
                        <th>Contract Details</th>
                        <th style="text-align:right"><a href="#">Customize</a>&nbsp;|&nbsp;<a href="#"></a>&nbsp;1 of   1&nbsp;</th>
                      </tr>
                      <tr>

                      <td colspan="2" style="padding-bottom:6px;padding-left:1px;">

                      <!-- Contract 1 -->
                      <div id="contract1" style="visibility:hidden; display:none;">
                        <table border="0" cellpadding="0" cellspacing="0" cols="11" class="secAppltable" style="margin-bottom:0px;" >
                          <tr valign="center">
                            <th width="16" rowspan="2" align="center" valign="top" scope="col" style="background-image:url(images/arrowblubg.gif);"> <div id="bluarrowright" style="visibility:visible; position:absolute; z-index: 1; margin-left:4px"> <a href="#" onClick="javascript:showOriginTable();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="6"/></a> </div>
                              <div id="bluarrowdown" style="visibility:hidden;position:relative; z-index: 0; margin-top:6px "> <a href="#" onClick="javascript:hideOriginTable();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="6"/></a> </div></th>
                            <th width="15" align="left" scope="col">&nbsp;</th>
                            <th width="30" scope="col">Nom</th>
                            <th width="150" align="left" scope="col">Customer Name </th>
                            <th scope="col" align="left">Contact</th>
                            <th scope="col" align="left">Contract ID </th>
                            <th scope="col" align="left">Contract Description </th>
                            <th width="20" align="left" scope="col">&nbsp;</th>
                            <th width="20" align="left" scope="col">&nbsp;</th>
                            <th width="20" align="left" scope="col">&nbsp;</th>
                          </tr>
                          <tr valign="center">
                            <td align="center" width="18">1 </td>
                            <td nowrap="nowrap" align="center"><input type="hidden" value="Y" name="RO_LINE_ITS_NOM_CUST_FLAG$chk$0" />
                              <input id="RO_LINE_ITS_NOM_CUST_FLAG$0" onClick="this.form.RO_LINE_ITS_NOM_CUST_FLAG$chk$0.value=(this.checked?'Y':'N');doFocus_win0(this,false,true);" tabindex="117" type="checkbox" checked="checked" value="Y" name="RO_LINE_ITS_NOM_CUST_FLAG$0" />
                            </td>
                            <td width="150" align="left">Astra Oil   Trading</td>
                            <td align="left"><a href="#" rel="contactdetail">Shelly Rayner</a> </td>
                            <td align="left"><a href="#">CBE-ASTRA OIL TRADING</a> </td>
                            <td align="left"><a href="#">ASTRAOT72703701</a> </td>
                            <td align="center"><a href="#" onClick="javascript:popup_show('addnote', 'addnote_drag', 'addnote_exit', 'screen-corner', 40, 80);hideIt();"><img src="images/icoaddnote.gif" alt="Add a note" width="18" height="15" border="0" /></a> </td>
                            <td align="center"><a href="#" onClick="javascript:popup_show('attach2', 'attach2_drag', 'attach2_exit', 'screen-corner', 40, 80);hideIt();"><img src="images/icoattach.gif" alt="Add an attachment" width="13" height="16" border="0" /></a></td>
                            <td align="center"><a href="#" onClick="javascript:delContract1();"><img src="images/icodel.gif" alt="Delete row" width="12" height="14" border="0" /></a></td>
                          </tr>
                        </table>
                        <div id="origintable" style="padding:0px; visibility:hidden; display:none;">

                        <table border="0" cellpadding="0" cellspacing="0" cols="11" class="secAppltable" style="margin-top:0px; border-top:none" >
                          <tr valign="center">
                            <td width="20" style="border-right:none;"><img src="images/spacer.gif" width="18" height="1" /></td>
                            <td width="18" style="border-right:none;"><img src="images/spacer.gif" width="15" height="1" /></td>
                            <td width="38"><img src="images/spacer.gif" width="31" height="1" /></td>
                            <td colspan="7" style="padding:0px;"><table border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="width:60%; margin:1px;">
                                <tr>
                                  <td width="150"><span style="height:20px;">Origin</span></td>
                                  <td><select name="sel1" size="1" class="selectionBox" id="sel1">
                                      <option value=""></option>
                                      <option value="EML" selected="selected">Email</option>
                                      <option value="FAX">Fax</option>
                                      <option value="IM">IM</option>
                                      <option value="MYCB">MyCB</option>
                                      <option value="PTRO">Petro</option>
                                      <option value="STOR">Stand Ordr</option>
                                      <option value="TRAN">Translink</option>
                                      <option value="VRBL">Verbal</option>
                                      <option value="WEB">Web</option>
                                      <option value="WEBP">Web Phone</option>
                                      <option value="WSAM">With Samp</option>
                                    </select></td>
                                </tr>
                                <tr>
                                  <td><span style="height:20px;">Main Reference</span></td>
                                  <td><input name="textfield" type="text" class="inputBox" size="30" /></td>
                                </tr>
                                <tr>
                                  <td>Reference 2 </td>
                                  <td><input name="textfield6" type="text" class="inputBox" size="30" /></td>
                                </tr>
                                <tr>
                                  <td>Reference 3 </td>
                                  <td><input name="textfield7" type="text" class="inputBox" size="30" /></td>
                                </tr>
                                <tr>
                                  <td>Reference 4 </td>
                                  <td><input name="textfield8" type="text" class="inputBox" size="30" /></td>
                                </tr>
                                <tr>
                                  <td>Reference 5 </td>
                                  <td><input name="textfield9" type="text" class="inputBox" size="30" /></td>
                                </tr>
                              </table>
                          </div>

                          </td>

                          </tr>

                        </table>
                      </div>
                      <!-- Contract 1 END -->
                      <!-- Contract 2 -->
                    <div id="contract2" style="visibility:hidden; display:none">
                      <table border="0" cellpadding="0" cellspacing="0" cols="11" class="secAppltable" style="margin-bottom:0px;" >
                        <tr valign="center">
                          <th width="16" rowspan="2" align="center" valign="top" scope="col" style="background-image:url(images/arrowblubg.gif);"> <div id="bluarrowrightot2" style="visibility:visible; position:absolute; z-index: 1; margin-left:4px"> <a href="#" onClick="javascript:showOriginTable2();"> <img src="images/bluarrowright.gif" width="8" height="16" border="0" vspace="6"/></a> </div>
                            <div id="bluarrowdownot2" style="visibility:hidden;position:relative; z-index: 0; margin-top:6px "> <a href="#" onClick="javascript:hideOriginTable2();"> <img src="images/bluarrowdown.gif" width="16" height="8" border="0" vspace="6"/></a> </div></th>
                          <th width="15" align="left" scope="col">&nbsp;</th>
                          <th width="30" scope="col">Nom</th>
                          <th width="150" align="left" scope="col">Customer Name </th>
                          <th scope="col" align="left">Contact</th>
                          <th scope="col" align="left">Contract ID </th>
                          <th scope="col" align="left">Contract Description </th>
                          <th width="20" align="left" scope="col">&nbsp;</th>
                          <th width="20" align="left" scope="col">&nbsp;</th>
                          <th width="20" align="left" scope="col">&nbsp;</th>
                        </tr>
                        <tr valign="center">
                          <td align="center" width="18">1 </td>
                          <td nowrap="nowrap" align="center"><input type="hidden" value="Y" name="RO_LINE_ITS_NOM_CUST_FLAG$chk$0" />
                            <input id="RO_LINE_ITS_NOM_CUST_FLAG$0" onClick="this.form.RO_LINE_ITS_NOM_CUST_FLAG$chk$0.value=(this.checked?'Y':'N');doFocus_win0(this,false,true);" tabindex="117" type="checkbox" checked="checked" value="Y" name="RO_LINE_ITS_NOM_CUST_FLAG$0" />
                          </td>
                          <td width="150" align="left">Bayou Steel </td>
                          <td align="left"><a href="#" rel="contactdetail">Shelly Rome</a> </td>
                          <td align="left"><a href="#">USA-BAYOU STEEL</a> </td>
                          <td align="left"><a href="#">BAYOU1125720001 </a> </td>
                          <td align="center"><a href="#" onClick="javascript:popup_show('addnote', 'addnote_drag', 'addnote_exit', 'screen-corner', 40, 80);hideIt();"><img src="images/icoaddnote.gif" alt="Add a note" width="18" height="15" border="0" /></a> </td>
                          <td align="center"><a href="#" onClick="javascript:popup_show('attach2', 'attach2_drag', 'attach2_exit', 'screen-corner', 40, 80);hideIt();"><img src="images/icoattach.gif" alt="Add an attachment" width="13" height="16" border="0" /></a></td>
                          <td align="center"><a href="#" onClick="javascript:hideContractDetails();"><img src="images/icodel.gif" alt="Delete row" width="12" height="14" border="0" /></a></td>
                        </tr>
                      </table>
                      <div id="origintable2" style="padding:0px; visibility:hidden; display:none;" >

                      <table border="0" cellpadding="0" cellspacing="0" cols="11" class="secAppltable" style="margin-top:0px; border-top:none" >
                        <tr valign="center">
                          <td width="20" style="border-right:none;"><img src="images/spacer.gif" width="18" height="1" /></td>
                          <td width="18" style="border-right:none;"><img src="images/spacer.gif" width="15" height="1" /></td>
                          <td width="38"><img src="images/spacer.gif" width="31" height="1" /></td>
                          <td colspan="7" style="padding:0px;"><table border="0" cellpadding="0" cellspacing="0" class="mainApplTable" style="width:60%; margin:1px;">
                              <tr>
                                <td width="150"><span style="height:20px;">Origin</span></td>
                                <td><select name="sel11" size="1" class="selectionBox" id="sel11">
                                    <option value=""></option>
                                    <option value="EML" selected="selected">Email</option>
                                    <option value="FAX">Fax</option>
                                    <option value="IM">IM</option>
                                    <option value="MYCB">MyCB</option>
                                    <option value="PTRO">Petro</option>
                                    <option value="STOR">Stand Ordr</option>
                                    <option value="TRAN">Translink</option>
                                    <option value="VRBL">Verbal</option>
                                    <option value="WEB">Web</option>
                                    <option value="WEBP">Web Phone</option>
                                    <option value="WSAM">With Samp</option>
                                  </select></td>
                              </tr>
                              <tr>
                                <td><span style="height:20px;">Main Reference</span></td>
                                <td><input name="textfield" type="text" class="inputBox" size="30" /></td>
                              </tr>
                              <tr>
                                <td>Reference 2 </td>
                                <td><input name="textfield6" type="text" class="inputBox" size="30" /></td>
                              </tr>
                              <tr>
                                <td>Reference 3 </td>
                                <td><input name="textfield7" type="text" class="inputBox" size="30" /></td>
                              </tr>
                              <tr>
                                <td>Reference 4 </td>
                                <td><input name="textfield8" type="text" class="inputBox" size="30" /></td>
                              </tr>
                              <tr>
                                <td>Reference 5 </td>
                                <td><input name="textfield9" type="text" class="inputBox" size="30" /></td>
                              </tr>
                            </table>
                        </div>

                        </td>

                        </tr>

                      </table>
                    </div>
                    <!-- Contract 2 END -->
                    </td>

                    </tr>

                    </tbody>

                  </table>
                </div>
                <!-- Contract Details Container End -->
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                  <tr>
                    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td align="right"></td>
                          <td style="text-align:right"><input type="image" src="images/right.gif" name="_target1" alt="Next" width="14" height="14" border="0" /></td>
                        </tr>
                      </table></td>
                  </tr>
                </table>
              </div>
              <!-- ------------------------------ TAB 2 CONTAINER END --------------------------------------- -->
          </div>
        </div>
        <script type="text/javascript">

              //tabtabs.init("ID_OF_TAB_MENU_ITSELF", SELECTED_INDEX)
              dolphintabs.init("tabnav", 0)

              </script>
        <!-- -------------------------------- TAB CONTENT END --------------------------------------- -->
      </div>
      <!-- ------------------------- MAIN CONTAINER END ------------------------------------------ -->

      </form:form>
   
    </td>
  </tr>
</table><!-- MAIN OUTSIDE TABLE HOLDER END ------------------------------------------------------- -->

<ajax:autocomplete
  baseUrl="shipping_agent.htm"
  source="shippingAgent.name"
  target="shippingAgent.name"
  className="autocomplete"
  minimumCharacters="1"
   />

<!-- LOOKUP BRANCH CODE -->
<div class="sample_popup"     id="popup1" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="popup_drag1"> <img class="menu_form_exit"   id="popup_exit1" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Lookup Branch Code </div>
  <div class="menu_form_body">
    <form method="post" action="popup.php">
      <table width="350" cellpadding="4" cellspacing="0"  align="center">
        <tr>
          <td><table width="350" cellpadding="4" cellspacing="0" class="mainApplTable">
              <tbody>
                <tr valign="top">
                  <td class="TDShade">SetID: </td>
                  <td class="TDShadeBlue"></td>
                  <td class="TDShadeBlue">USA01
                    <!-- ITS_DEPT_VW_SETID -->
                  </td>
                </tr>
                <tr valign="top">
                  <td class="TDShade">Location   Code: </td>
                  <td class="TDShadeBlue"><select name="ITS_DEPT_VW_LOCATION$op" class="selectionBox" tabindex="14" onChange="if (document.readyState == 'complete') {var tmp=this.options[this.selectedIndex].value;if (tmp=='9' || tmp=='10')submitAction_win0(this.form,this.name);}">
                      <option value="1" selected="selected">begins with</option>
                      <option value="8">contains</option>
                      <option value="2">=</option>
                      <option value="3">not   =</option>
                      <option value="4">&lt;</option>
                      <option value="6">&lt;=</option>
                      <option value="5">&gt;</option>
                      <option value="7">&gt;=</option>
                      <option value="9">between</option>
                      <option value="10">in</option>
                    </select>
                  </td>
                  <td class="TDShadeBlue"><input name="ITS_DEPT_VW_LOCATION" class="inputBox" id="ITS_DEPT_VW_LOCATION" size="10" maxlength="10" />
                  </td>
                </tr>
                <tr valign="top">
                  <td class="TDShade">Description: </td>
                  <td class="TDShadeBlue"><select name="ITS_DEPT_VW_DESCR$op" class="selectionBox" tabindex="17" onChange="if (document.readyState == 'complete') {var tmp=this.options[this.selectedIndex].value;if (tmp=='9' || tmp=='10')submitAction_win0(this.form,this.name);}">
                      <option value="1" selected="selected">begins with</option>
                      <option value="8">contains</option>
                      <option value="2">=</option>
                      <option value="3">not   =</option>
                      <option value="4">&lt;</option>
                      <option value="6">&lt;=</option>
                      <option value="5">&gt;</option>
                      <option value="7">&gt;=</option>
                      <option value="9">between</option>
                      <option value="10">in</option>
                    </select>
                  </td>
                  <td class="TDShadeBlue"><input name="ITS_DEPT_VW_DESCR" class="inputBox" id="ITS_DEPT_VW_DESCR" tabindex="18" size="10" maxlength="30" />
                  </td>
                </tr>
              </tbody>
            </table></td>
        </tr>
        <tr>
          <td><input name="#ICSearch" type="button" class="button1" id="#ICSearch" tabindex="20" title="Look up (Alt+1)" onClick="javascript:submitAction_win0(document.win0, '#ICSearch');" value="Look Up" alt="Look up (Alt+1)" psaccesskey="\" />
            &nbsp;
            <input name="#ICClear" type="button" class="button1" id="#ICClear" tabindex="21" title="Clear" onClick="javascript:submitAction_win0(document.win0, '#ICClear');" value="Clear" alt="Clear" />
            &nbsp;
            <input name="#ICCancel" type="button" class="button1" id="#ICCancel" tabindex="22" title="Cancel (Esc)" onClick="javascript:submitAction_win0(document.win0, '#ICCancel');" value="Cancel" alt="Cancel (Esc)" />
            &nbsp; <a href="javascript: submitAction_win0(document.win0,'#ICAdvSearch');" name="#ICAdvSearch" id="#ICAdvSearch" tabindex="23">Basic Lookup</a></td>
        </tr>
        <tr>
          <td><strong>Search   Results</strong></td>
        </tr>
        <tr>
          <td><table width="350" cellpadding="1" cellspacing="0" dir="ltr" class="mainApplTable">
              <tbody>
                <tr valign="baseline">
                  <td align="left" background="images/intablehdrgreybg.gif" bgcolor="#99CCFF"><strong>View All </strong>&nbsp;&nbsp;&nbsp;</td>
                  <td align="right" valign="baseline" background="images/intablehdrgreybg.gif" bgcolor="#99CCFF">First &nbsp;<img src="images/PT_PREVIOUSROW_D_1.gif" alt="Show previous rows (inactive button) (Alt+,)" name="PrevPage" align="middle" id="PrevPage" title="Show previous rows (inactive button) (Alt+,)" /> &nbsp;1-78 of 78 &nbsp;<img src="images/PT_NEXTROW_D_1.gif" alt="Show next rows (inactive button) (Alt+.)" name="NextPage" align="middle" id="NextPage" title="Show next rows (inactive button) (Alt+.)" /> &nbsp;Last </td>
                </tr>
                <tr align="left">
                  <td bgcolor="#666666" scope="col" style="background-color:#999999"><a href="javascript:submitAction_win0(document.win0,'#ICSortCol1');" name="#ICSortCol1" title="Click column heading to sort ascending" class="style1" id="#ICSortCol1" tabindex="24">Location Code</a></td>
                  <td bgcolor="#666666" scope="col" style="background-color:#999999"><a href="javascript:submitAction_win0(document.win0,'#ICSortCol2');" name="#ICSortCol2" title="Click column heading to sort ascending" class="style1" id="#ICSortCol2" tabindex="25">Description</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow0');" name="SEARCH_RESULT1" id="SEARCH_RESULT1" tabindex="26">US012</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow0');" name="RESULT$0$2" id="RESULT$0$2">US Country Overhead</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow1');" name="RESULT$1$1" id="RESULT$1$1" tabindex="27">US100</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow1');" name="RESULT$1$2" id="RESULT$1$2">Baltimore, MD, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow2');" name="RESULT$2$1" id="RESULT$2$1" tabindex="28">US115</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow2');" name="RESULT$2$2" id="RESULT$2$2">Bayport, TX, USA - Lab</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow3');" name="RESULT$3$1" id="RESULT$3$1" tabindex="29">US120</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow3');" name="RESULT$3$2" id="RESULT$3$2">Boston, MA, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow4');" name="RESULT$4$1" id="RESULT$4$1" tabindex="30">US130</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow4');" name="RESULT$4$2" id="RESULT$4$2">Calibrations, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow5');" name="RESULT$5$1" id="RESULT$5$1" tabindex="31">US140</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow5');" name="RESULT$5$2" id="RESULT$5$2">Channelview, TX, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow6');" name="RESULT$6$1" id="RESULT$6$1" tabindex="32">US150</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow6');" name="RESULT$6$2" id="RESULT$6$2">Chicago, IL, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow7');" name="RESULT$7$1" id="RESULT$7$1" tabindex="33">US160</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow7');" name="RESULT$7$2" id="RESULT$7$2">Cincinnati, OH, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow8');" name="RESULT$8$1" id="RESULT$8$1" tabindex="34">US170</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow8');" name="RESULT$8$2" id="RESULT$8$2">Corpus Christi, TX, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow9');" name="RESULT$9$1" id="RESULT$9$1" tabindex="35">US185</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow9');" name="RESULT$9$2" id="RESULT$9$2">DP Special Projects - Westport</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow10');" name="RESULT$10$1" id="RESULT$10$1" tabindex="36">US200</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow10');" name="RESULT$10$2" id="RESULT$10$2">Gonzales, LA, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow11');" name="RESULT$11$1" id="RESULT$11$1" tabindex="37">US210</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow11');" name="RESULT$11$2" id="RESULT$11$2">Gretna, LA, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow12');" name="RESULT$12$1" id="RESULT$12$1" tabindex="38">US220</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow12');" name="RESULT$12$2" id="RESULT$12$2">Hawaii, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow13');" name="RESULT$13$1" id="RESULT$13$1" tabindex="39">US230</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow13');" name="RESULT$13$2" id="RESULT$13$2">Houston, TX, USA - OPSC</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow14');" name="RESULT$14$1" id="RESULT$14$1" tabindex="40">US240</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow14');" name="RESULT$14$2" id="RESULT$14$2">Jacksonville, FL, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow15');" name="RESULT$15$1" id="RESULT$15$1" tabindex="41">US250</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow15');" name="RESULT$15$2" id="RESULT$15$2">Lake Charles, LA, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow16');" name="RESULT$16$1" id="RESULT$16$1" tabindex="42">US260</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow16');" name="RESULT$16$2" id="RESULT$16$2">Los Angeles, CA, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow17');" name="RESULT$17$1" id="RESULT$17$1" tabindex="43">US270</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow17');" name="RESULT$17$2" id="RESULT$17$2">Louisville, KY, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow18');" name="RESULT$18$1" id="RESULT$18$1" tabindex="44">US280</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow18');" name="RESULT$18$2" id="RESULT$18$2">Memphis, TN, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow19');" name="RESULT$19$1" id="RESULT$19$1" tabindex="45">US290</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow19');" name="RESULT$19$2" id="RESULT$19$2">Mobile, AL, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow20');" name="RESULT$20$1" id="RESULT$20$1" tabindex="46">US300</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow20');" name="RESULT$20$2" id="RESULT$20$2">Nederland, TX, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow21');" name="RESULT$21$1" id="RESULT$21$1" tabindex="47">US310</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow21');" name="RESULT$21$2" id="RESULT$21$2">New Haven, CT, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow22');" name="RESULT$22$1" id="RESULT$22$1" tabindex="48">US320</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow22');" name="RESULT$22$2" id="RESULT$22$2">New Orleans, LA, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow23');" name="RESULT$23$1" id="RESULT$23$1" tabindex="49">US330</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow23');" name="RESULT$23$2" id="RESULT$23$2">Norfolk, VA, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow24');" name="RESULT$24$1" id="RESULT$24$1" tabindex="50">US340</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow24');" name="RESULT$24$2" id="RESULT$24$2">NY Harbor, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow25');" name="RESULT$25$1" id="RESULT$25$1" tabindex="51">US346</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow25');" name="RESULT$25$2" id="RESULT$25$2">NY Harbor, USA - SAT LAB</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow26');" name="RESULT$26$1" id="RESULT$26$1" tabindex="52">US350</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow26');" name="RESULT$26$2" id="RESULT$26$2">Philadelphia, PA, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow27');" name="RESULT$27$1" id="RESULT$27$1" tabindex="53">US360</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow27');" name="RESULT$27$2" id="RESULT$27$2">Pittsburgh, PA, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow28');" name="RESULT$28$1" id="RESULT$28$1" tabindex="54">US370</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow28');" name="RESULT$28$2" id="RESULT$28$2">Ponce</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow29');" name="RESULT$29$1" id="RESULT$29$1" tabindex="55">US380</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow29');" name="RESULT$29$2" id="RESULT$29$2">Portland, ME, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow30');" name="RESULT$30$1" id="RESULT$30$1" tabindex="56">US390</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow30');" name="RESULT$30$2" id="RESULT$30$2">Portland, OR, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow31');" name="RESULT$31$1" id="RESULT$31$1" tabindex="57">US400</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow31');" name="RESULT$31$2" id="RESULT$31$2">Pt Everglades, FL, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow32');" name="RESULT$32$1" id="RESULT$32$1" tabindex="58">US410</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow32');" name="RESULT$32$2" id="RESULT$32$2">San Francisco, CA, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow33');" name="RESULT$33$1" id="RESULT$33$1" tabindex="59">US420</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow33');" name="RESULT$33$2" id="RESULT$33$2">San Juan</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow34');" name="RESULT$34$1" id="RESULT$34$1" tabindex="60">US430</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow34');" name="RESULT$34$2" id="RESULT$34$2">Seattle Bellingham, WA, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow35');" name="RESULT$35$1" id="RESULT$35$1" tabindex="61">US440</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow35');" name="RESULT$35$2" id="RESULT$35$2">St Croix</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow36');" name="RESULT$36$1" id="RESULT$36$1" tabindex="62">US450</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow36');" name="RESULT$36$2" id="RESULT$36$2">St Louis, MO, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow37');" name="RESULT$37$1" id="RESULT$37$1" tabindex="63">US465</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow37');" name="RESULT$37$2" id="RESULT$37$2">Stolthaven, TX, USA - LAB</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow38');" name="RESULT$38$1" id="RESULT$38$1" tabindex="64">US470</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow38');" name="RESULT$38$2" id="RESULT$38$2">Tampa, FL, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow39');" name="RESULT$39$1" id="RESULT$39$1" tabindex="65">US480</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow39');" name="RESULT$39$2" id="RESULT$39$2">Texas City, TX, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow40');" name="RESULT$40$1" id="RESULT$40$1" tabindex="66">US490</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow40');" name="RESULT$40$2" id="RESULT$40$2">Freeport, TX, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow41');" name="RESULT$41$1" id="RESULT$41$1" tabindex="67">US500</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow41');" name="RESULT$41$2" id="RESULT$41$2">Toledo, OH, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow42');" name="RESULT$42$1" id="RESULT$42$1" tabindex="68">US510</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow42');" name="RESULT$42$2" id="RESULT$42$2">Valdez, AK, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow43');" name="RESULT$43$1" id="RESULT$43$1" tabindex="69">US520</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow43');" name="RESULT$43$2" id="RESULT$43$2">Wilmington, NC USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow44');" name="RESULT$44$1" id="RESULT$44$1" tabindex="70">US610</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow44');" name="RESULT$44$2" id="RESULT$44$2">Houston, TX, USA - Agri</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow45');" name="RESULT$45$1" id="RESULT$45$1" tabindex="71">US620</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow45');" name="RESULT$45$2" id="RESULT$45$2">New Orleans, LA, USA - Agri</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow46');" name="RESULT$46$1" id="RESULT$46$1" tabindex="72">US625</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow46');" name="RESULT$46$2" id="RESULT$46$2">Louisiana Grain Services</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow47');" name="RESULT$47$1" id="RESULT$47$1" tabindex="73">US630</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow47');" name="RESULT$47$2" id="RESULT$47$2">International - Agri Vancouver</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow48');" name="RESULT$48$1" id="RESULT$48$1" tabindex="74">US640</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow48');" name="RESULT$48$2" id="RESULT$48$2">FOG Services</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow49');" name="RESULT$49$1" id="RESULT$49$1" tabindex="75">US710</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow49');" name="RESULT$49$2" id="RESULT$49$2">Houston OCM</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow50');" name="RESULT$50$1" id="RESULT$50$1" tabindex="76">US720</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow50');" name="RESULT$50$2" id="RESULT$50$2">California Technical Center</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow51');" name="RESULT$51$1" id="RESULT$51$1" tabindex="77">US730</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow51');" name="RESULT$51$2" id="RESULT$51$2">Dow Catalyst Project, LA, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow52');" name="RESULT$52$1" id="RESULT$52$1" tabindex="78">US739</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow52');" name="RESULT$52$2" id="RESULT$52$2">ALTA-San Diego</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow53');" name="RESULT$53$1" id="RESULT$53$1" tabindex="79">US740</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow53');" name="RESULT$53$2" id="RESULT$53$2">ALTA-El Dorado Hills</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow54');" name="RESULT$54$1" id="RESULT$54$1" tabindex="80">US741</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow54');" name="RESULT$54$2" id="RESULT$54$2">ALTA-EDH-Discovery</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow55');" name="RESULT$55$1" id="RESULT$55$1" tabindex="81">US750</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow55');" name="RESULT$55$2" id="RESULT$55$2">CITGO</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow56');" name="RESULT$56$1" id="RESULT$56$1" tabindex="82">US755</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow56');" name="RESULT$56$2" id="RESULT$56$2">DTE Energy</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow57');" name="RESULT$57$1" id="RESULT$57$1" tabindex="83">US760</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow57');" name="RESULT$57$2" id="RESULT$57$2">AR - DIESEL LAB</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow58');" name="RESULT$58$1" id="RESULT$58$1" tabindex="84">US761</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow58');" name="RESULT$58$2" id="RESULT$58$2">AR - GAS LAB</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow59');" name="RESULT$59$1" id="RESULT$59$1" tabindex="85">US762</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow59');" name="RESULT$59$2" id="RESULT$59$2">AR - ATF LAB</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow60');" name="RESULT$60$1" id="RESULT$60$1" tabindex="86">US763</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow60');" name="RESULT$60$2" id="RESULT$60$2">AR - Chemistry LAB</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow61');" name="RESULT$61$1" id="RESULT$61$1" tabindex="87">US764</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow61');" name="RESULT$61$2" id="RESULT$61$2">AR - VFLETS</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow62');" name="RESULT$62$1" id="RESULT$62$1" tabindex="88">US765</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow62');" name="RESULT$62$2" id="RESULT$62$2">AR - Durability LAB</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow63');" name="RESULT$63$1" id="RESULT$63$1" tabindex="89">US766</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow63');" name="RESULT$63$2" id="RESULT$63$2">AR - OTHER</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow64');" name="RESULT$64$1" id="RESULT$64$1" tabindex="90">US769</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow64');" name="RESULT$64$2" id="RESULT$64$2">AR-Lincoln Park TL</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow65');" name="RESULT$65$1" id="RESULT$65$1" tabindex="91">US770</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow65');" name="RESULT$65$2" id="RESULT$65$2">KCS - Houma, LA (Measurement)</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow66');" name="RESULT$66$1" id="RESULT$66$1" tabindex="92">US771</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow66');" name="RESULT$66$2" id="RESULT$66$2">KCS-Lake Arthur,LA Measurement</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow67');" name="RESULT$67$1" id="RESULT$67$1" tabindex="93">US772</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow67');" name="RESULT$67$2" id="RESULT$67$2">KCS - Houma, LA (Valve)</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow68');" name="RESULT$68$1" id="RESULT$68$1" tabindex="94">US775</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow68');" name="RESULT$68$2" id="RESULT$68$2">LMS - Lake Charles,LA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow69');" name="RESULT$69$1" id="RESULT$69$1" tabindex="95">US785</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow69');" name="RESULT$69$2" id="RESULT$69$2">Deer Park, TX, USA</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow70');" name="RESULT$70$1" id="RESULT$70$1" tabindex="96">US790</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow70');" name="RESULT$70$2" id="RESULT$70$2">PARC Technical Services</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow71');" name="RESULT$71$1" id="RESULT$71$1" tabindex="97">US792</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow71');" name="RESULT$71$2" id="RESULT$71$2">WTC - Business Development</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow72');" name="RESULT$72$1" id="RESULT$72$1" tabindex="98">US793</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow72');" name="RESULT$72$2" id="RESULT$72$2">WTC- Formation Characteristics</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow73');" name="RESULT$73$1" id="RESULT$73$1" tabindex="99">US794</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow73');" name="RESULT$73$2" id="RESULT$73$2">WTC - Drilling Fluids</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow74');" name="RESULT$74$1" id="RESULT$74$1" tabindex="100">US795</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow74');" name="RESULT$74$2" id="RESULT$74$2">WTC - Bus. Support / Overhead</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow75');" name="RESULT$75$1" id="RESULT$75$1" tabindex="101">US796</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow75');" name="RESULT$75$2" id="RESULT$75$2">WTC - Reservoir Fluids</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow76');" name="RESULT$76$1" id="RESULT$76$1" tabindex="102">US797</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow76');" name="RESULT$76$2" id="RESULT$76$2">WTC - Geo Chemistry</a></td>
                </tr>
                <tr align="left">
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow77');" name="SEARCH_RESULTLAST" id="SEARCH_RESULTLAST" tabindex="103">US798</a></td>
                  <td nowrap="nowrap"><a href="javascript: submitAction_win0(document.win0,'#ICRow77');" name="RESULT$77$2" id="RESULT$77$2">WTC - Projects</a></td>
                </tr>
              </tbody>
            </table></td>
        </tr>
      </table>
    </form>
  </div>
</div>
</div>
<!-- LOOKUP BRANCH CODE END -->


<!-- Lookup Table 2 Contract Search -->
<div class="sample_popup"     id="popup2" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="popup_drag2" style="width:600px;"> <img class="menu_form_exit"   id="popup_exit2" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Contract Search Results </div>
  <div class="menu_form_body" style="overflow:auto; height:400px; width:600px;">
    <form method="post" action="popup.php">
      <table cellspacing="0" cellpadding="0" align="center" style="width:95%;" >
        <tr>
          <td height="24" colspan="3">Multiple matches   have been found. Select a contract to add it to the nomination. </td>
        </tr>
        <tr>
          <td valign="top" align="left" colspan="2"><table cellspacing="0" cols="4" class="LookupTable">
              <tbody>
                <tr valign="center">
                  <th ><a href="#" >Contract Description</a></th>
                  <th><a href="#" >Contract ID</a></th>
                  <th><a href="#" >Customer</a></th>
                  <th><a href="#" >Scheduler</a></th>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a href="#" onClick="javascript:showContract1();">CBE-ASTRA OIL TRADING </a></td>
                  <td align="left">ASTRAOT72703701 </td>
                  <td align="left">Astra Oil   Trading </td>
                  <td align="left">Shelly   Rayner </td>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a href="#" onClick="javascript:showContract2();">USA-BAYOU STEEL</a></td>
                  <td align="left">BAYOU1125720001 </td>
                  <td align="left">Bayou   Steel </td>
                  <td align="left">Shelley   Rome </td>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a href="#">BEL-BELGIAN SHELL NV </a> </td>
                  <td align="left">BELSHEL12803501 </td>
                  <td align="left">Belgian   Shell NV </td>
                  <td align="left">Herman De   Roo </td>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a href="#">BEL-BELGIAN SHELL NV </a> </td>
                  <td align="left">BELSHEL12803501 </td>
                  <td align="left">Belgian   Shell NV
                    < </td>
                  <td align="left">Mila   Lachman </td>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a href="#">NLD-BHARAT SHELL LTD </a>
                    <!-- DESCR50$4 -->
                  </td>
                  <td align="left">BHARSHE20903601
                    <!-- KEY1$4 -->
                  </td>
                  <td align="left">Bharat   Shell Limited
                    <!-- BO_NAME_CUST$4 -->
                  </td>
                  <td align="left">Akileshwar Jha
                    <!-- BO_NAME_CONTACT$4 -->
                  </td>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a href="#">USA-BP CRUDE W. HEMIS-EXCL WC</a>
                    <!-- DESCR50$5 -->
                  </td>
                  <td align="left">BP11175801
                    <!-- KEY1$5 -->
                  </td>
                  <td align="left">BP   Products North America, Inc.
                    <!-- BO_NAME_CUST$5 -->
                  </td>
                  <td align="left">Shelly   Gaither
                    <!-- BO_NAME_CONTACT$5 -->
                  </td>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a href="#">USA-BP PRODS WESTERN HEMISPHR</a>
                    <!-- DESCR50$6 -->
                  </td>
                  <td align="left">BP11175802
                    <!-- KEY1$6 -->
                  </td>
                  <td align="left">BP   Products North America, Inc.
                    <!-- BO_NAME_CUST$6 -->
                  </td>
                  <td align="left">Shelly   Gaither
                    <!-- BO_NAME_CONTACT$6 -->
                  </td>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a href="#">USA-CHEMICALS, INC. </a>
                    <!-- DESCR50$7 -->
                  </td>
                  <td align="left">CHEMICA12423001
                    <!-- KEY1$7 -->
                  </td>
                  <td align="left">Chemicals, Inc.
                    <!-- BO_NAME_CUST$7 -->
                  </td>
                  <td align="left">Barb   Shell
                    <!-- BO_NAME_CONTACT$7 -->
                  </td>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a id="DESCR50$8" title="Description" tabindex="55" href="javascript:submitAction_win0(document.win0,'DESCR50$8');" name="DESCR50$8">USA-CITGO REF &amp; CHEMICALS MST</a>
                    <!-- DESCR50$8 -->
                  </td>
                  <td align="left">CITGO12570001
                    <!-- KEY1$8 -->
                  </td>
                  <td align="left">Citgo   Refining and Chemicals Company, L.P.
                    <!-- BO_NAME_CUST$8 -->
                  </td>
                  <td align="left">Shelly   Williams
                    <!-- BO_NAME_CONTACT$8 -->
                  </td>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a id="DESCR50$9" title="Description" tabindex="58" href="javascript:submitAction_win0(document.win0,'DESCR50$9');" name="DESCR50$9">USA-CITGO REF &amp; CHEMICALS MST</a>
                    <!-- DESCR50$9 -->
                  </td>
                  <td align="left">CITGO12570001
                    <!-- KEY1$9 -->
                  </td>
                  <td align="left">Citgo   Petroleum Corporation
                    <!-- BO_NAME_CUST$9 -->
                  </td>
                  <td align="left">Shelly   Williams
                    <!-- BO_NAME_CONTACT$9 -->
                  </td>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a id="DESCR50$10" title="Description" tabindex="61" href="javascript:submitAction_win0(document.win0,'DESCR50$10');" name="DESCR50$10">GBR-DANSK SHELL</a>
                    <!-- DESCR50$10 -->
                  </td>
                  <td align="left">DANSKSH80203501
                    <!-- KEY1$10 -->
                  </td>
                  <td align="left">Dansk   Shell
                    <!-- BO_NAME_CUST$10 -->
                  </td>
                  <td align="left">Jan   Mortensen
                    <!-- BO_NAME_CONTACT$10 -->
                  </td>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a id="DESCR50$11" title="Description" tabindex="64" href="javascript:submitAction_win0(document.win0,'DESCR50$11');" name="DESCR50$11">USA-DOWNEY SHELL</a>
                    <!-- DESCR50$11 -->
                  </td>
                  <td align="left">DOWNEYS70140601
                    <!-- KEY1$11 -->
                  </td>
                  <td align="left">Downey   Shell
                    <!-- BO_NAME_CUST$11 -->
                  </td>
                  <td align="left">Elias   George
                    <!-- BO_NAME_CONTACT$11 -->
                  </td>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a id="DESCR50$12" title="Description" tabindex="67" href="javascript:submitAction_win0(document.win0,'DESCR50$12');" name="DESCR50$12">CB-EXXMOB INTL (CRUDE &amp; PROD)</a>
                    <!-- DESCR50$12 -->
                  </td>
                  <td align="left">EXMCR15595102
                    <!-- KEY1$12 -->
                  </td>
                  <td align="left">ExxonMobil Refining and Supply
                    <!-- BO_NAME_CUST$12 -->
                  </td>
                  <td align="left">*don't   use*Shelley *don't use*Amato
                    <!-- BO_NAME_CONTACT$12 -->
                  </td>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a id="DESCR50$13" title="Description" tabindex="70" href="javascript:submitAction_win0(document.win0,'DESCR50$13');" name="DESCR50$13">GBR-LQS CONTRACT SUNBURY</a>
                    <!-- DESCR50$13 -->
                  </td>
                  <td align="left">LQSSUNB02511501
                    <!-- KEY1$13 -->
                  </td>
                  <td align="left">Shell   Mali SA
                    <!-- BO_NAME_CUST$13 -->
                  </td>
                  <td align="left">Accounts   Payable
                    <!-- BO_NAME_CONTACT$13 -->
                  </td>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a id="DESCR50$14" title="Description" tabindex="73" href="javascript:submitAction_win0(document.win0,'DESCR50$14');" name="DESCR50$14">GBR-LQS CONTRACT SUNBURY</a>
                    <!-- DESCR50$14 -->
                  </td>
                  <td align="left">LQSSUNB02511501
                    <!-- KEY1$14 -->
                  </td>
                  <td align="left">Shell   (UK) Expro
                    <!-- BO_NAME_CUST$14 -->
                  </td>
                  <td align="left">Accounts   Payable
                    <!-- BO_NAME_CONTACT$14 -->
                  </td>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a id="DESCR50$15" title="Description" tabindex="76" href="javascript:submitAction_win0(document.win0,'DESCR50$15');" name="DESCR50$15">AUS-Mobil Oil (Fiji) Pty. Ltd.</a>
                    <!-- DESCR50$15 -->
                  </td>
                  <td align="left">MOBFIJI05120405
                    <!-- KEY1$15 -->
                  </td>
                  <td align="left">SHELL   FIJI LIMITED
                    <!-- BO_NAME_CUST$15 -->
                  </td>
                  <td align="left">not in   use not in use
                    <!-- BO_NAME_CONTACT$15 -->
                  </td>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a id="DESCR50$16" title="Description" tabindex="79" href="javascript:submitAction_win0(document.win0,'DESCR50$16');" name="DESCR50$16">USA-NATL. ASSOC. OF SHELL MKTE</a>
                    <!-- DESCR50$16 -->
                  </td>
                  <td align="left">NASM24132001
                    <!-- KEY1$16 -->
                  </td>
                  <td align="left">National   Assoc. of Shell Marketers (N.A.S.M.)
                    <!-- BO_NAME_CUST$16 -->
                  </td>
                  <td align="left">Jennifer   Richards
                    <!-- BO_NAME_CONTACT$16 -->
                  </td>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a id="DESCR50$17" title="Description" tabindex="82" href="javascript:submitAction_win0(document.win0,'DESCR50$17');" name="DESCR50$17">USA-NIEBAUM-COPPOLA WINERY</a>
                    <!-- DESCR50$17 -->
                  </td>
                  <td align="left">NIEBAUM70135901
                    <!-- KEY1$17 -->
                  </td>
                  <td align="left">Niebaum-Coppola Winery
                    <!-- BO_NAME_CUST$17 -->
                  </td>
                  <td align="left">Shelly   McKay
                    <!-- BO_NAME_CONTACT$17 -->
                  </td>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a id="DESCR50$18" title="Description" tabindex="85" href="javascript:submitAction_win0(document.win0,'DESCR50$18');" name="DESCR50$18">GBR-RICHISLAND TECHNOLOGY</a>
                    <!-- DESCR50$18 -->
                  </td>
                  <td align="left">RICHISL21509601
                    <!-- KEY1$18 -->
                  </td>
                  <td align="left">Nanjing   Richisland Information Tech Co Ltd
                    <!-- BO_NAME_CUST$18 -->
                  </td>
                  <td align="left">Shelly   Shi
                    <!-- BO_NAME_CONTACT$18 -->
                  </td>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a id="DESCR50$19" title="Description" tabindex="88" href="javascript:submitAction_win0(document.win0,'DESCR50$19');" name="DESCR50$19">NZ-THE SHELL COMPANY OF AUSTRA</a>
                    <!-- DESCR50$19 -->
                  </td>
                  <td align="left">SCOA01050404
                    <!-- KEY1$19 -->
                  </td>
                  <td align="left">The   Shell Company of Australia Limited
                    <!-- BO_NAME_CUST$19 -->
                  </td>
                  <td align="left">Les   Sarkady
                    <!-- BO_NAME_CONTACT$19 -->
                  </td>
                </tr>
                <tr valign="center">
                  <td align="left" height="14"><a id="DESCR50$20" title="Description" tabindex="91" href="javascript:submitAction_win0(document.win0,'DESCR50$20');" name="DESCR50$20">NZ-SHELL NEW ZEALAND LIMITED -</a>
                    <!-- DESCR50$20 -->
                  </td>
                  <td align="left">SHCHM01060626
                    <!-- KEY1$20 -->
                  </td>
                  <td align="left">SHELL NEW   ZEALAND LTD - SHELL CHEMICALS
                    <!-- BO_NAME_CUST$20 -->
                  </td>
                  <td align="left">WAYNE   THOMAS
                    <!-- BO_NAME_CONTACT$20 -->
                  </td>
                </tr>
              </tbody>
            </table></td>
        </tr>
        <tr>
          <td valign="top" align="left" colspan="2">&nbsp;</td>
        </tr>
      </table>
    </form>
  </div>
</div>
</div>
<!-- Lookup Table 2 Contract Search END -->



<!-- -------------------------- ADD NOTE POPUP ------------------------------------------------------- -->
<div class="sample_popup"     id="addnote" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="addnote_drag" style="width:500px; "> <img class="menu_form_exit"   id="addnote_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Add Notes </div>
  <div class="menu_form_body" style="width:500px; height:200px;">
    <form method="post" action="popup.php">
      <table width="95%" align="center" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
        <tr>
          <td>Added by: </td>
          <td>xxxxxxx</td>
          <td>DateTime Added: </td>
          <td>xx : xx : xxxx | xx:xx </td>
        </tr>
        <tr>
          <td>Summary:<span class="redstar">*</span></td>
          <td colspan="3"><input name="notesummary" class="inputBox" id="notesummary" tabindex="47" size="40" maxlength="100" /></td>
        </tr>
        <tr>
          <td>Details: </td>
          <td colspan="3"><textarea name="notedetails" cols="40" rows="4" wrap="virtual" id="notedetails" tabindex="49"></textarea></td>
        </tr>
        <tr>
          <td>Note Type: </td>
          <td><select name="select" size="1" class="selectionBox" id="select" tabindex="51" style="width: 110px;">
              <option value="" selected="selected">Select ...</option>
              <option value="BILL">Billing</option>
              <option value="CMMT">Comment</option>
              <option value="CALL">Customer Call</option>
              <option value="INVC">Invoice</option>
              <option value="PPRC">Pricing</option>
              <option value="RSRC">Research</option>
            </select></td>
          <td>Visibility:<span class="redstar">*</span></td>
          <td><select name="select2" size="1" class="selectionBox" id="select2" tabindex="52">
              <option value="ALL">Everywhere</option>
              <option value="OUT">External Only</option>
              <option value="INT" selected="selected">Internal   Only</option>
            </select></td>
        </tr>
        <tr>
          <td>Origin:</td>
          <td>Internal</td>
          <td>Related Line:</td>
          <td>1</td>
        </tr>
      </table>
      <table width="95%" align="center" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
        <tr>
          <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><input name="savenotes" type="button" title="Save Notes" value="Save Notes" class="button1" id="savenotes" onClick="hideAddNote();">
                  &nbsp;&nbsp;
                  <input name="attachfile" type="button" class="button1" id="attachfile" accesskey="F" tabindex="53" title="Add Attachment" onClick="popup_show('attach', 'attach_drag', 'attach_exit', 'screen-corner', 40, 80);hideIt();hideAddNote();" value="Attach a File" /></td>
                <td style="text-align:right"><a href="int_jobsinstructions.html"></a></td>
              </tr>
            </table></td>
        </tr>
      </table>
    </form>
  </div>
</div>
</div>
<!-- ------------------------------ ADD NOTE POPUP END --------------------------------------- -->


<!-- ----------------------------- ATTACH FILE POPUP  ---------------------------------------------- -->
<div class="sample_popup"     id="attach" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="attach_drag" style="width:550px; "> <img class="menu_form_exit"   id="attach_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Attach File(s) </div>
  <div class="menu_form_body" style="width:550px; height:200px;">
    <form method="post" action="popup.php">
    <table width="95%" align="center" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
    <tr>
      <td colspan="2" width="50%">Attached Files:</td>
      <td width="50%" rowspan="7" style="border-left:#999999 dashed 1px; padding-top:10px;">
    <form>
      <table border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td><label>
            <input type="file" name="file" />
            </label></td>
        </tr>
        <tr>
          <td><input type="file" name="file2" /></td>
        </tr>
        <tr>
          <td><input type="file" name="file3" /></td>
        </tr>
        <tr>
          <td><input type="file" name="file4" /></td>
        </tr>
        <tr>
          <td><input type="file" name="file5" /></td>
        </tr>
        <tr>
          <td><input name="Upload" type="Button" class="button1" id="Upload" value="Upload" />
            <input name="Cancel" type="reset" class="button1" id="Cancel" value="Cancel" onClick="hideAttach();"/></td>
        </tr>
      </table>
    </form>
    </td>
    </tr>
    <tr>
      <td width="5%" align="center"><label>
        <input type="checkbox" name="checkbox" value="checkbox" />
        </label></td>
      <td width="45%">Attachment 01 </td>
    </tr>
    <tr>
      <td align="center"><input type="checkbox" name="checkbox2" value="checkbox" /></td>
      <td>Attachment 02 </td>
    </tr>
    <tr>
      <td align="center"><input type="checkbox" name="checkbox3" value="checkbox" /></td>
      <td>Attachment 03 </td>
    </tr>
    <tr>
      <td align="center"><input type="checkbox" name="checkbox4" value="checkbox" /></td>
      <td>Attachment 04 </td>
    </tr>
    <tr>
      <td align="center"><input type="checkbox" name="checkbox5" value="checkbox" /></td>
      <td>Attachment 05 </td>
    </tr>
    <tr>
      <td>
        <input name="Delete" type="submit" class="button1" id="Delete" value="Delete Selected Attachment" /></td>
    </tr>
    </table>
    </form>
  </div>
</div>
</div>
<!-- ----------------------------------- ATTACH FILE POPUP END ------------------------------------- -->


<!-- ------------------------------------- ATTACH ~2~ FILE POPUP -------------------------------------- -->
<div class="sample_popup"     id="attach2" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="attach2_drag" style="width:550px; "> <img class="menu_form_exit"   id="attach2_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Attach File(s) </div>
  <div class="menu_form_body" style="width:550px; height:200px;">
    <form method="post" action="popup.php">
    <table width="95%" align="center" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
    <tr>
      <td colspan="2" width="50%">Attached Files:</td>
      <td width="50%" rowspan="7" style="border-left:#999999 dashed 1px; padding-top:10px;">
    <form>
      <table border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td><label>
            <input type="file" name="file" />
            </label></td>
        </tr>
        <tr>
          <td><input type="file" name="file2" /></td>
        </tr>
        <tr>
          <td><input type="file" name="file3" /></td>
        </tr>
        <tr>
          <td><input type="file" name="file4" /></td>
        </tr>
        <tr>
          <td><input type="file" name="file5" /></td>
        </tr>
        <tr>
          <td><input name="Upload" type="Button" class="button1" id="Upload" value="Upload" />
            <input name="Cancel" type="reset" class="button1" id="Cancel" value="Cancel" onClick="hideAttach2();"/></td>
        </tr>
      </table>
    </form>
    </td>
    </tr>
    <tr>
      <td width="5%" align="center"><label>
        <input type="checkbox" name="checkbox" value="checkbox" />
        </label></td>
      <td width="45%">Attachment 01 </td>
    </tr>
    <tr>
      <td align="center"><input type="checkbox" name="checkbox2" value="checkbox" /></td>
      <td>Attachment 02 </td>
    </tr>
    <tr>
      <td align="center"><input type="checkbox" name="checkbox3" value="checkbox" /></td>
      <td>Attachment 03 </td>
    </tr>
    <tr>
      <td align="center"><input type="checkbox" name="checkbox4" value="checkbox" /></td>
      <td>Attachment 04 </td>
    </tr>
    <tr>
      <td align="center"><input type="checkbox" name="checkbox5" value="checkbox" /></td>
      <td>Attachment 05 </td>
    </tr>
    <tr>
      <td colspan="2"><label></label>
        <input name="Delete" type="submit" class="button1" id="Delete" value="Delete Selected Attachment" /></td>
    </tr>
    </table>
    </form>
  </div>
</div>
</div>
<!-- ------------------------------- ATTACH ~2~ FILE POPUP END ----------------------------------------- -->


<!-- --------------------------- Shipping Agent Lookup ------------------------------------------------- -->
<div class="sample_popup" id="shipagent" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="shipagent_drag" style="width:520px; "> 
  <img class="menu_form_exit"   id="shipagent_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Select Shipping Agent </div>
  <div class="menu_form_body" id="shipagentbody"   style="width:520px; height:260px;">
    <form method="post" action="popup.php">
    <table width="95%" align="center" border="0" class="InnerApplTable">
    <tr>
    <td width="50%"><strong>Country:</strong></td>
    <td><strong>State:</strong></td>
  </tr>
  <tr>
    <td><select name="selcountry" size="10" class="selectionBox" id="selcountry" onclick="copyname('selcountry');">
      <option>ABW - Aruba</option>
      <option>AFG - Afghanistan</option>
      <option>AGO - Angola</option>
      <option>AIA - Anguilla </option>
      <option>ALB - Albania </option>
      <option>AND - Andorra </option>
      <option>ANT - Netherlands Antilles </option>
      <option>ARE - United Arab Emirates </option>
      <option>ARG - Argentina </option>
      <option>ARM - Armenia </option>
      <option>ASM - American Samoa </option>
      <option>ATA - Antarctica </option>
      <option>ATF - French Southern Territories </option>
      <option>ATG - Antigua and Barbuda </option>
      <option>AUS - Australia </option>
      <option>AUT - Austria </option>
      <option>AZE - Azerbaijan </option>
    </select></td>
    <td><label>
      <select name="selstate" size="10" class="selectionBox" id="selstate" onclick="copyname('selstate')">
      </select>
    </label></td>
  </tr>
  <tr>
    <td><strong>City:</strong></td>
    <td><label>
      <input name="textfield" type="text" size="25" class="inputBox"/>
    </label></td>
  </tr>
  <tr>
    <td><strong>Shipping Agent:</strong></td>
    <td><input name="textfield2" type="text" size="25" class="inputBox"/></td>
  </tr>
  <tr>
    <td valign="middle"  colspan="2"><input id="search" type="button" value="Search" name="search" class="button1" onClick="searchshipag();"/>&nbsp;&nbsp;<input id="cancel" type="button" value="Cancel" name="cancel" class="button1" onClick="hideShipAgent();"/>&nbsp;&nbsp;
    | <a href="#" onClick="popup_show('shipagentcreate', 'shipagentcreate_drag', 'shipagentcreate_exit', 'screen-corner', 40, 80);hideShipAgent2();hideIt();"><strong>Create New Shipping Agent</strong> </a></td>
  </tr>
</table>
    </form>
  <br>
  <div id="searchresultsshipag" style="visibility:hidden; display:none"><!--search results -->
  &nbsp;&nbsp;&nbsp;&nbsp;<strong>Search Results</strong>
  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
    
    
    <tr>
      <th width="40%">Shipping Agent </th>
      <th width="10%">Country</th>
      <th width="20%">City</th>
      <th width="10%">State</th>
      <th width="20%">Phone No. </th>
    </tr>
  <tr>
    <td><a href="#">Transmarine Navigation Corp</a> </td>
    <td><label>USA</label></td>
    <td>Walnut Creek </td>
    <td>CA</td>
    <td>925/932-3360</td>
  </tr>
  <tr>
    <td><a href="#">Transmarine Navigation Corp</a> </td>
    <td>USA</td>
    <td>Long Beach </td>
    <td>CA</td>
    <td>562/432-6941</td>
  </tr>
  <tr>
    <td><a href="#">Transmarine North Puget Sound</a> </td>
    <td>USA</td>
    <td>Seattle</td>
    <td>WA</td>
    <td>206/525-2051</td>
  </tr>
  <tr>
    <td><a href="#">Transmarine North Puget Sound</a> </td>
    <td>USA</td>
    <td>Anacortes</td>
    <td>WA</td>
    <td>360/299-9343</td>
  </tr>
  <tr>
    <td><a href="#">Transmarine North Puget Sound </a></td>
    <td>USA</td>
    <td>Bellingham</td>
    <td>WA</td>
    <td>360/527-8724</td>
  </tr>
</table>
  </div><!--search results -->
  <br>
  </div>
</div>
</div>
<!-- --------------------------------- Shipping Agent Lookup END ----------------------------------------- -->


<!-- --------------------------- Create New Shipping Agent Lookup ------------------------------------------------- -->
<div class="sample_popup" id="shipagentcreate" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="shipagentcreate_drag" style="width:475px; "> 
  <img class="menu_form_exit"   id="shipagentcreate_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Create New Shipping Agent </div>
  <div class="menu_form_body"   style="width:475px; height:340px;">
  <form method="post" action="popup.php">  
      
    
    <table width="95%" border="0" align="center" class="InnerApplTable">
  <tr>
    <td valign="middle"  colspan="2">
  <iframe align="left" frameborder="0" style="padding:0px; height:290px;" width="100%" src="inc_create_newshippingagent.html" scrolling="no" id="frame1" name="frame1" allowtransparency="yes" ></iframe>
  
  </td> 
  </tr>
  <tr><td>
  
    <input id="ok" type="button" value="OK" name="ok" class="button1" onClick="hideShipAgentCreate();showIt();"/>
      &nbsp;&nbsp;
      <input id="cancel" type="button" value="Cancel" name="cancel" class="button1" onClick="popup_show('shipagent', 'shipagent_drag', 'shipagent_exit', 'screen-corner', 40, 80);hideShipAgentCreate();"/>
      &nbsp;&nbsp;
      <input id="cancel2" type="button" value="Apply" name="cancel2" class="button1"/>

  </td></tr>
  
</table>

    </form>
  </div>
</div>
</div>
<!-- --------------------------------- Create New Shipping Agent Lookup END ----------------------------------------- -->

<!-- ----------------------------------- Towing Company Lookup ------------------------------------------------- -->
<div class="sample_popup" id="towingco" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="towingco_drag" style="width:475px; "> 
  <img class="menu_form_exit"   id="towingco_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Select Towing Company </div>
  <div class="menu_form_body" id="towingbody"   style="width:475px; height:280px;">
    <form method="post" action="popup.php">
    <table width="95%" border="0" align="center" class="InnerApplTable">
  <tr>
    <td valign="middle"  colspan="2">
  <iframe align="left" frameborder="0" style="padding:0px; height:220px;" width="100%" src="inc_towingcompany_lookup.html" scrolling="no" id="frame2" name="frame2" allowtransparency="yes" ></iframe>
  
  </td> 
  </tr>
  <tr><td>
  
 <input id="search" type="button" value="Search" name="search" class="button1" onClick="searchtowing();"/>
      &nbsp;&nbsp;
      <input id="cancel" type="button" value="Cancel" name="cancel" class="button1" onClick="hideTowingCo();"/>
      &nbsp;&nbsp;
      | &nbsp;<a href="#" onClick="popup_show('towingcocreate', 'towingcocreate_drag', 'towingcocreate_exit', 'screen-corner', 40, 80);hideTowingCo2();showFrameTowingCoCreate();hideTowingCo();hideIt();"><strong>Create New Towing Company </strong> </a>
    

  </td></tr>
  
</table>
    </form>
  <br>
  <div id="searchresultstowing" style="visibility:hidden; display:none"><!--search results -->
  &nbsp;&nbsp;&nbsp;&nbsp;<strong>Search Results</strong>
  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
    
    
    <tr>
      <th width="40%">Towing Company </th>
      <th width="10%">Country</th>
      <th width="20%">City</th>
      <th width="10%">State</th>
      <th width="20%">Phone No. </th>
    </tr>
  <tr>
    <td><a href="#">Foss Maritime Company  </a></td>
    <td><label>USA</label></td>
    <td>Richmond</td>
    <td>CA</td>
    <td>51/307-7820</td>
  </tr>
  <tr>
    <td><a href="#">Foss Maritime Company</a> </td>
    <td>USA</td>
    <td>Portland</td>
    <td>OR</td>
    <td>503/286-0631</td>
  </tr>
  <tr>
    <td><a href="#">Foss Maritime Company</a> </td>
    <td>USA</td>
    <td>Long Beach </td>
    <td>CA</td>
    <td>562/425-0171</td>
  </tr>
  <tr>
    <td><a href="#">Foss Maritime Company </a></td>
    <td>USA</td>
    <td>Seattle</td>
    <td>WA</td>
    <td>206/281-800</td>
  </tr>
  <tr>
    <td><a href="#">Foss Maritime Company</a> </td>
    <td>USA</td>
    <td>Bellingham</td>
    <td>WA</td>
    <td>360/527-8724</td>
  </tr>
</table>
  </div>
  
  
  </div>
</div>
</div>
<!-- --------------------------------- Towing Company Lookup END ----------------------------------------- -->


<!-- --------------------------- Create New Towing Company Lookup ------------------------------------------------- -->
<div class="sample_popup" id="towingcocreate" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="towingcocreate_drag" style="width:475px; "> 
  <img class="menu_form_exit"   id="towingcocreate_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Create New Towing Company </div>
  <div class="menu_form_body"   style="width:475px; height:340px;">
  <form method="post" action="popup.php">  
      
    
    <table width="95%" border="0" align="center" class="InnerApplTable">
  <tr>
    <td valign="middle"  colspan="2">
  <iframe align="left" frameborder="0" style="padding:0px; height:290px;" width="100%" src="inc_create_newtowingco.html" scrolling="no" id="frame3" name="frame3" allowtransparency="yes" ></iframe>
  
  </td> 
  </tr>
  <tr><td>
  
    <input id="ok" type="button" value="OK" name="ok" class="button1" onClick="hideTowingCoCreate(); showIt();"/>
      &nbsp;&nbsp;
      <input id="cancel" type="button" value="Cancel" name="cancel" class="button1" onClick="popup_show('towingco', 'towingco_drag', 'towingco_exit', 'screen-corner', 40, 80);hideTowingCoCreate();showFrameTowingCo();"/>
      &nbsp;&nbsp;
      <input id="cancel2" type="button" value="Apply" name="cancel2" class="button1"/>

  </td></tr>
  
</table>

    </form>
  </div>
</div>
</div>
<!-- --------------------------------- Create New Towing Company Lookup END ----------------------------------------- -->


<!-- ----------------------------------- Service Location Lookup ------------------------------------------------- -->
<div class="sample_popup" id="servloc" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="servloc_drag" style="width:475px; "> 
  <img class="menu_form_exit"   id="servloc_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Select Service Location </div>
  <div class="menu_form_body" id="servlocbody"   style="width:475px; height:280px;">
    <form method="post" action="popup.php">
    <table width="95%" border="0" align="center" class="InnerApplTable">
  <tr>
    <td valign="middle"  colspan="2">
  <iframe align="left" frameborder="0" style="padding:0px; height:220px;" width="100%" src="inc_serviceloc_lookup.html" scrolling="no" id="frame4" name="frame4" allowtransparency="yes" ></iframe>
  
  </td> 
  </tr>
  <tr><td>
  
 <input id="search" type="button" value="Search" name="search" class="button1" onClick="searchservloc();"/>
      &nbsp;&nbsp;
      <input id="cancel" type="button" value="Cancel" name="cancel" class="button1" onClick="hideServLoc();"/>
      &nbsp;&nbsp;
      | &nbsp;<a href="#" onClick="popup_show('servloccreate', 'servloccreate_drag', 'servloccreate_exit', 'screen-corner', 40, 80);hideServLoc2();showFrameServLocCreate();hideServLoc();hideIt();"><strong>Create New Service Location </strong> </a>
    

  </td></tr>
  
</table>
    </form>
  <br>
  <div id="searchresultsservloc" style="visibility:hidden; display:none"><!--search results -->
  &nbsp;&nbsp;&nbsp;&nbsp;<strong>Search Results</strong>
  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="InnerApplTable">
    
    
    <tr>
      <th width="40%">Service Location </th>
      <th width="20%">City</th>
      <th width="40%">Address</th>
      </tr>
  <tr>
    <td><a href="#">Petroleum Fuel &amp; Terminal Co.</a> </td>
    <td>Baltimore</td>
    <td>5101 Erdman Avenue, Baltimore, MD, 21205, USA </td>
    </tr>
  <tr>
    <td><a href="#">Petroleum Fuel &amp; Terminal Co.</a> </td>
    <td>Baltimore</td>
    <td>1622 South Clinton Street, Baltimore, MD, 21224, USA </td>
    </tr>
</table>
  </div><!--search results -->
  
  </div>
</div>
</div>
<!-- --------------------------------- Service Location Lookup END ----------------------------------------- -->

<!-- ----------------------------------- Service Location Create Lookup ------------------------------------------------- -->
<div class="sample_popup" id="servloccreate" style="visibility: hidden; display: none;">
  <div class="menu_form_header" id="servloccreate_drag" style="width:475px; "> 
  <img class="menu_form_exit"   id="servloccreate_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Create New Service Location </div>
  <div class="menu_form_body"   style="width:475px; height:340px;">
    <form method="post" action="popup.php">
    <table width="95%" border="0" align="center" class="InnerApplTable">
  <tr>
    <td valign="middle"  colspan="2">
  <iframe align="left" frameborder="0" style="padding:0px; height:290px;" width="100%" src="inc_create_newtowingco.html" scrolling="no" id="frame5" name="frame5" allowtransparency="yes" ></iframe>
  
  </td> 
  </tr>
  <tr><td>
  
    <input id="ok" type="button" value="OK" name="ok" class="button1" onClick="hideServLocCreate(); showIt();"/>
      &nbsp;&nbsp;
      <input id="cancel" type="button" value="Cancel" name="cancel" class="button1" onClick="popup_show('servloc', 'servloc_drag', 'servloc_exit', 'screen-corner', 40, 80);hideServLocCreate();showFrameServLoc();"/>
      &nbsp;&nbsp;
      <input id="cancel2" type="button" value="Apply" name="cancel2" class="button1"/>

  </td></tr>
  
</table>
    </form>
  </div>
</div>
</div>
<!-- --------------------------------- Service Location Create Lookup END ----------------------------------------- -->

<!-- -----------------------------------------Contact Details --------------------------------- -->
<div id="contactdetail" class="balloonstyle">
  <table width="500" border="0" cellpadding="0" cellspacing="0" class="InnerApplTable">
    <tr>
      <td colspan="4">Shelly Rayner</td>
    </tr>
    <tr>
      <td width="120">Scheduler ID:</td>
      <td width="150" style="font-weight:normal">21506</td>
      <td width="80">Company ID:</td>
      <td width="150" style="font-weight:normal">1320</td>
    </tr>
    <tr>
      <td>Scheduler Address:</td>
      <td colspan="3" style="font-weight:normal">1st   Floor, 10 Bruton Street, London, W1J 6PX, GBR </td>
    </tr>
    <tr>
      <td>Telephone:</td>
      <td colspan="3" style="font-weight:normal">334-2424-222</td>
    </tr>
    <tr>
      <td>Email Address:</td>
      <td colspan="3" style="font-weight:normal">xxxx@xxxx.com</td>
    </tr>
  </table>
</div>
<!-- -------------------------- Contact Details END ------------------------------------------ -->


