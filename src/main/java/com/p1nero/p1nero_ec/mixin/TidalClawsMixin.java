package com.p1nero.p1nero_ec.mixin;

import com.github.L_Ender.cataclysm.items.Tidal_Claws;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

@Mixin(Tidal_Claws.class)
public class TidalClawsMixin extends Item {

    public TidalClawsMixin(Properties p_41383_) {
        super(p_41383_);
    }

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void tcr$use(Level level, Player user, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        if (EpicFightCapabilities.getEntityPatch(user, PlayerPatch.class).getEntityState().inaction()) {
            cir.setReturnValue(super.use(level, user, hand));
        }
    }

}
