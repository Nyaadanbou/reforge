package cc.mewcraft.reforge.internal;

import cc.mewcraft.reforge.internal.bind.ReforgeInternalConfig;
import cc.mewcraft.reforge.internal.bind.ReforgeOptionKey;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import me.lucko.helper.plugin.HelperPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.slf4j.Logger;

import java.nio.file.Path;

import javax.inject.Singleton;

public class ReforgeInternalBindings extends AbstractModule {
    private final HelperPlugin plugin;

    public ReforgeInternalBindings(final HelperPlugin plugin) {
        this.plugin = plugin;
    }

    @Override protected void configure() {
        // Should be safe to extract files in here
        plugin.saveResource("internal-config.yml");

        // Then configure binds
        bind(Plugin.class).toInstance(plugin);
        bind(HelperPlugin.class).toInstance(plugin);

        bind(Logger.class).toInstance(plugin.getSLF4JLogger());

        bind(FileConfiguration.class)
                .annotatedWith(ReforgeInternalConfig.class)
                .toProvider(() -> {
                    Path path = plugin.getDataFolder().toPath().resolve("internal-config.yml");
                    return YamlConfiguration.loadConfiguration(path.toFile());
                }).in(Singleton.class);
    }

    @Provides
    @Singleton
    @ReforgeOptionKey
    public String provideReforgeOptionKey(@ReforgeInternalConfig FileConfiguration configuration) {
        return configuration.getString("reforge_option");
    }
}
