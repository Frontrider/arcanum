package hu.frontrider.arcanum

import hu.frontrider.arcanum.Arcanum.MAGICAL_CRAFTING_TABLE_CONTAINER
import hu.frontrider.arcanum.client.block.gui.MagicalCraftingTableGui
import hu.frontrider.arcanum.container.MagicalCraftingTableContainer
import hu.frontrider.arcanum.entity.EntityFireballProjectile
import hu.frontrider.arcanum.item.ColoredItem
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.gui.GuiProviderRegistry
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry
import net.minecraft.client.render.item.ItemColorMapper
import net.minecraft.client.render.entity.ChickenEntityRenderer
import net.fabricmc.fabric.client.render.EntityRendererRegistry
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.entity.SnowballEntityRenderer


object ClientInitialiser: ClientModInitializer {
    override fun onInitializeClient() {

        //Registers a gui factory that opens our example gui, this uses the container created by ContainerProviderRegistry
        GuiProviderRegistry.INSTANCE.registerFactory(MAGICAL_CRAFTING_TABLE_CONTAINER) { container : MagicalCraftingTableContainer ->MagicalCraftingTableGui(container)};

        ColorProviderRegistry.ITEM.register(ItemColorMapper { itemStack, i ->
            if(i == 1){
                if(itemStack.item is ColoredItem)
                {
                    return@ItemColorMapper (itemStack.item as ColoredItem).color
                }
            }
            return@ItemColorMapper 16777215
        })
        EntityRendererRegistry.INSTANCE.register(EntityFireballProjectile::class.java) { ctx, ctx2 ->
            SnowballEntityRenderer<EntityFireballProjectile>(MinecraftClient.getInstance().entityRenderManager,ctx2.itemRenderer) }
    }
}