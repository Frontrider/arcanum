package hu.frontrider.arcanum

import hu.frontrider.arcanum.container.MagicalCraftingTableContainer
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.container.ContainerProviderRegistry
import net.minecraft.util.Identifier


object Arcanum:ModInitializer {
    var MODID="arcanum"
    val MAGICAL_CRAFTING_TABLE_CONTAINER = Identifier(MODID, "magical_crafting_table")

    override fun onInitialize() {
//Registers a container factory that opens our example Container, this reads the block pos from the buffer
        ContainerProviderRegistry.INSTANCE.registerFactory(MAGICAL_CRAFTING_TABLE_CONTAINER) { identifier, player, buf ->
            val pos = buf.readBlockPos()
            MagicalCraftingTableContainer(player,pos)
        }
    }
}