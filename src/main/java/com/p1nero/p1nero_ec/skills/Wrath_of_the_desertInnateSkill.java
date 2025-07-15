package com.p1nero.p1nero_ec.skills;

import com.p1nero.p1nero_ec.capability.PECPlayer;
import com.p1nero.p1nero_ec.gameassets.PECAnimations;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

public class Wrath_of_the_desertInnateSkill extends PECWeaponInnateSkillBase {
    public Wrath_of_the_desertInnateSkill(SkillBuilder<? extends Skill> builder) {
        super(builder);
    }

    @Override
    protected void tryExecuteSkill3(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        if (PECPlayer.consumeSkillPoint(serverPlayerPatch.getOriginal(), 4)) {
            executeSkill3(serverPlayerPatch, container);
        } else {
            onSkillPointNotEnough(container, 4);
        }
    }

    @Override
    public void executeSkill1(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        serverPlayerPatch.playAnimationSynchronized(PECAnimations.BOW_SKILL1, 0.15F);
    }

    @Override
    public void executeSkill2(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        serverPlayerPatch.playAnimationSynchronized(PECAnimations.BOW_SKILL2, 0.15F);
    }

    @Override
    public void executeSkill3(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        serverPlayerPatch.playAnimationSynchronized(PECAnimations.BOW_SKILL3, 0.15F);
    }
}
