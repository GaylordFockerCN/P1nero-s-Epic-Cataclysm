package com.p1nero.p1nero_ec.utils;

import com.github.L_Ender.cataclysm.init.ModParticle;
import com.merlin204.avalon.util.AvalonAnimationUtils;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import yesman.epicfight.api.animation.AnimationPlayer;
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.model.armature.HumanoidArmature;

import static com.hm.efn.util.ParticleEffectInvoker.spawnCustomParticle;

public class PECParticleEffectInvoker {

    public static AnimationEvent.InTimeEvent spawnExpParticle(
            int startFrame,
            double forwardOffset,
            double rightOffset,
            double upOffset) {
        return spawnCustomParticle(startFrame, forwardOffset, rightOffset, upOffset, ModParticle.IGNIS_ABYSS_EXPLODE.get());
    }

    public static AnimationEvent.InPeriodEvent createVoidRingEffect(int startFrame, int endFrame) {
        float start = startFrame / 60.0f;
        float end = endFrame / 60.0f;

        return AnimationEvent.InPeriodEvent.create(start, end, (entitypatch, self, params) -> {
            if (entitypatch.getOriginal().level().isClientSide()) {
                LivingEntity entity = entitypatch.getOriginal();
                ClientLevel level = (ClientLevel) entity.level();
                Vec3 center = entity.position().add(0, 0.1, 0);

                for (int i = 0; i < 50; i++) {
                    float angle = (float) (2 * Math.PI * i / 36);
                    float radius = 4f;

                    level.addParticle(
                            ParticleTypes.FALLING_OBSIDIAN_TEAR,
                            center.x + radius * Mth.cos(angle),
                            center.y,
                            center.z + radius * Mth.sin(angle),
                            0, 0.05f, 0  // 轻微上下浮动
                    );

                    if (i % 8 == 0) {
                        level.addParticle(
                                ParticleTypes.END_ROD,
                                center.x + radius * Mth.cos(angle),
                                center.y + 0.1f,
                                center.z + radius * Mth.sin(angle),
                                0, 0.1f, 0
                        );
                    }
                }

            }
        }, AnimationEvent.Side.CLIENT);
    }


    public static AnimationEvent.InPeriodEvent createLavaRingEffect(int startFrame, int endFrame) {
        float start = startFrame / 60.0f;
        float end = endFrame / 60.0f;

        return AnimationEvent.InPeriodEvent.create(start, end, (entitypatch, self, params) -> {
            if (entitypatch.getOriginal().level().isClientSide()) {
                LivingEntity entity = entitypatch.getOriginal();
                ClientLevel level = (ClientLevel) entity.level();
                Vec3 center = entity.position().add(0, 0.1, 0);

                for (int i = 0; i < 50; i++) {
                    float angle = (float) (2 * Math.PI * i / 36);
                    float radius = 6.5f;

                    level.addParticle(
                            ParticleTypes.LAVA,
                            center.x + radius * Mth.cos(angle),
                            center.y,
                            center.z + radius * Mth.sin(angle),
                            0, 0.05f, 0  // 轻微上下浮动
                    );

                    if (i % 8 == 0) {
                        level.addParticle(
                                ParticleTypes.FLAME,
                                center.x + radius * Mth.cos(angle),
                                center.y + 0.1f,
                                center.z + radius * Mth.sin(angle),
                                0, 0.1f, 0
                        );
                    }
                }

            }
        }, AnimationEvent.Side.CLIENT);
    }

