package com.spiteful.forbidden.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.IRepairable;

import com.spiteful.forbidden.Config;
import com.spiteful.forbidden.Forbidden;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDiabolistFork extends ItemSword implements IRepairable {
	
	public IIcon icon;
	
	public ItemDiabolistFork(ToolMaterial mat){
		super(mat);
		this.setCreativeTab(Forbidden.tab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		if(Config.spork)
			this.icon = ir.registerIcon("forbidden:spork");
		else
			this.icon = ir.registerIcon("forbidden:fork");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		return this.icon;
	}
	
	@Override
	/**
	 * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
	 * sword
	 */
	public float getDigSpeed(ItemStack stack, Block block, int meta)
	{
		return 1.0F;
	}
	
	@Override
	/**
	 * returns the action that specifies what animation to play when the items is being used
	 */
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.none;
	}
	
	@Override
	/**
	 * How long it takes to use or consume an item
	 */
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 0;
	}
	
	@Override
	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		return par1ItemStack;
	}
	
	@Override
	/**
	 * Return whether this item is repairable in an anvil.
	 */
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return par2ItemStack.getItem() == Items.quartz ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}
	
	@Override
	/**
	 * Returns if the item (tool) can harvest results from the block type.
	 */
	public boolean canHarvestBlock(Block par1Block, ItemStack itemStack)
	{
		return false;
	}
}