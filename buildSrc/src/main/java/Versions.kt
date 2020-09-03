object Versions {
    object Android {
        const val compileSdk = 29
        const val minSdk = 23
        const val targetSdk = 29
        const val gradlePlugin = "4.0.1"
    }

    object Kotlin {
        const val stdLib = "1.4.0"
        const val coroutines = "1.3.7"
    }

    object Google {
        object Jetpack {
            const val appcompat = "1.1.0"
            const val fragment = "1.2.5"
            const val recyclerview = "1.1.0"
            const val annotation = "1.1.0"
            const val constraintLayout = "2.0.1"
            const val lifecycle = "2.2.0"
            const val room = "2.2.0"
            const val ktx = "1.3.1"
            const val navigation = "2.3.0"
            const val transition = "1.3.1"
        }

        const val material = "1.2.0"
        const val googleServices = "4.3.3"

        object Firebase {
            const val core = "17.4.4"
        }

        object Dagger {
            const val dagger = "2.28.3"
            const val hilt = "1.0.0-alpha02"
            const val hiltAndroid = "2.28.3-alpha"
        }
    }

    object Test {
        const val junit = "4.13"
        const val mockito = "3.5.6"
        const val archTesting = "2.1.0"
        const val robolectric = "4.3.1"
        const val truth = "1.0.1"
    }

    const val desugarJdkLib = "1.0.10"
    const val insetter = "0.3.1"

    const val lottie = "3.4.1"
    const val materialDialog = "3.3.0"

    const val timber = "4.7.1"
    const val stetho = "1.5.1"

    const val ktlint = "0.38.1"
    const val detekt = "1.11.2"
}