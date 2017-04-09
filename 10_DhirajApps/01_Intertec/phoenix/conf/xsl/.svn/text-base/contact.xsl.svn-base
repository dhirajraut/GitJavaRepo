<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/contact">
    <CONTACT_SYNC>
      <FieldTypes>
        <CONTACT class="R">
          <SETID type="CHAR"/>
          <CONTACT_ID type="CHAR"/>
          <EFF_STATUS type="CHAR"/>
          <CONTACT_FLAG type="CHAR"/>
          <NAME1 type="CHAR"/>
          <TITLE type="CHAR"/>
          <LANGUAGE_CD type="CHAR"/>
          <EMAILID type="CHAR"/>
          <COMM_METHOD type="CHAR"/>
          <SALUTATION_CD type="CHAR"/>
          <SALUTATION type="CHAR"/>
          <CREATE_CUSTOMER type="CHAR"/>
          <LAST_MAINT_OPRID type="CHAR"/>
          <DATE_LAST_MAINT type="DATE"/>
          <AUTHORIZATION_ID type="CHAR"/>
          <PERSON_ID type="CHAR"/>
        </CONTACT>
        <CONTACT_CUST class="R">
          <SETID type="CHAR"/>
          <CONTACT_ID type="CHAR"/>
          <CUSTOMER_SETID type="CHAR"/>
          <CUST_ID type="CHAR"/>
          <EFF_STATUS type="CHAR"/>
          <CNTCT_SEQ_NUM type="NUMBER"/>
          <ADDRESS_SEQ_NUM type="NUMBER"/>
          <BILL_TO_FLG type="CHAR"/>
          <SOLD_TO_FLG type="CHAR"/>
          <SHIP_TO_FLG type="CHAR"/>
          <LAST_MAINT_OPRID type="CHAR"/>
          <DATE_LAST_MAINT type="DATE"/>
        </CONTACT_CUST>
        <CONTACT_PHN class="R">
          <SETID type="CHAR"/>
          <CONTACT_ID type="CHAR"/>
          <PHONE_TYPE type="CHAR"/>
          <COUNTRY_CODE type="CHAR"/>
          <PHONE type="CHAR"/>
          <EXTENSION type="CHAR"/>
          <LAST_MAINT_OPRID type="CHAR"/>
          <DATE_LAST_MAINT type="DATE"/>
        </CONTACT_PHN>
        <CONTACT_PAGER class="R">
          <SETID type="CHAR"/>
          <CONTACT_ID type="CHAR"/>
          <PHONE_TYPE type="CHAR"/>
          <COUNTRY_CODE type="CHAR"/>
          <PHONE type="CHAR"/>
          <EXTENSION type="CHAR"/>
          <PASSWORD type="CHAR"/>
          <LAST_MAINT_OPRID type="CHAR"/>
          <DATE_LAST_MAINT type="DATE"/>
        </CONTACT_PAGER>
        <CONTACT_CARD class="R">
          <SETID type="CHAR"/>
          <CONTACT_ID type="CHAR"/>
          <CUSTOMER_SETID type="CHAR"/>
          <CUST_ID type="CHAR"/>
          <CR_CARD_NBR type="CHAR"/>
          <CR_CARD_TYPE type="CHAR"/>
          <PRIMARY_CARD type="CHAR"/>
          <CR_CARD_FNAME type="CHAR"/>
          <CR_CARD_LNAME type="CHAR"/>
          <CR_CARD_EXPMO type="CHAR"/>
          <CR_CARD_EXPYR type="CHAR"/>
          <CR_CARD_DIGITS type="CHAR"/>
          <ADDR_SEQ_NUM type="NUMBER"/>
          <LAST_MAINT_OPRID type="CHAR"/>
          <DATE_LAST_MAINT type="DATE"/>
        </CONTACT_CARD>
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

          <xsl:variable name="myFinContactId" select="finContactId" />

          <xsl:variable name="auditFlag">
            <xsl:if test="newFlag = 'NEW'">
              <xsl:text>A</xsl:text>
            </xsl:if>
          </xsl:variable>

          <CONTACT class="R">
            <SETID>SHARE</SETID>
            <CONTACT_ID><xsl:value-of select="$myFinContactId" /></CONTACT_ID>            
            <EFF_STATUS><xsl:value-of select="status" /></EFF_STATUS>
            <CONTACT_FLAG><xsl:value-of select="contactFlag" /></CONTACT_FLAG>
            <NAME1><xsl:value-of select="lastName" />,<xsl:value-of select="firstName" /></NAME1>
            <TITLE><xsl:value-of select="title" /></TITLE>
            <LANGUAGE_CD></LANGUAGE_CD>
            <EMAILID><xsl:value-of select="workEmail" /></EMAILID>
            <COMM_METHOD><xsl:value-of select="prefCommunication" /></COMM_METHOD>
            <SALUTATION_CD><xsl:value-of select="salutationCd" /></SALUTATION_CD>
            <SALUTATION/>
            <CREATE_CUSTOMER/>
            <LAST_MAINT_OPRID/>
            <DATE_LAST_MAINT/>
            <AUTHORIZATION_ID/>
            <PERSON_ID><xsl:value-of select="id" /></PERSON_ID>            
            <xsl:for-each select="contactCust">
              <CONTACT_CUST class="R">
                <SETID>SHARE</SETID>
                <CONTACT_ID><xsl:value-of select="$myFinContactId" /></CONTACT_ID>
                <CUSTOMER_SETID>SHARE</CUSTOMER_SETID>
                <CUST_ID><xsl:value-of select="contactCustId/custCode" /></CUST_ID>
                <EFF_STATUS><xsl:value-of select="status" /></EFF_STATUS>
                <CNTCT_SEQ_NUM>1</CNTCT_SEQ_NUM>
                <ADDRESS_SEQ_NUM><xsl:value-of select="contactCustId/locationNumber" /></ADDRESS_SEQ_NUM>                
                <BILL_TO_FLG><xsl:value-of select="billTo" /></BILL_TO_FLG>
                <SOLD_TO_FLG><xsl:value-of select="soldTo" /></SOLD_TO_FLG>
                <SHIP_TO_FLG><xsl:value-of select="shipTo" /></SHIP_TO_FLG>
                <LAST_MAINT_OPRID/>
                <DATE_LAST_MAINT/>
              </CONTACT_CUST>
              <PSCAMA class="R">
                <LANGUAGE_CD/>
                <AUDIT_ACTN>A</AUDIT_ACTN>
                <BASE_LANGUAGE_CD/>
                <MSG_SEQ_FLG/>
                <PROCESS_INSTANCE>0</PROCESS_INSTANCE>
                <PUBLISH_RULE_ID/>
                <MSGNODENAME/>
              </PSCAMA>
              <CONTACT_CARD class="R">
                <SETID>SHARE</SETID>
                <CONTACT_ID><xsl:value-of select="$myFinContactId" /></CONTACT_ID>
                <CUSTOMER_SETID>SHARE</CUSTOMER_SETID>
                <CUST_ID><xsl:value-of select="contactCustId/custCode" /></CUST_ID>
                <CR_CARD_NBR/>
                <CR_CARD_TYPE/>
                <PRIMARY_CARD/>
                <CR_CARD_FNAME/>
                <CR_CARD_LNAME/>
                <CR_CARD_EXPMO/>
                <CR_CARD_EXPYR/>
                <CR_CARD_DIGITS/>
                <ADDR_SEQ_NUM/>
                <LAST_MAINT_OPRID/>
                <DATE_LAST_MAINT/>
              </CONTACT_CARD>
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
            <xsl:if test="workPhone">
              <CONTACT_PHN class="R">
                <SETID>SHARE</SETID>
                <CONTACT_ID><xsl:value-of select="$myFinContactId" /></CONTACT_ID>
                <PHONE_TYPE>BUSN</PHONE_TYPE>
                <COUNTRY_CODE/>
                <PHONE><xsl:value-of select="workPhone" /></PHONE>
                <EXTENSION/>
                <LAST_MAINT_OPRID></LAST_MAINT_OPRID>
                <DATE_LAST_MAINT></DATE_LAST_MAINT>
              </CONTACT_PHN>
              <PSCAMA class="R">
                <LANGUAGE_CD/>
                <AUDIT_ACTN>A</AUDIT_ACTN>
                <BASE_LANGUAGE_CD/>
                <MSG_SEQ_FLG/>
                <PROCESS_INSTANCE>0</PROCESS_INSTANCE>
                <PUBLISH_RULE_ID/>
                <MSGNODENAME/>
              </PSCAMA>
            </xsl:if>
            <xsl:if test="personalPhone">
              <CONTACT_PHN class="R">
                <SETID>SHARE</SETID>
                <CONTACT_ID><xsl:value-of select="$myFinContactId" /></CONTACT_ID>
                <PHONE_TYPE>HOME</PHONE_TYPE>
                <COUNTRY_CODE/>
                <PHONE><xsl:value-of select="personalPhone" /></PHONE>
                <EXTENSION/>
                <LAST_MAINT_OPRID></LAST_MAINT_OPRID>
                <DATE_LAST_MAINT></DATE_LAST_MAINT>
              </CONTACT_PHN>
              <PSCAMA class="R">
                <LANGUAGE_CD/>
                <AUDIT_ACTN>A</AUDIT_ACTN>
                <BASE_LANGUAGE_CD/>
                <MSG_SEQ_FLG/>
                <PROCESS_INSTANCE>0</PROCESS_INSTANCE>
                <PUBLISH_RULE_ID/>
                <MSGNODENAME/>
              </PSCAMA>
            </xsl:if>
            <xsl:if test="cellPhone">
              <CONTACT_PHN class="R">
                <SETID>SHARE</SETID>
                <CONTACT_ID><xsl:value-of select="$myFinContactId" /></CONTACT_ID>
                <PHONE_TYPE>CELL</PHONE_TYPE>
                <COUNTRY_CODE/>
                <PHONE><xsl:value-of select="cellPhone" /></PHONE>
                <EXTENSION/>
                <LAST_MAINT_OPRID></LAST_MAINT_OPRID>
                <DATE_LAST_MAINT></DATE_LAST_MAINT>
              </CONTACT_PHN>
              <PSCAMA class="R">
                <LANGUAGE_CD/>
                <AUDIT_ACTN>A</AUDIT_ACTN>
                <BASE_LANGUAGE_CD/>
                <MSG_SEQ_FLG/>
                <PROCESS_INSTANCE>0</PROCESS_INSTANCE>
                <PUBLISH_RULE_ID/>
                <MSGNODENAME/>
              </PSCAMA>
            </xsl:if>
            <CONTACT_PAGER class="R">
              <SETID>SHARE</SETID>
              <CONTACT_ID><xsl:value-of select="$myFinContactId" /></CONTACT_ID>
              <PHONE_TYPE/>
              <COUNTRY_CODE/>
              <PHONE/>
              <EXTENSION/>
              <PASSWORD/>
              <LAST_MAINT_OPRID/>
              <DATE_LAST_MAINT/>
            </CONTACT_PAGER>
            <PSCAMA class="R">
              <LANGUAGE_CD/>
              <AUDIT_ACTN>A</AUDIT_ACTN>
              <BASE_LANGUAGE_CD/>
              <MSG_SEQ_FLG/>
              <PROCESS_INSTANCE>0</PROCESS_INSTANCE>
              <PUBLISH_RULE_ID/>
              <MSGNODENAME/>
            </PSCAMA>
          </CONTACT>
          <PSCAMA class="R">
            <LANGUAGE_CD>ENG</LANGUAGE_CD>
            <xsl:choose>
              <xsl:when test="$auditFlag = 'A'">
                <AUDIT_ACTN>A</AUDIT_ACTN>
              </xsl:when>
              <xsl:otherwise>
                <AUDIT_ACTN>C</AUDIT_ACTN>
              </xsl:otherwise>
            </xsl:choose>
            <BASE_LANGUAGE_CD>ENG</BASE_LANGUAGE_CD>
            <MSG_SEQ_FLG/>
            <PROCESS_INSTANCE>0</PROCESS_INSTANCE>
            <PUBLISH_RULE_ID/>
            <MSGNODENAME/>
          </PSCAMA>
        </Transaction>
      </MsgData>
    </CONTACT_SYNC>
  </xsl:template>

</xsl:stylesheet>
