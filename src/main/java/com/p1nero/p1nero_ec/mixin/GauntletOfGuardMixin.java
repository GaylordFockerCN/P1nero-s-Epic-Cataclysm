package com.p1nero.p1nero_ec.mixin;

import com.github.L_Ender.cataclysm.items.Gauntlet_of_Guard;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Gauntlet_of_Guard.class)
public class GauntletOfGuardMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void cancelUse(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        cir.setReturnValue(InteractionResultHolder.pass(player.getItemInHand(hand)));
    }

    @Inject(method = "onUseTick", at = @At("HEAD"), cancellable = true)
    private void cancelOnUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int count, CallbackInfo ci) {
        if (livingEntity instanceof Player) {
            ci.cancel();
        }
    }
}
