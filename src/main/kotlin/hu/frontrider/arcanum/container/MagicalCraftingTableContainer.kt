package hu.frontrider.arcanum.container

import hu.frontrider.arcanum.Arcanum.MODID
import hu.frontrider.arcanum.Blocks
import hu.frontrider.arcanum.recipe.MagicalRecipe
import hu.frontrider.core.traits.entity.FilteredSlot
import net.minecraft.client.network.packet.GuiSlotUpdateClientPacket
import net.minecraft.container.Container
import net.minecraft.container.CraftingResultSlot
import net.minecraft.container.Slot
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.CraftingInventory
import net.minecraft.inventory.CraftingResultInventory
import net.minecraft.inventory.Inventory
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sortme.ItemScatterer
import net.minecraft.tag.ItemTags
import net.minecraft.tag.TagContainer
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class MagicalCraftingTableContainer(val player: PlayerEntity, val pos: BlockPos) : Container() {

    var craftingInv = CraftingInventory(this, 3, 4)
    var resultInv = CraftingResultInventory()
    val playerInventory = player.inventory
    val world: World = player.world

    init {
        this.addSlot(CraftingResultSlot(playerInventory.player, this.craftingInv, this.resultInv, 0, 124 + 14, 35))

        var var4 = 0
        var var5: Int
        while (var4 < 3) {
            var5 = 0
            while (var5 < 3) {
                val slot = Slot(this.craftingInv, var5 + var4 * 3, 44 + var5 * 18, 17 + var4 * 18)

                this.addSlot(slot)
                ++var5
            }
            ++var4
        }

        addSlot(FilteredSlot(this.craftingInv, 9, 44-18, 17) {

            val tagsFor = ItemTags.getContainer().getTagsFor(it.item);
            println(tagsFor)
            tagsFor.contains(Identifier(MODID,"artifact"))
        })

        addSlot(FilteredSlot(this.craftingInv, 10, 44-18, 17 + 18) {
            val tagsFor = ItemTags.getContainer().getTagsFor(it.item);
            println(tagsFor)
            tagsFor.contains(Identifier(MODID,"magic_book"))
        })

        addSlot(FilteredSlot(this.craftingInv, 11, 44-18, 17 + 18*2) {
            val tagsFor = ItemTags.getContainer().getTagsFor(it.item);
            println(tagsFor)
            tagsFor.contains(Identifier(MODID,"potion"))
        })

        var4 = 0
        while (var4 < 3) {
            var5 = 0
            while (var5 < 9) {
                this.addSlot(Slot(playerInventory, var5 + var4 * 9 + 9, 8 + var5 * 18, 84 + var4 * 18))
                ++var5
            }
            ++var4
        }

        var4 = 0
        while (var4 < 9) {
            this.addSlot(Slot(playerInventory, var4, 8 + var4 * 18, 142))
            ++var4
        }

    }


    override fun onContentChanged(var1: Inventory?) {
        this.craft(this.world, this.player, this.craftingInv, this.resultInv)
    }

    override fun canUse(playerEntity: PlayerEntity): Boolean {
        return if (this.world.getBlockState(this.pos).block !== Blocks.MAGICAL_CRAFTING_TABLE) {
            false
        } else {
            playerEntity.squaredDistanceTo(this.pos.x.toDouble() + 0.5, this.pos.y.toDouble() + 0.5, this.pos.z.toDouble() + 0.5) <= 64.0
        }
    }

    private fun craft(var1: World, var2: PlayerEntity, var3: Inventory, var4: CraftingResultInventory) {
        if (!var1.isClient) {
            val var5 = var2 as ServerPlayerEntity
            val recipe = world.recipeManager.values().firstOrNull {
                if (it is MagicalRecipe) {
                    return@firstOrNull it.matches(craftingInv, world)
                }
                return@firstOrNull false
            } ?: return
            val output = recipe.output
            var4.setInvStack(0, output)
            var5.networkHandler.sendPacket(GuiSlotUpdateClientPacket(this.syncId, 0, output))
        }
    }

    override fun close(playerEntity: PlayerEntity) {
        if (!player.world.isClient)
            ItemScatterer.spawn(playerEntity.world, playerEntity.pos, craftingInv)
    }
}