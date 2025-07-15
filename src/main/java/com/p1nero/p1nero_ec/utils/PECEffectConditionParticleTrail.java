package com.p1nero.p1nero_ec.utils;

import com.merlin204.avalon.util.AvalonAnimationUtils;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import yesman.epicfight.api.animation.AnimationPlayer;
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Armatures;

public class PECEffectConditionParticleTrail {
    public static AnimationEvent.InPeriodEvent buffedParticleTrail(int startFrame, int endFrame, InteractionHand hand, Vec3 startOffset, Vec3 endOffset, float timeInterpolation, int particleCount, ParticleOptions particleOptions, float randomX, float randomY, float randomZ, float velocityX, float velocityY, float velocityZ) {

        float start = (float)startFrame / 60.0F;
        float end = (float)endFrame / 60.0F;

        Joint joint = null;
        switch (hand) {
            case MAIN_HAND -> joint = Armatures.BIPED.get().toolR;
            case OFF_HAND -> joint = Armatures.BIPED.get().toolL;
        }

        Joint finalJoint = joint;

        return AnimationEvent.InPeriodEvent.create(start, end, (entityPatch, self, params) -> {
            if (!(entityPatch.getOriginal() instanceof LivingEntity)) {
                return;
            }

            LivingEntity living = entityPatch.getOriginal();

            AnimationPlayer player = entityPatch.getAnimator().getPlayerFor(null);
            float prevElapsedTime = 0;
            if (player != null) {
                prevElapsedTime = player.getPrevElapsedTime();
            }
            float elapsedTime = 0;
            if (player != null) {
                elapsedTime = player.getElapsedTime();
            }
            float step = (elapsedTime - prevElapsedTime) / timeInterpolation;

            Vec3f trailDirection = new Vec3f(
                    (float)(endOffset.x - startOffset.x),
                    (float)(endOffset.y - startOffset.y),
                    (float)(endOffset.z - startOffset.z)
            );

            for(float f = prevElapsedTime; f <= elapsedTime; f += step) {
                for(int i = 0; i <= particleCount; ++i) {
                    float ratio = (float)i / (float)particleCount;
                    Vec3f pointOffset = new Vec3f(
                            (float)(startOffset.x + (double)(trailDirection.x * ratio)),
                            (float)(startOffset.y + (double)(trailDirection.y * ratio)),
                            (float)(startOffset.z + (double)(trailDirection.z * ratio))
                    );
                    double randX = (Math.random() - 0.5) * (double)randomX;
                    double randY = (Math.random() - 0.5) * (double)randomY;
                    double randZ = (Math.random() - 0.5) * (double)randomZ;
                    Vec3 worldPos = AvalonAnimationUtils.getJointWorldRawPos(
                            entityPatch, finalJoint, f + step, pointOffset);

                    if (living.level().isClientSide) {
                        living.level().addParticle(
                                particleOptions,
                                worldPos.x + randX,
                                worldPos.y + randY,
                                worldPos.z + randZ,
                                velocityX, velocityY, velocityZ
                        );
                    }
                }
            }

        }, AnimationEvent.Side.CLIENT);
    }

    public static AnimationEvent.InPeriodEvent buffedParticleTrail(
            int startFrame, int endFrame, InteractionHand hand,
            Vec3 startOffset, Vec3 endOffset,
            float timeInterpolation, int particleCount,
            ParticleOptions particleOptions, float random) {
        return buffedParticleTrail(startFrame, endFrame, hand,
                startOffset, endOffset, timeInterpolation,
                particleCount, particleOptions,
                random, random, random,
                0.0f, 0.0f, 0.0f);
    }

    public static AnimationEvent.InPeriodEvent buffedParticleTrail(
            int startFrame, int endFrame, InteractionHand hand,
            Vec3 startOffset, Vec3 endOffset,
            float timeInterpolation, int particleCount,
            ParticleOptions particleOptions,
            float randomX, float randomY, float randomZ) {
        return buffedParticleTrail(startFrame, endFrame, hand,
                startOffset, endOffset, timeInterpolation,
                particleCount, particleOptions,
                randomX, randomY, randomZ,
                0.0f, 0.0f, 0.0f);
    }
}