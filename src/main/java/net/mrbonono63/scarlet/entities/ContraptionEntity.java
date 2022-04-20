package net.mrbonono63.scarlet.entities;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.mrbonono63.scarlet.Palette.BlockPalette;
import net.mrbonono63.scarlet.Palette.STrackedDataHandlerRegistry;
import net.mrbonono63.scarlet.blocks.SBlocks;
import org.jetbrains.annotations.Nullable;

public class ContraptionEntity extends Entity {

    public static final TrackedData<BlockPalette> BLOCK_PALETTE;

    static {
        BLOCK_PALETTE = DataTracker.registerData(ContraptionEntity.class, STrackedDataHandlerRegistry.BLOCK_PALETTE);
    }

    private float yaw;
    private float pitch;
    private float roll;

    private double prevX;
    private double prevY;
    private double prevZ;

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

            but it would be cool
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
        IN_AIR
    }

    public ContraptionEntity(EntityType<?> type, World world)
    {
        super(type, world);
        this.intersectionChecked = true;
    }

    //constructor
    public ContraptionEntity(EntityType<?> type, World world, double x, double y, double z) {
        super(type, world);
        this.world = world;
        this.setPosition(x,y,z);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }

    //Assemble and Disassemble

    public void Assemble()
    {}

    public void Disassemble()
    {}

    //retrieves the entities internal block palette
    public BlockPalette getPalette()
    {
        return this.dataTracker.get(BLOCK_PALETTE);
    }

    //sets the entities internal block palette
    public void setPalette(BlockPalette blockPalette) {
        this.dataTracker.set(BLOCK_PALETTE, blockPalette);
    }

    //Entity NBT Data
    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(BLOCK_PALETTE, new BlockPalette());
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

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
        this.setBoundingBox(new Box(0,0,0, 1,1,1));
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    //Player Interactions

    @Override
    public ActionResult interactAt(PlayerEntity player, Vec3d hitPos, Hand hand) {
        System.out.println(player.getDisplayName() + "Interacted with " + getEntityName());
        return ActionResult.SUCCESS;
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
    public int getSafeFallDistance() {
        return 1000;
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
