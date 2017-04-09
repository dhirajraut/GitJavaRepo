<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="icbfn" uri="http://www.intertek.com/jsp/jstl/functions" %>
<%@ taglib prefix="icb" uri="http://www.intertek.com/jsp/jstl/tags" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<script type="text/javascript" src="js/ce/common.js"></script>
<script type="text/JavaScript" src="js/mm_menu.js"></script>
<script type="text/javascript" src="js/ce/ce_select_charges.js"></script>

<form:form name="ceJobSelectChargesForm" method="POST" action="phx_ce_job_select_charges.htm">

<form:hidden path="refreshing" />      
<form:hidden path="productIndex" />      
<form:hidden path="joliIndex" />

<div class="redtext"><form:errors cssClass="error" /></div>
<table width="97%" height="80%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top">
<!------------------------------------------------------- BREADCRUMB TRAIL  -------------------------------------->
      <div id="breadcrumbContainer" >
        <table width="100%" border="0" cellpadding="0" cellspacing="0" background="images/intopbluetrailbg.jpg">
          <tr>
            <td width="25"><img src="images/inlfttrailcorner.gif" width="8" height="22"></td>
              <td><jsp:include page="ce_job_bread_crumb.jsp">
						<jsp:param name="jobNumber"
							value="${command.jobContract.jobOrder.jobNumber}" />
						<jsp:param name="pageNumber"
							value="${command.jobContract.jobOrder.pageNumber}" />
					</jsp:include></td>
          </tr>
        </table>
      </div>
 <!---------------------------------------------------- BREADCRUMB TRAIL END ---------------------------------------------------------->
      <!---------------------------------------------- MAIN CONTAINER -------------------------------------------------------------->
      <div id="MainContentContainer">
        <!-------------------------------------------------------- TABS CONTENTS ------------------------------------------------------->
        <div id="tabcontainer">
           <div id="tabnav">
            <ul>             
			  <li><a href="#" rel="tab2"><span><f:message key="ceGlobal"/></span></a></li>             
               </ul>
                 <label>
                   <select name="sel5" id="sel5" class="selectionBoxrt" onChange="MM_jumpMenu('parent',this,1)">
                    <option selected>Go to ... &gt;</option>
                      <option value="phx_job_entry_ce.htm?jobNumber=${command.jobNumber}"> <f:message key="entry"/></option>
                         <option value="phx_job_operational_info_ce.htm?jobNumber=${command.jobNumber}"><f:message key="jobInstructions"/></option>
                </select>
             </label>
           </div>
          <!--------------------------------- Sub Menus container. Do not remove ----------------------------------------------------->
          <div id="tab_inner">

			<c:if test="${command.isType3Project==false}">
			<!-- ------------------------- TAB 2 CONTAINER ----------------------------------------- -->
            <div id="tab2" class="innercontent" >
              <table class="mainApplTable" cellspacing="0" cellpadding="0" style="width:100%" >
                <tbody>
                  <tr>
                    <th width="76%">
                    	<f:message key="selectCharges"/>&nbsp;&nbsp;
                    	<img src="images/separator2.gif" width="2" height="27"  align="absmiddle"/>
                    	&nbsp;&nbsp;<f:message key="jobId"/>: ${command.jobNumber}&nbsp;&nbsp;
                    	<img src="images/separator2.gif" width="2" height="27"  align="absmiddle"/>&nbsp;&nbsp;
                    	<f:message key="jobStatus"/>: ${command.jobStatus}</th>
					<th>&nbsp;</th>
                    <th class="thr">
                    
                    <c:if test="${command.displayNewProductIcon}">
                        <a href="#"
							style="cursor: hand; text-decoration: none;"
							onMouseOver="doTooltip(event, '<a href=# onClick=onAddProduct()>Add New Product</a>')"
							onMouseOut="hideTip()"><img src="images/icoadddoc.gif"
							alt="Add Line Items" hspace="2" border="0" /></a>
		            </c:if>    
		                <a href="#">
		                	<img src="images/savennextbluarrow.gif" onClick="saveNextSelectCharges()" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page">
	              		</a>
	                    <a href="#">
	                    	<img src="images/icosave.gif" onClick="saveSelectCharges()" alt="Save" width="14" height="14" border="0" align="absmiddle" />
	                    </a>
                    </th>
                  </tr>
                  
				  <tr>
				  <td colspan="3" class="nopadding">

		<div id="divdata">
			<table class="mainApplTable" border="0" cellpadding="0" cellspacing="0" width="100%">
   			  <c:forEach items="${command.products}" var="product" varStatus="prodCounter">
											  	
									<tr>
										<th width="13" class="TDShadeGrey">
										<div id="bluarrowrightv1p1s1${prodCounter.index}"
											style="visibility: hidden; display: none; position: relative; z-index: 1; margin-top: 6px">
										<a href="#" onClick="javascript:showv1p1sample1Table(${prodCounter.index});">
										<img src="images/bluarrowrightsml.gif" width="7"
											height="13" border="0" vspace="6" /></a></div>
										<div id="bluarrowdownv1p1s1${prodCounter.index}"
											style="visibility: visible; display: block; position: relative; z-index: 1; margin-top: 6px">
										<a href="#" onClick="javascript:hidev1p1sample1Table(${prodCounter.index});">
										<img src="images/bluarrowdownsml.gif" width="13" height="7"
											border="0" vspace="6" /></a></div>
										</th>
										<th nowrap>Product ${prodCounter.index}:<span
											class="redstar">*</span> <a name="addProd01"></a> 
											<form:input
											cssClass="inputBox"
											path="products[${prodCounter.index}].description"
											id="products[${prodCounter.index}].description" size="10"
											maxlength="256" />
											<form:errors path="products[${prodCounter.index}].description" cssClass="redstar"/>
											</th>
										<th nowrap><strong>Product Group:<span
											class="redstar">*</span></strong> <form:select
											id="products[${prodCounter.index}].productGroup"
											path="products[${prodCounter.index}].productGroup"
											cssClass="selectionBox"
											items="${command.productGroupList}" itemLabel="name"
											itemValue="value"/>
											<form:errors path="products[${prodCounter.index}].productGroup" cssClass="redstar"/>
											</th>
										<th nowrap><strong>Product:<span
											class="redstar">*</span></strong> <form:select
											id="products[${prodCounter.index}].productName"
											path="products[${prodCounter.index}].productName"
											cssClass="selectionBox"
											items="${product.productList}" itemLabel="name"
											itemValue="value" />
											<form:errors path="products[${prodCounter.index}].productName" cssClass="redstar"/>
											</th>
											
										<ajax:select
                  							baseUrl="phx_ajax.htm"
                  							source="products[${prodCounter.index}].productGroup"
                  							target="products[${prodCounter.index}].productName"
                  							parameters="namedQuery=getProductByProdGroup,entity=com.intertek.phoenix.entity.Product,entityWrapper=com.intertek.phoenix.ajax.ArrayAjaxWrapper,~productGroup={products[${prodCounter.index}].productGroup}"
                  							parser="new ResponseXmlParser()"/> 
                  	
										<th colspan="10" class="thr" nowrap><div id="div22" style="width: 120px; text-align: right;">

										<a href="#" onMouseOver="doTooltip(event, '<a href=# onClick=updateSCAddTestIframeSrc(\'${command.jobNumber}\',\'${product.id}\');popAddTest();>Add Tests</a><br><a href=# onClick=updateSCAddManualTestIframeSrc(\'${command.jobNumber}\',\'${product.id}\');popAddTest();>Add Manual Tests</a><br><a href=# onClick=updateJobServicesIframeSrc(\'${command.jobNumber}\',\'${product.id}\');popJobServices();>Add Job Services</a>')" onMouseOut="hideTip()" >
						                    <img src="images/icoadddocgreen.gif" width="12" height="14" border="0" align="absmiddle"/>
		                				</a>
										
										&nbsp; 
										
										<a href="#"	onclick="javascript:onDeleteProduct('${prodCounter.index}');">
											<img src="images/delete.gif" alt="Delete Product" width="13" height="12" hspace="1px" border="0" align="absmiddle" />
									    </a>
										<a href="#"
											onClick="javascript:onCopyProduct('${prodCounter.index}','');"><img
											src="images/add.gif" alt="Copy Product" width="13"
											height="12" hspace="1px" border="0" align="absmiddle" /></a>
										</div></th>
									</tr>
			
			
			
				<tr>
				
				<td colspan="16" style="padding-left:1px;">
				
				<div id="descriptionContainer${prodCounter.index}" class="descriptionContainer">
				
				<table width="100%" cellpadding="0" border="0" cellspacing="0" class="InnerApplTable">
				  <tr>
				  	<th width="4%" class="dottedline"><f:message key="select"/></th>
					<th width="30%" class="dottedline"><f:message key="description"/></th>
					<th width="8%" class="dottedline"><a href="#" onClick="sortForm('${prodCounter.index}')"><f:message key="sort"/></a></th>
					<th width="10%" class="dottedline"><f:message key="baseCurrency"/>${command.baseCurrency}</th>
					<th width="10%" class="dottedline"><f:message key="billingCurrency"/>${command.billingCurrency}</th>
					<th width="8%" class="dottedline"><f:message key="Discount"/></th>
					<th width="10%" class="dottedline"><f:message key="billingAmount"/></th>
					<th width="10%" class="dottedline" nowrap><f:message key="extendedPrice"/>${command.billingCurrency}</th>					
					<th width="3%" class="dottedline" nowrap>&nbsp;</th>
					<th width="3%" nowrap>&nbsp;</th>
				  </tr>
   				<c:forEach items="${product.joliForms}" var="joliForm" varStatus="counter">
    				<tr>
    				<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px;">
						<form:checkbox id="checkbox1${counter.index}"  path="products[${prodCounter.index}].joliForms[${counter.index}].selectedFlag" disabled="${joliForm.isNotBillable}"/>
					</td>
    				<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px;">${joliForm.lineNumber} ${joliForm.testDescription} ${joliForm.description}</td>
					<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px;"><form:input id="sortJoli" path="products[${prodCounter.index}].joliForms[${counter.index}].sortNumber" size="1" />
								<span class="redtext"><form:errors path="products[${prodCounter.index}].joliForms[${counter.index}].sortNumber" cssClass="error"/></span></td>
								
					<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">
						<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${joliForm.baseCurrencyAmount}"/>&nbsp;</td>
					<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">
						<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${joliForm.billingCurrencyAmount}"/>&nbsp;</td>
					<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">${joliForm.discount}</td>
					<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">
						<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${joliForm.billingAmount}"/>&nbsp;</td>
					<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">
						<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${joliForm.extendedPrice}"/>&nbsp;</td>
					
					<td class="TDShadeBlue" style="border-right:#7c92be dashed 1px;" nowrap>
						<c:if test="${joliForm.showEditPriceIcon}">
							<div id="tablefunction" class="editpricediv"> 
								<a href="#" onclick="javascript:updateEditPriceIframeSrc(${joliForm.id});popup_show('editprice', 'editprice_drag', 'editprice_exit', 'screen-corner', 40, 80);showEditPrice();hideIt();popupboxenable();">							
									<img src="images/editprice.gif" alt="Edit Price" hspace="1px" border="0"/>
								</a>
							</div>
						</c:if>
					</td>
					<td class="TDShadeBlue" nowrap>	
						<c:if test="${joliForm.delete}">
							<a href="#"	onclick="javascript:onDeleteJoli(${joliForm.id});">
								<img src="images/delete.gif" alt="Delete Line" hspace="1px" border="0"/>
							</a>
						</c:if>
					</td>
    				</tr>
    				<c:forEach items="${joliForm.splitItems}" var="splitItem" varStatus="splitCounter">
    				<tr>
    				<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px;">
	    				<c:if test="${splitItem.invoiceable}">
							<form:checkbox id="checkbox2${splitCounter.index}"  path="products[${prodCounter.index}].joliForms[${counter.index}].splitItems[${splitCounter.index}].selectedFlag" disabled="${splitItem.isNotBillable}"/>
						</c:if>
						&nbsp;
					</td>
    				
    				<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px;">&nbsp;&nbsp; ${joliForm.lineNumber}.${splitItem.lineNumber} &nbsp;${splitItem.testDescription} ${splitItem.description}</td>
					<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px;"><form:input id="sortSplit" path="products[${prodCounter.index}].joliForms[${counter.index}].splitItems[${splitCounter.index}].sortNumber" size="1"/>
					<span class="redtext"><form:errors path="products[${prodCounter.index}].joliForms[${counter.index}].splitItems[${splitCounter.index}].sortNumber" cssClass="error"/></span>
					</td>
					<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">${splitItem.baseCurrencyAmount}</td>
					<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">${splitItem.billingCurrencyAmount}</td>
					<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">${splitItem.discount}</td>
					<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">${splitItem.billingAmount}</td>
					<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">${splitItem.extendedPrice}</td>
					
					<td nowrap class="TDShadeBlue" >
						<c:if test="${splitItem.showEditPriceIcon}">
							<div id="tablefunction" class="editpricediv"> 
								<a href="#" onclick="javascript:updateEditPriceIframeSrc(${splitItem.id});popup_show('editprice', 'editprice_drag', 'editprice_exit', 'screen-corner', 40, 80);showEditPrice();hideIt();popupboxenable();">							
									<img src="images/editprice.gif" alt="Edit Price" hspace="1px" border="0"/>
								</a>
							</div>
						</c:if>
					</td>
					<td class="TDShadeBlue" nowrap>	
						<c:if test="${splitItem.delete}">
							<a href="#"	onclick="javascript:onDeleteJoli(${splitItem.id});">
								<img src="images/delete.gif" alt="Delete Line" hspace="1px" border="0"/>
							</a>
						</c:if>
					</td>
					
    				</tr>
    				</c:forEach>
    				
    			</c:forEach>
    				
				</table>
				</div>
				
