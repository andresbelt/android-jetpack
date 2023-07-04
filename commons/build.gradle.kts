plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.commons"
    compileSdk = 33
}

dependencies {
    implementation(libs.androidx.appcompat)
}
