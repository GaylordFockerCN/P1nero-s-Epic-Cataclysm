package com.p1nero.p1nero_ec.skills;

import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

public class TheIncineratorInnateSkill extends PECWeaponInnateSkillBase {
    public TheIncineratorInnateSkill(SkillBuilder<? extends Skill> builder) {
        super(builder);
    }

    @Override
    public void executeSkill1(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        
    }

    @Override
    public void executeSkill2(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        System.out.println(2);
    }

    @Override
    public void executeSkill3(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        System.out.println(3);
        //枪蓄力下落
    }
}
