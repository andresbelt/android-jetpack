plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.data"
    compileSdk = 33
}

dependencies {

    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.paging)

    kapt(libs.androidx.room.compiler)
    implementation(libs.retrofit.module)
    implementation(libs.retrofit.serialization.converter)
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.lifecycle.compiler)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.paging.common.ktx)


    implementation(libs.logging.interceptor)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.junit)
    implementation(libs.coroutine.test)
    testImplementation(libs.mockk)
    implementation(project(":commons"))

    /*

    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

    //Coroutine
    androidTestImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:$versions.kotlinx_coroutine_test_version"
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$versions.kotlin_version"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$versions.kotlin_coroutine_version"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$versions.kotlin_coroutine_android_version"

    //Hilt
    implementation "com.google.dagger:hilt-android:$hilt_version"
    kapt "com.google.dagger:hilt-android-compiler:$hilt_version"

    //Retrofit
    implementation "com.squareup.retrofit:retrofit:$versions.retrofit_1_version"
    implementation "com.squareup.okhttp3:okhttp:$versions.okhttp3_version"
    implementation "com.squareup.okhttp3:okhttp-urlconnection:$versions.okhttp3_urlconnection_version"
    implementation "com.squareup.okhttp3:logging-interceptor:$versions.okhttp3_logging_version"
    implementation "com.google.code.gson:gson:$versions.google_gson_version"
    implementation 'com.squareup.retrofit2:converter-scalars:2.1.0'
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

    // Room
    implementation "androidx.room:room-runtime:2.4.3"
    kapt "androidx.room:room-compiler:2.4.3"
    implementation "androidx.room:room-ktx:2.4.3"
    implementation "androidx.room:room-paging:2.4.3"

    implementation 'androidx.paging:paging-common-ktx:3.1.1'

    // KotlinX Serialization
    implementation "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0"
    //MockK
    testImplementation "io.mockk:mockk:1.10.0"
    testImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1"
    implementation project(':commons')
        testImplementation 'junit:junit:4.+'

     */
}
