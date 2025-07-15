package com.p1nero.p1nero_ec.mixin;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;

@Mixin(LocalPlayerPatch.class)
public interface LocalPlayerPatchAccessor {
    @Accessor(value = "rayTarget", remap = false)
    void setRayTarget(LivingEntity target);
}
