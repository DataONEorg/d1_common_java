<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:V2="http://ns.dataone.org/service/types/v2.0"
                xmlns:V1="http://ns.dataone.org/service/types/v1" >
  <xsl:output encoding="UTF-8" method="xml" indent="yes"/>
  <xsl:strip-space elements="*" />

  <xsl:template match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
  </xsl:template>

  <xsl:template match="@xsi:schemaLocation">
    <xsl:attribute name="{name()}">
      <xsl:text>http://ns.dataone.org/service/types/v1 dataoneTypes.xsd</xsl:text>
    </xsl:attribute>
  </xsl:template>

  <xsl:template match="V2:*">
    <xsl:element name="V1:{local-name()}">
      <xsl:apply-templates select="@*|node()"/>
    </xsl:element>
  </xsl:template>

  <xsl:template match="extension | mediaType" />
</xsl:stylesheet>
