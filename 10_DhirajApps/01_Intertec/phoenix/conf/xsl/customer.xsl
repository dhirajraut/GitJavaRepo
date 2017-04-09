<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:include href="command_templates.xsl" />

  <xsl:template match="/customer">
    <CUSTOMER_SYNC>
      <FieldTypes>
        <CUSTOMER class="R">
          <SETID type="CHAR"/>
          <CUST_ID type="CHAR"/>
          <CUST_STATUS type="CHAR"/>
          <CUST_STATUS_DT type="DATE"/>
          <CUSTOMER_TYPE type="CHAR"/>
          <SINCE_DT type="DATE"/>
          <ADD_DT type="DATE"/>
          <NAME1 type="CHAR"/>
          <NAMESHORT type="CHAR"/>
          <NAME1_AC type="CHAR"/>
          <ADDRESS_SEQ_NUM type="NUMBER"/>
          <CORPORATE_SETID type="CHAR"/>
          <CORPORATE_CUST_ID type="CHAR"/>
          <CNTCT_SEQ_NUM type="NUMBER"/>
          <SUBCUST_USE type="CHAR"/>
          <CUR_RT_TYPE type="CHAR"/>
          <CURRENCY_CD type="CHAR"/>
          <VAT_TXN_TYPE_CD type="CHAR"/>
          <SHIP_TO_FLG type="CHAR"/>
          <BILL_TO_FLG type="CHAR"/>
          <SOLD_TO_FLG type="CHAR"/>
          <BROKER_FLG type="CHAR"/>
          <INDIRECT_CUST_FLG type="CHAR"/>
          <CUST_LEVEL type="CHAR"/>
          <ADDRESS_SEQ_SOLD type="NUMBER"/>
          <CNTCT_SEQ_SOLD type="NUMBER"/>
          <ADDRESS_SEQ_SHIP type="NUMBER"/>
          <CNTCT_SEQ_SHIP type="NUMBER"/>
          <ADDRESS_SEQ_BRK type="NUMBER"/>
          <ADDRESS_SEQ_IND type="NUMBER"/>
          <HOLD_UPDATE_SW type="CHAR"/>
          <TAXPAYER_ID type="CHAR"/>
          <WEB_URL type="CHAR"/>
          <STOCK_SYMBOL type="CHAR"/>
          <LAST_MAINT_OPRID type="CHAR"/>
          <DATE_LAST_MAINT type="DATE"/>
        </CUSTOMER>
        <CUST_ADDR_SEQ class="R">
          <SETID type="CHAR"/>
          <CUST_ID type="CHAR"/>
          <ADDRESS_SEQ_NUM type="NUMBER"/>
          <DESCR type="CHAR"/>
          <BILL_TO_ADDR type="CHAR"/>
          <SHIP_TO_ADDR type="CHAR"/>
          <SOLD_TO_ADDR type="CHAR"/>
          <CRSPD_TO_ADDR type="CHAR"/>
          <LAST_MAINT_OPRID type="CHAR"/>
          <DATE_LAST_MAINT type="DATE"/>
        </CUST_ADDR_SEQ>
        <CUST_OPTION class="R">
          <SETID type="CHAR"/>
          <CUST_ID type="CHAR"/>
          <EFFDT type="DATE"/>
          <EFF_STATUS type="CHAR"/>
          <PYMNT_TERMS_CD type="CHAR"/>
          <PYMNT_TERMS_AMT type="CHAR"/>
          <GRACE_DUE_DAYS type="NUMBER"/>
          <GRACE_DISC_DAYS type="NUMBER"/>
          <RETENTION_DAYS type="NUMBER"/>
          <PP_HOLD type="CHAR"/>
          <PP_METHOD type="CHAR"/>
          <REF_QUALIFIER_CODE type="CHAR"/>
          <PARTIAL_PY_SW type="CHAR"/>
          <PO_REQUIRED type="CHAR"/>
          <BILL_BY_ID type="CHAR"/>
          <FREIGHT_BILL_TYPE type="CHAR"/>
          <BILL_CYCLE_ID type="CHAR"/>
          <BILL_INQUIRY_PHONE type="CHAR"/>
          <BILL_TYPE_ID type="CHAR"/>
          <BILLING_SPECIALIST type="CHAR"/>
          <COLLECTOR type="CHAR"/>
          <CR_ANALYST type="CHAR"/>
          <INVOICE_FORM_ID type="CHAR"/>
          <DST_ID_AR type="CHAR"/>
          <DRAFT_APPROVAL type="CHAR"/>
          <DRAFT_DOC type="CHAR"/>
          <DRAFT_TYPE type="CHAR"/>
          <DD_PROFILE_ID type="CHAR"/>
          <DD_GROUP type="CHAR"/>
          <PAYMENT_METHOD type="CHAR"/>
          <DIRECT_INVOICING type="CHAR"/>
          <CUSTOMER_PO type="CHAR"/>
          <START_DATE type="DATE"/>
          <END_DATE type="DATE"/>
          <MICR_ID type="CHAR"/>
          <CONSOL_SETID type="CHAR"/>
          <CONSOL_CUST_ID type="CHAR"/>
          <CONSOL_KEY type="CHAR"/>
          <INTERUNIT_FLG type="CHAR"/>
          <BUSINESS_UNIT_GL type="CHAR"/>
          <DM_SPECIALIST type="CHAR"/>
          <DM_WRITE_OFF_AMT type="NUMBER"/>
          <DM_WRITE_OFF_DAYS type="NUMBER"/>
          <DM_WRITE_OFF_PCT type="NUMBER"/>
          <HOL_PROC_OPT type="CHAR"/>
          <HOL_PROC_DAYS type="NUMBER"/>
          <HOL_PROC_OVERFL type="CHAR"/>
          <REBATE_METHOD type="CHAR"/>
          <BI_PROMPT_CURR type="CHAR"/>
          <LAST_MAINT_OPRID type="CHAR"/>
          <DATE_LAST_MAINT type="DATE"/>
        </CUST_OPTION>
        <CUST_SOLDTO_OPT class="R">
          <SETID type="CHAR"/>
          <CUST_ID type="CHAR"/>
          <EFFDT type="DATE"/>
          <EFF_STATUS type="CHAR"/>
          <FRT_CHRG_METHOD type="CHAR"/>
          <GRACE_PRICE_DAYS type="NUMBER"/>
          <LABEL_FORMAT_ID2 type="CHAR"/>
          <LABEL_FORMAT_ID3 type="CHAR"/>
          <LOAD_PRICE_RULE type="CHAR"/>
          <PROD_ID_SRC type="CHAR"/>
          <PROD_SUBSTITUTE type="CHAR"/>
          <PRICE_PROGRAM type="CHAR"/>
          <PRICE_RULE_CD type="CHAR"/>
          <RESTOCK_FEE_FLAG type="CHAR"/>
          <DATE_LAST_MAINT type="DATE"/>
          <LAST_MAINT_OPRID type="CHAR"/>
        </CUST_SOLDTO_OPT>
        <CUST_SHIPTO_OPT class="R">
          <SETID type="CHAR"/>
          <CUST_ID type="CHAR"/>
          <EFFDT type="DATE"/>
          <EFF_STATUS type="CHAR"/>
          <SHIP_TYPE_ID type="CHAR"/>
          <SHIP_TYPE_ID_EXP type="CHAR"/>
          <SHIP_PRIOR_FLAG type="CHAR"/>
          <SHIP_PARTIAL_FLAG type="CHAR"/>
          <BCKORDR_CNCL_FLAG type="CHAR"/>
          <LANGUAGE_CD type="CHAR"/>
          <SHIP_PRIORITY_ID type="CHAR"/>
          <DS_NETWORK_CODE type="CHAR"/>
          <LAST_MAINT_OPRID type="CHAR"/>
          <DATE_LAST_MAINT type="DATE"/>
          <FREIGHT_TERMS type="CHAR"/>
          <FREIGHT_TERMS_EXP type="CHAR"/>
          <SHIP_EARLY_DAYS type="NUMBER"/>
          <SHIP_LATE_DAYS type="NUMBER"/>
          <SHIP_PARTIAL_ORD type="CHAR"/>
          <STORE_NUMBER type="CHAR"/>
          <NAFTA_REQ_EXP type="CHAR"/>
          <CCI_REQ_EXP type="CHAR"/>
          <SED_REQ_EXP type="CHAR"/>
          <COO_REQ_EXP type="CHAR"/>
          <AERP type="CHAR"/>
          <INSURANCE_REQD type="CHAR"/>
          <MILITARY_FLG type="CHAR"/>
          <EXPORT_ORDER_FLG type="CHAR"/>
          <CARRIER_ID type="CHAR"/>
          <CARRIER_ID_EXP type="CHAR"/>
          <SHIP_PRIORITY_EXP type="CHAR"/>
          <SINGLE_SHIP_FLAG type="CHAR"/>
          <LABEL_FORMAT_ID2 type="CHAR"/>
          <LABEL_FORMAT_ID3 type="CHAR"/>
          <ALLOW_OVERPICK_FLG type="CHAR"/>
          <MAX_PICK_TOLERANCE type="NUMBER"/>
        </CUST_SHIPTO_OPT>
        <CUST_SHIPTO class="R">
          <SETID type="CHAR"/>
          <CUST_ID type="CHAR"/>
          <SHIP_TO_CUST_ID type="CHAR"/>
          <SLDTO_PRIORITY_NBR type="NUMBER"/>
          <LAST_MAINT_OPRID type="CHAR"/>
          <DATE_LAST_MAINT type="DATE"/>
        </CUST_SHIPTO>
        <CUST_BILLTO class="R">
          <SETID type="CHAR"/>
          <CUST_ID type="CHAR"/>
          <BILL_TO_CUST_ID type="CHAR"/>
          <SLDTO_PRIORITY_NBR type="NUMBER"/>
          <LAST_MAINT_OPRID type="CHAR"/>
          <DATE_LAST_MAINT type="DATE"/>
        </CUST_BILLTO>
        <CUST_CGRP_LNK class="R">
          <SETID type="CHAR"/>
          <CUST_ID type="CHAR"/>
          <CUST_GRP_TYPE type="CHAR"/>
          <CUSTOMER_GROUP type="CHAR"/>
          <DEFAULT_TAX_GRP type="CHAR"/>
          <ROW_ADDED_DTTM type="DATETIME"/>
          <ROW_ADDED_OPRID type="CHAR"/>
          <ROW_LASTMANT_DTTM type="DATETIME"/>
          <ROW_LASTMANT_OPRID type="CHAR"/>
          <SYNCID type="NUMBER"/>
          <SYNCDTTM type="DATETIME"/>
        </CUST_CGRP_LNK>
        <CUST_TEAM class="R">
          <SETID type="CHAR"/>
          <CUST_ID type="CHAR"/>
          <SUPPORT_TEAM_CD type="CHAR"/>
          <DEFAULT_FLAG type="CHAR"/>
          <LAST_MAINT_OPRID type="CHAR"/>
          <DATE_LAST_MAINT type="DATE"/>
        </CUST_TEAM>
        <CUST_ADDRESS class="R">
          <SETID type="CHAR"/>
          <CUST_ID type="CHAR"/>
          <ADDRESS_SEQ_NUM type="NUMBER"/>
          <EFFDT type="DATE"/>
          <EFF_STATUS type="CHAR"/>
          <COUNTRY type="CHAR"/>
          <ADDRESS1 type="CHAR"/>
          <ADDRESS2 type="CHAR"/>
          <ADDRESS3 type="CHAR"/>
          <ADDRESS4 type="CHAR"/>
          <CITY type="CHAR"/>
          <NUM1 type="CHAR"/>
          <NUM2 type="CHAR"/>
          <HOUSE_TYPE type="CHAR"/>
          <ADDR_FIELD1 type="CHAR"/>
          <ADDR_FIELD2 type="CHAR"/>
          <ADDR_FIELD3 type="CHAR"/>
          <COUNTY type="CHAR"/>
          <STATE type="CHAR"/>
          <POSTAL type="CHAR"/>
          <GEO_CODE type="CHAR"/>
          <IN_CITY_LIMIT type="CHAR"/>
          <COUNTRY_CODE type="CHAR"/>
          <PHONE type="CHAR"/>
          <EXTENSION type="CHAR"/>
          <FAX type="CHAR"/>
          <LAST_MAINT_OPRID type="CHAR"/>
          <DATE_LAST_MAINT type="DATE"/>
        </CUST_ADDRESS>
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
          <xsl:variable name="auditFlag">
            <xsl:choose>
              <xsl:when test="newFlag = 'NEW'">
                <xsl:text>A</xsl:text>
              </xsl:when>
              <xsl:otherwise>
                <xsl:text>C</xsl:text>
              </xsl:otherwise>
            </xsl:choose>          
          </xsl:variable>

          <CUSTOMER class="R">
            <SETID>SHARE</SETID>
            <CUST_ID><xsl:value-of select="custCode" /></CUST_ID>
            <CUST_STATUS><xsl:value-of select="status" /></CUST_STATUS>
            <CUST_STATUS_DT>
              <xsl:call-template name="getNewDateFormat">
                <xsl:with-param name="dateInput"><xsl:value-of select="statusDate" /></xsl:with-param>
              </xsl:call-template>                
            </CUST_STATUS_DT>
            <CUSTOMER_TYPE><xsl:value-of select="type" /></CUSTOMER_TYPE>
            <SINCE_DT>
              <xsl:call-template name="getNewDateFormat">
                <xsl:with-param name="dateInput"><xsl:value-of select="sinceDate" /></xsl:with-param>
              </xsl:call-template>                
            </SINCE_DT>
            <ADD_DT>
              <xsl:call-template name="getNewDateFormat">
                <xsl:with-param name="dateInput"><xsl:value-of select="addDate" /></xsl:with-param>
              </xsl:call-template>                
            </ADD_DT>
            <NAME1><xsl:value-of select="substring(name, 1, 40)" /></NAME1>
            <NAMESHORT></NAMESHORT>
            <NAME1_AC/>
            <ADDRESS_SEQ_NUM><xsl:value-of select="custAddrSeq/custAddrSeqId/locationNumber" /></ADDRESS_SEQ_NUM>
            <CORPORATE_SETID>SHARE</CORPORATE_SETID>
            <CORPORATE_CUST_ID><xsl:value-of select="parentCustCode" /></CORPORATE_CUST_ID>
            <CNTCT_SEQ_NUM><xsl:value-of select="contactCust/contactCustId/locationNumber" /></CNTCT_SEQ_NUM>
            <SUBCUST_USE/>
            <CUR_RT_TYPE>CRRNT</CUR_RT_TYPE>
            <CURRENCY_CD><xsl:value-of select="currencyCd" /></CURRENCY_CD>
            <VAT_TXN_TYPE_CD/>
            <SHIP_TO_FLG>
              <xsl:call-template name="getYNFromBoolean">
                <xsl:with-param name="booleanInput"><xsl:value-of select="shipTo" /></xsl:with-param>
              </xsl:call-template>                
            </SHIP_TO_FLG>
            <BILL_TO_FLG>                
              <xsl:call-template name="getYNFromBoolean">
                <xsl:with-param name="booleanInput"><xsl:value-of select="billTo" /></xsl:with-param>
              </xsl:call-template>                
            </BILL_TO_FLG>
            <SOLD_TO_FLG>
              <xsl:call-template name="getYNFromBoolean">
                <xsl:with-param name="booleanInput"><xsl:value-of select="soldTo" /></xsl:with-param>
              </xsl:call-template>                                
            </SOLD_TO_FLG>
            <BROKER_FLG/>
            <INDIRECT_CUST_FLG/>
            <CUST_LEVEL/>
            <ADDRESS_SEQ_SOLD><xsl:value-of select="primarySoldToAddress" /></ADDRESS_SEQ_SOLD>
            <CNTCT_SEQ_SOLD>0</CNTCT_SEQ_SOLD>
            <ADDRESS_SEQ_SHIP><xsl:value-of select="primaryShipToAddress" /></ADDRESS_SEQ_SHIP>
            <CNTCT_SEQ_SHIP>0</CNTCT_SEQ_SHIP>
            <ADDRESS_SEQ_BRK>0</ADDRESS_SEQ_BRK>
            <ADDRESS_SEQ_IND>0</ADDRESS_SEQ_IND>
            <HOLD_UPDATE_SW/>
            <TAXPAYER_ID/>
            <WEB_URL/>
            <STOCK_SYMBOL/>
            <LAST_MAINT_OPRID/>
            <DATE_LAST_MAINT/>
            <xsl:for-each select="custAddrSeq">
              <CUST_ADDR_SEQ class="R">
                <SETID>SHARE</SETID>
                <CUST_ID><xsl:value-of select="custAddrSeqId/custCode" /></CUST_ID>
                <ADDRESS_SEQ_NUM><xsl:value-of select="custAddrSeqId/locationNumber" /></ADDRESS_SEQ_NUM>
                <DESCR><xsl:value-of select="addressDescr" /></DESCR>
                <BILL_TO_ADDR>
                  <xsl:call-template name="getYNFromBoolean">
                    <xsl:with-param name="booleanInput"><xsl:value-of select="billTo" /></xsl:with-param>
                  </xsl:call-template>                
                </BILL_TO_ADDR>
                <SHIP_TO_ADDR>
                  <xsl:call-template name="getYNFromBoolean">
                    <xsl:with-param name="booleanInput"><xsl:value-of select="shipTo" /></xsl:with-param>
                  </xsl:call-template>                
                </SHIP_TO_ADDR>
                <SOLD_TO_ADDR>
                  <xsl:call-template name="getYNFromBoolean">
                    <xsl:with-param name="booleanInput"><xsl:value-of select="soldTo" /></xsl:with-param>
                  </xsl:call-template>                
                </SOLD_TO_ADDR>                  
                <CRSPD_TO_ADDR>
                  <xsl:call-template name="getYNFromBoolean">
                    <xsl:with-param name="booleanInput"><xsl:value-of select="corresponence" /></xsl:with-param>
                  </xsl:call-template>                                
                </CRSPD_TO_ADDR>
                <LAST_MAINT_OPRID/>
                <DATE_LAST_MAINT/>
                <xsl:for-each select="custAddress">
                  <CUST_ADDRESS class="R">
                    <SETID>SHARE</SETID>
                    <CUST_ID><xsl:value-of select="custCode" /></CUST_ID>
                    <ADDRESS_SEQ_NUM><xsl:value-of select="locationNumber" /></ADDRESS_SEQ_NUM>
                    <EFFDT>
                      <xsl:call-template name="getNewDateFormat">
                        <xsl:with-param name="dateInput"><xsl:value-of select="effDate" /></xsl:with-param>
                      </xsl:call-template>                
                    </EFFDT>
                    <EFF_STATUS><xsl:value-of select="effStatus" /></EFF_STATUS>
                    <COUNTRY><xsl:value-of select="country" /></COUNTRY>
                    <ADDRESS1><xsl:value-of select="address1" /></ADDRESS1>
                    <ADDRESS2><xsl:value-of select="address2" /></ADDRESS2>
                    <ADDRESS3><xsl:value-of select="address3" /></ADDRESS3>
                    <ADDRESS4><xsl:value-of select="address4" /></ADDRESS4>
                    <CITY><xsl:value-of select="city" /></CITY>
                    <NUM1/>
                    <NUM2/>
                    <HOUSE_TYPE/>
                    <ADDR_FIELD1/>
                    <ADDR_FIELD2/>
                    <ADDR_FIELD3/>
                    <COUNTY><xsl:value-of select="county" /></COUNTY>
                    <STATE><xsl:value-of select="state" /></STATE>
                    <POSTAL><xsl:value-of select="postal" /></POSTAL>
                    <GEO_CODE/>
                    <IN_CITY_LIMIT>
                      <xsl:call-template name="getYNFromBoolean">
                        <xsl:with-param name="booleanInput"><xsl:value-of select="inCityLimit" /></xsl:with-param>
                      </xsl:call-template>                                
                    </IN_CITY_LIMIT>
                    <COUNTRY_CODE/>
                    <PHONE><xsl:value-of select="phoneNumber" /></PHONE>
                    <EXTENSION><xsl:value-of select="phoneExtension" /></EXTENSION>
                    <FAX><xsl:value-of select="fax" /></FAX>
                    <LAST_MAINT_OPRID></LAST_MAINT_OPRID>
                    <DATE_LAST_MAINT></DATE_LAST_MAINT>
                  </CUST_ADDRESS>
                </xsl:for-each>
                <PSCAMA class="R">
                  <LANGUAGE_CD/>
                  <AUDIT_ACTN>A</AUDIT_ACTN>
                  <BASE_LANGUAGE_CD/>
                  <MSG_SEQ_FLG/>
                  <PROCESS_INSTANCE>0</PROCESS_INSTANCE>
                  <PUBLISH_RULE_ID/>
                  <MSGNODENAME/>
                </PSCAMA>
              </CUST_ADDR_SEQ>
              <PSCAMA class="R">
                <LANGUAGE_CD/>
                <AUDIT_ACTN>A</AUDIT_ACTN>
                <BASE_LANGUAGE_CD/>
                <MSG_SEQ_FLG/>
                <PROCESS_INSTANCE>0</PROCESS_INSTANCE>
                <PUBLISH_RULE_ID/>
                <MSGNODENAME/>
              </PSCAMA>
            </xsl:for-each>
            <CUST_OPTION class="R">
              <SETID>SHARE</SETID>
              <CUST_ID><xsl:value-of select="custCode" /></CUST_ID>
              <EFFDT><xsl:value-of select="sinceDate" /></EFFDT>
              <EFF_STATUS>A</EFF_STATUS>
              <PYMNT_TERMS_CD/>
              <PYMNT_TERMS_AMT/>
              <GRACE_DUE_DAYS>0</GRACE_DUE_DAYS>
              <GRACE_DISC_DAYS>0</GRACE_DISC_DAYS>
              <RETENTION_DAYS>0</RETENTION_DAYS>
              <PP_HOLD/>
              <PP_METHOD/>
              <REF_QUALIFIER_CODE/>
              <PARTIAL_PY_SW/>
              <PO_REQUIRED/>
              <BILL_BY_ID/>
              <FREIGHT_BILL_TYPE/>
              <BILL_CYCLE_ID/>
              <BILL_INQUIRY_PHONE/>
              <BILL_TYPE_ID/>
              <BILLING_SPECIALIST/>
              <COLLECTOR><xsl:value-of select="collectorName" /></COLLECTOR>
              <CR_ANALYST><xsl:value-of select="creditAnalystName" /></CR_ANALYST>
              <INVOICE_FORM_ID/>
              <DST_ID_AR/>
              <DRAFT_APPROVAL/>
              <DRAFT_DOC/>
              <DRAFT_TYPE/>
              <DD_PROFILE_ID/>
              <DD_GROUP/>
              <PAYMENT_METHOD/>
              <DIRECT_INVOICING/>
              <CUSTOMER_PO/>
              <START_DATE/>
              <END_DATE/>
              <MICR_ID/>
              <CONSOL_SETID/>
              <CONSOL_CUST_ID/>
              <CONSOL_KEY/>
              <INTERUNIT_FLG>
                <xsl:call-template name="getYNFromBoolean">
                  <xsl:with-param name="booleanInput"><xsl:value-of select="interunitInd" /></xsl:with-param>
                </xsl:call-template>                                
              </INTERUNIT_FLG>
              <BUSINESS_UNIT_GL><xsl:value-of select="interunitBusUnitName" /></BUSINESS_UNIT_GL>
              <DM_SPECIALIST/>
              <DM_WRITE_OFF_AMT>0</DM_WRITE_OFF_AMT>
              <DM_WRITE_OFF_DAYS>0</DM_WRITE_OFF_DAYS>
              <DM_WRITE_OFF_PCT>0</DM_WRITE_OFF_PCT>
              <HOL_PROC_OPT/>
              <HOL_PROC_DAYS>0</HOL_PROC_DAYS>
              <HOL_PROC_OVERFL/>
              <REBATE_METHOD/>
              <BI_PROMPT_CURR/>
              <LAST_MAINT_OPRID/>
              <DATE_LAST_MAINT/>
            </CUST_OPTION>
            <PSCAMA class="R">
              <LANGUAGE_CD/>
              <AUDIT_ACTN>A</AUDIT_ACTN>
              <BASE_LANGUAGE_CD/>
              <MSG_SEQ_FLG/>
              <PROCESS_INSTANCE>0</PROCESS_INSTANCE>
              <PUBLISH_RULE_ID/>
              <MSGNODENAME/>
            </PSCAMA>
            <CUST_SOLDTO_OPT class="R">
              <SETID>SHARE</SETID>
              <CUST_ID><xsl:value-of select="custCode" /></CUST_ID>
              <EFFDT><xsl:value-of select="sinceDate" /></EFFDT>
              <EFF_STATUS>A</EFF_STATUS>
              <FRT_CHRG_METHOD/>
              <GRACE_PRICE_DAYS>0</GRACE_PRICE_DAYS>
              <LABEL_FORMAT_ID2/>
              <LABEL_FORMAT_ID3/>
              <LOAD_PRICE_RULE/>
              <PROD_ID_SRC/>
              <PROD_SUBSTITUTE/>
              <PRICE_PROGRAM/>
              <PRICE_RULE_CD/>
              <RESTOCK_FEE_FLAG/>
              <DATE_LAST_MAINT/>
              <LAST_MAINT_OPRID/>
            </CUST_SOLDTO_OPT>
            <PSCAMA class="R">
              <LANGUAGE_CD/>
              <AUDIT_ACTN>A</AUDIT_ACTN>
              <BASE_LANGUAGE_CD/>
              <MSG_SEQ_FLG/>
              <PROCESS_INSTANCE>0</PROCESS_INSTANCE>
              <PUBLISH_RULE_ID/>
              <MSGNODENAME/>
            </PSCAMA>
            <CUST_SHIPTO_OPT class="R">
              <SETID/>
              <CUST_ID/>
              <EFFDT/>
              <EFF_STATUS/>
              <SHIP_TYPE_ID/>
              <SHIP_TYPE_ID_EXP/>
              <SHIP_PRIOR_FLAG/>
              <SHIP_PARTIAL_FLAG/>
              <BCKORDR_CNCL_FLAG/>
              <LANGUAGE_CD/>
              <SHIP_PRIORITY_ID/>
              <DS_NETWORK_CODE/>
              <LAST_MAINT_OPRID/>
              <DATE_LAST_MAINT/>
              <FREIGHT_TERMS/>
              <FREIGHT_TERMS_EXP/>
              <SHIP_EARLY_DAYS>0</SHIP_EARLY_DAYS>
              <SHIP_LATE_DAYS>0</SHIP_LATE_DAYS>
              <SHIP_PARTIAL_ORD/>
              <STORE_NUMBER/>
              <NAFTA_REQ_EXP/>
              <CCI_REQ_EXP/>
              <SED_REQ_EXP/>
              <COO_REQ_EXP/>
              <AERP/>
              <INSURANCE_REQD/>
              <MILITARY_FLG/>
              <EXPORT_ORDER_FLG/>
              <CARRIER_ID/>
              <CARRIER_ID_EXP/>
              <SHIP_PRIORITY_EXP/>
              <SINGLE_SHIP_FLAG/>
              <LABEL_FORMAT_ID2/>
              <LABEL_FORMAT_ID3/>
              <ALLOW_OVERPICK_FLG/>
              <MAX_PICK_TOLERANCE>0</MAX_PICK_TOLERANCE>
            </CUST_SHIPTO_OPT>
            <PSCAMA class="R">
              <LANGUAGE_CD/>
              <AUDIT_ACTN>A</AUDIT_ACTN>
              <BASE_LANGUAGE_CD/>
              <MSG_SEQ_FLG/>
              <PROCESS_INSTANCE>0</PROCESS_INSTANCE>
              <PUBLISH_RULE_ID/>
              <MSGNODENAME/>
            </PSCAMA>
            <CUST_SHIPTO class="R">
              <SETID>SHARE</SETID>
              <CUST_ID><xsl:value-of select="custCode" /></CUST_ID>
              <SHIP_TO_CUST_ID/>
              <SLDTO_PRIORITY_NBR>0</SLDTO_PRIORITY_NBR>
              <LAST_MAINT_OPRID/>
              <DATE_LAST_MAINT/>
            </CUST_SHIPTO>
            <PSCAMA class="R">
              <LANGUAGE_CD/>
              <AUDIT_ACTN>A</AUDIT_ACTN>
              <BASE_LANGUAGE_CD/>
              <MSG_SEQ_FLG/>
              <PROCESS_INSTANCE>0</PROCESS_INSTANCE>
              <PUBLISH_RULE_ID/>
              <MSGNODENAME/>
            </PSCAMA>
            <CUST_BILLTO class="R">
              <SETID>SHARE</SETID>
              <CUST_ID><xsl:value-of select="custCode" /></CUST_ID>
              <BILL_TO_CUST_ID />
              <SLDTO_PRIORITY_NBR>1</SLDTO_PRIORITY_NBR>
              <LAST_MAINT_OPRID/>
              <DATE_LAST_MAINT/>
            </CUST_BILLTO>
            <PSCAMA class="R">
              <LANGUAGE_CD/>
              <AUDIT_ACTN>A</AUDIT_ACTN>
              <BASE_LANGUAGE_CD/>
              <MSG_SEQ_FLG/>
              <PROCESS_INSTANCE>0</PROCESS_INSTANCE>
              <PUBLISH_RULE_ID/>
              <MSGNODENAME/>
            </PSCAMA>
            <CUST_CGRP_LNK class="R">
              <SETID>SHARE</SETID>
              <CUST_ID><xsl:value-of select="custCode" /></CUST_ID>
              <CUST_GRP_TYPE/>
              <CUSTOMER_GROUP/>
              <DEFAULT_TAX_GRP/>
              <ROW_ADDED_DTTM/>
              <ROW_ADDED_OPRID/>
              <ROW_LASTMANT_DTTM/>
              <ROW_LASTMANT_OPRID/>
              <SYNCID>0</SYNCID>
              <SYNCDTTM/>
            </CUST_CGRP_LNK>
            <PSCAMA class="R">
              <LANGUAGE_CD/>
              <AUDIT_ACTN>A</AUDIT_ACTN>
              <BASE_LANGUAGE_CD/>
              <MSG_SEQ_FLG/>
              <PROCESS_INSTANCE>0</PROCESS_INSTANCE>
              <PUBLISH_RULE_ID/>
              <MSGNODENAME/>
            </PSCAMA>
            <CUST_TEAM class="R">
              <SETID>SHARE</SETID>
              <CUST_ID><xsl:value-of select="custCode" /></CUST_ID>
              <SUPPORT_TEAM_CD>SUPTM1</SUPPORT_TEAM_CD>
              <DEFAULT_FLAG>Y</DEFAULT_FLAG>
              <LAST_MAINT_OPRID/>
              <DATE_LAST_MAINT/>
            </CUST_TEAM>
            <PSCAMA class="R">
              <LANGUAGE_CD/>
              <AUDIT_ACTN>A</AUDIT_ACTN>
              <BASE_LANGUAGE_CD/>
              <MSG_SEQ_FLG/>
              <PROCESS_INSTANCE>0</PROCESS_INSTANCE>
              <PUBLISH_RULE_ID/>
              <MSGNODENAME/>
            </PSCAMA>
          </CUSTOMER>
          <PSCAMA class="R">
            <LANGUAGE_CD>ENG</LANGUAGE_CD>
            <AUDIT_ACTN><xsl:value-of select="$auditFlag" /></AUDIT_ACTN>
            <BASE_LANGUAGE_CD>ENG</BASE_LANGUAGE_CD>
            <MSG_SEQ_FLG/>
            <PROCESS_INSTANCE>0</PROCESS_INSTANCE>
            <PUBLISH_RULE_ID/>
            <MSGNODENAME/>
          </PSCAMA>
        </Transaction>
      </MsgData>
    </CUSTOMER_SYNC>
  </xsl:template>

</xsl:stylesheet>
