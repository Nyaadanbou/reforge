package cc.mewcraft.reforge.gui;

import cc.mewcraft.reforge.gui.bind.ChatLanguageDirectory;
import cc.mewcraft.reforge.gui.bind.GuiLanguageDirectory;
import cc.mewcraft.reforge.gui.bind.ReforgeMenuConfig;
import cc.mewcraft.reforge.gui.manager.ReforgeConfigDirectory;
import cc.mewcraft.spatula.message.Translations;
import com.google.inject.AbstractModule;
import me.lucko.helper.plugin.HelperPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.slf4j.Logger;

import java.nio.file.Path;

import javax.inject.Singleton;

public class ReforgeGuiBindings extends AbstractModule {
    private final HelperPlugin plugin;

    public ReforgeGuiBindings(final HelperPlugin plugin) {
        this.plugin = plugin;
    }

    @Override protected void configure() {
        // Should be safe to extract files in here
        // as we have got the java plugin instance
        plugin.saveResourceRecursively("lang");
        plugin.saveResourceRecursively("item");
        plugin.saveResource("gui-config.yml");

        // Then configure binds
        bind(Plugin.class).toInstance(plugin);
        bind(HelperPlugin.class).toInstance(plugin);

        bind(Logger.class).toInstance(plugin.getSLF4JLogger());

        bind(Translations.class)
                .toProvider(() -> new Translations(plugin, "lang/message"))
                .in(Singleton.class);

        bind(Path.class)
                .annotatedWith(ReforgeConfigDirectory.class)
                .toProvider(() -> plugin.getDataFolder().toPath().resolve("item"))
                .in(Singleton.class);

        bind(Path.class)
                .annotatedWith(GuiLanguageDirectory.class)
                .toProvider(() -> plugin.getDataFolder().toPath().resolve("lang").resolve("modding"))
                .in(Singleton.class);

        bind(Path.class)
                .annotatedWith(ChatLanguageDirectory.class)
                .toProvider(() -> plugin.getDataFolder().toPath().resolve("lang").resolve("message"))
                .in(Singleton.class);

        bind(FileConfiguration.class)
                .annotatedWith(ReforgeMenuConfig.class)
                .toProvider(() -> {
                    Path path = plugin.getDataFolder().toPath().resolve("gui-config.yml");
                    return YamlConfiguration.loadConfiguration(path.toFile());
                }).in(Singleton.class);
    }
}
