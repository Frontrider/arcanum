package hu.frontrider.arcanum.client.block.gui

import com.mojang.blaze3d.platform.GlStateManager
import hu.frontrider.arcanum.Arcanum.MODID
import hu.frontrider.arcanum.container.MagicalCraftingTableContainer
import net.minecraft.client.gui.ContainerGui
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos

//A container gui that shows how you can take in a container provided by a GuiFactory
class MagicalCraftingTableGui(container: MagicalCraftingTableContainer) : ContainerGui(container) {
    private val BG_TEX = Identifier(MODID,"textures/gui/magical_crafting_table.png")

    override fun drawBackground(v: Float, i: Int, i1: Int) {
        GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f)
        this.client.textureManager.bindTexture(BG_TEX)
        val left = this.left
        val top = (this.height - this.containerHeight) / 2
        this.drawTexturedRect(left, top, 0, 0, this.containerWidth, this.containerHeight)
    }
}
