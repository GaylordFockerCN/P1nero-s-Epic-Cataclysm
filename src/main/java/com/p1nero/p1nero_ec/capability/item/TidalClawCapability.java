package com.p1nero.p1nero_ec.capability.item;

import net.minecraft.world.item.UseAnim;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

public class TidalClawCapability extends WeaponCapability {
    public TidalClawCapability(CapabilityItem.Builder builder) {
        super(builder);
    }

    @Override
    public UseAnim getUseAnimation(LivingEntityPatch<?> entityPatch) {
        return UseAnim.BLOCK;
    }
}
