package com.p1nero.p1nero_ec.mixin;

import com.p1nero.p1nero_ec.client.KeyMappings;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Minecraft.class)
public class MixinMinecraft {
    @Inject(at = @At(value = "HEAD"), method = "handleKeybinds()V")
    private void pec$handleKeybinds(CallbackInfo info) {
        KeyMappings.KeyPressHandler.handleKeyPress();
    }
}