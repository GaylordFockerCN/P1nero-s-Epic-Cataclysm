package com.p1nero.p1nero_ec.events;

import com.p1nero.p1nero_ec.PECMod;
import com.p1nero.p1nero_ec.client.item_renderer.RenderTidalClaw;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.client.forgeevent.PatchedRenderersEvent;

@Mod.EventBusSubscriber(modid = PECMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {

    @SubscribeEvent
    public static void EFNSheath(PatchedRenderersEvent.RegisterItemRenderer event) {
        event.addItemRenderer(ResourceLocation.fromNamespaceAndPath(PECMod.MOD_ID,"tidal_claw"), RenderTidalClaw::new);
    }
}
