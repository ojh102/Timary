import org.gradle.api.Project

class VersionPropertyInfo : PropertyInfo {
    lateinit var versionName: String
    lateinit var extraVersion: String

    override fun initialize(project: Project) {
        with(project.readProperty("version.properties")) {
            versionName = getProperty("versionName")
            extraVersion = getProperty("extraVersion")
        }
    }
}