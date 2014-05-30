package com.spiteful.forbidden.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.IScribeTools;

import com.spiteful.forbidden.Forbidden;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDivinewell extends Item implements IScribeTools {

	@SideOnly(Side.CLIENT)
	public IIcon icon;
	
	public ItemDivinewell() {
		maxStackSize = 1;
		canRepair = false;
		setMaxDamage(100);
		setCreativeTab(Forbidden.tab);
		setHasSubtypes(false);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir) {
		this.icon = ir.registerIcon("forbidden:divinewell");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
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
		super.setDamage(stack, 0);
    }
	
}