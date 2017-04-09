<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/ns:AcknowledgementResponse" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ns="http://www.intertek.com/ESB/CTFServices" xmlns:xs="http://www.w3.org/2001/XMLSchema">
		<AcknowledgementResponseX>
			<messageId><xsl:value-of select="ns:MessageID"/></messageId>
		</AcknowledgementResponseX>
	</xsl:template>
	
	<xsl:template match="/AcknowledgementX" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ns="http://www.intertek.com/phoenix/schemas" xmlns:xs="http://www.w3.org/2001/XMLSchema">
		<ns:StatusCode><xsl:value-of select="statusCode"/></ns:StatusCode>
	</xsl:template>
	
	<xsl:template match="/CustomerProjectValidationResultX" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ns="http://www.intertek.com/ESB/ProjectValidationResponse" xmlns:xs="http://www.w3.org/2001/XMLSchema">
		<ns:GetProjectValidationResponse>
		 <ns:MessageData>
			<ns:JobNumber><xsl:value-of select="jobNumber"/></ns:JobNumber>
			<ns:JobDescription><xsl:value-of select="jobDescription"/></ns:JobDescription>
			<xsl:for-each select="tests">
				<ns:Test>
					<ns:LineNumber><xsl:value-of select="lineNumber"/></ns:LineNumber>
					<ns:TestStandardId><xsl:value-of select="testStandardId"/></ns:TestStandardId>
					<ns:TestStandardDescription><xsl:value-of select="testStandardDescription"/></ns:TestStandardDescription>
				</ns:Test>
			</xsl:for-each>
		  </ns:MessageData>
		</ns:GetProjectValidationResponse>
	</xsl:template>

	<xsl:template match="/BillableItemsResultX" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ns="http://www.intertek.com/phoenix/schemas" xmlns:xs="http://www.w3.org/2001/XMLSchema">
		<ns:statusCode><xsl:value-of select="statusCode"/></ns:statusCode>
	</xsl:template>
	
	<xsl:template match="/OrderResultX" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ns="http://www.intertek.com/ESB/JobOrderResponse" xmlns:xs="http://www.w3.org/2001/XMLSchema">
          <ns:GetJobOrderResponse>
		<ns:MessageData>
		   <ns:JobNumber><xsl:value-of select="jobNumber"/></ns:JobNumber>
		</ns:MessageData>
	   </ns:GetJobOrderResponse>
	</xsl:template>
	
	<xsl:template match="/ns:GetProjectContractResponse" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ns="http://www.intertek.com/ESB/ProjectContractResponse" xmlns:xs="http://www.w3.org/2001/XMLSchema">
		<ProjectX>
			<projectNumber><xsl:value-of select="ns:MessageData/ns:Transaction/ns:ProjectOutput/ns:ProjectId"/></projectNumber>
		</ProjectX>
	</xsl:template>
</xsl:stylesheet>
