plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.4.0"
}

rootProject.name = "creative-parent"

includePrefixed("api")
includePrefixed("server")
includePrefixed("serializer-minecraft")

includePrefixed("central:api")
includePrefixed("central:common")
includePrefixed("central:bukkit")

fun includePrefixed(name: String) {
    val kebabName = name.replace(':', '-')
    val path = name.replace(':', '/')

    include("creative-$kebabName")
    project(":creative-$kebabName").projectDir = file(path)
}