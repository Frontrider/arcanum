package hu.frontrider.arcanum

import hu.frontrider.arcanum.Arcanum.MODID
import hu.frontrider.arcanum.block.MagicalCraftingTableBlock
import net.fabricmc.api.ModInitializer
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.block.BlockItem
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object Blocks:ModInitializer {
    override fun onInitialize() {
        registerItemBlock(MAGICAL_CRAFTING_TABLE,"magical_crafting_table", ItemGroup.BREWING)

    }
    fun registerItemBlock(block: Block, name: String,group:ItemGroup= ItemGroup.DECORATIONS) {
        Registry.BLOCK.register(Identifier(MODID, name), block)
        Registry.ITEM.register(
                Identifier(MODID, name),
                BlockItem(block,
                        Item.Settings().itemGroup(group))
        )
    }

    var MAGICAL_CRAFTING_TABLE=MagicalCraftingTableBlock()
}