package cc.mewcraft.reforge.internal.hook;

import cc.mewcraft.reforge.internal.Reforge;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;

/**
 * This MockReforge simply appends a red text "(Reforged)" to the display name of input item.
 */
public class MockReforge implements Reforge {
    @Override public Optional<ItemStack> transform(@NotNull final ItemStack item, @NotNull final String optionKey) {
        ItemStack clone = item.clone();
        clone.editMeta(meta -> {
            Component oldName = meta.hasDisplayName() ? Objects.requireNonNull(meta.displayName()) : Component.translatable(clone.translationKey());
            Component newName = oldName.append(Component.space()).append(Component.text("(Reforged)").color(NamedTextColor.RED)).decoration(TextDecoration.ITALIC, false);
            meta.displayName(newName);
        });
        return Optional.of(clone);
    }
}
