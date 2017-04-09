<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/jobOrder">
    <setNomination>
      <nominationWsRequest>
        <userName>webservice.psoft</userName>
        <password>password</password>
        <nomination>
          <nominationHeader>
            <nominationId><xsl:value-of select="jobNumber" /></nominationId>
            <nominationStatus>
              <xsl:choose>
                <xsl:when test="jobStatus = '1000' or jobStatus = '7000'">
                  <xsl:text>Open</xsl:text>
                </xsl:when>
                <xsl:otherwise>
                  <xsl:text>CLOSED</xsl:text>
                </xsl:otherwise>
              </xsl:choose>
            </nominationStatus>
            <businessUnitId><xsl:value-of select="buName" /></businessUnitId>
            <areaId></areaId>
            <branchId><xsl:value-of select="branchName" /></branchId>
            <receivedBy><xsl:value-of select="receivedByUserName" /></receivedBy>
            <nominationDate><xsl:value-of select="nominationDate" /></nominationDate>
            <nominationType><xsl:value-of select="jobType" /></nominationType>
            <nominationJobType><xsl:value-of select="jobType" /></nominationJobType>
            <agent><xsl:value-of select="shippingAgent/name" /></agent>
            <agentTelephone><xsl:value-of select="shippingAgentPhone" /></agentTelephone>
            <towing><xsl:value-of select="towingCompany/name" /></towing>
            <towingTelephone><xsl:value-of select="towingCompanyPhone" /></towingTelephone>
            <eta><xsl:value-of select="etaDate" /></eta>
            <createdBy><xsl:value-of select="createdByUserName" /></createdBy>
            <updatedBy><xsl:value-of select="updatedByUserName" /></updatedBy>
            <dateCreated><xsl:value-of select="createTime" /></dateCreated>
            <dateUpdated><xsl:value-of select="updateTime" /></dateUpdated>
            <inspectorVersion></inspectorVersion>
          </nominationHeader>

          <nominationInspectionInstruction>
            <nominationId><xsl:value-of select="jobNumber" /></nominationId>
            <inspectionInst><xsl:value-of select="inspInstructions" /></inspectionInst>
          </nominationInspectionInstruction>

          <nominationSamplingInstruction>
            <nominationId><xsl:value-of select="jobNumber" /></nominationId>
            <samplingInst><xsl:value-of select="sampInstructions" /></samplingInst>
          </nominationSamplingInstruction>
          <nominationLabInstruction>
            <nominationId><xsl:value-of select="jobNumber" /></nominationId>
            <labInst><xsl:value-of select="labInstructions" /></labInst>
          </nominationLabInstruction>
          <nominationShippingInstruction>
            <nominationId><xsl:value-of select="jobNumber" /></nominationId>
            <shippingInst><xsl:value-of select="shipInstructions" /></shippingInst>
          </nominationShippingInstruction>
          <nominationReportingInstruction>
            <nominationId><xsl:value-of select="jobNumber" /></nominationId>
            <reportingInst><xsl:value-of select="reptInstructions" /></reportingInst>
          </nominationReportingInstruction>
          <nominationBillingInstruction>
            <nominationId><xsl:value-of select="jobNumber" /></nominationId>
            <billingInst><xsl:value-of select="billInstructions" /></billingInst>
          </nominationBillingInstruction>
          <nominationOtherInstruction>
            <nominationId><xsl:value-of select="jobNumber" /></nominationId>
            <otherInst><xsl:value-of select="otherInstructions" /></otherInst>
          </nominationOtherInstruction>
          <nominationReviewedAccepted>
            <nominationId><xsl:value-of select="jobNumber" /></nominationId>
            <icbOperationReview></icbOperationReview>
            <icbLabReview></icbLabReview>
            <icbDispatcherReview></icbDispatcherReview>
            <icbInspectorReview></icbInspectorReview>
          </nominationReviewedAccepted>
          <nominationLaboratoryEvent>
            <nominationId><xsl:value-of select="jobNumber" /></nominationId>
            <retainPeriod><xsl:value-of select="retainPeriod" /></retainPeriod>
            <otApprovedBy><xsl:value-of select="otApprovedby" /></otApprovedBy>
          </nominationLaboratoryEvent>

          <xsl:for-each select="jobVessel">
            <vessel>
              <nominationId><xsl:value-of select="jobNumber" /></nominationId>
              <vesselName><xsl:value-of select="vesselName" /></vesselName>
              <vesselType><xsl:value-of select="type" /></vesselType>
              <applicableCustomers></applicableCustomers>
            </vessel>
          </xsl:for-each>
          <xsl:for-each select="jobVessel">
            <xsl:for-each select="jobProd">
              <product>
                <nominationId><xsl:value-of select="jobNumber" /></nominationId>
                <productName><xsl:value-of select="jobProductName" /></productName>
                <productOption><xsl:value-of select="option" /></productOption>
                <productQuantity><xsl:value-of select="productQty" /></productQuantity>
                <productMeasurement><xsl:value-of select="uom" /></productMeasurement>
                <applicableCustomers></applicableCustomers>
                <plusMinus><xsl:value-of select="plusMinus" /></plusMinus>
                <percent><xsl:value-of select="plusMinusPct" /></percent>
                <tanks><xsl:value-of select="tanks" /></tanks>
                <draft><xsl:value-of select="drafts" /></draft>
              </product>
            </xsl:for-each>
          </xsl:for-each>

          <terminal>
            <nominationId><xsl:value-of select="jobNumber" /></nominationId>
            <portName><xsl:value-of select="serviceLocation/city" /></portName>
            <terminalName><xsl:value-of select="serviceLocation/name" /></terminalName>
          </terminal>

          <xsl:for-each select="jobContract">
            <contract>
              <nominationId><xsl:value-of select="jobNumber" /></nominationId>
              <customerReference><xsl:value-of select="custRefNum" /></customerReference>
              <companyName><xsl:value-of select="customer/name" /></companyName>
              <scheduler><xsl:value-of select="concat(contact/firstName, ' ', contact/lastName)" /></scheduler>
              <schedulerPhone><xsl:value-of select="contact/workPhone" /></schedulerPhone>
              <schedulerEmail><xsl:value-of select="contact/workEmail" /></schedulerEmail>
              <customerSentBy><xsl:value-of select="custSentBy" /></customerSentBy>
            </contract>
          </xsl:for-each>

          <xsl:for-each select="jobVessel">
            <xsl:for-each select="jobProd">
              <xsl:for-each select="jobInspectionEvent">
                <event>
                  <nominationId><xsl:value-of select="jobNumber" /></nominationId>
                  <eventName><xsl:value-of select="jobInspectionEventId/jobInspEvtName" /></eventName>
                  <eventDescription><xsl:value-of select="instructions" /></eventDescription>
                  <applicableCustomers></applicableCustomers>
                </event>
              </xsl:for-each>
            </xsl:for-each>
          </xsl:for-each>

          <xsl:for-each select="jobVessel">
            <xsl:for-each select="jobProd">
              <xsl:for-each select="jobProdSample">
                <nominationSampleInstruction>
                  <nominationId><xsl:value-of select="jobNumber" /></nominationId>
                  <sampleLocation><xsl:value-of select="jobSampleLocation" /></sampleLocation>
                  <tankNo><xsl:value-of select="tankNumber" /></tankNo>
                  <sampleTiming><xsl:value-of select="sampleTiming" /></sampleTiming>
                  <sampleType><xsl:value-of select="sampleType" /></sampleType>
                  <sampleVolume><xsl:value-of select="sampleVolume" /></sampleVolume>
                  <containerType><xsl:value-of select="containerType" /></containerType>
                  <retainTested><xsl:value-of select="retainTested" /></retainTested>
                  <ot></ot>
                </nominationSampleInstruction>
              </xsl:for-each>
            </xsl:for-each>
          </xsl:for-each>

        </nomination>
      </nominationWsRequest>
    </setNomination>
  </xsl:template>

</xsl:stylesheet>
