plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

apply {
    from("../teamConfig/staticCodeAnalysis/ktlint.gradle")
    from("../teamConfig/staticCodeAnalysis/detekt.gradle")

    if (readPropertyBoolean("jacocoEnabled")) {
        from("../jacoco.gradle")
    }
    if (readPropertyBoolean("isNewbie")) {
        from("../teamConfig/git/git-hooks.gradle")
    }
}

androidExtensions {
    isExperimental = true
}

kapt {
    correctErrorTypes = true
}


android {
    compileSdkVersion(Versions.Android.compileSdk)

    defaultConfig {
        applicationId = "com.github.ojh102.timary"
        minSdkVersion(Versions.Android.minSdk)
        targetSdkVersion(Versions.Android.targetSdk)
        versionCode = buildVersionCode()
        versionName = buildVersionName()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments(
                    mapOf(
                        "room.schemaLocation" to "$projectDir/schemas",
                        "room.incremental" to "true",
                        "room.expandProjection" to "true"
                    )
                )
            }
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    compileOptions {
        coreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    lintOptions {
        isCheckReleaseBuilds = false
        isCheckDependencies = true
        isIgnoreTestSources = true
    }

    buildFeatures {
        dataBinding = true
    }

    hilt {
        enableTransformForLocalTests = true
    }

    testOptions {
        unitTests {
            it.isIncludeAndroidResources = true
        }
    }

    signingConfigs {
        create("ide") {
            storeFile = file("../keystore/debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    packagingOptions {
        exclude("META-INF/*.version")
        exclude("META-INF/proguard/*")
        exclude("/*.properties")
        exclude("META-INF/*.properties")
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("ide")
            applicationIdSuffix = ".debug"

            isDebuggable = true
            isZipAlignEnabled = false
            isMinifyEnabled = false

            isTestCoverageEnabled = readPropertyBoolean("jacocoEnabled")
        }

        create("qa") {
            signingConfig = signingConfigs.getByName("ide")
            applicationIdSuffix = ".qa"

            isDebuggable = true
            isZipAlignEnabled = true
            isMinifyEnabled = true
            isShrinkResources = true
            isCrunchPngs = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                *file("../proguard").listFiles()
            )
        }

        getByName("release") {
            signingConfig = null

            isDebuggable = false
            isZipAlignEnabled = true
            isMinifyEnabled = true
            isShrinkResources = true
            isCrunchPngs = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                *file("../proguard").listFiles()
            )
        }
    }

    sourceSets {
        getByName("main") {
            assets.srcDir(file("$projectDir/schemas"))
        }
    }
}

dependencies {
    coreLibraryDesugaring(Libs.desugarJdkLib)

    implementation(Libs.Kotlin.stdLib)
    implementation(Libs.Kotlin.coroutines)

    // Jetpack
    kapt(Libs.Google.Jetpack.databindingCompiler)
    implementation(Libs.Google.Jetpack.appcompat)
    implementation(Libs.Google.Jetpack.fragment)
    implementation(Libs.Google.Jetpack.fragmentKtx)
    implementation(Libs.Google.Jetpack.recyclerview)
    implementation(Libs.Google.Jetpack.annotation)
    implementation(Libs.Google.Jetpack.transition)
    implementation(Libs.Google.Jetpack.constraintLayout)
    implementation(Libs.Google.Jetpack.lifecycleExtensions)
    implementation(Libs.Google.Jetpack.lifecycleViewModelKtx)
    implementation(Libs.Google.Jetpack.lifecycleCommonJava8)
    implementation(Libs.Google.Jetpack.room)
    implementation(Libs.Google.Jetpack.roomKtx)
    kapt(Libs.Google.Jetpack.roomCompiler)
    implementation(Libs.Google.Jetpack.ktx)
    implementation(Libs.Google.Jetpack.navigationFragment)
    implementation(Libs.Google.Jetpack.navigationFragmentKtx)
    implementation(Libs.Google.Jetpack.navigationUiKtx)

    implementation(Libs.Google.Firebase.core)

    implementation(Libs.Google.Dagger.dagger)
    kapt(Libs.Google.Dagger.daggerCompiler)
    implementation(Libs.Google.Dagger.hiltAndroid)
    kapt(Libs.Google.Dagger.hiltAndroidCompiler)
    implementation(Libs.Google.Dagger.hiltCommon)
    implementation(Libs.Google.Dagger.hiltLifecycleViewModel)
    kapt(Libs.Google.Dagger.hiltCompiler)

    implementation(Libs.Google.material)

    implementation(Libs.insetterKtx)
    implementation(Libs.insetterDbx)

    implementation(Libs.lottie)
    implementation(Libs.materialDialogCore)
    implementation(Libs.materialDialogLifecycle)

    implementation(Libs.timber)
    implementation(Libs.stetho)

    testImplementation(Libs.Test.junit)
    testImplementation(Libs.Test.coroutines)
    testImplementation(Libs.Test.coroutinesTest)
    testImplementation(Libs.Test.arcTesting)
    testImplementation(Libs.Test.roomTesting)
    testImplementation(Libs.Test.mockitoCore)
    testImplementation(Libs.Test.mockitoInline)
    testImplementation(Libs.Test.robolectric)
    testImplementation(Libs.Test.truth)
    testImplementation(Libs.Test.hiltAndroidTesting)
    kaptTest(Libs.Test.daggerCompiler)
    kaptTest(Libs.Test.hiltCompiler)
    kaptTest(Libs.Test.hiltAndroidCompiler)
}
