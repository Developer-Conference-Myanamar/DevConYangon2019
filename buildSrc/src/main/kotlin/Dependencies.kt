/**
 * Created by Vincent on 2019-05-22
 */
object BuildConfig {
  const val compileSdk = 28
  const val minSdk = 21
  const val targetSdk = 28

  private const val versionMajor = 1
  private const val versionMinor = 1
  private const val versionPatch = 1
  private const val versionBuild = 0

  const val versionName =
    "$versionMajor.$versionMinor.$versionPatch"
  const val versionCode =
    versionMajor * 1000000 + versionMinor * 10000 + versionPatch * 100 + versionBuild

}

object CommonLibs {
  const val android_gradle_plugin = "com.android.tools.build:gradle:3.6.0-rc01"

  const val dexter = "com.karumi:dexter:5.0.0"

  const val phrase = "com.squareup.phrase:phrase:1.1.0"
  const val sonar = "com.facebook.sonar:sonar:0.0.1"
  const val timber = "com.jakewharton.timber:timber:4.7.1"
  const val junit = "junit:junit:4.12"
  const val javaxInject = "javax.inject:javax.inject:1"
  const val rabbkt = "com.aungkyawpaing.rabbkt:rabbkt:1.0.1"

  const val easy_image = "com.github.jkwiecien:EasyImage:1.3.1"
  const val android_crop = "com.soundcloud.android:android-crop:1.0.1@aar"
  const val ucrop = "com.github.yalantis:ucrop:2.2.2"
  const val fastscroll = "com.simplecityapps:recyclerview-fastscroll:2.0.0"

  const val colorpicker = "com.github.QuadFlask:colorpicker:0.0.13"
  const val circle_image_view = "de.hdodenhof:circleimageview:3.0.0"
  const val shape_of_views = "com.github.florent37:shapeofview:1.4.7"
}

//region AndroidX
object AndroidXAnnotations {
  const val annotations = "androidx.annotation:annotation:1.1.0"
}

object AndroidXAppCompat {
  const val app_compat = "androidx.appcompat:appcompat:1.1.0"
}

object AndroidXRecyclerView {
  private const val version = "1.1.0-beta04"

  const val recycler_view = "androidx.recyclerview:recyclerview:$version"
  const val selection = "androidx.recyclerview:recyclerview-selection:$version"
}

object AndroidXCardView {
  const val card_view = "androidx.cardview:cardview:1.0.0"
}

object AndroidXConstraintLayout {
  private const val version = "2.0.0-beta3"

  const val constraint_layout = "androidx.constraintlayout:constraintlayout:$version"
}

object AndroidXViewPager {
  const val view_pager = "androidx.viewpager:viewpager:1.0.0"

  const val view_pager_2 = "androidx.viewpager2:viewpager2:1.0.0-rc01"
}

object AndroidXLegacy {
  private const val version = "1.0.0"

  const val support_v4 = "androidx.legacy:legacy-support-v4:$version"
}

object AndroidXPalette {
  const val palette = "androidx.palette:palette:1.0.0"
}

object AndroidXSqlite {
  private const val version = "2.0.1"

  const val sqlite = "androidx.sqlite:sqlite:$version"
  const val sqlite_framework = "androidx.sqlite:sqlite-framework:$version"
  const val sqlite_ktx = "androidx.sqlite:sqlite-ktx:$version"
}

object AndroidArchLifeCycle {
  private const val version = "2.1.0"

  const val lifecycle = "androidx.lifecycle:lifecycle-extensions:$version"
  const val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:$version"
  const val lives = "com.snakydesign.livedataextensions:lives:1.2.1"
}

object AndroidArchCore {
  private const val version = "2.1.0"

  const val test = "androidx.arch.core:core-testing:$version"
}

object AndroidArchWork {
  private const val version = "2.2.0"

  const val work_runtime = "androidx.work:work-runtime:$version"
  const val work_runtime_ktx = "androidx.work:work-runtime-ktx:$version"
  const val work_testing = "androidx.work:work-testing:$version"

}

object AndroidArchNavigation {
  private const val version = "2.1.0"

