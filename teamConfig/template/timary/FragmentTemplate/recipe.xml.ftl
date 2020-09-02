<?xml version="1.0"?>
<recipe>

    <merge from="res/navigation/navigation_mytine.xml.ftl"
        to="${escapeXmlAttribute(resOut)}/navigation/navigation_mytine.xml" />

    <instantiate from="res/layout/fragment_blank.xml.ftl"
                    to="${escapeXmlAttribute(resOut)}/layout/${escapeXmlAttribute(fragmentName)}.xml" />

   	<open file="${escapeXmlAttribute(srcOut)}/${packName}/${Name}ViewModel.kt" />

    <instantiate from="src/app_package/BlankViewModel.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${packName}/${Name}ViewModel.kt" />

    <open file="${escapeXmlAttribute(srcOut)}/${packName}/${className}.kt" />

    <instantiate from="src/app_package/BlankFragment.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${packName}/${className}.kt" />
</recipe>
