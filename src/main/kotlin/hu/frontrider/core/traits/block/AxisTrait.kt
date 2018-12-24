package hu.frontrider.core.traits.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateFactory
import net.minecraft.state.property.Properties

class AxisTrait : BlockStateManager {
    override fun getState(builder: StateFactory.Builder<Block, BlockState>): StateFactory.Builder<Block, BlockState> {
        return builder.with(Properties.AXIS_XYZ)
    }

    override fun getPlacementState(context: ItemPlacementContext, state: BlockState): BlockState {
        return state.with(Properties.AXIS_XYZ, context.facing.axis)
    }
}