package net.mrbonono63.scarlet.entities;


import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.EulerAngle;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.mrbonono63.scarlet.Main;
import net.mrbonono63.scarlet.blocks.SBlocks;
import net.mrbonono63.scarlet.server.Contraption;
import net.mrbonono63.scarlet.util.NBTReadBlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class ContraptionEntity extends Entity {

    //Local instance of the contraption entity that is then added to the Contraption List
    Contraption contraption = new Contraption(new Identifier("", ""), new BlockPos(0,0,0), new BlockPos(0,0,0), new BlockPos(0,0,0), new BlockPos(0,0,0));
    // Is used to delete the contraption from the List
    private int contraptionListIndex = 0;
    private World world;

    private EulerAngle rotation = new EulerAngle(0.0f, 0.0f, 0.0f);

    //similar to how boats function
    private boolean pressingForward = false;
    private boolean pressingBackward = false;
    private boolean pressingRight = false;
    private boolean pressingLeft = false;

    /*
            similar to the boat entities wood type enum
            however there will be changes to how the entity reacts to its environment based on these factors
            future example, Submarines will remove specifically the water overlay from displaying and
            will remove the oxygen bar but oxygen will also have an HUD element or guage on a terminal
            displaying how much oxygen is left inside the submarine
            there will also be a display denoting how much pressure is on the submarine based on the highest
            point where an air block is (will determine depth)
            and another display denoting how much ballast is being stored inside the vessel,
            ballast capacity will be determined upon creation of the submarine structure and will
            correlate with its maximum capable depth
            (even if the pressure would be too much for the vessel to handle)
            at maximum pressure the submarine will start to leak and air pressure will increase inside the
            vessel (yet another variable to take into consideration)
            How the submarine will be propelled is also into question,
            so this contraption will certainly be far down the pipeline,
            airships, boats, and trains are all considerable more popular and there isn't too
            much practicality in submarines inside of Minecraft

            but it would be cool (its gonna happen whether or not you like it)
        */
    //Contraption entity types
    private enum Type{
        AIRSHIP,
        BOAT,
        SUBMARINE,
        TRAIN
    }

    //similar to the boat entity
    //Will be updated every tick and determine based on the contraption type how it behaves
    private enum Location{
        IN_WATER,
        UNDER_WATER,
        ON_LAND,
        IN_AIR,
        IN_SPACE
    }

    //Used when making the entity form a block
    public ContraptionEntity(EntityType<?> type, World world, Contraption contraption)
    {
        super(type, world);
        //this.intersectionChecked = true;
        this.contraption = contraption;
        this.world = world;
    }

    public ContraptionEntity(EntityType<?> type, World world)
    {
        super(type, world);
        this.world = world;
        //this.intersectionChecked = true;
    }

    //Assemble and Disassemble

    public void Assemble(Contraption contraption)
    {
        Main.contraptionDimensionHandler.addContraption(contraption);
        // this needs to be called immediately after the contraption is added to the end of the list, otherwise it will
        // delete the wrong contraption from the contraptionList in Main.
        this.contraptionListIndex = Main.contraptionDimensionHandler.getListSize();
        //decide how many maximum passengers there are and where the mount points are located in the entity
        //Also needs to be done for block entities and regular blocks that can be interacted with, ( really just if
        // possible every interaction including breaking blocks needs to be sent to the contraption inside the
        // dimension to allow for literally all functionality from vanilla and mods being the best solution)
    }

    public void Disassemble()
    {
        Main.contraptionDimensionHandler.removeContraption(this.contraptionListIndex);
    }

    /*
        Entity NBT Data and data tracker

        Remember NBT data is for local server use (saving and loading then being used server side)
        While Tracked Data is synced between the client and server and is important for things like models etc.
     */
    @Override
    protected void initDataTracker() {}

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        this.rotation = new EulerAngle(nbt.getFloat("yaw"), nbt.getFloat("pitch"), nbt.getFloat("roll"));

        this.contraption = readContraptionNBTData(nbt);
        Main.LOGGER.info(this.contraption.getOriginDimensionID().toString() + " " + this.contraption.getOriginCorner1() + " " + this.contraption.getOriginCorner2() + " " + this.contraption.getContraptionCorner1() + " " + this.contraption.getContraptionCorner2());
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putFloat("yaw", this.rotation.getYaw());
        nbt.putFloat("pitch", this.rotation.getPitch());
        nbt.putFloat("roll", this.rotation.getRoll());

        //write the contraption data
        writeContraptionData(nbt);
    }

    public void writeContraptionData(NbtCompound nbt)
    {
        //write the namespace of the dimension
        nbt.putString("namespace", this.contraption.originDimensionID.getNamespace());
        nbt.putString("dim_id", this.contraption.originDimensionID.getPath());
        NBTReadBlockPos.writeBlockPos("originBlockPos", this.contraption.getOriginCorner1(), nbt);
        NBTReadBlockPos.writeBlockPos("originBlockPos_", this.contraption.getOriginCorner2(), nbt);
        NBTReadBlockPos.writeBlockPos("contraptionBlockPos", this.contraption.getContraptionCorner1(), nbt);
        NBTReadBlockPos.writeBlockPos("contraptionBlockPos_", this.contraption.getContraptionCorner2(), nbt);
    }

    public Contraption readContraptionNBTData(NbtCompound nbt)
    {
        BlockPos originBlockPos = NBTReadBlockPos.readBlockPos("originBlockPos",nbt) ;
        BlockPos originBlockPos_ = NBTReadBlockPos.readBlockPos("originBlockPos_",nbt) ;
        BlockPos contraptionBlockPos = NBTReadBlockPos.readBlockPos("contraptionBlockPos",nbt) ;
        BlockPos contraptionBlockPos_ = NBTReadBlockPos.readBlockPos("contraptionBlockPos_",nbt) ;

        return new Contraption(new Identifier (nbt.getString("namespace"), nbt.getString("dim_id")), originBlockPos, originBlockPos_, contraptionBlockPos, contraptionBlockPos_);
    }

    //Uses what everything else uses I don't know what else I would use anyway.
    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    //Base Tick
    //Essentially the main entity loop, happens every tick
    @Override
    public void baseTick() {
        this.checkBlockCollision();
        //this.setBoundingBox(new Box(0,0,0, 1,1,1));

        if (contraption == null)
        {
            Main.LOGGER.info(getEntityName()+" is missing contraption data");
        }

        RegistryEntry<DimensionType> world_registry = world.method_40134();
        world_registry.getType();
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    //Player Interactions

    @Override
    public ActionResult interactAt(PlayerEntity player, Vec3d hitPos, Hand hand) {
        if (player.shouldCancelInteraction())
        {
            return ActionResult.PASS;
        } else
        {
            System.out.println(player.getDisplayName() + "Interacted with " + getEntityName() + " at " + hitPos.toString());
            return ActionResult.SUCCESS;
        }
    }

    //Passenger handling TODO set the maximum number of passengers to the number of seats available on assembly of the entity instead of an arbitrary amount, this will be handled when entity assembly is added.
    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return this.getPassengerList().size() < 1;
    }

    @Override
    public void updatePassengerPosition(Entity passenger) {
        if (this.hasPassenger(passenger) && this.getPrimaryPassenger() instanceof PlayerEntity) {
            if (passenger == this.getPrimaryPassenger()) {
                passenger.updatePosition(this.getX(), this.getY() + 0.5, this.getZ());
            }
        }
    }

    @Override
    public double getMountedHeightOffset() {
        return -0.1D;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    //TODO handle inputs from the player and change the velocity accordingly
    public void setInputs(boolean forward, boolean backward, boolean right, boolean left)
    {
        this.pressingLeft = left;
        this.pressingRight = right;
        this.pressingForward = forward;
        this.pressingBackward = backward;
    }

    //"Physics" TODO add actual physics code to the contraption entity so it is better than a boat
    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    public boolean collidesWith(Entity other) {
        return true;
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public void onStruckByLightning(ServerWorld world, LightningEntity lightning) {
    }

    /*
        returns the contraption core when the player does the pick block action
        could be changed in the future to be the specific block they are looking at on the entity
        however that is farther down the pipeline since that requires a stored in memory block array
        outside the entity model.
    */
    @Nullable
    @Override
    public ItemStack getPickBlockStack() {
        return new ItemStack(SBlocks.CONTRAPTION_CORE);
    }
}
