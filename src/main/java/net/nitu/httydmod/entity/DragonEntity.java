package net.nitu.httydmod.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.UUID;

public abstract class DragonEntity extends AbstractHorse{
    // setup idle animations
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    @Override
    protected void registerGoals() {
        // what entity does
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.5, this::isTamingOrBreedingItem, false));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.2D, 20.0F, 60.0F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createBaseAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 24D);
    }

    protected DragonEntity(EntityType<? extends AbstractHorse> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return this.isTamingOrBreedingItem(itemStack);
    }

    // Each dragon type must define its food
    protected abstract boolean isTamingOrBreedingItem(ItemStack stack);

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return createChild((DragonEntity) otherParent, level);
    }

    protected abstract DragonEntity createChild(DragonEntity otherParent, ServerLevel level);

    @Override
    public boolean canMate(Animal otherAnimal) {
        if (otherAnimal == this) return false;

        if (!(otherAnimal instanceof DragonEntity otherDragon)) return false;

        return this.isTamed() && otherDragon.isTamed() && this.isInLove() && otherDragon.isInLove();
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 20;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }

        if (this.buckTimer > 0) {
            this.buckTimer--;

            if (this.buckTimer == 0 && this.buckTarget != null) {
                Player player = this.level().getPlayerByUUID(this.buckTarget);

                if (player != null && this.hasPassenger(player)) {
                    this.ejectPassengers(); // buck off
                    this.level().broadcastEntityEvent(this, (byte) 6); // Smoke particles
                }
                this.buckTarget = null;
            }
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // Feed dragon
        if (!stack.isEmpty() && this.isFood(stack) && !this.isVehicle()) {
            stack.shrink(1); // consume food
            this.gameEvent(GameEvent.EAT);
            this.playSound(SoundEvents.GENERIC_EAT, 1.0F, 1.0F);

            // if baby, feed to grow
            if (this.isBaby()){
                this.ageUp((int)((float)(-this.getAge()) / 20.0F * 0.1F), true);
                return InteractionResult.SUCCESS;
            }

            // if wild adult, feed to tame
            if (!this.isTamed()) {
                this.tryToTame(player);
                return InteractionResult.SUCCESS;
            }

            // if tamed adult, feed to breed
            if (this.isTamed() && !this.isInLove()) {
                this.setInLove(player);
                return InteractionResult.SUCCESS;
            }
        }

        // Riding interactions
        if (!this.isBaby() && stack.isEmpty() && !this.isVehicle()) {
            if (this.canRide(player)) {
                player.startRiding(this);

                // Buck only if untamed
                if (this.hasPassenger(player) && !this.isTamed()) {
                    this.startBucking(player);
                }
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
        }

        // Default fallback
        return InteractionResult.PASS;
    }

    /* RIDEABLE */

    // Attaching player and saddle
    public abstract boolean canRide(Player player);

    @Override
    public boolean canUseSlot(EquipmentSlot slot) {
        return true;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(Entity entity, EntityDimensions dimensions, float partialTick) {
        // Base seat position relative to dragon
        Vec3 seatPos = new Vec3(0, 0.25, 0.133);

        // Bobbing amount — this can be linked to walking animation
        double bob = Math.sin((this.tickCount + partialTick) * 0.2F) * 0.05F; // small up/down oscillation

        // Apply bob to the Y coordinate
        seatPos = seatPos.add(0.0, bob, 0.0);

        // Rotate seat with dragon's facing direction
        return seatPos.yRot(-this.getYRot() * ((float) Math.PI / 180F));
    }

    // Ejecting player
    private int buckTimer = 0;
    private UUID buckTarget;
    protected abstract int getMaxDragonTemper();

    private void startBucking (Player player) {
        this.buckTimer = this.random.nextInt(40) + 20;
        this.buckTarget = player.getUUID();
    }

    // Taming logic
    private void tryToTame(Player player) {
        int maxTemper = this.getMaxDragonTemper();

        if (this.temper <= 0) {
            this.temper = 0;
        }

        this.temper += 5 + this.random.nextInt(5);

        if (this.temper >= maxTemper) {
            this.tameWithName(player);
            this.navigation.stop();
            this.setTarget(null);
        } else {
            this.level().broadcastEntityEvent(this, (byte) 6); // Smoke
        }
    }
}
