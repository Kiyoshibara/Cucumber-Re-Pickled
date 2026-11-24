package com.blakebr0.cucumber.item.tool;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

import java.util.function.Function;

public class BaseHoeItem extends Item {
    private final float attackDamage;
    private final float attackSpeed;

    public BaseHoeItem(Identifier id, ToolMaterial material) {
        this(id, material, -1, -2.0F, p -> p);
    }

    public BaseHoeItem(Identifier id, ToolMaterial material, Function<Properties, Properties> properties) {
        this(id, material, -1, -2.0F, properties);
    }

    public BaseHoeItem(Identifier id, ToolMaterial material, int attackDamage, float attackSpeed, Function<Properties, Properties> properties) {
        super(properties.apply(new Properties().setId(ResourceKey.create(Registries.ITEM, id))).hoe(material, attackDamage, attackSpeed));
        this.attackDamage = attackDamage + material.attackDamageBonus();
        this.attackSpeed = attackSpeed;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public float getAttackSpeed() {
        return this.attackSpeed;
    }
}