    public static AnimationEvent.InTimeEvent createMagmaEruption(float startFrame) {
        float time = startFrame / 60.0F;
        return AnimationEvent.InTimeEvent.create(time, (entitypatch, self, params) -> {
            if (entitypatch.getOriginal().level().isClientSide()) {
                LivingEntity entity = entitypatch.getOriginal();
                ClientLevel level = (ClientLevel) entity.level();
                Vec3 center = entity.position().add(0, 0.1, 0);

                for (int i = 0; i < 12; i++) {
                    double spread = 0.7;
                    Vec3 spawnPos = new Vec3(
                            center.x + (Math.random() - 0.5) * spread,
                            center.y + (Math.random() - 0.5) * spread * 0.5,
                            center.z + (Math.random() - 0.5) * spread
                    );

                    Vec3 velocity = new Vec3(
                            (Math.random() - 0.5) * 0.25,
                            Math.random() * 0.4 + 0.3,
                            (Math.random() - 0.5) * 0.25
                    );

                    ParticleOptions type = Math.random() < 0.8 ?
                            ParticleTypes.LAVA : ParticleTypes.FLAME;

                    level.addParticle(
                            type,
                            spawnPos.x,
                            spawnPos.y,
                            spawnPos.z,
                            velocity.x,
                            velocity.y,
                            velocity.z
                    );
                }

                for (int spiral = 0; spiral < 8; spiral++) {
                    float baseAngle = (float) (spiral * Math.PI * 0.25);
                    for (int layer = 0; layer < 5; layer++) {
                        float progress = layer / 4f;
                        double angle = baseAngle + progress * Math.PI * 2;
                        float radius = 0.3f + progress * 0.7f;
                        float yPos = (float) (center.y + progress * 2.5f);

                        Vec3 pos = new Vec3(
                                center.x + radius * Math.cos(angle),
                                yPos,
                                center.z + radius * Math.sin(angle)
                        );

                        level.addParticle(
                                ParticleTypes.FLAME,
                                pos.x, pos.y, pos.z,
                                Math.cos(angle) * 0.1f,
                                0.2f,
                                Math.sin(angle) * 0.1f
                        );
                    }
                }

                for (int i = 0; i < 24; i++) {
                    double angle = Math.random() * 2 * Math.PI;
                    double radius = Math.random() * 1.8;
                    Vec3 pos = new Vec3(
                            center.x + radius * Math.cos(angle),
                            center.y,
                            center.z + radius * Math.sin(angle)
                    );

                    level.addParticle(
                            ParticleTypes.LAVA,
                            pos.x, pos.y, pos.z,
                            Math.cos(angle) * 0.05,
                            0.02,
                            Math.sin(angle) * 0.05
                    );

                    if (i % 3 == 0) {
                        level.addParticle(
                                ParticleTypes.SMALL_FLAME,
                                pos.x, pos.y + 0.1, pos.z,
                                Math.cos(angle) * 0.1,
                                0.15,
                                Math.sin(angle) * 0.1
                        );
                    }
                }
            }
        }, AnimationEvent.Side.CLIENT);
    }