  const val common = "androidx.navigation:navigation-common:$version"
  const val common_ktx = "androidx.navigation:navigation-common-ktx:$version"
  const val fragment = "androidx.navigation:navigation-fragment:$version"
  const val fragment_ktx = "androidx.navigation:navigation-fragment-ktx:$version"
  const val runtime = "androidx.navigation:navigation-runtime:$version"
  const val runtime_ktx = "androidx.navigation:navigation-runtime-ktx:$version"
  const val safe_args_generator = "androidx.navigation:navigation-safe-args-generator:$version"
  const val safe_args_plugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
  const val ui = "androidx.navigation:navigation-ui:$version"
  const val ui_ktx = "androidx.navigation:navigation-ui-ktx:$version"
}

object AndroidXActivity {
  private const val version = "1.0.0"

  const val activity = "androidx.activity:activity:$version"
  const val activity_ktx = "androidx.activity:activity-ktx:$version"
}

object AndroidXCore {
  private const val version = "1.1.0"

  const val core = "androidx.core:core:$version"
  const val core_ktx = "androidx.core:core-ktx:$version"
}

object AndroidXFragment {
  private const val version = "1.2.0-alpha03"

  const val fragment = "androidx.fragment:fragment:$version"
  const val fragment_ktx = "androidx.fragment:fragment-ktx:$version"
  const val fragment_testing = "androidx.fragment:fragment-testing:$version"
}

object AndroidXTest {
  private const val version = "1.2.1-alpha02"

  const val core = "androidx.test:core:$version"
  const val core_ktx = "androidx.test:core-ktx:1.1.0-beta01"
  const val runner = "androidx.test:runner:$version"
  const val rules = "androidx.test:rules:$version"
  const val roboelectric = "org.robolectric:robolectric:4.3"
}

object AndroidXTestExt {
  private const val version = "1.1.2-alpha02"

  const val junit = "androidx.test.ext:junit:$version"
  const val junit_ktx = "androidx.test.ext:junit-ktx:$version"
  const val truth = "androidx.test.ext:truth:1.3.0-alpha02"
}

object AndroidXEspresso {
  private const val version = "3.3.0-alpha02"

  const val core = "androidx.test.espresso:espresso-core:$version"
  const val contrib = "androidx.test.espresso:espresso-contrib:$version"
  const val intents = "androidx.test.espresso:espresso-intents:$version"
  const val idling_resource = "androidx.test.espresso:espresso-idling-resource:$version"
  const val idling_concurrent = "androidx.test.espresso.idling:idling-concurrent:$version"
  const val rx_idler = "com.squareup.rx.idler:rx2-idler:0.9.0"
}

object AndroidXLocalBroadcastManager {

  const val local_broadcast_manager =
    "androidx.localbroadcastmanager:localbroadcastmanager:1.1.0-alpha01"

}

//endregion

object AssistedInject {

  private const val version = "0.5.0"

  const val annotations_dagger = "com.squareup.inject:assisted-inject-annotations-dagger2:$version"
  const val processor_dagger = "com.squareup.inject:assisted-inject-processor-dagger2:$version"
}

object Material {
  const val material = "com.google.android.material:material:1.1.0-beta01"
}

object Coil {
  const val coil = "io.coil-kt:coil:0.8.0"
}

object Dagger {
  private const val version = "2.25.2"

  const val core = "com.google.dagger:dagger:$version"
  const val compiler = "com.google.dagger:dagger-compiler:$version"
  const val android_core = "com.google.dagger:dagger-android:$version"
  const val android_support = "com.google.dagger:dagger-android-support:$version"
  const val android_processor = "com.google.dagger:dagger-android-processor:$version"
}

object Detek {
  private const val version ="1.1.1"

  const val plugin ="io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$version"

}

object EverNoteJob {
  private const val version = "1.2.6"

  const val core = "com.evernote:android-job:$version"
}

object Facebook {

  const val account_kit = "com.facebook.android:account-kit-sdk:5.4.0"
}

object Firebase {
  private const val version = "17.2.0"

