package hu.frontrider.core.traits.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateFactory
import net.minecraft.state.property.Properties.WATERLOGGED

class WaterLoggedTrait : BlockStateManager {
    override fun getState(builder: StateFactory.Builder<Block, BlockState>): StateFactory.Builder<Block, BlockState> {
        return builder.with(WATERLOGGED)
    }

    override fun getPlacementState(context: ItemPlacementContext, state: BlockState): BlockState {
        val fluidState = context.world.getFluidState(context.pos)
        if(fluidState.fluid ==Fluids.WATER){
            state.with(WATERLOGGED,true)
        }
        return state

    }
}