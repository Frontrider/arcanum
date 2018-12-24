package hu.frontrider.core.traits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;

public interface BlockStateManager {
    StateFactory.Builder<Block, BlockState> getState(StateFactory.Builder<Block, BlockState> builder);
    BlockState getPlacementState(ItemPlacementContext context, BlockState state);
}
