import net.minecrell.pluginyml.paper.PaperPluginDescription

plugins {
    id("cc.mewcraft.deploy-conventions")
    alias(libs.plugins.pluginyml.paper)
}

project.ext.set("name", "Reforge")

group = "cc.mewcraft.reforge.plugin"
version = "1.0.1"
description = "The Bukkit plugin of our item reforge mechanism"

dependencies {
    // server
    compileOnly(libs.server.paper)

    // helper
    compileOnly(libs.helper)

    // standalone plugins
    compileOnly(project(":spatula:bukkit:item:api"))

    // internal
    implementation(project(":reforge:reforge-gui"))
    implementation(project(":reforge:reforge-internal"))
    implementation(project(":spatula:guice"))
    implementation(project(":spatula:bukkit:gui"))
    implementation(project(":spatula:bukkit:command"))
    implementation(project(":spatula:bukkit:message"))
}

paper {
    main = "cc.mewcraft.reforge.plugin.ReforgePlugin"
    name = project.ext.get("name") as String
    version = "${project.version}"
    description = project.description
    apiVersion = "1.19"
    authors = listOf("Nailm")
    serverDependencies {
        register("helper") {
            required = true
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
        }

        // custom item plugins
        register("SpatulaItems") {
            required = true
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
        }
        register("MythicLib") {
            required = false
            load = PaperPluginDescription.RelativeLoadOrder.OMIT
        }
        register("MMOItems") {
            required = false
            load = PaperPluginDescription.RelativeLoadOrder.OMIT
        }
        register("ItemsAdder") {
            required = false
            load = PaperPluginDescription.RelativeLoadOrder.OMIT
        }
        register("Nova") {
            required = false
            load = PaperPluginDescription.RelativeLoadOrder.OMIT
        }

        // reforge ingredient plugins
        register("GemsEconomy") {
            required = true // TODO make it optional
            load = PaperPluginDescription.RelativeLoadOrder.OMIT
        }
    }
}
