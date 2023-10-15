package cc.mewcraft.reforge.plugin;

import cc.mewcraft.reforge.gui.ReforgeGuiBindings;
import cc.mewcraft.reforge.gui.ReforgeGuiModule;
import cc.mewcraft.reforge.internal.ReforgeInternalBindings;
import cc.mewcraft.reforge.internal.ReforgeInternalModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import me.lucko.helper.plugin.ExtendedJavaPlugin;

public class ReforgePlugin extends ExtendedJavaPlugin {
    @Override protected void enable() {
        // Configure bindings
        Injector injector = Guice.createInjector(
                new ReforgeInternalBindings(this),
                new ReforgeGuiBindings(this)
        );

        // Initialize modules
        bindModule(injector.getInstance(ReforgeInternalModule.class));
        bindModule(injector.getInstance(ReforgeGuiModule.class));

        // Initialize commands
        injector.getInstance(ReforgeCommands.class).registerCommands();
    }

    @Override protected void disable() {
        //commands.deleteRootCommand();
    }
}
