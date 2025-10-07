package com.p1nero.p1nero_ec.mixin;

import com.github.L_Ender.cataclysm.items.Gauntlet_of_Guard;
import com.p1nero.p1nero_ec.client.KeyMappings;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = Gauntlet_of_Guard.class)
public class GauntletOfGuardClientMixin {

    @Inject(method = "appendHoverText", at = @At("HEAD"), cancellable = true)
    public void pec$appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn, CallbackInfo ci) {
        tooltip.add(Component.translatable("skill.p1nero_ec.desc").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("skill.p1nero_ec.gauntlet_of_guard.desc2", KeyMappings.SKILL_2.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD), 2).withStyle(ChatFormatting.AQUA));
        tooltip.add(Component.translatable("skill.p1nero_ec.gauntlet_of_guard.desc3", KeyMappings.SKILL_3.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD), 3).withStyle(ChatFormatting.AQUA));
        ci.cancel();
    }
}
