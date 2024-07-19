# 保留所有 public 类和方法
-keep public class * {
    public *;
}
-keep class com.teltlk.opensdk.model.** { *; }

-keepclassmembers class com.teltlk.opensdk.openapi.TKApi {
    public *;
}

# 混淆方法体的实现
-dontobfuscate