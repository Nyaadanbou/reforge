package cc.mewcraft.reforge.plugin;

import cc.mewcraft.reforge.gui.menu.ReforgeMenu;
import cc.mewcraft.spatula.command.CommandRegistry;
import cloud.commandframework.bukkit.parsers.PlayerArgument;
import com.google.inject.Injector;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ReforgeCommands {
    private static final String ROOT_COMMAND = "reforge";

    private final CommandRegistry registry;
    private final Injector injector;
    private final Plugin plugin;

    @Inject
    public ReforgeCommands(
            final CommandRegistry registry,
            final Injector injector,
            final Plugin plugin
    ) {
        this.registry = registry;
        this.injector = injector;
        this.plugin = plugin;
    }

    public void registerCommands() {
        // Prepare commands
        registry.addCommand(registry
                .commandBuilder(ROOT_COMMAND)
                .literal("open")
                .argument(PlayerArgument.optional("target"))
                .permission("reforge.command.open")
                .handler(ctx -> {
                    // TODO reuse ReforgeMenu instance for each player
                    ReforgeMenu menu = injector.getInstance(ReforgeMenu.class);
                    if (ctx.contains("target")) {
                        Player target = ctx.get("target");
                        menu.open(target);
                    } else if (ctx.getSender() instanceof Player player) {
                        menu.open(player);
                    }
                }).build());
        registry.addCommand(registry
                .commandBuilder(ROOT_COMMAND)
                .literal("reload")
                .permission("reforge.command.reload")
                .handler(ctx -> {
                    plugin.onDisable();
                    plugin.onEnable();
                }).build());

        // Register commands
        registry.registerCommands();
    }
}
