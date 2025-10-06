package com.p1nero.p1nero_ec.mixin;

import com.github.L_Ender.cataclysm.items.infernal_forge;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.client.ClientEngine;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;

import static yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch.PlayerMode.EPICFIGHT;

@Mixin(infernal_forge.class)
public class InfernalForgeMixin {
    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void cancelEarthQuake(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        LocalPlayerPatch playerpatch = ClientEngine.getInstance().getPlayerPatch();
        if (playerpatch != null && playerpatch.getPlayerMode().equals(EPICFIGHT)) {
            cir.setReturnValue(InteractionResult.PASS);
        }
    }
}
