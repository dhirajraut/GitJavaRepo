<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:proj="http://www.intertek.com/ESB/ProjectContractRequest" xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs xsi xsl" xmlns="http://www.intertek.com/ESB/ProjectContractRequest">
	<xsl:include href="../response/responses.xsl" />
	<xsl:template match="/ProjectContractX">
      <proj:GetProjectContractRequest>
         <proj:MessageData>
            <proj:Transaction>
               <proj:ProjectHeader>
                  <proj:BusinessUnit><xsl:value-of select="businessUnit"/></proj:BusinessUnit>
                  <proj:JobNumber><xsl:value-of select="jobNumber"/></proj:JobNumber>
                  <proj:JobDescription><xsl:value-of select="jobDescription"/></proj:JobDescription>
                  <proj:JobDescriptionDetail><xsl:value-of select="jobDescriptionDetail"/></proj:JobDescriptionDetail>
                  <proj:ProjectType><xsl:value-of select="projectType"/></proj:ProjectType>
                  <proj:CustomerId><xsl:value-of select="customerId"/></proj:CustomerId>
                  <proj:ProjectManager><xsl:value-of select="projectManager"/></proj:ProjectManager>
                  <!-- proj:ProjectManagerEffDt><xsl:value-of select="projectManagerEffDt"/></proj:ProjectManagerEffDt-->
                  <proj:OperatingUnit><xsl:value-of select="operatingUnit"/></proj:OperatingUnit>
                  <proj:Product><xsl:value-of select="product"/></proj:Product>
                  <proj:ServiceOffering><xsl:value-of select="serviceOffering"/></proj:ServiceOffering>
                  <!-- proj:CharfieldtEffDt><xsl:value-of select="charfieldtEffDt"/></proj:CharfieldtEffDt-->
                  <proj:JobStatus><xsl:value-of select="jobStatus"/></proj:JobStatus>
                  <!-- proj:JobStatusEffDt><xsl:value-of select="jobStatusEffDt"/></proj:JobStatusEffDt-->
                  <proj:MsgAction><xsl:value-of select="msgAction"/></proj:MsgAction>
               </proj:ProjectHeader>
				<xsl:for-each select="projectActivities">
	               <proj:ProjectActivity>
	                  <proj:BusinessUnit><xsl:value-of select="businessUnit"/></proj:BusinessUnit>
	                  <proj:JobNumber><xsl:value-of select="jobNumber"/></proj:JobNumber>
	                  <proj:JobLineNumber><xsl:value-of select="jobLineNumber"/></proj:JobLineNumber>
	                  <proj:JobLineDescription><xsl:value-of select="jobLineDescription"/></proj:JobLineDescription>
	                  <proj:JobLineDetailDescription><xsl:value-of select="jobLineDetailDescription"/></proj:JobLineDetailDescription>
	                  <proj:JobLineStatus><xsl:value-of select="jobLineStatus"/></proj:JobLineStatus>
	                  <!--proj:JobLineStatusEffDt><xsl:value-of select="jobLineStatusEffDt"/></proj:JobLineStatusEffDt-->
	                  <proj:OperatingUnit><xsl:value-of select="operatingUnit"/></proj:OperatingUnit>
	                  <proj:DepartmentId><xsl:value-of select="departmentId"/></proj:DepartmentId>
	                  <proj:Product><xsl:value-of select="product"/></proj:Product>
	                  <proj:ServiceOffering><xsl:value-of select="serviceOffering"/></proj:ServiceOffering>
	                  <!--proj:ChartfieldEffDt><xsl:value-of select="chartfieldEffDt"/></proj:ChartfieldEffDt-->
	                  <proj:MsgAction><xsl:value-of select="msgAction"/></proj:MsgAction>
	               </proj:ProjectActivity>
				</xsl:for-each>
            </proj:Transaction>
         </proj:MessageData>
      </proj:GetProjectContractRequest>
	</xsl:template>
</xsl:stylesheet>

