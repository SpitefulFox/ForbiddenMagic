package com.spiteful.forbidden.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.IScribeTools;
import WayofTime.alchemicalWizardry.api.items.interfaces.IBindable;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;

import com.spiteful.forbidden.Compat;
import com.spiteful.forbidden.Forbidden;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBloodwell extends Item implements IScribeTools, IBindable {

	@SideOnly(Side.CLIENT)
	public IIcon icon;
	
	public ItemBloodwell() {
		maxStackSize = 1;
		canRepair = false;
		setMaxDamage(5);
		setCreativeTab(Forbidden.tab);
		setHasSubtypes(false);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir) {
		this.icon = ir.registerIcon("forbidden:bloodwell");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int par1) {
		return this.icon;
	}
	
	/**
	* Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
	* update it's contents.
	*/
	@Override
	public void onUpdate(ItemStack stack, World world, Entity fauxplayer, int par4, boolean par5)
	{
		if(Compat.bm && stack.getItemDamage() > 0 && stack.hasTagCompound())
		{
			EntityPlayer player = null;
			if(fauxplayer instanceof EntityPlayer)
				player = (EntityPlayer)fauxplayer;
			else
				return;
			
			try
			{
				if(player.capabilities.isCreativeMode)
					stack.setItemDamage(0);
				else if(SoulNetworkHandler.syphonFromNetwork(stack, 25) > 0)
					stack.setItemDamage(stack.getItemDamage() - 1);
				else if(player.getHealth() > 6)
				{
					player.setHealth(player.getHealth() - 2);
					stack.setItemDamage(stack.getItemDamage() - 1);
				}
			}
			catch(Throwable e){}
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World par2World, EntityPlayer player)
	{
		if(Compat.bm)
		{
			try
			{
				SoulNetworkHandler.checkAndSetItemOwner(itemstack, player);
			}
			catch (Throwable e){}
		}
		return itemstack;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		if (stack.hasTagCompound())
		{
			list.add("");
			list.add("Current owner: " + stack.stackTagCompound.getString("ownerName"));
		}
	}
	
	/**
     * Set the damage for this itemstack. Note, this method is responsible for zero checking.
     * @param stack the stack
     * @param damage the new damage value
     */
	@Override
    public void setDamage(ItemStack stack, int damage)
    {
		if(damage > 0){
			
			try
			{
				if(SoulNetworkHandler.syphonFromNetwork(stack, 25 * damage) > 0)
					super.setDamage(stack, 0);
				else
					super.setDamage(stack, damage);
			}
			catch(Throwable e)
			{
				super.setDamage(stack, damage);
			}
		}
		else
			super.setDamage(stack, damage);
    }

}