import org.gradle.api.Project

fun Project.buildVersionCode(): Int {
    val versionInfo = propertyInfo<VersionPropertyInfo>()
    val (major, minor, patch) = versionInfo.versionName.split(".").map { it.toInt() }

    val versionCode = (major * 100000000) + (minor * 1000000) + (patch * 10000) + buildNumber()

    println("versionCode = $versionCode")

    return versionCode
}

fun Project.buildVersionName(): String {
    val versionInfo = propertyInfo<VersionPropertyInfo>()

    val versionNameSuffix = when {
        isDebugFlavor() -> "-snaphsot"
        isQaFlavor() -> "-rc${versionInfo.extraVersion}"
        isReleaseFlavor() -> ""
        else -> ""
    }

    val versionName = "${versionInfo.versionName}.${buildNumber()}${versionNameSuffix}"

    println("versionName = $versionName")

    return versionName
}

private fun buildNumber() = System.getenv("BITRISE_BUILD_NUMBER")?.toInt() ?: 0
