package cc.mewcraft.reforge.internal.hook;

import cc.mewcraft.reforge.internal.Reforge;
import net.Indyuce.mmoitems.api.ReforgeOptions;
import net.Indyuce.mmoitems.api.util.MMOItemReforger;
import org.bukkit.inventory.ItemStack;
import org.slf4j.Logger;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jetbrains.annotations.NotNull;

/**
 * Following is the order of options from the source code of MMOItems:
 * <ol>
 *     <li>keepName</li>
 *     <li>keepLore</li>
 *     <li>keepEnchantments</li>
 *     <li>keepUpgrades</li>
 *     <li>keepGemStones</li>
 *     <li>keepSoulBind</li>
 *     <li>keepExternalSH</li>
 *     <li>reRoll</li>
 *     <li>keepModifications</li>
 *     <li>keepAdvancedEnchantments</li>
 *     <li>keepSkins</li>
 *     <li>KeepTier</li>
 * </ol>
 * <p>
 * The option data format should be a list of numbers separated by commas, e.g., "1,2,3,4".
 * That a number is present means that the corresponding option should be true.
 */
@Singleton
public class MMOItemsReforge implements Reforge {
    private final Logger logger;
    private final MMOItemsReforgeOption option;

    @Inject
    public MMOItemsReforge(
            final Logger logger,
            final MMOItemsReforgeOption option
    ) {
        this.logger = logger;
        this.option = option;
    }

    @Override public Optional<ItemStack> transform(final @NotNull ItemStack item, final @NotNull String optionKey) {
        ReforgeOptions options = option.parse(optionKey);
        if (options == null) {
            logger.error("Unknown option key: {}", optionKey);
            return Optional.empty();
        }

        MMOItemReforger mod = new MMOItemReforger(item);
        if (!mod.hasTemplate()) {
            logger.error("Failed to reforge item due to null template: {}", optionKey);
            return Optional.empty();
        }
        if (!mod.reforge(options)) {
            logger.error("Failed to reforge item: {}", optionKey);
            return Optional.empty();
        }

        return Optional.ofNullable(mod.getResult());
    }
}
