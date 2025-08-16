package net.nitu.httydmod.entity.custom.nightterror;

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
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.nitu.httydmod.entity.DragonEntity;
import net.nitu.httydmod.entity.ModEntities;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class NightTerrorEntity extends DragonEntity {
    private static final Set<Item> TAMING_FOODS = Set.of(
            Items.PUMPKIN.asItem()
    );

    @Override
    protected int getMaxDragonTemper() {
        return 80;
    }

    private static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(NightTerrorEntity.class, EntityDataSerializers.INT);

    public NightTerrorEntity(EntityType<? extends AbstractHorse> entityType, Level level) { super(entityType, level); }

    public static AttributeSupplier.Builder createAttributes() {
        return createBaseAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.2D);
    }

    @Override
    protected DragonEntity createChild(DragonEntity otherParent, ServerLevel level) {
        NightTerrorEntity parent2 = (NightTerrorEntity) otherParent;
        NightTerrorEntity child = ModEntities.NIGHT_TERROR.get().create(level);

        if (child != null) {
            int i = this.random.nextInt(2);
            NightTerrorVariant variant;
            if (i < 1) {
                variant = this.getVariant();
            } else if (i < 2) {
                variant = parent2.getVariant();
            } else {
                variant = Util.getRandom(NightTerrorVariant.values(), this.random);
            }
            child.setVariant(variant);
        }

        return child;
    }

    @Override
    public boolean canRide(Player player) {
        return false;
    }

    @Override
    protected boolean isTamingOrBreedingItem(ItemStack stack) { return TAMING_FOODS.contains(stack.getItem()); }


    /* VARIANT */

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, 0);
    }

    public int getTypeVariant() {
        return this.entityData.get(VARIANT);
    }

    public NightTerrorVariant getVariant() {
        int id = this.getTypeVariant();
        return NightTerrorVariant.byId(id & 255);
    }

    private void setVariant(NightTerrorVariant variant) {
        this.entityData.set(VARIANT, variant.getId() & 255);
    }

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
        NightTerrorVariant variant = Util.getRandom(NightTerrorVariant.values(), this.random);
        this.setVariant(variant);

        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }
}
