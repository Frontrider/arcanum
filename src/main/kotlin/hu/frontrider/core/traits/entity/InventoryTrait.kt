package hu.frontrider.core.traits.entity

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.ListTag
import net.minecraft.text.StringTextComponent
import net.minecraft.text.TextComponent

class InventoryTrait(private var slotCount: Int, val name: String) : Inventory, Saveable {

    private var stacks = Array<ItemStack>(slotCount) {ItemStack.EMPTY}
    var dirty: Boolean = false


    override fun getInvStack(slot: Int): ItemStack {
        return stacks[slot].copy()
    }

    override fun canPlayerUseInv(p0: PlayerEntity?): Boolean {
        return true
    }

    override fun getName(): TextComponent {
        return StringTextComponent(name)
    }

    override fun takeInvStack(slot: Int, count: Int): ItemStack {
        val itemStack = stacks[slot]
        return if (itemStack.amount <= count) {
            stacks[slot] = ItemStack.EMPTY
            itemStack
        } else {
            itemStack.amount -= count
            val resultStack = itemStack.copy()
            resultStack.amount = count
            resultStack
        }
    }

    override fun markDirty() {
        dirty = true
    }

    override fun setInvStack(slot: Int, stack: ItemStack) {
        stacks[slot] = stack
    }

    override fun removeInvStack(slot: Int): ItemStack {
        val itemStack = stacks[slot]
        stacks[slot] = ItemStack.EMPTY
        markDirty()

        return itemStack
    }

    override fun clearInv() {
        for (i in 0 until slotCount) {
            stacks[i] = ItemStack.EMPTY
        }
    }

    override fun getInvSize(): Int {
        return slotCount
    }

    override fun isInvEmpty(): Boolean {
        return stacks.firstOrNull { it.amount > 0 } != null
    }

    override fun fromTag(nbt: CompoundTag) {
        val tag = nbt.getTag("inventory")
        var index = 0;
        (tag as ListTag).forEach {
            (it as CompoundTag).apply {
                stacks[index] = (ItemStack.fromTag(this))
                index++
            }
        }
    }

    override fun toTag(nbt: CompoundTag): CompoundTag {
        val listTag = ListTag()
        for (i in 0 until slotCount) {
            val itemStack = stacks[i]
            val stackTag = itemStack.toTag(CompoundTag())
            listTag.add(stackTag)
        }
        nbt.put("inventory",listTag)
        return nbt
    }
}