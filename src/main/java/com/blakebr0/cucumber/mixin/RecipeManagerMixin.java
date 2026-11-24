package com.blakebr0.cucumber.mixin;

import com.blakebr0.cucumber.helper.RecipeHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Minecraft 26.3 constructs a RecipeMap from the datapack recipe registry in the
 * RecipeManager constructor; the old prepare(...) injection no longer exists.
 *
 * Keep Cucumber's RecipeManagerLoadingEvent for mods that generate recipes at
 * runtime. The new recipes are added to the manager's recipe indexes before
 * finalizeRecipeLoading(...) computes the recipe-book/display indexes.
 *
 * IMPORTANT: This is a runtime compatibility bridge, not registry insertion.
 * Integrations that look up added recipes directly through Registries.RECIPE
 * may still need a later migration to datapack registry-based generation.
 */
@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin {
    @Shadow @Final @Mutable
    private RecipeMap recipes;

    @Shadow @Final @Mutable
    private Collection<RecipeHolder<?>> learnableRecipes;

    @Inject(method = "<init>(Lnet/minecraft/core/HolderLookup$Provider;)V", at = @At("TAIL"))
    private void cucumber$initializeDynamicRecipes(HolderLookup.Provider registries, CallbackInfo ci) {
        var additional = new ArrayList<RecipeHolder<?>>();
        RecipeHelper.fireRecipeManagerLoadingEvent((RecipeManager) (Object) this, registries, additional);

        if (additional.isEmpty()) {
            return;
        }

        // Preserve vanilla/datapack recipes; replace only exact duplicate recipe IDs.
        Map<ResourceKey<Recipe<?>>, RecipeHolder<?>> merged = new LinkedHashMap<>();
        for (RecipeHolder<?> recipe : this.recipes.values()) {
            merged.put(recipe.id(), recipe);
        }
        for (RecipeHolder<?> recipe : additional) {
            merged.put(recipe.id(), recipe);
        }

        // NeoForge 26.3 exposes a factory which builds RecipeMap from holders.
        // RecipeMap.create(...) now requires a registry lookup instead of a list.
        this.recipes = RecipeMap.createClient(List.copyOf(merged.values()));
        this.learnableRecipes = this.recipes.values().stream()
                .filter(recipe -> !recipe.value().isSpecial())
                .toList();
    }
}
