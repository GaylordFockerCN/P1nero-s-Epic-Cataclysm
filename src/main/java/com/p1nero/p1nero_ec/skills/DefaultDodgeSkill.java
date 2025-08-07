package com.p1nero.p1nero_ec.skills;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.Input;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.network.client.CPSkillRequest;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.skill.dodge.StepSkill;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import java.util.UUID;

public class DefaultDodgeSkill extends StepSkill {
    private static final UUID EVENT_UUID = UUID.fromString("11bd5c76-fe77-11ed-be56-0242ac191981");
    public DefaultDodgeSkill(Builder builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container) {
        container.getExecutor().getEventListener().addEventListener(PlayerEventListener.EventType.DODGE_SUCCESS_EVENT, EVENT_UUID, (event) -> {
            //改为全局触发
//            ServerPlayer serverPlayer = event.getPlayerPatch().getOriginal();
//            serverPlayer.serverLevel().sendParticles(AvalonParticles.AVALON_ENTITY_AFTER_IMAGE.get(),
//                    serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(),1, Double.longBitsToDouble(serverPlayer.getId()), 0.05, 0.05, 0);
        });
    }

    @Override
    public void onRemoved(SkillContainer container) {
        container.getExecutor().getEventListener().removeListener(PlayerEventListener.EventType.DODGE_SUCCESS_EVENT, EVENT_UUID);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Object getExecutionPacket(SkillContainer skillContainer, FriendlyByteBuf args) {
        Input input = skillContainer.getClientExecutor().getOriginal().input;
        float pulse = Mth.clamp(0.3F + EnchantmentHelper.getSneakingSpeedBonus(skillContainer.getExecutor().getOriginal()), 0.0F, 1.0F);
        input.tick(false, pulse);
        int forward = input.up ? 1 : 0;
        int backward = input.down ? -1 : 0;
        int left = input.left ? 1 : 0;
        int right = input.right ? -1 : 0;
        int vertic = forward + backward;
        int horizon = left + right;
        float yRot = Minecraft.getInstance().gameRenderer.getMainCamera().getYRot();
        float degree = (float) (-(90 * horizon * (1 - Math.abs(vertic)) + 45 * vertic * horizon)) + yRot;
        int animation;
        if (vertic == 0) {
            if (horizon == 0) {
                animation = 0;
            } else {
                animation = horizon >= 0 ? 2 : 3;
            }
        } else {
            animation = vertic >= 0 ? 0 : 1;
        }

        CPSkillRequest packet = new CPSkillRequest(SkillSlots.DODGE);
        packet.getBuffer().writeInt(animation);
        packet.getBuffer().writeFloat((vertic == 0 && horizon != 0) ? yRot : degree);
        return packet;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ResourceLocation getSkillTexture() {
        return ResourceLocation.fromNamespaceAndPath(EpicFightMod.MODID, "textures/gui/skills/dodge/step.png");
    }
}
