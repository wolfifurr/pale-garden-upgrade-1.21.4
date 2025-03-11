package wolfifurr.palegardenupgrade.entity;

import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.potion.Potions;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.UUID;

public class HauntedFlowerEntity extends HostileEntity implements Angerable {
    public HauntedFlowerEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super (entityType, world);
        this.attackCooldown=0;
    }
    
    @Override
    protected void initGoals() {
        this.goalSelector.add (0,new SwimGoal (this));
        this.goalSelector.add (4,new ReleasePotion (this));
        this.targetSelector.add(0, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        if (source.getSource ()!=null) {
            if (source.getSource ().getClass ()==AreaEffectCloudEntity.class) {
                return false;
            }
        }
        return super.damage (world, source, amount);
    }

    static class ReleasePotion extends Goal {
        
        public HauntedFlowerEntity mob;
        public int effectCooldown;
        public boolean effectSpawn;

        public ReleasePotion(HauntedFlowerEntity mob) {
            this.mob=mob;
        }
        
        @Override
        public boolean canStart() {
            return this.mob.attackCooldown<=0;
        }

        @Override
        public void stop() {
            if (this.effectSpawn && this.mob.attackCooldown<=0) {
                this.effectSpawn=false;
            }
            this.mob.setAttacking (false);
            super.stop ();
        }

        @Override
        public void start() {
            if (this.mob.getTarget ()!=null && this.mob.attackCooldown<=0) {
                System.out.println (this.mob.squaredDistanceTo (this.mob.getTarget ().getPos ()));
                if (this.mob.squaredDistanceTo (this.mob.getTarget ().getPos ())<21) {
                    this.mob.attackCooldown = 60;
                    this.effectCooldown = 20;
                    this.effectSpawn=true;
                    this.mob.setAttacking (true);
                }
            }
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public boolean shouldContinue() {
            return effectCooldown>=0;
        }

        @Override
        public void tick() {
            this.effectCooldown--;
            if (this.effectCooldown==10) {
                PotionContentsComponent potion = new PotionContentsComponent (Potions.HARMING);
                AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity (getServerWorld (this.mob), this.mob.getX (), this.mob.getY (), this.mob.getZ ());
                areaEffectCloudEntity.setOwner (this.mob);
                areaEffectCloudEntity.setRadius (6.0F);
                areaEffectCloudEntity.setWaitTime (60);
                areaEffectCloudEntity.setRadiusGrowth (-areaEffectCloudEntity.getRadius () / (float) areaEffectCloudEntity.getDuration ());
                areaEffectCloudEntity.setPotionContents (potion);
                getServerWorld (this.mob).spawnEntity (areaEffectCloudEntity);
            }
        }
    }

    public AnimationState idleAnimState = new AnimationState ();
    public AnimationState attackAnimState = new AnimationState ();
    public UUID angryAt;
    public int angrytime;
    public int attackCooldown;

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
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.BLOCK_CAVE_VINES_BREAK;
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes ()
                .add (EntityAttributes.MAX_HEALTH,6)
                .add (EntityAttributes.MOVEMENT_SPEED,0)
                .add (EntityAttributes.ATTACK_DAMAGE,3)
                .add (EntityAttributes.FOLLOW_RANGE,20)
                .add (EntityAttributes.ENTITY_INTERACTION_RANGE,4)
                ;
    }

    @Override
    public void tick() {
        super.tick ();

        if (this.attackCooldown>0) {
            this.attackCooldown--;
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
