package com.spiteful.forbidden.items;

import com.spiteful.forbidden.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import thaumcraft.api.IScribeTools;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import WayofTime.alchemicalWizardry.api.items.interfaces.IBindable;

public class ItemBloodwell extends Item implements IScribeTools, IBindable {

	@SideOnly(Side.CLIENT)
	public Icon icon;
	
	public ItemBloodwell(int id) {
		super(id);
		maxStackSize = 1;
		canRepair = false;
		setMaxDamage(100);
		setCreativeTab(Forbidden.tab);
		setHasSubtypes(false);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir) {
		this.icon = ir.registerIcon("forbidden:bloodwell");
	}

	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
		return this.icon;
	}
	
	/**
	* Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
	* update it's contents.
	*/
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
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		if (par1ItemStack.hasTagCompound())
		{
			par3List.add("");
			par3List.add("Current owner: " + par1ItemStack.stackTagCompound.getString("ownerName"));
		}
	}

}