<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  
  <xsl:template match="/jobContractInvoice">
    <ITS_INVOICE_GENERATION xmlns="http://peoplesoft.com/ITS_INVOICE_GENERATIONRequest">
      <FieldTypes>
        <INTFC_BI class="R">
          <INTFC_ID type="NUMBER"/>
          <INTFC_LINE_NUM type="NUMBER"/>
          <TRANS_TYPE_BI type="CHAR"/>
          <TRANS_TYPE_BI_SEQ type="NUMBER"/>
          <INVOICE type="CHAR"/>
          <INVOICE_TO_ADJ type="CHAR"/>
          <BILL_TO_CUST_ID type="CHAR"/>
          <ADDRESS_SEQ_NUM type="NUMBER"/>
          <UNIT_AMT type="NUMBER"/>
          <PACKSLIP_NO type="CHAR"/>
          <BUSINESS_UNIT type="CHAR"/>
          <RESOURCE_ID type="CHAR"/>
          <NAME1 type="CHAR"/>
          <BILL_TYPE_ID type="CHAR"/>
          <BANK_CD type="CHAR"/>
          <BANK_ACCT_KEY type="CHAR"/>
          <PYMNT_TERMS_CD type="CHAR"/>
          <BI_CURRENCY_CD type="CHAR"/>
          <BILL_INQUIRY_PHONE type="CHAR"/>
          <BILL_OF_LADING type="CHAR"/>
          <ACCOUNTING_DT type="DATE"/>
          <INVOICE_DT type="DATE"/>   
          <BILLING_SPECIALIST type="CHAR"/>
          <LINE_SEQ_NUM type="NUMBER"/>
          <DESCR type="CHAR"/>
          <SHIP_TO_ADDR_NUM type="NUMBER"/>
          <SHIP_TO_CUST_ID type="CHAR"/>
          <TAX_CD type="CHAR"/>
          <TAX_CD_VAT type="CHAR"/>
          <STD_NOTE_CD type="CHAR"/>
          <COUNTRY_VAT_BILLTO type="CHAR"/>
          <VAT_APPLICABILITY type="CHAR"/>
        </INTFC_BI>
        <INTFC_BI_NOTE class="R">
          <INTFC_ID type="NUMBER"/>
          <INTFC_LINE_NUM type="NUMBER"/>
          <TRANS_TYPE_BI type="CHAR"/>
          <TRANS_TYPE_BI_SEQ type="NUMBER"/>
          <TEXT254 type="CHAR"/>
        </INTFC_BI_NOTE>
        <INTFC_BI_AEDS class="R">
          <INTFC_ID type="NUMBER"/>
          <INTFC_LINE_NUM type="NUMBER"/>
          <TRANS_TYPE_BI type="CHAR"/>
          <TRANS_TYPE_BI_SEQ type="NUMBER"/>
          <ACCT_ENTRY_TYPE type="CHAR"/>
          <BUSINESS_UNIT type="CHAR"/>
          <PERCENTAGE type="NUMBER"/>
          <OPERATING_UNIT type="CHAR"/>
          <AMOUNT type="NUMBER"/>
          <ACCOUNT type="CHAR"/>
          <LINE_SEQ_NUM type="NUMBER"/>
          <DEPTID type="CHAR"/>
          <PRODUCT type="CHAR"/>
        </INTFC_BI_AEDS>
        <ITS_PRINT_HDR class="R">
          <BUSINESS_UNIT type="CHAR"/>
          <INVOICE type="CHAR"/>
          <ITS_NAME1 type="CHAR"/>
          <ITS_DESCR_LN2 type="CHAR"/>
          <DESCR_LN2 type="CHAR"/>
          <ITS_DESCR_LN3 type="CHAR"/>
          <DESCR_LN3 type="CHAR"/>
          <ITS_DESCR_LN4 type="CHAR"/>
          <DESCR_LN4 type="CHAR"/>
          <ITS_DESCR_LN5 type="CHAR"/>
          <DESCR_LN5 type="CHAR"/>
          <ITS_DESCR_LN6 type="CHAR"/>
          <DESCR_LN6 type="CHAR"/>
          <ITS_CUST_SENT_BY type="CHAR"/>
          <ITS_CONTRACT_ID type="CHAR"/>
          <ITS_VESSEL type="CHAR"/>
          <ITS_PRODUCT type="CHAR"/>
          <ITS_SVC_LOC_ID type="CHAR"/>
          <ITS_SVC_LOCATION type="CHAR"/>
          <ITS_OPERATION type="CHAR"/>
          <ITS_SCHEDULER_ID type="CHAR"/>
          <ITS_RECEIVED_BY type="CHAR"/>   
          <ITS_MONTH_HDR_IND type="CHAR"/>
          <ITS_MONTHLY_JOB_ID type="CHAR"/>
          <ITS_JOB_FINISH_DT type="DATE"/>
		  <ITS_CREATED_USER type="CHAR"/>    
		  <!-- START: Issue 125191 Adding values into new 4 fields -->
		  <TOT_VAT type="NUMBER"/>
		  <TOT_SU_TAX type="NUMBER"/>
		  <INVOICE_AMT_PRETAX type="NUMBER"/>
		  <INVOICE_AMOUNT type="NUMBER"/>
		  <!-- END: Issue 125191 Adding values into new 4 fields -->		  
        </ITS_PRINT_HDR>
        <ITS_PRINT_TMP class="R">
          <BUSINESS_UNIT type="CHAR"/>
          <INVOICE type="CHAR"/>
          <SEQ_NUM type="NUMBER"/>
          <DESCR_LN1 type="CHAR"/>
          <DESCR_LN2 type="CHAR"/>
          <DESCR_LN3 type="CHAR"/>
          <BSTRM_CODE type="CHAR"/>
          <DESCR254 type="CHAR"/>
          <UNIT_AMT type="NUMBER"/>
	<!-- START: Issue 125191 -->
	  <VAT_AMT type="NUMBER"/>
	  <TAX_CD_VAT_PCT type="NUMBER"/>
	  <TAX_AMT type="NUMBER"/>
	  <TAX_PCT type="NUMBER"/>          
	<!-- END: Issue 125191 -->
  	  <PERCENTAGE1 type="NUMBER"/>
          <PERCENTAGE2 type="NUMBER"/>
          <EXTENDED_PRICE type="NUMBER"/>
          <ITS_SERVICE_TYPE type="CHAR"/>
        </ITS_PRINT_TMP>
        <PSCAMA class="R">
          <LANGUAGE_CD type="CHAR"/>
          <AUDIT_ACTN type="CHAR"/>
          <BASE_LANGUAGE_CD type="CHAR"/>
          <MSG_SEQ_FLG type="CHAR"/>
          <PROCESS_INSTANCE type="NUMBER"/>
          <PUBLISH_RULE_ID type="CHAR"/>
          <MSGNODENAME type="CHAR"/>
        </PSCAMA>
      </FieldTypes>
      <MsgData>
        <!-- Get some common keys -->
        <xsl:variable name="businessunit" select="jobContract/jobOrder/buName"/>
        <xsl:variable name="nomlocation" select="jobContract/jobOrder/branchName"/>
        <xsl:variable name="jobfinishdate" select="substring(jobFinishDate, 1, 10)"/>
        <xsl:variable name="createdUserName" select="creationUserName"/>
        <xsl:variable name="shiptocustid" select="shipToCustId"/>
        <xsl:variable name="addrseqship" select="shipToAddrNum"/>
        <xsl:variable name="vesselname" select="vesselNames"/>
        <xsl:variable name="productname" select="productNames"/>
        <xsl:variable name="receivedby" select="receivedByUserName"/>
        <xsl:variable name="operationcode" select="operation"/>
        <xsl:variable name="svclocid" select="serviceLocationCode"/>
        
        <xsl:variable name="custsentby" select="custSentBy"/>
        <xsl:variable name="jobdesc" select="invoiceDescr"/>
        <xsl:variable name="invoice" select="invoice"/>
        <xsl:variable name="companyid" select="jobContract/custCode"/>
        <xsl:variable name="captureid" select="jobContract/jobNumber"/>
        <xsl:variable name="custrefnum" select="custRefNum"/>
        <xsl:variable name="billingname" select="billingContactName"/>
        <xsl:variable name="bankcd" select="bankCode"/>
        <xsl:variable name="bankacctkey" select="bankAcctCode"/>
        <xsl:variable name="pmtterms" select="pymntTermsCd"/>
        <xsl:variable name="contractid" select="jobContract/contractCode"/>
        <xsl:variable name="schedulerid" select="contactId"/>
        <xsl:variable name="monthhdrind" select="monthlyFlag"/>
        <xsl:variable name="monthlyjobid" select="monthlyJobNumber"/>
        <xsl:variable name="addrseq" select="custLocationNumber"/>
        <xsl:variable name="billtype" select="invoiceType"/>
        <xsl:variable name="currencycd" select="transactionCurrencyCd"/>
        <xsl:variable name="vatapplicability" select="taxVatFlag"/>
        <xsl:variable name="inquiryphone" select="branchPhoneNumber"/>

        <!-- unique run control id to be sent to PS - use a guid and store in JOB_CONTRACT_INVOICE -->
        <!-- note that only bring back the current invoice row (last row generated) from jobContractinvoice -->
        <xsl:variable name="runcntlid" select="runcntlid"/>

        <xsl:variable name="invoicedate" select="substring(invoiceDate, 1, 10)"/>
        <xsl:variable name="accountingdate" select="substring(accountingDate, 1, 10)"/>
          
        <!-- below field only used for credits - this is the original invoice id of the bill to be credited -->
        <xsl:variable name="invtoadj" select="invoiceToAdjust"/>

        <xsl:variable name="customername" select="customerName"/>
        <xsl:variable name="servicelocname" select="serviceLocationName"/>
        <xsl:variable name="invfname1" select="invoiceLabel1"/>
        <xsl:variable name="invfname2" select="invoiceLabel2"/>
        <xsl:variable name="invfname3" select="invoiceLabel3"/>
        <xsl:variable name="invfname4" select="invoiceLabel4"/>
        <xsl:variable name="invfname5" select="invoiceLabel5"/>
        <xsl:variable name="invfvalue1" select="invoiceValue1"/>
        <xsl:variable name="invfvalue2" select="invoiceValue2"/>
        <xsl:variable name="invfvalue3" select="invoiceValue3"/>
        <xsl:variable name="invfvalue4" select="invoiceValue4"/>
        <xsl:variable name="invfvalue5" select="invoiceValue5"/>
        <xsl:variable name="vatregcountry" select="vatRegCountryCode"/>
		<!-- START: Issue 125191 Adding values into new 4 fields -->
		<xsl:variable name="jcivatamt" select="vatAmt"/>
		<xsl:variable name="jcitaxamt" select="taxAmt"/>
		<xsl:variable name="jciinvamtpreTax" select="invAmtPreTax"/>
		<xsl:variable name="jciinvamtpostTax" select="invAmtPostTax"/>		
		<!-- END: Issue 125191 Adding values into new 4 fields -->
		
        <xsl:variable name="billType">
          <xsl:choose>
            <xsl:when test="creditInd = 'C'">
              <xsl:value-of select="'CR'" />
            </xsl:when>
            <xsl:otherwise>
              <xsl:value-of select="billType"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:variable>

        <!-- Populate the BI records with PreBill data -->
        <xsl:for-each select="invoiceLine">
          <xsl:variable name="prebillid" select="prebillId"/>
          <xsl:variable name="extprice" select="netPrice"/>
          <xsl:variable name="descr" select="lineDescription"/>
          <xsl:variable name="split" select="splitPct"/>
          <xsl:variable name="discount" select="discountPct"/>
          <xsl:variable name="price" select="unitPrice"/>
          <xsl:variable name="level0" select="level0"/>
          <xsl:variable name="level1" select="level1"/>
          <xsl:variable name="level2" select="level2"/>
          <xsl:variable name="busstream" select="busStreamCode"/>
          <xsl:variable name="account" select="account"/>
          <xsl:variable name="deptid" select="deptid"/>
          <xsl:variable name="product" select="productGroup"/>
          <xsl:variable name="servicetype" select="serviceType"/>
          <xsl:variable name="taxcode" select="taxCode"/>
          <xsl:variable name="vatcode" select="vatCode"/>            
          <xsl:variable name="taxartcode" select="taxArticleCode"/>
	<!-- START: Issue 125191 -->
          <xsl:variable name="linenumber" select="lineNumber"/>
          <xsl:variable name="vatamt" select="vatAmt"/>
          <xsl:variable name="vatpct" select="vatPct"/>
          <xsl:variable name="taxamt" select="taxAmt"/>
          <xsl:variable name="taxpct" select="taxPct"/>
    <!-- END: Issue 125191 -->
          
          <Transaction>
            <INTFC_BI class="R">
              <INTFC_ID>1</INTFC_ID>
              <!-- <INTFC_LINE_NUM><xsl:value-of select="$prebillid"/></INTFC_LINE_NUM> -->
              <INTFC_LINE_NUM/>
              <TRANS_TYPE_BI>LINE</TRANS_TYPE_BI>
              <TRANS_TYPE_BI_SEQ>0</TRANS_TYPE_BI_SEQ>
              <INVOICE><xsl:value-of select="$invoice"/></INVOICE>
              <INVOICE_TO_ADJ><xsl:value-of select="$invtoadj"/></INVOICE_TO_ADJ>
              <BILL_TO_CUST_ID><xsl:value-of select="$companyid"/></BILL_TO_CUST_ID>
              <ADDRESS_SEQ_NUM><xsl:value-of select="$addrseq"/></ADDRESS_SEQ_NUM>
              <UNIT_AMT><xsl:value-of select="$extprice"/></UNIT_AMT>
              <PACKSLIP_NO><xsl:value-of select="$captureid"/></PACKSLIP_NO>
              <BUSINESS_UNIT><xsl:value-of select="$businessunit"/></BUSINESS_UNIT>
              <RESOURCE_ID><xsl:value-of select="$custrefnum"/></RESOURCE_ID>
              <BILL_TYPE_ID><xsl:value-of select="$billType"/></BILL_TYPE_ID>
              <BANK_CD><xsl:value-of select="$bankcd"/></BANK_CD>
              <BANK_ACCT_KEY><xsl:value-of select="$bankacctkey"/></BANK_ACCT_KEY>
              <PYMNT_TERMS_CD><xsl:value-of select="$pmtterms"/></PYMNT_TERMS_CD>
              <BILLING_SPECIALIST/>
              <BI_CURRENCY_CD><xsl:value-of select="$currencycd"/></BI_CURRENCY_CD>
              <BILL_INQUIRY_PHONE><xsl:value-of select="$inquiryphone"/></BILL_INQUIRY_PHONE>
              <NAME1><xsl:value-of select="$billingname"/></NAME1>
              <BILL_OF_LADING><xsl:value-of select="$nomlocation"/></BILL_OF_LADING>
              <ACCOUNTING_DT><xsl:value-of select="$accountingdate"/></ACCOUNTING_DT>
              <INVOICE_DT><xsl:value-of select="$invoicedate"/></INVOICE_DT>    
              <!-- <LINE_SEQ_NUM><xsl:value-of select="$prebillid"/></LINE_SEQ_NUM> -->
              <LINE_SEQ_NUM/>
              <!-- START: Issue 125191 -->
              <!-- <DESCR><xsl:value-of select="$runcntlid"/></DESCR> -->
              <DESCR><xsl:value-of select="$invoice"/></DESCR>
              <!-- END: Issue 125191 -->
              <SHIP_TO_ADDR_NUM><xsl:value-of select="$addrseqship"/></SHIP_TO_ADDR_NUM>
              <SHIP_TO_CUST_ID><xsl:value-of select="$shiptocustid"/></SHIP_TO_CUST_ID>
              <TAX_CD><xsl:value-of select="$taxcode"/></TAX_CD>
              <TAX_CD_VAT><xsl:value-of select="$vatcode"/></TAX_CD_VAT>
              <STD_NOTE_CD><xsl:value-of select="$taxartcode"/></STD_NOTE_CD>
              <COUNTRY_VAT_BILLTO><xsl:value-of select="$vatregcountry"/></COUNTRY_VAT_BILLTO>    
              <VAT_APPLICABILITY><xsl:value-of select="$vatapplicability"/></VAT_APPLICABILITY>
              <xsl:for-each select="invoiceLineSplit">
                <INTFC_BI_AEDS class="R">
                  <INTFC_ID>1</INTFC_ID>
                  <!-- <INTFC_LINE_NUM><xsl:value-of select="$prebillid"/></INTFC_LINE_NUM> -->
                  <INTFC_LINE_NUM/>
                  <TRANS_TYPE_BI>AE</TRANS_TYPE_BI>
                  <TRANS_TYPE_BI_SEQ>0</TRANS_TYPE_BI_SEQ>
                  <ACCT_ENTRY_TYPE>RR</ACCT_ENTRY_TYPE>
                  <BUSINESS_UNIT><xsl:value-of select="$businessunit"/></BUSINESS_UNIT>
                  <PERCENTAGE><xsl:value-of select="allocPct"/></PERCENTAGE>
                  <OPERATING_UNIT><xsl:value-of select="branchName"/></OPERATING_UNIT>
                  <AMOUNT><xsl:value-of select="allocAmt"/></AMOUNT>
                  <ACCOUNT><xsl:value-of select="$account"/></ACCOUNT>
                  <!-- <LINE_SEQ_NUM><xsl:value-of select="$prebillid"/></LINE_SEQ_NUM> -->
                  <DEPTID><xsl:value-of select="$deptid"/></DEPTID>
                  <PRODUCT><xsl:value-of select="$product"/></PRODUCT>
                </INTFC_BI_AEDS>
              </xsl:for-each>
            </INTFC_BI>
            <ITS_PRINT_HDR class="R">
              <BUSINESS_UNIT><xsl:value-of select="$businessunit"/></BUSINESS_UNIT>
              <INVOICE><xsl:value-of select="$invoice"/></INVOICE>
              <ITS_NAME1><xsl:value-of select="$customername"/></ITS_NAME1>
              <ITS_DESCR_LN2><xsl:value-of select="$invfname1"/></ITS_DESCR_LN2>
              <DESCR_LN2><xsl:value-of select="$invfvalue1"/></DESCR_LN2>
              <ITS_DESCR_LN3><xsl:value-of select="$invfname2"/></ITS_DESCR_LN3>
              <DESCR_LN3><xsl:value-of select="$invfvalue2"/></DESCR_LN3>
              <ITS_DESCR_LN4><xsl:value-of select="$invfname3"/></ITS_DESCR_LN4>
              <DESCR_LN4><xsl:value-of select="$invfvalue3"/></DESCR_LN4>
              <ITS_DESCR_LN5><xsl:value-of select="$invfname4"/></ITS_DESCR_LN5>
              <DESCR_LN5><xsl:value-of select="$invfvalue4"/></DESCR_LN5>
              <ITS_DESCR_LN6><xsl:value-of select="$invfname5"/></ITS_DESCR_LN6> 
              <DESCR_LN6><xsl:value-of select="$invfvalue5"/></DESCR_LN6>
              <ITS_CUST_SENT_BY><xsl:value-of select="$custsentby"/></ITS_CUST_SENT_BY>
              <ITS_CONTRACT_ID><xsl:value-of select="$contractid"/></ITS_CONTRACT_ID>
              <ITS_VESSEL><xsl:value-of select="$vesselname"/></ITS_VESSEL>
              <ITS_PRODUCT><xsl:value-of select="$productname"/></ITS_PRODUCT>
              <ITS_SVC_LOC_ID><xsl:value-of select="$svclocid"/></ITS_SVC_LOC_ID>
              <ITS_SVC_LOCATION><xsl:value-of select="$servicelocname"/></ITS_SVC_LOCATION>
              <ITS_OPERATION><xsl:value-of select="$operationcode"/></ITS_OPERATION>
              <ITS_SCHEDULER_ID><xsl:value-of select="$schedulerid"/></ITS_SCHEDULER_ID>
              <ITS_RECEIVED_BY><xsl:value-of select="$receivedby"/></ITS_RECEIVED_BY>   
              <ITS_MONTH_HDR_IND><xsl:value-of select="$monthhdrind"/></ITS_MONTH_HDR_IND>
              <ITS_MONTHLY_JOB_ID><xsl:value-of select="$monthlyjobid"/></ITS_MONTHLY_JOB_ID>
              <ITS_JOB_FINISH_DT><xsl:value-of select="$jobfinishdate"/></ITS_JOB_FINISH_DT> 
              <ITS_CREATED_USER><xsl:value-of select="$createdUserName"/></ITS_CREATED_USER>              
			<!-- START: Issue 125191 Adding values into new 4 fields -->
			 <TOT_VAT><xsl:value-of select="$jcivatamt"/></TOT_VAT>
			 <TOT_SU_TAX><xsl:value-of select="$jcitaxamt"/></TOT_SU_TAX>
			 <INVOICE_AMT_PRETAX><xsl:value-of select="$jciinvamtpreTax"/></INVOICE_AMT_PRETAX>
			 <INVOICE_AMOUNT><xsl:value-of select="$jciinvamtpostTax"/></INVOICE_AMOUNT>	 
			<!-- END: Issue 125191 Adding values into new 4 fields -->                 
            </ITS_PRINT_HDR>
            <ITS_PRINT_TMP class="R">
              <BUSINESS_UNIT><xsl:value-of select="$businessunit"/></BUSINESS_UNIT>
              <INVOICE><xsl:value-of select="$invoice"/></INVOICE>
              <!-- START: Issue 125191 -->
              <SEQ_NUM><xsl:value-of select="$linenumber"/></SEQ_NUM>
              <!-- END: Issue 125191 -->
              <DESCR_LN1><xsl:value-of select="$level0"/></DESCR_LN1>
              <DESCR_LN2><xsl:value-of select="$level1"/></DESCR_LN2>
              <DESCR_LN3><xsl:value-of select="$level2"/></DESCR_LN3>
              <BSTRM_CODE><xsl:value-of select="$busstream"/></BSTRM_CODE>
              <DESCR254><xsl:value-of select="$descr"/></DESCR254>
              <UNIT_AMT><xsl:value-of select="$price"/></UNIT_AMT>
	  <!-- START: Issue 125191 -->
	      <VAT_AMT><xsl:value-of select="$vatamt"/></VAT_AMT>
	      <TAX_CD_VAT_PCT><xsl:value-of select="$vatpct"/></TAX_CD_VAT_PCT>
	      <TAX_AMT><xsl:value-of select="$taxamt"/></TAX_AMT>
	      <TAX_PCT><xsl:value-of select="$taxpct"/></TAX_PCT>
	  <!-- END: Issue 125191 -->             
	      <PERCENTAGE1><xsl:value-of select="$split"/></PERCENTAGE1>
              <PERCENTAGE2><xsl:value-of select="$discount"/></PERCENTAGE2>
              <EXTENDED_PRICE><xsl:value-of select="$extprice"/></EXTENDED_PRICE>
              <ITS_SERVICE_TYPE><xsl:value-of select="$servicetype"/></ITS_SERVICE_TYPE>
            </ITS_PRINT_TMP>
            <INTFC_BI_NOTE class="R">
              <INTFC_ID>1</INTFC_ID>
              <INTFC_LINE_NUM><xsl:value-of select="$prebillid"/></INTFC_LINE_NUM>
              <TRANS_TYPE_BI>NOTE</TRANS_TYPE_BI>
              <TRANS_TYPE_BI_SEQ>0</TRANS_TYPE_BI_SEQ>
              <TEXT254><xsl:value-of select="$jobdesc"/></TEXT254>
            </INTFC_BI_NOTE>
            <PSCAMA class="R">
              <LANGUAGE_CD/>
              <AUDIT_ACTN>A</AUDIT_ACTN>
              <BASE_LANGUAGE_CD/>
              <MSG_SEQ_FLG/>
              <PROCESS_INSTANCE/>
              <PUBLISH_RULE_ID/>
              <MSGNODENAME/>
            </PSCAMA>
          </Transaction>
        </xsl:for-each>       
      </MsgData>
    </ITS_INVOICE_GENERATION>
  </xsl:template>

</xsl:stylesheet>
