package com.p1nero.p1nero_ec;

import com.mojang.logging.LogUtils;
import com.p1nero.p1nero_ec.client.PECSounds;
import com.p1nero.p1nero_ec.network.PECPacketHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(PECMod.MOD_ID)
public class PECMod {

    public static final String MOD_ID = "p1nero_ec";
    private static final Logger LOGGER = LogUtils.getLogger();

    public PECMod(FMLJavaModLoadingContext context) {
        IEventBus bus = context.getModEventBus();
        PECSounds.REGISTRY.register(bus);
        bus.addListener(this::commonSetup);
        context.registerConfig(ModConfig.Type.COMMON, PECConfig.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        PECPacketHandler.register();
    }

}
