# Crashlytics 2.+

-keep class com.crashlytics.** { *; }
-keep class com.crashlytics.android.**
-keepattributes SourceFile, LineNumberTable, *Annotation*

# If you are using custom exceptions, add this line so that custom exception types are skipped during obfuscation:
-keep public class * extends java.lang.Exception

# For Fabric to properly de-obfuscate your crash reports, you need to remove this line from your ProGuard config:
# -printmapping mapping.txt

-keep class io.fabric.sdk.android.services.events.EventsStorageListener
-keep class io.fabric.sdk.android.services.events.EventsStorage
-keep class io.fabric.sdk.android.services.common.CurrentTimeProvider
-keep class io.fabric.sdk.android.services.events.EventTransform
-keep class io.fabric.sdk.android.services.concurrency.Task
-keep class io.fabric.sdk.android.services.network.HttpMethod
-keep class io.fabric.sdk.android.services.network.HttpRequestFactory
-keep class io.fabric.sdk.android.Kit
-keep class io.fabric.sdk.android.services.common.IdManager
-keep class io.fabric.sdk.android.InitializationCallback
-keep class io.fabric.sdk.android.Fabric
-keep class io.fabric.sdk.android.services.common.IdManager$DeviceIdentifierType
-keep class io.fabric.sdk.android.services.persistence.PreferenceStore
-keep class io.fabric.sdk.android.services.persistence.FileStore
-keep class io.fabric.sdk.android.services.settings.PromptSettingsData
-keep class io.fabric.sdk.android.services.network.HttpRequest
-keep class io.fabric.sdk.android.services.settings.SessionSettingsData
-keep class io.fabric.sdk.android.services.settings.SettingsData
-keep class io.fabric.sdk.android.services.settings.BetaSettingsData
-keep class io.fabric.sdk.android.ActivityLifecycleManager
-keep class io.fabric.sdk.android.services.settings.AnalyticsSettingsData
-keep class io.fabric.sdk.android.services.concurrency.internal.RetryState
-keep class io.fabric.sdk.android.services.concurrency.internal.Backoff
-keep class io.fabric.sdk.android.services.common.Crash$LoggedException
-keep class io.fabric.sdk.android.services.common.Crash$FatalException