    public static AnimationEvent.InTimeEvent createForwardMagmaEruption(float startFrame) {
        float time = startFrame / 60.0F;
        return AnimationEvent.InTimeEvent.create(time, (entitypatch, self, params) -> {
            if (entitypatch.getOriginal().level().isClientSide()) {
                LivingEntity entity = entitypatch.getOriginal();
                ClientLevel level = (ClientLevel) entity.level();

                Vec3 lookVec = entity.getLookAngle();

                Vec3 center = entity.position()
                        .add(0, 0.1, 0)
                        .add(lookVec.x * 1.5, 0, lookVec.z * 1.5);

                for (int i = 0; i < 12; i++) {
                    double spread = 0.7;
                    Vec3 spawnPos = new Vec3(
                            center.x + (Math.random() - 0.5) * spread,
                            center.y + (Math.random() - 0.5) * spread * 0.5,
                            center.z + (Math.random() - 0.5) * spread
                    );

                    Vec3 velocity = new Vec3(
                            (Math.random() - 0.5) * 0.25,
                            Math.random() * 0.4 + 0.3,
                            (Math.random() - 0.5) * 0.25
                    );

                    ParticleOptions type = Math.random() < 0.8 ?
                            ParticleTypes.LAVA : ParticleTypes.FLAME;

                    level.addParticle(
                            type,
                            spawnPos.x,
                            spawnPos.y,
                            spawnPos.z,
                            velocity.x,
                            velocity.y,
                            velocity.z
                    );
                }

                for (int spiral = 0; spiral < 8; spiral++) {
                    float baseAngle = (float) (spiral * Math.PI * 0.25);
                    for (int layer = 0; layer < 5; layer++) {
                        float progress = layer / 4f;
                        double angle = baseAngle + progress * Math.PI * 2;
                        float radius = 0.3f + progress * 0.7f;
                        float yPos = (float) (center.y + progress * 2.5f);

                        Vec3 pos = new Vec3(
                                center.x + radius * Math.cos(angle),
                                yPos,
                                center.z + radius * Math.sin(angle)
                        );

                        level.addParticle(
                                ParticleTypes.FLAME,
                                pos.x, pos.y, pos.z,
                                Math.cos(angle) * 0.1f,
                                0.2f,
                                Math.sin(angle) * 0.1f
                        );
                    }
                }

                for (int i = 0; i < 24; i++) {
                    double angle = Math.random() * 2 * Math.PI;
                    double radius = Math.random() * 1.8;
                    Vec3 pos = new Vec3(
                            center.x + radius * Math.cos(angle),
                            center.y,
                            center.z + radius * Math.sin(angle)
                    );

                    level.addParticle(
                            ParticleTypes.LAVA,
                            pos.x, pos.y, pos.z,
                            Math.cos(angle) * 0.05,
                            0.02,
                            Math.sin(angle) * 0.05
                    );

                    if (i % 3 == 0) {
                        level.addParticle(
                                ParticleTypes.SMALL_FLAME,
                                pos.x, pos.y + 0.1, pos.z,
                                Math.cos(angle) * 0.1,
                                0.15,
                                Math.sin(angle) * 0.1
                        );
                    }
                }
            }
        }, AnimationEvent.Side.CLIENT);
    }

    public static AnimationEvent.InPeriodEvent createForwardFlameJetParticles(int startFrame, int endFrame) {
        float start = startFrame / 60.0f;
        float end = endFrame / 60.0f;

        return AnimationEvent.InPeriodEvent.create(start, end, (entitypatch, self, params) -> {
            if (entitypatch.getOriginal().level().isClientSide()) {
                LivingEntity entity = entitypatch.getOriginal();
                ClientLevel level = (ClientLevel) entity.level();

                Vec3 center = entity.position().add(0, 0.1, 0);
                float yawRadians = entity.getYRot() * ((float) Math.PI / 180F);

                float width = 5f;
                float length = 12.0f;

                Vec3[] corners = new Vec3[4];
                corners[0] = new Vec3(-width / 2, 0, 1.0);      // 近端左侧
                corners[1] = new Vec3(width / 2, 0, 1.0);       // 近端右侧
                corners[2] = new Vec3(-width / 2, 0, 1.0 + length); // 远端左侧
                corners[3] = new Vec3(width / 2, 0, 1.0 + length);  // 远端右侧

                for (int i = 0; i < 4; i++) {
                    double rotatedX = corners[i].x * Math.cos(yawRadians) - corners[i].z * Math.sin(yawRadians);
                    double rotatedZ = corners[i].x * Math.sin(yawRadians) + corners[i].z * Math.cos(yawRadians);
                    corners[i] = new Vec3(rotatedX, corners[i].y, rotatedZ).add(center);
                }

                generateRectangleParticles(level, corners, yawRadians);
            }
        }, AnimationEvent.Side.CLIENT);
    }

