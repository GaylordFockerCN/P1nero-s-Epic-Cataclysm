package com.p1nero.p1nero_ec.client.patched_entity_renderer;

import com.github.L_Ender.cataclysm.client.model.entity.Drowned_Host_Model;
import com.github.L_Ender.cataclysm.client.render.entity.Drowned_Host_Renderer;
import com.github.L_Ender.cataclysm.client.render.layer.Drowned_Host_Outer_Layer;
import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.AcropolisMonsters.Drowned_Host_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.p1nero.p1nero_ec.capability.entitypatch.DrownedHostPatch;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.client.model.Meshes;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.client.mesh.HumanoidMesh;
import yesman.epicfight.client.renderer.patched.entity.PHumanoidRenderer;
import yesman.epicfight.client.renderer.patched.layer.ModelRenderLayer;

@OnlyIn(Dist.CLIENT)
public class PDrownHostRenderer extends PHumanoidRenderer<Drowned_Host_Entity, DrownedHostPatch, Drowned_Host_Model<Drowned_Host_Entity>, Drowned_Host_Renderer, HumanoidMesh> {
    public PDrownHostRenderer(EntityRendererProvider.Context context, EntityType<?> entityType) {
        super(Meshes.BIPED, context, entityType);
        this.addPatchedLayer(Drowned_Host_Outer_Layer.class, new OuterLayerRenderer());
    }

    @OnlyIn(Dist.CLIENT)
    public static class OuterLayerRenderer extends ModelRenderLayer<Drowned_Host_Entity, DrownedHostPatch, Drowned_Host_Model<Drowned_Host_Entity>, Drowned_Host_Outer_Layer<Drowned_Host_Entity>, HumanoidMesh> {
        public static final ResourceLocation DROWNED_OUTER_LAYER = ResourceLocation.fromNamespaceAndPath("cataclysm", "textures/entity/sea/drowned_host_outer_layer.png");

        public OuterLayerRenderer() {
            super(Meshes.BIPED_OUTLAYER);
        }

        protected void renderLayer(DrownedHostPatch entitypatch, Drowned_Host_Entity entityliving, Drowned_Host_Outer_Layer<Drowned_Host_Entity> vanillaLayer, PoseStack poseStack, MultiBufferSource buffer, int packedLight, OpenMatrix4f[] poses, float bob, float yRot, float xRot, float partialTicks) {
            this.mesh.get().draw(poseStack, buffer, RenderType.entityCutoutNoCull(DROWNED_OUTER_LAYER), packedLight, 1.0F, 1.0F, 1.0F, 1.0F, LivingEntityRenderer.getOverlayCoords(entityliving, 0.0F), entitypatch.getArmature(), poses);
        }

        protected void initMesh() {
            this.mesh.get().initialize();
        }
    }

}