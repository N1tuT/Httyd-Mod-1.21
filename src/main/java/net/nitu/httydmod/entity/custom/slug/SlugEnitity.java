package net.nitu.httydmod.entity.custom.slug;

import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.nitu.httydmod.entity.ModEntities;
import net.nitu.httydmod.entity.custom.hobgobbler.HobgobblerEntity;
import net.nitu.httydmod.entity.custom.hobgobbler.HobgobblerVariant;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class SlugEnitity extends Animal {
    private static final Set<Item> BREEDING_FOOD = Set.of(
            Items.ACACIA_LEAVES,
            Items.AZALEA_LEAVES,
            Items.BIRCH_LEAVES,
            Items.CHERRY_LEAVES,
            Items.DARK_OAK_LEAVES,
            Items.FLOWERING_AZALEA_LEAVES,
            Items.JUNGLE_LEAVES,
            Items.MANGROVE_LEAVES,
            Items.OAK_LEAVES,
            Items.SPRUCE_LEAVES
    );

    public final AnimationState idleAnimState = new AnimationState();
    private int idleAnimTimeout = 0;

    private static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(SlugEnitity.class, EntityDataSerializers.INT);

    public SlugEnitity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, this::isTamingOrBreedingItem, false));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.25));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 8.0)
                .add(Attributes.MOVEMENT_SPEED, 0.1)
                .add(Attributes.FOLLOW_RANGE, 24D);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return isTamingOrBreedingItem(itemStack);
    }

    protected boolean isTamingOrBreedingItem(ItemStack stack) {
        return BREEDING_FOOD.contains(stack.getItem());
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        SlugEnitity parent2 = (SlugEnitity) otherParent;
        SlugEnitity child = ModEntities.SLUG.get().create(level);
        if (child != null) {
            int i = this.random.nextInt(2);
            SlugVariant variant;
            if (i < 1) {
                variant = this.getVariant();
            } else if (i < 2) {
                variant = parent2.getVariant();
            } else {
                variant = Util.getRandom(SlugVariant.values(), this.random);
            }
            child.setVariant(variant);
        }

        return child;
    }

    private void setupAnimationStates() {
        if (this.idleAnimTimeout <= 0) {
            this.idleAnimTimeout = 80;
            this.idleAnimState.start(this.tickCount);
        } else {
            --this.idleAnimTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, 0);
    }

    private int getTypeVariant() { return this.entityData.get(VARIANT); }

    public SlugVariant getVariant() { return SlugVariant.byId(this.getTypeVariant() & 255); }

    private void setVariant(SlugVariant variant) { this.entityData.set(VARIANT, variant.getId() & 255); }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(VARIANT, compound.getInt("Variant"));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty,
                                        MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        SlugVariant variant = Util.getRandom(SlugVariant.values(), this.random);
        this.setVariant(variant);

        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }
}
