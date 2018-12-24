package hu.frontrider.gearcraft.core

import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.item.MiningToolItem

object BlockBreakingHelper {

    val wooden = makeItemStackList(Items.WOODEN_AXE as MiningToolItem, Items.WOODEN_PICKAXE as MiningToolItem, Items.WOODEN_SHOVEL as MiningToolItem)

    private fun makeItemStackList(vararg items: MiningToolItem): Array<ItemStack> {
        return items.map {
            ItemStack(it)
        }.toTypedArray()
    }

}