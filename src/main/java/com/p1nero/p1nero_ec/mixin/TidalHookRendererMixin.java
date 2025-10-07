package com.p1nero.p1nero_ec.mixin;

import com.github.L_Ender.cataclysm.client.render.entity.Tidal_Hook_Renderer;
import com.merlin204.avalon.util.AvalonAnimationUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

@Mixin(Tidal_Hook_Renderer.class)
public class TidalHookRendererMixin {
    @Inject(method = "getPositionOfPriorMob", at = @At("HEAD"), remap = false, cancellable = true)
    private void pec$getPositionOfPriorMob(Entity mob, float partialTicks, CallbackInfoReturnable<Vec3> cir) {
        PlayerPatch<?> playerPatch = EpicFightCapabilities.getEntityPatch(mob, PlayerPatch.class);
        if (playerPatch != null) {
            cir.setReturnValue(AvalonAnimationUtils.getJointWorldPos(playerPatch, Armatures.BIPED.get().toolR, new Vec3f(0, -mob.getEyeHeight() * 0.4F, 0), partialTicks));
        }
    }
}
