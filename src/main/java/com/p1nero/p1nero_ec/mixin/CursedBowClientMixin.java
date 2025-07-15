package com.p1nero.p1nero_ec.mixin;

import com.github.L_Ender.cataclysm.items.Cursed_bow;
import com.p1nero.p1nero_ec.animations.ScanAttackAnimation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.AnimationPlayer;
import yesman.epicfight.client.ClientEngine;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;

import static com.github.L_Ender.cataclysm.items.Wrath_of_the_desert.getUseTime;

@Mixin(Cursed_bow.class)
public abstract class CursedBowClientMixin extends Item {
    public CursedBowClientMixin(Properties p_41383_) {
        super(p_41383_);
    }

    @Shadow public abstract int getUseDuration(@NotNull ItemStack stack);

    @Shadow(remap = false)
    public static void setUseTime(ItemStack stack, int useTime) {
    }


    @Shadow(remap = false)
    private static int getMaxLoadTime() {
        return 0;
    }

    @Inject(method = "inventoryTick", at = @At("HEAD"), cancellable = true)
    private void pec$inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean held, CallbackInfo ci) {
        super.inventoryTick(stack, level, entity, i, held);
        boolean using = entity instanceof LivingEntity living && living.getUseItem().equals(stack);
        int useTime = getUseTime(stack);
        if (level.isClientSide) {

            CompoundTag tag = stack.getOrCreateTag();
            if (tag.getInt("PrevUseTime") != tag.getInt("UseTime")) {
                tag.putInt("PrevUseTime", getUseTime(stack));
            }

            LocalPlayerPatch localPlayerPatch = ClientEngine.getInstance().getPlayerPatch();
            if(localPlayerPatch != null && localPlayerPatch.getEntityState().attacking()) {
                AnimationPlayer animationPlayer = localPlayerPatch.getAnimator().getPlayerFor(null);
                if(animationPlayer != null && animationPlayer.getAnimation().get() instanceof ScanAttackAnimation) {
                    setUseTime(stack, (int) (animationPlayer.getElapsedTime() * 40.0F));
                }
            } else {
                int maxLoadTime = getMaxLoadTime();
                if (using && useTime < maxLoadTime) {
                    int set = useTime + 1;
                    setUseTime(stack, set);
                }
            }
        }
        if (!using && useTime > 0.0F) {
            setUseTime(stack, Math.max(0, useTime - 5));
        }
        ci.cancel();
    }
}
