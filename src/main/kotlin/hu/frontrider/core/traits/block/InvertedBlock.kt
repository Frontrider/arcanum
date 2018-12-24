package hu.frontrider.core.traits.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateFactory
import net.minecraft.state.property.Properties.INVERTED

class InvertedBlock : BlockStateManager {
    override fun getState(builder: StateFactory.Builder<Block, BlockState>): StateFactory.Builder<Block, BlockState> {
        return builder.with(INVERTED)
    }

    override fun getPlacementState(context: ItemPlacementContext, state: BlockState): BlockState {

        val player = context.player ?: return state.with(INVERTED, false)

        return state.with(INVERTED, player.isSneaking)
    }
}