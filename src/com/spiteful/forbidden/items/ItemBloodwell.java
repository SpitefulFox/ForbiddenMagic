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

public class ItemBloodwell extends Item implements IScribeTools {

	@SideOnly(Side.CLIENT)
	public Icon icon;
	
	Method checkOwner;
	Method syphon;

	public ItemBloodwell(int id) {
		super(id);
		maxStackSize = 1;
		canRepair = false;
		setMaxDamage(100);
		setCreativeTab(Forbidden.tab);
		setHasSubtypes(false);
		
		try
		{
			Class energyItems;
			energyItems = Class.forName("WayofTime.alchemicalWizardry.common.items.EnergyItems");

			checkOwner = energyItems.getDeclaredMethod("checkAndSetItemOwner", new Class[] {ItemStack.class, EntityPlayer.class});
			syphon = energyItems.getDeclaredMethod("syphonWhileInContainer", new Class[] {ItemStack.class, Integer.TYPE});
		}
		catch(Exception e){
		e.printStackTrace(); }
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
				else if(((Boolean)(syphon.invoke(null, stack, 25))).booleanValue())
					stack.setItemDamage(stack.getItemDamage() - 1);
				else if(player.getHealth() > 6)
				{
					player.setHealth(player.getHealth() - 2);
					stack.setItemDamage(stack.getItemDamage() - 1);
				}
			}
			catch(Exception e){}
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		try
		{
			checkOwner.invoke(null, par1ItemStack, par3EntityPlayer);
		}
		catch (Exception e){}
		return par1ItemStack;
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