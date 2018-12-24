package hu.frontrider.arcanum.entity

import net.fabricmc.fabric.entity.FabricEntityTypeBuilder



object EntityTypes {

    val BowFireball =  FabricEntityTypeBuilder.create(
            EntityFireballProjectile::class.java
    ) { w ->
        EntityFireballProjectile(w) }.build()

}