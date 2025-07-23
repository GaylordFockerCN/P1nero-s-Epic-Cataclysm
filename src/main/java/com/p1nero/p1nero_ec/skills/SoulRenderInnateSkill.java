package com.p1nero.p1nero_ec.skills;

import com.p1nero.p1nero_ec.capability.PECPlayer;
import com.p1nero.p1nero_ec.gameassets.PECAnimations;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

public class SoulRenderInnateSkill extends PECWeaponInnateSkillBase {
    public SoulRenderInnateSkill(SkillBuilder<? extends Skill> builder) {
        super(builder);
    }

    @Override
    protected void tryExecuteSkill1(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        if (PECPlayer.consumeSkillPoint(serverPlayerPatch.getOriginal(), 2)) {
            executeSkill1(serverPlayerPatch, container);
        } else {
            onSkillPointNotEnough(container, 2);
        }
    }

    @Override
    public void executeSkill1(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        serverPlayerPatch.playAnimationSynchronized(PECAnimations.SOUL_RENDER_SKILL1, 0.15F);
    }

    @Override
    public void executeSkill2(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        serverPlayerPatch.playAnimationSynchronized(PECAnimations.SOUL_RENDER_SKILL2, 0.15F);
    }

    @Override
    public void executeSkill3(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        serverPlayerPatch.playAnimationSynchronized(PECAnimations.SOUL_RENDER_SKILL3, 0.15F);
    }
}
