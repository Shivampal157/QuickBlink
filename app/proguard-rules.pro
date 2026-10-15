# QuickBlink ProGuard Rules
-keepattributes *Annotation*
-keepclassmembers class * {
    @androidx.room.* *;
}
