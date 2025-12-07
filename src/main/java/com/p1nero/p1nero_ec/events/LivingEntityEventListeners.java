package com.p1nero.p1nero_ec.events;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.p1nero.p1nero_ec.PECMod;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.damagesource.StunType;

@Mod.EventBusSubscriber(modid = PECMod.MOD_ID)
public class LivingEntityEventListeners {

    /**
     * 灾变硬直buff兼容
     */
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity living = event.getEntity();
        if (living.hasEffect(ModEffect.EFFECTSTUN.get())) {
            LivingEntityPatch<?> livingEntityPatch = EpicFightCapabilities.getEntityPatch(living, LivingEntityPatch.class);
            if (livingEntityPatch != null) {
                livingEntityPatch.applyStun(StunType.HOLD, 0.15F);
            }
        }
    }

}
