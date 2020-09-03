object Libs {
    object Kotlin {
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.Kotlin.stdLib}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
    }

    object Google {
        object Jetpack {
            const val databindingCompiler = "androidx.databinding:databinding-compiler:${Versions.Android.gradlePlugin}"
            const val appcompat = "androidx.appcompat:appcompat:${Versions.Google.Jetpack.appcompat}"
            const val fragment = "androidx.fragment:fragment:${Versions.Google.Jetpack.fragment}"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.Google.Jetpack.fragment}"
            const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.Google.Jetpack.recyclerview}"
            const val annotation = "androidx.annotation:annotation:${Versions.Google.Jetpack.annotation}"
            const val transition = "androidx.transition:transition:${Versions.Google.Jetpack.transition}"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.Google.Jetpack.constraintLayout}"
            const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.Google.Jetpack.lifecycle}"
            const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Google.Jetpack.lifecycle}"
            const val lifecycleCommonJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.Google.Jetpack.lifecycle}"
            const val room = "androidx.room:room-runtime:${Versions.Google.Jetpack.room}"
            const val roomKtx = "androidx.room:room-ktx:${Versions.Google.Jetpack.room}"
            const val roomCompiler = "androidx.room:room-compiler:${Versions.Google.Jetpack.room}"
            const val ktx = "androidx.core:core-ktx:${Versions.Google.Jetpack.ktx}"
            const val navigationFragment = "androidx.navigation:navigation-fragment:${Versions.Google.Jetpack.navigation}"
            const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.Google.Jetpack.navigation}"
            const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.Google.Jetpack.navigation}"
        }

        object Firebase {
            const val core = "com.google.firebase:firebase-core:${Versions.Google.Firebase.core}"
        }

        object Dagger {
            const val dagger = "com.google.dagger:dagger:${Versions.Google.Dagger.dagger}"
            const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.Google.Dagger.dagger}"
            const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.Google.Dagger.hiltAndroid}"
            const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.Google.Dagger.hiltAndroid}"
            const val hiltCommon = "androidx.hilt:hilt-common:${Versions.Google.Dagger.hilt}"
            const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.Google.Dagger.hilt}"
            const val hiltLifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.Google.Dagger.hilt}"
        }

        const val material = "com.google.android.material:material:${Versions.Google.material}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.Test.junit}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
        const val arcTesting = "androidx.arch.core:core-testing:${Versions.Test.archTesting}"
        const val roomTesting = "androidx.room:room-testing:${Versions.Google.Jetpack.room}"
        const val mockitoCore = "org.mockito:mockito-core:${Versions.Test.mockito}"
        const val mockitoInline = "org.mockito:mockito-inline:${Versions.Test.mockito}"
        const val robolectric = "org.robolectric:robolectric:${Versions.Test.robolectric}"
        const val truth = "com.google.truth:truth:${Versions.Test.truth}"
        const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:${Versions.Google.Dagger.hiltAndroid}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.Google.Dagger.dagger}"
        const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.Google.Dagger.hilt}"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.Google.Dagger.hiltAndroid}"
    }

    const val desugarJdkLib = "com.android.tools:desugar_jdk_libs:${Versions.desugarJdkLib}"
    const val insetterKtx = "dev.chrisbanes:insetter-ktx:${Versions.insetter}"
    const val insetterDbx = "dev.chrisbanes:insetter-dbx:${Versions.insetter}"

    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    const val materialDialogCore = "com.afollestad.material-dialogs:core:${Versions.materialDialog}"
    const val materialDialogLifecycle = "com.afollestad.material-dialogs:lifecycle:${Versions.materialDialog}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
}
