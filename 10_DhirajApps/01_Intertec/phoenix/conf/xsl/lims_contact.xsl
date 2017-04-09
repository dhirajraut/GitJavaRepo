<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/array">
    <UpdateTable xmlns="http://tempuri.org/">
      <xmlData>
        <Data>
          <CUSTOMER_CONTACT>
            <IDENTITY><xsl:value-of select="*[position() = 2]" /></IDENTITY>
            <CONTACT><xsl:value-of select="*[position() = 1]" /></CONTACT>
            <CRM_PERSON_ID><xsl:value-of select="*[position() = 1]" /></CRM_PERSON_ID>
            <FMS_CONTACT_ID><xsl:value-of select="*[position() = 3]" /></FMS_CONTACT_ID>
            <CONTACT_SHORT><xsl:value-of select="*[position() = 5]" />,<xsl:value-of select="*[position() = 4]" /></CONTACT_SHORT>
            <CONTACT_FULL><xsl:value-of select="*[position() = 4]" /><xsl:value-of select="' '" /><xsl:value-of select="*[position() = 5]" /></CONTACT_FULL>
            <FIRST_NAME><xsl:value-of select="*[position() = 4]" /></FIRST_NAME>
            <MIDDLE_NAME></MIDDLE_NAME>
            <LAST_NAME><xsl:value-of select="*[position() = 5]" /></LAST_NAME>
            <PHONE><xsl:value-of select="*[position() = 6]" /></PHONE>
            <FAX></FAX>
            <EMAIL><xsl:value-of select="*[position() = 7]" /></EMAIL>
            <ADDRESS_1><xsl:value-of select="*[position() = 8]" /></ADDRESS_1>
            <ADDRESS_2><xsl:value-of select="*[position() = 9]" /></ADDRESS_2>
            <ADDRESS_3><xsl:value-of select="*[position() = 10]" /></ADDRESS_3>
            <ADDRESS_4><xsl:value-of select="*[position() = 11]" /></ADDRESS_4>
            <CITY><xsl:value-of select="*[position() = 12]" /></CITY>
            <COUNTRY><xsl:value-of select="*[position() = 13]" /></COUNTRY>
            <STATE><xsl:value-of select="*[position() = 14]" /></STATE>
            <POSTAL><xsl:value-of select="*[position() = 15]" /></POSTAL>
          </CUSTOMER_CONTACT>
        </Data>
      </xmlData>
    </UpdateTable>
  </xsl:template>

</xsl:stylesheet>
