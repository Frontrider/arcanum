package hu.frontrider.arcanum.block

import hu.frontrider.arcanum.Arcanum
import net.fabricmc.fabric.api.container.ContainerProviderRegistry
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World


class MagicalCraftingTableBlock: Block(Block.Settings.of(Material.STONE)) {


    override fun activate(p0: BlockState?, world: World, pos: BlockPos, player: PlayerEntity, p4: Hand?, p5: Direction?, p6: Float, p7: Float, p8: Float): Boolean {
        return if (world.isClient) {
            true
        } else {
            ContainerProviderRegistry.INSTANCE.openContainer(Arcanum.MAGICAL_CRAFTING_TABLE_CONTAINER, player) { buf -> buf.writeBlockPos(pos) }
            true
        }
    }


}