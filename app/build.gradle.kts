import java.util.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

layout.buildDirectory.set(File(projectDir, "build_v4"))


android {
    namespace = "com.neubofy.reality"
    compileSdk = 36  // Android 16 for future compatibility

    defaultConfig {
        applicationId = "com.neubofy.reality"
        minSdk = 26
        targetSdk = 36  // Android 16
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        
        setProperty("archivesBaseName", "Ebb-v${versionName}")


        val buildTimestamp = System.currentTimeMillis()
        buildConfigField("Long", "BUILD_TIMESTAMP", "${buildTimestamp}L")
    }
    
    packaging {
        resources {
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/LICENSE"
            excludes += "META-INF/LICENSE.txt"
            excludes += "META-INF/license.txt"
            excludes += "META-INF/NOTICE"
            excludes += "META-INF/NOTICE.txt"
            excludes += "META-INF/notice.txt"
            excludes += "META-INF/ASL2.0"
            excludes += "META-INF/*.kotlin_module" 
        }
    }

    splits {
        abi {
            isEnable = false

        }
    }


    buildTypes {
        release {
            // R8/ProGuard enabled
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        
        debug {
            // No suffix - same package for debug and release
            versionNameSuffix = "-DEBUG"
        }
    }
    
    applicationVariants.all {
        outputs.all {
            val output = this as? com.android.build.gradle.internal.api.BaseVariantOutputImpl
            val timestamp = defaultConfig.buildConfigFields["BUILD_TIMESTAMP"]?.value?.replace("L", "") ?: ""
            output?.outputFileName = "Ebb-v${versionName}-${timestamp}-${buildType.name}.apk"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    
    lint {
        checkReleaseBuilds = true
        abortOnError = false
    }
}

dependencies {
    implementation("androidx.security:security-crypto:1.1.0-alpha06")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation(libs.androidx.core.ktx)
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.gson)
    implementation("com.getkeepsafe.taptargetview:taptargetview:1.13.3")
    implementation("com.google.android.gms:play-services-location:21.2.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.mpandroidchart)
    implementation(libs.timerangepicker)
    
    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // WorkManager
    implementation(libs.work.runtime.ktx)
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.airbnb.android:lottie:6.0.0")
}