  const val auth = "com.google.firebase:firebase-auth:$version"
  const val core = "com.google.firebase:firebase-core:$version"
  const val db = "com.google.firebase:firebase-database:$version"
  const val messaging = "com.google.firebase:firebase-messaging:17.3.4"
  const val crashlytics = "com.crashlytics.sdk.android:crashlytics:2.10.1"
  const val fabric_plugin = "io.fabric.tools:gradle:1.31.1"
}

object Flipper {
  private const val version = "0.27.0"

  const val flipper = "com.facebook.flipper:flipper:$version"
  const val soloader = "com.facebook.soloader:soloader:0.5.1"
  const val network_plugin = "com.facebook.flipper:flipper-network-plugin:$version"
  const val image_plugin = "com.facebook.flipper:flipper-fresco-plugin:$version"

  const val filpper_no_op = "com.facebook.flipper:flipper-noop:$version"
}

object FragmentTestRule {
  private const val version = "1.1.0"

  const val android = "com.21buttons:fragment-test-rule:$version"
  const val extra = "com.21buttons:fragment-test-rule-extras:$version"
}

object Glide {
  private const val version = "4.9.0"

  const val runtime = "com.github.bumptech.glide:glide:$version"
  const val compiler = "com.github.bumptech.glide:compiler:$version"
  const val transformations = "jp.wasabeef:glide-transformations:4.0.1"
}

object GoogleService {

  const val auth = "com.google.android.gms:play-services-auth:16.0.1"
  const val ads = "com.google.android.gms:play-services-ads:17.1.2"
  const val consent = "com.google.android.ads.consent:consent-library:1.0.6"
  const val gms = "com.google.gms:google-services:4.3.2"
  const val map = "com.google.android.gms:play-services-maps:16.1.0"
  const val location = "com.google.android.gms:play-services-location:16.0.0"
}

object Kotlin {
  private const val version = "1.3.61"

  const val stdblib_jdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
  const val gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
}

object KotlinCoroutine {
  private const val version = "1.3.2"

  const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
  const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
  const val adapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
}

object Mockito {
  private const val version = "3.0.0"

  const val core = "org.mockito:mockito-core:$version"
  const val android = "org.mockito:mockito-android:$version"
  const val inline = "org.mockito:mockito-inline:$version"
  const val kotlin = "com.nhaarman:mockito-kotlin:1.5.0"
}

object LeakCanary {
  const val android = "com.squareup.leakcanary:leakcanary-android:2.0-beta-3"
}

object Moshi {
  private const val version = "1.9.1"

  const val core = "com.squareup.moshi:moshi:$version"
  const val code_gen = "com.squareup.moshi:moshi-kotlin-codegen:$version"

  const val geoshi = "com.aungkyawpaing.geoshi:geoshi-adapter:0.0.2"
}

object MPAndroidChart {
  const val base = "com.github.PhilJay:MPAndroidChart:v3.1.0"
}

object OkHttp {
  private const val version = "4.2.2"

  const val client = "com.squareup.okhttp3:okhttp:$version"
  const val logger = "com.squareup.okhttp3:logging-interceptor:$version"
  const val mock_web_server = "com.squareup.okhttp3:mockwebserver:$version"
}

object Retrofit {
  private const val version = "2.6.2"

  const val core = "com.squareup.retrofit2:retrofit:$version"
  const val moshi_converter = "com.squareup.retrofit2:converter-moshi:$version"
}

object SqlDelight {
  private const val version = "1.2.1"

  const val gradle_plugin = "com.squareup.sqldelight:gradle-plugin:$version"
  const val android_driver = "com.squareup.sqldelight:android-driver:$version"
  const val runtime = "com.squareup.sqldelight:runtime-common:$version"
}

object Stetho {
  private const val version = "1.5.1"

  const val core = "com.facebook.stetho:stetho:$version"
  const val okhttp3 = "com.facebook.stetho:stetho-okhttp3:$version"
}

object Shimmer {
  const val reycler_view = "com.github.sharish:ShimmerRecyclerView:v1.3"
}

object ThreeTenBp {
  private const val version = "1.4.0"

  const val core = "org.threeten:threetenbp:$version"
  const val no_tz_db = "org.threeten:threetenbp:$version:no-tzdb"
  const val android = "com.jakewharton.threetenabp:threetenabp:1.2.1"
}