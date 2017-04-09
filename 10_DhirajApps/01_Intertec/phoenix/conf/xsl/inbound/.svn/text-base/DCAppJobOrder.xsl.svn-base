<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:dcj="http://www.intertek.com/ESB/DCJobOrderRequest">
	<xsl:include href="../command_templates.xsl" />	
	<xsl:include href="../response/responses.xsl" />
	<xsl:template match="dcj:GetJobOrderRequest">
		<List>
			<xsl:for-each select="dcj:MessageData">
				<DCAppJobOrderX>
		            <projectNumber><xsl:value-of select="dcj:ProjectNumber"/></projectNumber>
		            <projectDate><xsl:value-of select="dcj:ProjectDate"/></projectDate>
		            <customerId><xsl:value-of select="dcj:CustomerId"/></customerId>
		            <contactId><xsl:value-of select="dcj:ContactId"/></contactId>
		            <contractId><xsl:value-of select="dcj:ContractId"/></contractId>
		            <orderAmount><xsl:value-of select="dcj:OrderAmount"/></orderAmount>
		            <currencyCode><xsl:value-of select="dcj:CurrencyCode"/></currencyCode>
		            <paymentTerms><xsl:value-of select="dcj:PaymentTerms"/></paymentTerms>
		            <modelNumber><xsl:value-of select="dcj:ModelNumber"/></modelNumber>
		            <productionEvaluationDescription><xsl:value-of select="dcj:ProductionEvaluationDescription"/></productionEvaluationDescription>
		            <billToCustomerId><xsl:value-of select="dcj:BillToCustomerId"/></billToCustomerId>
		            <billToContactId><xsl:value-of select="dcj:BillToContactId"/></billToContactId>
		            <shipToCustomerId><xsl:value-of select="dcj:ShipToCustomerId"/></shipToCustomerId>
		            <shipToContactId><xsl:value-of select="dcj:ShipToContactId"/></shipToContactId>
		            <xsl:if test="dcj:BusinessStream">
		            <businessStream>
		               <productGroupId><xsl:value-of select="dcj:BusinessStream/dcj:ProductGroupId"/></productGroupId>
		               <productGroupSet><xsl:value-of select="dcj:BusinessStream/dcj:ProductGroupSet"/></productGroupSet>
		               <description><xsl:value-of select="dcj:BusinessStream/dcj:Description"/></description>
		               <beginDate>
		                 <xsl:call-template name="convertDateToDateTime">
			     		   <xsl:with-param name="dateInput"><xsl:value-of select="dcj:BusinessStream/dcj:BeginDate" /></xsl:with-param>
			       </xsl:call-template>
			       </beginDate>
			       <endDate>
			       <xsl:call-template name="convertDateToDateTime">
			       	          <xsl:with-param name="dateInput"><xsl:value-of select="dcj:BusinessStream/dcj:EndDate" /></xsl:with-param>
			       </xsl:call-template>
                               </endDate>		             		            
		            </businessStream>
		            </xsl:if>
		             <xsl:if test="dcj:Warehouse">
			    <warehouse>
			       <id><xsl:value-of select="dcj:Warehouse/dcj:Id"/></id>
			       <businessUnit><xsl:value-of select="dcj:Warehouse/dcj:BusinessUnit"/></businessUnit>
			       <address>
				  <address1><xsl:value-of select="dcj:Warehouse/dcj:Address/dcj:Address1"/></address1>
				  <address2><xsl:value-of select="dcj:Warehouse/dcj:Address/dcj:Address2"/></address2>
				  <address3><xsl:value-of select="dcj:Warehouse/dcj:Address/dcj:Address3"/></address3>
				  <address4><xsl:value-of select="dcj:Warehouse/dcj:Address/dcj:Address4"/></address4>
				  <city><xsl:value-of select="dcj:Warehouse/dcj:Address/dcj:City"/></city>
				  <postal><xsl:value-of select="dcj:Warehouse/dcj:Address/dcj:Postal"/></postal>
				  <state><xsl:value-of select="dcj:Warehouse/dcj:Address/dcj:State"/></state>
				  <country><xsl:value-of select="dcj:Warehouse/dcj:Address/dcj:Country"/></country>
				  <county><xsl:value-of select="dcj:Warehouse/dcj:Address/dcj:County"/></county>
				  <inCityLimit><xsl:value-of select="dcj:Warehouse/dcj:Address/dcj:InCityLimit"/></inCityLimit>
			       </address>
			       <contactPhone><xsl:value-of select="dcj:Warehouse/dcj:ContactPhone"/></contactPhone>
			       <faxNumber><xsl:value-of select="dcj:Warehouse/dcj:FaxNumber"/></faxNumber>
			       <status><xsl:value-of select="dcj:Warehouse/dcj:Status"/></status>
    			   </warehouse>
    			    </xsl:if>
		            <primarySalePersonId><xsl:value-of select="dcj:PrimarySalePersonId"/></primarySalePersonId>
		            <secondarySalePersonId><xsl:value-of select="dcj:SecondarySalePersonId"/></secondarySalePersonId>
		            <projectManagerId><xsl:value-of select="dcj:ProjectManagerId"/></projectManagerId>
		            <customerReadyDate><xsl:value-of select="dcj:CustomerReadyDate"/></customerReadyDate>
		            <promiseCompletionDate><xsl:value-of select="dcj:PromiseCompletionDate"/></promiseCompletionDate>					
				<xsl:for-each select="dcj:LineItem">
		            	<lineItems>
				            <lineItemNumber><xsl:value-of select="dcj:LineItemNumber"/></lineItemNumber>
				            <itemStandardId><xsl:value-of select="dcj:ItemStandardId"/></itemStandardId>
				            <itemStandardDescription><xsl:value-of select="dcj:ItemStandardDescription"/></itemStandardDescription>
				            <quotedValue><xsl:value-of select="dcj:QuotedValue"/></quotedValue>
				            <taskOwnerId><xsl:value-of select="dcj:TaskOwnerId"/></taskOwnerId>
				            <xsl:if test="dcj:ServiceOffering">
				            <serviceOffering>
				               <id><xsl:value-of select="dcj:ServiceOffering/dcj:Id"/></id>
				               <parentId><xsl:value-of select="dcj:ServiceOffering/dcj:ParentId"/></parentId>
				               <description><xsl:value-of select="dcj:ServiceOffering/dcj:Description"/></description>
				            </serviceOffering>	
				            </xsl:if>
				            <startDate>
				            <xsl:call-template name="convertDateToDateTime">
					    	<xsl:with-param name="dateInput"><xsl:value-of select="dcj:StartDate" /></xsl:with-param>
			                    </xsl:call-template>
			                    </startDate>
			                    <completionDate>
				            <xsl:call-template name="convertDateToDateTime">
					   	 <xsl:with-param name="dateInput"><xsl:value-of select="dcj:CompletionDate" /></xsl:with-param>
			                    </xsl:call-template>
			                    </completionDate>
				            <jobTitle><xsl:value-of select="dcj:JobTitle"/></jobTitle>
				            <billableHours><xsl:value-of select="dcj:BillableHours"/></billableHours>
				             <operationalStatus><xsl:value-of select="dcj:OperationalStatus"/></operationalStatus>
						</lineItems>
				</xsl:for-each>															
		         </DCAppJobOrderX>
			</xsl:for-each>
		</List>
	</xsl:template>
</xsl:stylesheet>
         