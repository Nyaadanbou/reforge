package cc.mewcraft.reforge.internal;

import cc.mewcraft.reforge.internal.bind.ReforgeInternalConfig;
import cc.mewcraft.reforge.internal.hook.ProviderInitializer;
import com.google.inject.Injector;
import me.lucko.helper.terminable.TerminableConsumer;
import me.lucko.helper.terminable.module.TerminableModule;
import org.bukkit.configuration.file.FileConfiguration;

import javax.inject.Inject;

import org.jetbrains.annotations.NotNull;

public class ReforgeInternalModule implements TerminableModule {
    private final Injector injector;
    private final FileConfiguration config;

    @Inject
    public ReforgeInternalModule(
            final Injector injector,
            @ReforgeInternalConfig final FileConfiguration config
    ) {
        this.injector = injector;
        this.config = config;
    }

    @Override public void setup(@NotNull final TerminableConsumer consumer) {
        // Register specific reforge provider
        String specificProvider = config.getString("reforge_provider");
        injector.getInstance(ProviderInitializer.class).initialize(specificProvider);
    }
}