    private static void generateRectangleParticles(ClientLevel level, Vec3[] corners, float yawRadians) {
        int particlesPerRow = 5;
        int particlesPerColumn = 12;

        for (int row = 0; row < particlesPerRow; row++) {
            for (int col = 0; col < particlesPerColumn; col++) {
                float u = (float) row / (particlesPerRow - 1);
                float v = (float) col / (particlesPerColumn - 1);

                Vec3 topPos = corners[0].add(corners[1].subtract(corners[0]).scale(u));
                Vec3 bottomPos = corners[2].add(corners[3].subtract(corners[2]).scale(u));
                Vec3 particlePos = topPos.add(bottomPos.subtract(topPos).scale(v));

                double randomOffsetX = (Math.random() - 0.5) * 0.3;
                double randomOffsetZ = (Math.random() - 0.5) * 0.3;

                level.addParticle(
                        ParticleTypes.LAVA,
                        particlePos.x + randomOffsetX,
                        particlePos.y,
                        particlePos.z + randomOffsetZ,
                        0, 0.05f, 0
                );

                if (col % 3 == 0 && row % 2 == 0) {
                    level.addParticle(
                            ParticleTypes.FLAME,
                            particlePos.x + randomOffsetX,
                            particlePos.y + 0.1f,
                            particlePos.z + randomOffsetZ,
                            0, 0.1f, 0
                    );
                }
            }
        }

        for (int i = 0; i < 15; i++) {
            float progress = (float) i / 14;
            Vec3 centerPos = corners[0].add(corners[1].subtract(corners[0]).scale(0.5))
                    .add(corners[2].subtract(corners[0]).scale(progress));

            level.addParticle(
                    ParticleTypes.FLAME,
                    centerPos.x,
                    centerPos.y + 0.2f,
                    centerPos.z,
                    (Math.random() - 0.5) * 0.05,
                    0.15f,
                    (Math.random() - 0.5) * 0.05
            );
        }
    }

    public static AnimationEvent.InPeriodEvent createForwardEnderJetParticles(int startFrame, int endFrame) {
        float start = startFrame / 60.0f;
        float end = endFrame / 60.0f;

        return AnimationEvent.InPeriodEvent.create(start, end, (entitypatch, self, params) -> {
            if (entitypatch.getOriginal().level().isClientSide()) {
                LivingEntity entity = entitypatch.getOriginal();
                ClientLevel level = (ClientLevel) entity.level();

                Vec3 center = entity.position().add(0, 0.1, 0);
                float yawRadians = entity.getYRot() * ((float) Math.PI / 180F);

                float width = 5f;
                float length = 5.0f;

                Vec3[] corners = new Vec3[4];
                corners[0] = new Vec3(-width / 2, 0, 1.0);      // 近端左侧
                corners[1] = new Vec3(width / 2, 0, 1.0);       // 近端右侧
                corners[2] = new Vec3(-width / 2, 0, 1.0 + length); // 远端左侧
                corners[3] = new Vec3(width / 2, 0, 1.0 + length);  // 远端右侧

                for (int i = 0; i < 4; i++) {
                    double rotatedX = corners[i].x * Math.cos(yawRadians) - corners[i].z * Math.sin(yawRadians);
                    double rotatedZ = corners[i].x * Math.sin(yawRadians) + corners[i].z * Math.cos(yawRadians);
                    corners[i] = new Vec3(rotatedX, corners[i].y, rotatedZ).add(center);
                }

                generateEnderRectangleParticles(level, corners, yawRadians, entity);
            }
        }, AnimationEvent.Side.CLIENT);
    }

