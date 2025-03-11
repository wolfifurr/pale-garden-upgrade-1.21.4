package wolfifurr.palegardenupgrade.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.AttackGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.Random;
import java.util.UUID;

public class HiddenWoodlingEntity extends HostileEntity implements Angerable {
    public HiddenWoodlingEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super (entityType, world);
    }
    public AnimationState idleAnimState = new AnimationState ();
    public AnimationState attackAnimState = new AnimationState ();
    public UUID angryAt;
    public int angrytime;
    //public int idleAnimTimeout=0;
    //public int attackAnimTimeout=0;


    @Override
    protected void initGoals() {
        this.goalSelector.add (0,new nAttackGoal (this));
        this.targetSelector.add(0, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }

    static class nAttackGoal extends Goal {

        private final MobEntity mob;
        private LivingEntity target;
        private int cooldown;
        private int cd;

        public nAttackGoal(MobEntity mob) {
            this.mob = mob;
            this.cd=60;
            this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
        }

        @Override
        public boolean canStart() {
            LivingEntity livingEntity = this.mob.getTarget();
            if (livingEntity == null) {
                return false;
            } else {
                this.target = livingEntity;
                return this.mob.squaredDistanceTo (this.target) > 3.0 && !this.mob.isAttacking ();
            }
        }

        @Override
        public boolean shouldContinue() {
            if (!this.target.isAlive()) {
                return false;
            } else {
                return this.cd>0;
            }
        }

        @Override
        public void stop() {
            this.target = null;
            this.mob.setAttacking (false);
            this.cd=60;
        }


        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            this.cd=Math.max (cd-1,0);
            double d = (double)(this.mob.getWidth() * 2.0F * this.mob.getWidth() * 2.0F);
            double e = this.mob.squaredDistanceTo(this.target.getX(), this.target.getY(), this.target.getZ());

            this.cooldown = Math.max(this.cooldown - 1, 0);
            if (!(e > d)) {
                this.mob.tryAttack(getServerWorld(this.mob), this.target);
                this.mob.setAttacking (true);
            }
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_CREAKING_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CREAKING_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_CREAKING_FREEZE;
    }

//    @Override
//    protected void playStepSound(BlockPos pos, BlockState state) {
//        if (state.isIn(BlockTags.CAMEL_SAND_STEP_SOUND_BLOCKS)) {
//            this.playSound(SoundEvents.ENTITY_CAMEL_STEP_SAND, 1.0F, 1.0F);
//        } else {
//            this.playSound(SoundEvents.ENTITY_CAMEL_STEP, 1.0F, 1.0F);
//        }
//    }

    public void setupAnimations() {
        if (this.isAttacking ()) {
            if (this.idleAnimState.isRunning ()) {
                this.idleAnimState.stop ();
            }
            if (!this.attackAnimState.isRunning ()) {
                this.attackAnimState.start (this.age);
            }
        } else {
            if (this.attackAnimState.isRunning ()) {
                this.attackAnimState.stop ();
            }
            if (!this.attackAnimState.isRunning () && !this.idleAnimState.isRunning ()) {
                this.idleAnimState.start (this.age);
            }
        }
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes ()
                .add (EntityAttributes.MAX_HEALTH,25)
                .add (EntityAttributes.MOVEMENT_SPEED,0)
                .add (EntityAttributes.ATTACK_DAMAGE,8)
                .add (EntityAttributes.FOLLOW_RANGE,20)
                .add (EntityAttributes.ENTITY_INTERACTION_RANGE,3)
                ;
    }

    @Override
    public void tick() {
        super.tick ();

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
