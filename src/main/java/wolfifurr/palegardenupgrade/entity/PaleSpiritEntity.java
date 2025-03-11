package wolfifurr.palegardenupgrade.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.control.Control;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.Random;
import java.util.UUID;

public class PaleSpiritEntity extends HostileEntity implements Angerable {

    public PaleSpiritEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super (entityType, world);
        this.moveControl=new MyMoveControl (this);
    }

    public AnimationState idleAnimState = new AnimationState ();
    public AnimationState attackAnimState = new AnimationState ();
    public UUID angryAt;
    public int angrytime;
    public Vec3d spos;

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
            if (!this.idleAnimState.isRunning ()) {
                this.idleAnimState.start (this.age);
            }
        }
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

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes ()
                .add (EntityAttributes.MAX_HEALTH,14)
                .add (EntityAttributes.MOVEMENT_SPEED,0.25)
                .add (EntityAttributes.ENTITY_INTERACTION_RANGE,2)
                .add (EntityAttributes.ATTACK_DAMAGE,8)
                .add (EntityAttributes.FOLLOW_RANGE,20);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add (0,new SwimGoal (this));
        this.goalSelector.add (0,new FlyRandomlyGoal(this));
        this.goalSelector.add(9, new LookAtEntityGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
        this.targetSelector.add(0, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }

    static class FlyRandomlyGoal extends Goal {
        private final PaleSpiritEntity mob;
        private int lookTime;
        private double flyx;
        private double flyy;
        private double flyz;

        public FlyRandomlyGoal(PaleSpiritEntity mob) {
            this.mob = mob;
            this.setControls(EnumSet.of(Goal.Control.MOVE,Goal.Control.LOOK, Goal.Control.TARGET));
        }

        @Override
        public boolean canStart() {
            return this.mob.getRandom ().nextBetween (1,100)<5;
        }

        @Override
        public boolean shouldContinue() {
            return lookTime>=0;
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            this.lookTime--;
            if (this.mob.getTarget ()!=null) {
                LivingEntity player = this.mob.getTarget ();
                this.mob.getLookControl().lookAt(player, 30.0F, 30.0F);
                this.mob.moveControl.moveTo (player.getPos ().x , player.getPos ().y, player.getPos ().z, 4);
                if (this.mob.squaredDistanceTo (player)<2) {
                    this.mob.tryAttack (getServerWorld (this.mob), player);
                    this.mob.setAttacking (true);
                }
            } else {
                this.mob.moveControl.moveTo (this.mob.getPos ().x + this.flyx, this.mob.getPos ().y + this.flyy, this.mob.getPos ().z + this.flyz, 1);
                //this.mob.getLookControl().lookAt(this.mob.getPos ().x + this.flyx, this.mob.getEyeY (), this.mob.getPos ().z + this.flyz, 30.0F, 30.0F);
                Vec3d vec3d2 = this.mob.getVelocity();
                this.mob.setYaw(-((float)MathHelper.atan2(vec3d2.x, vec3d2.z)) * (180.0F / (float)Math.PI));
                //this.mob.bodyYaw = this.mob.getYaw();
            }
        }

        @Override
        public void stop() {
            super.stop ();
            this.mob.setAttacking (false);
        }

        @Override
        public void start() {
            if (this.mob.angryAt==null) {
                double x = (double) (this.mob.getRandom ().nextBetween (-4, 4)) /40;
                double y = (double) (this.mob.getRandom ().nextBetween (-4, 4)) /40;
                double z = (double) (this.mob.getRandom ().nextBetween (-4, 4)) /40;
                if (this.mob.spos==null) {
                    this.mob.spos=this.mob.getPos ();
                }
                if (this.mob.spos.distanceTo (new Vec3d (this.mob.getX () + x, this.mob.getY () + y, this.mob.getZ () + z)) < 10
                        && this.mob.getWorld ().getBlockState (new BlockPos ((int) Math.round (this.mob.getX () + x), (int) Math.round (this.mob.getY () + y),
                        (int) Math.round (this.mob.getZ () + z))).getBlock () == Blocks.AIR) {
                    this.flyx=x;
                    this.flyy=y;
                    this.flyz=z;
                    this.lookTime=20;
                }
            }else {
                this.lookTime=20;
            }
        }
    }

    class MyMoveControl extends MoveControl {
        public MyMoveControl(final PaleSpiritEntity owner) {
            super(owner);
        }

        @Override
        public void tick() {
            if (this.state == MoveControl.State.MOVE_TO) {
                Vec3d vec3d = new Vec3d(this.targetX - PaleSpiritEntity.this.getX(), this.targetY - PaleSpiritEntity.this.getY(), this.targetZ - PaleSpiritEntity.this.getZ());
                double d = vec3d.length();
                if (d < PaleSpiritEntity.this.getBoundingBox().getAverageSideLength()) {
                    this.state = MoveControl.State.WAIT;
                    PaleSpiritEntity.this.setVelocity(PaleSpiritEntity.this.getVelocity().add(vec3d.multiply(this.speed * 0.005 / d)));
                } else {
                    PaleSpiritEntity.this.setVelocity(PaleSpiritEntity.this.getVelocity().add(vec3d.multiply(this.speed * 0.005 / d)));
                    if (PaleSpiritEntity.this.getTarget() == null) {
                        Vec3d vec3d2 = PaleSpiritEntity.this.getVelocity();
                        PaleSpiritEntity.this.setYaw(-((float)MathHelper.atan2(vec3d2.x, vec3d2.z)) * (180.0F / (float)Math.PI));
                        PaleSpiritEntity.this.bodyYaw = PaleSpiritEntity.this.getYaw();
                    } else {
                        double e = PaleSpiritEntity.this.getTarget().getX() - PaleSpiritEntity.this.getX();
                        double f = PaleSpiritEntity.this.getTarget().getZ() - PaleSpiritEntity.this.getZ();
                        PaleSpiritEntity.this.setYaw(-((float)MathHelper.atan2(e, f)) * (180.0F / (float)Math.PI));
                        PaleSpiritEntity.this.bodyYaw = PaleSpiritEntity.this.getYaw();
                    }
                }
            }
        }
    }

    @Override
    protected boolean shouldTickBlockCollision() {
        return !this.isRemoved ();
    }

    @Override
    public void tick() {
        this.noClip = true;
        super.tick();
        this.noClip = false;
        this.setNoGravity(true);

        if (this.getRotationClient ()==null) {
            System.out.println ("?");
            this.setRotation (0,0);
        }

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
