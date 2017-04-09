<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tes="http://www.intertek.com/ESB/TestMethodology" xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs xsi xsl" xmlns="http://www.intertek.com/ESB/TestMethodology">
	<xsl:include href="../command_templates.xsl" />
	<xsl:include href="../response/responses.xsl" />
	<xsl:template match="/TestMethodologyX">
      <tes:PublishTestMethodologyRequest>
         <tes:MessageData>
            <tes:Id><xsl:value-of select="id"/></tes:Id>
            <tes:Description><xsl:value-of select="description"/></tes:Description>
            <xsl:for-each select="serviceOffering">
	            <tes:ServiceOffering>
	               <tes:Id><xsl:value-of select="id"/></tes:Id>
	               <tes:Description><xsl:value-of select="description"/></tes:Description>
	               <tes:ParentId><xsl:value-of select="parentId"/></tes:ParentId>
	            </tes:ServiceOffering>
            </xsl:for-each>
            <tes:Price><xsl:value-of select="price"/></tes:Price>
            <tes:PriceId><xsl:value-of select="priceId"/></tes:PriceId>
            <xsl:if test="beginDate">
	            <tes:BeginDate>
					<xsl:call-template name="getNewDateFormat">
		                	<xsl:with-param name="dateInput"><xsl:value-of select="beginDate" /></xsl:with-param>
					</xsl:call-template>
				</tes:BeginDate>
			</xsl:if>
            <xsl:if test="endDate">
	            <tes:EndDate>
					<xsl:call-template name="getNewDateFormat">
		                	<xsl:with-param name="dateInput"><xsl:value-of select="endDate" /></xsl:with-param>
					</xsl:call-template>
				</tes:EndDate>
			</xsl:if>
         </tes:MessageData>
      </tes:PublishTestMethodologyRequest>
	</xsl:template>
</xsl:stylesheet>
