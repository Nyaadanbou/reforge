package cc.mewcraft.reforge.plugin;

import cc.mewcraft.reforge.gui.ReforgeGuiBindings;
import cc.mewcraft.reforge.gui.ReforgeGuiModule;
import cc.mewcraft.reforge.internal.ReforgeInternalBindings;
import cc.mewcraft.reforge.internal.ReforgeInternalModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import me.lucko.helper.plugin.ExtendedJavaPlugin;

public class ReforgePlugin extends ExtendedJavaPlugin {
    private Injector injector;
    private Commands commands;

    @Override protected void enable() {
        // Configure bindings
        injector = Guice.createInjector(
                new ReforgeInternalBindings(this),
                new ReforgeGuiBindings(this)
        );

        // Initialize modules
        bindModule(injector.getInstance(ReforgeInternalModule.class));
        bindModule(injector.getInstance(ReforgeGuiModule.class));

        // Initialize commands
        commands = injector.getInstance(Commands.class);
        commands.registerCommands();
    }

    @Override protected void disable() {
        //commands.deleteRootCommand();
    }
}
