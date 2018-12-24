package hu.frontrider.arcanum.item

import net.minecraft.client.item.TooltipOptions
import net.minecraft.item.*
import net.minecraft.potion.PotionUtil
import net.minecraft.text.TextComponent
import net.minecraft.world.World

class TippedSwordItem :SwordItem(ToolMaterials.DIAMOND,1000,3.0f, Item.Settings().stackSize(1).itemGroup(ItemGroup.TOOLS)) {

    override fun buildTooltip(p0: ItemStack?, p1: World?, p2: MutableList<TextComponent>?, p3: TooltipOptions?) {
        PotionUtil.addInformation(p0,p2,1.0f)
    }
}