package com.p1nero.p1nero_ec.skills;

import com.p1nero.p1nero_ec.gameassets.PECAnimations;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

public class TidalClawsInnateSkill extends PECWeaponInnateSkillBase {
    public TidalClawsInnateSkill(SkillBuilder<? extends Skill> builder) {
        super(builder);
    }

    @Override
    protected void tryExecuteSkill1(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
    }

    @Override
    public void executeSkill1(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
    }

    @Override
    public void executeSkill2(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        serverPlayerPatch.playAnimationSynchronized(PECAnimations.CLAW_SKILL2, 0.15F);
    }

    @Override
    public void executeSkill3(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        serverPlayerPatch.playAnimationSynchronized(PECAnimations.CLAW_SKILL3, 0.15F);
    }
}
