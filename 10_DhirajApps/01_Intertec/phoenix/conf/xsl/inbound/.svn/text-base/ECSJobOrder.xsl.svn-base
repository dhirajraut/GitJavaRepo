<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ecs="http://www.intertek.com/ESB/ECSJobOrderRequest">
	<xsl:include href="../command_templates.xsl" />	
	<xsl:include href="../response/responses.xsl" />
	<xsl:template match="ecs:GetJobOrderRequest">
		<List>
			<xsl:for-each select="ecs:MessageData">
				<ECSJobOrderX>
					<followUpServices><xsl:value-of select="ecs:FollowUpServices"/></followUpServices>
		            <quoteNumber><xsl:value-of select="ecs:QuoteNumber"/></quoteNumber>
		            <quoteDate><xsl:value-of select="ecs:QuoteDate"/></quoteDate>
		            <orderNumber><xsl:value-of select="ecs:OrderNumber"/></orderNumber>
		            <orderDate><xsl:value-of select="ecs:OrderDate"/></orderDate>
		            <customerId><xsl:value-of select="ecs:CustomerId"/></customerId>
		            <contactId><xsl:value-of select="ecs:ContactId"/></contactId>
		            <contractId><xsl:value-of select="ecs:ContractId"/></contractId>
		            <orderAmount><xsl:value-of select="ecs:OrderAmount"/></orderAmount>
		            <agreementPONumber><xsl:value-of select="ecs:AgreementPONumber"/></agreementPONumber>
		            <currencyCode><xsl:value-of select="ecs:CurrencyCode"/></currencyCode>
		            <modelNumber><xsl:value-of select="ecs:ModelNumber"/></modelNumber>
		            <productionEvaluationDescription><xsl:value-of select="ecs:ProductionEvaluationDescription"/></productionEvaluationDescription>
		            <billToCustomerId><xsl:value-of select="ecs:BillToCustomerId"/></billToCustomerId>
		            <billToContactId><xsl:value-of select="ecs:BillToContactId"/></billToContactId>
		            <shipToCustomerId><xsl:value-of select="ecs:ShipToCustomerId"/></shipToCustomerId>
		            <shipToContactId><xsl:value-of select="ecs:ShipToContactId"/></shipToContactId>
		            <businessStream>
		               <productGroupId><xsl:value-of select="ecs:BusinessStream/ecs:ProductGroupId"/></productGroupId>
		               <productGroupSet><xsl:value-of select="ecs:BusinessStream/ecs:ProductGroupSet"/></productGroupSet>
		               <description><xsl:value-of select="ecs:BusinessStream/ecs:Description"/></description>		              
		               <beginDate>
		               <xsl:call-template name="convertDateToDateTime">
			       		<xsl:with-param name="dateInput"><xsl:value-of select="ecs:BusinessStream/ecs:BeginDate" /></xsl:with-param>
			        </xsl:call-template>		              
		               </beginDate>		    
		              <endDate> 		              
		                <xsl:call-template name="convertDateToDateTime">
					<xsl:with-param name="dateInput"><xsl:value-of select="ecs:BusinessStream/ecs:EndDate" /></xsl:with-param>
			        </xsl:call-template>		              
		              </endDate>
		            </businessStream>
		            <warehouse>
		               <id><xsl:value-of select="ecs:Warehouse/ecs:Id"/></id>
		               <businessUnit><xsl:value-of select="ecs:Warehouse/ecs:BusinessUnit"/></businessUnit>
		               <address>
		                  <address1><xsl:value-of select="ecs:Warehouse/ecs:Address/ecs:Address1"/></address1>
		                  <address2><xsl:value-of select="ecs:Warehouse/ecs:Address/ecs:Address2"/></address2>
		                  <address3><xsl:value-of select="ecs:Warehouse/ecs:Address/ecs:Address3"/></address3>
		                  <address4><xsl:value-of select="ecs:Warehouse/ecs:Address/ecs:Address4"/></address4>
		                  <city><xsl:value-of select="ecs:Warehouse/ecs:Address/ecs:City"/></city>
		                  <postal><xsl:value-of select="ecs:Warehouse/ecs:Address/ecs:Postal"/></postal>
		                  <state><xsl:value-of select="ecs:Warehouse/ecs:Address/ecs:State"/></state>
		                  <country><xsl:value-of select="ecs:Warehouse/ecs:Address/ecs:Country"/></country>
		                  <county><xsl:value-of select="ecs:Warehouse/ecs:Address/ecs:County"/></county>
		                  <inCityLimit><xsl:value-of select="ecs:Warehouse/ecs:Address/ecs:InCityLimit"/></inCityLimit>
		               </address>
		               <contactPhone><xsl:value-of select="ecs:Warehouse/ecs:ContactPhone"/></contactPhone>
		               <faxNumber><xsl:value-of select="ecs:Warehouse/ecs:FaxNumber"/></faxNumber>
		               <status><xsl:value-of select="ecs:Warehouse/ecs:Status"/></status>
		               
		            </warehouse>
		            <primarySalePersonId><xsl:value-of select="ecs:PrimarySalePersonId"/></primarySalePersonId>
		            <secondarySalePersonId><xsl:value-of select="ecs:SecondarySalePersonId"/></secondarySalePersonId>
		            <projectManagerId><xsl:value-of select="ecs:ProjectManagerId"/></projectManagerId>
		            <customerReadyDate><xsl:value-of select="ecs:CustomerReadyDate"/></customerReadyDate>
		            <promiseCompletionDate><xsl:value-of select="ecs:PromiseCompletionDate"/></promiseCompletionDate>
					<xsl:for-each select="ecs:LineItem">
		            	<lineItems>
				            <lineItemNumber><xsl:value-of select="ecs:LineItemNumber"/></lineItemNumber>
				            <itemStandardId><xsl:value-of select="ecs:ItemStandardId"/></itemStandardId>
				            <itemStandardDescription><xsl:value-of select="ecs:ItemStandardDescription"/></itemStandardDescription>
				            <quotedValue><xsl:value-of select="ecs:QuotedValue"/></quotedValue>
				            <taskOwnerId><xsl:value-of select="ecs:TaskOwnerId"/></taskOwnerId>
				            <serviceOffering>
				               <id><xsl:value-of select="ecs:ServiceOffering/ecs:Id"/></id>
				               <parentId><xsl:value-of select="ecs:ServiceOffering/ecs:ParentId"/></parentId>
				               <description><xsl:value-of select="ecs:ServiceOffering/ecs:Description"/></description>
				            </serviceOffering>
				           
				           <startDate> 
				            <xsl:call-template name="convertDateToDateTime">
					   	 <xsl:with-param name="dateInput"><xsl:value-of select="ecs:StartDate" /></xsl:with-param>
			                    </xsl:call-template>
				           </startDate>				             
				            <completionDate>
				            <xsl:call-template name="convertDateToDateTime">
					    	 <xsl:with-param name="dateInput"><xsl:value-of select="ecs:CompletionDate" /></xsl:with-param>
			                    </xsl:call-template>				         			            
				            </completionDate>
				            <jobTitle><xsl:value-of select="ecs:JobTitle"/></jobTitle>
				            <billableHours><xsl:value-of select="ecs:BillableHours"/></billableHours>
						</lineItems>
					</xsl:for-each>					 
				</ECSJobOrderX>
			</xsl:for-each>
			
		</List>
	</xsl:template>
</xsl:stylesheet>
