# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int d(...);
    public static int i(...);
}

# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform
-dontwarn okhttp3.internal.platform.*
-dontwarn okhttp3.internal.platform.ConscryptPlatform
-dontwarn com.google.errorprone.annotations.Immutable
# Preserve all classes and their members in the MotionLayout package
-keep class androidx.constraintlayout.motion.widget.** {
    *;
}


# Preserve all attributes used by MotionLayout
-keepclassmembers class **.R$* {
    public static final int motion_*;
}

# Preserve all custom attributes used by MotionLayout
-keepattributes *MotionTarget*

-keep class com.airlibs.domain.models.responses.** { *; }
-keep class com.airlibs.domain.models.requests.** { *; }
-keep class com.airlibs.domain.models.** { *; }
-keep class com.airlibs.data.** { *; }
-keep class com.airlibs.app.features.dashboard.models.** { *; }
-keep class com.airlibs.app.features.login.models.** { *; }



 -keepattributes Signature
 -keep class com.google.gson.reflect.TypeToken { *; }
 -keep class * extends com.google.gson.reflect.TypeToken

 # For enumeration classes
 -keepclassmembers enum * {
     <fields>;
     public static **[] values();
     public static **[] entries;
     public static ** valueOf(java.lang.String);
 }

 -keep class * implements android.os.Parcelable {
   public static final android.os.Parcelable$Creator *;
 }
 -keepnames class * extends android.os.Parcelable

 # debugging stack traces.
 -keepattributes SourceFile,LineNumberTable
 -renamesourcefileattribute SourceFile

 -dontwarn java.lang.invoke.StringConcatFactory

 -keep class com.airlibs.data.di.CoroutineScopeModule_ProvideApplicationLevelCoroutineScopeFactory
 -keep class com.airlibs.data.di.DataModules_ProvidesMedicationDaoFactory
 -keep class com.airlibs.data.di.DataModules_ProvidesUsrDaoFactory
 -keep class com.airlibs.data.di.DataStoreModule_ProvidesDataStorePreferencesFactory
 -keep class com.airlibs.data.di.DatabaseModule
 -keep class com.airlibs.data.di.DatabaseModule_ProvideAppDatabaseFactory
 -keep class com.airlibs.data.di.DispatchersModule_ProvidesIODispatcherFactory
 -keep class com.airlibs.data.di.NetworkModule_ApiServiceBuilderFactory
 -keep class com.airlibs.data.di.NetworkModule_ProvideConverterFactoryFactory
 -keep class com.airlibs.data.di.NetworkModule_ProvideHttpClientFactory
 -keep class com.airlibs.data.di.NetworkModule_ProvideRetrofitFactory
 -keep class com.airlibs.data.di.UseCaseModule_ProvidesGetCurrentUserUseCaseFactory
 -keep class com.airlibs.data.di.UseCaseModule_ProvidesGetMedicineByIdUseCaseFactory
 -keep class com.airlibs.data.di.UseCaseModule_ProvidesGetMedicinesUseCaseFactory
 -keep class com.airlibs.data.di.UseCaseModule_ProvidesSaveUserUseCaseFactory
 -keep class com.airlibs.data.repositories.auth.AuthRepositoryImpl
 -keep class com.airlibs.data.repositories.medicines.MedicineRepositoryImpl
 -keep class com.airlibs.data.sources.local.LocalDataSource
 -keep class com.airlibs.data.sources.local.LocalDataSourceImpl
 -keep class com.airlibs.data.sources.local.dataStore.AirLibsDataStore
 -keep class com.airlibs.data.sources.remote.AirLibsApiService
 -keep class com.airlibs.data.sources.remote.RemoteDataSource
 -keep class com.airlibs.data.sources.remote.RemoteDataSourceImpl

 -dontwarn com.airlibs.data.di.CoroutineScopeModule_ProvideApplicationLevelCoroutineScopeFactory
 -dontwarn com.airlibs.data.di.DataModules_ProvidesMedicationDaoFactory
 -dontwarn com.airlibs.data.di.DataModules_ProvidesUsrDaoFactory
 -dontwarn com.airlibs.data.di.DataStoreModule_ProvidesDataStorePreferencesFactory
 -dontwarn com.airlibs.data.di.DatabaseModule
 -dontwarn com.airlibs.data.di.DatabaseModule_ProvideAppDatabaseFactory
 -dontwarn com.airlibs.data.di.DispatchersModule_ProvidesIODispatcherFactory
 -dontwarn com.airlibs.data.di.NetworkModule_ApiServiceBuilderFactory
 -dontwarn com.airlibs.data.di.NetworkModule_ProvideConverterFactoryFactory
 -dontwarn com.airlibs.data.di.NetworkModule_ProvideHttpClientFactory
 -dontwarn com.airlibs.data.di.NetworkModule_ProvideRetrofitFactory
 -dontwarn com.airlibs.data.di.UseCaseModule_ProvidesGetCurrentUserUseCaseFactory
 -dontwarn com.airlibs.data.di.UseCaseModule_ProvidesGetMedicineByIdUseCaseFactory
 -dontwarn com.airlibs.data.di.UseCaseModule_ProvidesGetMedicinesUseCaseFactory
 -dontwarn com.airlibs.data.di.UseCaseModule_ProvidesSaveUserUseCaseFactory
 -dontwarn com.airlibs.data.repositories.auth.AuthRepositoryImpl
 -dontwarn com.airlibs.data.repositories.medicines.MedicineRepositoryImpl
 -dontwarn com.airlibs.data.sources.local.LocalDataSource
 -dontwarn com.airlibs.data.sources.local.LocalDataSourceImpl
 -dontwarn com.airlibs.data.sources.local.dataStore.AirLibsDataStore
 -dontwarn com.airlibs.data.sources.remote.AirLibsApiService
 -dontwarn com.airlibs.data.sources.remote.RemoteDataSource
 -dontwarn com.airlibs.data.sources.remote.RemoteDataSourceImpl
