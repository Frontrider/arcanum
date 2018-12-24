package hu.frontrider.arcanum.item

import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterials

class MagicalSwordItem :SwordItem(ToolMaterials.DIAMOND,1000,3.0f, Item.Settings().stackSize(1).itemGroup(ItemGroup.TOOLS)) {

}