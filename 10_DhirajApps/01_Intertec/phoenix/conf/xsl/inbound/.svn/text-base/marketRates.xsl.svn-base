<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:mar="http://www.intertek.com/ESB/MarketRates">
	<xsl:include href="../response/responses.xsl" />
	<xsl:template match="mar:PublishMarketRatesRequest">
		<List>
			<xsl:for-each select="mar:MessageData/mar:Transaction">
				<MarketRateX>
					<rateIndex><xsl:value-of select="mar:RateIndex"/></rateIndex>
					<term><xsl:value-of select="mar:Term"/></term>
					<fromCurrencyCode><xsl:value-of select="mar:FromCurrencyCode"/></fromCurrencyCode>
					<toCurrencyCode><xsl:value-of select="mar:ToCurrencyCode"/></toCurrencyCode>
					<rateType><xsl:value-of select="mar:RateType"/></rateType>
					<effectiveDate><xsl:value-of select="mar:EffectiveDate"/></effectiveDate>
					<rateMultiplier><xsl:value-of select="mar:RateMultiplier"/></rateMultiplier>
					<rateDivisor><xsl:value-of select="mar:RateDivisor"/></rateDivisor>
					<synchronizationID><xsl:value-of select="mar:SynchronizationID"/></synchronizationID>
					<lastUpdateTime><xsl:value-of select="mar:LastUpdateTime"/></lastUpdateTime>
				</MarketRateX>
			</xsl:for-each>
		</List>
	</xsl:template>
</xsl:stylesheet>