    private static void generateEnderRectangleParticles(ClientLevel level, Vec3[] corners, float yawRadians, LivingEntity entity) {
        int particlesPerRow = 5;
        int particlesPerColumn = 12;

        for (int row = 0; row < particlesPerRow; row++) {
            for (int col = 0; col < particlesPerColumn; col++) {
                float u = (float) row / (particlesPerRow - 1);
                float v = (float) col / (particlesPerColumn - 1);

                Vec3 topPos = corners[0].add(corners[1].subtract(corners[0]).scale(u));
                Vec3 bottomPos = corners[2].add(corners[3].subtract(corners[2]).scale(u));
                Vec3 particlePos = topPos.add(bottomPos.subtract(topPos).scale(v));

                double randomOffsetX = (Math.random() - 0.5) * 0.3;
                double randomOffsetZ = (Math.random() - 0.5) * 0.3;

                level.addParticle(
                        ParticleTypes.PORTAL,
                        particlePos.x + randomOffsetX,
                        particlePos.y,
                        particlePos.z + randomOffsetZ,
                        0, 0.05f, 0
                );

                if (col % 3 == 0 && row % 2 == 0) {
                    level.addParticle(
                            ParticleTypes.END_ROD,
                            particlePos.x + randomOffsetX,
                            particlePos.y + 0.1f,
                            particlePos.z + randomOffsetZ,
                            0, 0.1f, 0
                    );
                }

                if (col % 3 == 0 && row % 2 == 0) {
                    level.addParticle(
                            ParticleTypes.SCULK_SOUL,
                            particlePos.x + randomOffsetX,
                            particlePos.y + 0.1f,
                            particlePos.z + randomOffsetZ,
                            0, 0.1f, 0
                    );
                }
            }
        }
    }

    public static AnimationEvent.InPeriodEvent particleTrail(int startFrame, int endFrame, InteractionHand hand, Vec3 startOffset, Vec3 endOffset, float timeInterpolation, int particleCount, ParticleOptions particleOptions) {
        return particleTrail(startFrame, endFrame, hand, startOffset, endOffset, timeInterpolation, particleCount, particleOptions, 0.0F);
    }

    public static AnimationEvent.InPeriodEvent particleTrail(int startFrame, int endFrame, InteractionHand hand, Vec3 startOffset, Vec3 endOffset, float timeInterpolation, int particleCount, ParticleOptions particleOptions, float random) {
        float start = (float) startFrame / 60.0F;
        float end = (float) endFrame / 60.0F;
        Joint joint = null;
        switch (hand) {
            case MAIN_HAND -> joint = Armatures.BIPED.get().handR;
            case OFF_HAND -> joint = Armatures.BIPED.get().handL;
        }

        Joint finalJoint = joint;
        return AnimationEvent.InPeriodEvent.create(start, end, (entityPatch, self, params) -> {
            AnimationPlayer player = entityPatch.getAnimator().getPlayerFor(null);
            float prevElapsedTime = player.getPrevElapsedTime();
            float elapsedTime = player.getElapsedTime();
            float step = (elapsedTime - prevElapsedTime) / timeInterpolation;
            Vec3 trailStartOffset = startOffset;
            Vec3 trailEndOffset = endOffset;
            Vec3f trailDirection = new Vec3f((float) (trailEndOffset.x - trailStartOffset.x), (float) (trailEndOffset.y - trailStartOffset.y), (float) (trailEndOffset.z - trailStartOffset.z));

            for (float f = prevElapsedTime; f <= elapsedTime; f += step) {
                for (int i = 0; i <= particleCount; ++i) {
                    float ratio = (float) i / (float) particleCount;
                    Vec3f pointOffset = new Vec3f((float) (trailStartOffset.x + (double) (trailDirection.x * ratio)), (float) (trailStartOffset.y + (double) (trailDirection.y * ratio)), (float) (trailStartOffset.z + (double) (trailDirection.z * ratio)));
                    double randX = (Math.random() - 0.5) * (double) random;
                    double randY = (Math.random() - 0.5) * (double) random;
                    double randZ = (Math.random() - 0.5) * (double) random;
                    Vec3 worldPos = AvalonAnimationUtils.getJointWorldRawPos(entityPatch, finalJoint, f + step, pointOffset);
                    if (entityPatch.getOriginal().level().isClientSide) {
                        entityPatch.getOriginal().level().addParticle(particleOptions, worldPos.x + randX, worldPos.y + randY, worldPos.z + randZ, 0.0, 0.0, 0.0);
                    }
                }
            }

        }, AnimationEvent.Side.CLIENT);
    }
}
