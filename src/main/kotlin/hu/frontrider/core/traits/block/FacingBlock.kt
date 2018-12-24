package hu.frontrider.core.traits.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateFactory
import net.minecraft.state.property.Properties.FACING
import net.minecraft.util.math.Direction

class FacingBlock : BlockStateManager {
    override fun getState(builder: StateFactory.Builder<Block, BlockState>): StateFactory.Builder<Block, BlockState> {
        return builder.with(FACING)
    }

    override fun getPlacementState(context: ItemPlacementContext, state: BlockState): BlockState {
        val player = context.player ?: return state
        return state.with(FACING, Direction.getEntityFacingOrder(player)[0])

    }
}