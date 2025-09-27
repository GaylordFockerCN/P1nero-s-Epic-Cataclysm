package com.p1nero.p1nero_ec.skills;

import com.google.common.collect.Lists;
import com.p1nero.p1nero_ec.capability.PECPlayer;
import com.p1nero.p1nero_ec.client.KeyMappings;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 技能基本类，按123按键触发
 */
public abstract class PECWeaponInnateSkillBase extends Skill {
    public PECWeaponInnateSkillBase(SkillBuilder<? extends Skill> builder) {
        super(builder);
    }

    public static SkillBuilder<Skill> createBuilder() {
        return new SkillBuilder<>().setCategory(SkillCategories.WEAPON_INNATE).setResource(Resource.NONE);
    }

    @Override
    public void executeOnServer(SkillContainer container, FriendlyByteBuf args) {
        int skillId = args.readInt();
        ServerPlayerPatch serverPlayerPatch = container.getServerExecutor();
        if(!PECPlayer.isValidWeapon(serverPlayerPatch.getOriginal().getMainHandItem())) {
            return;
        }
        switch (skillId) {
            case 1 -> tryExecuteSkill1(serverPlayerPatch, container);
            case 2 -> tryExecuteSkill2(serverPlayerPatch, container);
            case 3 -> tryExecuteSkill3(serverPlayerPatch, container);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public boolean hasSkillKeyIn(KeyMapping keyMapping) {
        return getAvailableKeys().contains(keyMapping);
    }

    @OnlyIn(Dist.CLIENT)
    protected List<KeyMapping> getAvailableKeys() {
        return List.of(KeyMappings.SKILL_1, KeyMappings.SKILL_2, KeyMappings.SKILL_3);
    }

    public int getSkillRequired(int slot) {
        return slot;
    }

    public boolean canExecute(PlayerPatch<?> playerPatch, int slot) {
        return PECPlayer.hasSkillPoint(playerPatch.getOriginal(), slot) && isExecutableState(playerPatch);
    }

    protected void tryExecuteSkill(ServerPlayerPatch serverPlayerPatch, SkillContainer container, int slot) {
        int skillRequired = this.getSkillRequired(slot);
        if (PECPlayer.consumeSkillPoint(serverPlayerPatch.getOriginal(), skillRequired)) {
            executeSkill1(serverPlayerPatch, container);
        } else {
            onSkillPointNotEnough(container, slot, skillRequired);
        }
    }

    protected void tryExecuteSkill1(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        if (PECPlayer.consumeSkillPoint(serverPlayerPatch.getOriginal(), 1)) {
            executeSkill1(serverPlayerPatch, container);
        } else {
            onSkillPointNotEnough(container, 1);
        }
    }

    protected void tryExecuteSkill2(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        if (PECPlayer.consumeSkillPoint(serverPlayerPatch.getOriginal(), 2)) {
            executeSkill2(serverPlayerPatch, container);
        } else {
            onSkillPointNotEnough(container, 2);
        }
    }

    protected void tryExecuteSkill3(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        if (PECPlayer.consumeSkillPoint(serverPlayerPatch.getOriginal(), 3)) {
            executeSkill3(serverPlayerPatch, container);
        } else {
            onSkillPointNotEnough(container, 3);
        }
    }

    public abstract void executeSkill1(ServerPlayerPatch serverPlayerPatch, SkillContainer container);

    public abstract void executeSkill2(ServerPlayerPatch serverPlayerPatch, SkillContainer container);

    public abstract void executeSkill3(ServerPlayerPatch serverPlayerPatch, SkillContainer container);

    public void executeSkill(ServerPlayerPatch serverPlayerPatch, SkillContainer container, int slot){};

    public void onSkillPointNotEnough(SkillContainer container, int need) {
        container.getExecutor().getOriginal().displayClientMessage(Component.translatable("info.p1nero_ec.skill_point_lack", need), true);
    }

    public void onSkillPointNotEnough(SkillContainer container, int slot, int need) {
        container.getExecutor().getOriginal().displayClientMessage(Component.translatable("info.p1nero_ec.skill_point_lack", need), true);
    }

    @Override
    public List<Component> getTooltipOnItem(ItemStack itemStack, CapabilityItem cap, PlayerPatch<?> playerpatch) {
        List<Component> list = Lists.newArrayList();
        list.add(Component.literal(""));
        return list;
    }
}
