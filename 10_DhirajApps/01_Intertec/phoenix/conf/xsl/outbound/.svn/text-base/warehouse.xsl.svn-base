<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ns="http://www.intertek.com/ESB/Warehouse" xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs xsi xsl" xmlns="http://www.intertek.com/ESB/Warehouse">
	<xsl:include href="../response/responses.xsl" />
	<xsl:template match="/WarehouseX">
      <ns:PublishWarehouseRequest>
         <ns:MessageData>
            <ns:Id><xsl:value-of select="id"/></ns:Id>
            <ns:BusinessUnit><xsl:value-of select="id"/></ns:BusinessUnit>
            <ns:Address>
               <ns:Address1><xsl:value-of select="address/address1"/></ns:Address1>
               <ns:Address2><xsl:value-of select="address/address2"/></ns:Address2>
               <ns:Address3><xsl:value-of select="address/address3"/></ns:Address3>
               <ns:Address4><xsl:value-of select="address/address4"/></ns:Address4>
               <ns:City><xsl:value-of select="address/city"/></ns:City>
               <ns:Postal><xsl:value-of select="address/postal"/></ns:Postal>
               <ns:State><xsl:value-of select="address/state"/></ns:State>
               <ns:Country><xsl:value-of select="address/country"/></ns:Country>
               <ns:County><xsl:value-of select="address/county"/></ns:County>
               <ns:InCityLimit><xsl:value-of select="address/inCityLimit"/></ns:InCityLimit>
            </ns:Address>
            <ns:ContactPhone><xsl:value-of select="contactPhone"/></ns:ContactPhone>
            <ns:FaxNumber><xsl:value-of select="faxNumber"/></ns:FaxNumber>
            <ns:Status><xsl:value-of select="status"/></ns:Status>
         </ns:MessageData>
      </ns:PublishWarehouseRequest>
	</xsl:template>
</xsl:stylesheet>
