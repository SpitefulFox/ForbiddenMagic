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

public class ItemDivinewell extends Item implements IScribeTools {

	@SideOnly(Side.CLIENT)
	public Icon icon;
	
	public ItemDivinewell(int id) {
		super(id);
		maxStackSize = 1;
		canRepair = false;
		setMaxDamage(100);
		setCreativeTab(Forbidden.tab);
		setHasSubtypes(false);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir) {
		this.icon = ir.registerIcon("forbidden:divinewell");
	}

	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
		return this.icon;
	}
	
/*	@Override
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
	} */
	
	/**
     * Set the damage for this itemstack. Note, this method is responsible for zero checking.
     * @param stack the stack
     * @param damage the new damage value
     */
    public void setDamage(ItemStack stack, int damage)
    {

    }
	
}