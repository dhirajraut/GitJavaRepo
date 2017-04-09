<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:psb="http://www.intertek.com/ESB/PSBillableItem">
	<xsl:include href="../response/responses.xsl" />
	
	
	<xsl:template match="psb:PublishBillableItemRequest">
		<List>
			<xsl:for-each select="psb:MessageData/psb:Transaction/psb:BillableItemLine">
               <PSBillableItemX>
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
	                  <BillingLineDistributionX>
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
	                  </BillingLineDistributionX>
                  </xsl:for-each>
               </PSBillableItemX>
			</xsl:for-each>
		</List>
	</xsl:template>	
</xsl:stylesheet>