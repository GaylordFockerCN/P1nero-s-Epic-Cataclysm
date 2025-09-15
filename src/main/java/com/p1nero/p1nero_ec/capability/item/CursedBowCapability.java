package com.p1nero.p1nero_ec.capability.item;

import com.p1nero.p1nero_ec.gameassets.PECAnimations;
import com.p1nero.p1nero_ec.gameassets.PECSkills;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.particle.HitParticleType;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.RangedWeaponCapability;
import yesman.epicfight.world.capabilities.item.Style;

import javax.annotation.Nullable;
import java.util.List;

public class CursedBowCapability extends RangedWeaponCapability {
    private List<AnimationManager.AnimationAccessor<? extends AttackAnimation>> attackMotion;
    private List<AnimationManager.AnimationAccessor<? extends AttackAnimation>> mountAttackMotion;

    public CursedBowCapability(CapabilityItem.Builder builder) {
        super(builder);
        this.attackMotion = List.of(PECAnimations.BOW_1, PECAnimations.BOW_2, PECAnimations.BOW_1, PECAnimations.BOW_1);
        this.mountAttackMotion = List.of(Animations.SWORD_MOUNT_ATTACK);
    }

    public Style getStyle(LivingEntityPatch<?> entitypatch) {
        return Styles.TWO_HAND;
    }

    public SoundEvent getHitSound() {
        return EpicFightSounds.BLUNT_HIT.get();
    }

    public HitParticleType getHitParticle() {
        return EpicFightParticles.HIT_BLUNT.get();
    }

    public List<AnimationManager.AnimationAccessor<? extends AttackAnimation>> getAutoAttackMotion(PlayerPatch<?> playerpatch) {
        return this.attackMotion;
    }

    public List<AnimationManager.AnimationAccessor<? extends AttackAnimation>> getMountAttackMotion() {
        return this.mountAttackMotion;
    }

    @Override
    public LivingMotion getLivingMotion(LivingEntityPatch<?> entityPatch, InteractionHand hand) {
        return entityPatch.getOriginal().isUsingItem() && entityPatch.getOriginal().getUseItem().getUseAnimation() == UseAnim.BOW ? LivingMotions.AIM : null;
    }

    @Nullable
    public Skill getInnateSkill(PlayerPatch<?> playerpatch, ItemStack itemstack) {
        return null;
    }
}
