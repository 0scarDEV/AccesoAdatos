<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : Actores.xsl
    Author     : María José Galán 
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>
    <xsl:template match="/">
        <html>
            <xsl:apply-templates />
        </html>
    </xsl:template>
    <xsl:template match="Actores">
        <head>
            <title> LISTADO DE ACTORES </title>
        </head>
        <body>
            <h1>Lista de actores</h1>
            <table border="1">
                <tr bgcolor="gray">
                    <th>Nombre</th>
                    <th>Sexo</th>
                    <th>Datanacemento</th>
                </tr>
                <xsl:apply-templates select="Actor"/>
            </table>     
        </body>
    </xsl:template>
    <xsl:template match="Actor">
        <!-- Generamos sólo el comienzo de la etiqueta tr -->
        <xsl:text disable-output-escaping="yes"> &lt;tr </xsl:text>
        <xsl:choose>
            <xsl:when test="(position() mod 2)=1">
                bgcolor ="aqua"
            </xsl:when>
            <xsl:otherwise>
                 bgcolor ="silver"
            </xsl:otherwise>
            
        </xsl:choose>
        
        <!-- Generamos el cierre de la etiqueta de apertura de tr -->
        <xsl:text disable-output-escaping="yes"> &gt; </xsl:text>

        <!-- <tr bgcolor="silver"> -->
        <xsl:apply-templates />
        <!--Generamos la etiqueta de cierre de tr -->
        <xsl:text disable-output-escaping="yes"> &lt;/tr&gt; </xsl:text>
     
        <!-- </tr> -->
    </xsl:template>
    <xsl:template match="Nome|Sexo|DataNacemento">
        <td>
            <xsl:apply-templates />
        </td>
    </xsl:template>
</xsl:stylesheet>
