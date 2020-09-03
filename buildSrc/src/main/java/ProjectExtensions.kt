import org.gradle.api.Project
import java.lang.NumberFormatException
import java.util.Locale
import java.util.Properties

fun Project.taskNames() = gradle.startParameter.taskNames.map { it.toLowerCase(Locale.ENGLISH) }
fun Project.isDebugFlavor() = taskNames().any { it.contains("debug") }
fun Project.isQaFlavor() = taskNames().any { it.contains("qa") }
fun Project.isReleaseFlavor() = taskNames().any { it.contains("release")}

fun Project.readPropertyString(key: String, defaultValue: String = ""): String {
    return properties[key]?.toString() ?: defaultValue
}

fun Project.readPropertyBoolean(key: String, defaultValue: Boolean = false): Boolean {
    return try {
        readPropertyString(key).toBoolean()
    } catch (nfe: NumberFormatException) {
        defaultValue
    }
}

fun Project.readProperty(path: String): Properties {
    return Properties().apply { load(rootProject.file(path).inputStream()) }
}

inline fun <reified T : PropertyInfo> Project.propertyInfo(): T {
    return T::class.java.newInstance().apply { initialize(project) }
}
