package hu.frontrider.arcanum.recipe

import com.google.common.annotations.VisibleForTesting
import com.google.common.collect.Maps
import com.google.common.collect.Sets
import com.google.gson.*
import hu.frontrider.arcanum.Arcanum.MODID
import hu.frontrider.arcanum.Recipes.ARCANE_RECIPE_SERIALISER
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.inventory.CraftingInventory
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.Recipe
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.util.DefaultedList
import net.minecraft.util.Identifier
import net.minecraft.util.JsonHelper
import net.minecraft.util.PacketByteBuf
import net.minecraft.util.registry.Registry
import net.minecraft.world.World
import java.util.Map.Entry

class MagicalRecipe(private val identifier: Identifier,
                    private val group: String,
                    private val width: Int,
                    private val height: Int,
                    private val ingredients: DefaultedList<Ingredient>,
                    private val result: ItemStack) : Recipe {


    override fun getId(): Identifier {
        return this.identifier
    }

    override fun getSerializer(): RecipeSerializer<*> {
        return ARCANE_RECIPE_SERIALISER
    }

    @Environment(EnvType.CLIENT)
    override fun getGroup(): String {
        return this.group
    }

    override fun getOutput(): ItemStack {
        return this.result
    }

    override fun getPreviewInputs(): DefaultedList<Ingredient> {
        return this.ingredients
    }

    @Environment(EnvType.CLIENT)
    override fun fits(var1: Int, var2: Int): Boolean {
        return var1 >= this.width && var2 >= this.height
    }

    override fun matches(var1: Inventory, var2: World): Boolean {
        if (var1 !is CraftingInventory) {
            return false
        } else {
            for (var3 in 0..var1.getInvWidth() - this.width) {
                for (var4 in 0..var1.getInvHeight() - this.height) {
                    if (this.matchesSmall(var1, var3, var4, true)) {
                        return true
                    }

                    if (this.matchesSmall(var1, var3, var4, false)) {
                        return true
                    }
                }
            }

            return false
        }
    }

    private fun matchesSmall(var1: Inventory, var2: Int, var3: Int, var4: Boolean): Boolean {
        for (var5 in 0 until var1.invWidth) {
            for (var6 in 0 until var1.invHeight) {
                val var7 = var5 - var2
                val var8 = var6 - var3
                var var9 = Ingredient.EMPTY
                if (var7 >= 0 && var8 >= 0 && var7 < this.width && var8 < this.height) {
                    var9 = if (var4) {
                        this.ingredients[this.width - var7 - 1 + var8 * this.width]
                    } else {
                        this.ingredients[var7 + var8 * this.width]
                    }
                }

                if (!var9.matches(var1.getInvStack(var5 + var6 * var1.invWidth))) {
                    return false
                }
            }
        }

        return true
    }

    override fun craft(var1: Inventory): ItemStack {
        return this.output.copy()
    }

    fun getWidth(): Int {
        return this.width
    }

    fun getHeight(): Int {
        return this.height
    }

    companion object {

        private fun method_8148(var0: Array<String?>, var1: Map<String, Ingredient>, var2: Int, var3: Int): DefaultedList<Ingredient> {
            val var4 = DefaultedList.create(var2 * var3, Ingredient.EMPTY)
            val var5 = Sets.newHashSet(var1.keys)
            var5.remove(" ")

            for (var6 in var0.indices) {
                for (var7 in 0 until var0[var6]!!.length) {
                    val var8 = var0[var6]!!.substring(var7, var7 + 1)
                    val var9 = var1[var8] as Ingredient
                            ?: throw JsonSyntaxException("Pattern references symbol '$var8' but it's not defined in the key")

                    var5.remove(var8)
                    var4[var7 + var2 * var6] = var9
                }
            }

            return if (!var5.isEmpty()) {
                throw JsonSyntaxException("Key defines symbols that aren't used in pattern: $var5")
            } else {
                var4
            }
        }

        @VisibleForTesting
        internal fun method_8146(vararg var0: String?): Array<String?> {
            var var1 = 2147483647
            var var2 = 0
            var var3 = 0
            var var4 = 0

            for (var5 in var0.indices) {
                val var6 = var0[var5]
                var1 = Math.min(var1, method_8151(var6!!))
                val var7 = method_8153(var6)
                var2 = Math.max(var2, var7)
                if (var7 < 0) {
                    if (var3 == var5) {
                        ++var3
                    }

                    ++var4
                } else {
                    var4 = 0
                }
            }

            return if (var0.size == var4) {
                arrayOfNulls(0)
            } else {
                val var8 = arrayOfNulls<String>(var0.size - var4 - var3)

                for (var9 in var8.indices) {
                    var8[var9] = var0[var9 + var3]!!.substring(var1, var2 + 1)
                }

                var8
            }
        }

        private fun method_8151(var0: String): Int {
            var var1: Int = 0
            while (var1 < var0.length && var0[var1] == ' ') {
                ++var1
            }

            return var1
        }

        private fun method_8153(var0: String?): Int {
            var var1: Int = var0!!.length - 1
            while (var1 >= 0 && var0[var1] == ' ') {
                --var1
            }

            return var1
        }

        fun deserializePattern(var0: JsonArray): Array<String?> {
            val var1 = arrayOfNulls<String>(var0.size())
            when {
                var1.size > 3 -> throw JsonSyntaxException("Invalid pattern: too many rows, 3 is maximum")
                var1.isEmpty() -> throw JsonSyntaxException("Invalid pattern: empty pattern not allowed")
                else -> {
                    for (var2 in var1.indices) {
                        val var3 = JsonHelper.asString(var0.get(var2), "pattern[$var2]")
                        if (var3.length > 3) {
                            throw JsonSyntaxException("Invalid pattern: too many columns, 3 is maximum")
                        }

                        if (var2 > 0 && var1[0]!!.length != var3.length) {
                            throw JsonSyntaxException("Invalid pattern: each row must be the same width")
                        }

                        var1[var2] = var3
                    }

                    return var1
                }
            }
        }

        fun deserializeComponents(var0: JsonObject): Map<String, Ingredient> {
            val var1 = Maps.newHashMap<String, Ingredient>()
            val var2 = var0.entrySet().iterator()

            while (var2.hasNext()) {
                val var3 = var2.next() as Entry<String, Ingredient>
                if ((var3.key as String).length != 1) {
                    throw JsonSyntaxException("Invalid key entry: '" + var3.key as String + "' is an invalid symbol (must be 1 character only).")
                }

                if (" " == var3.key) {
                    throw JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.")
                }

                var1[var3.key] = Ingredient.fromJson(var3.value as JsonElement)
            }

            var1[" "] = Ingredient.EMPTY
            return var1
        }


        fun deserializeItemStack(var0: JsonObject): ItemStack {
            val var1 = JsonHelper.getString(var0, "item")
            val var2 = Registry.ITEM.get(Identifier(var1))
            when {
                var2 == null -> throw JsonSyntaxException("Unknown item '$var1'")
                var0.has("data") -> throw JsonParseException("Disallowed data tag found")
                else -> {
                    val var3 = JsonHelper.getInt(var0, "count", 1)
                    return ItemStack(var2, var3)
                }
            }
        }
    }

    class Serializer : RecipeSerializer<MagicalRecipe> {

        override fun read(var1: Identifier, var2: JsonObject): MagicalRecipe {
            val var3 = JsonHelper.getString(var2, "group", "")
            val var4 = deserializeComponents(JsonHelper.getObject(var2, "key"))
            val var5 = method_8146(*deserializePattern(JsonHelper.getArray(var2, "pattern")))
            val var6 = var5[0]!!.length
            val var7 = var5.size
            val var8 = method_8148(var5, var4, var6, var7)
            val var9 = deserializeItemStack(JsonHelper.getObject(var2, "result"))
            return MagicalRecipe(var1, var3, var6, var7, var8, var9)
        }

        override fun getId(): String {
            return "$MODID:arcane_crafting"
        }

        override fun read(var1: Identifier, var2: PacketByteBuf): MagicalRecipe {
            val var3 = var2.readVarInt()
            val var4 = var2.readVarInt()
            val var5 = var2.readString(32767)
            val var6 = DefaultedList.create(var3 * var4, Ingredient.EMPTY)

            for (var7 in var6.indices) {
                var6[var7] = Ingredient.fromPacket(var2)
            }

            val var8 = var2.readItemStack()
            return MagicalRecipe(var1, var5, var3, var4, var6, var8)
        }

        override fun write(var1: PacketByteBuf, var2: MagicalRecipe) {
            var1.writeVarInt(var2.width)
            var1.writeVarInt(var2.height)
            var1.writeString(var2.group)
            val var3 = var2.ingredients.iterator()

            while (var3.hasNext()) {
                val var4 = var3.next() as Ingredient
                var4.write(var1)
            }

            var1.writeItemStack(var2.output)
        }
    }
}