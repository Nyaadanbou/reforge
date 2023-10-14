group = "cc.mewcraft.reforge.gui"
version = "1.0.1"
description = "Provides GUIs for the item reforge mechanism"

dependencies {
    // server
    compileOnly(libs.server.paper)

    // helper
    compileOnly(libs.helper)

    // standalone plugins
    compileOnly(project(":economy:api"))
    compileOnly(project(":reforge:reforge-internal"))

    // internal
    compileOnly(project(":spatula:guice"))
    compileOnly(project(":spatula:bukkit:gui"))
    compileOnly(project(":spatula:bukkit:command"))
    compileOnly(project(":spatula:bukkit:message"))
    compileOnly(project(":spatula:bukkit:item:api"))
}
