plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}


android {
    namespace = "com.example.domain"
    compileSdk = 33
}

dependencies {
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.paging.common.ktx)
    testImplementation(libs.junit)
    testImplementation(libs.coroutine.test)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.androidx.test.runner)
    testImplementation(libs.mockk)

    implementation(project(":data"))
    implementation(project(":commons"))
    /*
    implementation 'androidx.core:core-ktx:1.6.0'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.paging:paging-common-ktx:3.1.1'
    testImplementation 'junit:junit:4.+'
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
    //MockK
    testImplementation "io.mockk:mockk:1.10.0"
    testImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1"

    implementation project(':data')
    implementation project(':commons')

     */
}
