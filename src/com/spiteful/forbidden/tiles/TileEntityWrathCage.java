package com.spiteful.forbidden.tiles;

import com.spiteful.forbidden.DarkAspects;
import com.spiteful.forbidden.Config;

import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet132TileEntityData;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraftforge.common.ForgeDirection;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import cpw.mods.fml.common.FMLLog;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;


public class TileEntityWrathCage extends TileEntity implements IAspectContainer, IEssentiaTransport
{
    private final WrathSpawnerLogic spawnLogic = new WrathSpawnerLogic(this);
	public int wrath = 0;
	public int sloth = 0;
	public int special = 0;
	Aspect aspect;

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
    }
	
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		readFromNBT(pkt.data);
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag);
	}

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
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
				if(ic.canOutputTo(current.getOpposite()) && special < 64
					&& ic.getSuction(current.getOpposite()).getAmount(aspect) < this.getSuction(current).getAmount(aspect) && ic.takeVis(aspect, 1) == 1) {
					special++;
					worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					return;
				}
				else if(ic.canOutputTo(current.getOpposite()) && wrath < 64 && special < Config.wrathCost
					&& ic.getSuction(current.getOpposite()).getAmount(DarkAspects.WRATH) < this.getSuction(current).getAmount(DarkAspects.WRATH) && ic.takeVis(DarkAspects.WRATH, 1) == 1) {
					wrath++;
					worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					return;
				}
				else if(ic.canOutputTo(current.getOpposite()) && sloth < 64 && special < Config.wrathCost && wrath < Config.wrathCost
					&& ic.getSuction(current.getOpposite()).getAmount(DarkAspects.SLOTH) < this.getSuction(current).getAmount(DarkAspects.SLOTH) && ic.takeVis(DarkAspects.SLOTH, 1) == 1) {
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
    public boolean receiveClientEvent(int par1, int par2)
    {
        return this.spawnLogic.setDelayToMin(par1) ? true : super.receiveClientEvent(par1, par2);
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
			list.add(DarkAspects.WRATH, wrath).add(DarkAspects.SLOTH, sloth).add(aspect, special);
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
     * removes a bunch of different aspects and amounts from the tile entity.
     * @param ot the ObjectTags object that contains the aspects and their amounts.
     * @return true if all the aspects and their amounts were available and successfully removed
     */
    public boolean takeFromContainer(AspectList ot)
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
     * Checks if the tile entity contains all the listed aspects and their amounts
     * @param ot the ObjectTags object that contains the aspects and their amounts.
     * @return
     */
    public boolean doesContainerContain(AspectList ot){
		for (Aspect asp: ot.getAspects())
        {
            if(asp == DarkAspects.WRATH && wrath < ot.getAmount(DarkAspects.WRATH))
				return false;
			else if(asp == DarkAspects.SLOTH && sloth < ot.getAmount(DarkAspects.SLOTH))
				return false;
			else if(asp == aspect && special < ot.getAmount(aspect))
				return false;
			else if(asp != DarkAspects.WRATH && asp != DarkAspects.SLOTH && asp != aspect)
				return false;
        }
		return true;
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
     * Is this tile able to connect to other vis users/sources on the specified side?
     * @param face
     * @return
     */
    public boolean isConnectable(ForgeDirection face)
	{
		return true;
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
    public void setSuction(AspectList suction)
	{
	
	}

    /**
     * Sets the amount of suction this block will apply
     * @param suction
     */
    public void setSuction(Aspect aspect, int amount)
	{
	
	}

    /**
     * Returns the amount of suction this block is applying.
     * @param loc
     * 		the location from where the suction is being checked
     * @return
     */
    public AspectList getSuction(ForgeDirection face)
	{
		AspectList list = new AspectList();
		if(Config.wrathCost <= 0)
			return list;
		if(special < 64)
			list.add(aspect, 256);
		if(special < Config.wrathCost && wrath < 64)
			list.add(DarkAspects.WRATH, 256);
		if(special < Config.wrathCost && wrath < Config.wrathCost && sloth < 64)
			list.add(DarkAspects.SLOTH, 256);
		
		return list;
	}

    /**
     * remove the specified amount of vis from this transport tile
     * @param suction
     * @return how much was actually taken
     */
    public int takeVis(Aspect aspect, int amount)
	{
		return 0;
	}

    public AspectList getEssentia(ForgeDirection face)
	{
		return getAspects();
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
