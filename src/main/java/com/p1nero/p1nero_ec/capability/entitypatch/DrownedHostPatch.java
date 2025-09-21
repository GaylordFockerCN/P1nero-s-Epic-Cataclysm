package com.p1nero.p1nero_ec.capability.entitypatch;

import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.AcropolisMonsters.Drowned_Host_Entity;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.entity.PathfinderMob;
import yesman.epicfight.gameasset.MobCombatBehaviors;
import yesman.epicfight.world.capabilities.entitypatch.HumanoidMobPatch;
import yesman.epicfight.world.capabilities.entitypatch.mob.ZombiePatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.entity.ai.goal.AnimatedAttackGoal;
import yesman.epicfight.world.entity.ai.goal.CombatBehaviors;
import yesman.epicfight.world.entity.ai.goal.TargetChasingGoal;

public class DrownedHostPatch extends ZombiePatch<Drowned_Host_Entity> {
    protected void setWeaponMotions() {
        super.setWeaponMotions();
        this.weaponAttackMotions.put(CapabilityItem.WeaponCategories.TRIDENT, ImmutableMap.of(CapabilityItem.Styles.COMMON, MobCombatBehaviors.DROWNED_TRIDENT));
    }

    public void setAIAsInfantry(boolean holdingRanedWeapon) {
        CombatBehaviors.Builder<HumanoidMobPatch<?>> builder = this.getHoldingItemWeaponMotionBuilder();
        if (builder != null) {
            this.original.goalSelector.addGoal(0, new AnimatedAttackGoal(this, builder.build(this)));
            this.original.goalSelector.addGoal(1, new DrownedTargetChasingGoal(this, this.getOriginal(), (double)1.0F, true));
        }

    }

    static class DrownedTargetChasingGoal extends TargetChasingGoal {
        private final Drowned_Host_Entity drowned;

        public DrownedTargetChasingGoal(DrownedHostPatch mobpatch, PathfinderMob pathfinderMob, double speedModifier, boolean longMemory) {
            super(mobpatch, pathfinderMob, speedModifier, longMemory);
            this.drowned = mobpatch.getOriginal();
        }

        public boolean canUse() {
            return super.canUse() && this.drowned.getTarget() != null;
        }

        public boolean canContinueToUse() {
            return super.canContinueToUse() && this.drowned.getTarget() != null;
        }
    }
}
