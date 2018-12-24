package hu.frontrider.arcanum.entity

import hu.frontrider.arcanum.Items
import hu.frontrider.arcanum.item.arrows.FireChargeArrowItem
import net.minecraft.class_3856
import net.minecraft.entity.EntityType
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class EntityFireballProjectile( world: World) :ProjectileEntity(EntityTypes.BowFireball,world), class_3856 {
    override fun method_7495(): ItemStack {
        return ItemStack(Items.FIRE_CHARGE_ARROW,1)
    }

    override fun asItemStack(): ItemStack {
        return ItemStack(Items.FIRE_CHARGE_ARROW,1)
    }
}