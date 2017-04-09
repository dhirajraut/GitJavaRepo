<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:include href="command_templates.xsl" />

  <xsl:template match="/consolidatedInvoice">
    <ITS_CONS_INVOICE xmlns="http://peoplesoft.com/ITS_CONS_INVOICERequest">
      <FieldTypes>
        <ITS_CONS_HDR_VW class="R">
          <CONSOL_BUS_UNIT type="CHAR"/>
          <CONSOL_INVOICE type="CHAR"/>
          <BILL_TO_CUST_ID type="CHAR"/>
          <BILL_STATUS type="CHAR"/>
          <BILL_TYPE_ID type="CHAR"/>
          <BILL_SOURCE_ID type="CHAR"/>
          <ADDRESS_SEQ_NUM type="NUMBER"/>
          <BI_CURRENCY_CD type="CHAR"/>
          <INVOICE_FORM_ID type="CHAR"/>
          <FROM_DT type="DATE"/>
          <TO_DT type="DATE"/>
          <INVOICE_DT type="DATE"/>
          <PYMNT_TERMS_CD type="CHAR"/>
          <BANK_CD type="CHAR"/>
          <BANK_ACCT_KEY type="CHAR"/>
          <BILL_INQUIRY_PHONE type="CHAR"/>
          <CNTCT_SEQ_NUM type="NUMBER"/>
          <NAME1 type="CHAR"/>
        </ITS_CONS_HDR_VW>
        <ITS_CONS_ATT_VW class="R">
          <CONSOL_BUS_UNIT type="NUMBER"/>
          <CONSOL_INVOICE type="NUMBER"/>
          <BUSINESS_UNIT type="CHAR"/>
          <INVOICE type="NUMBER"/>
        </ITS_CONS_ATT_VW>
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
        <Transaction>
          <ITS_CONS_HDR_VW class="R">
            <CONSOL_BUS_UNIT><xsl:value-of select="consolidatedInvoiceId/buName"/></CONSOL_BUS_UNIT>
            <CONSOL_INVOICE><xsl:value-of select="consolidatedInvoiceId/consolInvoiceNo"/></CONSOL_INVOICE>
            <BILL_TO_CUST_ID><xsl:value-of select="custCode"/></BILL_TO_CUST_ID>
            <BILL_STATUS><xsl:value-of select="billStatus"/></BILL_STATUS>
            <BILL_TYPE_ID><xsl:value-of select="billType"/></BILL_TYPE_ID>
            <BILL_SOURCE_ID/>
            <ADDRESS_SEQ_NUM><xsl:value-of select="locationNumber"/></ADDRESS_SEQ_NUM>
            <BI_CURRENCY_CD><xsl:value-of select="currencyCd"/></BI_CURRENCY_CD>
            <INVOICE_FORM_ID>STANDARD</INVOICE_FORM_ID>
            <FROM_DT>
              <xsl:call-template name="getNewDateFormat">
                <xsl:with-param name="dateInput"><xsl:value-of select="fromDt" /></xsl:with-param>
              </xsl:call-template>                
            </FROM_DT>
            <TO_DT>
              <xsl:call-template name="getNewDateFormat">
                <xsl:with-param name="dateInput"><xsl:value-of select="toDt" /></xsl:with-param>
              </xsl:call-template>                
            </TO_DT>
            <INVOICE_DT>
              <xsl:call-template name="getNewDateFormat">
                <xsl:with-param name="dateInput"><xsl:value-of select="invoiceDt" /></xsl:with-param>
              </xsl:call-template>                
            </INVOICE_DT>
            <PYMNT_TERMS_CD><xsl:value-of select="pymntTermsCd"/></PYMNT_TERMS_CD>
            <BANK_CD><xsl:value-of select="bankCd"/></BANK_CD>
            <BANK_ACCT_KEY><xsl:value-of select="bankAcctKey"/></BANK_ACCT_KEY>
            <CNTCT_SEQ_NUM><xsl:value-of select="contactCust/contactSeqNum"/></CNTCT_SEQ_NUM>
            <NAME1><xsl:value-of select="concat(contact/firstName, ' ', contact/lastName)" /></NAME1>
            <xsl:for-each select="jobContractInvoice">
              <ITS_CONS_ATT_VW class="R">
                <CONSOL_BUS_UNIT><xsl:value-of select="consolBuName"/></CONSOL_BUS_UNIT>
                <CONSOL_INVOICE><xsl:value-of select="consolInvoiceNo"/></CONSOL_INVOICE>
                <BUSINESS_UNIT><xsl:value-of select="jobContract/jobOrder/buName"/></BUSINESS_UNIT>
                <INVOICE><xsl:value-of select="invoice"/></INVOICE>
              </ITS_CONS_ATT_VW>
            </xsl:for-each>
          </ITS_CONS_HDR_VW>
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
      </MsgData>
    </ITS_CONS_INVOICE>
  </xsl:template>

</xsl:stylesheet>
