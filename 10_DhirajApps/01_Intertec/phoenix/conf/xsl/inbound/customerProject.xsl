<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:proj="http://www.intertek.com/ESB/ProjectValidationRequest">
	<xsl:include href="../response/responses.xsl" />
	<xsl:template match="proj:GetProjectValidationRequest">
		<List>
			<xsl:for-each select="proj:MessageData">
	            <ProjectValidationX>
	               <projectNumber><xsl:value-of select="proj:ProjectNumber"/></projectNumber>
	               <customerId><xsl:value-of select="proj:CustomerId"/></customerId>
	            </ProjectValidationX>
			</xsl:for-each>
		</List>
	</xsl:template>
</xsl:stylesheet>