package hu.frontrider.core

import hu.frontrider.core.traits.block.AxisTrait
import hu.frontrider.core.traits.block.FacingBlock
import hu.frontrider.core.traits.block.InvertedBlock
import hu.frontrider.core.traits.block.WaterLoggedTrait

object TraitInstances {

    val invertedState = InvertedBlock()
    val facingState = FacingBlock()
    val axisTrait = AxisTrait()
    val waterLoggedTrait = WaterLoggedTrait()


}