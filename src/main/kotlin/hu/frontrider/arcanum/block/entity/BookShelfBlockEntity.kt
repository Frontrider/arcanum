package hu.frontrider.arcanum.block.entity

import hu.frontrider.arcanum.Arcanum
import hu.frontrider.arcanum.Items
import hu.frontrider.arcanum.block.entity.BlockEntityTypes.MAGICAL_BOOKSHELF
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.inventory.InventoryListener
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.tag.ItemTags
import net.minecraft.text.TextComponent
import net.minecraft.util.Identifier
import java.util.*

class BookShelfBlockEntity(private var type: Item = Items.MAGIC_BOOK) : BlockEntity(MAGICAL_BOOKSHELF), Inventory, InventoryListener {
    private var count = 0
    private val maxCount = 32

    lateinit var UUID:UUID

    override fun getInvStack(p0: Int): ItemStack {
        if (count == 0)
            return ItemStack.EMPTY
        return ItemStack(type, count)
    }

    override fun canPlayerUseInv(p0: PlayerEntity?): Boolean {
        return true
    }

    override fun getName(): TextComponent {
        return type.textComponent
    }

    override fun takeInvStack(p0: Int, takenAmount: Int): ItemStack {
        return when {
            count == 0 -> ItemStack.EMPTY
            count >= takenAmount -> {
                count -= takenAmount
                onInvChange(this)
                ItemStack(type, takenAmount)
            }
            count < takenAmount -> {
                count = 0
                onInvChange(this)

                ItemStack(type, count)
            }
            else -> ItemStack.EMPTY
        }
    }

    override fun setInvStack(p0: Int, stack: ItemStack) {
        val tagsFor = ItemTags.getContainer().getTagsFor(stack.item);
        println(tagsFor)
        if (!tagsFor.contains(Identifier(Arcanum.MODID, "magic_book")))
            return;

        if (stack.item != type) {
            type = stack.item
        }

        count = if (stack.amount <= maxCount)
            stack.amount
        else {
            maxCount
        }
        onInvChange(this)
    }

    override fun removeInvStack(p0: Int): ItemStack {
        count = 0
        onInvChange(this)
        return ItemStack(type, count)
    }

    override fun clearInv() {
        onInvChange(this)
        count = 0
    }

    override fun getInvSize(): Int {
        return 1
    }

    override fun isInvEmpty(): Boolean {
        return count == 0
    }

    override fun onInvChange(p0: Inventory?) {

    }
}