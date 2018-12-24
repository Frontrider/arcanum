package hu.frontrider.core.util

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.text.StringTextComponent
import net.minecraft.text.TextComponent

class CraftingInventory(slotCount: Int = 1) : Inventory {
    val inventory: Array<ItemStack> =
            arrayOfNulls<ItemStack>(slotCount)
                    .map {
                        if (it == null)
                            return@map ItemStack.EMPTY
                        it!!
                    }.toTypedArray()

    override fun getInvMaxStackAmount(): Int {
        return 1;
    }

    override fun getInvStack(slot: Int): ItemStack {
        return inventory[slot]
    }

    override fun markDirty() {

    }

    override fun setInvStack(slot: Int, stack: ItemStack) {
        inventory[slot] = stack
    }

    override fun getInvPropertyCount(): Int {
        return 0
    }

    override fun removeInvStack(slot: Int): ItemStack {
        val itemStack = inventory[slot]
        inventory[slot] = ItemStack.EMPTY
        return itemStack
    }

    override fun canPlayerUseInv(var1: PlayerEntity?): Boolean {
        return false
    }

    override fun clearInv() {
        inventory
    }

    override fun getName(): TextComponent {
        return StringTextComponent("")
    }

    override fun getInvSize(): Int {
        return inventory.size
    }

    override fun isValidInvStack(var1: Int, var2: ItemStack?): Boolean {
        return false
    }

    override fun onInvOpen(var1: PlayerEntity?) {

    }

    override fun setInvProperty(var1: Int, var2: Int) {

    }

    override fun onInvClose(var1: PlayerEntity?) {

    }

    override fun takeInvStack(slot: Int, amount: Int): ItemStack {
        val itemStack = inventory[slot]
        itemStack.addAmount(-amount)
        return itemStack
    }

    override fun isInvEmpty(): Boolean {
        return inventory.count { it.amount == 0 }==0
    }

    override fun getInvProperty(var1: Int): Int {
        return 0
    }
}