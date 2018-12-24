package hu.frontrider.arcanum.item.arrows

import hu.frontrider.arcanum.entity.EntityFireballProjectile
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.FireballEntity
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.item.ArrowItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Rarity
import net.minecraft.world.World

class FireChargeArrowItem : ArrowItem(Item.Settings().itemGroup(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)) {

    override fun createEntityArrow(world: World, itemStack_1: ItemStack?, livingEntity_1: LivingEntity?): ProjectileEntity {
        return EntityFireballProjectile(world)
    }
}