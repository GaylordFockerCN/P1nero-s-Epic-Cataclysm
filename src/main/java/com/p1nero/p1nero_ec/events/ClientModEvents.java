package com.p1nero.p1nero_ec.events;

import com.github.L_Ender.cataclysm.init.ModEntities;
import com.p1nero.p1nero_ec.PECMod;
import com.p1nero.p1nero_ec.client.item_renderer.RenderAnnihilator;
import com.p1nero.p1nero_ec.client.item_renderer.RenderGauntletGuard;
import com.p1nero.p1nero_ec.client.item_renderer.RenderTidalClaw;
import com.p1nero.p1nero_ec.client.patched_entity_renderer.PDrownHostRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.client.forgeevent.PatchedRenderersEvent;

@Mod.EventBusSubscriber(modid = PECMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerItemRenderer(PatchedRenderersEvent.RegisterItemRenderer event) {
        event.addItemRenderer(ResourceLocation.fromNamespaceAndPath(PECMod.MOD_ID, "tidal_claw"), RenderTidalClaw::new);
        event.addItemRenderer(ResourceLocation.fromNamespaceAndPath(PECMod.MOD_ID, "annihilator"), RenderAnnihilator::new);
        event.addItemRenderer(ResourceLocation.fromNamespaceAndPath(PECMod.MOD_ID, "gauntlet_guard"), RenderGauntletGuard::new);
    }

    @SubscribeEvent
    public static void onPatchedRenderer(PatchedRenderersEvent.Add event) {
        event.addPatchedEntityRenderer(ModEntities.DROWNED_HOST.get(), entityType -> new PDrownHostRenderer(event.getContext(), entityType).initLayerLast(event.getContext(), entityType));
    }

}
