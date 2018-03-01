<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
  <xsl:output method="xml" indent="yes"/>

  <xsl:template match="/">
    <entries>
      <xsl:apply-templates select="entries/entry" />
    </entries>
  </xsl:template>

  <xsl:template match="entry">
      <entry>
        <xsl:attribute name="field"><xsl:value-of select="field" /></xsl:attribute>
      </entry>
  </xsl:template>

</xsl:transform>