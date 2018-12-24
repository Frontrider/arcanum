package hu.frontrider.arcanum

import hu.frontrider.arcanum.Arcanum.MODID
import hu.frontrider.arcanum.item.ColoredItem
import hu.frontrider.arcanum.item.MagicalSwordItem
import hu.frontrider.arcanum.item.TippedSwordItem
import hu.frontrider.arcanum.item.arrows.FireChargeArrowItem
import net.fabricmc.api.ModInitializer
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object Items:ModInitializer {
    val MAGICAL_SWORD = MagicalSwordItem()
    val TIPPED_SWORD = TippedSwordItem()
    val MAGIC_INGOT=Item(Item.Settings().itemGroup(ItemGroup.MATERIALS))

    val FIRE_CHARGE_ARROW = FireChargeArrowItem()

    //magic books
    val MAGIC_BOOK= makeSingleMaterial(16776296)
    val MAGIC_BOOK_FIRE= makeSingleMaterial(14027274)
    val MAGIC_BOOK_WATER= makeSingleMaterial(14027274)
    val MAGIC_BOOK_ENDER= makeSingleMaterial(298601)
    val MAGIC_BOOK_AIR= makeSingleMaterial(16776296)
    val MAGIC_BOOK_LIFE= makeSingleMaterial(16776296)
    val MAGIC_BOOK_DEATH= makeSingleMaterial(16776296)
    val MAGIC_BOOK_EARTH= makeSingleMaterial(16776296)

    override fun onInitialize() {
        Registry.ITEM.register(Identifier(MODID,"firecharge_arrow"), FIRE_CHARGE_ARROW)
        Registry.ITEM.register(Identifier(MODID,"magical_sword"), MAGICAL_SWORD)
        Registry.ITEM.register(Identifier(MODID,"tipped_sword"), TIPPED_SWORD)
        Registry.ITEM.register(Identifier(MODID,"magic_ingot"), MAGIC_INGOT)
        Registry.ITEM.register(Identifier(MODID,"magic_book"), MAGIC_BOOK)
        Registry.ITEM.register(Identifier(MODID,"magic_book_fire"), MAGIC_BOOK_FIRE)
        Registry.ITEM.register(Identifier(MODID,"magic_book_water"), MAGIC_BOOK_WATER)
        Registry.ITEM.register(Identifier(MODID,"magic_book_ender"), MAGIC_BOOK_ENDER)
        Registry.ITEM.register(Identifier(MODID,"magic_book_air"), MAGIC_BOOK_AIR)
        Registry.ITEM.register(Identifier(MODID,"magic_book_life"), MAGIC_BOOK_LIFE)
        Registry.ITEM.register(Identifier(MODID,"magic_book_death"), MAGIC_BOOK_DEATH)
        Registry.ITEM.register(Identifier(MODID,"magic_book_earth"), MAGIC_BOOK_EARTH)
    }

    fun makeSingleMaterial():Item{
        return Item(Item.Settings().itemGroup(ItemGroup.MATERIALS).stackSize(1))
    }

    fun makeSingleMaterial(color:Int):Item{
        return ColoredItem(Item.Settings().itemGroup(ItemGroup.MATERIALS).stackSize(1),color)
    }
}