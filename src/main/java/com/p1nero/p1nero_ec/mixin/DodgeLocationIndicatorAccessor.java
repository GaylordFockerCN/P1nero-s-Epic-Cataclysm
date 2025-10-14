package com.p1nero.p1nero_ec.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.entity.DodgeLocationIndicator;

@Mixin(value = DodgeLocationIndicator.class)
public interface DodgeLocationIndicatorAccessor{

    @Accessor(value = "entitypatch", remap = false)
    LivingEntityPatch<?> getEntityPatch();

}
