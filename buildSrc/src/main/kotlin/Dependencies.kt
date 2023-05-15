object Dependencies {
    val coreAndroidX by lazy {"androidx.core:core-ktx:${Versions.coreAndroidX}"}
    val lifeCycleRuntime by lazy {"androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycleRuntime}"}
    val activityCompose by lazy {"androidx.activity:activity-compose:${Versions.activityCompose}"}
    val composeNav by lazy {"androidx.navigation:navigation-compose:${Versions.composeNav}"}
    val composeBom by lazy {"androidx.compose:compose-bom:${Versions.composeBom}"}
    val composeUi by lazy {"androidx.compose.ui:${Versions.composeUi}"}
    val composeGraphics by lazy {"androidx.compose.ui:ui-graphics"}
    val composeUiToolingPreview by lazy {"androidx.compose.ui:ui-tooling-preview"}
    val composeMaterial3 by lazy {"androidx.compose.material3:material3"}


    val junit by lazy {"junit:junit:${Versions.junit}"}
    val androidTestJUnit by lazy {"androidx.test.ext:junit:${Versions.androidTestJUnit}"}
    val androidTestEspresso by lazy {"androidx.test.espresso:espresso-core:${Versions.androidTestEspresso}"}
    val androidTestComposeBom by lazy {"androidx.compose:compose-bom:${Versions.androidTestComposeBom}"}
    val androidTestComposeUi by lazy {"androidx.compose.ui:${Versions.androidTestComposeUi}"}
    val debugComposeUiTooling by lazy {"androidx.compose.ui:${Versions.debugComposeUiTooling}"}
    val debugComposeUiTestManifest by lazy {"androidx.compose.ui:${Versions.debugComposeUiTestManifest}"}

    // Hilt
    val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltAndroidCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }
    val hiltNavigationCompose by lazy {"androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationComposeVersion}"}

    // Coroutine
    val workRuntime by lazy { "androidx.work:work-runtime-ktx:${Versions.workRuntime}" }

    // Room
    val roomRuntine by lazy {"androidx.room:room-runtime:${Versions.room}"}
    val roomKtx by lazy {"androidx.room:room-ktx:${Versions.room}"}
    val roomCompiler by lazy {"androidx.room:room-compiler:${Versions.room}"}

    // GSon
    val gson by lazy {"com.google.code.gson:gson:${Versions.gson}"}

    // ktor
    val ktor by lazy {"io.ktor:ktor-client-android:${Versions.ktor}"}

}