</td>
</tr>
<tr>
<td colspan="14" style="padding-left:1px;">
<div id="totalContainer${prodCounter.index}" class="totalContainer">

  			    <table class="mainApplTable" cellspacing="0" cellpadding="0" style="width:100%">
                    <tr>
                      <th width="97%" class="thr" style="padding-right:8px;">
						<f:message key="total"/> : ${product.extendedPriceTotal}                      
                      </th>
                      <th width="2%">&nbsp;</th>
					  
                    </tr>                  
                 </table>
                 </div>
    			</td>           
              </tr>
          </c:forEach>
             
          <tr>
          <td colspan="14" style="padding-left:1px;">
                
                
                
                <table class="InnerApplTable" cellspacing="0" cellpadding="0" width="100%">
                	<tr>
						<th width="6%" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">&nbsp;</th>
	    				<th width="46%" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:left;"><f:message key="depositReference"/></th>
						<th style="padding-left:12px;" width="7%" align="center" class="dottedline">
							<a href="#" onClick="sortDepositInv()"><f:message key="sort"/></a>
						</th>
						<th width="14%" align="center" class="dottedline"><f:message key="depositAmount"/></th>
						<th width="14%" align="center" class="dottedline"><f:message key="availableAmount"/></th>
						<th width="9%" align="center" class="dottedline"><f:message key="status"/></th>
						<th width="9%" align="center" class="dottedline">&nbsp;</th>
						<th width="9%" align="center" class="dottedline">&nbsp;</th>
						<th width="3%" nowrap>&nbsp;</th>
                	</tr>
	                <c:forEach items="${command.depositInvoiceForms}" var="depInvForm" varStatus="counter">

	    			 <tr>
						<td width="6%" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">
						<form:checkbox id="checkbox1${counter.index}"  path="depositInvoiceForms[${counter.index}].selected" disabled="${'INVOICED'==depInvForm.depositInvoice.status}"/></td>
	    				<td width="46%" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:left;">${depInvForm.depositReferenceNo}</td>
						<td style="padding-left:12px;" width="7%" align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px;">
							<form:input id="sortDep" path="depositInvoiceForms[${counter.index}].sortNumber" size="1" />
							<span class="redtext">
								<form:errors path="depositInvoiceForms[${counter.index}].sortNumber" cssClass="error"/>
							</span>
						</td>
						<td width="14%" align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">${depInvForm.depositAmount}</td>
						<td width="14%" align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">${depInvForm.availableAmount}</td>
						<td width="9%" align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">${depInvForm.depositInvoice.status}</td>
						<td width="9%" align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:left;" colspan="3">						  
						  <c:if test="${depInvForm.showEditAmount}">
							<div id="tablefunction" class="editamoountdiv"> 
								<a href="#" onclick="javascript:updateEditAmountIframeSrc(${counter.index});popup_show('editamount', 'editamount_drag', 'editamount_exit', 'screen-corner', 40, 80);showEditAmount();hideIt();popupboxenable();">							
									<img src="images/icoeditsmall.gif" alt="Edit Price" hspace="1px" border="0"/>
								</a>
							</div>
						  </c:if>
						
						</td>
	    			 </tr>
	    			</c:forEach>
    			</table>    				
              </td>
              </tr>
              </table>              
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td>&nbsp;</td>
                  <td class="tdr">
                  
                   <c:if test="${command.displayNewProductIcon}">
                        <a href="#"
							style="cursor: hand; text-decoration: none;"
							onMouseOver="doTooltip(event, '<a href=# onClick=onAddProduct()>Add New Product</a>')"
							onMouseOut="hideTip()"><img src="images/icoadddoc.gif"
							alt="Add Line Items" hspace="2" border="0" /></a>
		            </c:if>    
               		<a href="#">
                 		<img src="images/savennextbluarrow.gif"  onClick="saveNextSelectCharges()" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page">
                  	</a>
                  	<a href="#">
                  		<img src="images/icosave.gif" onClick="saveSelectCharges()" alt="Save" width="14" height="14" border="0" align="absmiddle" />
                  	</a>
                  </td>
                </tr>
              </table>
				</div>
				</td>
				</tr>
                </tbody>
              </table>

            </div>
		</c:if>
		<c:if test="${command.isType3Project}">
		<div id="tab2" class="innercontent" >
		<table class="mainApplTable" cellspacing="0" cellpadding="0" style="width:100%" >
                <tbody>
                  <tr>
                    <th width="76%">
                    	<f:message key="selectCharges"/>&nbsp;&nbsp;
                    	<img src="images/separator2.gif" width="2" height="27"  align="absmiddle"/>
                    	&nbsp;&nbsp;<f:message key="jobId"/>: ${command.jobNumber}&nbsp;&nbsp;
                    	<img src="images/separator2.gif" width="2" height="27"  align="absmiddle"/>&nbsp;&nbsp;
                    	<f:message key="jobStatus"/>: ${command.jobStatus}</th>
					<th>&nbsp;</th>
                    <th class="thr">
                        <a href="#">
		                	<img src="images/savennextbluarrow.gif" onClick="saveNextSelectCharges()" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page">
	              		</a>
	                    <a href="#">
	                    	<img src="images/icosave.gif" onClick="saveSelectCharges()" alt="Save" width="14" height="14" border="0" align="absmiddle" />
	                    </a>
                    </th>
                  </tr></tbody></table>
            <table class="mainAppliTable" cellspacing="0" cellpadding="0" width="100%" border=0>
            <tr><td>
			<div id="divIdBillingEvents">
			<table class="mainAppliTable" border="0" cellpadding="0" cellspacing="0" width="100%">
   			  <c:forEach items="${command.batchForms}" var="batchForm" varStatus="batchCounter">
   			  <tr>
   			  		<th nowrap>
   			  		<c:if test="${command.noOfBatches}">
   			  		<form:radiobutton id="batchSel" path="selBatch" value="${batchCounter.index}"/><f:message key="batch"/>${batchCounter.index}</c:if></th>
   			  		<th nowrap>Batch Number :&nbsp;${batchForm.batchNumber}</th>
			</tr>
			<tr>
				<td colspan="2">
				<div id="descriptionContainer${batchCounter.index}" class="descriptionContainer">
				<table width="100%" cellpadding="0" cellspacing="0" class="InnerApplTable">
				  <tr>
					<th width="50%" class="dottedline"><f:message key="description"/></th>
					<th width="10%" class="dottedline"><a href="#" onClick="sortForm('${batchCounter.index}')"><f:message key="sort"/></a></th>
					<th width="18%" class="dottedline"><f:message key="baseCurrency"/>${command.baseCurrency}</th>
					<th width="18%" class="dottedline"><f:message key="billingCurrency"/>${command.billingCurrency}</th>
					<th width="8%" class="dottedline"><f:message key="Discount"/></th>
					<th width="18%" class="dottedline"><f:message key="billingAmount"/></th>
					<th width="18%" nowrap><f:message key="extendedPrice"/>${command.billingCurrency}</th>					
				  </tr>
				
				<c:forEach items="${batchForm.joliForms}" var="joliForm" varStatus="counter">
    				<tr>
    				<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px;">${joliForm.lineNumber} ${joliForm.testDescription} ${joliForm.description}</td>
					<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">
						<form:input id="sortJoli" path="batchForms[${batchCounter.index}].joliForms[${counter.index}].sortNumber" size="1"/>
						<span class="redtext"><form:errors path="batchForms[${batchCounter.index}].joliForms[${counter.index}].sortNumber" cssClass="error"/></span>
					</td>
					
					<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">
						<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${joliForm.baseCurrencyAmount}"/>&nbsp;</td>
					<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">
						<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${joliForm.billingCurrencyAmount}"/>&nbsp;</td>
					<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">${joliForm.discount}</td>
					<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">
						<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${joliForm.billingAmount}"/>&nbsp;</td>
					<td align="center" class="TDShadeBlue" style="border-right:#7c92be dashed 1px; text-align:right;">
						<f:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${joliForm.extendedPrice}"/>&nbsp;</td>
    				</tr>
    			</c:forEach>
				</table>
				</div>
				</td>
				</tr>
				<tr>
					<td colspan="2">
					<div id="totalContainer${batchCounter.index}" class="totalContainer">
	  			    <table class="mainAppliTable" cellspacing="0" cellpadding="0" style="width:100%">
    	             <tr>
                     <th width="97%" class="thr" style="padding-right:8px;">
								<f:message key="total"/> :  ${batchForm.total}                    
                     </th>
	                 </tr>                  
    	             </table>
        	         </div>
    				</td>           
              </tr>
          </c:forEach>
		</table>	
		</div>
		</td>
		</tr>
	  </table>
	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="applTableBot">
                <tr>
                  <td>&nbsp;</td>
                  <td class="tdr">
               		<a href="#">
                 		<img src="images/savennextbluarrow.gif"  onClick="saveNextSelectCharges()" alt="Save and Go to Next Page" width="14" height="14" hspace="4" border="0" align="absmiddle" title="Save and Go to Next Page">
                  	</a>
                  	<a href="#">
                  		<img src="images/icosave.gif" onClick="saveSelectCharges()" alt="Save" width="14" height="14" border="0" align="absmiddle" />
                  	</a>
                  </td>
                </tr>
              </table>
		</div>
		</c:if>
          </div>
        </div>
        
        
        <script type="text/javascript">
		dolphintabs.init("tabnav", 0)
		</script>
        <!-------------------------------------------------------- TAB CONTENT END -------------------------------------------------------------->
        <table width="100%" cellspacing="0">
          <tr>
            <td height="24" class="tdr"><select name="bottomNavigation" id="bottomNavigation" class="SelectionBox" onChange="MM_jumpMenu('parent',this,1)">
                <option selected>Go to ... &gt;</option>
                  <option value="phx_job_entry_ce.htm?jobNumber=${command.jobNumber}"> <f:message key="entry"/></option>
                  <option value="phx_job_operational_info_ce.htm?jobNumber=${command.jobNumber}"><f:message key="jobInstructions"/></option>
              </select>
            </td>
          </tr>
        </table>
      </div>
      <!--------------------------------------------------- MAIN CONTAINER END -------------------------------------------------------------------->
    </td>
  </tr>
