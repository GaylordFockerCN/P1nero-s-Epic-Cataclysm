package com.p1nero.p1nero_ec.mixin;

import com.github.L_Ender.cataclysm.items.Astrape;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Astrape.class)
public class AstrapeMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void cancelUse(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        cir.setReturnValue(InteractionResultHolder.pass(player.getItemInHand(hand)));
    }

    @Inject(method = "releaseUsing", at = @At("HEAD"), cancellable = true)
    private void cancelReleaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int timeCharged, CallbackInfo ci) {
        if (livingEntity instanceof Player) {
            ci.cancel();
        }
    }

    @Inject(method = "getUseAnimation", at = @At("HEAD"), cancellable = true)
    private void cancelGetUseAnimation(ItemStack stack, CallbackInfoReturnable<UseAnim> cir) {
        cir.setReturnValue(UseAnim.NONE);
    }
}
