<?xml version="1.0"?>
<recipe>
    <instantiate from="res/layout/activity_blank.xml.ftl"
                    to="${escapeXmlAttribute(resOut)}/layout/${escapeXmlAttribute(activityName)}.xml" />

   	<open file="${escapeXmlAttribute(srcOut)}/${packName}/${Name}ActivityViewModel.kt" />

    <instantiate from="src/app_package/BlankActivityViewModel.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${packName}/${Name}ActivityViewModel.kt" />

    <open file="${escapeXmlAttribute(srcOut)}/${packName}/${Name}ActivityModule.kt" />

    <instantiate from="src/app_package/BlankActivityModule.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${packName}/${Name}ActivityModule.kt" />

    <open file="${escapeXmlAttribute(srcOut)}/${packName}/${className}.kt" />

    <instantiate from="src/app_package/BlankActivity.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${packName}/${className}.kt" />
</recipe>