</table>
</form:form>
<!-------------------------------------------------------------- Edit Price Lookup ---------------------------------------------------------------->
<div class="sample_popup" id="editprice">
  <div class="menu_form_header" id="editprice_drag" style="width:450px; "> 
  	<img class="menu_form_exit"   id="editprice_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Edit Price  
  </div>
  <div class="menu_form_body" id="editpricebody"   style="width:450px; height:150px;">
  	<iframe align="left" frameborder="0" class="nopadding" width="100%" scrolling="no" id="editPriceFrId" name="editPriceFr" allowtransparency="yes" ></iframe>
  </div>
</div>

<!----------------------------------- Edit Price Lookup END ----------------------------------------- -->

<!----------------------------------- Edit Amount Lookup ---------------------------------------------------------------->
<div class="sample_popup" id="editamount">
  <div class="menu_form_header" id="editamount_drag" style="width:300px; "> 
  	<img class="menu_form_exit"   id="editamount_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;Edit Deposit Amount  
  </div>
  <div class="menu_form_body" id="editamountbody"   style="width:300px; height:120px; overflow-y:hidden;">
  	<iframe align="left" frameborder="0" class="nopadding" width="100%" scrolling="no" id="editAmountFrId" name="editAmountFr" allowtransparency="yes" ></iframe>
  </div>
