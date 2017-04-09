<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:con="http://www.intertek.com/ESB/Contact" xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs xsi xsl" xmlns="http://www.intertek.com/ESB/Contact">
	<xsl:include href="../response/responses.xsl" />
	<xsl:template match="/ContactX">
      <con:PublishContactRequest>
         <con:MessageData>
            <con:ContactId><xsl:value-of select="contactId"/></con:ContactId>
            <con:Status><xsl:value-of select="status"/></con:Status>
            <con:FirstName><xsl:value-of select="firstName"/></con:FirstName>
            <con:LastName><xsl:value-of select="lastName"/></con:LastName>
            <con:Title><xsl:value-of select="title"/></con:Title>
            <con:Salutation><xsl:value-of select="salutation"/></con:Salutation>
            <con:WorkPhone><xsl:value-of select="workPhone"/></con:WorkPhone>
            <con:Fax><xsl:value-of select="fax"/></con:Fax>
            <con:PersonalPhone><xsl:value-of select="personalPhone"/></con:PersonalPhone>
            <con:WorkEmail><xsl:value-of select="workEmail"/></con:WorkEmail>
            <con:CellPhone><xsl:value-of select="cellPhone"/></con:CellPhone>
            <con:PersonalEmail><xsl:value-of select="personalEmail"/></con:PersonalEmail>
            <con:ContactFlag><xsl:value-of select="contactFlag"/></con:ContactFlag>
            <con:PreferredCommunicator><xsl:value-of select="preferredCommunicator"/></con:PreferredCommunicator>
            <xsl:for-each select="contactCustomer">
	            <con:ContactCustomer>
	               <con:ContactId><xsl:value-of select="contactId"/></con:ContactId>
	               <con:CustomerCode><xsl:value-of select="customerCode"/></con:CustomerCode>
	               <con:LocationNumber><xsl:value-of select="locationNumber"/></con:LocationNumber>
	               <con:Status><xsl:value-of select="status"/></con:Status>
	               <con:contactSequenceNumber><xsl:value-of select="contactSequenceNumber"/></con:contactSequenceNumber>
	               <con:BillTo><xsl:value-of select="billTo"/></con:BillTo>
	               <con:SoldTo><xsl:value-of select="soldTo"/></con:SoldTo>
	               <con:ShipTo><xsl:value-of select="shipTo"/></con:ShipTo>
	            </con:ContactCustomer>
			</xsl:for-each>
         </con:MessageData>
      </con:PublishContactRequest>
	</xsl:template>
</xsl:stylesheet>
