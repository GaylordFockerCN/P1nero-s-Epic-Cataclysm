package com.p1nero.p1nero_ec.mixin;

import com.github.L_Ender.cataclysm.client.render.entity.Tidal_Tentacle_Renderer;
import com.github.L_Ender.cataclysm.entity.projectile.Tidal_Tentacle_Entity;
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

@Mixin(Tidal_Tentacle_Renderer.class)
public class TidalTentacleRendererMixin {
    @Inject(method = "getPositionOfPriorMob", at = @At("HEAD"), remap = false, cancellable = true)
    private void pec$getPositionOfPriorMob(Tidal_Tentacle_Entity segment, Entity mob, float partialTicks, CallbackInfoReturnable<Vec3> cir){
        PlayerPatch<?> playerPatch = EpicFightCapabilities.getEntityPatch(mob, PlayerPatch.class);
        if(playerPatch != null && segment.isCreator(mob)) {
            cir.setReturnValue(AvalonAnimationUtils.getJointWorldPos(playerPatch, Armatures.BIPED.get().toolR, new Vec3f(0, 0.5, 0), partialTicks));
        }
    }
}