</div>

<!---------------------------------- Edit Amount Lookup END ----------------------------------------- -->

<!-- ----------------------------------- Add Tests Lookup ------------------------------------------------- -->
<div class="sample_popup" id="addTest">
    <div class="menu_form_header" id="addTest_drag" style="width:850px;"> 
      <img class="menu_form_exit"   id="addTest_exit" src="images/form_exit.png" /> &nbsp;&nbsp;&nbsp;<f:message key="tests"/></div>                                                           
        <div class="menu_form_body" id="testbody" style="width:850px; height:auto;" align="center">
      <iframe id="addTestId"  width="98%" height="1px" scrolling="auto" frameborder="0"
       allowtransparency="yes" style="border:0px; background:none"></iframe>
     </div>
  </div>



<!-- --------------------------------- Add Tests Lookup END ----------------------------------------- -->

<!-- ----------------------------------- Add Job Services Lookup ------------------------------------------------- -->
<div class="sample_popup" id="addJS">
  <div class="menu_form_header" id="addJS_drag" style="width:800px; "> 
  	<img class="menu_form_exit"   id="addJS_exit" src="images/form_exit.png" /> 
  	&nbsp;&nbsp;&nbsp;<f:message key="addJobServices"/>
  </div>
  <div class="menu_form_body" id="addJSbody"   style="width:800px; height:auto">
  	<iframe align="left" frameborder="0" class="nopadding" width="100%" scrolling="auto" id="addJSId" name="addJSFr" allowtransparency="yes" ></iframe>
  </div>
</div>

<!-- --------------------------------- Add Job Services Lookup END ----------------------------------------- -->
	<jsp:include page="../../common/requiredFields.jsp" flush="true" />

<div id="faderPane" class="faderStyle"><img src="images/fake-alpha-999.gif"></div>

