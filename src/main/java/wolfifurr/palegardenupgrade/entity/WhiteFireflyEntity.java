package wolfifurr.palegardenupgrade.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class WhiteFireflyEntity extends FlyingEntity {
    public WhiteFireflyEntity(EntityType<? extends FlyingEntity> entityType, World world) {
        super (entityType, world);
        this.moveControl=new MoveControl (this);
        this.lookControl=new LookControl (this);
    }
    public AnimationState idleAnimState = new AnimationState ();
    public AnimationState flyAnimState = new AnimationState ();
    public Vec3d spos;

    public void setupAnimations() {
        this.flyAnimState.startIfNotRunning (this.age);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add (0,new SwimGoal (this));
        this.goalSelector.add (2,new LookAroundGoal (this));
        this.goalSelector.add (6,new FlyRandomlyGoal (this));
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        if (this.spos==null) {
            this.spos=this.getPos ();
        }
        return super.initialize (world, difficulty, spawnReason, entityData);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes ()
                .add (EntityAttributes.MAX_HEALTH,3)
                .add (EntityAttributes.MOVEMENT_SPEED,0.3)
                .add (EntityAttributes.FOLLOW_RANGE,20);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt (nbt);
        this.spos=new Vec3d (nbt.getDouble ("ppos_x"),nbt.getDouble ("ppos_y"),nbt.getDouble ("ppos_z"));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt (nbt);
        if (this.spos==null) {
            this.spos=this.getPos ();
        }
        nbt.putDouble ("ppos_x", this.spos.x);
        nbt.putDouble ("ppos_y", this.spos.y);
        nbt.putDouble ("ppos_z", this.spos.z);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getPos ()!=null && this.spos!=null) {
            if (this.getPos ().distanceTo (this.spos) >= 10) {
                this.spos = this.getPos ();
            }
        }

        if (this.getWorld ().isClient) {
            this.setupAnimations ();
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_BEE_POLLINATE;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_BEE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BEE_DEATH;
    }

    static class FlyRandomlyGoal extends Goal {
        private final WhiteFireflyEntity mob;
        private int lookTime;
        private double flyx;
        private double flyy;
        private double flyz;

        public FlyRandomlyGoal(WhiteFireflyEntity mob) {
            this.mob = mob;
            this.setControls(EnumSet.of(Control.MOVE,Goal.Control.LOOK));
        }

        @Override
        public boolean canStart() {
            return this.mob.getRandom ().nextBetween (1,20)<5;
        }

        @Override
        public boolean shouldContinue() {
            return lookTime>=0;
        }

        @Override
        public void start() {
            double x = (double) (this.mob.getRandom ().nextBetween (-4, 4)) /40;
            double y = (double) (this.mob.getRandom ().nextBetween (-4, 4)) /40;
            double z = (double) (this.mob.getRandom ().nextBetween (-4, 4)) /40;
            if (this.mob.spos==null) {
                this.mob.spos=this.mob.getPos ();
            }
            if (this.mob.spos.distanceTo (new Vec3d (this.mob.getX ()+x,this.mob.getY ()+y,this.mob.getZ ()+z))<10) {
                this.mob.setVelocity (x, y, z);
                this.flyx=x;
                this.flyy=y;
                this.flyz=z;
                this.lookTime=20;
            }
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            this.lookTime--;
            this.mob.getMoveControl ().moveTo (this.mob.getX ()+this.flyx,this.mob.getY ()+this.flyy,this.mob.getZ ()+this.flyz,1);
        }
    }

}
