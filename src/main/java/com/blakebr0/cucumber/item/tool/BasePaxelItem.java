package com.blakebr0.cucumber.item.tool;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.cucumber.lib.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.BlockTransformers;
import net.minecraft.world.item.context.UseOnContext;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.function.Function;

/**
 * Multi-tool: the registered 26.3 block transformers handle stripping/scraping/wax removal
 * and shovel flattening. The tool's custom mining tag remains unchanged.
 */
public class BasePaxelItem extends BaseItem {
    public BasePaxelItem(Identifier id, ToolMaterial material) {
        this(id, material, Function.identity());
    }

    public BasePaxelItem(Identifier id, ToolMaterial material, Function<Properties, Properties> properties) {
        super(id, properties.compose(p -> p
                .setId(ResourceKey.create(Registries.ITEM, id))
                .tool(material, ModTags.MINEABLE_WITH_PAXEL, 4.0F, -3.2F, 5.0F)
                .durability((int) (material.durability() * 1.5))
        ));
    }

    @Override
    public boolean canPerformAction(ItemInstance stack, ItemAbility ability) {
        // The only shovel-specific ItemAbility retained in 26.3: extinguishing campfires.
        // Axe/flattening actions are handled by BlockTransformer registries, not ItemAbility.
        return ability == ItemAbilities.SHOVEL_DOUSE || super.canPerformAction(stack, ability);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var registry = context.getLevel().registryAccess();

        // Attempt vanilla/data-pack axe transformations first (strip/scrape/unwax).
        var axeResult = registry.getOrThrow(BlockTransformers.AXE).value().transformBlock(context);
        if (axeResult != InteractionResult.PASS) {
            return axeResult;
        }

        // Then attempt vanilla/data-pack shovel transformations (flattening).
        // Campfire dousing is handled by CampfireBlock via SHOVEL_DOUSE.
        return registry.getOrThrow(BlockTransformers.SHOVEL).value().transformBlock(context);
    }
}
