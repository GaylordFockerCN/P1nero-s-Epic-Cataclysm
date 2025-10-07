package com.p1nero.p1nero_ec.mixin;

import com.github.L_Ender.cataclysm.items.Soul_Render;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Soul_Render.class)
public class Soul_RenderMixin {

    /**
     * 对use操作无效？
     */
    @Inject(method = "releaseUsing", at = @At("HEAD"), cancellable = true)
    private void pec$releaseUsing(ItemStack p_43394_, Level p_43395_, LivingEntity p_43396_, int p_43397_, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "getUseAnimation", at = @At("HEAD"), cancellable = true)
    private void pec$anim(ItemStack p_43417_, CallbackInfoReturnable<UseAnim> cir) {
        cir.setReturnValue(UseAnim.NONE);
    }

}
