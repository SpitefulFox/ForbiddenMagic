package com.spiteful.forbidden.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import thaumcraft.api.IRepairable;

import com.spiteful.forbidden.Config;
import com.spiteful.forbidden.Forbidden;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTaintShovel extends ItemSpade implements IRepairable
{
	public IIcon icon;

	public ItemTaintShovel(ToolMaterial enumtoolmaterial)
	{
		super(enumtoolmaterial);
		this.setCreativeTab(Forbidden.tab);
		this.setHarvestLevel("shovel", 3);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.icon = ir.registerIcon("forbidden:taintshovel");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		return this.icon;
	}

	public EnumRarity getRarity(ItemStack itemstack)
	{
		return EnumRarity.rare;
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return par2ItemStack.isItemEqual(new ItemStack(Config.thaumcraftResourceID.getItem(), 1, 2)) ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta)
	{
		if (ForgeHooks.isToolEffective(stack, block, meta) || block.getMaterial() == Config.taintMaterial)
		{
			return efficiencyOnProperMaterial;
		}

		return func_150893_a(stack, block);
	}
	
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
		if(player != null && player.isPotionActive(Config.thaumcraftTaintPotionID)) {
			itemstack.damageItem(5, player);
			player.swingItem();
			if(player.worldObj.isRemote)
				player.removePotionEffectClient(Config.thaumcraftTaintPotionID);
			else {
				world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "thaumcraft:wandfail", 0.2F, 0.2F + world.rand.nextFloat() * 0.2F);
				player.removePotionEffect(Config.thaumcraftTaintPotionID);
			}
			return true;
		}
		return super.onItemUse(itemstack, player, world, x, y, z, side, par8, par9, par10);
	}
}
