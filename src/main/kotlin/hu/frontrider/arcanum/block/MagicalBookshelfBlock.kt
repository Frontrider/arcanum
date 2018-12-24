package hu.frontrider.arcanum.block

import hu.frontrider.arcanum.block.entity.BookShelfBlockEntity
import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView
import net.minecraft.world.World

class MagicalBookshelfBlock: Block(Block.Settings.of(Material.WOOD)),BlockEntityProvider {
    override fun createBlockEntity(p0: BlockView?): BlockEntity? {
            return BookShelfBlockEntity()
    }

    override fun onPlaced(world: World?, blockPos: BlockPos?, blockState_1: BlockState?, placer: LivingEntity?, itemStack_1: ItemStack?) {
        val isClient = world?.isClient?:return
        if(isClient){
            if(placer != null)
            if(placer is PlayerEntity){
                val bookShelfBlockEntity = world.getBlockEntity(blockPos) as BookShelfBlockEntity??:return
                bookShelfBlockEntity.UUID = placer.uuid
            }
        }
    }

}