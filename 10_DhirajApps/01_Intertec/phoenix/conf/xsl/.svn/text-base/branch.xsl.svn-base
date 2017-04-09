<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:datetime="http://exslt.org/dates-and-times"
  exclude-result-prefixes="datetime">
  
  <xsl:include href="command_templates.xsl" />

  <xsl:template match="/branch">
    <ITS_LOC_OPUNIT_SYNC>
      <FieldTypes>
        <LOCATION_TBL class="R">
          <SETID type="CHAR"/>
          <LOCATION type="CHAR"/>
          <EFFDT type="DATE"/>
          <EFF_STATUS type="CHAR"/>
          <DESCR type="CHAR"/>
          <DESCR_AC type="CHAR"/>
          <DESCRSHORT type="CHAR"/>
          <BUILDING type="CHAR"/>
          <FLOOR type="CHAR"/>
          <SECTOR type="CHAR"/>
          <JURISDICTION type="CHAR"/>
          <ATTN_TO type="CHAR"/>
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
          <SETID_SALARY type="CHAR"/>
          <SAL_ADMIN_PLAN type="CHAR"/>
          <LANG_CD type="CHAR"/>
          <HOLIDAY_SCHEDULE type="CHAR"/>
          <ESTABID type="CHAR"/>
          <LOCALITY type="CHAR"/>
          <CAN_CMA type="CHAR"/>
          <CAN_OEE_AREACD type="CHAR"/>
          <GEOLOC_CODE type="CHAR"/>
          <OFFICE_TYPE type="CHAR"/>
          <NCR_SW_CAN type="CHAR"/>
          <TBS_OFFICE_CD_CAN type="CHAR"/>
          <WRKS_CNCL_ID_LCL type="CHAR"/>
          <SPK_COMM_ID_GER type="CHAR"/>
          <TARIFF_AREA_GER type="CHAR"/>
          <TARIFF_GER type="CHAR"/>
          <INDUST_INSP_ID_GER type="CHAR"/>
          <NI_REPORT_SW_UK type="CHAR"/>
          <GVT_GEOLOC_CD type="CHAR"/>
          <GVT_DESIG_AGENT type="CHAR"/>
          <SOC_SEC_WRK_CTR type="CHAR"/>
          <MATRICULA_NBR type="NUMBER"/>
          <LABEL_FORMAT_ID2 type="CHAR"/>
          <LABEL_FORMAT_ID3 type="CHAR"/>
          <USG_LBL_FORMAT_ID type="CHAR"/>
          <COMMENTS_2000 type="CHAR"/>
          <REG_REGION type="CHAR"/>
          <SEQUENCE_NUMBER type="NUMBER"/>
          <ITS_BRANCH_TYPE type="CHAR"/>
          <EMAIL_TO type="CHAR"/>
          <ITS_SHADOW_SETID type="CHAR"/>
        </LOCATION_TBL>
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
            <xsl:if test="newFlag = 'NEW'">
              <xsl:text>A</xsl:text>
            </xsl:if>
          </xsl:variable>

          <LOCATION_TBL class="R">
            <SETID IsChanged="Y">SHARE</SETID>  
            <LOCATION IsChanged="Y"><xsl:value-of select="name" /></LOCATION>
            <EFFDT IsChanged="Y">
              <xsl:call-template name="getNewDateFormat">
                <xsl:with-param name="dateInput"><xsl:value-of  select="datetime:dateTime()"/></xsl:with-param>
              </xsl:call-template>                
            </EFFDT> 
            <EFF_STATUS IsChanged="Y"><xsl:value-of select="status" /></EFF_STATUS>
            <DESCR IsChanged="Y"><xsl:value-of select="description" /></DESCR> 
            <DESCR_AC/>
            <DESCRSHORT/>
            <BUILDING/>
            <FLOOR/>
            <SECTOR/>
            <JURISDICTION/>
            <ATTN_TO/>
            <COUNTRY IsChanged="Y"><xsl:value-of select="countryCode" /></COUNTRY> 
            <ADDRESS1 IsChanged="Y"><xsl:value-of select="address1" /></ADDRESS1> 
            <ADDRESS2><xsl:value-of select="address2" /></ADDRESS2>
            <ADDRESS3><xsl:value-of select="address3" /></ADDRESS3>
            <ADDRESS4><xsl:value-of select="address4" /></ADDRESS4>
            <CITY IsChanged="Y"><xsl:value-of select="city" /></CITY>
            <NUM1><xsl:value-of select="num1" /></NUM1>
            <NUM2><xsl:value-of select="num2" /></NUM2>
            <HOUSE_TYPE><xsl:value-of select="houseType" /></HOUSE_TYPE>
            <ADDR_FIELD1><xsl:value-of select="addrField1" /></ADDR_FIELD1>
            <ADDR_FIELD2><xsl:value-of select="addrField2" /></ADDR_FIELD2>
            <ADDR_FIELD3><xsl:value-of select="addrField3" /></ADDR_FIELD3>
            <COUNTY><xsl:value-of select="county" /></COUNTY>
            <STATE IsChanged="Y"><xsl:value-of select="stateCode" /></STATE>
            <POSTAL><xsl:value-of select="postal" /></POSTAL>
            <GEO_CODE/>
            <IN_CITY_LIMIT IsChanged="Y">Y</IN_CITY_LIMIT>
            <COUNTRY_CODE><xsl:value-of select="phonePrefix" /></COUNTRY_CODE>
            <PHONE><xsl:value-of select="phoneNumber" /></PHONE>
            <EXTENSION><xsl:value-of select="phoneExtension" /></EXTENSION>
            <FAX><xsl:value-of select="fax" /></FAX>
            <SETID_SALARY/>
            <SAL_ADMIN_PLAN/>
            <LANG_CD/>
            <HOLIDAY_SCHEDULE/>
            <ESTABID/>
            <LOCALITY/>
            <CAN_CMA/>
            <CAN_OEE_AREACD/>
            <GEOLOC_CODE/>
            <OFFICE_TYPE/>
            <NCR_SW_CAN/>
            <TBS_OFFICE_CD_CAN/>
            <WRKS_CNCL_ID_LCL/>
            <SPK_COMM_ID_GER/>
            <TARIFF_AREA_GER/>
            <TARIFF_GER/>
            <INDUST_INSP_ID_GER/>
            <NI_REPORT_SW_UK/>
            <GVT_GEOLOC_CD/>
            <GVT_DESIG_AGENT/>
            <SOC_SEC_WRK_CTR/>
            <MATRICULA_NBR IsChanged="Y">0</MATRICULA_NBR>
            <LABEL_FORMAT_ID2/>
            <LABEL_FORMAT_ID3/>
            <USG_LBL_FORMAT_ID/>
            <COMMENTS_2000/>
            <REG_REGION/>
            <SEQUENCE_NUMBER IsChanged="Y">0</SEQUENCE_NUMBER>
            <ITS_BRANCH_TYPE IsChanged="Y"><xsl:value-of select="type" /></ITS_BRANCH_TYPE> 
            <EMAIL_TO/>
            <ITS_SHADOW_SETID IsChanged="Y"><xsl:value-of select="buName" /></ITS_SHADOW_SETID> 
          </LOCATION_TBL>
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
    </ITS_LOC_OPUNIT_SYNC>
  </xsl:template>

</xsl:stylesheet>
