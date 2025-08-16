package net.nitu.httydmod.entity.ai.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.pathfinder.PathType;
import net.nitu.httydmod.entity.DragonEntity;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class FollowRiderGoal extends Goal {
    private final DragonEntity tamableDragon;
    @Nullable
    private LivingEntity owner;
    private final double speedModifier;
    private final PathNavigation navigation;
    private int timeToRecalcPath;
    private final float stopDistance;
    private final float startDistance;
    private float oldWaterCost;

    public FollowRiderGoal(DragonEntity tamableDragon, double speedModifier, float startDistance, float stopDistance) {
        this.tamableDragon = tamableDragon;
        this.speedModifier = speedModifier;
        this.navigation = tamableDragon.getNavigation();
        this.startDistance = startDistance;
        this.stopDistance = stopDistance;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        if (!(tamableDragon.getNavigation() instanceof GroundPathNavigation) && !(tamableDragon.getNavigation() instanceof FlyingPathNavigation)) {
            throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
        }
    }

    public boolean canUse() {
        LivingEntity livingentity = this.tamableDragon.getOwner();
        if (livingentity == null) {
            return false;
        } else if (this.tamableDragon.unableToMoveToOwner()) {
            return false;
        } else if (this.tamableDragon.distanceToSqr(livingentity) < (double)(this.startDistance * this.startDistance)) {
            return false;
        } else {
            this.owner = livingentity;
            return true;
        }
    }

    public boolean canContinueToUse() {
        if (this.navigation.isDone()) {
            return false;
        } else {
            return this.tamableDragon.unableToMoveToOwner() ? false : !(this.tamableDragon.distanceToSqr(this.owner) <= (double)(this.stopDistance * this.stopDistance));
        }
    }

    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.tamableDragon.getPathfindingMalus(PathType.WATER);
        this.tamableDragon.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    public void stop() {
        this.owner = null;
        this.navigation.stop();
        this.tamableDragon.setPathfindingMalus(PathType.WATER, this.oldWaterCost);
    }

    public void tick() {
        boolean flag = this.tamableDragon.shouldTryTeleportToOwner();
        if (!flag) {
            this.tamableDragon.getLookControl().setLookAt(this.owner, 10.0F, (float)this.tamableDragon.getMaxHeadXRot());
        }

        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            if (flag) {
                this.tamableDragon.tryToTeleportToOwner();
            } else {
                this.navigation.moveTo(this.owner, this.speedModifier);
            }
        }

    }
}
