<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:date="com.intertek.util.DateUtil"
  xmlns:java="java">

  <xsl:include href="command_templates.xsl" />

  <xsl:template match="/array">
    <UpdateTable xmlns="http://tempuri.org/">
      <xmlData>
        <Data>
          <CUSTOMER>
            <IDENTITY><xsl:value-of select="*[position() = 1]" /></IDENTITY>
            <COMPANY_NAME><xsl:value-of select="*[position() = 2]" /></COMPANY_NAME>
            <COUNTRY><xsl:value-of select="*[position() = 3]" /></COUNTRY>
            <ADDRESS1><xsl:value-of select="*[position() = 4]" /></ADDRESS1>
            <ADDRESS2><xsl:value-of select="*[position() = 5]" /></ADDRESS2>
            <ADDRESS3><xsl:value-of select="*[position() = 6]" /></ADDRESS3>
            <ADDRESS4><xsl:value-of select="*[position() = 7]" /></ADDRESS4>
            <CITY><xsl:value-of select="*[position() = 8]" /></CITY>
            <STATE><xsl:value-of select="*[position() = 9]" /></STATE>
            <POSTAL><xsl:value-of select="*[position() = 10]" /></POSTAL>
            <PHONE_NUM><xsl:value-of select="*[position() = 11]" /></PHONE_NUM>
            <PS_MODIFIED_ON>
              <xsl:value-of select="date:convertDate(*[position() = 12], 'yyyy-MM-dd', 'dd-MMM-yy')" />
            </PS_MODIFIED_ON>
          </CUSTOMER>
        </Data>
      </xmlData>
    </UpdateTable>
  </xsl:template>

</xsl:stylesheet>
