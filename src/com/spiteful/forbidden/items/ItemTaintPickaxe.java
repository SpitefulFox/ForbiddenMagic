package com.spiteful.forbidden.items;

import com.spiteful.forbidden.Forbidden;
import com.spiteful.forbidden.Config;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.block.Block;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import thaumcraft.api.IRepairable;
import thaumcraft.api.ItemApi;
import net.minecraftforge.common.ForgeHooks;

public class ItemTaintPickaxe extends ItemPickaxe implements IRepairable
{
	public Icon icon;

	public ItemTaintPickaxe(int i, EnumToolMaterial enumtoolmaterial)
	{
		super(i, enumtoolmaterial);
		this.setCreativeTab(Forbidden.tab);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
		this.icon = ir.registerIcon("forbidden:taintpickaxe");
	}

	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1)
	{
		return this.icon;
	}

	public EnumRarity getRarity(ItemStack itemstack)
	{
		return EnumRarity.rare;
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return par2ItemStack.isItemEqual(new ItemStack(Config.thaumcraftResourceID, 1, 2)) ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Override
	public float getStrVsBlock(ItemStack stack, Block block, int meta)
	{
		if(block.blockHardness == 0.0F)
			return 0.0F;
		else if(block.blockHardness < 5.0F)
			return 0.1F;
		else if(block.blockHardness < 20.0F)
			return block.blockHardness / 2.0F;
		else
			return 5.0F + block.blockHardness;
			
	}
}
