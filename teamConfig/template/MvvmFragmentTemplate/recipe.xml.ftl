<?xml version="1.0"?>
<recipe>

    <instantiate from="res/layout/fragment_blank.xml.ftl"
                    to="${escapeXmlAttribute(resOut)}/layout/${escapeXmlAttribute(fragmentName)}.xml" />

   	<open file="${escapeXmlAttribute(srcOut)}/${packName}/${Name}ViewModel.kt" />

    <instantiate from="src/app_package/BlankViewModel.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${packName}/${Name}ViewModel.kt" />

   	<open file="${escapeXmlAttribute(srcOut)}/${packName}/${Name}Action.kt" />

    <instantiate from="src/app_package/BlankAction.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${packName}/${Name}Action.kt" />

   	<open file="${escapeXmlAttribute(srcOut)}/${packName}/${Name}State.kt" />

    <instantiate from="src/app_package/BlankState.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${packName}/${Name}State.kt" />

    <open file="${escapeXmlAttribute(srcOut)}/${packName}/${Name}UseCase.kt" />

    <instantiate from="src/app_package/BlankUseCase.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${packName}/${Name}UseCase.kt" />

    <open file="${escapeXmlAttribute(srcOut)}/${packName}/${Name}UseCaseImpl.kt" />

    <instantiate from="src/app_package/BlankUseCaseImpl.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${packName}/${Name}UseCaseImpl.kt" />

    <open file="${escapeXmlAttribute(srcOut)}/${packName}/${Name}FragmentModule.kt" />

    <instantiate from="src/app_package/BlankFragmentModule.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${packName}/${Name}FragmentModule.kt" />

    <open file="${escapeXmlAttribute(srcOut)}/${packName}/${className}.kt" />

    <instantiate from="src/app_package/BlankFragment.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${packName}/${className}.kt" />
</recipe>
