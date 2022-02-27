import org.gradle.api.JavaVersion

object ConfigData {
    //Default
    const val compileSdk = 31
    const val applicationId = "com.iamkurtgoz.ecommerceandroid"
    const val minSdk = 24
    const val targetSdk = 31
    const val useSupportLibrary = true
    //Proguard
    const val minifyEnabledDebug = false
    const val minifyEnabledRelease = true
}