##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

##---------------End: proguard configuration for Gson  ----------

###### Firebase Begin

-keepattributes Signature
-keepattributes *Annotation*
-keepattributes EnclosingMethod
-keepattributes InnerClasses

###### Firebase End

## Dagger
-dontwarn com.google.errorprone.annotations.*
##

## Google Play Service Begin

-keep class com.google.protobuf.zzc
-keep class com.google.protobuf.zzd
-keep class com.google.protobuf.zze

-keep class com.google.android.gms.dynamic.IObjectWrapper
-keep class com.google.android.gms.internal.zzuq
-keep class com.google.android.gms.internal.oo
-keep class com.google.android.gms.internal.oh
-keep class com.google.android.gms.internal.zzcgl
-keep class com.google.android.gms.internal.ql
-keep class com.google.android.gms.internal.zzcem
-keep class com.google.android.gms.tagmanager.zzce
-keep class com.google.android.gms.tagmanager.zzcn
-keep class com.google.android.gms.plus.PlusOneButton$OnPlusOneClickListener
-keep class com.google.android.gms.measurement.AppMeasurement$zza
-keep class com.google.android.gms.measurement.AppMeasurement$OnEventListener
-keep class com.google.android.gms.measurement.AppMeasurement$EventInterceptor

-keep class com.google.android.gms.ads.mediation.MediationAdRequest
-keep class com.google.android.gms.ads.mediation.MediationBannerListener
-keep class com.google.android.gms.ads.AdSize
-keep class com.google.android.gms.ads.mediation.MediationInterstitialListener
-keep class com.google.android.gms.ads.mediation.MediationNativeListener
-keep class com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener
-keep class com.google.android.gms.ads.InterstitialAd
-keep class com.google.android.gms.ads.AdListener
-keep class com.google.android.gms.ads.Correlator
-keep class com.google.android.gms.ads.formats.NativeAd
-keep class com.google.android.gms.ads.mediation.NativeMediationAdRequest
-keep class com.google.android.gms.ads.formats.MediaView
-keep class com.google.android.gms.ads.formats.AdChoicesView
-keep class com.google.android.gms.ads.mediation.NativeMediationAdRequest
-keep class com.google.android.gms.ads.VideoOptions
-keep class com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener
-keep class com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener
-keep class com.google.android.gms.ads.doubleclick.AppEventListener
-keep class com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener
-keep class com.google.android.gms.ads.mediation.customevent.CustomEventNativeListener
-keep class com.google.android.gms.ads.mediation.customevent.CustomEventExtras

-keep class com.google.ads.mediation.MediationServerParameters
-keep class com.google.ads.mediation.NetworkExtras
-keep class com.google.ads.mediation.MediationInterstitialListener
-keep class com.google.ads.mediation.customevent.CustomEventServerParameters
-keep class com.google.ads.mediation.MediationBannerListener
-keep class com.google.ads.AdSize
-keep class com.google.ads.mediation.MediationAdRequest
-keep class com.google.ads.mediation.customevent.CustomEventBannerListener
-keep class com.google.ads.mediation.customevent.CustomEventInterstitialListener

-keep class com.google.firebase.FirebaseApp
-keep class com.google.firebase.database.connection.idl.zzah
-keep class com.google.firebase.database.connection.idl.zzq
-keep class com.google.firebase.database.connection.idl.zzw
-keep class com.google.firebase.database.connection.idl.zzk

-dontnote com.google.protobuf.zzc
-dontnote com.google.protobuf.zzd
-dontnote com.google.protobuf.zze
-dontnote com.google.android.gms.internal.q
-dontnote com.google.android.gms.internal.zzcem

-keep class com.google.android.gms.cast.framework.OptionsProvider

## Google Play Service End

## Support  egin
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }

-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}

-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

## Support End