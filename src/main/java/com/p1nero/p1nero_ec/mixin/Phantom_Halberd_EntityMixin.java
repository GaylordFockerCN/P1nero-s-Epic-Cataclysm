package com.p1nero.p1nero_ec.mixin;

import com.github.L_Ender.cataclysm.entity.projectile.Phantom_Halberd_Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.world.entity.DodgeLocationIndicator;

import javax.annotation.Nullable;

@Mixin(Phantom_Halberd_Entity.class)
public abstract class Phantom_Halberd_EntityMixin {

    @Shadow(remap = false)
    @Nullable
    public abstract LivingEntity getCaster();

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true, remap = false)
    private void tcr$damage(LivingEntity living, CallbackInfo ci) {
        if(living instanceof DodgeLocationIndicator dodgeLocationIndicator && ((DodgeLocationIndicatorAccessor)dodgeLocationIndicator).getEntityPatch().getOriginal() == this.getCaster()) {
            ci.cancel();
        }
    }
}
