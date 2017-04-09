<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/" 
		xmlns:mar="http://www.intertek.com/ESB/MarketRates"
		xmlns:cred="http://www.intertek.com/ESB/CreditHold"
		xmlns:proj="http://www.intertek.com/ESB/ProjectValidationRequest"
		xmlns:psb="http://www.intertek.com/ESB/PSBillableItem"
	>
		<xsl:for-each select="mar:PublishMarketRatesRequest/mar:MessageData/mar:Transaction">
			<MarketRates>
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
			</MarketRates>
		</xsl:for-each>

		<xsl:for-each select="cred:PublishCreditHoldRequest/cred:MessageData/cred:Transaction">
            <CreditHold>
               <customerId><xsl:value-of select="cred:CustomerId"/></customerId>
               <customerName><xsl:value-of select="cred:CustomerName"/></customerName>
               <creditHoldFlag><xsl:value-of select="cred:CreditHoldFlag"/></creditHoldFlag>
               <dateTime><xsl:value-of select="cred:DateTime"/></dateTime>
               <operatorId><xsl:value-of select="cred:OperatorId"/></operatorId>
            </CreditHold>
		</xsl:for-each>

		<xsl:for-each select="proj:GetProjectValidationRequest/proj:MessageData">
            <ProjectValidation>
               <projectNumber><xsl:value-of select="proj:ProjectNumber"/></projectNumber>
               <customerId><xsl:value-of select="proj:CustomerId"/></customerId>
            </ProjectValidation>
		</xsl:for-each>
	
		<xsl:for-each select="psb:PublishBillableItemRequest/psb:MessageData/psb:Transaction">
            <ProjectValidation>
               <projectNumber><xsl:value-of select="psb:ProjectNumber"/></projectNumber>
               <customerId><xsl:value-of select="psb:CustomerId"/></customerId>
               <PSBillableItem>
                  <businessUnit><xsl:value-of select="psb:BusinessUnit"/></businessUnit>
                  <temporaryInvoiceNumber><xsl:value-of select="psb:TemporaryInvoiceNumber"/></temporaryInvoiceNumber>
                  <lineSequenceNumber><xsl:value-of select="psb:LineSequenceNumber"/></lineSequenceNumber>
                  <lineDescription><xsl:value-of select="psb:LineDescription"/></lineDescription>
                  <jobNumber><xsl:value-of select="psb:JobNumber"/></jobNumber>
                  <jobLineNumber><xsl:value-of select="psb:JobLineNumber"/></jobLineNumber>
                  <invoiceAmt><xsl:value-of select="psb:InvoiceAmt"/></invoiceAmt>
                  <quantity><xsl:value-of select="psb:Quantity"/></quantity>
                  <unitAmt><xsl:value-of select="psb:UnitAmt"/></unitAmt>
                  <totalLineAmt><xsl:value-of select="psb:TotalLineAmt"/></totalLineAmt>
                  <billingCurrencyCd><xsl:value-of select="psb:BillingCurrencyCd"/></billingCurrencyCd>
                  <xsl:for-each select="psb:BillingLineDistribution">
	                  <BillingLineDistribution>
	                     <businessUnit><xsl:value-of select="psb:BusinessUnit"/></businessUnit>
	                     <invoiceNumber><xsl:value-of select="psb:InvoiceNumber"/></invoiceNumber>
	                     <lineSeqNumber><xsl:value-of select="psb:LineSeqNumber"/></lineSeqNumber>
	                     <lineDistSeqNumber><xsl:value-of select="psb:LineDistSeqNumber"/></lineDistSeqNumber>
	                     <jobNumber><xsl:value-of select="psb:JobNumber"/></jobNumber>
	                     <account><xsl:value-of select="psb:Account"/></account>
	                     <warehouseId><xsl:value-of select="psb:WarehouseId"/></warehouseId>
	                     <departmentId><xsl:value-of select="psb:DepartmentId"/></departmentId>
	                     <businessStream><xsl:value-of select="psb:BusinessStream"/></businessStream>
	                     <serviceOffering><xsl:value-of select="psb:ServiceOffering"/></serviceOffering>
	                     <distributionAmt><xsl:value-of select="psb:DistributionAmt"/></distributionAmt>
	                  </BillingLineDistribution>
                  </xsl:for-each>
               </PSBillableItem>               
            </ProjectValidation>
		</xsl:for-each>
	</xsl:template>
	
</xsl:stylesheet>