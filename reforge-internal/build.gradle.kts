group = "cc.mewcraft.reforge.internal"
version = "1.0.0"
description = "Adds item reforge mechanism to various custom items that can be \"reforged\""

dependencies {
    // server
    compileOnly(libs.server.paper)

    // helper
    compileOnly(libs.helper)

    // standalone plugins
    compileOnly(libs.mmoitems)
    compileOnly(libs.mythiclib)
    compileOnly(libs.mythicmobs)

    // internal
    compileOnly(project(":spatula:guice"))
    compileOnly(project(":spatula:bukkit:command"))
}
