package wolfifurr.palegardenupgrade.animations;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class HiddelWoodlingAnimations {

    public static final Animation IDLE = Animation.Builder.create(0f).looping()
            .addBoneAnimation("fore_arm1",
                    new Transformation (Transformation.Targets.ROTATE,
                            new Keyframe (0f, AnimationHelper.createRotationalVector(-90f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("last_arm1",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(90f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("fore_arm2",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, -90f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("last_arm2",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, -90f, 90f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("fore_arm3",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(62.99f, -28.79f, 46.68f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("last_arm3",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(-61.36f, 27.13f, -126.68f),
                                    Transformation.Interpolations.LINEAR))).build();
    public static final Animation ATTACK = Animation.Builder.create(1f)
            .addBoneAnimation("fore_arm1",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(-90f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(-97.5f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5f, AnimationHelper.createRotationalVector(-115f, -7.5f, -90f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.75f, AnimationHelper.createRotationalVector(112.5f, -7.5f, -90f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(-90f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("last_arm1",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(90f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(-40f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5f, AnimationHelper.createRotationalVector(-60f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.75f, AnimationHelper.createRotationalVector(95f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(90f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("fore_arm2",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, -90f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(0f, 0f, -97.5f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5f, AnimationHelper.createRotationalVector(-5f, 80f, -97.5f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.75f, AnimationHelper.createRotationalVector(-71.84f, -80.55f, 158.91f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(0f, 0f, -90f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("last_arm2",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, -90f, 90f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(90f, 0f, -17.5f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5f, AnimationHelper.createRotationalVector(90f, 0f, -25f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.75f, AnimationHelper.createRotationalVector(90f, 0f, 67.5f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(0f, -90f, 90f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("fore_arm3",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(62.99f, -28.79f, 46.68f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(66.83f, -33.4f, 48.92f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5f, AnimationHelper.createRotationalVector(74.74f, 8.61f, 106.42f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.75f, AnimationHelper.createRotationalVector(-144.76f, -27.42f, 62.65f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(62.99f, -28.79f, 46.68f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("last_arm3",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(-61.36f, 27.13f, -126.68f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(30.39f, 2.29f, 31.19f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5f, AnimationHelper.createRotationalVector(40.43f, -1.51f, 37.66f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.75f, AnimationHelper.createRotationalVector(-57.71f, -4.24f, -39.12f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(-61.36f, 27.13f, -126.68f),
                                    Transformation.Interpolations.CUBIC))).build();
}
