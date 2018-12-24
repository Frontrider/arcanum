package hu.frontrider.core.traits.entity

import net.minecraft.nbt.CompoundTag

interface Saveable {
    fun fromTag(nbt:CompoundTag)
    fun toTag(nbt: CompoundTag): CompoundTag
}