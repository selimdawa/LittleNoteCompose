# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\...\AppData\Local\Android\Sdk/tools/proguard/proguard-android-optimize.txt
# You can edit the include line and add additional flags to this file.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Keep generic signatures and line numbers for stack traces
-keepattributes Signature, InnerClasses, EnclosingMethod, *Annotation*, SourceFile, LineNumberTable

# Kotlin Coroutines
-dontwarn kotlinx.coroutines.**

# Room Database
-keepclassmembers class * extends androidx.room.RoomDatabase {
    <init>();
}

# Hilt / Dagger
-keepclassmembers class * {
    @javax.inject.Inject <init>(...);
}
