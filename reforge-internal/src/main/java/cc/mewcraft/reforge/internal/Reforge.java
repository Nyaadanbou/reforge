package cc.mewcraft.reforge.internal;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface Reforge {
    Optional<ItemStack> transform(@NotNull ItemStack item, @NotNull String optionKey);
}
