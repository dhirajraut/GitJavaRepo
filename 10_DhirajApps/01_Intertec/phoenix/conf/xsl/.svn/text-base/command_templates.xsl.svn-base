<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template name="getYNFromBoolean">
    <xsl:param name="booleanInput"></xsl:param>

    <xsl:choose> 
      <xsl:when test="$booleanInput = 'true'">
        <xsl:value-of select="'Y'" />
      </xsl:when>
      <xsl:when test="$booleanInput = 'false'">
        <xsl:value-of select="'N'" />
      </xsl:when>
      <xsl:when test="$booleanInput = '1'">
        <xsl:value-of select="'Y'" />
      </xsl:when>
      <xsl:when test="$booleanInput = '0'">
        <xsl:value-of select="'N'" />
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="$booleanInput" />
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <xsl:template name="getNewDateFormat">
    <xsl:param name="dateInput"></xsl:param>

    <xsl:choose> 
      <xsl:when test="$dateInput">
        <xsl:value-of select="substring($dateInput, 1, 10)" />
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="$dateInput" />
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  
   <xsl:template name="convertDateToDateTime">
      <xsl:param name="dateInput"></xsl:param>
  
      <xsl:choose> 
        <xsl:when test="$dateInput">
          <xsl:value-of select="concat(string($dateInput), 'T00:00:00')"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="$dateInput" />
        </xsl:otherwise>
      </xsl:choose>
  </xsl:template>

</xsl:stylesheet>
