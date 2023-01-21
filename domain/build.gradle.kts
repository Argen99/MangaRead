plugins {
    id(Plugins.Java.javaLibrary)
    id(Plugins.Kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
dependencies {
    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Javax.inject)
    implementation("androidx.paging:paging-common-ktx:3.1.1")

}