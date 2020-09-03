buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Plugins.buildTool)
        classpath(Plugins.kotlinGradle)
        classpath(Plugins.navigationSafeArgs)
        classpath(Plugins.daggerHilt)
        classpath(Plugins.googleServices)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { setUrl("https://jitpack.io") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}