<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ns="http://www.intertek.com/ESB/BusinessStream" xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs xsi xsl" xmlns="http://www.intertek.com/ESB/BusinessStream">
	<xsl:include href="../command_templates.xsl" />
	<xsl:include href="../response/responses.xsl" />
	<xsl:template match="/BusinessStreamX">
		<ns:PublishBusinessStreamRequest>
			<ns:MessageData>
				<ns:ProductGroupId><xsl:value-of select="productGroupId"/></ns:ProductGroupId>
				<ns:ProductGroupSet><xsl:value-of select="productGroupSet"/></ns:ProductGroupSet>
				<ns:Description><xsl:value-of select="description"/></ns:Description>
				<ns:BeginDate>
					<xsl:call-template name="getNewDateFormat">
	                	<xsl:with-param name="dateInput"><xsl:value-of select="beginDate" /></xsl:with-param>
					</xsl:call-template>
				</ns:BeginDate>
				<ns:EndDate>                
					<xsl:call-template name="getNewDateFormat">
		                <xsl:with-param name="dateInput"><xsl:value-of select="endDate" /></xsl:with-param>
					</xsl:call-template>                
				</ns:EndDate>
			</ns:MessageData>
		</ns:PublishBusinessStreamRequest>
	</xsl:template>
</xsl:stylesheet>
