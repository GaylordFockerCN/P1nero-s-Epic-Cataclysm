package com.p1nero.p1nero_ec.mixin;

import com.github.L_Ender.cataclysm.client.model.entity.Ceraunus_Model;
import com.github.L_Ender.cataclysm.client.render.entity.Player_Ceraunus_Renderer;
import com.github.L_Ender.cataclysm.entity.projectile.Player_Ceraunus_Entity;
import com.merlin204.avalon.util.AvalonAnimationUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.client.ClientEngine;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.gameasset.Armatures;

@Mixin(Player_Ceraunus_Renderer.class)
public abstract class Player_Ceraunus_RendererMixin {

    @Shadow(remap = false) @Final private Ceraunus_Model model;

    @Shadow(remap = false) protected abstract Vec3 getPositionOfPriorMob(Entity mob, float partialTicks);

    @Shadow(remap = false) public abstract ResourceLocation getTextureLocation(Player_Ceraunus_Entity entity);

    @Shadow(remap = false) @Final private static ResourceLocation CHAIN_TEXTURE;

    @Shadow(remap = false)
    public static void renderChainCube(Vec3 to, PoseStack poseStack, VertexConsumer buffer, int packedLightIn, int setOverlay) {
    }

    @Inject(method = "render(Lcom/github/L_Ender/cataclysm/entity/projectile/Player_Ceraunus_Entity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("HEAD"), remap = false, cancellable = true)
    private void pec$render(Player_Ceraunus_Entity entity, float yaw, float tickDelta, PoseStack matrices, MultiBufferSource provider, int light, CallbackInfo ci){
        matrices.pushPose();

        float yRot = Mth.lerp(tickDelta, entity.yRotO, entity.getYRot());
        float xRot = Mth.lerp(tickDelta, entity.xRotO, entity.getXRot());

        matrices.mulPose(Axis.YP.rotationDegrees(yRot - 90.0F));
        matrices.mulPose(Axis.ZP.rotationDegrees(xRot + 90.0F));

        VertexConsumer vertexConsumer = provider.getBuffer(this.model.renderType(getTextureLocation(entity)));
        model.renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY,1,1,1,1);

        matrices.popPose();
        Entity fromEntity = entity.getOwner();
        if (fromEntity != null) {
            Vec3 entityPos = entity.getPosition(tickDelta);

            PoseStack poseForModel = new PoseStack();
            poseForModel.mulPose(Axis.YP.rotationDegrees(yRot - 90.0F));
            poseForModel.mulPose(Axis.ZP.rotationDegrees(xRot + 90.0F));

            Vec3 fromPos = getPositionOfPriorMob(fromEntity, tickDelta);
            LocalPlayerPatch localPlayerPatch = ClientEngine.getInstance().getPlayerPatch();
            if(localPlayerPatch != null) {
                fromPos = AvalonAnimationUtils.getJointWorldPos(localPlayerPatch, Armatures.BIPED.get().handL);
            }
            Vec3 chainTo = fromPos.subtract(entityPos);

            VertexConsumer chainBuffer = provider.getBuffer(RenderType.entityCutoutNoCull(CHAIN_TEXTURE));
            renderChainCube(chainTo, matrices, chainBuffer, light, OverlayTexture.NO_OVERLAY);
        }
        ci.cancel();
    }
}
