/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * Learn more about Gradle by exploring our samples at https://docs.gradle.org/7.4.2/samples
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    id("java")
    //id("org.jetbrains.kotlin.jvm") version "1.7.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.freefair.lombok") version "6.5.0.3"

}

group = "homeward.plugin"
version = "1.0-SNAPSHOT"
var targetJavaVersion = 17

repositories {
    mavenCentral()
    maven {
        name = "spigotmc-repo"
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }

    maven {
        name = "sonatype"
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }

    maven {
        name = "placeholderapi"
        url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }

    maven{
        name = "protocolLib"
        url = uri("https://repo.dmulloy2.net/repository/public/")
    }

    maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots/") {
        name = "sonatype-oss-snapshots"
    }
}

dependencies {

    implementation("net.kyori:adventure-api:4.11.0")
    compileOnly("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT")
    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.8.2")
    testImplementation("org.junit.jupiter", "junit-jupiter-engine", "5.8.2")
    ///fileTree(dir: 'libs/compile', include: ['*.jar'])
    compileOnly(fileTree("libs/compile"))
    compileOnly("me.clip:placeholderapi:2.11.2")
    implementation("net.kyori:adventure-text-minimessage:4.11.0")
    compileOnly("com.comphenix.protocol:ProtocolLib:4.8.0")
    implementation("net.kyori:adventure-text-serializer-gson:4.11.0")


}


java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
    if (JavaVersion.current() < javaVersion) {
        java.toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
    }
}

tasks.withType(JavaCompile::class).configureEach {
    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible) {
        options.release.set(targetJavaVersion)
    }
}

tasks {


    compileJava {
        options.encoding = "UTF-8"
    }


}


tasks.register("configured") {
    println("This is also executed during the configuration phase, because :configured is used in the build.")
}

tasks.processResources {
    val props = LinkedHashMap<String, Any>()
    props["version"] = version
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}


tasks.register<Copy>("copyJar") {
    mustRunAfter("shadowJar")
    val projectLibJarName = "${getRootProject().name}-${version}.jar"
    val normalJarPath = File(project.buildDir, "libs/${projectLibJarName}")
    from(normalJarPath)
    into(findProperty("server.plugin.folder")!!)

}

tasks.shadowJar {


    minimize()
    archiveBaseName.set(rootProject.name)
    archiveClassifier.set("")
    archiveVersion.set(project.version.toString())
    archiveExtension.set("jar")
}




tasks.register("compileAll") {
    dependsOn("shadowJar")
    dependsOn("copyJar")
}





