package cc.mewcraft.reforge.internal.hook;

import cc.mewcraft.reforge.internal.Reforge;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;

public class NovaReforge implements Reforge {
    @Override public Optional<ItemStack> transform(@NotNull final ItemStack item, @NotNull final String optionKey) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
