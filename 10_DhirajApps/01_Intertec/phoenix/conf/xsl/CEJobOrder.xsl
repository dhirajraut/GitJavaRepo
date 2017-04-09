<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:datetime="http://exslt.org/dates-and-times" exclude-result-prefixes="datetime">
  <xsl:include href="command_templates.xsl" />
	<xsl:template match="/CEJobOrder">
		<project>
			<jobNumber><xsl:value-of select="jobNumber"/></jobNumber>
		</project>
	</xsl:template>
</xsl:stylesheet>

