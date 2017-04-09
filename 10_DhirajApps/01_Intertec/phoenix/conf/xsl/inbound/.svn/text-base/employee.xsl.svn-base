<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:em="http://www.intertek.com/ESB/Employee">
	<xsl:include href="../response/responses.xsl" />
	<xsl:template match="em:PublishEmployeeRequest">
		<List>
			<xsl:for-each select="em:MessageData/em:Transaction/em:EmployeeRecord">
				<EmployeeX>
					<employeeId><xsl:value-of select="em:EmployeeID"/></employeeId>
					<firstName><xsl:value-of select="em:FirstName"/></firstName>
					<lastName><xsl:value-of select="em:LastName"/></lastName>
					<samAccount><xsl:value-of select="em:SamAccount"/></samAccount>
					<businessUnit><xsl:value-of select="em:BusinessUnit"/></businessUnit>
					<operatingUnit><xsl:value-of select="em:OperatingUnit"/></operatingUnit>
				</EmployeeX>
			</xsl:for-each>
		</List>
	</xsl:template>
</xsl:stylesheet>