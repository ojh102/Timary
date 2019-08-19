<?xml version="1.0"?>
<recipe>
    <instantiate from="res/layout/activity_blank.xml.ftl"
                    to="${escapeXmlAttribute(resOut)}/layout/${escapeXmlAttribute(activityName)}.xml" />

   	<open file="${escapeXmlAttribute(srcOut)}/${packName}/${Name}ActivityViewModel.kt" />

    <instantiate from="src/app_package/BlankActivityViewModel.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${packName}/${Name}ActivityViewModel.kt" />

   	<open file="${escapeXmlAttribute(srcOut)}/${packName}/${Name}ActivityAction.kt" />

    <instantiate from="src/app_package/BlankActivityAction.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${packName}/${Name}ActivityAction.kt" />

   	<open file="${escapeXmlAttribute(srcOut)}/${packName}/${Name}ActivityState.kt" />

    <instantiate from="src/app_package/BlankActivityState.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${packName}/${Name}ActivityState.kt" />

    <open file="${escapeXmlAttribute(srcOut)}/${packName}/${Name}ActivityUseCase.kt" />

    <instantiate from="src/app_package/BlankActivityUseCase.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${packName}/${Name}ActivityUseCase.kt" />

    <open file="${escapeXmlAttribute(srcOut)}/${packName}/${Name}ActivityUseCaseImpl.kt" />

    <instantiate from="src/app_package/BlankActivityUseCaseImpl.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${packName}/${Name}ActivityUseCaseImpl.kt" />

    <open file="${escapeXmlAttribute(srcOut)}/${packName}/${Name}ActivityModule.kt" />

    <instantiate from="src/app_package/BlankActivityModule.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${packName}/${Name}ActivityModule.kt" />

    <open file="${escapeXmlAttribute(srcOut)}/${packName}/${className}.kt" />

    <instantiate from="src/app_package/BlankActivity.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${packName}/${className}.kt" />
</recipe>
