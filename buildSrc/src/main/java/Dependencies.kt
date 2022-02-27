/**
 * To define plugins
 */
object ClassPaths {
    const val gradle = "com.android.tools.build:gradle:${Versions.Plugins.gradlePlugin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Plugins.kotlin}"
    const val daggerHiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.Plugins.hilt}"
}

/**
 * To define dependencies
 */
object BuildDependencies {
    //Kotlin
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.Plugins.kotlin}"

    //AndroidX
    const val androidKTX = "androidx.core:core-ktx:${Versions.Dependencies.androidKTX}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.Dependencies.appCompat}"
    const val materialDesign =
        "com.google.android.material:material:${Versions.Dependencies.material}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.Dependencies.constraintLayout}"
    const val activityCompose =
        "androidx.activity:activity-compose:${Versions.Dependencies.activityCompose}"
    const val pagination = "androidx.paging:paging-compose:${Versions.Dependencies.pagination}"
    const val legacySupport =
        "androidx.legacy:legacy-support-v4:${Versions.Dependencies.legacySupport}"

    //Compose
    const val compose = "androidx.compose.ui:ui:${Versions.Dependencies.composeVersion}"
    const val composeFoundation =  "androidx.compose.foundation:foundation:${Versions.Dependencies.composeVersion}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.Dependencies.composeVersion}"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.Dependencies.composeVersion}"
    const val composeUITooling = "androidx.compose.ui:ui-tooling:${Versions.Dependencies.composeVersion}"
    const val composeUITest = "androidx.compose.ui:ui-test-junit4:${Versions.Dependencies.composeVersion}"
    const val composeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.Dependencies.composeVersion}"
    const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.Dependencies.composeNavigation}"

    /*Other*/
    const val lottieCompose = "com.airbnb.android:lottie-compose:${Versions.Dependencies.lottie}"

    /*Retrofit*/
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Dependencies.retrofit}"
    const val retrofitGson =
        "com.squareup.retrofit2:converter-gson:${Versions.Dependencies.retrofit}"

    /*OK HTTP*/
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.Dependencies.okhttp}"
    const val okhttpInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.Dependencies.okhttp}"

    //Coroutines
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Dependencies.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Dependencies.coroutines}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Dependencies.coroutines}"

    //Lifecycle
    const val lifeCycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Dependencies.lifecycle}"
    const val lifeCycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.Dependencies.lifecycleExtension}"
    const val lifeCycleLivedata =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Dependencies.lifecycle}"
    const val lifeCycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Dependencies.lifecycle}"
    const val lifeCycleCommon =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.Dependencies.lifecycle}"
    const val lifeCycleReactiveStreams =
        "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.Dependencies.lifecycle}"

    /*Hilt*/
    const val hilt = "com.google.dagger:hilt-android:${Versions.Dependencies.hiltVersion}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.Dependencies.hiltNavigationComposeVersion}"
    const val hiltCompiler =
        "com.google.dagger:hilt-android-compiler:${Versions.Dependencies.hiltVersion}"
    const val hiltCompilerX = "androidx.hilt:hilt-compiler:${Versions.Dependencies.hiltCompilerX}"

    /*Room*/
    const val room = "androidx.room:room-runtime:${Versions.Dependencies.room}"
    const val roomAnnotationProcessor =  "androidx.room:room-compiler:${Versions.Dependencies.room}"
    const val roomKapt =  "androidx.room:room-compiler:${Versions.Dependencies.room}"
    const val roomKtx =  "androidx.room:room-ktx:${Versions.Dependencies.room}"

    /*Coil*/
    const val coil = "io.coil-kt:coil-compose:${Versions.Dependencies.coil}"

    /*Debug Tools*/
    const val chucker = "com.github.chuckerteam.chucker:library:${Versions.Dependencies.chucker}"
    const val chuckerNoOp = "com.github.chuckerteam.chucker:library-no-op:${Versions.Dependencies.chucker}"

    //Test
    const val junit = "junit:junit:${Versions.Dependencies.jUnit}"
    const val androidTestJunit = "androidx.test.ext:junit:${Versions.Dependencies.androidTestJunit}"
    const val androidTestEspressoCore =
        "androidx.test.espresso:espresso-core:${Versions.Dependencies.androidTestEspressoCore}"


}