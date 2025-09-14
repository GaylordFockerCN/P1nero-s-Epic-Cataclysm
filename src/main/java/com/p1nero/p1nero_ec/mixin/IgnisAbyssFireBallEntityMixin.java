package com.p1nero.p1nero_ec.mixin;

import com.github.L_Ender.cataclysm.entity.projectile.Ignis_Abyss_Fireball_Entity;
import com.github.L_Ender.cataclysm.entity.projectile.Ignis_Fireball_Entity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

@Mixin(Ignis_Abyss_Fireball_Entity.class)
public abstract class IgnisAbyssFireBallEntityMixin extends AbstractHurtingProjectile {
    @Shadow(remap = false) private int timer;

    public IgnisAbyssFireBallEntityMixin(EntityType<? extends AbstractHurtingProjectile> p_36817_, double p_36818_, double p_36819_, double p_36820_, double p_36821_, double p_36822_, double p_36823_, Level p_36824_) {
        super(p_36817_, p_36818_, p_36819_, p_36820_, p_36821_, p_36822_, p_36823_, p_36824_);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void pec$tick(CallbackInfo ci) {
        if (this.timer == 0 || this.timer == -40) {
            Entity entity = this.getOwner();
            PlayerPatch<?> playerPatch = EpicFightCapabilities.getEntityPatch(entity, PlayerPatch.class);
            if (playerPatch != null && playerPatch.getTarget() != null) {
                LivingEntity target = playerPatch.getTarget();
                float speed = 0.2F;
                if (target == null) {
                    Vec3 view = entity.getViewVector(1.0F).normalize();
                    this.xPower = view.x * (double)speed;
                    this.yPower = view.y * (double)speed;
                    this.zPower = view.z * (double)speed;
                } else {
                    double dx = target.getX() - this.getX();
                    double dy = target.getY() + (double)(target.getBbHeight() * 0.5F) - this.getY();
                    double dz = target.getZ() - this.getZ();
                    double d = Math.sqrt(dx * dx + dy * dy + dz * dz);
                    dx /= d;
                    dy /= d;
                    dz /= d;
                    this.xPower = dx * (double)speed;
                    this.yPower = dy * (double)speed;
                    this.zPower = dz * (double)speed;
                }
            }
        }

    }
}
