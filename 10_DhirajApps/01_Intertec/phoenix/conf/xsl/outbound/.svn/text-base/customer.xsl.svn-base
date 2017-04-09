<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:cus="http://www.intertek.com/ESB/Customer" xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs xsi xsl" xmlns="http://www.intertek.com/ESB/Customer">
	<xsl:include href="../command_templates.xsl" />	
	<xsl:include href="../response/responses.xsl" />
	<xsl:template match="/CustomerX">
      <cus:PublishCustomerRequest>
         <cus:MessageData>
            <cus:CustomerCode><xsl:value-of select="customerCode"/></cus:CustomerCode>
            <cus:CustomerName><xsl:value-of select="customerName"/></cus:CustomerName>
            <cus:CustomerStatus><xsl:value-of select="customerStatus"/></cus:CustomerStatus>
            <xsl:if test="statusDate">
	            <cus:StatusDate>
					<xsl:call-template name="getNewDateFormat">
		                	<xsl:with-param name="dateInput"><xsl:value-of select="statusDate" /></xsl:with-param>
					</xsl:call-template>
				</cus:StatusDate>
			</xsl:if>
            <cus:AlternateName><xsl:value-of select="alternateName"/></cus:AlternateName>
            <xsl:if test="sinceDate">
	            <cus:SinceDate>
					<xsl:call-template name="getNewDateFormat">
		                	<xsl:with-param name="dateInput"><xsl:value-of select="sinceDate" /></xsl:with-param>
					</xsl:call-template>
				</cus:SinceDate>
			</xsl:if>
			<xsl:if test="addDate">
	            <cus:AddDate>
					<xsl:call-template name="getNewDateFormat">
		                	<xsl:with-param name="dateInput"><xsl:value-of select="addDate" /></xsl:with-param>
					</xsl:call-template>
	            </cus:AddDate>
            </xsl:if>
            <cus:Industry><xsl:value-of select="industry"/></cus:Industry>
            <cus:CurrencyCd><xsl:value-of select="currencyCd"/></cus:CurrencyCd>
            <cus:CustomerType><xsl:value-of select="customerType"/></cus:CustomerType>
            <cus:LocationType><xsl:value-of select="locationType"/></cus:LocationType>
            <cus:ParentCustomerCode><xsl:value-of select="parentCustomerCode"/></cus:ParentCustomerCode>
            <cus:CreditAnalystName><xsl:value-of select="creditAnalystName"/></cus:CreditAnalystName>
            <cus:Collector><xsl:value-of select="collector"/></cus:Collector>
            <cus:CreditApplication><xsl:value-of select="creditApplication"/></cus:CreditApplication>
            <cus:CreditHold><xsl:value-of select="creditHold"/></cus:CreditHold>
            <cus:CreditLimit><xsl:value-of select="creditLimit"/></cus:CreditLimit>
            <cus:InterUnit><xsl:value-of select="interUnit"/></cus:InterUnit>
            <cus:InterUnitBusinessUnitName><xsl:value-of select="interUnitBusinessUnitName"/></cus:InterUnitBusinessUnitName>
            <cus:InterUnitBusinessUnit><xsl:value-of select="interUnitBusinessUnit"/></cus:InterUnitBusinessUnit>
            <cus:InterUnitPending><xsl:value-of select="interUnitPending"/></cus:InterUnitPending>
            <cus:AccountOwner><xsl:value-of select="accountOwner"/></cus:AccountOwner>
            <cus:PaymentType><xsl:value-of select="paymentType"/></cus:PaymentType>
            <cus:PaymentTerms><xsl:value-of select="paymentTerms"/></cus:PaymentTerms>
            <cus:PrimaryIndustry><xsl:value-of select="primaryIndustry"/></cus:PrimaryIndustry>
            <cus:Contact><xsl:value-of select="contact"/></cus:Contact>
            <cus:Vip><xsl:value-of select="vip"/></cus:Vip>
            <cus:CustomerStatusComment><xsl:value-of select="customerStatusComment"/></cus:CustomerStatusComment>
            <cus:DunsNumber><xsl:value-of select="dunsNumber"/></cus:DunsNumber>
            <cus:InternalOriginDivision><xsl:value-of select="internalOriginDivision"/></cus:InternalOriginDivision>
            <cus:InternalOriginBusStrm><xsl:value-of select="internalOriginBusStrm"/></cus:InternalOriginBusStrm>
            <cus:InternalOrigin><xsl:value-of select="internalOrigin"/></cus:InternalOrigin>
            <cus:InvoiceType><xsl:value-of select="invoiceType"/></cus:InvoiceType>
            <xsl:if test="lastServiceDate">
	            <cus:LastServiceDate>
					<xsl:call-template name="getNewDateFormat">
		                	<xsl:with-param name="dateInput"><xsl:value-of select="lastServiceDate" /></xsl:with-param>
					</xsl:call-template>
				</cus:LastServiceDate>
			</xsl:if>
            <cus:LastServiceLocation><xsl:value-of select="lastServiceLocation"/></cus:LastServiceLocation>
            <cus:LegalEntitySubsidiary><xsl:value-of select="legalEntitySubsidiary"/></cus:LegalEntitySubsidiary>
            <cus:Referral><xsl:value-of select="referral"/></cus:Referral>
            <cus:ReferralParentCust><xsl:value-of select="referralParentCust"/></cus:ReferralParentCust>
            <cus:ReferralChildCust><xsl:value-of select="referralChildCust"/></cus:ReferralChildCust>
            <cus:ReferralNotes><xsl:value-of select="referralNotes"/></cus:ReferralNotes>
            <cus:SecondaryIndustry><xsl:value-of select="secondaryIndustry"/></cus:SecondaryIndustry>
            <cus:TaxpayerIdNumber><xsl:value-of select="taxpayerIdNumber"/></cus:TaxpayerIdNumber>
            <cus:CustType><xsl:value-of select="custType"/></cus:CustType>
            <xsl:for-each select="customerAddressSequence">
            <cus:CustomerAddressSequence>
               <cus:CustomerCode><xsl:value-of select="customerCode"/></cus:CustomerCode>
               <cus:AddressSequenceNumber><xsl:value-of select="addressSequenceNumber"/></cus:AddressSequenceNumber>
               <cus:AddressDescription><xsl:value-of select="addressDescription"/></cus:AddressDescription>
               <cus:CorrespondenceAddress><xsl:value-of select="correspondenceAddress"/></cus:CorrespondenceAddress>
               <cus:Location><xsl:value-of select="location"/></cus:Location>
               <cus:ShipTo><xsl:value-of select="shipTo"/></cus:ShipTo>
               <cus:BillTo><xsl:value-of select="billTo"/></cus:BillTo>
               <cus:SoldTo><xsl:value-of select="soldTo"/></cus:SoldTo>
               <cus:BillToPrimary><xsl:value-of select="billToPrimary"/></cus:BillToPrimary>
               <cus:SoldToPrimary><xsl:value-of select="soldToPrimary"/></cus:SoldToPrimary>
               <xsl:for-each select="customerAddresses">
	               <cus:CustomerAddress>
	                  <cus:Id><xsl:value-of select="id"/></cus:Id>
	                  <cus:CustomerCode><xsl:value-of select="customerCode"/></cus:CustomerCode>
	                  <cus:LocationNumber><xsl:value-of select="locationNumber"/></cus:LocationNumber>
	                  <cus:Status><xsl:value-of select="status"/></cus:Status>
					  <xsl:if test="effectiveDate">
							<cus:EffectiveDate>
								<xsl:call-template name="getNewDateFormat">
									<xsl:with-param name="dateInput"><xsl:value-of select="effectiveDate" /></xsl:with-param>
								</xsl:call-template>
							</cus:EffectiveDate>
					  </xsl:if>	                  
	                  <cus:TaxCode><xsl:value-of select="taxCode"/></cus:TaxCode>
	                  <cus:Address>
	                     <cus:Address1><xsl:value-of select="address/address1"/></cus:Address1>
	                     <cus:Address2><xsl:value-of select="address/address2"/></cus:Address2>
	                     <cus:Address3><xsl:value-of select="address/address3"/></cus:Address3>
	                     <cus:Address4><xsl:value-of select="address/address4"/></cus:Address4>
	                     <cus:City><xsl:value-of select="address/city"/></cus:City>
	                     <cus:Postal><xsl:value-of select="address/postal"/></cus:Postal>
	                     <cus:State><xsl:value-of select="address/state"/></cus:State>
	                     <cus:Country><xsl:value-of select="address/country"/></cus:Country>
	                     <cus:County><xsl:value-of select="address/county"/></cus:County>
	                     <cus:InCityLimit><xsl:value-of select="address/inCityLimit"/></cus:InCityLimit>
	                  </cus:Address>
	               </cus:CustomerAddress>
               </xsl:for-each>
            </cus:CustomerAddressSequence>
            </xsl:for-each>
         </cus:MessageData>
      </cus:PublishCustomerRequest>
	</xsl:template>
</xsl:stylesheet>
