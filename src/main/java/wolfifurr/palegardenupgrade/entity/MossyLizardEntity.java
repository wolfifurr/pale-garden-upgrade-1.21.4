package wolfifurr.palegardenupgrade.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.Random;
import java.util.UUID;

public class MossyLizardEntity extends PathAwareEntity implements Angerable {
    public MossyLizardEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super (entityType, world);
    }
    public AnimationState idleAnimState = new AnimationState ();
    public AnimationState attackAnimState = new AnimationState ();
    public AnimationState walkAnimState = new AnimationState ();
    public UUID angryAt;
    public int angrytime;

    public void setupAnimations() {
        if (this.isAttacking ()) {
            if (this.idleAnimState.isRunning ()) {
                this.idleAnimState.stop ();
            }
            this.attackAnimState.startIfNotRunning (this.age);
        } else {
            if (this.attackAnimState.isRunning ()) {
                this.attackAnimState.stop ();
            }
            this.idleAnimState.startIfNotRunning (this.age);
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add (0,new SwimGoal (this));
        this.goalSelector.add (0,new MossyLizardEntity.AttackGoal (this));
        this.goalSelector.add (1,new LookAroundGoal (this));
        this.goalSelector.add (2,new WanderAroundFarGoal (this,1));
        this.targetSelector.add(0, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SILVERFISH_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SILVERFISH_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SILVERFISH_HURT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_SILVERFISH_STEP, 1.0F, 1.0F);
    }

    static class AttackGoal extends Goal {
        private final MobEntity mob;
        private LivingEntity target;
        private int cooldown;

        public AttackGoal(MobEntity mob) {
            this.mob = mob;
            this.cooldown=20;
            this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
        }

        @Override
        public boolean canStart() {
            LivingEntity livingEntity = this.mob.getTarget();
            if (livingEntity == null) {
                return false;
            } else {
                this.target = livingEntity;
                return !this.mob.isAttacking ();
            }
        }

        @Override
        public boolean shouldContinue() {
            if (!this.target.isAlive()) {
                return false;
            } else {
                return this.canStart() && this.cooldown>0;
            }
        }

        @Override
        public void stop() {
            this.target = null;
            this.mob.getNavigation().stop();
            this.mob.setAttacking (false);
            this.cooldown=20;
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            this.mob.getLookControl().lookAt(this.target, 30.0F, 30.0F);
            double d = (double)(this.mob.getWidth() * 2.0F * this.mob.getWidth() * 2.0F)*2;
            double e = this.mob.squaredDistanceTo(this.target.getX(), this.target.getY(), this.target.getZ());

            double f = 0.6;

            this.mob.getNavigation().startMovingTo(this.target, f);
            this.cooldown = Math.max(this.cooldown - 1, 0);
            if (!(e > d)) {
                this.mob.tryAttack(getServerWorld(this.mob), this.target);
                this.mob.setAttacking (true);
            }
        }
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes ()
                .add (EntityAttributes.MAX_HEALTH,6)
                .add (EntityAttributes.MOVEMENT_SPEED,0.5)
                .add (EntityAttributes.ATTACK_DAMAGE,6)
                .add (EntityAttributes.FOLLOW_RANGE,20)
                ;
    }

    @Override
    public void tick() {
        super.tick ();

//        if (this.age-this.getLastAttackTime ()<5) {
//            this.setAttacking (true);
//        }else {
//            this.setAttacking (false);
//        }

        if (this.getWorld ().isClient) {
            this.setupAnimations ();
        }
    }

    @Override
    public int getAngerTime() {
        return this.angrytime;
    }

    @Override
    public void setAngerTime(int angerTime) {
        this.angrytime=angerTime;
    }

    @Nullable
    @Override
    public UUID getAngryAt() {
        return this.angryAt;
    }

    @Override
    public void setAngryAt(@Nullable UUID angryAt) {
        this.angryAt=angryAt;
    }

    @Override
    public void chooseRandomAngerTime() {
        this.angrytime=new Random ().nextInt (5,20);
    }
}
