package com.p1nero.p1nero_ec.mixin;

import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Leviathan.Abyss_Blast_Entity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

@Mixin(Abyss_Blast_Entity.class)
public abstract class AbyssBlastEntityMixin extends Entity {

    @Shadow(remap = false) public LivingEntity caster;

    @Shadow(remap = false) public float renderYaw;

    @Shadow(remap = false) public float renderPitch;

    public AbyssBlastEntityMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Shadow(remap = false) public abstract float getYaw();

    @Shadow(remap = false) public abstract float getPitch();

    @Shadow(remap = false) public double endPosX;

    @Shadow(remap = false) public double endPosZ;

    @Shadow(remap = false) public double endPosY;

    @Inject(method = "tick", at = @At("TAIL"))
    private void pec$tick(CallbackInfo ci) {
        if (this.caster != null) {
            PlayerPatch<?> playerPatch = EpicFightCapabilities.getEntityPatch(this.caster, PlayerPatch.class);
            if(playerPatch != null) {
                this.renderYaw = this.getYaw();
                this.renderPitch = this.getPitch();
            }
        }
    }

    @Inject(method = "calculateEndPos", at = @At("HEAD"), cancellable = true, remap = false)
    private void pec$calculateEndPos(CallbackInfo ci) {
        this.endPosX = this.getX() + (double)50.0F * Math.cos(this.getYaw()) * Math.cos(this.getPitch());
        this.endPosZ = this.getZ() + (double)50.0F * Math.sin(this.getYaw()) * Math.cos(this.getPitch());
        this.endPosY = this.getY() + (double)50.0F * Math.sin(this.getPitch());
        ci.cancel();
    }

}
