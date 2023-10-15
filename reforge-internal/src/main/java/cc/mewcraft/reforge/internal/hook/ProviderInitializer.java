package cc.mewcraft.reforge.internal.hook;

import cc.mewcraft.reforge.internal.ReforgeProvider;
import com.google.inject.Injector;
import me.lucko.helper.plugin.HelperPlugin;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class ProviderInitializer {
    private final Injector injector;
    private final HelperPlugin plugin;
    private final Logger logger;

    @Inject
    public ProviderInitializer(
            final Injector injector,
            final HelperPlugin plugin,
            final Logger logger
    ) {
        this.injector = injector;
        this.plugin = plugin;
        this.logger = logger;
    }

    /**
     * Initializes {@link ReforgeProvider} by the specific provider key.
     * <p>
     * If the specific provider cannot be initialized, it will fall back to {@link MockReforge}.
     *
     * @param provider the provider key
     */
    public void initialize(String provider) {
        ProviderEnum match = ProviderEnum.match(provider);

        if (plugin.isPluginPresent(match.plugin) || (match == ProviderEnum.MOCK /* Allow to use "Mock" as a plugin name */)) {
            ReforgeProvider.register(injector.getInstance(match.clazz));
            logger.info("Registered reforge provider: {}", match.clazz.getSimpleName());
        } else {
            logger.warn("Specific reforge provider cannot be registered: {}", provider);
            ReforgeProvider.register(injector.getInstance(MockReforge.class));
            logger.warn("Fall back to default provider: {}", MockReforge.class.getSimpleName());
        }
    }
}
