<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:include href="command_templates.xsl" />

  <xsl:template match="/jobOrder">
    <ProcessDS xmlns="http://tempuri.org/">
      <xmlData>
        <pstables xmlns="http://tempuri.org/pstables.xsd">
          <PS_ORDER> 
            <PEOPLESOFT_NUMBER><xsl:value-of select="jobNumber" /></PEOPLESOFT_NUMBER> 
            <DESCRIPTION><xsl:value-of select="jobDescription" /></DESCRIPTION> 
            <TAKEN_BY><xsl:value-of select="createdByUserName" /></TAKEN_BY> 
            <TAKEN_ON>
              <xsl:call-template name="getNewDateFormat">
                <xsl:with-param name="dateInput"><xsl:value-of select="createTime" /></xsl:with-param>
              </xsl:call-template>                
            </TAKEN_ON>

            <TAKEN_METHOD>
              <xsl:for-each select="jobContract[nominationFlag='true']">
                <xsl:value-of select="custSentBy"/>
              </xsl:for-each>             
            </TAKEN_METHOD>   
            
            <ORIGINATING_OFFICE><xsl:value-of select="branchName"/></ORIGINATING_OFFICE>

            <ETA>
              <xsl:call-template name="getNewDateFormat">
                <xsl:with-param name="dateInput"><xsl:value-of select="etaDate" /></xsl:with-param>
              </xsl:call-template>                
            </ETA>
            <JOB_TYPE><xsl:value-of select="operation"/></JOB_TYPE>
            <FACILITY><xsl:value-of select="serviceLocation/name"/></FACILITY>

            <SUBMISSION_TYPE><xsl:value-of select="jobType"/></SUBMISSION_TYPE>

            <ORDER_CUSTOMER>
              <xsl:for-each select="jobContract[nominationFlag='true']">
                <xsl:value-of select="customer/custCode"/>
              </xsl:for-each>
            </ORDER_CUSTOMER>

            <xsl:choose>
              <xsl:when test="labAnalysis='true'">
                <LAB_ANALYSIS>T</LAB_ANALYSIS>            
              </xsl:when>
              <xsl:otherwise>
                <LAB_ANALYSIS>F</LAB_ANALYSIS>
              </xsl:otherwise>
            </xsl:choose>

            <xsl:choose>
              <xsl:when test="otApproved='true'">
                <OVERTIME>T</OVERTIME>
              </xsl:when>
              <xsl:otherwise>
                <OVERTIME>F</OVERTIME>
              </xsl:otherwise>
            </xsl:choose>

            <OT_APPROVED_BY><xsl:value-of select="otApprovedby"/></OT_APPROVED_BY>      
            <RETAIN_PERIOD><xsl:value-of select="retainPeriod"/></RETAIN_PERIOD>
            <LAB_INSTRUCTIONS><xsl:value-of select="labInstructions"/></LAB_INSTRUCTIONS>    

            <SHIPPING_INSTRUCTIONS>
              <xsl:if test="shipInstructions!=''"><xsl:value-of select="shipInstructions"/></xsl:if>
              <xsl:if test="shipInstructions=''">NONE</xsl:if>
            </SHIPPING_INSTRUCTIONS>               

            <REPORTING_INSTRUCTIONS><xsl:value-of select="reptInstructions"/></REPORTING_INSTRUCTIONS>
            <BILLING_INSTRUCTIONS><xsl:value-of select="billInstructions"/></BILLING_INSTRUCTIONS>            
            
            <LIMS_BRANCH_ID><xsl:value-of select="limsBranchId"/></LIMS_BRANCH_ID>
          </PS_ORDER> 

          <xsl:for-each select="jobContract">
            <PS_CUSTOMER>
              <PEOPLESOFT_NUMBER><xsl:value-of select="jobNumber" /></PEOPLESOFT_NUMBER>
              <CUSTOMER><xsl:value-of select="customer/custCode"/></CUSTOMER>
              <xsl:choose>
                <xsl:when test="nominationFlag='true'">
                  <PRIMARY_CUSTOMER>T</PRIMARY_CUSTOMER>
                </xsl:when>
                <xsl:otherwise>
                  <PRIMARY_CUSTOMER>F</PRIMARY_CUSTOMER>
                </xsl:otherwise>
              </xsl:choose>              
              <REFERENCE_NUM><xsl:value-of select="custRefNum"/></REFERENCE_NUM>
              
              <PX_REF1><xsl:value-of select="invoiceValue1"/></PX_REF1>
              <PX_REF2><xsl:value-of select="invoiceValue2"/></PX_REF2>
              <PX_REF3><xsl:value-of select="invoiceValue3"/></PX_REF3>
              <PX_REF4><xsl:value-of select="invoiceValue4"/></PX_REF4>
              <INTERNAL_REF><xsl:value-of select="invoiceValue5"/></INTERNAL_REF>
            </PS_CUSTOMER>
          </xsl:for-each>

          <xsl:for-each select="jobVessel">
            <xsl:for-each select="jobProd">
              <PS_PRODUCTS>
                <PEOPLESOFT_NUMBER><xsl:value-of select="jobProdId/jobNumber" /></PEOPLESOFT_NUMBER>
                <I_PRODUCT><xsl:value-of select="jobProductName"/></I_PRODUCT>
              </PS_PRODUCTS>
            </xsl:for-each>
          </xsl:for-each>



          <xsl:for-each select="jobContract">    
           <PS_REPORT_TO>
             <PEOPLESOFT_NUMBER><xsl:value-of select="jobNumber" /></PEOPLESOFT_NUMBER>
             <REPORT_TO><xsl:value-of select="contact/id" /></REPORT_TO>
           </PS_REPORT_TO>
          </xsl:for-each>       



          <xsl:for-each select="jobVessel">
            <PS_VESSEL>
              <PEOPLESOFT_NUMBER><xsl:value-of select="jobVesselId/jobNumber" /></PEOPLESOFT_NUMBER>
              <VESSEL><xsl:value-of select="vesselName"/></VESSEL>
              <VESSEL_TYPE><xsl:value-of select="type"/></VESSEL_TYPE>
            </PS_VESSEL>
          </xsl:for-each>  

          <xsl:for-each select="jobVessel">
            <xsl:for-each select="jobProd">
              <xsl:for-each select="jobProdSample">
                <PS_SAMPLE>
                  <PEOPLESOFT_NUMBER><xsl:value-of select="jobProdSampleId/jobNumber" /></PEOPLESOFT_NUMBER>
                  <VESSEL><xsl:value-of select="../../vesselName"/></VESSEL>
                  <PS_PRODUCT><xsl:value-of select="../jobProductName"/></PS_PRODUCT>
                  <SEQ_NO><xsl:value-of select="jobProdSampleId/linkedVslRow" />-<xsl:value-of select="jobProdSampleId/prodSeqNum" />-<xsl:value-of select="jobProdSampleId/sampSeqId" /></SEQ_NO>
                  <SAMPLE_LOC><xsl:value-of select="jobSampleLocation" /></SAMPLE_LOC>
                  <SAMPLE_NO><xsl:value-of select="jobProdSampleId/sampSeqId" /></SAMPLE_NO>
                  <TANK_NO><xsl:value-of select="tankNumber" /></TANK_NO>
                  <SAMPLE_TIMING><xsl:value-of select="sampleTiming" /></SAMPLE_TIMING>
                  <SAMPLE_TYPE><xsl:value-of select="sampleType" /></SAMPLE_TYPE>
                  <SAMPLE_VOLUME><xsl:value-of select="sampleVolume" /></SAMPLE_VOLUME>
                  <CONTAINER_TYPE><xsl:value-of select="containerType" /></CONTAINER_TYPE>

                  <xsl:choose>
                    <xsl:when test="retainTested='R'">
                      <RETAIN_ONLY>T</RETAIN_ONLY>
                    </xsl:when>
                    <xsl:otherwise>
                      <RETAIN_ONLY>F</RETAIN_ONLY>
                    </xsl:otherwise>
                  </xsl:choose>
                  <RETAIN_TESTED><xsl:value-of select="retainTested" /></RETAIN_TESTED>
                  <xsl:choose>
                    <xsl:when test="../../../otApproved='true'">
                      <OT>T</OT>
                    </xsl:when>
                    <xsl:otherwise>
                      <OT>F</OT>
                    </xsl:otherwise>
                  </xsl:choose>
                  <INSTRUCTIONS><xsl:value-of select="sampleInstructions" /></INSTRUCTIONS>         
                </PS_SAMPLE>
              </xsl:for-each>
            </xsl:for-each>
          </xsl:for-each>

        </pstables>
      </xmlData>         
    </ProcessDS>      
  </xsl:template>

</xsl:stylesheet>
