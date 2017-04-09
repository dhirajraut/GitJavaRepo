<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:dcb="http://www.intertek.com/ESB/DCBillableItemRequest">
	<xsl:include href="../response/responses.xsl" />
	<xsl:template match="dcb:GetBillableItemRequest">
		<List>
			<xsl:for-each select="dcb:MessageData">
	            <DCAppBillableItemX>
		            <projectNumber><xsl:value-of select="dcb:ProjectNumber"/></projectNumber>
		            <orderNumber><xsl:value-of select="dcb:OrderNumber"/></orderNumber>
		            <lineItems>
			            <xsl:for-each select="dcb:LineItem">
				            <DCAppBillableLineItemX>
				               <lineItemNumber><xsl:value-of select="dcb:LineItemNumber"/></lineItemNumber>
				               <lineItemDescription><xsl:value-of select="dcb:LineItemDescription"/></lineItemDescription>
				               <billableItemQuantity><xsl:value-of select="dcb:BillableItemQuantity"/></billableItemQuantity>
				               <invoiceAmount><xsl:value-of select="dcb:InvoiceAmount"/></invoiceAmount>
				            </DCAppBillableLineItemX>
			            </xsl:for-each>
		            </lineItems>
	            </DCAppBillableItemX>
			</xsl:for-each>
		</List>
	</xsl:template>
</xsl:stylesheet>