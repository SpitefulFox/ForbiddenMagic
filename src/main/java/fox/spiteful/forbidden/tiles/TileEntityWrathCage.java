package fox.spiteful.forbidden.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;

import fox.spiteful.forbidden.Config;
import fox.spiteful.forbidden.DarkAspects;


public class TileEntityWrathCage extends TileEntity implements IAspectContainer, IEssentiaTransport
{
    private final WrathSpawnerLogic spawnLogic = new WrathSpawnerLogic(this);
    public int wrath = 0;
    public int sloth = 0;
    public int special = 0;
    public byte mode = 0;
    Aspect aspect = Aspect.GREED;

    @Override
    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        this.spawnLogic.readFromNBT(par1NBTTagCompound);
        this.wrath = par1NBTTagCompound.getShort("Wrath");
        this.sloth = par1NBTTagCompound.getShort("Sloth");
        this.special = par1NBTTagCompound.getShort("Special");
        this.mode = par1NBTTagCompound.getByte("Mode");
        aspect = spawnLogic.getAspect();
    }

    @Override
    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        this.spawnLogic.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("Wrath", (short)this.wrath);
        par1NBTTagCompound.setShort("Sloth", (short)this.sloth);
        par1NBTTagCompound.setShort("Special", (short)this.special);
        par1NBTTagCompound.setByte("Mode", this.mode);
    }
    
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
    
    @Override
    public Packet getDescriptionPacket()  {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbttagcompound);
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    @Override
    public void updateEntity()
    {
        if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)){
            this.spawnLogic.updateSpawner();
        }
        
        if(Config.wrathCost > 0 && spawnLogic.isMobSet() && !(special >= 64 || wrath >= 64 || sloth >= 64))
                drawEssentia();
        
        super.updateEntity();
    }
    
    void drawEssentia()
    {
        for(int x = 0;x < ForgeDirection.VALID_DIRECTIONS.length;x++){
            ForgeDirection current = ForgeDirection.VALID_DIRECTIONS[x];
            TileEntity te = ThaumcraftApiHelper.getConnectableTile(worldObj, xCoord, yCoord, zCoord, current);
            if(te != null) {
                IEssentiaTransport ic = (IEssentiaTransport)te;
                if(ic.canOutputTo(current.getOpposite()) && special < 64 //THE DIRECTION HERE MAY BE WRONG SPITEFULFOXY SO CHECK IT
                    && ic.getEssentiaType(current.getOpposite()) == aspect && ic.getEssentiaAmount(current.getOpposite()) > 0 && ic.takeEssentia(aspect, 1, current.getOpposite()) == 1) {
                    special++;
                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    return;
                }
                else if(ic.canOutputTo(current.getOpposite()) && wrath < 64 && special < Config.wrathCost
                    && ic.getEssentiaType(current.getOpposite()) == DarkAspects.WRATH && ic.getEssentiaAmount(current.getOpposite()) > 0 && ic.takeEssentia(DarkAspects.WRATH, 1, current.getOpposite()) == 1) {
                    wrath++;
                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    return;
                }
                else if(ic.canOutputTo(current.getOpposite()) && sloth < 64 && special < Config.wrathCost && wrath < Config.wrathCost
                    && ic.getEssentiaType(current.getOpposite()) == DarkAspects.SLOTH && ic.getEssentiaAmount(current.getOpposite()) > 0 && ic.takeEssentia(DarkAspects.SLOTH, 1, current.getOpposite()) == 1) {
                    sloth++;
                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    return;
                }
            }
        }
    }
    
    public void checkAspect() {
        if(aspect != spawnLogic.getAspect())
            special = 0;
        aspect = spawnLogic.getAspect();
    }
    
    /**
     * Called when a client event is received with the event number and argument, see World.sendClientEvent
     */
    public boolean receiveClientEvent(int par1, int x)
    {
        return this.spawnLogic.setDelayToMin(par1) ? true : super.receiveClientEvent(par1, x);
    }

    /**
     * Returns the spawner logic associated with this spawner
     */
    public WrathSpawnerLogic getSpawnerLogic()
    {
        return this.spawnLogic;
    }
    
    public AspectList getAspects()
    {
        AspectList list = new AspectList();
        if(Config.wrathCost > 0 && spawnLogic.isMobSet())
        {
            if(mode == 0)
                list.add(aspect, special);
            else if(mode == 1)
                list.add(DarkAspects.WRATH, wrath);
            else
                list.add(DarkAspects.SLOTH, sloth);
        }
        return list;
    }
    
    public void setAspects(AspectList aspects)
    {
    
    }
    
    /**
     * This method is used to determine of a specific aspect can be added to this container.
     * @param tag
     * @return true or false
     */
    public boolean doesContainerAccept(Aspect tag)
    {
        if(Config.wrathCost <= 0)
            return false;
        else
            return (tag == DarkAspects.WRATH || tag == DarkAspects.SLOTH || tag == aspect);
    }
    
    /**
     * This method is used to add a certain amount of an aspect to the tile entity.
     * @param tag
     * @param amount
     * @return the amount of aspect left over that could not be added.
     */
    public int addToContainer(Aspect tag, int amount)
    {
        if(Config.wrathCost <= 0)
            return amount;
        if(tag == DarkAspects.WRATH){
            wrath += amount;
            return 0;
        }
        else if(tag == DarkAspects.SLOTH){
            sloth += amount;
            return 0;
        }
        else if(tag == aspect){
            special += amount;
            return 0;
        }
        else
            return amount;
    }

    /**
     * Removes a certain amount of a specific aspect from the tile entity
     * @param tag
     * @param amount
     * @return true if that amount of aspect was available and was removed
     */
    public boolean takeFromContainer(Aspect tag, int amount)
    {
        return false;
    }

    /**
     * Checks if the tile entity contains the listed amount (or more) of the aspect
     * @param tag
     * @param amount
     * @return
     */
    public boolean doesContainerContainAmount(Aspect tag, int amount)
    {
        if(tag == DarkAspects.WRATH)
            return wrath >= amount;
        else if(tag == DarkAspects.SLOTH)
            return sloth >= amount;
        else if(tag == aspect)
            return special >= amount;
        else
            return false;
    }

    /**
     * Returns how much of the aspect this tile entity contains
     * @param tag
     * @return the amount of that aspect found
     */
    public int containerContains(Aspect tag)
    {
        if(tag == DarkAspects.WRATH)
            return wrath;
        else if(tag == DarkAspects.SLOTH)
            return sloth;
        else if(tag == aspect)
            return special;
        else
            return 0;
    }
    
    /**
     * Checks if the tile entity contains all the listed aspects and their amounts
     * @param ot the ObjectTags object that contains the aspects and their amounts.
     * @return
     * 
     * Going away in the next major patch
     */
    @Deprecated
    public boolean doesContainerContain(AspectList ot)
    {
        return false;
    }
    
    /**
     * removes a bunch of different aspects and amounts from the tile entity.
     * @param ot the ObjectTags object that contains the aspects and their amounts.
     * @return true if all the aspects and their amounts were available and successfully removed
     * 
     * Going away in the next major patch
     */
    @Deprecated
    public boolean takeFromContainer(AspectList ot)
    {
        return false;
    }
    
    /**
     * Is this tile able to connect to other vis users/sources on the specified side?
     * @param face
     * @return
     */
    public boolean isConnectable(ForgeDirection face)
    {
        if(Config.wrathCost > 0)
            return true;
        else
            return false;
    }

    /**
     * Is this side used to input essentia?
     * @param face
     * @return
     */
    public boolean canInputFrom(ForgeDirection face)
    {
        return true;
    }

    /**
     * Is this side used to output essentia?
     * @param face
     * @return
     */
    public boolean canOutputTo(ForgeDirection face)
    {
        return false;
    }

    /**
     * Sets the amount of suction this block will apply
     * @param suction
     */
    public void setSuction(Aspect aspect, int amount)
    {
    
    }

    //**
     //* Returns the amount of suction this block is applying.
     //* @param loc
     //*         the location from where the suction is being checked
     //* @return
     //*/
    //public AspectList getSuction(ForgeDirection face)
    //{
        //AspectList list = new AspectList();
        //if(Config.wrathCost <= 0)
            //return list;
        //if(special < 64)
            //list.add(aspect, 256);
        //if(special < Config.wrathCost && wrath < 64)
            //list.add(DarkAspects.WRATH, 256);
        //if(special < Config.wrathCost && wrath < Config.wrathCost && sloth < 64)
            //list.add(DarkAspects.SLOTH, 256);
        
        //return list;
    //}

    /**
     * Returns the type of suction this block is applying. 
     * @param loc
     *         the location from where the suction is being checked
     * @return
     *         a return type of null indicates the suction is untyped and the first thing available will be drawn
     */
    public Aspect getSuctionType(ForgeDirection face)
    {
        if(Config.wrathCost <= 0)
            return null;
        if(mode == 0)
            return aspect;
        else if(mode == 1)
            return DarkAspects.WRATH;
        else
            return DarkAspects.SLOTH;
    }
    
    /**
     * Returns the strength of suction this block is applying. 
     * @param loc
     *         the location from where the suction is being checked
     * @return
     */
    public int getSuctionAmount(ForgeDirection face)
    {
        if(Config.wrathCost > 0)
            return 256;
        else
            return 0;
    }
    
    /**
     * remove the specified amount of vis from this transport tile
     * @param suction
     * @return how much was actually taken
     */
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection dir)
    {
        return 0;
    }
    
    /**
     * add the specified amount of vis to this transport tile
     * @return how much was actually added
     */
    public int addEssentia(Aspect inp, int amount, ForgeDirection dir)
    {
        if(inp == aspect)
        {
            special += Math.min(amount, 64);
            return Math.min(amount, 64);
        }
        else if(inp == DarkAspects.WRATH)
        {
            wrath += Math.min(amount, 64);
            return Math.min(amount, 64);
        }
        else if(inp == DarkAspects.SLOTH)
        {
            sloth += Math.min(amount, 64);
            return Math.min(amount, 64);
        }
        else
            return amount;
    }
    
    /**
     * What type of essentia this contains
     * @param face
     * @return
     */
    public Aspect getEssentiaType(ForgeDirection face)
    {
        return getSuctionType(face);
    }
    
    /**
     * How much essentia this block contains
     * @param face
     * @return
     */
    public int getEssentiaAmount(ForgeDirection face)
    {
        if(mode == 0)
            return special;
        else if(mode == 1)
            return wrath;
        else
            return sloth;
    }

    /**
     * Essentia will not be drawn from this container unless the suction exceeds this amount.
     * @return the amount
     */
    public int getMinimumSuction()
    {
        return 9000;
    }

    /**
     * Return true if you want the conduit to extend a little further into the block.
     * Used by jars and alembics that have smaller than normal hitboxes
     * @return
     */
    public boolean renderExtendedTube()
    {
        return false;
    }
}
