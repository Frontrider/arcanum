package hu.frontrider.arcanum

import hu.frontrider.arcanum.recipe.MagicalRecipe
import net.fabricmc.api.ModInitializer
import net.minecraft.recipe.RecipeSerializers

object Recipes:ModInitializer {
    val ARCANE_RECIPE_SERIALISER = MagicalRecipe.Serializer()

    override fun onInitialize() {
        RecipeSerializers.register(ARCANE_RECIPE_SERIALISER)
    }
}