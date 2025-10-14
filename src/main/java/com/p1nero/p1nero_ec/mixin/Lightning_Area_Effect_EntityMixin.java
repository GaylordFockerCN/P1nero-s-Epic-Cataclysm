package com.p1nero.p1nero_ec.mixin;

import com.github.L_Ender.cataclysm.entity.effect.Lightning_Area_Effect_Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.world.entity.DodgeLocationIndicator;

import javax.annotation.Nullable;

@Mixin(Lightning_Area_Effect_Entity.class)
public abstract class Lightning_Area_Effect_EntityMixin {

    @Shadow(remap = false)
    @Nullable
    public abstract LivingEntity getOwner();

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true, remap = false)
    private void pec$damage(LivingEntity living, CallbackInfo ci) {
        if (living instanceof DodgeLocationIndicator dodgeLocationIndicator) {
            LivingEntity dodgeOwner = ((DodgeLocationIndicatorAccessor) dodgeLocationIndicator).getEntityPatch().getOriginal();
            LivingEntity areaEffectOwner = this.getOwner();

            if (dodgeOwner == areaEffectOwner) {
                ci.cancel();
            }
        }
    }
}
