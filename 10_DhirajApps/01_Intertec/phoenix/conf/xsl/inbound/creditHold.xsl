<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cred="http://www.intertek.com/ESB/CreditHold">
	<xsl:include href="../response/responses.xsl" />
	<xsl:template match="cred:PublishCreditHoldRequest">
		<List>
			<xsl:for-each select="cred:MessageData/cred:Transaction">
	            <CreditHoldX>
	               <customerId><xsl:value-of select="cred:CustomerId"/></customerId>
	               <customerName><xsl:value-of select="cred:CustomerName"/></customerName>
	               <creditHoldFlag><xsl:value-of select="cred:CreditHoldFlag"/></creditHoldFlag>
	               <dateTime><xsl:value-of select="cred:DateTime"/></dateTime>
	               <operatorId><xsl:value-of select="cred:OperatorId"/></operatorId>
	            </CreditHoldX>
			</xsl:for-each>
		</List>
	</xsl:template>
</xsl:stylesheet>