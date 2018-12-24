package hu.frontrider.core.traits.entity

import net.minecraft.container.Slot
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack

class FilteredSlot(inventory: Inventory,slot:Int, x:Int, y:Int,val condition:(ItemStack)->Boolean):Slot(inventory,slot,x,y) {
    override fun canInsert(itemStack_1: ItemStack): Boolean {
        return super.canInsert(itemStack_1) && condition(itemStack_1)
    }
}