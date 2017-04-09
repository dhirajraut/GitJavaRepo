<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE cXML SYSTEM "http://xml.cxml.org/schemas/cXML/1.2.018/InvoiceDetail.dtd">
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:datetime="http://exslt.org/dates-and-times" exclude-result-prefixes="datetime" xmlns:exsl="http://exslt.org/common" extension-element-prefixes="exsl"  version="2.0">

  <xsl:template match="jobContractInvoice">
  <xsl:variable name="payloadid" select="concat(jobContract/jobNumber,'-',invoice,'@intertek')"/>
  <cXML>
 	<xsl:attribute name="payloadID"><xsl:value-of select="$payloadid"/></xsl:attribute>
 	<xsl:attribute name="timestamp"><xsl:value-of select="datetime:dateTime()"/></xsl:attribute>
  <Header>
  <From>
  	<Credential domain="NetworkId">
  		<Identity>AN01000647493-T</Identity>
  	</Credential>
  	<Credential domain="DUNS">
		<Identity>074159310-T</Identity>
	</Credential>
  </From>
  <To>
    <Credential domain="NetworkID">
        <Identity>AN01000682640-T</Identity>
    </Credential>
  </To>

  <Sender>
  <Credential domain="NetworkID">
    <Identity>AN01000647493-T</Identity>
    <SharedSecret>intertek2200</SharedSecret>
  </Credential>
  
  <UserAgent>Supplier</UserAgent>
  </Sender>
  </Header>
  <Request>
    <InvoiceDetailRequest>
    <InvoiceDetailRequestHeader>
        <xsl:attribute name="invoiceDate" >
        	<xsl:if test="contains(jobContract/invoiceDate,'.000')">
        		<xsl:value-of select="concat(substring-before(jobContract/invoiceDate,'.000'),substring-after(jobContract/invoiceDate,'.000'))" />
        	</xsl:if>
        </xsl:attribute>
        <xsl:attribute name="operation">new</xsl:attribute>
        <xsl:attribute name="purpose">
        	<xsl:choose>
    			<xsl:when test="jobContract/jobContractStatus='7200'">lineLevelCreditMemo</xsl:when>
    			<xsl:otherwise>standard</xsl:otherwise>
			</xsl:choose>
        </xsl:attribute>
        
        <xsl:attribute name="invoiceID">
          <xsl:value-of select="invoice" />
        </xsl:attribute>
        
        <InvoiceDetailHeaderIndicator>
          <!--  xsl:attribute name="isVatRecoverable" / -->
          <!-- xsl:attribute name="isHeaderInvoice"/> -->
        </InvoiceDetailHeaderIndicator>
        <InvoiceDetailLineIndicator>
          <xsl:attribute name="isDiscountInLine">yes</xsl:attribute>
          <!--  xsl:attribute name="isShippingInLine" /-->
          <xsl:attribute name="isAccountingInLine">yes</xsl:attribute>
          <!-- xsl:attribute name="isSpecialHandlingInLine" / -->
          <xsl:attribute name="isTaxInLine">yes</xsl:attribute>
        </InvoiceDetailLineIndicator>
        <InvoicePartner>
			<Contact>
				<xsl:attribute name="role">soldTo</xsl:attribute>
				<Name xml:lang="en-SE">ExxonMobil eEnablement-TEST</Name>
				<PostalAddress>
					<Street>601 Jefferson</Street>
					<City>Houston</City>
					<State>TX</State>
					<PostalCode>77002</PostalCode>
					<Country isoCountryCode="US">United States</Country>
				</PostalAddress>
				<Email>detti@exxon.com</Email>
			</Contact>
		</InvoicePartner>
		<InvoicePartner>
			<Contact>
				<xsl:attribute name="role">from</xsl:attribute>
				<Name xml:lang="en-SE">
          			Intertek USA Inc
        		</Name>
				<PostalAddress>
	              <xsl:attribute name="name" />
	              <Street>2200 West Loop South, Suite 200</Street>
	              <City>Houston</City>
	              <State>TX</State>
	              <PostalCode>77027</PostalCode>
	              <Country isoCountryCode="US">United States</Country>
           		 </PostalAddress>
			</Contact>
		</InvoicePartner>
        <InvoicePartner>
          <Contact>
           <!-- START: Remit To changes as per Sheida's email on 20 Aug 2009 -->
            <xsl:attribute name="addressID">6355574</xsl:attribute>
            <xsl:attribute name="role">remitTo</xsl:attribute>
            <Name xml:lang="en-SE">
          		Intertek USA Inc
        		</Name>
            <PostalAddress>
              <xsl:attribute name="name" />
              <DeliverTo />
              <Street>PO Box 32849</Street>
              <City>HARTFORD</City>
              <State>CT</State>
              <PostalCode>06150-2849</PostalCode>
              <Country isoCountryCode="US">USA</Country>
            </PostalAddress>
 		<!-- END: Remit To changes as per Sheida's email on 20 Aug 2009 -->   
            <!--  Email>
              <xsl:attribute name="name" />
              <xsl:attribute name="preferredLang" />
            </Email>
            <Phone>
              <xsl:attribute name="name" />
              <TelephoneNumber>
                <Extension />
                <Number />
                <AreaOrCityCode />
                <CountryCode>
                  <xsl:attribute name="isoCountryCode" />
                </CountryCode>
              </TelephoneNumber>
            </Phone>
            <Fax>
              <xsl:attribute name="name" />
              <Email>
                <xsl:attribute name="name" />
                <xsl:attribute name="preferredLang" />
              </Email>
              <TelephoneNumber>
                <Extension />
                <Number />
                <AreaOrCityCode />
                <CountryCode>
                  <xsl:attribute name="isoCountryCode" />
                </CountryCode>
              </TelephoneNumber>
            </Fax>
            <URL>
              <xsl:attribute name="name" />
            </URL-->
          </Contact>
          <!--  IdReference>
            <xsl:attribute name="identifier" />
            <xsl:attribute name="domain" />
            <Creator>
              <xsl:attribute name="xml:lang" />
            </Creator>
            <Description>
              <xsl:attribute name="xml:lang" />
              <ShortName />
            </Description>
          </IdReference -->
        </InvoicePartner>
        
        <!-- custaddress -->
	      <InvoicePartner>
	      <Contact>
	        <xsl:attribute name="addressID">0102</xsl:attribute>
	        <xsl:attribute name="role">billTo</xsl:attribute>
	        <Name>
	          <xsl:attribute name="xml:lang" />
	          <xsl:value-of select="billingContactName" />
	        </Name>
	        <PostalAddress>
	          <xsl:attribute name="name" />
	          <DeliverTo />
	          <Street>
     			 <xsl:value-of select="concat(custAddress1,custAddress2,custAddress3)" />
   			  </Street>
	          <City>
	            <xsl:value-of select="custCity" />
	          </City>
	          <State>
	            <xsl:value-of select="custState" />
	          </State>
	          <PostalCode>
	            <xsl:value-of select="custPostal" />
	          </PostalCode>
	          <Country>
	            <xsl:variable name="mycountry" select="custCountry"/>
	            <xsl:if test="$mycountry='USA'">
	            	<xsl:attribute name="isoCountryCode">US</xsl:attribute>
	            </xsl:if>
	            <xsl:value-of select="custCountry" />
	          </Country>
	        </PostalAddress>
	        <!--  Email>
	          <xsl:attribute name="name" />
	          <xsl:attribute name="preferredLang" />
	        </Email>
	        <Phone>
	          <xsl:attribute name="name" />
	          <TelephoneNumber>
	            <Extension />
	            <Number />
	            <AreaOrCityCode />
	            <CountryCode>
	              <xsl:attribute name="isoCountryCode" />
	            </CountryCode>
	          </TelephoneNumber>
	        </Phone>
	        <Fax>
	          <xsl:attribute name="name" />
	          <Email>
	            <xsl:attribute name="name" />
	            <xsl:attribute name="preferredLang" />
	          </Email>
	          <TelephoneNumber>
	            <Extension />
	            <Number />
	            <AreaOrCityCode />
	            <CountryCode>
	              <xsl:attribute name="isoCountryCode" />
	            </CountryCode>
	          </TelephoneNumber>
	          <URL>
	            <xsl:attribute name="name" />
	          </URL>
	        </Fax>
	        <URL>
	          <xsl:attribute name="name" />
	        </URL -->
	      </Contact>
	      <!-- IdReference>
	        <xsl:attribute name="identifier" />
	        <xsl:attribute name="domain" />
	        <Creator>
	          <xsl:attribute name="xml:lang" />
	        </Creator>
	        <Description>
	          <xsl:attribute name="xml:lang" />
	          <ShortName />
	        </Description>
	      </IdReference -->
	    </InvoicePartner>
	    <!-- end of cust address -->
    
        <xsl:if test="(jobContract/jobContractStatus != '6000') and (contains(invoice,'CR'))">
        <DocumentReference>
        	<xsl:attribute name="payloadID">
              <xsl:value-of select="concat(jobContract/jobNumber,'-',invoiceToAdjust,'@intertek')" />
          </xsl:attribute>
        </DocumentReference>
        </xsl:if>
        <!-- InvoiceIDInfo>
          <xsl:attribute name="invoiceDate" />
          <xsl:attribute name="invoiceID" />
        </InvoiceIDInfo>
        <InvoiceDetailShipping>
          <xsl:attribute name="shippingDate" />
          <ShipmentIdentifier />
          <CarrierIdentifier>
            <xsl:attribute name="domain" />
          </CarrierIdentifier>
          <Contact>
            <xsl:attribute name="addressID" />
            <xsl:attribute name="role" />
            <Name>
              <xsl:attribute name="xml:lang" />
            </Name>
            <Phone>
              <xsl:attribute name="name" />
              <TelephoneNumber>
                <Extension />
                <Number />
                <AreaOrCityCode />
                <CountryCode>
                  <xsl:attribute name="isoCountryCode" />
                </CountryCode>
              </TelephoneNumber>
            </Phone>
            <Email>
              <xsl:attribute name="name" />
              <xsl:attribute name="preferredLang" />
            </Email>
            <Fax>
              <xsl:attribute name="name" />
              <Email>
                <xsl:attribute name="name" />
                <xsl:attribute name="preferredLang" />
              </Email>
              <TelephoneNumber>
                <Extension />
                <Number />
                <AreaOrCityCode />
                <CountryCode>
                  <xsl:attribute name="isoCountryCode" />
                </CountryCode>
              </TelephoneNumber>
              <URL>
                <xsl:attribute name="name" />
              </URL>
            </Fax>
            <PostalAddress>
              <xsl:attribute name="name" />
              <Street />
              <PostalCode />
              <State />
              <DeliverTo />
              <Country>
                <xsl:attribute name="isoCountryCode" />
              </Country>
              <City />
            </PostalAddress>
            <URL>
              <xsl:attribute name="name" />
            </URL>
          </Contact>
          <DocumentReference>
            <xsl:attribute name="payloadID" />
          </DocumentReference>
        </InvoiceDetailShipping -->
        <PaymentTerm>
          <xsl:attribute name="payInNumberOfDays">
          	<xsl:if test="pymntTermsCd='DUE'">0</xsl:if>
          	<xsl:if test="contains(pymntTermsCd,'NET')">
          		<xsl:variable name="payduedays" select="pymntTermsCd"/>
          		<xsl:value-of select="number(substring-after($payduedays,'NET'))" />
          	</xsl:if>
          	<xsl:if test="pymntTermsCd='EOM45'">
          		<xsl:value-of select="45+30-(datetime:day-in-month(datetime:dateTime()))"/>
				<!-- xsl:variable name="ndays" select="45+(datetime:difference(datetime:dateTime(),datetime:add(datetime:dateTime(),'P1M')))"/ >
				<xsl:value-of select="$ndays"/ -->
          	</xsl:if>
          </xsl:attribute>
          <!--  Discount>
            <DiscountPercent>
              <xsl:attribute name="percent" />
            </DiscountPercent>
            <DiscountAmount>
              <Money>
                <xsl:attribute name="currency" />
                <xsl:attribute name="alternateAmount" />
                <xsl:attribute name="alternateCurrency" />
              </Money>
            </DiscountAmount>
          </Discount-->
        </PaymentTerm>
        <!-- InvoiceDetailPaymentTerm>
          <xsl:attribute name="percentageRate" />
          <xsl:attribute name="payInNumberOfDays" />
        </InvoiceDetailPaymentTerm -->
        <xsl:apply-templates select="jobOrder" />
        <Comments>          
          <!-- START: Patni Offshore changes to allow multiple files to be attached in Ariba Invoice -->
                      <!-- Attachment>
            <URL>cid:part2@intertek.com</URL>
          </Attachment -->
          <xsl:for-each select="/jobContractInvoice/JobContractAttaches">
          <Attachment>			 				
					<URL><xsl:value-of select="FileName" /></URL>
		  </Attachment>
		  </xsl:for-each>
		<!-- END: Patni Offshore changes to allow multiple files to be attached in Ariba Invoice -->          
        </Comments>
        <Extrinsic>
          <xsl:attribute name="name" />
        </Extrinsic>
        </InvoiceDetailRequestHeader>
    
      <InvoiceDetailOrder>
          <InvoiceDetailOrderInfo>
          <!-- OrderReference>
              <xsl:attribute name="orderDate" />
              <xsl:attribute name="orderID" >
              	<xsl:value-of select="custRefNum" />
              </xsl:attribute>
              <DocumentReference>
                <xsl:attribute name="payloadID" />
              </DocumentReference>
            </OrderReference -->
            <!--  MasterAgreementReference>
              <xsl:attribute name="agreementDate" />
              <xsl:attribute name="agreementID" />
              <DocumentReference>
                <xsl:attribute name="payloadID" />
              </DocumentReference>
            </MasterAgreementReference -->
            <MasterAgreementIDInfo>
              <xsl:attribute name="agreementDate" />
              <xsl:attribute name="agreementID"/>
            </MasterAgreementIDInfo>
            <OrderIDInfo>
              <xsl:attribute name="orderDate" />
              <xsl:attribute name="orderID" >
   				<xsl:value-of select="custRefNum" />
              	<xsl:apply-templates select="invoiceValue1" />
              </xsl:attribute>
            </OrderIDInfo>
            <SupplierOrderInfo>
              <xsl:attribute name="orderID" />
            </SupplierOrderInfo>
          </InvoiceDetailOrderInfo>
          
      <xsl:for-each select="invoiceLine">
          <InvoiceDetailServiceItem>
            <xsl:attribute name="inspectionDate" />
            <xsl:attribute name="quantity"><xsl:value-of select="splitPct div 100" /></xsl:attribute>
            <xsl:attribute name="referenceDate" />
            <xsl:attribute name="invoiceLineNumber">
            	<xsl:value-of select="lineNumber" />
            </xsl:attribute>
            <InvoiceDetailServiceItemReference>
              <xsl:attribute name="lineNumber">00010</xsl:attribute>
              <Classification>
                <xsl:attribute name="domain" />
                <xsl:value-of select="deptid" />
              </Classification>
              <ItemID>
                <SupplierPartID>
                	<xsl:value-of select="substring-before(lineDescription,'#')" />
                </SupplierPartID>
                <SupplierPartAuxiliaryID />
              </ItemID>
              <Description xml:lang="en">
                <xsl:value-of select="lineDescription"/>
                <ShortName />
              </Description>
            </InvoiceDetailServiceItemReference>
            <SubtotalAmount>
              <Money>
                <xsl:attribute name="currency"><xsl:value-of select="/jobContractInvoice/transactionCurrencyCd" /></xsl:attribute>
                <xsl:attribute name="alternateAmount" />
                <xsl:attribute name="alternateCurrency" />
                <xsl:value-of select="unitPrice * splitPct div 100" />
              </Money>
            </SubtotalAmount>
            <!-- Period>
              <xsl:attribute name="startDate" />
              <xsl:attribute name="endDate" />
            </Period -->
            <!-- UnitRate>
              <Money>
                <xsl:attribute name="currency" />
                <xsl:attribute name="alternateAmount" />
                <xsl:attribute name="alternateCurrency" />
              </Money>
              <UnitOfMeasure />
              <TermReference>
                <xsl:attribute name="termName" />
                <xsl:attribute name="term" />
              </TermReference>
            </UnitRate -->
            <UnitOfMeasure>C62</UnitOfMeasure>
             <UnitPrice>
              <Money>
                <xsl:attribute name="currency"><xsl:value-of select="/jobContractInvoice/transactionCurrencyCd" /></xsl:attribute>
                <xsl:attribute name="alternateAmount" />
                <xsl:attribute name="alternateCurrency" />
                <xsl:value-of select="unitPrice * splitPct div 100" />
              </Money>
            </UnitPrice>
            <Tax>
            <Money>
                <xsl:attribute name="currency"><xsl:value-of select="/jobContractInvoice/transactionCurrencyCd" /></xsl:attribute>
                <xsl:attribute name="alternateAmount" />
                <xsl:attribute name="alternateCurrency" />
                <xsl:value-of select="taxAmt+vatAmt" />
             </Money>
             <Description xml:lang="en">
                  <!-- ShortName /-->tax
            </Description>
            <TaxDetail>
                <xsl:attribute name="purpose">sales tax</xsl:attribute>
                <xsl:attribute name="paymentDate" />
                <xsl:attribute name="percentageRate" />
                <xsl:attribute name="taxPointDate" />
                <xsl:attribute name="category">sales</xsl:attribute>
                <!-- xsl:attribute name="isTriangularTransaction" /-->
                <!--  xsl:attribute name="isVatRecoverable" / -->
            <!--  TaxableAmount>
                  <Money>
                    <xsl:attribute name="currency" />
                    <xsl:attribute name="alternateAmount" />
                    <xsl:attribute name="alternateCurrency" />
                  </Money>
                </TaxableAmount-->
                <TaxAmount>
                  <Money>
                    <xsl:attribute name="currency"><xsl:value-of select="/jobContractInvoice/transactionCurrencyCd"/></xsl:attribute>
                    <xsl:attribute name="alternateAmount" />
                    <xsl:attribute name="alternateCurrency" />
                    <xsl:value-of select="taxAmt" />
                  </Money>
                </TaxAmount>
                <!--  TaxLocation><xsl:attribute name="xml:lang" /></TaxLocation -->
                <Description xml:lang="en">
                  <!-- ShortName /-->sales
                </Description>
                <TriangularTransactionLawReference>
                  <xsl:attribute name="xml:lang" />
                </TriangularTransactionLawReference>
              </TaxDetail>
              <TaxDetail>
                <xsl:attribute name="purpose">vat</xsl:attribute>
                <xsl:attribute name="paymentDate" />
                <xsl:attribute name="percentageRate" />
                <xsl:attribute name="taxPointDate" />
                <!--  xsl:attribute name="isTriangularTransaction" / -->
                <xsl:attribute name="isVatRecoverable">yes</xsl:attribute>
                <xsl:attribute name="category">vat</xsl:attribute>
                <!-- TaxableAmount>
                  <Money>
                    <xsl:attribute name="currency" />
                    <xsl:attribute name="alternateAmount" />
                    <xsl:attribute name="alternateCurrency" />
                  </Money>
                </TaxableAmount-->
                <TaxAmount>
                  <Money>
                    <xsl:attribute name="currency"><xsl:value-of select="/jobContractInvoice/transactionCurrencyCd" /></xsl:attribute>
                    <xsl:attribute name="alternateAmount" />
                    <xsl:attribute name="alternateCurrency" />
                    <xsl:value-of select="vatAmt" />
                  </Money>
                </TaxAmount>
                <!-- TaxLocation><xsl:attribute name="xml:lang" /></TaxLocation -->
                <Description xml:lang="en">
                  <!-- ShortName /-->tax
                </Description>
                <TriangularTransactionLawReference>
                  <xsl:attribute name="xml:lang" />
                </TriangularTransactionLawReference>
              </TaxDetail>
            </Tax>
            <GrossAmount>
              <Money>
                <xsl:attribute name="currency"><xsl:value-of select="/jobContractInvoice/transactionCurrencyCd" /></xsl:attribute>
                <xsl:attribute name="alternateAmount" />
                <xsl:attribute name="alternateCurrency" />
                <xsl:value-of select="unitPrice * splitPct div 100 + taxAmt + vatAmt" />
              </Money>
            </GrossAmount>
            <InvoiceDetailDiscount>
              <xsl:attribute name="percentageRate">
                <xsl:value-of select="discountPct" />
              </xsl:attribute>
              <Money>
                <xsl:attribute name="currency"><xsl:value-of select="/jobContractInvoice/transactionCurrencyCd" /></xsl:attribute>
                <xsl:attribute name="alternateAmount" />
                <xsl:attribute name="alternateCurrency" />
                <xsl:value-of select="((unitPrice * splitPct div 100 )+ taxAmt + vatAmt)* (discountPct div 100)" />
              </Money>
            </InvoiceDetailDiscount>
            <NetAmount>
              <Money>
                <xsl:attribute name="currency"><xsl:value-of select="/jobContractInvoice/transactionCurrencyCd" /></xsl:attribute>
                <xsl:attribute name="alternateAmount" />
                <xsl:attribute name="alternateCurrency" />
                <xsl:value-of select="netPrice" />
              </Money>
            </NetAmount>
            <!--  Distribution>
				<Accounting name="Cost Center">
					<AccountingSegment id="C200012 ">
						<Name xml:lang="en">Cost Center</Name>
						<Description xml:lang="en">Cost Center</Description>
					</AccountingSegment>
				</Accounting>
				<Charge>
					<Money>
						<xsl:attribute name="currency"><xsl:value-of select="/jobContractInvoice/transactionCurrencyCd" /></xsl:attribute>
						<xsl:value-of select="netPrice" />
					</Money>
				</Charge>
				</Distribution>
				<Distribution>
					<Accounting name="Work Order">
						<AccountingSegment id="33000A096500 ">
							<Name xml:lang="en">Work Order</Name>
							<Description xml:lang="en">Work Order</Description>
						</AccountingSegment>
					</Accounting>
				<Charge>
				<Money>
					<xsl:attribute name="currency"><xsl:value-of select="/jobContractInvoice/transactionCurrencyCd" /></xsl:attribute>
					<xsl:value-of select="netPrice" />
				</Money>
				</Charge>
			</Distribution -->
            <Comments xml:lang="en">
              <xsl:value-of select="lineDescription"/>
              <Attachment>
                <URL>
                  <xsl:attribute name="name" />
                </URL>
              </Attachment>
            </Comments>
            <!--  InvoiceLaborDetail>
              <WorkLocation>
                <Address>
                  <xsl:attribute name="addressID" />
                  <xsl:attribute name="isoCountryCode" />
                  <Name>
                    <xsl:attribute name="xml:lang" />
                  </Name>
                  <Phone>
                    <xsl:attribute name="name" />
                    <TelephoneNumber>
                      <Extension />
                      <Number />
                      <AreaOrCityCode />
                      <CountryCode>
                        <xsl:attribute name="isoCountryCode" />
                      </CountryCode>
                    </TelephoneNumber>
                  </Phone>
                  <Email>
                    <xsl:attribute name="name" />
                    <xsl:attribute name="preferredLang" />
                  </Email>
                  <Fax>
                    <xsl:attribute name="name" />
                    <Email>
                      <xsl:attribute name="name" />
                      <xsl:attribute name="preferredLang" />
                    </Email>
                    <TelephoneNumber>
                      <Extension />
                      <Number />
                      <AreaOrCityCode />
                      <CountryCode>
                        <xsl:attribute name="isoCountryCode" />
                      </CountryCode>
                    </TelephoneNumber>
                    <URL>
                      <xsl:attribute name="name" />
                    </URL>
                  </Fax>
                  <PostalAddress>
                    <xsl:attribute name="name" />
                    <Street />
                    <PostalCode />
                    <State />
                    <DeliverTo />
                    <Country>
                      <xsl:attribute name="isoCountryCode" />
                    </Country>
                    <City />
                  </PostalAddress>
                  <URL>
                    <xsl:attribute name="name" />
                  </URL>
                </Address>
              </WorkLocation>
              <InvoiceTimeCardDetail>
                <TimeCardIDInfo>
                  <xsl:attribute name="timeCardID" />
                </TimeCardIDInfo>
                <TimeCardReference>
                  <xsl:attribute name="timeCardID" />
                  <DocumentReference>
                    <xsl:attribute name="payloadID" />
                  </DocumentReference>
                </TimeCardReference>
              </InvoiceTimeCardDetail>
              <Contractor>
                <ContractorIdentifier>
                  <xsl:attribute name="domain" />
                </ContractorIdentifier>
                <Contact>
                  <xsl:attribute name="addressID" />
                  <xsl:attribute name="role" />
                  <Name>
                    <xsl:attribute name="xml:lang" />
                  </Name>
                  <Phone>
                    <xsl:attribute name="name" />
                    <TelephoneNumber>
                      <Extension />
                      <Number />
                      <AreaOrCityCode />
                      <CountryCode>
                        <xsl:attribute name="isoCountryCode" />
                      </CountryCode>
                    </TelephoneNumber>
                  </Phone>
                  <Email>
                    <xsl:attribute name="name" />
                    <xsl:attribute name="preferredLang" />
                  </Email>
                  <Fax>
                    <xsl:attribute name="name" />
                    <Email>
                      <xsl:attribute name="name" />
                      <xsl:attribute name="preferredLang" />
                    </Email>
                    <TelephoneNumber>
                      <Extension />
                      <Number />
                      <AreaOrCityCode />
                      <CountryCode>
                        <xsl:attribute name="isoCountryCode" />
                      </CountryCode>
                    </TelephoneNumber>
                    <URL>
                      <xsl:attribute name="name" />
                    </URL>
                  </Fax>
                  <PostalAddress>
                    <xsl:attribute name="name" />
                    <Street />
                    <PostalCode />
                    <State />
                    <DeliverTo />
                    <Country>
                      <xsl:attribute name="isoCountryCode" />
                    </Country>
                    <City />
                  </PostalAddress>
                  <URL>
                    <xsl:attribute name="name" />
                  </URL>
                </Contact>
              </Contractor>
              <Supervisor>
                <Contact>
                  <xsl:attribute name="addressID" />
                  <xsl:attribute name="role" />
                  <Name>
                    <xsl:attribute name="xml:lang" />
                  </Name>
                  <Phone>
                    <xsl:attribute name="name" />
                    <TelephoneNumber>
                      <Extension />
                      <Number />
                      <AreaOrCityCode />
                      <CountryCode>
                        <xsl:attribute name="isoCountryCode" />
                      </CountryCode>
                    </TelephoneNumber>
                  </Phone>
                  <Email>
                    <xsl:attribute name="name" />
                    <xsl:attribute name="preferredLang" />
                  </Email>
                  <Fax>
                    <xsl:attribute name="name" />
                    <Email>
                      <xsl:attribute name="name" />
                      <xsl:attribute name="preferredLang" />
                    </Email>
                    <TelephoneNumber>
                      <Extension />
                      <Number />
                      <AreaOrCityCode />
                      <CountryCode>
                        <xsl:attribute name="isoCountryCode" />
                      </CountryCode>
                    </TelephoneNumber>
                    <URL>
                      <xsl:attribute name="name" />
                    </URL>
                  </Fax>
                  <PostalAddress>
                    <xsl:attribute name="name" />
                    <Street />
                    <PostalCode />
                    <State />
                    <DeliverTo />
                    <Country>
                      <xsl:attribute name="isoCountryCode" />
                    </Country>
                    <City />
                  </PostalAddress>
                  <URL>
                    <xsl:attribute name="name" />
                  </URL>
                </Contact>
              </Supervisor>
              <JobDescription>
                <Description>
                  <xsl:attribute name="xml:lang" />
                  <ShortName />
                </Description>
              </JobDescription>
            </InvoiceLaborDetail -->
            <Extrinsic>
              <xsl:attribute name="name" />
            </Extrinsic>
            
          </InvoiceDetailServiceItem>
      </xsl:for-each>
      
      </InvoiceDetailOrder>
      
      
      <InvoiceDetailSummary>
      <SubtotalAmount>
        <Money>
          <xsl:attribute name="currency"><xsl:value-of select="/jobContractInvoice/transactionCurrencyCd" /></xsl:attribute>
          <xsl:attribute name="alternateAmount" />
          <xsl:attribute name="alternateCurrency" />
		<!-- Gather subtotals into variable -->
            <xsl:variable name="subtotals">
              <xsl:for-each select="/jobContractInvoice/invoiceLine">
                <number>
                  <xsl:value-of select="unitPrice * splitPct div 100"/>
                </number>
              </xsl:for-each>
            </xsl:variable>

            <!-- Sum subtotals stored as a result tree fragment in the variable -->
            <xsl:value-of select="sum(exsl:node-set($subtotals)/number)"/>
        </Money>
      </SubtotalAmount>
      <Tax>
      	 <Money>
          <xsl:attribute name="currency"><xsl:value-of select="/jobContractInvoice/transactionCurrencyCd" /></xsl:attribute>
          <xsl:attribute name="alternateAmount" />
          <xsl:attribute name="alternateCurrency" />
          <xsl:value-of  select="sum(exsl:node-set(//taxAmt)) + sum(exsl:node-set(//vatAmt)) "/>
        </Money>
        <Description xml:lang="en">
          <!--ShortName /-->tax
        </Description>
        <TaxDetail>
          <xsl:attribute name="purpose" />
          <xsl:attribute name="paymentDate" />
          <xsl:attribute name="percentageRate" />
          <xsl:attribute name="taxPointDate" />
          <!--  xsl:attribute name="isTriangularTransaction" / -->
          <!--  xsl:attribute name="isVatRecoverable" / -->
          <xsl:attribute name="category">all</xsl:attribute>
          <!--  TaxableAmount>
            <Money>
              <xsl:attribute name="currency" />
              <xsl:attribute name="alternateAmount" />
              <xsl:attribute name="alternateCurrency" />
            </Money>
          </TaxableAmount -->
          <TaxAmount>
            <Money>
              <xsl:attribute name="currency"><xsl:value-of select="/jobContractInvoice/transactionCurrencyCd" /></xsl:attribute>
              <xsl:attribute name="alternateAmount" />
              <xsl:attribute name="alternateCurrency" />
              <xsl:value-of  select="sum(exsl:node-set(//taxAmt)) + sum(exsl:node-set(//vatAmt)) "/>
            </Money>
          </TaxAmount>
          <!--  TaxLocation><xsl:attribute name="xml:lang" /></TaxLocation -->
          <Description xml:lang="en">
            <!--ShortName /-->tax
          </Description>
          <TriangularTransactionLawReference>
            <xsl:attribute name="xml:lang" />
          </TriangularTransactionLawReference>
        </TaxDetail>
      </Tax>
      <!--  SpecialHandlingAmount>
        <Description>
          <xsl:attribute name="xml:lang" />
          <ShortName />
        </Description>
        <Money>
          <xsl:attribute name="currency" />
          <xsl:attribute name="alternateAmount" />
          <xsl:attribute name="alternateCurrency" />
        </Money>
      </SpecialHandlingAmount -->
      <!-- ShippingAmount>
        <Money>
          <xsl:attribute name="currency" />
          <xsl:attribute name="alternateAmount" />
          <xsl:attribute name="alternateCurrency" />
        </Money>
      </ShippingAmount -->
      <GrossAmount>
        <Money>
          <xsl:attribute name="currency"><xsl:value-of select="/jobContractInvoice/transactionCurrencyCd" /></xsl:attribute>
          <xsl:attribute name="alternateAmount" />
          <xsl:attribute name="alternateCurrency" />
          <!-- Gather grosstotals into variable -->
            <xsl:variable name="grosstotals">
              <xsl:for-each select="/jobContractInvoice/invoiceLine">
                <number>
                  <xsl:value-of select="unitPrice * splitPct div 100 + taxAmt + vatAmt"/>
                </number>
              </xsl:for-each>
            </xsl:variable>
            <xsl:value-of select="sum(exsl:node-set($grosstotals)/number)"/>
        </Money>
      </GrossAmount>
      <InvoiceDetailDiscount>
        <xsl:attribute name="percentageRate" />
        <Money>
          <xsl:attribute name="currency"><xsl:value-of select="/jobContractInvoice/transactionCurrencyCd" /></xsl:attribute>
          <xsl:attribute name="alternateAmount" />
          <xsl:attribute name="alternateCurrency" />
          <!-- Gather subtotals into variable -->
            <xsl:variable name="discounttotal">
              <xsl:for-each select="/jobContractInvoice/invoiceLine">
                <number>
                  <xsl:value-of select="((unitPrice * splitPct div 100)+taxAmt+vatAmt) * (discountPct div 100)"/>
                </number>
              </xsl:for-each>
            </xsl:variable>
            <!-- Sum subtotals stored as a result tree fragment in the variable -->
            <xsl:value-of select="sum(exsl:node-set($discounttotal)/number)"/>
        </Money>
      </InvoiceDetailDiscount>
      <NetAmount>
        <Money>
          <xsl:attribute name="currency"><xsl:value-of select="/jobContractInvoice/transactionCurrencyCd" /></xsl:attribute>
          <xsl:attribute name="alternateAmount" />
          <xsl:attribute name="alternateCurrency" />
          <xsl:value-of  select="sum(exsl:node-set(//netPrice))"/>
        </Money>
      </NetAmount>
      <!-- DepositAmount>
        <Money>
          <xsl:attribute name="currency" />
          <xsl:attribute name="alternateAmount" />
          <xsl:attribute name="alternateCurrency" />
        </Money>
      </DepositAmount-->
      <DueAmount>
        <Money>
          <xsl:attribute name="currency"><xsl:value-of select="/jobContractInvoice/transactionCurrencyCd" /></xsl:attribute>
          <xsl:attribute name="alternateAmount" />
          <xsl:attribute name="alternateCurrency" />
          <xsl:value-of  select="sum(exsl:node-set(//netPrice))"/>
        </Money>
      </DueAmount>
    </InvoiceDetailSummary>
    </InvoiceDetailRequest>
    </Request>
  </cXML>
  </xsl:template>
  
  <xsl:template match="invoiceValue1">
  	<xsl:variable name="myValue1" select="."/>
    	<xsl:if test="$myValue1 != ''" >
			<xsl:if test="contains($myValue1,'po')">
   				<xsl:value-of select="substring-after($myValue1,'po#')" />
			</xsl:if>
			<xsl:if test="contains($myValue1,'PO') or contains($myValue1, 'PO')">
   				<xsl:value-of select="substring-after($myValue1,'PO#')" />
			</xsl:if>
  		</xsl:if>
  </xsl:template>
  
  
  <xsl:template match="jobOrder">
    <Period>
      <xsl:attribute name="startDate">
      <xsl:if test="contains(nominationDate,'.000')">
        <xsl:value-of select="concat(substring-before(nominationDate,'.000'),substring-after(nominationDate,'.000'))" />
      </xsl:if>
      </xsl:attribute>
      <xsl:attribute name="endDate">
      <xsl:if test="jobFinishDate!='' and contains(jobFinishDate,'.000')">
      <xsl:variable name="jobfinishdate" select="concat(substring-before(jobFinishDate,'.000'), substring-after(jobFinishDate,'.000'))"/>
        <xsl:value-of select="$jobfinishdate" />
      </xsl:if>
      </xsl:attribute>
      
    </Period>
  </xsl:template>

  
</xsl:stylesheet>
