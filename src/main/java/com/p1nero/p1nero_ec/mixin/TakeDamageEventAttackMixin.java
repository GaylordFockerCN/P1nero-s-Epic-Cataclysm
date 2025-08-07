package com.p1nero.p1nero_ec.mixin;

import com.p1nero.p1nero_ec.PECConfig;
import com.p1nero.p1nero_ec.capability.PECPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.entity.eventlistener.AbstractPlayerEvent;
import yesman.epicfight.world.entity.eventlistener.TakeDamageEvent;

/**
 * 招架计数
 */
@Mixin(value = TakeDamageEvent.Attack.class)
public abstract class TakeDamageEventAttackMixin extends AbstractPlayerEvent<ServerPlayerPatch> {

    @Shadow(remap = false)
    protected boolean parried;

    public TakeDamageEventAttackMixin(ServerPlayerPatch playerPatch, boolean cancelable) {
        super(playerPatch, cancelable);
    }

    @Inject(method = "setParried", at = @At("HEAD"), remap = false)
    private void pec$setParry(boolean parried, CallbackInfo ci){
        if(!this.parried && parried) {
            if(!PECConfig.GLOBAL_CHARGE.get() && !PECPlayer.isValidWeapon(this.getPlayerPatch().getOriginal().getMainHandItem())) {
                return;
            }
            PECPlayer.addSkillPoint(this.getPlayerPatch().getOriginal());
        }
    }
}
