package com.p1nero.p1nero_ec.gameassets;

import com.hm.efn.gameasset.EFNAnimations;
import com.p1nero.p1nero_ec.PECMod;
import com.p1nero.p1nero_ec.capability.item.CursedBowCapability;
import com.p1nero.p1nero_ec.capability.item.TidalClawCapability;
import com.p1nero.p1nero_ec.capability.item.WrathOfTheDesertCapability;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.api.collider.MultiOBBCollider;
import yesman.epicfight.api.collider.OBBCollider;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.RangedWeaponCapability;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

import java.util.function.Function;

@Mod.EventBusSubscriber(modid = PECMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PECWeaponPresets {
    public static final Collider BOW_SCAN = new MultiOBBCollider(2, 8, 4D, 8, 0.0D, 1, -8.5);

    public static final Function<Item, CapabilityItem.Builder> TIDAL_CLAW = (item) ->
            WeaponCapability.builder().category(CapabilityItem.WeaponCategories.FIST)
                    .styleProvider((entityPatch) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(new OBBCollider(0.75F, 1.4F, 1.4F, 0.0F, 0.0F, -1.0F))
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .canBePlacedOffhand(false)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            EFNAnimations.NF_CLAW_AUTO1,
                            EFNAnimations.NF_CLAW_AUTO2,
                            EFNAnimations.NF_CLAW_AUTO3,
                            EFNAnimations.NF_CLAW_DASH,
                            EFNAnimations.NF_CLAW_AIRSLASH)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, (itemStack -> PECSkills.TIDAL_CLAW_INNATE))
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SHOT, PECAnimations.CLAW_SHOOT)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, EFNAnimations.NF_CLAW_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, EFNAnimations.NF_CLAW_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, EFNAnimations.NF_CLAW_RUN)
                    .comboCancel((style) -> false)
                    .constructor(TidalClawCapability::new);

    public static final Function<Item, CapabilityItem.Builder> CERAUNUS = (item) ->
            WeaponCapability.builder().category(CapabilityItem.WeaponCategories.SWORD)
                    .styleProvider((entityPatch) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(new OBBCollider(0.75F, 1.4F, 1.4F, 0.0F, 0.0F, -1.0F))
                    .swingSound(EpicFightSounds.WHOOSH_BIG.get())
                    .hitSound(EpicFightSounds.BLUNT_HIT.get())
                    .hitParticle(EpicFightParticles.HIT_BLUNT.get())
                    .canBePlacedOffhand(false)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            EFNAnimations.ARC_AUTO1,
                            EFNAnimations.ARC_AUTO2,
                            EFNAnimations.ARC_AUTO3,
                            Animations.GREATSWORD_DASH,
                            Animations.GREATSWORD_DASH)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, (itemStack -> PECSkills.CERAUNUS_INNATE))
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.GREATSWORD_GUARD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, EFNAnimations.ARC_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK_GREATSWORD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_GREATSWORD)
                    .comboCancel((style) -> false);

    public static final Function<Item, CapabilityItem.Builder> SOUL_RENDER = (item) ->
            WeaponCapability.builder().category(CapabilityItem.WeaponCategories.SWORD)
                    .styleProvider((entityPatch) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(new MultiOBBCollider(3, 0.7, 0.7, 1.3F, 0.0F, 0.0F, -1.3F))
                    .swingSound(EpicFightSounds.WHOOSH_BIG.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, (itemStack -> PECSkills.SOUL_RENDER_INNATE))
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            EFNAnimations.NF_MEEN_AUTO1,
                            EFNAnimations.NF_MEEN_AUTO2,
                            EFNAnimations.NF_MEEN_AUTO3,
                            Animations.SPEAR_DASH,
                            EFNAnimations.NF_MEEN_AIRSLASH)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SPEAR_GUARD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_HOLD_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK_SPEAR)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
                    .comboCancel((style) -> false);

    public static final Function<Item, CapabilityItem.Builder> THE_INCINERATOR = (item) ->
            WeaponCapability.builder().category(CapabilityItem.WeaponCategories.GREATSWORD)
                    .styleProvider((entityPatch) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(new MultiOBBCollider(3, 0.6, 0.6, 1.8F, 0.0F, 0.0F, -1.3F))
                    .swingSound(EpicFightSounds.WHOOSH_BIG.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .canBePlacedOffhand(false)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, (itemStack -> PECSkills.THE_INCINERATOR_INNATE))
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                            EFNAnimations.NG_GREATSWORD_AUTO1,
                            EFNAnimations.NG_GREATSWORD_AUTO2,
                            EFNAnimations.NG_GREATSWORD_AUTO3,
                            EFNAnimations.NG_GREATSWORD_DASH,
                            EFNAnimations.NG_GREATSWORD_AIRSLASH)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.GREATSWORD_GUARD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, EFNAnimations.NG_GREATSWORD_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, EFNAnimations.NG_GREATSWOED_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, EFNAnimations.NG_GREATSWORD_RUN)
                    .comboCancel((style) -> false);

    public static final Function<Item, CapabilityItem.Builder> WRATH_OF_THE_DESERT = (item) ->
            RangedWeaponCapability.builder()
            .zoomInType(CapabilityItem.ZoomInType.USE_TICK)
            .addAnimationsModifier(LivingMotions.IDLE, Animations.BIPED_IDLE)
                    .addAnimationsModifier(LivingMotions.WALK, Animations.BIPED_WALK)
                    .addAnimationsModifier(LivingMotions.AIM, Animations.BIPED_BOW_AIM)
                    .addAnimationsModifier(LivingMotions.SHOT, Animations.BIPED_BOW_SHOT)
            .constructor(WrathOfTheDesertCapability::new);


    public static final Function<Item, CapabilityItem.Builder> CURSED_BOW = (item) ->
            RangedWeaponCapability.builder()
                    .zoomInType(CapabilityItem.ZoomInType.USE_TICK)
                    .addAnimationsModifier(LivingMotions.IDLE, Animations.BIPED_IDLE)
                    .addAnimationsModifier(LivingMotions.WALK, Animations.BIPED_WALK)
                    .addAnimationsModifier(LivingMotions.AIM, Animations.BIPED_BOW_AIM)
                    .addAnimationsModifier(LivingMotions.SHOT, Animations.BIPED_BOW_SHOT)
                    .constructor(CursedBowCapability::new);

    @SubscribeEvent
    public static void register(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(PECMod.MOD_ID, "tidal_claw"), TIDAL_CLAW);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(PECMod.MOD_ID, "ceraunus"), CERAUNUS);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(PECMod.MOD_ID, "soul_render"), SOUL_RENDER);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(PECMod.MOD_ID, "the_incinerator"), THE_INCINERATOR);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(PECMod.MOD_ID, "wrath_of_the_desert"), WRATH_OF_THE_DESERT);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(PECMod.MOD_ID, "cursed_bow"), CURSED_BOW);
    }
}
