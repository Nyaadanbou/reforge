package cc.mewcraft.reforge.gui;

import cc.mewcraft.reforge.gui.bind.GuiLanguageDirectory;
import me.lucko.helper.terminable.TerminableConsumer;
import me.lucko.helper.terminable.module.TerminableModule;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.slf4j.Logger;
import xyz.xenondevs.inventoryaccess.component.i18n.AdventureComponentLocalizer;
import xyz.xenondevs.inventoryaccess.component.i18n.Languages;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import javax.inject.Inject;

import org.jetbrains.annotations.NotNull;

public class ReforgeGuiModule implements TerminableModule {
    private final Logger logger;
    private final Path guiLanguageDirectory;

    @Inject
    public ReforgeGuiModule(
            final Logger logger,
            @GuiLanguageDirectory final Path guiLanguageDirectory
    ) {
        this.logger = logger;
        this.guiLanguageDirectory = guiLanguageDirectory;
    }

    @Override public void setup(@NotNull final TerminableConsumer consumer) {
        // Initialize languages for InvUI
        try {
            Languages.getInstance().loadLanguage("zh_cn", guiLanguageDirectory.resolve("zh_cn.json").toFile(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error("Failed to load language files", e);
        }

        // Add support of MiniMessage in InvUI localization config
        AdventureComponentLocalizer.getInstance().setComponentCreator(MiniMessage.miniMessage()::deserialize);
    }
}
